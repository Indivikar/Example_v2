package app.example.Internationalization.ChangeLanguageWithProperties;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ChangeLanguageDemo extends Application {

    private final ObservableResourceFactory RESOURCE_FACTORY = new ObservableResourceFactory();

    public ChangeLanguageDemo() {
		RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(ChangeLanguageDemo.class.getPackage().getName()+".en"));
		Platform.runLater(() -> {           
	           start(new Stage());		       
		});
	}
    
    @Override
    public void start(Stage primaryStage) {
        ComboBox<Locale> languageSelect = new ComboBox<>();
        languageSelect.getItems().addAll(Locale.ENGLISH, Locale.GERMANY, Locale.FRENCH);
        languageSelect.setValue(Locale.ENGLISH);
        languageSelect.setCellFactory(lv -> new LocaleCell());
        languageSelect.setButtonCell(new LocaleCell());

        languageSelect.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
            	RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(ChangeLanguageDemo.class.getPackage().getName()+"." + newValue));
//                RESOURCE_FACTORY.setResources(ResourceBundle.getBundle(RESOURCE_NAME, newValue));
            }
        });

        Label label = new Label();
        label.textProperty().bind(RESOURCE_FACTORY.getStringBinding("greeting"));
        
        Label label2 = new Label();
        label2.textProperty().bind(RESOURCE_FACTORY.getStringBinding("day"));

        BorderPane root = new BorderPane(null, languageSelect, null, label, label2);
        root.setPadding(new Insets(10));
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static class LocaleCell extends ListCell<Locale> {
        @Override
        public void updateItem(Locale locale, boolean empty) {
            super.updateItem(locale, empty);
            if (empty) {
                setText(null);
            } else {
                setText(locale.getDisplayLanguage(locale));
            }
        }
    }

    public class ObservableResourceFactory {

        private ObjectProperty<ResourceBundle> resources = new SimpleObjectProperty<>();
        public ObjectProperty<ResourceBundle> resourcesProperty() {
            return resources ;
        }
        public final ResourceBundle getResources() {
            return resourcesProperty().get();
        }
        public final void setResources(ResourceBundle resources) {
            resourcesProperty().set(resources);
        }

        public StringBinding getStringBinding(String key) {
            return new StringBinding() {
                { bind(resourcesProperty()); }
                @Override
                public String computeValue() {
                    return getResources().getString(key);
                }
            };
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}