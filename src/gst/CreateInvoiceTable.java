package gst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;


public class CreateInvoiceTable  {
	
	Connection con = null;
	Statement st;
	
	public String bill = Invoice.billnotext.getText()+"_";
    public String name = Invoice.namelbl.getText().toUpperCase()+"_";
    public String bill1 = Invoice.billnotext.getText();
    public String name1 = Invoice.namecombo.getSelectedItem().toString().toUpperCase();
    public String date = Invoice.datebox.getText().toUpperCase();
    public String grandt = Invoice.grandtotallabel.getText();
    public String pd = Invoice.paidtxt.getText();
    public String du =Invoice.duetxt.getText();
	
	public void invoicetable() {
		
	    
	  System.out.println(name+bill+date);
	    
	try {
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GST/INVOICESsnacks","sa","");
			 st = con.createStatement();
		
		String sql = "CREATE TABLE "+name+bill+date+""
				+ "(Bill_no int(10),"
				+ "Date varchar(100),"
				+ "Customer_Name varchar(50),"
				+ "batch varchar(255),"
				+ "Item_Name varchar(255),"
				+ "mfg varchar(255),"
				+ "sac_hsn int(10),"
				+ "packs int(10),"
				+ "Qty INT(10),"
				+ "Price FLOAT(10),"
				+ "Tax INT(2),"                         
				+ "Discount FLOAT(10),"
	            + "Total FLOAT(10),"		
				+ "Grand_Total float(10),"
				+ "paid FLOAT(10),"
				+ "DUE FLOAT(10))";
			
			st.execute(sql);
			String a = "INSERT INTO "+name+bill+date+"(Bill_no , Date ,  Customer_Name ) values('"+bill1+"','"+date+"','"+name1+"')";
			st.execute(a);
			
			DefaultTableModel mt = (DefaultTableModel)Invoice.table.getModel();
			for(int i = 0 ; i<Invoice.table.getRowCount(); i++) {
				String batch = (String) Invoice.table.getValueAt(i, 0);
				String item = (String) Invoice.table.getValueAt(i, 1);
				String packs = Invoice.table.getValueAt(i, 2).toString();
				String mfg = (String) Invoice.table.getValueAt(i, 3);
				String sac = (String) Invoice.table.getValueAt(i, 4);
				String qty = (String) Invoice.table.getValueAt(i, 5).toString();
				String price = (String) Invoice.table.getValueAt(i, 6).toString();
				String tax = (String) Invoice.table.getValueAt(i, 7).toString();
				String discount = (String) Invoice.table.getValueAt(i, 8).toString();
				String total = (String) Invoice.table.getValueAt(i, 9).toString();
				
				String b = "INSERT INTO "+name+bill+date+"(batch , Item_Name, packs ,  mfg, sac_hsn,  Qty, Price, Tax, Discount,Total) values('"+batch+"',"
				+ "'"+item+"',"
					+ "'"+Integer.parseInt(packs)+"',"
							+ "'"+mfg+"',"
								+ "'"+sac+"',"
										+ "'"+Integer.parseInt(qty)+"',"
												+ "'"+Float.parseFloat(price)+"',"
														+ "'"+Integer.parseInt(tax)+"',"
																+ "'"+Float.parseFloat(discount)+"',"
																		+ "'"+Float.parseFloat(total)+"')";
				st.execute(b);
			}
			
			
			
		String c ="INSERT INTO "+name+bill+date+"(Grand_Total , paid , due ) VALUES('"+Float.parseFloat(grandt)+"','"+Float.parseFloat(pd)+"','"+Float.parseFloat(du)+"')";
		st.execute(c);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	
	}
	
	public void insertTempInv()
	{
		
		try {
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
			 st = con.createStatement();
				DefaultTableModel mt = (DefaultTableModel)Invoice.table.getModel();
				for(int i = 0 ; i<Invoice.table.getRowCount(); i++) {
					String batch = (String) Invoice.table.getValueAt(i, 0);
					String item = (String) Invoice.table.getValueAt(i, 1);
					String packs = (String) Invoice.table.getValueAt(i, 2);
					String mfg = (String) Invoice.table.getValueAt(i, 3);
					String sac =  Invoice.table.getValueAt(i, 4).toString();
					String qty = Invoice.table.getValueAt(i, 5).toString();
					String price = Invoice.table.getValueAt(i, 6).toString();
					String tax =  Invoice.table.getValueAt(i, 7).toString();
					String discount =  Invoice.table.getValueAt(i, 8).toString();
					String total =  Invoice.table.getValueAt(i, 9).toString();
					
					String bd = "INSERT INTO tempinv(batch , Item_Name, packs , mfg, sac_hsn,  Qty, Price, Tax, Disc,Total) values('"+batch+"',"
							+ "'"+item+"',"
									+ "'"+Integer.parseInt(packs)+"',"
							+ "'"+mfg+"',"
									+ "'"+sac+"',"
											+ "'"+Integer.parseInt(qty)+"',"
													+ "'"+Float.parseFloat(price)+"',"
															+ "'"+Integer.parseInt(tax)+"',"
																	+ "'"+Float.parseFloat(discount)+"',"
																			+ "'"+Float.parseFloat(total)+"')";
					
					st.execute(bd);
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void deleteTempInv() {
		try {
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
			 st = con.createStatement();
			 
			 String del = "DROP TABLE TEMPINV";
			 st.executeUpdate(del);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		

	
	public void createTempTable() {
		
		try {
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
			 st = con.createStatement();
			 String tempinv = "CREATE TABLE TEMPINV"
					 + "(Sr int auto_increment,"
				 		+ "batch varchar(10),"
				 		+ "ITEM_NAME VARCHAR(100),"
				 		+ "packs int(5),"
				 		+ "MFG VARCHAR(100),"
				 		+ "SAC_HSN int(100),"
				 		+ "QTY int(10),"
				 		+ "PRICE float(17),"
				 		+ "TAX int(4),"
				 		+ "DISC float(4),"
				 		+ "TOTAL float(17))";
				 
				 st.execute(tempinv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
			 
	}


	
	
	}

