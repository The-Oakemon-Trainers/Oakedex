package com.github.theoakemontrainers.oakedex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.net.URL;

public class OakMenu extends JFrame implements ActionListener {

	private Container frame = getContentPane();
	
	private GridLayout menuLayout = new GridLayout(0, 3);
	
	private JMenuBar menuBar;
	private JMenu menuBack, menuScreen;
	private JMenuItem itemExit, itemFull, itemExitFull;
	
	
	public static void main (String[] args)
	{
		OakMenu menuPage = new OakMenu();
		menuPage.setVisible(true);
		menuPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public OakMenu()
	{
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Oakedex Menu");
		frame.setLayout(menuLayout);
		frame.setBackground(new Color(126, 0, 0));
		
		// ---------- Menu Bar ------------\\
	      
	    menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	    
	    menuBack = new JMenu("Back");
	    menuBar.add(menuBack);
	    
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
	    
	    //---------------- Other ------------------\\
	    
	    frame.add(new JLabel());
	    frame.add(new JLabel());
	    frame.add(new JLabel());
	    frame.add(new JLabel());
	    
	    JPanel center = new JPanel();
	    center.setLayout(new FlowLayout());
	    center.setBackground(new Color(126, 0, 0));
	    JLabel lblSmallHead = new JLabel("The Oakland Community College");
	    lblSmallHead.setForeground(new Color(242, 242, 242));
	    lblSmallHead.setFont(lblSmallHead.getFont().deriveFont(12.0f));
	    center.add(lblSmallHead);
	    JLabel lblBigHead = new JLabel("OAKEDEX");
	    lblBigHead.setForeground(new Color(242, 242, 242));
	    lblBigHead.setFont(lblBigHead.getFont().deriveFont(80.0f));
	    center.add(lblBigHead);
	    frame.add(center);
	    
	    frame.add(new JLabel());
	    frame.add(new JLabel());
	    
	    JButton btnOpen = new JButton("Open");
	    btnOpen.setBackground(new Color(126, 0, 0));
	    btnOpen.setForeground(new Color(242, 242, 242));
	    btnOpen.setFont(btnOpen.getFont().deriveFont(40.0f));
	    btnOpen.setBorder(BorderFactory.createEmptyBorder());
		frame.add(btnOpen);
	    
	    frame.add(new JLabel());
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == itemExit) // Closes the program if you hit exit
	    {
	       System.exit(0);
	    }
	}
}
