package app.example.PDF.PDFRenderer;

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class PDFRendererDemo extends Application {
    
	private PdfRenderer pdfRenderer;
	
//	public PDFRendererDemo() {
//		System.out.println("HelloWorld()");
//
//		Platform.runLater(() -> {           
//		           start(new Stage());		       
//		 });
//	}

	@Override
	public void init() throws Exception {
		this.pdfRenderer = new PdfRenderer();
		pdfRenderer.getNumberOfPages(new File(PDFRendererDemo.class.getResource("file.pdf").toExternalForm()));
	}
	
    @Override
    public void start(Stage primaryStage) {
        
    	
    	
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(pdfRenderer.renderPage(1, 1000, 1000));
 
        Scene scene = new Scene(root, 300, 250);
 
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
