package controller;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import gui.MainFrame;

public class FetchNextBlockAction extends AbstractAction{

	//velicina bloka u broju slogova koji se uzimaju u jednom zahvatu iz datoteke, tj. faktor blokiranja
		protected long BLOCK_FACTOR=20;
		
		
		//velicina bafera u bajtima, odnosno broj bajtova koji se uyimaju u jednom bloku
		protected int BUFFER_SIZE=0;
		
		//velicina jednog sloga u datoteci u bajtovima, u datotekama sa kojima ćemo mi raditi slogovi su fiksne velicine
		// to znači da svaki slog u jednoj datoteci ima istu dužinu
		protected int RECORD_SIZE=0;
		
		//broj blokova u datoteci
		protected int BLOCK_NUM=0;
		
		//broj slogova u datoteci
		protected long RECORD_NUM=0;
		
		//pointer u datoteci
		protected long FILE_POINTER=0;
		
		//velicina datoteke u bajtima
		protected long FILE_SIZE=0;
		
		// putanja do direktorijuma u kome se nalazi datoteka
		protected String path;
		//naziv fajla u kome se nalazi header datoteke
		protected String headerName;
		//naziv fajla u kome se nalazi podaci datoteke
		protected String fileName;
		
		protected boolean directory;
		
		//opis polja koji cine slog dobija se iz header datoteke
		protected ArrayList<UIFileField> fields = new ArrayList<UIFileField>();

	    //sadrzaj bloka koji se uzima u jednom zahvatu iz datoteke, ovo je u stvari bafer	
		protected byte[] buffer;
		
		//sadrzaj jednog bloka predstavljen kao matrica
		protected String[][] data=null;
	
	public FetchNextBlockAction() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//broj slogova u tabeli :
				Statement stmt0= MainFrame.getInstance().getModel().getConfig().getConnection().createStatement();
				ResultSet rs0=stmt0.executeQuery("SELECT (*) FROM "+"LANGUAGES");
				if (rs0.next()){
				
					RECORD_NUM=rs0.getInt(1);
				}
				stmt0.close();
				rs0.close();
				
				
				//formiranje dela upita za SELECT sql nad tabelom
				String columnParams=null;
				for (int i=0;i<fields.size();i++){
					if (columnParams==null){
						columnParams=fields.get(i).getFieldName();
					}else{
						columnParams+=","+fields.get(i).getFieldName();
					}
					
				}
				
				Statement stmt=AppCore.getInstance().getConn().createStatement();
				ResultSet rs=stmt.executeQuery("SELECT "+columnParams+" FROM "+TABLE_NAME);
				
				
				data = new String[(int) RECORD_NUM][];

				//u objektu ResultSet-a rs nalaze se svi podaci iz date tabele
				//iz result set-a se podaci čitaju i prebacuju u matricu data[][]
				int i=0;
				while (rs.next()){
					data[i]=new String [fields.size()];
					for (int j=0;j<fields.size();j++){
						data[i][j]=rs.getString(j+1);
					}
					i++;
				}
				System.out.println("broj slogova:"+(TABLE_NAME));
				System.out.println("broj slogova:"+(i++));
		
	}

}
