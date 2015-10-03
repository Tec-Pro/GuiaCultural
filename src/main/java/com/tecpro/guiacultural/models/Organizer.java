/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecpro.guiacultural.models;

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

    
    /*
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

}
