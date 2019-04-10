package app.example.Kopieren.AutoPaste_from_Clipboard;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.util.Duration;

public class MainController implements Initializable {

	@FXML private TextArea textArea;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	    final Clipboard clipboard = Clipboard.getSystemClipboard();
        Timeline repeatTask = new Timeline(new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (clipboard.hasString()) {
                    String newString = clipboard.getString();
                    textArea.setText(newString);
                }
            }
        }));
        repeatTask.setCycleCount(Timeline.INDEFINITE);
        repeatTask.play();        
	}
	
}
