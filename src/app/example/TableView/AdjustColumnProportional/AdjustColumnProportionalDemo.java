package app.example.TableView.AdjustColumnProportional;

import app.example.TableView.Model.Person;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
 
public class AdjustColumnProportionalDemo extends Application {
 
    private TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data =
        FXCollections.observableArrayList(
            new Person("Jacob", "Smith", "jacob.smith@example.com"),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Person("Ethan", "Williams", "ethan.williams@example.com"),
            new Person("Emma", "Jones", "emma.jones@example.com"),
            new Person("Michael", "Brown", "michael.brown@example.com")
        );
   
    public AdjustColumnProportionalDemo() {
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
        stage.setTitle("Table View Sample");
        stage.setWidth(450);
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
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));
 
        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));
 
        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));
 
        // adjusts your columns Proportional when the window size is changed     
        firstNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.32));
        lastNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.32));
        emailCol.prefWidthProperty().bind(table.widthProperty().multiply(0.32));

        firstNameCol.setResizable(false);
        lastNameCol.setResizable(false);
        emailCol.setResizable(false);
        
        table.setItems(data);     
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
 
        addListenerTableViewAdjustColumnToWindow(table, emailCol, 4);
        
        root.getChildren().addAll(label, table);
 
        stage.setScene(scene);
        stage.show();
    }
 
    // 
	public void addListenerTableViewAdjustColumnToWindow(TableView<?> tableView, TableColumn<?, ?> columnToBeAdjusted, int correction) {
		final ReadOnlyDoubleProperty progressProperty = new SimpleDoubleProperty(correction);
		DoubleBinding usedWidth = Bindings.createDoubleBinding(() -> progressProperty.get());
		
		for (TableColumn<?, ?> tableColumn : tableView.getColumns()) {
			
			if(!tableColumn.equals(columnToBeAdjusted)){
//				System.out.println("TableColumns: " + tableColumn.getText() + " -> " + tableColumn.widthProperty() + " - " + tableColumn.getWidth());
				usedWidth = usedWidth.add(tableColumn.widthProperty());
			}				
		}
				
		columnToBeAdjusted.prefWidthProperty().bind(tableView.widthProperty().subtract(usedWidth));
	}
    
} 
