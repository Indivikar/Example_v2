package app.example.Internationalization.NodeNamesWithPropsFXML;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class InternationalizationDemo extends Application {

	  private Scene scene;
	  
	  public InternationalizationDemo() {
			Platform.runLater(() -> {           
		        try {
					start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		       
			});
	  }
	  
	  public static void main(String[] args) {
	    launch(args);
	  }
	
	  @Override
	  public void start(Stage stage) throws Exception {

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setResources(ResourceBundle.getBundle(InternationalizationDemo.class.getPackage().getName()+".names"));
	    BorderPane root = fxmlLoader.load(InternationalizationDemo.class.getResource("Internationalization.fxml").openStream());
		  	      
	    Controller controller = fxmlLoader.getController();
	    controller.set(this);
		
		scene = new Scene(root);
 
	    stage.setScene(scene);
	    stage.setTitle("Internationalization");
	    stage.show();
	
	  }
	
	//  private ComboBox<Locale> createComboBox() {
	//
	////    ComboBox<Locale> comboBox = new ComboBox<>();
	//    ObservableList<Locale> options = FXCollections.observableArrayList(Locale.UK, Locale.GERMANY);
	//    comboBox.setItems(options);
	//    comboBox.setConverter(new StringConverter<Locale>() {
	//      @Override
	//      public String toString(Locale object) {
	//        return object.getDisplayLanguage();
	//      }
	//
	//      @Override
	//      public Locale fromString(String string) {
	//        return null;
	//      }
	//    });
	//
	//    comboBox.setCellFactory(p -> new LanguageListCell());
	//    comboBox.getSelectionModel().selectFirst();
	////    comboBox.setOnAction(event -> loadView(comboBox.getSelectionModel().getSelectedItem()));
	//    return comboBox;
	//
	//  }
	
//	  public BorderPane loadView(Locale locale) {
//		  
//		  BorderPane pane = null;
//	    try {
//	      FXMLLoader fxmlLoader = new FXMLLoader();
//	
//	      // Here, just the resource bundles name is mentioned. You can add support for more languages
//	      // by adding more properties-files with language-specific endings like
//	      // "en_GB.properties".
//	
//	      fxmlLoader.setResources(ResourceBundle.getBundle(InternationalizationDemo.class.getPackage().getName()+".names"));
//	      pane = (BorderPane) fxmlLoader.load(this.getClass().getResource("Internationalization.fxml").openStream());
//	      
//	      Controller controller = fxmlLoader.getController();
//	      controller.set(this);
//	      if (scene != null) {
//			scene.setRoot(pane);
//	      }
//	      
////	      stage.setScene(new Scene(pane));
//	//      borderPane.setCenter(pane);
//	    } catch (IOException ex) {
//	      ex.printStackTrace();
//	    }
//		return pane;
//	  }
	
	  class LanguageListCell extends ListCell<Locale> {
	
		    @Override protected void updateItem(Locale item, boolean empty) {
			      super.updateItem(item, empty);
			      if (item != null) {  
			    	  setText(item.getDisplayLanguage());
			      }
		    }
	  }

}