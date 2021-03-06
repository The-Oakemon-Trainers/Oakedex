package com.github.theoakemontrainers.oakedex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.text.NumberFormat;

public class OakSearch extends JFrame implements ActionListener {

	private Container frame = getContentPane();
	
	private JMenuBar menuBar;
	private JMenu menuBack, menuScreen, menuSubmit;
	private JMenuItem itemMenu, itemExit, itemFull, itemExitFull, itemSubmit;

	private JPanel col1, col2, col3;
	
	// Column 1 fields
	private JTextField txtName, txtAbility;
	private JComboBox cmbType;
	private JCheckBox chkNormalAbil, chkHiddenAbil, chkNormalType, chkFightingType, chkFlyingType, chkPoisonType, chkGroundType, chkRockType, chkBugType, chkGhostType, chkSteelType, chkFireType, chkWaterType, chkGrassType, chkElectricType, chkPsychicType, chkIceType, chkDragonType, chkDarkType, chkFairyType;
	
	// Column 2 fields
	private JComboBox cmbGender1, cmbGender2, cmbMoves, cmbForm;
	private JTextField txtMoves1, txtMoves2, txtMoves3, txtMoves4, txtMovesLvl;
	private JCheckBox chkGen1, chkGen2, chkGen3, chkGen4, chkGen5, chkGen6, chkGen7, chkSameEff, chkLvl, chkEvolution, chkMachine, chkTutor, chkEgg, chkAlolaForm, chkMega;
	
	// Column 3 fields
	private JTextField txtNumber;
	private JCheckBox chkIn1, chkIn2, chkIn3, chkIn4, chkIn5, chkIn6, chkIn7, chkIn8, chkIn9, chkIn10, chkIn11, chkIn12, chkIn13, chkIn14, chkIn15, chkIn16, chkIn17, chkIn18, chkIn19, chkIn20, chkIn21, chkIn22, chkIn23, chkEx1, chkEx2, chkEx3, chkEx4, chkEx5, chkEx6, chkEx7, chkEx8, chkEx9, chkEx10, chkEx11, chkEx12, chkEx13, chkEx14, chkEx15, chkEx16, chkEx17, chkEx18, chkEx19, chkEx20, chkEx21, chkEx22, chkEx23;
	
	
	// Remove comment to test
//	public static void main (String[] args)
//	{
//		OakSearch searchPage = new OakSearch();
//		searchPage.setVisible(true);
//	}
	
	public OakSearch()
	{
		setTitle("Oakedex Search");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	    
	    menuSubmit = new JMenu("Submit");
	    menuBar.add(menuSubmit);
	    
	    itemSubmit = new JMenuItem("Submit");
	    itemSubmit.addActionListener(this);
	    menuSubmit.add(itemSubmit);
	    
	    
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
	    txtAbility = new JTextField();
	    col1.add(txtAbility);
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
	    
	    class Option
	    {
	    	public String displayMember;
	    	public int valueMember;
	    	
	    	public Option(String display, int value)
	    	{
	    		displayMember = display;
	    		valueMember = value;
	    	}
	    	
	    	public String toString()
	    	{
	    		return displayMember.toString();
	    	}
	    	
	    	public int hashCode()
	    	{
	    		return valueMember;
	    	}
	    }
	    
	    col1.add(new JLabel("Types:", SwingConstants.RIGHT));
	    cmbType = new JComboBox<Option>();
	    cmbType.addItem(new Option("At least:", 1));
	    cmbType.addItem(new Option("Only:", 2));
	    cmbType.addItem(new Option("One of:", 3));
	    cmbType.addItem(new Option("Only one of:", 4));
	    cmbType.addItem(new Option("Two of:", 5));
	    cmbType.addItem(new Option("Not:", 6));
	    cmbType.addItem(new Option("Not just:", 7));
	    cmbType.addItem(new Option("Primarily:", 8));
	    cmbType.addItem(new Option("Secondly:", 9));
	    
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
	    
	    col2EmptySpace();
	    
	    col2.add(new JLabel("Gender:", SwingConstants.RIGHT));
	    cmbGender1 = new JComboBox<Option>();
	    cmbGender1.addItem(new Option("At least:", 1));
	    cmbGender1.addItem(new Option("Exactly:", 2));
	    cmbGender1.addItem(new Option("At most:", 3));
	    col2.add(cmbGender1);
	    col2.add(new JLabel());
	    
	    col2.add(new JLabel());
	    cmbGender2 = new JComboBox<Option>();
	    cmbGender2.addItem(new Option("", -2));
	    cmbGender2.addItem(new Option("Genderless", -1));
	    cmbGender2.addItem(new Option("Never female", 0));
	    cmbGender2.addItem(new Option("1/8 female", 1));
	    cmbGender2.addItem(new Option("1/4 female", 2));
	    cmbGender2.addItem(new Option("3/8 female", 3));
	    cmbGender2.addItem(new Option("1/2 female", 4));
	    cmbGender2.addItem(new Option("5/8 female", 5));
	    cmbGender2.addItem(new Option("3/4 female", 6));
	    cmbGender2.addItem(new Option("7/8 female", 7));
	    cmbGender2.addItem(new Option("All female", 8));
	    col2.add(cmbGender2);
	    col2.add(new JLabel());
	    
	    col2EmptySpace();
	    
	    col2.add(new JLabel("Regional Info:", SwingConstants.RIGHT));
	    col2.add(new JLabel());
	    col2.add(new JLabel());
	    
	    col2.add(new JLabel("Gen 1:", SwingConstants.RIGHT));
	    chkGen1 = new JCheckBox();
	    col2.add(chkGen1);
	    col2.add(new JLabel());
	    
	    col2.add(new JLabel("Gen 2:", SwingConstants.RIGHT));
	    chkGen2 = new JCheckBox();
	    col2.add(chkGen2);
	    col2.add(new JLabel());
	    
	    col2.add(new JLabel("Gen 3:", SwingConstants.RIGHT));
	    chkGen3 = new JCheckBox();
	    col2.add(chkGen3);
	    col2.add(new JLabel());
	    
	    col2.add(new JLabel("Gen 4:", SwingConstants.RIGHT));
	    chkGen4 = new JCheckBox();
	    col2.add(chkGen4);
	    col2.add(new JLabel());
	    
	    col2.add(new JLabel("Gen 5:", SwingConstants.RIGHT));
	    chkGen5 = new JCheckBox();
	    col2.add(chkGen5);
	    col2.add(new JLabel());
	    
	    col2.add(new JLabel("Gen 6:", SwingConstants.RIGHT));
	    chkGen6 = new JCheckBox();
	    col2.add(chkGen6);
	    col2.add(new JLabel());
	    
	    col2.add(new JLabel("Gen 7:", SwingConstants.RIGHT));
	    chkGen7 = new JCheckBox();
	    col2.add(chkGen7);
	    col2.add(new JLabel());
	    
	    col2EmptySpace();
	    
	    col2.add(new JLabel("Moves:", SwingConstants.RIGHT));
	    cmbMoves = new JComboBox<Option>();
	    cmbMoves.addItem(new Option("Any of:", 1));
	    cmbMoves.addItem(new Option("All of:", 2));
	    cmbMoves.addItem(new Option("None of:", 3));
	    cmbMoves.enable(false); //<------------------------------------
	    col2.add(cmbMoves);
	    col2.add(new JLabel());
	    
	    col2.add(new JLabel("Any with same effect:", SwingConstants.RIGHT));
	    chkSameEff = new JCheckBox();
	    chkSameEff.setEnabled(false); //<------------------------------------
	    col2.add(chkSameEff);
	    col2.add(new JLabel());
	    
	    col2.add(new JLabel());
	    txtMoves1 = new JTextField();
	    txtMoves2 = new JTextField();
	    txtMoves1.enable(false); //<----------------------------------
	    txtMoves2.enable(false); //<---------------------------------
	    col2.add(txtMoves1);
	    col2.add(txtMoves2);
	    
	    col2.add(new JLabel());
	    txtMoves3 = new JTextField();
	    txtMoves4 = new JTextField();
	    txtMoves3.enable(false); //<---------------------------------
	    txtMoves4.enable(false); //<---------------------------------
	    col2.add(txtMoves3);
	    col2.add(txtMoves4);
	    
	    col2.add(new JLabel("At any level", SwingConstants.RIGHT));
	    chkLvl = new JCheckBox();
	    chkLvl.setEnabled(false); //<---------------------------
	    col2.add(chkLvl);
	    col2.add(new JLabel());
	    
	    col2.add(new JLabel("At level:", SwingConstants.RIGHT));
	    txtMovesLvl = new JTextField();
	    txtMovesLvl.enable(false); //<----------------------------
	    col2.add(txtMovesLvl);
	    col2.add(new JLabel());
	    
	    col2.add(new JLabel("Upon evolution", SwingConstants.RIGHT));
	    chkEvolution = new JCheckBox();
	    chkEvolution.setEnabled(false); //<--------------------------------
	    col2.add(chkEvolution);
	    col2.add(new JLabel());
	    
	    col2.add(new JLabel("From any machine", SwingConstants.RIGHT));
	    chkMachine = new JCheckBox();
	    chkMachine.setEnabled(false); //<----------------------------
	    col2.add(chkMachine);
	    col2.add(new JLabel());
	    
	    col2.add(new JLabel("From a tutor", SwingConstants.RIGHT));
	    chkTutor = new JCheckBox();
	    chkTutor.setEnabled(false); //<---------------------------
	    col2.add(chkTutor);
	    col2.add(new JLabel());
	    
	    col2.add(new JLabel("As an egg", SwingConstants.RIGHT));
	    chkEgg = new JCheckBox();
	    chkEgg.setEnabled(false); //<--------------------------
	    col2.add(chkEgg);
	    col2.add(new JLabel());
	    
	    col2EmptySpace();
	    
	    col2.add(new JLabel("Form Searching:", SwingConstants.RIGHT));
	    cmbForm = new JComboBox<Option>();
	    cmbForm.addItem(new Option("Only search default forms", 1));
	    cmbForm.addItem(new Option("Search all, return only defaults", 2));
	    cmbForm.addItem(new Option("Return only one form", 3));
	    cmbForm.addItem(new Option("Return all matching forms", 4));
	    cmbForm.setSelectedIndex(3);
	    col2.add(cmbForm);
	    col2.add(new JLabel());
	    
	    col2.add(new JLabel("Separate Alola forms", SwingConstants.RIGHT));
	    chkAlolaForm = new JCheckBox();
	    chkAlolaForm.setEnabled(false); //<--------------------------------
	    col2.add(chkAlolaForm);
	    col2.add(new JLabel());
	    
	    col2.add(new JLabel("Search mega evolutions", SwingConstants.RIGHT));
	    chkMega = new JCheckBox();
	    chkMega.setEnabled(false); //<-------------------------------------
	    col2.add(chkMega);
	    col2.add(new JLabel());
	    
	    col2EmptySpace();
	    col2EmptySpace();
	    
	    frame.add(col2);
	    
	    //---------------- Column 3 ---------------------\\
	    
	    col3 = new JPanel();
	    col3.setLayout(colLayout);
	    
	    JPanel subPan1, subPan2, subPan3, subPan4, subPan5, subPan6, subPan7, subPan8, subPan9, subPan10, subPan11, subPan12, subPan13, subPan14, subPan15, subPan16, subPan17, subPan18, subPan19, subPan20, subPan21, subPan22, subPan23;
	    
	    col3EmptySpace();
	    
	    col3.add(new JLabel("Pokedex #:", SwingConstants.RIGHT));
	    txtNumber = new JTextField();
	    col3.add(txtNumber);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel());
	    col3.add(new JLabel("in.   ex.", SwingConstants.CENTER));
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("National", SwingConstants.RIGHT));
	    subPan1 = new JPanel();
	    chkIn1 = new JCheckBox();
	    chkEx1 = new JCheckBox();
	    chkEx1.setEnabled(false); //<-------------------------
	    subPan1.add(chkIn1);
	    subPan1.add(chkEx1);
	    col3.add(subPan1);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Johto (II)", SwingConstants.RIGHT));
	    subPan2 = new JPanel();
	    chkIn2 = new JCheckBox();
	    chkEx2 = new JCheckBox();
	    subPan2.add(chkIn2);
	    subPan2.add(chkEx2);
	    col3.add(subPan2);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Johto (IV)", SwingConstants.RIGHT));
	    subPan3 = new JPanel();
	    chkIn3 = new JCheckBox();
	    chkEx3 = new JCheckBox();
	    subPan3.add(chkIn3);
	    subPan3.add(chkEx3);
	    col3.add(subPan3);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Hoenn (III)", SwingConstants.RIGHT));
	    subPan4 = new JPanel();
	    chkIn4 = new JCheckBox();
	    chkEx4 = new JCheckBox();
	    subPan4.add(chkIn4);
	    subPan4.add(chkEx4);
	    col3.add(subPan4);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Hoenn (VI)", SwingConstants.RIGHT));
	    subPan5 = new JPanel();
	    chkIn5 = new JCheckBox();
	    chkEx5 = new JCheckBox();
	    subPan5.add(chkIn5);
	    subPan5.add(chkEx5);
	    col3.add(subPan5);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Sinnoh (D/P)", SwingConstants.RIGHT));
	    subPan6 = new JPanel();
	    chkIn6 = new JCheckBox();
	    chkEx6 = new JCheckBox();
	    subPan6.add(chkIn6);
	    subPan6.add(chkEx6);
	    col3.add(subPan6);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Sinnoh (Pt)", SwingConstants.RIGHT));
	    subPan7 = new JPanel();
	    chkIn7 = new JCheckBox();
	    chkEx7 = new JCheckBox();
	    subPan7.add(chkIn7);
	    subPan7.add(chkEx7);
	    col3.add(subPan7);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Unova (BW)", SwingConstants.RIGHT));
	    subPan8 = new JPanel();
	    chkIn8 = new JCheckBox();
	    chkEx8 = new JCheckBox();
	    subPan8.add(chkIn8);
	    subPan8.add(chkEx8);
	    col3.add(subPan8);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Unova (B2W2)", SwingConstants.RIGHT));
	    subPan9 = new JPanel();
	    chkIn9 = new JCheckBox();
	    chkEx9 = new JCheckBox();
	    subPan9.add(chkIn9);
	    subPan9.add(chkEx9);
	    col3.add(subPan9);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Kalos (All)", SwingConstants.RIGHT));
	    subPan10 = new JPanel();
	    chkIn10 = new JCheckBox();
	    chkEx10 = new JCheckBox();
	    chkIn10.setEnabled(false); //<-----------------------------
	    chkEx10.setEnabled(false); //<---------------------------
	    subPan10.add(chkIn10);
	    subPan10.add(chkEx10);
	    col3.add(subPan10);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Kalos (Coastal)", SwingConstants.RIGHT));
	    subPan11 = new JPanel();
	    chkIn11 = new JCheckBox();
	    chkEx11 = new JCheckBox();
	    subPan11.add(chkIn11);
	    subPan11.add(chkEx11);
	    col3.add(subPan11);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Kalos (Central)", SwingConstants.RIGHT));
	    subPan12 = new JPanel();
	    chkIn12 = new JCheckBox();
	    chkEx12 = new JCheckBox();
	    subPan12.add(chkIn12);
	    subPan12.add(chkEx12);
	    col3.add(subPan12);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Kalos (Mountain)", SwingConstants.RIGHT));
	    subPan13 = new JPanel();
	    chkIn13 = new JCheckBox();
	    chkEx13 = new JCheckBox();
	    subPan13.add(chkIn13);
	    subPan13.add(chkEx13);
	    col3.add(subPan13);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Alola (All - SM)", SwingConstants.RIGHT));
	    subPan14 = new JPanel();
	    chkIn14 = new JCheckBox();
	    chkEx14 = new JCheckBox();
	    subPan14.add(chkIn14);
	    subPan14.add(chkEx14);
	    col3.add(subPan14);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Alola (Melemele - SM)", SwingConstants.RIGHT));
	    subPan15 = new JPanel();
	    chkIn15 = new JCheckBox();
	    chkEx15 = new JCheckBox();
	    subPan15.add(chkIn15);
	    subPan15.add(chkEx15);
	    col3.add(subPan15);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Alola (Akala - SM)", SwingConstants.RIGHT));
	    subPan16 = new JPanel();
	    chkIn16 = new JCheckBox();
	    chkEx16 = new JCheckBox();
	    subPan16.add(chkIn16);
	    subPan16.add(chkEx16);
	    col3.add(subPan16);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Alola (Ula'ula - SM)", SwingConstants.RIGHT));
	    subPan17 = new JPanel();
	    chkIn17 = new JCheckBox();
	    chkEx17 = new JCheckBox();
	    subPan17.add(chkIn17);
	    subPan17.add(chkEx17);
	    col3.add(subPan17);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Alola (Poni - SM)", SwingConstants.RIGHT));
	    subPan18 = new JPanel();
	    chkIn18 = new JCheckBox();
	    chkEx18 = new JCheckBox();
	    subPan18.add(chkIn18);
	    subPan18.add(chkEx18);
	    col3.add(subPan18);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Alola (All - USUM)", SwingConstants.RIGHT));
	    subPan19 = new JPanel();
	    chkIn19 = new JCheckBox();
	    chkEx19 = new JCheckBox();
	    subPan19.add(chkIn19);
	    subPan19.add(chkEx19);
	    col3.add(subPan19);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Alola (Melemele - USUM)", SwingConstants.RIGHT));
	    subPan20 = new JPanel();
	    chkIn20 = new JCheckBox();
	    chkEx20 = new JCheckBox();
	    subPan20.add(chkIn20);
	    subPan20.add(chkEx20);
	    col3.add(subPan20);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Alola (Akala - USUM)", SwingConstants.RIGHT));
	    subPan21 = new JPanel();
	    chkIn21 = new JCheckBox();
	    chkEx21 = new JCheckBox();
	    subPan21.add(chkIn21);
	    subPan21.add(chkEx21);
	    col3.add(subPan21);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Alola (Ula'ula - USUM)", SwingConstants.RIGHT));
	    subPan22 = new JPanel();
	    chkIn22 = new JCheckBox();
	    chkEx22 = new JCheckBox();
	    subPan22.add(chkIn22);
	    subPan22.add(chkEx22);
	    col3.add(subPan22);
	    col3.add(new JLabel());
	    
	    col3.add(new JLabel("Alola (Poni - USUM)", SwingConstants.RIGHT));
	    subPan23 = new JPanel();
	    chkIn23 = new JCheckBox();
	    chkEx23 = new JCheckBox();
	    subPan23.add(chkIn23);
	    subPan23.add(chkEx23);
	    col3.add(subPan23);
	    col3.add(new JLabel());
	    
	    col3EmptySpace();
	    col3EmptySpace();
	    
	    frame.add(col3);
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
		
		if (ae.getSource() == itemSubmit)
		{
			
			OakMain.search.resetSearch();
			
			if (!(txtName.getText() == null||txtName.getText().isEmpty())) {
				OakMain.search.filterName(txtName.getText());
			}
			
			if (!(txtAbility.getText() == null||txtAbility.getText().isEmpty()))			{
				OakMain.search.filterAbilities(txtAbility.getText(), chkNormalAbil.isSelected(), chkHiddenAbil.isSelected());
			}
			
			if (chkNormalType.isSelected() || chkFightingType.isSelected() || chkFlyingType.isSelected() || chkPoisonType.isSelected() || chkGroundType.isSelected() || chkRockType.isSelected() || chkBugType.isSelected() || chkGhostType.isSelected() || chkSteelType.isSelected() || chkFireType.isSelected() || chkWaterType.isSelected() || chkGrassType.isSelected() || chkElectricType.isSelected() || chkPsychicType.isSelected() || chkIceType.isSelected() || chkDragonType.isSelected() || chkDarkType.isSelected() || chkFairyType.isSelected())
			{
				OakMain.search.filterTypes(cmbType.getSelectedItem().hashCode(), chkNormalType.isSelected(), chkFightingType.isSelected(), chkFlyingType.isSelected(), chkPoisonType.isSelected(), chkGroundType.isSelected(), chkRockType.isSelected(), chkBugType.isSelected(), chkGhostType.isSelected(), chkSteelType.isSelected(), chkFireType.isSelected(), chkWaterType.isSelected(), chkGrassType.isSelected(), chkElectricType.isSelected(), chkPsychicType.isSelected(), chkIceType.isSelected(), chkDragonType.isSelected(), chkDarkType.isSelected(), chkFairyType.isSelected());
			}
			
			if (!(cmbGender2 == null || cmbGender2.equals(""))) {
				OakMain.search.filterGender(cmbGender1.getSelectedItem().hashCode(), cmbGender2.getSelectedItem().hashCode());
			}

			if (chkGen1.isSelected() || chkGen2.isSelected() || chkGen3.isSelected() || chkGen4.isSelected() || chkGen5.isSelected() || chkGen6.isSelected() || chkGen7.isSelected())
			{
				OakMain.search.filterGeneration(chkGen1.isSelected(), chkGen2.isSelected(), chkGen3.isSelected(), chkGen4.isSelected(), chkGen5.isSelected(), chkGen6.isSelected(), chkGen7.isSelected());
			}
			
			if (txtNumber.getText().length() > 0 || chkIn1.isSelected() || chkIn2.isSelected() || chkIn3.isSelected() || chkIn4.isSelected() || chkIn5.isSelected() || chkIn6.isSelected() || chkIn7.isSelected() || chkIn8.isSelected() || chkIn9.isSelected() || chkIn11.isSelected() || chkIn12.isSelected() || chkIn13.isSelected() || chkIn14.isSelected() || chkIn15.isSelected() || chkIn16.isSelected() || chkIn17.isSelected() || chkIn18.isSelected() || chkIn19.isSelected() || chkIn20.isSelected() || chkIn21.isSelected() || chkIn22.isSelected() || chkIn23.isSelected())
			{
				Integer val = null;
				try {
					val = Integer.parseInt(txtNumber.getText());
				} catch (NumberFormatException ex) {
					// do nothing
				}
				OakMain.search.filterIncludedDex(val, chkIn1.isSelected(), chkIn2.isSelected(), chkIn3.isSelected(), chkIn4.isSelected(), chkIn5.isSelected(), chkIn6.isSelected(), chkIn7.isSelected(), chkIn8.isSelected(), chkIn9.isSelected(), chkIn11.isSelected(), chkIn12.isSelected(), chkIn13.isSelected(), chkIn14.isSelected(), chkIn15.isSelected(), chkIn16.isSelected(), chkIn17.isSelected(), chkIn18.isSelected(), chkIn19.isSelected(), chkIn20.isSelected(), chkIn21.isSelected(), chkIn22.isSelected(), chkIn23.isSelected());
			}
			
			if (chkEx2.isSelected() || chkEx3.isSelected() || chkEx4.isSelected() || chkEx5.isSelected() || chkEx6.isSelected() || chkEx7.isSelected() || chkEx8.isSelected() || chkEx9.isSelected() || chkEx11.isSelected() || chkEx12.isSelected() || chkEx13.isSelected() || chkEx14.isSelected() || chkEx15.isSelected() || chkEx16.isSelected() || chkEx17.isSelected() || chkEx18.isSelected() || chkEx19.isSelected() || chkEx20.isSelected() || chkEx21.isSelected() || chkEx22.isSelected() || chkEx23.isSelected())
			{
				OakMain.search.filterExcludedDex(chkEx2.isSelected(), chkEx3.isSelected(), chkEx4.isSelected(), chkEx5.isSelected(), chkEx6.isSelected(), chkEx7.isSelected(), chkEx8.isSelected(), chkEx9.isSelected(), chkEx11.isSelected(), chkEx12.isSelected(), chkEx13.isSelected(), chkEx14.isSelected(), chkEx15.isSelected(), chkEx16.isSelected(), chkEx17.isSelected(), chkEx18.isSelected(), chkEx19.isSelected(), chkEx20.isSelected(), chkEx21.isSelected(), chkEx22.isSelected(), chkEx23.isSelected());
			}
			
			//Testing ComboBox value 
			//System.out.println(cmbGender2.getSelectedItem().hashCode());

			OakMain.search.filterForms(cmbForm.getSelectedItem().hashCode());
			
			dispose();
			OakResults resultsPage = new OakResults();
			
			ArrayList<String> list = OakMain.search.getResults();
			System.out.println("test");
			for (int i = 0; i < list.size(); i++)
			{
				resultsPage.addToResults(list.get(i));
			}
			
			resultsPage.setVisible(true);
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
