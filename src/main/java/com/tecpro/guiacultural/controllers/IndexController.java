/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.controllers;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author joako
 */
@RestController
public class IndexController implements ErrorController {

    private final String error_path = "/error";
    
    @RequestMapping(value = error_path)
    public String error(){
        return "<p>Content not found</p>";
    }
    
    @Override
    public String getErrorPath() {
        return "/error";
    }
    
}
