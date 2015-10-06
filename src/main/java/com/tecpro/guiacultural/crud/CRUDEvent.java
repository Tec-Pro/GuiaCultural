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

/**
 *
 * @author joako
 */
public class CRUDEvent {
    
    public LazyList list(){
        openBase();
        return Event.findAll().include(Organizer.class);
    }
    
    protected void openBase() {
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
