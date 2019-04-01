package app.example.Scene.ChangeScene;

import java.util.ResourceBundle;

import app.Start;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChangeSceneDemo extends Application
{
    public static void main(String[] args) throws ClassNotFoundException
    {
        Application.launch(ChangeSceneDemo.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
    	
		FXMLLoader loaderRoot  = new FXMLLoader(getClass().getResource("MainView.fxml"));			
		Parent root = loaderRoot.load();
    	
//        Parent root = FXMLLoader.load
//        (
//        		ChangeSceneDemo.class.getResource("MainView.fxml"),
//            ResourceBundle.getBundle(ChangeSceneDemo.class.getPackage().getName()+".MainView")/*properties file*/
//        );

        Scene scene = new Scene(root);
        stage.setScene(scene);
        

        
    	Controller controller = loaderRoot.getController();
    	controller.set();
    	
        stage.show();
    }
}