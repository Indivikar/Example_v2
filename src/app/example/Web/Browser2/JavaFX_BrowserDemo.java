package app.example.Web.Browser2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
*
* @web http://java-buddy.blogspot.com/
*/
public class JavaFX_BrowserDemo extends Application {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
      launch(args);
  }

  public JavaFX_BrowserDemo() {
		Platform.runLater(() -> {           
	           start(new Stage());		       
	 });
  }
  
  @Override
  public void start(Stage primaryStage) {
      primaryStage.setTitle("java-buddy.blogspot.com");

      WebView myBrowser = new WebView();
      WebEngine myWebEngine = myBrowser.getEngine();
      //myWebEngine.load("http://java-buddy.blogspot.com/");
      myWebEngine.load("http://google.com");

      StackPane root = new StackPane();
      root.getChildren().add(myBrowser);
      primaryStage.setScene(new Scene(root, 640, 480));
      primaryStage.show();
  }
}
