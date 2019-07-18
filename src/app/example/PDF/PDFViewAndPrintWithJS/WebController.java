package app.example.PDF.PDFViewAndPrintWithJS;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import app.example.PDF.PDFViewAndPrintWithJS.testrun.FXMLDocumentController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;



public class WebController implements Initializable {

    @FXML private WebView web;
    @FXML private Button btn;

    public void initialize(URL location, ResourceBundle resources) {
        WebEngine engine = web.getEngine();
        String url = getClass().getResource("web/viewer.html").toExternalForm();

        // connect CSS styles to customize pdf.js appearance
        engine.setUserStyleSheetLocation(getClass().getResource("web.css").toExternalForm());

        engine.setJavaScriptEnabled(true);
        engine.load(url);

        
        
        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
    		public void changed(ObservableValue ov, State oldState, State newState) {
        		if (newState == engine.getLoadWorker().getState().SUCCEEDED) {
	        		byte[] data = null;
	        		try {
	        			data = FileUtils.readFileToByteArray(new File("TestData/file.pdf"));
	        		} catch (IOException ex) {
	        			Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
	        		}
	                
	                String base64 = Base64.getEncoder().encodeToString(data);
	                engine.executeScript("openFileFromBase64('" + base64 + "')");

        		}
    		}

    });
        
//        engine.getLoadWorker()
//                .stateProperty()
//                .addListener((observable, oldValue, newValue) -> {
//                    // to debug JS code by showing console.log() calls in IDE console
//                    JSObject window = (JSObject) engine.executeScript("window");
//                    window.setMember("java", new JSLogListener());
//                    engine.executeScript("console.log = function(message){ java.log(message); };");
//
//                    // this pdf file will be opened on application startup
//                    if (newValue == Worker.State.SUCCEEDED) {
//                        try {
////                            // readFileToByteArray() comes from commons-io library
////                        	String path = Launcher.class.getResource("file.pdf").getFile();
////                            byte[] data = FileUtils.readFileToByteArray(new File(path));
////                            String base64 = Base64.getEncoder().encodeToString(data);
////                            // call JS function from Java code
////                            engine.executeScript("openFileFromBase64('" + base64 + "')");
//                            
//                            
//                            engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
//                        		public void changed(ObservableValue ov, State oldState, State newState) {
//                	        		if (newState == engine.getLoadWorker().getState().SUCCEEDED) {
//                		        		byte[] data = null;
//                		        		try {
//                		        			data = FileUtils.readFileToByteArray(new File("TestData/file.pdf"));
//                		        		} catch (IOException ex) {
//                		        			Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//                		        		}
//                		                
//                		                String base64 = Base64.getEncoder().encodeToString(data);
//                		                engine.executeScript("openFileFromBase64('" + base64 + "')");
//                	
//                	        		}
//                        		}
//
//                        });
//                            
//                            
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });

        // this file will be opened on button click
        btn.setOnAction(actionEvent -> {
            try {
                engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            		public void changed(ObservableValue ov, State oldState, State newState) {
    	        		if (newState == engine.getLoadWorker().getState().SUCCEEDED) {
    		        		byte[] data = null;
    		        		try {
    		        			data = FileUtils.readFileToByteArray(new File("TestData/file.pdf"));
    		        		} catch (IOException ex) {
    		        			Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    		        		}
    		                
    		                String base64 = Base64.getEncoder().encodeToString(data);
    		                engine.executeScript("openFileFromBase64('" + base64 + "')");
    	
    	        		}
            		}

            });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}