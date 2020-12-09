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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

public class Dues extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JInternalFrame internalFrame;
	public static String passBill;
	public static boolean isPassedFromDues = false;
	public static boolean isDuesVisible = true;

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
	private JButton btnPassBill;
	private JTextField searchTF;

	public void showDuesBill() {

		try {
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGST/GST", "sa", "");
			String sql = "SELECT * FROM DUES";
			st = con.createStatement();
			rs = st.executeQuery(sql);
		//	while (rs.next())
				table.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void searchItem(JTable table, String query) {
		String sql = "SELECT * FROM DUES WHERE customer_name LIKE '%"+query+"%' ";
		
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

	public void showSelectedBillInfo() {
		DefaultTableModel mt = (DefaultTableModel) table.getModel();
		String name = mt.getValueAt(table.getSelectedRow(), 0).toString();

		try {
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGST/GST/INVOICESsnacks", "sa", "");
			String sql = "SELECT * FROM " + name + " ORDER BY Item_name ASC";
			st = con.createStatement();
			rs = st.executeQuery(sql);

			table_1.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	public void closeFrame() {

		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		Action escapeAction = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				isDuesVisible = false;
				dispose();
			}
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "manjit");
		getRootPane().getActionMap().put("manjit", escapeAction);

	}

	public Dues() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1383, 760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(227, 67, 950, 363);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma" , Font.PLAIN , 15));
		table.setRowMargin(9);
		table.setRowHeight(30);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("entered");

				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

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
		BasicInternalFrameUI iff = (BasicInternalFrameUI) internalFrame.getUI();
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

		btnPassBill = new JButton("passBill");
		btnPassBill.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				table.requestFocus();
			}
		});
		btnPassBill.setBorder(null);
		btnPassBill.setBounds(1088, 38, 85, 21);
		btnPassBill.setBackground(new Color(240, 230, 140));
		btnPassBill.setForeground(new Color(240, 230, 140));
		btnPassBill.setMnemonic('P');
		btnPassBill.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel mt = (DefaultTableModel) table.getModel();
				passBill = (String) mt.getValueAt(table.getSelectedRow(), 0);
				isPassedFromDues = true;
				PastTransaction.main(null);
				dispose();

			}
		});
		contentPane.add(btnPassBill);
		
		searchTF = new JTextField();
		searchTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				searchItem(table , searchTF.getText());
				if(searchTF.getText().equals("")) {
					showDuesBill();
				}
			}
		});
		searchTF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		searchTF.setColumns(10);
		searchTF.setBounds(483, 32, 273, 25);
		contentPane.add(searchTF);
		
		JLabel lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(423, 32, 75, 25);
		contentPane.add(lblNewLabel_1);

		showDuesBill();
		closeFrame();
	}
}
