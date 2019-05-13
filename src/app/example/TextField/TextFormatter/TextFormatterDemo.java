package app.example.TextField.TextFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;

//more Pattern Example
//https://howtodoinjava.com/regex/java-regex-validate-email-address/

public class TextFormatterDemo extends Application {
	
	private VBox root;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		root = new VBox();
		root.setSpacing(10.0);
//		root.setAlignment(Pos.CENTER_LEFT);
		root.setPadding(new Insets(10.0));
		
		addNewExample("Time Formatter", "10:15:09", new TextField(), formatterDate());
		addNewExample("Only numbers", "1234", new TextField_1(50.0, "^[0-9]*$"));
		addNewExample("Decimal only one dot", "10.12", new TextField_1(50.0, "^\\d*\\.?\\d*$"));
		addNewExample("No letters", "1-|2", new TextField_2(50.0, "[a-z]"));
		addNewExample("No letters", "1-|2", new TextField_3(150.0, "^(.+)@(.+)$")); // Geht noch nicht
				
		Scene scene = new Scene(root, 450, 400);

		stage.setScene(scene);
		stage.show();
	}
		  
	
	private void addNewExample(String labelName, String promptText, TextField textField) {
		addNewExample(labelName, promptText, textField, null);
	}
	
	private void addNewExample(String labelName, String promptText, TextField textField, TextFormatter<?> textFormatter) {
		
		HBox hBox = new HBox();	
		hBox.setSpacing(10.0);

		
		AnchorPane anchorPane = new AnchorPane();
		
		Label label = new Label(labelName + ":");
		AnchorPane.setTopAnchor(label, 5.0);
		
		if (textFormatter != null) {
			textField = new TextField();
			textField.setTextFormatter(textFormatter);
		}		
		textField.setPromptText(promptText);		
		AnchorPane.setLeftAnchor(textField, 150.0);

						
		anchorPane.getChildren().addAll(label, textField);
		hBox.getChildren().addAll(anchorPane);
		root.getChildren().add(hBox);
	}

	
	private TextFormatter<?> formatterDate() {	

		TextFormatter<?> form = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			form = new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return form;
	}
	
}
