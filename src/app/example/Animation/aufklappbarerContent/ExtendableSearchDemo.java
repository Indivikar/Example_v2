package app.example.Animation.aufklappbarerContent;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


// Orginal
// https://stevenschwenke.de/extendableSearchPaneInJavaFX
// Gab aber ein Fehler, ich habe es abgewandelt

public class ExtendableSearchDemo extends Application {

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
}
