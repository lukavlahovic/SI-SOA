/***********************************************************************
 * Module:  Entitet.java
 * Author:  PC
 * Purpose: Defines the Class Entitet
 ***********************************************************************/

package tree.treeModel;

import tree.treeView.AtributView;
import tree.treeView.EntitetView;
import java.util.*;


public class Entitet extends Node implements java.util.Observer {
  

    
   public ArrayList<Atribut> atributi;
   
   public java.util.Collection<Relacija> relacije;
   

   public Entitet() {
	   setTreeView(new EntitetView(this));
	   
   }
   
   
   /** @pdGenerated default getter */
   public ArrayList<Atribut> getAtribut() {
      if (atributi == null)
         atributi = new ArrayList<Atribut>();
      return atributi;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAtribut() {
      if (atributi == null)
         atributi = new ArrayList<Atribut>();
      return atributi.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAtribut */
   public void setAtribut(ArrayList<Atribut> newAtribut) {
      removeAllAtribut();
      for (java.util.Iterator iter = newAtribut.iterator(); iter.hasNext();)
         addAtribut((Atribut)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newAtribut */
   public void addAtribut(Node newAtribut) {
      if (newAtribut == null)
         return;
      if (this.atributi == null)
         this.atributi = new ArrayList<Atribut>();
      if (!this.atributi.contains((Atribut)newAtribut))
         this.atributi.add((Atribut)newAtribut);
   }
   
   /** @pdGenerated default remove
     * @param oldAtribut */
   public void removeAtribut(Node oldAtribut) {
      if (oldAtribut == null)
         return;
      if (this.atributi != null)
         if (this.atributi.contains((Atribut)oldAtribut))
            this.atributi.remove((Atribut)oldAtribut);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllAtribut() {
      if (atributi != null)
         atributi.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Relacija> getRelacija() {
      if (relacije == null)
         relacije = new java.util.HashSet<Relacija>();
      return relacije;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorRelacija() {
      if (relacije == null)
         relacije = new java.util.HashSet<Relacija>();
      return relacije.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newRelacija */
   public void setRelacija(java.util.Collection<Relacija> newRelacija) {
      removeAllRelacija();
      for (java.util.Iterator iter = newRelacija.iterator(); iter.hasNext();)
         addRelacija((Relacija)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newRelacija */
   public void addRelacija(Relacija newRelacija) {
      if (newRelacija == null)
         return;
      if (this.relacije == null)
         this.relacije = new java.util.HashSet<Relacija>();
      if (!this.relacije.contains(newRelacija))
         this.relacije.add(newRelacija);
   }
   
   /** @pdGenerated default remove
     * @param oldRelacija */
   public void removeRelacija(Relacija oldRelacija) {
      if (oldRelacija == null)
         return;
      if (this.relacije != null)
         if (this.relacije.contains(oldRelacija))
            this.relacije.remove(oldRelacija);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllRelacija() {
      if (relacije != null)
         relacije.clear();
   }


@Override
public void update(Observable o, Object arg) {
	
	try {
		if(this.atributi.contains((Atribut)arg))
			this.getTreeView().add(((Atribut)arg).getTreeView());
	} catch (Exception e) {
		// TODO: handle exception
	}


}

}