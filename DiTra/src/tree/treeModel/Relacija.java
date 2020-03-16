/***********************************************************************
 * Module:  Relacija.java
 * Author:  PC
 * Purpose: Defines the Class Relacija
 ***********************************************************************/

package tree.treeModel;

import java.util.*;



/** @pdOid abc1987c-f289-4570-bb33-5ae9e4cfc6cc */
public class Relacija {
	
	private int id;
	private ArrayList<Integer> rbAtributa = new ArrayList<>();

	
	public Relacija(){
		
	}
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public ArrayList<Integer> getRbAtributa() {
		return rbAtributa;
	}

	public void setRbAtributa(ArrayList<Integer> rbAtributa) {
		this.rbAtributa = rbAtributa;
	}
	
	
}