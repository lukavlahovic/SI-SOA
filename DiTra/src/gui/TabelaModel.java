package gui;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import database.FileField;





@SuppressWarnings("serial")
public class TabelaModel extends DefaultTableModel{

	/**
	 * 
	 */
	

	public TabelaModel( ArrayList<FileField> fields,Object[][] data) {
		  //prvi parametar u konstruktoru nadklase se podaci u obliku matrice
		  //drugi parametar u konstruktoru su nazivi polja u headeru tabele 
		
		
		try{
		   setDataVector(data,fields.toArray());
		}catch(OutOfMemoryError e){
			JOptionPane.showMessageDialog(MainFrame.getInstance(),e.getMessage(),"Greška",JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
