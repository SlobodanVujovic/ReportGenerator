/*
 * This is class for different types of notifications.
 */

package reportGenerator;

import javax.swing.*;
import java.io.*;

public class Notifications extends JFrame {
	private static final long serialVersionUID = 1L;

	public void fileNotFound(String fileName) {
		fileName = fileName.substring(fileName.indexOf("\\", 3) + 1);
		JOptionPane.showMessageDialog(Notifications.this, "No \"" + fileName + "\" file in input folder.", "Error",
				JOptionPane.ERROR_MESSAGE); // JOptionPane is used when we want
											// some kind of warning, error or
											// notification message to show, it
											// already have defined all
											// parameters of window (JFrame) we
											// just need to insert message to be
											// showed and type of icon to be
											// displayed.
		System.exit(0);
	}

	public void stringNotFound(File file, String str) {
		String fileStr = file.toString();
		fileStr = fileStr.substring(fileStr.indexOf("\\", 3) + 1);
		JOptionPane.showMessageDialog(Notifications.this, "No \"" + str + "\" in file: " + fileStr, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	public void emptyEngNameError() {
		JOptionPane.showMessageDialog(Notifications.this, "Please provide Engineer name", "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	public void noCommRepInFolder() {
		JOptionPane.showMessageDialog(Notifications.this, "Please put all files in input folder", "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	public void windowMandatoryMethods() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Report Generator");
	}
}
