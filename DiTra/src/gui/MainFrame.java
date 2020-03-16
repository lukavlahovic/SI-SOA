/***********************************************************************
 * Module:  MainFrame.java
 * Author:  PC
 * Purpose: Defines the Class MainFrame
 ***********************************************************************/

package gui;

import controller.ActionManager;
import tree.treeModel.TreeModelImplementacion;
import gui.MyMenu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {
	private static MainFrame instance = null;
	public MainFrame mainFrameB;
	public TreeModelImplementacion model;
	public ActionManager actionManager;
	public MyMenu myMenu;
	public TabelPanel tabelPanel;
   
	private JPanel jPanel;
	private MyMenu menu;
	private JTabbedPane desktop;
	
	public void initializeModel(TreeModelImplementacion model) {
		this.model = model;
		initialiseGUI();
	}

	private MainFrame()
	{
		setActionManager(new ActionManager());
		System.out.println("Ucitan view");
	}
   
	public static MainFrame getInstance() {
		if(instance == null)
			instance = new MainFrame();
		return instance;
	}

	public void setActionManager(ActionManager actionManager) {
		this.actionManager = actionManager;
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
       
       setSize((screenWidth/2)+400, (screenHeight /2)+300);
       setTitle("DiTra-Teski klijent");
    
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLocationRelativeTo(null);
       setVisible(true);
   }
   
}
