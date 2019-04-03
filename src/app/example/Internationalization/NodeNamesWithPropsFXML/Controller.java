package app.example.Internationalization.NodeNamesWithPropsFXML;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

public class Controller implements Initializable{

	private InternationalizationDemo Internationalization;

//	@FXML private BorderPane borderPane;
//	@FXML private ComboBox<Locale> comboBox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void set(InternationalizationDemo Internationalization) {
		this.Internationalization = Internationalization;
	}
	
	class LanguageListCell extends ListCell<Locale> {
		  @Override protected void updateItem(Locale item, boolean empty) {
		      super.updateItem(item, empty);
		      if (item != null) {  
		    	  setText(item.getDisplayLanguage());
		      }
		  }
	}
}
