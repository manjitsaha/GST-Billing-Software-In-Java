package gst;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import java.awt.event.KeyAdapter;

public class AddCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField ledgerNameTF;
	private JTextField placeTF;
	private JTextField emailTF;
	private JTextField add2TF;
	private JTextField pinTF;
	private JTextField websiteTF;
	private JTextField mobileTF;
	private JTextField altMobileTF;
	private JTextField panDateTF;
	private JTextField faxTF;
	private JTextField phoneTF;
	private JTextField countryTF;
	private JTextField add1TF;
	private JComboBox<String> stateComboBox;
	private JComboBox<String> ledgerCatComboBox;
	private JFormattedTextField gstTF;
	private JFormattedTextField panNoTF;
	private JButton btnSave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCustomer frame = new AddCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 * @throws SQLException
	 */

	Connection con = null;
	Statement ps;
	ResultSet rs;

	public void populateState() {
		String sta = "select * from states";
		try {
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGST/GST", "sa", "");
			ps = con.createStatement();
			rs = ps.executeQuery(sta);
			while (rs.next()) {
				stateComboBox.addItem(rs.getString("states"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void savingCustomerDetail() {
		String ledname = ledgerNameTF.getText();
		String place = placeTF.getText();
		String states = stateComboBox.getSelectedItem().toString();
		String em = emailTF.getText();
		String add1 = add1TF.getText();
		String add2 = add2TF.getText();
		String pin = pinTF.getText();
		String webs = websiteTF.getText();
		String mobno = mobileTF.getText().replaceAll(" ", "");
		String altmob = altMobileTF.getText();
		String fax = faxTF.getText();
		String phone = phoneTF.getText();
		String gst = gstTF.getText();
		String pan = panNoTF.getText();
		String pandate = panDateTF.getText();
		String ledcat = ledgerCatComboBox.getSelectedItem().toString();
		String country = countryTF.getText();

		try {
			Connection con = DriverManager.getConnection("jdbc:h2:C:/SimpleGST/GST", "sa", "");
			PreparedStatement ps = con
					.prepareStatement("insert into customers values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, ledname);
			ps.setString(2, place);
			ps.setString(3, states);
			ps.setString(4, em);
			ps.setString(5, add1);
			ps.setString(6, add2);
			ps.setInt(7, Integer.parseInt(pin));
			ps.setString(8, webs);
			ps.setString(9, mobno);
			ps.setString(10, altmob);
			ps.setString(11, fax);
			ps.setString(12, phone);
			ps.setString(13, gst);
			ps.setString(14, pan);
			ps.setString(15, pandate);
			ps.setString(16, ledcat);
			ps.setString(17, country);

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void resetFields() {
		
		ledgerNameTF.setText("");
		stateComboBox.setSelectedIndex(0);
		placeTF.setText("");
		emailTF.setText("");
		add1TF.setText("");
		add2TF.setText("");
		pinTF.setText("");
		websiteTF.setText("");
		mobileTF.setText("");
		altMobileTF.setText("");
		faxTF.setText("");
		phoneTF.setText("");
		gstTF.setText("");
		panNoTF.setText("");
		panDateTF.setText("");
		ledgerCatComboBox.setSelectedIndex(0);
		
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AddCustomer() {
		setUndecorated(true);
		setResizable(false);
		setSize(1383, 760);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLedgerName = new JLabel("Ledger Name         :");
		lblLedgerName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLedgerName.setBounds(298, 104, 150, 27);
		contentPane.add(lblLedgerName);

		ledgerNameTF = new JTextField();
		ledgerNameTF.transferFocusDownCycle();
		ledgerNameTF.setOpaque(false);
		ledgerNameTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(stateComboBox, e);
			}
		});
		ledgerNameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {

				if (ledgerNameTF.hasFocus()) {
					ledgerNameTF.setOpaque(true);
					ledgerNameTF.setForeground(new Color(255, 255, 255));
					ledgerNameTF.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				ledgerNameTF.setForeground(Color.black);
				ledgerNameTF.setBackground(new Color(240, 230, 140));
			}
		});

		ledgerNameTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ledgerNameTF.setBorder(null);
		ledgerNameTF.setBounds(447, 106, 282, 27);
		contentPane.add(ledgerNameTF);
		ledgerNameTF.setColumns(10);

		JLabel lblState = new JLabel("State                    :");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblState.setBounds(298, 142, 150, 27);
		contentPane.add(lblState);

		stateComboBox = new JComboBox();
		stateComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		stateComboBox.setBounds(447, 144, 282, 27);
		stateComboBox.transferFocusUpCycle();
		stateComboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(placeTF, e);
			}
		});
		contentPane.add(stateComboBox);

		JLabel lblEmailId = new JLabel("Email ID                :");
		lblEmailId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmailId.setBounds(298, 217, 150, 25);
		contentPane.add(lblEmailId);

		JLabel lblPlace = new JLabel("Place                    :");
		lblPlace.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPlace.setBounds(298, 179, 150, 27);
		contentPane.add(lblPlace);

		JLabel lblAddress = new JLabel("Address 1              :");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress.setBounds(298, 254, 150, 25);
		contentPane.add(lblAddress);

		placeTF = new JTextField();
		placeTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		placeTF.setOpaque(false);
		placeTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(emailTF, e);
			}
		});
		placeTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				placeTF.setOpaque(false);
				if (placeTF.hasFocus()) {
					placeTF.setOpaque(true);
					placeTF.setForeground(new Color(255, 255, 255));
					placeTF.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				placeTF.setForeground(Color.black);
				placeTF.setBackground(new Color(240, 230, 140));
			}
		});
		placeTF.setBorder(null);
		placeTF.setColumns(10);
		placeTF.setBounds(447, 180, 282, 27);
		contentPane.add(placeTF);

		emailTF = new JTextField();
		emailTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emailTF.setOpaque(false);
		emailTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(add1TF, e);
			}
		});
		emailTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				emailTF.setOpaque(false);
				if (emailTF.hasFocus()) {
					emailTF.setOpaque(true);
					emailTF.setForeground(new Color(255, 255, 255));
					emailTF.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				emailTF.setForeground(Color.black);
				emailTF.setBackground(new Color(240, 230, 140));
			}
		});
		emailTF.setBorder(null);
		emailTF.setColumns(10);
		emailTF.setBounds(447, 218, 282, 27);
		contentPane.add(emailTF);

		JLabel lblAddress_1 = new JLabel("Address 2              :");
		lblAddress_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress_1.setBounds(298, 290, 150, 25);
		contentPane.add(lblAddress_1);

		add2TF = new JTextField();
		add2TF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add2TF.setOpaque(false);
		add2TF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(pinTF, e);
			}
		});
		add2TF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				add2TF.setOpaque(false);
				if (add2TF.hasFocus()) {
					add2TF.setOpaque(true);
					add2TF.setForeground(new Color(255, 255, 255));
					add2TF.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				add2TF.setForeground(Color.black);
				add2TF.setBackground(new Color(240, 230, 140));
			}
		});
		add2TF.setBorder(null);
		add2TF.setColumns(10);
		add2TF.setBounds(447, 290, 282, 27);
		contentPane.add(add2TF);

		JLabel lblPin = new JLabel("Pin                       :");
		lblPin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPin.setBounds(298, 326, 150, 25);
		contentPane.add(lblPin);

		pinTF = new JTextField();
		pinTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pinTF.setOpaque(false);
		pinTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(websiteTF, e);
			}
		});
		pinTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				pinTF.setOpaque(false);
				if (pinTF.hasFocus()) {
					pinTF.setOpaque(true);
					pinTF.setForeground(new Color(255, 255, 255));
					pinTF.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				pinTF.setForeground(Color.black);
				pinTF.setBackground(new Color(240, 230, 140));
			}
		});
		pinTF.setBorder(null);
		pinTF.setColumns(10);
		pinTF.setBounds(447, 326, 282, 27);
		contentPane.add(pinTF);

		websiteTF = new JTextField();
		websiteTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		websiteTF.setOpaque(false);
		websiteTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(mobileTF, e);
			}
		});
		websiteTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				websiteTF.setOpaque(false);
				if (websiteTF.hasFocus()) {
					websiteTF.setOpaque(true);
					websiteTF.setForeground(new Color(255, 255, 255));
					websiteTF.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				websiteTF.setForeground(Color.black);
				websiteTF.setBackground(new Color(240, 230, 140));
			}
		});
		websiteTF.setBorder(null);
		websiteTF.setColumns(10);
		websiteTF.setBounds(447, 364, 282, 27);
		contentPane.add(websiteTF);

		JLabel lblWebsite = new JLabel("Website                :");
		lblWebsite.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWebsite.setBounds(298, 362, 150, 25);
		contentPane.add(lblWebsite);

		JLabel lblMobileNo = new JLabel("Mobile No.             :");
		lblMobileNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMobileNo.setBounds(298, 404, 150, 25);
		contentPane.add(lblMobileNo);

		JLabel lblAlternateMobNo = new JLabel("Alternate Mob. No.  :");
		lblAlternateMobNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlternateMobNo.setBounds(298, 440, 150, 25);
		contentPane.add(lblAlternateMobNo);

		mobileTF = new JTextField();
		mobileTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mobileTF.setOpaque(false);
		mobileTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(faxTF, e);
			}
		});
		mobileTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				mobileTF.setOpaque(false);
				if (mobileTF.hasFocus()) {
					mobileTF.setOpaque(true);
					mobileTF.setForeground(new Color(255, 255, 255));
					mobileTF.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				mobileTF.setForeground(Color.black);
				mobileTF.setBackground(new Color(240, 230, 140));
			}
		});
		mobileTF.setBorder(null);
		mobileTF.setColumns(10);
		mobileTF.setBounds(447, 402, 282, 27);
		contentPane.add(mobileTF);

		altMobileTF = new JTextField();
		altMobileTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		altMobileTF.setOpaque(false);
		altMobileTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(phoneTF, e);
			}
		});
		altMobileTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				altMobileTF.setOpaque(false);
				if (altMobileTF.hasFocus()) {
					altMobileTF.setOpaque(true);
					altMobileTF.setForeground(new Color(255, 255, 255));
					altMobileTF.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				altMobileTF.setForeground(Color.black);
				altMobileTF.setBackground(new Color(240, 230, 140));
			}
		});
		altMobileTF.setBorder(null);
		altMobileTF.setColumns(10);
		altMobileTF.setBounds(447, 440, 282, 27);
		contentPane.add(altMobileTF);

		JLabel lblGstNo = new JLabel("GST No.                :");
		lblGstNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGstNo.setBounds(298, 476, 150, 25);
		contentPane.add(lblGstNo);

		
		try {
			MaskFormatter mask = new MaskFormatter("##?????####?#?A");
			gstTF = new JFormattedTextField(mask);			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		gstTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		gstTF.setOpaque(false);
		gstTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(panNoTF, e);
			}
		});
		
		gstTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				gstTF.setOpaque(false);
				if (gstTF.hasFocus()) {
					gstTF.setOpaque(true);
					gstTF.setForeground(new Color(255, 255, 255));
					gstTF.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				gstTF.setForeground(Color.black);
				gstTF.setBackground(new Color(240, 230, 140));
			}
		});
		gstTF.setBorder(null);
		gstTF.setBounds(447, 476, 282, 25);
		contentPane.add(gstTF);

		JLabel lblPan = new JLabel("PAN                      :");
		lblPan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPan.setBounds(298, 512, 150, 25);
		contentPane.add(lblPan);

		JLabel lblPanDate = new JLabel("PAN Date  :");
		lblPanDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPanDate.setBounds(786, 512, 85, 25);
		contentPane.add(lblPanDate);

		panDateTF = new JTextField();
		panDateTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panDateTF.setOpaque(false);
		panDateTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(ledgerCatComboBox, e);
			}
		});
		panDateTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				panDateTF.setOpaque(false);
				if (panDateTF.hasFocus()) {
					panDateTF.setOpaque(true);
					panDateTF.setForeground(new Color(255, 255, 255));
					panDateTF.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				panDateTF.setForeground(Color.black);
				panDateTF.setBackground(new Color(240, 230, 140));
			}
		});
		panDateTF.setBorder(null);
		panDateTF.setColumns(10);
		panDateTF.setBounds(881, 511, 141, 27);
		contentPane.add(panDateTF);

		JLabel lblLedgerCategory = new JLabel("Ledger Category      :");
		lblLedgerCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLedgerCategory.setBounds(298, 548, 150, 25);
		contentPane.add(lblLedgerCategory);

		String ledcat[] = { "OTHERS", "RETAILERS", "STOCKIST", "DISTRIBUTORS" };
		ledgerCatComboBox = new JComboBox(ledcat);
		ledgerCatComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ledgerCatComboBox.setBounds(447, 551, 141, 22);
		ledgerCatComboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(countryTF, e);
			}
		});
		contentPane.add(ledgerCatComboBox);

		JLabel lblFaxNo = new JLabel("Fax No.    :");
		lblFaxNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFaxNo.setBounds(786, 404, 85, 25);
		contentPane.add(lblFaxNo);

		faxTF = new JTextField();
		faxTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		faxTF.setOpaque(false);
		faxTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(altMobileTF, e);
			}
		});
		faxTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				faxTF.setOpaque(false);
				if (faxTF.hasFocus()) {
					faxTF.setOpaque(true);
					faxTF.setForeground(new Color(255, 255, 255));
					faxTF.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				faxTF.setForeground(Color.black);
				faxTF.setBackground(new Color(240, 230, 140));
			}
		});
		faxTF.setBorder(null);
		faxTF.setColumns(10);
		faxTF.setBounds(881, 403, 141, 27);
		contentPane.add(faxTF);

		JLabel lblPhoneNo = new JLabel("Phone No. :");
		lblPhoneNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhoneNo.setBounds(786, 440, 85, 25);
		contentPane.add(lblPhoneNo);

		phoneTF = new JTextField();
		phoneTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		phoneTF.setOpaque(false);
		phoneTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(gstTF, e);
			}
		});
		phoneTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				phoneTF.setOpaque(false);
				if (phoneTF.hasFocus()) {
					phoneTF.setOpaque(true);
					phoneTF.setForeground(new Color(255, 255, 255));
					phoneTF.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				phoneTF.setForeground(Color.black);
				phoneTF.setBackground(new Color(240, 230, 140));
			}
		});
		phoneTF.setBorder(null);
		phoneTF.setColumns(10);
		phoneTF.setBounds(881, 439, 141, 27);
		contentPane.add(phoneTF);

		JLabel lblCountry = new JLabel("Country                 :");
		lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCountry.setBounds(298, 586, 150, 25);
		contentPane.add(lblCountry);

		countryTF = new JTextField();
		countryTF.setText("INDIA");
		countryTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		countryTF.setColumns(10);
		countryTF.setBounds(447, 585, 141, 27);
		countryTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(btnSave, e);
			}
		});
		contentPane.add(countryTF);

		MaskFormatter mk;
		try {
			mk = new MaskFormatter("?????####?");
			panNoTF = new JFormattedTextField(mk);
			panNoTF.setFont(new Font("Tahoma", Font.PLAIN, 15));
			panNoTF.setOpaque(false);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		panNoTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(panDateTF, e);
			}
		});

		panNoTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				panNoTF.setOpaque(false);
				if (panNoTF.hasFocus()) {
					panNoTF.setOpaque(true);
					panNoTF.setForeground(new Color(255, 255, 255));
					panNoTF.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				panNoTF.setForeground(Color.black);
				panNoTF.setBackground(new Color(240, 230, 140));
			}
		});
		panNoTF.setBorder(null);
		panNoTF.setBounds(447, 512, 282, 25);
		contentPane.add(panNoTF);

		add1TF = new JTextField();
		add1TF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add1TF.setOpaque(false);
		add1TF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				CommonFunctions.requestFocus(add2TF, e);
			}
		});
		add1TF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				add1TF.setOpaque(false);
				if (add1TF.hasFocus()) {
					add1TF.setOpaque(true);
					add1TF.setForeground(new Color(255, 255, 255));
					add1TF.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				add1TF.setForeground(Color.black);
				add1TF.setBackground(new Color(240, 230, 140));
			}
		});
		add1TF.setColumns(10);
		add1TF.setBorder(null);
		add1TF.setBounds(447, 253, 282, 27);
		contentPane.add(add1TF);

		btnSave = new JButton("Save");
		btnSave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					savingCustomerDetail();
					resetFields();
					if(Invoice.isInvoiceVisible) {
						Invoice.main(null);
					}
				}
			}
		});
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				savingCustomerDetail();
				resetFields();
				if(Invoice.isInvoiceVisible) {
					Invoice.main(null);
				}
			}
		});

		btnSave.setBackground(Color.GREEN);
		btnSave.setBounds(782, 676, 89, 23);
		contentPane.add(btnSave);

		JButton btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetFields();
			}
		});
		btnClear.setBackground(Color.ORANGE);
		btnClear.setBounds(901, 676, 89, 23);
		contentPane.add(btnClear);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				dispose();
			}
		});
		btnClose.setBackground(Color.RED);
		btnClose.setBounds(1019, 676, 89, 23);
		contentPane.add(btnClose);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 204, 760);
		contentPane.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(1185, 0, 198, 760);
		contentPane.add(panel_1);

		populateState();
		closeFrame();

	}
}
