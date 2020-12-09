package gst;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.json.JSONObject;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;

public class Mains extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtAuthorisedUser;
	private JInternalFrame fileinternal;
	private JInternalFrame mastersinternal;
	private JInternalFrame saleinternal;
	private JInternalFrame transactioninternal;
	private JInternalFrame settingsinternal;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mains frame = new Mains();
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
	private JTable table;

	public boolean checkIfDatabaseExist() {
		/*
		 * File f = new File("C:\\SimpleGST"); if(f.exists()) { return true; }
		 */

		String f = new File("").getAbsoluteFile() + "/src/dbs.json";

		try {
			String contents = new String(Files.readAllBytes(Paths.get(f)));
			JSONObject jsonobj = new JSONObject(contents);

			String obj = jsonobj.getString("isDatabaseCreated");
			if (obj.equals("false")) {
				return false;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public void stock() {

		try {
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGST/GST", "sa", "");
			String stk = "SELECT ITEM_NAME , STOCK FROM ADDITEMS WHERE STOCK <= " + Constants.STOCK_END_ALERT;
			// System.out.println(stk);
			PreparedStatement st = con.prepareStatement(stk);
			rs = st.executeQuery();

			// while(rs.next()){
			table.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
			// }
		} catch (SQLException e) {

			e.printStackTrace();

		}
	}

	public void populateCompNameInAuthuser() {
		if (checkIfDatabaseExist()) {
			try {
				con = DriverManager.getConnection("jdbc:h2:C:/SimpleGST/GST", "sa", "");
				st = con.createStatement();
				rs = st.executeQuery("SELECT COMPANY_NAME FROM COMPANY");
				while (rs.next()) {
					textArea.setText(rs.getString("COMPANY_NAME"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void turnoncapslock() {

		Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, true);
	}

	public void closeInternalFrames() {
		if (fileinternal.isVisible()) {
			fileinternal.dispose();
		}
		if (mastersinternal.isVisible()) {
			mastersinternal.dispose();
		}

		if (saleinternal.isVisible()) {
			saleinternal.dispose();
		}

		if (transactioninternal.isVisible()) {
			transactioninternal.dispose();
		}

		if (settingsinternal.isVisible()) {
			settingsinternal.dispose();
		}
	}

	public Mains() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				closeInternalFrames();
			}
		});
		Toolkit t = getToolkit();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String username = System.getProperty("user.name");
		setTitle("Simple GST - C:/SimpleGST/GST " + "Username -" + username);
		setSize(t.getScreenSize().width, t.getScreenSize().height);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				closeInternalFrames();

			}
		});
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Mains.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Mains.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Mains.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Mains.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		fileinternal = new JInternalFrame();
		fileinternal.getContentPane().setLayout(null);
		fileinternal.getContentPane().setBackground(new Color(51, 51, 153));
		BasicInternalFrameUI ui = (BasicInternalFrameUI) fileinternal.getUI();
		ui.setNorthPane(null);

		JButton button_8 = new JButton("Exit");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_8.setOpaque(false);
		button_8.setMnemonic(KeyEvent.VK_Q);
		button_8.setMargin(new Insets(2, 14, 2, 65));
		button_8.setForeground(Color.WHITE);
		button_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_8.setContentAreaFilled(false);
		button_8.setBackground(Color.GRAY);
		button_8.setBounds(0, 56, 127, 45);
		fileinternal.getContentPane().add(button_8);

		JButton button_9 = new JButton("New");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreatingTableAndPreData a = new CreatingTableAndPreData();

				if (checkIfDatabaseExist()) {
					JOptionPane.showMessageDialog(getParent(), "Database already exist", "Error",
							JOptionPane.WARNING_MESSAGE);

				} else {
					a.createDetails();
					JOptionPane.showMessageDialog(getParent(), "Database created successfully", "Success",
							JOptionPane.INFORMATION_MESSAGE);

					try {
						JSONObject obj = new JSONObject();
						obj.put("isDatabaseCreated", "true");
						FileWriter fw = new FileWriter(new File("").getAbsoluteFile() + "/src/dbs.json");
						fw.write(obj.toString());

						fw.flush();
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		SpringLayout sl_contentPane = new SpringLayout();
		sl_contentPane.putConstraint(SpringLayout.NORTH, fileinternal, 153, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, fileinternal, 196, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, fileinternal, 258, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, fileinternal, 323, SpringLayout.WEST, contentPane);
		contentPane.setLayout(sl_contentPane);

		button_9.setOpaque(false);
		button_9.setMnemonic(KeyEvent.VK_N);
		button_9.setMargin(new Insets(2, 14, 2, 65));
		button_9.setForeground(Color.WHITE);
		button_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_9.setContentAreaFilled(false);
		button_9.setBackground(Color.GRAY);
		button_9.setBounds(0, 0, 127, 55);
		fileinternal.getContentPane().add(button_9);
		fileinternal.setBorder(null);
		contentPane.add(fileinternal);

		mastersinternal = new JInternalFrame();
		sl_contentPane.putConstraint(SpringLayout.NORTH, mastersinternal, 153, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, mastersinternal, 196, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, mastersinternal, 398, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, mastersinternal, 405, SpringLayout.WEST, contentPane);
		mastersinternal.getContentPane().setLayout(null);
		mastersinternal.getContentPane().setBackground(new Color(51, 51, 153));
		BasicInternalFrameUI mi = (BasicInternalFrameUI) mastersinternal.getUI();
		mi.setNorthPane(null);

		JButton button_12 = new JButton("Company Details");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Addcompanyy.main(null);
				closeInternalFrames();
			}
		});
		button_12.setMargin(new Insets(2, 0, 2, 52));
		button_12.setForeground(Color.WHITE);
		button_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_12.setContentAreaFilled(false);
		button_12.setBounds(0, 0, 207, 43);
		mastersinternal.getContentPane().add(button_12);

		JButton button_13 = new JButton("Item Master");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Items.main(null);
				closeInternalFrames();

			}
		});
		button_13.setMargin(new Insets(2, 0, 2, 95));
		button_13.setForeground(Color.WHITE);
		button_13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_13.setContentAreaFilled(false);
		button_13.setBounds(10, 48, 197, 43);
		mastersinternal.getContentPane().add(button_13);

		JButton button_14 = new JButton("Stock Master");
		button_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Stockm.main(null);
				closeInternalFrames();
			}
		});
		button_14.setMargin(new Insets(2, 0, 2, 80));
		button_14.setForeground(Color.WHITE);
		button_14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_14.setContentAreaFilled(false);
		button_14.setBounds(0, 156, 207, 43);
		mastersinternal.getContentPane().add(button_14);

		JButton button_15 = new JButton("Add Customer");
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				AddCustomer.main(null);
				closeInternalFrames();
			}
		});
		button_15.setMargin(new Insets(2, 0, 2, 80));
		button_15.setForeground(Color.WHITE);
		button_15.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_15.setContentAreaFilled(false);
		button_15.setBounds(10, 102, 197, 43);
		mastersinternal.getContentPane().add(button_15);
		mastersinternal.setBorder(null);
		contentPane.add(mastersinternal);

		saleinternal = new JInternalFrame("New JInternalFrame");
		sl_contentPane.putConstraint(SpringLayout.NORTH, saleinternal, 256, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, saleinternal, 195, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, saleinternal, 410, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, saleinternal, 404, SpringLayout.WEST, contentPane);
		saleinternal.getContentPane().setLayout(null);
		saleinternal.getContentPane().setBackground(new Color(51, 51, 153));
		BasicInternalFrameUI vi = (BasicInternalFrameUI) saleinternal.getUI();
		vi.setNorthPane(null);

		JButton button_16 = new JButton("New Sale");
		button_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Invoice.main(null);
				closeInternalFrames();
			}
		});
		button_16.setOpaque(false);
		button_16.setMargin(new Insets(2, 0, 2, 80));
		button_16.setForeground(Color.WHITE);
		button_16.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_16.setBorderPainted(false);
		button_16.setBounds(0, 0, 209, 45);
		saleinternal.getContentPane().add(button_16);
		saleinternal.setBorder(null);
		contentPane.add(saleinternal);

		transactioninternal = new JInternalFrame("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, transactioninternal, 430, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, transactioninternal, 196, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, transactioninternal, 551, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, transactioninternal, 405, SpringLayout.WEST, contentPane);
		transactioninternal.getContentPane().setLayout(null);
		BasicInternalFrameUI ci = (BasicInternalFrameUI) transactioninternal.getUI();
		ci.setNorthPane(null);

		JButton button_17 = new JButton("Past Transaction");
		button_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PastTransaction.main(null);
				closeInternalFrames();
			}
		});
		button_17.setMargin(new Insets(2, 14, 2, 75));
		button_17.setForeground(Color.WHITE);
		button_17.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_17.setBorderPainted(false);
		button_17.setBackground(new Color(51, 51, 153));
		button_17.setBounds(0, 0, 209, 38);
		transactioninternal.getContentPane().add(button_17);

		JButton button_18 = new JButton("Dues Bills");
		button_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dues.main(null);
				closeInternalFrames();
			}
		});
		button_18.setMargin(new Insets(2, 14, 2, 115));
		button_18.setForeground(Color.WHITE);
		button_18.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_18.setBorderPainted(false);
		button_18.setBackground(new Color(51, 51, 153));
		button_18.setBounds(0, 41, 209, 38);
		transactioninternal.getContentPane().add(button_18);
		transactioninternal.setBorder(null);
		transactioninternal.setBackground(new Color(51, 51, 153));
		contentPane.add(transactioninternal);

		settingsinternal = new JInternalFrame("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, settingsinternal, 503, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, settingsinternal, 196, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, settingsinternal, 615, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, settingsinternal, 405, SpringLayout.WEST, contentPane);
		contentPane.add(settingsinternal);
		settingsinternal.getContentPane().setLayout(null);
		BasicInternalFrameUI xi = (BasicInternalFrameUI) settingsinternal.getUI();
		xi.setNorthPane(null);

		JButton button_11 = new JButton("Change Password");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Settings.main(null);
				closeInternalFrames();
			}
		});
		button_11.setMargin(new Insets(2, 14, 2, 60));
		button_11.setForeground(Color.WHITE);
		button_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		button_11.setBorderPainted(false);
		button_11.setBackground(new Color(51, 51, 153));
		button_11.setBounds(0, 0, 209, 38);
		settingsinternal.getContentPane().add(button_11);
		settingsinternal.setBorder(null);
		settingsinternal.setBackground(new Color(51, 51, 153));

		txtAuthorisedUser = new JTextField();
		txtAuthorisedUser.setHorizontalAlignment(SwingConstants.CENTER);
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtAuthorisedUser, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtAuthorisedUser, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtAuthorisedUser, 32, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtAuthorisedUser, 196, SpringLayout.WEST, contentPane);
		txtAuthorisedUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				closeInternalFrames();
			}
		});
		txtAuthorisedUser.setText("   Authorised User :-");
		txtAuthorisedUser.setForeground(Color.RED);
		txtAuthorisedUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAuthorisedUser.setEditable(false);
		txtAuthorisedUser.setColumns(10);
		txtAuthorisedUser.setBorder(null);
		txtAuthorisedUser.setBackground(Color.DARK_GRAY);
		contentPane.add(txtAuthorisedUser);

		textArea = new JTextArea();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textArea, 31, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, textArea, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textArea, 173, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textArea, 196, SpringLayout.WEST, contentPane);
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				closeInternalFrames();
			}
		});

		textArea.setForeground(Color.RED);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textArea.setEditable(false);
		textArea.setBorder(null);
		textArea.setBackground(Color.DARK_GRAY);
		contentPane.add(textArea);

		JLabel lblFile = new JLabel("     File");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblFile, 144, SpringLayout.NORTH, textArea);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblFile, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblFile, 205, SpringLayout.NORTH, textArea);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblFile, 196, SpringLayout.WEST, contentPane);
		lblFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				fileinternal.setVisible(true);

				if (mastersinternal.isVisible()) {
					mastersinternal.dispose();
				}

				if (saleinternal.isVisible()) {
					saleinternal.dispose();
				}

				if (transactioninternal.isVisible()) {
					transactioninternal.dispose();
				}

				if (settingsinternal.isVisible()) {
					settingsinternal.dispose();
				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblFile.setBackground(new Color(51, 51, 153));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				lblFile.setBackground(Color.DARK_GRAY);
			}
		});
		lblFile.setOpaque(true);
		lblFile.setBackground(Color.DARK_GRAY);
		lblFile.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblFile.setForeground(Color.WHITE);
		contentPane.add(lblFile);

		JLabel lblMasters = new JLabel("     Masters");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblMasters, 237, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblMasters, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblMasters, 299, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblMasters, 196, SpringLayout.WEST, contentPane);
		lblMasters.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mastersinternal.setVisible(true);

				if (fileinternal.isVisible()) {
					fileinternal.dispose();
				}

				if (saleinternal.isVisible()) {
					saleinternal.dispose();
				}

				if (transactioninternal.isVisible()) {
					transactioninternal.dispose();
				}

				if (settingsinternal.isVisible()) {
					settingsinternal.dispose();
				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblMasters.setBackground(new Color(51, 51, 153));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				lblMasters.setBackground(Color.DARK_GRAY);
			}
		});

		lblMasters.setOpaque(true);
		lblMasters.setForeground(Color.WHITE);
		lblMasters.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMasters.setBackground(Color.DARK_GRAY);
		contentPane.add(lblMasters);

		JLabel lblSale = new JLabel("     Sale");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSale, 300, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSale, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblSale, 362, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblSale, 196, SpringLayout.WEST, contentPane);
		lblSale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				saleinternal.setVisible(true);

				if (fileinternal.isVisible()) {
					fileinternal.dispose();
				}

				if (mastersinternal.isVisible()) {
					mastersinternal.dispose();
				}

				if (transactioninternal.isVisible()) {
					transactioninternal.dispose();
				}

				if (settingsinternal.isVisible()) {
					settingsinternal.dispose();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblSale.setBackground(new Color(51, 51, 153));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				lblSale.setBackground(Color.DARK_GRAY);
			}
		});
		lblSale.setOpaque(true);
		lblSale.setForeground(Color.WHITE);
		lblSale.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSale.setBackground(Color.DARK_GRAY);
		contentPane.add(lblSale);

		JLabel label = new JLabel("     File");
		sl_contentPane.putConstraint(SpringLayout.NORTH, label, 363, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, label, 425, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, label, 196, SpringLayout.WEST, contentPane);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				label.setBackground(new Color(51, 51, 153));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				label.setBackground(Color.DARK_GRAY);
			}
		});
		label.setOpaque(true);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label.setBackground(Color.DARK_GRAY);
		contentPane.add(label);

		JLabel label_1 = new JLabel("     File");
		sl_contentPane.putConstraint(SpringLayout.NORTH, label_1, 426, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, label_1, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, label_1, 488, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, label_1, 196, SpringLayout.WEST, contentPane);
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				label_1.setBackground(new Color(51, 51, 153));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				label_1.setBackground(Color.DARK_GRAY);
			}
		});
		label_1.setOpaque(true);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_1.setBackground(Color.DARK_GRAY);
		contentPane.add(label_1);

		JLabel lblTransaction = new JLabel("     Transactions");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblTransaction, 489, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblTransaction, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblTransaction, 551, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblTransaction, 196, SpringLayout.WEST, contentPane);
		lblTransaction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				transactioninternal.setVisible(true);

				if (fileinternal.isVisible()) {
					fileinternal.dispose();
				}

				if (mastersinternal.isVisible()) {
					mastersinternal.dispose();
				}

				if (saleinternal.isVisible()) {
					saleinternal.dispose();
				}

				if (settingsinternal.isVisible()) {
					settingsinternal.dispose();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblTransaction.setBackground(new Color(51, 51, 153));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				lblTransaction.setBackground(Color.DARK_GRAY);
			}
		});
		lblTransaction.setOpaque(true);
		lblTransaction.setForeground(Color.WHITE);
		lblTransaction.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblTransaction.setBackground(Color.DARK_GRAY);
		contentPane.add(lblTransaction);

		JLabel lblSettings = new JLabel("     Settings");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSettings, 552, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSettings, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblSettings, 614, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblSettings, 196, SpringLayout.WEST, contentPane);
		lblSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				settingsinternal.setVisible(true);

				if (fileinternal.isVisible()) {
					fileinternal.dispose();
				}

				if (mastersinternal.isVisible()) {
					mastersinternal.dispose();
				}

				if (saleinternal.isVisible()) {
					saleinternal.dispose();
				}

				if (transactioninternal.isVisible()) {
					transactioninternal.dispose();
				}

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblSettings.setBackground(new Color(51, 51, 153));
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				lblSettings.setBackground(Color.DARK_GRAY);
			}
		});
		lblSettings.setOpaque(true);
		lblSettings.setForeground(Color.WHITE);
		lblSettings.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSettings.setBackground(Color.DARK_GRAY);
		contentPane.add(lblSettings);

		JScrollPane scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, 8, SpringLayout.SOUTH, settingsinternal);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, contentPane);
		scrollPane.setBackground(Color.DARK_GRAY);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setRowHeight(25);
		table.setRowMargin(9);
		table.setBackground(Color.DARK_GRAY);
		table.setForeground(Color.white);
		scrollPane.setForeground(Color.GRAY);
		scrollPane.setColumnHeaderView(table);
		
		JLabel lblNewLabel = new JLabel("ENDING STOCKS");
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 11, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 1037, SpringLayout.EAST, txtAuthorisedUser);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel, -774, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel, -5, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, lblNewLabel);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblNewLabel);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("New toggle button");
		contentPane.add(tglbtnNewToggleButton);

		turnoncapslock();
		if (checkIfDatabaseExist()) {
			populateCompNameInAuthuser();
			stock();
		}
	}
}
