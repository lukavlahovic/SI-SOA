/***********************************************************************
 * Module:  Atribut.java
 * Author:  PC
 * Purpose: Defines the Class Atribut
 ***********************************************************************/

package tree.treeModel;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import tree.treeView.AtributView;


/** @pdOid 980d2c99-92ce-42b1-ae3c-c7f1b7a98cac */
public class Atribut extends Node implements Observer{
  
	private ArrayList<Opis> opisi;
	private int lenght;
	private boolean isKey;
	
   /** @pdOid 47e9c842-a685-4d99-9cf0-6ee645653f44 */
   public Atribut() {
	   setTreeView(new AtributView(this));
	   
   }

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public boolean isKey() {
		return isKey;
	}

	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}

	@Override
	public void update(Observable o, Object arg) {
		try {
			if(this.opisi.contains((Opis)arg))
				this.getTreeView().add(((Opis)arg).getTreeView());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public ArrayList<Opis> getOpisi() {
		if(opisi == null) {
			opisi = new ArrayList<>();
		}
		return opisi;
	}

	public void setOpisi(ArrayList<Opis> opisi) {
		this.opisi = opisi;
	}
	
	
}