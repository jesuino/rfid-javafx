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
import javafx.scene.layout.Pane;
import org.jugvale.peoplemanagement.client.view.AppNavigation;

/**
 * FXML Controller class
 *
 * @author william
 */
public class NavigationController {
    
    public void navigateToNew() {
        AppNavigation.getInstance().navigateTo(AppNavigation.Screens.NEW);
    }

    public void navigateToManage() {
        AppNavigation.getInstance().navigateTo(AppNavigation.Screens.MANAGE);
    }

    public void navigateToScan() {
        AppNavigation.getInstance().navigateTo(AppNavigation.Screens.SCAN);
    }
    
}
