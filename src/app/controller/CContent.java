package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.NotificationPane;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;

import app.Start;
import app.view.function.IWindowMax;
import app.view.function.MyNotification;
import app.view.function.MyNotification.MyNotificationGraphic;
import app.view.function.MyNotificationPane;
import app.view.function.MyNotificationPane.MyNotificationPaneGraphic;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CContent implements Initializable, IWindowMax {

	private Start start;
	private Stage primaryStage;
	private JFXDrawersStack drawersStack;
	private JFXDrawer drawer;
	
	private MyNotification notification;
	private MyNotificationPane notificationPane;
	
	@FXML private AnchorPane shadowPane;
	@FXML private AnchorPane mainAnchorPane;
	
	@FXML private Button buttonWindowMin;
	@FXML private Button buttonWindowMax;
	@FXML private Button buttonWindowClose;
	
	@FXML private Button buttonDrawer;
	@FXML private Button buttonNotiBottomRight;
	@FXML private Button buttonNotiBottomLeft;
	@FXML private Button buttonNotiPaneTop;
	@FXML private Button buttonNotiPaneBottom;
	
//	@FXML JFXDrawersStack drawersStack;
//	@FXML JFXDrawer drawer;
	
//	@FXML Button buttonDrawer;
	
	@FXML public void actionButtonWindowMin(){
		System.out.println("Window min");
		primaryStage.setIconified(true);
	}
	@FXML public void actionButtonWindowMax(){
		setWindowMaxIcon(primaryStage, buttonWindowMax, shadowPane, mainAnchorPane, Start.hasShadowPane);
	}
	@FXML public void actionButtonWindowClose(){
		primaryStage.close();
		Platform.exit();
		System.exit(0);
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.notification = new MyNotification();
		this.notificationPane = new MyNotificationPane();
	}
	
	private void addActionButtons() {
		Platform.runLater(()->{
			buttonDrawer.setOnAction(e -> {
				drawersStack.toggle(drawer);				
			});
		});
		
		buttonNotiBottomRight.setOnAction(e -> {
			notification.create(Pos.BOTTOM_RIGHT, primaryStage, MyNotificationGraphic.PEN, true, "title", "text\ntext\ntext\ntext\ntext\ntext\n");			
		});

		buttonNotiBottomLeft.setOnAction(e -> {
			notification.create(Pos.BOTTOM_LEFT, primaryStage, MyNotificationGraphic.GEAR, true, "title", "text");			
		});
		
		buttonNotiPaneTop.setOnAction(e -> {
//			showNotificationPane(primaryStage);			
			notificationPane.create(primaryStage, true, MyNotificationPaneGraphic.PEN, "text\ntext\ntext\ntext\ntext\ntext\n");			
		});
		
		buttonNotiPaneBottom.setOnAction(e -> {
			notificationPane.create(primaryStage, false, MyNotificationPaneGraphic.GEAR, "text 2");			
		});
		
	}
	
	public void showNotificationPane(Stage stage) {
	    Scene scene = stage.getScene();
	    Parent pane = scene.getRoot();
	    if (!(pane instanceof NotificationPane)){
	        NotificationPane notificationPane = new NotificationPane(pane);
	        
	        scene = new Scene(notificationPane, scene.getWidth(), scene.getHeight());
	        stage.setScene(scene);
	        notificationPane.show();
	    } else {
	        ((NotificationPane)pane).show();
	    }
	}
	
	
	// Getter
	public Button getButtonWindowMax() {return buttonWindowMax;}
	
	// Setter
	public void set(Start start, Stage primaryStage) {
		this.start = start;
		this.primaryStage = primaryStage;		
		this.drawersStack = start.getControllerDrawersStack().getDrawersStack();
		this.drawer = start.getDrawer();

		addActionButtons();
		
	}

}
