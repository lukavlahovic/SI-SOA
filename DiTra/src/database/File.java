package database;

import com.mysql.cj.xdevapi.JsonArray;
import event.UpdateBlockEvent;
import event.UpdateBlockListener;
import gui.MainFrame;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import javax.swing.event.EventListenerList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class File {

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
	
	//naziv fajla u kome se nalazi podaci datoteke
	protected String fileName;
	
	//naziv fajla u kome se nalazi header datoteke
	protected String headerName;
	
	//opis polja koji cine slog dobija se iz header datoteke
	protected ArrayList<FileField> fields = new ArrayList<FileField>();

    //sadrzaj bloka koji se uzima u jednom zahvatu iz datoteke, ovo je u stvari bafer	
	protected byte[] buffer;
	
	//sadrzaj jednog bloka predstavljen kao matrica
	protected String[][] data=null;

	
    //lista slušača koja se koristi da se osveži prikaz tabele u klasi FileView
	//prilikom učitavanja novog bloka iz datoteke 
	
	EventListenerList listenerBlockList = new EventListenerList();
	UpdateBlockEvent updateBlockEvent = null;
	
	//naziv tabele iz baze podtaka koju UIDBFile predstavlja
	protected String TABLE_NAME;
	
	/*
	 * U konstruktoru se inicijalizuje naziv tabele koju instanca klase
	 * predstavlja
	 */
	public File(String tableName) {
		
		this.headerName=tableName;
		this.TABLE_NAME=tableName;
		this.fileName=headerName;
	
	}
	
	/*
	 * Metoda čita naziv svih kolona iz otvorene tabele, proverava koje  kolone 
	 * ulaze u sastav primarnog ključa
	 * u tabeli i na osnovu toga formira listu polja (lista fields) 
	 */
	public void readHeader() throws IOException, SQLException {
	
		DatabaseMetaData dbMetaData=MainFrame.getInstance().getModel().getConfig().getConnection().getMetaData();
	
		//čitanje svih kolona koje ulaze u sastav tabele
		ResultSet columnNames=dbMetaData.getColumns(null, null, TABLE_NAME, null);
		//čitanje strukture primarnog ključa posmatrane tabele
		ResultSet 	pkeys= dbMetaData.getPrimaryKeys(null, null,TABLE_NAME);
	
		
		//za svaku kolonu koja se nalazi u tabeli, kreira se po jedna instanca klasa UIFieldFild
		while (columnNames.next()){
			FileField field=new FileField(columnNames.getString("COLUMN_NAME"),"TYPE_"+columnNames.getString("TYPE_NAME").toUpperCase(),columnNames.getInt("COLUMN_SIZE"),false);
			fields.add(field);
			
			while (pkeys.next()){
				if (pkeys.getString("COLUMN_NAME").equals(field.getFieldName())){
					field.setFieldPK(true);
					field.setSort(true);
					break;
				}
			}

		}
		pkeys.close();
		columnNames.close();
	}
	
	
	/*
	 * Metoda čita sve podatke iz tabele
	 * SELECT upit se generički formira na osnovu opisa polja u tabeli
	 */
	public boolean fetchNextBlock() throws IOException, SQLException {
		
		
		//broj slogova u tabeli :
//		Statement stmt0=MainFrame.getInstance().getModel().getConfig().getConnection().createStatement();
//		ResultSet rs0=stmt0.executeQuery("SELECT COUNT(*) as broj FROM "+TABLE_NAME);
//		if (rs0.next()){
//
//			RECORD_NUM=rs0.getInt(1);
//		}
//		stmt0.close();
//		rs0.close();

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/provajder1/teski/select");

		String json = "{ \"entitet\":\"" + TABLE_NAME + "\",\"atributi\":\"";
		for(int i=0;i<fields.size();i++){
			json += fields.get(i);
			if (i<fields.size()-1)
				json+=",";
		}
		json += "\"}";

		System.out.println(json);
		StringEntity entity = new StringEntity(json);
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");

		try {
			CloseableHttpResponse response = client.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity());

			Map<String,String> myMap = new HashMap<>();
			String[] pairs = result.split(",");
			for(int i = 0; i < pairs.length; i++)
			{
				String pair = pairs[i];
				String[]keyValue = pair.split(":");
				myMap.put(keyValue[0],keyValue[1]);
			}
			System.out.println(myMap);

			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}


		//formiranje dela upita za SELECT sql nad tabelom
//		String columnParams=null;
//		for (int i=0;i<fields.size();i++){
//			if (columnParams==null){
//				columnParams=fields.get(i).getFieldName();
//			}else{
//				columnParams+=","+fields.get(i).getFieldName();
//			}
//
//		}
//
//		Statement stmt=MainFrame.getInstance().getModel().getConfig().getConnection().createStatement();
//		ResultSet rs=stmt.executeQuery("SELECT "+columnParams+" FROM "+TABLE_NAME);
		
//		data = new String[(int) RECORD_NUM][];
//
//		//u objektu ResultSet-a rs nalaze se svi podaci iz date tabele
//		//iz result set-a se podaci čitaju i prebacuju u matricu data[][]
//		int i=0;
//		while (rs.nexStrit()){
////			data[i]=new ng [fields.size()];
//			for (int j=0;j<fields.size();j++){
//				data[i][j]=rs.getString(j+1);
//			}
//			i++;
//		}
//		System.out.println("broj slogova:"+(TABLE_NAME));
//		System.out.println("broj slogova:"+(i++));
		fireUpdateBlockPerformed();
		return true;
	}
	
	//registracija i deregistracija slusaca
	 public void addUpdateBlockListener(UpdateBlockListener l) {
		 listenerBlockList.add(UpdateBlockListener.class, l);
	 }

	 public void removeUpdateBlockListener(UpdateBlockListener l) {
		 listenerBlockList.remove(UpdateBlockListener.class, l);
	 }
	 
	 //kada se izvrsi odgovarajuca akcija, sve observere (slusace) obavestavamo da se dogadjaj desio
	protected void fireUpdateBlockPerformed() {
	     Object[] listeners = listenerBlockList.getListenerList();
	     for (int i = listeners.length-2; i>=0; i-=2) {
	         if (listeners[i]==UpdateBlockListener.class) {
	             if (updateBlockEvent == null)
	            	 updateBlockEvent = new UpdateBlockEvent(this);
	             ((UpdateBlockListener)listeners[i+1]).updateBlockPerformed(updateBlockEvent);
	         }
	     }

	 }

	public long getBLOCK_FACTOR() {
		return BLOCK_FACTOR;
	}

	public void setBLOCK_FACTOR(long bLOCK_FACTOR) {
		BLOCK_FACTOR = bLOCK_FACTOR;
	}

	public int getBUFFER_SIZE() {
		return BUFFER_SIZE;
	}

	public void setBUFFER_SIZE(int bUFFER_SIZE) {
		BUFFER_SIZE = bUFFER_SIZE;
	}

	public int getRECORD_SIZE() {
		return RECORD_SIZE;
	}

	public void setRECORD_SIZE(int rECORD_SIZE) {
		RECORD_SIZE = rECORD_SIZE;
	}

	public int getBLOCK_NUM() {
		return BLOCK_NUM;
	}

	public void setBLOCK_NUM(int bLOCK_NUM) {
		BLOCK_NUM = bLOCK_NUM;
	}

	public long getRECORD_NUM() {
		return RECORD_NUM;
	}

	public void setRECORD_NUM(long rECORD_NUM) {
		RECORD_NUM = rECORD_NUM;
	}

	public long getFILE_POINTER() {
		return FILE_POINTER;
	}

	public void setFILE_POINTER(long fILE_POINTER) {
		FILE_POINTER = fILE_POINTER;
	}

	public long getFILE_SIZE() {
		return FILE_SIZE;
	}

	public void setFILE_SIZE(long fILE_SIZE) {
		FILE_SIZE = fILE_SIZE;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public ArrayList<FileField> getFields() {
		return fields;
	}

	public void setFields(ArrayList<FileField> fields) {
		this.fields = fields;
	}

	public byte[] getBuffer() {
		return buffer;
	}

	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}

	public String[][] getData() {
		return data;
	}

	public void setData(String[][] data) {
		this.data = data;
	}

	public EventListenerList getListenerBlockList() {
		return listenerBlockList;
	}

	public void setListenerBlockList(EventListenerList listenerBlockList) {
		this.listenerBlockList = listenerBlockList;
	}

	public UpdateBlockEvent getUpdateBlockEvent() {
		return updateBlockEvent;
	}

	public void setUpdateBlockEvent(UpdateBlockEvent updateBlockEvent) {
		this.updateBlockEvent = updateBlockEvent;
	}

	public String getTABLE_NAME() {
		return TABLE_NAME;
	}

	public void setTABLE_NAME(String tABLE_NAME) {
		TABLE_NAME = tABLE_NAME;
	}	
	
	public void setBLOCK_SIZE(long block_size) {
		BLOCK_FACTOR = block_size;
		BLOCK_NUM=(int) (RECORD_NUM/BLOCK_FACTOR)+1;
	}
}
