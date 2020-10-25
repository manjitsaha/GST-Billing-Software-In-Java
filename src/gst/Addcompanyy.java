package gst;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


public class Addcompanyy extends JFrame {

	private JPanel contentPane;
	private JTextField compnametxt;
	private JTextField tradenametxt;
	private JTextField compadd1txt;
	private JTextField compadd2txt;
	private JTextField compplacetxt;
	private JTextField comppintxt;
	private JTextField compemailtxt;
	private JTextField compwebtxt;
	private JTextField compmobtxt;
	private JTextField compestddatetxt;
	private JTextField compgstdatetxt;
	private JTextField comppannotxt;
	private JTextField comppandatetxt;
	private JTextField comptannotxt;
	private JTextField comptandatetxt;
	private JComboBox statecomboBox_2;
	private JLabel compnamelbl;
	private JComboBox gstcatcomboBox_1;
	private JComboBox comboBox;
	private JFormattedTextField compgstnotxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Addcompanyy frame = new Addcompanyy();
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
	
	 Connection con =null;
	 Statement ps ;
	 ResultSet rs ;
	 PreparedStatement st ;
	
	 public void populateState() {
		 String sta ="select * from states";
			try {
				con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
				 ps = con.createStatement();
				 rs = ps.executeQuery(sta);
				while(rs.next()){
					statecomboBox_2.addItem(rs.getString("states"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	 }
	 
	 
	 public void populateCompname() {
		 try {
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
			String popcompname = "SELECT COMPANY_NAME FROM COMPANY";
			st = con.prepareStatement(popcompname);
			rs = st.executeQuery();
			
			while(rs.next()) {
			compnamelbl.setText(rs.getString("company_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	 }
	 
	public void saveCompname() {
		try {
			String gstcat = gstcatcomboBox_1.getSelectedItem().toString();
			String state = statecomboBox_2.getSelectedItem().toString();
			String t = comboBox.getSelectedItem().toString();
			String cname = compnametxt.getText();
			String tc = t+" "+cname;
			String trade = tradenametxt.getText();
			String add1 = compadd1txt.getText();
			String add2 = compadd2txt.getText();
			String Place = compplacetxt.getText();
			String pin = comppintxt.getText();
			String email = compemailtxt.getText();
			String website = compwebtxt.getText();
			String mob = compmobtxt.getText();
			String estd = compestddatetxt.getText();
			String gst_no = compgstnotxt.getText();
			String gst_date = compgstdatetxt.getText();
			String pan =comppannotxt.getText();
			String pan_date = comppandatetxt.getText();
			String tan =comptannotxt.getText();
			String tan_date = comptandatetxt.getText();
			
		
			 con=DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
			 
			 st = con.prepareStatement("insert into company values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				st.setString(1,tc );
				st.setString(2,trade );
				st.setString(3,add1 );
				st.setString(4,add2 );
				st.setString(5,Place );
				st.setString(6,state );
				st.setString(7,pin );
				st.setString(8,email );
				st.setString(9,website );
				st.setString(10,mob );
				st.setString(11,estd );
				st.setString(12,gst_no );
				st.setString(13,gst_date );
				st.setString(14,gstcat );
				st.setString(15,pan );
				st.setString(16,pan_date );
				st.setString(17,tan );
				st.setString(18,tan_date );
				
				
				st.execute();

				/*
				 * st = con.prepareStatement("UPDATE COMPANY SET company_name = '"+cname+"'," +
				 * "trade_name = '"+tc+"'," + "add_1 = '"+add1+"'," + "add_2 = '"+add2+"'," +
				 * "state = '"+state+"'," + "place = '"+Place+"',"
				 * 
				 * + "pin = '"+pin+"'," + "email_id = '"+email+"'," + "website = '"+website+"',"
				 * + "mobile_no = '"+mob+"'," + " establishment_date = '"+estd+"'," +
				 * "gst_number = '"+gst_no+"'," + "gst_date = '"+gst_date+"'," +
				 * "gst_category = '"+gstcat+"'," + "pan = '"+pan+"'," +
				 * "pan_date = '"+pan_date+"'," + "tan  = '"+tan+"'," +
				 * "tan_date = '"+tan_date+"'");
				 */
			
				/*
				 * st.setString(1,tc ); st.setString(2,trade ); st.setString(3,add1 );
				 * st.setString(4,add2 ); st.setString(5,state ); st.setString(6,Place );
				 * st.setString(7,pin ); st.setString(8,email ); st.setString(9,website );
				 * st.setString(10,mob ); st.setString(11,estd ); st.setString(12,gst_no );
				 * st.setString(13,gst_date ); st.setString(14,gstcat ); st.setString(15,pan );
				 * st.setString(16,pan_date ); st.setString(17,tan ); st.setString(18,tan_date
				 * );
				 */
			
			
			st.execute();
			
			System.out.println("updated");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	

	
	public Addcompanyy() {
		
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1383,760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String ms[]={"M/s.","Shree.","Dr.","Er."};
		 comboBox = new JComboBox(ms);
		comboBox.setBounds(377, 107, 71, 37);
		contentPane.add(comboBox);
		
		compnametxt = new JTextField();
		compnametxt.setColumns(10);
		compnametxt.setBounds(452, 107, 702, 37);
		contentPane.add(compnametxt);
		
		JLabel label = new JLabel("Company Name");
		label.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label.setBounds(241, 107, 133, 37);
		contentPane.add(label);
		
		tradenametxt = new JTextField();
		tradenametxt.setColumns(10);
		tradenametxt.setBounds(377, 155, 777, 37);
		contentPane.add(tradenametxt);
		
		JLabel label_1 = new JLabel("Trade Name");
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_1.setBounds(241, 155, 133, 37);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Contact Details");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 19));
		label_2.setBounds(286, 221, 142, 26);
		contentPane.add(label_2);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new LineBorder(Color.DARK_GRAY));
		layeredPane.setBounds(241, 438, 401, -324);
		contentPane.add(layeredPane);
		
		compadd1txt = new JTextField();
		compadd1txt.setColumns(10);
		compadd1txt.setBounds(391, 288, 273, 26);
		contentPane.add(compadd1txt);
		
		JLabel label_3 = new JLabel("Address Line 1");
		label_3.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_3.setBounds(255, 288, 125, 22);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Address Line 2");
		label_4.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_4.setBounds(255, 321, 125, 22);
		contentPane.add(label_4);
		
		compadd2txt = new JTextField();
		compadd2txt.setColumns(10);
		compadd2txt.setBounds(391, 318, 273, 26);
		contentPane.add(compadd2txt);
		
		JLabel label_5 = new JLabel("State");
		label_5.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_5.setBounds(255, 355, 125, 20);
		contentPane.add(label_5);
		
		compplacetxt = new JTextField();
		compplacetxt.setColumns(10);
		compplacetxt.setBounds(391, 383, 124, 26);
		contentPane.add(compplacetxt);
		
		JLabel label_6 = new JLabel("Place");
		label_6.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_6.setBounds(255, 386, 125, 26);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("Pin");
		label_7.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_7.setBounds(543, 386, 28, 14);
		contentPane.add(label_7);
		
		comppintxt = new JTextField();
		comppintxt.setColumns(10);
		comppintxt.setBounds(578, 383, 86, 26);
		contentPane.add(comppintxt);
		
		JLabel label_8 = new JLabel("Email ID");
		label_8.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_8.setBounds(255, 411, 125, 37);
		contentPane.add(label_8);
		
		JLabel label_9 = new JLabel("Website");
		label_9.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_9.setBounds(255, 449, 125, 35);
		contentPane.add(label_9);
		
		JLabel label_10 = new JLabel("Mobile No.");
		label_10.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_10.setBounds(255, 495, 125, 23);
		contentPane.add(label_10);
		
		compemailtxt = new JTextField();
		compemailtxt.setColumns(10);
		compemailtxt.setBounds(391, 416, 273, 31);
		contentPane.add(compemailtxt);
		
		compwebtxt = new JTextField();
		compwebtxt.setColumns(10);
		compwebtxt.setBounds(391, 455, 273, 26);
		contentPane.add(compwebtxt);
		
		compmobtxt = new JTextField();
		compmobtxt.setColumns(10);
		compmobtxt.setBounds(391, 492, 273, 26);
		contentPane.add(compmobtxt);
		
		JLabel label_11 = new JLabel("Other Details");
		label_11.setForeground(Color.BLUE);
		label_11.setFont(new Font("SansSerif", Font.PLAIN, 18));
		label_11.setBounds(726, 226, 114, 19);
		contentPane.add(label_11);
		
		JLabel label_12 = new JLabel("Establishment Date");
		label_12.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_12.setBounds(698, 299, 142, 21);
		contentPane.add(label_12);
		
		compestddatetxt = new JTextField();
		compestddatetxt.setColumns(10);
		compestddatetxt.setBounds(836, 294, 125, 26);
		contentPane.add(compestddatetxt);
		
		JLabel label_13 = new JLabel("GST Number");
		label_13.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_13.setBounds(698, 324, 103, 19);
		contentPane.add(label_13);
		
		compgstdatetxt = new JTextField();
		compgstdatetxt.setColumns(10);
		compgstdatetxt.setBounds(836, 353, 114, 26);
		contentPane.add(compgstdatetxt);
		
		JLabel label_14 = new JLabel("GST Date");
		label_14.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_14.setBounds(698, 352, 76, 22);
		contentPane.add(label_14);
		
		String gstcate[]={"Regular","Composition","Non-Resident","Input Service Distributor"};
		 gstcatcomboBox_1 = new JComboBox(gstcate);
		gstcatcomboBox_1.setBounds(836, 382, 159, 26);
		contentPane.add(gstcatcomboBox_1);
		
		JLabel label_15 = new JLabel("GST Category");
		label_15.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_15.setBounds(698, 383, 103, 25);
		contentPane.add(label_15);
		
		comppannotxt = new JTextField();
		comppannotxt.setColumns(10);
		comppannotxt.setBounds(836, 413, 93, 26);
		contentPane.add(comppannotxt);
		
		JLabel label_16 = new JLabel("PAN");
		label_16.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_16.setBounds(698, 414, 46, 22);
		contentPane.add(label_16);
		
		comppandatetxt = new JTextField();
		comppandatetxt.setColumns(10);
		comppandatetxt.setBounds(1042, 410, 93, 27);
		contentPane.add(comppandatetxt);
		
		JLabel label_17 = new JLabel("PAN Date");
		label_17.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_17.setBounds(957, 413, 86, 21);
		contentPane.add(label_17);
		
		comptannotxt = new JTextField();
		comptannotxt.setColumns(10);
		comptannotxt.setBounds(836, 446, 93, 27);
		contentPane.add(comptannotxt);
		
		comptandatetxt = new JTextField();
		comptandatetxt.setColumns(10);
		comptandatetxt.setBounds(1042, 446, 93, 27);
		contentPane.add(comptandatetxt);
		
		JLabel label_18 = new JLabel("TAN");
		label_18.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_18.setBounds(698, 444, 46, 29);
		contentPane.add(label_18);
		
		JLabel label_19 = new JLabel("TAN Date");
		label_19.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label_19.setBounds(957, 443, 86, 30);
		contentPane.add(label_19);
		
		 statecomboBox_2 = new JComboBox();
		statecomboBox_2.setBounds(391, 354, 273, 26);
		contentPane.add(statecomboBox_2);
		
		JButton button = new JButton("Close");
		button.setBackground(Color.RED);
		button.setBounds(1000, 582, 89, 23);
		contentPane.add(button);
		
		try {
			MaskFormatter mask = new MaskFormatter("##?????####?#?A");
			 compgstnotxt = new JFormattedTextField(mask);
			compgstnotxt.setBounds(836, 324, 125, 26);
			contentPane.add(compgstnotxt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JButton button_1 = new JButton("Clear");
		button_1.setBackground(Color.ORANGE);
		button_1.setBounds(899, 582, 89, 23);
		contentPane.add(button_1);
		
		 compnamelbl = new JLabel("");
		compnamelbl.setBounds(452, 90, 46, 14);
		contentPane.add(compnamelbl);
		
		JButton button_2 = new JButton("Save");
		button_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					saveCompname();
				}
			}
		});
		button_2.setBackground(Color.GREEN);
		button_2.setBounds(791, 582, 89, 23);
		contentPane.add(button_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 204, 760);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(1185, 0, 198, 760);
		contentPane.add(panel_1);
		
		
		
		populateState();
		populateCompname();
		closeFrame();
		
	}
}
