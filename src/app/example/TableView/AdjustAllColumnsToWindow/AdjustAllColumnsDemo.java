package app.example.TableView.AdjustAllColumnsToWindow;

import app.example.TableView.Model.Person;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class AdjustAllColumnsDemo extends Application {
 
    private TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data =
        FXCollections.observableArrayList(
            new Person("Jacob", "Smith", "jacob.smith@example.com"),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Person("Ethan", "Williams", "ethan.williams@example.com"),
            new Person("Emma", "Jones", "emma.jones@example.com"),
            new Person("Michael", "Brown", "michael.brown@example.com")
        );
   
    public AdjustAllColumnsDemo() {
		Platform.runLater(() -> {
			start(new Stage());
		});
	}
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
    	AnchorPane root = new AnchorPane();
    	
        Scene scene = new Scene(root);
        stage.setTitle("Adjust all Columns Demo");
        stage.setWidth(450);
        stage.setMinWidth(450);
        stage.setHeight(500);
 
        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));
        AnchorPane.setTopAnchor(label, 10.0);
        AnchorPane.setLeftAnchor(label, 10.0);
        
        AnchorPane.setTopAnchor(table, 50.0);
        AnchorPane.setLeftAnchor(table, 10.0);
        AnchorPane.setBottomAnchor(table, 10.0);
        AnchorPane.setRightAnchor(table, 10.0);
        
        table.setEditable(true);

        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
 
        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
 
        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(100);
        emailCol.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));

        table.setItems(data);     
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        // all columns are adjusted proportionally to the size of the table
        addListenerTableViewAdjustColumnToWindow(table, -4);
        
        root.getChildren().addAll(label, table);
        
        stage.setScene(scene);
        stage.show();
    }
 
    
	public void addListenerTableViewAdjustColumnToWindow(TableView<?> tableView, int correction) {

		// correction = if the scrollbar is visible, then you can reduce the last column
				
		for (TableColumn<?, ?> tableColumn : tableView.getColumns()) {				
			tableColumn.widthProperty().addListener((ov, oldVal, newVal) -> {
				    double width = 0;
				    
				    // get the last Column
				    int lastIndex = tableView.getColumns().size() - 1;
					TableColumn<?, ?> lastColumn = tableView.getColumns().get(lastIndex);	    

				    for (TableColumn<?, ?> tableColumn2 : tableView.getColumns()) {				    	
				        if (!tableColumn2.equals(lastColumn)) {
					        width += tableColumn2.getWidth();
				        } 				        
				    }
				    // compensate the change with the last column
				    lastColumn.setPrefWidth(tableView.getWidth() - width + correction);

			});								
		}
					
		tableView.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
			Number oldValue, Number newValue) {
				if (oldValue.doubleValue() == 0) {
					return;
				}

				double difference = newValue.doubleValue() - oldValue.doubleValue();
				
				int factor = tableView.getColumns().size();
				double result = difference / factor;
				
				for (TableColumn<?, ?> tableColumn : tableView.getColumns()) {				
					tableColumn.setPrefWidth(tableColumn.getWidth() + result);								
				}				
			}
		});	
	}   
} 
