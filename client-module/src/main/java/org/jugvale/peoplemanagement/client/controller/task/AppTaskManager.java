/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.peoplemanagement.client.controller.task;

import java.util.function.Consumer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

/**
 *
 * This class will wrap the execution of a Task and expose the interesting info.
 * So the App will know what is being done and give a feedback to the user
 * 
 * @author william
 */
public class AppTaskManager {

    public final static BooleanProperty runningProperty;
    public final static StringProperty runningTaskMssage;

    static {
        runningProperty = new SimpleBooleanProperty();
        runningTaskMssage = new SimpleStringProperty();
    }

    public static void doTask(Task<?> t) {
        runningTaskMssage.set(t.getTitle());
        runningProperty.unbind();
        runningProperty.bind(t.runningProperty());
        new Thread(t).start();
    }

}
