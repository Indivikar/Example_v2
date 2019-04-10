package app.example.Grafik.Elipse;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class EllipticalDemo extends Application {

	public EllipticalDemo() {
		Platform.runLater(() -> {           
	           try {
				start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		       
		});
	}
	
    @Override public void start(Stage stage) throws Exception {
        Stop[] stops = {
            new Stop(0.0, Color.RED),
            new Stop(0.5, Color.GREEN),
            new Stop(1.0, Color.BLUE)
        };

        EllipticalGradient gradient = new EllipticalGradient(stops);

        ImageView img = new ImageView(gradient.getImage(500, 200));

        HBox pane = new HBox();
        pane.setSpacing(0);
        pane.getChildren().addAll(img);

        final Scene scene = new Scene(pane, Color.rgb(67, 67, 67));

        stage.setTitle("Elliptical Gradient");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(final String[] args) {
        Application.launch(args);
    }
}
