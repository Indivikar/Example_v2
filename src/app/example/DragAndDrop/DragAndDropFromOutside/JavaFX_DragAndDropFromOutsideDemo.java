package app.example.DragAndDrop.DragAndDropFromOutside;

import java.io.File;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
 
/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class JavaFX_DragAndDropFromOutsideDemo extends Application {
 
	public JavaFX_DragAndDropFromOutsideDemo() {
		Platform.runLater(() -> {           
	           start(new Stage());		       
		});
	}
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
     
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("java-buddy.blogspot.com");
        Group root = new Group();
        Scene scene = new Scene(root, 350, 300);
         
        
        HBox hBox = new HBox();
         
        //ScrollPane sp = new ScrollPane();
        //sp.setContent(hBox);
        //sp.setFitToHeight(true);
        //sp.setFitToWidth(true);
        
        setupGestureTarget(scene, hBox);
         
        root.getChildren().add(hBox);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.show();
    }
 
    void setupGestureTarget(final Scene target, final HBox targetBox){
         
        target.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");
 
                Dragboard db = event.getDragboard();
                if(db.hasFiles()){
                    event.acceptTransferModes(TransferMode.COPY);
                }
                 
                event.consume();
            }
        });
 
        target.setOnDragDropped(new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* data dropped */
                System.out.println("onDragDropped");
  
                Dragboard db = event.getDragboard();
                 
                if(db.hasFiles()){
                	
                    for(File file : db.getFiles()){                  	
                        String absolutePath = "file:///" + file.getAbsolutePath();
                        System.out.println("File: " + absolutePath);
                        Image dbimage = new Image(absolutePath);
                        ImageView dbImageView = new ImageView();
                        dbImageView.setImage(dbimage);
                        targetBox.getChildren().add(dbImageView);
                    }
 
                    event.setDropCompleted(true);
                }else{
                    event.setDropCompleted(false);
                }
 
                event.consume();
            }
        });
         
    }
}
