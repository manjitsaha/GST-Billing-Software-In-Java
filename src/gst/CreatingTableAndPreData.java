package gst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreatingTableAndPreData {
	
	static Connection con =null;
	static Statement st =null;
	static ResultSet rs = null;
	
	public void createDetails() {
		try {
			
			//items table
			
			 con=DriverManager.getConnection("jdbc:h2:C:/SimpleGST/GST","sa","");
			 st = con.createStatement();
	 		String q = "CREATE TABLE additems"
					+ "(Batch varchar(255),"
					+ "item_name VARCHAR(255),"
					+ "manufacturer varchar(255),"
					+ "sac_hsn int(255),"
					+ "sale_rate float(10),"
					+ "purchase_rate float(10),"
					+ "Tax int(2),"
					+ "stock int(10))";
			st.execute(q);
			
			
			//company table
			
			String c = "CREATE TABLE company"
					+ "(company_name VARCHAR(255),"
					+ "trade_name VARCHAR(255),"
					+ "add_1 VARCHAR(255),"
					+ "add_2 VARCHAR(255),"
					+ "state VARCHAR(255),"
					+ "place VARCHAR(255),"
					+ "pin int(8),"
					+ "email_id VARCHAR(255),"
					+ "website VARCHAR(255),"
					+ "mobile_no VARCHAR(10),"
					+ "establishment_date VARCHAR(10),"
					+ "gst_number VARCHAR(20),"
					+ "gst_date VARCHAR(10),"
					+ "gst_category VARCHAR(255),"
					+ "pan VARCHAR(20),"
					+ "pan_date VARCHAR(10))";
			
			st.execute(c);
			
			/*
			 * String inscomp = "INSERT INTO COMPANY(company_name) VALUES ('SAHA STUDIOS')";
			 * st.execute(inscomp);
			 */
			
			//Adding default states
			
		String s = "CREATE TABLE states"
				+ "(States varchar(30),"
				+ "states_code int(3))";
		st.execute(s);	
		
		// Inserting states into table
		
		String an ="insert into states values ('Andaman and Nicobar Islands',35)";
		String ap ="insert into states values ('Andhra Pradesh'	,28)";
		String apn ="insert into states values('Andhra Pradesh New',37)";
		String anp ="insert into states values ('Arunachal Pradesh',12)";
		String as ="insert into states values ('Assam',18)";
		String br ="insert into states values ('Bihar',10)";
		String ch ="insert into states values ('Chandigarh',04)";
		String chtg ="insert into states values ('Chattisgarh',22)";
		String dnnh ="insert into states values ('Dadra and Nagar Haveli',26)";
		String dnd ="insert into states values ('Daman and Diu',25)";
		String dl ="insert into states values ('Delhi',7)";
		String goa ="insert into states values ('Goa',30)";
		String gj ="insert into states values ('Gujarat',24)";
		String hr ="insert into states values ('Haryana',6)";
		String hp ="insert into states values ('Himachal Pradesh',2)";
		String jk ="insert into states values ('Jammu and Kashmir',1)";
		String jh ="insert into states values ('Jharkhand',20)";
		String kr ="insert into states values ('Karnataka',29)";
		String ke ="insert into states values ('Kerla',32)";
		String ldi ="insert into states values ('Lakshadweep Islands',31)";
		String mp ="insert into states values ('Madhya Pradesh',23)";
		String mh ="insert into states values ('Maharashtra',27)";
		String mani ="insert into states values ('Manipur',14)";
		String mg ="insert into states values ('Meghalaya',17)";
		String mz ="insert into states values ('Mizoram',15)";
		String ng ="insert into states values ('Nagaland',13)";
		String od ="insert into states values ('Odisha',21)";
		String pd ="insert into states values ('Pondicherry',34)";
		String pj ="insert into states values ('Punjab',3)";
		String rj ="insert into states values ('Rajasthan',8)";
		String sk ="insert into states values ('Sikkim',11)";
		String tn ="insert into states values ('Tamil Nadu',33)";
		String tg ="insert into states values ('Telangana',36)";
		String tr ="insert into states values ('Tripura',16)";
		String up ="insert into states values ('Uttar Pradesh',9)";
		String uk ="insert into states values ('Uttarakhand',5)";
		String wb ="insert into states values ('West Bengal',19)";


		
			
			
		
			st.execute(an);
			st.execute(ap);
			st.execute(apn);
			st.execute(anp);
			st.execute(as);
			st.execute(br);
			st.execute(ch);
			st.execute(chtg);
			st.execute(dnnh);
			st.execute(dnd);
			st.execute(dl);
			st.execute(goa);
			st.execute(gj);
			st.execute(hr);
			st.execute(hp);
			st.execute(jk);
			st.execute(jh);
			st.execute(kr);
			st.execute(ke);
			st.execute(ldi);
			st.execute(mp);
			st.execute(mh);
			st.execute(mani);
			st.execute(mg);
			st.execute(mz);
			st.execute(ng);
			st.execute(od);
			st.execute(pd);
			st.execute(pj);
			st.execute(rj);
			st.execute(sk);
			st.execute(tn);
			st.execute(tg);
			st.execute(tr);
			st.execute(up);
			st.execute(uk);
			st.execute(wb);
			
			
			
			// Create customer table
			
			String com = "CREATE TABLE CUSTOMERS"
					+ "(NAME varchar(25),"
					+ "place varchar(25),"
					+ "state varchar(50),"
					+ "email varchar(100),"
					+ "add1 varchar(100),"
					+ "add2 varchar(100),"
					+ "pin int(10),"
					+ "website varchar(50),"
					+ "mob_no varchar(12),"
					+ "alt_mob varchar(12),"
					+ "fax varchar(10),"
					+ "phone varchar(10),"
					+ "gst varchar(20),"
					+ "pan varchar(20),"
					+ "pan_Date varchar(10),"
					+ "ledger_category varchar(15),"
					+ "country varchar(50))";
		
			st.execute(com);			
		
		
			
			// CREATE INVOICE NO
			
			String billno = "CREATE TABLE BILL_NO"
					+ "(NO int(10))";
			String insbill = "INSERT INTO BILL_NO VALUES('0')";
			st.execute(billno);
			st.execute(insbill);
			
			// ADMIN PANEL
			
			String user = "CREATE TABLE ADMIN"
					+ "(USERNAME VARCHAR(255),"
					+ "PASSWORD VARCHAR(255))";
			
			String def = "INSERT INTO ADMIN VALUES('admin','password')";
			
			st.execute(user);
			st.execute(def);
			
			// CREATE DUE TABLE
			
					
			String due = "CREATE TABLE DUES"
					+ "(Bill_No varchar(100),"
					+ "Date varchar(100),"
					+ "Customer_name varchar(30),"
					+ "Grand_Total float(10),"
					+ "paid float(10),"
					+ "Dues float(10))";
			
					st.execute(due);
			
			
			 
			 String tempinv = "CREATE TABLE TEMPINV"
			 		+ "(Sr int auto_increment,"
			 		+ "batch varchar(10),"
			 		+ "ITEM_NAME VARCHAR(100),"
			 		+ "MFG VARCHAR(100),"
			 		+ "SAC_HSN int(100),"
			 		+ "QTY int(10),"
			 		+ "PRICE float(17),"
			 		+ "TAX int(4),"
			 		+ "DISC float(4),"
			 		+ "TOTAL float(17))";
			 
			 st.execute(tempinv);
			 		
			 
				/*
				 * DateFormat formatter = new SimpleDateFormat("dd"); Date d = new Date(); int a
				 * = Integer.parseInt(formatter.format(d)); int b = a + 7;
				 * 
				 * String trial = "CREATE TABLE TRIAL" + "(FRO INT(3)," + "TO INT(3))"; String
				 * instrial = "INSERT INTO TRIAL(FRO , TO) VALUES('"+a+"', '"+b+"')";
				 * st.execute(trial); st.execute(instrial);
				 * 
				 */
			
			
			
			System.out.println("table added successfully"); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
			
				
				
	}

}
