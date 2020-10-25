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

public class AddCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_11;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_12;
	private JTextField textField_3;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JFormattedTextField formattedTextField;
	private JFormattedTextField formattedTextField_1;

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
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks", "sa", "");
			ps = con.createStatement();
			rs = ps.executeQuery(sta);
			while (rs.next()) {
				comboBox.addItem(rs.getString("states"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void savingCustomerDetail() {
		String ledname = textField.getText();
		String place = textField_1.getText();
		String states = comboBox.getSelectedItem().toString();
		String em = textField_2.getText();
		String add1 = textField_3.getText();
		String add2 = textField_4.getText();
		String pin = textField_5.getText();
		String webs = textField_6.getText();
		String mobno = textField_7.getText().replaceAll(" ", "");
		String altmob = textField_8.getText();
		String fax = textField_9.getText();
		String phone = textField_10.getText();
		String gst = formattedTextField.getText();
		String pan = formattedTextField_1.getText();
		String pandate = textField_11.getText();
		String ledcat = comboBox_1.getSelectedItem().toString();
		String country = textField_12.getText();

		try {
			Connection con = DriverManager.getConnection("jdbc:h2:C:/SimpleGSTsnacks/GSTsnacks", "sa", "");
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
			ps.setLong(9, Long.parseLong(mobno));
			ps.setLong(10, Long.parseLong(altmob));
			ps.setInt(11, Integer.parseInt(fax));
			ps.setInt(12, Integer.parseInt(phone));
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

	public void resettingFields() {
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
		textField_7.setText("");
		textField_8.setText("");
		textField_9.setText("");
		textField_10.setText("");
		textField_11.setText("");
		textField_12.setText("");
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

		textField = new JTextField();
		textField.setOpaque(false);
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {

				if (textField.hasFocus()) {
					textField.setOpaque(true);
					textField.setForeground(new Color(255, 255, 255));
					textField.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				textField.setForeground(Color.black);
				textField.setBackground(new Color(240, 230, 140));
			}
		});

		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBorder(null);
		textField.setBounds(447, 106, 282, 27);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblState = new JLabel("State                    :");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblState.setBounds(298, 142, 150, 27);
		contentPane.add(lblState);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(447, 144, 282, 27);
		contentPane.add(comboBox);

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

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setOpaque(false);
		textField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textField_1.setOpaque(false);
				if (textField_1.hasFocus()) {
					textField_1.setOpaque(true);
					textField_1.setForeground(new Color(255, 255, 255));
					textField_1.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				textField_1.setForeground(Color.black);
				textField_1.setBackground(new Color(240, 230, 140));
			}
		});
		textField_1.setBorder(null);
		textField_1.setColumns(10);
		textField_1.setBounds(447, 180, 282, 27);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_2.setOpaque(false);
		textField_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textField_2.setOpaque(false);
				if (textField_2.hasFocus()) {
					textField_2.setOpaque(true);
					textField_2.setForeground(new Color(255, 255, 255));
					textField_2.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				textField_2.setForeground(Color.black);
				textField_2.setBackground(new Color(240, 230, 140));
			}
		});
		textField_2.setBorder(null);
		textField_2.setColumns(10);
		textField_2.setBounds(447, 218, 282, 27);
		contentPane.add(textField_2);

		JLabel lblAddress_1 = new JLabel("Address 2              :");
		lblAddress_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress_1.setBounds(298, 290, 150, 25);
		contentPane.add(lblAddress_1);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_4.setOpaque(false);
		textField_4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textField_4.setOpaque(false);
				if (textField_4.hasFocus()) {
					textField_4.setOpaque(true);
					textField_4.setForeground(new Color(255, 255, 255));
					textField_4.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				textField_4.setForeground(Color.black);
				textField_4.setBackground(new Color(240, 230, 140));
			}
		});
		textField_4.setBorder(null);
		textField_4.setColumns(10);
		textField_4.setBounds(447, 290, 282, 27);
		contentPane.add(textField_4);

		JLabel lblPin = new JLabel("Pin                       :");
		lblPin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPin.setBounds(298, 326, 150, 25);
		contentPane.add(lblPin);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_5.setOpaque(false);
		textField_5.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textField_5.setOpaque(false);
				if (textField_5.hasFocus()) {
					textField_5.setOpaque(true);
					textField_5.setForeground(new Color(255, 255, 255));
					textField_5.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				textField_5.setForeground(Color.black);
				textField_5.setBackground(new Color(240, 230, 140));
			}
		});
		textField_5.setBorder(null);
		textField_5.setColumns(10);
		textField_5.setBounds(447, 326, 282, 27);
		contentPane.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_6.setOpaque(false);
		textField_6.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textField_6.setOpaque(false);
				if (textField_6.hasFocus()) {
					textField_6.setOpaque(true);
					textField_6.setForeground(new Color(255, 255, 255));
					textField_6.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				textField_6.setForeground(Color.black);
				textField_6.setBackground(new Color(240, 230, 140));
			}
		});
		textField_6.setBorder(null);
		textField_6.setColumns(10);
		textField_6.setBounds(447, 364, 282, 27);
		contentPane.add(textField_6);

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

		textField_7 = new JTextField();
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_7.setOpaque(false);
		textField_7.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textField_7.setOpaque(false);
				if (textField_7.hasFocus()) {
					textField_7.setOpaque(true);
					textField_7.setForeground(new Color(255, 255, 255));
					textField_7.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				textField_7.setForeground(Color.black);
				textField_7.setBackground(new Color(240, 230, 140));
			}
		});
		textField_7.setBorder(null);
		textField_7.setColumns(10);
		textField_7.setBounds(447, 402, 282, 27);
		contentPane.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_8.setOpaque(false);
		textField_8.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textField_8.setOpaque(false);
				if (textField_8.hasFocus()) {
					textField_8.setOpaque(true);
					textField_8.setForeground(new Color(255, 255, 255));
					textField_8.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				textField_8.setForeground(Color.black);
				textField_8.setBackground(new Color(240, 230, 140));
			}
		});
		textField_8.setBorder(null);
		textField_8.setColumns(10);
		textField_8.setBounds(447, 440, 282, 27);
		contentPane.add(textField_8);

		JLabel lblGstNo = new JLabel("GST No.                :");
		lblGstNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGstNo.setBounds(298, 476, 150, 25);
		contentPane.add(lblGstNo);

		MaskFormatter mask;
		try {
			mask = new MaskFormatter("##?????####?#?A");
			formattedTextField = new JFormattedTextField(mask);
			formattedTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
			formattedTextField.setOpaque(false);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		formattedTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				formattedTextField.setOpaque(false);
				if (formattedTextField.hasFocus()) {
					formattedTextField.setOpaque(true);
					formattedTextField.setForeground(new Color(255, 255, 255));
					formattedTextField.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				formattedTextField.setForeground(Color.black);
				formattedTextField.setBackground(new Color(240, 230, 140));
			}
		});
		formattedTextField.setBorder(null);
		formattedTextField.setBounds(447, 476, 282, 25);
		contentPane.add(formattedTextField);

		JLabel lblPan = new JLabel("PAN                      :");
		lblPan.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPan.setBounds(298, 512, 150, 25);
		contentPane.add(lblPan);

		JLabel lblPanDate = new JLabel("PAN Date  :");
		lblPanDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPanDate.setBounds(786, 512, 85, 25);
		contentPane.add(lblPanDate);

		textField_11 = new JTextField();
		textField_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_11.setOpaque(false);
		textField_11.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textField_11.setOpaque(false);
				if (textField_11.hasFocus()) {
					textField_11.setOpaque(true);
					textField_11.setForeground(new Color(255, 255, 255));
					textField_11.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				textField_11.setForeground(Color.black);
				textField_11.setBackground(new Color(240, 230, 140));
			}
		});
		textField_11.setBorder(null);
		textField_11.setColumns(10);
		textField_11.setBounds(881, 511, 141, 27);
		contentPane.add(textField_11);

		JLabel lblLedgerCategory = new JLabel("Ledger Category      :");
		lblLedgerCategory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLedgerCategory.setBounds(298, 548, 150, 25);
		contentPane.add(lblLedgerCategory);

		String ledcat[] = { "OTHERS", "RETAILERS", "STOCKIST", "DISTRIBUTORS" };
		comboBox_1 = new JComboBox(ledcat);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox_1.setBounds(447, 551, 141, 22);
		contentPane.add(comboBox_1);

		JLabel lblFaxNo = new JLabel("Fax No.    :");
		lblFaxNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFaxNo.setBounds(786, 404, 85, 25);
		contentPane.add(lblFaxNo);

		textField_9 = new JTextField();
		textField_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_9.setOpaque(false);
		textField_9.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textField_9.setOpaque(false);
				if (textField_9.hasFocus()) {
					textField_9.setOpaque(true);
					textField_9.setForeground(new Color(255, 255, 255));
					textField_9.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				textField_9.setForeground(Color.black);
				textField_9.setBackground(new Color(240, 230, 140));
			}
		});
		textField_9.setBorder(null);
		textField_9.setColumns(10);
		textField_9.setBounds(881, 403, 141, 27);
		contentPane.add(textField_9);

		JLabel lblPhoneNo = new JLabel("Phone No. :");
		lblPhoneNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPhoneNo.setBounds(786, 440, 85, 25);
		contentPane.add(lblPhoneNo);

		textField_10 = new JTextField();
		textField_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_10.setOpaque(false);
		textField_10.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textField_10.setOpaque(false);
				if (textField_10.hasFocus()) {
					textField_10.setOpaque(true);
					textField_10.setForeground(new Color(255, 255, 255));
					textField_10.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				textField_10.setForeground(Color.black);
				textField_10.setBackground(new Color(240, 230, 140));
			}
		});
		textField_10.setBorder(null);
		textField_10.setColumns(10);
		textField_10.setBounds(881, 439, 141, 27);
		contentPane.add(textField_10);

		JLabel lblCountry = new JLabel("Country                 :");
		lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCountry.setBounds(298, 586, 150, 25);
		contentPane.add(lblCountry);

		textField_12 = new JTextField();
		textField_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_12.setColumns(10);
		textField_12.setBounds(588, 584, 141, 27);
		contentPane.add(textField_12);

		final JCheckBox chckbxIndia = new JCheckBox("     INDIA");
		chckbxIndia.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxIndia.isSelected()) {
					textField_12.setText("INDIA");
				}
			}
		});
		chckbxIndia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxIndia.setBounds(446, 585, 128, 27);
		contentPane.add(chckbxIndia);

		JLabel lblTypeForAnother = new JLabel("[ TICK THE BOX FOR INDIA OR TYPE FOR ANOTHER COUNTRY ]");
		lblTypeForAnother.setForeground(Color.GRAY);
		lblTypeForAnother.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblTypeForAnother.setBounds(447, 612, 333, 14);
		contentPane.add(lblTypeForAnother);

		MaskFormatter mk;
		try {
			mk = new MaskFormatter("?????####?");
			formattedTextField_1 = new JFormattedTextField(mk);
			formattedTextField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			formattedTextField_1.setOpaque(false);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		formattedTextField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				formattedTextField_1.setOpaque(false);
				if (formattedTextField_1.hasFocus()) {
					formattedTextField_1.setOpaque(true);
					formattedTextField_1.setForeground(new Color(255, 255, 255));
					formattedTextField_1.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				formattedTextField_1.setForeground(Color.black);
				formattedTextField_1.setBackground(new Color(240, 230, 140));
			}
		});
		formattedTextField_1.setBorder(null);
		formattedTextField_1.setBounds(447, 512, 282, 25);
		contentPane.add(formattedTextField_1);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_3.setOpaque(false);
		textField_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textField_3.setOpaque(false);
				if (textField_3.hasFocus()) {
					textField_3.setOpaque(true);
					textField_3.setForeground(new Color(255, 255, 255));
					textField_3.setBackground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				textField_3.setForeground(Color.black);
				textField_3.setBackground(new Color(240, 230, 140));
			}
		});
		textField_3.setColumns(10);
		textField_3.setBorder(null);
		textField_3.setBounds(447, 253, 282, 27);
		contentPane.add(textField_3);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				savingCustomerDetail();
				resettingFields();

			}
		});
		btnSave.setBackground(Color.GREEN);
		btnSave.setBounds(782, 676, 89, 23);
		contentPane.add(btnSave);

		JButton btnClear = new JButton("Clear");
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
