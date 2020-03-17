package database;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;


import event.UpdateBlockEvent;
import event.UpdateBlockListener;
import event.UpdateOverZoneEvent;
import event.UpdateOverZoneListener;
import gui.AddView;
import gui.DeleteView;
import gui.TabelaModel;
import gui.UpdateView;
import tree.treeModel.Node;


@SuppressWarnings("serial")
public class FileView extends JPanel implements  UpdateBlockListener,UpdateOverZoneListener,TreeSelectionListener{
  
	private File uiFile;
	private JTable table;
	
	//tabela za prikaz slogova u zoni prekoračilaca
	private JTable overZoneTable;
	
	private JPanel panTop;
	private JTextField txtBlockSize;
	private JTextField txtFileSize;
	private JTextField txtRecordSize;
	private JTextField txtRecordNum;
	private JTextField txtBlockNum;

	
	private JTree indexTree;
	
	
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
		
		JButton btnFind=new JButton("Find Record");
		
		btnFind.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
			
			}
			
		});
		panToolbar.add(btnFind);

		
		
		JButton btnFilterFind=new JButton("Filter Find");
		
		btnFilterFind.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {

				}
			
			});
		panToolbar.add(btnFilterFind);
		
		
		
	



		
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



	@Override
	public void updateOverZonePerformed(UpdateOverZoneEvent e) {
		// TODO Auto-generated method stub
		
	}




	public JTable getTable() {
		return table;
	}




	public void setTable(JTable table) {
		this.table = table;
	}
	
}

