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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
 *
 * @author joako
 */
public class CRUDOrganizer {

    public Organizer create(String name) {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO organizer(name) VALUES ('" + name + "');", Statement.RETURN_GENERATED_KEYS);
            
            if (stmt.execute()) {
                System.out.println("Acá si");
                try (ResultSet generated_keys = stmt.getGeneratedKeys()){
                    System.out.println("Acá también");
                    int id = 0;
                    while (generated_keys.next()){
                        id = generated_keys.getInt(1);
                    }
                    generated_keys.close();
                    return get(id);
                } catch (Exception e){
                    System.out.println(Arrays.toString(e.getStackTrace()));
                }
            }
        } catch (SQLException sql) {
            System.out.println("ERROR: "+sql.toString());
        }
        return get(-1);
    }

    public Organizer get(int id) {
        Connection connection = getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM organizer WHERE id=" + id);
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
