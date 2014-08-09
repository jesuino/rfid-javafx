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
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author william
 */
public class ScanController implements Initializable {
    
    @FXML
    StackPane mainPane;
    
    @FXML
    Label lblRFID;
    
    @FXML
    Label lblReadInfo;
    
    @FXML
    Label lblStatus;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   //     mainPane.getChildren().add()
    }    
    
}
