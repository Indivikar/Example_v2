package app.example.ComboBox.ComboBoxFilter.controller;

import app.example.ComboBox.ComboBoxFilter.model.FilterComboBox2;
import app.example.ComboBox.ComboBoxFilter.model.OpenFileOrFolder;
import app.example.ComboBox.ComboBoxFilter.model.Person;
import app.example.ComboBox.ComboBoxFilter.model.PfadErmitteln;
import app.example.ComboBox.ComboBoxFilter.MainDemo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainWindowController {

	// Stage
	private MainDemo main;
	private Stage primaryStage;



		// Models
	public Person person;

	// View's
	@FXML private AnchorPane mainAnchorPane;
	@FXML private TextField textField;
	@FXML private Button button;

	// Action
	@FXML public void actionButton(){
		System.out.println("Button");
		person.setFirstName("Inge");
		person.setLastName("Tenneberg");
		person.setAge("34");
		System.out.println(person.toString());

	}
	  ObservableList<String> cursors = FXCollections.observableArrayList(
		      "A1",
		      "A1.1",
		      "A1.2.3",
		      "A1.1.2",
		      "A1.3.2",
		      "A1.1.4",
		      "A1.4.1",
		      "A1.1.5",
		      "A1.3",
		      "A1.1.6",
		      "A1.3",
		      "A1.1.3",
		      "A1.4",
		      "B1.1.1",
		      "B1.3.2",
		      "B1.1.5",
		      "B1.3.5",
		      "B1.1.6"

		    );

	public void initialize(){

		ComboBox<String> comboBox = new FilterComboBox2(true);

		mainAnchorPane.getChildren().add(comboBox);

		PfadErmitteln pe = new PfadErmitteln(new String[]{"sandmann.jpg"}, false, false);
		textField.setText(pe.getErmittelterFile().toString());
		OpenFileOrFolder open = new OpenFileOrFolder();
		open.mitDesktopMethode(pe.getErmittelterFile());
	}

	public void setMainWindowStage(MainDemo main, Stage primaryStage){
		this.main = main;
		this.primaryStage = primaryStage;

		person = new Person();

		System.out.println(person.toString());
	}




}
