/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hva.fys.btracker.Models;

import hva.fys.btracker.GeneralConfig;
import hva.fys.btracker.database.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mich2
 */
public class User {

    public int id;
    private String username;
    private String password;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param userName the username to set
     */
    public void setUsername(String userName) {
        this.username = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param Password the password to set
     */
    public void setPassword(String Password) {
        this.password = Password;
    }

    /**
     * *
     * Creates a new user in the database
     *
     * @param object A user object that contains all required data for creating
     * a new user
     */
    public static void create(User object) {
        DatabaseManager dbManager = new DatabaseManager(GeneralConfig.databaseName);

        try {
            dbManager.executeUpdateQuery("INSERT INTO User (username, password) VALUES("
                    + "'" + object.getUsername() + "',"
                    + "'" + object.getPassword() + "')");
            dbManager.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    

 

    
    
    /***
     * retreives a list of all the users in the database
     * @return a list of User objects
     */
    public static List<User> getAll() {
        List<User> result = new ArrayList();
        DatabaseManager dbManager = new DatabaseManager(GeneralConfig.databaseName);

        try {
            //fill the list with all the user records from the database
            ResultSet queryResult = dbManager.executeResultSetQuery("SELECT * FROM User");
            
             //keep going as long as the dataSet has a next record
            while (queryResult.next()) {

                //serialize the data from the dataSet to a User object
                User user = new User();
                user.id = queryResult.getInt("id");
                user.setUsername(queryResult.getString("username"));
                user.setPassword(queryResult.getString("password"));
                
                //add the seralized User obejct to the result list
                result.add(user);
            }

        } catch (SQLException e) {
            dbManager.error(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
