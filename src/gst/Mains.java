package gst;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class Mains extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
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

	public void populateCompNameInAuthuser() {
		try {
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks", "sa", "");
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
	
	public boolean checkIfDatabaseExist() {
		File f = new File("C:\\SimpleGSTsnacks");
		if(f.exists()) {			
			return true;
		}
		
		return false;
	}

	public Mains() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				closeInternalFrames();
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String username = System.getProperty("user.name");
		setTitle("Simple GST - C:/SimpleGST/GST " + "Username -" + username);
		setSize(1383, 760);
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
		contentPane.setLayout(null);

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
				
				if(checkIfDatabaseExist()) {
					JOptionPane.showMessageDialog(getParent(), "Database already exist","Error",JOptionPane.WARNING_MESSAGE);
					
				}else {
					a.createDetails();
					JOptionPane.showMessageDialog(getParent(), "Database created successfully","Success",JOptionPane.INFORMATION_MESSAGE);
					
				}
				
			}
		});

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
		fileinternal.setBounds(196, 153, 127, 105);
		contentPane.add(fileinternal);

		mastersinternal = new JInternalFrame();
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
		mastersinternal.setBounds(196, 153, 209, 245);
		contentPane.add(mastersinternal);

		saleinternal = new JInternalFrame("New JInternalFrame");
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
		saleinternal.setBounds(195, 256, 209, 154);
		contentPane.add(saleinternal);

		transactioninternal = new JInternalFrame("");
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
		transactioninternal.setBounds(196, 430, 209, 121);
		contentPane.add(transactioninternal);

		settingsinternal = new JInternalFrame("");
		settingsinternal.setBounds(196, 503, 209, 112);
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

		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				closeInternalFrames();
			}
		});
		textField.setText("Authorised User :-");
		textField.setForeground(Color.RED);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBackground(Color.DARK_GRAY);
		textField.setBounds(0, 0, 196, 32);
		contentPane.add(textField);

		textArea = new JTextArea();
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
		textArea.setBounds(0, 31, 196, 142);
		contentPane.add(textArea);

		JLabel lblFile = new JLabel("     File");
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
		lblFile.setBounds(0, 174, 196, 62);
		contentPane.add(lblFile);

		JLabel lblMasters = new JLabel("     Masters");
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
		lblMasters.setBounds(0, 237, 196, 62);
		contentPane.add(lblMasters);

		JLabel lblSale = new JLabel("     Sale");
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
		lblSale.setBounds(0, 300, 196, 62);
		contentPane.add(lblSale);

		JLabel label = new JLabel("     File");
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
		label.setBounds(0, 363, 196, 62);
		contentPane.add(label);

		JLabel label_1 = new JLabel("     File");
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
		label_1.setBounds(0, 426, 196, 62);
		contentPane.add(label_1);

		JLabel lblTransaction = new JLabel("     Transactions");
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
		lblTransaction.setBounds(0, 489, 196, 62);
		contentPane.add(lblTransaction);

		JLabel lblSettings = new JLabel("     Settings");
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
		lblSettings.setBounds(0, 552, 196, 62);
		contentPane.add(lblSettings);

		// turnoncapslock();
		//populateCompNameInAuthuser();
	}
}
