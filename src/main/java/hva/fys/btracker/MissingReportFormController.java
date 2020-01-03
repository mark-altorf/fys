/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hva.fys.btracker;

import hva.fys.btracker.Models.MissingReport;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author mich2
 */
public class MissingReportFormController extends AnchorPane {

    @FXML
    private Button btnEdit;
    @FXML
    private Button btnCreate;

    @FXML
    private Label lblId;
    @FXML
    private DatePicker dtpDate;
    @FXML
    private TextField txtTime;
    @FXML
    private TextField txtAirport;
    @FXML
    private TextField txtTagNr;
    @FXML
    private TextField txtFlightNr;
    @FXML
    private TextField txtDestination;
    @FXML
    private TextField txtFirstname;
    @FXML
    private TextField txtLastname;
    @FXML
    private TextField txtAdres;
    @FXML
    private TextField txtState;
    @FXML
    private TextField txtPostalCode;
    @FXML
    private TextField txtCountry;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtLuggageType;
    @FXML
    private TextField txtLuggageBrand;
    @FXML
    private TextField txtLuggageColor;
    @FXML
    private TextField txtLuggageWeight;

    /***
     * Constructor to initialize a create form
     */
    public MissingReportFormController() {
        loadFXML();

        //config buttons
        btnCreate.setVisible(true);
    }

    /***
     * Constructor to initialize an edit form
     */
    public MissingReportFormController(MissingReport object) {
        loadFXML();

        //fill in input fields 
        initInputFields(object);

        //config buttons
        btnEdit.setVisible(true);
    }

    @FXML
    private void btnCreateMissingReport(ActionEvent event) {

        MissingReport.create(inputToObject());

        //Let the user know the new record was succesvuly added to the database
        GeneralConfig.showInfoAlert("De nieuwe vermissing is geregistreerd", "");

        GeneralConfig.mainPane.setCenter(new MissingReportManagementController());
    }
    
    
    @FXML
    private void btnCancel_Click(ActionEvent event){
        GeneralConfig.mainPane.setCenter(new MissingReportManagementController());
    }
    
    @FXML
    private void btnEditClick(ActionEvent event) {
        MissingReport.update(Integer.parseInt(lblId.getText()), inputToObject());
        
        //let the user know evrything went ok
        GeneralConfig.showInfoAlert("De record is succesvol geupdate.", "Gelukt!");
        
        //redirect the user to the Overview page
        GeneralConfig.mainPane.setCenter(new MissingReportManagementController());
    }

    /**
     * Puts all the userInput into a new MissingReport object
     *
     * @return a MissingReport object filled with the current input data from
     * the screen
     */
    private MissingReport inputToObject() {
        MissingReport missingReport = new MissingReport(
                dtpDate.getValue(),
                txtTime.getText(),
                txtAirport.getText(),
                txtFirstname.getText(),
                txtLastname.getText(),
                txtAdres.getText(),
                txtState.getText(),
                txtPostalCode.getText(),
                txtCountry.getText(),
                txtPhoneNumber.getText(),
                txtEmail.getText(),
                txtTagNr.getText(),
                txtFlightNr.getText(),
                txtDestination.getText(),
                txtLuggageType.getText(),
                txtLuggageBrand.getText(),
                txtLuggageColor.getText(),
                "",
                txtLuggageWeight.getText());
        return missingReport;
    }

    /**
     * Loads the fxml file and binds it to this controller
     */
    public void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MissingReportForm.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /***
     * Fills all the input fields with the values of a specific userReport record, this is used to initialize the edit screen
     * @param object A missingReport object that contains all data for a specific missingReport
     */
    public void initInputFields(MissingReport object) {
        //an invisible label that holds the unique identifier of the object. 
        lblId.setText(Integer.toString(object.getId()));

        //Fill all input fields the current data of the object
        dtpDate.setValue(object.getDate());
        txtTime.setText(object.getTime());
        txtAirport.setText(object.getAirport());
        txtFirstname.setText(object.getFirstname());
        txtLastname.setText(object.getLastname());
        txtAdres.setText(object.getAdres());
        txtState.setText(object.getState());
        txtPostalCode.setText(object.getPostalCode());
        txtCountry.setText(object.getCountry());
        txtPhoneNumber.setText(object.getPhoneNumber());
        txtEmail.setText(object.getEmail());
        txtTagNr.setText(object.getLuggageTagNr());
        txtFlightNr.setText(object.getFlightNr());
        txtDestination.setText(object.getDestination());
        txtLuggageType.setText(object.getLuggageType());
        txtLuggageBrand.setText(object.getLuggageBrand());
        txtLuggageColor.setText(object.getLuggageColor());
        txtLuggageWeight.setText(object.getWeight());
        //txtLuggageSpecialCharacteristics TODO
    }
}
