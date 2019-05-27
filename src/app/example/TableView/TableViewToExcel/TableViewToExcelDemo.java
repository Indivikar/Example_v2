package app.example.TableView.TableViewToExcel;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TableViewToExcelDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public TableViewToExcelDemo() {
		Platform.runLater(() -> {
			try {
				start(new Stage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
    
    @Override
    public void start(Stage primaryStage) throws IOException {

    	AnchorPane root = new AnchorPane();
    	
    	VBox vBox = new VBox();
    	vBox.setSpacing(10);
    	AnchorPane.setTopAnchor(vBox, 10.0d);
        AnchorPane.setRightAnchor(vBox, 10.0d);
        AnchorPane.setBottomAnchor(vBox, 10.0d);
        AnchorPane.setLeftAnchor(vBox, 10.0d);
    	
        TableView<Person> table = getTableView();
        
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        
        Label label = new Label();
        label.setPrefHeight(25);
        
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));

        Button buttonDirectoryChooser = new Button("Export as Excel-File");
        buttonDirectoryChooser.setOnAction(e -> {
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            createExcelFile(table, new File(selectedDirectory + File.separator + "workbook.xls"));
            label.setText("exported to: " + selectedDirectory.getAbsolutePath());
        });
             
        hBox.getChildren().addAll(buttonDirectoryChooser, label);     
    	vBox.getChildren().addAll(hBox, table);
        root.getChildren().add(vBox);
        Scene scene = new Scene(root, 1900, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    private void createExcelFile(TableView<Person> table, File file) {
    	
    	
    	try {
			Workbook workbook = new HSSFWorkbook();
	        Sheet spreadsheet = workbook.createSheet("sample");
	
	        Row row = spreadsheet.createRow(0);
	
	        for (int j = 0; j < table.getColumns().size(); j++) {
	            row.createCell(j).setCellValue(table.getColumns().get(j).getText());
	        }
	
	        for (int i = 0; i < table.getItems().size(); i++) {
	            row = spreadsheet.createRow(i + 1);
	            for (int j = 0; j < table.getColumns().size(); j++) {
	                if(table.getColumns().get(j).getCellData(i) != null) { 
	                    row.createCell(j).setCellValue(table.getColumns().get(j).getCellData(i).toString()); 
	                }
	                else {
	                    row.createCell(j).setCellValue("");
	                }   
	            }
	        }
	
	        FileOutputStream fileOut = new FileOutputStream(file);
	        workbook.write(fileOut);
        fileOut.close();
        
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    
    private TableView<Person> getTableView() {
        TableView<Person> table = new TableView<Person>();

        ObservableList<Person> teamMembers = getTeamMembers();
        table.setItems(teamMembers);

        TableColumn<Person,String> firstNameCol = new TableColumn<Person,String>("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        TableColumn<Person,String> lastNameCol = new TableColumn<Person,String>("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));

        ObservableList<TableColumn<Person, ?>> columns = table.getColumns();
        columns.add(firstNameCol);
        columns.add(lastNameCol);
    	
		return table;
	}
    

    private ObservableList<Person> getTeamMembers() {

        ObservableList<Person> people = FXCollections.observableArrayList();
        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Doe");
        people.add(person1);
        people.add(person1);
        people.add(person1);
        people.add(person1);
        people.add(person1);
        people.add(person1);

        Person person2 = new Person();
        person2.setFirstName("Jane");
        person2.setLastName("Doe");
        people.add(person2);
        people.add(person2);
        people.add(person2);
        people.add(person2);
        people.add(person2);

        return people;
    }

    public class Person {
        private StringProperty firstName;
        public void setFirstName(String value) { firstNameProperty().set(value); }
        public String getFirstName() { return firstNameProperty().get(); }
        public StringProperty firstNameProperty() {
            if (firstName == null) firstName = new SimpleStringProperty(this, "firstName");
            return firstName;
        }

        private StringProperty lastName;
        public void setLastName(String value) { lastNameProperty().set(value); }
        public String getLastName() { return lastNameProperty().get(); }
        public StringProperty lastNameProperty() {
            if (lastName == null) lastName = new SimpleStringProperty(this, "lastName");
            return lastName;
        }
    }
}
