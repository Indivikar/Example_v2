package app.example.Animation.aufklappbarerContent;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


// Orginal
// https://stevenschwenke.de/extendableSearchPaneInJavaFX
// Gab aber ein Fehler, ich habe es abgewandelt

public class ExtendableSearchDemo extends Application {

	@FXML
	private GridPane extendableSearchPane;

	private Rectangle clipRect;

	public ExtendableSearchDemo() {
		Platform.runLater(() -> {           
	        try {
				start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		       
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(ExtendableSearchDemo.class.getResource("extendableSearch.fxml"));

		Scene scene = new Scene(root, 600, 400);

		stage.setTitle("Extendable search pane demo");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void initialize() {
		double widthInitial = 200;
		double heightInitial = 200;
		clipRect = new Rectangle();
		clipRect.setWidth(widthInitial);
		clipRect.setHeight(0);
		clipRect.translateYProperty().set(heightInitial);
		extendableSearchPane.setClip(clipRect);
		extendableSearchPane.translateYProperty().set(-heightInitial);
		extendableSearchPane.prefHeightProperty().set(0);

		extendableSearchPane.setStyle(
		        "-fx-background-color: white;" +
	            "-fx-effect: dropshadow(gaussian, black, " + 1 + ", 0, 0, 0);" +
	            "-fx-background-insets: " + 1 + ";"
	        );
	}

	@FXML
	public void toggleExtendableSearch() {

		clipRect.setWidth(extendableSearchPane.getWidth());

		if (clipRect.heightProperty().get() != 0) {

			// Animation for scroll up.
			Timeline timelineUp = new Timeline();

			// Animation of sliding the search pane up, implemented via
			// clipping.
			final KeyValue kvUp1 = new KeyValue(clipRect.heightProperty(), 0);
			final KeyValue kvUp2 = new KeyValue(clipRect.translateYProperty(), extendableSearchPane.getHeight());

			// The actual movement of the search pane. This makes the table
			// grow.
			final KeyValue kvUp4 = new KeyValue(extendableSearchPane.prefHeightProperty(), 0);
			final KeyValue kvUp3 = new KeyValue(extendableSearchPane.translateYProperty(), -extendableSearchPane.getHeight());

			final KeyFrame kfUp = new KeyFrame(Duration.millis(200), kvUp1, kvUp2, kvUp3, kvUp4);
			timelineUp.getKeyFrames().add(kfUp);
			timelineUp.play();
		} else {

			// Animation for scroll down.
			Timeline timelineDown = new Timeline();

			// Animation for sliding the search pane down. No change in size,
			// just making the visible part of the pane
			// bigger.
			final KeyValue kvDwn1 = new KeyValue(clipRect.heightProperty(), extendableSearchPane.getHeight());
			final KeyValue kvDwn2 = new KeyValue(clipRect.translateYProperty(), 0);

			// Growth of the pane.
			final KeyValue kvDwn4 = new KeyValue(extendableSearchPane.prefHeightProperty(), extendableSearchPane.getHeight());
			final KeyValue kvDwn3 = new KeyValue(extendableSearchPane.translateYProperty(), 0);

			final KeyFrame kfDwn = new KeyFrame(Duration.millis(200), createBouncingEffect(extendableSearchPane.getHeight()), kvDwn1, kvDwn2,
					kvDwn3, kvDwn4);
			timelineDown.getKeyFrames().add(kfDwn);

			timelineDown.play();
		}
	}

	private EventHandler<ActionEvent> createBouncingEffect(double height) {
		final Timeline timelineBounce = new Timeline();
		timelineBounce.setCycleCount(2);
		timelineBounce.setAutoReverse(true);
		final KeyValue kv1 = new KeyValue(clipRect.heightProperty(), (height - 15));
		final KeyValue kv2 = new KeyValue(clipRect.translateYProperty(), 15);
		final KeyValue kv3 = new KeyValue(extendableSearchPane.translateYProperty(), -15);
		final KeyFrame kf1 = new KeyFrame(Duration.millis(100), kv1, kv2, kv3);
		timelineBounce.getKeyFrames().add(kf1);

		// Event handler to call bouncing effect after the scroll down is
		// finished.
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				timelineBounce.play();
			}
		};
		return handler;
	}
}
