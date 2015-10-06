/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.controllers;

import com.tecpro.guiacultural.crud.CRUDEvent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author joako
 */
public class EventController {

    @RequestMapping(value = "/events",method = RequestMethod.GET)
    public String index(){
        CRUDEvent crud = new CRUDEvent();
        return crud.list().toJson(true);
    }
}
