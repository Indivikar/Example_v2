package app.example.Web.BrowserMitAlert;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.*;
import javafx.concurrent.Worker;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.*;

/** Demonstrates a modal confirm box in JavaFX.
    Dialog is rendered upon a blurred background.
    Dialog is translucent. */
public class ModalConfirmDemo extends Application {
	  public static void main(String[] args) { launch(args); }
	  
	  
	  public ModalConfirmDemo() {
		  Platform.runLater(() -> {           
		           start(new Stage());		       
		  });
	  }
	  
	  @Override public void start(final Stage primaryStage) {
		    // initialize the stage
		    primaryStage.setTitle("Modal Confirm Example");
		    final WebView webView = new WebView(); webView.getEngine().load("http://docs.oracle.com/javafx/");
		    primaryStage.setScene(new Scene(webView));
		    primaryStage.show();
		
		    // initialize the confirmation dialog
		    final Stage util = new Stage(StageStyle.TRANSPARENT);
		    util.initModality(Modality.APPLICATION_MODAL);
		    
		    HBox hBox = new HBox();
		    hBox.setSpacing(10);
		    hBox.setAlignment(Pos.CENTER);
		    hBox.setStyle("-fx-padding: 20; -fx-font-size: 20; -fx-background-color: linear-gradient(to bottom, derive(cadetblue, 20%), cadetblue); -fx-border-color: derive(cadetblue, -20%); -fx-border-width: 5;");
		    
		    Label label = new Label();
		    label.setText("Will you like this page?");
		    
		    Button buttonYes = new Button("Yes");
		    buttonYes.setDefaultButton(true);
		    buttonYes.setOnAction(e -> {
		    	System.out.println("Liked: " + webView.getEngine().getTitle());
		        primaryStage.getScene().getRoot().setEffect(null);
		        util.close();
		    });
		    
		    Button buttonNo = new Button("No");
		    buttonNo.setCancelButton(true);
		    buttonNo.setOnAction(e -> {
		        System.out.println("Disliked: " + webView.getEngine().getTitle());
		        primaryStage.getScene().getRoot().setEffect(null);
		        util.close();
		    });
		    
		    hBox.getChildren().addAll(label, buttonYes, buttonNo);
		    
		    util.setScene(new Scene(hBox));
		    util.getScene().setFill(Color.TRANSPARENT);
		    util.getScene().getRoot().setOpacity(0.9);
		
		    // show the confirmation dialog each time a new page is loaded.
		    webView.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
			      @Override public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State state, Worker.State newState) {
				        if (newState.equals(Worker.State.SUCCEEDED)) {
				          primaryStage.getScene().getRoot().setEffect(new BoxBlur());
				          util.show();
				          util.toFront();
				        }
			      }
		    });
	  }
}

