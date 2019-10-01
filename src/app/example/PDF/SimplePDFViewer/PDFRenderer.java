package app.example.PDF.SimplePDFViewer;

import java.io.File;

import org.jpedal.examples.viewer.gui.javafx.FXViewerTransitions;
import org.jpedal.examples.viewer.gui.javafx.FXViewerTransitions.TransitionDirection;
import org.jpedal.examples.viewer.gui.javafx.FXViewerTransitions.TransitionType;
import org.jpedal.examples.viewer.gui.javafx.dialog.FXInputDialog;
import org.jpedal.exception.PdfException;
import org.jpedal.objects.PdfPageData;

import app.example.PDF.PDFRenderer_2.BaseViewerFXDemo.FitToPage;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.beans.property.SimpleFloatProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PDFRenderer {

	private SimplePDFViewerFXDemo viewerFX;
	private Stage stage;
	private Scene scene;
	private AnchorPane root;
	private Group group;
	
	private FitToPage zoomMode = FitToPage.AUTO;
	private int currentPage = 1;
	private TransitionType transitionType = TransitionType.None;
	private SimpleFloatProperty scale = new SimpleFloatProperty(1.0f);
    private static final float insetX = 25;
    private static final float insetY = 25;
	
    //These two variables are todo with PDF encryption & passwords
    private String password; //Holds the password from the JVM or from User input
    private boolean closePasswordPrompt; //boolean controls whether or not we should close the prompt box
    
    private File file;
    private String PDFfile;
    
    
    public enum FitToPage{
        AUTO, WIDTH, HEIGHT, NONE
    }
	
	private final org.jpedal.PdfDecoderFX pdf = new org.jpedal.PdfDecoderFX();

	public PDFRenderer(SimplePDFViewerFXDemo viewerFX) {
		this.viewerFX = viewerFX;
		this.stage = viewerFX.getStage();
		this.scene = viewerFX.getScene();
		this.root = viewerFX.getRoot();
		this.group = viewerFX.getGroup();
		
        // Set transition if set in properites:
        String trans = System.getProperty("org.jpedal.fxtransition");
        if(trans != null){
            // Ensure correct lettering 
            trans = trans.substring(0, 1).toUpperCase() + trans.substring(1).toLowerCase();         
                transitionType = TransitionType.valueOf(trans);
        }
        
        // Set page if set in JVM flag
        final String pageNum = System.getProperty("org.jpedal.page");
        if(pageNum != null){
                currentPage = Integer.parseInt(pageNum);
        }
		
		group.getChildren().add(pdf);
		addListeners();
	}
	
	   public void addListeners(){
	        /**
	         * auto adjust so dynamically resized as viewer width alters
	         */
		   scene.widthProperty().addListener((ov, oldVal, newVal) -> {       	
	        	fitToX(zoomMode);
	        });

		   scene.heightProperty().addListener((ov, oldVal, newVal) -> {
	        	fitToX(zoomMode);
	        });
	       
	        /**
	         * Controls for dragging a PDF into the scene
	         * Using the dragboard, which extends the clipboard class, 
	         * detect a file being dragged onto the scene and if the user drops the file
	         * we load it.
	         */
		   scene.setOnDragOver(new EventHandler<>() {
	            @Override
	            public void handle(final DragEvent event) {
	                final Dragboard db = event.getDragboard();
	                if (db.hasFiles()) {
	                    event.acceptTransferModes(TransferMode.COPY);
	                } else {
	                    event.consume();
	                }
	            }
	        });
	        
		   scene.setOnDragDropped(new EventHandler<>() {        
	            @Override
	            public void handle(final DragEvent event) {
	                final Dragboard db = event.getDragboard();
	                boolean success = false;
	                if(db.hasFiles()){
	                    success = true;
	                    // Only get the first file from the list
	                    file = db.getFiles().get(0);
	            		Platform.runLater(() -> {
	            			loadPDF(file);
	            		});
	                }
	                event.setDropCompleted(success);
	                event.consume();
	            }
	        });
	    }
	
	public void loadPDF(final File input){
	    System.out.println("PDF-File: " + input);
	    if(input == null) {
	        return;
	    }
	    
	    scale.set(1); //reset to default for new page
	
	    PDFfile = input.getAbsolutePath();
	
	    openFile(input, null, false);
	            
	}
	   
    private boolean openFile(final File input,String url, boolean isURL) {
    	System.out.println("openFile()");
        try {
            //Open the pdf file so we can check for encryption
            if(isURL){
                pdf.openPdfFileFromURL(url, false);
            }else{
                pdf.openPdfFile(input.getAbsolutePath());
            }
            
            if(System.getProperty("org.jpedal.page") != null && !System.getProperty("org.jpedal.page").isEmpty()){
                currentPage = currentPage < 1 ? 1 : currentPage;
                currentPage = currentPage > pdf.getPageCount() ? pdf.getPageCount() : currentPage;
            }else{
                currentPage = 1;
            }
            /**
             * This code block deals with user input and JVM passwords in Encrypted PDF documents.
             */
            if(pdf.isEncrypted()){
                
                int passwordCount = 0;        //Monitors how many attempts there have been to the password
                closePasswordPrompt = false;  //Do not close the prompt box
                
                //While the PDF content is not viewable, repeat until the correct password is found
                while(!pdf.isFileViewable() && !closePasswordPrompt) {
                    
                    /**
                     * See if there's a JVM flag for the password & Use it if there is
                     * Otherwise prompt the user to enter a password
                     */
                    if(System.getProperty("org.jpedal.password")!=null){
                        password = System.getProperty("org.jpedal.password");
                    }else if(!closePasswordPrompt){
                        showPasswordPrompt(passwordCount);
                    }
                    
                    //If we have a password, try and open the PdfFile again with the password
                    if (password != null) {
                        
                        if(isURL){
                            pdf.openPdfFileFromURL(url,false,password);
                        }else{
                            pdf.openPdfFile(input.getAbsolutePath());
                        }
                        //pdf.setEncryptionPassword(password);
                        
                    }
                    passwordCount += 1; //Increment he password attempt
                    
                }
                
            }
        } catch (final PdfException ex) {
            ex.printStackTrace();
            // If the pdf failed to open, don't decode it.
            return true;
        }
        // Set up top bar values
//        ((Label)top.lookup("#pgCount")).setText("/" + pdf.getPageCount());
//        final ComboBox pages = ((ComboBox)top.lookup("#pages"));
//        pages.getItems().clear();
//        for(int i = 1; i <= pdf.getPageCount(); i++){
//            pages.getItems().add(String.valueOf(i));
//        }
        // Goes to the first page and starts the decoding process
        goToPage(currentPage);
        return false;                
    }
	   
    private void showPasswordPrompt(final int passwordCount){
        
        //Setup password prompt content
        final Text titleText = new Text("Password Request");
        final TextField inputPasswordField = new TextField("Please Enter Password");
        
        //If the user has attempted to enter the password more than once, change the text
        if(passwordCount >= 1){
            titleText.setText("Incorrect Password");
            inputPasswordField.setText("Please Try Again");
        }
        
        final FXInputDialog passwordInput = new FXInputDialog(new Stage(), titleText.getText()){
            @Override
            protected void positiveClose(){
                super.positiveClose();
                closePasswordPrompt = true;
            }
        };
        
        password = passwordInput.showInputDialog();
        
    }
	 private void goToPage(final int newPage){
	    System.out.println("goToPage()");    
        final TransitionDirection direction ;
        
        // For sliding Transitions
        if(transitionType != TransitionType.Fade || transitionType != TransitionType.None){
            direction = newPage > currentPage ? TransitionDirection.LEFT: TransitionDirection.RIGHT;
        }else{
            direction = TransitionDirection.NONE;
        }
        
        switch (transitionType){
            case Fade:
                startTransition(  newPage, direction);
                break;              
            case Scale:
                startTransition(  newPage, direction);
                break;            
            case Rotate:
                startTransition(  newPage, direction);
                break;                
            case CardStack:
                startTransition(  newPage, direction);
                break;               
            default: //no transition                
                currentPage = newPage;
                decodePage();
                break;
        }
	        
	}
	 
    private void decodePage() {  
    	System.out.println("decodePage()");
        try {
            final PdfPageData pageData = pdf.getPdfPageData();
            final int rotation = pageData.getRotation(currentPage); //rotation angle of current page

            //Only call this when the page is displayed vertically, otherwise
            //it will mess up the document cropping on side-ways documents.
            if (rotation == 0 || rotation == 180) {
                pdf.setPageParameters(scale.get(), currentPage);
            }

            pdf.decodePage(currentPage);
            //wait to ensure decoded
            pdf.waitForDecodingToFinish();
        } catch (final Exception e) {
//	        	visibleHandler(GroupVis.MELDUNG, "Die PDF-Datei konnte nicht geladen werden.\nBitte versuchen sie es nochmal.");
            e.printStackTrace();
        }
        fitToX(FitToPage.AUTO);
//	        updateNavButtons();
//	        setBorder();
//	        adjustPagePosition(scrollPanePDF.getViewportBounds());
    } 
	 
    
    private void fitToX(final FitToPage fitToPage) {
        System.out.println("fitToX()");
        if(fitToPage == FitToPage.NONE) {
            return;
        }
        
        final float pageW = pdf.getPdfPageData().getCropBoxWidth2D(currentPage);
        final float pageH = pdf.getPdfPageData().getCropBoxHeight2D(currentPage);
        final int rotation = pdf.getPdfPageData().getRotation(currentPage);
        
        System.out.println("rotation [" + rotation + "]  pageW [" + pageW + "]  pageH [" + pageH + "]  fitToPage [" + fitToPage + "]");
        
        //Handle how we auto fit the content to the page
//        if(fitToPage == FitToPage.AUTO && (pageW < pageH)){
        if(fitToPage == FitToPage.AUTO){
                if(pdf.getPDFWidth() < pdf.getPDFHeight()) {
                    fitToX(FitToPage.HEIGHT);
                }
                else {
                	fitToX(FitToPage.HEIGHT);
//                    fitToX(FitToPage.WIDTH);
                }
        }        
            
        //Handle how we fit the content to the page width or height
        if(fitToPage == FitToPage.WIDTH){
            final float width = (float) (root.getWidth());
            
            if(rotation == 90 || rotation == 270){
                scale.set((width - insetX - insetX) / pageH);
            }else{
                scale.set((width - insetX - insetX) / pageW);
            }  
        }else if(fitToPage == FitToPage.HEIGHT){
            final float height = (float) (root.getHeight());

            if(rotation == 90 || rotation == 270){
                scale.set((height - insetY - insetY) / pageW);
            }else{
                scale.set((height - insetY - insetY) / pageH);
            }
        }
        
        pdf.setPageParameters(scale.get(), currentPage);
        System.out.println("fitToX() Ende");
    }
    
     private void startTransition(final int newPage,final TransitionDirection direction){
         final Transition exitTransition = FXViewerTransitions.exitTransition(pdf, transitionType, direction);
         if(exitTransition != null){
             exitTransition.setOnFinished(new EventHandler<>() {
                 @Override public void handle(final ActionEvent t) {
                     
                     currentPage = newPage;

                     Platform.runLater(new Runnable() {
                         @Override
                         public void run() {
                             decodePage();
                         }
                     });
                                 
                     TransitionDirection entryDirection = direction;
                     if(direction != TransitionDirection.NONE){
                         entryDirection = direction == TransitionDirection.LEFT ? TransitionDirection.RIGHT : TransitionDirection.LEFT;
                     }
                      
                     final Transition entryTransition = FXViewerTransitions.entryTransition(pdf, transitionType, entryDirection);
                     entryTransition.play();
                 }
             });
             exitTransition.play();
         }
     }
}
