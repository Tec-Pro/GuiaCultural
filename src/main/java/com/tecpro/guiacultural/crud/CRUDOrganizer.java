/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.crud;

import com.tecpro.guiacultural.models.Event;
import com.tecpro.guiacultural.models.Organizer;
import java.net.URI;
import java.net.URISyntaxException;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/**
 *
 * @author joako
 */
public class CRUDOrganizer {

    public Organizer create(int name) {
        //if (name != null) {
            openBase();
            Base.openTransaction();
            Organizer organizer = Organizer.create("name", name);
            if (organizer.save()) {
                Base.commitTransaction();
                return organizer;
            }
            Base.rollbackTransaction();
        //}
            return null;
    }

    public Organizer get(int id) {
        openBase();
        Organizer organizer = Organizer.findById(id);
        return organizer;
    }

    public LazyList<Model> list() {
        openBase();
        return Organizer.findAll();
    }

    public boolean delete(int id) {
        openBase();
        Organizer organizer = Organizer.findById(id);
        boolean result = false;
        if (organizer != null) {
            Base.openTransaction();
            result = organizer.delete();
            Base.commitTransaction();
        }
        return result;
    }

    public Organizer update(int id, String name) {
        openBase();
        Organizer organizer = Organizer.findById(id);
        if (organizer != null) {
            Base.openTransaction();
            organizer.setString("name", name);
            organizer.save();
            Base.commitTransaction();
        }
        return organizer;
    }

    public LazyList<Model> listEvents(int id) {
        openBase();
        return Event.where("organizer_id = ?", id);
    }

    private void openBase() {
        try {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
            if (!Base.hasConnection()) {
                Base.open("org.postgresql.Driver", dbUrl, username, password);
            }
        } catch (URISyntaxException uri) {
            System.out.println("URI Exception: " + uri.getReason() + " Message: " + uri.getMessage());
            System.out.println("On input: " + uri.getInput());
        }
    }
}
