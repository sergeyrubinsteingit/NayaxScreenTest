//Builds a combobox window to let
//the user a choice between browsers
//to test the site on.

package ProgramFiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ProgramFiles.RunBrowser;

public class Combo_Box { 
	
	public static String comboTitle;
	public static ArrayList<String> menuEntries;
	public static String [] browserLst = new String[] {"Choose a browser","Firefox","Chrome","Edge"};
	public int frWidth = 300;
	public int frHeight = 300;	
	public Combo_Frame frameInst = new Combo_Frame();
	public Dimension frameSize = frameInst.getPreferredSize();
	public static boolean timeFlag;
	public static ItemEvent cbxEvent;
	public static String selectedBrowser;


public class Combo_Box_Set extends JComboBox<String> {
	

	public Combo_Box_Set(ArrayList<String> browsersList_) { // Sets a combobox
		
		System.out.println("\n============> COMBO BOX <================\n");
			
		menuEntries = browsersList_;
		timeFlag = false;
		
		this.setPreferredSize(new Dimension((int)(frameSize.getWidth()*0.7),(int)(frameSize.getHeight()*0.1)));
		this.setModel(new DefaultComboBoxModel<String>(MakeBrowsersList.listStringArray)); //Sets an array for combobox
		this.setPrototypeDisplayValue("internet explorer");

			this.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent cbxEvent_) {
					cbxEvent = cbxEvent_;
					System.out.println("itemStateChanged:" + "\ngetItem = " + cbxEvent.getItem() + "\ngetStateChange = "
							+ cbxEvent.getStateChange() + "\ngetID = " + cbxEvent.getID());
					
					try {
						TimeUnit.SECONDS.sleep(1);
						selectedBrowser = cbxEvent.getItem().toString();
						timeFlag = true;
						frameInst.dispose();
					} catch (InterruptedException e) {
						System.out.println("Failed in Combo Box / itemStateChanged\n");
						System.exit(-1);
						e.printStackTrace();
					}
					
				}
			}); // eo addItemListener
			
		Combo_Panel cbxPanel = new Combo_Panel();		
		Combo_Label_Txt cbxLabelTxt_ = new Combo_Label_Txt(comboTitle); // Combobox label instance		
		Combo_Button cbxButton = new Combo_Button();
	
		cbxPanel.add(cbxLabelTxt_, BorderLayout.NORTH);
		cbxPanel.add(this, BorderLayout.SOUTH);
		cbxPanel.add(cbxButton, BorderLayout.SOUTH);
		frameInst.getContentPane().add(cbxPanel);
		frameInst.pack();
	} //eoCombo_Box constructor	
} // eo class Combo_Box_Set 
		
		
	class Combo_Frame extends JFrame { // Sets a frame for combobox
	
		public Combo_Frame () {
			super("Select a browser");
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setPreferredSize(new Dimension(300, 300));
			this.pack();
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			
		}//eocbxFrame constructor
	}//eo Combo_Frame
	
	
	class Combo_Label_Txt extends JLabel { // Sets a label for combobox

		private Combo_Label_Txt (String comboTxt_) {
			comboTitle = comboTxt_;
			this.setHorizontalAlignment(JLabel.CENTER);
			this.setVerticalAlignment(JLabel.CENTER);
			this.setText(comboTxt_);
			this.setPreferredSize(new Dimension((int)(frameSize.getWidth()*0.8),(int)(frameSize.getHeight()*0.5)));
			this.setBackground(Color.CYAN);
		}//eo Label constructor
	}// eoCombo_Label_Txt
	
	
	class Combo_Button extends JButton {
		
		private Combo_Button () {
			this.setText("Cancel the process");
			this.setPreferredSize(new Dimension((int)(frameSize.getWidth()*0.5), (int)(frameSize.getHeight()*0.1)));
			this.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Process has been cancelled by tester");
					if (RunBrowser.webDriver != null) {
						RunBrowser.webDriver.quit();
					}
					System.exit(-1);
				}
			}); // eo ActionListener
		} // eo constructor
	} // eo class Combo_Button
	

	class Combo_Panel extends JPanel { // Sets a panel for both label and combobox
		public Combo_Panel() {
			this.setSize(frWidth, frHeight);
		    this.setOpaque(true);
		    this.setBackground(Color.ORANGE);
		}//eoconstructor
	} //eo class Combo_Panel
	
	
public static void main(String comboTitle_, ArrayList<String> browsersList_) throws InterruptedException {
	
	System.out.println(":::: Deafult: listStringArray :::: " + Arrays.toString(MakeBrowsersList.listStringArray));

			final Combo_Box startBox = new Combo_Box();
			startBox.new Combo_Label_Txt(comboTitle_);
			startBox.new Combo_Button();
			startBox.new Combo_Box_Set(browsersList_);

	} //eomain

}//eoclass Combo_Box
