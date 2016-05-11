/*
 * This is front panel that we see when work in program. Front window is divided on west (UMTS)
 * and east (LTE) part.
 * If field or method don't have in it's name "East" then it belong to west (UMTS) part, if have
 * then it belong to east (LTE part). 
 */

package reportGenerator;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FrontWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel, bottomPanel, northPanel, centerPanel, westPanel, eastPanel, westNorthPanel,
			eastNorthPanel, southPanel, eastCenterPanel;
	private JRadioButton umts, lte, indoorBTS, indoorBTSEast, outdoorBTS, outdoorBTSEast, commonBTS, commonBTSEast,
			distributeBTS, distributeBTSEast, stackAssembled, stackAssembledEast, wallAssembled, wallAssembledEast,
			poleAssembled, poleAssembledEast, cabinetAssembled, cabinetAssembledEast, stackAssembledRF,
			stackAssembledRFEast, wallAssembledRF, wallAssembledRFEast, poleAssembledRF, poleAssembledRFEast,
			cabinetAssembledRF, cabinetAssembledRFEast, transmission2G, transmission3G;
	private JTextField fiberLengthTextField, distributeJumperLengthS1TextField, distributeJumperLengthS2TextField,
			distributeJumperLengthS3TextField, distributeJumperLengthS4TextField, feederLengthTextField,
			groundsNoTextField, commonJumperAtBtsLengthTextField, commonJumperBeforeMhaLengthTextField,
			commonJumperAfterMhaLengthTextField, commonJumperPerSectorTextField, fiberLengthTextFieldEast,
			distributeJumperLengthS1TextFieldEast, distributeJumperLengthS2TextFieldEast,
			distributeJumperLengthS3TextFieldEast, distributeJumperLengthS4TextFieldEast, feederLengthTextFieldEast,
			commonJumperAtBtsLengthTextFieldEast, commonJumperBeforeMhaLengthTextFieldEast,
			commonJumperAfterMhaLengthTextFieldEast, commonJumperPerSectorTextFieldEast, groundsNoTextFieldEast;
	private JLabel fiberLengthLabel, distributeJumperLengthS1Label, distributeJumperLengthS2Label,
			distributeJumperLengthS3Label, distributeJumperLengthS4Label, feederTypeLabel, feederLengthLabel,
			groundsNoLabel, commonJumperAtBtsLengthLabel, commonJumperBeforeMhaLengthLabel,
			commonJumperAfterMhaLengthLabel, commonJumperPerSectorLabel, fiberLengthLabelEast,
			distributeJumperLengthS1LabelEast, distributeJumperLengthS2LabelEast, distributeJumperLengthS3LabelEast,
			distributeJumperLengthS4LabelEast, feederTypeLabelEast, feederLengthLabelEast,
			commonJumperAtBtsLengthLabelEast, commonJumperBeforeMhaLengthLabelEast, commonJumperAfterMhaLengthLabelEast,
			commonJumperPerSectorLabelEast, groundsNoLabelEast, powSupplyLabel, powSupplyLabelEast, btsCabinetLabel,
			btsCabinetLabelEast;
	private JComboBox<String> feederTypeCombo, feederTypeComboEast, powSupplyCombo, powSupplyComboEast, btsCabinetCombo,
			btsCabinetComboEast;
	private JButton genReportButton, resetButton;
	private InputFiles iFiles;
	private String feederSize, feederSizeEast, fiberLength, fiberLengthEast, feederLength, feederLengthEast,
			jumperLengthS1, jumperLengthS1East, jumperLengthS2, jumperLengthS2East, jumperLengthS3, jumperLengthS3East,
			jumperLengthS4, jumperLengthS4East, jumperAtBtsLength, jumperAtBtsLengthEast, jumperBeforeMhaLength,
			jumperBeforeMhaLengthEast, jumperAfterMhaLength, jumperAfterMhaLengthEast, jumpersPerSector,
			jumpersPerSectorEast, groundsNo, groundsNoEast, instalationTypeStr = "Outdoor",
			instalationTypeStrEast = "Outdoor", btsCabinet = "Flatpack PRSB 16kW 48V",
			btsCabinetEast = "Flatpack PRSB 16kW 48V", architectureTypeStr = "Distribute",
			architectureTypeStrEast = "Distribute", assembleType = "Cabinet", assembleTypeEast = "Cabinet",
			assembleRFType = "Cabinet", assembleRFTypeEast = "Cabinet", powSupplyType = "Eltek",
			powSupplyTypeEast = "Eltek", transmissionType = "3g";

	public JRadioButton getUmts() {
		return umts;
	}

	public JRadioButton getLte() {
		return lte;
	}

	public String getFeederSize() {
		return this.feederSize;
	}

	public void setFeederSize(String feederSize) {
		this.feederSize = feederSize;
	}

	public String getFeederSizeEast() {
		return this.feederSizeEast;
	}

	public void setFeederSizeEast(String feederSizeEast) {
		this.feederSizeEast = feederSizeEast;
	}

	public String getFiberLength() {
		return this.fiberLength;
	}

	public void setFiberLength(String fiberLength) {
		this.fiberLength = fiberLength;
	}

	public String getFiberLengthEast() {
		return this.fiberLengthEast;
	}

	public void setFiberLengthEast(String fiberLengthEast) {
		this.fiberLengthEast = fiberLengthEast;
	}

	public String getFeederLength() {
		return this.feederLength;
	}

	public void setFeederLength(String feederLength) {
		this.feederLength = feederLength;
	}

	public String getFeederLengthEast() {
		return this.feederLengthEast;
	}

	public void setFeederLengthEast(String feederLengthEast) {
		this.feederLengthEast = feederLengthEast;
	}

	public String getJumperLengthS1() {
		return this.jumperLengthS1;
	}

	public void setJumperLengthS1(String jumperLengthS1) {
		this.jumperLengthS1 = jumperLengthS1;
	}

	public String getJumperLengthS1East() {
		return this.jumperLengthS1East;
	}

	public void setJumperLengthS1East(String jumperLengthS1East) {
		this.jumperLengthS1East = jumperLengthS1East;
	}

	public String getJumperLengthS2() {
		return this.jumperLengthS2;
	}

	public void setJumperLengthS2(String jumperLengthS2) {
		this.jumperLengthS2 = jumperLengthS2;
	}

	public String getJumperLengthS2East() {
		return this.jumperLengthS2East;
	}

	public void setJumperLengthS2East(String jumperLengthS2East) {
		this.jumperLengthS2East = jumperLengthS2East;
	}

	public String getJumperLengthS3() {
		return this.jumperLengthS3;
	}

	public void setJumperLengthS3(String jumperLengthS3) {
		this.jumperLengthS3 = jumperLengthS3;
	}

	public String getJumperLengthS3East() {
		return this.jumperLengthS3East;
	}

	public void setJumperLengthS3East(String jumperLengthS3East) {
		this.jumperLengthS3East = jumperLengthS3East;
	}

	public String getJumperLengthS4() {
		return this.jumperLengthS4;
	}

	public void setJumperLengthS4(String jumperLengthS4) {
		this.jumperLengthS4 = jumperLengthS4;
	}

	public String getJumperLengthS4East() {
		return this.jumperLengthS4East;
	}

	public void setJumperLengthS4East(String jumperLengthS4East) {
		this.jumperLengthS4East = jumperLengthS4East;
	}

	public String getJumperAtBtsLength() {
		return this.jumperAtBtsLength;
	}

	public void setJumperAtBtsLength(String jumperAtBtsLength) {
		this.jumperAtBtsLength = jumperAtBtsLength;
	}

	public String getJumperAtBtsLengthEast() {
		return this.jumperAtBtsLengthEast;
	}

	public void setJumperAtBtsLengthEast(String jumperAtBtsLengthEast) {
		this.jumperAtBtsLengthEast = jumperAtBtsLengthEast;
	}

	public String getJumperBeforeMhaLength() {
		return this.jumperBeforeMhaLength;
	}

	public void setJumperBeforeMhaLength(String jumperBeforeMhaLength) {
		this.jumperBeforeMhaLength = jumperBeforeMhaLength;
	}

	public String getJumperBeforeMhaLengthEast() {
		return this.jumperBeforeMhaLengthEast;
	}

	public void setJumperBeforeMhaLengthEast(String jumperBeforeMhaLengthEast) {
		this.jumperBeforeMhaLengthEast = jumperBeforeMhaLengthEast;
	}

	public String getJumperAfterMhaLength() {
		return this.jumperAfterMhaLength;
	}

	public void setJumperAfterMhaLength(String jumperAfterMhaLength) {
		this.jumperAfterMhaLength = jumperAfterMhaLength;
	}

	public String getJumperAfterMhaLengthEast() {
		return this.jumperAfterMhaLengthEast;
	}

	public void setJumperAfterMhaLengthEast(String jumperAfterMhaLengthEast) {
		this.jumperAfterMhaLengthEast = jumperAfterMhaLengthEast;
	}

	public String getJumpersPerSector() {
		return this.jumpersPerSector;
	}

	public void setJumpersPerSector(String jumpersPerSector) {
		this.jumpersPerSector = jumpersPerSector;
	}

	public String getJumpersPerSectorEast() {
		return this.jumpersPerSectorEast;
	}

	public void setJumpersPerSectorEast(String jumpersPerSectorEast) {
		this.jumpersPerSectorEast = jumpersPerSectorEast;
	}

	public String getGroundsNo() {
		return this.groundsNo;
	}

	public void setGroundsNo(String groundsNo) {
		this.groundsNo = groundsNo;
	}

	public String getGroundsNoEast() {
		return this.groundsNoEast;
	}

	public void setGroundsNoEast(String groundsNoEast) {
		this.groundsNoEast = groundsNoEast;
	}

	public String getInstalationTypeStr() {
		return this.instalationTypeStr;
	}

	public void setInstalationTypeStr(String instalationTypeStr) {
		this.instalationTypeStr = instalationTypeStr;
	}

	public String getInstalationTypeStrEast() {
		return this.instalationTypeStrEast;
	}

	public void setInstalationTypeStrEast(String instalationTypeStrEast) {
		this.instalationTypeStrEast = instalationTypeStrEast;
	}

	public void setArchitectureTypeStr(String architectureTypeStr) {
		this.architectureTypeStr = architectureTypeStr;
	}

	public String getArchitectureTypeStr() {
		return this.architectureTypeStr;
	}

	public void setArchitectureTypeStrEast(String architectureTypeStrEast) {
		this.architectureTypeStrEast = architectureTypeStrEast;
	}

	public String getArchitectureTypeStrEast() {
		return this.architectureTypeStrEast;
	}

	public String getBtsCabinet() {
		return this.btsCabinet;
	}

	public void setBtsCabinet(String btsCabinet) {
		this.btsCabinet = btsCabinet;
	}

	public String getBtsCabinetEast() {
		return this.btsCabinetEast;
	}

	public void setBtsCabinetEast(String btsCabinetEast) {
		this.btsCabinetEast = btsCabinetEast;
	}

	public String getAssembleType() {
		return this.assembleType;
	}

	public void setAssembleType(String assembleType) {
		this.assembleType = assembleType;
	}

	public String getAssembleTypeEast() {
		return this.assembleTypeEast;
	}

	public void setAssembleTypeEast(String assembleTypeEast) {
		this.assembleTypeEast = assembleTypeEast;
	}

	public void setAssembleRFType(String assembleRFType) {
		this.assembleRFType = assembleRFType;
	}

	public void setAssembleRFTypeEast(String assembleRFTypeEast) {
		this.assembleRFTypeEast = assembleRFTypeEast;
	}

	public String getAssembleRFType() {
		return this.assembleRFType;
	}

	public String getAssembleRFTypeEast() {
		return this.assembleRFTypeEast;
	}

	public String getPowSupplyType() {
		return this.powSupplyType;
	}

	public void setPowSupplyType(String powSupplyType) {
		this.powSupplyType = powSupplyType;
	}

	public String getPowSupplyTypeEast() {
		return this.powSupplyTypeEast;
	}

	public void setPowSupplyTypeEast(String powSupplyTypeEast) {
		this.powSupplyTypeEast = powSupplyTypeEast;
	}

	public String getTransmissionType() {
		return this.transmissionType;
	}

	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}

	public InputFiles getIFiles() {
		return this.iFiles;
	}

	// Create front (main) window of program.
	public void createWindow() {
		this.iFiles = new InputFiles();

		this.mainPanel = new JPanel(); // This is first panel after JFrame.
										// Contains only bottomPanel.
		this.bottomPanel = new JPanel(); // JFrame - mainPanel - bottomPanel.
											// Contains everything on front
											// window.
		this.northPanel = new JPanel(); // Front window is divided in north,
										// center and south panel. They are in
										// bottomPanel.
		this.centerPanel = new JPanel(new GridLayout(1, 2));
		this.southPanel = new JPanel();

		this.westPanel = new JPanel(new GridBagLayout()); // Center panel
															// contains
															// westPanel and
															// eastPanel.
		this.eastPanel = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		GridBagConstraints gbcEast = new GridBagConstraints();

		this.westNorthPanel = new JPanel(); // westPanel contains
											// westNorthPanel.
		this.eastNorthPanel = new JPanel(); // eastPanel contains eastNorthPanel
											// & eastCenterPanel.

		this.eastCenterPanel = new JPanel();

		this.umts = new JRadioButton("UMTS");
		this.lte = new JRadioButton("LTE");
		ListenForUmts lForUmts = new ListenForUmts();
		this.umts.addItemListener(lForUmts);
		ListenForLte lForLte = new ListenForLte();
		this.lte.addItemListener(lForLte);

		this.indoorBTS = new JRadioButton("Indoor BTS");
		this.outdoorBTS = new JRadioButton("Outdoor BTS", true);
		this.indoorBTSEast = new JRadioButton("Indoor BTS");
		this.outdoorBTSEast = new JRadioButton("Outdoor BTS", true);

		this.commonBTS = new JRadioButton("Common arch.");
		this.distributeBTS = new JRadioButton("Distribute arch.", true);
		this.commonBTSEast = new JRadioButton("Common arch.");
		this.distributeBTSEast = new JRadioButton("Distribute arch.", true);

		this.stackAssembled = new JRadioButton("Stack");
		this.wallAssembled = new JRadioButton("Wall");
		this.poleAssembled = new JRadioButton("Pole");
		this.cabinetAssembled = new JRadioButton("Cabinet", true);
		this.stackAssembledEast = new JRadioButton("Stack");
		this.wallAssembledEast = new JRadioButton("Wall");
		this.poleAssembledEast = new JRadioButton("Pole");
		this.cabinetAssembledEast = new JRadioButton("Cabinet", true);

		this.stackAssembledRF = new JRadioButton("Stack");
		this.wallAssembledRF = new JRadioButton("Wall");
		this.poleAssembledRF = new JRadioButton("Pole");
		this.cabinetAssembledRF = new JRadioButton("Cabinet", true);
		this.stackAssembledRFEast = new JRadioButton("Stack");
		this.wallAssembledRFEast = new JRadioButton("Wall");
		this.poleAssembledRFEast = new JRadioButton("Pole");
		this.cabinetAssembledRFEast = new JRadioButton("Cabinet", true);

		this.transmission2G = new JRadioButton("over 2G");
		this.transmission3G = new JRadioButton("over 3G", true);

		this.fiberLengthTextField = new JTextField("50");
		this.distributeJumperLengthS1TextField = new JTextField("3");
		this.distributeJumperLengthS2TextField = new JTextField("3");
		this.distributeJumperLengthS3TextField = new JTextField("3");
		this.distributeJumperLengthS4TextField = new JTextField();
		this.fiberLengthLabel = new JLabel("Instaled fiber length [m]:");
		this.distributeJumperLengthS1Label = new JLabel("Jumper length at S1 [m]:");
		this.distributeJumperLengthS2Label = new JLabel("Jumper length at S2 [m]:");
		this.distributeJumperLengthS3Label = new JLabel("Jumper length at S3 [m]:");
		this.distributeJumperLengthS4Label = new JLabel("Jumper length at S4 [m]:");
		this.fiberLengthTextFieldEast = new JTextField("50", 0);
		this.distributeJumperLengthS1TextFieldEast = new JTextField("3", 0);
		this.distributeJumperLengthS2TextFieldEast = new JTextField("3", 0);
		this.distributeJumperLengthS3TextFieldEast = new JTextField("3", 0);
		this.distributeJumperLengthS4TextFieldEast = new JTextField(0);
		this.fiberLengthLabelEast = new JLabel("Instaled fiber length [m]:");
		this.distributeJumperLengthS1LabelEast = new JLabel("Jumper length at S1 [m]:");
		this.distributeJumperLengthS2LabelEast = new JLabel("Jumper length at S2 [m]:");
		this.distributeJumperLengthS3LabelEast = new JLabel("Jumper length at S3 [m]:");
		this.distributeJumperLengthS4LabelEast = new JLabel("Jumper length at S4 [m]:");

		String[] feederTypeList = { "1/2\"", "7/8\"", "1 1/4\"" };
		this.feederTypeCombo = new JComboBox<>(feederTypeList);
		this.feederLengthTextField = new JTextField(0);
		this.groundsNoTextField = new JTextField("3", 0);
		this.commonJumperAtBtsLengthTextField = new JTextField("3", 0);
		this.commonJumperBeforeMhaLengthTextField = new JTextField("2", 0);
		this.commonJumperAfterMhaLengthTextField = new JTextField("2", 0);
		this.commonJumperPerSectorTextField = new JTextField("2", 0);
		this.feederLengthLabel = new JLabel("Instaled feeder length [m]:");
		this.groundsNoLabel = new JLabel("Instaled number of grounds:");
		this.commonJumperAtBtsLengthLabel = new JLabel("Instaled jumper at BTS length [m]:");
		this.commonJumperBeforeMhaLengthLabel = new JLabel("Instaled jumper before MHA length [m]:");
		this.commonJumperAfterMhaLengthLabel = new JLabel("Instaled jumper after MHA length [m]:");
		this.commonJumperPerSectorLabel = new JLabel("Instaled number of jumpers per sector [m]:");
		this.feederTypeLabel = new JLabel("Choose feeder type:");
		this.feederTypeComboEast = new JComboBox<>(feederTypeList);
		this.feederLengthTextFieldEast = new JTextField(0);
		this.groundsNoTextFieldEast = new JTextField("3", 0);
		this.commonJumperAtBtsLengthTextFieldEast = new JTextField("3", 0);
		this.commonJumperBeforeMhaLengthTextFieldEast = new JTextField("2", 0);
		this.commonJumperAfterMhaLengthTextFieldEast = new JTextField("2", 0);
		this.commonJumperPerSectorTextFieldEast = new JTextField("2", 0);
		this.feederLengthLabelEast = new JLabel("Instaled feeder length [m]:");
		this.groundsNoLabelEast = new JLabel("Instaled number of grounds:");
		this.commonJumperAtBtsLengthLabelEast = new JLabel("Instaled jumper at BTS length [m]:");
		this.commonJumperBeforeMhaLengthLabelEast = new JLabel("Instaled jumper before MHA length [m]:");
		this.commonJumperAfterMhaLengthLabelEast = new JLabel("Instaled jumper after MHA length [m]:");
		this.commonJumperPerSectorLabelEast = new JLabel("Instaled number of jumpers per sector [m]:");
		this.feederTypeLabelEast = new JLabel("Choose feeder type:");

		String[] powSupplyList = { "Eltek", "FPMA", "FPRA", "Telkom", "PBC", "Other" };
		this.powSupplyCombo = new JComboBox<>(powSupplyList);
		this.powSupplyComboEast = new JComboBox<>(powSupplyList);
		this.powSupplyLabel = new JLabel("Power supply type:");
		this.powSupplyLabelEast = new JLabel("Power supply type:");

		String[] btsCabinetList = { "Flatpack PRSB 16kW 48V", "BS 24x Service", "Micro BBU", "FCIA", "" };
		this.btsCabinetCombo = new JComboBox<>(btsCabinetList);
		this.btsCabinetComboEast = new JComboBox<>(btsCabinetList);
		this.btsCabinetLabel = new JLabel("BTS Cabinet:");
		this.btsCabinetLabelEast = new JLabel("BTS Cabinet:");

		this.genReportButton = new JButton("Generate Report");
		ListenForGenReportButton lForGenReportButton = new ListenForGenReportButton();
		this.genReportButton.addActionListener(lForGenReportButton);
		this.southPanel.add(this.genReportButton);

		this.resetButton = new JButton("Reset");
		ListenForResetButton lForResetButton = new ListenForResetButton();
		this.resetButton.addActionListener(lForResetButton);
		this.southPanel.add(this.resetButton);

		// West panel (UMTS part) by default is gray out.
		this.westPanel.setEnabled(false);
		this.westNorthPanel.setEnabled(false);
		this.indoorBTS.setEnabled(false);
		this.outdoorBTS.setEnabled(false);
		this.commonBTS.setEnabled(false);
		this.distributeBTS.setEnabled(false);
		this.stackAssembled.setEnabled(false);
		this.wallAssembled.setEnabled(false);
		this.poleAssembled.setEnabled(false);
		this.cabinetAssembled.setEnabled(false);
		this.stackAssembledRF.setEnabled(false);
		this.wallAssembledRF.setEnabled(false);
		this.poleAssembledRF.setEnabled(false);
		this.cabinetAssembledRF.setEnabled(false);
		this.fiberLengthLabel.setEnabled(false);
		this.fiberLengthTextField.setEnabled(false);
		this.distributeJumperLengthS1TextField.setEnabled(false);
		this.distributeJumperLengthS2TextField.setEnabled(false);
		this.distributeJumperLengthS3TextField.setEnabled(false);
		this.distributeJumperLengthS4TextField.setEnabled(false);
		this.distributeJumperLengthS1Label.setEnabled(false);
		this.distributeJumperLengthS2Label.setEnabled(false);
		this.distributeJumperLengthS3Label.setEnabled(false);
		this.distributeJumperLengthS4Label.setEnabled(false);
		this.feederTypeCombo.setEnabled(false);
		this.feederLengthTextField.setEnabled(false);
		this.groundsNoTextField.setEnabled(false);
		this.commonJumperAtBtsLengthTextField.setEnabled(false);
		this.commonJumperBeforeMhaLengthTextField.setEnabled(false);
		this.commonJumperAfterMhaLengthTextField.setEnabled(false);
		this.commonJumperPerSectorTextField.setEnabled(false);
		this.feederLengthLabel.setEnabled(false);
		this.groundsNoLabel.setEnabled(false);
		this.commonJumperAtBtsLengthLabel.setEnabled(false);
		this.commonJumperBeforeMhaLengthLabel.setEnabled(false);
		this.commonJumperAfterMhaLengthLabel.setEnabled(false);
		this.commonJumperPerSectorLabel.setEnabled(false);
		this.feederTypeLabel.setEnabled(false);
		this.btsCabinetLabel.setEnabled(false);
		this.btsCabinetCombo.setEnabled(false);
		this.powSupplyCombo.setEnabled(false);
		this.powSupplyLabel.setEnabled(false);
		// East panel (LTE panel) is gray out by default.
		this.eastPanel.setEnabled(false);
		this.eastNorthPanel.setEnabled(false);
		this.eastCenterPanel.setEnabled(false);
		this.indoorBTSEast.setEnabled(false);
		this.outdoorBTSEast.setEnabled(false);
		this.commonBTSEast.setEnabled(false);
		this.distributeBTSEast.setEnabled(false);
		this.stackAssembledEast.setEnabled(false);
		this.wallAssembledEast.setEnabled(false);
		this.poleAssembledEast.setEnabled(false);
		this.cabinetAssembledEast.setEnabled(false);
		this.wallAssembledRFEast.setEnabled(false);
		this.poleAssembledRFEast.setEnabled(false);
		this.stackAssembledRFEast.setEnabled(false);
		this.cabinetAssembledRFEast.setEnabled(false);
		this.transmission2G.setEnabled(false);
		this.transmission3G.setEnabled(false);
		this.fiberLengthLabelEast.setEnabled(false);
		this.fiberLengthTextFieldEast.setEnabled(false);
		this.distributeJumperLengthS1TextFieldEast.setEnabled(false);
		this.distributeJumperLengthS2TextFieldEast.setEnabled(false);
		this.distributeJumperLengthS3TextFieldEast.setEnabled(false);
		this.distributeJumperLengthS4TextFieldEast.setEnabled(false);
		this.fiberLengthLabelEast.setEnabled(false);
		this.distributeJumperLengthS1LabelEast.setEnabled(false);
		this.distributeJumperLengthS2LabelEast.setEnabled(false);
		this.distributeJumperLengthS3LabelEast.setEnabled(false);
		this.distributeJumperLengthS4LabelEast.setEnabled(false);
		this.feederTypeComboEast.setEnabled(false);
		this.feederLengthTextFieldEast.setEnabled(false);
		this.groundsNoTextFieldEast.setEnabled(false);
		this.commonJumperAtBtsLengthTextFieldEast.setEnabled(false);
		this.commonJumperBeforeMhaLengthTextFieldEast.setEnabled(false);
		this.commonJumperAfterMhaLengthTextFieldEast.setEnabled(false);
		this.commonJumperPerSectorTextFieldEast.setEnabled(false);
		this.feederLengthLabelEast.setEnabled(false);
		this.groundsNoLabelEast.setEnabled(false);
		this.commonJumperAtBtsLengthLabelEast.setEnabled(false);
		this.commonJumperBeforeMhaLengthLabelEast.setEnabled(false);
		this.commonJumperAfterMhaLengthLabelEast.setEnabled(false);
		this.commonJumperPerSectorLabelEast.setEnabled(false);
		this.feederTypeLabelEast.setEnabled(false);
		this.btsCabinetLabelEast.setEnabled(false);
		this.btsCabinetComboEast.setEnabled(false);
		this.powSupplyComboEast.setEnabled(false);
		this.powSupplyLabelEast.setEnabled(false);

		this.bottomPanel.setLayout(new BorderLayout());
		this.bottomPanel.add(BorderLayout.NORTH, this.northPanel);
		this.bottomPanel.add(BorderLayout.CENTER, this.centerPanel);
		this.bottomPanel.add(BorderLayout.SOUTH, this.southPanel);

		/*
		 * Create scrolls and attach it to frame. Frame has preferred size.
		 */
		JScrollPane frameScrolls = new JScrollPane(bottomPanel);
		frameScrolls.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frameScrolls.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.setPreferredSize(new Dimension(1170, 730));
		this.add(this.mainPanel);
		this.setContentPane(frameScrolls);

		// Center panel
		this.centerPanel.add(this.westPanel);
		this.centerPanel.add(this.eastPanel);

		// West panel
		Border westBorder = BorderFactory.createTitledBorder("UMTS");
		this.westPanel.setBorder(westBorder);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipady = 91; // Because there is no transmission part in UMTS, here
						// we add pixels to keep in line arrangement.
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(5, 5, 5, 5); // Space around every element in
												// panel.
		this.westPanel.add(this.westNorthPanel, gbc);

		// East panel
		Border eastBorder = BorderFactory.createTitledBorder("LTE");
		this.eastPanel.setBorder(eastBorder);
		gbcEast.gridx = 0; // With this we define column and
		gbcEast.gridy = 0; // row in which we want element.
		gbcEast.anchor = GridBagConstraints.WEST;
		gbcEast.insets = new Insets(5, 5, 5, 5);
		this.eastPanel.add(this.eastNorthPanel, gbcEast);
		gbcEast.gridy = 1;
		this.eastPanel.add(this.eastCenterPanel, gbcEast);

		// UMTS/LTE check boxes
		this.northPanel.add(this.umts);
		this.northPanel.add(this.lte);
		ButtonGroup umtsLteGroup = new ButtonGroup();
		umtsLteGroup.add(umts);
		umtsLteGroup.add(lte);
		Border umtsLteBorder = BorderFactory.createTitledBorder("Instaled technology:");
		this.northPanel.setBorder(umtsLteBorder);

		// Input/Output bullets group.
		ButtonGroup inOutGroup = new ButtonGroup();
		inOutGroup.add(this.indoorBTS);
		inOutGroup.add(this.outdoorBTS);
		JPanel inOutPanel = new JPanel(new GridLayout(2, 1));
		inOutPanel.add(this.indoorBTS);
		inOutPanel.add(this.outdoorBTS);
		ListenForInstalationType lForInstalationType = new ListenForInstalationType();
		this.indoorBTS.addItemListener(lForInstalationType);
		this.outdoorBTS.addItemListener(lForInstalationType);
		Border inOutBorder = BorderFactory.createTitledBorder("Instalation type:");
		inOutPanel.setBorder(inOutBorder);
		this.westNorthPanel.add(inOutPanel);
		// Input/Output bullets group, east.
		ButtonGroup inOutGroupEast = new ButtonGroup();
		inOutGroupEast.add(this.indoorBTSEast);
		inOutGroupEast.add(this.outdoorBTSEast);
		JPanel inOutPanelEast = new JPanel(new GridLayout(2, 1));
		inOutPanelEast.add(this.indoorBTSEast);
		inOutPanelEast.add(this.outdoorBTSEast);
		ListenForInstalationTypeEast lForInstalationTypeEast = new ListenForInstalationTypeEast();
		this.indoorBTSEast.addItemListener(lForInstalationTypeEast);
		this.outdoorBTSEast.addItemListener(lForInstalationTypeEast);
		Border inOutBorderEast = BorderFactory.createTitledBorder("Instalation type:");
		inOutPanelEast.setBorder(inOutBorderEast);
		this.eastNorthPanel.add(inOutPanelEast);

		// Common/Distribute bullets group.
		ButtonGroup archTypeGroup = new ButtonGroup();
		archTypeGroup.add(this.commonBTS);
		archTypeGroup.add(this.distributeBTS);
		JPanel archTypePanel = new JPanel(new GridLayout(2, 1));
		archTypePanel.add(this.commonBTS);
		archTypePanel.add(this.distributeBTS);
		ListenForCommonButton lForCommonButton = new ListenForCommonButton();
		this.commonBTS.addItemListener(lForCommonButton);
		ListneForDistributeButton lForDistributeButton = new ListneForDistributeButton();
		this.distributeBTS.addItemListener(lForDistributeButton);
		ListenForArchType lForArchType = new ListenForArchType();
		this.commonBTS.addItemListener(lForArchType);
		this.distributeBTS.addItemListener(lForArchType);
		Border archBorder = BorderFactory.createTitledBorder("Architecture type:");
		archTypePanel.setBorder(archBorder);
		this.westNorthPanel.add(archTypePanel);
		// Common/Distribute bullets group, east.
		ButtonGroup archTypeGroupEast = new ButtonGroup();
		archTypeGroupEast.add(this.commonBTSEast);
		archTypeGroupEast.add(this.distributeBTSEast);
		JPanel archTypePanelEast = new JPanel(new GridLayout(2, 1));
		archTypePanelEast.add(this.commonBTSEast);
		archTypePanelEast.add(this.distributeBTSEast);
		ListenForCommonButtonEast lForCommonButtonEast = new ListenForCommonButtonEast();
		this.commonBTSEast.addItemListener(lForCommonButtonEast);
		ListneForDistributeButtonEast lForDistributeButtonEast = new ListneForDistributeButtonEast();
		this.distributeBTSEast.addItemListener(lForDistributeButtonEast);
		ListenForArchTypeEast lForArchTypeEast = new ListenForArchTypeEast();
		this.commonBTSEast.addItemListener(lForArchTypeEast);
		this.distributeBTSEast.addItemListener(lForArchTypeEast);
		Border archBorderEast = BorderFactory.createTitledBorder("Architecture type:");
		archTypePanelEast.setBorder(archBorderEast);
		this.eastNorthPanel.add(archTypePanelEast);

		// Wall/Pole/Stack/Cabinet bullets group.
		ButtonGroup assembleTypeGroup = new ButtonGroup();
		assembleTypeGroup.add(this.wallAssembled);
		assembleTypeGroup.add(this.poleAssembled);
		assembleTypeGroup.add(this.stackAssembled);
		assembleTypeGroup.add(this.cabinetAssembled);
		ListenForAssemblyType lForAssemblyType = new ListenForAssemblyType();
		this.wallAssembled.addItemListener(lForAssemblyType);
		this.poleAssembled.addItemListener(lForAssemblyType);
		this.stackAssembled.addItemListener(lForAssemblyType);
		this.cabinetAssembled.addItemListener(lForAssemblyType);
		JPanel assembleTypePanel = new JPanel(new GridLayout(2, 2));
		assembleTypePanel.add(this.wallAssembled);
		assembleTypePanel.add(this.poleAssembled);
		assembleTypePanel.add(this.stackAssembled);
		assembleTypePanel.add(this.cabinetAssembled);
		Border assembleTypeBorder = BorderFactory.createTitledBorder("Sys mod. assemble:");
		assembleTypePanel.setBorder(assembleTypeBorder);
		this.westNorthPanel.add(assembleTypePanel);
		// Wall/Pole/Stack/Cabinet bullets group, east.
		ButtonGroup assembleTypeGroupEast = new ButtonGroup();
		assembleTypeGroupEast.add(this.wallAssembledEast);
		assembleTypeGroupEast.add(this.poleAssembledEast);
		assembleTypeGroupEast.add(this.stackAssembledEast);
		assembleTypeGroupEast.add(this.cabinetAssembledEast);
		ListenForAssemblyTypeEast lForAssemblyTypeEast = new ListenForAssemblyTypeEast();
		this.wallAssembledEast.addItemListener(lForAssemblyTypeEast);
		this.poleAssembledEast.addItemListener(lForAssemblyTypeEast);
		this.stackAssembledEast.addItemListener(lForAssemblyTypeEast);
		this.cabinetAssembledEast.addItemListener(lForAssemblyTypeEast);
		JPanel assembleTypePanelEast = new JPanel(new GridLayout(2, 2));
		assembleTypePanelEast.add(this.wallAssembledEast);
		assembleTypePanelEast.add(this.poleAssembledEast);
		assembleTypePanelEast.add(this.stackAssembledEast);
		assembleTypePanelEast.add(this.cabinetAssembledEast);
		Border assembleTypeBorderEast = BorderFactory.createTitledBorder("Sys mod. assemble:");
		assembleTypePanelEast.setBorder(assembleTypeBorderEast);
		this.eastNorthPanel.add(assembleTypePanelEast);

		// Wall/Pole/Stack/Cabinet RF bullets group.
		ButtonGroup assembleRFTypeGroup = new ButtonGroup();
		assembleRFTypeGroup.add(this.wallAssembledRF);
		assembleRFTypeGroup.add(this.poleAssembledRF);
		assembleRFTypeGroup.add(this.stackAssembledRF);
		assembleRFTypeGroup.add(this.cabinetAssembledRF);
		ListenForAssemblyRFType lForAssemblyRFType = new ListenForAssemblyRFType();
		this.wallAssembledRF.addItemListener(lForAssemblyRFType);
		this.poleAssembledRF.addItemListener(lForAssemblyRFType);
		this.stackAssembledRF.addItemListener(lForAssemblyRFType);
		this.cabinetAssembledRF.addItemListener(lForAssemblyRFType);
		JPanel assembleRFTypePanel = new JPanel(new GridLayout(2, 2));
		assembleRFTypePanel.add(this.wallAssembledRF);
		assembleRFTypePanel.add(this.poleAssembledRF);
		assembleRFTypePanel.add(this.stackAssembledRF);
		assembleRFTypePanel.add(this.cabinetAssembledRF);
		Border assembleRFTypeBorder = BorderFactory.createTitledBorder("RF mod. assemble:");
		assembleRFTypePanel.setBorder(assembleRFTypeBorder);
		this.westNorthPanel.add(assembleRFTypePanel);
		// Wall/Pole/Stack/Cabinet RF bullets group, east.
		ButtonGroup assembleRFTypeGroupEast = new ButtonGroup();
		assembleRFTypeGroupEast.add(this.wallAssembledRFEast);
		assembleRFTypeGroupEast.add(this.poleAssembledRFEast);
		assembleRFTypeGroupEast.add(this.stackAssembledRFEast);
		assembleRFTypeGroupEast.add(this.cabinetAssembledRFEast);
		ListenForAssemblyRFTypeEast lForAssemblyRFTypeEast = new ListenForAssemblyRFTypeEast();
		this.wallAssembledRFEast.addItemListener(lForAssemblyRFTypeEast);
		this.poleAssembledRFEast.addItemListener(lForAssemblyRFTypeEast);
		this.stackAssembledRFEast.addItemListener(lForAssemblyRFTypeEast);
		this.cabinetAssembledRFEast.addItemListener(lForAssemblyRFTypeEast);
		JPanel assembleRFTypePanelEast = new JPanel(new GridLayout(2, 2));
		assembleRFTypePanelEast.add(this.wallAssembledRFEast);
		assembleRFTypePanelEast.add(this.poleAssembledRFEast);
		assembleRFTypePanelEast.add(this.stackAssembledRFEast);
		assembleRFTypePanelEast.add(this.cabinetAssembledRFEast);
		Border assembleRFTypeBorderEast = BorderFactory.createTitledBorder("RF mod. assemble:");
		assembleRFTypePanelEast.setBorder(assembleRFTypeBorderEast);
		this.eastNorthPanel.add(assembleRFTypePanelEast);

		// Transmission 2G/3G bullet group.
		ButtonGroup transmissionGroup = new ButtonGroup();
		transmissionGroup.add(this.transmission2G);
		transmissionGroup.add(this.transmission3G);
		JPanel transmissionPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gridConstraints = new GridBagConstraints();
		transmissionPanel.add(this.transmission2G, gridConstraints);
		gridConstraints.gridy = 2;
		transmissionPanel.add(this.transmission3G, gridConstraints);
		Border transmissionBorder = BorderFactory.createTitledBorder("Transmission goes:");
		transmissionPanel.setBorder(transmissionBorder);
		Dimension transmissionDim = new Dimension(130, 71);
		transmissionPanel.setPreferredSize(transmissionDim); // Because
																// "Transmission
																// goes:" is
																// longer then
																// bullet
																// labels, then
																// it would be
																// cut off, so
																// we create
																// Dimension and
																// set it as
																// preferred.
		transmissionPanel.revalidate(); // We need to validate preferred size,
										// so
										// this is mandatory.
		this.eastCenterPanel.add(transmissionPanel);
		ListenForTransmissionGoes lForTransmissionGoes = new ListenForTransmissionGoes();
		this.transmission2G.addItemListener(lForTransmissionGoes);
		this.transmission3G.addItemListener(lForTransmissionGoes);

		// Distribute cable length.
		JPanel distributeCablePanel = new JPanel(new GridLayout(5, 2));
		distributeCablePanel.add(this.fiberLengthLabel);
		distributeCablePanel.add(this.fiberLengthTextField);
		distributeCablePanel.add(this.distributeJumperLengthS1Label);
		distributeCablePanel.add(this.distributeJumperLengthS1TextField);
		distributeCablePanel.add(this.distributeJumperLengthS2Label);
		distributeCablePanel.add(this.distributeJumperLengthS2TextField);
		distributeCablePanel.add(this.distributeJumperLengthS3Label);
		distributeCablePanel.add(this.distributeJumperLengthS3TextField);
		distributeCablePanel.add(this.distributeJumperLengthS4Label);
		distributeCablePanel.add(this.distributeJumperLengthS4TextField);
		Border distributeCableBorder = BorderFactory.createTitledBorder("Distribute architecture");
		distributeCablePanel.setBorder(distributeCableBorder);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.ipady = 0;
		this.westPanel.add(distributeCablePanel, gbc);
		// Distribute cable length, east.
		JPanel distributeCablePanelEast = new JPanel(new GridLayout(5, 2));
		distributeCablePanelEast.add(this.fiberLengthLabelEast);
		distributeCablePanelEast.add(this.fiberLengthTextFieldEast);
		distributeCablePanelEast.add(this.distributeJumperLengthS1LabelEast);
		distributeCablePanelEast.add(this.distributeJumperLengthS1TextFieldEast);
		distributeCablePanelEast.add(this.distributeJumperLengthS2LabelEast);
		distributeCablePanelEast.add(this.distributeJumperLengthS2TextFieldEast);
		distributeCablePanelEast.add(this.distributeJumperLengthS3LabelEast);
		distributeCablePanelEast.add(this.distributeJumperLengthS3TextFieldEast);
		distributeCablePanelEast.add(this.distributeJumperLengthS4LabelEast);
		distributeCablePanelEast.add(this.distributeJumperLengthS4TextFieldEast);
		Border distributeCableBorderEast = BorderFactory.createTitledBorder("Distribute architecture");
		distributeCablePanelEast.setBorder(distributeCableBorderEast);
		gbcEast.gridx = 0;
		gbcEast.gridy = 2;
		this.eastPanel.add(distributeCablePanelEast, gbcEast);

		// Common cables length.
		JPanel commonCablePanel = new JPanel(new GridLayout(7, 2));
		commonCablePanel.add(this.feederTypeLabel);
		commonCablePanel.add(this.feederTypeCombo);
		commonCablePanel.add(this.feederLengthLabel);
		commonCablePanel.add(this.feederLengthTextField);
		commonCablePanel.add(this.commonJumperAtBtsLengthLabel);
		commonCablePanel.add(this.commonJumperAtBtsLengthTextField);
		commonCablePanel.add(this.commonJumperBeforeMhaLengthLabel);
		commonCablePanel.add(this.commonJumperBeforeMhaLengthTextField);
		commonCablePanel.add(this.commonJumperAfterMhaLengthLabel);
		commonCablePanel.add(this.commonJumperAfterMhaLengthTextField);
		commonCablePanel.add(this.commonJumperPerSectorLabel);
		commonCablePanel.add(this.commonJumperPerSectorTextField);
		commonCablePanel.add(this.groundsNoLabel);
		commonCablePanel.add(this.groundsNoTextField);
		Border commonCableBorder = BorderFactory.createTitledBorder("Common architecture");
		commonCablePanel.setBorder(commonCableBorder);
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.westPanel.add(commonCablePanel, gbc);
		// Common cables length, east.
		JPanel commonCablePanelEast = new JPanel(new GridLayout(7, 2));
		commonCablePanelEast.add(this.feederTypeLabelEast);
		commonCablePanelEast.add(this.feederTypeComboEast);
		commonCablePanelEast.add(this.feederLengthLabelEast);
		commonCablePanelEast.add(this.feederLengthTextFieldEast);
		commonCablePanelEast.add(this.commonJumperAtBtsLengthLabelEast);
		commonCablePanelEast.add(this.commonJumperAtBtsLengthTextFieldEast);
		commonCablePanelEast.add(this.commonJumperBeforeMhaLengthLabelEast);
		commonCablePanelEast.add(this.commonJumperBeforeMhaLengthTextFieldEast);
		commonCablePanelEast.add(this.commonJumperAfterMhaLengthLabelEast);
		commonCablePanelEast.add(this.commonJumperAfterMhaLengthTextFieldEast);
		commonCablePanelEast.add(this.commonJumperPerSectorLabelEast);
		commonCablePanelEast.add(this.commonJumperPerSectorTextFieldEast);
		commonCablePanelEast.add(this.groundsNoLabelEast);
		commonCablePanelEast.add(this.groundsNoTextFieldEast);
		Border commonCableBorderEast = BorderFactory.createTitledBorder("Common architecture");
		commonCablePanelEast.setBorder(commonCableBorderEast);
		gbcEast.gridx = 0;
		gbcEast.gridy = 3;
		this.eastPanel.add(commonCablePanelEast, gbcEast);

		// BTS cabinet type
		JPanel btsCabinetPanel = new JPanel();
		btsCabinetPanel.add(this.btsCabinetLabel);
		btsCabinetPanel.add(this.btsCabinetCombo);
		ListenForBtsCabinet lForBtsCabinet = new ListenForBtsCabinet();
		this.btsCabinetCombo.addItemListener(lForBtsCabinet);
		JPanel btsCabinetPanelEast = new JPanel();
		btsCabinetPanelEast.add(this.btsCabinetLabelEast);
		btsCabinetPanelEast.add(this.btsCabinetComboEast);
		ListenForBtsCabinetEast lForBtsCabinetEast = new ListenForBtsCabinetEast();
		this.btsCabinetComboEast.addItemListener(lForBtsCabinetEast);
		// Power supply type
		JPanel powSupplyPanel = new JPanel();
		powSupplyPanel.add(this.powSupplyLabel);
		powSupplyPanel.add(this.powSupplyCombo);
		ListenForPowerSupply lForPowerSupply = new ListenForPowerSupply();
		this.powSupplyCombo.addItemListener(lForPowerSupply);
		JPanel powSupplyPanelEast = new JPanel();
		powSupplyPanelEast.add(this.powSupplyLabelEast);
		powSupplyPanelEast.add(this.powSupplyComboEast);
		ListenForPowerSupplyEast lForPowerSupplyEast = new ListenForPowerSupplyEast();
		this.powSupplyComboEast.addItemListener(lForPowerSupplyEast);
		// BTS cabinet / power supply panel
		JPanel btsAndCabinetComboPanel = new JPanel();
		btsAndCabinetComboPanel.add(btsCabinetPanel);
		btsAndCabinetComboPanel.add(powSupplyPanel);
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.westPanel.add(btsAndCabinetComboPanel, gbc);
		JPanel btsAndCabinetComboPanelEast = new JPanel();
		btsAndCabinetComboPanelEast.add(btsCabinetPanelEast);
		btsAndCabinetComboPanelEast.add(powSupplyPanelEast);
		gbcEast.gridx = 0;
		gbcEast.gridy = 4;
		this.eastPanel.add(btsAndCabinetComboPanelEast, gbcEast);

		this.pack(); // After adding all elements to JFrame we need to pack it
						// so that all layers take their action.
		this.windowMandatoryMethods();
	}

	public void windowMandatoryMethods() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Report Generator");
	}

	/*
	 * When we (un)check UMTS box, UMTS part is (disabled)enabled. Also when is check it search for site code.
	 */
	public class ListenForUmts implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			InputFiles iFilesUmts = FrontWindow.this.iFiles;
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (iFilesUmts.getSiteCode3gStr().equals("xxxyy")) {
					iFilesUmts.findSiteCodeFromCommissioningReport('U');
					iFilesUmts.setSiteCode('U');
					iFilesUmts.sortOutInputFilesToAppropriateVariables();
				}
				if (iFilesUmts.getSiteCode3gStr() != null) {
					FrontWindow.this.westPanel.setEnabled(true);
					FrontWindow.this.westNorthPanel.setEnabled(true);
					FrontWindow.this.indoorBTS.setEnabled(true);
					FrontWindow.this.outdoorBTS.setEnabled(true);
					FrontWindow.this.commonBTS.setEnabled(true);
					FrontWindow.this.distributeBTS.setEnabled(true);
					FrontWindow.this.stackAssembled.setEnabled(true);
					FrontWindow.this.wallAssembled.setEnabled(true);
					FrontWindow.this.poleAssembled.setEnabled(true);
					FrontWindow.this.cabinetAssembled.setEnabled(true);
					FrontWindow.this.stackAssembledRF.setEnabled(true);
					FrontWindow.this.wallAssembledRF.setEnabled(true);
					FrontWindow.this.poleAssembledRF.setEnabled(true);
					FrontWindow.this.cabinetAssembledRF.setEnabled(true);
					if (FrontWindow.this.distributeBTS.isSelected()) { // Depending
																		// on
																		// architecture
																		// type
																		// some
																		// part
																		// of
																		// window
																		// stay
																		// gray.
						FrontWindow.this.fiberLengthLabel.setEnabled(true);
						FrontWindow.this.fiberLengthTextField.setEnabled(true);
						FrontWindow.this.distributeJumperLengthS1TextField.setEnabled(true);
						FrontWindow.this.distributeJumperLengthS2TextField.setEnabled(true);
						FrontWindow.this.distributeJumperLengthS3TextField.setEnabled(true);
						FrontWindow.this.distributeJumperLengthS4TextField.setEnabled(true);
						FrontWindow.this.distributeJumperLengthS1Label.setEnabled(true);
						FrontWindow.this.distributeJumperLengthS2Label.setEnabled(true);
						FrontWindow.this.distributeJumperLengthS3Label.setEnabled(true);
						FrontWindow.this.distributeJumperLengthS4Label.setEnabled(true);
					}
					if (FrontWindow.this.commonBTS.isSelected()) {
						FrontWindow.this.feederTypeCombo.setEnabled(true);
						FrontWindow.this.feederLengthTextField.setEnabled(true);
						FrontWindow.this.groundsNoTextField.setEnabled(true);
						FrontWindow.this.commonJumperAtBtsLengthTextField.setEnabled(true);
						FrontWindow.this.commonJumperBeforeMhaLengthTextField.setEnabled(true);
						FrontWindow.this.commonJumperAfterMhaLengthTextField.setEnabled(true);
						FrontWindow.this.commonJumperPerSectorTextField.setEnabled(true);
						FrontWindow.this.feederLengthLabel.setEnabled(true);
						FrontWindow.this.groundsNoLabel.setEnabled(true);
						FrontWindow.this.commonJumperAtBtsLengthLabel.setEnabled(true);
						FrontWindow.this.commonJumperBeforeMhaLengthLabel.setEnabled(true);
						FrontWindow.this.commonJumperAfterMhaLengthLabel.setEnabled(true);
						FrontWindow.this.commonJumperPerSectorLabel.setEnabled(true);
						FrontWindow.this.feederTypeLabel.setEnabled(true);
					}
					FrontWindow.this.btsCabinetLabel.setEnabled(true);
					FrontWindow.this.btsCabinetCombo.setEnabled(true);
					FrontWindow.this.powSupplyCombo.setEnabled(true);
					FrontWindow.this.powSupplyLabel.setEnabled(true);
				}
			} else {
				FrontWindow.this.westPanel.setEnabled(false);
				FrontWindow.this.westNorthPanel.setEnabled(false);
				FrontWindow.this.indoorBTS.setEnabled(false);
				FrontWindow.this.outdoorBTS.setEnabled(false);
				FrontWindow.this.commonBTS.setEnabled(false);
				FrontWindow.this.distributeBTS.setEnabled(false);
				FrontWindow.this.stackAssembled.setEnabled(false);
				FrontWindow.this.wallAssembled.setEnabled(false);
				FrontWindow.this.poleAssembled.setEnabled(false);
				FrontWindow.this.cabinetAssembled.setEnabled(false);
				FrontWindow.this.stackAssembledRF.setEnabled(false);
				FrontWindow.this.wallAssembledRF.setEnabled(false);
				FrontWindow.this.poleAssembledRF.setEnabled(false);
				FrontWindow.this.cabinetAssembledRF.setEnabled(false);
				FrontWindow.this.fiberLengthLabel.setEnabled(false);
				FrontWindow.this.fiberLengthTextField.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS1TextField.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS2TextField.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS3TextField.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS4TextField.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS1Label.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS2Label.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS3Label.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS4Label.setEnabled(false);
				FrontWindow.this.feederTypeCombo.setEnabled(false);
				FrontWindow.this.feederLengthTextField.setEnabled(false);
				FrontWindow.this.groundsNoTextField.setEnabled(false);
				FrontWindow.this.commonJumperAtBtsLengthTextField.setEnabled(false);
				FrontWindow.this.commonJumperBeforeMhaLengthTextField.setEnabled(false);
				FrontWindow.this.commonJumperAfterMhaLengthTextField.setEnabled(false);
				FrontWindow.this.commonJumperPerSectorTextField.setEnabled(false);
				FrontWindow.this.feederLengthLabel.setEnabled(false);
				FrontWindow.this.groundsNoLabel.setEnabled(false);
				FrontWindow.this.commonJumperAtBtsLengthLabel.setEnabled(false);
				FrontWindow.this.commonJumperBeforeMhaLengthLabel.setEnabled(false);
				FrontWindow.this.commonJumperAfterMhaLengthLabel.setEnabled(false);
				FrontWindow.this.commonJumperPerSectorLabel.setEnabled(false);
				FrontWindow.this.feederTypeLabel.setEnabled(false);
				FrontWindow.this.btsCabinetLabel.setEnabled(false);
				FrontWindow.this.btsCabinetCombo.setEnabled(false);
				FrontWindow.this.powSupplyCombo.setEnabled(false);
				FrontWindow.this.powSupplyLabel.setEnabled(false);
			}
		}
	}

	// Same as for UMTS check box.
	public class ListenForLte implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			InputFiles iFilesLte = FrontWindow.this.iFiles;
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (iFilesLte.getSiteCode4gStr().equals("xxxyy")) {
					iFilesLte.findSiteCodeFromCommissioningReport('L');
					iFilesLte.setSiteCode('L');
					iFilesLte.sortOutInputFilesToAppropriateVariables();
				}
				if (iFilesLte.getSiteCode4gStr() != null) {
					FrontWindow.this.eastPanel.setEnabled(true);
					FrontWindow.this.eastNorthPanel.setEnabled(true);
					FrontWindow.this.eastCenterPanel.setEnabled(true);
					FrontWindow.this.indoorBTSEast.setEnabled(true);
					FrontWindow.this.outdoorBTSEast.setEnabled(true);
					FrontWindow.this.commonBTSEast.setEnabled(true);
					FrontWindow.this.distributeBTSEast.setEnabled(true);
					FrontWindow.this.stackAssembledEast.setEnabled(true);
					FrontWindow.this.wallAssembledEast.setEnabled(true);
					FrontWindow.this.poleAssembledEast.setEnabled(true);
					FrontWindow.this.cabinetAssembledEast.setEnabled(true);
					FrontWindow.this.stackAssembledRFEast.setEnabled(true);
					FrontWindow.this.wallAssembledRFEast.setEnabled(true);
					FrontWindow.this.poleAssembledRFEast.setEnabled(true);
					FrontWindow.this.cabinetAssembledRFEast.setEnabled(true);
					FrontWindow.this.transmission2G.setEnabled(true);
					FrontWindow.this.transmission3G.setEnabled(true);
					if (FrontWindow.this.distributeBTSEast.isSelected()) {
						FrontWindow.this.fiberLengthLabelEast.setEnabled(true);
						FrontWindow.this.fiberLengthTextFieldEast.setEnabled(true);
						FrontWindow.this.distributeJumperLengthS1TextFieldEast.setEnabled(true);
						FrontWindow.this.distributeJumperLengthS2TextFieldEast.setEnabled(true);
						FrontWindow.this.distributeJumperLengthS3TextFieldEast.setEnabled(true);
						FrontWindow.this.distributeJumperLengthS4TextFieldEast.setEnabled(true);
						FrontWindow.this.fiberLengthLabelEast.setEnabled(true);
						FrontWindow.this.distributeJumperLengthS1LabelEast.setEnabled(true);
						FrontWindow.this.distributeJumperLengthS2LabelEast.setEnabled(true);
						FrontWindow.this.distributeJumperLengthS3LabelEast.setEnabled(true);
						FrontWindow.this.distributeJumperLengthS4LabelEast.setEnabled(true);
					}
					if (FrontWindow.this.commonBTSEast.isSelected()) {
						FrontWindow.this.feederTypeComboEast.setEnabled(true);
						FrontWindow.this.feederLengthTextFieldEast.setEnabled(true);
						FrontWindow.this.groundsNoTextFieldEast.setEnabled(true);
						FrontWindow.this.commonJumperAtBtsLengthTextFieldEast.setEnabled(true);
						FrontWindow.this.commonJumperBeforeMhaLengthTextFieldEast.setEnabled(true);
						FrontWindow.this.commonJumperAfterMhaLengthTextFieldEast.setEnabled(true);
						FrontWindow.this.commonJumperPerSectorTextFieldEast.setEnabled(true);
						FrontWindow.this.feederLengthLabelEast.setEnabled(true);
						FrontWindow.this.groundsNoLabelEast.setEnabled(true);
						FrontWindow.this.commonJumperAtBtsLengthLabelEast.setEnabled(true);
						FrontWindow.this.commonJumperBeforeMhaLengthLabelEast.setEnabled(true);
						FrontWindow.this.commonJumperAfterMhaLengthLabelEast.setEnabled(true);
						FrontWindow.this.commonJumperPerSectorLabelEast.setEnabled(true);
						FrontWindow.this.feederTypeLabelEast.setEnabled(true);
					}
					FrontWindow.this.btsCabinetLabelEast.setEnabled(true);
					FrontWindow.this.btsCabinetComboEast.setEnabled(true);
					FrontWindow.this.powSupplyComboEast.setEnabled(true);
					FrontWindow.this.powSupplyLabelEast.setEnabled(true);
				}
			} else {
				FrontWindow.this.eastPanel.setEnabled(false);
				FrontWindow.this.eastNorthPanel.setEnabled(false);
				FrontWindow.this.eastCenterPanel.setEnabled(false);
				FrontWindow.this.indoorBTSEast.setEnabled(false);
				FrontWindow.this.outdoorBTSEast.setEnabled(false);
				FrontWindow.this.commonBTSEast.setEnabled(false);
				FrontWindow.this.distributeBTSEast.setEnabled(false);
				FrontWindow.this.stackAssembledEast.setEnabled(false);
				FrontWindow.this.wallAssembledEast.setEnabled(false);
				FrontWindow.this.poleAssembledEast.setEnabled(false);
				FrontWindow.this.cabinetAssembledEast.setEnabled(false);
				FrontWindow.this.stackAssembledRFEast.setEnabled(false);
				FrontWindow.this.wallAssembledRFEast.setEnabled(false);
				FrontWindow.this.poleAssembledRFEast.setEnabled(false);
				FrontWindow.this.cabinetAssembledRFEast.setEnabled(false);
				FrontWindow.this.transmission2G.setEnabled(false);
				FrontWindow.this.transmission3G.setEnabled(false);
				FrontWindow.this.fiberLengthLabelEast.setEnabled(false);
				FrontWindow.this.fiberLengthTextFieldEast.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS1TextFieldEast.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS2TextFieldEast.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS3TextFieldEast.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS4TextFieldEast.setEnabled(false);
				FrontWindow.this.fiberLengthLabelEast.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS1LabelEast.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS2LabelEast.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS3LabelEast.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS4LabelEast.setEnabled(false);
				FrontWindow.this.feederTypeComboEast.setEnabled(false);
				FrontWindow.this.feederLengthTextFieldEast.setEnabled(false);
				FrontWindow.this.groundsNoTextFieldEast.setEnabled(false);
				FrontWindow.this.commonJumperAtBtsLengthTextFieldEast.setEnabled(false);
				FrontWindow.this.commonJumperBeforeMhaLengthTextFieldEast.setEnabled(false);
				FrontWindow.this.commonJumperAfterMhaLengthTextFieldEast.setEnabled(false);
				FrontWindow.this.commonJumperPerSectorTextFieldEast.setEnabled(false);
				FrontWindow.this.feederLengthLabelEast.setEnabled(false);
				FrontWindow.this.groundsNoLabelEast.setEnabled(false);
				FrontWindow.this.commonJumperAtBtsLengthLabelEast.setEnabled(false);
				FrontWindow.this.commonJumperBeforeMhaLengthLabelEast.setEnabled(false);
				FrontWindow.this.commonJumperAfterMhaLengthLabelEast.setEnabled(false);
				FrontWindow.this.commonJumperPerSectorLabelEast.setEnabled(false);
				FrontWindow.this.feederTypeLabelEast.setEnabled(false);
				FrontWindow.this.btsCabinetLabelEast.setEnabled(false);
				FrontWindow.this.btsCabinetComboEast.setEnabled(false);
				FrontWindow.this.powSupplyComboEast.setEnabled(false);
				FrontWindow.this.powSupplyLabelEast.setEnabled(false);
			}
		}
	}

	/*
	 * Here we start with gathering of informations necessary for report and then populate report.
	 */
	public class ListenForGenReportButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == FrontWindow.this.genReportButton) {
				if (FrontWindow.this.umts.isSelected()) {
					if (FrontWindow.this.distributeBTS.isSelected()) {
						setFeederSize("FIBER");
						setFiberLength(FrontWindow.this.fiberLengthTextField.getText());
						setJumperLengthS1(FrontWindow.this.distributeJumperLengthS1TextField.getText());
						setJumperLengthS2(FrontWindow.this.distributeJumperLengthS2TextField.getText());
						setJumperLengthS3(FrontWindow.this.distributeJumperLengthS3TextField.getText());
						setJumperLengthS4(FrontWindow.this.distributeJumperLengthS4TextField.getText());
					}
					if (FrontWindow.this.commonBTS.isSelected()) {
						setFeederSize((String) FrontWindow.this.feederTypeCombo.getSelectedItem());
						setFeederLength(FrontWindow.this.feederLengthTextField.getText());
						setJumperAtBtsLength(FrontWindow.this.commonJumperAtBtsLengthTextField.getText());
						setJumperBeforeMhaLength(FrontWindow.this.commonJumperBeforeMhaLengthTextField.getText());
						setJumperAfterMhaLength(FrontWindow.this.commonJumperAfterMhaLengthTextField.getText());
						setJumpersPerSector(FrontWindow.this.commonJumperPerSectorTextField.getText());
						setGroundsNo(FrontWindow.this.groundsNoTextField.getText());
					}
					SiteUmts siteUmts = new SiteUmts();
					siteUmts.setSiteCode(getIFiles());
					siteUmts.setSiteName(getIFiles());
					siteUmts.setDate(getIFiles());
					siteUmts.antenaChanged(getIFiles());
					siteUmts.setNumOfSectors(siteUmts.getUmts21Changed());
					siteUmts.setMhaType(getIFiles());
					siteUmts.setRttaType(getIFiles());
					if (FrontWindow.this.distributeBTS.isSelected()) {
						siteUmts.setFeederSize(FrontWindow.this);
						siteUmts.setFiberLength(FrontWindow.this);
						siteUmts.setDistributeJumperLengthS1(FrontWindow.this);
						siteUmts.setDistributeJumperLengthS2(FrontWindow.this);
						siteUmts.setDistributeJumperLengthS3(FrontWindow.this);
						siteUmts.setDistributeJumperLengthS4(FrontWindow.this);
					}
					if (FrontWindow.this.commonBTS.isSelected()) {
						siteUmts.setFeederSize(FrontWindow.this);
						siteUmts.setFeederLength(FrontWindow.this);
						siteUmts.setJumperAtBtsLen(FrontWindow.this);
						siteUmts.setJumpersBeforeMhaLen(FrontWindow.this);
						siteUmts.setJumpersAfterMhaLen(FrontWindow.this);
						siteUmts.setJumperPerSectorNum(FrontWindow.this);
						siteUmts.setGroungNum(FrontWindow.this);
					}
					if (FrontWindow.this.indoorBTS.isSelected() | FrontWindow.this.outdoorBTS.isSelected()) {
						siteUmts.setInstalationType(FrontWindow.this);
					}
					if (FrontWindow.this.distributeBTS.isSelected() | FrontWindow.this.commonBTS.isSelected()) {
						siteUmts.setArchitectureType(FrontWindow.this);
					}
					siteUmts.setBtsCabinetType(FrontWindow.this);
					siteUmts.setSysModule1Type(getIFiles());
					siteUmts.setSysModule1Loc(FrontWindow.this);
					siteUmts.setTransModuleType(getIFiles());
					siteUmts.setE1LinesNo(getIFiles());
					siteUmts.setGbEthElectLinesNo(getIFiles());
					siteUmts.setGbEthOptLinesNo(getIFiles());
					siteUmts.setRfModulesType(getIFiles());
					siteUmts.setRfModuleLoc(FrontWindow.this);
					siteUmts.setPowSupplyType(FrontWindow.this);
					siteUmts.setPowerSypplyParameters();
					siteUmts.setSwVersion(getIFiles());
					siteUmts.setIpAdresses(getIFiles());
					siteUmts.setCellId(getIFiles());
					siteUmts.setUarfcns(getIFiles());
					siteUmts.setSiteConfig();
					siteUmts.setExtAl(getIFiles());
					siteUmts.setCrFile(getIFiles());
					siteUmts.setCommFile(getIFiles());
					siteUmts.setSiteInfo(getIFiles());
					OutputFiles report = new OutputFiles();
					report.createTargetFile(siteUmts);
					report.populateTargetFile(siteUmts, FrontWindow.this);
				}
				if (FrontWindow.this.lte.isSelected()) {
					if (FrontWindow.this.distributeBTSEast.isSelected()) {
						setFeederSizeEast("FIBER");
						setFiberLengthEast(FrontWindow.this.fiberLengthTextFieldEast.getText());
						setJumperLengthS1East(FrontWindow.this.distributeJumperLengthS1TextFieldEast.getText());
						setJumperLengthS2East(FrontWindow.this.distributeJumperLengthS2TextFieldEast.getText());
						setJumperLengthS3East(FrontWindow.this.distributeJumperLengthS3TextFieldEast.getText());
						setJumperLengthS4East(FrontWindow.this.distributeJumperLengthS4TextFieldEast.getText());
					}
					if (FrontWindow.this.commonBTSEast.isSelected()) {
						setFeederSizeEast((String) FrontWindow.this.feederTypeComboEast.getSelectedItem());
						setFeederLengthEast(FrontWindow.this.feederLengthTextFieldEast.getText());
						setJumperAtBtsLengthEast(FrontWindow.this.commonJumperAtBtsLengthTextFieldEast.getText());
						setJumperBeforeMhaLengthEast(
								FrontWindow.this.commonJumperBeforeMhaLengthTextFieldEast.getText());
						setJumperAfterMhaLengthEast(FrontWindow.this.commonJumperAfterMhaLengthTextFieldEast.getText());
						setJumpersPerSectorEast(FrontWindow.this.commonJumperPerSectorTextFieldEast.getText());
						setGroundsNoEast(FrontWindow.this.groundsNoTextFieldEast.getText());
					}
					SiteLte siteLte = new SiteLte();
					siteLte.setSiteCode(getIFiles());
					siteLte.setSiteName(getIFiles());
					siteLte.setDate(getIFiles());
					siteLte.antenaChanged(getIFiles());
					siteLte.setNumOfSectors(siteLte.getLte18Changed());
					siteLte.setMhaType(getIFiles());
					siteLte.setOverTransSiteCode(FrontWindow.this);
					if (siteLte.getOverTransSiteCode().charAt(2) == 'U') {
						FrontWindow.this.iFiles.setCrOfTransmissionForLteOver3g(siteLte.getOverTransSiteCode());
						siteLte.setOverTransModuleOver3g(getIFiles());
						siteLte.setOverE1LinesNo(getIFiles());
						siteLte.setOverGbEthElectLinesNo(getIFiles());
						siteLte.setOverGbEthOptLinesNo(getIFiles());
					} else {
						FrontWindow.this.iFiles.setCrOfTransmissionForLteOver2g();
						siteLte.setOverTransModuleOver2g(getIFiles());
						siteLte.parseXmlFileForE1LinesNo(getIFiles());
						siteLte.parseXmlFileForGbEthLinesNo(getIFiles());
					}
					siteLte.setRttaType(getIFiles());
					if (FrontWindow.this.distributeBTSEast.isSelected()) {
						siteLte.setFeederSize(FrontWindow.this);
						siteLte.setFiberLength(FrontWindow.this);
						siteLte.setDistributeJumperLengthS1(FrontWindow.this);
						siteLte.setDistributeJumperLengthS2(FrontWindow.this);
						siteLte.setDistributeJumperLengthS3(FrontWindow.this);
						siteLte.setDistributeJumperLengthS4(FrontWindow.this);
					}
					if (FrontWindow.this.commonBTSEast.isSelected()) {
						siteLte.setFeederSize(FrontWindow.this);
						siteLte.setFeederLength(FrontWindow.this);
						siteLte.setJumperAtBtsLen(FrontWindow.this);
						siteLte.setJumpersBeforeMhaLen(FrontWindow.this);
						siteLte.setJumpersAfterMhaLen(FrontWindow.this);
						siteLte.setJumperPerSectorNum(FrontWindow.this);
						siteLte.setGroungNum(FrontWindow.this);
					}
					if (FrontWindow.this.indoorBTSEast.isSelected() | FrontWindow.this.outdoorBTSEast.isSelected()) {
						siteLte.setInstalationType(FrontWindow.this);
					}
					if (FrontWindow.this.distributeBTSEast.isSelected() | FrontWindow.this.commonBTSEast.isSelected()) {
						siteLte.setArchitectureType(FrontWindow.this);
					}
					siteLte.setBtsCabinetType(FrontWindow.this);
					siteLte.setSysModule1Type(getIFiles());
					siteLte.setSysModule1Loc(FrontWindow.this);

					siteLte.setE1LinesNo(getIFiles());
					siteLte.setGbEthElectLinesNo(getIFiles());
					siteLte.setGbEthOptLinesNo(getIFiles());
					siteLte.setRfModulesType(getIFiles());
					siteLte.setRfModuleLoc(FrontWindow.this);
					siteLte.setPowSupplyType(FrontWindow.this);
					siteLte.setPowerSupplyParameters();
					siteLte.setSwVersion(getIFiles());
					siteLte.setSiteConfig();
					siteLte.setCrFile(getIFiles());
					siteLte.setCommFile(getIFiles());
					siteLte.setSiteInfo(getIFiles());
					OutputFiles report = new OutputFiles();
					report.createTargetFile(siteLte);
					report.populateTargetFile(siteLte, FrontWindow.this);
				}
			}
		}
	}

	// When Reset button is pressed it start program from point of creating
	// FrontWindow.
	public class ListenForResetButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == FrontWindow.this.resetButton) {
				FrontWindow fw = new FrontWindow();
				fw.createWindow();
			}
		}
	}

	public class ListneForDistributeButton implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				FrontWindow.this.fiberLengthLabel.setEnabled(true);
				FrontWindow.this.fiberLengthTextField.setEnabled(true);
				FrontWindow.this.distributeJumperLengthS1TextField.setEnabled(true);
				FrontWindow.this.distributeJumperLengthS2TextField.setEnabled(true);
				FrontWindow.this.distributeJumperLengthS3TextField.setEnabled(true);
				FrontWindow.this.distributeJumperLengthS4TextField.setEnabled(true);
				FrontWindow.this.distributeJumperLengthS1Label.setEnabled(true);
				FrontWindow.this.distributeJumperLengthS2Label.setEnabled(true);
				FrontWindow.this.distributeJumperLengthS3Label.setEnabled(true);
				FrontWindow.this.distributeJumperLengthS4Label.setEnabled(true);
			} else {
				FrontWindow.this.fiberLengthLabel.setEnabled(false);
				FrontWindow.this.fiberLengthTextField.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS1TextField.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS2TextField.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS3TextField.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS4TextField.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS1Label.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS2Label.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS3Label.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS4Label.setEnabled(false);
			}
		}
	}

	public class ListenForCommonButton implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				FrontWindow.this.feederTypeCombo.setEnabled(true);
				FrontWindow.this.feederLengthTextField.setEnabled(true);
				FrontWindow.this.groundsNoTextField.setEnabled(true);
				FrontWindow.this.commonJumperAtBtsLengthTextField.setEnabled(true);
				FrontWindow.this.commonJumperBeforeMhaLengthTextField.setEnabled(true);
				FrontWindow.this.commonJumperAfterMhaLengthTextField.setEnabled(true);
				FrontWindow.this.commonJumperPerSectorTextField.setEnabled(true);
				FrontWindow.this.feederLengthLabel.setEnabled(true);
				FrontWindow.this.groundsNoLabel.setEnabled(true);
				FrontWindow.this.commonJumperAtBtsLengthLabel.setEnabled(true);
				FrontWindow.this.commonJumperBeforeMhaLengthLabel.setEnabled(true);
				FrontWindow.this.commonJumperAfterMhaLengthLabel.setEnabled(true);
				FrontWindow.this.commonJumperPerSectorLabel.setEnabled(true);
				FrontWindow.this.feederTypeLabel.setEnabled(true);
			} else {
				FrontWindow.this.feederTypeCombo.setEnabled(false);
				FrontWindow.this.feederLengthTextField.setEnabled(false);
				FrontWindow.this.groundsNoTextField.setEnabled(false);
				FrontWindow.this.commonJumperAtBtsLengthTextField.setEnabled(false);
				FrontWindow.this.commonJumperBeforeMhaLengthTextField.setEnabled(false);
				FrontWindow.this.commonJumperAfterMhaLengthTextField.setEnabled(false);
				FrontWindow.this.commonJumperPerSectorTextField.setEnabled(false);
				FrontWindow.this.feederLengthLabel.setEnabled(false);
				FrontWindow.this.groundsNoLabel.setEnabled(false);
				FrontWindow.this.commonJumperAtBtsLengthLabel.setEnabled(false);
				FrontWindow.this.commonJumperBeforeMhaLengthLabel.setEnabled(false);
				FrontWindow.this.commonJumperAfterMhaLengthLabel.setEnabled(false);
				FrontWindow.this.commonJumperPerSectorLabel.setEnabled(false);
				FrontWindow.this.feederTypeLabel.setEnabled(false);
			}
		}
	}

	public class ListneForDistributeButtonEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				FrontWindow.this.fiberLengthLabelEast.setEnabled(true);
				FrontWindow.this.fiberLengthTextFieldEast.setEnabled(true);
				FrontWindow.this.distributeJumperLengthS1TextFieldEast.setEnabled(true);
				FrontWindow.this.distributeJumperLengthS2TextFieldEast.setEnabled(true);
				FrontWindow.this.distributeJumperLengthS3TextFieldEast.setEnabled(true);
				FrontWindow.this.distributeJumperLengthS4TextFieldEast.setEnabled(true);
				FrontWindow.this.fiberLengthLabelEast.setEnabled(true);
				FrontWindow.this.distributeJumperLengthS1LabelEast.setEnabled(true);
				FrontWindow.this.distributeJumperLengthS2LabelEast.setEnabled(true);
				FrontWindow.this.distributeJumperLengthS3LabelEast.setEnabled(true);
				FrontWindow.this.distributeJumperLengthS4LabelEast.setEnabled(true);
			} else {
				FrontWindow.this.fiberLengthLabelEast.setEnabled(false);
				FrontWindow.this.fiberLengthTextFieldEast.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS1TextFieldEast.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS2TextFieldEast.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS3TextFieldEast.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS4TextFieldEast.setEnabled(false);
				FrontWindow.this.fiberLengthLabelEast.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS1LabelEast.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS2LabelEast.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS3LabelEast.setEnabled(false);
				FrontWindow.this.distributeJumperLengthS4LabelEast.setEnabled(false);
			}
		}
	}

	public class ListenForCommonButtonEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				FrontWindow.this.feederTypeComboEast.setEnabled(true);
				FrontWindow.this.feederLengthTextFieldEast.setEnabled(true);
				FrontWindow.this.groundsNoTextFieldEast.setEnabled(true);
				FrontWindow.this.commonJumperAtBtsLengthTextFieldEast.setEnabled(true);
				FrontWindow.this.commonJumperBeforeMhaLengthTextFieldEast.setEnabled(true);
				FrontWindow.this.commonJumperAfterMhaLengthTextFieldEast.setEnabled(true);
				FrontWindow.this.commonJumperPerSectorTextFieldEast.setEnabled(true);
				FrontWindow.this.feederLengthLabelEast.setEnabled(true);
				FrontWindow.this.groundsNoLabelEast.setEnabled(true);
				FrontWindow.this.commonJumperAtBtsLengthLabelEast.setEnabled(true);
				FrontWindow.this.commonJumperBeforeMhaLengthLabelEast.setEnabled(true);
				FrontWindow.this.commonJumperAfterMhaLengthLabelEast.setEnabled(true);
				FrontWindow.this.commonJumperPerSectorLabelEast.setEnabled(true);
				FrontWindow.this.feederTypeLabelEast.setEnabled(true);
			} else {
				FrontWindow.this.feederTypeComboEast.setEnabled(false);
				FrontWindow.this.feederLengthTextFieldEast.setEnabled(false);
				FrontWindow.this.groundsNoTextFieldEast.setEnabled(false);
				FrontWindow.this.commonJumperAtBtsLengthTextFieldEast.setEnabled(false);
				FrontWindow.this.commonJumperBeforeMhaLengthTextFieldEast.setEnabled(false);
				FrontWindow.this.commonJumperAfterMhaLengthTextFieldEast.setEnabled(false);
				FrontWindow.this.commonJumperPerSectorTextFieldEast.setEnabled(false);
				FrontWindow.this.feederLengthLabelEast.setEnabled(false);
				FrontWindow.this.groundsNoLabelEast.setEnabled(false);
				FrontWindow.this.commonJumperAtBtsLengthLabelEast.setEnabled(false);
				FrontWindow.this.commonJumperBeforeMhaLengthLabelEast.setEnabled(false);
				FrontWindow.this.commonJumperAfterMhaLengthLabelEast.setEnabled(false);
				FrontWindow.this.commonJumperPerSectorLabelEast.setEnabled(false);
				FrontWindow.this.feederTypeLabelEast.setEnabled(false);
			}
		}
	}

	public class ListenForInstalationType implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == FrontWindow.this.indoorBTS) {
				setInstalationTypeStr("Indoor");
			} else {
				setInstalationTypeStr("Outdoor");
			}
		}
	}

	public class ListenForInstalationTypeEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == FrontWindow.this.indoorBTSEast) {
				setInstalationTypeStrEast("Indoor");
			} else {
				setInstalationTypeStrEast("Outdoor");
			}
		}
	}

	public class ListenForArchType implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == FrontWindow.this.commonBTS) {
				setArchitectureTypeStr("Common");
			} else {
				setArchitectureTypeStr("Distribute");
			}
		}
	}

	public class ListenForArchTypeEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == FrontWindow.this.commonBTSEast) {
				setArchitectureTypeStrEast("Common");
			} else {
				setArchitectureTypeStrEast("Distribute");
			}
		}
	}

	public class ListenForBtsCabinet implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				setBtsCabinet((String) FrontWindow.this.btsCabinetCombo.getSelectedItem());
			}
		}
	}

	public class ListenForBtsCabinetEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				setBtsCabinetEast((String) FrontWindow.this.btsCabinetComboEast.getSelectedItem());
			}
		}
	}

	public class ListenForAssemblyType implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == FrontWindow.this.wallAssembled) {
				setAssembleType("Wall");
			} else if (e.getSource() == FrontWindow.this.poleAssembled) {
				setAssembleType("Pole");
			} else if (e.getSource() == FrontWindow.this.stackAssembled) {
				setAssembleType("Stack");
			} else if (e.getSource() == FrontWindow.this.cabinetAssembled) {
				setAssembleType("Cabinet");
			}
		}
	}

	public class ListenForAssemblyTypeEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == FrontWindow.this.wallAssembledEast) {
				setAssembleTypeEast("Wall");
			} else if (e.getSource() == FrontWindow.this.poleAssembledEast) {
				setAssembleTypeEast("Pole");
			} else if (e.getSource() == FrontWindow.this.stackAssembledEast) {
				setAssembleTypeEast("Stack");
			} else if (e.getSource() == FrontWindow.this.cabinetAssembledEast) {
				setAssembleTypeEast("Cabinet");
			}
		}
	}

	public class ListenForAssemblyRFType implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == FrontWindow.this.wallAssembledRF) {
				setAssembleRFType("Wall");
			} else if (e.getSource() == FrontWindow.this.poleAssembledRF) {
				setAssembleRFType("Pole");
			} else if (e.getSource() == FrontWindow.this.stackAssembledRF) {
				setAssembleRFType("Stack");
			} else if (e.getSource() == FrontWindow.this.cabinetAssembledRF) {
				setAssembleRFType("Cabinet");
			}
		}
	}

	public class ListenForAssemblyRFTypeEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == FrontWindow.this.wallAssembledRFEast) {
				setAssembleRFTypeEast("Wall");
			} else if (e.getSource() == FrontWindow.this.poleAssembledRFEast) {
				setAssembleRFTypeEast("Pole");
			} else if (e.getSource() == FrontWindow.this.stackAssembledRFEast) {
				setAssembleRFTypeEast("Stack");
			} else if (e.getSource() == FrontWindow.this.cabinetAssembledRFEast) {
				setAssembleRFTypeEast("Cabinet");
			}
		}
	}

	public class ListenForPowerSupply implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				setPowSupplyType((String) FrontWindow.this.powSupplyCombo.getSelectedItem());
			}
		}
	}

	public class ListenForPowerSupplyEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				setPowSupplyTypeEast((String) FrontWindow.this.powSupplyComboEast.getSelectedItem());
			}
		}
	}

	public class ListenForTransmissionGoes implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == FrontWindow.this.transmission2G) {
				setTransmissionType("2g");
			} else {
				setTransmissionType("3g");
			}
		}
	}
}
