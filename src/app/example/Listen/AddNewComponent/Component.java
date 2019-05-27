package app.example.Listen.AddNewComponent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Component extends HBox {

	private MainController mainController;
	private int number;
	
	private Label label;
	private TextField textField;
	private ComboBox<String> comboBox;
	private Button buttonPlus;
	private Button buttonMinus;
	
	
	public Component(MainController mainController, int number) {
		this.number = number;

		label = new Label(number + "");
		label.setAlignment(Pos.CENTER);
		label.setPrefWidth(100);
		label.setPrefHeight(25);
		
		textField = new TextField("Tom " + number);
		textField.setPrefWidth(100);
		
		comboBox = new ComboBox<>();
		comboBox.setPrefWidth(100);
		addComboBoxItems();
		
		buttonPlus = new Button("+");
		buttonPlus.setPrefWidth(40);
		buttonPlus.setOnAction(e -> {
			mainController.addNewComponent();
		});
		
		buttonMinus = new Button("-");
		buttonMinus.setPrefWidth(40);
		buttonMinus.setOnAction(e -> {
			System.out.println("remove number: " + this.number);
			mainController.removeComponent(this.number);
		});
		
		setSpacing(10);
		setAlignment(Pos.TOP_CENTER);
		getChildren().addAll(label, textField, comboBox, buttonPlus, buttonMinus);					
	}

	private void addComboBoxItems() {
		ObservableList<String> list = FXCollections.observableArrayList("Baker", "Draftsman", "Driver" ,"Undertaker");
		comboBox.setItems(list);
	}
	
	// Getter
	public int getNumber() {return number;}
	public Label getLabel() {return label;}
	public TextField getTextField() {return textField;}
	public ComboBox<String> getComboBox() {return comboBox;}
	public Button getButtonPlus() {return buttonPlus;}
	public Button getButtonMinus() {return buttonMinus;}


	// Setter
	public void setNumber(int number) {
		this.number = number;
		this.label.setText(number + "");
	}

	public void setLabel(Label label) {this.label = label;}
	public void setTextField(TextField textField) {this.textField = textField;}
	public void setComboBox(ComboBox<String> comboBox) {this.comboBox = comboBox;}
	public void setButtonPlus(Button buttonPlus) {this.buttonPlus = buttonPlus;}
	public void setButtonMinus(Button buttonMinus) {this.buttonMinus = buttonMinus;}
	

	
	
	
}
