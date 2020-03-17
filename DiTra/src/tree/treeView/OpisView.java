package tree.treeView;

import javax.swing.tree.DefaultMutableTreeNode;

import tree.treeModel.Atribut;
import tree.treeModel.Opis;

public class OpisView extends DefaultMutableTreeNode{

	private Opis tip;
	
	public OpisView(Opis tip) {
		
		this.tip = tip;
		
	}

	public Opis getTipModel() {
		return tip;
	}
	
	
	@Override
	public String toString() {
		return tip.getName();
	}
}
