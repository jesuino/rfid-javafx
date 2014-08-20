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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.jugvale.peoplemanagement.client.view.AppNavigation;

/**
 *
 * @author william
 */
public class InitialController implements Initializable {

    @FXML
    Pane contentPane;

    @FXML
    Button btnHome;

    public void navigateToHome() {
        AppNavigation.getInstance().navigateTo(AppNavigation.Screens.NAVIGATION);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppNavigation.getInstance().setContentParent(contentPane);
        btnHome.visibleProperty().bind(AppNavigation.getInstance().isHome.not());
        AppNavigation.getInstance().navigateTo(AppNavigation.Screens.NAVIGATION);
    }

}
