/***********************************************************************
 * Module:  InformacioniResurs.java
 * Author:  PC
 * Purpose: Defines the Class InformacioniResurs
 ***********************************************************************/

package tree.treeModel;

import tree.treeView.InformacioniResursView;
import java.util.*;

/** @pdOid dd6c4f7a-f25d-4619-a9ea-4fb47adfa57c */
public class InformacioniResurs extends Node {
   /** @pdOid 06aca534-00ed-4d78-ace6-3798a76edfca */
   private InformacioniResursView informacioniResursView;
   
   /** @pdRoleInfo migr=no name=Entitet assc=association1 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<Entitet> entitet;
   
   /** @pdOid 2769d915-178a-42c6-af91-5c3b03e90c76 */
   public InformacioniResurs() {
      // TODO: implement
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Entitet> getEntitet() {
      if (entitet == null)
         entitet = new java.util.HashSet<Entitet>();
      return entitet;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorEntitet() {
      if (entitet == null)
         entitet = new java.util.HashSet<Entitet>();
      return entitet.iterator();
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
   public void addEntitet(Entitet newEntitet) {
      if (newEntitet == null)
         return;
      if (this.entitet == null)
         this.entitet = new java.util.HashSet<Entitet>();
      if (!this.entitet.contains(newEntitet))
         this.entitet.add(newEntitet);
   }
   
   /** @pdGenerated default remove
     * @param oldEntitet */
   public void removeEntitet(Entitet oldEntitet) {
      if (oldEntitet == null)
         return;
      if (this.entitet != null)
         if (this.entitet.contains(oldEntitet))
            this.entitet.remove(oldEntitet);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllEntitet() {
      if (entitet != null)
         entitet.clear();
   }

}