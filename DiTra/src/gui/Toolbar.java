package gui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar {

	private JLabel blocknumLabel;
	private JTextField blocknumText;
	private JButton fetchNextBlock;
	private JButton addRecord;
	private JButton deleteRecord;
	private JLabel brojPristupaLabel;
	private JButton editRecord;
	private JButton search;
	private JButton exitTab;
	
	
	
	public Toolbar() {
		// TODO Auto-generated constructor stub
		super();
		setFloatable(false);
		add(Box.createHorizontalStrut(5));
		add((blocknumLabel = new JLabel("Block factor: ")));
		blocknumText = new JTextField("30");
		blocknumText.setMaximumSize(new Dimension(40, 40));
		add(blocknumText);
		add(Box.createHorizontalStrut(10));
		
		fetchNextBlock = new JButton("Fetch next block");
		fetchNextBlock.addActionListener(MainFrame.getInstance().getActionManager().getFetchNextBlockAction());
		add(fetchNextBlock);
		add(Box.createHorizontalStrut(5));
		
		/*addRecord = new JButton("Add record");
		addRecord.addActionListener(MainView.getInstance().getActionManager().getAddRecordAction());
		add(addRecord);
		add(Box.createHorizontalStrut(5));
		
		deleteRecord = new JButton("Delete record");
		//dodati akciju
		add(deleteRecord);
		add(Box.createHorizontalStrut(5));
		
		search = new JButton("Search record");
		//dodati akciju
		search.addActionListener(MainView.getInstance().getActionManager().getSearchRecordAction());
		add(search);
		add(Box.createHorizontalStrut(5));
		
		exitTab = new JButton("Exit");
		exitTab.addActionListener(MainView.getInstance().getActionManager().getExitTabAction());
		add(exitTab);
		add(Box.createHorizontalStrut(20));
		*/
		
		brojPristupaLabel = new JLabel("Broj pristupa: ");
		add(brojPristupaLabel);
		
		
	}



	public void setBrojPristupaLabel(long brojPristupa) {
		brojPristupaLabel.setText("Broj pristupa: "+brojPristupa);
	}



	public JLabel getBlocknumLabel() {
		return blocknumLabel;
	}



	public JTextField getBlocknumText() {
		return blocknumText;
	}



	public JButton getFetchNextBlock() {
		return fetchNextBlock;
	}



	public JButton getAddRecord() {
		return addRecord;
	}



	public JButton getDeleteRecord() {
		return deleteRecord;
	}



	public JLabel getBrojPristupaLabel() {
		return brojPristupaLabel;
	}



	public JButton getEditRecord() {
		return editRecord;
	}



	public JButton getSearch() {
		return search;
	}
	
	
	
}
