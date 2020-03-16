/***********************************************************************
 * Module:  InformacioniResurs.java
 * Author:  PC
 * Purpose: Defines the Class InformacioniResurs
 ***********************************************************************/

package tree.treeModel;

import tree.treeView.InformacioniResursView;
import java.util.*;


public class InformacioniResurs extends Node implements java.util.Observer {

   
  
   public java.util.Collection<Entitet> entiteti;
   
  
   public InformacioniResurs() {
      setTreeView(new InformacioniResursView(this));
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Entitet> getEntitet() {
      if (entiteti == null)
         entiteti = new java.util.HashSet<Entitet>();
      return entiteti;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorEntitet() {
      if (entiteti == null)
         entiteti = new java.util.HashSet<Entitet>();
      return entiteti.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newEntitet */
   public void setEntitet(java.util.Collection<Entitet> newEntitet) {
      removeAllEntitet();
      for (java.util.Iterator iter = newEntitet.iterator(); iter.hasNext();)
         addEntitet((Entitet)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newEntitet */
   public void addEntitet(Node newEntitet) {
      if (newEntitet == null)
         return;
      if (this.entiteti == null)
         this.entiteti = new java.util.HashSet<Entitet>();
      if (!this.entiteti.contains((Entitet)newEntitet))
         this.entiteti.add((Entitet)newEntitet);
   }
   
   /** @pdGenerated default remove
     * @param oldEntitet */
   public void removeEntitet(Node oldEntitet) {
      if (oldEntitet == null)
         return;
      if (this.entiteti != null)
         if (this.entiteti.contains((Entitet)oldEntitet))
            this.entiteti.remove((Entitet)oldEntitet);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllEntitet() {
      if (entiteti != null)
         entiteti.clear();
   }


@Override
  public void update(Observable o, Object arg) {
	 
	try {
		if(this.entiteti.contains((Entitet)arg))
			this.getTreeView().add(((Entitet)arg).getTreeView());
	} catch (Exception e) {
		// TODO: handle exception
	}
		
  }

}