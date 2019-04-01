package app.example.CheckBox.RadioCheckBox;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;

//import com.javafx.experiments.scenicview.ScenicView;

public class RadioCheckBoxDemo extends Application {

	Stage stage;
	Scene scene;
	StackPane root;

	public RadioCheckBoxDemo() {
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
		VBox vb = new VBox();
		vb.setSpacing(10);

		ToggleGroup tg = new ToggleGroup();

		RadioButton radioButton1 = new RadioButton();
		radioButton1.setText("First");
		radioButton1.getStyleClass().add("radio-checkbox");
		radioButton1.setToggleGroup(tg);
		
		RadioButton radioButton2 = new RadioButton();
		radioButton2.setText("Second");
		radioButton2.getStyleClass().add("radio-checkbox");
		radioButton2.setToggleGroup(tg);
		
		RadioButton radioButton3 = new RadioButton();
		radioButton3.setText("Third");
		radioButton3.getStyleClass().add("radio-checkbox");
		radioButton3.setToggleGroup(tg);
				
//		vb.getChildren().addAll(RadioButtonBuilder.create().text("First").styleClass("radio-checkbox").toggleGroup(tg).build(),
//				RadioButtonBuilder.create().text("Second").styleClass("radio-checkbox").toggleGroup(tg).build(),
//				RadioButtonBuilder.create().text("Third").styleClass("radio-checkbox").toggleGroup(tg).build());
		vb.getChildren().addAll(radioButton1, radioButton2, radioButton3);
		root.getChildren().add(vb);
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
		scene.getStylesheets().add(RadioCheckBoxDemo.class.getResource("radioCheckBox.css").toExternalForm());
	}

	private Node getBottom() {
		StackPane sp = new StackPane();
		sp.setMinHeight(25);
		sp.setAlignment(Pos.TOP_RIGHT);
		Circle c = new Circle();
		c.setFill(Color.RED);
		c.setTranslateX(-5);
		c.setTranslateY(3);
		c.setRadius(8);
		c.setCursor(Cursor.HAND);
		
//		Circle c = CircleBuilder.create().fill(Color.RED).translateX(-5).translateY(3).radius(8).cursor(Cursor.HAND).build();
		c.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent paramT) {
//				ScenicView.show(scene);
			}
		});
		sp.getChildren().addAll(new Separator(), c);
		return sp;
	}

}

