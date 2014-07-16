/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.peoplemanagement.client.controller;

import org.jugvale.peoplemanagement.client.view.AppNavigation;

/**
 * FXML Controller class
 *
 * @author william
 */
public class InitialController {

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
