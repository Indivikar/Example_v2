package app.example.Grafik.SVGToXML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

// https://github.com/skrb/SVGLoader

public class SVGToXMLDemo extends Application {

	private VBox root;
	private TextArea textArea;
	private WebEngine webEngine;
	
	public SVGToXMLDemo() {
		Platform.runLater(() -> {
			try {
				start(new Stage());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
    @Override
    public void start(Stage stage) throws FileNotFoundException {

    	SVGPath svg = createSVGPath(getClass().getResource("outline-save-24px.svg").getFile()); 
    	
    	WebView browser = new WebView();
    	browser.setPrefHeight(50);
    	webEngine = browser.getEngine();
    	
    	textArea = new TextArea();
    	
    	root = new VBox(browser, textArea);     	
        Scene scene = new Scene(root, 1024, 200);
        
        stage.setScene(scene);
        stage.setTitle("SVG to XML Demo");
        stage.show();
    }

    private SVGPath createSVGPath(String url) {
    	System.out.println(url);
    	SVGPath svgPath = new SVGPath();
    	Task<String> svgLoadWorker = createSVGLoadWorker(url);
    	svgLoadWorker.setOnSucceeded(e -> {
    		
    		svgPath.setContent(svgLoadWorker.getValue()); 
    		
    		System.out.println(svgPath.getContent());
    		textArea.setText(svgPath.getContent());
    		webEngine.loadContent(svgPath.getContent());  		
    	});
    	
    	new Thread(svgLoadWorker).start();
    	return svgPath;
	}
    
    private Task<String> createSVGLoadWorker(String pathDataUrl){
    	
		return new Task<String>() {

			@Override
			protected String call() throws Exception {
				String pathData = null;
				InputStream in = new FileInputStream(new File(pathDataUrl));
				pathData = new Scanner(in, "UTF-8").useDelimiter("\\A").next();
				return pathData;
			}
			
		};   	
    };
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}
