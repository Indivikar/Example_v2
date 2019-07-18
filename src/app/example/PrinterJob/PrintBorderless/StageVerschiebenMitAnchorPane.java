package app.example.PrinterJob.PrintBorderless;

import java.awt.MouseInfo;
import java.awt.Point;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StageVerschiebenMitAnchorPane implements IWindowMax {

	private double xOffset;
	private double yOffset;
	
	private Stage stage;
	private AnchorPane shadowPane;
	private AnchorPane mainAnchorPane;
	private Button buttonWindowMax;
	
	private boolean isStageWithBorder;
	private double paneWidth;
	private double paneHeight;
	
	private boolean verschieben;
	

	public StageVerschiebenMitAnchorPane(AnchorPane shadowPane, DialogPane dialogPane, Button buttonWindowMax, Stage stage, boolean isStageWithBorder) {	
		this.stage = stage;
		this.isStageWithBorder = isStageWithBorder;
		this.paneWidth = dialogPane.getWidth();
		this.paneHeight = dialogPane.getHeight();
		
		dialogPane.setOnMousePressed(mousePressedEvent);
		dialogPane.setOnMouseDragged(mouseDraggedEvent);
		dialogPane.setOnMouseReleased(mouseReleasedEvent);
    }
    
	public StageVerschiebenMitAnchorPane(AnchorPane shadowPane, AnchorPane pane, Button buttonWindowMax, Stage stage, boolean isStageWithBorder) {
		this.stage = stage;
		this.mainAnchorPane = pane;
		this.shadowPane = pane;
		this.buttonWindowMax = buttonWindowMax;
		this.isStageWithBorder = isStageWithBorder;
		this.paneWidth = pane.getWidth();
		this.paneHeight = pane.getHeight();
				
		pane.setOnMousePressed(mousePressedEvent);
		pane.setOnMouseDragged(mouseDraggedEvent);
		pane.setOnMouseReleased(mouseReleasedEvent);
	}

	
	EventHandler<MouseEvent> mousePressedEvent = new EventHandler<MouseEvent>() {
		@Override
        public void handle(MouseEvent event) {
        	System.out.println("setOnMousePressed");

        	if (!istDasDerResizeBereich(stage, event)) {

        		verschieben = true;

				if(isStageWithBorder){
	            	xOffset = event.getSceneX() + 8; // + 8 ist der Rand von der Stage
	                yOffset = event.getSceneY() + 30; // + 30 ist der Rand von der Stage
            	} else {
	            	xOffset = event.getSceneX();
	                yOffset = event.getSceneY();
            	}
			} else {
				verschieben = false;
			}

        	System.out.println("xOffset: " + xOffset + " | yOffset: " + yOffset );
        }
    };
	
    EventHandler<MouseEvent> mouseDraggedEvent = new EventHandler<MouseEvent>() {
		@Override
        public void handle(MouseEvent event) {
			if (verschieben) {
//				System.out.println("setOnMouseDragged");


				// wenn das Fenster verschoben wird und es noch max. ist, dann erst das Fenster min. und den Schatten wieder einblenden
            	if(stage.isMaximized()){
	            	double x = event.getScreenX() - xOffset;
	            	double y = event.getScreenY() - yOffset;

	            	// Mouse Position
	            	Point p = MouseInfo.getPointerInfo().getLocation();
	            	int xMouse = p.x;
	            	int yMouse = p.y;

	            		            	
	            	if (mainAnchorPane != null) {
	            		paneWidth = mainAnchorPane.getWidth();
	            		paneHeight = mainAnchorPane.getHeight();	            		
	            	}
	            	
	            	// an welcher Y-Position im Fenster ist die Mouse (in %)
	            	double mousePosYInProzent = yMouse / paneHeight;

	            	// das Fenster wieder minimieren
	            	if (mainAnchorPane != null) {	            		
						setWindowMaxIcon(stage, buttonWindowMax, shadowPane, mainAnchorPane);
	            		paneWidth = mainAnchorPane.getWidth();
	            		paneHeight = mainAnchorPane.getHeight();
	            			            		
	            		double neueFensterPosX = 0;
	            		double neueFensterPosY = 0;
	            		
	            		// setze das Fenster auf die Mouse Pos. in X
            			neueFensterPosX = xMouse - paneWidth;
            			stage.setX(neueFensterPosX);
            			// setzte xOffset neu, ab da wird mit dem neuen wert weiter gerechnet            			
            			xOffset = paneWidth / 2;
            			
            			// setze das Fenster auf die Mouse Pos. in Y
            			neueFensterPosY = yMouse - (paneHeight * mousePosYInProzent);
            			stage.setY(neueFensterPosY);
            			// setzte yOffset neu, ab da wird mit dem neuen wert weiter gerechnet
            			System.out.println("paneHeight: " + paneHeight + " | neueFensterPosY: " + neueFensterPosY );
            			yOffset = 30;
	            		
					}
            		
//            		double neueFensterPosX = 0;
//            		double neueFensterPosY = 0;
//
//            		// wenn die Mouse auf der X-Achse, nach dem minimieren nicht mehr im Fenster ist, dann setze das Fenster auf die Mouse Pos. in X
//            		if(xMouse >= (x + paneWidth)){
//            			System.out.println(1);
//            			neueFensterPosX = xMouse - paneWidth;
//            			stage.setX(neueFensterPosX);
//            			// setzte xOffset neu, ab da wird mit dem neuen wert weiter gerechnet
//            			
//            			xOffset = paneWidth / 2;
//            		}
//
//            		// wenn die Mouse auf der Y-Achse, nach dem minimieren nicht mehr im Fenster ist, dann setze das Fenster auf die Mouse Pos. in Y
//            		if(yMouse > (y + paneHeight)){
//            			System.out.println(2);
//            			
//            			neueFensterPosY = yMouse - (paneHeight * mousePosYInProzent);
//            			stage.setY(neueFensterPosY);
//            			// setzte yOffset neu, ab da wird mit dem neuen wert weiter gerechnet
//            			
//            			yOffset = paneHeight - (neueFensterPosY / 2);
//            		}

            	} else {
//            		System.out.println("event.getScreenY(): " + event.getScreenY() + " - yOffset: " + yOffset );
	            	double x = event.getScreenX() - xOffset;
	            	double y = event.getScreenY() - yOffset;

//	            	System.out.println("stage.setX(x): " + x + " | stage.setY(y): " + y );
	            	
	            	stage.setX(x);
	            	stage.setY(y);
            	}
			}
        }
    };
    
//    EventHandler<MouseEvent> mouseDraggedEvent = new EventHandler<MouseEvent>() {
//		@Override
//        public void handle(MouseEvent event) {
//			if (verschieben) {
////				System.out.println("setOnMouseDragged");
//
//
//				// wenn das Fenster verschoben wird und es noch max. ist, dann erst das Fenster min. und den Schatten wieder einblenden
//            	if(stage.isMaximized()){
//	            	double x = event.getScreenX() - xOffset;
//	            	double y = event.getScreenY() - yOffset;
//
//	            	// Mouse Position
//	            	Point p = MouseInfo.getPointerInfo().getLocation();
//	            	int xMouse = p.x;
//	            	int yMouse = p.y;
//
//	            	// an welcher Y-Position im Fenster ist die Mouse (in %)
//	            	double mousePosYInProzent = yMouse / mainAnchorPane.getHeight();
//
//	            	// das Fenster wieder minimieren
//            		setWindowMaxIcon(stage, buttonWindowMax, shadowPane, mainAnchorPane);
//
//            		double neueFensterPosX = 0;
//            		double neueFensterPosY = 0;
//
//            		// wenn die Mouse auf der X-Achse, nach dem minimieren nicht mehr im Fenster ist, dann setze das Fenster auf die Mouse Pos. in X
//            		if(xMouse > (x + mainAnchorPane.getWidth())){
//
//            			neueFensterPosX = xMouse - mainAnchorPane.getWidth();
//            			stage.setX(neueFensterPosX);
//            			// setzte xOffset neu, ab da wird mit dem neuen wert weiter gerechnet
//            			xOffset = mainAnchorPane.getWidth() -100;
//            		}
//
//            		// wenn die Mouse auf der Y-Achse, nach dem minimieren nicht mehr im Fenster ist, dann setze das Fenster auf die Mouse Pos. in Y
//            		if(yMouse > (y + mainAnchorPane.getHeight())){
//
//            			neueFensterPosY = yMouse - (mainAnchorPane.getHeight() * mousePosYInProzent);
//            			stage.setY(neueFensterPosY);
//            			// setzte yOffset neu, ab da wird mit dem neuen wert weiter gerechnet
//            			yOffset = mainAnchorPane.getHeight() - (neueFensterPosY / 2);
//            		}
//
//            	} else {
//	            	double x = event.getScreenX() - xOffset;
//	            	double y = event.getScreenY() - yOffset;
//
//	            	stage.setX(x);
//	            	stage.setY(y);
//            	}
//			}
//        }
//    };
    
    
    EventHandler<MouseEvent> mouseReleasedEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
        	if (verschieben) {
            	if(event.getClickCount() == 1){
            		System.out.println("setOnMouseReleased");
                	double x = event.getScreenX() - xOffset;
                	double y = event.getScreenY() - yOffset;

            	}
        	}
        }
    };
    
	private boolean istDasDerResizeBereich(Stage stage, MouseEvent event) {

	    System.out.println(event.getScreenX());
	    System.out.println(event.getScreenY());

        double 	mouseEventX = event.getSceneX(),
                mouseEventY = event.getSceneY(),
                sceneWidth = stage.getScene().getWidth(),
                sceneHeight = stage.getScene().getHeight();

        int border = ResizeHelper.ResizeListener.border;


        if (mouseEventX < border && mouseEventY < border) {
        	return true;
        } else if (mouseEventX < border && mouseEventY > sceneHeight - border) {
        	return true;
        } else if (mouseEventX > sceneWidth - border && mouseEventY < border) {
        	return true;
        } else if (mouseEventX > sceneWidth - border && mouseEventY > sceneHeight - border) {
        	return true;
        } else if (mouseEventX < border) {
        	return true;
        } else if (mouseEventX > sceneWidth - border) {
        	return true;
        } else if (mouseEventY < border) {
        	return true;
        } else if (mouseEventY > sceneHeight - border) {
        	return true;
        } else {
        	return false;
        }

	}

}
