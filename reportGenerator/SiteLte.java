/*
 * This is LTE site class. All information concern LTE are first collected here and then populate to
 * final report.
 */

package reportGenerator;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SiteLte implements Site {
	private String siteCode, siteName, date, mhaTypeS1, mhaTypeS2, mhaTypeS3, mhaTypeS4, rtaaTypeS1, rtaaTypeS2,
			rtaaTypeS3, rtaaTypeS4, feederSizeS1, feederSizeS2, feederSizeS3, feederSizeS4, feederLengthS1,
			feederLengthS2, feederLengthS3, feederLengthS4, sysModule1Type, sysModule1Loc, fiberLengthS1, fiberLengthS2,
			fiberLengthS3, fiberLengthS4, jumperAtBtsLenS1, jumperAtBtsLenS2, jumperAtBtsLenS3, jumperAtBtsLenS4,
			jumperBeforeMhaLenS1, jumperBeforeMhaLenS2, jumperBeforeMhaLenS3, jumperBeforeMhaLenS4, jumperAfterMhaLenS1,
			jumperAfterMhaLenS2, jumperAfterMhaLenS3, jumperAfterMhaLenS4, jumperPerSectorS1, jumperPerSectorS2,
			jumperPerSectorS3, jumperPerSectorS4, earthKitNoS1, earthKitNoS2, earthKitNoS3, earthKitNoS4,
			distributeJumperLengthS1, distributeJumperLengthS2, distributeJumperLengthS3, distributeJumperLengthS4,
			instalationType, btsCabinetType, architectureType, rfModule1Type, rfModule1Loc, rfModule2Type, rfModule2Loc,
			rfModule3Type, rfModule3Loc, powSupplyType, rectifierType, rectifierPower, rectifierQty, batteryType,
			batteryCap, batQty, current, voltage, swVersion, crFile, commFile, siteInfo, overTransSiteCode,
			overTransModule;
	private int e1LinesNo, gbEthElectLinesNo, gbEthOptLinesNo, overE1LinesNo, overGbEthElectLinesNo,
			overGbEthOptLinesNo, numOfSectors;
	private AffectedSite gsm9Changed, gsm18Changed, umts21Changed, lte18Changed;
	private String[] rfModulesType;

	@Override
	public String getSiteCode() {
		return this.siteCode;
	}

	@Override
	public void setSiteCode(InputFiles inputFiles) {
		this.siteCode = inputFiles.getSiteCode4gStr();
	}

	@Override
	public String getSiteName() {
		return this.siteName;
	}

	@Override
	public void setSiteName(InputFiles inputFiles) {
		this.siteName = inputFiles.readParameterFromCommissioningReport(inputFiles.getLteCrFile(), "Description:");
	}

	@Override
	public String getDate() {
		return this.date;
	}

	// Set date work for input types dd.MM.YYYY and YYYY.MM.dd .
	@Override
	public void setDate(InputFiles inputFiles) {
		String rowDate, dd, MM, yy;
		rowDate = inputFiles.readParameterFromCommissioningReport(inputFiles.getLteCrFile(), "Date:");
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
		this.mhaTypeS1 = inputFiles.readParameterFromCommissioningReport(inputFiles.getLteCrFile(), "MHA type:");
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
		if (getGsm9Changed() != null && getGsm18Changed() != null && getUmts21Changed() != null
				&& getLte18Changed() != null) {
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
		} else {
			this.rtaaTypeS1 = inputFiles.readRetInfoFromCommissioningReport(inputFiles.getLteCrFile());
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
	public void setFeederSize(String feederSize) {
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
	public void setFiberLength(String fiberLen) {
		if (this.numOfSectors >= 1) {
			this.fiberLengthS1 = fiberLen;
		}
		if (this.numOfSectors >= 2) {
			this.fiberLengthS2 = fiberLen;
		}
		if (this.numOfSectors >= 3) {
			this.fiberLengthS3 = fiberLen;
		}
		if (this.numOfSectors >= 4) {
			this.fiberLengthS4 = fiberLen;
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

	public void setDistributeJumperLengthS1(String distributeJumperLengthS1) {
		this.distributeJumperLengthS1 = distributeJumperLengthS1;
	}

	@Override
	public String getDistributeJumperLengthS1() {
		return this.distributeJumperLengthS1;
	}

	public void setDistributeJumperLengthS2(String distributeJumperLengthS2) {
		this.distributeJumperLengthS2 = distributeJumperLengthS2;
	}

	@Override
	public String getDistributeJumperLengthS2() {
		return this.distributeJumperLengthS2;
	}

	public void setDistributeJumperLengthS3(String distributeJumperLengthS3) {
		this.distributeJumperLengthS3 = distributeJumperLengthS3;
	}

	@Override
	public String getDistributeJumperLengthS3() {
		return this.distributeJumperLengthS3;
	}

	public void setDistributeJumperLengthS4(String distributeJumperLengthS4) {
		this.distributeJumperLengthS4 = distributeJumperLengthS4;
	}

	@Override
	public String getDistributeJumperLengthS4() {
		return this.distributeJumperLengthS4;
	}

	// Find feeder length for all sectors, depending how many sectors there are.
	public void setFeederLength(String feederLen) {
		if (this.numOfSectors >= 1) {
			this.feederLengthS1 = feederLen;
		}
		if (this.numOfSectors >= 2) {
			this.feederLengthS2 = feederLen;
		}
		if (this.numOfSectors >= 3) {
			this.feederLengthS3 = feederLen;
		}
		if (this.numOfSectors >= 4) {
			this.feederLengthS4 = feederLen;
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
	public void setJumperAtBtsLen(String jumperAtBtsLen) {
		if (this.numOfSectors >= 1) {
			this.jumperAtBtsLenS1 = jumperAtBtsLen;
		}
		if (this.numOfSectors >= 2) {
			this.jumperAtBtsLenS2 = jumperAtBtsLen;
		}
		if (this.numOfSectors >= 3) {
			this.jumperAtBtsLenS3 = jumperAtBtsLen;
		}
		if (this.numOfSectors >= 4) {
			this.jumperAtBtsLenS4 = jumperAtBtsLen;
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
	public void setJumpersBeforeMhaLen(String jumperBeforeMhaLen) {
		if (this.numOfSectors >= 1) {
			this.jumperBeforeMhaLenS1 = jumperBeforeMhaLen;
		}
		if (this.numOfSectors >= 2) {
			this.jumperBeforeMhaLenS2 = jumperBeforeMhaLen;
		}
		if (this.numOfSectors >= 3) {
			this.jumperBeforeMhaLenS3 = jumperBeforeMhaLen;
		}
		if (this.numOfSectors >= 4) {
			this.jumperBeforeMhaLenS4 = jumperBeforeMhaLen;
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
	public void setJumpersAfterMhaLen(String jumperAfterMhaLen) {
		if (this.numOfSectors >= 1) {
			this.jumperAfterMhaLenS1 = jumperAfterMhaLen;
		}
		if (this.numOfSectors >= 2) {
			this.jumperAfterMhaLenS2 = jumperAfterMhaLen;
		}
		if (this.numOfSectors >= 3) {
			this.jumperAfterMhaLenS3 = jumperAfterMhaLen;
		}
		if (this.numOfSectors >= 4) {
			this.jumperAfterMhaLenS4 = jumperAfterMhaLen;
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
	public void setJumperPerSectorNum(String jumpersPerSector) {
		if (this.numOfSectors >= 1) {
			this.jumperPerSectorS1 = jumpersPerSector;
		}
		if (this.numOfSectors >= 2) {
			this.jumperPerSectorS2 = jumpersPerSector;
		}
		if (this.numOfSectors >= 3) {
			this.jumperPerSectorS3 = jumpersPerSector;
		}
		if (this.numOfSectors >= 4) {
			this.jumperPerSectorS4 = jumpersPerSector;
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
	public void setGroungNum(String groundsNo) {
		if (this.numOfSectors >= 1) {
			this.earthKitNoS1 = groundsNo;
		}
		if (this.numOfSectors >= 2) {
			this.earthKitNoS2 = groundsNo;
		}
		if (this.numOfSectors >= 3) {
			this.earthKitNoS3 = groundsNo;
		}
		if (this.numOfSectors >= 4) {
			this.earthKitNoS4 = groundsNo;
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

	public void setInstalationType(String instalationType) {
		this.instalationType = instalationType;
	}

	@Override
	public String getBtsCabinetType() {
		return this.btsCabinetType;
	}

	public void setBtsCabinetType(String btsCabinetType) {
		this.btsCabinetType = btsCabinetType;
	}

	public void setArchitectureType(String architectureType) {
		this.architectureType = architectureType;
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
		this.sysModule1Type = inputFiles.readSystemModuleInfoFromCommissionReport(inputFiles.getLteCrFile());
	}

	@Override
	public String getSysModule1Loc() {
		return this.sysModule1Loc;
	}

	public void setSysModule1Loc(String sysModule1Loc) {
		this.sysModule1Loc = sysModule1Loc;
	}

	// When there is no "Over" in method name that mean that method finds lines
	// number for station itself, but when method name contain "Over" then it is
	// number of lines for BTS that station use to access network.
	public void setE1LinesNo(InputFiles inputFiles) {
		this.e1LinesNo = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getUmtsCrFile(),
				"Physical layer configuration", "IF");
	}

	@Override
	public int getE1LinesNo() {
		return this.e1LinesNo;
	}

	public void setGbEthElectLinesNo(InputFiles inputFiles) {
		this.gbEthElectLinesNo = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getUmtsCrFile(),
				"Physical layer configuration", "EIF 1");
	}

	@Override
	public int getGbEthElectLinesNo() {
		return this.gbEthElectLinesNo;
	}

	public void setGbEthOptLinesNo(InputFiles inputFiles) {
		this.gbEthOptLinesNo = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getUmtsCrFile(),
				"Physical layer configuration", "EIF 2");
	}

	@Override
	public int getGbEthOptLinesNo() {
		return this.gbEthOptLinesNo;
	}

	// First collect all RF modules from commissioning report and then divide
	// them per sectors.
	public void setRfModulesType(InputFiles inputFiles) {
		this.rfModulesType = inputFiles.readCommReportForRfModuleType(inputFiles.getLteCrFile(), "Module locations",
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

	public void setRfModuleLoc(String rfModuleLoc) {
		rfModule1Loc = rfModule2Loc = rfModule3Loc = rfModuleLoc;
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

	public void setPowSupplyType(String powSupplyType) {
		this.powSupplyType = powSupplyType;
	}

	@Override
	public String getPowSupplyType() {
		return this.powSupplyType;
	}

	// For different chose of power supply type set default values for other
	// relevant parameters.
	public void setPowerSupplyParameters() {
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
		this.swVersion = inputFiles.readParameterFromCommissioningReport(inputFiles.getLteCrFile(),
				"SW package version:");
	}

	@Override
	public String getSwVersion() {
		return this.swVersion;
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
	public int getNumOfSectors() {
		return this.numOfSectors;
	}

	@Override
	public String getOverTransSiteCode() {
		return this.overTransSiteCode;
	}

	// Set type of station that is used for transmission. All methods that have
	// "Over" in its name are used on station that provide transmission for LTE
	// station.
	public void setOverTransSiteCode(String transmissionType) {
		if (transmissionType.equals("2g")) {
			this.overTransSiteCode = getSiteCode().substring(0, 2) + getSiteCode().substring(3);
		} else {
			this.overTransSiteCode = getSiteCode().replace(getSiteCode().charAt(2), 'U');
		}
	}

	@Override
	public String getOverTransModule() {
		return this.overTransModule;
	}

	public void setOverTransModuleOver3g(InputFiles inputFiles) {
		if (inputFiles.getCrOfTransmissionForLte() != null) {
			this.overTransModule = inputFiles.readTransportModuleInfoFrom3gCommissionReport();
		}
	}

	public void setOverTransModuleOver2g(InputFiles inputFiles) {
		if (inputFiles.getCrOfTransmissionForLte() != null) {
			this.overTransModule = "FIQB";
		}
	}

	// Find out number of E1 lines when UMTS station is used for transmission.
	public void setOverE1LinesNo(InputFiles inputFiles) {
		if (inputFiles.getCrOfTransmissionForLte() != null) {
			this.overE1LinesNo = inputFiles.readCommReportForNoOfTranssmisionLines(
					inputFiles.getCrOfTransmissionForLte(), "Physical layer configuration", "IF");
		}
	}

	@Override
	public int getOverE1LinesNo() {
		return this.overE1LinesNo;
	}

	// Because Gb electrical Eth can have different labels depending of module
	// type, we find out how menu interface exist for each label and then
	// collect them as final result. This is case where UMTS station is used for
	// transmission.
	public void setOverGbEthElectLinesNo(InputFiles inputFiles) {
		if (inputFiles.getCrOfTransmissionForLte() != null) {
			int x1 = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getCrOfTransmissionForLte(),
					"Physical layer configuration", "EIF 2");
			int x2 = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getUmtsCrFile(),
					"Physical layer configuration", "EIF 3");
			int x3 = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getCrOfTransmissionForLte(),
					"Physical layer configuration", "FTIF 3");
			int x4 = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getCrOfTransmissionForLte(),
					"Physical layer configuration", "FTIF 4");
			this.overGbEthElectLinesNo = x1 + x2 + x3 + x4;
		}
	}

	@Override
	public int getOverGbEthElectLinesNo() {
		return this.overGbEthElectLinesNo;
	}

	// Because Gb optical Eth can have different labels depending of module
	// type, we find out how menu interface exist for each label and then
	// collect them as final result. This is case where UMTS station is used for
	// transmission.
	public void setOverGbEthOptLinesNo(InputFiles inputFiles) {
		if (inputFiles.getCrOfTransmissionForLte() != null) {
			int x1 = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getCrOfTransmissionForLte(),
					"Physical layer configuration", "EIF 1");
			int x2 = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getCrOfTransmissionForLte(),
					"Physical layer configuration", "FTIF 1");
			int x3 = inputFiles.readCommReportForNoOfTranssmisionLines(inputFiles.getCrOfTransmissionForLte(),
					"Physical layer configuration", "FTIF 2");
			this.overGbEthOptLinesNo = x1 + x2 + x3;
		}
	}

	@Override
	public int getOverGbEthOptLinesNo() {
		return this.overGbEthOptLinesNo;
	}

	// If GSM station is used for transmission then .xml file (SCF file) is used
	// to collect all information from. This method search .xml for E1 lines
	// number.
	public void parseXmlFileForE1LinesNo(InputFiles inputFiles) {
		int e1num = 0;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // For
																			// .xml
																			// file
																			// we
																			// use
																			// DocumentBuilderFactory
		try {
			DocumentBuilder db = dbf.newDocumentBuilder(); // Create
															// DocumentBuilder
															// from
															// DocumentBuilderFactory.
			Document dom = db.newDocument(); // Create Document from
												// DocumentBuilder.
			dom = db.parse(inputFiles.getCrOfTransmissionForLte()); // Now we
																	// use .xml
																	// to create
																	// Document
																	// (parse
																	// .xml to
																	// Document).
			Element rootEle = dom.getDocumentElement(); // Element is object
														// that we take from
														// .xml file.
			NodeList rootNodeList = rootEle.getElementsByTagName("managedObject"); // Then
																					// from
																					// that
																					// object
																					// we
																					// search
																					// specific
																					// object
																					// (that
																					// is
																					// marked
																					// with
																					// specific
																					// tag
																					// in
																					// .xml).
			if (rootNodeList != null && rootNodeList.getLength() > 0) { // We
																		// can
																		// iterate
																		// through
																		// that
																		// object
																		// if
																		// there
																		// is
																		// more
																		// then
																		// 1
																		// tag.
				for (int i = 0; i < rootNodeList.getLength(); i++) {
					Element el = (Element) rootNodeList.item(i); // Every tag
																	// (item) is
																	// again
																	// object.
					if (el.getAttribute("class").equals("UNIT")) { // If in tag
																	// definition
																	// we have
																	// more
																	// parameters
																	// they are
																	// called
																	// attributes.
																	// In this
																	// case
																	// attribute
																	// "class"
																	// should
																	// have
																	// value
																	// "UNIT".
						NodeList interfaceSettings = el.getElementsByTagName("list"); // Again
																						// in
																						// Element
																						// we
																						// can
																						// have
																						// more
																						// levels
																						// of
																						// tags
																						// and
																						// multiple
																						// items
																						// with
																						// "list"
																						// tag,
																						// so
																						// we
																						// create
																						// new
																						// NodeList
																						// to
																						// iterate
																						// through
																						// all
																						// Elements
																						// with
																						// tag
																						// "list".
						for (int j = 0; j < interfaceSettings.getLength(); j++) {
							Element e1Int = (Element) interfaceSettings.item(j);
							NodeList pList = e1Int.getElementsByTagName("p");
							for (int k = 0; k < pList.getLength(); k++) {
								Element pElement = (Element) pList.item(k);
								if (pElement.getAttribute("name").equals("interfaceInUse")) { // With
																								// this
																								// we
																								// search
																								// for
																								// tag
																								// that
																								// have
																								// attribute
																								// "name
																								// =
																								// interfaceInUse".
									if (pElement.getTextContent().equals("Yes")) { // When
																					// we
																					// find
																					// that
																					// tag
																					// and
																					// want
																					// value
																					// of
																					// him
																					// then
																					// use
																					// .getTextContent()
																					// method.
										e1num++;
									}
								}
							}
						}
					}
				}
			}
			overE1LinesNo = e1num;
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// If GSM station is used for transmission. This method search .xml for
	// number of Gb Eth lines both electrical and optical.
	public void parseXmlFileForGbEthLinesNo(InputFiles inputFiles) {
		int GbEthElec = 0, GbEthOpt = 0;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.newDocument();
			dom = db.parse(inputFiles.getCrOfTransmissionForLte());
			Element rootEle = dom.getDocumentElement();
			NodeList rootNodeList = rootEle.getElementsByTagName("managedObject");
			if (rootNodeList != null && rootNodeList.getLength() > 0) {
				for (int i = 0; i < rootNodeList.getLength(); i++) {
					Element el = (Element) rootNodeList.item(i);
					if (el.getAttribute("class").equals("ETHPRT")) {
						NodeList interfaceSettings = el.getElementsByTagName("list");
						for (int j = 0; j < interfaceSettings.getLength(); j++) {
							Element ethInt = (Element) interfaceSettings.item(j);
							NodeList pList = ethInt.getElementsByTagName("p");
							for (int k = 0; k < pList.getLength(); k++) {
								Element pElement = (Element) pList.item(k);
								if (pElement.getAttribute("name").equals("portID")) {
									if (pElement.getTextContent().equals("2") | pElement.getTextContent().equals("3")) {
										Element innerPElement = (Element) pList.item(k + 1);
										if (innerPElement.getAttribute("name").equals("portInUse")) {
											if (innerPElement.getTextContent().equals("Yes")) {
												GbEthElec++;
											}
										}
									}
									if (pElement.getTextContent().equals("1")) {
										Element innerPElement = (Element) pList.item(k + 1);
										if (innerPElement.getAttribute("name").equals("portInUse")) {
											if (innerPElement.getTextContent().equals("Yes")) {
												GbEthOpt++;
											}
										}
									}
								}
							}
						}
					}
				}
			}
			overGbEthElectLinesNo = GbEthElec;
			overGbEthOptLinesNo = GbEthOpt;
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// All other methods are not necessary for LTE sites so they are not
	// provided with body.
	@Override
	public String getTransModuleType() {
		return null;
	}

	@Override
	public String getTrsIp() {
		return null;
	}

	@Override
	public String getNodebIp() {
		return null;
	}

	@Override
	public String getRncIp() {
		return null;
	}

	@Override
	public String getRcuaIp() {
		return null;
	}

	@Override
	public String[] getCellIdS1() {
		return null;
	}

	@Override
	public String[] getCellIdS2() {
		return null;
	}

	@Override
	public String[] getCellIdS3() {
		return null;
	}

	@Override
	public String[] getCellIdS4() {
		return null;
	}

	@Override
	public String[][] getCellIdGroup() {
		return null;
	}

	@Override
	public int[][] getUarfcnGroup() {
		return null;
	}

	@Override
	public int[] getUarfcnS1() {
		return null;
	}

	@Override
	public int[] getUarfcnS2() {
		return null;
	}

	@Override
	public int[] getUarfcnS3() {
		return null;
	}

	@Override
	public int[] getUarfcnS4() {
		return null;
	}

	@Override
	public String getExtAl1() {
		return null;
	}

	@Override
	public String getExtAl2() {
		return null;
	}

	@Override
	public String getExtAl3() {
		return null;
	}

	@Override
	public String getExtAl4() {
		return null;
	}

	@Override
	public String getExtAl5() {
		return null;
	}

	@Override
	public String getExtAl6() {
		return null;
	}

	@Override
	public String getExtAl7() {
		return null;
	}

	@Override
	public String getExtAl8() {
		return null;
	}

	@Override
	public String getExtAl9() {
		return null;
	}

	@Override
	public String getExtAl10() {
		return null;
	}

	@Override
	public String getExtAl1Name() {
		return null;
	}

	@Override
	public String getExtAl2Name() {
		return null;
	}

	@Override
	public String getExtAl3Name() {
		return null;
	}

	@Override
	public String getExtAl4Name() {
		return null;
	}

	@Override
	public String getExtAl5Name() {
		return null;
	}

	@Override
	public String getExtAl6Name() {
		return null;
	}

	@Override
	public String getExtAl7Name() {
		return null;
	}

	@Override
	public String getExtAl8Name() {
		return null;
	}

	@Override
	public String getExtAl9Name() {
		return null;
	}

	@Override
	public String getExtAl10Name() {
		return null;
	}

	@Override
	public String getExtAl1Pol() {
		return null;
	}

	@Override
	public String getExtAl2Pol() {
		return null;
	}

	@Override
	public String getExtAl3Pol() {
		return null;
	}

	@Override
	public String getExtAl4Pol() {
		return null;
	}

	@Override
	public String getExtAl5Pol() {
		return null;
	}

	@Override
	public String getExtAl6Pol() {
		return null;
	}

	@Override
	public String getExtAl7Pol() {
		return null;
	}

	@Override
	public String getExtAl8Pol() {
		return null;
	}

	@Override
	public String getExtAl9Pol() {
		return null;
	}

	@Override
	public String getExtAl10Pol() {

		return null;
	}

	@Override
	public String getExtAl1Sev() {
		return null;
	}

	@Override
	public String getExtAl2Sev() {

		return null;
	}

	@Override
	public String getExtAl3Sev() {
		return null;
	}

	@Override
	public String getExtAl4Sev() {

		return null;
	}

	@Override
	public String getExtAl5Sev() {
		return null;
	}

	@Override
	public String getExtAl6Sev() {

		return null;
	}

	@Override
	public String getExtAl7Sev() {
		return null;
	}

	@Override
	public String getExtAl8Sev() {
		return null;
	}

	@Override
	public String getExtAl9Sev() {
		return null;
	}

	@Override
	public String getExtAl10Sev() {
		return null;
	}

	@Override
	public void setSiteConfig() {
	}

	@Override
	public String getSiteConfig() {

		return null;
	}
}