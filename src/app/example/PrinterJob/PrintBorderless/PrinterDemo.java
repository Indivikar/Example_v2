package app.example.PrinterJob.PrintBorderless;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
 
public class PrinterDemo extends Application {
    
	private Stage primaryStage;
	private PrinterDemo PrinterDemo;

	
	private SimpleBooleanProperty propBoolDis = new SimpleBooleanProperty(false);
	
	public PrinterDemo() {
		
	}

    @Override
    public void start(Stage primaryStage) {
    	this.PrinterDemo = this;
        this.primaryStage = primaryStage;

        Label labelMessage = new Label();
        labelMessage.setTextFill(Color.RED);

        TextField textField = new TextField();
        textField.setText("H:\\Users\\santosh\\Documents\\A4-gross.pdf");
        textField.setPrefWidth(400);
        textField.textProperty().addListener((ov, oldVal, newVal) -> {
        	File filePDF = new File(newVal);
        	if (filePDF.exists() && filePDF.getName().endsWith(".pdf")) {
        		labelMessage.setText("");
        		propBoolDis.setValue(false);

        	} else {
        		propBoolDis.setValue(true);
        		labelMessage.setText("File not founded!");
        	}
        });
        
        Button buttonPrint = new Button("Print..."); 
        buttonPrint.disableProperty().bind(propBoolDis);
        buttonPrint.setOnAction(e -> {
                System.out.println("Hello World!");   
                new PrintPDF(this, new File(textField.getText()));                
        });
        
        Button buttonSearch = new Button("search...");
        buttonSearch.setOnAction(e -> {
        	startFileChooser(textField);
        });

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(textField, buttonSearch);


        
        VBox root = new VBox();
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(hBox, labelMessage, buttonPrint);
 
        Scene scene = new Scene(root, 500, 250);
 
        primaryStage.setTitle("PrintPDF-Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startFileChooser(TextField textField) {
    	 FileChooser fileChooser = new FileChooser();

         // Set extension filter
         FileChooser.ExtensionFilter extFilter = 
                 new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
         fileChooser.getExtensionFilters().add(extFilter);

         // Show open file dialog
         File file = fileChooser.showOpenDialog(primaryStage);
         if (file != null) {
        	 textField.setText(file.getPath());
         }
	}
    
    public PrinterDemo getPrinterDemo() {return PrinterDemo;}
    public Stage getPrimaryStage() {return primaryStage;}
    
    public static void main(String[] args) {
        launch(args);
    }
}
