/***********************************************************************
 * Module:  MainFrame.java
 * Author:  PC
 * Purpose: Defines the Class MainFrame
 ***********************************************************************/

package gui;


import database.FileView;
import tree.treeModel.TreeModelImplementacion;

import gui.MyMenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {
	private static MainFrame instance = null;
	public MainFrame mainFrame;
	public TreeModelImplementacion model;

	public MyMenu myMenu;
	
    private FileView fileView;
	private JPanel jPanel;
	private MyMenu menu;
	private JTabbedPane desktop;
	
	public void initializeModel(TreeModelImplementacion model) {
		this.model = model;
		initialiseGUI();
	}

	private MainFrame()
	{
		System.out.println("Ucitan view");
	}
   
	public static MainFrame getInstance() {
		if(instance == null)
			instance = new MainFrame();
		return instance;
	}


	public void initialiseGUI() {
	   Toolkit kit = Toolkit.getDefaultToolkit();
       Dimension screenSize = kit.getScreenSize();
       int screenHeight = screenSize.height;
       int screenWidth = screenSize.width;
      
      
       jPanel = new JPanel();
       jPanel.setLayout(new BorderLayout());
      
       menu = new MyMenu();
       setJMenuBar(menu);

       desktop = new JTabbedPane();
       
       JScrollPane scroll=new JScrollPane(model.getWorkspaceTree());
       
       
		scroll.setMinimumSize(new Dimension(200,150));
		JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,desktop);
		add(split,BorderLayout.CENTER);
		split.setDividerLocation(250);
		
		
		
		add(jPanel,BorderLayout.NORTH);
       
       setSize((screenWidth/2)+400, (screenHeight /2)+300);
       setTitle("DiTra-Teski klijent");
    
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
       setVisible(true);
   }

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public TreeModelImplementacion getModel() {
		return model;
	}


	public MyMenu getMyMenu() {
		return myMenu;
	}

	public JPanel getjPanel() {
		return jPanel;
	}

	public MyMenu getMenu() {
		return menu;
	}

	public JTabbedPane getDesktop() {
		return desktop;
	}
	
	public void setFileView(FileView fileView) {
		this.fileView = fileView;
		desktop.add(fileView,fileView.getUiFile().getFileName());
		desktop.setSelectedIndex(desktop.getComponentCount()-1);
	}

	public FileView getFileView() {
		return fileView;
	}
   
}
