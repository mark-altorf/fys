/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hva.fys.btracker;

import java.awt.FileDialog;
import java.awt.Frame;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author mich2
 */
public class GeneralConfig {

    public static BorderPane mainPane;

    public static String databaseName = "BTracker";

    /**
     * *
     * Shows an information alert
     *
     * @param message The message you want to display in the alert
     * @param headerText Optional, if you don't want to use a header just pass
     * in an empty string
     */
    public static void showInfoAlert(String message, String headerText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /***
     * Asks the user for a file
     * @param parent the Frame (Scene) the dialog should be shown on
     * @return String the filePath
     */
    public static String openFileDialog() {
        String filePath = "";

        //get the file from the user
        FileDialog fileDialog = new FileDialog((Frame)null, "Chose a file", FileDialog.LOAD);
        fileDialog.setFile("*.xlsx");
        fileDialog.setVisible(true);
        if (fileDialog.getFile() != null) {
            filePath = fileDialog.getDirectory() + fileDialog.getFile();
        }
        return filePath;
    }
}
