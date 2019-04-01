package app.example.Scene.ChangeScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Controller implements Initializable {

	@FXML private Button button1;
	@FXML private Button button2;
	
	@FXML private VBox content;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ScreenController screenController = new ScreenController(content);
		try {       
	    	screenController.addScreen("1", FXMLLoader.load(getClass().getResource( "first.fxml" )));   	
			screenController.addScreen("2", FXMLLoader.load(getClass().getResource( "second.fxml" )));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		button1.setOnAction(e -> {
			screenController.activate("1");
		});
		
		button2.setOnAction(e -> {
			screenController.activate("2");
		});
		
	}

	public void set() {
			
	}

	
	
}
