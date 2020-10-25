package gst;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

public class Stockm extends JFrame {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stockm frame = new Stockm();
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
	private JPanel panel;
	private JPanel panel_1;
	
	public void stock() {
		try {
		    con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks","sa","");
			String stk = "SELECT ITEM_NAME , STOCK FROM ADDITEMS ORDER BY ITEM_NAME ASC";
			 st = con.prepareStatement(stk);
			 rs = st.executeQuery();
			
		//	while(rs.next()){
				table.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
		//	}
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
	
	
	public Stockm() {
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				dispose();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1383,760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(209, 24, 973, 712);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 190, 760);
		contentPane.add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(1204, 0, 179, 760);
		contentPane.add(panel_1);
		stock();
		closeFrame();
		

	}
}
