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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author mich2
 */
public class MissingReportMatchingController extends AnchorPane {

    @FXML
    private TableView<FoundLuggage> tableViewMatches;
    //the datasource of the posible matches table view
    private final ObservableList<FoundLuggage> datasourceMatches = FXCollections.observableArrayList();

    @FXML
    private CheckBox checkBoxShowAll;

    @FXML
    private ImageView imgTitle;

    MissingReport missingReportToMatch;

    @FXML
    private void checkBoxShowAll_Change(ActionEvent event) {
        if (checkBoxShowAll.isSelected()) {
            populateTableViewAll();
        } else {
            populateTableViewPosibleMatches(missingReportToMatch);
        }
    }

    @FXML
    private void btnMatch_Click(ActionEvent event) {
        FoundLuggage selectedItem = tableViewMatches.getSelectionModel().getSelectedItem();

        //check if a item was selected, if not let the user know this is required
        if (selectedItem == null) {
            GeneralConfig.showInfoAlert("Er is geen item geselecteerd om aan de vermissing te koppelen, selecteer een bagage record en probeer het opnieuw", "Oops");
            return;
        }

        //match the missing report with the currently selected foundLuggage row in the database
        missingReportToMatch.match(selectedItem);

        //let the user know the action was succsesvol and return him to the missingReport management page
        GeneralConfig.showInfoAlert("De vermissing is sucsesvol gekoppeld aan het door jouw geselecteerde bagage object", "Gelukt!");
        GeneralConfig.mainPane.setCenter(new MissingReportManagementController());
    }

    public MissingReportMatchingController(MissingReport missingReportToMatch) {
        loadFXML();

        this.missingReportToMatch = missingReportToMatch;

        //init the tableView
        initTableView(tableViewMatches, datasourceMatches);
        populateTableViewPosibleMatches(missingReportToMatch);

    }

    /**
     * *
     * loads the fxml file and binds it to this controller
     */
    public void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MissingReportMatching.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Initializes the tableView, binds the tableview to its datasource and
     * binds each column in the tableview to its associated class property.
     *
     * @param tbl the table you want to init
     */
    private void initTableView(TableView<FoundLuggage> tbl, ObservableList<FoundLuggage> dataSource) {
        //associate the observableList with the tableView (bind the dataSource)
        tbl.setItems(dataSource);

        //bind each TableColumn to its associated property from the missingReport class
        for (int i = 0; i < tbl.getColumns().size(); i++) {
            //retreive a table column
            TableColumn column = (TableColumn) tbl.getColumns().get(i);

            //get the propertie name this column should be bound to based on the id of the column
            String propName = column.getId();

            if (propName != null && !propName.isEmpty()) {

                //bind the column to its associated property
                column.setCellValueFactory(new PropertyValueFactory<>(propName));
            }
        }

    }

    /**
     * populates the tableView with all foundluggage records that possibly match
     * the missing report
     */
    private void populateTableViewPosibleMatches(MissingReport object) {
        datasourceMatches.clear();
        datasourceMatches.addAll(FoundLuggage.getPosibleMatches(object));
    }

    /**
     * populates the tableView with all the foundLugagge records in the database
     */
    private void populateTableViewAll() {
        datasourceMatches.clear();
        datasourceMatches.addAll(FoundLuggage.getAll());
    }

}
