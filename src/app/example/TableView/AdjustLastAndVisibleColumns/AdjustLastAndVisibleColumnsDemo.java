package app.example.TableView.AdjustLastAndVisibleColumns;

import app.example.TableView.Model.Person;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class AdjustLastAndVisibleColumnsDemo extends Application {
 
	private CheckBox checkBoxScrollBar;
	
    private TableView<Person> table = new TableView<Person>();    
    private final ObservableList<Person> data =
        FXCollections.observableArrayList(
            new Person("Jacob", "Smith", "jacob.smith@example.com"),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Person("Ethan", "Williams", "ethan.williams@example.com"),
            new Person("Emma", "Jones", "emma.jones@example.com"),
            new Person("Michael", "Brown", "michael.brown@example.com")
        );

    
    public AdjustLastAndVisibleColumnsDemo() {
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
        
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(hBox, 50.0);
        AnchorPane.setLeftAnchor(hBox, 10.0);
        AnchorPane.setRightAnchor(hBox, 10.0);

        AnchorPane.setTopAnchor(table, 100.0);
        AnchorPane.setLeftAnchor(table, 10.0);
        AnchorPane.setBottomAnchor(table, 50.0);
        AnchorPane.setRightAnchor(table, 10.0);
        
        table.setEditable(true);
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

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
        addListenerTableViewAdjustColumnToWindow(table, emailCol, 100, -4);
        
        
        
        CheckBox checkBoxFirstName = getCheckBoxFirstName(firstNameCol);
        CheckBox checkBoxLastName = getCheckBoxLastName(lastNameCol);
        CheckBox checkBoxEmail = getCheckBoxEmail(emailCol);
        
        checkBoxScrollBar = getCheckBoxScrollBar();
        AnchorPane.setLeftAnchor(checkBoxScrollBar, 10.0);
        AnchorPane.setBottomAnchor(checkBoxScrollBar, 10.0);
       
        hBox.getChildren().addAll(checkBoxFirstName, checkBoxLastName, checkBoxEmail);
        root.getChildren().addAll(label, hBox, table, checkBoxScrollBar);
        
        stage.setScene(scene);
        stage.show();
    }
 
    private CheckBox getCheckBoxFirstName(TableColumn<Person, String> column) {
    	CheckBox checkBox = new CheckBox("Show/Hide First Name");    	
    	checkBox.setSelected(column.isVisible());
    	checkBox.selectedProperty().addListener((ov, oldVal, newVal) -> {
    		visibleOptions(column, newVal);
    	});
		return checkBox;
	}
    
    private CheckBox getCheckBoxLastName(TableColumn<Person, String> column) {
    	CheckBox checkBox = new CheckBox("Show/Hide Last Name");    	
    	checkBox.setSelected(column.isVisible());
    	checkBox.selectedProperty().addListener((ov, oldVal, newVal) -> {
    		visibleOptions(column, newVal);
    	});
		return checkBox;
	}
    
    private CheckBox getCheckBoxEmail(TableColumn<Person, String> column) {

    	CheckBox checkBox = new CheckBox("Show/Hide Email");    	
    	checkBox.setSelected(column.isVisible());
    	checkBox.selectedProperty().addListener((ov, oldVal, newVal) -> {	
    		visibleOptions(column, newVal);
    	});
		return checkBox;
	}

    private CheckBox getCheckBoxScrollBar() {
    	CheckBox checkBox = new CheckBox("Show ScrollBar, if add the hidden column");    	
    	checkBox.setSelected(true);
//    	checkBox.selectedProperty().addListener((ov, oldVal, newVal) -> {
//    		if (newVal) {
//    			table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);  	          	        
//			} else {
//				table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);  
//			}
//    	});
		return checkBox;
	}
    
    private void visibleOptions(TableColumn<Person, String> column, boolean newVal) {		
		column.setVisible(newVal);
		
		// if set visible false, then adjust the last column
		change(-4);
		
		// set all Columns same Width, after visible
		if (column.isVisible() && !checkBoxScrollBar.isSelected()) {
			table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
			table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);     			
		} 
	}
    
    private void remove(TableColumn<?, ?> removeColumn) {
		Platform.runLater(() -> {
			for (TableColumn<?, ?> tableColumn : table.getColumns()) {				
	    		System.out.println(tableColumn.getText());
				if (tableColumn.getText().equals(removeColumn.getText())) {
					table.getColumns().remove(tableColumn);
				}								
			}
		});
	}
    
    private void change(int correction) {
		Platform.runLater(() -> {
			
	    	boolean isLastVisibleColumnSaved = false;
	    	double widthColumns = 0; // alle Sichtbaren Columns addieren, bis auf den letzten
	    	TableColumn<Person, ?> lastVisibleColumn = null;
	    	
	    	for (int i = table.getColumns().size() - 1; i >= 0; i--) {
	    		
	    		TableColumn<Person, ?> column = table.getColumns().get(i);
	    		
	    		
	    		if (column.isVisible()) {
	    			if (!isLastVisibleColumnSaved) {
	    				isLastVisibleColumnSaved = true;
	    				lastVisibleColumn = column;
	    				System.out.println("last Visible: " + column.getText());
	    				continue;
					}   			
	    			
	    			widthColumns += column.getWidth();
					System.out.println(column.getText());
				}
			}
	    	
	//    	if (widthColumns == 0) {
	//    		widthColumns = table.getWidth();
	//		}
	    	
	    	System.out.println("table.getWidth() [" + table.getWidth() + "]   widthColumns [" + widthColumns + "]");
	    	if (lastVisibleColumn != null) {    	
	    		
		    		System.out.println(lastVisibleColumn.getText() + " ->  get old Width: " + lastVisibleColumn.getWidth());
					lastVisibleColumn.setPrefWidth(table.getWidth() - widthColumns + correction);
					System.out.println(lastVisibleColumn.getText() + " ->  set Width: " + (table.getWidth() - widthColumns + correction));
					System.out.println(lastVisibleColumn.getText() + " ->  get new Width: " + lastVisibleColumn.getWidth());
	    		
	
			}
    	
    	});
	}
    
	public void addListenerTableViewAdjustColumnToWindow(TableView<?> tableView, TableColumn<?, ?> columnToBeAdjusted, int minWidthColumn, int correction) {

		// correction = if the scrollbar is visible, then you can reduce the last column

    	for (int i = table.getColumns().size() - 1; i >= 0; i--) {
    		table.getColumns().get(i).widthProperty().addListener((ov, oldVal, newVal) -> {
    			change(-4);
    		});
		}
			
    	tableView.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {			
				change(-4);
			}
		});
	}   
} 
