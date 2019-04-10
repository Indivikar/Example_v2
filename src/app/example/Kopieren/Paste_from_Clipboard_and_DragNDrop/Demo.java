package app.example.Kopieren.Paste_from_Clipboard_and_DragNDrop;

import app.example.Kopieren.Paste_from_Clipboard_and_DragNDrop.controller.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Demo extends Application {

	private MainController mainController;

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
	
	@Override
    public void start(Stage stage) throws Exception {

    	FXMLLoader loader = new FXMLLoader(getClass().getResource("view/fxml/MainController.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.setX(1200);
        stage.show();

        mainController = loader.getController();
        mainController.setMainStage(this);

    }

	@Override
    public void stop() throws Exception {

    }

    public static void main(String[] args) {
        launch(args);
    }

}

