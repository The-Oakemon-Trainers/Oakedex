package com.github.theoakemontrainers.oakedex;

import javax.swing.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.net.URL;
import java.util.StringTokenizer;
import java.text.NumberFormat;

public class OakResults extends JFrame implements ActionListener {

	private JMenuBar menuBar;
	private JMenu menuBack, menuScreen;
	private JMenuItem itemSearch, itemMenu, itemExit, itemFull, itemExitFull;
	
	private Container frame = getContentPane();
	
	private JPanel middle;
	
	private int resultNum = 0;
	
	// Remove comment for testing
//	public static void main (String[] args)
//	{
//		OakResults resultsPage = new OakResults();
//		resultsPage.setVisible(true);
//	}
	
	public OakResults()
	{
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Oakedex Search Results");
		GridLayout resultLayout = new GridLayout(1, 3, 20, 0);
	    frame.setLayout(resultLayout);
	    
	    // ---------- Menu Bar ------------\\
	    
	    menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    
	    menuBack = new JMenu("Back");
	    menuBar.add(menuBack);
	    
	    itemSearch = new JMenuItem("Search");
	    menuBack.add(itemSearch);
	    itemSearch.addActionListener(this);
	    
	    itemMenu = new JMenuItem("Menu");
	    menuBack.add(itemMenu);
	    itemMenu.addActionListener(this);
	    
	    JSeparator separator = new JSeparator();
	    menuBack.add(separator);
	    
	    itemExit = new JMenuItem("Exit");
	    menuBack.add(itemExit);
	    itemExit.addActionListener(this);
	    
	    menuScreen = new JMenu("Screen");
	    menuBar.add(menuScreen);
	    
	    itemFull = new JMenuItem("Enter Fullscreen");
	    menuScreen.add(itemFull);
	    itemFull.addActionListener(new ActionListener()
	   		  {
	   	  		public void actionPerformed(ActionEvent ae) 
					{
	   	  			dispose();
	   	  			setExtendedState(JFrame.MAXIMIZED_BOTH);
						setUndecorated(true);
						setVisible(true);
					}
	   		  });
	    
	    itemExitFull = new JMenuItem("Exit Fullscreen");
	    menuScreen.add(itemExitFull);
	    itemExitFull.addActionListener(new ActionListener()
	  		  {
	  	  		public void actionPerformed(ActionEvent ae) 
	  	  		{
	  	  			dispose();
	  	  			setExtendedState(JFrame.MAXIMIZED_BOTH);
	  	  			setUndecorated(false);
	  	  			setVisible(true);
	  	  		}
	  		  });
	    
	    // ----------- Other ------------\\
	    
	    blankCell();
	    
	    middle = new JPanel();
	    middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
	    frame.add(new JScrollPane(middle, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
	    
	    // These are just examples. Add more if you want the scroll functionality
	    // Remove comments if you want to test it
//	    addToResults("255", "Torchic");
//	    addToResults("256", "Combusken");
//	    addToResults("257", "Blaziken");
//	    addToResults("007", "Squirtle");
//	    addToResults("002", "Ivysaur");
//	    addToResults("006", "Charizard");
//	    addToResults("133", "Eevee");
//	    addToResults("134", "Vaporeon");
//	    addToResults("470", "Leafeon");
//	    addToResults("136", "flareon");
	    
	    
	    blankCell();
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == itemExit) // Closes the program if you hit exit
	    {
	       System.exit(0);
	    }
		
		if (ae.getSource() == itemMenu)
		{
			dispose();
			new OakMenu().setVisible(true);
		}
		
		if (ae.getSource() == itemSearch)
		{
			dispose();
			new OakSearch().setVisible(true);
		}
		
		if (ae.getSource() instanceof JButton)	// This is called when you hit one of the resulting pokemon stuff
		{
			JButton clicked = (JButton)ae.getSource();	// gets the button that you hit
			String clickedText = clicked.getText();		// gets the text in the button (the ID and name of the pokemon)
			Scanner sc = new Scanner(clickedText);
			int resultID = sc.nextInt();
			
			// eventually resorts to...
			dispose();
			OakEntry resultEntry = new OakEntry(resultID);
			resultEntry.setVisible(true);
		}
	}
	
	private void blankCell()
	{
		JPanel blank = new JPanel();
		blank.setBackground(new Color(126, 0, 0));
		frame.add(blank);
	}
	
	public void addToResults(String pID, String pName)
	{
		JPanel resultPanel = new JPanel();
		JButton btnResult = new JButton(pID + " " + pName);
		
		if ((resultNum % 2) == 0)
		{
			btnResult.setBackground(new Color(255, 255, 255));
			resultPanel.setBackground(new Color(255, 255, 255));
		}
		else
		{
			btnResult.setBackground(new Color(179, 236, 255));
			resultPanel.setBackground(new Color(179, 236, 255));
		}
			
		
		resultNum++;
		
		btnResult.setBorder(BorderFactory.createEmptyBorder());
		btnResult.addActionListener(this);
		resultPanel.add(btnResult);
		
		middle.add(resultPanel);
	}
}
