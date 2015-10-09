/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.crud;

import com.tecpro.guiacultural.models.LiteraryCycle;
import com.tecpro.guiacultural.models.Organizer;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/**
 *
 * @author joako
 */
public class CRUDLiteraryCycle extends CRUDEvent {

    public LiteraryCycle create(String name, String location, float price, String date_from, String date_until, String hour_from, String hour_until,
            int organizer_id, String description) {
        openBase();
        Base.openTransaction();
        LiteraryCycle literaryCycle = LiteraryCycle.create("name", name,
                "location", location,
                "price", price,
                "date_from", date_from,
                "date_until", date_until,
                "hour_from", hour_from,
                "hour_until", hour_until,
                "description", description);
        if (literaryCycle.save()) {
            Organizer organizer = Organizer.findById(organizer_id);
            if (organizer == null) {
                return null;
            }
            organizer.add(literaryCycle);
            Base.commitTransaction();
            return literaryCycle;
        }
        Base.rollbackTransaction();
        return null;
    }

    public LiteraryCycle get(int id) {
        openBase();
        LiteraryCycle literaryCycle = LiteraryCycle.findById(id);
        return literaryCycle;
    }

    @Override
    public LazyList<Model> list() {
        openBase();
        return LiteraryCycle.findAll();
    }

    public boolean delete(int id) {
        openBase();
        Base.openTransaction();
        boolean result = false;
        LiteraryCycle literaryCycle = LiteraryCycle.findById(id);
        if (literaryCycle != null) {
            result = literaryCycle.delete();
        }
        Base.commitTransaction();
        return result;
    }

    public LiteraryCycle update(int id, String name, String location, float price, String date_from, String date_until, String hour_from, String hour_until,
            String description) {
        openBase();
        Base.openTransaction();
        LiteraryCycle literaryCycle = LiteraryCycle.findById(id);
        if (literaryCycle != null) {
            literaryCycle.set("name", name, "location", location, "price", price, "date_from", date_from, "date_until", date_until, "hour_from",
                    hour_from, "hour_until", hour_until, "description", description);
            literaryCycle.save();
        }
        Base.commitTransaction();
        return literaryCycle;
    }

}
