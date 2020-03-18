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