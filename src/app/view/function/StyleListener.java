package app.view.function;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;

public class StyleListener {

	// TODO - methode "addCSSEigenschaften" nochmal überarbeiten, bei mehreren eigenschaften geht es nicht mehr, vergessen, das ";" hinten an zu hängen

	public void addCSSEigenschaften(SimpleStringProperty styleProperty, String string) {

		List<String> listAlteEigenschaften = new ArrayList<String>();
		List<String> listNeueEigenschaften = new ArrayList<String>();

		// Listen Füllen
		if(styleProperty.get() != null){
			String[] partsAlteEigenschaften = styleProperty.get().split(";");
			for (String alteParts : partsAlteEigenschaften) {
				listAlteEigenschaften.add(alteParts);
			}
		}

		String[] partsNeueEigenschaften = string.split(";");
		for (String neueParts : partsNeueEigenschaften) {
			listNeueEigenschaften.add(neueParts);
		}

		// Wenn es den z.b. den Befehl "-fx-text-fill:" schon gibt,dann entfernen, aber nur, wenn er neu gesetzt werden soll
		for (int i = 0; i < listAlteEigenschaften.size(); i++) {
			for (int j = 0; j < listNeueEigenschaften.size(); j++) {

				String[] partVonNeuemString = listNeueEigenschaften.get(j).split(":");
				if(listAlteEigenschaften.get(i).contains(partVonNeuemString[0])){
					listAlteEigenschaften.remove(i);

				}
			}
		}

		// Neue Befehle in die Liste mit den alten Befehlen adden
		for (int j = 0; j < listNeueEigenschaften.size(); j++) {
			listAlteEigenschaften.add(listNeueEigenschaften.get(j));
		}

		// Einen String aus der alten Liste erstellen
		StringBuilder sb = new StringBuilder();
		for (String strings : listAlteEigenschaften) {
			sb.append(strings);
		}

		styleProperty.setValue(null);
		System.out.println("Style: " + sb.toString());
		styleProperty.setValue(sb.toString());

	}

	public void removeCSSEigenschaften(SimpleStringProperty styleProperty, String string) {
		String newString = null;

		String[] parts = string.split(";");
		for (String part : parts) {


			newString = styleProperty.get().replaceAll(part, "");
		}

		styleProperty.setValue(null);
		styleProperty.setValue(newString);

	}

	public void removeAllCSSEigenschaften(SimpleStringProperty styleProperty) {
		styleProperty.setValue(null);
	}


}
