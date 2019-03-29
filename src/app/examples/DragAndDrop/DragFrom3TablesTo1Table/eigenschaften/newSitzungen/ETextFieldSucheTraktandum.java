package app.examples.DragAndDrop.DragFrom3TablesTo1Table.eigenschaften.newSitzungen;


import app.examples.DragAndDrop.DragFrom3TablesTo1Table.controller.ControllerNewSitzung;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;



public class ETextFieldSucheTraktandum {

	private TextField textField;

	private ControllerNewSitzung controllerNewSitzung;

	public ETextFieldSucheTraktandum(ControllerNewSitzung controllerNewSitzung, TextField textField) {

		this.controllerNewSitzung = controllerNewSitzung;
		this.textField = textField;

		textField.textProperty().addListener(textFieldChangeListener);
	}

	public ChangeListener<String> textFieldChangeListener = new ChangeListener<String>() {
	    @Override
	    public void changed(ObservableValue<? extends String> observable,
	            String oldValue, String newValue) {

	    	if(controllerNewSitzung.getCheckBoxSucheImmerAnzeigen().isSelected()){
	    		controllerNewSitzung.getETableViewTraktandenImmer().filtern();
	    	} else {
	    		controllerNewSitzung.getETableViewTraktandenImmer().filterReset();
	    	}


	    	if(controllerNewSitzung.getCheckBoxSucheAlle().isSelected()){
	    		controllerNewSitzung.getETableViewAlle().filtern();
	    	} else {
	    		controllerNewSitzung.getETableViewAlle().filterReset();
	    	}

	    }
	};

}
