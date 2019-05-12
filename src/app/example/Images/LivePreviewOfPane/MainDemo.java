package app.example.Images.LivePreviewOfPane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainDemo extends Application {

  private double width = 500.0;
	
  public MainDemo() {
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
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));

    Scene scene = new Scene(loader.load(), width, 300);

    primaryStage.setScene(scene);
    primaryStage.setTitle("Main Window");
    primaryStage.setResizable(false);
    primaryStage.show();
       
    MainController mainController = loader.getController();
    mainController.displayOtherWindow(primaryStage.getX() + width, primaryStage.getY());
  }

	public static void main( String[] args ) {
		launch( args );
	}
  
}
