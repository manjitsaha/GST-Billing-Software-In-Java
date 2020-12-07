package gst;


import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

public class PastTransaction extends JFrame {

	private JPanel contentPane;
	private JTable table;
    private DefaultTableModel model;
    private DefaultTableModel modtab = new DefaultTableModel();
	private JScrollPane scrollPane;
	private DefaultTableModel mt;
	private String tablename;
	private JLabel qty2;
	private JLabel totalsumlbl;
	private JButton btnUpdate;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PastTransaction frame = new PastTransaction();
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
	PreparedStatement st;
	ResultSet rs;
	Statement ps;
	private JTable table_1;
	private JLabel lblQty;
	private JLabel label_3;
	private JLabel lblPrice;
	private JLabel label_5;
	private JLabel lblTax;
	private JLabel label_6;
	private JLabel lblDiscount;
	private JLabel label_7;
	private JLabel lblTotal;
	private JLabel label_8;
	private JTextField mfgtxt;
	private JTextField sachsntxt;
	private JTextField qtytxt;
	private JTextField pricetxt;
	private JTextField discounttxt;
	private JTextField totaltxt;
	private JLabel lblGrandTotal;
	private JLabel label_9;
	private JLabel grandtxt;
	private JLabel p1;
	private JComboBox itemCombo;
	private JComboBox taxcombo;
	private JLabel grandtotlbl;
	private JButton modifybtn;
	private JLabel stocklbl;
	private JLabel lblStock;
	private JLabel outslbl;
	
	

	
	public void DisplayPastTrx() {
		
		/*
		 * try { con =
		 * DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GST/INVOICESsnacks",
		 * "sa",""); String[] types = {"TABLE"}; DatabaseMetaData metadata =
		 * con.getMetaData(); ResultSet resultSet = metadata.getTables(null, null, "%",
		 * types);
		 * 
		 * 
		 * while (resultSet.next()) {
		 * 
		 * Vector row = new Vector(); row.addElement(resultSet.getString(3));
		 * model.addRow(row); }
		 * 
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		
        String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'";
		
		try {
			Connection con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GST/INVOICESsnacks", "sa", "");
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			table.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
		//	System.out.println(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	
	
	public void selectedBillInfo() {
		
		 mt =  (DefaultTableModel)table.getModel();
		 tablename = mt.getValueAt(table.getSelectedRow(), 0).toString();
		try {
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GST/INVOICESsnacks","sa","");
			String sql = "SELECT * FROM "+tablename+" ORDER BY ITEM_NAME ASC";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			//if(rs.next()){
				
				table_1.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
				
                 //     }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void setRowToEdit() {
		
	 DefaultTableModel	nt = (DefaultTableModel)table_1.getModel();
	
		String name = nt.getValueAt(table_1.getSelectedRow(), 4).toString();
		String mfg = nt.getValueAt(table_1.getSelectedRow(), 5).toString();
		String sachsn = nt.getValueAt(table_1.getSelectedRow(), 6).toString();
		String qty = nt.getValueAt(table_1.getSelectedRow(), 7).toString();
		String price = nt.getValueAt(table_1.getSelectedRow(), 8).toString();
		String tax = nt.getValueAt(table_1.getSelectedRow(), 9).toString();
		String disc = nt.getValueAt(table_1.getSelectedRow(), 10).toString();
		String total = nt.getValueAt(table_1.getSelectedRow(), 11).toString();
		
		itemCombo.setSelectedItem(name);
		mfgtxt.setText(mfg);
		sachsntxt.setText(sachsn);
		qtytxt.setText(qty);
		qty2.setText(qty);
		pricetxt.setText(price);
		taxcombo.setSelectedItem(tax);
		discounttxt.setText(disc);
		totaltxt.setText(total);
		
	}
	
	
	
	public void setTotalTotal() {
		
		 final  String a = pricetxt.getText();
		    float b = Float.parseFloat(a);
		    String c = discounttxt.getText();
	        float d = Float.parseFloat(c);
	        float e = b*d/100;
	        float mm = b-e;
	        String g = taxcombo.getSelectedItem().toString();
	        float h = Float.parseFloat(g);
	        float i = mm+(mm*h/100);
	        String j = String.format("%.2f", i);
	        totaltxt.setText(j);
		
	}
	
	

	public void setItemPrice() {
		
		DefaultTableModel ts = (DefaultTableModel)table_1.getModel();
		try {
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
			String in = ts.getValueAt(table_1.getSelectedRow(), 4).toString();
			String price = "SELECT SALE_RATE FROM ADDITEMS WHERE ITEM_NAME = '"+in+"' ";
			st = con.prepareStatement(price);
			rs = st.executeQuery();
			if(rs.next()) {
				p1.setText(rs.getString("SALE_RATE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void updatingPriceAsQty() {
		
		 final String a = p1.getText();
		    float b = Float.parseFloat(a);
		    String c=	qtytxt.getText();
	        float d = Float.parseFloat(c);
	        float e = b*d;
	        String f = String.format("%.2f", e);
	        pricetxt.setText(f);
	        totaltxt.setText(f);
	     
	}
	
	
	
	
	public void DeleteBill() {
		
		try {
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GST/INVOICESsnacks","sa","");
			String del = "DROP TABLE "+tablename+" ";
			
			ps = con.createStatement();
			ps.execute(del);
			
			model.setRowCount(0);
			
			DisplayPastTrx();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void populateItemName() {
		
		try {
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
			st = con.prepareStatement("SELECT ITEM_NAME FROM ADDITEMS ORDER BY ITEM_NAME ASC");
			rs = st.executeQuery();
			while(rs.next()) {
				itemCombo.addItem(rs.getString("ITEM_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public void setDue() {
		String pd = pdtxt.getText();
		String tot = grandtxt.getText();
	    String pad = pdlbl.getText();
		if(pd.isEmpty()) {
			pdtxt.setText("0");
		}else {
		
		float a = Float.parseFloat(pd);
		float b = Float.parseFloat(tot);
		float e = Float.parseFloat(pad);
		float c = a+e;
		float f = b-c;
		
		String d = String.format("%.2f", f);
		dutxt.setText(d);
		
		}
	}
	
	
	
	public void updatepaidanddue() {
		String pd = pdtxt.getText();
		String du = dutxt.getText();
		String pdl = pdlbl.getText();
		String dl = duelbl.getText();
		float a = Float.parseFloat(pdl);
		float b = Float.parseFloat(pd);
		
		float c = a+b;
		String d = String.format("%.2f", c);
		
		try {
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GST/INVOICESsnacks","sa","");
			String sl = "UPDATE "+tablename+" SET PAID ='"+d+"' WHERE PAID = '"+pdl+"' ";
			String sql = "UPDATE "+tablename+" SET due ='"+du+"' WHERE due = '"+dl+"' ";
			ps = con.createStatement();
			ps.execute(sl);
			ps.execute(sql);
			System.out.println("added");
			
			selectedBillInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void showPaidandDue() {
		
		try {
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GST/INVOICESsnacks","sa","");
			String sql = "SELECT PAID , DUE FROM "+tablename+"";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			
			while(rs.next()) {
				pdlbl.setText(rs.getString("paid"));
				duelbl.setText(rs.getString("due"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void modifyBills() {
             String itemname = itemCombo.getSelectedItem().toString();
             String mfg = mfgtxt.getText();
             String sac = sachsntxt.getText();                                                                    
             String qty = qtytxt.getText();
             String price = pricetxt.getText();
             String tax = taxcombo.getSelectedItem().toString();
             String disc = discounttxt.getText();
             String total = totaltxt.getText();
             String grandtotal = grandtxt.getText();
             String gtot = grandtotlbl.getText();
             
            
             
          
             
		try {
			
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GST/INVOICESsnacks","sa","");
			String up = "UPDATE "+tablename+" SET ITEM_NAME = '"+itemname+"', mfg = '"+mfg+"', sac_hsn = '"+sac+"' , Qty = '"+qty+"', Price = '"+price+"', Tax = '"+tax+"' , Discount = '"+disc+"', Total = '"+total+"'  WHERE ITEM_NAME = '"+itemname+"' " ;
			String upp = "UPDATE "+tablename+" SET Grand_Total = '"+grandtotal+"' WHERE Grand_Total = '"+gtot+"' ";
			ps = con.createStatement();
			ps.execute(up);
			ps.execute(upp);
			
			
			st = con.prepareStatement("SELECT * FROM "+tablename+" ORDER BY ITEM_NAME ASC ");
			rs = st.executeQuery();
			
			
			 table_1.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
			 selectedBillInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void setGrandTotal() {
		try {
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GST/INVOICESsnacks","sa","");
			String sql = "SELECT Grand_Total from "+tablename+"";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			
			while(rs.next()) {
				grandtxt.setText(rs.getString("Grand_Total"));
				grandtotlbl.setText(rs.getString("Grand_Total"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void discount() {
		 final  String a = pricetxt.getText();
		    float b = Float.parseFloat(a);
		    String c = discounttxt.getText();
	        float d = Float.parseFloat(c);
	        float e = b*d/100;
	        float mm = b-e;
	       
	        String g = taxcombo.getSelectedItem().toString();
	        float h = Float.parseFloat(g);
	        float i = mm+(mm*h/100);
	        String j = String.format("%.2f", i);
	        totaltxt.setText(j);
	}
	
	
	
	
	public void updatePriceAsQty() {
		 final String a = p1.getText();
		    float b = Float.parseFloat(a);
		    String c=	qtytxt.getText();
	        float d = Float.parseFloat(c);
	        float e = b*d;
	        String f = String.format("%.2f", e);
	        pricetxt.setText(f);
	}
	
	
	
	
	public void subPastGtotal() {
		
		String dp = p1.getText();
		String qt = qty2.getText();
		String gt = grandtotlbl.getText();
		String tx = taxcombo.getSelectedItem().toString();
		
		
		final	String z = p1.getText();
	    float x = Float.parseFloat(z);
		String ta  =  taxcombo.getSelectedItem().toString();
		float t = Float.parseFloat(ta);
	    String a=	qty2.getText();
	    float b = Float.parseFloat(a);
	    float c = b*x*t/100;
		
		
		float pr = Float.parseFloat(dp);
		float qty =Float.parseFloat(qt);
		float gto = Float.parseFloat(gt);
		float tot = pr*qty+c;
		float gtt = gto-tot;
		String fi = String.format("%.2f", gtt);
		grandtxt.setText(fi);
		
	}
	
	
	
	
	public void setFinalGTotal() {
		
		String t = totaltxt.getText();
		String gt = grandtxt.getText();
		final	String z = p1.getText();
	    float x = Float.parseFloat(z);
		String ta  =  taxcombo.getSelectedItem().toString();
		float tt = Float.parseFloat(ta);
	    String w=	qtytxt.getText();
	    float v = Float.parseFloat(w);
	    float c = v*x*tt/100;
		
		float a = Float.parseFloat(t);
		float b = Float.parseFloat(gt);
		float n = a+b;
		String d = String.format("%.2f", n);
		grandtxt.setText(d);
		
		
	}
	
	
	
	
	public void showStock() {
		
		 String name = itemCombo.getSelectedItem().toString();
		  try {
				con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
			
				String sql1 = "SELECT STOCK FROM ADDITEMS WHERE ITEM_NAME = '"+name+"'";
				ps = con.createStatement();
				st = con.prepareStatement(sql1);
				rs = st.executeQuery();
				while(rs.next()) {
					stocklbl.setText(rs.getString("STOCK"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	
	
	public void subtractStock() {
		
		 String w=	qtytxt.getText();
		    int v = Integer.parseInt(w);
		    String qt = qty2.getText();
		    int b = Integer.parseInt(qt);
		    String stk = stocklbl.getText(); 
		    int s = Integer.parseInt(stk);
		    
		    
		    int f = s+b;
		    String ff = Integer.toString(f);
		    stocklbl.setText(ff);
		    
		    
		    int g = f-v;
		    String gg = Integer.toString(g);
		    stocklbl.setText(gg);
		    
	}
	
	
	public void updateStock() {
		
	
		    String name = itemCombo.getSelectedItem().toString();
		    String stk = stocklbl.getText();
		  
		    
		    try {
				con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
				String sql = "UPDATE ADDITEMS SET STOCK = '"+stk+"' WHERE ITEM_NAME = '"+name+"' ";
				ps = con.createStatement();
				ps.execute(sql);
				showStock();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
	}
	
	
	String nn;
	private JTextField pdtxt;
	private JTextField dutxt;
	private JLabel pdlbl;
	private JLabel duelbl;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField searchTF;
	private JLabel lblNewLabel;
	public void copyItemName() {
		
		mt = (DefaultTableModel)table_1.getModel();
		 nn = mt.getValueAt(table_1.getSelectedRow(), 4).toString();
		
		
	}
	
	
	
	public void delItem()  {
		
		try {
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GST/INVOICESsnacks","sa","");
			String sql = "DELETE FROM "+tablename+" WHERE ITEM_NAME = '"+nn+"'";
			st = con.prepareStatement(sql);
			st.execute();
			
			
			selectedBillInfo();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void addGTotalPriceAfDel() {
	     
		try {
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GST/INVOICESsnacks","sa","");
			String sql = "SELECT SUM(TOTAL) AS SUMOF FROM "+tablename+" ";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				totalsumlbl.setText(rs.getString("SUMOF"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	
	
	public void updateNewGTot() {
		
		String tot = totalsumlbl.getText();
		String pg = grandtotlbl.getText();
	
		
		try {
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GST/INVOICESsnacks","sa","");
			String sql = "UPDATE "+tablename+" SET GRAND_TOTAL = '"+tot+"' WHERE GRAND_TOTAL = '"+pg+"'";
			ps = con.createStatement();
			ps.execute(sql);
			selectedBillInfo();
			grandtotlbl.setText(tot);
			grandtxt.setText(tot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void outofstock() {
		
		String out = stocklbl.getText();
		if(out.isEmpty()) {
			stocklbl.setText("0");
		}
		int a = Integer.parseInt(out);
		if(a<=10) {
			outslbl.setText("FILL STOCK");
		}else {
			outslbl.setText(" ");
		}
		
	}
	
	
	
	public void setDelItemStock() {

		String name = itemCombo.getSelectedItem().toString();
		String stock = stocklbl.getText();
		String qty = qty2.getText();
		
		int a = Integer.parseInt(stock);
		int b = Integer.parseInt(qty);
		int c = a+b;
		
		String d = Integer.toString(c);
		
		try {
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
			String sql = "UPDATE ADDITEMS SET STOCK = '"+d+"' WHERE ITEM_NAME = '"+name+"'";
			ps = con.createStatement();
			ps.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public void deleteBillAfterPayingDue() {
		
		float due = Float.parseFloat(dutxt.getText());
		float paid = Float.parseFloat(pdlbl.getText());
		float du =Float.parseFloat(duelbl.getText());
	
		if(due <= 0) {
			try {
				con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
				String sql = "DELETE FROM DUES WHERE BILL_NO ='"+tablename+"'";
				st = con.prepareStatement(sql);
				st.execute();
				System.out.println(due);
				System.out.println(tablename);
				System.out.println("deleted due bill");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			
			try {
				con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
				String sl = "UPDATE DUES SET PAID = '"+paid+"' , DUES ='"+du+"' WHERE BILL_NO = '"+tablename+"'";
				ps = con.createStatement();
				ps.execute(sl);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	 public void closeFrame() {
			
			KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		    Action escapeAction = new AbstractAction() {
		      public void actionPerformed(ActionEvent e) {
		        dispose();
		      }
		    };
		    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		        escapeKeyStroke, "manjit");
		    getRootPane().getActionMap().put("manjit", escapeAction);
		    
		  
		}

	 public void searchItem() {

		 String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC' AND TABLE_NAME LIKE '%"+searchTF.getText()+"%'";
			
			try {
				Connection con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GST/INVOICESsnacks", "sa", "");
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				table.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
			//	System.out.println(rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	public PastTransaction() {
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1383,760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		String[] columns = {"Invoices"};
		 model = new DefaultTableModel();
			   
		  table = new JTable(model  );
		  table.addKeyListener(new KeyAdapter() {
		  	@Override
		  	public void keyPressed(KeyEvent e) {
		  		if(e.getKeyCode()==KeyEvent.VK_DELETE) {
		  			
		  			DeleteBill();
		  			
		  		}
		  	}
		  });
		    table.addMouseListener(new MouseAdapter() {
		    	@Override
		    	public void mouseClicked(MouseEvent arg0) {
		    	selectedBillInfo();
		    	setGrandTotal();
		    	showPaidandDue();
					
		    	}
		    });
		    table.setModel(model);
		    model.setColumnIdentifiers(columns);
			scrollPane = new JScrollPane( table );
			scrollPane.setBounds(596, 91, 559, 297);
			contentPane.add( scrollPane );
			scrollPane.setViewportView(table);
			
			
			Object column[]= {"Bill No","Date","Customer Name","Item","Mfg","Sac/Hsn","Qty","Price","Tax","Disc","Total","Grand Total"};
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(225, 407, 930, 265);
			contentPane.add(scrollPane_1);
			
			table_1 = new JTable();
			table_1.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_DELETE) {
						copyItemName();
						setDelItemStock();
						delItem();
						addGTotalPriceAfDel();
						updateNewGTot();
					
					}
				}
			});
			table_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					populateItemName();
					setRowToEdit();
					setTotalTotal();
					setItemPrice();
					showStock();
					outofstock();
				
					
					
					
				}
			});
			table_1.setModel(modtab);
			modtab.setColumnIdentifiers(column);
			scrollPane_1.setViewportView(table_1);
			
			JLabel lblItemName = new JLabel("Item Name");
			lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblItemName.setBounds(225, 68, 63, 22);
			contentPane.add(lblItemName);
			
			JLabel label = new JLabel(":");
			label.setBounds(308, 68, 14, 22);
			contentPane.add(label);
			
			JLabel lblMfg = new JLabel("Mfg");
			lblMfg.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblMfg.setBounds(225, 100, 46, 22);
			contentPane.add(lblMfg);
			
			JLabel label_1 = new JLabel(":");
			label_1.setBounds(308, 100, 14, 22);
			contentPane.add(label_1);
			
			JLabel lblSachsn = new JLabel("Sac/Hsn");
			lblSachsn.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblSachsn.setBounds(225, 133, 63, 22);
			contentPane.add(lblSachsn);
			
			JLabel label_2 = new JLabel(":");
			label_2.setBounds(308, 133, 14, 22);
			contentPane.add(label_2);
			
			lblQty = new JLabel("Qty");
			lblQty.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblQty.setBounds(225, 166, 63, 22);
			contentPane.add(lblQty);
			
			label_3 = new JLabel(":");
			label_3.setBounds(308, 164, 14, 24);
			contentPane.add(label_3);
			
			lblPrice = new JLabel("Price");
			lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblPrice.setBounds(225, 199, 63, 22);
			contentPane.add(lblPrice);
			
			label_5 = new JLabel(":");
			label_5.setBounds(308, 197, 14, 24);
			contentPane.add(label_5);
			
			lblTax = new JLabel("Tax");
			lblTax.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTax.setBounds(225, 232, 63, 22);
			contentPane.add(lblTax);
			
			label_6 = new JLabel(":");
			label_6.setBounds(308, 230, 14, 22);
			contentPane.add(label_6);
			
			lblDiscount = new JLabel("Discount");
			lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblDiscount.setBounds(225, 265, 63, 22);
			contentPane.add(lblDiscount);
			
			label_7 = new JLabel(":");
			label_7.setBounds(308, 263, 14, 24);
			contentPane.add(label_7);
			
			lblTotal = new JLabel("Total");
			lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTotal.setBounds(225, 298, 63, 22);
			contentPane.add(lblTotal);
			
			label_8 = new JLabel(":");
			label_8.setBounds(308, 296, 14, 22);
			contentPane.add(label_8);
			
			mfgtxt = new JTextField();
			mfgtxt.setOpaque(false);
			mfgtxt.setColumns(10);
			mfgtxt.setBorder(null);
			mfgtxt.setBounds(318, 101, 255, 22);
			contentPane.add(mfgtxt);
			
			sachsntxt = new JTextField();
			sachsntxt.setOpaque(false);
			sachsntxt.setColumns(10);
			sachsntxt.setBorder(null);
			sachsntxt.setBounds(318, 135, 122, 22);
			contentPane.add(sachsntxt);
			
			qtytxt = new JTextField();
			qtytxt.addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent arg0) {
					
				}
			});
			qtytxt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					subPastGtotal();
					updatePriceAsQty();
					subtractStock();
					outofstock();

					discounttxt.requestFocus();
				}
			});
			qtytxt.setOpaque(false);
			qtytxt.setColumns(10);
			qtytxt.setBorder(null);
			qtytxt.setBounds(318, 168, 122, 22);
			contentPane.add(qtytxt);
			
			pricetxt = new JTextField();
			pricetxt.setEditable(false);
			pricetxt.setOpaque(false);
			pricetxt.setColumns(10);
			pricetxt.setBorder(null);
			pricetxt.setBounds(318, 201, 122, 22);
			contentPane.add(pricetxt);
			
			discounttxt = new JTextField();
			
			discounttxt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					discount();
					setFinalGTotal();
					modifybtn.requestFocus();
					
					
				}
			});
			discounttxt.setOpaque(false);
			discounttxt.setColumns(10);
			discounttxt.setBorder(null);
			discounttxt.setBounds(318, 267, 122, 22);
			contentPane.add(discounttxt);
			
			totaltxt = new JTextField();
			totaltxt.setEditable(false);
			totaltxt.setOpaque(false);
			totaltxt.setColumns(10);
			totaltxt.setBorder(null);
			totaltxt.setBounds(318, 300, 122, 22);
			contentPane.add(totaltxt);
			
			lblGrandTotal = new JLabel("Grand Total");
			lblGrandTotal.setForeground(SystemColor.textHighlight);
			lblGrandTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblGrandTotal.setBounds(225, 366, 73, 22);
			contentPane.add(lblGrandTotal);
			
			label_9 = new JLabel(":");
			label_9.setForeground(SystemColor.textHighlight);
			label_9.setBounds(308, 364, 14, 24);
			contentPane.add(label_9);
			
			grandtxt = new JLabel("");
			grandtxt.setForeground(SystemColor.textHighlight);
			grandtxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
			grandtxt.setBounds(318, 364, 122, 24);
			contentPane.add(grandtxt);
			
			p1 = new JLabel("");
			p1.setForeground(new Color(240, 230, 140));
			p1.setBounds(445, 204, 29, 14);
			contentPane.add(p1);
			
			 modifybtn = new JButton("Modify");
			 modifybtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					modifyBills();
					updateStock();
					setGrandTotal();
					
					
					
				}
			});
			modifybtn.setBounds(497, 367, 89, 23);
			contentPane.add(modifybtn);
			
			itemCombo = new JComboBox();
			itemCombo.setBounds(318, 69, 255, 22);
			contentPane.add(itemCombo);
			
			String tax [] = {"0","5","12","18","28"};
			
			taxcombo = new JComboBox(tax);
			taxcombo.setBounds(318, 232, 52, 22);
			contentPane.add(taxcombo);
			
			 qty2 = new JLabel("");
			 qty2.setForeground(new Color(240, 230, 140));
			qty2.setBounds(441, 171, 46, 14);
			contentPane.add(qty2);
			
			grandtotlbl = new JLabel("");
			grandtotlbl.setForeground(new Color(240, 230, 140));
			grandtotlbl.setBounds(441, 374, 46, 14);
			contentPane.add(grandtotlbl);
			
			stocklbl = new JLabel("");
			stocklbl.setBounds(374, 48, 46, 14);
			contentPane.add(stocklbl);
			
			lblStock = new JLabel("Stock  :");
			lblStock.setBounds(318, 48, 46, 14);
			contentPane.add(lblStock);
			
			totalsumlbl = new JLabel("");
			totalsumlbl.setForeground(new Color(240, 230, 140));
			totalsumlbl.setBounds(1058, 684, 46, 14);
			contentPane.add(totalsumlbl);
			
			outslbl = new JLabel("");
			outslbl.setForeground(Color.RED);
			outslbl.setBounds(430, 48, 177, 14);
			contentPane.add(outslbl);
			
			pdtxt = new JTextField();
			pdtxt.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						setDue();
						btnUpdate.requestFocus();
					}
				}
			});
			pdtxt.setHorizontalAlignment(SwingConstants.RIGHT);
			pdtxt.setColumns(10);
			pdtxt.setBounds(694, 690, 86, 20);
			contentPane.add(pdtxt);
			
			JLabel label_4 = new JLabel("Paid");
			label_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
			label_4.setBounds(601, 698, 37, 14);
			contentPane.add(label_4);
			
			JLabel label_10 = new JLabel("Due");
			label_10.setFont(new Font("Tahoma", Font.PLAIN, 13));
			label_10.setBounds(813, 690, 37, 20);
			contentPane.add(label_10);
			
			dutxt = new JTextField();
			dutxt.setHorizontalAlignment(SwingConstants.RIGHT);
			dutxt.setEditable(false);
			dutxt.setColumns(10);
			dutxt.setBounds(863, 690, 86, 20);
			contentPane.add(dutxt);
			
			JLabel label_11 = new JLabel(":");
			label_11.setBounds(684, 688, 14, 22);
			contentPane.add(label_11);
			
			JLabel label_12 = new JLabel(":");
			label_12.setBounds(849, 690, 14, 22);
			contentPane.add(label_12);
			
			pdlbl = new JLabel("");
			pdlbl.setBounds(717, 709, 46, 14);
			contentPane.add(pdlbl);
			
			duelbl = new JLabel("");
			duelbl.setBounds(888, 709, 46, 14);
			contentPane.add(duelbl);
			
			btnUpdate = new JButton("Update");
			btnUpdate.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						updatepaidanddue();
						showPaidandDue();
						deleteBillAfterPayingDue();
					}
				}
			});
			btnUpdate.setBounds(975, 689, 89, 23);
			contentPane.add(btnUpdate);
			
			panel = new JPanel();
			panel.setBackground(Color.GRAY);
			panel.setBounds(0, 0, 190, 760);
			contentPane.add(panel);
			
			panel_1 = new JPanel();
			panel_1.setBackground(Color.GRAY);
			panel_1.setBounds(1185, 0, 198, 760);
			contentPane.add(panel_1);
			
			searchTF = new JTextField();
			searchTF.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					searchItem();
					if(searchTF.getText() == "") {
						DisplayPastTrx();
					}
				}
			});
			searchTF.setFont(new Font("Tahoma", Font.PLAIN, 13));
			searchTF.setColumns(10);
			searchTF.setBounds(656, 56, 273, 25);
			contentPane.add(searchTF);
			
			lblNewLabel = new JLabel("Search");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNewLabel.setBounds(596, 56, 75, 25);
			contentPane.add(lblNewLabel);
			
			
			
	

		
		DisplayPastTrx();
		closeFrame();
		
		
		
	}
}
