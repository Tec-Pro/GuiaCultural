/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.controllers;

import com.tecpro.guiacultural.models.Organizer;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author joako
 */
@RestController
public class OrganizerController {

    @RequestMapping(value="/organizer", method = RequestMethod.GET)
    public Organizer getOrganizer(@RequestParam(value = "id", defaultValue = "1") int id) {
        try {
            Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            try {
                ResultSet rs = stmt.executeQuery("SELECT * FROM organizer WHERE id=" + id);
                System.out.println(rs);
                rs.next();
                return new Organizer(rs.getInt(1),rs.getString(2));
            } catch (SQLException ex) {
                Logger.getLogger(OrganizerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OrganizerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


    
    private Connection getConnection() {
        try {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

            return DriverManager.getConnection(dbUrl, username, password);
        } catch (URISyntaxException uri) {
            System.out.println("URI Exception: " + uri.getReason() + " Message: " + uri.getMessage());
            System.out.println("On input: " + uri.getInput());
        } catch (SQLException sql) {
            System.out.println("SQL Exception: " + sql.getSQLState());
        }
        return null;
    }
}
