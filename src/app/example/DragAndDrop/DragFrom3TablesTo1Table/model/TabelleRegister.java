package app.example.DragAndDrop.DragFrom3TablesTo1Table.model;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TabelleRegister {

		private SimpleIntegerProperty register;
		private SimpleStringProperty sign;
		private SimpleIntegerProperty geschaeftsJahr;
		private SimpleIntegerProperty geschaeftsNr;
		private SimpleStringProperty text;
		private SimpleStringProperty path;
		private SimpleStringProperty file;
		private SimpleStringProperty doc;
		private SimpleStringProperty gremium;
		private SimpleStringProperty textSort;
		private SimpleIntegerProperty record;
		private SimpleIntegerProperty art;
		private SimpleBooleanProperty immerDrucken;
//		private SimpleStringProperty protokoll;
		private SimpleStringProperty sitzung;

		public TabelleRegister(int register, String sign, int geschaeftsJahr, int geschaeftsNr, String text, String path, String file,
				String doc, String gremium, String textSort, int record, int art, boolean immerDrucken, String sitzung) {

			this.register = new SimpleIntegerProperty(register);
			this.sign = new SimpleStringProperty(sign);
			this.geschaeftsJahr = new SimpleIntegerProperty(geschaeftsJahr);
			this.geschaeftsNr = new SimpleIntegerProperty(geschaeftsNr);
			this.text = new SimpleStringProperty(text);
			this.path = new SimpleStringProperty(path);
			this.file = new SimpleStringProperty(file);
			this.doc = new SimpleStringProperty(doc);
			this.gremium = new SimpleStringProperty(gremium);
			this.textSort = new SimpleStringProperty(textSort);
			this.record = new SimpleIntegerProperty(record);
			this.art = new SimpleIntegerProperty(art);
			this.immerDrucken = new SimpleBooleanProperty(immerDrucken);
//			this.protokoll = new SimpleStringProperty(protokoll);
			this.sitzung = new SimpleStringProperty(sitzung);

		}

	    public ReadOnlyIntegerProperty registerProperty() {return register;}
	    public ReadOnlyStringProperty signaturProperty() {return sign;}
	    public ReadOnlyIntegerProperty geschaeftsJahrProperty() {return geschaeftsJahr;}
	    public ReadOnlyIntegerProperty geschaeftsNrProperty() {return geschaeftsNr;}
	    public ReadOnlyStringProperty textProperty() {return text;}
	    public ReadOnlyStringProperty pathProperty() {return path;}
	    public ReadOnlyStringProperty fileProperty() {return file;}
	    public ReadOnlyStringProperty docProperty() {return doc;}
	    public ReadOnlyStringProperty gremiumProperty() {return gremium;}
	    public ReadOnlyStringProperty textSortProperty() {return textSort;}
	    public ReadOnlyIntegerProperty recordProperty() {return record;}
	    public ReadOnlyIntegerProperty artProperty() {return art;}
	    public ReadOnlyBooleanProperty immerDruckenProperty() {return immerDrucken;}
//	    public ReadOnlyStringProperty protokollProperty() {return protokoll;}
	    public ReadOnlyStringProperty sitzungProperty() {return sitzung;}

	    // Getter
		public final int getRegister() {return register.get();}
		public final String getSign() {return sign.get();}
		public final int getGeschaeftsJahr() {return geschaeftsJahr.get();}
		public final int getGeschaeftsNr() {return geschaeftsNr.get();}
		public final String getText() {return text.get();}
		public final String getPath() {return path.get();}
		public final String getFile() {return file.get();}
		public final String getDoc() {return doc.get();}
		public final String getGremium() {return gremium.get();}
		public final String getTextSort() {return textSort.get();}
		public final int getRecord() {return record.get();}
		public final int getArt() {return art.get();}
		public final boolean getImmerDrucken() {return immerDrucken.get();}
//		public final String getProtokoll() {return protokoll.get();}
		public final String getSitzung() {return sitzung.get();}

		// Setter
		public final void setRegister(int register) {this.register.set(register);}
		public final void setSign(String sign) {this.sign.set(sign);}
		public final void setGeschaeftsJahr(int geschaeftsJahr) {this.geschaeftsJahr.set(geschaeftsJahr);}
		public final void setGeschaeftsNr(int geschaeftsNr) {this.geschaeftsNr.set(geschaeftsNr);}
		public final void setText(String text) {this.text.set(text);}
		public final void setPath(String path) {this.path.set(path);}
		public final void setFile(String file) {this.file.set(file);}
		public final void setDoc(String doc) {this.doc.set(doc);}
		public final void setGremium(String gremium) {this.gremium.set(gremium);}
		public final void setTextSort(String textSort) {this.textSort.set(textSort);}
		public final void setRecord(int record) {this.record.set(record);}
		public final void setArt(int art) {this.art.set(art);}
		public final void setImmerDrucken(boolean immerDrucken) {this.immerDrucken.set(immerDrucken);}
//		public final void setProtokoll(String protokoll) {this.protokoll.set(protokoll);}
		public final void setSitzung(String sitzung) {this.sitzung.set(sitzung);}

}
