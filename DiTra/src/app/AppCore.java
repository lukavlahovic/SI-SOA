/***********************************************************************
 * Module:  AppCore.java
 * Author:  User
 * Purpose: Defines the Class AppCore
 ***********************************************************************/

package app;

import java.util.*;

import appFramework.AppFramework;
import tree.treeModel.TreeModelImplementacion;

public class AppCore extends AppFramework {
	public static void main(String[] args) {
		AppFramework af = new AppCore();
		af.create();// kreira TreeModelImplementacion i GuiImplementation
		af.getGui().createGui(af.getTree()); //kreira MainFrame i povezuje View sa Modelom
		
	}
}