package Intermediate;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

class InItems{
	private int id;
	private String name;
	private int quantity;
	private double price;
	public InItems(int id,String name,int quantity,double price) {
		this.id=id;
		this.name=name;
		this.quantity=quantity;
		this.price=price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String toString() {
		return "Items["+"id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                "]";
	}
	
}
public class InventoryManagement extends JFrame {
	private ArrayList<InItems> invertory;
	private DefaultTableModel table;
	private JTable tab;
	private JTextField idField,nameField,quantityField,priceField;
	public InventoryManagement() {
		invertory =new ArrayList<>();
		setTitle("Inventory Management System");
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JPanel panel=new JPanel(new BorderLayout());
		JPanel input=new JPanel(new GridLayout(5,2));
		JPanel button=new JPanel();
		JLabel idLabel =new JLabel("ID:");
		idField=new JTextField();
		JLabel nameLabel =new JLabel("NAME:");
		nameField=new JTextField();
		JLabel quantityLabel=new JLabel("QUANTITY:");
		quantityField=new JTextField();
		JLabel priceLabel=new JLabel("PRICE:");
		priceField=new JTextField();
		input.add(idLabel);
		input.add(idField);
		input.add(nameLabel);
		input.add(nameField);
		input.add(quantityLabel);
		input.add(quantityField);
		input.add(priceLabel);
		input.add(priceField);
		JButton add=new JButton("ADD");
		JButton update=new JButton("UPDATE");
		JButton delete=new JButton("DELETE");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInvItem();
			}
		});
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateInvItem();
			}
		});
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteInvItem();
			}
		});
		button.add(add);
		button.add(update);
		button.add(delete);
		table=new DefaultTableModel(new String[] {"ID","NAME","QUANTITY","PRICE"},0);
		tab=new JTable(table);
		panel.add(input,BorderLayout.NORTH);
		panel.add(new JScrollPane(tab),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
		add(panel);
		setVisible(true);
	}
	private void addInvItem() {
		try {
			int id=Integer.parseInt(idField.getText());
			String name=nameField.getText();
			int quantity=Integer.parseInt(quantityField.getText());
			double price=Double.parseDouble(priceField.getText());
			InItems item=new InItems(id,name,quantity,price);
			invertory.add(item);
			table.addRow(new Object[] {id,name,quantity,price});
			clearFields();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Invalid Data Try Again!!!");
		}
	}
	private void updateInvItem() {
		int selectRow=tab.getSelectedRow();
		if(selectRow>=0) {
			try {
				int id=Integer.parseInt(idField.getText());
				String name=nameField.getText();
				int quantity=Integer.parseInt(quantityField.getText());
				double price=Double.parseDouble(priceField.getText()); 
				InItems item=invertory.get(selectRow);
				item.setId(id);
				item.setName(name);
				item.setQuantity(quantity);
				item.setPrice(price);
				table.setValueAt(id,selectRow,0);
				table.setValueAt(name,selectRow,1);
				table.setValueAt(quantity,selectRow,2);
				table.setValueAt(price,selectRow,3);
				clearFields();
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(this, "Invalid Data Try Again!!!");
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Select a row to update");
		}
	}
	public void deleteInvItem() {
		int selectRow=tab.getSelectedRow();
		if(selectRow>=0) {
			invertory.remove(selectRow);
			table.removeRow(selectRow);
			clearFields();
		}
		else {
			JOptionPane.showMessageDialog(this, "Select a row to delete");
		}
	}
	private void clearFields() {
		idField.setText("");
		nameField.setText("");
		quantityField.setText("");
		priceField.setText("");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new InventoryManagement();
			}
		});

	}

}
