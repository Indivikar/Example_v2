package app.example.Ping.SendPing;

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

public class PingDemo {
//	public static void main(String[] args) throws UnknownHostException, IOException {
//	    InetAddress inet;
//
//	    
//	    
//	    Process runTime =  Runtime.getRuntime().exec(new String[]{"cmd","/c","start"});
//	    OutputStream out = runTime.getOutputStream();
//	    Scanner myObj = new Scanner((Readable) System.out);
//	    runTime.
//	    		
//	    out.write("input text".);
//	    out.close();
//	    
//	    inet = InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 });
//	    System.out.println("Sending Ping Request to " + inet);
//	    System.out.println(inet.isReachable(5000) ? "Host ist erreichbar" : "Host ist nicht erreichbar");
//
//	    inet = InetAddress.getByAddress(new byte[] { (byte) 173, (byte) 194, 32, 38 });
//	    System.out.println("Sending Ping Request to " + inet);
//	    String string =  inet.isReachable(5000) ? "Host ist erreichbar" : "Host ist nicht erreichbar";
//	    System.out.println(inet.isReachable(5000) ? "Host ist erreichbar" : "Host ist nicht erreichbar");
//	    
//	    
//	    
////	    runTime..exec(new String[]{"test"});   
//	}
	
	 public static void main (String [] args) throws IOException, InterruptedException, URISyntaxException{
	        Console console = System.console();
	        if(console == null && !GraphicsEnvironment.isHeadless()){
	            String filename = PingDemo.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
	            Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
	            System.out.println("Test");
//	            console.writer().print("test 1");
	            Console.classwriteLine("I use Console.writeLine to print text. If you can read this, it works!!");
	        } else {
console.writer().print("test 2");
	            System.out.println("Program has ended, please type 'exit' to close the console");
	        }
	    }
	
//	public static void main(String[] args) throws Exception {
//	    String[] processArgs = new String[]{"ping","google.com"};
//	    Process process = new ProcessBuilder(processArgs).start();
//
//	    BufferedReader in = new BufferedReader(new InputStreamReader(
//	            //I'am using Win7 with PL encoding in console -> "CP852"
//	            process.getInputStream(), "CP852"));
//
//	    String line;
//	    while ((line = in.readLine()) != null)
//	        System.out.println(line);
//
//	    in.close();
//	    System.out.println("process ended");
//	}
	
}
