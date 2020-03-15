/***********************************************************************
 * Module:  Entitet.java
 * Author:  PC
 * Purpose: Defines the Class Entitet
 ***********************************************************************/

package tree.treeModel;

import tree.treeView.EntitetView;
import java.util.*;

/** @pdOid 568da8c7-7c25-4c0c-80b5-86f9b91df67a */
public class Entitet extends Node {
   /** @pdOid 061be757-6e0f-47cc-af26-a883857894fc */
   private EntitetView entitetView;
   
   /** @pdRoleInfo migr=no name=Atribut assc=association2 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<Atribut> atribut;
   /** @pdRoleInfo migr=no name=Relacija assc=association3 coll=java.util.Collection impl=java.util.HashSet mult=0..* type=Composition */
   public java.util.Collection<Relacija> relacija;
   
   /** @pdOid 8596f0a8-6a0e-47cc-8e03-81bd7cc6e69b */
   public Entitet() {
      // TODO: implement
   }
   
   
   /** @pdGenerated default getter */
   public java.util.Collection<Atribut> getAtribut() {
      if (atribut == null)
         atribut = new java.util.HashSet<Atribut>();
      return atribut;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorAtribut() {
      if (atribut == null)
         atribut = new java.util.HashSet<Atribut>();
      return atribut.iterator();
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
      if (this.atribut == null)
         this.atribut = new java.util.HashSet<Atribut>();
      if (!this.atribut.contains(newAtribut))
         this.atribut.add(newAtribut);
   }
   
   /** @pdGenerated default remove
     * @param oldAtribut */
   public void removeAtribut(Atribut oldAtribut) {
      if (oldAtribut == null)
         return;
      if (this.atribut != null)
         if (this.atribut.contains(oldAtribut))
            this.atribut.remove(oldAtribut);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllAtribut() {
      if (atribut != null)
         atribut.clear();
   }
   /** @pdGenerated default getter */
   public java.util.Collection<Relacija> getRelacija() {
      if (relacija == null)
         relacija = new java.util.HashSet<Relacija>();
      return relacija;
   }
   
   /** @pdGenerated default iterator getter */
   public java.util.Iterator getIteratorRelacija() {
      if (relacija == null)
         relacija = new java.util.HashSet<Relacija>();
      return relacija.iterator();
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
      if (this.relacija == null)
         this.relacija = new java.util.HashSet<Relacija>();
      if (!this.relacija.contains(newRelacija))
         this.relacija.add(newRelacija);
   }
   
   /** @pdGenerated default remove
     * @param oldRelacija */
   public void removeRelacija(Relacija oldRelacija) {
      if (oldRelacija == null)
         return;
      if (this.relacija != null)
         if (this.relacija.contains(oldRelacija))
            this.relacija.remove(oldRelacija);
   }
   
   /** @pdGenerated default removeAll */
   public void removeAllRelacija() {
      if (relacija != null)
         relacija.clear();
   }

}