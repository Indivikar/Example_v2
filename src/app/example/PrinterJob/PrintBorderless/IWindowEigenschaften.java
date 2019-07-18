package app.example.PrinterJob.PrintBorderless;

import app.Start;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public interface IWindowEigenschaften {

	public default void setOpenWindowInWindowCenter(Stage stageMain, Stage stage) {
				
		
			Task<Void> task = new Task<Void>() {
				@Override protected Void call() throws Exception {
					
					// so lange warten, bis die Child-Stage angezeigt wird, wenn nicht gewartet wird, dann gibt es noch keine werte
					while (!stage.isShowing()) {					
						Thread.sleep(250);
					}
					
					double[] array = new double[2];
					
					double stageMainPosX = stageMain.getX();
					double stageMainPosY = stageMain.getY();
					double stageMainWidth = stageMain.getWidth();
					double stageMainHeight = stageMain.getHeight();
					
					Stage stageChild = (Stage) stage.getScene().getWindow();
					double stageChildWidth = stageChild.getWidth();
					double stageChildHeight = stageChild.getHeight();
		
					double posX = (stageMainWidth / 2) - (stageChildWidth / 2) +  stageMainPosX;
					double posY = (stageMainHeight / 2) - (stageChildHeight / 2) +  stageMainPosY;
//					System.out.println(halbStageMainWidth + " + " + halbStageChildWidth + " + " + stageMainPosX + " = " + posX);
//					System.out.println(stageMainHeight / 2 + " + " + stageChildHeight / 2 + " + " + stageMainPosY + " = " + posY);

					// PosX
					array[0] = posX;
					// PosY
					array[1] = posY;
					
					stage.setX(posX);
					stage.setY(posY);
					
					return null;
				}
			};
			
		Platform.runLater(() -> {
			Thread th = new Thread(task);
			th.start();
		});
		
	}

	public default void setSceneEigenschaften(Scene scene) {
//		scene.getStylesheets().add(StartMain.class.getResource("/app/view/css/MainStage.css").toExternalForm());
		scene.getStylesheets().add(Start.class.getResource("/app/style/MainStyle.css").toExternalForm());
		scene.getStylesheets().add(Start.class.getResource("/app/font/lucist__.ttf").toExternalForm());
		scene.setFill(Color.TRANSPARENT); // Fill our scene with nothing
	}
	
}
