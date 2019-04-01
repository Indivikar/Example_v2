package app.example.StackPane.ControlWithFixPosInScrollPane;

import java.util.Arrays;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StackPaneAndScrollPaneDemo extends Application {
	
	public StackPaneAndScrollPaneDemo() {
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
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		ComboBox<String> comboBox = new ComboBox<>();
		ComboBox<String> comboBox2 = new ComboBox<>();
		comboBox.setItems(FXCollections.observableArrayList(Arrays.asList("One", "Two", "Three")));
		comboBox2.setItems(FXCollections.observableArrayList(Arrays.asList("One", "Two", "Three")));
		
		StackPane stackPane = new StackPane();
		stackPane.setMinHeight(600);
		stackPane.setPadding(new Insets(50, 20, 20, 20));
		stackPane.setAlignment(Pos.TOP_LEFT);
		stackPane.getChildren().addAll(comboBox);
				
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(stackPane);
		
		StackPane root = new StackPane();
		root.getChildren().addAll(scrollPane, comboBox2);

		Scene scene = new Scene(root, Color.LINEN);
		stage.setTitle(this.getClass().getSimpleName());
		stage.setWidth(500);
		stage.setHeight(500);
		stage.setScene(scene);
		stage.show();
	}
}
