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

	private JPanel col1, col2, col3;
	
	// Column 1 fields
	private JTextField txtName;
	private JComboBox cmbAbility, cmbType;
	private JCheckBox chkNormalAbil, chkHiddenAbil, chkNormalType, chkFightingType, chkFlyingType, chkPoisonType, chkGroundType, chkRockType, chkBugType, chkGhostType, chkSteelType, chkFireType, chkWaterType, chkGrassType, chkElectricType, chkPsychicType, chkIceType, chkDragonType, chkDarkType, chkFairyType;
	
	// Column 2 fields
	
	
	// Column 3 fields
	
	
	
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
	    
	    GridLayout colLayout = new GridLayout(0, 3, 5, 5);	// A layout where there is an ambiguous amount of rows and two columns
	    
	    //------------- Column 1 --------------------\\
	    
	    col1 = new JPanel();
	    col1.setLayout(colLayout);
	    
	    col1EmptySpace();
	    
	    col1.add(new JLabel("Name:", SwingConstants.RIGHT));
	    txtName = new JTextField();
	    col1.add(txtName);
	    col1.add(new JLabel());
	    
	    col1EmptySpace();
	    
	    col1.add(new JLabel("Ability:", SwingConstants.RIGHT));
	    cmbAbility = new JComboBox();
	    col1.add(cmbAbility);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("As Normal:", SwingConstants.RIGHT));
	    chkNormalAbil = new JCheckBox();
	    col1.add(chkNormalAbil);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("As Hidden:", SwingConstants.RIGHT));
	    chkHiddenAbil = new JCheckBox();
	    col1.add(chkHiddenAbil);
	    col1.add(new JLabel());
	    
	    col1EmptySpace();
	    
	    col1.add(new JLabel("Types:", SwingConstants.RIGHT));
	    cmbType = new JComboBox();
	    col1.add(cmbType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Normal", SwingConstants.RIGHT));
	    chkNormalType = new JCheckBox();
	    col1.add(chkNormalType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Fighting", SwingConstants.RIGHT));
	    chkFightingType = new JCheckBox();
	    col1.add(chkFightingType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Flying", SwingConstants.RIGHT));
	    chkFlyingType = new JCheckBox();
	    col1.add(chkFlyingType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Poison", SwingConstants.RIGHT));
	    chkPoisonType = new JCheckBox();
	    col1.add(chkPoisonType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Ground", SwingConstants.RIGHT));
	    chkGroundType = new JCheckBox();
	    col1.add(chkGroundType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Rock", SwingConstants.RIGHT));
	    chkRockType = new JCheckBox();
	    col1.add(chkRockType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Bug", SwingConstants.RIGHT));
	    chkBugType = new JCheckBox();
	    col1.add(chkBugType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Ghost", SwingConstants.RIGHT));
	    chkGhostType = new JCheckBox();
	    col1.add(chkGhostType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Steel", SwingConstants.RIGHT));
	    chkSteelType = new JCheckBox();
	    col1.add(chkSteelType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Fire", SwingConstants.RIGHT));
	    chkFireType = new JCheckBox();
	    col1.add(chkFireType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Water", SwingConstants.RIGHT));
	    chkWaterType = new JCheckBox();
	    col1.add(chkWaterType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Grass", SwingConstants.RIGHT));
	    chkGrassType = new JCheckBox();
	    col1.add(chkGrassType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Electric", SwingConstants.RIGHT));
	    chkElectricType = new JCheckBox();
	    col1.add(chkElectricType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Psychic", SwingConstants.RIGHT));
	    chkPsychicType = new JCheckBox();
	    col1.add(chkPsychicType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Ice", SwingConstants.RIGHT));
	    chkIceType = new JCheckBox();
	    col1.add(chkIceType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Dragon", SwingConstants.RIGHT));
	    chkDragonType = new JCheckBox();
	    col1.add(chkDragonType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Dark", SwingConstants.RIGHT));
	    chkDarkType = new JCheckBox();
	    col1.add(chkDarkType);
	    col1.add(new JLabel());
	    
	    col1.add(new JLabel("Fairy", SwingConstants.RIGHT));
	    chkFairyType = new JCheckBox();
	    col1.add(chkFairyType);
	    col1.add(new JLabel());
	    
	    col1EmptySpace();
	    col1EmptySpace();
	    col1EmptySpace();
	    
	    frame.add(col1);
	    
	    //-------------- Column 2 --------------------\\
	    
	    col2 = new JPanel();
	    col2.setLayout(colLayout);
	    
	    frame.add(col2);
	    
	    //---------------- Column 3 ---------------------\\
	    
	    col3 = new JPanel();
	    col3.setLayout(colLayout);
	    
	    frame.add(col3);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == itemExit) // Closes the program if you hit exit
	    {
	       System.exit(0);
	    }
	}
	
	private void col1EmptySpace()
	{
		col1.add(new JLabel());
		col1.add(new JLabel());
		col1.add(new JLabel());
	}
	
	private void col2EmptySpace()
	{
		col2.add(new JLabel());
		col2.add(new JLabel());
		col2.add(new JLabel());
	}
	
	private void col3EmptySpace()
	{
		col3.add(new JLabel());
		col3.add(new JLabel());
		col3.add(new JLabel());
	}
}
