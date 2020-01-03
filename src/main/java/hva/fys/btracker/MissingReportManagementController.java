/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hva.fys.btracker;

import hva.fys.btracker.Models.MissingReport;
import hva.fys.btracker.Models.User;
import java.io.IOException;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author mich2
 */
public class MissingReportManagementController extends AnchorPane {

    @FXML
    private GridPane gridPaneNewMissingReport;

    @FXML
    private TableView<MissingReport> tableViewMissingReports;

    @FXML
    private BorderPane mainPane;
    
    @FXML
    private CheckBox checkBoxShowResolvedMissingReports;

    //the datasource of the missingReport tableView
    private final ObservableList<MissingReport> datasourceMissingReportList = FXCollections.observableArrayList();

    public MissingReportManagementController() {
        loadFXML();

        //get the tableView ready for the view
        initTableView();
        populateTableViewUnresolved();

    }

    @FXML
    private void btnEditMissingReport_Click(ActionEvent event) {
        MissingReport missingReport = tableViewMissingReports.getSelectionModel().getSelectedItem();

        //if no item is selected let the user know he has to select a item first and return
        if (missingReport == null) {
            GeneralConfig.showInfoAlert("Er is geen item geselecteerd om aan te passen, selecteer een item in de tabel en probeer het opnieuw.", "Geen vermissing geselcteerd!");
            return;
        }
        GeneralConfig.mainPane.setCenter(new MissingReportFormController(missingReport));
    }

    @FXML
    private void btnNewMissingReport_Click(ActionEvent event) {
        GeneralConfig.mainPane.setCenter(new MissingReportFormController());
    }

    @FXML
    private void btnMatchMissingReport_Click(ActionEvent event) {
        MissingReport missingReport = tableViewMissingReports.getSelectionModel().getSelectedItem();

        //if no item is selected let the user know he has to select an item first, and return the user to the overview page
        if (missingReport == null) {
            //alert the user about the problem
            GeneralConfig.showInfoAlert("Er is geen vermissing geselecteerd om een matching zoekopdracht op uit te voeren, selecteer een vermissing in"
                    + " de tabel en probeer het opnieuw",
                    "Geen vermissing geselecteerd!");
            return;

        }

        GeneralConfig.mainPane.setCenter(new MissingReportMatchingController(missingReport));
    }
    
    @FXML
    private void checkBoxShowResolvedMissingReports_Change(ActionEvent event){
        if(checkBoxShowResolvedMissingReports.isSelected()){
            populateTableViewResolved();
        }
        else{
            populateTableViewUnresolved();
        }
    }

    public void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MissingReportManagement.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * *
     * Initializes the tableView, binds the tableview to its datasource and
     * binds each column in the tableview to its associated class property.
     */
    private void initTableView() {
        //associate the observableList with the tableView (bind the dataSource)
        tableViewMissingReports.setItems(datasourceMissingReportList);

        //bind each TableColumn to its associated property from the missingReport class
        for (int i = 0; i < tableViewMissingReports.getColumns().size(); i++) {
            //retreive a table column
            TableColumn column = (TableColumn) tableViewMissingReports.getColumns().get(i);

            //get the propertie name this column should be bound to based on the id of the column
            String propName = column.getId();

            if (propName != null && !propName.isEmpty()) {

                //bind the column to its associated property
                column.setCellValueFactory(new PropertyValueFactory<>(propName));
            }
        }

    }

    /**
     * *
     * Populates the tableView with all the unresolved missingReports
     */
    private void populateTableViewUnresolved() {
        datasourceMissingReportList.clear();
        datasourceMissingReportList.addAll(MissingReport.getAllUnResolved());
    }

    /**
     * *
     * Populates the tableView with all the resolved missingReports
     */
    private void populateTableViewResolved() {
        datasourceMissingReportList.clear();
        datasourceMissingReportList.addAll(MissingReport.getAllResolved());
    }
}
