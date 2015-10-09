/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.crud;

import com.tecpro.guiacultural.models.VisualArts;
import com.tecpro.guiacultural.models.Organizer;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/**
 *
 * @author joako
 */
public class CRUDVisualArts extends CRUDEvent {

    public VisualArts create(String name, String location, float price, String date_from, String date_until, String hour_from, String hour_until,
            int organizer_id, String description, String artist) {
        openBase();
        Base.openTransaction();
        VisualArts visualArts = VisualArts.create("name", name,
                "location", location,
                "price", price,
                "date_from", date_from,
                "date_until", date_until,
                "hour_from", hour_from,
                "hour_until", hour_until,
                "description", description,
                "artist", artist);
        if (visualArts.save()) {
            Organizer organizer = Organizer.findById(organizer_id);
            if (organizer != null) {
                organizer.add(visualArts);
                Base.commitTransaction();
                return visualArts;
            }
        }
        return null;
    }

    public VisualArts get(int id) {
        openBase();
        VisualArts visualArts = VisualArts.findById(id);
        return visualArts;
    }

    @Override
    public LazyList<Model> list() {
        openBase();
        return VisualArts.findAll();
    }

    public boolean delete(int id) {
        openBase();
        Base.openTransaction();
        boolean result = false;
        VisualArts visualArts = VisualArts.findById(id);
        if (visualArts != null) {
            result = visualArts.delete();
        }
        Base.commitTransaction();
        return result;
    }

    public VisualArts update(int id, String name, String location, float price, String date_from, String date_until, String hour_from, String hour_until,
            String description, String artist) {
        openBase();
        Base.openTransaction();
        VisualArts visualArts = VisualArts.findById(id);
        if (visualArts != null) {
            visualArts.set("name", name, "location", location, "price", price, "date_from", date_from, "date_until", date_until, "hour_from",
                    hour_from, "hour_until", hour_until, "description", description, "artist", artist);
            visualArts.save();
        }
        Base.commitTransaction();
        return visualArts;
    }

}
