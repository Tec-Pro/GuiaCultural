/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.models;

import org.javalite.activejdbc.Model;

/**
 *
 * @author joako
 */
public class Organizer extends Model {

    private String name;
    private int organizer_id;

    public Organizer(int id, String name) {
        this.name = name;
        this.organizer_id = id;
    }
 
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the organizer_id
     */
    public int getOrganizerId() {
        return organizer_id;
    }

    /**
     * @param id the organizer_id to set
     */
    public void setId(int id) {
        this.organizer_id = id;
    }

}
