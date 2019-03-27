package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.Start;
import app.view.function.IWindowMax;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CDrawerContent implements Initializable, IWindowMax {

	private Start start;
	private Stage primaryStage;
	
	@FXML private Button buttonDrawerClose;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		buttonDrawerClose.setOnAction(e -> {
			start.getDrawer().close();
		});		
	}

	public void set(Start start, Stage primaryStage) {
		this.start = start;
		this.primaryStage = primaryStage;		
	}
	

}
