/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.peoplemanagement.client.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jugvale.peoplemanagement.client.controller.task.AppTaskManager;
import org.jugvale.peoplemanagement.client.model.Person;
import org.jugvale.peoplemanagement.client.service.PersonService;

/**
 *
 * @author william
 */
public class ManageController implements Initializable {
    
    PersonService service;
    @FXML
    TableView<Person> tblPerson;
    @FXML
    TextField txtFilter;
    @FXML
    Label lblError;
    @FXML
    Button btnRemove;
    
    @FXML
    TableColumn cId;
    @FXML
    TableColumn cFirstName;
    @FXML
    TableColumn cLastName;
    @FXML
    TableColumn cRFID;
    @FXML
    TableColumn cJob;
    @FXML
    TableColumn cAge;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = PersonService.getInstance();
        
        btnRemove.disableProperty().bind(tblPerson.getSelectionModel().selectedItemProperty().isNull());
        cId.setCellValueFactory(new PropertyValueFactory("id"));
        cFirstName.setCellValueFactory(new PropertyValueFactory("firstName"));
        cLastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        cRFID.setCellValueFactory(new PropertyValueFactory("rfid"));
        cJob.setCellValueFactory(new PropertyValueFactory("job"));
        cAge.setCellValueFactory(new PropertyValueFactory("age"));
        refresh();
    }
    
    public void refresh() {
        Task<List<Person>> refreshTask = new Task<List<Person>>() {
            @Override
            protected List<Person> call() throws Exception {
                updateMessage("Retrieving the list of people");
                return service.listAll();
            }
            
            @Override
            protected void succeeded() {
                tblPerson.getItems().setAll(getValue());
            }
            
            @Override
            protected void failed() {
                lblError.setText(getException().getMessage());
            }
            
        };
        AppTaskManager.doTask(refreshTask);
    }
    
    public void remove() {
        long id = tblPerson.getSelectionModel().getSelectedItem().getId();
        Task<String> removeTask = new Task<String>() {
            
            @Override
            protected String call() throws Exception {
                updateMessage("Removing person with ID " + id);
                return service.remove(id);
            }
            
            @Override
            protected void succeeded() {
                lblError.setText(getValue());
                refresh();
            }

            @Override
            protected void failed() {
                lblError.setText(getException().getMessage());
            }     
        };
        AppTaskManager.doTask(removeTask);
    }
    
    public void filter() {
        tblPerson.getItems().clear();
        Predicate<Person> cond = p -> {
            return p.getFirstName().toLowerCase().contains(txtFilter.getText().toLowerCase());
        };
        // TODO: fix filter
   //     allObjects.stream().filter(cond).forEach(tblPerson.getItems()::add);
    }
}
