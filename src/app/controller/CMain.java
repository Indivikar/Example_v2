package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawersStack;

import app.Start;
import app.view.function.IWindowMax;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CMain implements Initializable, IWindowMax {

	private Start start;
	private Stage primaryStage;
	
	@FXML private AnchorPane mainAnchorPane;
	@FXML private JFXDrawersStack drawersStack;
	@FXML private Button buttonWindowMin;
	@FXML private Button buttonWindowMax;
	@FXML private Button buttonWindowClose;

	@FXML public void actionButtonWindowMin(){
		primaryStage.setIconified(true);
	}
	
	@FXML public void actionButtonWindowMax(){
		setWindowMaxIcon(primaryStage, buttonWindowMax, mainAnchorPane, Start.hasShadowPane);
	}
	
	@FXML public void actionButtonWindowClose(){
		primaryStage.close();
		Platform.exit();
		System.exit(0);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
	// Getter
	public JFXDrawersStack getDrawersStack() {return drawersStack;}
	public Button getButtonWindowMax() {return buttonWindowMax;}

	// Setter
	public void set(Start startTest, Stage primaryStage) {
		this.start = start;
		this.primaryStage = primaryStage;
	}
	

}
