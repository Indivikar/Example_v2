package app.example.Ping.PingTimeoutsSpeichern.controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.example.Ping.PingTimeoutsSpeichern.Demo;
import app.example.Ping.PingTimeoutsSpeichern.model.PfadErmitteln;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainWindowController {

	// Config
//	int timeOutNachWievielMS = 5000;

	MainWindowController mainWindowController;

	// Stage
	private Demo main;
	private Stage primaryStage;

	// Models


	// View's
	@FXML private AnchorPane mainAnchorPane;
	@FXML private ComboBox<String> comboBoxIPAdresse;
	@FXML private ComboBox<String> comboBoxTimeOutsInMS;
	@FXML private Button buttonPingSenden;
	@FXML private Button buttonPingStop;
	@FXML private Button buttonLogsOpen;

	@FXML private ListView<String> listViewPings;
	@FXML private ListView<String> listViewTimeOuts;

	
	private ObservableList<String> listeIPs = FXCollections.observableArrayList();
	private ObservableList<String> listeTimeOutsInMS = FXCollections.observableArrayList("1000", "2000", "3000", "4000", "5000", "6000", "7000", "8000", "9000", "10000");
	
	private ObservableList<String> listePing = FXCollections.observableArrayList();
	private ObservableList<String> listeTimeOuts = FXCollections.observableArrayList();

	long msSave;

	private SimpleBooleanProperty startePing = new SimpleBooleanProperty(false);


	// Action's
	@FXML public void actionButtonPingSenden(){
        if (!friendFinder.isRunning()) {
            friendFinder.reset();
            friendFinder.start();
          }
	}

	@FXML public void actionButtonPingStop(){
		friendFinder.cancel();

	}

	@FXML public void actionButtonLogsOpen(){

		PfadErmitteln pe = new PfadErmitteln(new String[]{}, false, false);
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().open(pe.getErmittelterFile());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("java.awt.Desktop wird nicht Supported");
		}


	}

	public void initialize(){
		startePing.addListener(changeStartePing);


		buttonPingSenden.disableProperty().bind(friendFinder.runningProperty());
		buttonPingStop.disableProperty().bind(friendFinder.runningProperty().not());

		comboBoxIPAdresse.setItems(listeIPs);
		comboBoxTimeOutsInMS.setItems(listeTimeOutsInMS);
		
		listViewPings.setItems(listePing);
		listViewTimeOuts.setItems(listeTimeOuts);

		addAutoScroll(listViewPings);
		addAutoScroll(listViewTimeOuts);
		
		erstelleIPFile();
		leseIPs();
		
	}

	
	
	private void schreibeInLogFile(String text) {
		
		String dateiName = "log-" + getSystemDatum() + ".txt";		
		PfadErmitteln pe = new PfadErmitteln(new String[]{dateiName}, false, false);
		
		if(!pe.getErmittelterFile().exists()){
			erstelleLogFile(pe.getErmittelterFile());
		}
		
		text = "\r\n" +  text;
		
		try {
		    Files.write(pe.getErmittelterPath(), text.getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}
	


    private void erstelleLogFile(File file){
        try {
            FileWriter fileWriter = null;
            
            fileWriter = new FileWriter(file);           
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void erstelleIPFile(){
    	PfadErmitteln pe = new PfadErmitteln(new String[]{"IPs.txt"}, false, false);
    	
    	if (!pe.getErmittelterFile().exists()) {
			try {
	            FileWriter fileWriter = null;
	            
	            fileWriter = new FileWriter(pe.getErmittelterFile());  
	            fileWriter.write("127.0.0.1\r\n" + "192.168.0.1\r\n" + "192.168.1.1\r\n" + "62.26.26.62");
	            fileWriter.close();
	        } catch (IOException ex) {
	            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
	        }
		}
    }
    
    private void leseIPs(){
    	PfadErmitteln pe = new PfadErmitteln(new String[]{"IPs.txt"}, false, false);
    	
    	try (BufferedReader br = new BufferedReader(new FileReader(pe.getErmittelterFile()))) {
    	    String line;
    	    while ((line = br.readLine()) != null) {
    	       listeIPs.add(line);
    	    }
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
    public final Service<Boolean> friendFinder = new Service<Boolean>() {
        @Override protected Task<Boolean> createTask() {
          return new Task<Boolean>() {
            @Override protected Boolean call() throws InterruptedException, IOException {
//              updateMessage("Finding friends . . .");
//              updateProgress(0, 10);
              while (true) {
                Thread.sleep(1000);
//                System.out.println("Thread läuft");

                
                String itemMS = comboBoxTimeOutsInMS.getSelectionModel().getSelectedItem();
                int milliSec = Integer.parseInt(itemMS);
                String itemIP = comboBoxIPAdresse.getSelectionModel().getSelectedItem();
                String[] part = itemIP.split("\\.");
                int part1 = Integer.parseInt(part[0]);
                int part2 = Integer.parseInt(part[1]);
                int part3 = Integer.parseInt(part[2]);
                int part4 = Integer.parseInt(part[3]);

                InetAddress inet = InetAddress.getByAddress(new byte[] { (byte) part1, (byte) part2, (byte) part3, (byte) part4 });
//        	    System.out.println("Sending Ping Request to " + inet);
//        	    System.out.println(inet.isReachable(5000) ? "Host ist erreichbar" : "Host ist nicht erreichbar");
                msSave = System.currentTimeMillis();
        	    if (inet.isReachable(milliSec)) {
        	    	long ms = System.currentTimeMillis() - msSave;


        	    	Platform.runLater(() -> {
        	    		listePing.add("ms " + ms + "  |  " + getSystemUhrzeitUndDatum() + "  " + removeSlash(inet));
        	    		if(listePing.size() > 60){
        	    			listePing.remove(0);
        	    		}

        	    	});
				} else {
        	    	long ms = System.currentTimeMillis() - msSave;
//        	    	System.out.println("ms: " + ms);
        	    	Platform.runLater(() -> {
        	    		listeTimeOuts.add("TimeOut nach " + milliSec + " ms  |  " + getSystemUhrzeitUndDatum() + "  " + removeSlash(inet));
        	    		schreibeInLogFile("TimeOut nach " + milliSec + " ms  |  " + getSystemUhrzeitUndDatum() + "  " + removeSlash(inet));
        	    		if(listeTimeOuts.size() > 60){
        	    			listeTimeOuts.remove(0);
        	    		}

        	    	});
				}
              }

            }
          };
        }
  };

  private String removeSlash(InetAddress inet) {
	  return inet.toString().replace("/", "|  ");
  }

  public static <S> void addAutoScroll(final ListView<S> view) {
	    if (view == null) {
	        throw new NullPointerException();
	    }

	    view.getItems().addListener((ListChangeListener<S>) (c -> {
	        c.next();
	        final int size = view.getItems().size();
	        if (size > 0) {
	            view.scrollTo(size - 1);
	        }
	    }));
	}

  private String getSystemUhrzeitUndDatum() {

      Calendar cal = Calendar.getInstance();
      int second = cal.get(Calendar.SECOND);
      int minute = cal.get(Calendar.MINUTE);
      int hour = cal.get(Calendar.HOUR_OF_DAY);

      int day = cal.get(Calendar.DAY_OF_MONTH);
      int month = cal.get(Calendar.MONTH) + 1;
      int year = cal.get(Calendar.YEAR);

      String h = nullAdden(hour);
      String min = nullAdden(minute);
      String sec = nullAdden(second);

      String d = nullAdden(day);
      String m = nullAdden(month);
      String y = nullAdden(year);

      return h + ":" + min + ":" + sec + " Uhr - " + d + "." + m + "." + y;

  }

  private String getSystemDatum() {

      Calendar cal = Calendar.getInstance();
      int second = cal.get(Calendar.SECOND);
      int minute = cal.get(Calendar.MINUTE);
      int hour = cal.get(Calendar.HOUR_OF_DAY);

      int day = cal.get(Calendar.DAY_OF_MONTH);
      int month = cal.get(Calendar.MONTH) + 1;
      int year = cal.get(Calendar.YEAR);

      String h = nullAdden(hour);
      String min = nullAdden(minute);
      String sec = nullAdden(second);

      String d = nullAdden(day);
      String m = nullAdden(month);
      String y = nullAdden(year);

      return y + "-" + m + "-" + d;

  }
  
  private String nullAdden(int alteWert) {

	  if(alteWert <= 9){
		  return "0" + alteWert;
	  }

	return alteWert + "";
  }

  final ChangeListener<Boolean> changeStartePing = new ChangeListener<Boolean>() {
		@Override
		public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

		}
  };



	// Getter


    // Setter
	public void setMainWindowStage(Demo systemTrayIcon, Stage primaryStage){
		this.main = systemTrayIcon;
		this.primaryStage = primaryStage;



	}

}
