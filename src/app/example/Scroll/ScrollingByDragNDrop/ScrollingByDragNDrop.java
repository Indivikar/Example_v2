package app.example.Scroll.ScrollingByDragNDrop;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TreeView;
import javafx.scene.input.DragEvent;
import javafx.util.Duration;

public class ScrollingByDragNDrop {

    private Timeline scrolltimeline = new Timeline();
    private double scrollDirection = 0;
    private double scrollSpeed = 0.5;

	public ScrollingByDragNDrop(TreeView<String> tree) {
    	setupScrolling(tree);
	}

	private void setupScrolling(TreeView<String> tree) {
        scrolltimeline.setCycleCount(Timeline.INDEFINITE);
        scrolltimeline.getKeyFrames().add(new KeyFrame(Duration.millis(20), "Scoll", (ActionEvent) -> { dragScroll(tree);}));
	
	    tree.setOnDragExited(event -> {
	
	    	double contentHeight = tree.getHeight();
	    	double contentWidth = tree.getWidth();
	    	int countItems = tree.getExpandedItemCount();
	
	    	startScrolling(event, contentHeight, contentWidth, countItems);
	       	        
	    });
	     
	    stopScrollingEvent(tree);
	}

	private void startScrolling(DragEvent event, double contentHeight, double contentWidth, int countItems) {
		  
//      	System.out.println("event: event.getY() > 0 -> " + event.getY() + " -> " + (event.getY() < 0));
//      	System.out.println("event: event.getX() > 0 -> " + event.getX() + " -> " + (event.getX() > 0));
//      	System.out.println("event: event.getX() < treeWidth -> " + event.getX() + " < " + treeWidth + " -> " + (event.getX() < treeWidth));
		  
        if (event.getY() < 0 && event.getX() > 0 && event.getX() < contentWidth) {
          	System.err.println("startScrolling Up");
            scrollDirection = -scrollSpeed / countItems;
            scrolltimeline.play();
        } 
          
//		System.out.println("event: event.getY() >= treeHeight -> " + event.getY() + " >= " + contentHeight + " -> " + (event.getY() >= contentHeight));
//		System.out.println("event: event.getX() > 0 -> " + event.getX() + " -> " + (event.getX() > 0));
//		System.out.println("event: event.getX() < treeWidth -> " + event.getX() + " < " + contentWidth + " -> " + (event.getX() < contentWidth));
      	
        if (event.getY() > 0 && event.getY() >= contentHeight && event.getX() > 0 && event.getX() < contentWidth) {
          	System.err.println("startScrolling Down");
            scrollDirection = scrollSpeed / countItems;
            scrolltimeline.play();
        } 

	}
	  
	private void stopScrollingEvent(Node node) {
		
	    node.setOnDragEntered(event -> {
	    	System.err.println("Stop DragEntered");
	        scrolltimeline.stop();
	    });

	    node.setOnDragDone(event -> {
	    	System.err.println("Stop DragDone");
	        scrolltimeline.stop();
	    });  
	}
	  
	  
	public void stopScrolling() {
		scrolltimeline.stop();
	}
	  
	private void dragScroll(Node node) {
	    ScrollBar sb = getVerticalScrollbar(node);
	    if (sb != null) {
	        double newValue = sb.getValue() + scrollDirection;
	        newValue = Math.min(newValue, 1.0);
	        newValue = Math.max(newValue, 0.0);
	        sb.setValue(newValue);
	    }
	}
	    
	private ScrollBar getVerticalScrollbar(Node node) {
	    ScrollBar result = null;	     
	    for (Node n : node.lookupAll(".scroll-bar")) {
	        if (n instanceof ScrollBar) {
	            ScrollBar bar = (ScrollBar) n;
	            if (bar.getOrientation().equals(Orientation.VERTICAL)) {
	                result = bar;
	            }
	        }
	    }       
	    return result;
	}
	
}
