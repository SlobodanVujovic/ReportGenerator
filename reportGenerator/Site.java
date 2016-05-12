/*
 * This is interface that describe what site class should have.
 */

package reportGenerator;

public interface Site {

	public void setSiteCode(InputFiles inputFiles);

	public String getSiteCode();

	public void setSiteName(InputFiles inputFiles);

	public String getSiteName();

	public void setDate(InputFiles inputFiles);

	public String getDate();

	public AffectedSite getGsm9Changed();

	public AffectedSite getGsm18Changed();

	public AffectedSite getUmts21Changed();

	public AffectedSite getLte18Changed();

	public String getInstalationType();

	public String getArchitectureType();

	public String getMhaTypeS1();

	public String getMhaTypeS2();

	public String getMhaTypeS3();

	public String getMhaTypeS4();

	public String getRtaaTypeS1();

	public String getRtaaTypeS2();

	public String getRtaaTypeS3();

	public String getRtaaTypeS4();

	public String getFeederSizeS1();

	public String getFeederSizeS2();

	public String getFeederSizeS3();

	public String getFeederSizeS4();

	public String getFeederLengthS1();

	public String getFeederLengthS2();

	public String getFeederLengthS3();

	public String getFeederLengthS4();

	public String getFiberLengthS1();

	public String getFiberLengthS2();

	public String getFiberLengthS3();

	public String getFiberLengthS4();

	public String getDistributeJumperLengthS1();

	public String getDistributeJumperLengthS2();

	public String getDistributeJumperLengthS3();

	public String getDistributeJumperLengthS4();

	public String getEarthKitNoS1();

	public String getEarthKitNoS2();

	public String getEarthKitNoS3();

	public String getEarthKitNoS4();

	public String getJumperAtBtsLenS1();

	public String getJumperAtBtsLenS2();

	public String getJumperAtBtsLenS3();

	public String getJumperAtBtsLenS4();

	public String getJumperBeforeMhaLenS1();

	public String getJumperBeforeMhaLenS2();

	public String getJumperBeforeMhaLenS3();

	public String getJumperBeforeMhaLenS4();

	public String getJumperAfterMhaLenS1();

	public String getJumperAfterMhaLenS2();

	public String getJumperAfterMhaLenS3();

	public String getJumperAfterMhaLenS4();

	public String getJumperPerSectorS1();

	public String getJumperPerSectorS2();

	public String getJumperPerSectorS3();

	public String getJumperPerSectorS4();

	public String getBtsCabinetType();

	public String getSysModule1Type();

	public String getSysModule1Loc();

	public String getTransModuleType();

	public int getE1LinesNo();

	public int getGbEthElectLinesNo();

	public int getGbEthOptLinesNo();

	public String getRfModule1Type();

	public String getRfModule2Type();

	public String getRfModule3Type();

	public String getRfModule1Loc();

	public String getRfModule2Loc();

	public String getRfModule3Loc();

	public String getPowSupplyType();

	public String getRectifierType();

	public String getRectifierPower();

	public String getRectifierQty();

	public String getBatteryType();

	public String getBatteryCap();

	public String getBatQty();

	public String getCurrent();

	public String getVoltage();

	public String getSwVersion();

	public String getTrsIp();

	public String getNodebIp();

	public String getRncIp();

	public String getRcuaIp();

	public String[] getCellIdS1();

	public String[] getCellIdS2();

	public String[] getCellIdS3();

	public String[] getCellIdS4();

	public String[][] getCellIdGroup();

	public int[][] getUarfcnGroup();

	public int[] getUarfcnS1();

	public int[] getUarfcnS2();

	public int[] getUarfcnS3();

	public int[] getUarfcnS4();

	public String getExtAl1();

	public String getExtAl2();

	public String getExtAl3();

	public String getExtAl4();

	public String getExtAl5();

	public String getExtAl6();

	public String getExtAl7();

	public String getExtAl8();

	public String getExtAl9();

	public String getExtAl10();

	public String getExtAl1Name();

	public String getExtAl2Name();

	public String getExtAl3Name();

	public String getExtAl4Name();

	public String getExtAl5Name();

	public String getExtAl6Name();

	public String getExtAl7Name();

	public String getExtAl8Name();

	public String getExtAl9Name();

	public String getExtAl10Name();

	public String getExtAl1Pol();

	public String getExtAl2Pol();

	public String getExtAl3Pol();

	public String getExtAl4Pol();

	public String getExtAl5Pol();

	public String getExtAl6Pol();

	public String getExtAl7Pol();

	public String getExtAl8Pol();

	public String getExtAl9Pol();

	public String getExtAl10Pol();

	public String getExtAl1Sev();

	public String getExtAl2Sev();

	public String getExtAl3Sev();

	public String getExtAl4Sev();

	public String getExtAl5Sev();

	public String getExtAl6Sev();

	public String getExtAl7Sev();

	public String getExtAl8Sev();

	public String getExtAl9Sev();

	public String getExtAl10Sev();

	public String getSiteInfo();

	public String getCommFile();

	public String getCrFile();

	public void setSiteConfig();

	public String getSiteConfig();

	public void setNumOfSectors(AffectedSite affectedSite);

	public int getNumOfSectors();

	public String getOverTransSiteCode();

	public String getOverTransModule();

	public int getOverE1LinesNo();

	public int getOverGbEthElectLinesNo();

	public int getOverGbEthOptLinesNo();

}
