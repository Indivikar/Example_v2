package app.example.Listen.AddNewComponent;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddNewCombonentDemo extends Application {
    
	public AddNewCombonentDemo() {
		Platform.runLater(() -> {           
	        try {
				start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		       
		});
	}
	
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane  root = FXMLLoader.load(getClass().getResource("main.fxml")); 
        primaryStage.setTitle("Add new combonent Demo");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }

    
    
    public static void main(String[] args) {
        launch(args);
    }
} 
