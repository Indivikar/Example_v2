package app.example.Animation.AnimationMitSVG;

import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WebViewSVG extends Application  {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
	    InputStream svgFile = 
	            getClass().getResourceAsStream("Indi.svg");
	      SvgLoader loader = new SvgLoader();
	      Group svgImage = loader.loadSvg(svgFile);

	      // Scale the image and wrap it in a Group to make the button 
	      // properly scale to the size of the image  
	      svgImage.setScaleX(0.1);
	      svgImage.setScaleY(0.1);
	      Group graphic = new Group(svgImage);

	      // create a button and set the graphics node
	      Button button = new Button();
	      button.setGraphic(graphic);

	      // add the button to the scene and show the scene
	      HBox layout = new HBox(button);
	      HBox.setMargin(button, new Insets(10));
	      Scene scene = new Scene(layout);
	      primaryStage.setScene(scene);
	      primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
