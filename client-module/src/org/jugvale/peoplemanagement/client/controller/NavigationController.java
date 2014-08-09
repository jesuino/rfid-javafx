/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.peoplemanagement.client.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.value.ObservableBooleanValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.jugvale.peoplemanagement.client.view.AppNavigation;

/**
 * FXML Controller class
 *
 * @author william
 */
public class NavigationController implements Initializable {

    @FXML
    Pane contentPane;

    @FXML
    Button btnHome;

    public void navigateToHome() {
        AppNavigation.getInstance().navigateTo(AppNavigation.Screens.NAVIGATION);
    }

    public void navigateToNew() {
        AppNavigation.getInstance().navigateTo(AppNavigation.Screens.NEW);
    }

    public void navigateToManage() {
        AppNavigation.getInstance().navigateTo(AppNavigation.Screens.MANAGE);
    }

    public void navigateToScan() {
        AppNavigation.getInstance().navigateTo(AppNavigation.Screens.SCAN);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AppNavigation.getInstance().setContentParent(contentPane);
        btnHome.visibleProperty().bind(AppNavigation.getInstance().isHome.not());
        AppNavigation.getInstance().navigateTo(AppNavigation.Screens.NAVIGATION);
    }
}
