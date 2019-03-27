package app;

import java.io.IOException;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXDrawer.DrawerDirection;

import app.controller.CContent;
import app.controller.CDrawerContent;
import app.controller.CMain;
import app.test.JavaKeywordsDemo;
import app.view.function.IWindowMax;
import app.view.function.ResizeHelper;
import app.view.function.StageVerschiebenMitAnchorPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Start extends Application implements IWindowMax {

	// Config
	public static boolean hasShadowPane = false;
	
	private JFXDrawersStack drawersStack;
	private JFXDrawer drawer;
	private CMain controllerMain;	
	
	private CContent controllerContent;
	private CDrawerContent controllerDrawerContent;
	
	@Override
	public void start(Stage primaryStage)  {
		try {
			Platform.setImplicitExit(false);
			
			FXMLLoader loaderRoot  = new FXMLLoader(Start.class.getResource("view/fxml/main.fxml"));			
	        AnchorPane root = loaderRoot.load();
		
			FXMLLoader loaderContent  = new FXMLLoader(Start.class.getResource("view/fxml/content.fxml"));			
			AnchorPane content = loaderContent.load();
        
			FXMLLoader loaderDrawerContent = new FXMLLoader(Start.class.getResource("view/fxml/drawerContent.fxml"));			
	        AnchorPane drawerContent = loaderDrawerContent.load();
	        drawer = addContentInDrawer(drawerContent);
	        
			Scene scene = new Scene(root);
			scene.getStylesheets().add(Start.class.getResource("/app/view/css/MainStyle.css").toExternalForm());
			scene.getStylesheets().add(Start.class.getResource("/app/view/css/java-keywords.css").toExternalForm());

			controllerMain = loaderRoot.getController();
			controllerMain.set(this, primaryStage);
	
			controllerContent = loaderContent.getController();
			controllerContent.set(this, primaryStage);
			
			controllerDrawerContent = loaderDrawerContent.getController();
			controllerDrawerContent.set(this, primaryStage);
			
			// Set content in DrawersStack
			this.drawersStack = controllerMain.getDrawersStack();
			drawersStack.setContent(content);
			
			setWindowMaxMitDoppelKlick(primaryStage, controllerMain.getButtonWindowMax(), root, root, hasShadowPane);
			new StageVerschiebenMitAnchorPane(root, root, controllerMain.getButtonWindowMax(), primaryStage, false);
			ResizeHelper.addResizeListener(primaryStage, root);
			
			primaryStage.setOnCloseRequest((event) -> {
	    		primaryStage.close();
	    		Platform.exit();
	    		System.exit(0);
			});
	
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.setResizable(true);
			primaryStage.setX(6000);
			primaryStage.setY(10);		
			primaryStage.show();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    @Override
    public void stop() throws Exception {
    	Platform.exit();
    	System.exit(0);
    }

    private JFXDrawer addContentInDrawer(AnchorPane drawerContent) {
    	JFXDrawer drawer = new JFXDrawer();
        drawer.setDirection(DrawerDirection.LEFT);
        drawer.setDefaultDrawerSize(300);
        drawer.setSidePane(drawerContent);
        drawer.setOverLayVisible(false);
        drawer.setResizableOnDrag(true);
        
		return drawer;
	}
    
    // Getter 
	public CMain getControllerDrawersStack() {return controllerMain;}
	public JFXDrawer getDrawer() {return drawer;}
	public JFXDrawersStack getDrawersStack() {return drawersStack;}

	public static void main(String[] args) {
        launch(args);
    }

}
