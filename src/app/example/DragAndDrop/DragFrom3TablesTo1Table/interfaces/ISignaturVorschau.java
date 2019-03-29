package app.example.DragAndDrop.DragFrom3TablesTo1Table.interfaces;

import app.example.DragAndDrop.DragFrom3TablesTo1Table.model.TabelleRegistraturplan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public interface ISignaturVorschau {



	public default Pair<Boolean, String> toolTipSignatur(SystemTrayIcon systemTrayIcon, String signatur) {

			ObservableList<TabelleRegistraturplan> registraturPlan = systemTrayIcon.getDatenbankenLaden().get(systemTrayIcon.getListernerWerte().getSelectedDB()).getTabelleDetails();

			String haupttitel = null;
			String untertitel1 = null;
			String untertitel2 = null;

			boolean isSignaturFehler = false;

			String[] partSign = signatur.split("\\.");
			for (int j = 0; j < partSign.length; j++) {

				if (j == 0) {
					Pair<Boolean, String> liste = setLabelHaupttitel(partSign[0], registraturPlan);

					haupttitel = liste.getValue();
					isSignaturFehler = liste.getKey();
				}

				if (j == 1) {
					Pair<Boolean, String> liste = setLabelUntertitel1(partSign[0], partSign[1], registraturPlan);

					haupttitel = setLabelHaupttitel(partSign[0], registraturPlan).getValue();
					untertitel1 = liste.getValue();
					isSignaturFehler = liste.getKey();
				}

				if (j == 2) {
					Pair<Boolean, String> liste = setLabelUntertitel2(partSign[0], partSign[1], partSign[2], registraturPlan);

					haupttitel = setLabelHaupttitel(partSign[0], registraturPlan).getValue();
					untertitel1 = setLabelUntertitel1(partSign[0], partSign[1], registraturPlan).getValue();
					untertitel2 = liste.getValue();
					isSignaturFehler = liste.getKey();
				}
			}

			int anzahlPunkte = countZeichen(signatur, '.');

			if (signatur.equalsIgnoreCase("")) {
				haupttitel = "";
			}
			if(anzahlPunkte == 0){
				untertitel1 = "";
			}
			if(anzahlPunkte == 0 || anzahlPunkte == 1){
				untertitel2 = "";
			}

		return new Pair<>(isSignaturFehler, haupttitel + "\n" + untertitel1 + "\n" + untertitel2);
	}

	public default Pair<Boolean, String> setLabelHaupttitel(String partSign0, ObservableList<TabelleRegistraturplan> registraturPlan){


		ObservableList<TabelleRegistraturplan> alleZeilenMitDieserSign = FXCollections.observableArrayList();
		StringBuilder sb = new StringBuilder();


		for (TabelleRegistraturplan tabelleRegistraturplan : registraturPlan) {
			if(tabelleRegistraturplan.getSign().equalsIgnoreCase(partSign0 + ".0") ){

				alleZeilenMitDieserSign.add(new TabelleRegistraturplan(tabelleRegistraturplan.getSign(), tabelleRegistraturplan.getText(),
						tabelleRegistraturplan.getTotal(), tabelleRegistraturplan.getTeil(), tabelleRegistraturplan.getZaehler()));

			}
		}

		for (int i = 0; i < alleZeilenMitDieserSign.size(); i++) {
//			System.out.println(alleZeilenMitDieserSign.get(i).getText());

//			System.out.println(i + " -> " + alleZeilenMitDieserSign.size());
			if(i == 0){
				sb.append(alleZeilenMitDieserSign.get(i).getText().trim());
			} else if(i == alleZeilenMitDieserSign.size() - 1) {
				String string = sb.toString();
//				labelHaupttitel.setText(textLeerzeichen(string.substring(0, string.length()-3).trim()));
				return new Pair<>(false , textLeerzeichen(string.substring(0, string.length()-3).trim()));
			} else {
				String string = sb.toString().trim().substring(sb.toString().trim().length()-1);
				if(string.equals("-")){
					System.out.println("drin");
					String saveString = sb.substring(0, sb.toString().length()-1);
					sb = new StringBuilder();
					sb.append(saveString + alleZeilenMitDieserSign.get(i).getText().trim());
				} else {
					sb.append(" " + alleZeilenMitDieserSign.get(i).getText().trim());
				}
			}

		}
		return new Pair<>(false, "");

	}

	public default Pair<Boolean, String> setLabelUntertitel1(String partSign0, String partSign1, ObservableList<TabelleRegistraturplan> registraturPlan){

		ObservableList<TabelleRegistraturplan> alleZeilenMitDieserSign = FXCollections.observableArrayList();
		StringBuilder sb = new StringBuilder();

		for (TabelleRegistraturplan tabelleRegistraturplan : registraturPlan) {
			if(tabelleRegistraturplan.getSign().equalsIgnoreCase(partSign0 + "." + partSign1)){
				if(tabelleRegistraturplan.getTotal() == 0){
//					labelUntertitel1.setText(textLeerzeichen(tabelleRegistraturplan.getText()));
					return new Pair<>(false, textLeerzeichen(tabelleRegistraturplan.getText()));
				} else {
					alleZeilenMitDieserSign.add(new TabelleRegistraturplan(tabelleRegistraturplan.getSign(), tabelleRegistraturplan.getText(),
						tabelleRegistraturplan.getTotal(), tabelleRegistraturplan.getTeil(), tabelleRegistraturplan.getZaehler()));
				}
			}
		}

		for (int i = 0; i <= alleZeilenMitDieserSign.size(); i++) {

//			System.out.println(i + " -> " + alleZeilenMitDieserSign.size());
			if(i == 0){
				sb.append(alleZeilenMitDieserSign.get(i).getText().trim());
			} else if(i ==
					alleZeilenMitDieserSign.size()) {
//				labelUntertitel1.setText(textLeerzeichen(sb.toString()));
				return new Pair<>(false, textLeerzeichen(sb.toString()));
			} else {
				String string = sb.toString().trim().substring(sb.toString().trim().length()-1);
				if(string.equals("-")){
					String saveString = sb.substring(0, sb.toString().length()-1);
					sb = new StringBuilder();
					sb.append(saveString + alleZeilenMitDieserSign.get(i).getText().trim());
				} else {
					sb.append(" " + alleZeilenMitDieserSign.get(i).getText().trim());
				}

			}
		}
		return new Pair<>(false, "");
	}

	public default Pair<Boolean, String> setLabelUntertitel2(String partSign0, String partSign1, String partSign2, ObservableList<TabelleRegistraturplan> registraturPlan){
		boolean isSignaturFehler = false;
		String signatur = partSign0 + "." + partSign1 + "." + partSign2;

		ObservableList<TabelleRegistraturplan> alleZeilenMitDieserSign = FXCollections.observableArrayList();
		StringBuilder sb = new StringBuilder();

		for (TabelleRegistraturplan tabelleRegistraturplan : registraturPlan) {
			if(tabelleRegistraturplan.getSign().equalsIgnoreCase(signatur)){
					if(tabelleRegistraturplan.getTotal() == 0){
//						labelUntertitel2.setText(textLeerzeichen(tabelleRegistraturplan.getText()));
						return new Pair<>(false, textLeerzeichen(tabelleRegistraturplan.getText()));
					} else {
						alleZeilenMitDieserSign.add(new TabelleRegistraturplan(tabelleRegistraturplan.getSign(), tabelleRegistraturplan.getText(),
							tabelleRegistraturplan.getTotal(), tabelleRegistraturplan.getTeil(), tabelleRegistraturplan.getZaehler()));
					}
			}
		}

		for (int i = 0; i <= alleZeilenMitDieserSign.size(); i++) {
			try {
				if(i == 0){
					sb.append(alleZeilenMitDieserSign.get(i).getText().trim());
				} else if(i == alleZeilenMitDieserSign.size()) {
	//				labelUntertitel2.setText(textLeerzeichen(sb.toString()));
					return new Pair<>(false, textLeerzeichen(sb.toString()));
				} else {
					String string = sb.toString().trim().substring(sb.toString().trim().length()-1);
					if(string.equals("-")){
						System.out.println("drin");
						String saveString = sb.substring(0, sb.toString().length()-1);
						sb = new StringBuilder();
						sb.append(saveString + alleZeilenMitDieserSign.get(i).getText().trim());
					} else {
						sb.append(" " + alleZeilenMitDieserSign.get(i).getText().trim());
					}
				}
			} catch (Exception e) {
				isSignaturFehler = true;
				e.getMessage();
			}
//			System.out.println(alleZeilenMitDieserSign.get(i).getText());
//
//			System.out.println(i + " -> " + alleZeilenMitDieserSign.size());

		}
		return new Pair<>(isSignaturFehler, "");
	}


	public default String textLeerzeichen(String string) {
		StringBuilder newSB = new StringBuilder();
		String text;

		String[] part = string.split(" ");
		text = string.replaceFirst(part[0], "").trim();
		newSB.append(part[0]);

		for (int j = part[0].length(); j < 11; j++) {
			newSB.append(" ");
		}
		newSB.append(text);

		return newSB.toString();
	}



	public default int countZeichen(String string, char zeichen)
	{
	    int count = 0;
	    for (int i=0; i < string.length(); i++)
	    {
	        if (string.charAt(i) == zeichen)
	        {
	             count++;
	        }
	    }
	    return count;
	}

}
