package app.example.Ping.SendPingInConsole;

import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.stage.Stage;

public class PingDemo {

	public PingDemo() {
		Platform.runLater(() -> {           
			ping();	       
		});
	}
	
	public static void main(String[] args) throws Exception {
		ping();
	}
	
	private static void ping() {
		try {
		    String[] processArgs = new String[]{"ping","google.com"};
		    Process process = new ProcessBuilder(processArgs).start();
	
		    BufferedReader in = new BufferedReader(new InputStreamReader(
		            //I'am using Win7 with PL encoding in console -> "CP852"
		            process.getInputStream(), "CP852"));
	
		    String line;
		    while ((line = in.readLine()) != null)
		        System.out.println(line);
	
		    in.close();
		    System.out.println("process ended");
	    } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
