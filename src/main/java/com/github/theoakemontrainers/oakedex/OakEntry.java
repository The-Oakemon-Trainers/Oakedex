package com.github.theoakemontrainers.oakedex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.net.URL;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.text.NumberFormat;

public class OakEntry extends JFrame implements ActionListener {
   // commented code is old examples of things that could be added... I
   // don't want to look up how to do them later.
   
   private Container frame = getContentPane();
   
   private JMenuBar menuBar;
   private JMenu menuBack, menuScreen;
   private JMenuItem itemResults, itemSearch, itemMenu, itemExit, itemFull, itemExitFull;
   
   private JLabel lblName, lblNum, lblType1, lblType2, lblGender,
         lblGeneration;
   private JTextArea lblRegion;
   //private JLabel LBLSTAT, LBLABILITY;
   private JTable statTable, abilityTable;
   
   private String name, number, type1, type2, gender, generation, region;
   private String statVal1, statVal2, statVal3, statVal4, statVal5, statVal6, ability1, ability2, ability3;
   
   private GridLayout entryLayout = new GridLayout(0, 4, 20, 0);
   
   private JFileChooser fileChooser = new JFileChooser();
   
   private DataInputStream dataIn;
   private FileInputStream fileIn;
   
//   public static void main(String[] args) {			
//      OakEntry entryPage = new OakEntry();
//      entryPage.setVisible(true);
//   }
   
   public OakEntry(int pID) {
	  setExtendedState(JFrame.MAXIMIZED_BOTH);
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  setTitle("Oakedex Pokemon Entry");
      frame.setLayout(entryLayout);
      setGUI(pID);
      
      // ---------- Menu Bar ------------\\
      
      menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      
      menuBack = new JMenu("Back");
      menuBar.add(menuBack);
      
      itemResults = new JMenuItem("Results");
      menuBack.add(itemResults);
      itemResults.addActionListener(this);
      
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
      
      lblName = new JLabel(name, SwingConstants.CENTER);
      frame.add(lblName);
      
      lblNum = new JLabel("National Dex #:  " + number, SwingConstants.CENTER);
      frame.add(lblNum);
      
      blankCell();
      blankCell();
      
      lblType1 = new JLabel("Primary Type:  " + type1, SwingConstants.CENTER);
      frame.add(lblType1);
      
      lblType2 = new JLabel("Secondary Type:  " + type2, SwingConstants.CENTER);
      frame.add(lblType2);
      
      blankCell();
      blankCell();
      
      //LBLSTAT = new JLabel("Base Stats:");
      //frame.add(LBLSTAT);
      String[][] data = { { "HP", statVal1 }, { "Attack", statVal2 },		// I don't know what stats we're going to display.
         { "Defense", statVal3 }, { "Special Attack", statVal4 }, 
         { "Special Defense", statVal5 }, { "Speed", statVal6 } };														// As soon as I do, I can update this.
      String[] colHeads = { "Stat", "Value" };
      statTable = new JTable(data, colHeads);
      JScrollPane statPane = new JScrollPane(statTable);
      statPane.setBorder(BorderFactory.createEmptyBorder());
      frame.add(statPane, BorderLayout.CENTER);
      
    //LBLABILITY = new JLabel("Abilities:");
      //frame.add(LBLABILITY);
      String[][] dataAbility = { { ability1, ability3 },
         { ability2, "" } };
      String[] abilityColHeads = { "Ability", "Hidden" };
      abilityTable = new JTable(dataAbility, abilityColHeads);
      JScrollPane abilityPane = new JScrollPane(abilityTable);
      abilityPane.setBorder(BorderFactory.createEmptyBorder());
      frame.add(abilityPane);
      
      blankCell();
      blankCell();
      
      lblGender = new JLabel("Gender Ratio:  " + gender, SwingConstants.CENTER);
      frame.add(lblGender);
      
      lblGeneration = new JLabel("Generation:  " + generation, SwingConstants.CENTER);
      frame.add(lblGeneration);
      
      blankCell();
      blankCell();
      
      lblRegion = new JTextArea("Regional Pokédex numbers:  \n" + region);
      //lblRegion.setAlignment(Component.CENTER);
      lblRegion.setEditable(false);
      lblRegion.setOpaque(false);
      frame.add(new JScrollPane(lblRegion));
      
      frame.add(new JLabel());
      
      blankCell();
   }
   
   public void setGUI(int pokemonFormID)
   {
	   HashMap<String, String> hashyboi = OakMain.info.getMainInfo(pokemonFormID);
	   number = hashyboi.get("number");
	   //lblNum.setText(number);
	   type1 = hashyboi.get("type 1");
	   //lblType1.setText(type1);
	   type2 = hashyboi.get("type 2");
	   if (type2 == null)
		   type2 = "None";
	   //lblType2.setText(type2);
	   ability1 = hashyboi.get("ability 1");
	   ability2 = hashyboi.get("ability 2");
	   ability3 = hashyboi.get("hidden ability");
	   gender = hashyboi.get("gender ratio");
	   
	   switch (gender)
	   {
	   case "-1": gender = "Genderless";
	   	break;
	   case "0": gender = "All Male";
	   	break;
	   case "1": gender = "87.5% Male";
	   	break;
	   case "2": gender = "75% Male";
	    break;
	   case "3": gender = "62.5% Male";
	   	break;
	   case "4": gender = "Half Male and Female";
	   	break;
	   case "5": gender = "62.5% Female";
	   	break;
	   case "6": gender = "75% Female";
	   	break;
	   case "7": gender = "87.5% Female";
	   	break;
	   case "8": gender = "All Female";
	   	break;
	   }
	   
	   //lblGender.setText(gender);
	   generation = hashyboi.get("generation");
	   //lblGeneration.setText(generation);
	   name = OakMain.info.getEnglishName(pokemonFormID);
	   //lblName.setText(name);
	   
	   HashMap<String, String> hashStat = OakMain.info.getStats(pokemonFormID);
	   statVal1 = hashStat.get("hp");
	   statVal2 = hashStat.get("attack");
	   statVal3 = hashStat.get("defense");
	   statVal4 = hashStat.get("special-attack");
	   statVal5 = hashStat.get("special-defense");
	   statVal6 = hashStat.get("speed");
	   
	   region = "";
	   for (String line : OakMain.info.getDexNumber(pokemonFormID)) {
		   region += "\n" + line;
	   }
   }
   
   public void blankCell()
   {
	   JPanel blank = new JPanel();
	   blank.setBackground(new Color(126, 0, 0));
	   frame.add(blank);
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
   }
   
   /*
   public void openFile() // This allows the user to choose the database
                          // file from wherever via a menu,
   { // but we could also just put the database in the same directory as
     // the java
      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // files
                                                                 // and
                                                                 // simply
                                                                 // use the
                                                                 // file
                                                                 // class
                                                                 // to get
                                                                 // it by
                                                                 // name.
      int result = fileChooser.showOpenDialog(this);
      
      if (result == JFileChooser.CANCEL_OPTION) return;
      File chosenFile = fileChooser.getSelectedFile();
      
      if (chosenFile == null || chosenFile.getName().equals(""))
         JOptionPane.showMessageDialog(this, "Invalid File", "fileInfo",
               JOptionPane.ERROR_MESSAGE);
      else {
         try {
            fileIn = new FileInputStream(chosenFile);
            dataIn = new DataInputStream(fileIn);
            
            System.out
                  .println(chosenFile.getName() + " Successfully Opened");
            JOptionPane.showMessageDialog(this,
                  chosenFile.getName() + " Successfully Opened");
            
         } catch (IOException ioe) {
            JOptionPane.showMessageDialog(this, ioe.toString(), "Error",
                  JOptionPane.ERROR_MESSAGE);
            System.exit(1);
         }
      }
   }*/
}
