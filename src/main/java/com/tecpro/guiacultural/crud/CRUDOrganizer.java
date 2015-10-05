/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.crud;

import com.tecpro.guiacultural.models.Organizer;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/**
 *
 * @author joako
 */
public class CRUDOrganizer {

    public Organizer create(String name) {
        openBase();
        Base.openTransaction();
        Organizer organizer = Organizer.createIt("name", name);
        Base.commitTransaction();
        return organizer;
    }

    public Organizer get(int id) {
        openBase();
        Organizer organizer = Organizer.findById(id);
        System.out.println("");
        return organizer;
    }

    public LazyList<Model> list() {
        openBase();
        return Organizer.findAll();
    }

    /*
     public boolean delete(int id){
        
     }
    
     public Organizer update(int id, String name){
        
     }
     */
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
