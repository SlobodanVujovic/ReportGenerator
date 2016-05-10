/*
 * This is class for reading input files for all necessary information.
 */

package reportGenerator;

import java.io.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

public class InputFiles {
	private File inputFolder = new File("C:\\RG input");
	private File[] listOfFiles = this.inputFolder.listFiles();
	private File crFileUmts, crFileLte, crFileGsm, commFile, siteInfo, backupCommFile, engineerFile, upFile,
			crOfTransmissionForLte;
	private String crStr = "CommissioningReport_", scfStr = "SCF", commStr = "Commissioning_",
			siteInfoStr = "SiteInformation_", backupCommStr = "BackupCommissioning_", upStr = "UP ",
			engineerStr = "Engineer", siteCodeStr = "xxxyy", siteCode3gStr, siteCode4gStr, azimuthStr = "Azimut",
			mechanicalTiltStr = "Mehan", electricalTiltStr = "Elektr", antHighStr = "Visina",
			antenaTypeStr = "Antenski", s1Str = "1", s2Str = "2", s3Str = "3", s4Str = "4";

	public String getSiteCode3gStr() {
		return this.siteCode3gStr;
	}

	public String getSiteCode4gStr() {
		return this.siteCode4gStr;
	}

	public File getUmtsCrFile() {
		return this.crFileUmts;
	}

	public File getLteCrFile() {
		return this.crFileLte;
	}

	public File getCommFile() {
		return this.commFile;
	}

	public File getSiteInfo() {
		return this.siteInfo;
	}

	public File getBackupCommFile() {
		return this.backupCommFile;
	}

	public File getUpFile() {
		return this.upFile;
	}

	public File getEngineerFile() {
		return this.engineerFile;
	}

	public File getCrOfTransmissionForLte() {
		return this.crOfTransmissionForLte;
	}

	void findSiteCodeFromCommissioningReport(char siteCodeType) {
		for (File file : this.listOfFiles) {
			if (file.toString().contains(this.crStr)) {
				int siteCodeStartIndex = file.toString().indexOf("_") + 1;
				int siteCodeEndIndex = file.toString().indexOf("_", siteCodeStartIndex);
				this.siteCodeStr = file.toString().substring(siteCodeStartIndex, siteCodeEndIndex);
				if (siteCodeStr.charAt(2) == siteCodeType) {
					break;
				}
			}
		}
	}

	void setSiteCode(FrontWindow fw, char siteCodeType) {
		if (siteCodeStr.charAt(2) == 'U') {
			this.siteCode3gStr = siteCodeStr;
		} else if (siteCodeStr.charAt(2) == 'L') {
			this.siteCode4gStr = siteCodeStr;
			this.siteCode3gStr = siteCode4gStr.substring(0, 2) + 'U' + siteCode4gStr.substring(3);
		} else {
			noCommissioningReport();
		}
		sortOutInputFilesToAppropriateVariables();
	}

	public void noCommissioningReport() {
		Notifications notifications = new Notifications();
		notifications.noCommRepInFolder();
	}

	public void sortOutInputFilesToAppropriateVariables() {
		for (File inputFile : this.listOfFiles) {
			if (inputFile.toString().contains(this.crStr) && inputFile.toString().contains(siteCode3gStr)) {
				this.crFileUmts = inputFile;
			}
			if (inputFile.toString().contains(this.crStr) && inputFile.toString().contains(siteCode4gStr)) {
				this.crFileLte = inputFile;
			}
			if (inputFile.toString().contains(this.scfStr)) {
				this.crFileGsm = inputFile;
			}
			if (inputFile.toString().contains(this.commStr)) {
				this.commFile = inputFile;
			}
			if (inputFile.toString().contains(this.siteInfoStr)) {
				this.siteInfo = inputFile;
			}
			if (inputFile.toString().contains(this.backupCommStr)) {
				this.backupCommFile = inputFile;
			}
			if (inputFile.toString().contains(this.upStr)) {
				this.upFile = inputFile;
			}
			if (inputFile.toString().contains(this.engineerStr)) {
				this.engineerFile = inputFile;
			}
		}
	}

	// Ne cita dobro broj linija za LTE.
	String readTransportModuleInfoFromCommissionReport() {
		String result = "Dummy_Data";
		try {
			BufferedReader br = new BufferedReader(new FileReader(crFileUmts));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.indexOf("FTI") != -1) {
					result = line.substring(line.indexOf('F'), line.indexOf('F') + 4);
				}
			}
			br.close();
			isResultValid(result, "FTI");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	String readSystemModuleInfoFromCommissionReport() {
		String result = "Dummy_Data";
		try {
			BufferedReader br = new BufferedReader(new FileReader(crFileUmts));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.indexOf("Module locations") != -1) {
					String innerLine;
					while ((innerLine = br.readLine()) != null) {
						if (innerLine.indexOf("FSM") != -1) {
							result = innerLine.substring(innerLine.indexOf("F"), innerLine.indexOf("F") + 4).trim();
							break;
						}
					}
				}
			}
			br.close();
			isResultValid(result, "Module locations");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String readRetInfoFromCommissioningReport(File commissioningReport) {
		String result = "Dummy_Data";
		try {
			BufferedReader br = new BufferedReader(new FileReader(commissioningReport));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.indexOf("RET settings") != -1) {
					String innerLine;
					while ((innerLine = br.readLine()) != null) {
						if (innerLine.indexOf("Product code:") != -1) {
							result = innerLine.substring(innerLine.indexOf(":") + 1).trim();
							break;
						}
					}
				}
			}
			br.close();
			isResultValid(result, "RET settings");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	String readParameterFromCommissioningReport(String parameter) {
		String result = "Dummy_Data";
		try {
			BufferedReader br = new BufferedReader(new FileReader(crFileUmts));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.indexOf(parameter) != -1) {
					result = line.substring(line.indexOf(":") + 1).trim();
					break;
				}
			}
			br.close();
			isResultValid(result, parameter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private void isResultValid(String result, String parameter) {
		if (result.equals("Dummy_Data") & !parameter.equals("MHA type:")) {
			Notifications notifications = new Notifications();
			notifications.stringNotFound(crFileUmts, parameter);
		}
	}

	public int readCommReportForNoOfTranssmisionLines(File file, String str1, String str2) {
		int result = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.indexOf(str1) != -1) {
					String innerLine;
					for (int i = 0; i < 29; i++) {
						innerLine = br.readLine();
						if (innerLine.indexOf(str2) != -1 & str2.equals("IF")) {
							if (innerLine.contains("Yes")) {
								innerLine = innerLine.substring(innerLine.indexOf("Yes") + 3);
								if (innerLine.contains("Yes")) {
									result++;
								}
							}
						}
						if (innerLine.indexOf(str2) != -1
								& (str2.equals("EIF 1") | str2.equals("EIF 2") | str2.equals("FTIF 1")
										| str2.equals("FTIF 2") | str2.equals("FTIF 3") | str2.equals("FTIF 4"))) {
							if (innerLine.contains("Yes")) {
								result++;
							}
						}
					}
					break;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String[] readCommReportForRfModuleType(String str1, File file, String str2) {
		String[] result = new String[5];
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.indexOf(str1) != -1) {
					String innerLine;
					while ((innerLine = br.readLine()) != null) {
						if (innerLine.indexOf(str2) != -1) {
							String deepLine;
							for (int i = 0; i < 5; i++) {
								deepLine = br.readLine();
								if (deepLine.contains(str2)) {
									result[i] = deepLine.substring(deepLine.indexOf(str2), deepLine.indexOf(str2) + 4);
								}
							}
							break;
						}
					}
					break;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String readCommReportForIpAdr(File file, String str1, String str2) {
		String result = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			String lastCharInString;
			lastCharInString = str2.substring((str2.length() - 1));
			while ((line = br.readLine()) != null) {
				if (line.indexOf(str1) != -1) {
					String innerLine;
					while ((innerLine = br.readLine()) != null) {
						if (innerLine.indexOf(str2) != -1) {
							result = innerLine.substring(innerLine.indexOf(lastCharInString) + 1).trim();
							break;
						}
					}
					break;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void readCommReportForCellIds(File file, String str1, String str2, Site site) {
		String[] result = new String[9];
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine(), tempStr;
			int i = 0, x = 0, y = 0, z = 0, k = 0;
			while (line != null && !line.contains(str1)) {
				line = br.readLine();
				if (line != null && line.contains(str2)) {
					String innerLine;
					while ((innerLine = br.readLine()) != null && !innerLine.contains(str1)) {
						if (innerLine.contains("Local cell ")) {
							result[i] = innerLine.substring(innerLine.indexOf('e') + 3).trim();
							if (result[i].length() == 4) { // When we find cell
															// ID, 1. check how
															// many digits it
															// have, because it
															// is not same logic
															// for sector
															// affiliations for
															// cell IDs with
															// different number
															// of digits.
								tempStr = result[i]; // If cell ID have 4 digits
														// then we look last
														// digit to see in which
														// sector is that cell.
								if (tempStr.charAt(tempStr.length() - 1) == '1'
										| tempStr.charAt(tempStr.length() - 1) == '5') {
									site.setCellId1(x, tempStr);
									++x;
								}
								if (tempStr.charAt(tempStr.length() - 1) == '2'
										| tempStr.charAt(tempStr.length() - 1) == '6') {
									site.setCellId2(y, tempStr);
									++y;
								}
								if (tempStr.charAt(tempStr.length() - 1) == '3'
										| tempStr.charAt(tempStr.length() - 1) == '7') {
									site.setCellId3(z, tempStr);
									++z;
								}
								if (tempStr.charAt(tempStr.length() - 1) == '4'
										| tempStr.charAt(tempStr.length() - 1) == '8') {
									site.setCellId4(k, tempStr);
									++k;
								}
							} else if (result[i].length() == 5) { // If cell ID
																	// have 5
																	// digits
																	// and 1.
																	// digit is
																	// 5 then we
																	// look last
																	// digit to
																	// see in
																	// which
																	// sector is
																	// that cell
																	// ID.
								if (result[i].charAt(0) == '5') {
									tempStr = result[i];
									if (tempStr.charAt(tempStr.length() - 1) == '1'
											| tempStr.charAt(tempStr.length() - 1) == '5') {
										site.setCellId1(x, tempStr);
										++x;
									}
									if (tempStr.charAt(tempStr.length() - 1) == '2'
											| tempStr.charAt(tempStr.length() - 1) == '6') {
										site.setCellId2(y, tempStr);
										++y;
									}
									if (tempStr.charAt(tempStr.length() - 1) == '3'
											| tempStr.charAt(tempStr.length() - 1) == '7') {
										site.setCellId3(z, tempStr);
										++z;
									}
									if (tempStr.charAt(tempStr.length() - 1) == '4'
											| tempStr.charAt(tempStr.length() - 1) == '8') {
										site.setCellId4(k, tempStr);
										++k;
									}
								} else { // In other cases (cell ID have 5
											// digits and it doesn't start with
											// 5) we look for 1. digit to see in
											// which sector is that cell ID.
									tempStr = result[i];
									if (tempStr.charAt(0) == '1') {
										site.setCellId1(x, tempStr);
										++x;
									}
									if (tempStr.charAt(0) == '2') {
										site.setCellId2(y, tempStr);
										++y;
									}
									if (tempStr.charAt(0) == '3') {
										site.setCellId3(z, tempStr);
										++z;
									}
									if (tempStr.charAt(0) == '4') {
										site.setCellId4(k, tempStr);
										++k;
									}
								}
							}
							i++;
						}
					}
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readCommReportForUarfcn(File file, String str1, String str2, Site site) {
		String[][] cellIdGroup = site.getCellIdGroup(); // We use cell IDs that
														// we find previously to
														// look for appropriate
														// channel numbers.
		String helpStr1 = "", helpStr2 = "", localCellStr;
		helpStr1 = str2.substring(0, str2.indexOf(":"));
		helpStr2 = str2.substring(str2.length() - 1);
		long pointer = 0;
		try {
			RandomAccessFile raf = new RandomAccessFile(file, "r"); // Because
																	// we need
																	// to read
																	// same file
																	// more then
																	// once we
																	// use
																	// RandomAccessFile.
			String line = raf.readLine();
			while (line != null && !line.contains(str1)) {
				line = raf.readLine();
				if (line != null && line.contains(helpStr1) & line.contains(helpStr2)) {
					pointer = raf.getFilePointer(); // When we find beginning of
													// part that we need to read
													// more then once, we mark
													// that point and when we
													// read it again, we don't
													// start from beginning but
													// from this point which is
													// much more efficient.
					break;
				}
			}
			for (int i = 0; i < 4; i++) { // Iterate through 2-D array of cell
											// IDs and search for channels for
											// every one of them.
				String[] cellIdOfSec = cellIdGroup[i];
				if (cellIdOfSec != null) {
					BEGINING: for (int j = 0; j < 3; j++) {
						String cellId = cellIdOfSec[j];
						if (cellId != null) {
							localCellStr = "Local cell " + cellId;
							String innerLine;
							while ((innerLine = raf.readLine()) != null && !innerLine.contains(str1)) {
								if (innerLine.contains(localCellStr)) {
									String deepLine;
									for (int k = 0; k < 7; k++) {
										deepLine = raf.readLine();
										if (deepLine.contains("Default carrier:")) {
											site.getUarfcnGroup()[i][j] = Integer
													.parseInt(deepLine.substring(deepLine.indexOf(":") + 1).trim()); // Here
																														// we
																														// populate
																														// 2-D
																														// array
																														// of
																														// channel
																														// numbers.
											raf.seek(pointer); // Set pointer to
																// the beginning
																// of part that
																// we nee to
																// read again.
											continue BEGINING;
										}
									}
								}
							}
						}

					}
				}
			}
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[] readCommReportForExtAl(File file, String str1, String str2) {
		String[] result = new String[4]; // result[] contains information if
											// alarm is used, name of alarm, is
											// it normally open or close and its
											// priority.
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.indexOf(str1) != -1) {
					String innerLine;
					while ((innerLine = br.readLine()) != null) {
						if (innerLine.indexOf(str2) != -1 && innerLine.contains("Yes")) {
							result[0] = "Yes";
							result[1] = innerLine.substring(innerLine.indexOf('s') + 1, innerLine.indexOf("Normally"))
									.trim();
							result[2] = innerLine.substring(innerLine.indexOf("Normally"),
									innerLine.indexOf("Normally") + 16);
							result[3] = innerLine.substring(innerLine.indexOf("Normally") + 16, innerLine.indexOf('0'))
									.trim();
							break;
						}
					}
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// Here we search UP file and read data if find specific site code in it.
	public AffectedSite readUpForSiteChanged(String techType, String siteCode) {
		AffectedSite affectedSite = new AffectedSite();
		int antenaTypeS1ColNo = 0, antenaTypeS2ColNo = 0, antenaTypeS3ColNo = 0, antenaTypeS4ColNo = 0,
				azimuthS1ColNo = 0, azimuthS2ColNo = 0, azimuthS3ColNo = 0, azimuthS4ColNo = 0,
				mechanicalTiltS1ColNo = 0, mechanicalTiltS2ColNo = 0, mechanicalTiltS3ColNo = 0,
				mechanicalTiltS4ColNo = 0, electricalTiltS1ColNo = 0, electricalTiltS2ColNo = 0,
				electricalTiltS3ColNo = 0, electricalTiltS4ColNo = 0, antHighS1ColNo = 0, antHighS2ColNo = 0,
				antHighS3ColNo = 0, antHighS4ColNo = 0;
		Cell tempCell;
		String searchSite = siteCode.substring(0, 2) + techType + siteCode.substring(3);
		try (XSSFWorkbook reportWorkbook = new XSSFWorkbook(new FileInputStream(this.upFile))) {
			XSSFSheet reportSheet01 = reportWorkbook.getSheetAt(0);
			ROW_LOOP: for (Row row : reportSheet01) {
				for (Cell cell : row) {
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						if (cell.getStringCellValue().trim().contains(this.azimuthStr) // 1.
																						// search
																						// for
																						// cells
																						// with
																						// specific
																						// strings
																						// in
																						// itself.
																						// If
																						// we
																						// find
																						// that
																						// cell
																						// then
																						// take
																						// column
																						// number
																						// of
																						// that
																						// cell.
								& cell.getStringCellValue().trim().contains(this.s1Str)) {
							azimuthS1ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.azimuthStr)
								& cell.getStringCellValue().trim().contains(this.s2Str)) {
							azimuthS2ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.azimuthStr)
								& cell.getStringCellValue().trim().contains(this.s3Str)) {
							azimuthS3ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.azimuthStr)
								& cell.getStringCellValue().trim().contains(this.s4Str)) {
							azimuthS4ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.electricalTiltStr)
								& cell.getStringCellValue().trim().contains(this.s1Str)) {
							electricalTiltS1ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.electricalTiltStr)
								& cell.getStringCellValue().trim().contains(this.s2Str)) {
							electricalTiltS2ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.electricalTiltStr)
								& cell.getStringCellValue().trim().contains(this.s3Str)) {
							electricalTiltS3ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.electricalTiltStr)
								& cell.getStringCellValue().trim().contains(this.s4Str)) {
							electricalTiltS4ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.mechanicalTiltStr)
								& cell.getStringCellValue().trim().contains(this.s1Str)) {
							mechanicalTiltS1ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.mechanicalTiltStr)
								& cell.getStringCellValue().trim().contains(this.s2Str)) {
							mechanicalTiltS2ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.mechanicalTiltStr)
								& cell.getStringCellValue().trim().contains(this.s3Str)) {
							mechanicalTiltS3ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.mechanicalTiltStr)
								& cell.getStringCellValue().trim().contains(this.s4Str)) {
							mechanicalTiltS4ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.antHighStr)
								& cell.getStringCellValue().trim().contains(this.s1Str)) {
							antHighS1ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.antHighStr)
								& cell.getStringCellValue().trim().contains(this.s2Str)) {
							antHighS2ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.antHighStr)
								& cell.getStringCellValue().trim().contains(this.s3Str)) {
							antHighS3ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.antHighStr)
								& cell.getStringCellValue().trim().contains(this.s4Str)) {
							antHighS4ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.antenaTypeStr)
								& cell.getStringCellValue().trim().contains(this.s1Str)) {
							antenaTypeS1ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.antenaTypeStr)
								& cell.getStringCellValue().trim().contains(this.s2Str)) {
							antenaTypeS2ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.antenaTypeStr)
								& cell.getStringCellValue().trim().contains(this.s3Str)) {
							antenaTypeS3ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().contains(this.antenaTypeStr)
								& cell.getStringCellValue().trim().contains(this.s4Str)) {
							antenaTypeS4ColNo = cell.getColumnIndex();
						}
						if (cell.getStringCellValue().trim().equals(searchSite)) { // When
																					// we
																					// find
																					// row
																					// that
																					// contains
																					// code
																					// of
																					// site,
																					// then
																					// from
																					// that
																					// row,
																					// from
																					// column
																					// numbers
																					// that
																					// we
																					// determine
																					// previously,
																					// we
																					// read
																					// cell
																					// content
																					// and
																					// write
																					// it
																					// to
																					// affected
																					// site.
							affectedSite.setSiteCode(searchSite);
							if (azimuthS1ColNo != 0) {
								tempCell = row.getCell(azimuthS1ColNo);
								if (tempCell.toString() != "") {
									affectedSite.setAzimuthS1((int) tempCell.getNumericCellValue());
								}
							}
							if (azimuthS2ColNo != 0) {
								tempCell = row.getCell(azimuthS2ColNo);
								if (tempCell.toString() != "") {
									affectedSite.setAzimuthS2((int) tempCell.getNumericCellValue());
								}
							}
							if (azimuthS3ColNo != 0) {
								tempCell = row.getCell(azimuthS3ColNo);
								if (tempCell.toString() != "") {
									affectedSite.setAzimuthS3((int) tempCell.getNumericCellValue());
								}
							}
							if (azimuthS4ColNo != 0) {
								tempCell = row.getCell(azimuthS4ColNo);
								if (tempCell.toString() != "") {
									affectedSite.setAzimuthS4((int) tempCell.getNumericCellValue());
								}
							}
							if (electricalTiltS1ColNo != 0) {
								tempCell = row.getCell(electricalTiltS1ColNo);
								if (tempCell.toString() != "") {
									affectedSite.setElectricalTiltS1((int) tempCell.getNumericCellValue());
								}
							}
							if (electricalTiltS2ColNo != 0) {
								tempCell = row.getCell(electricalTiltS2ColNo);
								if (tempCell.toString() != "") {
									affectedSite.setElectricalTiltS2((int) tempCell.getNumericCellValue());
								}
							}
							if (electricalTiltS3ColNo != 0) {
								tempCell = row.getCell(electricalTiltS3ColNo);
								if (tempCell.toString() != "") {
									affectedSite.setElectricalTiltS3((int) tempCell.getNumericCellValue());
								}
							}
							if (electricalTiltS4ColNo != 0) {
								tempCell = row.getCell(electricalTiltS4ColNo);
								if (tempCell.toString() != "") {
									affectedSite.setElectricalTiltS4((int) tempCell.getNumericCellValue());
								}
							}
							if (mechanicalTiltS1ColNo != 0) {
								tempCell = row.getCell(mechanicalTiltS1ColNo);
								if (tempCell.toString() != "") {
									affectedSite.setMechanicalTiltS1((int) tempCell.getNumericCellValue());
								}
							}
							if (mechanicalTiltS2ColNo != 0) {
								tempCell = row.getCell(mechanicalTiltS2ColNo);
								if (tempCell.toString() != "") {
									affectedSite.setMechanicalTiltS2((int) tempCell.getNumericCellValue());
								}
							}
							if (mechanicalTiltS3ColNo != 0) {
								tempCell = row.getCell(mechanicalTiltS3ColNo);
								if (tempCell.toString() != "") {
									affectedSite.setMechanicalTiltS3((int) tempCell.getNumericCellValue());
								}
							}
							if (mechanicalTiltS4ColNo != 0) {
								tempCell = row.getCell(mechanicalTiltS4ColNo);
								if (tempCell.toString() != "") {
									affectedSite.setMechanicalTiltS4((int) tempCell.getNumericCellValue());
								}
							}
							if (antHighS1ColNo != 0) {
								tempCell = row.getCell(antHighS1ColNo);
								if (tempCell.toString() != "") {
									affectedSite.setAntHighS1(Float.parseFloat(tempCell.toString()));
								}
							}
							if (antHighS2ColNo != 0) {
								tempCell = row.getCell(antHighS2ColNo);
								if (tempCell.toString() != "") {
									affectedSite.setAntHighS2(Float.parseFloat(tempCell.toString()));
								}
							}
							if (antHighS3ColNo != 0) {
								tempCell = row.getCell(antHighS3ColNo);
								if (tempCell.toString() != "") {
									affectedSite.setAntHighS3(Float.parseFloat(tempCell.toString()));
								}
							}
							if (antHighS4ColNo != 0) {
								tempCell = row.getCell(antHighS4ColNo);
								if (tempCell.toString() != "") {
									affectedSite.setAntHighS4(Float.parseFloat(tempCell.toString()));
								}
							}
							if (antenaTypeS1ColNo != 0) {
								tempCell = row.getCell(antenaTypeS1ColNo);
								if (tempCell.toString() != "" && tempCell.getCellType() == 0) {
									affectedSite.setAntenaTypeS1((int) tempCell.getNumericCellValue());
								} else {
									affectedSite.setAntenaTypeS1Str(tempCell.getStringCellValue());
								}
							}
							if (antenaTypeS2ColNo != 0) {
								tempCell = row.getCell(antenaTypeS2ColNo);
								if (tempCell.toString() != "" && tempCell.getCellType() == 0) {
									affectedSite.setAntenaTypeS2((int) tempCell.getNumericCellValue());
								} else {
									affectedSite.setAntenaTypeS2Str(tempCell.getStringCellValue());
								}
							}
							if (antenaTypeS3ColNo != 0) {
								tempCell = row.getCell(antenaTypeS3ColNo);
								if (tempCell.toString() != "" && tempCell.getCellType() == 0) {
									affectedSite.setAntenaTypeS3((int) tempCell.getNumericCellValue());
								} else {
									affectedSite.setAntenaTypeS3Str(tempCell.getStringCellValue());
								}
							}
							if (antenaTypeS4ColNo != 0) {
								tempCell = row.getCell(antenaTypeS4ColNo);
								if (tempCell.toString() != "" && tempCell.getCellType() == 0) {
									affectedSite.setAntenaTypeS4((int) tempCell.getNumericCellValue());
								} else {
									affectedSite.setAntenaTypeS4Str(tempCell.getStringCellValue());
								}
							}
							affectedSite.setNumOfSectors();
							break ROW_LOOP;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return affectedSite;
	}

	// Search for commissioning report if transmission for LTE goes over UMTS.
	public void setCrOfTransmissionForLteOver3g(String transOver) {
		for (File readFile : this.listOfFiles) {
			if (readFile.toString().contains(this.crStr) & readFile.toString().contains(transOver)) {
				this.crOfTransmissionForLte = readFile;
			}
		}
	}

	// Search for SCF file if transmission for LTE goes over GSM.
	public void setCrOfTransmissionForLteOver2g() {
		for (File readFile : this.listOfFiles) {
			if (readFile.toString().contains("SCF")) {
				this.crOfTransmissionForLte = readFile;
			}
		}
	}

}