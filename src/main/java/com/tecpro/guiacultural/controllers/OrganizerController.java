/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.controllers;

import com.tecpro.guiacultural.crud.CRUDOrganizer;
import com.tecpro.guiacultural.models.Organizer;
import java.util.Map;
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

    /**
     *
     * @param id organizer id.
     * @return organizer data to json.
     */
    @RequestMapping(value = "/organizers", method = RequestMethod.GET)
    public String show  (@RequestParam(value = "id", defaultValue = "1") int id) {
        CRUDOrganizer crud = new CRUDOrganizer();
        Organizer organizer = crud.get(id);
        if (organizer != null) {
            return organizer.toJson(true);
        }
        return "{error: \"organizer not found\"}";
    }

    @RequestMapping(value = "/organizers", method = RequestMethod.POST)
    public String create(@RequestParam(value = "name") String name) {
        if (!name.isEmpty()) {
            CRUDOrganizer crud = new CRUDOrganizer();
            return crud.create(name).toJson(true);
        }
        return "{error: \"field name can't be empty\"}";
    }

    @RequestMapping(value = "/organizers", method = RequestMethod.GET)
    public String index() {
        CRUDOrganizer crud = new CRUDOrganizer();
        return crud.list().toJson(true);
    }

    @RequestMapping(value = "/organizers", method = RequestMethod.DELETE)
    public String delete(@RequestParam(value = "id") int id) {
        CRUDOrganizer crud = new CRUDOrganizer();
        return String.valueOf(crud.delete(id));
    }

    @RequestMapping(value = "/organizers", method = RequestMethod.PUT)
    public String update(@RequestParam Map<String, String> params) {
        CRUDOrganizer crud = new CRUDOrganizer();
        return crud.update(Integer.parseInt(params.get("id")), params.get("name")).toJson(true);
    }

}
