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

public class OakSearch extends JFrame implements ActionListener {

	private Container frame = getContentPane();
	
	private JMenuBar menuBar;
	private JMenu menuBack, menuScreen;
	private JMenuItem itemMenu, itemExit, itemFull, itemExitFull;
	
	// Remove comment to test
	public static void main (String[] args)
	{
		OakSearch searchPage = new OakSearch();
		searchPage.setVisible(true);
		searchPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public OakSearch()
	{
		setTitle("Oakedex Results");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		GridLayout searchLayout = new GridLayout(1, 3, 20, 0);
		frame.setLayout(searchLayout);
		frame.setBackground(new Color(126, 0, 0));
		
		// ---------- Menu Bar ------------\\
	      
	    menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    
	    menuBack = new JMenu("Back");
	    menuBar.add(menuBack);
	    
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
	    
	    //----------------- Other --------------------\\
	    
	    JPanel col1 = new JPanel();
	    frame.add(col1);
	    
	    
	    JPanel col2 = new JPanel();
	    frame.add(col2);
	    
	    
	    JPanel col3 = new JPanel();
	    frame.add(col3);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == itemExit) // Closes the program if you hit exit
	    {
	       System.exit(0);
	    }
	}
}
