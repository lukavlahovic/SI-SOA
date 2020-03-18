package database;




import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import event.UpdateBlockEvent;
import event.UpdateBlockListener;
import gui.AddViewRelacija;
import gui.DeleteViewRelacija;
import gui.TabelaModel;
import gui.UpdateViewRelacija;



@SuppressWarnings("serial")
public class FileViewRelacija extends JPanel implements  UpdateBlockListener,TreeSelectionListener{
  
	private File uiFile;
	private JTable table;
	private JPanel panTop;
	
	
	public FileViewRelacija(final File uiFile) {
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
		
		
			
		add(scr,BorderLayout.CENTER);
		
	
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
				AddViewRelacija av = new AddViewRelacija();
				
			}
			
		});
		panToolbar.add(btnAdd);		

		
		
		JButton btnUpdate=new JButton("Update Record");
		btnUpdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				UpdateViewRelacija uv = new UpdateViewRelacija();
			}
			
		});	
		//update samo za .db fajlove
		
			
		panToolbar.add(btnUpdate);	
		
		
		
		
		JButton btnDelete=new JButton("Delete Record");
		btnDelete.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				DeleteViewRelacija dv = new DeleteViewRelacija();
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
	
}

