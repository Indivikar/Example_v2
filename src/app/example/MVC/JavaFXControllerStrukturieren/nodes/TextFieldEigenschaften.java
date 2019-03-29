package app.example.MVC.JavaFXControllerStrukturieren.nodes;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class TextFieldEigenschaften {

	public TextFieldEigenschaften(TextField textField) {
		textField.setOnDragOver(new EventHandler <DragEvent>() {
	        public void handle(DragEvent event) {
	            /* data is dragged over the target */
	            System.out.println("onDragOver");

	            /* accept it only if it is  not dragged from the same node
	             * and if it has a string data */
	            if (event.getGestureSource() != textField &&
	                    event.getDragboard().hasString()) {
	                /* allow for both copying and moving, whatever user chooses */
	                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	            }

	            event.consume();
	        }
	    });

		textField.setOnDragEntered(new EventHandler <DragEvent>() {
	        public void handle(DragEvent event) {
	            /* the drag-and-drop gesture entered the target */
	            System.out.println("onDragEntered");
	            /* show to the user that it is an actual gesture target */
	            if (event.getGestureSource() != textField &&
	                    event.getDragboard().hasString()) {

	            }

	            event.consume();
	        }
	    });



		textField.setOnDragExited(new EventHandler <DragEvent>() {
	        public void handle(DragEvent event) {
	            /* mouse moved away, remove the graphical cues */
	        	System.out.println("setOnDragExited");

	            event.consume();
	        }
	    });

		textField.setOnDragDropped(new EventHandler <DragEvent>() {
	        public void handle(DragEvent event) {
	            /* data dropped */
	            System.out.println("onDragDropped");
	            /* if there is a string data on dragboard, read it and use it */
	            Dragboard db = event.getDragboard();
	            boolean success = false;
	            if (db.hasString()) {

	            	String string = textField.getText() + " " + db.getString();
	            	textField.setText(string.trim());
	                success = true;
	            }
	            /* let the source know whether the string was successfully
	             * transferred and used */
	            event.setDropCompleted(success);

	            event.consume();
	        }
	    });
	}



}
