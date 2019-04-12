package app.functions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

import app.Start;
import app.models.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;

public class ReadExamplesFolder {

	final String pathExample = "app/example";
	final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
	PfadErmitteln pe = new PfadErmitteln(new String[] {"app", "example"}, true, false);
	Path path = pe.getErmittelterPath();
	
	private Path demoFile;
//	private ObservableSet<App> appsSetList = FXCollections.observableSet();
	private ObservableList<App> appsList = FXCollections.observableArrayList();
	
	
    private boolean isItemExists(String bezeichnung, String kategorie) {
    	boolean b = false;

    	for (App app : appsList) {
			if (bezeichnung.equalsIgnoreCase(app.getBezeichnung()) && kategorie.equalsIgnoreCase(app.getKategorie())) {
				b = true;
			}
		}
    	
        return b;
    }
	
	public ReadExamplesFolder() {

		appsList.clear();
		
		
		
		System.out.println(path);
		
		try {
			if(jarFile.isFile()) {  // Run with JAR file
			    JarFile jar = new JarFile(jarFile);
			    final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
			    while(entries.hasMoreElements()) {
			        final String name = entries.nextElement().getName();
			        if (name.startsWith(pathExample + "/")) { //filter according to the path
//			            System.out.println(jarFile + File.separator + name);
			            
			            File dir = new File(jarFile + File.separator + name);
			            File readMe = null;
			            
			            if (dir.toString().contains("GehtNicht") || dir.toString().contains("gehtNicht") || dir.toString().contains("gehtnicht")) {
							continue;
						}
			            
						if (dir.getAbsolutePath().endsWith(".class")) {
							dir = new File(jarFile + File.separator + name).getParentFile();
//							System.out.println("------------------------------------------");
//							readMe = new File(new File(jarFile + File.separator + name).getParentFile() + File.separator + "readme.txt");
//							System.out.println("readme: " + readMe);						
//							System.out.println("readme is exists: " + readMe.exists());
						} 
						
	            
						String[] part = splitString(path, dir.toPath());
						
						if (part != null && part.length == 3) {
							System.out.println("Add: [" + part[2] + "], [" + part[1] + "], [" + getDemoFileJarFile(dir.toPath()).toString() + "]");
							if (!isItemExists(part[2], part[1])) {
								appsList.add(new App(part[2], part[1], getDemoFileJarFile(dir.toPath()).toString(), getReadMeJarFile(dir.toPath())));
								getCrossReferencesJarFile(dir.toPath(), part);
							}

						}
						
			        }
			    }
			    jar.close();
			} else { // Run with IDE

				Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//						System.out.println("file: " + file);
						return FileVisitResult.CONTINUE;					
					}

					@Override
					public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

						if (dir.toString().contains("GehtNicht") || dir.toString().contains("gehtNicht") || dir.toString().contains("gehtnicht")) {
							return FileVisitResult.CONTINUE;
						}
						
						String[] part = splitString(path, dir);
						
						
						if (part != null && part.length == 3) {
//							System.out.println("Add: [" + part[2] + "], [" + part[1] + "], [" + getDemoFileIDE(dir).toString() + "]");
							String startFile = getDemoFileIDE(dir).toString();
							appsList.add(new App(part[2], part[1], startFile, getReadme(startFile)));
							getCrossReferences(dir, part);
						}
															
//						System.out.println("dir: " + dir);
						return FileVisitResult.CONTINUE;
					}
				});
								
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		PfadErmitteln pe = new PfadErmitteln(new String[] {"app", "example"}, true, false);
//		Path path = pe.getErmittelterPath();
//		System.out.println(path);
//		
//		if (!path.toFile().exists()) {
//			System.err.println("Ordner nicht gefunden");
//		}
//		
//		try {
//			Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
//				@Override
//				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
////					System.out.println("file: " + file);
//					return FileVisitResult.CONTINUE;					
//				}
//
//				@Override
//				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
//
//					if (dir.toString().contains("GehtNicht") || dir.toString().contains("gehtNicht") || dir.toString().contains("gehtnicht")) {
//						return FileVisitResult.CONTINUE;
//					}
//					
//					String[] part = splitString(path, dir);
//					
//					if (part != null && part.length == 3) {
//						appsList.add(new App(part[2], part[1],getDemoFile(dir).toString()));
//						getCrossReferences(dir, part);
//					}
//														
//					System.out.println("dir: " + dir);
//					return FileVisitResult.CONTINUE;
//				}
//			});
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
	}
	
	private String getReadMeJarFile(Path dir) {
//		Path readMeFile = new File("").toPath();

		String readMe = "";
		
		String string = dir.toString().replace(path.getParent().toString(), "");
		String examplePath = string.replace("\\", "/").substring(1);
		
		
		
		try {
			if(jarFile.isFile()) {  // Run with JAR file
			    JarFile jar = new JarFile(jarFile);
			    final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
			    while(entries.hasMoreElements()) {
			        final String name = entries.nextElement().getName();
//			        System.out.println("getDemoFile() examplePath: " + examplePath + " == " + name);
			        if (name.contains("readme.txt")  && name.contains(examplePath)) {
//			        	System.out.println("getDemoFile() examplePath: " + examplePath + File.separator + "readme.txt");
//			        	System.out.println("getDemoFile() name: " + name);
//			        	System.out.println(Start.class.getResourceAsStream(examplePath));
			        	InputStream inputStream = Start.class.getResourceAsStream(examplePath + "/readme.txt");
			        	if (inputStream != null) {
							InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF-8");
				        	BufferedReader in = new BufferedReader(streamReader);
				        	
				        	for (String line; (line = in.readLine()) != null;) {
//				        	    System.out.println(line);
				        	    readMe += line;
				        	}
						}
					}
			    }
			    jar.close();
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return readMe;
	}
	
	private String getReadme(String startFile) {
//		System.out.println("setSourceCode: " + startFile);
		if (startFile != null && !startFile.equals("")) {
			File file = new File(startFile);
			
			String parent = file.getParent();
			File readmeFile = new File(parent + File.separator + "readme.txt");
			System.out.println("setReadme: " + readmeFile);
			System.out.println("Readme is exists: " + readmeFile.exists());
			if (readmeFile.exists()) {
				
				try {
					String content = new String(Files.readAllBytes(Paths.get(readmeFile.toURI())));
					return content;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "";
				}
			} else {
				return "";
			}

			
//			PfadErmitteln pe = new PfadErmitteln(new String[] {}, true, false);
//			String newString = parent.replace(pe.getErmittelterFile().getAbsolutePath(), "");
//			String newStringWithSlash= newString.replaceAll(Pattern.quote("\\"), "/");
//			String newURL = "https://github.com/Indivikar/Example_v2/tree/master/src" + newStringWithSlash;
//			System.out.println("newURL: " + newURL);
//			return newURL;
		} else {
			return "";
		}
		
	}
	
	private void getCrossReferencesJarFile(Path dir, String[] part) {
//		File file = new File(dir + File.separator + "crossReferences.txt");
		
		
		String string = dir.toString().replace(path.getParent().toString(), "");
		String examplePath = string.replace("\\", "/").substring(1);
		
		
		
		try {
			if(jarFile.isFile()) {  // Run with JAR file
			    JarFile jar = new JarFile(jarFile);
			    final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
			    while(entries.hasMoreElements()) {
			        final String name = entries.nextElement().getName();
//			        System.out.println("getDemoFile() examplePath: " + examplePath + " == " + name);
			        if (name.contains("crossReferences.txt")  && name.contains(examplePath)) {
			        	System.out.println("getDemoFile() examplePath: " + examplePath + File.separator + "crossReferences.txt");
			        	System.out.println("getDemoFile() name: " + name);
			        	System.out.println(Start.class.getResourceAsStream(examplePath));
			        	InputStream inputStream = Start.class.getResourceAsStream(examplePath + "/crossReferences.txt");
			        	if (inputStream != null) {
							InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF-8");
				        	BufferedReader in = new BufferedReader(streamReader);
				        	
				        	System.out.println("---------------------");
				        	
				        	for (String line; (line = in.readLine()) != null;) {
				        		System.out.println("Add crossReferences: " + line);
				        	    appsList.add(new App(part[2], line, getDemoFileJarFile(dir).toString(), ""));
//				        	    readMe += line;
				        	}
						}
					}
			    }
			    jar.close();
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		if (file.exists()) {
//			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//			    String line;
//			    while ((line = br.readLine()) != null) {
//			    	appsList.add(new App(part[2], line, getDemoFileJarFile(dir).toString(), ""));
//			    }
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

	}
	
	
	private void getCrossReferences(Path dir, String[] part) {
		File file = new File(dir + File.separator + "crossReferences.txt");
		
		if (file.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			    	appsList.add(new App(part[2], line, getDemoFileJarFile(dir).toString(), ""));
			    }
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private Path getDemoFileJarFile(Path dir) {
		demoFile = new File("").toPath();
//		System.out.println("getDemoFile(): " + dir);


		String string = dir.toString().replace(path.toString(), "");
		String examplePath = string.replace("\\", "/");
		
//		System.out.println("getDemoFile() examplePath: " + examplePath);
		try {
			if(jarFile.isFile()) {  // Run with JAR file
			    JarFile jar = new JarFile(jarFile);
			    final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
			    while(entries.hasMoreElements()) {
			        final String name = entries.nextElement().getName();
//			        System.out.println("getDemoFile() examplePath: " + examplePath + " == " + name);
			        if (name.contains("Demo") && !name.endsWith("$1.class") && name.contains(examplePath)) {
//						System.out.println("file: " + name);
						demoFile = new File(jarFile + File.separator + name).toPath();
//						System.out.println("file: " + demoFile);
//						System.out.println("demoFile: " + demoFile);
					}
			    }
			    jar.close();
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
//		try {
//			Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
//				@Override
//				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//					if (file.toFile().getName().contains("Demo") && !file.toFile().getName().endsWith("$1.class")) {
////						System.out.println("file: " + file);
//						demoFile = file;
////						System.out.println("demoFile: " + demoFile);
//					}
//
//					return FileVisitResult.CONTINUE;					
//				}
//
//			});
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
//		File[] files = dir.toFile().listFiles();
//		if (files != null) { // Erforderliche Berechtigungen etc. sind vorhanden
//			for (int i = 0; i < files.length; i++) {
//				if (!files[i].isDirectory() && files[i].getName().contains("Demo")) {
//					System.out.println(files[i]);
//					return files[i].toPath();
//				}
//			}
//		}
		
		return demoFile;
	}
	
	private Path getDemoFileIDE(Path dir) {
		demoFile = new File("").toPath();
		System.out.println("getDemoFile(): " + dir);

		try {
			Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					if (file.toFile().getName().contains("Demo") && !file.toFile().getName().endsWith("$1.class")) {
//						System.out.println("file: " + file);
						demoFile = file;
//						System.out.println("demoFile: " + demoFile);
					}

					return FileVisitResult.CONTINUE;					
				}

			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

		
		return demoFile;
	}
	
//	private <T> void myClass() {
//		String className = "com.mycompany.Foo";
//		Class<T> c = (Class<T>) Class.forName(className);
//		T castToT = c.cast(myObject);
//
//	}
	
	private String[] splitString(Path mainPath, Path filePath ) {
		
		String string = filePath.toString().replace(mainPath.toString(), "");	
//		System.out.println("Main Path: " + filePath);
//		System.out.println("Replace: " + mainPath);
//		System.out.println("Split: " + string);
		String[] parts = string.split(Pattern.quote("\\"));
		if (parts.length == 3) {
			return parts;
		}
		return null;

	}
	
	
	
	public ObservableList<App> getAppsList() {return appsList;}
	
}
