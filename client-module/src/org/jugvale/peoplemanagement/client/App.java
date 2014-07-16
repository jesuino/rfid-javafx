/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jugvale.peoplemanagement.client;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jugvale.peoplemanagement.client.view.AppNavigation;

/**
 * The main class
 * @author william
 */
public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Pane center = new Pane();
        
        Label lblTitle = new Label("RFID People Management");
        lblTitle.getStyleClass().add("title");
        root.setTop(lblTitle);
        root.setCenter(center);
        
        BorderPane.setAlignment(lblTitle, Pos.CENTER);
        
        Scene scene = new Scene(root, 800, 600 );
        scene.getStylesheets().add("/org/jugvale/peoplemanagement/client/appStyle.css");
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        lblTitle.setOnMouseClicked(e -> {
            AppNavigation.getInstance().navigateTo(AppNavigation.Screens.INITIAL);
        });
        AppNavigation.getInstance().setContentParent(center);
        AppNavigation.getInstance().navigateTo(AppNavigation.Screens.INITIAL);
    }
}