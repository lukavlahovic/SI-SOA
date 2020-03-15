/***********************************************************************
 * Module:  GuiImplementation.java
 * Author:  User
 * Purpose: Defines the Class GuiImplementation
 ***********************************************************************/

package appFramework;

import gui.MainFrame;
import java.util.*;

import appFramework.GuiComponent;

/** @pdOid 0d8f60f8-d150-43a8-ac11-b9222a109cd3 */
public class GuiImplementation implements GuiComponent {
   /** @pdRoleInfo migr=no name=MainFrame assc=association1 mult=1..1 type=Composition */
   public MainFrame mainFrame;
   
   /** @pdOid e832d696-1900-4fb0-a3b4-d47a32de85d2 */
   public void createGui();

}
