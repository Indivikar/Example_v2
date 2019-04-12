package app.example.Ping.PingTimeoutsSpeichern;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.IOException;

import app.example.Ping.PingTimeoutsSpeichern.Demo;
import app.example.Ping.PingTimeoutsSpeichern.controller.MainWindowController;


public class Demo extends Application {

	private MainWindowController mainWindowController;


	public Demo() {
		Platform.runLater(() -> {           
	           start(new Stage());		       
		});
	}
	
    @Override public void start(final Stage stage) {
        startStageEinstellungen(stage);
    }


    @Override
    public void stop() throws Exception {

    }

	public void startStageEinstellungen(final Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(Demo.class.getResource("view/fxml/MainWindowView.fxml"));
			AnchorPane root = loader.load();

			stage.setTitle("Ping");
			stage.getIcons().add(new Image(Demo.class.getResourceAsStream( "view/images/PING.png" )));
			stage.setMinWidth(400.00);
			stage.setMinHeight(300.00);

			Scene scene = new Scene(root);
			scene.getStylesheets().add(Demo.class.getResource("view/css/MainWindowCSS.css").toExternalForm());

			mainWindowController = loader.getController();
			mainWindowController.setMainWindowStage(this, stage);

			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

    }


    public static void main(String[] args) throws IOException, java.awt.AWTException {
        launch(args);
    }
}
