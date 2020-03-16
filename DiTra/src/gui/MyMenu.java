/***********************************************************************
 * Module:  MyMenu.java
 * Author:  PC
 * Purpose: Defines the Class MyMenu
 ***********************************************************************/

package gui;

import java.util.*;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import gui.MainFrame;

public class MyMenu extends JMenuBar {
	public MyMenu() {
		
		JMenu menuFile = new JMenu("File");
		//menuFile.add(MainFrame.getInstance().getActionManager().getEditMetaSema());
			
		JMenu menuHelp = new JMenu("Help");
		//menuHelp.add(MainView.getInstance().getActionManager().getAbout());
			
			
		add(menuFile);
		add(menuHelp);
	}
}