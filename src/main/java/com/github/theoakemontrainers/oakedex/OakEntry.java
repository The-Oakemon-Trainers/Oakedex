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

public class OakEntry extends JFrame implements ActionListener {
   // commented code is old examples of things that could be added... I
   // don't want to look up how to do them later.
   
   private Container frame = getContentPane();
   
   private JMenuBar menuBar;
   private JMenu menuBack, menuScreen;
   private JMenuItem itemResults, itemSearch, itemMenu, itemExit, itemFull, itemExitFull;
   
   private JLabel lblName, lblNum, lblType1, lblType2, lblGender,
         lblGeneration, lblRegion;
   private JLabel LBLSTAT, LBLABILITY;
   private JTable statTable, abilityTable;
   
   private String statVal1, statVal2, statVal3, statVal4, ability1, ability2, ability3;
   
   private JFileChooser fileChooser = new JFileChooser();
   
   private DataInputStream dataIn;
   private FileInputStream fileIn;
   
   public static void main(String[] args) {
      OakEntry page = new OakEntry();
      page.setVisible(true);
      
   }
   
   public OakEntry() {
	  setExtendedState(JFrame.MAXIMIZED_BOTH);
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  setTitle("Pokemon Entry");
      frame.setLayout(new GridLayout());
      
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
      itemSearch.addActionListener(this);
      
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
      
      lblName = new JLabel("Name");
      frame.add(lblName);
      
      lblNum = new JLabel("Number");
      frame.add(lblNum);
      
      lblType1 = new JLabel("Type1");
      frame.add(lblType1);
      
      lblType2 = new JLabel("(Type2)"); // Remove default text later to
                                        // avoid displaying 'Type2' for
                                        // Pokemon with no second type
      frame.add(lblType2);
      
      LBLSTAT = new JLabel("Base Stats:");
      frame.add(LBLSTAT);
      String[][] data = { { "Strength", statVal1 }, { "Defense", statVal2 },		// I don't know what stats we're going to display.
         { "etc.", "etc." } };														// As soon as I do, I can update this.
      String[] colHeads = { "Stat", "Value" };
      statTable = new JTable(data, colHeads);
      JScrollPane statPane = new JScrollPane(statTable);
      statTable.setFillsViewportHeight(true);
      frame.add(statPane, BorderLayout.CENTER);
      
      lblGender = new JLabel("(gender ratio)");
      frame.add(lblGender);
      
      lblGeneration = new JLabel("generation");
      frame.add(lblGeneration);
      
      LBLABILITY = new JLabel("Abilities:");
      frame.add(LBLABILITY);
      String[][] dataAbility = { { ability1, ability3 },
         { ability2, "" } };
      String[] abilityColHeads = { "Ability", "Hidden" };
      abilityTable = new JTable(dataAbility, abilityColHeads);
      JScrollPane abilityPane = new JScrollPane(abilityTable);
      abilityTable.setFillsViewportHeight(true);
      frame.add(abilityPane);
      
      lblRegion = new JLabel("Region");
      frame.add(lblRegion);  
      
      //------------ Set Data ------------------\\
      setGUI();
   }
   
   public void setGUI()
   {
	   //This is where we set the text for the pokemon's data when the page is opened.
	   //I just don't know the object name or variable names yet, so I can't really fill this out at the moment.
   }
   
   public void actionPerformed(ActionEvent ae) {
      if (ae.getSource() == itemExit) // Closes the program if you hit exit
      {
         System.exit(0);
      }
   }
   
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
            
            /*******************************************************************************************
             * String data = dataIn.readLine(); while (data != null) { if
             * (data != null||data != "") System.out.println(data); //More
             * or less of confirmation that it works in testing data =
             * dataIn.readLine(); }
             *******************************************************************************************/
         } catch (IOException ioe) {
            JOptionPane.showMessageDialog(this, ioe.toString(), "Error",
                  JOptionPane.ERROR_MESSAGE);
            System.exit(1);
         }
      }
   }
   
   /*
    * Yes, I have this whole class as an example. You have no idea how much
    * of this topic I forgot. At least I cut out a lot of the functions.
    * public class ProcessRec extends JDialog {
    * 
    * private JButton btnins, btndel, btndis, btnhid; private JTextField
    * txttool, txtid, txtprice, txtqual, txtstock, txtmes; private JLabel
    * lbltitle, lbltool, lblid, lblprice, lblqual, lblstock, lblmes; public
    * String messageout[] = new String[10]; private JLabel lblmes2[] = new
    * JLabel[10]; private Font f1 = new Font("serif", Font.BOLD, 24);
    * private Font f2 = new Font("serif", Font.PLAIN, 20); private Font f3
    * = new Font("serif", Font.PLAIN, 10); private Container frame =
    * getContentPane(); private Tools tools = new Tools();
    * 
    * 
    * public static void main(String[] args) { ProcessRec timframe = new
    * ProcessRec(); timframe.setSize(450,750); timframe.setVisible(true);
    * timframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); }
    * 
    * 
    * 
    * public ProcessRec() { setTitle("Tim's Tools"); this.setSize(425,750);
    * frame.setLayout(new FlowLayout());
    * 
    * lbltitle = new JLabel("Tiny Tim's Tool Warehouse, Inc.");
    * lbltitle.setFont(f1); frame.add(lbltitle); lbltool = new
    * JLabel("Enter ToolName:"); lbltool.setFont(f2); frame.add(lbltool);
    * txttool = new JTextField(30); frame.add(txttool); lblid = new
    * JLabel("Enter ID:    "); lblid.setFont(f2); frame.add(lblid); txtid =
    * new JTextField(30); frame.add(txtid); lblprice = new
    * JLabel("BasePrice:"); lblprice.setFont(f2); frame.add(lblprice);
    * txtprice = new JTextField(30); frame.add(txtprice); lblqual = new
    * JLabel("Enter Quality:"); lblqual.setFont(f2); frame.add(lblqual);
    * txtqual = new JTextField(30); frame.add(txtqual); lblstock = new
    * JLabel("Enter NumberInStock:"); lblstock.setFont(f2);
    * frame.add(lblstock); txtstock = new JTextField(30);
    * frame.add(txtstock); btnins = new JButton("Insert");
    * btnins.setPreferredSize(new Dimension(85, 25)); frame.add(btnins);
    * btndel = new JButton("Delete"); btndel.setPreferredSize(new
    * Dimension(85, 25)); frame.add(btndel); btndis = new
    * JButton("Display"); btndis.setPreferredSize(new Dimension(85, 25));
    * frame.add(btndis); btnhid = new JButton("Hide");
    * btnhid.setPreferredSize(new Dimension(85, 25)); frame.add(btnhid);
    * lblmes = new JLabel("Messages:     "); lblmes.setFont(f2);
    * frame.add(lblmes); txtmes = new JTextField(30); frame.add(txtmes);
    * 
    * for (int i = 0; i < 10; i++) { lblmes2[i] = new JLabel("");
    * lblmes2[i].setFont(f3); frame.add(lblmes2[i]); }
    * 
    * ActionHandler ahandle = new ActionHandler();
    * txttool.addActionListener(ahandle); txtid.addActionListener(ahandle);
    * txtprice.addActionListener(ahandle);
    * txtqual.addActionListener(ahandle);
    * txtstock.addActionListener(ahandle);
    * txtmes.addActionListener(ahandle); btnins.addActionListener(ahandle);
    * btndel.addActionListener(ahandle); btndis.addActionListener(ahandle);
    * btnhid.addActionListener(ahandle);
    * 
    * ItemHandler ihandle = new ItemHandler(); }
    */
}
