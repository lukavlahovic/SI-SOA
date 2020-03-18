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

import database.API;
import database.AbstractAPI;
import database.FileView;
import database.Mysql;
import tree.treeModel.Atribut;
import tree.treeModel.Entitet;
import tree.treeModel.InformacioniResurs;



public class DeleteView extends JDialog
{
	private JButton btnCancel = new JButton("Cancel");
	private JButton btnDelete = new JButton("Delete");
	private JTable table;
	private Entitet entitet;
		
		public DeleteView() 
		{
			String nazivEntiteta = MainFrame.getInstance().getDesktop().getTitleAt(MainFrame.getInstance().getDesktop().getSelectedIndex());
			FileView fv = (FileView) MainFrame.getInstance().getDesktop().getSelectedComponent();
			table = fv.getTable();
			int SelectedRowIndex = table.getSelectedRow();
			InformacioniResurs informacioniResurs = MainFrame.getInstance().getModel().getWorkspaceModel().getInfResursi().get(0);
			ArrayList<Entitet>listaEntiteta =  informacioniResurs.getEntitet();
			for(Entitet e: listaEntiteta) {
				if(e.getName().equals(nazivEntiteta))
				{
					entitet = e;
					break;
				}
			}
			
			
			System.out.println(SelectedRowIndex);
			ArrayList<Atribut> atributi = entitet.getAtribut();
			TextField[] nizPolja = new TextField[atributi.size()];
			int i = 0;
			int k = 0;
			for (Atribut attribute : atributi) 
			{

				nizPolja[i] = new TextField();
				nizPolja[i].setPreferredSize(new Dimension(200, 25));
				nizPolja[i].setText(table.getValueAt(SelectedRowIndex, k++).toString());
				nizPolja[i].setEnabled(false);
				
				add(new Label(attribute.getName()));
				add(nizPolja[i++]);
			}
			setLayout(new FlowLayout());
			setSize(240, 100 * atributi.size());

			btnDelete.addActionListener(l -> {
				int j = 0;
				String[] redKojiSeDodaje = new String[nizPolja.length];
				for (TextField textField : nizPolja) 
				{
					redKojiSeDodaje[j++] = textField.getText();
				}

				try 
				{

					AbstractAPI api = new API(new Mysql());
					api.remove(entitet, redKojiSeDodaje);

				} 
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
			});
			btnCancel.addActionListener(l -> {
				
			});


			add(btnCancel);
			add(btnDelete);

			setVisible(true);

		}

}
