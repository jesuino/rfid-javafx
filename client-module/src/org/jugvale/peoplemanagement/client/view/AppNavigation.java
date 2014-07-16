/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.peoplemanagement.client.view;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * It will control the application navigation between screens
 *
 * @author william
 */
public class AppNavigation {

    private final String INITIAL_FXML = "/org/jugvale/peoplemanagement/client/view/initial.fxml";
    private final String MANAGE_FXML = "/org/jugvale/peoplemanagement/client/view/manage.fxml";
    private final String NEW_FXML = "/org/jugvale/peoplemanagement/client/view/new.fxml";
    private final String SCAN_FXML = "/org/jugvale/peoplemanagement/client/view/manage.fxml";

    private static AppNavigation instance;
    private Pane contentParent;
    private Map<Screens, Node> initializedScreens;

    private AppNavigation() {
        initializeScreens();
    }

    public static AppNavigation getInstance() {
        if (instance == null) {
            instance = new AppNavigation();
        }
        return instance;
    }

    private void initializeScreens() {
        try {
            initializedScreens = new HashMap<>();
            URL initialScreenUrl = getClass().getResource(INITIAL_FXML);
            URL newScreenUrl = getClass().getResource(NEW_FXML);
            URL manageScreenUrl = getClass().getResource(MANAGE_FXML);
            URL scanScreenUrl = getClass().getResource(SCAN_FXML);

            initializedScreens.put(Screens.INITIAL, FXMLLoader.load(initialScreenUrl));
            initializedScreens.put(Screens.NEW, FXMLLoader.load(newScreenUrl));
            initializedScreens.put(Screens.MANAGE, FXMLLoader.load(manageScreenUrl));
            initializedScreens.put(Screens.SCAN, FXMLLoader.load(scanScreenUrl));
        } catch (IOException ex) {
            Logger.getLogger(AppNavigation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void navigateTo(Screens s) {
        // TODO: Add effects when navigating to a screen
        contentParent.getChildren().setAll(initializedScreens.get(s));
    }

    public void setContentParent(Pane contentParent) {
        this.contentParent = contentParent;
    }

    public static enum Screens {

        SCAN,
        INITIAL,
        MANAGE,
        NEW
    }
}
