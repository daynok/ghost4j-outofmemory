package com.medata;

import java.io.File;
import org.ghost4j.document.PDFDocument;
import org.ghost4j.renderer.SimpleRenderer;

public class Driver {
	
	public static void main(String args[]) {
		try {
			String sourcePath = args[0];
			System.out.println("Reading files from path:" + sourcePath);
			
			File[] fileList = new File(sourcePath).listFiles();
			
			for(File pdfFile : fileList) {
				System.out.println("starting to process file: " + pdfFile.getName());
				long startIterTime = System.currentTimeMillis();
				PDFDocument document = null;
				SimpleRenderer renderer = null;
				
				try {
					
					document = new PDFDocument();
					document.load(pdfFile);
					renderer = new SimpleRenderer();
					renderer.setResolution(300);
					renderer.render(document);
				}
				catch(Exception e) {
					throw e;
				}
				finally {
					renderer = null;
					document = null;
				}
				long totalIterTime = System.currentTimeMillis() - startIterTime;
				System.out.println("file:" + pdfFile.getName() + " total time:" + totalIterTime + " ms, total time sec:" + (totalIterTime/1000));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}