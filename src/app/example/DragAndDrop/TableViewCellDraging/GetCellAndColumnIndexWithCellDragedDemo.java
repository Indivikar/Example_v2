package app.example.DragAndDrop.TableViewCellDraging;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GetCellAndColumnIndexWithCellDragedDemo extends Application {

	// Wenn Zelle verschoben wird, bekommt man den "Cell row index" und "Cell column index" zurück

	public GetCellAndColumnIndexWithCellDragedDemo() {
		Platform.runLater(() -> {           
	           start(new Stage());		       
		});
	}
	

    @Override
    public void start(Stage stage) {
        TableView<ObservableList<String>> table = new TableView<ObservableList<String>>();
        table.getSelectionModel().setCellSelectionEnabled(true);

        TableColumn<ObservableList<String>, String> col1 = new TableColumn<ObservableList<String>, String>();
        TableColumn<ObservableList<String>, String> col2 = new TableColumn<ObservableList<String>, String>();

        col1.setText("col 1");
        col2.setText("col 2");

        ObservableList<TableColumn<ObservableList<String>, String>> columns = FXCollections.observableArrayList(col1,
                col2);

        table.getColumns().addAll(columns);

        // For each column we set a cellFactory and a cellValueFactory.
        for (TableColumn<ObservableList<String>, String> col : columns) {
            col.setCellValueFactory(e -> new SimpleStringProperty("1"));

            // We need to set the cell factory so that we can create a cell and manipulate its behavior.
            col.setCellFactory(e -> {
                TableCell<ObservableList<String>, String> cell = new TableCell<ObservableList<String>, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        // Make sure you call super.updateItem, or you might get really weird bugs.
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(item);
                            setGraphic(null);
                        }
                    }
                };

                // HERE IS HOW TO GET THE ROW AND CELL INDEX
                cell.setOnDragDetected(eh -> {
                    // Get the row index of this cell
                    int rowIndex = cell.getIndex();
                    System.out.println("Cell row index: " + rowIndex);

                    // Get the column index of this cell.
                    int columnIndex = cell.getTableView().getColumns().indexOf(cell.getTableColumn());
                    System.out.println("Cell column index: " + columnIndex);
                });

                return cell;
            });
        }

        ObservableList<ObservableList<String>> items = FXCollections.observableArrayList(
                FXCollections.observableArrayList("1", "2"), FXCollections.observableArrayList("1", "2"));

        table.setItems(items);

        BorderPane view = new BorderPane();
        view.setCenter(table);

        stage.setScene(new Scene(view));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
