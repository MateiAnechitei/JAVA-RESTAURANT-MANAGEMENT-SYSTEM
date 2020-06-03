package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import business.BaseProduct;
import business.CompositeProduct;
import business.MenuItem;
import business.Restaurant;

public class AGUIController {
	private Restaurant restaurant;
	private AdministratorGraphicalUserInterface view;
	
	public AGUIController(Restaurant restaurant, AdministratorGraphicalUserInterface view) {
		
		this.restaurant = restaurant;
		this.view = view;
		this.view.addBPListener(new AddBPListener());
		this.view.addCPListener(new AddCPListener());
		this.view.editBPListener(new EditBPListener());
		this.view.editCPListener(new EditCPListener());
		this.view.deletePListener(new DeletePListener());
	}
	
	class AddBPListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String nameInput = "";
			String priceInput = "";
			
			nameInput = view.getAddBPName();
			priceInput = view.getAddBPPrice();
			
			String name = nameInput;
			Float price = Float.parseFloat(priceInput);
			
			restaurant.createMenuItem(name, price);
			view.setAddCPList();
			view.setDeletePList();
			view.setEditBPList();
			view.setEditCPList1();
			view.setEditCPList2();
			view.setAllItems();
			
		}
	}
	
	class AddCPListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ArrayList<MenuItem> comp = new ArrayList<MenuItem>();
			String name = view.getAddCPName();
			for(MenuItem mi : view.getAddCPList().getSelectedValuesList()) {
				comp.add(mi);
			}
			restaurant.createMenuItem(name, comp);
			view.setAddCPList();
			view.setDeletePList();
			view.setEditBPList();
			view.setEditCPList1();
			view.setEditCPList2();
			view.setAllItems();
		}
	}
	
	class EditBPListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String newName = view.getEditBPName();
			Float newPrice = Float.parseFloat(view.getEditBPPrice());
			restaurant.editMenuItem(view.getEditBPList().getSelectedValue(), new BaseProduct(newName, newPrice));
			view.setAddCPList();
			view.setDeletePList();
			view.setEditBPList();
			view.setEditCPList1();
			view.setEditCPList2();
			view.setAllItems();
		}
	}
	
	class EditCPListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String newName = view.getEditCPName();
			ArrayList<MenuItem> newComp = new ArrayList<MenuItem>();
			for(MenuItem mi : view.getEditCPList2().getSelectedValuesList()) {
				newComp.add(mi);
			}
			restaurant.editMenuItem(view.getEditCPList1().getSelectedValue(), new CompositeProduct(newName, newComp));
			view.setAddCPList();
			view.setDeletePList();
			view.setEditBPList();
			view.setEditCPList1();
			view.setEditCPList2();
			view.setAllItems();
		}
	}
	class DeletePListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			restaurant.deleteMenuItem(view.getDeletePList().getSelectedValue());
			view.setAddCPList();
			view.setDeletePList();
			view.setEditBPList();
			view.setEditCPList1();
			view.setEditCPList2();
			view.setAllItems();
		}
	}
}
