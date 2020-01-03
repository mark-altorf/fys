/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hva.fys.btracker.Models;

import com.sun.org.apache.bcel.internal.classfile.Utility;
import hva.fys.btracker.GeneralConfig;
import hva.fys.btracker.database.DatabaseManager;
import java.awt.FileDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author mich2
 */
public class FoundLuggage {

    private String registrationNr;
    private LocalDate dateFound;
    private String timeFound;
    private String luggageType;
    private String brand;
    private String specialCharacteristics;
    private double weightInKG;
    private String flightNr;
    private String tagNr;
    private String locationFound;
    private String mainColor;
    private String secondColor;
    private String size;
    private String passengerName;
    private String passengerCity;
    private String airport;

    public String getFlightNr() {
        return flightNr;
    }

    public void setFlightNr(String flightNr) {
        this.flightNr = flightNr;
    }

    public String getTagNr() {
        return tagNr;
    }

    public void setTagNr(String tagNr) {
        this.tagNr = tagNr;
    }

    public String getLocationFound() {
        return locationFound;
    }

    public void setLocationFound(String locationFound) {
        this.locationFound = locationFound;
    }

    public String getMainColor() {
        return mainColor;
    }

    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
    }

    public String getSecondColor() {
        return secondColor;
    }

    public void setSecondColor(String secondColor) {
        this.secondColor = secondColor;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerCity() {
        return passengerCity;
    }

    public void setPassengerCity(String passengerCity) {
        this.passengerCity = passengerCity;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    /**
     * @return the registrationNr
     */
    public String getRegistrationNr() {
        return registrationNr;
    }

    /**
     * @param registrationNr the registrationNr to set
     */
    public void setRegistrationNr(String registrationNr) {
        this.registrationNr = registrationNr;
    }

    /**
     * @return the dateFound
     */
    public LocalDate getDateFound() {
        return dateFound;
    }

    /**
     * @param dateFound the dateFound to set
     */
    public void setDateFound(LocalDate dateFound) {
        this.dateFound = dateFound;
    }

    /**
     * @return the timeFound
     */
    public String getTimeFound() {
        return timeFound;
    }

    /**
     * @param timeFound the timeFound to set
     */
    public void setTimeFound(String timeFound) {
        this.timeFound = timeFound;
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
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return the specialCharacteristics
     */
    public String getSpecialCharacteristics() {
        return specialCharacteristics;
    }

    /**
     * @param specialCharacteristics the specialCharacteristics to set
     */
    public void setSpecialCharacteristics(String specialCharacteristics) {
        this.specialCharacteristics = specialCharacteristics;
    }

    /**
     * @return the weightInKG
     */
    public double getWeightInKG() {
        return weightInKG;
    }

    /**
     * @param weightInKG the weightInKG to set
     */
    public void setWeightInKG(double weightInKG) {
        this.weightInKG = weightInKG;
    }

    public static List<FoundLuggage> getObjectListFromExcel(String filePath) throws IOException {
        List<FoundLuggage> result = new ArrayList();
        String airport = "";

        //read the file
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(inputStream);

        //Read out each sheet in the workbook
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);

            //loop trough each row in the sheet
            for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {

                //get the airport from the seccond row 3th cell
                if (j == 1) {
                    Cell airportCell = sheet.getRow(j).getCell(2);
                    airport = airportCell != null ? airportCell.getStringCellValue() : "";
                }

                //skip the first 3 rows since they are not holding FoundLuggage object data
                if (j < 4) {
                    continue;
                }

                Row row = sheet.getRow(j);

                result.add(serializeRow(row, airport));
            }
        }

        return result;
    }

    /**
     * *
     * Serializes a row from the excel export to a foundLuggage object
     *
     * @param row A Apache POI row object from the excel export with found
     * luggage records
     * @return A Foundluggage object
     */
    private static FoundLuggage serializeRow(Row row, String airport) {
        //Serialize the row to a found luggage object
        FoundLuggage foundLuggage = new FoundLuggage();

        //RegistrationNR
        Cell regisrationNrCell = row.getCell(0);
        foundLuggage.registrationNr = regisrationNrCell != null ? regisrationNrCell.getStringCellValue() : "";//TODO this should not be tagNr? it is registration nr wich is not in founluggage atm!!!!!!!!!!!!!!!!!!!

        //DateFound, convert the DateType to a localDate
        Cell dateFoundCell = row.getCell(1);
        Date temp = new Date();
        //check if the value of the cell i null
        temp = dateFoundCell != null ? dateFoundCell.getDateCellValue() : null;
        //if the value of temp is not null convert it to a localDate to insert into the database
        foundLuggage.dateFound = temp != null ? temp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : LocalDate.now();

        //timeFound
        Cell timeFoundCell = row.getCell(2);
        foundLuggage.timeFound = timeFoundCell != null ? Double.toString(timeFoundCell.getNumericCellValue()) : "";

        //luggageType
        Cell luggageTypeCell = row.getCell(3);
        foundLuggage.luggageType = luggageTypeCell != null ? luggageTypeCell.getStringCellValue() : "";

        //brand
        Cell brandCell = row.getCell(4);
        foundLuggage.brand = brandCell != null ? brandCell.getStringCellValue() : "";

        //FlightNr
        Cell flightNrCell = row.getCell(5);
        foundLuggage.flightNr = flightNrCell != null ? flightNrCell.getStringCellValue() : "";

        //tagNr
        Cell tagNrCell = row.getCell(6);
        foundLuggage.tagNr = tagNrCell != null ? Double.toString(tagNrCell.getNumericCellValue()) : "";

        //locationFound
        Cell locationFound = row.getCell(7);
        foundLuggage.locationFound = locationFound != null ? locationFound.getStringCellValue() : "";

        //Main color
        Cell mainColorCell = row.getCell(8);
        foundLuggage.mainColor = mainColorCell != null ? mainColorCell.getStringCellValue() : "";

        //seccondColor
        Cell seccondColorCell = row.getCell(9);
        foundLuggage.secondColor = seccondColorCell != null ? seccondColorCell.getStringCellValue() : "";

        //Size
        Cell sizeCell = row.getCell(10);
        foundLuggage.size = sizeCell != null ? sizeCell.getStringCellValue() : "";

        //Weight: get the weight from the weight string if it is not empty
        Cell weightCell = row.getCell(11);
        String tempWeight = weightCell != null ? weightCell.getStringCellValue() : "";
        if (tempWeight != null && tempWeight != "") {
            //remove the KG text from the string so it can be converted to a 
            tempWeight = tempWeight.replace("kg", "");

            //parse the string to a double and asign it to its associaded founLuggage object property
            foundLuggage.weightInKG = (Double.parseDouble(tempWeight));
        }

        //PassengerName and citty
        Cell nameAndCityCell = row.getCell(12);

        //if the cell is not null, split the cell wich contains both the name and city of the pasanger so they can be stored in their associated property
        String[] split = nameAndCityCell != null ? nameAndCityCell.getStringCellValue().replace("'", "").split(",") : new String[0];

        //check if split contains atleast 2 records to avoid null refference exceptions
        if (split.length > 1) {
            foundLuggage.passengerName = split[0];
            foundLuggage.passengerCity = split[1];
        }

        //Special characteristics
        Cell spcialCharacteristicsCell = row.getCell(13);
        foundLuggage.specialCharacteristics = spcialCharacteristicsCell != null ? spcialCharacteristicsCell.getStringCellValue() : "";

        foundLuggage.airport = airport;
        return foundLuggage;
    }

    /**
     * *
     * Creates a new foundLuggage record in the database
     *
     * @param object a foundLuggage object that contains all required data for
     * creating a new foundLuggage record in the database
     */
    public static void create(FoundLuggage object) {
        DatabaseManager dbManager = new DatabaseManager(GeneralConfig.databaseName);
        try {
            dbManager.executeUpdateQuery("INSERT INTO foundLuggage VALUES("
                    + "'" + object.registrationNr + "',"
                    + "'" + object.dateFound + "',"
                    + "'" + object.timeFound + "',"
                    + "'" + object.luggageType + "',"
                    + "'" + object.brand + "',"
                    + "'" + object.specialCharacteristics + "',"
                    + "'" + object.weightInKG + "',"
                    + "'" + object.flightNr + "',"
                    + "'" + object.tagNr + "',"
                    + "'" + object.locationFound + "',"
                    + "'" + object.mainColor + "',"
                    + "'" + object.secondColor + "',"
                    + "'" + object.size + "',"
                    + "'" + object.passengerName + "',"
                    + "'" + object.passengerCity + "',"
                    + "'" + object.airport + "')");
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbManager.close();
    }

    /**
     * *
     * Checks if a foundLuggage record already exists in the database based on
     * its registration NR
     *
     * @param registrationNr the registrationNr of the FoundLuggage object you
     * want to check for if it exists already
     * @return true if it already exists in the database false if not
     */
    public static boolean exists(String registrationNr) {
        DatabaseManager dbManager = new DatabaseManager(GeneralConfig.databaseName);

        int count = dbManager.executeCountQuery("SELECT COUNT(*) FROM FoundLuggage WHERE registrationNr='" + registrationNr + "'");
        dbManager.close();
        //if count it bigger than 0 than it exists alrdy and return true if not return false
        return count > 0 ? true : false;

    }

    /**
     * *
     * Retreives a list of all FoundLuggage records in the database
     *
     * @return List of type FoundLuggage
     */
    public static List<FoundLuggage> getAll() {
        List<FoundLuggage> result = new ArrayList();
        DatabaseManager dbManager = new DatabaseManager(GeneralConfig.databaseName);

        try {
            //fill the list with all the FoundLuggage records from the database
            ResultSet queryResult = dbManager.executeResultSetQuery("SELECT * FROM foundLuggage");

            //keep going as long as the dataSet has a next record
            while (queryResult.next()) {

                //serialize the data from the dataSet to a User object
                FoundLuggage foundLuggage = new FoundLuggage();
                foundLuggage.setRegistrationNr(queryResult.getString("registrationNr"));
                foundLuggage.setDateFound(queryResult.getDate("dateFound").toLocalDate());
                foundLuggage.setTimeFound(queryResult.getString("timeFound"));
                foundLuggage.setLuggageType(queryResult.getString("luggageType"));
                foundLuggage.setBrand(queryResult.getString("brand"));
                foundLuggage.setSpecialCharacteristics(queryResult.getString("specialCharacteristics"));
                foundLuggage.setWeightInKG(queryResult.getDouble("weightInKG"));
                foundLuggage.setFlightNr(queryResult.getString("flightNr"));
                foundLuggage.setTagNr(queryResult.getString("TagNr"));
                foundLuggage.setLocationFound(queryResult.getString("locationFound"));
                foundLuggage.setMainColor(queryResult.getString("mainColor"));
                foundLuggage.setSecondColor(queryResult.getString("secondColor"));
                foundLuggage.setSize(queryResult.getString("size"));
                foundLuggage.setPassengerName(queryResult.getString("passengerName"));
                foundLuggage.setPassengerCity(queryResult.getString("passengerCity"));
                foundLuggage.setAirport(queryResult.getString("airport"));

                //add the seralized foundLuggage obejct to the result list
                result.add(foundLuggage);
            }

        } catch (SQLException e) {
            dbManager.error(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * *
     * look for and retrieves all possible foundLuggage matches for a specific
     * missing report
     *
     * @param objToMatch The missing report you want to retreive all matches for
     * @return a list of all foundLuaggge records that have something in common
     * with the specified missing report
     */
    public static List<FoundLuggage> getPosibleMatches(MissingReport objToMatch) {
        List<FoundLuggage> result = new ArrayList();
        DatabaseManager dbManager = new DatabaseManager(GeneralConfig.databaseName);

        try {

            String query = "SELECT * FROM FoundLuggage WHERE ";

            //add the search criterea to the sql querie as long as the value in the MissingReport obj is not emty
            if (!"".equals(objToMatch.getDate().toString())) {
                query += "dateFound = '" + objToMatch.getDate() + "' ||";
            }
            if (!"".equals(objToMatch.getTime())) {
                query += "timeFound = '" + objToMatch.getTime() + "' ||";
            }
            if (!"".equals(objToMatch.getLuggageType())) {
                query += "luggageType = '" + objToMatch.getLuggageType() + "' ||";
            }
            if (!"".equals(objToMatch.getLuggageBrand())) {
                query += "brand = '" + objToMatch.getLuggageBrand() + "' ||";
            }
            if (!"".equals(objToMatch.getLuggageSpecialCharacteristics())) {
                query += "specialCharacteristics = '" + objToMatch.getLuggageSpecialCharacteristics() + "' ||";
            }
            if (!"".equals(objToMatch.getFlightNr())) {
                query += "flightNr = '" + objToMatch.getFlightNr() + "' ||";
            }
            if (!"".equals(objToMatch.getLuggageTagNr())) {
                query += "TagNr = '" + objToMatch.getLuggageTagNr() + "' ||";
            }
            if (!"".equals(objToMatch.getLuggageColor())) {
                query += "mainColor = '" + objToMatch.getLuggageColor() + "' ||";
            }
            if (!"".equals(objToMatch.getFirstname())) {
                query += "passengerName LIKE '%" + objToMatch.getFirstname() + "%' ||";
            }
            if (!"".equals(objToMatch.getLastname())) {
                query += "passengerName LIKE '%" + objToMatch.getLastname() + "%' ||";
            }
            if (!"".equals(objToMatch.getState())) {
                query += "passengerCity = '" + objToMatch.getState() + "' ||";
            }
            if (!"".equals(objToMatch.getAirport())) {
                query += "airport = '" + objToMatch.getAirport() + "'";
            }else{
                //if airport is amty remove the last || since there is no statement following 
                query = query.substring(0, query.length() -2);
            }

            ResultSet queryResult = dbManager.executeResultSetQuery(query);

            //keep going as long as the dataSet has a next record
            while (queryResult.next()) {

                //serialize the data from the dataSet to a User object
                FoundLuggage foundLuggage = new FoundLuggage();
                foundLuggage.setRegistrationNr(queryResult.getString("registrationNr"));
                foundLuggage.setDateFound(queryResult.getDate("dateFound").toLocalDate());
                foundLuggage.setTimeFound(queryResult.getString("timeFound"));
                foundLuggage.setLuggageType(queryResult.getString("luggageType"));
                foundLuggage.setBrand(queryResult.getString("brand"));
                foundLuggage.setSpecialCharacteristics(queryResult.getString("specialCharacteristics"));
                foundLuggage.setWeightInKG(queryResult.getDouble("weightInKG"));
                foundLuggage.setFlightNr(queryResult.getString("flightNr"));
                foundLuggage.setTagNr(queryResult.getString("TagNr"));
                foundLuggage.setLocationFound(queryResult.getString("locationFound"));
                foundLuggage.setMainColor(queryResult.getString("mainColor"));
                foundLuggage.setSecondColor(queryResult.getString("secondColor"));
                foundLuggage.setSize(queryResult.getString("size"));
                foundLuggage.setPassengerName(queryResult.getString("passengerName"));
                foundLuggage.setPassengerCity(queryResult.getString("passengerCity"));
                foundLuggage.setAirport(queryResult.getString("airport"));

                //add the seralized foundLuggage obejct to the result list
                result.add(foundLuggage);
            }

        } catch (SQLException e) {
            dbManager.error(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
