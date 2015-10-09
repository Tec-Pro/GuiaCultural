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

    /*
     public Concert create(String aName, String aLocation, float aPrice, String aDate_from, String aDate_until, String aHour_from, String aHour_until,
     int anOrganizer_id, String aDescription, String aMusician, String aGenre) {
     openBase();
     if (aName == null || aLocation == null || aDate_from == null || aHour_from == null || aPrice < 0.0f) {
     return null;
     }
     String name = aName;
     String location = aLocation;
     float price = aPrice;
     String date_from = aDate_from;
     String date_until = aDate_until;
     if (date_until == null) {
     date_until = "";
     }
     String hour_from = aHour_from;
     String hour_until = aHour_until;
     if (hour_until == null) {
     hour_until = "";
     }
     String description = aDescription;
     if (description == null) {
     description = "";
     }
     String musician = aMusician;
     if (musician == null) {
     musician = "";
     }
     String genre = aGenre;
     if (genre == null) {
     genre = "";
     }
    
     */
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
        Base.commitTransaction();
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
        boolean result = false;
        Base.openTransaction();
        Concert concert = Concert.findById(id);
        if (concert != null){
            result = concert.delete();
        }
        Base.commitTransaction();
        return result;
    }

    public Concert update(int id, String name, String location, float price, String date_from, String date_until, String hour_from, String hour_until,
            String description, String musician, String genre) {
        openBase();
        Base.openTransaction();
        Concert concert = Concert.findById(id);
        if (concert != null) {
            concert.set("name", name, "location", location, "price", price, "date_from", date_from, "date_until", date_until, "hour_from",
                    hour_from, "hour_until", hour_until, "description", description, "musician", musician, "genre", genre);
            concert.save();
        }
        Base.commitTransaction();
        return concert;
    }

}
