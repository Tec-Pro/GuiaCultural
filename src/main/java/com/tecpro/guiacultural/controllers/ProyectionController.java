/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.controllers;

import com.tecpro.guiacultural.crud.CRUDProyection;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author joako
 */
public class ProyectionController {

    @RequestMapping(value = "/proyections", method = RequestMethod.GET)
    public String index() {
        CRUDProyection crud = new CRUDProyection();
        return crud.list().toJson(true);
    }

    @RequestMapping(value = "/proyections/{id}", method = RequestMethod.GET)
    public String get(@PathVariable int id) {
        CRUDProyection crud = new CRUDProyection();
        return crud.get(id).toJson(true);
    }

    @RequestMapping(value = "/proyections", method = RequestMethod.POST)
    public String create(@RequestParam Map<String, String> params) {
        CRUDProyection crud = new CRUDProyection();
        return crud.create(params.get("name"), params.get("location"), Float.parseFloat(params.get("price")), params.get("date_from"), params.get("date_until"),
                params.get("hour_from"), params.get("hour_until"), Integer.parseInt(params.get("organizer_id")), params.get("description"), params.get("movie_name"), Integer.parseInt(params.get("length")),
                params.get("director"), Integer.parseInt(params.get("year")), params.get("country"), params.get("genre"), params.get("rating"), params.get("synopsis")).toJson(true);
    }

    @RequestMapping(value = "/proyections/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id) {
        CRUDProyection crud = new CRUDProyection();
        return "{result:" + String.valueOf(crud.delete(id) + ", id:" + id + "}");
    }

    @RequestMapping(value = "/proyections/{id}", method = RequestMethod.PUT)
    public String update(@PathVariable int id, @RequestParam Map<String, String> params) {
        CRUDProyection crud = new CRUDProyection();
        return crud.update(id, params.get("name"), params.get("location"), Float.parseFloat(params.get("price")), params.get("date_from"), params.get("date_until"),
                params.get("hour_from"), params.get("hour_until"), params.get("description"), params.get("movie_name"), Integer.parseInt(params.get("length")),
                params.get("director"), Integer.parseInt(params.get("year")), params.get("country"), params.get("genre"), params.get("rating"), params.get("synopsis")).toJson(true);
    }
}
