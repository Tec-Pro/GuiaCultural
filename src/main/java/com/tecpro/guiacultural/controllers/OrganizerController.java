/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.controllers;

import com.tecpro.guiacultural.models.Organizer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author joako
 */
@RestController
public class OrganizerController {

    @RequestMapping(value="/organizer")
    public Organizer getOrganizer(@RequestParam(value="id", defaultValue="1") int id){
        Organizer org = new Organizer();
        return org.get(id);
    }
    
    

   
}
