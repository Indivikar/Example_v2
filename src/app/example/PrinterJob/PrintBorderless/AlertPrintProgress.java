package app.example.PrinterJob.PrintBorderless;

import java.io.File;
import java.io.IOException;

import app.Start;
import app.view.function.StageVerschiebenMitAnchorPane;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertPrintProgress implements IWindowEigenschaften {

	private AlertPrintProgress AlertPrintSettings;
	private PrinterDemo printerDemo;
	private Alert alert;
	private Stage alertStage;
	private CAlertPrintProgress controller;
	private Button buttonCancel;
	private File filePDF;
	private String title;

	private void buttonStyle(Node b) {
		// in der Button-Bar befindet sich auch der ExpandableContent-Button, der nicht verändert werden soll.
		// es werden nur die Button geändert
		try {
			Button button = (Button) b;
			button.getStyleClass().add("myButton");
		} catch (Exception e) {
			
		}
	}
		
	public AlertPrintProgress(PrinterDemo printerDemo, String title) {
		this.AlertPrintSettings = this;
		this.printerDemo = printerDemo;
		this.filePDF = filePDF;
		this.title = title;
		
	  	FXMLLoader loader = new FXMLLoader(PrinterDemo.class.getResource("dialogPrintProgress.fxml"));
        DialogPane dialogPane = null;
        try {
        	dialogPane = (DialogPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		       
		alert = new Alert(AlertType.INFORMATION);
		alert.setDialogPane(dialogPane);

		alertStage = (Stage) dialogPane.getScene().getWindow();
		alertStage.initStyle(StageStyle.UNDECORATED);
		alertStage.setMaxHeight(150.0);
		alertStage.setAlwaysOnTop(true);

		dialogPane.getButtonTypes().addAll(ButtonType.CANCEL);

		ButtonBar buttonBar = (ButtonBar)dialogPane.lookup(".button-bar");
		buttonBar.getButtons().forEach(b->buttonStyle(b));

		buttonCancel = (Button) dialogPane.lookupButton(ButtonType.CANCEL); 
		buttonCancel.getStyleClass().add("myButton");

//		new StageVerschiebenMitAnchorPane(null, dialogPane, null, alertStage, false);
		
		alert.setTitle(title);
		alert.setHeaderText(null);

		controller = loader.getController();
		controller.set(printerDemo, this, title);

		setOpenWindowInWindowCenter(printerDemo.getPrimaryStage(), alertStage);

		alert.show();
	}

	// Getter	
	public Stage getAlertStage() {return alertStage;}
	public Button getButtonCancel() {return buttonCancel;}
	public CAlertPrintProgress getController() {return controller;}


	
}
