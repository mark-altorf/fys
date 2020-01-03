/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hva.fys.btracker;

import hva.fys.btracker.database.DatabaseManager;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author mich2
 */
public class LoginSceneController {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;

    @FXML
    private void btnLoginClickAction(ActionEvent event) {
        DatabaseManager dbManager = new DatabaseManager(GeneralConfig.databaseName);
        int count = dbManager.executeCountQuery("SELECT COUNT(*) FROM user WHERE "
                + "username='" + txtUsername.getText() + "' "
                + "AND password='" + txtPassword.getText() + "' ");
        dbManager.close();

        if (count < 1) {
            GeneralConfig.showInfoAlert("Sorry maar de opgegeven combinatie van gebruikersnaam en wachtwoord is ongeldig. Probeer het opnieuw.",
                     "Ongeldige login");
            return;
        }

        redirectToMain(event);
    }

    public void redirectToMain(ActionEvent event) {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            Stage stage = new Stage();
            stage.setTitle("BTracker");
            stage.setScene(new Scene(root));
            stage.show();

            //hide the current window
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
