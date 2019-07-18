package app.example.Threads.LoadFilesInBackground;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StageOneDemo extends Application {
    
	private Stage primaryStage;
	private StageOneDemo PrinterDemo;
	
	public StageOneDemo() {
		start(new Stage());
	}

    @Override
    public void start(Stage primaryStage) {
    	this.PrinterDemo = this;
        this.primaryStage = primaryStage;

        Button buttonPrint = new Button("Print...");               
        buttonPrint.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                	new StageTwo();
            }
        });
        
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(buttonPrint);
 
        Scene scene = new Scene(root, 500, 250);
 
        primaryStage.setTitle("Load files in Background Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public StageOneDemo getPrinterDemo() {return PrinterDemo;}
    public Stage getPrimaryStage() {return primaryStage;}
    
    public static void main(String[] args) {
        launch(args);
    }
}
