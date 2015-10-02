/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural;

import org.joda.time.LocalTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author joako
 */
@SpringBootApplication
public class Main {

    public static void main(String args[]) {
        LocalTime currentTime = new LocalTime();
        System.out.println("The current local time is: " + currentTime);
        SpringApplication.run(Main.class, args);
        
    }
}
