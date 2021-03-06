package app.example.Animation.analogclock;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AnalogClockDemo extends Application {

    private static final double START_RADIUS = 100;
    private static final int NO_HOUR_TICKS = 12;
    private static final int NO_MINUTE_TICKS = 60;
    private final AnalogClockwork clockwork = new AnalogClockwork();

    public AnalogClockDemo() {
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
        launch(args);
    }

    public void start(final Stage stage) throws Exception {
    	
    	
    	Group group = new Group();
    	group.getChildren().addAll(
    			clockDial(),
                minuteTickMarks(),
                hourTickMarks(),
                hourHand(),
                minuteHand(),
                secondsHand()
           );
    	
    	
    	
    	final Parent root = group;
//        final Parent root = GroupBuilder.create()
//            .children(
//
//             )
//             .build();
        setUpMouseForScaleAndMove(stage, root);
        Scene scene = transparentScene(root);
        showTransparentStage(stage, scene);
    }

    private Node clockDial() {

        Stop stops[] = {
            new Stop(0.92, Color.WHITE),
            new Stop(0.98, Color.BLACK),
            new Stop(1.0, Color.BLACK)
        };
        RadialGradient gradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, stops);

        Circle circle = new Circle(START_RADIUS, gradient);
        circle.setCenterX(START_RADIUS);
        circle.setCenterY(START_RADIUS);
        return circle;
    }

    private Node hourHand() {
        double distanceFromRim = START_RADIUS * 0.5;
        Rotate rotate = handRotation(clockwork.hourHandAngle());
        return hourOrMinuteHand(distanceFromRim, Color.BLACK, rotate);
    }

    private Node minuteHand() {
        double distanceFromRim = START_RADIUS * 0.75;
        Rotate rotate = handRotation(clockwork.minuteHandAngle());
        return hourOrMinuteHand(distanceFromRim, Color.BLACK, rotate);
    }

    private Node secondsHand() {
        double distanceFromRim = START_RADIUS * 0.7;
        Color handColor = Color.RED;
        Rotate rotate = handRotation(clockwork.secondsHandAngle());
        Group group = new Group();
        group.getChildren().addAll(                
        		secondsHandLine(distanceFromRim, handColor),
                secondsHandTip(distanceFromRim, handColor),
                centerPoint(handColor)
           );
        group.getTransforms().add(rotate);
        
        
        return group;
        		
//        return GroupBuilder.create()
//            .children(
//
//            )
//           .transforms(rotate)
//           .build();
    }

    private Node secondsHandTip(double distanceFromRim, Color handColor) {
        double handTipRadius = START_RADIUS * 0.07;
        
        Circle circle = new Circle();
        circle.setCenterX(START_RADIUS);
        circle.setCenterY(START_RADIUS - distanceFromRim);
        circle.setFill(handColor);
        circle.setRadius(handTipRadius);
        
        return circle;
        
//        return CircleBuilder.create()
//            .centerX(START_RADIUS)
//            .centerY(START_RADIUS - distanceFromRim)
//            .fill(handColor)
//            .radius(handTipRadius)
//            .build();
    }

    private Node secondsHandLine(double distanceFromRim, Paint handColor) {
        double handCenterExtension = START_RADIUS * 0.15;
        double handWidth = START_RADIUS * 0.02;
        
        Line line = new Line();
        line.setStartX(START_RADIUS);
        line.setStartY(START_RADIUS - distanceFromRim);
        line.setEndX(START_RADIUS);
        line.setEndY(START_RADIUS + handCenterExtension);
        line.setStrokeWidth(handWidth);
        line.setStroke(handColor);
        
        return line;
        
//        return LineBuilder.create()
//            .startX(START_RADIUS)
//            .startY(START_RADIUS - distanceFromRim)
//            .endX(START_RADIUS)
//            .endY(START_RADIUS + handCenterExtension)
//            .strokeWidth(handWidth)
//            .stroke(handColor)
//            .build();
    }

    private Rotate handRotation(ObservableDoubleValue handAngle) {
    	
    	Rotate handRotation = new Rotate();
    	handRotation.setPivotX(START_RADIUS);
    	handRotation.setPivotY(START_RADIUS);
    	
//        Rotate handRotation = RotateBuilder.create()
//            .pivotX(START_RADIUS)
//            .pivotY(START_RADIUS)
//            .build();
    	
        handRotation.angleProperty().bind(handAngle);
        return handRotation;
    }

    private Node hourOrMinuteHand(double distanceFromRim, Color color, Rotate rotate) {
        double handBaseWidth = START_RADIUS * 0.05;
        double handTipWidth = START_RADIUS * 0.03;
        double handCenterExtension = START_RADIUS * 0.15;
        double leftBaseCornerX = START_RADIUS - handBaseWidth;
        double baseY = START_RADIUS + handCenterExtension;
        double tipY = START_RADIUS - distanceFromRim;
        double leftTipCornerX = START_RADIUS - handTipWidth;
        double rightTipCornerX = START_RADIUS + handTipWidth;
        double rightCornerBaseX = START_RADIUS + handBaseWidth;
        
        Path path = new Path();
        path.setFill(color);
        path.setStroke(Color.TRANSPARENT);
        path.getTransforms().add(rotate);
        path.getElements().addAll(
	        		new MoveTo(leftBaseCornerX, baseY),
	                new LineTo(leftTipCornerX, tipY),
	                new LineTo(rightTipCornerX, tipY),
	                new LineTo(rightCornerBaseX, baseY),
	                new LineTo(leftBaseCornerX, baseY)
        		);
      
        return path;
        
//        return PathBuilder.create()
//            .fill(color)
//            .stroke(Color.TRANSPARENT)
//            .elements(
//
//             )
//             .transforms(rotate)
//             .build();
    }

    private Node minuteTickMarks() {
        Group tickMarkGroup = new Group();
        int noTicks = NO_MINUTE_TICKS;
        for (int n = 0; n < noTicks; n++) {
            tickMarkGroup.getChildren().add(tickMark(n, 1, noTicks));
        }
        return tickMarkGroup;
    }

    private Node hourTickMarks() {
        Group tickMarkGroup = new Group();
        int noTicks = NO_HOUR_TICKS;
        for (int n = 0; n < noTicks; n++) {
            tickMarkGroup.getChildren().add(tickMark(n, 6, noTicks));
        }
        return tickMarkGroup;
    }

    private Node tickMark(int n, double width, int noTicks) {
    	
    	Rotate rotate = new Rotate();
    	rotate.setPivotX(START_RADIUS);
    	rotate.setPivotY(START_RADIUS);
    	rotate.setAngle(360 / noTicks * n);

    	
        Line line = new Line();
        line.setStartX(START_RADIUS);
        line.setStartY(START_RADIUS * 0.12);
        line.setEndX(START_RADIUS);
        line.setEndY(START_RADIUS * 0.2 + width * 2);
        line.getTransforms().add(rotate);
        line.setStrokeWidth(width);
        
        return line;
        
//        return LineBuilder.create()
//            .startX(START_RADIUS)
//            .startY(START_RADIUS * 0.12)
//            .endX(START_RADIUS)
//            .endY(START_RADIUS * 0.2 + width * 2)
//            .transforms(
//                RotateBuilder.create()
//                .pivotX(START_RADIUS)
//                .pivotY(START_RADIUS)
//                .angle(360 / noTicks * n)
//                .build()
//            )
//           .strokeWidth(width)
//           .build();
    }

    private Node centerPoint(Color color) {
    	
    	Circle circle = new Circle();
    	circle.setFill(color);
    	circle.setRadius(0.03 * START_RADIUS);
    	circle.setCenterX(START_RADIUS);
    	circle.setCenterY(START_RADIUS);
    	
    	return circle;
    	
//        return CircleBuilder.create()
//            .fill(color)
//            .radius(0.03 * START_RADIUS)
//            .centerX(START_RADIUS)
//            .centerY(START_RADIUS)
//            .build();
    }

    private void setUpMouseForScaleAndMove(final Stage stage, final Parent root) {
        SimpleDoubleProperty mouseStartX = new SimpleDoubleProperty(0);
        SimpleDoubleProperty mouseStartY = new SimpleDoubleProperty(0);
        root.setOnMousePressed(setMouseStartPoint(mouseStartX, mouseStartY));
        root.setOnMouseDragged(moveWhenDragging(stage, mouseStartX, mouseStartY));
        root.onScrollProperty().set(scaleWhenScrolling(stage, root));
    }

    private EventHandler<? super MouseEvent> setMouseStartPoint(final SimpleDoubleProperty mouseStartX, final SimpleDoubleProperty mouseStartY) {
        return new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                mouseStartX.set(mouseEvent.getX());
                mouseStartY.set(mouseEvent.getY());
            }
        };
    }

    private EventHandler<MouseEvent> moveWhenDragging(final Stage stage, final SimpleDoubleProperty mouseStartX, final SimpleDoubleProperty mouseStartY) {
        return new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                stage.setX(stage.getX() + mouseEvent.getX() - mouseStartX.doubleValue());
                stage.setY(stage.getY() + mouseEvent.getY() - mouseStartY.doubleValue());
            }
        };
    }

    private EventHandler<ScrollEvent> scaleWhenScrolling(final Stage stage, final Parent root) {
        return new EventHandler<ScrollEvent>() {
            public void handle(ScrollEvent scrollEvent) {
                double scroll = scrollEvent.getDeltaY();
                root.setScaleX(root.getScaleX() + scroll / 100);
                root.setScaleY(root.getScaleY() + scroll / 100);
                root.setTranslateX(root.getTranslateX() + scroll);
                root.setTranslateY(root.getTranslateY() + scroll);
                stage.sizeToScene();
            }
        };
    }

    private Scene transparentScene(Parent root) {
    	
    	Scene scene = new Scene(root);
    	scene.setFill(Color.TRANSPARENT);
    	
    	return scene;
    	
//        return SceneBuilder.create()
//            .root(root)
//            .fill(Color.TRANSPARENT)
//            .build();
    }

    private void showTransparentStage(Stage stage, Scene scene) {
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

}
