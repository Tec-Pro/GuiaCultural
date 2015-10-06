/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.crud;

import com.tecpro.guiacultural.models.Proyection;
import com.tecpro.guiacultural.models.Organizer;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/**
 *
 * @author joako
 */
public class CRUDProyection extends CRUDEvent{

    public Proyection create(String name, String location, float price, String date_from, String date_until, String hour_from, String hour_until,
            int organizer_id, String description, String movie_name, int length, String director, int year, String country, String genre, String rating, String synopsis) {
        openBase();
        Base.openTransaction();
        Proyection proyection = Proyection.createIt("name", name,
                "location", location,
                "price", price,
                "date_from", date_from,
                "date_until", date_until,
                "hour_from", hour_from,
                "hour_until", hour_until,
                "description", description,
                "movie_name", movie_name,
                "length", length,
                "director", director,
                "year", year,
                "country", country,
                "rating", rating,
                "synopsis", synopsis,
                "genre", genre);
        Organizer organizer = Organizer.findById(organizer_id);
        organizer.add(proyection);
        Base.commitTransaction();
        return proyection;
    }

    public Proyection get(int id) {
        openBase();
        Proyection proyection = Proyection.findById(id);
        return proyection;
    }

    @Override
    public LazyList<Model> list() {
        openBase();
        return Proyection.findAll();
    }

    public boolean delete(int id) {
        openBase();
        Base.openTransaction();
        Proyection proyection = Proyection.findById(id);
        boolean result = proyection.delete();
        Base.commitTransaction();
        return result;
    }

    public Proyection update(int id, String name, String location, float price, String date_from, String date_until, String hour_from, String hour_until,
            String description, String movie_name, int length, String director, int year, String country, String genre, String rating, String synopsis) {
        openBase();
        Base.openTransaction();
        Proyection proyection = Proyection.findById(id);
        proyection.set("name", name,
                "location", location,
                "price", price,
                "date_from", date_from,
                "date_until", date_until,
                "hour_from", hour_from,
                "hour_until", hour_until,
                "description", description,
                "movie_name", movie_name,
                "length", length,
                "director", director,
                "year", year,
                "country", country,
                "rating", rating,
                "synopsis", synopsis,
                "genre", genre);
        proyection.saveIt();
        Base.commitTransaction();
        return proyection;
    }


}
