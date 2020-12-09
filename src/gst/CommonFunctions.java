package gst;

import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class CommonFunctions {
	
	
	
	public static void requestFocus(JTextField txtField , KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			txtField.requestFocus();
		}
		
	}
	
	public static void requestFocus(JComboBox comboBox , KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			comboBox.requestFocus();
		}
		
	}
	
	public static void requestFocus(JButton button , KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			button.requestFocus();
		}
		
	}

	

}
