package presentation;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import business.MenuItem;
import business.Restaurant;

public class WaiterGraphicalUserInterface {
	private Restaurant restaurant;
	private JFrame frame = new JFrame("Waiter");
	
	private JList<String> tableList = new JList<String>();
	private JList<MenuItem> menuItemList = new JList<MenuItem>();
	private JButton addItemsBtn = new JButton("AddItems");
	private JList<MenuItem> addedMenuItemsList = new JList<MenuItem>();
	private JButton createOrderBtn = new JButton("Create Order");
	
	private JList<String> computePriceList = new JList<String>();
	private JButton computePriceBtn = new JButton("Compute Price");
	
	private JList<String> tableBillList = new JList<String>();
	private JButton tableBillBtn = new JButton("Generate Bill");
	
	public WaiterGraphicalUserInterface(Restaurant r) {
		this.restaurant = r;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 800);
		
		JPanel createOrderPanel = new JPanel();
		createOrderPanel.add(tableList);
		createOrderPanel.add(menuItemList);
		createOrderPanel.add(addItemsBtn);
		createOrderPanel.add(addedMenuItemsList);
		createOrderPanel.add(createOrderBtn);
		
		JPanel computePricePanel = new JPanel();
		computePricePanel.add(computePriceList);
		computePricePanel.add(computePriceBtn);
		
		JPanel billPanel = new JPanel();
		billPanel.add(tableBillList);
		billPanel.add(tableBillBtn);
		
		JPanel fullPanel = new JPanel();
		fullPanel.add(createOrderPanel);
		fullPanel.add(computePricePanel);
		fullPanel.add(billPanel);
		
		fullPanel.setLayout(new BoxLayout(fullPanel, BoxLayout.Y_AXIS));
		frame.setContentPane(fullPanel);
		frame.setVisible(true);
		setTableList();
		setMenuItemList();
		setComputePriceList();
	}
	
	void addItemsBtnListener(ActionListener mal) {
		addItemsBtn.addActionListener(mal);
	}
	
	void createOrderBtnListener(ActionListener mal) {
		createOrderBtn.addActionListener(mal);
	}
	
	void computePriceBtnListener(ActionListener mal) {
		computePriceBtn.addActionListener(mal);
	}
	
	void tableBillBtnListener(ActionListener mal) {
		tableBillBtn.addActionListener(mal);
	}
	
	void setTableList() {
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		for(int i = 1; i <= restaurant.getNumberOfTables(); i++) {
			dlm.addElement("Table " + i);
		}
		tableList.setModel(dlm);
	}
	
	void setMenuItemList() {
		menuItemList.setModel(restaurant.getMenuItemList());
	}
	
	void setAddedMenuItemsList() {
		DefaultListModel<MenuItem> dfm = new DefaultListModel<MenuItem>();
		dfm.removeAllElements();
		addedMenuItemsList.setModel(dfm);
	}
	
	void setComputePriceList() {
		DefaultListModel<String> dfm = new DefaultListModel<String>();
		restaurant.getMadeOrders().forEach( (k,v) ->{
			if(k.getPrice() == 0.0f)
				dfm.addElement(k.getOrderID() + "-Table " + k.getTable());
		});
		computePriceList.setModel(dfm);
	}
	
	void setTableBillList() {
		DefaultListModel<String> dfm = new DefaultListModel<String>();
		for(int i = 1; i <= restaurant.getNumberOfTables(); i++) {
			if(restaurant.getTables().get(i-1) == true) {
				dfm.addElement("Table " + i);
			}
		}
		tableBillList.setModel(dfm);
	}

	public JList<String> getTableList() {
		return tableList;
	}

	public JList<MenuItem> getMenuItemList() {
		return menuItemList;
	}

	public JList<MenuItem> getAddedMenuItemsList() {
		return addedMenuItemsList;
	}

	public JList<String> getComputePriceList() {
		return computePriceList;
	}

	public JList<String> getTableBillList() {
		return tableBillList;
	}
	
	
}
