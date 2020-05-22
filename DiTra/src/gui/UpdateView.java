package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import database.*;
import tree.treeModel.Atribut;
import tree.treeModel.Entitet;
import tree.treeModel.InformacioniResurs;

public class UpdateView extends JDialog{

	private Entitet entitet;
	private JButton btnCancel = new JButton("Cancel");
	private JButton btnOk = new JButton("OK");
	private JTable table;
	
	public UpdateView() {
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
			
			add(new Label(attribute.getName()));
			add(nizPolja[i++]);
		}
		String[] staraVrednost = new String[nizPolja.length];
		for(int j=0;j<nizPolja.length;j++) {
			staraVrednost[j] = nizPolja[j].getText();
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
				AbstractAPI api = new API(new Broker());
				api.update(entitet, redKojiSeDodaje,staraVrednost);
			} 
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
		});
		btnCancel.addActionListener(l -> {
			//((StateView) this.getParent()).startOffState();
		});


		add(btnCancel);
		add(btnOk);

		setVisible(true);
	}
}
