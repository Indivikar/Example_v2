package app.example.Interfaces.Countdown_with_Interface;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Demo extends Application{
	
	private Button btnStartCountdown;
	private TextArea textArea;
	private Countdown c;
	
	public Demo() {
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

	private void addCountdown() {
		c = new Countdown();

		c.setMinimum(0);
		c.setMaximum(5);

		c.addListener(new ICountdownListener() {

			@Override
			public void onUnPause() {
				Platform.runLater(() -> {
					System.out.println("Unpaused");
					String newString = textArea.getText() + "\nUnpaused";
					textArea.setText(newString);
				});

			}

			@Override
			public void onTick(int current) {
				Platform.runLater(() -> {
					System.out.println("Tick: " + current);
					String newString = textArea.getText() + "\nTick: " + current;
					textArea.setText(newString);
				});
			}

			@Override
			public void onStop() {
				Platform.runLater(() -> {
					System.out.println("Stopped");	
					String newString = textArea.getText() + "\nStopped";
					textArea.setText(newString);
//					btnStartCountdown.setDisable(false);
				});
			}

			@Override
			public void onStart() {
				Platform.runLater(() -> {		
					System.out.println("Started");					
//					btnStartCountdown.setDisable(true);
					textArea.setText("Started");
				});
			}

			@Override
			public void onPause() {
				Platform.runLater(() -> {			
					System.out.println("Paused");
					String newString = textArea.getText() + "\nPaused";
					textArea.setText(newString);
				});
			}

			@Override
			public void onMaximum(int maximum) {
				Platform.runLater(() -> {
					System.out.println("Maximum reached: " + maximum);
					String newString = textArea.getText() + "\nMaximum reached: " + maximum;
					textArea.setText(newString);
				});
			}
		});

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		addCountdown();
		
		btnStartCountdown = new Button("Start");
		btnStartCountdown.setOnAction(e -> {
			Platform.runLater(() -> {
				
				if (!c.isAlive() || c.isStopped()) {
					addCountdown();
					textArea.setText("");
					c.startCountdown();
				} else {
					if (c.isPause()) {
						c.setPause(false);
					}
				}
				
			});
		});
		
		textArea = new TextArea();

		Button btnPause = new Button("Pause");
		btnPause.setOnAction(e -> {
			Platform.runLater(() -> {
				c.setPause(true);
			});
		});
		
		Button btnStop = new Button("Stop");
		btnStop.setOnAction(e -> {
			Platform.runLater(() -> {
				c.stopCountdown();
			});
		});
		
		HBox hBoxButtons = new HBox();
		hBoxButtons.setSpacing(10);
		hBoxButtons.setAlignment(Pos.CENTER);
		hBoxButtons.getChildren().addAll(btnStartCountdown, btnPause, btnStop);
		
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(vBox, 10.0);
        AnchorPane.setLeftAnchor(vBox, 10.0);
        AnchorPane.setBottomAnchor(vBox, 10.0);
        AnchorPane.setRightAnchor(vBox, 10.0);
        vBox.getChildren().addAll(hBoxButtons, textArea);
              
        AnchorPane root = new AnchorPane();
        root.getChildren().add(vBox);

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Countdown with Interface");
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}
}
