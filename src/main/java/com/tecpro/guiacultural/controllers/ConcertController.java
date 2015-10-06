/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.controllers;

import com.tecpro.guiacultural.crud.CRUDConcert;
import java.util.Map;
import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author joako
 */
@RestController
public class ConcertController {

    @RequestMapping(value = "/concerts", method = RequestMethod.GET)
    public String index() {
        CRUDConcert crud = new CRUDConcert();
        return crud.list().toJson(true);
    }

    @RequestMapping(value = "/concerts/{id}", method = RequestMethod.GET)
    public String get(@PathVariable int id) {
        CRUDConcert crud = new CRUDConcert();
        return crud.get(id).toJson(true);
    }

    @RequestMapping(value = "/concerts", method = RequestMethod.POST)
    public String create(@RequestParam Map<String, String> params) {
        CRUDConcert crud = new CRUDConcert();
        return crud.create(params.get("name"), params.get("location"), Float.parseFloat(params.get("price")), params.get("date_from"), params.get("date_until"),
                params.get("hour_from"), params.get("hour_until"), Integer.parseInt(params.get("organizer_id")), params.get("description"), params.get("musician"), params.get("genre")).toJson(true);
    }

    @RequestMapping(value = "/concerts/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id) {
        CRUDConcert crud = new CRUDConcert();
        return "{result:" + String.valueOf(crud.delete(id) + ", id:" + id + "}");
    }

    @RequestMapping(value = "/concerts/{id}", method = RequestMethod.PUT)
    public String update(@PathVariable int id, @RequestParam Map<String, String> params) {
        CRUDConcert crud = new CRUDConcert();
        return crud.update(id, params.get("name"), params.get("location"), Float.parseFloat(params.get("price")), params.get("date_from"), params.get("date_until"),
                params.get("hour_from"), params.get("hour_until"), params.get("description"), params.get("musician"), params.get("genre")).toJson(true);
    }
}
