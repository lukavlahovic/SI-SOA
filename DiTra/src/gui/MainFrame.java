/***********************************************************************
 * Module:  MainFrame.java
 * Author:  PC
 * Purpose: Defines the Class MainFrame
 ***********************************************************************/

package gui;

import tree.Model.TreeModelImplementacion;
import controller.ActionManager;
import java.util.*;

import javax.swing.JFrame;

/** @pdOid 4853b9ef-ba33-4f2b-adc8-c43ba7cb6ce2 */
public class MainFrame extends JFrame {
   /** @pdRoleInfo migr=no name=MainFrame assc=association2 mult=0..1 type=Aggregation */
   public MainFrame mainFrameB;
   /** @pdRoleInfo migr=no name=TreeModelImplementacion assc=association3 mult=1..1 type=Composition */
   public TreeModelImplementacion model;
   /** @pdRoleInfo migr=no name=ActionManager assc=association4 mult=1..1 */
   public ActionManager actionManager;
   /** @pdRoleInfo migr=no name=MyMenu assc=association5 mult=1..1 type=Composition */
   public MyMenu myMenu;
   /** @pdRoleInfo migr=no name=TabelPanel assc=association6 mult=1..1 type=Composition */
   public TabelPanel tabelPanel;
   
   /** @param model
    * @pdOid 99d4821c-0ecd-427c-98b2-276b2189035d */
   public void initializeModel(TreeModelImplementacion model) {
      // TODO: implement
   }

}
