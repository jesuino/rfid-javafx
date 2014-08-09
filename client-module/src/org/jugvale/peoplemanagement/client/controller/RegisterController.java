/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.peoplemanagement.client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.jugvale.peoplemanagement.client.rfid.ui.FTDIReaderPane;

/**
 *
 * @author william
 */
public class RegisterController implements Initializable {
    
    @FXML
    Pane formPane;
    @FXML
    StackPane mainPane; 
    @FXML
    Label lblRFID;
    @FXML
    TextField txtFirstName;
    @FXML
    TextField txtLastName;
    @FXML
    TextField txtJob;
    @FXML
    Label lblStatus;

    FTDIReaderPane rfidPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rfidPane = new FTDIReaderPane(s -> {
            lblRFID.setText("RFID: " + s);
        }, lblStatus::setText);
        rfidPane.setVisible(false);
        mainPane.getChildren().add(rfidPane);
        formPane.visibleProperty().bind(rfidPane.visibleProperty().not());
    }

    public void save() {
        System.out.println(txtLastName.getText());
        System.out.println(txtJob.getText());
        System.out.println(txtFirstName.getText());
        System.out.println(lblRFID.getText());
    }

    public void showRfidPane() {
        rfidPane.askForDeviceAndReadTag();
    }

}