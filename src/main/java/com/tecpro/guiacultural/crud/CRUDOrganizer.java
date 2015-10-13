/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.crud;

import com.tecpro.guiacultural.models.Event;
import com.tecpro.guiacultural.models.Organizer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Decoder;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;
import org.javalite.common.Convert;

/**
 *
 * @author joako
 */
public class CRUDOrganizer {

    public Organizer create(String name, File img) {
        if (name != null) {
            openBase();
            Base.openTransaction();
            byte[] ba = Convert.toBytes(img);
            Organizer organizer = Organizer.create("name", name, "org_img", ba);
            if (organizer.save()) {
                Base.commitTransaction();
                return organizer;
            }
        }
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
