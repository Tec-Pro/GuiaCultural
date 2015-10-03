/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.crud;

import com.tecpro.guiacultural.models.Organizer;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author joako
 */
public class CRUDOrganizer {
    
    public Organizer create(String name){
        Connection conn = getConnection();
        try{
            Statement stmt = conn.createStatement();
            int last_id = stmt.executeUpdate("INSERT INTO organizer(name) VALUES ('"+name+"') returning id;");
            return get(last_id);
        } catch (SQLException sql){
            System.out.println(sql.toString());
        }
        return get(1);
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
    /*
    public LinkedList<Organizer> list(){
        
    }
    
    public boolean delete(int id){
        
    }
    
    public Organizer update(int id, String name){
        
    }
    */
    
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
