/*
6 * This is class for reading input files for all necessary information.
 */

package reportGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

public class InputFiles {
	private File[] listOfFiles = new File("C:\\RG input").listFiles();
	private File crFileGsm = new File("C:\\RG output\\dummy_file.txt"),
			crFileUmts = new File("C:\\RG output\\dummy_file.txt"),
			crFileLte = new File("C:\\RG output\\dummy_file.txt"), commFile, siteInfo, backupCommFile, engineerFile,
			upFile, crOfTransmissionForLte;
	private String crStr = "CommissioningReport_", scfStr = "SCF", commStr = "Commissioning_",
			siteInfoStr = "SiteInformation_", backupCommStr = "BackupCommissioning_", upStr = "UP ",
			engineerStr = "Engineer", siteCodeStr = "xxxyy", siteCode2gStr = "xxxyy", siteCode3gStr = "xxxyy",
			siteCode4gStr = "xxxyy";
	private String[] resultCellIds = { "0", "0", "0", "0", "0", "0", "0", "0", "0" };

	void setListOfFiles(String folderPath) {
		this.listOfFiles = new File(folderPath).listFiles();
	}

	String getSiteCodeStr() {
		return siteCodeStr;
	}

	String getSiteCode2gStr() {
		return siteCode2gStr;
	}

	String getSiteCode3gStr() {
		return this.siteCode3gStr;
	}

	String getSiteCode4gStr() {
		return this.siteCode4gStr;
	}

	File getCrFileGsm() {
		return crFileGsm;
	}

	File getUmtsCrFile() {
		return this.crFileUmts;
	}

	File getLteCrFile() {
		return this.crFileLte;
	}

	File getCommFile() {
		return this.commFile;
	}

	File getSiteInfo() {
		return this.siteInfo;
	}

	File getBackupCommFile() {
		return this.backupCommFile;
	}

	File getUpFile() {
		return this.upFile;
	}

	File getEngineerFile() {
		return this.engineerFile;
	}

	File getCrOfTransmissionForLte() {
		return this.crOfTransmissionForLte;
	}

	String[] getResultCellIds() {
		return resultCellIds;
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

	void setSiteCode(char siteCodeType) {
		if (siteCodeStr.charAt(2) == 'U') {
			this.siteCode3gStr = siteCodeStr;
		} else if (siteCodeStr.charAt(2) == 'L') {
			this.siteCode4gStr = siteCodeStr;
			this.siteCode3gStr = siteCode4gStr.substring(0, 2) + 'U' + siteCode4gStr.substring(3);
			this.siteCode2gStr = siteCode4gStr.substring(0, 2) + siteCode4gStr.substring(3);
		} else {
			noCommissioningReport();
		}
	}

	void noCommissioningReport() {
		Notifications notifications = new Notifications();
		notifications.noCommRepInFolder();
	}

	void sortOutInputFilesToAppropriateVariables() {
		for (File inputFile : this.listOfFiles) {
			if (inputFile.toString().contains(this.scfStr)) {
				this.crFileGsm = inputFile;
			}
			if (inputFile.toString().contains(this.crStr) && inputFile.toString().contains(siteCode3gStr)) {
				this.crFileUmts = inputFile;
			}
			if (inputFile.toString().contains(this.crStr) && inputFile.toString().contains(siteCode4gStr)) {
				this.crFileLte = inputFile;
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

	String readTransportModuleInfoFrom3gCommissionReport() {
		String result = "Dummy_Data";
		try {
			BufferedReader br = new BufferedReader(new FileReader(crFileUmts));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.indexOf("FTI") != -1) {
					result = line.substring(line.indexOf('F'), line.indexOf('F') + 4);
					break;
				}
			}
			br.close();
			isResultValid(result, "FTI");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	void isResultValid(String result, String parameter) {
		if (result.equals("Dummy_Data") & !parameter.equals("MHA type:")) {
			Notifications notifications = new Notifications();
			notifications.stringNotFound(crFileUmts, parameter);
		}
	}

	String readSystemModuleInfoFromCommissionReport(File crFile) {
		String result = "Dummy_Data";
		try {
			BufferedReader br = new BufferedReader(new FileReader(crFile));
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

	String readRetInfoFromCommissioningReport(File crFile) {
		String result = "Dummy_Data";
		try {
			BufferedReader br = new BufferedReader(new FileReader(crFile));
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

	String readParameterFromCommissioningReport(File crFile, String parameter) {
		String result = "Dummy_Data";
		try {
			BufferedReader br = new BufferedReader(new FileReader(crFile));
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

	int readCommReportForNoOfTranssmisionLines(File crFile, String firstLine, String secondLine) {
		int result = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(crFile));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.indexOf(firstLine) != -1) {
					String innerLine;
					for (int i = 0; i < 29; i++) {
						innerLine = br.readLine();
						if (innerLine.indexOf(secondLine) != -1 && secondLine.equals("IF")
								&& innerLine.contains("Yes")) {
							innerLine = innerLine.substring(innerLine.indexOf("Yes") + 3);
							if (innerLine.contains("Yes")) {
								result++;
							}
						}
						if (innerLine.indexOf(secondLine) != -1
								&& (secondLine.equals("EIF 1") | secondLine.equals("EIF 2") | secondLine.equals("EIF 3")
										| secondLine.equals("FTIF 1") | secondLine.equals("FTIF 2")
										| secondLine.equals("FTIF 3") | secondLine.equals("FTIF 4"))) {
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

	String[] readCommReportForRfModuleType(File crFile, String firstLine, String secondLine) {
		String[] result = new String[5];
		try {
			BufferedReader br = new BufferedReader(new FileReader(crFile));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.indexOf(firstLine) != -1) {
					String innerLine;
					while ((innerLine = br.readLine()) != null) {
						if (innerLine.indexOf(secondLine) != -1) {
							String deepLine;
							for (int i = 0; i < 5; i++) {
								deepLine = br.readLine();
								if (deepLine.contains(secondLine)) {
									result[i] = deepLine.substring(deepLine.indexOf(secondLine),
											deepLine.indexOf(secondLine) + 4);
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

	String readCommReportForIpAdr(File crFile, String firstLine, String secondLine) {
		String result = "Dummy_Data";
		try {
			BufferedReader br = new BufferedReader(new FileReader(crFile));
			String line;
			String lastCharInString;
			lastCharInString = secondLine.substring((secondLine.length() - 1));
			while ((line = br.readLine()) != null) {
				if (line.indexOf(firstLine) != -1) {
					String innerLine;
					while ((innerLine = br.readLine()) != null) {
						if (innerLine.indexOf(secondLine) != -1) {
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

	void read3gCommReportForCellIds(String topLine, String bottomLine) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(crFileUmts));
			String line = br.readLine();
			int resultIndex = 0;
			while (line != null && !line.contains(bottomLine)) {
				line = br.readLine();
				if (line != null && line.contains(topLine)) {
					while ((line = br.readLine()) != null && !line.contains(bottomLine)) {
						if (line.contains("Local cell ")) {
							resultCellIds[resultIndex] = line.substring(line.indexOf('e') + 3).trim();
							resultIndex++;
						}
					}
					break;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	String[][] set3gCellIds(String[] allCellIds) {
		String[] cellIdS1 = new String[] { "0", "0", "0" }, cellIdS2 = new String[] { "0", "0", "0" },
				cellIdS3 = new String[] { "0", "0", "0" }, cellIdS4 = new String[] { "0", "0", "0" };
		String[][] cellIdGroup = { cellIdS1, cellIdS2, cellIdS3, cellIdS4 };
		int firstSectorIndex = 0, secondSectorIndex = 0, thirdSectorIndex = 0, fourthSectorIndex = 0;
		for (int i = 0; i < allCellIds.length; i++) {
			String cellId = allCellIds[i];
			// When we find cell ID, 1. check how many digits it have, because it is not same
			// logic for sector affiliations for cell IDs with different number of digits.
			// If cell ID have 4 digits then we look last digit to see in which sector is
			// that cell.
			if (cellId.length() == 4) {
				if (cellId.charAt(cellId.length() - 1) == '1' | cellId.charAt(cellId.length() - 1) == '5') {
					cellIdS1[firstSectorIndex] = cellId;
					++firstSectorIndex;
				}
				if (cellId.charAt(cellId.length() - 1) == '2' | cellId.charAt(cellId.length() - 1) == '6') {
					cellIdS2[secondSectorIndex] = cellId;
					++secondSectorIndex;
				}
				if (cellId.charAt(cellId.length() - 1) == '3' | cellId.charAt(cellId.length() - 1) == '7') {
					cellIdS3[thirdSectorIndex] = cellId;
					++thirdSectorIndex;
				}
				if (cellId.charAt(cellId.length() - 1) == '4' | cellId.charAt(cellId.length() - 1) == '8') {
					cellIdS4[fourthSectorIndex] = cellId;
					++fourthSectorIndex;
				}
				// If cell ID have 5 digits and 1. digit is 5 then we look last digit to see in
				// which sector is that cell ID.
			} else if (cellId.length() == 5) {
				if (cellId.charAt(0) == '5') {
					if (cellId.charAt(cellId.length() - 1) == '1' | cellId.charAt(cellId.length() - 1) == '5') {
						cellIdS1[firstSectorIndex] = cellId;
						++firstSectorIndex;
					}
					if (cellId.charAt(cellId.length() - 1) == '2' | cellId.charAt(cellId.length() - 1) == '6') {
						cellIdS2[secondSectorIndex] = cellId;
						++secondSectorIndex;
					}
					if (cellId.charAt(cellId.length() - 1) == '3' | cellId.charAt(cellId.length() - 1) == '7') {
						cellIdS3[thirdSectorIndex] = cellId;
						++thirdSectorIndex;
					}
					if (cellId.charAt(cellId.length() - 1) == '4' | cellId.charAt(cellId.length() - 1) == '8') {
						cellIdS4[fourthSectorIndex] = cellId;
						++fourthSectorIndex;
					}
					// In other cases (cell ID have 5 digits and it doesn't start with 5) we look for
					// 1. digit to see in which sector is that cell ID.
				} else {
					if (cellId.charAt(0) == '1') {
						cellIdS1[firstSectorIndex] = cellId;
						++firstSectorIndex;
					}
					if (cellId.charAt(0) == '2') {
						cellIdS2[secondSectorIndex] = cellId;
						++secondSectorIndex;
					}
					if (cellId.charAt(0) == '3') {
						cellIdS3[thirdSectorIndex] = cellId;
						++thirdSectorIndex;
					}
					if (cellId.charAt(0) == '4') {
						cellIdS4[fourthSectorIndex] = cellId;
						++fourthSectorIndex;
					}
				}
			}
		}
		return cellIdGroup;
	}

	int[][] read3gCommReportForUarfcn(String topLine, String bottomLine, String[][] cellIdGroup) {
		int[] uarfcnS1 = new int[] { 0, 0, 0 }, uarfcnS2 = new int[] { 0, 0, 0 }, uarfcnS3 = new int[] { 0, 0, 0 },
				uarfcnS4 = new int[] { 0, 0, 0 };
		int[][] uarfcnGroup = { uarfcnS1, uarfcnS2, uarfcnS3, uarfcnS4 };
		// We use cell IDs that we find previously to look for appropriate channel numbers.
		String token1InTopLine = "", token2InTopLine = "";
		token1InTopLine = topLine.substring(0, topLine.indexOf(":"));
		token2InTopLine = topLine.substring(topLine.length() - 1);
		long pointer = 0;
		try {
			// Because we need to read same file more then once we use RandomAccessFile.
			RandomAccessFile raf = new RandomAccessFile(crFileUmts, "r");
			String line = raf.readLine();
			while (line != null && !line.contains(bottomLine)) {
				line = raf.readLine();
				if (line != null && (line.contains(token1InTopLine) & line.contains(token2InTopLine))) {
					// When we find beginning of part that we need to read more then once, we
					// mark that point and when we read it again, we don't start from beginning
					// but from this point which is much more efficient.
					pointer = raf.getFilePointer();
					break;
				}
			}
			// Iterate through 2-D array of cell IDs and search for channels for every one of them.
			for (int i = 0; i < 4; i++) {
				String[] cellIdOfSec = cellIdGroup[i];
				BEGINING: for (int j = 0; j < 3; j++) {
					String cellId = cellIdOfSec[j];
					if (cellId != "0") {
						while ((line = raf.readLine()) != null && !line.contains(bottomLine)) {
							if (line.contains("Local cell " + cellId)) {
								for (int k = 0; k < 7; k++) {
									line = raf.readLine();
									if (line.contains("Default carrier:")) {
										// Here we populate 2-D array of channel numbers.
										uarfcnGroup[i][j] = Integer
												.parseInt(line.substring(line.indexOf(":") + 1).trim());
										// Set pointer to the beginning of part that we need to read again.
										raf.seek(pointer);
										continue BEGINING;
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
		return uarfcnGroup;
	}

	String[] read3gCommReportForExtAl(String firstLine, String alarmNo) {
		// result[] contains information if alarm is used, name of alarm, is it normally open
		// or close and its priority.
		String[] result = new String[4];
		try {
			BufferedReader br = new BufferedReader(new FileReader(crFileUmts));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.indexOf(firstLine) != -1) {
					while ((line = br.readLine()) != null) {
						if (line.indexOf(alarmNo) != -1 && line.contains("Yes")) {
							result[0] = "Yes";
							result[1] = line.substring(line.indexOf('s') + 1, line.indexOf("Normally")).trim();
							result[2] = line.substring(line.indexOf("Normally"), line.indexOf("Normally") + 16).trim();
							result[3] = line.substring(line.indexOf("Normally") + 16, line.indexOf('0')).trim();
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
	AffectedSite readUpForSiteChanged(String techType, String siteCode) {
		AffectedSite affectedSite = new AffectedSite();
		String siteCodeStr = "Kod";
		int siteCodeColNo = 100;
		String[] header = new String[] { "Azimut", "Elektr", "Mehan", "Visina", "Antenski" };
		String[] sector = new String[] { "1", "2", "3", "4" };
		int[] columnNumbers = new int[20];
		List<KeyForAffectedSite> listOfKeys = new ArrayList<>();
		for (int i = 0; i < header.length; i++) {
			for (int j = 0; j < sector.length; j++) {
				listOfKeys.add(new KeyForAffectedSite(header[i], sector[j], columnNumbers[(i + 1) * (j + 1) - 1]));
			}
		}
		Cell tempCell;
		DataFormatter dataFormatter = new DataFormatter();
		String searchSite = siteCode.substring(0, 2) + techType + siteCode.substring(3);
		try (XSSFWorkbook reportWorkbook = new XSSFWorkbook(new FileInputStream(upFile))) {
			XSSFSheet reportSheet01 = reportWorkbook.getSheetAt(0);
			for (int i = 0; i < 3; i++) {
				Row row = reportSheet01.getRow(i);
				for (Cell cell : row) {
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						// 1. search for cells with specific strings in itself. If we find
						// that cell then take column number of that cell.
						if (cell.getStringCellValue().trim().contains(siteCodeStr)) {
							siteCodeColNo = cell.getColumnIndex();
						}
						for (Iterator<KeyForAffectedSite> iterator = listOfKeys.iterator(); iterator.hasNext();) {
							KeyForAffectedSite affectedSiteKey = iterator.next();
							if (cell.getStringCellValue().trim().contains(affectedSiteKey.firstPartOfHeader)
									&& cell.getStringCellValue().trim().contains(affectedSiteKey.secondPartOfHeader)) {
								affectedSiteKey.columnNumber = cell.getColumnIndex();
							}
						}
					}
				}
				if (listOfKeys.get(0).columnNumber != 0) {
					break;
				}
			}
			Map<KeyForAffectedSite, String> affectedSiteMap = new HashMap<>();
			for (Iterator<KeyForAffectedSite> iterator = listOfKeys.iterator(); iterator.hasNext();) {
				KeyForAffectedSite affectedSiteKey = iterator.next();
				affectedSiteMap.put(affectedSiteKey, "");
			}
			for (Row row : reportSheet01) {
				Cell cell = row.getCell(siteCodeColNo);
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					// When we find row that contains code of site, then from that row,
					// from column numbers that we determine previously, we read cell
					// content and write it to affected site.
					if (cell.getStringCellValue().trim().equals(searchSite)) {
						affectedSite.siteCode = searchSite;
						for (Map.Entry<KeyForAffectedSite, String> entry : affectedSiteMap.entrySet()) {
							KeyForAffectedSite keyForAffectedSite = entry.getKey();
							if (keyForAffectedSite.firstPartOfHeader.equals("Azimut")
									| keyForAffectedSite.firstPartOfHeader.equals("Elektr")
									| keyForAffectedSite.firstPartOfHeader.equals("Mehan")) {
								if (keyForAffectedSite.columnNumber != 0) {
									tempCell = row.getCell(keyForAffectedSite.columnNumber);
									if (tempCell.toString() != "") {
										String tempString = tempCell.toString();
										entry.setValue(tempString.substring(0, tempString.indexOf(".")));
									}
								}
							}
							if (keyForAffectedSite.firstPartOfHeader.equals("Visina")) {
								if (keyForAffectedSite.columnNumber != 0) {
									tempCell = row.getCell(keyForAffectedSite.columnNumber);
									if (tempCell.toString() != "") {
										entry.setValue(tempCell.toString());
									}
								}
							}
							if (keyForAffectedSite.firstPartOfHeader.equals("Antenski")) {
								if (keyForAffectedSite.columnNumber != 0) {
									tempCell = row.getCell(keyForAffectedSite.columnNumber);
									if (tempCell.toString() != "") {
										String tempString = dataFormatter.formatCellValue(tempCell);
										entry.setValue(tempString);
									}
								}
							}
						}
					}
				}
			}
			for (Map.Entry<KeyForAffectedSite, String> entry : affectedSiteMap.entrySet()) {
				KeyForAffectedSite keyForAffectedSite = entry.getKey();
				if (keyForAffectedSite.firstPartOfHeader.equals("Azimut")) {
					switch (keyForAffectedSite.secondPartOfHeader) {
					case "1":
						affectedSite.azimuthS1 = entry.getValue();
						break;
					case "2":
						affectedSite.azimuthS2 = entry.getValue();
						break;
					case "3":
						affectedSite.azimuthS3 = entry.getValue();
						break;
					case "4":
						affectedSite.azimuthS4 = entry.getValue();
						break;
					}
				}
				if (keyForAffectedSite.firstPartOfHeader.equals("Elektr")) {
					switch (keyForAffectedSite.secondPartOfHeader) {
					case "1":
						affectedSite.electricalTiltS1 = entry.getValue();
						break;
					case "2":
						affectedSite.electricalTiltS2 = entry.getValue();
						break;
					case "3":
						affectedSite.electricalTiltS3 = entry.getValue();
						break;
					case "4":
						affectedSite.electricalTiltS4 = entry.getValue();
						break;
					}
				}
				if (keyForAffectedSite.firstPartOfHeader.equals("Mehan")) {
					switch (keyForAffectedSite.secondPartOfHeader) {
					case "1":
						affectedSite.mechanicalTiltS1 = entry.getValue();
						break;
					case "2":
						affectedSite.mechanicalTiltS2 = entry.getValue();
						break;
					case "3":
						affectedSite.mechanicalTiltS3 = entry.getValue();
						break;
					case "4":
						affectedSite.mechanicalTiltS4 = entry.getValue();
						break;
					}
				}
				if (keyForAffectedSite.firstPartOfHeader.equals("Visina")) {
					switch (keyForAffectedSite.secondPartOfHeader) {
					case "1":
						affectedSite.antHighS1 = entry.getValue();
						break;
					case "2":
						affectedSite.antHighS2 = entry.getValue();
						break;
					case "3":
						affectedSite.antHighS3 = entry.getValue();
						break;
					case "4":
						affectedSite.antHighS4 = entry.getValue();
						break;
					}
				}
				if (keyForAffectedSite.firstPartOfHeader.equals("Antenski")) {
					switch (keyForAffectedSite.secondPartOfHeader) {
					case "1":
						affectedSite.antenaTypeS1 = entry.getValue();
						break;
					case "2":
						affectedSite.antenaTypeS2 = entry.getValue();
						break;
					case "3":
						affectedSite.antenaTypeS3 = entry.getValue();
						break;
					case "4":
						affectedSite.antenaTypeS4 = entry.getValue();
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return affectedSite;
	}

	// Search for commissioning report if transmission for LTE goes over UMTS.
	void setCrOfTransmissionForLteOver3g(String transOver) {
		for (File readFile : this.listOfFiles) {
			if (readFile.toString().contains(this.crStr) & readFile.toString().contains(transOver)) {
				this.crOfTransmissionForLte = readFile;
			}
		}
	}

	// Search for SCF file if transmission for LTE goes over GSM.
	void setCrOfTransmissionForLteOver2g() {
		for (File readFile : this.listOfFiles) {
			if (readFile.toString().contains("SCF")) {
				this.crOfTransmissionForLte = readFile;
			}
		}
	}

}
