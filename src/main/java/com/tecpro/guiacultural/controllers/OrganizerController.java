/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.controllers;

import com.tecpro.guiacultural.crud.CRUDOrganizer;
import com.tecpro.guiacultural.models.Organizer;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;
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

    @RequestMapping(value = "/organizer", method = RequestMethod.GET)
    public Organizer getOrganizer(@RequestParam(value = "id", defaultValue = "1") int id) {
        CRUDOrganizer crud = new CRUDOrganizer();
        return crud.get(id);
    }

    @RequestMapping(value = "/organizers", method = RequestMethod.GET)
    public LazyList<Model> getOrganizers() {
        CRUDOrganizer crud = new CRUDOrganizer();
        return crud.list();
    }

    @RequestMapping(value = "/organizer", method = RequestMethod.PUT)
    public Organizer createOrganizer(@RequestParam(value = "name") String name) {
        if (!name.isEmpty()) {
            CRUDOrganizer crud = new CRUDOrganizer();
            return crud.create(name);
        }
        return null;
    }

}
