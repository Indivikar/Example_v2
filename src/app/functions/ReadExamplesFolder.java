package app.functions;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Pattern;

import app.models.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReadExamplesFolder {

	private Path demoFile;
	private ObservableList<App> appsList = FXCollections.observableArrayList();
	
	
	public ReadExamplesFolder() {
		
		appsList.clear();
		
		PfadErmitteln pe = new PfadErmitteln(new String[] {"app", "example"}, true, false);
		Path path = pe.getErmittelterPath();
		System.out.println(path);
		
		try {
			Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//					System.out.println("file: " + file);
					return FileVisitResult.CONTINUE;					
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

					if (dir.toString().contains("GehtNicht") || dir.toString().contains("gehtNicht") || dir.toString().contains("gehtnicht")) {
						return FileVisitResult.CONTINUE;
					}
					
					String[] part = splitString(path, dir);
					
					if (part != null && part.length == 3) {
						appsList.add(new App(part[2], part[1], 
								getDemoFile(dir).toString()) );
					}
//					String className = "com.mycompany.Foo";
//					Class<T> c = Class.forName(className);
//					T castToT = c.cast(myObject);
					
//					System.out.println("dir: " + dir);
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	private Path getDemoFile(Path dir) {
		demoFile = new File("").toPath();
		
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
	
//	private <T> void myClass() {
//		String className = "com.mycompany.Foo";
//		Class<T> c = (Class<T>) Class.forName(className);
//		T castToT = c.cast(myObject);
//
//	}
	
	private String[] splitString(Path mainPath, Path filePath ) {
		
		String string = filePath.toString().replace(mainPath.toString(), "");	
		System.out.println(string);
		String[] parts = string.split(Pattern.quote("\\"));
		if (parts.length == 3) {
			return parts;
		}
		return null;

	}
	
	public ObservableList<App> getAppsList() {return appsList;}
	
}
