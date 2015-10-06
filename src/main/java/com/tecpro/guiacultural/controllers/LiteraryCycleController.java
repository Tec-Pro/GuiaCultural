/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.controllers;

import com.tecpro.guiacultural.crud.CRUDLiteraryCycle;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author joako
 */
public class LiteraryCycleController {
    @RequestMapping(value = "/cycles", method = RequestMethod.GET)
    public String index() {
        CRUDLiteraryCycle crud = new CRUDLiteraryCycle();
        return crud.list().toJson(true);
    }

    @RequestMapping(value = "/cycles/{id}", method = RequestMethod.GET)
    public String get(@PathVariable int id) {
        CRUDLiteraryCycle crud = new CRUDLiteraryCycle();
        return crud.get(id).toJson(true);
    }

    @RequestMapping(value = "/cycles", method = RequestMethod.POST)
    public String create(@RequestParam Map<String, String> params) {
        CRUDLiteraryCycle crud = new CRUDLiteraryCycle();
        return crud.create(params.get("name"), params.get("location"), Float.parseFloat(params.get("price")), params.get("date_from"), params.get("date_until"),
                params.get("hour_from"), params.get("hour_until"), Integer.parseInt(params.get("organizer_id")), params.get("description")).toJson(true);
    }

    @RequestMapping(value = "/cycles/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id) {
        CRUDLiteraryCycle crud = new CRUDLiteraryCycle();
        return "{result:" + String.valueOf(crud.delete(id) + ", id:" + id + "}");
    }

    @RequestMapping(value = "/cycles/{id}", method = RequestMethod.PUT)
    public String update(@PathVariable int id, @RequestParam Map<String, String> params) {
        CRUDLiteraryCycle crud = new CRUDLiteraryCycle();
        return crud.update(id, params.get("name"), params.get("location"), Float.parseFloat(params.get("price")), params.get("date_from"), params.get("date_until"),
                params.get("hour_from"), params.get("hour_until"), params.get("description")).toJson(true);
    }
}
