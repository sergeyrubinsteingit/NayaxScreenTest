//Starts the program, fires ListBrowsers_ class
package com.Start;

import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import ProgramFiles.ListBrowsers_;

public abstract class Start implements KeyListener {

	public static void main(String [] args) throws InterruptedException {
		
		System.out.println("\n============> DEFAULT START BROWSER <================\n");
		
		try {
			ListBrowsers_.main(args);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} // try
		TimeUnit.SECONDS.sleep(1);	
	} //eomain

}// eoclass