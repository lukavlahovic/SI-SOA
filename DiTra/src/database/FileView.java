package database;




import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import event.UpdateBlockEvent;
import event.UpdateBlockListener;
import gui.AddView;
import gui.DeleteView;
import gui.MainFrame;
import gui.TabelaModel;
import gui.UpdateView;


@SuppressWarnings("serial")
public class FileView extends JPanel implements  UpdateBlockListener,TreeSelectionListener{
  
	private File uiFile;
	private JTable table;
	private JPanel panTop;
	private JTabbedPane jtp;
	private JSplitPane jsp;

	
	public FileView(final File uiFile) {
		super();
		this.uiFile=uiFile;
		setLayout(new BorderLayout());
		
	    //inicijalizacija gornjeg dela panela	
		panTop=new JPanel(new BorderLayout());		
		
		try {
			this.uiFile.readHeader();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		initPanToolbar();				
		
		this.uiFile.addUpdateBlockListener(this);
					
		table=new JTable();
		table.setModel( new TabelaModel(uiFile.getFields(),uiFile.getData()));
			
		JScrollPane scr=new JScrollPane(table);
		
		
		jtp = new JTabbedPane();
		jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT,scr,jtp);
		jsp.setDividerLocation(300);
		this.add(jsp);
		
		try {
			for(String s: printForeignKeys(uiFile.getTABLE_NAME()))
			{
				setFileViewRelacija(new FileViewRelacija(new File(s)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		//add(scr,BorderLayout.CENTER);
		
	
	}


    private void initPanToolbar(){
    	JPanel panToolbar=new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	
		JButton btnFetch=new JButton("Refresh");
		btnFetch.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				try {
					uiFile.fetchNextBlock();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			
		});
		panToolbar.add(btnFetch);	
		
		
		JButton btnAdd=new JButton("Add Record");
		btnAdd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				AddView av = new AddView();
				
			}
			
		});
		panToolbar.add(btnAdd);		

		
		
		JButton btnUpdate=new JButton("Update Record");
		btnUpdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				UpdateView uv = new UpdateView();
			}
			
		});	
		//update samo za .db fajlove
		
			
		panToolbar.add(btnUpdate);	
		
		
		
		
		JButton btnDelete=new JButton("Delete Record");
		btnDelete.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				DeleteView dv = new DeleteView();
			}
			
		});		
		panToolbar.add(btnDelete);		

		
        panTop.add(panToolbar,BorderLayout.CENTER);		
		add(panTop,BorderLayout.NORTH);    	
    	
    }
	public File getUiFile() {
		return uiFile;
	}



    
	public void updateBlockPerformed(UpdateBlockEvent e) {
		table.setModel(new TabelaModel(uiFile.getFields(),uiFile.getData()));
	}





	public void valueChanged(TreeSelectionEvent e) {
		// implementirati otvaranje bloka koji sadrži slog sa vrednošću ključa
		// prvog elementa u čvoru
		DefaultMutableTreeNode node= (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
		if (node.getChildCount()==0){

		}
		
	}




	public JTable getTable() {
		return table;
	}




	public void setTable(JTable table) {
		this.table = table;
	}
	
	public void setFileViewRelacija(FileViewRelacija fileViewRelacija) {
		jtp.add(fileViewRelacija,fileViewRelacija.getUiFile().getFileName());
		jtp.setSelectedIndex(jtp.getComponentCount()-1);
	}
	
	public ArrayList<String> printForeignKeys(String tableName) throws SQLException {
		ArrayList<String> str = new ArrayList<>();
		Connection c = MainFrame.getInstance().getModel().getConfig().getConnection();
	    DatabaseMetaData metaData = c.getMetaData();
	    ResultSet foreignKeys = metaData.getImportedKeys(c.getCatalog(), null, tableName);
	    while (foreignKeys.next()) {
	    	if(!str.contains(foreignKeys.getString("PKTABLE_NAME")))
	    		str.add(foreignKeys.getString("PKTABLE_NAME"));
	       
	    }
	    return str;
	}


	public JTabbedPane getJtp() {
		return jtp;
	}
	
	
}

