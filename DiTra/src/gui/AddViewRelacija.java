package gui;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.API;
import database.AbstractAPI;
import database.FileView;
import database.FileViewRelacija;
import database.Mysql;
import tree.treeModel.Atribut;
import tree.treeModel.Entitet;
import tree.treeModel.InformacioniResurs;


public class AddViewRelacija extends JDialog
{

	private Entitet entitet;
	private JButton btnCancel = new JButton("Cancel");
	private JButton btnOk = new JButton("OK");
	private String nazivEntiteta;
	
	public AddViewRelacija() 
	{
		
		FileView fv = (FileView) MainFrame.getInstance().getDesktop().getSelectedComponent();
		FileViewRelacija fvr = (FileViewRelacija) fv.getJtp().getSelectedComponent();
		nazivEntiteta = fvr.getUiFile().getTABLE_NAME();
		System.out.println(nazivEntiteta);
		InformacioniResurs informacioniResurs = MainFrame.getInstance().getModel().getWorkspaceModel().getInfResursi().get(0);
		ArrayList<Entitet>listaEntiteta =  informacioniResurs.getEntitet();
		for(Entitet e: listaEntiteta) {
			if(e.getName().equals(nazivEntiteta))
			{
				entitet = e;
				break;
			}
		}
		
		
		ArrayList<Atribut> atributi =entitet.getAtribut();
		TextField[] nizPolja = new TextField[atributi.size()];
		int i = 0;
		for (Atribut attribute : atributi) 
		
		{

			nizPolja[i] = new TextField();
			nizPolja[i].setPreferredSize(new Dimension(200, 25));
			
			add(new Label(attribute.getName()));
			add(nizPolja[i++]);
		}
		setLayout(new FlowLayout());
		setSize(240, 100 * atributi.size());

		btnOk.addActionListener(l -> {
			int j = 0;
			String[] redKojiSeDodaje = new String[nizPolja.length];
			for (TextField textField : nizPolja) {
				redKojiSeDodaje[j++] = textField.getText();
			}

			try 
			{

				AbstractAPI api = new API(new Mysql());
				api.add(entitet, redKojiSeDodaje);
				//MainFrame.getInstance().getModel().loadMetaSema();
				//((StateView) this.getParent()).startOffState();
			} 
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
		});



		//add(btnCancel);
		add(btnOk);

		setVisible(true);

	}

}

