/***********************************************************************
 * Module:  Atribut.java
 * Author:  PC
 * Purpose: Defines the Class Atribut
 ***********************************************************************/

package tree.treeModel;


import tree.treeView.AtributView;


/** @pdOid 980d2c99-92ce-42b1-ae3c-c7f1b7a98cac */
public class Atribut extends Node {
  
	private String type;
	private int lenght;
	private boolean isKey;
	
   /** @pdOid 47e9c842-a685-4d99-9cf0-6ee645653f44 */
   public Atribut() {
	   setTreeView(new AtributView(this));
	   
   }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
}