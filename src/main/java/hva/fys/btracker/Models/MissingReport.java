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
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mich2
 */
public class MissingReport {

    private int id;
    private LocalDate date;
    private String time;
    private String airport;
    private String firstname;
    private String lastname;
    private String adres;
    private String state;
    private String postalCode;
    private String country;
    private String phoneNumber;
    private String email;
    private String luggageTagNr;
    private String flightNr;
    private String destination;
    private String luggageType;
    private String luggageBrand;
    private String luggageColor;
    private String luggageSpecialCharacteristics;
    private String weight;

    private String foundLuggageId;
    private LocalDate dateResolved;

    public MissingReport() {

    }

    public MissingReport(LocalDate date, String time, String airport, String firstname, String lastname, String adres, String state,
            String postalCode, String country, String phoneNumber, String email, String luggageLabelNr, String flightNr, String destination,
            String luggageType, String luggageBrand, String luggageColor, String luggageSpecialCharacteristics, String weight) {
        this.date = date;
        this.time = time;
        this.airport = airport;
        this.firstname = firstname;
        this.lastname = lastname;
        this.adres = adres;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.luggageTagNr = luggageLabelNr;
        this.flightNr = flightNr;
        this.destination = destination;
        this.luggageType = luggageType;
        this.luggageBrand = luggageBrand;
        this.luggageColor = luggageColor;
        this.luggageSpecialCharacteristics = luggageSpecialCharacteristics;
        this.weight = weight;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the airport
     */
    public String getAirport() {
        return airport;
    }

    /**
     * @param airport the airport to set
     */
    public void setAirport(String airport) {
        this.airport = airport;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the adres
     */
    public String getAdres() {
        return adres;
    }

    /**
     * @param adres the adres to set
     */
    public void setAdres(String adres) {
        this.adres = adres;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the luggageTagNr
     */
    public String getLuggageTagNr() {
        return luggageTagNr;
    }

    /**
     * @param luggageTagNr the luggageTagNr to set
     */
    public void setLuggageTagNr(String luggageTagNr) {
        this.luggageTagNr = luggageTagNr;
    }

    /**
     * @return the flightNr
     */
    public String getFlightNr() {
        return flightNr;
    }

    /**
     * @param flightNr the flightNr to set
     */
    public void setFlightNr(String flightNr) {
        this.flightNr = flightNr;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @return the luggageType
     */
    public String getLuggageType() {
        return luggageType;
    }

    /**
     * @param luggageType the luggageType to set
     */
    public void setLuggageType(String luggageType) {
        this.luggageType = luggageType;
    }

    /**
     * @return the luggageBrand
     */
    public String getLuggageBrand() {
        return luggageBrand;
    }

    /**
     * @param luggageBrand the luggageBrand to set
     */
    public void setLuggageBrand(String luggageBrand) {
        this.luggageBrand = luggageBrand;
    }

    /**
     * @return the luggageColor
     */
    public String getLuggageColor() {
        return luggageColor;
    }

    /**
     * @param luggageColor the luggageColor to set
     */
    public void setLuggageColor(String luggageColor) {
        this.luggageColor = luggageColor;
    }

    /**
     * @return the luggageSpecialCharacteristics
     */
    public String getLuggageSpecialCharacteristics() {
        return luggageSpecialCharacteristics;
    }

    /**
     * @param luggageSpecialCharacteristics the luggageSpecialCharacteristics to
     * set
     */
    public void setLuggageSpecialCharacteristics(String luggageSpecialCharacteristics) {
        this.luggageSpecialCharacteristics = luggageSpecialCharacteristics;
    }

    public String getFoundLuggageId() {
        return foundLuggageId;
    }

    public void setFoundLuggageId(String foundLuggageId) {
        this.foundLuggageId = foundLuggageId;
    }

    public LocalDate getDateResolved() {
        return dateResolved;
    }

    public void setDateResolved(LocalDate dateResolved) {
        this.dateResolved = dateResolved;
    }

    public static void create(MissingReport object) {
        DatabaseManager dbManager = new DatabaseManager(GeneralConfig.databaseName);

        try {
            dbManager.executeUpdateQuery("INSERT INTO missingReport ("
                    + "date,"
                    + "time,"
                    + "airport,"
                    + "firstname,"
                    + "lastname,"
                    + "adres,"
                    + "state,"
                    + "postalCode,"
                    + "country,"
                    + "phoneNumber,"
                    + "email,"
                    + "luggageTagNr,"
                    + "flightNr,"
                    + "destination,"
                    + "luggageType,"
                    + "luggageBrand,"
                    + "luggageColor,"
                    + "luggageSpecialCharacteristics,"
                    + "weight)"
                    + "VALUES("
                    + "'" + object.getDate() + "',"
                    + "'" + object.getTime() + "',"
                    + "'" + object.getAirport() + "',"
                    + "'" + object.getFirstname() + "',"
                    + "'" + object.getLastname() + "',"
                    + "'" + object.getAdres() + "',"
                    + "'" + object.getState() + "',"
                    + "'" + object.getPostalCode() + "',"
                    + "'" + object.getCountry() + "',"
                    + "'" + object.getPhoneNumber() + "',"
                    + "'" + object.getEmail() + "',"
                    + "'" + object.getLuggageTagNr() + "',"
                    + "'" + object.getFlightNr() + "',"
                    + "'" + object.getDestination() + "',"
                    + "'" + object.getLuggageType() + "',"
                    + "'" + object.getLuggageBrand() + "',"
                    + "'" + object.getLuggageColor() + "',"
                    + "'" + object.getLuggageSpecialCharacteristics() + "',"
                    + "'" + object.getWeight() + "')");

        } catch (Exception e) {
            e.printStackTrace();
        }
        dbManager.close();

    }

    public static List<MissingReport> getAllResolved() {
        List<MissingReport> result = new ArrayList();
        DatabaseManager dbManager = new DatabaseManager(GeneralConfig.databaseName);

        try {
            //fill the list with all the missingReport records from the database
            ResultSet queryResult = dbManager.executeResultSetQuery("SELECT * FROM missingReport WHERE foundLuggageId IS NOT NULL");

            //keep going as long as the dataSet has a next record
            while (queryResult.next()) {

                //serialize the data from the dataSet to a User object
                MissingReport missingReport = new MissingReport();
                missingReport.id = queryResult.getInt("id");
                missingReport.setDate(queryResult.getDate("date").toLocalDate());
                missingReport.setTime(queryResult.getString("time"));
                missingReport.setAirport(queryResult.getString("airport"));
                missingReport.setFirstname(queryResult.getString("firstname"));
                missingReport.setLastname(queryResult.getString("lastname"));
                missingReport.setAdres(queryResult.getString("adres"));
                missingReport.setState(queryResult.getString("state"));
                missingReport.setPostalCode(queryResult.getString("postalCode"));
                missingReport.setCountry(queryResult.getString("country"));
                missingReport.setPhoneNumber(queryResult.getString("phoneNumber"));
                missingReport.setEmail(queryResult.getString("email"));
                missingReport.setLuggageTagNr(queryResult.getString("luggageTagNr"));
                missingReport.setFlightNr(queryResult.getString("flightNr"));
                missingReport.setDestination(queryResult.getString("destination"));
                missingReport.setLuggageType(queryResult.getString("luggageType"));
                missingReport.setLuggageBrand(queryResult.getString("luggageBrand"));
                missingReport.setLuggageColor(queryResult.getString("luggageColor"));
                missingReport.setLuggageSpecialCharacteristics(queryResult.getString("luggageSpecialCharacteristics"));
                missingReport.setFoundLuggageId(queryResult.getString("foundLuggageId"));
                missingReport.setDateResolved(queryResult.getDate("dateResolved").toLocalDate());
                missingReport.setWeight(queryResult.getString("weight"));

                //add the seralized missingReport obejct to the result list
                result.add(missingReport);
            }

        } catch (SQLException e) {
            dbManager.error(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<MissingReport> getAllUnResolved() {
        List<MissingReport> result = new ArrayList();
        DatabaseManager dbManager = new DatabaseManager(GeneralConfig.databaseName);

        try {
            //fill the list with all the missingReport records from the database
            ResultSet queryResult = dbManager.executeResultSetQuery("SELECT * FROM missingReport WHERE foundLuggageId IS NULL");

            //keep going as long as the dataSet has a next record
            while (queryResult.next()) {

                //serialize the data from the dataSet to a User object
                MissingReport missingReport = new MissingReport();
                missingReport.id = queryResult.getInt("id");
                missingReport.setDate(queryResult.getDate("date").toLocalDate());
                missingReport.setTime(queryResult.getString("time"));
                missingReport.setAirport(queryResult.getString("airport"));
                missingReport.setFirstname(queryResult.getString("firstname"));
                missingReport.setLastname(queryResult.getString("lastname"));
                missingReport.setAdres(queryResult.getString("adres"));
                missingReport.setState(queryResult.getString("state"));
                missingReport.setPostalCode(queryResult.getString("postalCode"));
                missingReport.setCountry(queryResult.getString("country"));
                missingReport.setPhoneNumber(queryResult.getString("phoneNumber"));
                missingReport.setEmail(queryResult.getString("email"));
                missingReport.setLuggageTagNr(queryResult.getString("luggageTagNr"));
                missingReport.setFlightNr(queryResult.getString("flightNr"));
                missingReport.setDestination(queryResult.getString("destination"));
                missingReport.setLuggageType(queryResult.getString("luggageType"));
                missingReport.setLuggageBrand(queryResult.getString("luggageBrand"));
                missingReport.setLuggageColor(queryResult.getString("luggageColor"));
                missingReport.setLuggageSpecialCharacteristics(queryResult.getString("luggageSpecialCharacteristics"));
                missingReport.setFoundLuggageId(queryResult.getString("foundLuggageId"));
                missingReport.setDateResolved(queryResult.getDate("dateResolved") != null ? queryResult.getDate("dateResolved").toLocalDate() : null);
                missingReport.setWeight(queryResult.getString("weight"));

                //add the seralized missingReport obejct to the result list
                result.add(missingReport);
            }

        } catch (SQLException e) {
            dbManager.error(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static MissingReport get(int id) {
        DatabaseManager dbManager = new DatabaseManager(GeneralConfig.databaseName);
        MissingReport result = new MissingReport();
        try {
            //retreive a specific missingReport record from the database based on id
            ResultSet queryResult = dbManager.executeResultSetQuery("SELECT * FROM missingReport WHERE id= '" + id + "'");

            while (queryResult.next()) {

                //serialize the data from the dataSet to a User object
                MissingReport missingReport = new MissingReport();
                missingReport.id = queryResult.getInt("id");
                missingReport.setDate(queryResult.getDate("date").toLocalDate());
                missingReport.setTime(queryResult.getString("time"));
                missingReport.setAirport(queryResult.getString("airport"));
                missingReport.setFirstname(queryResult.getString("firstname"));
                missingReport.setLastname(queryResult.getString("lastname"));
                missingReport.setAdres(queryResult.getString("adres"));
                missingReport.setState(queryResult.getString("state"));
                missingReport.setPostalCode(queryResult.getString("postalCode"));
                missingReport.setCountry(queryResult.getString("country"));
                missingReport.setPhoneNumber(queryResult.getString("phoneNumber"));
                missingReport.setEmail(queryResult.getString("email"));
                missingReport.setLuggageTagNr(queryResult.getString("luggageTagNr"));
                missingReport.setFlightNr(queryResult.getString("flightNr"));
                missingReport.setDestination(queryResult.getString("destination"));
                missingReport.setLuggageType(queryResult.getString("luggageType"));
                missingReport.setLuggageBrand(queryResult.getString("luggageBrand"));
                missingReport.setLuggageColor(queryResult.getString("luggageColor"));
                missingReport.setLuggageSpecialCharacteristics(queryResult.getString("luggageSpecialCharacteristics"));
                missingReport.setFoundLuggageId(queryResult.getString("foundLuggageId"));
                missingReport.setDateResolved(queryResult.getDate("dateResolved").toLocalDate());
                missingReport.setWeight(queryResult.getString("weight"));
                result = missingReport;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public static void delete() {

    }

    public static void update(int id, MissingReport object) {
        DatabaseManager dbManager = new DatabaseManager(GeneralConfig.databaseName);

        dbManager.executeUpdateQuery("UPDATE missingReport SET "
                + "date= '" + object.date + "',"
                + "time= '" + object.time + "',"
                + "airport= '" + object.airport + "',"
                + "firstname= '" + object.firstname + "',"
                + "lastname= '" + object.lastname + "',"
                + "adres= '" + object.adres + "',"
                + "state= '" + object.state + "',"
                + "postalCode ='" + object.postalCode + "',"
                + "country= '" + object.country + "',"
                + "phoneNumber= '" + object.phoneNumber + "',"
                + "email='" + object.email + "',"
                + "luggageTagNr= '" + object.luggageTagNr + "',"
                + "flightNr= '" + object.flightNr + "',"
                + "destination= '" + object.destination + "',"
                + "luggageType= '" + object.luggageType + "',"
                + "luggageBrand = '" + object.luggageBrand + "',"
                + "luggageColor= '" + object.luggageColor + "',"
                + "luggageSpecialCharacteristics= '" + object.luggageSpecialCharacteristics + "',"
                + "weight='"+ object.weight + "'"
                + "WHERE id= '" + id + "'");

    }

    /**
     * *
     * Matches a missing report with a found luggage object in the database by
     * connecting the id of the foundLuggage to the missing report
     */
    public void match(FoundLuggage object) {
        DatabaseManager dbManager = new DatabaseManager(GeneralConfig.databaseName);

        dbManager.executeUpdateQuery("UPDATE MissingReport SET"
                + " foundLuggageId = '" + object.getRegistrationNr() + "',"
                + " dateResolved = '" + LocalDate.now() + "'"
                + " WHERE id = '" + this.id + "'");

    }

}
