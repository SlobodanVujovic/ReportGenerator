/*
 * Class that deal with engineer name.
 */

package reportGenerator;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;

public class Engineer extends JFrame {
	private static final long serialVersionUID = 1L;
	private String engineerName;
	private JLabel engineerNameLabel;
	private JTextField engineerNameTextField;
	private JButton submitEngNameButton;

	public String getEngineerName() {
		return this.engineerName;
	}

	public void createSubmitEngineerNameWindow() {
		try {
			File engFile = new File("C:\\RG input\\Engineer.txt");
			if (!engFile.exists()) {
				engFile.createNewFile();
			}
			FileReader engFileReader = new FileReader(engFile);
			BufferedReader engBufferReader = new BufferedReader(engFileReader);
			// If file does exist and there is something in it then there is no need to ask for engineer
			// name again, just pass to FrontWindow.
			String line = engBufferReader.readLine();
			if (line == null) {
				JPanel engineerPanel = new JPanel();
				engineerNameTextField = new JTextField(10);
				engineerNameLabel = new JLabel("Enter your name:");
				submitEngNameButton = new JButton("Submit");
				ListenForSubmitEngButton lForSubmitEngButton = new ListenForSubmitEngButton();
				submitEngNameButton.addActionListener(lForSubmitEngButton);
				engineerPanel.add(engineerNameLabel);
				engineerPanel.add(engineerNameTextField);
				engineerPanel.add(submitEngNameButton);
				Border engineerBorder = BorderFactory.createTitledBorder("Engineer");
				engineerPanel.setBorder(engineerBorder);
				this.add(engineerPanel);
				this.pack();
				this.submitWindowMandatoryMethods();
			} else {
				FrontWindow fw = new FrontWindow();
				fw.createWindow();
			}
			engBufferReader.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public class ListenForSubmitEngButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == submitEngNameButton) {
				String engNameInput = engineerNameTextField.getText();
				// If we leave blank and press "Submit" it will generate error.
				if (engNameInput.equals("")) {
					Notifications emptyEngName = new Notifications();
					emptyEngName.emptyEngNameError();
				} else {
					setEngineerNameIfItIsMissing(engNameInput);
					FrontWindow fw = new FrontWindow();
					fw.createWindow();
					dispose();
				}
			}
		}
	}

	public void setEngineerNameIfItIsMissing(String str) {
		try {
			this.engineerName = this.engineerName == null ? str : this.engineerName;
			File engFile = new File("C:\\RG input\\Engineer.txt");
			FileWriter engFileWriter = new FileWriter(engFile);
			BufferedWriter engBufferWriter = new BufferedWriter(engFileWriter);
			engBufferWriter.write(engineerName);
			engBufferWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void submitWindowMandatoryMethods() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Report Generator");
	}
}