/***********************************************************************
 * Module:  GuiComponent.java
 * Author:  User
 * Purpose: Defines the Interface GuiComponent
 ***********************************************************************/

package appFramework;

import java.util.*;

import gui.GuiImplementation;
import tree.treeModel.TreeModelImplementacion;

/** @pdOid 149052a2-72e2-4390-8ad3-a8aeb52f2bb4 */
public interface GuiComponent {
   void createGui(TreeModelImplementacion model);

}