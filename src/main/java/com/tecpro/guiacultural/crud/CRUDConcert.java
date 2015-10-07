/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.crud;

import com.tecpro.guiacultural.models.Concert;
import com.tecpro.guiacultural.models.Organizer;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/**
 *
 * @author joako
 */
public class CRUDConcert extends CRUDEvent {

    public Concert create(String name, String location, float price, String date_from, String date_until, String hour_from, String hour_until,
            int organizer_id, String description, String musician, String genre) {
        openBase();
        Base.openTransaction();
        Concert concert = Concert.create("name", name,
                "location", location,
                "price", price,
                "date_from", date_from,
                "date_until", date_until,
                "hour_from", hour_from,
                "hour_until", hour_until,
                "description", description,
                "musician", musician,
                "genre", genre);
        if (concert.save()) {
            Organizer organizer = Organizer.findById(organizer_id);
            if (organizer != null) {
                organizer.add(concert);
                Base.commitTransaction();
                return concert;
            }
        }
        return null;
    }

    public Concert get(int id) {
        openBase();
        Concert concert = Concert.findById(id);
        return concert;
    }

    @Override
    public LazyList<Model> list() {
        openBase();
        return Concert.findAll();
    }

    public boolean delete(int id) {
        openBase();
        Base.openTransaction();
        Concert concert = Concert.findById(id);
        boolean result = concert.delete();
        Base.commitTransaction();
        return result;
    }

    public Concert update(int id, String name, String location, float price, String date_from, String date_until, String hour_from, String hour_until,
            String description, String musician, String genre) {
        openBase();
        Base.openTransaction();
        Concert concert = Concert.findById(id);
        concert.set("name", name, "location", location, "price", price, "date_from", date_from, "date_until", date_until, "hour_from",
                hour_from, "hour_until", hour_until, "description", description, "musician", musician, "genre", genre);
        concert.saveIt();
        Base.commitTransaction();
        return concert;
    }

}
