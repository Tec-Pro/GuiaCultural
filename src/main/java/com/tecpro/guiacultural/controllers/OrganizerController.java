/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.controllers;

import com.tecpro.guiacultural.crud.CRUDOrganizer;
import com.tecpro.guiacultural.models.Organizer;
import java.io.File;
import java.net.URL;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author joako
 */
@RestController
public class OrganizerController {

    @RequestMapping(value = "/organizers", method = RequestMethod.GET)
    public String index() {
        CRUDOrganizer crud = new CRUDOrganizer();
        return crud.list().toJson(true);
    }

    /**
     *
     * @param id organizer id.
     * @return organizer data to json.
     */
    @RequestMapping(value = "/organizers/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id) {
        CRUDOrganizer crud = new CRUDOrganizer();
        Organizer organizer = crud.get(id);
        if (organizer != null) {
            return organizer.toJson(true);
        }

        return "{error: \"organizer not found\"}";
    }

    @RequestMapping(value = "/organizers/{id}/events", method = RequestMethod.GET)
    public String indexEvents(@PathVariable int id) {
        CRUDOrganizer crud = new CRUDOrganizer();
        if (Organizer.findById(id) != null) {
            return crud.listEvents(id).toJson(true);
        }
        return "{error: \"organizer not found\"}";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/organizers", method = RequestMethod.POST)
    public String create(@RequestParam Map<String, String> params) throws Exception {
        CRUDOrganizer crud = new CRUDOrganizer();
        File img = null;
        //try {
            URL url = new URL("http://image-link-archive.meteor.com/images/placeholder-640x480.png");
            if (params.get("image_url") != null) {
                url = new URL(params.get("image_url"));
            }
            FileUtils.copyURLToFile(url, img);
            Organizer organizer = crud.create(params.get("name"), img);
            if (organizer != null) {
                return organizer.toJson(true);
            } 
        //} catch (Exception e) {
         //   return "{error: "+e.toString()+"\"an error ocurred\"}";
        //}

        return "{error: \"field name can't be empty\"}";
    }

    @RequestMapping(value = "/organizers/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id) {
        CRUDOrganizer crud = new CRUDOrganizer();
        if (crud.delete(id) == true) {
            return String.valueOf(crud.delete(id));
        }
        return "{error: \"organizer not found\"}";
    }

    @RequestMapping(value = "/organizers/{id}", method = RequestMethod.PUT)
    public String update(@PathVariable int id, @RequestParam Map<String, String> params) {
        CRUDOrganizer crud = new CRUDOrganizer();
        Organizer organizer = crud.update(id, params.get("name"));
        if (organizer != null) {
            return organizer.toJson(true);
        }
        return "{error: \"organizer not found\"}";
    }

}
