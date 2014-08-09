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
import org.jugvale.peoplemanagement.client.model.Person;
import org.jugvale.peoplemanagement.client.rfid.ui.FTDIReaderPane;
import org.jugvale.peoplemanagement.client.service.PersonService;

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
    TextField txtAge;
    @FXML
    Label lblStatus;

    PersonService service;

    FTDIReaderPane rfidPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rfidPane = new FTDIReaderPane(s -> {
            lblRFID.setText("RFID: " + s);
        }, lblStatus::setText);
        rfidPane.setVisible(false);
        mainPane.getChildren().add(rfidPane);
        formPane.visibleProperty().bind(rfidPane.visibleProperty().not());
        service = PersonService.getInstance();
    }

    public void save() {
        Person toSave = new Person(
                32,
                txtFirstName.getText(),
                txtLastName.getText(),
                lblRFID.getText(),
                txtJob.getText(),
                Integer.parseInt(txtAge.getText())
        );
        service.save(toSave, p -> {
            lblStatus.setText("Person " + p.getFirstName() + " with RFID " + p.getRfid() + " saved with success");
        }, lblStatus::setText);
    }

    public void showRfidPane() {
        rfidPane.askForDeviceAndReadTag();
    }

}
