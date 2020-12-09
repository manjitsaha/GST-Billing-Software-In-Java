package gst;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import com.jtattoo.plaf.DecorationHelper;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login {

	private static JPasswordField tf;
	private static JPasswordField pf;
	public static String duser = "15316121122";
	public static String dpass = "213103";
	private static JFrame f;

	public static void theme() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

	}
	public static void closeFrame() {

		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
		Action escapeAction = new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		};
		f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
		f.getRootPane().getActionMap().put("ESCAPE", escapeAction);
	}

	public static void main(String[] args) {

		theme();

		UIManager.put("TextField.caretForeground", new ColorUIResource(Color.white));
		UIManager.put("PasswordField.caretForeground", new ColorUIResource(Color.white));

		f = new JFrame("GST Billing");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setForeground(Color.WHITE);
		f.getContentPane().setBackground(Color.DARK_GRAY);
		f.setBackground(Color.BLACK);
		f.setUndecorated(true);
		DecorationHelper.decorateWindows(false);

		f.setBounds(360, 200, 606, 309);
		f.getContentPane().setLayout(null);

		tf = new JPasswordField();
		tf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					pf.requestFocus();
				}
			}
		});
		tf.setBorder(null);
		tf.setOpaque(false);
		tf.setToolTipText("username");
		tf.setForeground(new Color(255, 248, 220));
		tf.setBounds(193, 54, 201, 31);
		f.getContentPane().add(tf);
		tf.setColumns(10);
		tf.requestFocusInWindow();

		final JLabel newuser = new JLabel("");
		newuser.setForeground(Color.DARK_GRAY);
		newuser.setBounds(258, 29, 46, 14);
		f.getContentPane().add(newuser);

		final JLabel newpass = new JLabel("");
		newpass.setForeground(Color.DARK_GRAY);
		newpass.setOpaque(false);
		newpass.setBounds(258, 109, 46, 14);
		f.getContentPane().add(newpass);

		final JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBackground(Color.WHITE);
		lblUsername.setBounds(193, 29, 83, 14);

		f.getContentPane().add(lblUsername);

		final JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(193, 109, 83, 14);
		f.getContentPane().add(lblPassword);

		final JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(193, 165, 201, 23);
		f.getContentPane().add(lblNewLabel);

		final JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(246, 199, 89, 23);
		f.getContentPane().add(btnLogin);

		final JLabel warning = new JLabel("");
		warning.setForeground(Color.RED);
		warning.setBounds(193, 174, 346, 23);
		f.getContentPane().add(warning);

		pf = new JPasswordField();
		pf.setForeground(Color.WHITE);
		pf.setOpaque(false);
		pf.setBorder(null);
		pf.setToolTipText("password");
		pf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String user = newuser.getText();
				if (user.isEmpty()) {
					newuser.setText(".");
				}
				String pass = newpass.getText();
				if (pass.isEmpty()) {
					newpass.setText(".");
				}
				String u = tf.getText();
				@SuppressWarnings("deprecation")
				String p = pf.getText();

				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					if (u.equals(user) && p.equals(pass)) {
						Mains.main(null);
						f.dispose();
					} else if (u.equals(duser) && p.equals(dpass)) {
						Mains.main(null);
						f.dispose();
					} else if (u.isEmpty() && p.isEmpty()) {
						lblNewLabel.setText("USERNAME OR PASSWORD IS EMPTY");
					} else {

						lblNewLabel.setText("USERNAME OR PASSWORD IS WRONG");

					}

			}
		});
		pf.setBounds(193, 125, 201, 38);
		f.getContentPane().add(pf);
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				String user = newuser.getText();
				if (user.isEmpty()) {
					newuser.setText(".");
				}
				String pass = newpass.getText();
				if (pass.isEmpty()) {
					newpass.setText(".");
				}
				String u = tf.getText();
				@SuppressWarnings("deprecation")
				String p = pf.getText();

				if (u.equals(user) && p.equals(pass)) {
					Mains.main(null);
					f.dispose();
				} else if (u.equals(duser) && p.equals(dpass)) {
					Mains.main(null);
					f.dispose();
				} else if (u.isEmpty() && p.isEmpty()) {
					lblNewLabel.setText("USERNAME OR PASSWORD IS EMPTY");
				} else {

					lblNewLabel.setText("USERNAME OR PASSWORD IS WRONG");

				}
			}
		});

		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
		Action escapeAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		};
		((JComponent) f.getContentPane()).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
		((JComponent) f.getContentPane()).getActionMap().put("ESCAPE", escapeAction);

		JButton btnExit = new JButton("EXIT");
		btnExit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tf.requestFocus();
			}
		});
		btnExit.requestFocusInWindow();

		btnExit.setBounds(551, 0, 55, 23);
		f.getContentPane().add(btnExit);

		JLabel lblBySahaStudios = new JLabel("By Saha Studios");
		lblBySahaStudios.setFont(new Font("Arial", Font.PLAIN, 11));
		lblBySahaStudios.setForeground(Color.GREEN);
		lblBySahaStudios.setBounds(10, 262, 173, 14);
		f.getContentPane().add(lblBySahaStudios);

		JLabel lblAllCopyrightsAre = new JLabel("All copyrights are reserved to Saha Studios");
		lblAllCopyrightsAre.setFont(new Font("Arial", Font.PLAIN, 11));
		lblAllCopyrightsAre.setForeground(Color.GREEN);
		lblAllCopyrightsAre.setBounds(10, 287, 309, 14);
		f.getContentPane().add(lblAllCopyrightsAre);

		JLabel lblEmailUs = new JLabel("Email Us");
		lblEmailUs.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEmailUs.setForeground(Color.GREEN);
		lblEmailUs.setBounds(435, 262, 104, 14);
		f.getContentPane().add(lblEmailUs);

		JLabel lblComeonamilmeegmailcom = new JLabel("comeonmailmee@gmail.com");
		lblComeonamilmeegmailcom.setFont(new Font("Arial", Font.PLAIN, 11));
		lblComeonamilmeegmailcom.setForeground(Color.GREEN);
		lblComeonamilmeegmailcom.setBounds(435, 287, 171, 14);
		f.getContentPane().add(lblComeonamilmeegmailcom);

		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(10, 13, 87, 28);
		f.getContentPane().add(panel);

		JLabel lblGstBilling = new JLabel("GST Billing");
		panel.add(lblGstBilling);
		lblGstBilling.setForeground(new Color(147, 112, 219));
		lblGstBilling.setBackground(Color.CYAN);
		lblGstBilling.setFont(new Font("Arial", Font.PLAIN, 15));

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 128, 128));
		separator.setForeground(new Color(0, 128, 128));
		separator.setBounds(193, 76, 201, 2);
		f.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 128, 128));
		separator_1.setBackground(new Color(0, 128, 128));
		separator_1.setBounds(193, 154, 201, 2);
		f.getContentPane().add(separator_1);

		JLabel trialexpnotice = new JLabel("");
		trialexpnotice.setForeground(Color.RED);
		trialexpnotice.setBounds(193, 233, 237, 23);
		f.getContentPane().add(trialexpnotice);

		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				f.dispose();
			}
		});

		f.setVisible(true);

		Connection con = null;
		Statement st;
		ResultSet rs;

		try {
			con = DriverManager.getConnection("jdbc:h2:C:/SimpleGST/GST", "sa", "");
			String user = "SELECT * FROM ADMIN";
			st = con.createStatement();
			rs = st.executeQuery(user);
			while (rs.next()) {
				newuser.setText(rs.getString("username"));
				newpass.setText(rs.getString("password"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
		
		closeFrame();
	}
}
