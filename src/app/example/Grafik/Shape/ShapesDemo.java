package app.example.Grafik.Shape;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ShapesDemo extends Application {

	Stage stage;
	Scene scene;
	
	HBox root;
	
	public ShapesDemo() {
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
//		root.setHgap(10);
//		root.setVgap(10);
		createTriangle();
		createPlusShape();
		createRectangle();
		createSquare();
		createOctagone();
		createCurve();
		createCircle();
		createPlus();
		createMinus();
		createArrows();
		createPath();
	}

	
	private void createPlus() {
		Path plus = new Path();
		plus.setStroke(Color.web("#000000"));
		plus.setFill(Color.web("#FFFFFF"));
		plus.setStrokeWidth(1);		
		plus.setRotate(45);
		plus.setCursor(Cursor.HAND);
		plus.setOnMouseClicked(e -> {
			
		});
		plus.getElements().addAll(new MoveTo(5, 0),
				new LineTo(5, 5),
				new LineTo(0, 5),
				new LineTo(0, 10),
				new LineTo(5, 10),
				new LineTo(5, 15),
				new LineTo(10, 15),
				new LineTo(10, 10),
				new LineTo(15, 10),
				new LineTo(15, 5),
				new LineTo(10, 5),
				new LineTo(10, 0),
				new LineTo(5, 0));
		
//		
//		Path plus = PathBuilder.create()
//				.elements(new MoveTo(5, 0),
//						new LineTo(5, 5),
//						new LineTo(0, 5),
//						new LineTo(0, 10),
//						new LineTo(5, 10),
//						new LineTo(5, 15),
//						new LineTo(10, 15),
//						new LineTo(10, 10),
//						new LineTo(15, 10),
//						new LineTo(15, 5),
//						new LineTo(10, 5),
//						new LineTo(10, 0),
//						new LineTo(5, 0))
//				.stroke(Color.web("#000000"))
//				.fill(Color.web("#FFFFFF"))
//				.strokeWidth(1)
//				.rotate(45)
//				.cursor(Cursor.HAND)
//				.build();
		
		Circle c = new Circle();
		c.setRadius(13);
		c.setStyle("-fx-fill:-fx-base;");
		
		StackPane sp = new StackPane();
		sp.setMaxWidth(26);
		sp.setMaxHeight(26);
		sp.setPrefWidth(26);
		sp.setPrefHeight(26);
		sp.getChildren().addAll(c, plus);
		
//		Circle c = CircleBuilder.create().radius(13).style("-fx-fill:-fx-base;").build() ;
//		StackPane sp = StackPaneBuilder.create()
//									   .maxHeight(26).maxWidth(26)
//									   .prefHeight(26).prefWidth(26)
//									   .children(c,plus).build();
		root.getChildren().add(sp);
	}
	
	private void createMinus() {
		
		Path minus = new Path();
		minus.setStroke(Color.web("#000000"));
		minus.setFill(Color.web("#FFFFFF"));
		minus.setStrokeWidth(1);		
		minus.setCursor(Cursor.HAND);
		minus.getElements().addAll(new MoveTo(0, 0),
				new LineTo(0, 5),
				new LineTo(15, 5),
				new LineTo(15, 0),
				new LineTo(0, 0));
		
//		Path minus = PathBuilder.create()
//				.elements(new MoveTo(0, 0),
//						new LineTo(0, 5),
//						new LineTo(15, 5),
//						new LineTo(15, 0),
//						new LineTo(0, 0))
//				.stroke(Color.web("#000000"))
//				.fill(Color.web("#FFFFFF"))
//				.strokeWidth(1)
//				.cursor(Cursor.HAND)
//				.build();
		
		Circle c = new Circle();
		c.setRadius(13);
		c.setStyle("-fx-fill:-fx-base;");
		
		StackPane sp = new StackPane();
		sp.getStyleClass().add("close-btn");
		sp.setMaxWidth(26);
		sp.setMaxHeight(26);
		sp.setPrefWidth(26);
		sp.setPrefHeight(26);
		sp.getChildren().addAll(c, minus);
		
//		Circle c = CircleBuilder.create().radius(13).style("-fx-fill:-fx-base;").build() ;
//		StackPane sp = StackPaneBuilder.create().styleClass("close-btn")
//									   .maxHeight(26).maxWidth(26)
//									   .prefHeight(26).prefWidth(26)
//									   .children(c,minus).build();
		
		root.getChildren().add(sp);
	}

	private void createArrows() {
		Path arrow = new Path();
		arrow.getElements().add(new MoveTo(40, 20));
		arrow.getElements().add(new LineTo(27, 20));
		arrow.getElements().add(new LineTo(27, 50));
		arrow.getElements().add(new LineTo(34, 40));
		arrow.getElements().add(new LineTo(27, 50));
		arrow.getElements().add(new LineTo(20, 40));
		arrow.setStrokeWidth(1);
		arrow.setStroke(Color.BLACK);
		
		Path arrow1 = new Path();
		arrow1.getElements().add(new MoveTo(0, 60)); //M 0 60
		arrow1.getElements().add(new LineTo(0, 70)); //V 70
		arrow1.getElements().add(new LineTo(60, 130)); // L 60 130
		arrow1.getElements().add(new LineTo(80, 110)); // L 80 110
		arrow1.getElements().add(new LineTo(40, 70)); // L 40 70
		arrow1.getElements().add(new LineTo(40, 60)); // V 60
		arrow1.getElements().add(new LineTo(80, 20)); // L 80 20
		arrow1.getElements().add(new LineTo(60, 0));  // L 60 0
		arrow1.getElements().add(new LineTo(0, 60)); // L 0 60 Z
		arrow1.getElements().add(new LineTo(0, 70));
		arrow1.setStrokeWidth(1);
		arrow1.setStroke(Color.BLACK);
		arrow1.setFill(Color.WHITE);
		
		Path arrow2 = new Path();
		arrow2.getElements().add(new MoveTo(0, 6));
		arrow2.getElements().add(new LineTo(0, 7));
		arrow2.getElements().add(new LineTo(6, 13));
		arrow2.getElements().add(new LineTo(8, 11));
		arrow2.getElements().add(new LineTo(4, 7));
		arrow2.getElements().add(new LineTo(4, 6));
		arrow2.getElements().add(new LineTo(8, 2));
		arrow2.getElements().add(new LineTo(6, 0));
		arrow2.getElements().add(new LineTo(0, 6));
		arrow2.getElements().add(new LineTo(0, 7));
		arrow2.setStrokeWidth(1);
		arrow2.setStroke(Color.BLACK);
		arrow2.setFill(Color.WHITE);
		
		StackPane leftSide = new StackPane();
		leftSide.setPrefSize(8, 13);
		leftSide.getStyleClass().add("small-bended-arrow");
		
		Rectangle p = new Rectangle();
		p.getStyleClass().add("small-bended-arrow-rect");
		
		Path downArrow = new Path();
		downArrow.getElements().add(new MoveTo(0, 0));
		downArrow.getElements().add(new LineTo(0, 1));
		downArrow.getElements().add(new LineTo(5, 6));
		downArrow.getElements().add(new LineTo(6, 6));
		downArrow.getElements().add(new LineTo(11, 1));
		downArrow.getElements().add(new LineTo(11, 0));
		downArrow.getElements().add(new LineTo(0, 0));
		downArrow.setStrokeWidth(.9);
		downArrow.setStroke(Color.BLACK);
		downArrow.setFill(Color.WHITE);
		
		Group group = new Group();
		group.getChildren().add(leftSide);
		
//		root.getChildren().addAll(group, arrow1, downArrow);
		root.getChildren().addAll(group, arrow1, downArrow);
		
	}
	
	private void createCircle() {
		StackPane sp = new StackPane();
		sp.setPrefSize(60, 60);
		sp.getStyleClass().add("circle-arc-shape");
		
		StackPane sp1 = new StackPane();
		sp1.setStyle("-fx-border-color:red;-fx-border-width:1px;");
		sp1.getChildren().add(sp);
		root.getChildren().add(sp1);
	}

	private void configureStage(){
		stage.setTitle("Shapes Demo");
		stage.setX(0);
	    stage.setY(0);
	    stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
	    stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
	    stage.setScene(this.scene);
	    stage.show();
	}
	
	private void configureScene(){
		root = new HBox();
		root.setSpacing(10);
		root.autosize();
		this.scene = new Scene(root, Color.LINEN);
		scene.getStylesheets().add("styles/shapes.css");
	}

	private void createPath() {
	      Path path = new Path();
	      path.getElements().add(new MoveTo(50, 50));
	      path.getElements().add(new LineTo(50, 200));
	      path.getElements().add(new LineTo(100, 200));
	      path.getElements().add(new LineTo(100, 100));
	      path.getElements().add(new LineTo(200, 100));
	      path.setStrokeWidth(1);
	      path.setStroke(Color.RED);
	     
	      Path arrow = new Path();
	      arrow.getElements().add(new MoveTo(90, 50));
	      arrow.getElements().add(new LineTo(50, 50));
	      arrow.getElements().add(new LineTo(50, 100));
	      arrow.getElements().add(new LineTo(63, 87));
	      arrow.getElements().add(new LineTo(50, 100));
	      arrow.getElements().add(new LineTo(37, 87));
	      arrow.setStrokeWidth(1);
	      arrow.setStroke(Color.BLACK);
	      arrow.setRotate(135);
	      
	      Path arrow1 = new Path();
	      arrow1.getElements().add(new MoveTo(40, 20));
	      arrow1.getElements().add(new LineTo(25, 20));
	      arrow1.getElements().add(new LineTo(25, 50));
	      arrow1.getElements().add(new LineTo(35, 37));
	      arrow1.getElements().add(new LineTo(25, 50));
	      arrow1.getElements().add(new LineTo(15, 37));
	      arrow1.setStrokeWidth(1);
	      arrow1.setStroke(Color.GREEN);
	      
	      
	      StackPane sp = new StackPane();
	      sp.setPadding(new Insets(15,0,0,0));
	      sp.setMaxWidth(40);
	      sp.setAlignment(Pos.TOP_CENTER);
	      
	      Button printBtn = new Button("Print Me");
			printBtn.setRotate(-90);
			printBtn.setDisable(true);
			
			Group printCol = new Group();
			printCol.getChildren().addAll(printBtn);
			printCol.setTranslateY(40);
			
			sp.getChildren().addAll(arrow1,printCol);
			
	      root.getChildren().addAll(path, sp);

	}
	
	private void createTriangle() {
		Group gp = new Group();
		StackPane leftSide = new StackPane();
		leftSide.setPrefSize(18, 30);
		leftSide.getStyleClass().add("triangle-shape");
		gp.getChildren().add(leftSide);
		
		StackPane rightSide = new StackPane();
		rightSide.setPrefSize(18, 30);
		rightSide.getStyleClass().add("triangle-shape");
		rightSide.setRotate(180);
		
		root.getChildren().addAll(gp,rightSide);
	}

	private void createPlusShape() {
		Group gp = new Group();
		StackPane plus1 = new StackPane();
		plus1.setPrefSize(30, 30);
		plus1.getStyleClass().add("plus-shape");
		gp.getChildren().add(plus1);
		
		StackPane plus2 = new StackPane();
		plus2.setPrefSize(18, 30);
		plus2.getStyleClass().add("plus-shape");
		
		StackPane plus3 = new StackPane();
		plus3.setPrefSize(18, 30);
		plus3.getStyleClass().add("plus-shape-with-curve");
		
		StackPane plus4 = new StackPane();
		plus4.setMaxSize(12, 12);
		plus4.setMinSize(12, 12);
		plus4.setRotate(45);
		plus4.getStyleClass().add("plus-shape-with-curve");
		
		root.getChildren().addAll(gp,plus2,plus3,plus4);
	}

	private void createRectangle() {
		StackPane sp = new StackPane();
		sp.setPrefSize(60, 20);
		sp.getStyleClass().add("rectangle-shape");
		root.getChildren().add(sp);
	}
	private void createSquare() {
		StackPane sp = new StackPane();
		sp.setPrefSize(40, 40);
		sp.getStyleClass().add("square-shape");
		root.getChildren().add(sp);
	}
	private void createOctagone() {
		StackPane sp = new StackPane();
		sp.setPrefSize(60, 60);
		sp.getStyleClass().add("octagone-shape");
		root.getChildren().add(sp);
	}
	private void createCurve() {
		StackPane sp = new StackPane();
		sp.setPrefSize(60, 60);
		sp.getStyleClass().add("curve-shape");
		root.getChildren().add(sp);
		
		MainModel model = new MainModel();
		final Button btn1 = new Button("BUtton 1");
		model.flagProperty().bind(btn1.armedProperty());
		
		Button btn2 = new Button("BUtton 2");
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent paramT) {
				System.out.println("Button two clicked");
				btn1.arm();
			}
		});
		root.getChildren().addAll(btn1,btn2);
		
		
	}

}

class MainModel{
	private SimpleBooleanProperty flag = new SimpleBooleanProperty();
	
	public MainModel(){
		flag.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(
					ObservableValue<? extends Boolean> paramObservableValue,
					Boolean paramT1, Boolean paramT2) {
				System.out.println("Modle changed....");
				
			}
		});
	}
	
	public SimpleBooleanProperty flagProperty(){
		return flag;
	}
}

