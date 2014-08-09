/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.peoplemanagement.client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    ListProperty<Person> allObjects;
    FilteredList<Person> filteredObjects;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = PersonService.getInstance();
        allObjects = new SimpleListProperty<>();
        filteredObjects = new FilteredList<>(allObjects, e -> true);
        btnRemove.disableProperty().bind(tblPerson.getSelectionModel().selectedItemProperty().isNotNull());
        refresh();
    }

    public void refresh() {
        service.listAll(allObjects::setAll, lblError::setText);
        filteredObjects.setPredicate(e -> true);
    }

    public void remove() {
        long id = tblPerson.getSelectionModel().getSelectedItem().getId();
        service.remove(id, lblError::setText, lblError::setText);
    }

    public void filter() {
        filteredObjects.setPredicate(p -> p.getFirstName().toLowerCase().contains(txtFilter.getText().toLowerCase()));
    }

}
