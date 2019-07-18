package app.example.PrinterJob.PrintBorderless;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.pdfbox.pdmodel.PDDocument;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;


public class CAlertPrintProgress implements Initializable {

	private PrinterDemo printerDemo;
	private AlertPrintProgress alertPrintProgress;

	private PrinterJob printerJob;
	private File filePDF;
	private PDDocument document;
	private int anzahlPDFSeiten;
	
	@FXML private Label labelHeader;
	@FXML private ProgressBar progressBar;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}


	// Getter
	public PrinterJob getPrinterJob() {return printerJob;}
	public ProgressBar getProgressBar() {return progressBar;}
//	public int getAnzahlPDFSeiten() {return anzahlPDFSeiten;}
	

	public void set(PrinterDemo printerDemo,  AlertPrintProgress alertPrintProgress, String title) {
		this.printerDemo = printerDemo;
		this.alertPrintProgress = alertPrintProgress;
		
		labelHeader.setText(title);
	}




}
