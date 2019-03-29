package app.example.DragAndDrop.DragFrom3TablesTo1Table;

import java.io.IOException;

import app.example.DragAndDrop.DragFrom3TablesTo1Table.controller.ControllerNewSitzung;
import app.example.DragAndDrop.DragFrom3TablesTo1Table.model.TabelleSitzungen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageNewSitzungDemo extends Application {

	private Stage stage;
	private ControllerNewSitzung controllerNewSitzung;

	private TableRow<TabelleSitzungen> row;

	public StageNewSitzungDemo() {
		Platform.runLater(() -> {           
	           try {
				start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		       
		});
	}
	
//	public StageNewSitzung(SystemTrayIcon systemTrayIcon) {
//		this.systemTrayIcon = systemTrayIcon;
////		this.row = row;
//		
//	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		loadStage();		
	}
	
	public void loadStage() {

		try {

	        FXMLLoader loader = new FXMLLoader(StageNewSitzungDemo.class
	        		.getResource("view/fxml/NewSitzung.fxml"));

//			AnchorPane root = loader.load();
	        AnchorPane shadowPane = loader.load();
	        AnchorPane root = (AnchorPane) shadowPane.lookup("#mainAnchorPane");

	        Platform.setImplicitExit(false); // instructs the javafx system not to exit implicitly when the last application window is shut.

			stage = new Stage();
	        stage.setTitle("Drag and Drop Items from 3 Tables To 1 Table");
//	        stage.getIcons().add(new Image(StageNewSitzung.class.getResourceAsStream( "/application/images/img.png" )));
	        stage.setAlwaysOnTop(true);
	        stage.setMinWidth(1050.00);
	        stage.setMinHeight(800.00);



			Scene scene = new Scene(shadowPane);
	//		scene.getStylesheets().add(SystemTrayIcon.class.getResource("/application/module/modulPR/view/css/CSSControllerProtokollRegister.css").toExternalForm());
//			scene.getStylesheets().add(StageNewSitzung.class.getResource("/application/style/MainStyle.css").toExternalForm());
//			scene.setFill(Color.TRANSPARENT); // Fill our scene with nothing

			controllerNewSitzung = loader.getController();
			controllerNewSitzung.setMainStageModulPR(this, row);

			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showStage(){
		stage.show();
	}

	public void hideStage(){
		stage.hide();
	}

	// Getter
	public final Stage getStage() {return stage;}

	public static void main(String[] args) {
		launch(args);
	}


}
