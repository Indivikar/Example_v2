package app.example.TextArea.MaxCharAndRow;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MaxCharAndRowDemo extends Application  {

	private TextField textFiledChar;
	private TextField textFiledRow;
	private TextArea textArea;

    public MaxCharAndRowDemo() {
		Platform.runLater(() -> {
			try {
				start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Max. Char and Row Text-Formatter");

        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);
        
        textFiledChar = new TextField("10");
        textFiledChar.textProperty().addListener(textChangeListenerChar);
        textFiledChar.setPrefWidth(40);
        Label labelChar = new Label("Max. Char in a Line");       
        HBox hChar = new HBox(textFiledChar, labelChar);
        hChar.setSpacing(10);
        
        textFiledRow = new TextField("2");
        textFiledRow.textProperty().addListener(textChangeListenerRow);
        textFiledRow.setPrefWidth(40);
        Label labelRow = new Label("Max. Row");
        HBox hRow = new HBox(textFiledRow, labelRow);
        hRow.setSpacing(10);
        
        int maxChar = Integer.parseInt(textFiledChar.getText());
        int maxRow = Integer.parseInt(textFiledRow.getText());
        
        textArea = new TextArea();
        textArea.setTextFormatter(createTextFormatterMaxCharAndRow(maxChar, maxRow));
        

        root.getChildren().addAll(hChar, hRow, textArea);
        
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    ChangeListener<String> textChangeListenerChar = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
            String newValue) {
        	if (!newValue.isEmpty()) {
	            if (!newValue.matches("\\d*")) {    
	            	textFiledChar.setText(newValue.replaceAll("[^\\d]", ""));
	            } else {
	                int maxChar = Integer.parseInt(textFiledChar.getText());
	                int maxRow = Integer.parseInt(textFiledRow.getText());
	                textArea.setTextFormatter(createTextFormatterMaxCharAndRow(maxChar, maxRow));
	            }
        	}
        }
    };
    
    ChangeListener<String> textChangeListenerRow = new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, 
            String newValue) {
        	if (!newValue.isEmpty()) {
				if (!newValue.matches("\\d*")) {   
                	textFiledRow.setText(newValue.replaceAll("[^\\d]", ""));              	
                } else {
                    int maxChar = Integer.parseInt(textFiledChar.getText());
                    int maxRow = Integer.parseInt(textFiledRow.getText());
                    textArea.setTextFormatter(createTextFormatterMaxCharAndRow(maxChar, maxRow));
                }
			}
        }      
    };
    
	public <T> TextFormatter<T> createTextFormatterMaxCharAndRow(int maxCharsInARow, int maxRows) {
    	final IntegerProperty lines = new SimpleIntegerProperty(1);

        return new TextFormatter<>(change -> {
            if (change.isAdded()) {
            	String str = change.getControlNewText().substring(0, change.getRangeStart());
            	lines.set(countChar(str, '\n') + 1);

                if (countChar(change.getControlNewText(), '\n') >= maxRows) {
                    	return null;
				}
            }

            if(lines.get() > 0){
	           	String[] part = change.getControlNewText().split("\n");
	            for (int i = lines.get() -1; i < part.length; i++) {
					System.out.println(part[i]);
					if(part[i].length() <= maxCharsInARow){
						return change;
					} else {
						return null;
					}
				}
            }

            return change;
        });
    }
    
	public int countChar(String input, char toCount){
        int counter = 0;
        for(char c: input.toCharArray()){
            if(c==toCount)
                counter++;
        }
        return counter;
    }
	
    public static void main(String[] args) {
        Application.launch(args);
    }

}
