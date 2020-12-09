package gst;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

public class Stockm extends JFrame {

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
	private JTextField searchTF;
	private JLabel lblNewLabel;

	public void stock() {
		try {
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGST/GST", "sa", "");
			String stk = "SELECT ITEM_NAME , STOCK FROM ADDITEMS";
			st = con.prepareStatement(stk);
			rs = st.executeQuery();

			// while(rs.next()){
			table.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
			// }
		} catch (SQLException e) {
			
			e.printStackTrace();

		}
	}

	public void closeFrame() {

		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		Action escapeAction = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "manjit");
		getRootPane().getActionMap().put("manjit", escapeAction);

	}
	
	public void searchItem() {
		String sql = "SELECT item_name , stock FROM ADDITEMS WHERE item_name LIKE '%"+searchTF.getText()+"%' ";
		
		try {
			Connection con = DriverManager.getConnection("jdbc:h2:C:/SimpleGST/GST", "sa", "");
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			table.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
		//	System.out.println(rs);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	public Stockm() {
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				dispose();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1383, 760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(209, 83, 973, 653);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma" , Font.PLAIN , 15));
		table.setRowMargin(9);
		table.setRowHeight(30);
		scrollPane.setViewportView(table);

		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 190, 760);
		contentPane.add(panel);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(1204, 0, 179, 760);
		contentPane.add(panel_1);
		
		searchTF = new JTextField();
		searchTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				searchItem();
				if(searchTF.getText() == "") {
					stock();
				}
			}
		});
		searchTF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		searchTF.setColumns(10);
		searchTF.setBounds(269, 54, 273, 25);
		contentPane.add(searchTF);
		
		lblNewLabel = new JLabel("Search");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(209, 54, 75, 25);
		contentPane.add(lblNewLabel);
		stock();
		closeFrame();

	}
}
