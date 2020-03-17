package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Position;

import event.UpdateBlockEvent;
import event.UpdateBlockListener;
import tree.treeModel.Atribut;
import tree.treeModel.Entitet;
import tree.treeModel.Relacija;

//import database.UIAbstractFile;
//import database.UIDBFile;
import tree.treeView.InformacioniResursView;



public class TablePanel extends JPanel implements  UpdateBlockListener {

	
	private JTable glavnaTabela;
	private Entitet entitet;
	private JScrollPane spGlavnaTabela;
	private JScrollPane spTabelaRelacija;
	private JTabbedPane jtp;
	private JSplitPane jsp;
	private DefaultTableModel model;
	private DefaultTableModel relacijaTableModel;
	private JTable tabelaRelacija;
	//private ResultSet uiFile;
	//private JPanel panTop;
	//private Toolbar toolbar;
	
	
	public TablePanel(Entitet entitet) {
		
		setLayout(new BorderLayout());
		this.entitet = entitet;
		String[] dbType = {"TABLE"};
		//uiFile = MainFrame.getInstance().getModel().getConfig().getConnection().getMetaData().getTables(null, null, null, dbType);
		//this.uiFile.addUpdateBlockListener(this);
		
		//toolbar = new Toolbar();
		
		
		glavnaTabela = new JTable();
		model = new DefaultTableModel(null, entitet.getAtribut().toArray()); // zameniti null sa podacima iz baze
		
	
		glavnaTabela.setModel(model);
		
		
		//panTop=new JPanel(new BorderLayout());
		
		//add(toolbar, BorderLayout.NORTH);
		
		
		
		spGlavnaTabela = new JScrollPane(glavnaTabela);
		jtp = new JTabbedPane();
		
		/*if(entitet.getType().equals("sek")||entitet.getType().equals("ind")) {
		for(Relacija r: entitet.getListaRelacija())
		{
			ArrayList<Entitet> entiteti = ((PaketTree)entitet.geteT().getParent()).getPaketModel().getEntiteti();
			for(Entitet e: entiteti)
			{
				if(r.getId() == e.getId())
				{
					
					relacijaTableModel = new DefaultTableModel(uiFile.getData(),e.getListaAtributa().toArray());
					tabelaRelacija = new JTable();
					tabelaRelacija.setModel(relacijaTableModel);
					//for(Atribut a : e.getListaAtributa())
					//{
						
					//	relacijaTableModel.addColumn(a.getName());
					//	System.out.println(a.getName());
					//}
					
					//tabelaRelacija.setFillsViewportHeight(true);
					//tabelaRelacija.setPreferredScrollableViewportSize(new Dimension(900, 400));
					spTabelaRelacija = new JScrollPane(tabelaRelacija);
					String title = e.getName();
					jtp.add(title,spTabelaRelacija);
			
					
				}
				
			}
			
			
		}
		}*/
		
		
		
		
		
		
		
		
		
		
		
		jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT,spGlavnaTabela,jtp);
		jsp.setDividerLocation(350);
		this.add(jsp);
		
		
		
		
	}
	
	
	
	
	
	/*public void updateBlockPerformed(UpdateBlockEvent e) {
		glavnaTabela.setModel(new DefaultTableModel(uiFile.getData(), entitet.getAtribut().toArray()));
	}*/


	/*public UIAbstractFile getUiFile() {
		return uiFile;
	}*/


	public Entitet getEntitet() {
		return entitet;
	}





	/*public Toolbar getToolbar() {
		return toolbar;
	}*/





	public JTable getGlavnaTabela() {
		return glavnaTabela;
	}





	@Override
	public void updateBlockPerformed(UpdateBlockEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
