package app.example.Internationalization.ChangeLanguageWithPropertiesFXML;

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
//	private Locale selectedLocale;
	
	@FXML private BorderPane borderPane;
	@FXML private ComboBox<Locale> comboBox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
//		comboBoxProps();
	}

	private void comboBoxProps(Locale selectedLocale) {
		System.out.println("Locale: " + selectedLocale);
	    ObservableList<Locale> options = FXCollections.observableArrayList(Locale.UK, Locale.GERMANY);
	    comboBox.setItems(options);
	    
	    comboBox.setConverter(new StringConverter<Locale>() {
	      @Override
	      public String toString(Locale object) {
	        return object.getDisplayLanguage();
	      }

	      @Override
	      public Locale fromString(String string) {
	        return null;
	      }
	    });

	    comboBox.setCellFactory(p -> new LanguageListCell());
	    comboBox.getSelectionModel().select(selectedLocale);
	    comboBox.setOnAction(event -> Internationalization.loadView(comboBox.getSelectionModel().getSelectedItem()));
	}
	
	public void set(InternationalizationDemo Internationalization, Locale selectedLocale) {
		this.Internationalization = Internationalization;
		comboBoxProps(selectedLocale);
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
