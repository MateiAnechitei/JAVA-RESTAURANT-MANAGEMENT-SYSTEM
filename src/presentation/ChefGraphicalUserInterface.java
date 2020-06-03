package presentation;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.CompositeProduct;
import business.MenuItem;

public class ChefGraphicalUserInterface {
	
	private JFrame frame = new JFrame("Chef");
	private JList<MenuItem> chefList = new JList<MenuItem>();
	private JButton deleteItem = new JButton("Delete selected item");
	private JTextField notifyBox = new JTextField("", 30);
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public ChefGraphicalUserInterface(){
		this.chefList.setModel(new DefaultListModel<MenuItem>());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 540);
		
		JPanel listPanel = new JPanel();
		listPanel.add(chefList);
		listPanel.add(deleteItem);
		
		JPanel textPanel = new JPanel();
		textPanel.add(notifyBox);
		
		JPanel fullPanel = new JPanel();
		fullPanel.add(listPanel);
		fullPanel.add(textPanel);
		
		fullPanel.setLayout(new BoxLayout(fullPanel, BoxLayout.Y_AXIS));
		frame.setContentPane(fullPanel);
		frame.setVisible(true);
		
	}
	
	void deleteItemListener(ActionListener mal) {
		deleteItem.addActionListener(mal);
	}
	
	public void updateChefList(ArrayList<MenuItem> orderedItems) {
		DefaultListModel<MenuItem> dlm = (DefaultListModel<MenuItem>) chefList.getModel();
		for(MenuItem mi : orderedItems){		
			if (mi instanceof CompositeProduct) {
				dlm.addElement(mi);
			}
		}
		chefList.setModel(dlm);
		notifyAllObservers();
	}
	
	
	public JList<MenuItem> getChefList() {
		return this.chefList;
	}
	public void notifyAllObservers() {
		for(Observer o : observers) {
			o.update();
		}
	}
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void setNotifyBox(String s) {
		this.notifyBox.setText(s);
	}
	
}
