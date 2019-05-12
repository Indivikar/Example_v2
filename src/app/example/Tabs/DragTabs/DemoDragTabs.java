package app.example.Tabs.DragTabs;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.gillius.jfxutils.tab.TabUtil;

/**
 * Demonstrate draggable Tabs from one TabPane to another, allowing the user to rearrange their GUI layout.
 *
 * Note:  For this to work, instead of using new Tab("Label") you should use
 * <pre>
 * t = new Tab();
 * t.setGraphic(new Label("Label"));
 * </pre>
 *
 * @author gforman44
 */


public class DemoDragTabs extends Application {
	
	public DemoDragTabs() {
		Platform.runLater(() -> {
			try {
				start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	private static Tab makeTab( String tabName, String tabContentDummy ) {
		Tab t = TabUtil.newDraggableTab( tabName );
		t.setContent(new Label(tabContentDummy));
		return t;
	}

	@Override
	public void start(Stage primaryStage) {

		Tab f = makeTab("Files", "File system view here");
		Tab t = makeTab("Type Hierarchy","Type hierarchy view here");
		Tab d = makeTab("Debug","Debug view here");

		Tab p = makeTab("Properties","Ah, the ubiquitous 'properties' panel");
		Tab c = makeTab("Console","Console output here");
		Tab o = makeTab("Outline","Outline of fields/methods view here");

		TabPane left = new TabPane(f,t,d);
		TabUtil.makeDroppable(left); //////////////// see

		TabPane right = new TabPane(p,c,o);
		TabUtil.makeDroppable(right); /////////////// see

		left.setStyle("-fx-border-color: black;");
		right.setStyle("-fx-border-color: black;");

		BorderPane main = new BorderPane();
		main.setPadding(new Insets(0, 20, 0, 20));
		main.setTop(new Label("Menubar and toolbars"));
		main.setLeft(left);
		main.setCenter(new Label("Central work area here"));
		main.setRight(right);
		main.setBottom(new Label("Statusbar"));

		primaryStage.setScene(new Scene(main, 800, 600));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
