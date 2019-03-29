package app.example.Resize.ResizablePane;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Pane extends BorderPane {
	double thick = 5;
	private StackPane topDragger;
	private StackPane bottomDragger;
	private StackPane leftDragger;
	private StackPane rightDragger;
	private StackPane center;

	private double startDragX;
	private double startDragY;
	private double startNodeX;
	private double startNodeY;
	private double startWidth;
	private double startHeight;
	private final StackPane dock;
	private EventHandler<MouseEvent> pressedListener;

//	public ResizablePane(StackPane dock) {
//		super();
//		this.dock = dock;
//		configure();
//	}

	public Pane(StackPane dock, Node content) {
		super();
		this.dock = dock;
		
		topDragger = new StackPane();
		topDragger.setMaxHeight(thick);
		topDragger.setPrefHeight(thick);
		topDragger.setMinHeight(thick);
		topDragger.setCursor(Cursor.V_RESIZE);
		topDragger.setStyle("-fx-background-color:transparent;");
		
		bottomDragger = new StackPane();
		bottomDragger.setMaxHeight(thick);
		bottomDragger.setPrefHeight(thick);
		bottomDragger.setMinHeight(thick);
		bottomDragger.setCursor(Cursor.V_RESIZE);
		bottomDragger.setStyle("-fx-background-color:transparent;");
		
		leftDragger = new StackPane();
		leftDragger.setMaxWidth(thick);
		leftDragger.setPrefWidth(thick);
		leftDragger.setMinWidth(thick);
		leftDragger.setCursor(Cursor.H_RESIZE);
		leftDragger.setStyle("-fx-background-color:transparent;");
		
		rightDragger = new StackPane();
		rightDragger.setMaxWidth(thick);
		rightDragger.setPrefWidth(thick);
		rightDragger.setMinWidth(thick);
		rightDragger.setCursor(Cursor.H_RESIZE);
		rightDragger.setStyle("-fx-background-color:transparent;");
		
		center = new StackPane();
		center.setAlignment(Pos.TOP_LEFT);
		
		configure();
		center.getChildren().add(content);
	}

	private void configure() {
		// Initializing the mouse pressed listener.
		this.pressedListener = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				startDragX = me.getSceneX();
				startDragY = me.getSceneY();
				startNodeX = getTranslateX();
				startNodeY = getTranslateY();
				startWidth = getMaxWidth();
				startHeight = getMaxHeight();
			}
		};
		topDragger.setOnMousePressed(this.pressedListener);
		bottomDragger.setOnMousePressed(this.pressedListener);
		leftDragger.setOnMousePressed(this.pressedListener);
		rightDragger.setOnMousePressed(this.pressedListener);

		setTop(topDragger);
		setBottom(bottomDragger);
		setLeft(leftDragger);
		setRight(rightDragger);
		setCenter(center);
		buildDragListeners();
		buildResizeListeners();
	}

	public void setContent(Node content) {
		center.getChildren().add(content);
	}

	public ObservableList<Node> getContent() {
		return center.getChildren();
	}

	private void buildResizeListeners() {
		topDragger.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				double diffY = (me.getSceneY() - startDragY);
				double yTr = startNodeY + diffY;
				double calHeight = startHeight - diffY;
				if (calHeight < 0) {
					setTranslateY(startNodeY + startHeight);
					setMaxHeight(calHeight * -1);
				} else {
					setTranslateY(yTr);
					setMaxHeight(calHeight);
				}
			}
		});

		leftDragger.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				double diffX = (me.getSceneX() - startDragX);
				double xTr = startNodeX + diffX;
				double calWidth = startWidth - diffX;

				if (calWidth < 0) {
					setTranslateX(startNodeX + startWidth);
					setMaxWidth(calWidth * -1);
				} else {
					setTranslateX(xTr);
					setMaxWidth(calWidth);
				}
			}
		});

		rightDragger.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				double diffX = (me.getSceneX() - startDragX);
				double calWidth = startWidth + diffX;
				if (diffX < 0 && ((diffX * -1) > startWidth)) {
					double dTx = (diffX * -1) - startWidth;
					setTranslateX(startNodeX - dTx);
					setMaxWidth(dTx);
				} else {
					setTranslateX(startNodeX);
					setMaxWidth(calWidth);
				}
			}
		});

		bottomDragger.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				double diffY = (me.getSceneY() - startDragY);
				double calHeight = startHeight + diffY;
				if (diffY < 0 && ((diffY * -1) > startHeight)) {
					double dTy = (diffY * -1) - startHeight;
					setTranslateY(startNodeY - dTy);
					setMaxHeight(dTy);
				} else {
					setTranslateY(startNodeY);
					setMaxHeight(calHeight);
				}
			}
		});
	}

	/**
	 * Builds the drag listeners for the {@link Resizer} to move the node.
	 */
	private void buildDragListeners() {
		center.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				center.setCursor(Cursor.MOVE);
				startDragX = me.getSceneX();
				startDragY = me.getSceneY();
				startNodeX = getTranslateX();
				startNodeY = getTranslateY();
			}
		});
		center.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				center.setCursor(Cursor.DEFAULT);
			}
		});
		center.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				double xTr = startNodeX + (me.getSceneX() - startDragX);
				double yTr = startNodeY + (me.getSceneY() - startDragY);
				double mxDiff = dock.getWidth() - getWidth();
				double myDiff = dock.getHeight() - getHeight();
				xTr = xTr < 0 ? 0 : (xTr > mxDiff ? mxDiff : xTr);
				yTr = yTr < 0 ? 0 : (yTr > myDiff ? myDiff : yTr);

				setTranslateX(xTr < 0 ? 0 : xTr);
				setTranslateY(yTr);
			}
		});
	}
}
