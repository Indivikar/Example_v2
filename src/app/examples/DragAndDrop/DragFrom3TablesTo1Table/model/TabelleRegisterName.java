package app.examples.DragAndDrop.DragFrom3TablesTo1Table.model;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TabelleRegisterName {

		private SimpleIntegerProperty id;
		private SimpleStringProperty nameRegister;


		public TabelleRegisterName(int id, String nameRegister) {

			this.id = new SimpleIntegerProperty(id);
			this.nameRegister = new SimpleStringProperty(nameRegister);


		}

	    public ReadOnlyIntegerProperty idProperty() {return id;}
	    public ReadOnlyStringProperty nameRegisterProperty() {return nameRegister;}


	    // Getter
		public final int getID() {return id.get();}
		public final String getNameRegister() {return nameRegister.get();}


		// Setter
		public final void setID(int id) {this.id.set(id);}
		public final void setNameRegister(String nameRegister) {this.nameRegister.set(nameRegister);}


}
