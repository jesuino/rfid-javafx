/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.peoplemanagement.client;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The main class
 *
 * @author william
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        URL rootUrl = getClass().getResource("/org/jugvale/peoplemanagement/client/view/initial.fxml");

        Pane root = null;
        try {
            root = FXMLLoader.load(rootUrl);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        Scene scene = new Scene(root, 500, 500);
        scene.getStylesheets().add("/org/jugvale/peoplemanagement/client/appStyle.css");
        primaryStage.setTitle("People Management APP");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
