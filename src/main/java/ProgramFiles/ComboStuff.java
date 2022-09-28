package ProgramFiles;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ComboStuff {
	
	private static String comboTitle;
	private static String comboTarget;

	public static void ComboStuff(ArrayList<String> browsersList_) throws InterruptedException {
		
		System.out.println("\n============> COMBO STUFF <================\n");

		comboTarget = "browser you want to run a test on";
		String comboTitle = "<html><body>"
				+ "<font size='15px' family='Helvetica, sans-serif'>"
				+ "<i>Please select the " + comboTarget + ".</i>"
				+ "</font></body></html>";
		
//		setComboTitle(comboTitle);
		
		Combo_Box.main(comboTitle, browsersList_);
		
		while (Combo_Box.timeFlag != true) {
			for (int i = 0; i < 1000; i++) {
				
					TimeUnit.SECONDS.sleep(1);
					i++;
					if (Combo_Box.timeFlag == true) {
						RunBrowser.main(null);
						break;
					}
					else if (i >= 1000) {
						System.exit(-1);
						System.out.println("FAILED!!! Time is out.");
					}/// eo if1
				}/// eo while
			}/// eo for
	}//eof


//	public static String getComboTitle() {
//		return comboTitle;
//	} //eogetter
//
//	public static String setComboTitle(String comboTitle) {
////		comboTitle = comboTitle;
//		return comboTitle;
//	} //eosetter
//	
	public static void main(ArrayList<String> browsersList_) throws InterruptedException {
		ComboStuff(browsersList_);
	}

}//class
