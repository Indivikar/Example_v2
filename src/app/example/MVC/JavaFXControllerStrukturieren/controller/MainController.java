package app.example.MVC.JavaFXControllerStrukturieren.controller;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import app.example.MVC.JavaFXControllerStrukturieren.StartDemo;
import app.example.MVC.JavaFXControllerStrukturieren.nodes.TextFieldEigenschaften;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class MainController implements Initializable {

	@FXML private AnchorPane anchorPane;

	@FXML private TextField textField1;
	@FXML private TextField textField2;

	@FXML private TextArea textArea;

	@FXML private ScrollPane scrollPane;
	@FXML private HBox hBoxStichwort;

	String saveString;

    final String cssDefault = "-fx-border-color: black;\n"
            + "-fx-border-insets: 3;\n"
            + "-fx-border-width: 1;\n"
            + "-fx-border-style: dashed;\n"
            + "-fx-padding: 5 5 5 5;\n";



	@FXML private void actionButtonAdd(ActionEvent event) {
		splitString(ausZwischenablageEinfuegen());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		new TextFieldEigenschaften(textField1);
		new TextFieldEigenschaften(textField2);
		
		

		scrollPane.setContent(hBoxStichwort);
		scrollPane.setHbarPolicy(ScrollBarPolicy.ALWAYS);
	}


    private String ausZwischenablageEinfuegen(){
    	String clipCont = null;

        Transferable data = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        DataFlavor[] df = data.getTransferDataFlavors();

        try{
	        clipCont = (String) data.getTransferData(DataFlavor.stringFlavor);

        } catch (Exception e) {
        	Alert alert = new Alert(AlertType.WARNING);
        	alert.setTitle("ACHTUNG!");
        	alert.setHeaderText(null);
        	alert.setContentText("Sie können nur Zeichenketten einfügen.");

        	alert.showAndWait();

//            JOptionPane.showMessageDialog(null, "Error -- " + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }

		return clipCont;
    }

    public void splitString(String string){
    	Platform.runLater(() -> {
    		hBoxStichwort.getChildren().clear();
    		textField1.setText("");
    		textField2.setText("");
    		textArea.setText(string);
    		if(string != null){
	    		String[] part = string.split(" |\\n|\\t");
		    	int j = 0;
		    	for (int i = 0; i < part.length; i++) {

		    		if(!part[i].equals("") && !part[i].equals(" ") && !part[i].equals("  ") && !part[i].equals("\\n") && !part[i].equals("\\t") &&
		    			part[i].trim().length() != 0)
		    		{
			    		Label label = new Label(part[i].trim());
			    		setDrag(label);
			    		label.setStyle(cssDefault);
			    		label.setCursor(Cursor.MOVE);
			    		label.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			    		    @Override
			    		    public void handle(MouseEvent mouseEvent) {
			    		        System.out.println("mouse click detected! " + mouseEvent.getSource());
			    		    }
			    		});

			    		hBoxStichwort.getChildren().add(label);

				    	switch (j) {
							case 0:
								textField1.setText(part[i]);
								break;
							case 1:
								textField2.setText(part[i]);
								break;
							case 2:
								textArea.setText(string);
								break;
						}
			    		j++;
		    		}
				}
    		}
	    });
    }

    private void setDrag(Label label){
    	label.setOnDragDetected(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start drag-and-drop gesture*/
                System.out.println("onDragDetected");

                /* allow any transfer mode */
                Dragboard db = label.startDragAndDrop(TransferMode.ANY);

                /* put a string on dragboard */

                saveString = label.getText();

                ClipboardContent content = new ClipboardContent();
                content.putString(label.getText());
                db.setContent(content);

                event.consume();
            }
        });


    }

    private void setDrop(TextField textField){
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

	public void setMainStage(StartDemo start) {
		splitString(ausZwischenablageEinfuegen());
		

	}

}
