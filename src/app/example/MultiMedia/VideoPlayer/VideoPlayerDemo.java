package app.example.MultiMedia.VideoPlayer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
 
public class VideoPlayerDemo extends Application {
     
    private static final String MEDIA_URL = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";//"http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
    private static String arg1;
 
    public VideoPlayerDemo() {
		Platform.runLater(() -> {           
	           start(new Stage());		       
		});
	}
        
    public static void main(String[] args) {
        launch(args);
    }
     
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Player");
         
        Group root = new Group();
        Scene scene = new Scene(root, 540, 210);
 
        // create media player
//        Media media = new Media((arg1 != null) ? arg1 : MEDIA_URL);
        Media media = new Media(VideoPlayerDemo.class.getResource("video.flv").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.setAutoPlay(true);
         
        // create mediaView and add media player to the viewer
        MediaView mediaView = new MediaView(mediaPlayer);
        ((Group)scene.getRoot()).getChildren().add(mediaView);
         
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}