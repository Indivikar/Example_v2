package app.example.PDF.SimplePDFViewer;

import java.io.File;
import java.util.List;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jpedal.PdfDecoderFX;
import org.jpedal.examples.viewer.gui.javafx.FXViewerTransitions;
import org.jpedal.examples.viewer.gui.javafx.FXViewerTransitions.TransitionDirection;
import org.jpedal.examples.viewer.gui.javafx.FXViewerTransitions.TransitionType;
import org.jpedal.examples.viewer.gui.javafx.dialog.FXInputDialog;
import org.jpedal.exception.PdfException;
import org.jpedal.objects.PdfPageData;
import org.jpedal.parser.DecoderOptions;

public class SimplePDFViewerFXDemo extends Application {

	private Stage stage;
	private Scene scene;
	private AnchorPane root;
	private Group group;
		
	String cssLayout = "-fx-border-color: red;\n" +
            "-fx-border-insets: 5;\n" +
            "-fx-border-width: 1;\n" +
            "-fx-border-style: dashed;\n";
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		this.root = new AnchorPane();
		
		this.group = new Group();
		group.setAutoSizeChildren(true);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPannable(true);
		AnchorPane.setTopAnchor(scrollPane, 10.0);
		AnchorPane.setLeftAnchor(scrollPane, 10.0);
		AnchorPane.setRightAnchor(scrollPane, 10.0);
		AnchorPane.setBottomAnchor(scrollPane, 10.0);
		
		VBox vBox = new VBox();
		vBox.setFillWidth(true);
		vBox.setAlignment(Pos.CENTER);
		vBox.setStyle(cssLayout);

		vBox.getChildren().addAll(new Label("Drag N Drop PDF-File"), group);
		scrollPane.setContent(vBox);
		root.getChildren().addAll(scrollPane);
        this.scene = new Scene(root, 800, 600);
                
        PDFRenderer pdfRenderer = new PDFRenderer(this);

        stage.setTitle("Simple PDF-ViewerFX Demo"); 
        stage.setScene(scene); 
        stage.sizeToScene(); 
        stage.show();		
	}

	// Getter
	public Stage getStage() {return stage;}
	public Scene getScene() {return scene;}
	public AnchorPane getRoot() {return root;}
	public Group getGroup() {return group;}
	
    public static void main(String[] args) {
        Application.launch(args);
    }


 
}
