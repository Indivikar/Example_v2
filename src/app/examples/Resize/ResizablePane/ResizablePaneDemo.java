package app.examples.Resize.ResizablePane;

//import org.scenicview.ScenicView;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;



public class ResizablePaneDemo extends Application {

	Stage stage;
	Scene scene;
	StackPane root;

	public ResizablePaneDemo() {
		Platform.runLater(() -> {           
	           try {
				start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		       
		});
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		configureScene();
		configureStage();
		// Logic starts
//		StackPane dock = StackPaneBuilder.create().alignment(Pos.TOP_LEFT).build();
//		StackPane content = StackPaneBuilder.create().style("-fx-background-color:red;").build();
		
		StackPane dock = new StackPane();
		dock.setAlignment(Pos.TOP_LEFT);
		StackPane content = new StackPane();
		content.setStyle("-fx-background-color:red;");
		Pane pane = new Pane(dock,content);
		pane.setMaxSize(200, 200);
		dock.getChildren().add(pane);
		
		root.setPadding(new Insets(15));
		root.getChildren().add(dock);
	}

	private void configureStage() {
		stage.setTitle(this.getClass().getSimpleName());
		stage.setX(0);
		stage.setY(0);
		stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
		stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
		stage.setScene(this.scene);
		stage.show();
	}

	private void configureScene() {
		root = new StackPane();
		BorderPane bp = new BorderPane();
		bp.setBottom(getBottom());
		bp.setCenter(root);
		bp.autosize();
		this.scene = new Scene(bp, Color.LINEN);
//		scene.getStylesheets().add("styles/demoTemplate.css");
	}

	private Node getBottom() {
		StackPane sp = new StackPane();
		sp.setMinHeight(25);
		sp.setAlignment(Pos.TOP_RIGHT);
		ImageView image = new ImageView(new Image(getClass().getResourceAsStream("mglass.gif")));
		image.setCursor(Cursor.HAND);
		image.setTranslateX(-5);
		image.setTranslateY(3);
		image.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent paramT) {
//				ScenicView.show(scene);
			}
		});
		sp.getChildren().addAll(new Separator(), image);
		return sp;
	}

}

