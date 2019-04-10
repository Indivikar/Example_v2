package app.example.Grafik.Transform;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
 

public class ScaleTransformDemo extends Application {
 
    Rectangle rect;
    double x0, y0;

    public ScaleTransformDemo() {
		Platform.runLater(() -> {           
	           start(new Stage());		       
		});
	}
    
    public static void main(String[] args) {
        launch(args);
    }
     
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ScaleTransformDemo");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
 
        rect = new Rectangle(100, 100, Color.RED);
         
        scene.setOnMouseDragged(mouseHandler);
        scene.setOnMousePressed(mouseHandler);
 
        root.getChildren().add(rect);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
     
    void setScaleRect(double sX, double sY){
         
        rect.setScaleX(sX);
        rect.setScaleY(sY);
         
    }
     
         
    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
 
        @Override
        public void handle(MouseEvent mouseEvent) {
             
            if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
                x0 = mouseEvent.getX();
                y0 = mouseEvent.getY();
                setScaleRect(1.0, 1.0);
 
            }else if(mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED){
                double scaleX = ((mouseEvent.getX() - x0) /100) + 1;
                double scaleY = ((mouseEvent.getY() - y0)/100) + 1;
                 
                setScaleRect(scaleX, scaleY);
 
            }
             
        }
     
    };
}
