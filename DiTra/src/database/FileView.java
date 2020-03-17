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
import gui.TabelaModel;
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
		
		
		//ako radimo sa bazom podataka neće se prikazati toolbar sa parametrima za datoteke 
		if (!uiFile.getHeaderName().contains(".db")){
			initPanParams();
		}
		initPanToolbar();		
		
		
		this.uiFile.addUpdateBlockListener(this);
		

			
		table=new JTable();
		table.setModel( new TabelaModel(uiFile.getFields(),uiFile.getData()));
	
		
		JScrollPane scr=new JScrollPane(table);
		
	
		add(scr,BorderLayout.CENTER);

		

		
	}


	
    private void initPanParams(){
    	JPanel panParams=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		
		
		//velicina bloka - moze da se menja
		panParams.add(new JLabel("f (block factor):"));
		txtBlockSize=new JTextField();
		txtBlockSize.setColumns(5);
		txtBlockSize.setText(String.valueOf(uiFile.getBLOCK_FACTOR()));
		panParams.add(txtBlockSize);
		JButton btnChangeBS=new JButton("Change f");
		btnChangeBS.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				 
				uiFile.setBLOCK_SIZE(Integer.valueOf(txtBlockSize.getText()).longValue());
				txtBlockNum.setText(String.valueOf(uiFile.getBLOCK_NUM()));
				
			}
			
		});
		panParams.add(btnChangeBS);

		
		//velicina datoteke
		panParams.add(new JLabel("File size:"));
		txtFileSize=new JTextField();
		txtFileSize.setColumns(7);
		txtFileSize.setEnabled(false);
		
		txtFileSize.setText(String.valueOf(Math.ceil(uiFile.getFILE_SIZE()/1024.0000))+"KB");
		panParams.add(txtFileSize);
		
		

		
		
		//velicina linije u datoteci  
		panParams.add(new JLabel("Record size(B):"));
		txtRecordSize=new JTextField();
		txtRecordSize.setColumns(7);
		txtRecordSize.setEnabled(false);
		txtRecordSize.setText(String.valueOf(uiFile.getRECORD_SIZE()));
		panParams.add(txtRecordSize);
		
		
		//broj recorda u datoteci  
		panParams.add(new JLabel("Record num:"));
		txtRecordNum=new JTextField();
		txtRecordNum.setColumns(7);
		txtRecordNum.setEnabled(false);
		txtRecordNum.setText(String.valueOf(uiFile.getRECORD_NUM()));
		panParams.add(txtRecordNum);

		
		//broj blokova u datoteci  
		panParams.add(new JLabel("Block num:"));
		txtBlockNum=new JTextField();
		txtBlockNum.setColumns(7);
		txtBlockNum.setEnabled(false);
		txtBlockNum.setText(String.valueOf(uiFile.getBLOCK_NUM()));
		panParams.add(txtBlockNum);

		
		
		
		panParams.setBackground(new Color(153,204,255));
		panTop.add(panParams, BorderLayout.NORTH);
		
		   	
    	
    }

     
    private void initPanToolbar(){
    	JPanel panToolbar=new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	
		JButton btnFetch=new JButton(!uiFile.getHeaderName().contains(".db")?"Fetch next block":"Refresh");
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
		
			}
			
		});
		panToolbar.add(btnAdd);		

		
		
		JButton btnUpdate=new JButton("Update Record");
		btnUpdate.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {

			}
			
		});	
		//update samo za .db fajlove
		if (uiFile.getHeaderName().contains(".db")){
			
			panToolbar.add(btnUpdate);	
		}
		
		
		
		JButton btnDelete=new JButton("Delete Record");
		btnDelete.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {

			}
			
		});		
		panToolbar.add(btnDelete);		
		
		JButton btnFind=new JButton("Find Record");
		
		btnFind.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
			
			}
			
		});
		panToolbar.add(btnFind);

		
		if (uiFile.getHeaderName().contains(".db")){
			JButton btnFilterFind=new JButton("Filter Find");
		
			btnFilterFind.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {

				}
			
			});
			panToolbar.add(btnFilterFind);
		}
		
		
	



		
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
}

