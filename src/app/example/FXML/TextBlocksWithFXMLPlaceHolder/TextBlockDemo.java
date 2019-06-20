package app.example.FXML.TextBlocksWithFXMLPlaceHolder;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TextBlockDemo extends Application {

    private Stage stage;

//    public TextBlockDemo() {
//		Platform.runLater(() -> {
//			try {
//				start(new Stage());
//			} catch (IllegalAccessException | InstantiationException | IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
//	}
    
    @Override
    public void start(Stage primaryStage) throws IOException, IllegalAccessException, InstantiationException {
        stage = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader();

        fxmlLoader.setLocation(TextBlockDemo.class.getResource("MyView.fxml"));
        AnchorPane root = fxmlLoader.load();

        MyController controller =  fxmlLoader.getController();
        controller.setTextBlocks(root);
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
