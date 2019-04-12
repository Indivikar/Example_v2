package app.example.MultiMedia.VideoPlayerMitUntertitel;

import app.example.MultiMedia.VideoPlayer.VideoPlayerDemo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MediaPlayerDemo extends Application {

	public MediaPlayerDemo() {
		Platform.runLater(() -> {           
	           start(new Stage());		       
		});
	}
	
	public void start(Stage primaryStage) {
        primaryStage.setTitle("Embedded Media Player");
        Group root = new Group();
        Scene scene = new Scene(root, 540, 241);
        
        // create media player
//        Media media = new Media("http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv");
        Media media = new Media(VideoPlayerDemo.class.getResource("video.flv").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.setAutoPlay(true);
        StackPane stack = new StackPane();
        
        Text t = new Text("Subtitle..");
     // t.textProperty().addChangeListener(..);
    //    MediaControl mediaControl = new MediaControl(mediaPlayer);
        MediaView m =new MediaView(mediaPlayer);
        
        stack.getChildren().addAll(m, t);
        scene.setRoot(stack);
 
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	
	public static void main(String[] args) throws Exception { launch(args); }
}
