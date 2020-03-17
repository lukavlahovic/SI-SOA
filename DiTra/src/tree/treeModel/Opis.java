package tree.treeModel;

import tree.treeView.AtributView;
import tree.treeView.OpisView;

public class Opis extends Node{

	public Opis() {
		   setTreeView(new OpisView(this));
		   
	}
	
}
