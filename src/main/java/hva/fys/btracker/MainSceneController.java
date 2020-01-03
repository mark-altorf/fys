package hva.fys.btracker;

import hva.fys.btracker.Models.FoundLuggage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainSceneController implements Initializable {

    @FXML
    public BorderPane mainPane;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private void menuUserManagement_Click(ActionEvent event) {
        UserManagementController userManagement = new UserManagementController();
        mainPane.setCenter(userManagement);
    }
    
    @FXML
    private void menuStatistics_Click(ActionEvent event) {
        StatisticsController Statistics = new StatisticsController();
        mainPane.setCenter(Statistics);
    }
    
    @FXML
    private void menuMissingReportManagement_Click(ActionEvent event) {
        MissingReportManagementController missingReportC = new MissingReportManagementController();
        mainPane.setCenter(missingReportC);
    }

    @FXML
    private void homePage_click(ActionEvent event) {
        mainPane.setCenter(new FoundLuggageManagementController());
    }

    @FXML
    private void menuImportFoundLuggage_Click(ActionEvent event) {

        String filePath = GeneralConfig.openFileDialog();

        //if no file is selcted just return
        if (filePath == "") {
            return;
        }

        List<FoundLuggage> foundLuggageList = new ArrayList();
        try {
            //get al the foundLuggage records from an excel file
            foundLuggageList = FoundLuggage.getObjectListFromExcel(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int addedRecords = 0;

        //add each foundLuggage record from the excel export to the database
        for (int i = 0; i < foundLuggageList.size(); i++) {
            FoundLuggage object = foundLuggageList.get(i);

            //only add the record if it does not exist yet
            if (!FoundLuggage.exists(object.getRegistrationNr())) {
                FoundLuggage.create(foundLuggageList.get(i));
                addedRecords++;
            }
        }

        //let the user know how many records have been inserted into the database
        GeneralConfig.showInfoAlert(Integer.toString(addedRecords) + " out of " + Integer.toString(foundLuggageList.size())
                + " have successfully been added to the database", "Done!");

        
        //just redirect to the foundLuggageManagement controller to trigger its constructor wich will update the tableView
        mainPane.setCenter(new FoundLuggageManagementController());
        
    }

    @FXML
    private void logout_Click(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
            Stage stage = new Stage();
            stage.setTitle("");
            stage.setScene(new Scene(root));
            stage.show();

            //hide the current window
            mainAnchorPane.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Pass the borderPane from the mainScene to the static class GeneralConfig so other scenes/ controllers can acces it
        GeneralConfig.mainPane = this.mainPane;

        mainPane.setCenter(new FoundLuggageManagementController());
    }
}
