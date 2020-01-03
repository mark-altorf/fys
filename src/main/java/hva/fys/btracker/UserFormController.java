/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hva.fys.btracker;

import hva.fys.btracker.Models.User;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author mich2
 */
public class UserFormController extends AnchorPane {

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPasswordCheck;

    public UserFormController(){
        loadFXML();
    }
    
    public void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserForm.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnCreateUser(ActionEvent event) {
        if (!txtPassword.getText().equals(txtPasswordCheck.getText())) {
            GeneralConfig.showInfoAlert("Het lijkt erop dat de password velden niet overeenkomen, probeer het nogmaals.", "");
            return;
        }

        User.create(new User(txtUsername.getText(), txtPassword.getText()));
        clearForm();
        
        //let the user know the new user has been created
        GeneralConfig.showInfoAlert("De gebruiker is succesvol aangemaakt!", "");

        //redirect the user back to the management page
        GeneralConfig.mainPane.setCenter(new UserManagementController());
      
    }
    
    @FXML
    private void btnCancel_Click(ActionEvent event){
        //redirect the user back to the userManagement page
        GeneralConfig.mainPane.setCenter(new UserManagementController());
    }
    
      private void clearForm() {
        txtPassword.clear();
        txtPasswordCheck.clear();
        txtUsername.clear();
    }
      
   

}
