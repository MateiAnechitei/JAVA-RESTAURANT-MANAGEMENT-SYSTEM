package presentation;

import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;



public class ChefObserver extends Observer {
	public ChefObserver(ChefGraphicalUserInterface cgui) {
		this.cgui = cgui;
		this.cgui.attach(this);
		cgui.deleteItemListener(new DeleteItemListener());
	}
	
	@Override
	public void update() {
		cgui.setNotifyBox("A new composite product has been added to an order!");
	}
	
	class DeleteItemListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			DefaultListModel<MenuItem> model = (DefaultListModel) cgui.getChefList().getModel();
			int selectedIndex = cgui.getChefList().getSelectedIndex();
			if (selectedIndex != -1) {
			    model.remove(selectedIndex);
			}
		}
	}
}
