package app.view.function;

import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.action.Action;

import app.Start;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MyNotificationPane {

	public enum MyNotificationPaneGraphic {
		NULL, PEN, GEAR
	}
	private NotificationPane notificationPane; 
	
	private Image PEN_GRAPHIC = new Image(Start.class.getResourceAsStream("view/images/edit_32px.png")); 
	private Image GEAR_GRAPHIC = new Image(Start.class.getResourceAsStream("view/images/edit_32px.png"));
	
	public void create(Stage stage, boolean showFromTop, MyNotificationPaneGraphic graphic, String text) {
		
	    Scene scene = stage.getScene();
	    Parent pane = scene.getRoot();

//	    if (!(pane instanceof NotificationPane)){
			this.notificationPane = new NotificationPane(pane);
			
			notificationPane.setGraphic(getGraphic(graphic));
			notificationPane.setShowFromTop(showFromTop);
			notificationPane.setCloseButtonVisible(true);
			notificationPane.getStyleClass().add(NotificationPane.STYLE_CLASS_DARK);
			notificationPane.setText(text);
			
	        scene = new Scene(notificationPane, scene.getWidth(), scene.getHeight());
	        stage.setScene(scene);
	        

			
	//		notificationPane.getActions().addAll(new Action("Sync", ae -> {
	//             // do sync           
	//             // then hide...
	//             notificationPane.hide();
	//		}));
	        System.out.println(1);
			notificationPane.show();
//	    } else {
//	    	System.out.println(2);
//	        ((NotificationPane)pane).show();
//	    }
	}
	
	private ImageView getGraphic(MyNotificationPaneGraphic graphic) {
		ImageView image = null;
	    switch (graphic) {
	        case PEN:  image = new ImageView(PEN_GRAPHIC); break;
	        case GEAR: image = new ImageView(GEAR_GRAPHIC); break;
	        default: image = null; 
	    }
	    
		return image;
	}
	
	
}
