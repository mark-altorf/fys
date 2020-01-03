/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hva.fys.btracker;

import hva.fys.btracker.Models.FoundLuggage;
import hva.fys.btracker.Models.MissingReport;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author mich2
 */
public class FoundLuggageManagementController extends AnchorPane {

    @FXML
    private TableView<FoundLuggage> TableViewFoundLuggage;
    
    //the datasource of the missingReport tableView
    private final ObservableList<FoundLuggage> datasourceFoundLuggageList = FXCollections.observableArrayList();

    public FoundLuggageManagementController() {
        //load the associaded fxml file
        loadFXML();

        //init and populate the tableView
        initTableView();
        populateTableView();
    }

    public void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FoundLuggageManagement.fxml"));

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
        TableViewFoundLuggage.setItems(datasourceFoundLuggageList);

        //bind each TableColumn to its associated property from the missingReport class
        for (int i = 0; i < TableViewFoundLuggage.getColumns().size(); i++) {
            //retreive a table column
            TableColumn column = (TableColumn) TableViewFoundLuggage.getColumns().get(i);

            //get the propertie name this column should be bound to based on the id of the column
            String propName = column.getId();

            if (propName != null && !propName.isEmpty()) {

                //bind the column to its associated property
                column.setCellValueFactory(new PropertyValueFactory<>(propName));
            }
        }
    }

    /**
     * populates the tableView with its associated data from the database
     */
    private void populateTableView() {
        datasourceFoundLuggageList.clear();
        datasourceFoundLuggageList.addAll(FoundLuggage.getAll());
    }
}
