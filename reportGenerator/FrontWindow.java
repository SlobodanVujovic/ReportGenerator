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
	private JPanel mainPanel, northPanel, centerPanel, southPanel, centerUpPanel, centerDownPanel;
	private JRadioButton umts, lte, indoorBTS, outdoorBTS, commonBTS, distributeBTS, stackSys, wallSys, poleSys,
			cabinetSys, stackRf, wallRf, poleRf, cabinetRf, transmission2G, transmission3G;
	private JTextField fiberLengthTextField, distributeJumperLengthS1TextField, distributeJumperLengthS2TextField,
			distributeJumperLengthS3TextField, distributeJumperLengthS4TextField, feederLengthTextField,
			commonJumperAtBtsLengthTextField, commonJumperBeforeMhaLengthTextField, commonJumperAfterMhaLengthTextField,
			commonJumperPerSectorTextField, groundsNoTextField;
	private JLabel fiberLengthLabel, distributeJumperLengthS1Label, distributeJumperLengthS2Label,
			distributeJumperLengthS3Label, distributeJumperLengthS4Label, feederTypeLabel, feederLengthLabel,
			commonJumperAtBtsLengthLabel, commonJumperBeforeMhaLengthLabel, commonJumperAfterMhaLengthLabel,
			commonJumperPerSectorLabel, groundsNoLabel, powSupplyLabel, btsCabinetLabel;
	private JComboBox<String> feederTypeCombo, powSupplyCombo, btsCabinetCombo;
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
		return feederSize;
	}

	public String getFiberLength() {
		return fiberLength;
	}

	public String getFeederLength() {
		return feederLength;
	}

	public String getJumperLengthS1() {
		return jumperLengthS1;
	}

	public String getJumperLengthS2() {
		return jumperLengthS2;
	}

	public String getJumperLengthS3() {
		return jumperLengthS3;
	}

	public String getJumperLengthS4() {
		return jumperLengthS4;
	}

	public String getJumperAtBtsLength() {
		return jumperAtBtsLength;
	}

	public String getJumperBeforeMhaLength() {
		return jumperBeforeMhaLength;
	}

	public String getJumperAfterMhaLength() {
		return jumperAfterMhaLength;
	}

	public String getJumpersPerSector() {
		return jumpersPerSector;
	}

	public String getGroundsNo() {
		return groundsNo;
	}

	public String getInstalationTypeStr() {
		return instalationTypeStr;
	}

	public String getArchitectureTypeStr() {
		return architectureTypeStr;
	}

	public String getBtsCabinet() {
		return btsCabinet;
	}

	public String getAssembleType() {
		return assembleType;
	}

	public String getAssembleRFType() {
		return assembleRFType;
	}

	public String getPowSupplyType() {
		return powSupplyType;
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
		// JFrame - mainPanel. Contains everything on front window.
		this.mainPanel = new JPanel();
		// Front window is divided in north, center and south panel. They are in bottomPanel.
		this.northPanel = new JPanel();
		this.southPanel = new JPanel();

		// Center panel contains centerUp and centerDown panel.
		this.centerPanel = new JPanel(new GridBagLayout());

		GridBagConstraints gbcEast = new GridBagConstraints();

		this.centerUpPanel = new JPanel();
		this.centerDownPanel = new JPanel();

		this.umts = new JRadioButton("UMTS");
		this.lte = new JRadioButton("LTE", true);
		ListenForTechnologyType lForUmts = new ListenForTechnologyType();
		this.umts.addItemListener(lForUmts);
		ListenForTechnologyType lForLte = new ListenForTechnologyType();
		this.lte.addItemListener(lForLte);

		this.indoorBTS = new JRadioButton("Indoor BTS");
		this.outdoorBTS = new JRadioButton("Outdoor BTS", true);

		this.commonBTS = new JRadioButton("Common arch.");
		this.distributeBTS = new JRadioButton("Distribute arch.", true);

		this.stackSys = new JRadioButton("Stack");
		this.wallSys = new JRadioButton("Wall");
		this.poleSys = new JRadioButton("Pole");
		this.cabinetSys = new JRadioButton("Cabinet", true);

		this.stackRf = new JRadioButton("Stack");
		this.wallRf = new JRadioButton("Wall");
		this.poleRf = new JRadioButton("Pole");
		this.cabinetRf = new JRadioButton("Cabinet", true);

		this.transmission2G = new JRadioButton("over 2G");
		this.transmission3G = new JRadioButton("over 3G", true);

		this.fiberLengthTextField = new JTextField("50", 0);
		this.distributeJumperLengthS1TextField = new JTextField("3", 0);
		this.distributeJumperLengthS2TextField = new JTextField("3", 0);
		this.distributeJumperLengthS3TextField = new JTextField("3", 0);
		this.distributeJumperLengthS4TextField = new JTextField(0);
		this.fiberLengthLabel = new JLabel("Instaled fiber length [m]:");
		this.distributeJumperLengthS1Label = new JLabel("Jumper length at S1 [m]:");
		this.distributeJumperLengthS2Label = new JLabel("Jumper length at S2 [m]:");
		this.distributeJumperLengthS3Label = new JLabel("Jumper length at S3 [m]:");
		this.distributeJumperLengthS4Label = new JLabel("Jumper length at S4 [m]:");

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

		String[] powSupplyList = { "Eltek", "FPMA", "FPRA", "Telkom", "PBC", "Other" };
		this.powSupplyCombo = new JComboBox<>(powSupplyList);
		this.powSupplyLabel = new JLabel("Power supply type:");

		String[] btsCabinetList = { "Flatpack PRSB 16kW 48V", "BS 24x Service", "Micro BBU", "FCIA", "" };
		this.btsCabinetCombo = new JComboBox<>(btsCabinetList);
		this.btsCabinetLabel = new JLabel("BTS Cabinet:");

		this.genReportButton = new JButton("Generate Report");
		ListenForGenReportButton lForGenReportButton = new ListenForGenReportButton();
		this.genReportButton.addActionListener(lForGenReportButton);
		this.southPanel.add(this.genReportButton);

		this.resetButton = new JButton("Reset");
		ListenForResetButton lForResetButton = new ListenForResetButton();
		this.resetButton.addActionListener(lForResetButton);
		this.southPanel.add(this.resetButton);

		this.mainPanel.setLayout(new BorderLayout());
		this.mainPanel.add(BorderLayout.NORTH, this.northPanel);
		this.mainPanel.add(BorderLayout.CENTER, this.centerPanel);
		this.mainPanel.add(BorderLayout.SOUTH, this.southPanel);

		// Create scrolls and attach it to frame. Frame has preferred size.
		JScrollPane frameScrolls = new JScrollPane(mainPanel);
		frameScrolls.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frameScrolls.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.setPreferredSize(new Dimension(620, 730));
		this.setContentPane(frameScrolls);

		// Center panel
		Border eastBorder = BorderFactory.createTitledBorder("Parameters");
		this.centerPanel.setBorder(eastBorder);
		// With this we define column and row in which we want element.
		gbcEast.gridx = 0;
		gbcEast.gridy = 0;
		gbcEast.anchor = GridBagConstraints.WEST;
		gbcEast.insets = new Insets(5, 5, 5, 5);
		this.centerPanel.add(this.centerUpPanel, gbcEast);
		gbcEast.gridy = 1;
		this.centerPanel.add(this.centerDownPanel, gbcEast);

		// UMTS/LTE check boxes
		this.northPanel.add(this.umts);
		this.northPanel.add(this.lte);
		ButtonGroup umtsLteGroup = new ButtonGroup();
		umtsLteGroup.add(umts);
		umtsLteGroup.add(lte);
		Border umtsLteBorder = BorderFactory.createTitledBorder("Instaled technology:");
		this.northPanel.setBorder(umtsLteBorder);

		// Input/Output bullets group.
		ButtonGroup inOutGroupEast = new ButtonGroup();
		inOutGroupEast.add(this.indoorBTS);
		inOutGroupEast.add(this.outdoorBTS);
		JPanel inOutPanelEast = new JPanel(new GridLayout(2, 1));
		inOutPanelEast.add(this.indoorBTS);
		inOutPanelEast.add(this.outdoorBTS);
		ListenForInstalationTypeEast lForInstalationTypeEast = new ListenForInstalationTypeEast();
		this.indoorBTS.addItemListener(lForInstalationTypeEast);
		this.outdoorBTS.addItemListener(lForInstalationTypeEast);
		Border inOutBorderEast = BorderFactory.createTitledBorder("Instalation type:");
		inOutPanelEast.setBorder(inOutBorderEast);
		this.centerUpPanel.add(inOutPanelEast);

		// Common/Distribute bullets group.
		ButtonGroup archTypeGroupEast = new ButtonGroup();
		archTypeGroupEast.add(this.commonBTS);
		archTypeGroupEast.add(this.distributeBTS);
		JPanel archTypePanelEast = new JPanel(new GridLayout(2, 1));
		archTypePanelEast.add(this.commonBTS);
		archTypePanelEast.add(this.distributeBTS);
		ListenForArchTypeButtonEast lForCommonButtonEast = new ListenForArchTypeButtonEast();
		this.commonBTS.addItemListener(lForCommonButtonEast);
		ListenForArchTypeButtonEast lForDistributeButtonEast = new ListenForArchTypeButtonEast();
		this.distributeBTS.addItemListener(lForDistributeButtonEast);
		ListenForArchTypeEast lForArchTypeEast = new ListenForArchTypeEast();
		this.commonBTS.addItemListener(lForArchTypeEast);
		this.distributeBTS.addItemListener(lForArchTypeEast);
		Border archBorderEast = BorderFactory.createTitledBorder("Architecture type:");
		archTypePanelEast.setBorder(archBorderEast);
		this.centerUpPanel.add(archTypePanelEast);

		// Wall/Pole/Stack/Cabinet bullets group.
		ButtonGroup assembleTypeGroupEast = new ButtonGroup();
		assembleTypeGroupEast.add(this.wallSys);
		assembleTypeGroupEast.add(this.poleSys);
		assembleTypeGroupEast.add(this.stackSys);
		assembleTypeGroupEast.add(this.cabinetSys);
		ListenForAssemblyTypeEast lForAssemblyTypeEast = new ListenForAssemblyTypeEast();
		this.wallSys.addItemListener(lForAssemblyTypeEast);
		this.poleSys.addItemListener(lForAssemblyTypeEast);
		this.stackSys.addItemListener(lForAssemblyTypeEast);
		this.cabinetSys.addItemListener(lForAssemblyTypeEast);
		JPanel assembleTypePanelEast = new JPanel(new GridLayout(2, 2));
		assembleTypePanelEast.add(this.wallSys);
		assembleTypePanelEast.add(this.poleSys);
		assembleTypePanelEast.add(this.stackSys);
		assembleTypePanelEast.add(this.cabinetSys);
		Border assembleTypeBorderEast = BorderFactory.createTitledBorder("Sys mod. assemble:");
		assembleTypePanelEast.setBorder(assembleTypeBorderEast);
		this.centerUpPanel.add(assembleTypePanelEast);

		// Wall/Pole/Stack/Cabinet RF bullets group, east.
		ButtonGroup assembleRFTypeGroupEast = new ButtonGroup();
		assembleRFTypeGroupEast.add(this.wallRf);
		assembleRFTypeGroupEast.add(this.poleRf);
		assembleRFTypeGroupEast.add(this.stackRf);
		assembleRFTypeGroupEast.add(this.cabinetRf);
		ListenForAssemblyRFTypeEast lForAssemblyRFTypeEast = new ListenForAssemblyRFTypeEast();
		this.wallRf.addItemListener(lForAssemblyRFTypeEast);
		this.poleRf.addItemListener(lForAssemblyRFTypeEast);
		this.stackRf.addItemListener(lForAssemblyRFTypeEast);
		this.cabinetRf.addItemListener(lForAssemblyRFTypeEast);
		JPanel assembleRFTypePanelEast = new JPanel(new GridLayout(2, 2));
		assembleRFTypePanelEast.add(this.wallRf);
		assembleRFTypePanelEast.add(this.poleRf);
		assembleRFTypePanelEast.add(this.stackRf);
		assembleRFTypePanelEast.add(this.cabinetRf);
		Border assembleRFTypeBorderEast = BorderFactory.createTitledBorder("RF mod. assemble:");
		assembleRFTypePanelEast.setBorder(assembleRFTypeBorderEast);
		this.centerUpPanel.add(assembleRFTypePanelEast);

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
		this.centerDownPanel.add(transmissionPanel);
		ListenForTransmissionGoes lForTransmissionGoes = new ListenForTransmissionGoes();
		this.transmission2G.addItemListener(lForTransmissionGoes);
		this.transmission3G.addItemListener(lForTransmissionGoes);

		// Distribute cable length.
		JPanel distributeCablePanelEast = new JPanel(new GridLayout(5, 2));
		distributeCablePanelEast.add(this.fiberLengthLabel);
		distributeCablePanelEast.add(this.fiberLengthTextField);
		distributeCablePanelEast.add(this.distributeJumperLengthS1Label);
		distributeCablePanelEast.add(this.distributeJumperLengthS1TextField);
		distributeCablePanelEast.add(this.distributeJumperLengthS2Label);
		distributeCablePanelEast.add(this.distributeJumperLengthS2TextField);
		distributeCablePanelEast.add(this.distributeJumperLengthS3Label);
		distributeCablePanelEast.add(this.distributeJumperLengthS3TextField);
		distributeCablePanelEast.add(this.distributeJumperLengthS4Label);
		distributeCablePanelEast.add(this.distributeJumperLengthS4TextField);
		Border distributeCableBorderEast = BorderFactory.createTitledBorder("Distribute architecture");
		distributeCablePanelEast.setBorder(distributeCableBorderEast);
		gbcEast.gridx = 0;
		gbcEast.gridy = 2;
		this.centerPanel.add(distributeCablePanelEast, gbcEast);

		// Common cables length.
		JPanel commonCablePanelEast = new JPanel(new GridLayout(7, 2));
		commonCablePanelEast.add(this.feederTypeLabel);
		commonCablePanelEast.add(this.feederTypeCombo);
		commonCablePanelEast.add(this.feederLengthLabel);
		commonCablePanelEast.add(this.feederLengthTextField);
		commonCablePanelEast.add(this.commonJumperAtBtsLengthLabel);
		commonCablePanelEast.add(this.commonJumperAtBtsLengthTextField);
		commonCablePanelEast.add(this.commonJumperBeforeMhaLengthLabel);
		commonCablePanelEast.add(this.commonJumperBeforeMhaLengthTextField);
		commonCablePanelEast.add(this.commonJumperAfterMhaLengthLabel);
		commonCablePanelEast.add(this.commonJumperAfterMhaLengthTextField);
		commonCablePanelEast.add(this.commonJumperPerSectorLabel);
		commonCablePanelEast.add(this.commonJumperPerSectorTextField);
		commonCablePanelEast.add(this.groundsNoLabel);
		commonCablePanelEast.add(this.groundsNoTextField);
		Border commonCableBorderEast = BorderFactory.createTitledBorder("Common architecture");
		commonCablePanelEast.setBorder(commonCableBorderEast);
		gbcEast.gridx = 0;
		gbcEast.gridy = 3;
		this.centerPanel.add(commonCablePanelEast, gbcEast);

		// BTS cabinet type
		JPanel btsCabinetPanelEast = new JPanel();
		btsCabinetPanelEast.add(this.btsCabinetLabel);
		btsCabinetPanelEast.add(this.btsCabinetCombo);
		ListenForBtsCabinetEast lForBtsCabinetEast = new ListenForBtsCabinetEast();
		this.btsCabinetCombo.addItemListener(lForBtsCabinetEast);
		// Power supply type
		JPanel powSupplyPanelEast = new JPanel();
		powSupplyPanelEast.add(this.powSupplyLabel);
		powSupplyPanelEast.add(this.powSupplyCombo);
		ListenForPowerSupplyEast lForPowerSupplyEast = new ListenForPowerSupplyEast();
		this.powSupplyCombo.addItemListener(lForPowerSupplyEast);
		// BTS cabinet / power supply panel
		JPanel btsAndCabinetComboPanelEast = new JPanel();
		btsAndCabinetComboPanelEast.add(btsCabinetPanelEast);
		btsAndCabinetComboPanelEast.add(powSupplyPanelEast);
		gbcEast.gridx = 0;
		gbcEast.gridy = 4;
		this.centerPanel.add(btsAndCabinetComboPanelEast, gbcEast);

		feederTypeLabel.setEnabled(false);
		feederTypeCombo.setEnabled(false);
		feederLengthLabel.setEnabled(false);
		feederLengthTextField.setEnabled(false);
		commonJumperAtBtsLengthLabel.setEnabled(false);
		commonJumperAtBtsLengthTextField.setEnabled(false);
		commonJumperBeforeMhaLengthLabel.setEnabled(false);
		commonJumperBeforeMhaLengthTextField.setEnabled(false);
		commonJumperAfterMhaLengthLabel.setEnabled(false);
		commonJumperAfterMhaLengthTextField.setEnabled(false);
		commonJumperPerSectorLabel.setEnabled(false);
		commonJumperPerSectorTextField.setEnabled(false);
		groundsNoLabel.setEnabled(false);
		groundsNoTextField.setEnabled(false);

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

	// When UMTS/LTE is check it search for site code.
	public class ListenForTechnologyType implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == umts) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (iFiles.getSiteCode3gStr().equals("xxxyy")) {
						iFiles.findSiteCodeFromCommissioningReport('U');
						iFiles.setSiteCode('U');
						iFiles.sortOutInputFilesToAppropriateVariables();
					}
					transmission2G.setEnabled(false);
					transmission3G.setEnabled(false);
				} else {
					transmission2G.setEnabled(true);
					transmission3G.setEnabled(true);
				}
			}
			if (e.getSource() == lte) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (iFiles.getSiteCode4gStr().equals("xxxyy")) {
						iFiles.findSiteCodeFromCommissioningReport('L');
						iFiles.setSiteCode('L');
						iFiles.sortOutInputFilesToAppropriateVariables();
					}
				}
			}
		}
	}

	// Here we start with gathering of informations necessary for report and then populate report.
	public class ListenForGenReportButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == genReportButton) {
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
				if (umts.isSelected()) {
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
					if (distributeBTS.isSelected()) {
						siteLte.setFeederSize(getFeederSize());
						siteLte.setFiberLength(getFiberLength());
						siteLte.setDistributeJumperLengthS1(getJumperLengthS1());
						siteLte.setDistributeJumperLengthS2(getJumperLengthS2());
						siteLte.setDistributeJumperLengthS3(getJumperLengthS3());
						siteLte.setDistributeJumperLengthS4(getJumperLengthS4());
					}
					if (commonBTS.isSelected()) {
						siteLte.setFeederSize(getFeederSize());
						siteLte.setFeederLength(getFeederLength());
						siteLte.setJumperAtBtsLen(getJumperAtBtsLength());
						siteLte.setJumpersBeforeMhaLen(getJumperBeforeMhaLength());
						siteLte.setJumpersAfterMhaLen(getJumperAfterMhaLength());
						siteLte.setJumperPerSectorNum(getJumpersPerSector());
						siteLte.setGroungNum(getGroundsNo());
					}
					if (indoorBTS.isSelected() | outdoorBTS.isSelected()) {
						siteLte.setInstalationType(getInstalationTypeStr());
					}
					if (distributeBTS.isSelected() | commonBTS.isSelected()) {
						siteLte.setArchitectureType(getArchitectureTypeStr());
					}
					siteLte.setBtsCabinetType(getBtsCabinet());
					siteLte.setSysModule1Type(getIFiles());
					siteLte.setSysModule1Loc(getAssembleType());

					siteLte.setE1LinesNo(getIFiles());
					siteLte.setGbEthElectLinesNo(getIFiles());
					siteLte.setGbEthOptLinesNo(getIFiles());
					siteLte.setRfModulesType(getIFiles());
					siteLte.setRfModuleLoc(getAssembleRFType());
					siteLte.setPowSupplyType(getPowSupplyType());
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

	public class ListenForArchTypeButtonEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == distributeBTS) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					fiberLengthLabel.setEnabled(true);
					fiberLengthTextField.setEnabled(true);
					distributeJumperLengthS1TextField.setEnabled(true);
					distributeJumperLengthS2TextField.setEnabled(true);
					distributeJumperLengthS3TextField.setEnabled(true);
					distributeJumperLengthS4TextField.setEnabled(true);
					fiberLengthLabel.setEnabled(true);
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
					fiberLengthLabel.setEnabled(false);
					distributeJumperLengthS1Label.setEnabled(false);
					distributeJumperLengthS2Label.setEnabled(false);
					distributeJumperLengthS3Label.setEnabled(false);
					distributeJumperLengthS4Label.setEnabled(false);
				}
			}
			if (e.getSource() == commonBTS) {
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
	}

	public class ListenForInstalationTypeEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == indoorBTS) {
				instalationTypeStr = "Indoor";
			} else {
				instalationTypeStr = "Outdoor";
			}
		}
	}

	public class ListenForArchTypeEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == commonBTS) {
				architectureTypeStr = "Common";
			} else {
				architectureTypeStr = "Distribute";
			}
		}
	}

	public class ListenForBtsCabinetEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				btsCabinet = (String) btsCabinetCombo.getSelectedItem();
			}
		}
	}

	public class ListenForAssemblyTypeEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == wallSys) {
				assembleType = "Wall";
			} else if (e.getSource() == poleSys) {
				assembleType = "Pole";
			} else if (e.getSource() == stackSys) {
				assembleType = "Stack";
			} else if (e.getSource() == cabinetSys) {
				assembleType = "Cabinet";
			}
		}
	}

	public class ListenForAssemblyRFTypeEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() == wallRf) {
				assembleRFType = "Wall";
			} else if (e.getSource() == poleRf) {
				assembleRFType = "Pole";
			} else if (e.getSource() == stackRf) {
				assembleRFType = "Stack";
			} else if (e.getSource() == cabinetRf) {
				assembleRFType = "Cabinet";
			}
		}
	}

	public class ListenForPowerSupplyEast implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				powSupplyType = (String) powSupplyCombo.getSelectedItem();
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
