package app.test.unknowClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class Start extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        Button btn = new Button();
        btn.setText("Start second Stage");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
					try {
						String className = "app.test.unknowClass.HelloWorld";
						Class<?> clazz = Class.forName(className);
						Constructor<?> constructor = clazz.getConstructor();
						Object helloWorld = constructor.newInstance();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
 
        Scene scene = new Scene(root, 300, 250);
 
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
