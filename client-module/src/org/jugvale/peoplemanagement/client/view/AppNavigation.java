/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.peoplemanagement.client.view;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * It will control the application navigation between screens
 *
 * @author william
 */
public class AppNavigation {

    public static enum Screens {

        SCAN("/org/jugvale/peoplemanagement/client/view/scan.fxml"),
        NAVIGATION("/org/jugvale/peoplemanagement/client/view/navigation.fxml"),
        MANAGE("/org/jugvale/peoplemanagement/client/view/manage.fxml"),
        NEW("/org/jugvale/peoplemanagement/client/view/new.fxml");

        private final String fxml;

        Screens(String fxml) {
            this.fxml = fxml;
        }

        public String getFxml() {
            return this.fxml;
        }
    }

    public BooleanProperty isHome;
    private static AppNavigation instance;
    private Pane contentParent;
    private Map<Screens, Node> initializedScreens;
    private Screens home = Screens.NAVIGATION;

    private AppNavigation() {
        isHome = new SimpleBooleanProperty();
    }

    public static AppNavigation getInstance() {
        if (instance == null) {
            instance = new AppNavigation();
        }
        return instance;
    }

    public void navigateTo(Screens s) {
        try {
            Parent p;
            p = FXMLLoader.load(getClass().getResource(s.getFxml()));
            contentParent.getChildren().setAll(p);
            isHome.set(s.equals(home));
        } catch (IOException ex) {
            Logger.getLogger(AppNavigation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setContentParent(Pane contentParent) {
        this.contentParent = contentParent;
    }

    public void setHome(Screens s) {
        this.home = s;
    }
}
