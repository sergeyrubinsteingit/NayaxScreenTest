package ProgramFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import ProgramFiles.ListBrowsers_;

public class MakeBrowsersList {

	public static Scanner sourceReader;
	public static boolean brwsFlag;
	private static ArrayList<String> browsersList = new ArrayList<>();
	public static String[] listStringArray;
	
	public static void main(String[] args) {
	
		browsersList.add("Please select a browser"); // This produces a first line for the browsers menu
		
		try {
			ListBrowsers_.process_.destroy();
			File listSource = new File(System.getProperty("user.dir") + "\\src\\main\\java\\ProgramFiles\\ListBrowsers\\browsers_.txt");
			System.out.println("\n============> MAKE BROWSERS LIST <================\n");
			System.out.println(System.getProperty("user.dir") + "\\src\\main\\java\\ProgramFiles\\ListBrowsers\\browsers_.txt");

			sourceReader = new Scanner(listSource);
				while (sourceReader.hasNextLine()) {
					brwsFlag = true;
					String browserName = sourceReader.nextLine().toLowerCase();
					browsersList (browserName);
				}
				
				if (!sourceReader.hasNextLine()) {
					listStringArray(browsersList);
					sourceReader.close();
				}//eoif
								
		} catch (FileNotFoundException e) {
			System.exit(-1);
			e.printStackTrace();
		}
		
	}// eomain
	
	public static ArrayList<String> browsersList (String browserName_){
		
		if (browserName_.contains("irefox") || browserName_.contains("fire")) {
			browserName_ = "Firefox";
		} else if (browserName_.contains("hrome") || browserName_.contains("chr")) {
			browserName_ = "Chrome";
		} else if (browserName_.contains("xplorer") || browserName_.contains("iexplore")) {
			browserName_ = "Internet Explorer";
		} else if (browserName_.contains("dge") || browserName_.contains("Edge")) {
			browserName_ = "MS Edge";
		} else if (browserName_.contains("pera") || browserName_.contains("Opera")) {
			browserName_ = "Opera";
		} else if (browserName_.contains("Safari") || browserName_.contains("afari")) {
			browserName_ = "Safari";
		} else {
			browserName_ = "Unknown browser";
		}//eoif
			
		browsersList.add(browserName_);
		return browsersList;
		} // eometh
	
	public static String[] listStringArray (ArrayList<String> browsersList_) {
		listStringArray = new String[browsersList.size()];
		for (int i = 0; i < browsersList_.size(); i++) {
			listStringArray [i] = browsersList_.get(i);
		}
		System.out.println("####>>>>> " + Arrays.toString(listStringArray));
		
		try {
			ComboStuff.main(browsersList_);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return listStringArray ;		
	}//eometh

}// eoclass
