package app.example.Animation.aufklappbarerContent;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Controller implements Initializable {

	@FXML
	private GridPane extendableSearchPane;

	private Rectangle clipRect;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
