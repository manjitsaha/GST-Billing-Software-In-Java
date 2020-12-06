package gst;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Invoice extends JFrame implements Runnable {

	private JPanel contentPane;
	public static JTextField billnotext;
	public static JTextField datebox;
	private JTextField sachsntext;
	private JTextField qtytext;
	private JTextField pricetext;
	private JTextField taxtext;
	public static JTable table;
	private JTextField discounttext;
	private JTextField sgstrate;
	private JTextField taxablevalue;
	private JTextField taxratetext;
	private JTextField cgstrate;
	private JTextField cgstamount;
	private JTextField sgstamount;
	public static JComboBox namecombo;
	public static JComboBox itemcombo;
	private JLabel custadd1;
	private JLabel custmob;
	private JLabel custadd2;
	private DefaultTableModel model_table = new DefaultTableModel();
	private static JLabel p1;
	public static JLabel grandtotallabel;
	private JLabel totalamountlabel;

	private JButton btnAdd;
	public static JLabel namelbl;
	public static JTextField paidtxt;
	public static JTextField duetxt;
	private static JLabel custGstin;
	private JLabel custCountry;
	private JLabel custPin;
	private JLabel custState;
	private JLabel compgstin;
	private JLabel compmob;
	private JLabel comppin;
	private JLabel compstate;
	private JLabel compadd1;
	private JLabel compadd2;
	private JLabel compname;
	private JLabel compplace;
	private JLabel cgst14lbl;
	private JLabel sgst14lbl;
	private JLabel sgst9lbl;
	private JLabel cgst9lbl;
	private JLabel cgst6lbl;
	private JLabel sgst6lbl;
	private JLabel cgst2lbl;
	private JLabel sgst2lbl;
	private JLabel cgst14;
	private JLabel sgst14;
	private JLabel cgst9;
	private JLabel sgst9;
	private JLabel cgst6;
	private JLabel sgst6;
	private JLabel cgst25;
	private JLabel sgst25;
	private JLabel itemcount;
	private JLabel discountedlbl;
	Thread t = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Invoice frame = new Invoice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con = null;
	ResultSet rs;
	java.sql.Statement st = null;
	private JTextField discounted;
	private JTextField total;
	private JTextField stocktext;
	private JLabel lblMfg;
	private JTextField mfgtext;
	private JLabel lblDiscountAmt;
	private JLabel label_1;
	private JTextField timebox;
	private JTextField batchtxt;
	private JLabel compemail;
	private JLabel compwebsite;
	private JLabel custtype;
	private JLabel lblMaxItem;

	public void commonMethodForSt(String query) {
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}

	public void closeFrame() {

		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		Action escapeAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "manjit");
		getRootPane().getActionMap().put("manjit", escapeAction);

	}

	public void populateCompDetails() {
		try {
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks", "sa", "");
			commonMethodForSt("select * from company");
			while (rs.next()) {
				compname.setText(rs.getString("company_name"));
				compadd1.setText(rs.getString("add_1"));
				compadd2.setText(rs.getString("add_2"));
				compstate.setText(rs.getString("state"));
				comppin.setText(rs.getString("pin"));
				compplace.setText(rs.getString("place"));
				compmob.setText(rs.getString("mobile_no"));
				compgstin.setText(rs.getString("gst_number"));
				compemail.setText(rs.getString("email_id"));
				compwebsite.setText(rs.getString("website"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void populatename() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks", "sa", "");
			namecombo.addItem("_");
			commonMethodForSt("SELECT * FROM CUSTOMERS ORDER BY NAME ASC ");
			while (rs.next()) {
				namecombo.addItem(rs.getString("name"));
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void populateNameDetail() {
		try {

			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks", "sa", "");
			String name = namecombo.getSelectedItem().toString();
			commonMethodForSt("SELECT * FROM CUSTOMERS WHERE NAME = '" + name + "'");
			if (rs.next()) {
				custadd1.setText(rs.getString("add1"));
				custadd2.setText(rs.getString("add2"));
				custmob.setText(rs.getString("mob_no"));
				custGstin.setText(rs.getString("gst"));
				custState.setText(rs.getString("state"));
				custPin.setText(rs.getString("pin"));
				custCountry.setText(rs.getString("country"));
				custtype.setText(rs.getString("ledger_category"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void populateItemCombo() {
		try {
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks", "sa", "");
			itemcombo.addItem("_");
			commonMethodForSt("SELECT * FROM ADDITEMS ORDER BY ITEM_NAME ASC");
			while (rs.next()) {
				itemcombo.addItem(rs.getString("item_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void populateItemNameAndDetails() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks", "sa", "");
			qtytext.setText("1");
			discounttext.setText("0");

			String pname = itemcombo.getSelectedItem().toString();
			commonMethodForSt("select * from additems where item_name='" + pname + "'");
			if (rs.next()) {
				p1.setText(rs.getString("sale_rate"));
				sachsntext.setText(rs.getString("sac_hsn"));
				pricetext.setText(rs.getString("sale_rate"));
				taxtext.setText(rs.getString("tax"));
				stocktext.setText(rs.getString("stock"));
				mfgtext.setText(rs.getString("manufacturer"));
				batchtxt.setText(rs.getString("batch"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void FillingRateField() {
		final String tax = taxtext.getText();
		if (tax.isEmpty()) {
			cgstrate.setText("0");
			sgstrate.setText("0");
			taxratetext.setText("0");

		}
		if (tax.equals("5")) {
			cgstrate.setText("2.5");
			sgstrate.setText("2.5");
			taxratetext.setText("2.5");
		}

		if (tax.equals("12")) {
			cgstrate.setText("6");
			sgstrate.setText("6");
			taxratetext.setText("6");
		}
		if (tax.equals("18")) {
			cgstrate.setText("9");
			sgstrate.setText("9");
			taxratetext.setText("9");
		}
		if (tax.equals("28")) {
			cgstrate.setText("14");
			sgstrate.setText("14");
			taxratetext.setText("14");
		}
	}

	public void fillingAmountField() {
		String check = p1.getText();
		if (check.isEmpty()) {
			p1.setText("0");
		}
		final String amount = p1.getText();
		float divide = Float.parseFloat(amount);
		float tot = divide / 2;
		String fin = String.format("%.2f", tot);
		cgstamount.setText(fin);
		sgstamount.setText(fin);

	}

	public void fillingAmountFieldAsNewPrice() {

		final String amount = pricetext.getText();
		float divide = Float.parseFloat(amount);
		float tot = divide / 2;
		String fin = String.format("%.2f", tot);
		cgstamount.setText(fin);
		sgstamount.setText(fin);

	}

	public void calculatingTaxValue() {
		String check = p1.getText();
		if (check.isEmpty()) {
			p1.setText("0");
		}

		final String z = p1.getText();
		float x = Float.parseFloat(z);
		String ta = taxtext.getText();
		float t = Float.parseFloat(ta);
		String a = qtytext.getText();
		float b = Float.parseFloat(a);
		float c = b * x * t / 100;
		String f = String.format("%.2f", c);
		taxablevalue.setText(f);

	}

	public void Discount() {
		String check = p1.getText();
		if (check.isEmpty()) {
			p1.setText("0");
		}

		final String a = pricetext.getText();
		float b = Float.parseFloat(a);
		String c = discounttext.getText();
		float d = Float.parseFloat(c);
		float e = b * d / 100;
		float mm = b - e;
		String f = String.format("%.2f", mm);
		discounted.setText(f);
		String g = taxtext.getText();
		float h = Float.parseFloat(g);
		float i = mm + (mm * h / 100);
		String j = String.format("%.2f", i);
		total.setText(j);

	}

	public void updatingPriceAndTaxesAsQty() {
		String check = p1.getText();
		if (check.isEmpty()) {
			p1.setText("0");
		}
		final String a = p1.getText();
		float b = Float.parseFloat(a);
		String c = qtytext.getText();
		float d = Float.parseFloat(c);
		float e = b * d;
		String f = String.format("%.2f", e);
		pricetext.setText(f);
		float g = e / 2;
		String h = String.format("%.2f", g);

		cgstamount.setText(h);
		sgstamount.setText(h);

	}

	public void updatingTotalAsPriceAndTax() {
		String check = p1.getText();
		if (check.isEmpty()) {
			p1.setText("0");
		}
		final String a = pricetext.getText();
		float b = Float.parseFloat(a);
		String i = taxablevalue.getText();
		float j = Float.parseFloat(i);
		float k = b + j;
		String l = String.format("%.2f", k);
		total.setText(l);
	}

	public void setItemDetailInTable() {

		Object row[] = new Object[20];

		row[0] = batchtxt.getText();
		row[1] = itemcombo.getSelectedItem().toString();
		row[2] = mfgtext.getText();
		row[3] = sachsntext.getText();
		row[4] = qtytext.getText();
		row[5] = pricetext.getText();
		row[6] = taxtext.getText();
		row[7] = discounttext.getText();
		row[8] = total.getText();

		model_table.addRow(row);
		if (table.getRowCount() > 24) {
			model_table.setRowCount(24);
		}
		itemcombo.requestFocus();
	}

	public void setAllFieldToZero() {

		sachsntext.setText("0");
		qtytext.setText("0");
		pricetext.setText("0");
		taxtext.setText("0");
		taxablevalue.setText("0");
		taxratetext.setText("0");
		discounttext.setText("0");
		discounted.setText("0");
		cgstamount.setText("0");
		cgstrate.setText("0");
		sgstamount.setText("0");
		sgstrate.setText("0");
		total.setText("0");
		custadd1.setText(" ");
		custadd2.setText(" ");
		custmob.setText(" ");
		namecombo.setSelectedIndex(0);
		namecombo.requestFocus();
	}

	public void IncreasingBillNo() {
		try {
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks", "sa", "");
			commonMethodForSt("select max(NO) from BILL_NO");

			while (rs.next()) {
				int billn = rs.getInt("max(NO)");
				billn += 1;
				System.out.println(billn);
				billnotext.setText(Integer.toString(billn));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void insertingBillNoIntoDatabase() {
		try {

			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks", "sa", "");
			PreparedStatement ps = con.prepareStatement("insert into bill_no values(?)");
			ps.setString(1, billnotext.getText());
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addingTotalAmountWithTax() {

		float total = 0;
		for (int i = 0; i < table.getRowCount(); i++) {

			float amount = Float.parseFloat((String) table.getValueAt(i, 8));
			total += amount;
		}
		String tot = String.format("%.2f", total);
		grandtotallabel.setText(tot);
	}

	public void addingTotalAmount() {
		float totalprice = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			float totalamount = Float.parseFloat(table.getValueAt(i, 6).toString());
			totalprice += totalamount;
		}
		String d = String.format("%.2f", totalprice);
		totalamountlabel.setText(d);
	}

	public void deletingRow() {
		int i = table.getSelectedRow();
		if (i >= 0) {
			model_table.removeRow(i);
		}

		float total = 0;
		for (int i1 = 0; i1 < table.getRowCount(); i1++) {

			float amount = Float.parseFloat((String) table.getValueAt(i1, 9));
			total += amount;
		}
		String to = String.format("%.2f", total);
		grandtotallabel.setText(to);
	}

	public void substractingStock() {
		String getstock = stocktext.getText();
		int s = Integer.parseInt(getstock);
		String getqty = qtytext.getText();
		int q = Integer.parseInt(getqty);
		int subtract = s - q;
		String f = Integer.toString(subtract);
		stocktext.setText(f);
	}

	public void StockUpdate() {
		String fp = stocktext.getText();
		try {
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks", "sa", "");
			String stock = itemcombo.getSelectedItem().toString();
			PreparedStatement us = con
					.prepareStatement("update additems set stock = '" + fp + "'where item_name ='" + stock + "'");

			us.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void outofstock() {
		String out = stocktext.getText();
		if (out.isEmpty()) {
			stocktext.setText("0");
		}
		int a = Integer.parseInt(out);
		if (a <= 10) {
			label_1.setText("FILL STOCK");
		} else {
			label_1.setText(" ");
		}

	}

	public void setDate() {
		DateFormat formatter = new SimpleDateFormat("dd_MMM_yyyy");
		Date d = new Date();
		datebox.setText(formatter.format(d));
	}

	public void setTime() {

		while (true) {
//			Calendar cal = Calendar.getInstance();
//			hours = cal.get(Calendar.HOUR_OF_DAY);
//			min = cal.get(Calendar.MINUTE);
//			sec = cal.get(Calendar.SECOND);
//			SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
//			Date date = cal.getTime();

			String timeString = String.format("%tr", new Date());
			timebox.setText(timeString.substring(0, 8));

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void setPaid() {

		String pd = paidtxt.getText();
		String tot = grandtotallabel.getText();

		if (pd.isEmpty()) {

			paidtxt.setText(tot);
			duetxt.setText("0");

		}

	}

	public void setDue() {
		String pd = paidtxt.getText();
		String tot = grandtotallabel.getText();
		if (pd.isEmpty()) {
			paidtxt.setText("0");
		} else {

			float a = Float.parseFloat(pd);
			float b = Float.parseFloat(tot);
			float c = b - a;

			String d = String.format("%.2f", c);
			duetxt.setText(d);

		}
	}

	public void insertDueDetail() {
		String name = namelbl.getText().toUpperCase() + "_";
		String name1 = namecombo.getSelectedItem().toString();
		String bill = billnotext.getText() + "_";
		String date = datebox.getText().toUpperCase();
		String Gtot = grandtotallabel.getText();
		String paid = paidtxt.getText();
		String due = duetxt.getText();
		System.out.println(name + bill + date);
		float duee = Float.parseFloat(due);

		if (duee > 0) {
			try {
				con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks", "sa", "");
				String sql = "INSERT INTO DUES(Bill_No ,Date , Customer_name ,  Grand_Total , paid , Dues) VALUES ('"
						+ name + bill + date + "' ," + "'" + date + "', " + "'" + name1 + "' ," + " '"
						+ Float.parseFloat(Gtot) + "' , " + "'" + Float.parseFloat(paid) + "' ," + "'" + duee + "')";

				PreparedStatement ps = con.prepareStatement(sql);
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void countitem() {
		int count = table.getRowCount();
		String co = Integer.toString(count);
		itemcount.setText(co);
	}

	public void showDiscountedAmt() {
		String price = pricetext.getText();
		String disc = discounted.getText();
		String disclbl = discountedlbl.getText();
		float pr = Float.parseFloat(price);
		float di = Float.parseFloat(disc);
		float disco = pr - di;
		float dis = Float.parseFloat(disclbl);
		float t = dis + disco;
		String to = String.format("%.2f", t);
		discountedlbl.setText(to);

	}

	public void minnusdiscamtondel() {

		DefaultTableModel mt = (DefaultTableModel) table.getModel();
		String disc = mt.getValueAt(table.getSelectedRow(), 8).toString();
		float di = Float.parseFloat(disc);
		String price = mt.getValueAt(table.getSelectedRow(), 6).toString();
		float pr = Float.parseFloat(price);
		float per = pr * di / 100;

		String fd = discountedlbl.getText();
		float df = Float.parseFloat(fd);

		float t = df - per;
		String d = String.format("%.2f", t);
		discountedlbl.setText(d);

	}

	public void settaxbelow() {

		String tax = taxtext.getText();
		float t = Float.parseFloat(tax);
		String sgpr = pricetext.getText();

		String sg25 = sgst2lbl.getText();
		float sg = Float.parseFloat(sg25);
		String cg25 = cgst2lbl.getText();
		float cg = Float.parseFloat(cg25);

		String sg6 = sgst6lbl.getText();
		float s6g = Float.parseFloat(sg6);
		String cg6 = cgst6lbl.getText();
		float c6g = Float.parseFloat(cg6);

		String sg9 = sgst9lbl.getText();
		float s9g = Float.parseFloat(sg9);
		String cg9 = cgst9lbl.getText();
		float c9g = Float.parseFloat(cg9);

		String sg14 = sgst14lbl.getText();
		float s14g = Float.parseFloat(sg14);
		String cg14 = cgst14lbl.getText();
		float c14g = Float.parseFloat(cg14);

		float pr = Float.parseFloat(sgpr);
		float ta = pr * t / 100;
		float di = ta / 2;

		if (t == 5) {

			float s = sg + di;
			String ss = String.format("%.2f", s);
			sgst2lbl.setText(ss);
			cgst2lbl.setText(ss);

		} else if (t == 12) {

			float c = s6g + di;
			String cc = String.format("%.2f", c);
			sgst6lbl.setText(cc);
			cgst6lbl.setText(cc);

		} else if (t == 18) {
			float c1 = s9g + di;
			String cc1 = String.format("%.2f", c1);
			sgst9lbl.setText(cc1);
			cgst9lbl.setText(cc1);

		} else if (t == 28) {

			float c2 = s14g + di;
			String cc2 = String.format("%.2f", c2);
			sgst14lbl.setText(cc2);
			cgst14lbl.setText(cc2);
		}
	}

	public void decreasebelowtax() {
		DefaultTableModel mt = (DefaultTableModel) table.getModel();

		String gt = mt.getValueAt(table.getSelectedRow(), 7).toString();
		float t = Float.parseFloat(gt);
		String g = mt.getValueAt(table.getSelectedRow(), 6).toString();
		float gp = Float.parseFloat(g);
		float gg = gp * t / 100;
		float tot = gg / 2;

		String sg25 = sgst2lbl.getText();
		float sg = Float.parseFloat(sg25);
		String cg25 = cgst2lbl.getText();
		float cg = Float.parseFloat(cg25);

		String sg6 = sgst6lbl.getText();
		float s6g = Float.parseFloat(sg6);
		String cg6 = cgst6lbl.getText();
		float c6g = Float.parseFloat(cg6);

		String sg9 = sgst9lbl.getText();
		float s9g = Float.parseFloat(sg9);
		String cg9 = cgst9lbl.getText();
		float c9g = Float.parseFloat(cg9);

		String sg14 = sgst14lbl.getText();
		float s14g = Float.parseFloat(sg14);
		String cg14 = cgst14lbl.getText();
		float c14g = Float.parseFloat(cg14);

		if (t == 5) {
			float c = sg - tot;
			String cc = String.format("%.2f", c);
			sgst2lbl.setText(cc);
			cgst2lbl.setText(cc);

		} else if (t == 12) {
			float d = s6g - tot;
			String dd = String.format("%.2f", d);
			sgst6lbl.setText(dd);
			cgst6lbl.setText(dd);

		} else if (t == 18) {
			float e = s9g - tot;
			String ee = String.format("%.2f", e);
			sgst9lbl.setText(ee);
			cgst9lbl.setText(ee);

		} else if (t == 28) {
			float f = s14g - tot;
			String ff = String.format("%.2f", f);
			sgst14lbl.setText(ff);
			cgst14lbl.setText(ff);
		}

	}

	public void printInvoice() {

		String tot = totalamountlabel.getText();
		String gtot = grandtotallabel.getText();
		float to = Float.parseFloat(tot);
		float gto = Float.parseFloat(gtot);
		float ta = gto - to;
		String tax = String.format("%.2f", ta);

		DateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
		Date d = new Date();

		setPaid();
		insertingBillNoIntoDatabase();
		CreateInvoiceTable op = new CreateInvoiceTable();
		op.invoicetable();
		op.insertTempInv();
		insertDueDetail();

		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks", "sa", "");

			HashMap param = new HashMap();

			param.put("company_name", compname.getText());
			param.put("add1", compadd1.getText());
			param.put("add2", compadd2.getText());
			param.put("compgstin", compgstin.getText());
			param.put("compState", compstate.getText());
			param.put("place", compplace.getText());
			param.put("compPin", comppin.getText());
			param.put("compmob", compmob.getText());
			param.put("compemail", compemail.getText());
			param.put("compwebsite", compwebsite.getText());
			param.put("Invoice_no", billnotext.getText());
			param.put("InvoiceDate", formatter.format(d));
			param.put("InvoiceTime", timebox.getText());
			param.put("custname", namecombo.getSelectedItem().toString().toUpperCase());
			param.put("custadd1", custadd1.getText());
			param.put("custadd2", custadd2.getText());
			param.put("custstate", custState.getText());
			param.put("custpin", custPin.getText());
			param.put("custMob", custmob.getText());
			param.put("custType", custtype.getText());
			param.put("custgstin", custGstin.getText());
			param.put("total", totalamountlabel.getText());
			param.put("grandtotal", grandtotallabel.getText());
			param.put("totalno", itemcount.getText());
			param.put("taxamt", tax);
			param.put("discamt", discountedlbl.getText());
			param.put("paid", paidtxt.getText());
			param.put("due", duetxt.getText());
			param.put("sgst2.5", sgst2lbl.getText());
			param.put("cgst2.5", cgst2lbl.getText());
			param.put("sgst6", sgst6lbl.getText());
			param.put("cgst6", cgst6lbl.getText());
			param.put("sgst9", sgst9lbl.getText());
			param.put("cgst9", cgst9lbl.getText());
			param.put("sgst14", sgst14lbl.getText());
			param.put("cgst14", cgst14lbl.getText());
			param.put("jurisdiction", compplace.getText().toUpperCase() + " " + "JURISDICTION Only");
			param.put("compsign", "For " + compname.getText());

			BigDecimal numberVariable = new BigDecimal("" + gtot);
			String numberInWords = NumberToWords.convertNumberToWords(numberVariable);
			param.put("amtinwords", numberInWords);

			JasperDesign jd;
			jd = JRXmlLoader.load(new File("").getAbsoluteFile() + "/src/GSTls.jrxml");

			JasperReport jr = JasperCompileManager.compileReport(jd);
			@SuppressWarnings("unchecked")
			JasperPrint jp = JasperFillManager.fillReport(jr, param, con);
			JasperViewer jv = new JasperViewer(jp, false);
			jv.setVisible(true);

		} catch (SQLException | JRException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("success print");
		setAllFieldToZero();
		itemcombo.setSelectedIndex(0);
		paidtxt.setText("0");
		duetxt.setText("0");
		op.deleteTempInv();
		op.createTempTable();
		IncreasingBillNo();

	}

	public Invoice() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1383, 760);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name           :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(240, 84, 86, 29);
		contentPane.add(lblName);

		namecombo = new JComboBox();
		namecombo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					itemcombo.requestFocus();
					namelbl.setText(namecombo.getSelectedItem().toString().replaceAll(" ", "_"));
				}
			}
		});
		namecombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				populateNameDetail();
			}
		});
		namecombo.setBounds(353, 85, 272, 29);
		contentPane.add(namecombo);

		JLabel lblBillNo = new JLabel("Invoice No. :");
		lblBillNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBillNo.setBounds(240, 123, 103, 27);
		contentPane.add(lblBillNo);

		billnotext = new JTextField();
		billnotext.setBackground(new Color(0, 0, 0));
		billnotext.setForeground(new Color(255, 255, 255));
		billnotext.setFont(new Font("Tahoma", Font.BOLD, 15));
		billnotext.setEditable(false);
		billnotext.setBounds(353, 124, 188, 29);
		contentPane.add(billnotext);
		billnotext.setColumns(10);

		JLabel lblDate = new JLabel("Date   :");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDate.setBounds(984, 95, 65, 21);
		contentPane.add(lblDate);

		datebox = new JTextField();
		datebox.setBackground(Color.BLACK);
		datebox.setForeground(Color.WHITE);
		datebox.setEditable(false);
		datebox.setBounds(1051, 90, 86, 24);
		contentPane.add(datebox);
		datebox.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(208, 161, 990, 2);
		contentPane.add(separator);

		JLabel lblItemName = new JLabel("Item Name ");
		lblItemName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblItemName.setBounds(240, 170, 73, 27);
		contentPane.add(lblItemName);

		itemcombo = new JComboBox();
		itemcombo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					qtytext.requestFocus();
				}
			}
		});
		itemcombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				pricetext.setText("0");
				populateItemNameAndDetails();
				fillingAmountField();
				FillingRateField();

			}
		});
		itemcombo.setBounds(240, 197, 366, 22);
		contentPane.add(itemcombo);

		sachsntext = new JTextField();
		sachsntext.setEditable(false);
		sachsntext.setHorizontalAlignment(SwingConstants.RIGHT);
		sachsntext.setBounds(628, 197, 86, 22);
		contentPane.add(sachsntext);
		sachsntext.setColumns(10);

		JLabel lblSachsnCode = new JLabel("SAC/HSN Code");
		lblSachsnCode.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSachsnCode.setBounds(628, 177, 86, 14);
		contentPane.add(lblSachsnCode);

		qtytext = new JTextField();
		qtytext.setForeground(new Color(0, 0, 0));
		qtytext.setBorder(null);
		qtytext.setBackground(new Color(240, 230, 140));
		qtytext.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					calculatingTaxValue();
					updatingPriceAndTaxesAsQty();
					substractingStock();
					pricetext.requestFocus();

				}
			}
		});
		qtytext.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				updatingTotalAsPriceAndTax();
				qtytext.setBackground(new Color(240, 230, 140));
				qtytext.setForeground(Color.BLACK);

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				qtytext.setBackground(Color.BLACK);
				qtytext.setForeground(Color.WHITE);
				qtytext.selectAll();
			}
		});

		qtytext.setHorizontalAlignment(SwingConstants.RIGHT);
		qtytext.setColumns(10);
		qtytext.setBounds(818, 197, 50, 22);
		contentPane.add(qtytext);

		JLabel lblQty = new JLabel("Qty");
		lblQty.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQty.setBounds(822, 176, 46, 14);
		contentPane.add(lblQty);

		pricetext = new JTextField();
		pricetext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillingAmountFieldAsNewPrice();
				discounttext.requestFocus();
			}
		});
		pricetext.setHorizontalAlignment(SwingConstants.RIGHT);
		pricetext.setColumns(10);
		pricetext.setBounds(878, 195, 72, 22);
		contentPane.add(pricetext);

		taxtext = new JTextField();
		taxtext.setHorizontalAlignment(SwingConstants.RIGHT);
		taxtext.setEditable(false);
		taxtext.setColumns(10);
		taxtext.setBounds(960, 197, 86, 22);
		contentPane.add(taxtext);

		JLabel label = new JLabel("Price");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(878, 174, 58, 14);
		contentPane.add(label);

		JLabel lblTax = new JLabel("Tax");
		lblTax.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTax.setBounds(984, 176, 46, 14);
		contentPane.add(lblTax);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(240, 318, 918, 258);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma" , Font.PLAIN , 16));
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					minnusdiscamtondel();
					decreasebelowtax();
					deletingRow();
					countitem();
					addingTotalAmount();
				}
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(model_table);

		Object column_names[] = { "Batch", "Item Name", "Mfg", "Sac/Hsn", "Qty", "Price", "Tax(%)",
				"Discount(%)", "Total" };

		model_table.setColumnIdentifiers(column_names);

		final JButton btnPrint = new JButton("Print");
		btnPrint.setMnemonic('p');
		btnPrint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					printInvoice();

				}
			}
		});
		
		btnPrint.setBounds(1035, 674, 89, 23);
		contentPane.add(btnPrint);

		discounttext = new JTextField();
		discounttext.setBackground(new Color(240, 230, 140));
		discounttext.setBorder(null);
		discounttext.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				discounttext.setBackground(Color.BLACK);
				discounttext.setForeground(Color.WHITE);
				discounttext.selectAll();

			}

			@Override
			public void focusLost(FocusEvent arg0) {
				discounttext.setBackground(new Color(240, 230, 140));
				discounttext.setForeground(Color.BLACK);
			}
		});

		discounttext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Discount();

				if (qtytext.getText().equals("0")) {
					qtytext.requestFocus();
				} else {

					btnAdd.requestFocus();

				}
			}
		});
		discounttext.setHorizontalAlignment(SwingConstants.RIGHT);
		discounttext.setBounds(1072, 199, 86, 20);
		contentPane.add(discounttext);
		discounttext.setColumns(10);

		JLabel lblDiscount = new JLabel("Discount (%)");
		lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDiscount.setBounds(1072, 177, 86, 14);
		contentPane.add(lblDiscount);

		JLabel lblTaxRate = new JLabel("Tax Rate");
		lblTaxRate.setBounds(240, 230, 46, 20);
		contentPane.add(lblTaxRate);

		JLabel lblCgstRate = new JLabel("CGST Rate");
		lblCgstRate.setBounds(443, 232, 65, 20);
		contentPane.add(lblCgstRate);

		JLabel lblSutgstRate = new JLabel("S/UTGST Rate");
		lblSutgstRate.setBounds(644, 231, 86, 14);
		contentPane.add(lblSutgstRate);

		sgstrate = new JTextField();
		sgstrate.setHorizontalAlignment(SwingConstants.RIGHT);
		sgstrate.setEditable(false);
		sgstrate.setColumns(10);
		sgstrate.setBounds(736, 228, 86, 22);
		contentPane.add(sgstrate);

		JLabel lblTaxableValue = new JLabel("Taxable Value");
		lblTaxableValue.setBounds(240, 252, 73, 23);
		contentPane.add(lblTaxableValue);

		taxablevalue = new JTextField();
		taxablevalue.setHorizontalAlignment(SwingConstants.RIGHT);
		taxablevalue.setEditable(false);
		taxablevalue.setColumns(10);
		taxablevalue.setBounds(330, 253, 86, 22);
		contentPane.add(taxablevalue);

		taxratetext = new JTextField();
		taxratetext.setHorizontalAlignment(SwingConstants.RIGHT);
		taxratetext.setEditable(false);
		taxratetext.setColumns(10);
		taxratetext.setBounds(330, 230, 86, 22);
		contentPane.add(taxratetext);

		cgstrate = new JTextField();
		cgstrate.setHorizontalAlignment(SwingConstants.RIGHT);
		cgstrate.setEditable(false);
		cgstrate.setColumns(10);
		cgstrate.setBounds(522, 231, 86, 22);
		contentPane.add(cgstrate);

		JLabel lblCgstAmount = new JLabel("CGST Amount");
		lblCgstAmount.setBounds(443, 258, 80, 20);
		contentPane.add(lblCgstAmount);

		cgstamount = new JTextField();
		cgstamount.setHorizontalAlignment(SwingConstants.RIGHT);
		cgstamount.setEditable(false);
		cgstamount.setColumns(10);
		cgstamount.setBounds(522, 255, 86, 22);
		contentPane.add(cgstamount);

		JLabel lblSutgstAmount = new JLabel("S/UTGST Amount");
		lblSutgstAmount.setBounds(640, 256, 86, 14);
		contentPane.add(lblSutgstAmount);

		sgstamount = new JTextField();
		sgstamount.setHorizontalAlignment(SwingConstants.RIGHT);
		sgstamount.setEditable(false);
		sgstamount.setColumns(10);
		sgstamount.setBounds(736, 253, 86, 22);
		contentPane.add(sgstamount);

		JLabel lblTotalAmount = new JLabel("Total Amount        :");
		lblTotalAmount.setBounds(965, 587, 102, 14);
		contentPane.add(lblTotalAmount);

		totalamountlabel = new JLabel("0.0");
		totalamountlabel.setBounds(1077, 587, 81, 14);
		contentPane.add(totalamountlabel);

		JLabel lblGrandTotal = new JLabel("Grand Total           :");
		lblGrandTotal.setForeground(new Color(0, 51, 204));
		lblGrandTotal.setBounds(965, 606, 102, 14);
		contentPane.add(lblGrandTotal);

		grandtotallabel = new JLabel("0.0");
		grandtotallabel.setForeground(new Color(0, 51, 255));
		grandtotallabel.setBounds(1077, 606, 81, 14);
		contentPane.add(grandtotallabel);

		custadd1 = new JLabel("");
		custadd1.setFont(new Font("SansSerif", Font.PLAIN, 10));
		custadd1.setBackground(new Color(240, 240, 240));
		custadd1.setBounds(635, 38, 331, 15);
		contentPane.add(custadd1);

		custmob = new JLabel("");
		custmob.setFont(new Font("SansSerif", Font.PLAIN, 10));
		custmob.setBounds(635, 75, 331, 14);
		contentPane.add(custmob);

		custadd2 = new JLabel("");
		custadd2.setFont(new Font("SansSerif", Font.PLAIN, 10));
		custadd2.setBounds(636, 57, 330, 14);
		contentPane.add(custadd2);

		discounted = new JTextField();
		discounted.setEditable(false);
		discounted.setHorizontalAlignment(SwingConstants.RIGHT);
		discounted.setColumns(10);
		discounted.setBounds(522, 287, 86, 20);
		contentPane.add(discounted);

		total = new JTextField();
		total.setHorizontalAlignment(SwingConstants.RIGHT);
		total.setEditable(false);
		total.setColumns(10);
		total.setBounds(330, 285, 86, 22);
		contentPane.add(total);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(240, 286, 46, 21);
		contentPane.add(lblTotal);

		p1 = new JLabel("");
		p1.setForeground(new Color(240, 230, 140));
		p1.setBounds(918, 175, 18, 14);
		contentPane.add(p1);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStock.setBounds(902, 230, 46, 21);
		contentPane.add(lblStock);

		stocktext = new JTextField();
		stocktext.setHorizontalAlignment(SwingConstants.RIGHT);
		stocktext.setEditable(false);
		stocktext.setColumns(10);
		stocktext.setBounds(960, 232, 86, 22);
		contentPane.add(stocktext);

		btnAdd = new JButton("");
		btnAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				setItemDetailInTable();
				settaxbelow();
				showDiscountedAmt();
				countitem();
				addingTotalAmountWithTax();
				addingTotalAmount();
				StockUpdate();
			}
		});
		btnAdd.setOpaque(false);
		btnAdd.setBorder(null);
		btnAdd.setBounds(1069, 295, 89, 23);
		contentPane.add(btnAdd);

		lblMfg = new JLabel("Mfg");
		lblMfg.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMfg.setBounds(902, 261, 46, 17);
		contentPane.add(lblMfg);

		mfgtext = new JTextField();
		mfgtext.setHorizontalAlignment(SwingConstants.LEFT);
		mfgtext.setEditable(false);
		mfgtext.setColumns(10);
		mfgtext.setBounds(960, 258, 198, 22);
		contentPane.add(mfgtext);

		lblDiscountAmt = new JLabel("After Disc.");
		lblDiscountAmt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDiscountAmt.setBounds(443, 289, 80, 18);
		contentPane.add(lblDiscountAmt);

		label_1 = new JLabel("");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(1062, 230, 96, 22);
		contentPane.add(label_1);

		JLabel lblTime = new JLabel("Time   :");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTime.setBounds(984, 126, 65, 21);
		contentPane.add(lblTime);

		timebox = new JTextField();
		timebox.setForeground(Color.WHITE);
		timebox.setEditable(false);
		timebox.setColumns(10);
		timebox.setBackground(Color.BLACK);
		timebox.setBounds(1051, 126, 86, 24);
		contentPane.add(timebox);

		namelbl = new JLabel("");
		namelbl.setForeground(Color.BLACK);
		namelbl.setBounds(384, 60, 139, 14);
		contentPane.add(namelbl);

		paidtxt = new JTextField();

		paidtxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				paidtxt.selectAll();
			}
		});
		paidtxt.setHorizontalAlignment(SwingConstants.RIGHT);
		paidtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					setDue();
					btnPrint.requestFocus();
				}
			}
		});
		paidtxt.setBounds(869, 604, 86, 20);
		contentPane.add(paidtxt);
		paidtxt.setColumns(10);

		duetxt = new JTextField();
		duetxt.setEditable(false);
		duetxt.setHorizontalAlignment(SwingConstants.RIGHT);
		duetxt.setColumns(10);
		duetxt.setBounds(869, 651, 86, 20);
		contentPane.add(duetxt);

		JLabel lblPaid = new JLabel("Paid");
		lblPaid.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPaid.setBounds(899, 587, 37, 14);
		contentPane.add(lblPaid);

		JLabel lblDue = new JLabel("Due");
		lblDue.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDue.setBounds(899, 635, 37, 14);
		contentPane.add(lblDue);

		custGstin = new JLabel("");
		custGstin.setFont(new Font("SansSerif", Font.PLAIN, 10));
		custGstin.setBounds(635, 147, 331, 14);
		contentPane.add(custGstin);

		custState = new JLabel("");
		custState.setFont(new Font("SansSerif", Font.PLAIN, 10));
		custState.setBounds(635, 93, 331, 14);
		contentPane.add(custState);

		custPin = new JLabel("");
		custPin.setFont(new Font("SansSerif", Font.PLAIN, 10));
		custPin.setBounds(635, 112, 331, 14);
		contentPane.add(custPin);

		custCountry = new JLabel("");
		custCountry.setFont(new Font("SansSerif", Font.PLAIN, 10));
		custCountry.setBounds(635, 129, 331, 14);
		contentPane.add(custCountry);

		JLabel lblBatch = new JLabel("Batch");
		lblBatch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBatch.setBounds(736, 176, 46, 14);
		contentPane.add(lblBatch);

		batchtxt = new JTextField();
		batchtxt.setEditable(false);
		batchtxt.setBounds(736, 198, 73, 20);
		contentPane.add(batchtxt);
		batchtxt.setColumns(10);

		compname = new JLabel("");
		compname.setForeground(new Color(240, 230, 140));
		compname.setBounds(218, 0, 19, 14);
		contentPane.add(compname);

		compadd1 = new JLabel("");
		compadd1.setForeground(new Color(240, 230, 140));
		compadd1.setBounds(240, 0, 19, 14);
		contentPane.add(compadd1);

		compadd2 = new JLabel("");
		compadd2.setForeground(new Color(240, 230, 140));
		compadd2.setBounds(254, 0, 19, 14);
		contentPane.add(compadd2);

		compstate = new JLabel("");
		compstate.setForeground(new Color(240, 230, 140));
		compstate.setBounds(280, 0, 19, 14);
		contentPane.add(compstate);

		comppin = new JLabel("");
		comppin.setForeground(new Color(240, 230, 140));
		comppin.setBounds(307, 0, 19, 14);
		contentPane.add(comppin);

		compmob = new JLabel("");
		compmob.setForeground(new Color(240, 230, 140));
		compmob.setBounds(330, 0, 19, 14);
		contentPane.add(compmob);

		compgstin = new JLabel("");
		compgstin.setForeground(new Color(240, 230, 140));
		compgstin.setBounds(353, 0, 19, 14);
		contentPane.add(compgstin);

		compplace = new JLabel("");
		compplace.setForeground(new Color(240, 230, 140));
		compplace.setBounds(375, 0, 19, 14);
		contentPane.add(compplace);

		compemail = new JLabel("");
		compemail.setForeground(new Color(240, 230, 140));
		compemail.setBounds(397, 0, 19, 14);
		contentPane.add(compemail);

		compwebsite = new JLabel("");
		compwebsite.setForeground(new Color(240, 230, 140));
		compwebsite.setBounds(420, 0, 19, 14);
		contentPane.add(compwebsite);

		custtype = new JLabel("");
		custtype.setFont(new Font("SansSerif", Font.PLAIN, 10));
		custtype.setBackground(SystemColor.menu);
		custtype.setBounds(635, 15, 331, 15);
		contentPane.add(custtype);

		sgst25 = new JLabel("SGST 2.5 %  :");
		sgst25.setBounds(349, 634, 73, 14);
		contentPane.add(sgst25);

		cgst25 = new JLabel("CGST 2.5 %  :");
		cgst25.setBounds(349, 663, 80, 14);
		contentPane.add(cgst25);

		sgst6 = new JLabel("SGST 6%  :");
		sgst6.setBounds(481, 634, 65, 14);
		contentPane.add(sgst6);

		cgst6 = new JLabel("CGST 6%  :");
		cgst6.setBounds(481, 663, 61, 14);
		contentPane.add(cgst6);

		sgst9 = new JLabel("SGST 9%  :");
		sgst9.setBounds(601, 634, 65, 14);
		contentPane.add(sgst9);

		cgst9 = new JLabel("CGST 9%  :");
		cgst9.setBounds(601, 663, 65, 14);
		contentPane.add(cgst9);

		sgst14 = new JLabel("SGST 14%  :");
		sgst14.setBounds(715, 634, 65, 14);
		contentPane.add(sgst14);

		cgst14 = new JLabel("CGST 14%  :");
		cgst14.setBounds(715, 663, 80, 14);
		contentPane.add(cgst14);

		sgst2lbl = new JLabel("0.0");
		sgst2lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sgst2lbl.setBounds(425, 634, 46, 14);
		contentPane.add(sgst2lbl);

		cgst2lbl = new JLabel(" 0.0");
		cgst2lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cgst2lbl.setBounds(421, 663, 50, 14);
		contentPane.add(cgst2lbl);

		sgst6lbl = new JLabel("0.0");
		sgst6lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sgst6lbl.setBounds(541, 634, 50, 14);
		contentPane.add(sgst6lbl);

		cgst6lbl = new JLabel("0.0");
		cgst6lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cgst6lbl.setBounds(541, 663, 50, 14);
		contentPane.add(cgst6lbl);

		cgst9lbl = new JLabel("0.0");
		cgst9lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cgst9lbl.setBounds(663, 663, 46, 14);
		contentPane.add(cgst9lbl);

		sgst9lbl = new JLabel("0.0");
		sgst9lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sgst9lbl.setBounds(663, 634, 46, 14);
		contentPane.add(sgst9lbl);

		sgst14lbl = new JLabel("0.0");
		sgst14lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		sgst14lbl.setBounds(784, 634, 46, 14);
		contentPane.add(sgst14lbl);

		cgst14lbl = new JLabel("0.0");
		cgst14lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cgst14lbl.setBounds(784, 663, 46, 14);
		contentPane.add(cgst14lbl);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(333, 619, 508, 2);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(330, 619, 2, 69);
		contentPane.add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setForeground(Color.BLACK);
		separator_3.setBounds(839, 619, 2, 69);
		contentPane.add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(Color.BLACK);
		separator_4.setBounds(330, 688, 508, 2);
		contentPane.add(separator_4);

		JLabel lblNoOfItems = new JLabel("No. Of Items :");
		lblNoOfItems.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblNoOfItems.setBounds(240, 576, 76, 14);
		contentPane.add(lblNoOfItems);

		itemcount = new JLabel("");
		itemcount.setFont(new Font("Tahoma", Font.ITALIC, 11));
		itemcount.setBounds(315, 576, 30, 14);
		contentPane.add(itemcount);

		lblMaxItem = new JLabel("Max . Item 24");
		lblMaxItem.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblMaxItem.setBounds(240, 590, 76, 14);
		contentPane.add(lblMaxItem);

		JLabel lblDiscounted = new JLabel("Discounted            :");
		lblDiscounted.setBounds(965, 631, 108, 14);
		contentPane.add(lblDiscounted);

		discountedlbl = new JLabel("0.0");
		discountedlbl.setForeground(new Color(0, 51, 255));
		discountedlbl.setBounds(1077, 631, 81, 14);
		contentPane.add(discountedlbl);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 204, 760);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(1185, 0, 198, 760);
		contentPane.add(panel_1);

		populateCompDetails();
		populatename();
		populateItemCombo();
		FillingRateField();
		IncreasingBillNo();
		setDate();
		t = new Thread(this);
		t.start();
		countitem();
		closeFrame();

	}

	public void run() {
		setTime();
	}
}
