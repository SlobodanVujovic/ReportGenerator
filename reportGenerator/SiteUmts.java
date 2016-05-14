/*
 * This is UMTS site class. All information concern UMTS are first collected here and then populate to
 * final report.
 */

package reportGenerator;

public class SiteUmts implements Site {
	private String siteCode, siteName, date, siteConfig, mhaTypeS1, mhaTypeS2, mhaTypeS3, mhaTypeS4, rtaaTypeS1,
			rtaaTypeS2, rtaaTypeS3, rtaaTypeS4, feederSizeS1, feederSizeS2, feederSizeS3, feederSizeS4, feederLengthS1,
			feederLengthS2, feederLengthS3, feederLengthS4, sysModule1Type, sysModule1Loc, transModuleType,
			fiberLengthS1, fiberLengthS2, fiberLengthS3, fiberLengthS4, jumperAtBtsLenS1, jumperAtBtsLenS2,
			jumperAtBtsLenS3, jumperAtBtsLenS4, jumperBeforeMhaLenS1, jumperBeforeMhaLenS2, jumperBeforeMhaLenS3,
			jumperBeforeMhaLenS4, jumperAfterMhaLenS1, jumperAfterMhaLenS2, jumperAfterMhaLenS3, jumperAfterMhaLenS4,
			jumperPerSectorS1, jumperPerSectorS2, jumperPerSectorS3, jumperPerSectorS4, earthKitNoS1, earthKitNoS2,
			earthKitNoS3, earthKitNoS4, distributeJumperLengthS1, distributeJumperLengthS2, distributeJumperLengthS3,
			distributeJumperLengthS4, instalationType, btsCabinetType, architectureType, rfModule1Type, rfModule1Loc,
			rfModule2Type, rfModule2Loc, rfModule3Type, rfModule3Loc, powSupplyType, rectifierType, rectifierPower,
			rectifierQty, batteryType, batteryCap, batQty, current, voltage, swVersion, trsIp, nodebIp, rncIp, rcuaIp,
			crFile, commFile, siteInfo;
	private int e1LinesNo, gbEthElectLinesNo, gbEthOptLinesNo, numOfSectors;
	private AffectedSite gsm9Changed, gsm18Changed, umts21Changed, lte18Changed;
	private String[][] cellIdGroup = new String[4][3];
	private int[][] uarfcnGroup = new int[4][3];
	private String[] rfModulesType, cellIdS1 = cellIdGroup[0], cellIdS2 = cellIdGroup[1], cellIdS3 = cellIdGroup[2],
			cellIdS4 = cellIdGroup[3], extAl1Str, extAl2Str, extAl3Str, extAl4Str, extAl5Str, extAl6Str, extAl7Str,
			extAl8Str, extAl9Str, extAl10Str;
	// Meaning of elements of extAlx array:
	// extAlx = extAlxStr[0]; extAlxName = extAlxStr[1]; extAlxPol =
	// extAlxStr[2];
	// extAlxSev = extAlxStr[3];
	private int[] uarfcnS1 = uarfcnGroup[0], uarfcnS2 = uarfcnGroup[1], uarfcnS3 = uarfcnGroup[2],
			uarfcnS4 = uarfcnGroup[3];

	@Override
	public String getSiteCode() {
		return this.siteCode;
	}

	@Override
	public void setSiteCode(InputFiles inputFiles) {
		this.siteCode = inputFiles.getSiteCode3gStr();
	}

	@Override
	public String getSiteName() {
		return this.siteName;
	}

	@Override
	public void setSiteName(InputFiles inputFiles) {
		this.siteName = inputFiles.readParameterFromCommissioningReport(inputFiles.getUmtsCrFile(), "Description:");
	}

	@Override
	public String getDate() {
		return this.date;
	}

	// Set date work for input types dd.MM.YYYY and YYYY.MM.dd .
	@Override
	public void setDate(InputFiles inputFiles) {
		String rowDate, dd, MM, yy;
		rowDate = inputFiles.readParameterFromCommissioningReport(inputFiles.getUmtsCrFile(), "Date:");
		if (rowDate.charAt(2) == '.') {
			this.date = rowDate;
		} else {
			yy = rowDate.substring(0, 4);
			MM = rowDate.substring(5, 7);
			dd = rowDate.substring(8, 10);
			this.date = dd + "." + MM + "." + yy + ".";
		}
	}

	@Override
	public AffectedSite getGsm9Changed() {
		return this.gsm9Changed;
	}

	@Override
	public AffectedSite getGsm18Changed() {
		return this.gsm18Changed;
	}

	@Override
	public AffectedSite getUmts21Changed() {
		return this.umts21Changed;
	}

	@Override
	public AffectedSite getLte18Changed() {
		return this.lte18Changed;
	}

	// Here we read data from UP file for different technology.
	public void antenaChanged(InputFiles inputFiles) {
		this.gsm9Changed = inputFiles.readUpForSiteChanged("", this.siteCode);
		this.gsm18Changed = inputFiles.readUpForSiteChanged("H", this.siteCode);
		this.umts21Changed = inputFiles.readUpForSiteChanged("U", this.siteCode);
		this.lte18Changed = inputFiles.readUpForSiteChanged("L", this.siteCode);
	}

	// Find MHA type for all sectors, depending how many sectors there are.
	public void setMhaType(InputFiles inputFiles) {
		this.mhaTypeS1 = inputFiles.readParameterFromCommissioningReport(inputFiles.getUmtsCrFile(), "MHA type:");
		if (this.mhaTypeS1 != null) {
			if (2 <= this.numOfSectors) {
				this.mhaTypeS2 = this.mhaTypeS1;
			}
			if (3 <= this.numOfSectors) {
				this.mhaTypeS3 = this.mhaTypeS1;
			}
			if (4 <= this.numOfSectors) {
				this.mhaTypeS4 = this.mhaTypeS1;
			}
		}
	}

	@Override
	public String getMhaTypeS1() {
		return this.mhaTypeS1;
	}

	@Override
	public String getMhaTypeS2() {
		return this.mhaTypeS2;
	}

	@Override
	public String getMhaTypeS3() {
		return this.mhaTypeS3;
	}

	@Override
	public String getMhaTypeS4() {
		return this.mhaTypeS4;
	}

	// Find RET type for all sectors, depending how many sectors there are.
	public void setRttaType(InputFiles inputFiles) {
		this.rtaaTypeS1 = inputFiles.readRetInfoFromCommissioningReport(inputFiles.getUmtsCrFile());
		if (this.rtaaTypeS1 != null) {
			if (2 <= this.numOfSectors) {
				this.rtaaTypeS2 = this.rtaaTypeS1;
			}
			if (3 <= this.numOfSectors) {
				this.rtaaTypeS3 = this.rtaaTypeS1;
			}
			if (4 <= this.numOfSectors) {
				this.rtaaTypeS4 = this.rtaaTypeS1;
			}
		}
	}

	@Override
	public String getRtaaTypeS1() {
		return this.rtaaTypeS1;
	}

	@Override
	public String getRtaaTypeS2() {
		return this.rtaaTypeS2;
	}

	@Override
	public String getRtaaTypeS3() {
		return this.rtaaTypeS3;
	}

	@Override
	public String getRtaaTypeS4() {
		return this.rtaaTypeS4;
	}

	// Find feeder type for all sectors, depending how many sectors there are.
	public void setFeederSize(FrontWindow fw) {
		String feederSize = fw.getFeederSize();
		if (feederSize.equals("FIBER")) {
			if (this.numOfSectors >= 1) {
				this.feederSizeS1 = feederSize;
			}
			if (this.numOfSectors >= 2) {
				this.feederSizeS2 = feederSize;
			}
			if (this.numOfSectors >= 3) {
				this.feederSizeS3 = feederSize;
			}
			if (this.numOfSectors >= 4) {
				this.feederSizeS4 = feederSize;
			}
		} else if (feederSize.equals("1/2\"")) {
			if (this.numOfSectors >= 1) {
				this.feederSizeS1 = feederSize;
			}
			if (this.numOfSectors >= 2) {
				this.feederSizeS2 = feederSize;
			}
			if (this.numOfSectors >= 3) {
				this.feederSizeS3 = feederSize;
			}
			if (this.numOfSectors >= 4) {
				this.feederSizeS4 = feederSize;
			}
		} else if (feederSize.equals("7/8\"")) {
			if (this.numOfSectors >= 1) {
				this.feederSizeS1 = feederSize;
			}
			if (this.numOfSectors >= 2) {
				this.feederSizeS2 = feederSize;
			}
			if (this.numOfSectors >= 3) {
				this.feederSizeS3 = feederSize;
			}
			if (this.numOfSectors >= 4) {
				this.feederSizeS4 = feederSize;
			}
		} else {
			if (this.numOfSectors >= 1) {
				this.feederSizeS1 = "1 1/4\"";
			}
			if (this.numOfSectors >= 2) {
				this.feederSizeS2 = "1 1/4\"";
			}
			if (this.numOfSectors >= 3) {
				this.feederSizeS3 = "1 1/4\"";
			}
			if (this.numOfSectors >= 4) {
				this.feederSizeS4 = "1 1/4\"";
			}
		}
	}

	@Override
	public String getFeederSizeS1() {
		return this.feederSizeS1;
	}

	@Override
	public String getFeederSizeS2() {
		return this.feederSizeS2;
	}

	@Override
	public String getFeederSizeS3() {
		return this.feederSizeS3;
	}

	@Override
	public String getFeederSizeS4() {
		return this.feederSizeS4;
	}

	// Find fiber length for all sectors, depending how many sectors there are.
	public void setFiberLength(FrontWindow fw) {
		String str = fw.getFiberLength();
		if (this.numOfSectors >= 1) {
			this.fiberLengthS1 = str;
		}
		if (this.numOfSectors >= 2) {
			this.fiberLengthS2 = str;
		}
		if (this.numOfSectors >= 3) {
			this.fiberLengthS3 = str;
		}
		if (this.numOfSectors >= 4) {
			this.fiberLengthS4 = str;
		}
	}

	@Override
	public String getFiberLengthS1() {
		return this.fiberLengthS1;
	}

	@Override
	public String getFiberLengthS2() {
		return this.fiberLengthS2;
	}

	@Override
	public String getFiberLengthS3() {
		return this.fiberLengthS3;
	}

	@Override
	public String getFiberLengthS4() {
		return this.fiberLengthS4;
	}

	public void setDistributeJumperLengthS1(FrontWindow fw) {
		this.distributeJumperLengthS1 = fw.getJumperLengthS1();
	}

	@Override
	public String getDistributeJumperLengthS1() {
		return this.distributeJumperLengthS1;
	}

	public void setDistributeJumperLengthS2(FrontWindow fw) {
		this.distributeJumperLengthS2 = fw.getJumperLengthS2();
	}

	@Override
	public String getDistributeJumperLengthS2() {
		return this.distributeJumperLengthS2;
	}

	public void setDistributeJumperLengthS3(FrontWindow fw) {
		this.distributeJumperLengthS3 = fw.getJumperLengthS3();
	}

	@Override
	public String getDistributeJumperLengthS3() {
		return this.distributeJumperLengthS3;
	}

	public void setDistributeJumperLengthS4(FrontWindow fw) {
		this.distributeJumperLengthS4 = fw.getJumperLengthS4();
	}

	@Override
	public String getDistributeJumperLengthS4() {
		return this.distributeJumperLengthS4;
	}

	// Find feeder length for all sectors, depending how many sectors there are.
	public void setFeederLength(FrontWindow fw) {
		String str = fw.getFeederLength();
		if (this.numOfSectors >= 1) {
			this.feederLengthS1 = str;
		}
		if (this.numOfSectors >= 2) {
			this.feederLengthS2 = str;
		}
		if (this.numOfSectors >= 3) {
			this.feederLengthS3 = str;
		}
		if (this.numOfSectors >= 4) {
			this.feederLengthS4 = str;
		}
	}

	@Override
	public String getFeederLengthS1() {
		return this.feederLengthS1;
	}

	@Override
	public String getFeederLengthS2() {
		return this.feederLengthS2;
	}

	@Override
	public String getFeederLengthS3() {
		return this.feederLengthS3;
	}

	@Override
	public String getFeederLengthS4() {
		return this.feederLengthS4;
	}

	// Find jumper at BTS length for all sectors, depending how many sectors
	// there are.
	public void setJumperAtBtsLen(FrontWindow fw) {
		String str = fw.getJumperAtBtsLength();
		if (this.numOfSectors >= 1) {
			this.jumperAtBtsLenS1 = str;
		}
		if (this.numOfSectors >= 2) {
			this.jumperAtBtsLenS2 = str;
		}
		if (this.numOfSectors >= 3) {
			this.jumperAtBtsLenS3 = str;
		}
		if (this.numOfSectors >= 4) {
			this.jumperAtBtsLenS4 = str;
		}
	}

	@Override
	public String getJumperAtBtsLenS1() {
		return this.jumperAtBtsLenS1;
	}

	@Override
	public String getJumperAtBtsLenS2() {
		return this.jumperAtBtsLenS2;
	}

	@Override
	public String getJumperAtBtsLenS3() {
		return this.jumperAtBtsLenS3;
	}

	@Override
	public String getJumperAtBtsLenS4() {
		return this.jumperAtBtsLenS4;
	}

	// Find jumper before BTS length for all sectors, depending how many sectors
	// there are.
	public void setJumpersBeforeMhaLen(FrontWindow fw) {
		String str = fw.getJumperBeforeMhaLength();
		if (this.numOfSectors >= 1) {
			this.jumperBeforeMhaLenS1 = str;
		}
		if (this.numOfSectors >= 2) {
			this.jumperBeforeMhaLenS2 = str;
		}
		if (this.numOfSectors >= 3) {
			this.jumperBeforeMhaLenS3 = str;
		}
		if (this.numOfSectors >= 4) {
			this.jumperBeforeMhaLenS4 = str;
		}
	}

	@Override
	public String getJumperBeforeMhaLenS1() {
		return this.jumperBeforeMhaLenS1;
	}

	@Override
	public String getJumperBeforeMhaLenS2() {
		return this.jumperBeforeMhaLenS2;
	}

	@Override
	public String getJumperBeforeMhaLenS3() {
		return this.jumperBeforeMhaLenS3;
	}

	@Override
	public String getJumperBeforeMhaLenS4() {
		return this.jumperBeforeMhaLenS4;
	}

	// Find jumper after BTS length for all sectors, depending how many sectors
	// there are.
	public void setJumpersAfterMhaLen(FrontWindow fw) {
		String str = fw.getJumperAfterMhaLength();
		if (this.numOfSectors >= 1) {
			this.jumperAfterMhaLenS1 = str;
		}
		if (this.numOfSectors >= 2) {
			this.jumperAfterMhaLenS2 = str;
		}
		if (this.numOfSectors >= 3) {
			this.jumperAfterMhaLenS3 = str;
		}
		if (this.numOfSectors >= 4) {
			this.jumperAfterMhaLenS4 = str;
		}
	}

	@Override
	public String getJumperAfterMhaLenS1() {
		return this.jumperAfterMhaLenS1;
	}

	@Override
	public String getJumperAfterMhaLenS2() {
		return this.jumperAfterMhaLenS2;
	}

	@Override
	public String getJumperAfterMhaLenS3() {
		return this.jumperAfterMhaLenS3;
	}

	@Override
	public String getJumperAfterMhaLenS4() {
		return this.jumperAfterMhaLenS4;
	}

	// Find number of jumper per sector, depending how many sectors there are.
	public void setJumperPerSectorNum(FrontWindow fw) {
		String str = fw.getJumpersPerSector();
		if (this.numOfSectors >= 1) {
			this.jumperPerSectorS1 = str;
		}
		if (this.numOfSectors >= 2) {
			this.jumperPerSectorS2 = str;
		}
		if (this.numOfSectors >= 3) {
			this.jumperPerSectorS3 = str;
		}
		if (this.numOfSectors >= 4) {
			this.jumperPerSectorS4 = str;
		}
	}

	@Override
	public String getJumperPerSectorS1() {
		return this.jumperPerSectorS1;
	}

	@Override
	public String getJumperPerSectorS2() {
		return this.jumperPerSectorS2;
	}

	@Override
	public String getJumperPerSectorS3() {
		return this.jumperPerSectorS3;
	}

	@Override
	public String getJumperPerSectorS4() {
		return this.jumperPerSectorS4;
	}

	// Find number of grounds per sector, depending how many sectors there are.
	public void setGroungNum(FrontWindow fw) {
		String str = fw.getGroundsNo();
		if (this.numOfSectors >= 1) {
			this.earthKitNoS1 = str;
		}
		if (this.numOfSectors >= 2) {
			this.earthKitNoS2 = str;
		}
		if (this.numOfSectors >= 3) {
			this.earthKitNoS3 = str;
		}
		if (this.numOfSectors >= 4) {
			this.earthKitNoS4 = str;
		}
	}

	@Override
	public String getEarthKitNoS1() {
		return this.earthKitNoS1;
	}

	@Override
	public String getEarthKitNoS2() {
		return this.earthKitNoS2;
	}

	@Override
	public String getEarthKitNoS3() {
		return this.earthKitNoS3;
	}

	@Override
	public String getEarthKitNoS4() {
		return this.earthKitNoS4;
	}

	@Override
	public String getInstalationType() {
		return this.instalationType;
	}

	public void setInstalationType(FrontWindow fw) {
		this.instalationType = fw.getInstalationTypeStr();
	}

	@Override
	public String getBtsCabinetType() {
		return this.btsCabinetType;
	}

	public void setBtsCabinetType(FrontWindow fw) {
		this.btsCabinetType = fw.getBtsCabinet();
	}

	public void setArchitectureType(FrontWindow fw) {
		this.architectureType = fw.getArchitectureTypeStr();
	}

	@Override
	public String getArchitectureType() {
		return this.architectureType;
	}

	@Override
	public String getSysModule1Type() {
		return this.sysModule1Type;
	}

	public void setSysModule1Type(InputFiles inputFiles) {
		this.sysModule1Type = inputFiles.readSystemModuleInfoFromCommissionReport(inputFiles.getUmtsCrFile());
	}

	@Override
	public String getSysModule1Loc() {
		return this.sysModule1Loc;
	}

	public void setSysModule1Loc(FrontWindow fw) {
		this.sysModule1Loc = fw.getAssembleType();
	}

	@Override
	public String getTransModuleType() {
		return this.transModuleType;
	}

	public void setTransModuleType(InputFiles inputFiles) {
		this.transModuleType = inputFiles.readTransportModuleInfoFrom3gCommissionReport();
	}

	public void setE1LinesNo(InputFiles inputFiles) {
		this.e1LinesNo = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getUmtsCrFile(),
				"Physical layer configuration", "IF");
	}

	@Override
	public int getE1LinesNo() {
		return this.e1LinesNo;
	}

	// Because Gb electrical Eth can have different labels depending of module
	// type, we find out how menu interface exist for each label and then
	// collect them as final result.
	public void setGbEthElectLinesNo(InputFiles inputFiles) {
		int x1 = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getUmtsCrFile(),
				"Physical layer configuration", "EIF 2");
		int x2 = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getUmtsCrFile(),
				"Physical layer configuration", "EIF 3");
		int x3 = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getUmtsCrFile(),
				"Physical layer configuration", "FTIF 3");
		int x4 = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getUmtsCrFile(),
				"Physical layer configuration", "FTIF 4");
		this.gbEthElectLinesNo = x1 + x2 + x3 + x4;
	}

	@Override
	public int getGbEthElectLinesNo() {
		return this.gbEthElectLinesNo;
	}

	// Because Gb optical Eth can have different labels depending of module
	// type, we find out how menu interface exist for each label and then
	// collect them as final result.
	public void setGbEthOptLinesNo(InputFiles inputFiles) {
		int x1 = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getUmtsCrFile(),
				"Physical layer configuration", "EIF 1");
		int x2 = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getUmtsCrFile(),
				"Physical layer configuration", "FTIF 1");
		int x3 = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getUmtsCrFile(),
				"Physical layer configuration", "FTIF 2");
		this.gbEthOptLinesNo = x1 + x2 + x3;
	}

	@Override
	public int getGbEthOptLinesNo() {
		return this.gbEthOptLinesNo;
	}

	// First collect all RF modules from commissioning report and then divide
	// them per sectors.
	public void setRfModulesType(InputFiles inputFiles) {
		this.rfModulesType = inputFiles.readCommReportForRfModuleType(inputFiles.getUmtsCrFile(), "Module locations",
				"F");
		this.rfModule1Type = this.rfModulesType[0];
		this.rfModule2Type = this.rfModulesType[1];
		this.rfModule3Type = this.rfModulesType[2];
	}

	public String[] getRfModulesType() {
		return this.rfModulesType;
	}

	@Override
	public String getRfModule1Type() {
		return this.rfModule1Type;
	}

	@Override
	public String getRfModule2Type() {
		return this.rfModule2Type;
	}

	@Override
	public String getRfModule3Type() {
		return this.rfModule3Type;
	}

	public void setRfModuleLoc(FrontWindow fw) {
		String rfModuleLoc = fw.getAssembleRFType();
		this.rfModule1Loc = rfModuleLoc;
		this.rfModule2Loc = rfModuleLoc;
		this.rfModule3Loc = rfModuleLoc;
	}

	@Override
	public String getRfModule1Loc() {
		return this.rfModule1Loc;
	}

	@Override
	public String getRfModule2Loc() {
		return this.rfModule2Loc;
	}

	@Override
	public String getRfModule3Loc() {
		return this.rfModule3Loc;
	}

	public void setPowSupplyType(FrontWindow fw) {
		this.powSupplyType = fw.getPowSupplyType();
	}

	@Override
	public String getPowSupplyType() {
		return this.powSupplyType;
	}

	// For different chose of power supply type set default values for other
	// relevant parameters.
	public void setPowerSypplyParameters() {
		if (this.powSupplyType.equals("Eltek")) {
			this.rectifierType = "Flatpack 2HE";
			this.rectifierPower = "2kW";
			this.rectifierQty = "3";
			this.batteryType = "PowerSafe12V92F";
			this.batteryCap = "92Ah";
			this.batQty = "2";
			this.current = "5";
			this.voltage = "-53.53";
		} else if (this.powSupplyType.equals("FPMA")) {
			this.rectifierType = "FPAA";
			this.rectifierPower = "";
			this.rectifierQty = "2";
			this.batteryType = "FPBA";
			this.batteryCap = "";
			this.batQty = "2";
			this.current = "";
			this.voltage = "";
		} else if (this.powSupplyType.equals("FPRA")) {
			this.rectifierType = "";
			this.rectifierPower = "";
			this.rectifierQty = "2";
			this.batteryType = "";
			this.batteryCap = "";
			this.batQty = "";
			this.current = "-12";
			this.voltage = "-53.38";
		} else if (this.powSupplyType.equals("Telkom")) {
			this.rectifierType = "IM16";
			this.rectifierPower = "2kW";
			this.rectifierQty = "3";
			this.batteryType = "MONBAT 12MVR100";
			this.batteryCap = "100Ah";
			this.batQty = "2";
			this.current = "2.4";
			this.voltage = "54.2";
		} else if (this.powSupplyType.equals("PBC")) {
			this.rectifierType = "";
			this.rectifierPower = "";
			this.rectifierQty = "";
			this.batteryType = "North Star NSB 13";
			this.batteryCap = "12Ah";
			this.batQty = "2";
			this.current = "";
			this.voltage = "";
		} else {
			this.rectifierType = "";
			this.rectifierPower = "";
			this.rectifierQty = "";
			this.batteryType = "";
			this.batteryCap = "";
			this.batQty = "";
			this.current = "";
			this.voltage = "";
		}
	}

	@Override
	public String getRectifierType() {
		return this.rectifierType;
	}

	@Override
	public String getRectifierPower() {
		return this.rectifierPower;
	}

	@Override
	public String getRectifierQty() {
		return this.rectifierQty;
	}

	@Override
	public String getBatteryType() {
		return this.batteryType;
	}

	@Override
	public String getBatteryCap() {
		return this.batteryCap;
	}

	@Override
	public String getBatQty() {
		return this.batQty;
	}

	@Override
	public String getCurrent() {
		return this.current;
	}

	@Override
	public String getVoltage() {
		return this.voltage;
	}

	public void setSwVersion(InputFiles inputFiles) {
		this.swVersion = inputFiles.readParameterFromCommissioningReport(inputFiles.getUmtsCrFile(),
				"SW package version:");
	}

	@Override
	public String getSwVersion() {
		return this.swVersion;
	}

	// Find TRS and RNC addresses. According to TRS set BTS and RCUA addresses.
	public void setIpAdresses(InputFiles inputFiles) {
		this.trsIp = inputFiles.readCommReportForIpAdr(inputFiles.getUmtsCrFile(), "IP addresses", "TRS:");
		this.trsIp = this.trsIp.substring(0, this.trsIp.indexOf("/") - 1);
		this.rncIp = inputFiles.readCommReportForIpAdr(inputFiles.getUmtsCrFile(), "IP addresses", "RNC:");
		if (this.trsIp != "Dummy_Data") {
			int helper = Integer.parseInt(getTrsIp().substring(getTrsIp().lastIndexOf('.') + 1));
			helper += 1;
			this.nodebIp = getTrsIp().substring(0, getTrsIp().lastIndexOf('.') + 1) + helper;
			helper += 1;
			this.rcuaIp = getNodebIp().substring(0, getTrsIp().lastIndexOf('.') + 1) + helper;
		}
	}

	@Override
	public String getTrsIp() {
		return this.trsIp;
	}

	@Override
	public String getNodebIp() {
		return this.nodebIp;
	}

	@Override
	public String getRncIp() {
		return this.rncIp;
	}

	@Override
	public String getRcuaIp() {
		return this.rcuaIp;
	}

	// Find all cell IDs from commissioning report file.
	public void setCellId(InputFiles inputFiles) {
		inputFiles.read3gCommReportForCellIds("Local cell resources", "Local cell settings");
		cellIdGroup = inputFiles.set3gCellIds(inputFiles.getResultCellIds());
	}

	@Override
	public String[] getCellIdS1() {
		return this.cellIdS1;
	}

	@Override
	public String[] getCellIdS2() {
		return this.cellIdS2;
	}

	@Override
	public String[] getCellIdS3() {
		return this.cellIdS3;
	}

	@Override
	public String[] getCellIdS4() {
		return this.cellIdS4;
	}

	// Find all UARFCNs.
	public void setUarfcns(InputFiles inputFiles) {
		uarfcnGroup = inputFiles.read3gCommReportForUarfcn("Local cell group:", "Baseband (BB) allocation",
				cellIdGroup);
	}

	@Override
	public int[] getUarfcnS1() {
		return this.uarfcnS1;
	}

	@Override
	public int[] getUarfcnS2() {
		return this.uarfcnS2;
	}

	@Override
	public int[] getUarfcnS3() {
		return this.uarfcnS3;
	}

	@Override
	public int[] getUarfcnS4() {
		return this.uarfcnS4;
	}

	// Find which external alarms are used, what is their name, polarity and
	// priority.
	public void setExtAl(InputFiles inputFiles) {
		this.extAl1Str = inputFiles.read3gCommReportForExtAl("External fault lines", "1");
		this.extAl2Str = inputFiles.read3gCommReportForExtAl("External fault lines", "2");
		this.extAl3Str = inputFiles.read3gCommReportForExtAl("External fault lines", "3");
		this.extAl4Str = inputFiles.read3gCommReportForExtAl("External fault lines", "4");
		this.extAl5Str = inputFiles.read3gCommReportForExtAl("External fault lines", "5");
		this.extAl6Str = inputFiles.read3gCommReportForExtAl("External fault lines", "6");
		this.extAl7Str = inputFiles.read3gCommReportForExtAl("External fault lines", "7");
		this.extAl8Str = inputFiles.read3gCommReportForExtAl("External fault lines", "8");
		this.extAl9Str = inputFiles.read3gCommReportForExtAl("External fault lines", "9");
		this.extAl10Str = inputFiles.read3gCommReportForExtAl("External fault lines", "10");
	}

	// Separate different parameters of alarm to appropriate variable.
	@Override
	public String getExtAl1() {
		return this.extAl1Str[0];
	}

	@Override
	public String getExtAl2() {
		return this.extAl2Str[0];
	}

	@Override
	public String getExtAl3() {
		return this.extAl3Str[0];
	}

	@Override
	public String getExtAl4() {
		return this.extAl4Str[0];
	}

	@Override
	public String getExtAl5() {
		return this.extAl5Str[0];
	}

	@Override
	public String getExtAl6() {
		return this.extAl6Str[0];
	}

	@Override
	public String getExtAl7() {
		return this.extAl7Str[0];
	}

	@Override
	public String getExtAl8() {
		return this.extAl8Str[0];
	}

	@Override
	public String getExtAl9() {
		return this.extAl9Str[0];
	}

	@Override
	public String getExtAl10() {
		return this.extAl10Str[0];
	}

	@Override
	public String getExtAl1Name() {
		return this.extAl1Str[1];
	}

	@Override
	public String getExtAl2Name() {
		return this.extAl2Str[1];
	}

	@Override
	public String getExtAl3Name() {
		return this.extAl3Str[1];
	}

	@Override
	public String getExtAl4Name() {
		return this.extAl4Str[1];
	}

	@Override
	public String getExtAl5Name() {
		return this.extAl5Str[1];
	}

	@Override
	public String getExtAl6Name() {
		return this.extAl6Str[1];
	}

	@Override
	public String getExtAl7Name() {
		return this.extAl7Str[1];
	}

	@Override
	public String getExtAl8Name() {
		return this.extAl8Str[1];
	}

	@Override
	public String getExtAl9Name() {
		return this.extAl9Str[1];
	}

	@Override
	public String getExtAl10Name() {
		return this.extAl10Str[1];
	}

	@Override
	public String getExtAl1Pol() {
		return this.extAl1Str[2];
	}

	@Override
	public String getExtAl2Pol() {
		return this.extAl2Str[2];
	}

	@Override
	public String getExtAl3Pol() {
		return this.extAl3Str[2];
	}

	@Override
	public String getExtAl4Pol() {
		return this.extAl4Str[2];
	}

	@Override
	public String getExtAl5Pol() {
		return this.extAl5Str[2];
	}

	@Override
	public String getExtAl6Pol() {
		return this.extAl6Str[2];
	}

	@Override
	public String getExtAl7Pol() {
		return this.extAl7Str[2];
	}

	@Override
	public String getExtAl8Pol() {
		return this.extAl8Str[2];
	}

	@Override
	public String getExtAl9Pol() {
		return this.extAl9Str[2];
	}

	@Override
	public String getExtAl10Pol() {
		return this.extAl10Str[2];
	}

	@Override
	public String getExtAl1Sev() {
		return this.extAl1Str[3];
	}

	@Override
	public String getExtAl2Sev() {
		return this.extAl2Str[3];
	}

	@Override
	public String getExtAl3Sev() {
		return this.extAl3Str[3];
	}

	@Override
	public String getExtAl4Sev() {
		return this.extAl4Str[3];
	}

	@Override
	public String getExtAl5Sev() {
		return this.extAl5Str[3];
	}

	@Override
	public String getExtAl6Sev() {
		return this.extAl6Str[3];
	}

	@Override
	public String getExtAl7Sev() {
		return this.extAl7Str[3];
	}

	@Override
	public String getExtAl8Sev() {
		return this.extAl8Str[3];
	}

	@Override
	public String getExtAl9Sev() {
		return this.extAl9Str[3];
	}

	@Override
	public String getExtAl10Sev() {
		return this.extAl10Str[3];
	}

	public void setCrFile(InputFiles inputFiles) {
		String str = inputFiles.getUmtsCrFile().toString();
		this.crFile = str.substring(str.indexOf("\\", 5) + 1);
	}

	@Override
	public String getCrFile() {
		return this.crFile;
	}

	public void setCommFile(InputFiles inputFiles) {
		String str = inputFiles.getCommFile().toString();
		this.commFile = str.substring(str.indexOf("\\", 5) + 1);
	}

	@Override
	public String getCommFile() {
		return this.commFile;
	}

	public void setSiteInfo(InputFiles inputFiles) {
		if (inputFiles.getSiteInfo() != null) {
			String str = inputFiles.getSiteInfo().toString();
			this.siteInfo = str.substring(str.indexOf("\\", 5) + 1);
		}
	}

	@Override
	public String getSiteInfo() {
		return this.siteInfo;
	}

	@Override
	public String getSiteConfig() {
		return this.siteConfig;
	}

	// Find out site configuration (eg. 2+2+3). Iterate through all sectors and
	// count number of cell IDs in it.
	@Override
	public void setSiteConfig() {
		int x1 = 0, x2 = 0, x3 = 0, x4 = 0;
		for (int i = 0; i < 3; i++) {
			if (this.cellIdS1[i] != null) {
				x1++;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (this.cellIdS2[i] != null) {
				x2++;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (this.cellIdS3[i] != null) {
				x3++;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (this.cellIdS4[i] != null) {
				x4++;
			}
		}
		if (x1 != 0) { // Depending of result create configuration string.
			this.siteConfig = String.valueOf(x1);
		}
		if (x2 != 0) {
			this.siteConfig = x1 + "+" + x2;
		}
		if (x3 != 0) {
			this.siteConfig = x1 + "+" + x2 + "+" + x3;
		}
		if (x4 != 0) {
			this.siteConfig = x1 + "+" + x2 + "+" + x3 + "+" + x4;
		}
	}

	@Override
	public int getNumOfSectors() {
		return this.numOfSectors;
	}

	@Override
	public void setNumOfSectors(AffectedSite aSite) {
		if (aSite.antenaTypeS1 != null && aSite.antenaTypeS1.length() != 0) {
			this.numOfSectors = 1;
		}
		if (aSite.antenaTypeS2 != null && aSite.antenaTypeS2.length() != 0) {
			this.numOfSectors = 2;
		}
		if (aSite.antenaTypeS3 != null && aSite.antenaTypeS3.length() != 0) {
			this.numOfSectors = 3;
		}
		if (aSite.antenaTypeS4 != null && aSite.antenaTypeS4.length() != 0) {
			this.numOfSectors = 4;
		}
	}

	@Override
	public String[][] getCellIdGroup() {
		return this.cellIdGroup;
	}

	@Override
	public int[][] getUarfcnGroup() {
		return this.uarfcnGroup;
	}

	// Next methods are characteristic for LTE sites so this class doesn't
	// provide method body for them.
	@Override
	public String getOverTransModule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOverE1LinesNo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getOverGbEthElectLinesNo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getOverGbEthOptLinesNo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getOverTransSiteCode() {
		// TODO Auto-generated method stub
		return null;
	}

}