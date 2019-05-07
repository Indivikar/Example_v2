package app.example.DragAndDrop.DragAndDropFromInside;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DragFromInsideDemo extends Application {
	
	public DragFromInsideDemo() {
		Platform.runLater(() -> {           
	           start(new Stage());		       
		});
	}
	
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 400, 200);
        
        File myFile = new File("C:\\Windows\\explorer.exe");
        
        List<File> list = new ArrayList<File>();
        list.add(myFile);
        
        Label label = new Label("Source -> " + myFile.getAbsolutePath());
        label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, null, new BorderWidths(2))));
        label.setPadding(new Insets(10));
        label.setCursor(Cursor.OPEN_HAND);
        label.setLayoutX(100);
        label.setLayoutY(85);
        
        label.setOnDragDetected(event -> {
            /* drag was detected, start drag-and-drop gesture*/
            System.out.println("onDragDetected");
            
            /* allow any transfer mode */
            Dragboard db = label.startDragAndDrop(TransferMode.COPY);
            
            /* put a string on dragboard */
            ClipboardContent content = new ClipboardContent();

            content.putFiles(list);
            db.setContent(content);
            
            event.consume();
        });
        
        root.getChildren().addAll(label);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
