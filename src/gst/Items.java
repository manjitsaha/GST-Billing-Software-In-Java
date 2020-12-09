package gst;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Items extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel label;
	private JTextField itemname;
	private JLabel label_1;
	private JFormattedTextField sac;
	private JLabel label_3;
	private JFormattedTextField stock;
	private JLabel label_4;
	private JFormattedTextField salerate;
	private JLabel label_5;
	private JComboBox taxComboBox;
	private JFormattedTextField purchaserate;
	private JLabel label_6;
	private JButton btnSave;
	private JLabel lblNewLabel;
	private JButton btnClear;
	private JLabel NAMELBL;
	private JScrollPane scrollPane;
	private JLabel label_12;
	private JLabel label_14;
	private JLabel label_15;
	private JLabel label_16;
	private JLabel label_17;
	private JLabel label_19;
	private JLabel label_21;
	private JTextField mfgtext;
	private JLabel label_22;
	private JLabel label_23;
	private JLabel label_25;
	private JLabel label_26;
	private JLabel label_27;
	private JLabel label_28;
	private JLabel label_30;
	private JLabel label_31;
	private JButton button_3;
	private JLabel label_34;
	private JLabel label_35;
	private JLabel label_36;
	private JTextField Batchtxt;
	private JTable table;
	private DefaultTableModel model_table = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Items frame = new Items();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	Connection con = null;

	public void countItem() {
		DefaultTableModel mt = (DefaultTableModel) table.getModel();
		int count = mt.getRowCount();
		String co = Integer.toString(count);
		itemcount.setText(co);

	}

	public void itemlist() {
		ResultSet rs = null;
		PreparedStatement st = null;

		try {
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGST/GST", "sa", "");
			String h = "SELECT * FROM ADDITEMS";
			st = con.prepareStatement(h);
			rs = st.executeQuery();

			// while(rs.next()){
			table.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
			// }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		countItem();
	}

	public void blankFields() {

		Batchtxt.setText("");
		itemname.setText("");
		sac.setText("");
		salerate.setText("");
		purchaserate.setText("");
		stock.setText("");
		mfgtext.setText("");
		taxComboBox.setSelectedIndex(0);

	}

	public void additem() {

		String empt = itemname.getText();
		if (empt.isEmpty()) {
			lblNewLabel.setText("FILL ALL * FIELDS");
		} else {
			try {

				String batch = Batchtxt.getText();
				String item = itemname.getText();
				String hsn = sac.getText();
				String sale = salerate.getText();
				String purchase = purchaserate.getText();
				String stck = stock.getText();
				String tx = taxComboBox.getSelectedItem().toString();
				String mfg = mfgtext.getText();

				Connection con = DriverManager.getConnection("jdbc:h2:C:/SimpleGST/GST", "sa", "");

				PreparedStatement ps = con.prepareStatement("insert into additems values(?,?,?,?,?,?,?,?)");

				ps.setString(1, batch);
				ps.setString(2, item);
				ps.setString(3, mfg);
				ps.setString(4, hsn);
				ps.setString(5, sale);
				ps.setString(6, purchase);
				ps.setString(7, tx);
				ps.setString(8, stck);
				

				ps.executeUpdate();
				// System.out.println("item added successfully");

				itemlist();
				Batchtxt.requestFocus();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			blankFields();

		}
	}

	String name;
	private JLabel lblNoOfItems;
	private JLabel itemcount;
	private JLabel lblNewLabel_1;
	private JTextField searchTF;
	private JTable suggestionTable;

	public void copyItemname() {
		DefaultTableModel mt = (DefaultTableModel) table.getModel();
		name = mt.getValueAt(table.getSelectedRow(), 1).toString();
	}

	public void delItem() {

		String sql = "DELETE FROM ADDITEMS WHERE ITEM_NAME = '" + name + "'";
		try {
			Connection con = DriverManager.getConnection("jdbc:h2:C:/SimpleGST/GST", "sa", "");
			Statement st = con.createStatement();
			st.execute(sql);
			// System.out.println("deleted");
			String up = "SELECT * FROM ADDITEMS ORDER BY ITEM_NAME ASC";
			PreparedStatement ps = con.prepareStatement(up);
			ResultSet rs = ps.executeQuery();
			// while(rs.next()) {
			table.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
			// }

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		blankFields();
	}

	public void setRowToEdit(JTable table) {
		DefaultTableModel mt = (DefaultTableModel) table.getModel();
		String batch = (String) mt.getValueAt(table.getSelectedRow(), 0);
		String itemnam = (String) mt.getValueAt(table.getSelectedRow(), 1);
		String mfg = (String) mt.getValueAt(table.getSelectedRow(), 2);
		String sachsn = mt.getValueAt(table.getSelectedRow(), 3).toString();
		String sr = mt.getValueAt(table.getSelectedRow(), 4).toString();
		String pr = mt.getValueAt(table.getSelectedRow(), 5).toString();
		String tax = mt.getValueAt(table.getSelectedRow(), 6).toString();
		String stk = mt.getValueAt(table.getSelectedRow(), 7).toString();
		
		Batchtxt.setText(batch);
		itemname.setText(itemnam);
		NAMELBL.setText(itemnam);
		mfgtext.setText(mfg);
		sac.setText(sachsn);
		salerate.setText(sr);
		purchaserate.setText(pr);
		taxComboBox.setSelectedItem(tax);
		stock.setText(stk);
}

	public void updateItem() {
		String batch = Batchtxt.getText();
		String item = itemname.getText();
		String item1 = NAMELBL.getText();
		String hsn = sac.getText();
		String sale = salerate.getText();
		String purchase = purchaserate.getText();
		String stck = stock.getText();
		String tx = taxComboBox.getSelectedItem().toString();
		String mg = mfgtext.getText();

		try {
			Connection con = DriverManager.getConnection("jdbc:h2:C:/SimpleGST/GST", "sa", "");
			String sql = "update additems set batch ='" + batch + "' ,  item_name = '" + item + "' ,   manufacturer = '" + mg + "', sac_hsn = '" + hsn + "',sale_rate = '" + sale
					+ "', purchase_rate = '" + purchase + "', tax = '" + tx + "',stock = '" + stck + "' where item_name = '" + item1 + "'";
			Statement st = con.createStatement();
			st.execute(sql);

			itemlist();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void closeFrame() {

		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
		Action escapeAction = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", escapeAction);
	}

	public void searchItem(JTable table, String query) {
		String sql = "SELECT * FROM ADDITEMS WHERE item_name LIKE '%"+query+"%' ";
		
		try {
			Connection con = DriverManager.getConnection("jdbc:h2:C:/SimpleGST/GST", "sa", "");
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			table.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
		//	System.out.println(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Items() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1383, 760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		label = new JLabel("Item Name ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(258, 101, 81, 21);
		contentPane.add(label);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(361, 130, 522, 158);
		panel_2.setVisible(false);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 522, 158);
		panel_2.add(scrollPane_1);
		
		suggestionTable = new JTable();
		suggestionTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setRowToEdit(suggestionTable);
				panel_2.setVisible(false);
			}
		});
		suggestionTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		scrollPane_1.setColumnHeaderView(suggestionTable);

		itemname = new JTextField();
		itemname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		itemname.setColumns(10);
		itemname.setBorder(null);
		itemname.setBounds(361, 101, 522, 28);
		itemname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(sac , e);
				panel_2.setVisible(true);
				searchItem(suggestionTable, itemname.getText());
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					panel_2.setVisible(false);
				}
			}
			
		});
		contentPane.add(itemname);

		label_1 = new JLabel("SAC/HSN");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(926, 103, 71, 20);
		contentPane.add(label_1);

		sac = new JFormattedTextField(new RegexFormatter("[\\d]{0,10}"));
		sac.setColumns(10);
		sac.setBorder(null);
		sac.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sac.setBounds(1041, 101, 124, 28);
		sac.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(stock , e);
			}
			
		});
		contentPane.add(sac);

		label_3 = new JLabel("Stock");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_3.setBounds(258, 144, 43, 21);
		contentPane.add(label_3);

		stock = new JFormattedTextField(new RegexFormatter("[\\d]{0,10}"));
		stock.setHorizontalAlignment(SwingConstants.RIGHT);
		stock.setColumns(10);
		stock.setBorder(null);
		stock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		stock.setBounds(361, 143, 90, 28);
		stock.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(salerate , e);
			}
			
		});
		contentPane.add(stock);

		label_4 = new JLabel("Sale Rate");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_4.setBounds(488, 148, 71, 21);
		contentPane.add(label_4);

		salerate = new JFormattedTextField(new RegexFormatter("[\\d.?]{0,10}"));
		salerate.setHorizontalAlignment(SwingConstants.RIGHT);
		salerate.setColumns(10);
		salerate.setBorder(null);
		salerate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		salerate.setBounds(578, 146, 124, 28);
		salerate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(purchaserate , e);
			}
			
		});
		contentPane.add(salerate);

		label_5 = new JLabel("Purchase Rate");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_5.setBounds(746, 145, 97, 28);
		contentPane.add(label_5);

		String[] tax = { "0", "5", "12", "18", "28" };
		taxComboBox = new JComboBox(tax);
		taxComboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(mfgtext , e);
			}
		});
		taxComboBox.setBounds(361, 202, 71, 23);
		contentPane.add(taxComboBox);

		purchaserate = new JFormattedTextField(new RegexFormatter("[\\d.?]{0,10}"));
		purchaserate.setHorizontalAlignment(SwingConstants.RIGHT);
		purchaserate.setColumns(10);
		purchaserate.setBorder(null);
		purchaserate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		purchaserate.setBounds(861, 147, 124, 28);
		purchaserate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(taxComboBox , e);
			}
			
		});
		contentPane.add(purchaserate);

		label_6 = new JLabel("Tax ");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_6.setBounds(258, 200, 32, 23);
		contentPane.add(label_6);

		btnSave = new JButton("Save");
		btnSave.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					additem();

				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBackground(Color.GREEN);
		btnSave.setBounds(787, 299, 90, 33);
		contentPane.add(btnSave);

		lblNewLabel = new JLabel("");
		lblNewLabel.setToolTipText("");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(451, 386, 249, 33);
		contentPane.add(lblNewLabel);

		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				blankFields();
			}
		});
		btnClear.setBackground(Color.RED);
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClear.setBounds(1015, 299, 90, 33);
		contentPane.add(btnClear);

		NAMELBL = new JLabel("");
		NAMELBL.setForeground(new Color(240, 230, 140));
		NAMELBL.setBounds(363, 82, 321, 14);
		NAMELBL.setFont((new Font("Tahoma", Font.PLAIN, 15)));
		contentPane.add(NAMELBL);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(225, 463, 950, 272);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setRowMargin(9);
		table.setRowHeight(30);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				copyItemname();
				setRowToEdit(table);
			}
		});
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					delItem();
				}
			}
		});
				
		
		scrollPane.setViewportView(table);
		
		table.setModel(model_table);

		Object column_names[] = { "Batch", "Item Name", "Mfg", "Sac/Hsn", "Sale Rate", "Purchase Rate", "Tax(%)",
				"Stock" };

		model_table.setColumnIdentifiers(column_names);

		label_12 = new JLabel("*");
		label_12.setForeground(Color.RED);
		label_12.setBounds(336, 106, 13, 14);
		contentPane.add(label_12);

		label_14 = new JLabel("*");
		label_14.setForeground(Color.RED);
		label_14.setBounds(304, 143, 13, 21);
		contentPane.add(label_14);

		label_15 = new JLabel("*");
		label_15.setForeground(Color.RED);
		label_15.setBounds(554, 150, 13, 14);
		contentPane.add(label_15);

		label_16 = new JLabel("*");
		label_16.setForeground(Color.RED);
		label_16.setBounds(988, 102, 13, 14);
		contentPane.add(label_16);

		label_17 = new JLabel("*");
		label_17.setForeground(Color.RED);
		label_17.setBounds(842, 154, 13, 14);
		contentPane.add(label_17);

		label_19 = new JLabel("*");
		label_19.setForeground(Color.RED);
		label_19.setBounds(286, 202, 13, 14);
		contentPane.add(label_19);

		label_21 = new JLabel("Mfg ");
		label_21.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_21.setBounds(488, 202, 32, 21);
		contentPane.add(label_21);

		mfgtext = new JTextField();
		mfgtext.setColumns(10);
		mfgtext.setBorder(null);
		mfgtext.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mfgtext.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(btnSave , e);
			}
			
		});
		mfgtext.setBounds(578, 197, 412, 28);
		
		contentPane.add(mfgtext);

		label_22 = new JLabel("*");
		label_22.setForeground(Color.RED);
		label_22.setBounds(514, 206, 13, 14);
		contentPane.add(label_22);

		label_23 = new JLabel(":");
		label_23.setBounds(349, 106, 13, 14);
		contentPane.add(label_23);

		label_25 = new JLabel(":");
		label_25.setBounds(337, 204, 13, 14);
		contentPane.add(label_25);

		label_26 = new JLabel(":");
		label_26.setBounds(349, 150, 13, 14);
		contentPane.add(label_26);

		label_27 = new JLabel(":");
		label_27.setBounds(569, 139, 13, 36);
		contentPane.add(label_27);

		label_28 = new JLabel(":");
		label_28.setBounds(528, 208, 13, 14);
		contentPane.add(label_28);

		label_30 = new JLabel(":");
		label_30.setBounds(1030, 108, 4, 14);
		contentPane.add(label_30);

		label_31 = new JLabel(":");
		label_31.setBounds(853, 150, 13, 14);
		contentPane.add(label_31);

		button_3 = new JButton("Update");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateItem();
			}
		});
		button_3.setBackground(Color.CYAN);
		button_3.setBounds(899, 299, 89, 33);
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(button_3);

		label_34 = new JLabel("Batch  ");
		label_34.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_34.setBounds(258, 57, 61, 33);
		contentPane.add(label_34);

		label_35 = new JLabel("*");
		label_35.setForeground(Color.RED);
		label_35.setBounds(298, 62, 13, 14);
		contentPane.add(label_35);

		label_36 = new JLabel(":");
		label_36.setBounds(344, 68, 18, 14);
		contentPane.add(label_36);

		Batchtxt = new JTextField();
		Batchtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(itemname , e);
			}
			
		});
		Batchtxt.setColumns(10);
		Batchtxt.setBorder(null);
		Batchtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Batchtxt.setBounds(361, 61, 124, 28);
		contentPane.add(Batchtxt);

		lblNoOfItems = new JLabel("No. Of Items  :");
		lblNoOfItems.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNoOfItems.setBounds(1061, 445, 76, 14);
		contentPane.add(lblNoOfItems);

		itemcount = new JLabel("");
		itemcount.setFont(new Font("Tahoma", Font.ITALIC, 11));
		itemcount.setBounds(1138, 445, 37, 14);
		contentPane.add(itemcount);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 204, 760);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(1198, 0, 185, 760);
		contentPane.add(panel_1);
		
		lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(226, 434, 75, 25);
		contentPane.add(lblNewLabel_1);
		
		searchTF = new JTextField();
		searchTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchItem(table ,searchTF.getText());
				if(searchTF.getText() == "") {
					itemlist();
				}
			}
		});
		
		searchTF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		searchTF.setBounds(286, 434, 273, 25);
		contentPane.add(searchTF);
		searchTF.setColumns(10);
		
		

		itemlist();
		closeFrame();
	}
}
