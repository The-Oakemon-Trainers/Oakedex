package com.github.theoakemontrainers.oakedex;

import javax.swing.*;
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
	public static void main (String[] args)
	{
		OakResults resultsPage = new OakResults();
		resultsPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		resultsPage.setVisible(true);
	}
	
	public OakResults()
	{
		setExtendedState(JFrame.MAXIMIZED_BOTH);
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
	    middle.setLayout(new GridLayout(0, 1, 0, 3));
	    frame.add(middle);
	    addToResults("Torchic");
	    addToResults("Combusken");
	    addToResults("Blaziken");
	    addToResults("Squirtle");
	    addToResults("Ivysaur");
	    addToResults("Charizard");
	    
	    blankCell();
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == itemExit) // Closes the program if you hit exit
	    {
	       System.exit(0);
	    }
	}
	
	private void blankCell()
	{
		JPanel blank = new JPanel();
		blank.setBackground(new Color(126, 0, 0));
		frame.add(blank);
	}
	
	public void addToResults(String pName)
	{
		JButton btnResult = new JButton(pName);
		
		if ((resultNum % 2) == 0)
			btnResult.setBackground(new Color(255, 255, 255));
		else
			btnResult.setBackground(new Color(179, 236, 255));
		
		resultNum++;
		
		middle.add(btnResult);
	}
}
