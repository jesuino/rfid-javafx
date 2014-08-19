/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.peoplemanagement.client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.jugvale.peoplemanagement.client.model.Person;
import org.jugvale.peoplemanagement.client.rfid.ui.FTDIReaderPane;
import org.jugvale.peoplemanagement.client.service.PersonService;

/**
 *
 * @author william
 */
public class RegisterController implements Initializable {
    
    final String SCAN_RFID_MESSAGE = "Click here to scan a new RFID";
    
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
        lblRFID.setText(SCAN_RFID_MESSAGE);
        rfidPane = new FTDIReaderPane(s -> {
            lblRFID.setText("RFID: " + s + " (click to re-scan)");
            lblRFID.setUserData(s);
            success("");
        }, this::error);
        rfidPane.setVisible(false);
        mainPane.getChildren().add(rfidPane);
        formPane.visibleProperty().bind(rfidPane.visibleProperty().not());
        service = PersonService.getInstance();
    }
    
    public void save() {
        getPersonIfValid(valided -> {
            service.save(valided, p -> {
                clearForm();
                lblStatus.setText(String.format("Person %s with RFID %s saved with success", p.getFirstName(), p.getRfid()));
                lblStatus.setTextFill(Color.BLUE);
            }, this::error);
        }, this::error);
    }
    
    public void showRfidPane() {
        rfidPane.askForDeviceAndReadTag();
    }
    
    private void clearForm() {
        Stream.of(txtAge, txtFirstName, txtJob, txtLastName).forEach(t -> t.setText(""));
        lblRFID.setText(SCAN_RFID_MESSAGE);
    }
    
    private void getPersonIfValid(Consumer<Person> whenValid, Consumer<String> whenNotValid) {
        boolean isEmpty
                = Stream.of(txtAge, txtFirstName, txtJob, txtLastName)
                .map(t -> t.getText())
                .map(String::isEmpty)
                .reduce(Boolean::logicalAnd)
                .get();
        boolean isAgeNotANumber = !Pattern.matches("[0-9]+", txtAge.getText());
        boolean missingRFID = lblRFID.getUserData() == null;
        
        if (missingRFID) {
            whenNotValid.accept("Please, scan a RFID");
        } else if (isEmpty) {
            whenNotValid.accept("Please fill all fields");
        } else if (isAgeNotANumber) {
            whenNotValid.accept("Age must be a number");
        } else {
            whenValid.accept(new Person(
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    String.valueOf(lblRFID.getUserData()),
                    txtJob.getText(),
                    Integer.parseInt(txtAge.getText())
            ));
        }
    }
    
    private void error(String e) {
        lblStatus.setTextFill(Color.RED);
        lblStatus.setText(e);
    }
    
    private void success(String e) {
        lblStatus.setTextFill(Color.BLUE);
        lblStatus.setText(e);
    }
}
