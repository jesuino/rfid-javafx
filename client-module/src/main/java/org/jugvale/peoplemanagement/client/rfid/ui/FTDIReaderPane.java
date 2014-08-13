/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jugvale.peoplemanagement.client.rfid.ui;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import org.jugvale.peoplemanagement.client.rfid.LinuxFTDISerialReader;

/**
 * @author william
 *
 */
public class FTDIReaderPane extends TitledPane {

	LinuxFTDISerialReader reader = new LinuxFTDISerialReader();
	ListView<Path> listDevices;
	
	/**
	 * To be invoked when we read the RFID
	 */
	Consumer<String> onRead;
	Consumer<String> onError;

	private Path selectedDevice;

	
	public FTDIReaderPane() {
		this(null, null);
	}
	
	public FTDIReaderPane(Consumer<String> onRead, Consumer<String> onError) {
		super();
		this.onRead = onRead;
		this.onError = onError;
		settings();
		initComponent();
	}

	public void setOnRead(Consumer<String> onRead) {
		this.onRead = onRead;
	}


	public void setOnError(Consumer<String> onError) {
		this.onError = onError;
	}

	/**
	 * 
	 * Will show up and read a RFID tag, but it will always ask to select a tag
	 * 
	 * @throws IOException
	 *             Error when it can't read the device
	 */
	public void askForDeviceAndReadTag() {
		try {
			if (selectedDevice == null)
				fillDevices();
			else {
				readDevice();
			}
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(LinuxFTDISerialReader.DEFAULT_ERROR_MESSAGE);
		}
	}

	private void settings() {
		setText("Read RFID Tag");
		setMaxHeight(200);
		setMaxWidth(300);
		setStyle("-fx-stroke-width: 20;");
		setCollapsible(false);
		setVisible(false);
	}

	private void initComponent() {
		StackPane root = new StackPane();
		Label lblPassCard = new Label("Pass RFID Card");
		lblPassCard.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
		listDevices = new ListView<>();
		listDevices.setOnMouseReleased(this::newDeviceSelectedAction);
		root.getChildren().addAll(listDevices, lblPassCard);
		lblPassCard.visibleProperty().bind(listDevices.visibleProperty().not());
		this.setContent(root);
	}

	private void newDeviceSelectedAction(MouseEvent e) {
		Path selectedPath = listDevices.getSelectionModel().getSelectedItem();
		if (selectedPath != null) {
			listDevices.setVisible(false);
			listDevices.getSelectionModel().clearSelection();
			selectedDevice = selectedPath;
			readDevice();
			return;
		}
	}

	private void fillDevices() throws IOException {
		listDevices.setVisible(true);
		listDevices.getItems().setAll(reader.getAvailableDevices());
		listDevices.getSelectionModel().clearSelection();
	}

	private void readDevice() {
		new Thread(new Task<String>() {
			@Override
			protected String call() throws Exception {
				return reader.waitAndRead(selectedDevice);
			}

			@Override
			protected void succeeded() {
				try {
					success(this.get());
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
				setVisible(false);
			}

			@Override
			protected void failed() {
				fail(LinuxFTDISerialReader.DEFAULT_ERROR_MESSAGE);
				setVisible(false);
				selectedDevice = null;
			}
		}).start();
	}
	
	private void success(String value) {
		if(onRead == null) {
			throw new Error("You must set an onRead funcion");
		}
		onRead.accept(value);
	}
	
	private void fail(String value) {
		if(onError == null) {
			throw new Error("You must set an onError funcion");
		}
		onError.accept(value);
		
	}
}