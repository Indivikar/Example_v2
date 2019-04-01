package app.example.ComboBox.ComboBoxFilter.stage;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MCVE extends Application {

	ComboBox<String> cb = new ComboBox<String>();
	TextField tf = new TextField();

    public void start(Stage stage) {
        VBox root = new VBox();


        cb.setEditable(true);

        // Create a list with some dummy values.
        ObservableList<String> items = FXCollections.observableArrayList("One", "Two", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten");

        // Create a FilteredList wrapping the ObservableList.
        FilteredList<String> filteredItems = new FilteredList<String>(items, p -> true);

        // Add a listener to the textProperty of the combobox editor. The
        // listener will simply filter the list every time the input is changed
        // as long as the user hasn't selected an item in the list.
        cb.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            final TextField editor = cb.getEditor();
            final String selected = cb.getSelectionModel().getSelectedItem();


            // This needs run on the GUI thread to avoid the error described
            // here: https://bugs.openjdk.java.net/browse/JDK-8081700.
            Platform.runLater(() -> {
                // If the no item in the list is selected or the selected item
                // isn't equal to the current input, we refilter the list.
                if (selected == null || !selected.equals(editor.getText())) {
                    filteredItems.setPredicate(item -> {
                        // We return true for any items that starts with the
                        // same letters as the input. We use toUpperCase to
                        // avoid case sensitivity.
                        if (item.toUpperCase().startsWith(newValue.toUpperCase())) {
                        	cb.hide();
                        	cb.show();
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
            });
        });

//        cb.setOnKeyPressed(this::handleOnKeyPressed);
//
//		cb.setOnAction(new EventHandler<ActionEvent>() {
//
//	        @Override
//	        public void handle(ActionEvent event) {
//	          // Reset so all options are available:
//	        	final String selected1 = cb.getSelectionModel().getSelectedItem();
//	        	cb.getEditor().setText(selected1);
//	        	cb.getEditor().positionCaret(0);
//	        	cb.getEditor().positionCaret(4);
//	        	System.out.println("action");
//
//
//	        }
//
//	      });

        cb.setItems(filteredItems);

        root.getChildren().addAll(cb, tf);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleOnKeyPressed(KeyEvent e) {
		KeyCode code = e.getCode();

		if (code == KeyCode.BACK_SPACE ) {
			cb.getEditor().positionCaret(0);
			cb.getEditor().positionCaret(4);
		}
		if (code == KeyCode.ESCAPE) {

		}
//		comboBoxSignatur.getItems().setAll(filteredList);
	}

    public static void main(String[] args) {
        launch();
    }
}
