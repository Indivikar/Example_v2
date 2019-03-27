package app.view.function;


import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public interface IWindowMax {

	
	public default void setWindowMaxIcon(Stage stage, Button buttonWindowMax, AnchorPane mainAnchorPane, boolean hasShadowPane) {
		setWindowMaxIcon(stage, buttonWindowMax, null, mainAnchorPane, hasShadowPane);
	}

	public default void setWindowMaxIcon(Stage stage, Button buttonWindowMax, AnchorPane shadowPane, AnchorPane mainAnchorPane, boolean hasShadowPane) {
		
		stage.setMaximized(!stage.isMaximized());
		
		if(stage.isMaximized()){
//			buttonWindowMax.setStyle("-fx-graphic: url('/application/module/modulPR/view/images/Button-Max-true.png')");
			buttonWindowMax.setStyle("-fx-graphic: url('/app/view/images/windowButtons/Button-Max-true.png')");
		} else {
//			buttonWindowMax.setStyle("-fx-graphic: url('/application/module/modulPR/view/images/Button-Max-false.png')");
			buttonWindowMax.setStyle("-fx-graphic: url('/app/view/images/windowButtons/Button-Max-false.png')");
		}

		// entferne die shadowPane, wenn Fenster max. ist
		if (shadowPane == null) {
			return;
		}
		
		if (hasShadowPane) {
			if(stage.isMaximized()){
				shadowPane.setStyle("-fx-border-insets: 0; -fx-background-insets: 0;");
	//			ResizeHelper.removeResizeListener(stage, mainAnchorPane);
			} else {
				shadowPane.setStyle("-fx-border-insets: 23; -fx-background-insets: 23;");
	//			ResizeHelper.addResizeListener(stage, mainAnchorPane);
			}
		}


	}

	
	public default void setWindowMaxMitDoppelKlick(Stage stage, Button buttonWindowMax, AnchorPane mainAnchorPane, boolean hasShadowPane) {
		setWindowMaxMitDoppelKlick(stage, buttonWindowMax, null, mainAnchorPane, hasShadowPane);
	}
	
	public default void setWindowMaxMitDoppelKlick(Stage stage, Button buttonWindowMax, AnchorPane shadowPane, AnchorPane mainAnchorPane, boolean hasShadowPane) {
		
		mainAnchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
		            if(mouseEvent.getClickCount() == 2){
		            	setWindowMaxIcon(stage, buttonWindowMax, shadowPane, mainAnchorPane, hasShadowPane);
		            }
		        }
		    }
		});
	}
	
}
