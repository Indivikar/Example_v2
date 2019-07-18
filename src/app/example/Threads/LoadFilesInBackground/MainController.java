package app.example.Threads.LoadFilesInBackground;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class MainController implements Initializable {

	@FXML private Button button;
	@FXML private ProgressBar bar1;
	@FXML private ProgressBar bar2;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Load a big File or something else, the new Stage will not blocked
		loadAppConfigurationFile();
		loadOtherFile();

		button.setOnAction(e -> {
			System.out.println("Hello World!");
		});
		
	}

	
	 private void loadAppConfigurationFile() {
		 
	        Task<Void> task = new Task<Void>() {
	            @Override
	            public Void call() throws InterruptedException {
	                int max = 10000000;
	                for (int i = 1; i <= max; i = i + 10) {
	                    if (isCancelled()) {
	                        break;
	                    }
	                    updateProgress(i, max);
	                    System.out.println("loadAppConfigurationFile() -> " + i);
	                }
	                return null;
	            }
	        };
	        bar1.progressProperty().bind(task.progressProperty());
	        new Thread(task).start();

	        task.setOnSucceeded(e -> {
	 		  System.out.println("loadAppConfigurationFile() Finished");
	        });
	        
	 }
	
  	private void loadOtherFile() {
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                int max = 1000000;
                for (int i = 1; i <= max; i = i + 10) {
                    if (isCancelled()) {
                        break;
                    }
                    updateProgress(i, max);
                    System.out.println("loadOtherFile() -> " + i);
                }
                return null;
            }
        };
        bar2.progressProperty().bind(task.progressProperty());
        new Thread(task).start();

        task.setOnSucceeded(e -> {
 		  System.out.println("loadOtherFile() Finished");
        });
 	     

		
	}
	
}
