package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import business.MenuItem;
import business.Order;
import business.Restaurant;

public class WGUIController {
	private Restaurant restaurant;
	private WaiterGraphicalUserInterface view;
	private ChefGraphicalUserInterface cgui;
	
	public WGUIController(Restaurant r, WaiterGraphicalUserInterface view, ChefGraphicalUserInterface cgui) {
		this.restaurant = r;
		this.view = view;
		this.cgui = cgui;
		view.addItemsBtnListener(new AddItemsListener());
		view.createOrderBtnListener(new CreateOrderListener());
		view.computePriceBtnListener(new ComputePriceListener());
		view.tableBillBtnListener(new TableBillListener());
	}
	
	class AddItemsListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			DefaultListModel<MenuItem> dfm = new DefaultListModel<MenuItem>();
			for(MenuItem mi : view.getMenuItemList().getSelectedValuesList()) {
				dfm.addElement(mi);
			}
			view.getAddedMenuItemsList().setModel(dfm);
		}
	}
	class CreateOrderListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int index = view.getTableList().getSelectedIndex() + 1;
			restaurant.getTables().setElementAt(true, index-1);
			Order newOrder = new Order(index);
			ArrayList<MenuItem> orderedItems = new ArrayList<MenuItem>();
			for(int i = 0; i < view.getAddedMenuItemsList().getModel().getSize(); i++){
				orderedItems.add(view.getAddedMenuItemsList().getModel().getElementAt(i));
			}
			restaurant.createOrder(newOrder, orderedItems);
			cgui.updateChefList(orderedItems);
			view.setAddedMenuItemsList();
			view.setComputePriceList();
		}
	}
	
	class ComputePriceListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String[] parts = view.getComputePriceList().getSelectedValue().split("[-]");
			//System.out.println(parts[0]);
			int oid = Integer.parseInt(parts[0]);
			restaurant.getMadeOrders().forEach( (k,v) ->{
				if(k.getOrderID() == oid)
					k.setPrice(restaurant.computeOrderPrice(k));
			});
			view.setComputePriceList();
			view.setTableBillList();
		}
	}
	
	class TableBillListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String[] parts = view.getTableBillList().getSelectedValue().split("[\\s]");
			int table = Integer.parseInt(parts[1]);
			restaurant.generateBill(table);
			view.setTableBillList();
		}
	}
}
