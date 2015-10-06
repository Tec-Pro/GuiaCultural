/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.crud;

import com.tecpro.guiacultural.models.Organizer;
import com.tecpro.guiacultural.models.TheaterPlay;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/**
 *
 * @author joako
 */
public class CRUDTheaterPlay extends CRUDEvent {

    public TheaterPlay create(String name, String location, float price, String date_from, String date_until, String hour_from, String hour_until,
            int organizer_id, String description, String play, String actor_group, String actors, String rating, String synopsis) {
        openBase();
        Base.openTransaction();
        TheaterPlay proyection = TheaterPlay.createIt("name", name,
                "location", location,
                "price", price,
                "date_from", date_from,
                "date_until", date_until,
                "hour_from", hour_from,
                "hour_until", hour_until,
                "description", description,
                "rating", rating,
                "synopsis", synopsis);
        Organizer organizer = Organizer.findById(organizer_id);
        organizer.add(proyection);
        Base.commitTransaction();
        return proyection;
    }

    public TheaterPlay get(int id) {
        openBase();
        TheaterPlay proyection = TheaterPlay.findById(id);
        return proyection;
    }

    @Override
    public LazyList<Model> list() {
        openBase();
        return TheaterPlay.findAll();
    }

    public boolean delete(int id) {
        openBase();
        Base.openTransaction();
        TheaterPlay proyection = TheaterPlay.findById(id);
        boolean result = proyection.delete();
        Base.commitTransaction();
        return result;
    }

    public TheaterPlay update(int id, String name, String location, float price, String date_from, String date_until, String hour_from, String hour_until,
            String description, String play, String actor_group, String actors, String rating, String synopsis) {
        openBase();
        Base.openTransaction();
        TheaterPlay proyection = TheaterPlay.findById(id);
        proyection.set("name", name,
                "location", location,
                "price", price,
                "date_from", date_from,
                "date_until", date_until,
                "hour_from", hour_from,
                "hour_until", hour_until,
                "description", description,
                "rating", rating,
                "synopsis", synopsis);
        proyection.saveIt();
        Base.commitTransaction();
        return proyection;
    }

}
