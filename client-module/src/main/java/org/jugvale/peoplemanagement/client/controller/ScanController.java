/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.peoplemanagement.client.controller;

import com.sun.javafx.binding.StringFormatter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.jugvale.peoplemanagement.client.controller.task.AppTaskManager;
import org.jugvale.peoplemanagement.client.model.Person;
import org.jugvale.peoplemanagement.client.rfid.ui.FTDIReaderPane;
import org.jugvale.peoplemanagement.client.service.PersonService;

/**
 * FXML Controller class
 *
 * @author william
 */
public class ScanController implements Initializable {

    @FXML
    VBox infoPane;

    @FXML
    StackPane mainPane;

    @FXML
    Label lblRFID;

    @FXML
    Label lblReadInfo;

    @FXML
    Label lblStatus;

    FTDIReaderPane rfidPane;

    PersonService service;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rfidPane = new FTDIReaderPane(s -> {
            lblRFID.setText("RFID: " + s);
            lblStatus.setText("RFID Read. Searching info on the DB...");
            getPersonInfo(s);
        }, lblStatus::setText);
        rfidPane.setVisible(false);
        mainPane.getChildren().add(rfidPane);
        infoPane.visibleProperty().bind(rfidPane.visibleProperty().not());
        service = PersonService.getInstance();
    }

    public void readRFID() {
        rfidPane.askForDeviceAndReadTag();
    }

    public void getPersonInfo(String rfid) {
        Task<Person> loadPerson = new Task<Person>() {
            @Override
            protected Person call() throws Exception {
                Person p = service.get(rfid);
                if (p == null) {
                    throw new Error("Person with rfid " + rfid + " not found.");
                }
                return p;
            }

            @Override

            protected void succeeded() {
                Person p = getValue();
                lblReadInfo.setText(String.format(
                        "Person %s %s, %d years old, is a %s.",
                        p.getFirstName(),
                        p.getLastName(),
                        p.getAge(),
                        p.getJob()
                ));
                lblStatus.setText("");
            }

            @Override
            protected void failed() {
                rfidPane.setVisible(false);
                lblStatus.setText(getException().getMessage());
                lblReadInfo.setText("Can't find any info on database...");
            }
        };
        AppTaskManager.doTask(loadPerson);
    }

}
