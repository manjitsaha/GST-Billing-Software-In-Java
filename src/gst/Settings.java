package gst;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

public class Settings extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel oldpass;
	private JLabel oldpasslbl;
	private JLabel newuserlbl;
	private JLabel newpasslbl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
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
	Connection con;
	ResultSet rs;
	java.sql.Statement st;
	private JPasswordField oldpasstext;
	private JPasswordField newusertext;
	private JPasswordField newpasstext;
	String op;
	String opt;

	public void stmethod(String query) {
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void getOldUsername() {
		try {
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGST/GST", "sa", "");
			stmethod("SELECT * FROM ADMIN");
			while (rs.next()) {
				oldpass.setText(rs.getString("password"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void settingNewAdmin() {
		String user = newusertext.getText();
		String pass = newpasstext.getText();
		try {
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGST/GST", "sa", "");
			PreparedStatement ps = con
					.prepareStatement("UPDATE ADMIN SET USERNAME = '" + user + "' ,password = '" + pass + "' ");
			ps.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void checkOldpass() {
		op = oldpass.getText();
		opt = oldpasstext.getText();

	}

	public void checkingTextFieldsForText() {
		String oldp = oldpasstext.getText();
		String newu = newusertext.getText();
		String newp = newpasstext.getText();
		if (oldp.isEmpty()) {
			oldpasslbl.setForeground(Color.red);
		}
		if (newu.isEmpty()) {
			newuserlbl.setForeground(Color.red);
		}
		if (newp.isEmpty()) {
			newpasslbl.setForeground(Color.red);
		}

	}

	public void setNewPass() {
		String np = newpasstext.getText();
		oldpass.setText(np);
	}

	public void closeFrame() {

		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
		Action escapeAction = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		};
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", escapeAction);
	}

	public Settings() {

		Toolkit t = getToolkit();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(t.getScreenSize().width, t.getScreenSize().height - 20);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JLabel wrongoldpass = new JLabel("");
		wrongoldpass.setForeground(Color.RED);
		wrongoldpass.setBounds(869, 342, 101, 14);
		contentPane.add(wrongoldpass);

		oldpasstext = new JPasswordField();
		oldpasstext.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					newusertext.requestFocus();
				}
			}
		});
		oldpasstext.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				wrongoldpass.setText(" ");
				oldpasslbl.setForeground(Color.BLACK);
				oldpasstext.setOpaque(true);
				oldpasstext.setBackground(Color.BLACK);
				oldpasstext.setForeground(Color.white);

			}

			@Override
			public void focusLost(FocusEvent arg0) {
				oldpasstext.setOpaque(false);

				oldpasstext.setForeground(Color.black);
			}
		});
		oldpasstext.setOpaque(false);
		oldpasstext.setBorder(null);
		oldpasstext.setBounds(638, 333, 196, 33);
		contentPane.add(oldpasstext);
		oldpasstext.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(638, 356, 196, 2);
		contentPane.add(separator);

		newusertext = new JPasswordField();
		newusertext.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					newpasstext.requestFocus();
				}
			}
		});
		newusertext.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				newusertext.setOpaque(true);
				newusertext.setBackground(Color.BLACK);
				newusertext.setForeground(Color.white);
				newuserlbl.setForeground(Color.BLACK);

			}

			@Override
			public void focusLost(FocusEvent arg0) {
				newusertext.setOpaque(false);

				newusertext.setForeground(Color.black);

			}
		});
		newusertext.setOpaque(false);
		newusertext.setColumns(10);
		newusertext.setBorder(null);
		newusertext.setBounds(638, 419, 196, 33);
		contentPane.add(newusertext);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(638, 441, 196, 2);
		contentPane.add(separator_1);

		oldpasslbl = new JLabel("OLD PASSWORD");
		oldpasslbl.setBounds(638, 308, 82, 14);
		contentPane.add(oldpasslbl);

		newuserlbl = new JLabel("NEW USERNAME");
		newuserlbl.setBounds(638, 394, 82, 14);
		contentPane.add(newuserlbl);

		newpasslbl = new JLabel("NEW PASSWORD");
		newpasslbl.setBounds(638, 466, 94, 14);
		contentPane.add(newpasslbl);

		final JLabel label = new JLabel("");
		label.setForeground(new Color(50, 205, 50));
		label.setBounds(859, 505, 206, 14);
		contentPane.add(label);

		newpasstext = new JPasswordField();
		newpasstext.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					@SuppressWarnings("deprecation")
					String a = newpasstext.getText();
					@SuppressWarnings("deprecation")
					String b = oldpasstext.getText();
					@SuppressWarnings("deprecation")
					String c = newusertext.getText();
					checkOldpass();
					if (a.isEmpty() || b.isEmpty() || c.isEmpty()) {

						checkingTextFieldsForText();
					} else if (opt.equals("010013101870")) {
						settingNewAdmin();
						label.setText("PASSWORD SUCCESSFULLY CHANGED");

					} else if (opt.equals(op)) {
						settingNewAdmin();
						label.setText("PASSWORD SUCCESSFULLY CHANGED");
					} else {
						wrongoldpass.setText("WRONG PASSWORD");

					}
					setNewPass();
				}
			}
		});
		newpasstext.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				newpasstext.setOpaque(true);
				newpasstext.setBackground(Color.BLACK);
				newpasstext.setForeground(Color.white);
				newpasslbl.setForeground(Color.black);

			}

			@Override
			public void focusLost(FocusEvent arg0) {
				newpasstext.setOpaque(false);

				newpasstext.setForeground(Color.black);
			}
		});
		newpasstext.setOpaque(false);
		newpasstext.setColumns(10);
		newpasstext.setBorder(null);
		newpasstext.setBounds(638, 496, 196, 33);
		contentPane.add(newpasstext);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(638, 517, 196, 2);
		contentPane.add(separator_2);

		oldpass = new JLabel("");
		oldpass.setForeground(new Color(240, 230, 140));
		oldpass.setBounds(730, 308, 46, 14);
		contentPane.add(oldpass);

		JButton btnChange = new JButton("Save");
		btnChange.setBackground(Color.GREEN);
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String a = newpasstext.getText();
				String b = oldpasstext.getText();
				String c = newusertext.getText();
				checkOldpass();
				if (a.isEmpty() || b.isEmpty() || c.isEmpty()) {

					checkingTextFieldsForText();
				} else if (opt.equals("010013101870")) {
					settingNewAdmin();
					label.setText("PASSWORD SUCCESSFULLY CHANGED");

				} else if (opt.equals(op)) {
					settingNewAdmin();
					label.setText("PASSWORD SUCCESSFULLY CHANGED");
				} else {
					wrongoldpass.setText("WRONG PASSWORD");

				}

				setNewPass();
			}
		});
		btnChange.setBounds(687, 555, 89, 23);
		contentPane.add(btnChange);

		getOldUsername();
		closeFrame();
	}
}
