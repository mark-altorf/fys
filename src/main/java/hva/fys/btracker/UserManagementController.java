/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hva.fys.btracker;

import hva.fys.btracker.Models.User;
import hva.fys.btracker.database.DatabaseManager;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author mich2
 */
public class UserManagementController extends AnchorPane {
    @FXML
    private TableView tableViewUsers;

    //the datasource of the User tableView
    private final ObservableList<User> dataSourceUserList = FXCollections.observableArrayList();

    public UserManagementController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserManagement.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        //initialize and populate the tableView
        initTableView();
        populateTableView();
    }

    
    @FXML
    private void btnNewUser_Click(ActionEvent event) {
        GeneralConfig.mainPane.setCenter(new UserFormController());
    }

  

  
    /**
     * *
     * Initializes the tableView, binds the tableview to its datasource and
     * binds each column in the tableview to its associated class property.
     */
    private void initTableView() {
        //associate the observableList with the tableView (bind the dataSource)
        tableViewUsers.setItems(dataSourceUserList);
        
           //bind each TableColumn to its associated property from the FoundLuggage class
        for (int i = 0; i < tableViewUsers.getColumns().size(); i++) {
            //retreive a table column
            TableColumn column = (TableColumn) tableViewUsers.getColumns().get(i);

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
        dataSourceUserList.clear();
        dataSourceUserList.addAll(User.getAll());
    }
    
   
}
