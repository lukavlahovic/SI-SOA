/***********************************************************************
 * Module:  GuiImplementation.java
 * Author:  User
 * Purpose: Defines the Class GuiImplementation
 ***********************************************************************/

package gui;

import gui.MainFrame;
import tree.treeModel.TreeModelImplementacion;

import java.util.*;

import appFramework.GuiComponent;

public class GuiImplementation implements GuiComponent {
	
	public MainFrame mainFrame;
   
	public void createGui(TreeModelImplementacion model) {
	   mainFrame.getInstance().initializeModel(model);
   }

}
