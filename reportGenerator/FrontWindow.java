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

// sve getEast() metode prebaciti na get() metode ali pre toga treba promeniti if petlje GUI-a da bi citali
// samo jednu stranu GUI-a.
// Sva East polja GUI-a prebaciti na obicna polja pre nego sto se nastavi sa sredjivanjem klase.
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
	private String feederSize, fiberLength, feederLength, jumperLengthS1, jumperLengthS2, jumperLengthS3,
			jumperLengthS4, jumperAtBtsLength, jumperBeforeMhaLength, jumperAfterMhaLength, jumpersPerSector, groundsNo,
			instalationTypeStr = "Outdoor", btsCabinet = "Flatpack PRSB 16kW 48V", architectureTypeStr = "Distribute",
			assembleType = "Cabinet", assembleRFType = "Cabinet", powSupplyType = "Eltek", transmissionType = "3g";

	public JRadioButton getUmts() {
		return umts;
	}

	public JRadioButton getLte() {
		return lte;
	}

	public String getFeederSize() {
		return this.feederSize;
	}

	public String getFeederSizeEast() {
		return getFeederSize();
	}

	public String getFiberLength() {
		return this.fiberLength;
	}

	public String getFiberLengthEast() {
		return getFiberLength();
	}

	public String getFeederLength() {
		return this.feederLength;
	}

	public String getFeederLengthEast() {
		return getFeederLength();
	}

	public String getJumperLengthS1() {
		return this.jumperLengthS1;
	}

	public String getJumperLengthS1East() {
		return getJumperLengthS1();
	}

	public String getJumperLengthS2() {
		return this.jumperLengthS2;
	}

	public String getJumperLengthS2East() {
		return getJumperLengthS2();
	}

	public String getJumperLengthS3() {
		return this.jumperLengthS3;
	}

	public String getJumperLengthS3East() {
		return getJumperLengthS3();
	}

	public String getJumperLengthS4() {
		return this.jumperLengthS4;
	}

	public String getJumperLengthS4East() {
		return getJumperLengthS4();
	}

	public String getJumperAtBtsLength() {
		return this.jumperAtBtsLength;
	}

	public String getJumperAtBtsLengthEast() {
		return getJumperAtBtsLength();
	}

	public String getJumperBeforeMhaLength() {
		return this.jumperBeforeMhaLength;
	}

	public String getJumperBeforeMhaLengthEast() {
		return getJumperBeforeMhaLength();
	}

	public String getJumperAfterMhaLength() {
		return this.jumperAfterMhaLength;
	}

	public String getJumperAfterMhaLengthEast() {
		return getJumperAfterMhaLength();
	}

	public String getJumpersPerSector() {
		return this.jumpersPerSector;
	}

	public String getJumpersPerSectorEast() {
		return getJumpersPerSector();
	}

	public String getGroundsNo() {
		return this.groundsNo;
	}

	public String getGroundsNoEast() {
		return getGroundsNo();
	}

	public String getInstalationTypeStr() {
		return this.instalationTypeStr;
	}

	public String getInstalationTypeStrEast() {
		return getInstalationTypeStr();
	}

	public String getArchitectureTypeStr() {
		return this.architectureTypeStr;
	}

	public String getArchitectureTypeStrEast() {
		return getArchitectureTypeStr();
	}

	public String getBtsCabinet() {
		return this.btsCabinet;
	}

	public String getBtsCabinetEast() {
		return getBtsCabinet();
	}

	public String getAssembleType() {
		return this.assembleType;
	}

	public String getAssembleTypeEast() {
		return getAssembleType();
	}

	public String getAssembleRFType() {
		return this.assembleRFType;
	}

	public String getAssembleRFTypeEast() {
		return getAssembleRFType();
	}

	public String getPowSupplyType() {
		return this.powSupplyType;
	}

	public String getPowSupplyTypeEast() {
		return getPowSupplyType();
	}

	public String getTransmissionType() {
		return this.transmissionType;
	}

	public InputFiles getIFiles() {
		return this.iFiles;
	}

	// Create front (main) window of program.
	public void createWindow() {
		this.iFiles = new InputFiles();
		// This is first panel after JFrame. Contains only bottomPanel.
		this.mainPanel = new JPanel();
		// JFrame - mainPanel - bottomPanel. Contains everything on front window.
		this.bottomPanel = new JPanel();
		// Front window is divided in north, center and south panel. They are in bottomPanel.
		this.northPanel = new JPanel();
		this.centerPanel = new JPanel(new GridLayout(1, 2));
		this.southPanel = new JPanel();

		// Center panel contains westPanel and eastPanel.
		this.westPanel = new JPanel(new GridBagLayout());
		this.eastPanel = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		GridBagConstraints gbcEast = new GridBagConstraints();

		// westPanel contains westNorthPanel.
		this.westNorthPanel = new JPanel();
		// eastPanel contains eastNorthPanel & eastCenterPanel.
		this.eastNorthPanel = new JPanel();

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

		// Create scrolls and attach it to frame. Frame has preferred size.
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
		// Because there is no transmission part in UMTS, here we add pixels to keep in line arrangement.
		gbc.ipady = 91;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		// Space around every element in panel.
		gbc.insets = new Insets(5, 5, 5, 5);
		this.westPanel.add(this.westNorthPanel, gbc);

		// East panel
		Border eastBorder = BorderFactory.createTitledBorder("LTE");
		this.eastPanel.setBorder(eastBorder);
		// With this we define column and row in which we want element.
		gbcEast.gridx = 0;
		gbcEast.gridy = 0;
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
		// Because "Transmission goes:" is longer then bullet labels, then it would be cut off, so we create
		// Dimension and set it as preferred.
		transmissionPanel.setPreferredSize(transmissionDim);
		// We need to validate preferred size, so this is mandatory.
		transmissionPanel.revalidate();
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

		// After adding all elements to JFrame we need to pack it so that all layers take their action.
		this.pack();
		this.windowMandatoryMethods();
	}

	public void windowMandatoryMethods() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setTitle("Report Generator");
	}

	// When we (un)check UMTS box, UMTS part is (disabled)enabled. Also when is check it search for site code.

	public class ListenForUmts implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			InputFiles iFilesUmts = iFiles;
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (iFilesUmts.getSiteCode3gStr().equals("xxxyy")) {
					iFilesUmts.findSiteCodeFromCommissioningReport('U');
					iFilesUmts.setSiteCode('U');
					iFilesUmts.sortOutInputFilesToAppropriateVariables();
				}
				if (iFilesUmts.getSiteCode3gStr() != null) {
					westPanel.setEnabled(true);
					westNorthPanel.setEnabled(true);
					indoorBTS.setEnabled(true);
					outdoorBTS.setEnabled(true);
					commonBTS.setEnabled(true);
					distributeBTS.setEnabled(true);
					stackAssembled.setEnabled(true);
					wallAssembled.setEnabled(true);
					poleAssembled.setEnabled(true);
					cabinetAssembled.setEnabled(true);
					stackAssembledRF.setEnabled(true);
					wallAssembledRF.setEnabled(true);
					poleAssembledRF.setEnabled(true);
					cabinetAssembledRF.setEnabled(true);
					// Depending on architecture type some part of window stay gray.
					if (distributeBTS.isSelected()) {
						fiberLengthLabel.setEnabled(true);
						fiberLengthTextField.setEnabled(true);
						distributeJumperLengthS1TextField.setEnabled(true);
						distributeJumperLengthS2TextField.setEnabled(true);
						distributeJumperLengthS3TextField.setEnabled(true);
						distributeJumperLengthS4TextField.setEnabled(true);
						distributeJumperLengthS1Label.setEnabled(true);
						distributeJumperLengthS2Label.setEnabled(true);
						distributeJumperLengthS3Label.setEnabled(true);
						distributeJumperLengthS4Label.setEnabled(true);
					}
					if (commonBTS.isSelected()) {
						feederTypeCombo.setEnabled(true);
						feederLengthTextField.setEnabled(true);
						groundsNoTextField.setEnabled(true);
						commonJumperAtBtsLengthTextField.setEnabled(true);
						commonJumperBeforeMhaLengthTextField.setEnabled(true);
						commonJumperAfterMhaLengthTextField.setEnabled(true);
						commonJumperPerSectorTextField.setEnabled(true);
						feederLengthLabel.setEnabled(true);
						groundsNoLabel.setEnabled(true);
						commonJumperAtBtsLengthLabel.setEnabled(true);
						commonJumperBeforeMhaLengthLabel.setEnabled(true);
						commonJumperAfterMhaLengthLabel.setEnabled(true);
						commonJumperPerSectorLabel.setEnabled(true);
						feederTypeLabel.setEnabled(true);
					}
					btsCabinetLabel.setEnabled(true);
					btsCabinetCombo.setEnabled(true);
					powSupplyCombo.setEnabled(true);
					powSupplyLabel.setEnabled(true);
				}
			} else {
				westPanel.setEnabled(false);
				westNorthPanel.setEnabled(false);
				indoorBTS.setEnabled(false);
				outdoorBTS.setEnabled(false);
				commonBTS.setEnabled(false);
				distributeBTS.setEnabled(false);
				stackAssembled.setEnabled(false);
				wallAssembled.setEnabled(false);
				poleAssembled.setEnabled(false);
				cabinetAssembled.setEnabled(false);
				stackAssembledRF.setEnabled(false);
				wallAssembledRF.setEnabled(false);
				poleAssembledRF.setEnabled(false);
				cabinetAssembledRF.setEnabled(false);
				fiberLengthLabel.setEnabled(false);
				fiberLengthTextField.setEnabled(false);
				distributeJumperLengthS1TextField.setEnabled(false);
				distributeJumperLengthS2TextField.setEnabled(false);
				distributeJumperLengthS3TextField.setEnabled(false);
				distributeJumperLengthS4TextField.setEnabled(false);
				distributeJumperLengthS1Label.setEnabled(false);
				distributeJumperLengthS2Label.setEnabled(false);
				distributeJumperLengthS3Label.setEnabled(false);
				distributeJumperLengthS4Label.setEnabled(false);
				feederTypeCombo.setEnabled(false);
				feederLengthTextField.setEnabled(false);
				groundsNoTextField.setEnabled(false);
				commonJumperAtBtsLengthTextField.setEnabled(false);
				commonJumperBeforeMhaLengthTextField.setEnabled(false);
				commonJumperAfterMhaLengthTextField.setEnabled(false);
				commonJumperPerSectorTextField.setEnabled(false);
				feederLengthLabel.setEnabled(false);
				groundsNoLabel.setEnabled(false);
				commonJumperAtBtsLengthLabel.setEnabled(false);
				commonJumperBeforeMhaLengthLabel.setEnabled(false);
				commonJumperAfterMhaLengthLabel.setEnabled(false);
				commonJumperPerSectorLabel.setEnabled(false);
				feederTypeLabel.setEnabled(false);
				btsCabinetLabel.setEnabled(false);
				btsCabinetCombo.setEnabled(false);
				powSupplyCombo.setEnabled(false);
				powSupplyLabel.setEnabled(false);
			}
		}
	}

	// Same as for UMTS check box.
	public class ListenForLte implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			InputFiles iFilesLte = iFiles;
			if (e.getStateChange() == ItemEvent.SELECTED) {
				if (iFilesLte.getSiteCode4gStr().equals("xxxyy")) {
					iFilesLte.findSiteCodeFromCommissioningReport('L');
					iFilesLte.setSiteCode('L');
					iFilesLte.sortOutInputFilesToAppropriateVariables();
				}
				if (iFilesLte.getSiteCode4gStr() != null) {
					eastPanel.setEnabled(true);
					eastNorthPanel.setEnabled(true);
					eastCenterPanel.setEnabled(true);
					indoorBTSEast.setEnabled(true);
					outdoorBTSEast.setEnabled(true);
					commonBTSEast.setEnabled(true);
					distributeBTSEast.setEnabled(true);
					stackAssembledEast.setEnabled(true);
					wallAssembledEast.setEnabled(true);
					poleAssembledEast.setEnabled(true);
					cabinetAssembledEast.setEnabled(true);
					stackAssembledRFEast.setEnabled(true);
					wallAssembledRFEast.setEnabled(true);
					poleAssembledRFEast.setEnabled(true);
					cabinetAssembledRFEast.setEnabled(true);
					transmission2G.setEnabled(true);
					transmission3G.setEnabled(true);
					if (distributeBTSEast.isSelected()) {
						fiberLengthLabelEast.setEnabled(true);
						fiberLengthTextFieldEast.setEnabled(true);
						distributeJumperLengthS1TextFieldEast.setEnabled(true);
						distributeJumperLengthS2TextFieldEast.setEnabled(true);
						distributeJumperLengthS3TextFieldEast.setEnabled(true);
						distributeJumperLengthS4TextFieldEast.setEnabled(true);
						fiberLengthLabelEast.setEnabled(true);
						distributeJumperLengthS1LabelEast.setEnabled(true);
						distributeJumperLengthS2LabelEast.setEnabled(true);
						distributeJumperLengthS3LabelEast.setEnabled(true);
						distributeJumperLengthS4LabelEast.setEnabled(true);
					}
					if (commonBTSEast.isSelected()) {
						feederTypeComboEast.setEnabled(true);
						feederLengthTextFieldEast.setEnabled(true);
						groundsNoTextFieldEast.setEnabled(true);
						commonJumperAtBtsLengthTextFieldEast.setEnabled(true);
						commonJumperBeforeMhaLengthTextFieldEast.setEnabled(true);
						commonJumperAfterMhaLengthTextFieldEast.setEnabled(true);
						commonJumperPerSectorTextFieldEast.setEnabled(true);
						feederLengthLabelEast.setEnabled(true);
						groundsNoLabelEast.setEnabled(true);
						commonJumperAtBtsLengthLabelEast.setEnabled(true);
						commonJumperBeforeMhaLengthLabelEast.setEnabled(true);
						commonJumperAfterMhaLengthLabelEast.setEnabled(true);
						commonJumperPerSectorLabelEast.setEnabled(true);
						feederTypeLabelEast.setEnabled(true);
					}
					btsCabinetLabelEast.setEnabled(true);
					btsCabinetComboEast.setEnabled(true);
					powSupplyComboEast.setEnabled(true);
					powSupplyLabelEast.setEnabled(true);
				}
			} else {
				eastPanel.setEnabled(false);
				eastNorthPanel.setEnabled(false);
				eastCenterPanel.setEnabled(false);
				indoorBTSEast.setEnabled(false);
				outdoorBTSEast.setEnabled(false);
				commonBTSEast.setEnabled(false);
				distributeBTSEast.setEnabled(false);
				stackAssembledEast.setEnabled(false);
				wallAssembledEast.setEnabled(false);
				poleAssembledEast.setEnabled(false);
				cabinetAssembledEast.setEnabled(false);
				stackAssembledRFEast.setEnabled(false);
				wallAssembledRFEast.setEnabled(false);
				poleAssembledRFEast.setEnabled(false);
				cabinetAssembledRFEast.setEnabled(false);
				transmission2G.setEnabled(false);
				transmission3G.setEnabled(false);
				fiberLengthLabelEast.setEnabled(false);
				fiberLengthTextFieldEast.setEnabled(false);
				distributeJumperLengthS1TextFieldEast.setEnabled(false);
				distributeJumperLengthS2TextFieldEast.setEnabled(false);
				distributeJumperLengthS3TextFieldEast.setEnabled(false);
				distributeJumperLengthS4TextFieldEast.setEnabled(false);
				fiberLengthLabelEast.setEnabled(false);
				distributeJumperLengthS1LabelEast.setEnabled(false);
				distributeJumperLengthS2LabelEast.setEnabled(false);
				distributeJumperLengthS3LabelEast.setEnabled(false);
				distributeJumperLengthS4LabelEast.setEnabled(false);
				feederTypeComboEast.setEnabled(false);
				feederLengthTextFieldEast.setEnabled(false);
				groundsNoTextFieldEast.setEnabled(false);
				commonJumperAtBtsLengthTextFieldEast.setEnabled(false);
				commonJumperBeforeMhaLengthTextFieldEast.setEnabled(false);
				commonJumperAfterMhaLengthTextFieldEast.setEnabled(false);
				commonJumperPerSectorTextFieldEast.setEnabled(false);
				feederLengthLabelEast.setEnabled(false);
				groundsNoLabelEast.setEnabled(false);
				commonJumperAtBtsLengthLabelEast.setEnabled(false);
				commonJumperBeforeMhaLengthLabelEast.setEnabled(false);
				commonJumperAfterMhaLengthLabelEast.setEnabled(false);
				commonJumperPerSectorLabelEast.setEnabled(false);
				feederTypeLabelEast.setEnabled(false);
				btsCabinetLabelEast.setEnabled(false);
				btsCabinetComboEast.setEnabled(false);
				powSupplyComboEast.setEnabled(false);
				powSupplyLabelEast.setEnabled(false);
			}
		}
	}

	// Here we start with gathering of informations necessary for report and then populate report.
	public class ListenForGenReportButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == genReportButton) {
				if (umts.isSelected()) {
					if (distributeBTS.isSelected()) {
						feederSize = "FIBER";
						fiberLength = fiberLengthTextField.getText();
						jumperLengthS1 = distributeJumperLengthS1TextField.getText();
						jumperLengthS2 = distributeJumperLengthS2TextField.getText();
						jumperLengthS3 = distributeJumperLengthS3TextField.getText();
						jumperLengthS4 = distributeJumperLengthS4TextField.getText();
					}
					if (commonBTS.isSelected()) {
						feederSize = (String) feederTypeCombo.getSelectedItem();
						feederLength = feederLengthTextField.getText();
						jumperAtBtsLength = commonJumperAtBtsLengthTextField.getText();
						jumperBeforeMhaLength = commonJumperBeforeMhaLengthTextField.getText();
						jumperAfterMhaLength = commonJumperAfterMhaLengthTextField.getText();
						jumpersPerSector = commonJumperPerSectorTextField.getText();
						groundsNo = groundsNoTextField.getText();
					}
					SiteUmts siteUmts = new SiteUmts();
					siteUmts.setSiteCode(getIFiles());
					siteUmts.setSiteName(getIFiles());
					siteUmts.setDate(getIFiles());
					siteUmts.antenaChanged(getIFiles());
					siteUmts.setNumOfSectors(siteUmts.getUmts21Changed());
					siteUmts.setMhaType(getIFiles());
					siteUmts.setRttaType(getIFiles());
					if (distributeBTS.isSelected()) {
						siteUmts.setFeederSize(getFeederSize());
						siteUmts.setFiberLength(getFiberLength());
						siteUmts.setDistributeJumperLengthS1(getJumperLengthS1());
						siteUmts.setDistributeJumperLengthS2(getJumperLengthS2());
						siteUmts.setDistributeJumperLengthS3(getJumperLengthS3());
						siteUmts.setDistributeJumperLengthS4(getJumperLengthS4());
					}
					if (commonBTS.isSelected()) {
						siteUmts.setFeederSize(getFeederSize());
						siteUmts.setFeederLength(getFeederLength());
						siteUmts.setJumperAtBtsLen(getJumperAtBtsLength());
						siteUmts.setJumpersBeforeMhaLen(getJumperBeforeMhaLength());
						siteUmts.setJumpersAfterMhaLen(getJumperAfterMhaLength());
						siteUmts.setJumperPerSectorNum(getJumpersPerSector());
						siteUmts.setGroungNum(getGroundsNo());
					}
					if (indoorBTS.isSelected() | outdoorBTS.isSelected()) {
						siteUmts.setInstalationType(getInstalationTypeStr());
					}
					if (distributeBTS.isSelected() | commonBTS.isSelected()) {
						siteUmts.setArchitectureType(getArchitectureTypeStr());
					}
					siteUmts.setBtsCabinetType(getBtsCabinet());
					siteUmts.setSysModule1Type(getIFiles());
					siteUmts.setSysModule1Loc(getAssembleType());
					siteUmts.setTransModuleType(getIFiles());
					siteUmts.setE1LinesNo(getIFiles());
					siteUmts.setGbEthElectLinesNo(getIFiles());
					siteUmts.setGbEthOptLinesNo(getIFiles());
					siteUmts.setRfModulesType(getIFiles());
					siteUmts.setRfModuleLoc(getAssembleRFType());
					siteUmts.setPowSupplyType(getPowSupplyType());
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
					report.populateTargetFile(siteUmts, getUmts().isSelected(), getLte().isSelected());
				}
				if (lte.isSelected()) {
					if (distributeBTSEast.isSelected()) {
						feederSize = "FIBER";
						fiberLength = fiberLengthTextFieldEast.getText();
						jumperLengthS1 = distributeJumperLengthS1TextFieldEast.getText();
						jumperLengthS2 = distributeJumperLengthS2TextFieldEast.getText();
						jumperLengthS3 = distributeJumperLengthS3TextFieldEast.getText();
						jumperLengthS4 = distributeJumperLengthS4TextFieldEast.getText();
					}
					if (commonBTSEast.isSelected()) {
						feederSize = (String) feederTypeComboEast.getSelectedItem();
						feederLength = feederLengthTextFieldEast.getText();
						jumperAtBtsLength = commonJumperAtBtsLengthTextFieldEast.getText();
						jumperBeforeMhaLength = commonJumperBeforeMhaLengthTextFieldEast.getText();
						jumperAfterMhaLength = commonJumperAfterMhaLengthTextFieldEast.getText();
						jumpersPerSector = commonJumperPerSectorTextFieldEast.getText();
						groundsNo = groundsNoTextFieldEast.getText();
					}
					SiteLte siteLte = new SiteLte();
					siteLte.setSiteCode(getIFiles());
					siteLte.setSiteName(getIFiles());
					siteLte.setDate(getIFiles());
					siteLte.antenaChanged(getIFiles());
					siteLte.setNumOfSectors(siteLte.getLte18Changed());
					siteLte.setMhaType(getIFiles());
					siteLte.setOverTransSiteCode(getTransmissionType());
					if (siteLte.getOverTransSiteCode().charAt(2) == 'U') {
						iFiles.setCrOfTransmissionForLteOver3g(siteLte.getOverTransSiteCode());
						siteLte.setOverTransModuleOver3g(getIFiles());
						siteLte.setOverE1LinesNo(getIFiles());
						siteLte.setOverGbEthElectLinesNo(getIFiles());
						siteLte.setOverGbEthOptLinesNo(getIFiles());
					} else {
						iFiles.setCrOfTransmissionForLteOver2g();
						siteLte.setOverTransModuleOver2g(getIFiles());
						siteLte.parseXmlFileForE1LinesNo(getIFiles());
						siteLte.parseXmlFileForGbEthLinesNo(getIFiles());
					}
					siteLte.setRttaType(getIFiles());
					if (distributeBTSEast.isSelected()) {
						siteLte.setFeederSize(getFeederSizeEast());
						siteLte.setFiberLength(getFiberLengthEast());
						siteLte.setDistributeJumperLengthS1(getJumperLengthS1East());
						siteLte.setDistributeJumperLengthS2(getJumperLengthS2East());
						siteLte.setDistributeJumperLengthS3(getJumperLengthS3East());
						siteLte.setDistributeJumperLengthS4(getJumperLengthS4East());
					}
					if (commonBTSEast.isSelected()) {
						siteLte.setFeederSize(getFeederSizeEast());
						siteLte.setFeederLength(getFeederLengthEast());
						siteLte.setJumperAtBtsLen(getJumperAtBtsLengthEast());
						siteLte.setJumpersBeforeMhaLen(getJumperBeforeMhaLengthEast());
						siteLte.setJumpersAfterMhaLen(getJumperAfterMhaLengthEast());
						siteLte.setJumperPerSectorNum(getJumpersPerSectorEast());
						siteLte.setGroungNum(getGroundsNoEast());
					}
					if (indoorBTSEast.isSelected() | outdoorBTSEast.isSelected()) {
						siteLte.setInstalationType(getInstalationTypeStrEast());
					}
					if (distributeBTSEast.isSelected() | commonBTSEast.isSelected()) {
						siteLte.setArchitectureType(getArchitectureTypeStrEast());
					}
					siteLte.setBtsCabinetType(getBtsCabinetEast());
					siteLte.setSysModule1Type(getIFiles());
					siteLte.setSysModule1Loc(getAssembleTypeEast());

					siteLte.setE1LinesNo(getIFiles());
					siteLte.setGbEthElectLinesNo(getIFiles());
					siteLte.setGbEthOptLinesNo(getIFiles());
					siteLte.setRfModulesType(getIFiles());
					siteLte.setRfModuleLoc(getAssembleRFTypeEast());
					siteLte.setPowSupplyType(getPowSupplyTypeEast());
					siteLte.setPowerSupplyParameters();
					siteLte.setSwVersion(getIFiles());
					siteLte.setSiteConfig();
					siteLte.setCrFile(getIFiles());
					siteLte.setCommFile(getIFiles());
					siteLte.setSiteInfo(getIFiles());
					OutputFiles report = new OutputFiles();
					report.createTargetFile(siteLte);
					report.populateTargetFile(siteLte, getUmts().isSelected(), getLte().isSelected());
				}
			}
		}
	}

	// When Reset button is pressed it start program from point of creating FrontWindow.
	public class ListenForResetButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == resetButton) {
				FrontWindow fw = new FrontWindow();
				fw.createWindow();
			}
		}
	}

	public class ListneForDistributeButton implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				fiberLengthLabel.setEnabled(true);
				fiberLengthTextField.setEnabled(true);
				distributeJumperLengthS1TextField.setEnabled(true);
				distributeJumperLengthS2TextField.setEnabled(true);
				distributeJumperLengthS3TextField.setEnabled(true);
				distributeJumperLengthS4TextField.setEnabled(true);
				distributeJumperLengthS1Label.setEnabled(true);
				distributeJumperLengthS2Label.setEnabled(true);
				distributeJumperLengthS3Label.setEnabled(true);
				distributeJumperLengthS4Label.setEnabled(true);
			} else {
				fiberLengthLabel.setEnabled(false);
				fiberLengthTextField.setEnabled(false);
				distributeJumperLengthS1TextField.setEnabled(false);
				distributeJumperLengthS2TextField.setEnabled(false);
				distributeJumperLengthS3TextField.setEnabled(false);
				distributeJumperLengthS4TextField.setEnabled(false);
				distributeJumperLengthS1Label.setEnabled(false);
				distributeJumperLengthS2Label.setEnabled(false);
				distributeJumperLengthS3Label.setEnabled(false);
				distributeJumperLengthS4Label.setEnabled(false);
			}
		}
	}

	public class ListenForCommonButton implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				feederTypeCombo.setEnabled(true);
				feederLengthTextField.setEnabled(true);
				groundsNoTextField.setEnabled(true);
				commonJumperAtBtsLengthTextField.setEnabled(true);
				commonJumperBeforeMhaLengthTextField.setEnabled(true);
				commonJumperAfterMhaLengthTextField.setEnabled(true);
				commonJumperPerSectorTextField.setEnabled(true);
				feederLengthLabel.setEnabled(true);
				groundsNoLabel.setEnabled(true);
				commonJumperAtBtsLengthLabel.setEnabled(true);
				commonJumperBeforeMhaLengthLabel.setEnabled(true);
				commonJumperAfterMhaLengthLabel.setEnabled(true);
				commonJumperPerSectorLabel.setEnabled(true);
				feederTypeLabel.setEnabled(true);
			} else {
				feederTypeCombo.setEnabled(false);
				feederLengthTextField.setEnabled(false);
				groundsNoTextField.setEnabled(false);
				commonJumperAtBtsLengthTextField.setEnabled(false);
				commonJumperBeforeMhaLengthTextField.setEnabled(false);
				commonJumperAfterMhaLengthTextField.setEnabled(false);
				commonJumperPerSectorTextField.setEnabled(false);
				feederLengthLabel.setEnabled(false);
				groundsNoLabel.setEnabled(false);
				commonJumperAtBtsLengthLabel.setEnabled(false);
				commonJumperBeforeMhaLengthLabel.setEnabled(false);
				commonJumperAfterMhaLengthLabel.setEnabled(false);
				commonJumperPerSectorLabel.setEnabled(false);
				feederTypeLabel.setEnabled(false);
			}
		}
	}

	public class ListneForDistributeButtonEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				fiberLengthLabelEast.setEnabled(true);
				fiberLengthTextFieldEast.setEnabled(true);
				distributeJumperLengthS1TextFieldEast.setEnabled(true);
				distributeJumperLengthS2TextFieldEast.setEnabled(true);
				distributeJumperLengthS3TextFieldEast.setEnabled(true);
				distributeJumperLengthS4TextFieldEast.setEnabled(true);
				fiberLengthLabelEast.setEnabled(true);
				distributeJumperLengthS1LabelEast.setEnabled(true);
				distributeJumperLengthS2LabelEast.setEnabled(true);
				distributeJumperLengthS3LabelEast.setEnabled(true);
				distributeJumperLengthS4LabelEast.setEnabled(true);
			} else {
				fiberLengthLabelEast.setEnabled(false);
				fiberLengthTextFieldEast.setEnabled(false);
				distributeJumperLengthS1TextFieldEast.setEnabled(false);
				distributeJumperLengthS2TextFieldEast.setEnabled(false);
				distributeJumperLengthS3TextFieldEast.setEnabled(false);
				distributeJumperLengthS4TextFieldEast.setEnabled(false);
				fiberLengthLabelEast.setEnabled(false);
				distributeJumperLengthS1LabelEast.setEnabled(false);
				distributeJumperLengthS2LabelEast.setEnabled(false);
				distributeJumperLengthS3LabelEast.setEnabled(false);
				distributeJumperLengthS4LabelEast.setEnabled(false);
			}
		}
	}

	public class ListenForCommonButtonEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				feederTypeComboEast.setEnabled(true);
				feederLengthTextFieldEast.setEnabled(true);
				groundsNoTextFieldEast.setEnabled(true);
				commonJumperAtBtsLengthTextFieldEast.setEnabled(true);
				commonJumperBeforeMhaLengthTextFieldEast.setEnabled(true);
				commonJumperAfterMhaLengthTextFieldEast.setEnabled(true);
				commonJumperPerSectorTextFieldEast.setEnabled(true);
				feederLengthLabelEast.setEnabled(true);
				groundsNoLabelEast.setEnabled(true);
				commonJumperAtBtsLengthLabelEast.setEnabled(true);
				commonJumperBeforeMhaLengthLabelEast.setEnabled(true);
				commonJumperAfterMhaLengthLabelEast.setEnabled(true);
				commonJumperPerSectorLabelEast.setEnabled(true);
				feederTypeLabelEast.setEnabled(true);
			} else {
				feederTypeComboEast.setEnabled(false);
				feederLengthTextFieldEast.setEnabled(false);
				groundsNoTextFieldEast.setEnabled(false);
				commonJumperAtBtsLengthTextFieldEast.setEnabled(false);
				commonJumperBeforeMhaLengthTextFieldEast.setEnabled(false);
				commonJumperAfterMhaLengthTextFieldEast.setEnabled(false);
				commonJumperPerSectorTextFieldEast.setEnabled(false);
				feederLengthLabelEast.setEnabled(false);
				groundsNoLabelEast.setEnabled(false);
				commonJumperAtBtsLengthLabelEast.setEnabled(false);
				commonJumperBeforeMhaLengthLabelEast.setEnabled(false);
				commonJumperAfterMhaLengthLabelEast.setEnabled(false);
				commonJumperPerSectorLabelEast.setEnabled(false);
				feederTypeLabelEast.setEnabled(false);
			}
		}
	}

	public class ListenForInstalationType implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == indoorBTS) {
				instalationTypeStr = "Indoor";
			} else {
				instalationTypeStr = "Outdoor";
			}
		}
	}

	public class ListenForInstalationTypeEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == indoorBTSEast) {
				instalationTypeStr = "Indoor";
			} else {
				instalationTypeStr = "Outdoor";
			}
		}
	}

	public class ListenForArchType implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == commonBTS) {
				architectureTypeStr = "Common";
			} else {
				architectureTypeStr = "Distribute";
			}
		}
	}

	public class ListenForArchTypeEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == commonBTSEast) {
				architectureTypeStr = "Common";
			} else {
				architectureTypeStr = "Distribute";
			}
		}
	}

	public class ListenForBtsCabinet implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				btsCabinet = (String) btsCabinetCombo.getSelectedItem();
			}
		}
	}

	public class ListenForBtsCabinetEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				btsCabinet = (String) btsCabinetComboEast.getSelectedItem();
			}
		}
	}

	public class ListenForAssemblyType implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == wallAssembled) {
				assembleType = "Wall";
			} else if (e.getSource() == poleAssembled) {
				assembleType = "Pole";
			} else if (e.getSource() == stackAssembled) {
				assembleType = "Stack";
			} else if (e.getSource() == cabinetAssembled) {
				assembleType = "Cabinet";
			}
		}
	}

	public class ListenForAssemblyTypeEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == wallAssembledEast) {
				assembleType = "Wall";
			} else if (e.getSource() == poleAssembledEast) {
				assembleType = "Pole";
			} else if (e.getSource() == stackAssembledEast) {
				assembleType = "Stack";
			} else if (e.getSource() == cabinetAssembledEast) {
				assembleType = "Cabinet";
			}
		}
	}

	public class ListenForAssemblyRFType implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == wallAssembledRF) {
				assembleRFType = "Wall";
			} else if (e.getSource() == poleAssembledRF) {
				assembleRFType = "Pole";
			} else if (e.getSource() == stackAssembledRF) {
				assembleRFType = "Stack";
			} else if (e.getSource() == cabinetAssembledRF) {
				assembleRFType = "Cabinet";
			}
		}
	}

	public class ListenForAssemblyRFTypeEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == wallAssembledRFEast) {
				assembleRFType = "Wall";
			} else if (e.getSource() == poleAssembledRFEast) {
				assembleRFType = "Pole";
			} else if (e.getSource() == stackAssembledRFEast) {
				assembleRFType = "Stack";
			} else if (e.getSource() == cabinetAssembledRFEast) {
				assembleRFType = "Cabinet";
			}
		}
	}

	public class ListenForPowerSupply implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				powSupplyType = (String) powSupplyCombo.getSelectedItem();
			}
		}
	}

	public class ListenForPowerSupplyEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				powSupplyType = (String) powSupplyComboEast.getSelectedItem();
			}
		}
	}

	public class ListenForTransmissionGoes implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == transmission2G) {
				transmissionType = "2g";
			} else {
				transmissionType = "3g";
			}
		}
	}
}
