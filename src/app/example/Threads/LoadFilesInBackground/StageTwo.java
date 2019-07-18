package app.example.Threads.LoadFilesInBackground;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
 
 
public class StageTwo extends Application {
	
		  public StageTwo() {
			  try {
				  start(new Stage());
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
		  }

          @Override
          public void start(Stage primaryStage) throws IOException {
					FXMLLoader loader = new FXMLLoader(StageTwo.class.getResource("main.fxml"));  
					AnchorPane root = loader.load();
					
					Scene scene = new Scene(root);
	
					primaryStage.setTitle("Load file Stage");
	             
		            primaryStage.setScene(scene);
		            primaryStage.sizeToScene();
		             
		            primaryStage.show();
					
         }
          
   }
