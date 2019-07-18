package app.example.PrinterJob.PrintBorderless;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Set;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PageRanges;
import javax.print.attribute.standard.Sides;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PageLayout;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.controlsfx.control.Notifications;

//import com.sun.pdfview.PDFFile;
//import com.sun.pdfview.PDFPage;

import app.Start;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class PrintPDF extends Task<Boolean>{

//	private Start start;
	private PrinterDemo printerDemo;
	private AlertPrintProgress alertPrintProgress;
	private CAlertPrintProgress cAlertPrintProgress;
	
	private Stage stage;
	private File pdfFile;

	private ProgressBar progressBar;
	
	int progressCounter = 0;
	
	private PDDocument document;
	private int documentPages;
	
	private PrinterJob printJob;
	private PDFRenderer pdfRenderer;
	private PageFormat pf;

	public PrintPDF(PrinterDemo printerDemo, File pdfFile) {
		this.printerDemo = printerDemo;
		this.pdfFile = pdfFile;

		try {		
			document = PDDocument.load(new File("H:\\Users\\santosh\\Documents\\A4-gross.pdf"));
			this.documentPages = document.getPages().getCount();
			
			pdfRenderer = new PDFRenderer(document);
			PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
			  	  
			int pageWidth = (int) document.getPage(0).getMediaBox().getWidth();
			int pageHeight = (int) document.getPage(0).getMediaBox().getHeight();
			
			printJob = PrinterJob.getPrinterJob();
			printJob.setPrintService(defaultService);
			pf = printJob.defaultPage();
			System.out.println("Printer Page width=" + pf.getWidth() + " height=" + pf.getHeight());
				      
			
			final Paper paper = new Paper();
			paper.setSize(pageWidth * 72.0f, pageHeight * 72.0f);
			paper.setImageableArea(0.0, 0.0, paper.getWidth(), paper.getHeight());
			pf.setPaper(paper);	
			
//		    if (printJob.printDialog() == false) {
//		  		Notifications.create()
//			        .title("Drucken")
//			        .text("Beim bearbeiten des Druckauftrags ist ein Fehler aufgetreten.")
//			        .showError();
//		  		  document.close();
//		    	  return; 
//		    } else {
//		    	  setAlertProgress();
//		    	  
//		    }
		    new Thread(this).start();
	    } catch (IOException | PrinterException e) {
	  		Notifications.create()
		        .title("Drucken")
		        .text("Beim bearbeiten des Druckauftrags ist ein Fehler aufgetreten.")
		        .showError();
	  		documentClose();
			e.printStackTrace();
		}
	      
	}
	
	@Override
	protected void cancelled() {
		alertPrintProgress.getAlertStage().close();
		Notifications.create()
	        .title("Drucken")
	        .text("Druckauftrag wurde abgebrochen.")
	        .showWarning();		
		documentClose();
	}
	
	@Override
	protected void failed() {
		alertPrintProgress.getAlertStage().close();
		Notifications.create()
	        .title("Drucken")
	        .text("Beim bearbeiten des Druckauftrags ist ein Fehler aufgetreten.")
	        .showError();
		documentClose();		
	}
	
	@Override
	protected void succeeded() {
		alertPrintProgress.getAlertStage().close();
		Notifications.create()
	         .title("Drucken")
	         .text("Druckauftrag wurde an Drucker gesendet.")
	         .showInformation();
		documentClose();
	}
	
	@Override
	protected Boolean call() throws Exception {
		
//		PDDocument document = PDDocument.load(new File("H:\\Users\\santosh\\Documents\\A4-gross.pdf"));
	    try {
//	       	PDFRenderer pdfRenderer = new PDFRenderer(document);
//	          PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
//	    	  
//	    	  int pageWidth = (int) document.getPage(0).getMediaBox().getWidth();
//	          int pageHeight = (int) document.getPage(0).getMediaBox().getHeight();
//
//	    	  final PrinterJob printJob = PrinterJob.getPrinterJob();
//		      printJob.setPrintService(defaultService);
//		      final PageFormat pf = printJob.defaultPage();
//		      System.out.println("Printer Page width=" + pf.getWidth() + " height=" + pf.getHeight());
//		      
//		      final Paper paper = new Paper();
//		      paper.setSize(pageWidth * 72.0f, pageHeight * 72.0f);
//		      paper.setImageableArea(0.0, 0.0, paper.getWidth(), paper.getHeight());
//		      pf.setPaper(paper);
	    	
//	    	PageRanges pageRangesAttr =  (PageRanges) printJob.getPrintService().getAttributes().get(PageRanges.class);
//	    	pageRangesAttr.getMembers()
		      printJob.setPrintable(new Printable() {
		    	  public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		    		  
		    	      Graphics2D g2 = (Graphics2D) graphics;
		    	      final double xScale = 1.0;
		    	      final double xTranslate = 0; // -55
		    	      final double yScale = 1;
		    	      final double yTranslate = 0;

		    	      
		    	      		    	      
		    	      if (pageIndex < documentPages) {
		    	    	  try {		

							  BufferedImage bim = pdfRenderer.renderImageWithDPI(pageIndex, 400, ImageType.RGB);
							  
				    	      final double widthScale = (pageFormat.getWidth() / bim.getWidth()) * xScale;
				    	      final double heightScale = (pageFormat.getHeight() / bim.getHeight()) * yScale;
				    	      		    	      
				    	      final AffineTransform at = AffineTransform.getScaleInstance(widthScale, heightScale);	    	      
				    	      at.translate(xTranslate, yTranslate);
				    	      
				    	      System.out.println("Setting scale to " + widthScale + "x" + heightScale);
				    	      System.out.println("Setting translate to " + xTranslate + "x" + yTranslate);
			
			    	    	  g2.drawRenderedImage(bim, at);			    	    	  
			    	    	  updateProgress(pageIndex +1, documentPages);
			   	    	  
		    	    	  } catch (IOException e) {
		    	    		  e.printStackTrace();
		    	    	  }
		    	    	  
		    	          return PAGE_EXISTS;
		    	      } else {
		    	    	  return NO_SUCH_PAGE;
		    	      }

		    	  }}, pf);
		
				  if (printJob.printDialog() == false) {
				  		Notifications.create()
					        .title("Drucken")
					        .text("Beim bearbeiten des Druckauftrags ist ein Fehler aufgetreten.")
					        .showError();
				  		  document.close();
				    	  return null; 
				  } 
				  
				  Platform.runLater(() -> {
					  setAlertProgress();
				  });	
				  
			      printJob.print(); 
			      
		    } catch (PrinterException pe) {
		    	documentClose();
		    	pe.printStackTrace();
		    }
		return null;
	}

	private void setAlertProgress() {
		this.alertPrintProgress = new AlertPrintProgress(printerDemo, "Druckauftrag wird erstellt");		
		this.cAlertPrintProgress = alertPrintProgress.getController();
		this.progressBar = cAlertPrintProgress.getProgressBar();
		
		progressBar.progressProperty().bind(this.progressProperty());
		
		alertPrintProgress.getButtonCancel().setOnAction(e -> {
			cancel();
		});
	}
	
	private void documentClose() {
		try {
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public PrintPDF(Stage stage, File pdfFile) {

	
//	private void print(PrinterJob job, File file) throws InvalidPasswordException, IOException {
//        // Set the Job Status Message
////		Platform.runLater(() -> {
////			label.textProperty().bind(job.jobStatusProperty().asString());
////		});
//	
//		
//		System.out.println(job.getPrinter());
//		if (job != null) {
//		    boolean success = true ;
//      
//		    	Printer printer = cAlertPrintSettings.getComboBoxPrinter().getSelectionModel().getSelectedItem();
//		        Paper paper = cAlertPrintSettings.getComboBoxPapierFormat().getSelectionModel().getSelectedItem();
//		        PageOrientation pageOrientation = cAlertPrintSettings.getComboBoxAusrichtung().getSelectionModel().getSelectedItem();
//		    
//		        job.getJobSettings().setCopies(cAlertPrintSettings.getSpinnerAnzahlExemplare().getValue());
//		        job.setPrinter(printer);
//		        
//		        PDDocument document = PDDocument.load(file);
//
////		        job.showPrintDialog(stage);
//		        
//		        System.out.println("test -> " + job.getPrinter().getName());
//		        System.out.println("test -> " + job.getPrinter().getPrinterAttributes().getDefaultPaperSource());		        
//
//		        for (PDPage item : document.getPages()) {
//					item.getMediaBox().getWidth();					
//					System.out.println("Page: "  + " -> " + item.getMediaBox().getWidth() + " x " + item.getMediaBox().getHeight());
//				}
//
//		        
//		        int pageVon = 0;
//		        int pageBis = cAlertPrintSettings.getAnzahlPDFSeiten();
//		        
//		        if (cAlertPrintSettings.getRadioButtonSeiten().isSelected()) {
//			        pageVon = cAlertPrintSettings.getSpinnerSeitenVon().getValue() - 1;
//			        pageBis = cAlertPrintSettings.getSpinnerSeitenBis().getValue();
//				}
//		        
//		    	PDFRenderer pdfRenderer = new PDFRenderer(document);
//		    	for (int page = pageVon; page < pageBis; ++page)
//		    	{ 
//		    		
//		    	    BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 400, ImageType.RGB);
//		    	    Paper format = job.getPrinter().getPrinterAttributes().getDefaultPaper();
//		    	    
//		    	    double width = (float) format.getWidth();
//		    	    double height = (float) format.getHeight();
//
//		    	    System.out.println(format.getName());
//		    	    System.out.println("Paper: " + width + " x " + height);
//		    	    System.out.println("printer: " + printer.getPrinterAttributes().getMaxCopies());
////		    	    System.out.println("PrintQuality: " + quality.name());
//
//
//		    	    javafx.scene.image.Image fxImg = SwingFXUtils.toFXImage(bim, null);
//			        ImageView imageView = new ImageView(fxImg);
//
//			        System.out.println("test -> " + printer.getDefaultPageLayout().getPaper());
//			        
//			        
//			        
//			        javafx.print.PageLayout paisagem = printer.createPageLayout(paper, pageOrientation, MarginType.HARDWARE_MINIMUM);
//			        Double newWidth = paisagem.getPrintableWidth();
//			        Double newHeight = paisagem.getPrintableHeight();
//			        job.getJobSettings().setPageLayout(paisagem);
//			        imageView.setFitWidth(newWidth);
//			        imageView.setFitHeight(newHeight);
//			     
//			        
////			        for (int i = 0; i < 2; i++) {
//			        	success = success & job.printPage(imageView);
////			        }
//			        
//			        System.out.println(success + " -> counter: " + ++counter);
//		    	    // suffix in filename will be used as the file format
////		    	    ImageIOUtil.writeImage(bim, "img" + "-" + (page+1) + ".png", 300);
//		    	}
//		    	document.close();
//		        
//
//		    if (success) {
//		        job.endJob();
//		    }
//		}
//		
//		
//    }   
	
//	public float pt2mmForWeb72dpi(float pt) {
//		   return pt2mm(pt,72);
//		}
//	public float pt2mmForPrint300dpi(float pt) {
//	   return pt2mm(pt,300);
//	}
//	public float pt2mmForPrint600dpi(float pt) {
//	   return pt2mm(pt,600);
//	}
//	public float pt2mm(float pt, float dpi) {
//	   return pt * 25.4f / dpi;
//	}
	
//	private static PDFFile mkPDFFile(final InputStream pdfIn)
//	{
//		final PDFFile pdfFile;
//		try
//		{
//			final ByteBuffer bb = ByteBuffer.wrap(Util.toByteArray(pdfIn));
//			pdfFile = new PDFFile(bb);
//		}
//		catch (final IOException e)
//		{
//			throw new RuntimeException(e);
//		}
//		return pdfFile;
//	}
//	
//	private void startPrint(PrinterJob job, File pdfFile) {
//		try {
//			DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
//		    PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
//		    patts.add(Sides.DUPLEX);
//		    PrintService[] ps = PrintServiceLookup.lookupPrintServices(flavor, patts);
//		    if (ps.length == 0) {
//		        throw new IllegalStateException("No Printer found");
//		    }
//		    System.out.println("Available printers: " + Arrays.asList(ps));
//	
//		    PrintService myService = null;
//		    for (PrintService printService : ps) {
//		        if (printService.getName().equals(job.getPrinter().toString())) {
//		            myService = printService;
//		            break;
//		        }
//		    }
//	
//		    if (myService == null) {
//		        throw new IllegalStateException("Printer not found");
//		    }
//
//		    
//		    System.out.println("1");
//		    
//	        PDDocument document = PDDocument.load(new File("C:/temp/example.pdf"));
//
////	        PDFPageable doc = new PDFPageable(document.);
//	        
//		    
//		    FileInputStream fis = new FileInputStream(pdfFile);
//		    Doc pdfDoc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
//		    DocPrintJob printJob = myService.createPrintJob();
//		    printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
//		    fis.close();    
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    }
	
	
	
}
