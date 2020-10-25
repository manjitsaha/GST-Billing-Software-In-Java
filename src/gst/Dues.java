package gst;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JInternalFrame;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dues extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JInternalFrame internalFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dues frame = new Dues();
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
	Statement st;
	ResultSet rs;
	private JTable table_1;
	private JPanel panel;
	private JPanel panel_1;
	
	public void showDuesBill() {
		
		try {
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
			String sql = "SELECT * FROM DUES ORDER BY CUSTOMER_NAME ASC ";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next())
			 table.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void showSelectedBillInfo() {
		DefaultTableModel mt = (DefaultTableModel)table.getModel();
		String name = mt.getValueAt(table.getSelectedRow(), 0).toString();
		
		try {
			con =  DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GST/INVOICESsnacks","sa","");
			String sql = "SELECT * FROM "+name+" ORDER BY Item_name ASC";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			table_1.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
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
		    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		        escapeKeyStroke, "manjit");
		    getRootPane().getActionMap().put("manjit", escapeAction);
		    
		  
		}
	
	public Dues() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1383,760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(227, 67, 950, 363);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				showSelectedBillInfo();
				internalFrame.setVisible(true);
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblDuesbills = new JLabel("Dues Bills");
		lblDuesbills.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDuesbills.setBounds(274, 11, 192, 66);
		contentPane.add(lblDuesbills);
		
		 internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBorder(null);
		internalFrame.getContentPane().setBackground(new Color(238, 232, 170));
		internalFrame.setBounds(227, 430, 950, 301);
		contentPane.add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		BasicInternalFrameUI iff = (BasicInternalFrameUI)internalFrame.getUI();
		iff.setNorthPane(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 23, 950, 253);
		internalFrame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(1185, 0, 198, 760);
		contentPane.add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(0, 0, 217, 760);
		contentPane.add(panel_1);
		
		
		
		
		
		showDuesBill();
		closeFrame();
	}
}
