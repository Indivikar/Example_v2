package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import app.Start;
import app.view.function.IWindowMax;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CDrawerContent implements Initializable, IWindowMax {

	private Start start;
	private Stage primaryStage;
	
	@FXML private Button buttonDrawerClose;
	@FXML private Button buttonTest;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		buttonDrawerClose.setOnAction(e -> {
			start.getDrawer().close();
		});		
		
//		buttonTest.setOnAction(e -> {
//			FXMLLoader loaderContent  = new FXMLLoader(Start.class.getResource("view/fxml/nodes/test.fxml"));			
//			AnchorPane content = null;
//			try {
//				content = loaderContent.load();
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			

			
//	        content.getChildren().add(rs);
	        
//			start.getDrawersStack().setContent(content);
//		});		
	}

	public void set(Start start, Stage primaryStage) {
		this.start = start;
		this.primaryStage = primaryStage;		
	}
	

}
