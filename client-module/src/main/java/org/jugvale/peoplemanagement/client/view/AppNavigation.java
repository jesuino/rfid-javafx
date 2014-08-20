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
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.jugvale.peoplemanagement.client.controller.task.AppTaskManager;

/**
 * It will control the application navigation between screens
 *
 * @author william
 */
public class AppNavigation {

    public static enum Screens {

        SCAN("/views/scan.fxml"),
        NAVIGATION("/views/navigation.fxml"),
        MANAGE("/views/manage.fxml"),
        NEW("/views/new.fxml");

        private final String fxml;

        Screens(String fxml) {
            this.fxml = fxml;
        }

        public String getFxml() {
            return this.fxml;
        }
    }

    // this will be used when a task is running
    private final ProgressIndicator pgRunning;

    public BooleanProperty isHome;
    private static AppNavigation instance;
    private Pane contentParent;
    private Map<Screens, Node> initializedScreens;
    private Screens home = Screens.NAVIGATION;

    private AppNavigation() {
        isHome = new SimpleBooleanProperty();
        pgRunning = new ProgressIndicator();
        pgRunning.setMaxSize(150, 150);
        pgRunning.visibleProperty().bind(AppTaskManager.runningProperty);
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
            p.disableProperty().bind(AppTaskManager.runningProperty);
            contentParent.getChildren().setAll(new StackPane(p, pgRunning));
            applyEffect(contentParent);
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

    private void applyEffect(Node n) {
        ScaleTransition s = new ScaleTransition(Duration.millis(200));
        s.setFromX(0);
        s.setToX(1);
        s.setFromY(0);
        s.setToY(1);
        s.setCycleCount(1);
        FadeTransition f = new FadeTransition(Duration.millis(500));
        f.setFromValue(0);
        f.setToValue(1);
        f.setCycleCount(1);
        new ParallelTransition(n, s, f).play();
    }
}
