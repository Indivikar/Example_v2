package app.example.TableView.AdjustColumnToWindow_2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
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
 
public class AdjustColumnDemo extends Application {
 
    private TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data =
        FXCollections.observableArrayList(
            new Person("Jacob", "Smith", "jacob.smith@example.com"),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Person("Ethan", "Williams", "ethan.williams@example.com"),
            new Person("Emma", "Jones", "emma.jones@example.com"),
            new Person("Michael", "Brown", "michael.brown@example.com")
        );
   
    public AdjustColumnDemo() {
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
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));
 
        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));
 
        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));
 
        // a column of your choice will be adjusted to the window size
        addListenerTableViewAdjustColumnToWindow(table, emailCol, 200, -3);
        
        table.setItems(data);     
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        root.getChildren().addAll(label, table);
 
        stage.setScene(scene);
        stage.show();
    }
 
    // 
	public void addListenerTableViewAdjustColumnToWindow(TableView<?> tableView,
			TableColumn<?, ?> columnToBeAdjusted,
			int minWidthColumn, int correction) {

		tableView.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
			Number oldValue, Number newValue) {
				//System.out.println("------------------");
				double newWidth = tableView.getWidth();
				
				for (TableColumn<?, ?> tableColumn : tableView.getColumns()) {
				
					if(!tableColumn.equals(columnToBeAdjusted)){
						// System.out.println("TableColumns: " + tableColumn.getText() + " -> " + neueWidth + " - " + tableColumn.getWidth());
						newWidth = newWidth - tableColumn.getWidth();
					}				
				}
				
				// System.out.println(neueWidth + " <= " + minWidth);
				if(newWidth <= minWidthColumn){
					columnToBeAdjusted.setPrefWidth(minWidthColumn + correction);
				} else {
					columnToBeAdjusted.setPrefWidth(newWidth + correction);
				}
			}
		});
	
	}
    
    public static class Person {
 
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;
 
        private Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }
 
        public String getFirstName() {
            return firstName.get();
        }
 
        public void setFirstName(String fName) {
            firstName.set(fName);
        }
 
        public String getLastName() {
            return lastName.get();
        }
 
        public void setLastName(String fName) {
            lastName.set(fName);
        }
 
        public String getEmail() {
            return email.get();
        }
 
        public void setEmail(String fName) {
            email.set(fName);
        }
    }
} 
