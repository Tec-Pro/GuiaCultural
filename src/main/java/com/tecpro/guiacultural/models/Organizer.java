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
    int id;
    String name;
    
    public Organizer(){
        
    }
    
    public Organizer(int id, String name){
        this.id = id;
        this.name = name;
    }
}
