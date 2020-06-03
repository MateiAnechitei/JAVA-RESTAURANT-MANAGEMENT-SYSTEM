package presentation;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import business.BaseProduct;
import business.CompositeProduct;
import business.MenuItem;
import business.Restaurant;

import data.RestaurantSerializator;

public class AdministratorGraphicalUserInterface {
	private Restaurant restaurant;
	private RestaurantSerializator rs = new RestaurantSerializator();
	private JFrame frame = new JFrame("Administrator");
	
	private JButton addBP = new JButton("Add Base Product");
	private JTextField addBPName = new JTextField("Name", 10);
	private JTextField addBPPrice = new JTextField("Price", 10);
	
	private JButton addCP = new JButton("Add Composite Product");
	private JList<MenuItem> addCPList = new JList<>();
	private JTextField addCPName = new JTextField("Name", 10);
	
	private JButton editBP = new JButton("Edit Base Product");
	private JList<MenuItem> editBPList = new JList<>();
	private JTextField editBPName = new JTextField("Name", 10);
	private JTextField editBPPrice = new JTextField("Price", 10);
	
	private JButton editCP = new JButton("Edit Composite Product");
	private JList<MenuItem> editCPList1 = new JList<>();
	private JList<MenuItem> editCPList2 = new JList<>();
	private JTextField editCPName = new JTextField("Name");

	private JButton deleteP = new JButton("Delete Product");
	private JList<MenuItem> deletePList = new JList<>();
	
	private JTable allItems = new JTable();
	
	public AdministratorGraphicalUserInterface(Restaurant r){
		this.restaurant = r;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 1080);
		
		JPanel addBPPanel = new JPanel();
		addBPPanel.add(addBPName);
		addBPPanel.add(addBPPrice);
		addBPPanel.add(addBP);
		//buttonPanel.setLayout(new GridLayout(2, 3));
		JPanel addCPPanel = new JPanel();
		addCPPanel.add(addCPList);
		addCPPanel.add(addCPName);
		addCPPanel.add(addCP);
		
		JPanel editBPPanel = new JPanel();
		editBPPanel.add(editBPList);
		editBPPanel.add(editBPName);
		editBPPanel.add(editBPPrice);
		editBPPanel.add(editBP);
		
		JPanel editCPPanel = new JPanel();
		editCPPanel.add(editCPList1);
		editCPPanel.add(editCPList2);
		editCPPanel.add(editCPName);
		editCPPanel.add(editCP);
		
		JPanel deletePPanel = new JPanel();
		deletePPanel.add(deletePList);
		deletePPanel.add(deleteP);
		
		JPanel allPanel = new JPanel();
		allPanel.add(allItems);
		
		JPanel fullPanel = new JPanel();
		fullPanel.add(addBPPanel);
		fullPanel.add(addCPPanel);
		fullPanel.add(editBPPanel);
		fullPanel.add(editCPPanel);
		fullPanel.add(deletePPanel);
		fullPanel.add(allPanel);
		fullPanel.setLayout(new BoxLayout(fullPanel, BoxLayout.Y_AXIS));
		frame.setContentPane(fullPanel);
		frame.setVisible(true);	
		setAddCPList();
		setDeletePList();
		setEditBPList();
		setEditCPList1();
		setEditCPList2();
		setAllItems();
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        try {
					rs.restaurantToFile(restaurant);
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		});
	}
	
	void addBPListener(ActionListener mal) {
		addBP.addActionListener(mal);
	}
	
	void editBPListener(ActionListener mal) {
		editBP.addActionListener(mal);
	}
	
	void addCPListener(ActionListener mal) {
		addCP.addActionListener(mal);
	}
	
	void editCPListener(ActionListener mal) {
		editCP.addActionListener(mal);
	}
	
	void deletePListener(ActionListener mal) {
		deleteP.addActionListener(mal);
	}
	
	String getAddBPName() {
		return addBPName.getText();
	}
	
	String getAddBPPrice() {
		return addBPPrice.getText();
	}
	
	String getAddCPName() {
		return addCPName.getText();
	}
	
	String getEditBPName() {
		return editBPName.getText();
	}
	
	String getEditBPPrice() {
		return editBPPrice.getText();
	}
	
	String getEditCPName() {
		return editCPName.getText();
	}
	
	void setAddCPList() {
		addCPList.setModel(restaurant.getMenuItemList());
	}
	
	void setEditBPList() {
		DefaultListModel<MenuItem> dlm = new DefaultListModel<MenuItem>();
		for(int i = 0; i < restaurant.getMenuItemList().getSize(); i++){
			if (restaurant.getMenuItemList().get(i) instanceof BaseProduct) {
				dlm.addElement(restaurant.getMenuItemList().get(i));
			}
		}
		editBPList.setModel(dlm);
	}
	
	void setEditCPList1() {
		DefaultListModel<MenuItem> dlm = new DefaultListModel<MenuItem>();
		for(int i = 0; i < restaurant.getMenuItemList().getSize(); i++){
			if (restaurant.getMenuItemList().get(i) instanceof CompositeProduct) {
				dlm.addElement(restaurant.getMenuItemList().get(i));
			}
		}
		editCPList1.setModel(dlm);
	}
	
	void setEditCPList2() {
		editCPList2.setModel(restaurant.getMenuItemList());
	}
	
	void setDeletePList() {
		deletePList.setModel(restaurant.getMenuItemList());
	}
	
	public JList<MenuItem> getAddCPList() {
		return addCPList;
	}

	public JList<MenuItem> getEditBPList() {
		return editBPList;
	}

	public JList<MenuItem> getEditCPList1() {
		return editCPList1;
	}

	public JList<MenuItem> getEditCPList2() {
		return editCPList2;
	}

	public JList<MenuItem> getDeletePList() {
		return deletePList;
	}
	
	void setAllItems() {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnCount(8);
		for(int i = 0; i < restaurant.getMenuItemList().getSize(); i++) {
			dtm.addRow(restaurant.getMenuItemList().get(i).addInTable());
		}
		allItems.setModel(dtm);
	}
	
	public JTable getAllItems() {
		return allItems;
	}
	
	
}
