package app.example.Grafik.SVGToPNG;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.*;
import javafx.scene.web.*;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Pos;
//
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.File;

public class SVGToPNGDemo extends Application {
	
	public SVGToPNGDemo() {
		Platform.runLater(() -> {
			try {
				start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
  public void start(Stage stage) throws Exception {
	  
    stage.setTitle("Joe's SVG file to Image of png/jpg/gif");
    String home = "http://thenewcode.com/assets/images/thumbnails/homer-simpson.svg";
    
    
    WebView wView = new WebView();
    WebEngine engine = wView.getEngine();
    engine.load(home);
    
    // Input fields: Format and location
    TextField format = new TextField("png");
    TextField loc = new TextField(home);
    format.setPrefColumnCount(3);
    loc.setPrefColumnCount(40);
    loc.setOnAction(a -> {
        String url = loc.getText().toLowerCase();;
        if (url.indexOf("://") < 0) {
          url = "file://" + url;
          loc.setText(url);
        }
        engine.load(url);
    });
    
    // create Save Button and Progress Indicator
    Button save = new Button("SaveImage");
    
    ProgressIndicator progress = new ProgressIndicator();
    progress.setVisible(false);
    PauseTransition pt = new PauseTransition();
    pt.setDuration(Duration.millis(500));
    pt.setOnFinished(a -> {
	        String svg = loc.getText().toLowerCase();
	        if (!home.equals(svg)) {
		          if (svg.indexOf("://") < 0) {
			            svg = "file://" + svg;
			            loc.setText(svg);
		          }
		          engine.load(svg);
	        }
	        String fmt = getFormat(format.getText());
	        try { // saving the snapshot.....
		          ImageIO.write(SwingFXUtils.fromFXImage(wView.snapshot(null, null), null),
		                       fmt,
		                       new File(getName(svg, fmt)));
		          progress.setVisible(false);
		          save.setDisable(false);
	        } catch (Exception ex) {
		          Alert alert = new Alert(AlertType.ERROR);
		          alert.setTitle("In Real Trouble!");
		          alert.setHeaderText(null);
		          alert.setContentText(ex.toString());
		          alert.showAndWait();
	        }
    });
    
    // prepare the copying event
    save.setOnAction(a -> {
	        pt.play();
	        save.setDisable(true);
	        progress.setVisible(true);
	        String svg = loc.getText().toLowerCase();
	        String fmt = getFormat(format.getText());
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Done!");
	        alert.setHeaderText(null);
	        alert.setContentText("SVG image is saved in "+getName(svg, fmt));
	        alert.showAndWait();
    });
    
    // 2 horizontal boxes
    HBox hBox = new HBox(5);
    HBox txtB = new HBox(5);
    txtB.getChildren().addAll(loc, format); 
    hBox.getChildren().addAll(save, progress);
    hBox.setAlignment(Pos.CENTER);
    
    // prepare for the Show
    VBox vBox = new VBox(5);
    vBox.setPrefSize(700, 600);
    vBox.setStyle("-fx-padding: 5; -fx-background-color: cornsilk; -fx-font-size: 12pt;");
    vBox.getChildren().setAll(txtB, wView, hBox );
    stage.setScene(new Scene(vBox));
    stage.show();
  }
  

  private String getName(String svg, String fmt) {
	    int e, b;
	    
	    if (svg.startsWith("http")) {
		      b = svg.lastIndexOf("/");
		      e = svg.lastIndexOf(".");
		      if (b < 0) b = 0; else ++b;
	    } else {
		      b = svg.indexOf("://");
		      e = svg.lastIndexOf(".");
		      if (b < 0) b = 0; else b += 3;
	    }
	    
	    if (e < 0) e = svg.length();
	    
	    return svg.substring(b, e)+"."+fmt;
  }
  
  private String fmts[] = { "png", "jpg", "gif" };
  
  private String getFormat(String x) {
	    boolean fnd = false;
	    x = x.toLowerCase();
	    for (String f:fmts) if (f.equals(x)) {
		      fnd = true;
		      break;
	    }
	    return !fnd? "png":x; // found or default png
  }
}