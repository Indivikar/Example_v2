package app.example.DragAndDrop.DragButton;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * @author amol.hingmire
 * 
 */
public class DragButtonDemo extends Application {

	public DragButtonDemo() {
		Platform.runLater(() -> {           
	           start(new Stage());		       
		});
	}
	
	@Override
	public void start(Stage stage) {
		try {

			Button buttonToDrag1 = new Button();
			buttonToDrag1.setText("Draggable Button 1");
			buttonToDrag1.setId("b1");
			
			Button buttonToDrag2 = new Button();
			buttonToDrag1.setText("Draggable Button 2");
			buttonToDrag1.setId("b2");
			
			Button buttonToDrag3 = new Button();
			buttonToDrag1.setText("Draggable Button 3");
			buttonToDrag1.setId("b3");
			
			DragUtility dragUtility = new DragUtility();
	
			
			dragUtility.setDragControl(buttonToDrag1);
			dragUtility.setDragControl(buttonToDrag2);
			dragUtility.setDragControl(buttonToDrag3);
	
			HorizontalDock horizontalDock = new HorizontalDock();
			horizontalDock.addChildren(buttonToDrag1);
			horizontalDock.addChildren(buttonToDrag2);
			// horizontalDock.addChildren(buttonToDrag3);
	
			dragUtility.setTargetPane(horizontalDock);
	
			stage.centerOnScreen();
	
			Scene scene = new Scene(horizontalDock, 750, 500, Color.AQUAMARINE);
			stage.setScene(scene);
	
			stage.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
