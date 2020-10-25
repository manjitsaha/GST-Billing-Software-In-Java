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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Items extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JTextField itemname;
	private JLabel label_1;
	private JFormattedTextField sac;
	private JLabel label_2;
	private JLabel label_3;
	private JFormattedTextField stock;
	private JLabel label_4;
	private JFormattedTextField salerate;
	private JLabel label_5;
	private JComboBox comboBox_1;
	private JFormattedTextField purchaserate;
	private JLabel label_6;
	private JLabel label_7;
	private JFormattedTextField cessrate;
	private JLabel label_8;
	private JButton btnSave;
	private JTextArea ta1;
	private JTextArea ta;
	private JLabel label_9;
	private JFormattedTextField packs;
	private JLabel lblNewLabel;
	private JButton btnClear;
	private JLabel NAMELBL;
	private JScrollPane scrollPane;
	private JLabel label_12;
	private JLabel label_13;
	private JLabel label_14;
	private JLabel label_15;
	private JLabel label_16;
	private JLabel label_17;
	private JLabel label_18;
	private JLabel label_19;
	private JLabel label_21;
	private JTextField mfgtext;
	private JLabel label_22;
	private JLabel label_23;
	private JLabel label_24;
	private JLabel label_25;
	private JLabel label_26;
	private JLabel label_27;
	private JLabel label_28;
	private JLabel label_29;
	private JLabel label_30;
	private JLabel label_31;
	private JLabel label_32;
	private JLabel label_33;
	private JButton button_3;
	private JLabel label_34;
	private JLabel label_35;
	private JLabel label_36;
	private JTextField Batchtxt;
	private JTable table;
	

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
		DefaultTableModel mt = (DefaultTableModel)table.getModel();
		int count = mt.getRowCount();
		String co = Integer.toString(count);
		itemcount.setText(co);
		
	}
	
	
	public void itemlist() {
		 ResultSet rs = null;
			PreparedStatement st = null;
			
				try {
					con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
					String h = "SELECT * FROM ADDITEMS ORDER BY ITEM_NAME ASC";
					st = con.prepareStatement(h);
					rs = st.executeQuery();
					
		//			while(rs.next()){
					table.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
         //             }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				countItem();
          	}
	
	
	public void blankFields() {
		
		Batchtxt.setText("");
		itemname.setText("");
		packs.setText("");
		sac.setText("");
		salerate.setText("");
		purchaserate.setText("");
		cessrate.setText("");
		stock.setText("");
		ta.setText("");
		ta1.setText("");
		mfgtext.setText("");
		comboBox_1.setSelectedIndex(0);
		
	}
	

	
	public void additem() {
		
		String empt = itemname.getText();
		if(empt.isEmpty()){
			lblNewLabel.setText("FILL ALL * FIELDS");
		}else{
		try {
			
			String batch = Batchtxt.getText();
			String item=itemname.getText();
			String hsn = sac.getText();
			String ut =  packs.getText();
			String sale = salerate.getText();
			String purchase = purchaserate.getText();
			String stck = stock.getText();
			String desc = ta.getText();
			String rmrk = ta1.getText();
			String tx =  comboBox_1.getSelectedItem().toString();
			String mfg = mfgtext.getText();
			
			
			Connection con=DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
			
			PreparedStatement ps = con.prepareStatement("insert into additems values(?,?,?,?,?,?,?,?,?,?,?)");
		   
			ps.setString(1, batch);
		    ps.setString(2, item);
		    ps.setString(3, mfg);
		    ps.setString(4, hsn);
		    ps.setString(5, ut);
		    ps.setString(6, sale);
		    ps.setString(7, purchase);
		    ps.setString(8, tx);
		    ps.setString(9, stck);
		    ps.setString(10, desc);
		    ps.setString(11, rmrk);
		    
		  ps.executeUpdate();
	//		System.out.println("item added successfully"); 
			
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
	public void copyItemname() {
		DefaultTableModel mt = (DefaultTableModel)table.getModel();
		 name = mt.getValueAt(table.getSelectedRow(), 1).toString();
	}
	
	public void delItem() {
		
		String sql = "DELETE FROM ADDITEMS WHERE ITEM_NAME = '"+name+"'";
		try {
			Connection con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
			Statement st = con.createStatement();
			st.execute(sql);
		//	System.out.println("deleted");
			String up = "SELECT * FROM ADDITEMS ORDER BY ITEM_NAME ASC";
			PreparedStatement ps = con.prepareStatement(up);
			ResultSet rs = ps.executeQuery();
	//		while(rs.next()) {
				table.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
		//	}
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		blankFields();
	}
	
	
	public void setRowToEdit() {
		DefaultTableModel mt = (DefaultTableModel)table.getModel();
		String batch = (String) mt.getValueAt(table.getSelectedRow(), 0);
		String itemnam =(String) mt.getValueAt(table.getSelectedRow(), 1);
		String mfg =(String) mt.getValueAt(table.getSelectedRow(), 2);
		String sachsn = mt.getValueAt(table.getSelectedRow(), 3).toString();
		String pack = mt.getValueAt(table.getSelectedRow(), 4).toString();
		String sr = mt.getValueAt(table.getSelectedRow(), 5).toString();
		String pr = mt.getValueAt(table.getSelectedRow(), 6).toString();
		String tax = mt.getValueAt(table.getSelectedRow(), 7).toString();
		String stk = mt.getValueAt(table.getSelectedRow(), 8).toString();
		String desc =(String) mt.getValueAt(table.getSelectedRow(), 9);
		String rmrk =(String) mt.getValueAt(table.getSelectedRow(), 10);
		
	
		Batchtxt.setText(batch);
		itemname.setText(itemnam);
		NAMELBL.setText(itemnam);
		mfgtext.setText(mfg);
		sac.setText(sachsn);
		packs.setText(pack);
		salerate.setText(sr);
		purchaserate.setText(pr);
		comboBox_1.setSelectedItem(tax);
		stock.setText(stk);
		ta.setText(desc);
		ta1.setText(rmrk);
	}
	
	
	public void updateItem() {
		String batch = Batchtxt.getText();
		String item=itemname.getText();
		String item1=NAMELBL.getText();
		String hsn = sac.getText();
		String ut =  packs.getText();
		String sale = salerate.getText();
		String purchase = purchaserate.getText();
		String stck = stock.getText();
		String desc = ta.getText();
		String rmrk = ta1.getText();
		String tx =  comboBox_1.getSelectedItem().toString();
		String mg = mfgtext.getText();
		
		try {
			Connection con=DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
			String sql ="update additems set batch ='"+batch+"' ,  item_name = '"+item+"' , PACKS = '"+ut+"' ,  manufacturer = '"+mg+"', sac_hsn = '"+hsn+"',sale_rate = '"+sale+"', purchase_rate = '"+purchase+"', tax = '"+tx+"',stock = '"+stck+"',description = '"+desc+"', remarks = '"+rmrk+"' where item_name = '"+item1+"'" ;
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
	      public void actionPerformed(ActionEvent e) {
	        dispose();
	      }
	    };
	    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	        escapeKeyStroke, "ESCAPE");
	    getRootPane().getActionMap().put("ESCAPE", escapeAction);
	}
	
	
	
	public Items() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1383,760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		label = new JLabel("Item Name ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(258, 101, 81, 21);
		contentPane.add(label);
		
		itemname = new JTextField();
		itemname.setColumns(10);
		itemname.setBorder(null);
		itemname.setBounds(361, 101, 522, 28);
		contentPane.add(itemname);
		
		label_1 = new JLabel("SAC/HSN");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(926, 103, 71, 20);
		contentPane.add(label_1);
		
		sac = new JFormattedTextField(new RegexFormatter("[\\d]{0,10}"));
		sac.setColumns(10);
		sac.setBorder(null);
		sac.setBounds(1041, 101, 124, 28);
		contentPane.add(sac);
		
		label_2 = new JLabel("Packs");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBounds(258, 148, 43, 21);
		contentPane.add(label_2);
		
		label_3 = new JLabel("Stock");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_3.setBounds(451, 148, 43, 21);
		contentPane.add(label_3);
		
		stock = new JFormattedTextField(new RegexFormatter("[\\d]{0,10}"));
		stock.setHorizontalAlignment(SwingConstants.RIGHT);
		stock.setColumns(10);
		stock.setBorder(null);
		stock.setBounds(544, 146, 90, 28);
		contentPane.add(stock);
		
		label_4 = new JLabel("Sale Rate");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_4.setBounds(669, 143, 71, 21);
		contentPane.add(label_4);
		
		salerate = new JFormattedTextField(new RegexFormatter("[\\d.?]{0,10}"));
		salerate.setHorizontalAlignment(SwingConstants.RIGHT);
		salerate.setColumns(10);
		salerate.setBorder(null);
		salerate.setBounds(759, 141, 124, 28);
		contentPane.add(salerate);
		
		label_5 = new JLabel("Purchase Rate");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_5.setBounds(926, 144, 97, 28);
		contentPane.add(label_5);
		
		 String[] tax ={"0","5","12","18","28"};
		comboBox_1 = new JComboBox(tax);
		comboBox_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					cessrate.requestFocus();
				}
			}
		});
		comboBox_1.setBounds(361, 202, 71, 23);
		contentPane.add(comboBox_1);
		
		purchaserate = new JFormattedTextField(new RegexFormatter("[\\d.?]{0,10}"));
		purchaserate.setHorizontalAlignment(SwingConstants.RIGHT);
		purchaserate.setColumns(10);
		purchaserate.setBorder(null);
		purchaserate.setBounds(1041, 146, 124, 28);
		contentPane.add(purchaserate);
		
		label_6 = new JLabel("Tax ");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_6.setBounds(258, 200, 32, 23);
		contentPane.add(label_6);
		
		label_7 = new JLabel("Cess Rate");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_7.setBounds(451, 195, 90, 28);
		contentPane.add(label_7);
		
		cessrate = new JFormattedTextField(new RegexFormatter("[\\d.?]{0,10}"));
		cessrate.setHorizontalAlignment(SwingConstants.RIGHT);
		cessrate.setColumns(10);
		cessrate.setBorder(null);
		cessrate.setBounds(544, 197, 90, 28);
		contentPane.add(cessrate);
		
		label_8 = new JLabel("Description");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_8.setBounds(258, 242, 90, 56);
		contentPane.add(label_8);
		
		btnSave = new JButton("Save");
		btnSave.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					additem();
					
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSave.setBackground(Color.GREEN);
		btnSave.setBounds(793, 386, 90, 33);
		contentPane.add(btnSave);
		
		ta1 = new JTextArea();
		ta1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					btnSave.requestFocus();
				}
			}
		});
		ta1.setOpaque(false);
		ta1.setBorder(null);
		ta1.setBounds(365, 312, 792, 56);
		contentPane.add(ta1);
		
		ta = new JTextArea();
		ta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					ta1.requestFocus();
				}
			}
		});
		ta.setOpaque(false);
		ta.setBorder(null);
		ta.setBounds(365, 245, 792, 56);
		contentPane.add(ta);
		
		label_9 = new JLabel("Remarks");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_9.setBounds(258, 309, 90, 56);
		contentPane.add(label_9);
		
		packs = new JFormattedTextField(new RegexFormatter("[\\d]{0,10}"));
		packs.setBorder(null);
		packs.setBounds(361, 147, 71, 26);
		contentPane.add(packs);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setToolTipText("");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel.setBounds(451, 386, 249, 33);
		contentPane.add(lblNewLabel);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				blankFields();
			}
		});
		btnClear.setBackground(Color.RED);
		btnClear.setBounds(1021, 386, 90, 33);
		contentPane.add(btnClear);
		
		NAMELBL = new JLabel("");
		NAMELBL.setForeground(new Color(240, 230, 140));
		NAMELBL.setBounds(363, 82, 321, 14);
		contentPane.add(NAMELBL);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(225, 463, 950, 272);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				copyItemname();
				setRowToEdit();
			}
		});
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_DELETE) {
					delItem();
				}
			}
		});
		scrollPane.setViewportView(table);
		
		label_12 = new JLabel("*");
		label_12.setForeground(Color.RED);
		label_12.setBounds(336, 106, 13, 14);
		contentPane.add(label_12);
		
		label_13 = new JLabel("*");
		label_13.setForeground(Color.RED);
		label_13.setBounds(298, 153, 13, 14);
		contentPane.add(label_13);
		
		label_14 = new JLabel("*");
		label_14.setForeground(Color.RED);
		label_14.setBounds(487, 146, 13, 21);
		contentPane.add(label_14);
		
		label_15 = new JLabel("*");
		label_15.setForeground(Color.RED);
		label_15.setBounds(736, 148, 13, 14);
		contentPane.add(label_15);
		
		label_16 = new JLabel("*");
		label_16.setForeground(Color.RED);
		label_16.setBounds(988, 102, 13, 14);
		contentPane.add(label_16);
		
		label_17 = new JLabel("*");
		label_17.setForeground(Color.RED);
		label_17.setBounds(1022, 153, 13, 14);
		contentPane.add(label_17);
		
		label_18 = new JLabel("*");
		label_18.setForeground(Color.RED);
		label_18.setBounds(521, 202, 13, 14);
		contentPane.add(label_18);
		
		label_19 = new JLabel("*");
		label_19.setForeground(Color.RED);
		label_19.setBounds(286, 202, 13, 14);
		contentPane.add(label_19);
		
		label_21 = new JLabel("Mfg ");
		label_21.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_21.setBounds(669, 198, 32, 21);
		contentPane.add(label_21);
		
		mfgtext = new JTextField();
		mfgtext.setColumns(10);
		mfgtext.setBorder(null);
		mfgtext.setBounds(746, 197, 137, 28);
		contentPane.add(mfgtext);
		
		label_22 = new JLabel("*");
		label_22.setForeground(Color.RED);
		label_22.setBounds(699, 198, 13, 14);
		contentPane.add(label_22);
		
		label_23 = new JLabel(":");
		label_23.setBounds(349, 106, 13, 14);
		contentPane.add(label_23);
		
		label_24 = new JLabel(":");
		label_24.setBounds(335, 155, 13, 14);
		contentPane.add(label_24);
		
		label_25 = new JLabel(":");
		label_25.setBounds(337, 204, 13, 14);
		contentPane.add(label_25);
		
		label_26 = new JLabel(":");
		label_26.setBounds(497, 153, 13, 14);
		contentPane.add(label_26);
		
		label_27 = new JLabel(":");
		label_27.setBounds(750, 134, 13, 36);
		contentPane.add(label_27);
		
		label_28 = new JLabel(":");
		label_28.setBounds(709, 204, 13, 14);
		contentPane.add(label_28);
		
		label_29 = new JLabel(":");
		label_29.setBounds(531, 204, 13, 14);
		contentPane.add(label_29);
		
		label_30 = new JLabel(":");
		label_30.setBounds(1030, 108, 4, 14);
		contentPane.add(label_30);
		
		label_31 = new JLabel(":");
		label_31.setBounds(1032, 155, 13, 14);
		contentPane.add(label_31);
		
		label_32 = new JLabel(":");
		label_32.setBounds(336, 265, 13, 14);
		contentPane.add(label_32);
		
		label_33 = new JLabel(":");
		label_33.setBounds(323, 332, 13, 14);
		contentPane.add(label_33);
		
		button_3 = new JButton("Update");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateItem();
			}
		});
		button_3.setBackground(Color.CYAN);
		button_3.setBounds(905, 386, 89, 33);
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
		Batchtxt.setColumns(10);
		Batchtxt.setBorder(null);
		Batchtxt.setBounds(361, 61, 124, 28);
		contentPane.add(Batchtxt);
		
		lblNoOfItems = new JLabel("No. Of Items  :");
		lblNoOfItems.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNoOfItems.setBounds(225, 446, 76, 14);
		contentPane.add(lblNoOfItems);
		
		itemcount = new JLabel("");
		itemcount.setFont(new Font("Tahoma", Font.ITALIC, 11));
		itemcount.setBounds(302, 446, 37, 14);
		contentPane.add(itemcount);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 204, 760);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(1198, 0, 185, 760);
		contentPane.add(panel_1);
		
		itemlist();
		closeFrame();
	}
}
