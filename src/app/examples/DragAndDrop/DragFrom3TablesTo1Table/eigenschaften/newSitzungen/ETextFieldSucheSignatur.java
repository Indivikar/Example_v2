package app.examples.DragAndDrop.DragFrom3TablesTo1Table.eigenschaften.newSitzungen;

import app.examples.DragAndDrop.DragFrom3TablesTo1Table.controller.ControllerNewSitzung;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;



public class ETextFieldSucheSignatur {

	private TextField textField;

	private ControllerNewSitzung controllerNewSitzung;

	public ETextFieldSucheSignatur(ControllerNewSitzung controllerNewSitzung, TextField textField) {

		this.controllerNewSitzung = controllerNewSitzung;
		this.textField = textField;

		textField.textProperty().addListener(textFieldChangeListener);
	}

	public ChangeListener<String> textFieldChangeListener = new ChangeListener<String>() {
	    @Override
	    public void changed(ObservableValue<? extends String> observable,
	            String oldValue, String newValue) {
//	    	signGefunden = false;
//			int index = 0;
//			tabelleDeteils = datenbankenLaden.get(changeListenerDB.intValue()).getTabelleDetails();

			// Table filtern, wenn Checkbox Filter setzen aktiv ist
//        	if(controllerNewTraktandum.getCheckBoxFilterSetzen().isSelected()){


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





//        	}

//			// Das TextArea(Traktandum Text) auf Rot setzen, wenn es lehr ist
//			if(controllerNewTraktandum.getTextArea().getText().length() == 0){
//				controllerNewTraktandum.getTextArea().setStyle(fehler);
//			} else {
//				controllerNewTraktandum.getTextArea().setStyle(allesOK);
//			}
//
//			for (int i = 0; i < tabelleDeteils.size(); i++) {
//				if (tabelleDeteils.get(i).getSign().equalsIgnoreCase(newValue)) {
//					index = i;
//					signGefunden = true;
//					break;
//				} else {
//					signGefunden = false;
//				}
//			}
//
//			if(signGefunden){
//				System.out.println("Sinatur \"" + newValue + "\" gefunden");
//				setLabelMeldungUndTextField("green", "");
//				changeListenerSignatur.setValue(newValue);
////				styleListener.addCSSEigenschaften(propStringCSSTextFieldSignatur, "-fx-border-radius: 3; -fx-border-color: green; -fx-focus-color: green; -fx-faint-focus-color: #CAFFDC22;");
////				styleListener.removeCSSEigenschaften(propStringCSSTextFieldSignatur, "-fx-border-width: 1; -fx-border-color: red; -fx-focus-color: red; -fx-faint-focus-color: #ff000022;");
//				String[] partSign = newValue.split("\\.");
//				for (int j = 0; j < partSign.length; j++) {
//
//					if (j == 0) {
//						setLabelHaupttitel(partSign[0]);
//					}
//
//					if (j == 1) {
//						setLabelHaupttitel(partSign[0]);
//						setLabelUntertitel1(partSign[0], partSign[1]);
//					}
//
//					if (j == 2) {
//						setLabelHaupttitel(partSign[0]);
//						setLabelUntertitel1(partSign[0], partSign[1]);
//						setLabelUntertitel2(partSign[0], partSign[1], partSign[2]);
//					}
//				}
//
//				int anzahlPunkte = countZeichen(newValue, '.');
//
//				if (newValue.equalsIgnoreCase("")) {
//					labelHaupttitel.setText("");
//				}
//				if(anzahlPunkte == 0){
//					labelUntertitel1.setText("");
//				}
//				if(anzahlPunkte == 0 || anzahlPunkte == 1){
//					labelUntertitel2.setText("");
//				}
//
//			} else {
//				System.err.println("Sinatur \"" + newValue + "\" nicht gefunden");
//				setLabelMeldungUndTextField("red", "Signatur konnte nicht gefunden werden.");
//				changeListenerSignatur.setValue(newValue);
////				styleListener.addCSSEigenschaften(propStringCSSTextFieldSignatur, "-fx-border-radius: 3; -fx-border-width: 1; -fx-border-color: red; -fx-focus-color: red; -fx-faint-focus-color: #FFBCBC22;");
//				// Haupt- und Untertitel aus Label löschen
//				int anzahlPunkte = countZeichen(newValue, '.');
//
//				if (newValue.equalsIgnoreCase("")) {
//					labelHaupttitel.setText("");
//				}
//				if(anzahlPunkte == 0){
//					labelUntertitel1.setText("");
//				}
//				if(anzahlPunkte == 0 || anzahlPunkte == 1){
//					labelUntertitel2.setText("");
//				}
//
//			}
//
	    }
	};

}
