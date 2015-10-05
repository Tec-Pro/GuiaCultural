/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.controllers;

import com.tecpro.guiacultural.models.Organizer;
import org.javalite.activeweb.AppController;
import org.javalite.activeweb.annotations.RESTful;

/**
 *
 * @author joako
 */
@RESTful
public class OrganizerController extends AppController {

    public void show(){
        Organizer organizer = Organizer.findById(getId());
        if (organizer != null)
            organizer.toJson(true);
    }
        /*

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
         */
    }
