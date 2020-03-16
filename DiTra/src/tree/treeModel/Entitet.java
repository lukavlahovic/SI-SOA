/***********************************************************************
 * Module:  Entitet.java
 * Author:  PC
 * Purpose: Defines the Class Entitet
 ***********************************************************************/

package tree.treeModel;

import tree.treeView.AtributView;
import tree.treeView.EntitetView;
import java.util.*;


public class Entitet extends Node {
  

    
   public java.util.Collection<Atribut> atributi;
   
   public java.util.Collection<Relacija> relacije;
   

   public Entitet() {
	   setTreeView(new EntitetView(this));
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Atribut> getAtribut() {
      if (atributi == null)
         atributi = new java.util.HashSet<Atribut>();
      return atributi;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAtribut() {
      if (atributi == null)
         atributi = new java.util.HashSet<Atribut>();
      return atributi.iterator();
   }
   
   /** @pdGenerated default setter
     * @param newAtribut */
   public void setAtribut(java.util.Collection<Atribut> newAtribut) {
      removeAllAtribut();
      for (java.util.Iterator iter = newAtribut.iterator(); iter.hasNext();)
         addAtribut((Atribut)iter.next());
   }
   
   /** @pdGenerated default add
     * @param newAtribut */
   public void addAtribut(Atribut newAtribut) {
      if (newAtribut == null)
         return;
      if (this.atributi == null)
         this.atributi = new java.util.HashSet<Atribut>();
      if (!this.atributi.contains(newAtribut))
         this.atributi.add(newAtribut);
   }
   
   /** @pdGenerated default remove
     * @param oldAtribut */
   public void removeAtribut(Atribut oldAtribut) {
      if (oldAtribut == null)
         return;
      if (this.atributi != null)
         if (this.atributi.contains(oldAtribut))
            this.atributi.remove(oldAtribut);
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

}