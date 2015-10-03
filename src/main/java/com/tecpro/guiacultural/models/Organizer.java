/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.models;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author joako
 */
public class Organizer {

    private String name;
    private int id;

    public Organizer(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public Organizer() {

    }

    public Organizer get(int id) {
        Connection connection = getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM organizer WHERE id="+id);
            rs.next();
            return new Organizer(rs.getInt(1), rs.getString(2));
        } catch (SQLException sql) {
            System.out.println(sql.toString());
        }
        return null;
    }
    
    public LinkedList<Organizer> list(){
        Connection conn = getConnection();
        LinkedList<Organizer> res = new LinkedList<>();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM organizer");
            while(rs.next()){
                res.add(new Organizer(rs.getInt(1),rs.getString(2)));
            }
            return res;
        } catch (SQLException sql){
            System.out.println(sql.toString());
        }
        return null;
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
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    private Connection getConnection() {
        try {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

            return DriverManager.getConnection(dbUrl, username, password);
        } catch (URISyntaxException uri) {
            System.out.println("URI Exception: " + uri.getReason() + " Message: " + uri.getMessage());
            System.out.println("On input: " + uri.getInput());
        } catch (SQLException sql) {
            System.out.println("SQL Exception: " + sql.getSQLState());
        }
        return null;
    }
}
