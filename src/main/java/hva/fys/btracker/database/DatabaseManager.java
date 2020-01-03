package hva.fys.btracker.database;

import hva.fys.btracker.GeneralConfig;
import java.sql.*;
import java.util.Enumeration;

/**
 * *
 * Demonstrates use of JDBC in a plain Java Program
 *
 * @author somej
 */
public class DatabaseManager {

    private static final String DB_DEFAULT_DATABASE = "sys";
    private static final String DB_DEFAULT_SERVER_URL = "localhost:3306";//3306 for laptop //3307 for desktop
    private static final String DB_DEFAULT_ACCOUNT = "root";
    private static final String DB_DEFAULT_PASSWORD = "admin";

    private final static String DB_DRIVER_URL = "com.mysql.jdbc.Driver";
    private final static String DB_DRIVER_PREFIX = "jdbc:mysql://";
    private final static String DB_DRIVER_PARAMETERS = "?useSSL=false";

    private Connection connection = null;

    // set for verbose logging of all queries
    private boolean verbose = true;

    // remembers the first error message on the connection 
    private String errorMessage = null;

    // constructors
    public DatabaseManager() {
        this(DB_DEFAULT_DATABASE, DB_DEFAULT_SERVER_URL, DB_DEFAULT_ACCOUNT, DB_DEFAULT_PASSWORD);
    }

    public DatabaseManager(String dbName) {
        this(dbName, DB_DEFAULT_SERVER_URL, DB_DEFAULT_ACCOUNT, DB_DEFAULT_PASSWORD);
    }

    public DatabaseManager(String dbName, String account, String password) {
        this(dbName, DB_DEFAULT_SERVER_URL, account, password);
    }

    public DatabaseManager(String dbName, String serverURL, String account, String password) {
        try {
            // verify that a proper JDBC driver has been installed and linked
            if (!selectDriver(DB_DRIVER_URL)) {
                return;
            }

            if (password == null) {
                password = "";
            }

            // establish a connection to a named database on a specified server	
            String connStr = DB_DRIVER_PREFIX + serverURL + "/" + dbName + DB_DRIVER_PARAMETERS;

            this.connection = DriverManager.getConnection(connStr, account, password);

        } catch (SQLException eSQL) {
            error(eSQL);
            this.close();
        }
    }

    public final void close() {

        if (this.connection == null) {
            // db has been closed earlier already
            return;
        }
        try {
            this.connection.close();
            this.connection = null;
            this.log("Data base has been closed");
        } catch (SQLException eSQL) {
            error(eSQL);
        }
    }

    /**
     * *
     * elects proper loading of the named driver for database connections. This
     * is relevant if there are multiple drivers installed that match the JDBC
     * type
     *
     * @param driverName the name of the driver to be activated.
     * @return indicates whether a suitable driver is available
     */
    private Boolean selectDriver(String driverName) {
        try {
            Class.forName(driverName);
            // Put all non-prefered drivers to the end, such that driver selection hits the first
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver d = drivers.nextElement();
                if (!d.getClass().getName().equals(driverName)) {   // move the driver to the end of the list
                    DriverManager.deregisterDriver(d);
                    DriverManager.registerDriver(d);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            error(ex);
            return false;
        }
        return true;
    }

    /**
     * *
     * Executes a DDL, DML or DCL query that does not yield a result set
     *
     * @param sql the full sql text of the query.
     * @return the number of rows that have been impacted, -1 on error
     */
    public int executeUpdateQuery(String sql) {
        try {
            Statement s = this.connection.createStatement();
            log(sql);
            int n = s.executeUpdate(sql);
            s.close();
            return (n);
        } catch (SQLException ex) {
            // handle exception
            error(ex);
            return -1;
        }
    }

    /**
     * *
     * Executes an SQL query that yields a ResultSet with the outcome of the
     * query. This outcome may be a single row with a single column in case of a
     * scalar outcome.
     *
     * @param sql the full sql text of the query.
     * @return a ResultSet object that can iterate along all rows
     * @throws SQLException
     */
    public ResultSet executeResultSetQuery(String sql) throws SQLException {
        Statement s = this.connection.createStatement();
        log(sql);
        ResultSet rs = s.executeQuery(sql);
        // cannot close the statement, because that also closes the resultset
        return rs;
    }

    /**
     * *
     * Executes query that is expected to return a single String value
     *
     * @param sql the full sql text of the query.
     * @return the string result, null if no result or error
     */
    public String executeStringQuery(String sql) {
        String result = null;
        try {
            Statement s = this.connection.createStatement();
            log(sql);
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                result = rs.getString(1);
            }
            // close both statement and resultset
            s.close();
        } catch (SQLException ex) {
            error(ex);
        }

        return result;
    }

    /**
     * *
     * This method is used for executing count query's on the database and
     * getting the results
     *
     * @param sql The count query you want to execute on the database
     * @return The result of the given query
     */
    public int executeCountQuery(String sql) {
        int result = -1;
        try {
            Statement s = this.connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                result = rs.getInt(1);
            }
            // close both statement and resultset
            s.close();
        } catch (SQLException ex) {
            error(ex);
        }

        return result;
    }

    /**
     * *
     * Executes query that is expected to return a list of String values
     *
     * @param sql the full sql text of the query.
     * @return the string result, null if no result or error
     */
    public String executeStringListQuery(String sql) {
        String result = null;
        try {
            Statement s = this.connection.createStatement();
            log(sql);
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                result = rs.getString(1);
            }
            // close both statement and resultset
            s.close();
        } catch (SQLException ex) {
            error(ex);
        }

        return result;
    }

    /**
     * *
     * echoes a message on the system console, if run in verbose mode
     *
     * @param message
     */
    public void log(String message) {
        if (isVerbose()) {
            System.out.println("MyJDBC: " + message);
        }
    }

    /**
     * *
     * echoes an exception and its stack trace on the system console. remembers
     * the message of the first error that occurs for later reference. closes
     * the connection such that no further operations are possible.
     *
     * @param e
     */
    public final void error(Exception e) {
        String msg = "MyJDBC-" + e.getClass().getName() + ": " + e.getMessage();

        // capture the message of the first error of the connection
        if (this.errorMessage == null) {
            this.errorMessage = msg;
        }
        System.out.println(msg);
        e.printStackTrace();

        // if an error occurred, close the connection to prevent further operations
        this.close();
    }

    /**
     * *
     * builds the database if it doesn't yet exist
     *
     * @param dbName the name of the database
     */
    public static void createDatabase(String dbName) {

        System.out.println("Creating the " + dbName + " database...");

        // use the default sys db for creating the db if it doesnt exist yet
        DatabaseManager sysJDBC = new DatabaseManager("sys");
        sysJDBC.executeUpdateQuery("CREATE DATABASE IF NOT EXISTS " + dbName);
        sysJDBC.close();

        // create the tables if they dont exist yet
        DatabaseManager myJDBC = new DatabaseManager(dbName);

        //FoundLuggage table
        myJDBC.executeUpdateQuery("CREATE TABLE IF NOT EXISTS FoundLuggage ("
                + " registrationNr varchar(45) NOT NULL PRIMARY KEY,"
                + " dateFound DATETIME,"
                + " timeFound varchar(45),"
                + " luggageType varchar(45),"
                + " brand varchar(45),"
                + " specialCharacteristics varchar(500),"
                + " weightInKG double,"
                + " flightNr varchar(45),"
                + " TagNr varchar(45),"
                + " locationFound varchar(45),"
                + " mainColor varchar(45),"
                + " secondColor varchar(45),"
                + " size varchar(45),"
                + " passengerName varchar(45),"
                + " passengerCity varchar(45),"
                + " airport varchar(45))");

        //Users table
        myJDBC.executeUpdateQuery("CREATE TABLE IF NOT EXISTS User ("
                + " id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                + " username varchar(45),"
                + " password varchar(45))");

        //missingReportsTable
        myJDBC.executeUpdateQuery("CREATE TABLE IF NOT EXISTS MissingReport ("
                + " id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                + " date DATETIME,"
                + " time varchar(45),"
                + " airport varchar(45),"
                + " firstname varchar(45),"
                + " lastname varchar(45),"
                + " adres varchar(45),"
                + " state varchar(45),"
                + " postalCode varchar(45),"
                + " country varchar(45),"
                + " phoneNumber varchar(45),"
                + " email varchar(45),"
                + " luggageTagNr varchar(45),"
                + " flightNr varchar(45),"
                + " destination varchar(45),"
                + " luggageType varchar(45),"
                + " luggageBrand varchar(45),"
                + " luggageColor varchar(45),"
                + " luggageSpecialCharacteristics varchar(45),"
                + " foundLuggageId varchar(45),"
                + " dateResolved DATETIME,"
                + " weight varchar(20))");

        //seed the db with 2 test users if no users exist
        if (myJDBC.executeCountQuery("SELECT COUNT(*) FROM USER") <= 0) {
            myJDBC.executeUpdateQuery("INSERT INTO User  (Username, Password) VALUES ('user', 'Welkom01')");
            myJDBC.executeUpdateQuery("INSERT INTO User  (Username, Password) VALUES ('admin', 'Welkom01')");
        }

        // close the connection with the database
        myJDBC.close();
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
