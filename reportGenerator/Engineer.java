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

	/*
	 * Set engineer name if "Engineer.txt" file does't exist, if does than
	 * nothing happen.
	 */
	public void setEngineerName(String str) {
		try {
			this.engineerName = this.engineerName == null ? str : this.engineerName; // Set
																						// engineer
																						// name
																						// only
																						// if
																						// it
																						// is
																						// null,
																						// else
																						// leave
																						// it
																						// be.
			File engFile = new File("C:\\RG input\\Engineer.txt"); //$NON-NLS-1$
			FileWriter engFileWriter = new FileWriter(engFile);
			BufferedWriter engBufferWriter = new BufferedWriter(engFileWriter);
			engBufferWriter.write(engineerName);
			engBufferWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Create window for collecting engineer name.
	 */
	public void createSubmitWindow() {
		try {
			File engFile = new File("C:\\RG input\\Engineer.txt");
			if (!engFile.exists()) { // If "Engineer.txt" doesn't exist then
										// create one.
				engFile.createNewFile();
			}
			FileReader engFileReader = new FileReader(engFile);
			BufferedReader engBufferReader = new BufferedReader(engFileReader);
			String line = engBufferReader.readLine(); // If file does exist and
			if (line == null) { // there is something in it then there is no
								// need to ask for engineer name again, just
								// pass to FrontWindow.
				JPanel engineerPanel = new JPanel();
				engineerNameTextField = new JTextField(10);
				engineerNameLabel = new JLabel("Enter your name:");
				submitEngNameButton = new JButton("Submit");
				ListenForSubmitEngButton lForSubmitEngButton = new ListenForSubmitEngButton();
				submitEngNameButton.addActionListener(lForSubmitEngButton);
				// Engineer name.
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

	public void submitWindowMandatoryMethods() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Report Generator");
	}

	public class ListenForSubmitEngButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == submitEngNameButton) {
				String engNameInput = engineerNameTextField.getText();
				if (engNameInput.equals("")) { // If we leave blank and press
												// "Submit" it will generate
												// error.
					Notifications emptyEngName = new Notifications();
					emptyEngName.emptyEngNameError();
				} else {
					setEngineerName(engineerNameTextField.getText());
					FrontWindow fw = new FrontWindow();
					fw.createWindow();
					dispose();
				}
			}
		}
	}
}