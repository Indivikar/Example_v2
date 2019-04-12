package app.example.ListView.ListViewWithObject;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SimpleListViewDemo extends Application {

	private Stage stage;
	private Scene scene;
	private TilePane root;

	public SimpleListViewDemo() {
		Platform.runLater(() -> {           
	        try {
				start(new Stage());
			} catch (Exception e) {
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
		configureHeaderListView();
	}

	private void configureHeaderListView() {
		ObservableList<ListData> list = getListData();
		ListView<ListData> listView = new ListView<ListData>();
		listView.setItems(list);
		listView.setCellFactory(new Callback<ListView<ListData>, ListCell<ListData>>() {
			@Override
			public ListCell<ListData> call(ListView<ListData> arg0) {
				return new ListCell<ListData>() {
					@Override
					public void updateItem(ListData item, boolean empty) {
						super.updateItem(item, empty);
						if (!isEmpty()) {
							
							Label labelCode = new Label(item.getCode());							
							Label labelDesc = new Label(item.getDesc());
							
							AnchorPane ap = new AnchorPane();
							ap.getChildren().addAll(labelCode, labelDesc);

							AnchorPane.setLeftAnchor(labelDesc, 50.0);
							
							setGraphic(ap);
						}
					}
				};
			}
		});
		
		listView.setPrefWidth(300);
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ListData>() {
			@Override
			public void changed(ObservableValue<? extends ListData> paramObservableValue, ListData paramT1, ListData paramT2) {
				System.out.println("----------------------------> " + paramT2.getCode());
			}
		});
		root.getChildren().addAll(listView);
	}

	private ObservableList<ListData> getListData() {
		List<ListData> list = new ArrayList<ListData>();
		list.add(new ListData("AA", "Apple"));
		list.add(new ListData("B", "Bread"));
		list.add(new ListData("C", "Cool Drink"));
		list.add(new ListData("D", "Drainage"));
		list.add(new ListData("E", "Encyclopedia"));
		list.add(new ListData("FF", "Father in law"));
		list.add(new ListData("G", "Goddess"));
		list.add(new ListData("H", "Helicopter"));
		list.add(new ListData("I", "IreLand"));
		list.add(new ListData("J", "Jack n Jill"));
		list.add(new ListData("K", "Kingston"));
		list.add(new ListData("L", "Lantern"));
		list.add(new ListData("M", "Monkey"));
		list.add(new ListData("N", "Nasiruddin Shah"));
		list.add(new ListData("OO", "Omani"));
		list.add(new ListData("P", "Pandi"));
		list.add(new ListData("Q", "Queen"));
		list.add(new ListData("R", "Rasberrry"));
		list.add(new ListData("S", "Satthi pandu"));
		list.add(new ListData("T", "Telangana"));
		list.add(new ListData("U", "Uranium"));
		list.add(new ListData("V", "Very good"));
		list.add(new ListData("W", "Wat is that"));
		list.add(new ListData("X", "Xenon rays"));
		list.add(new ListData("Y", "Yatch"));
		list.add(new ListData("Z", "Zindagi na milegi"));

		ObservableList<ListData> olist = FXCollections.observableArrayList();
		olist.addAll(list);
		return olist;
	}

	private void configureStage() {
		stage.setTitle("List View Demo");
		stage.setWidth(600);
		stage.setHeight(600);
		stage.setScene(this.scene);
		stage.show();
	}

	private void configureScene() {
		root = new TilePane();
		root.autosize();
		this.scene = new Scene(root, Color.LINEN);
	}
}

class ListData {
	private String code;
	private String desc;

	public ListData(String code, String desc) {
		super();
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
