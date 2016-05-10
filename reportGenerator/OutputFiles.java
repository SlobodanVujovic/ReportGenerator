/*
 * This is class which define how to populate final report.
 */

package reportGenerator;

import java.io.*;
import java.nio.file.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

public class OutputFiles {
	private File templateFile, targetFile;
	private String templateFileStr = "C:\\RG output\\XYZ01_ABCD_Acceptance test.xlsx",
			targetFileStr = "C:\\RG output\\XYZ01_ABCD_Acceptance test.xlsx";

	// We use template report which we copy and then edit that new report.
	public void createTargetFile(Site site) {
		String siteCode = site.getSiteCode();
		String siteName = site.getSiteName();
		this.targetFileStr = getTargetFileStr().replace("XYZ01", siteCode).replace("ABCD", siteName);
		this.templateFile = new File(this.templateFileStr);
		this.targetFile = new File(this.targetFileStr);
		try {
			Files.copy(this.templateFile.toPath(), this.targetFile.toPath()); // This
																				// is
																				// how
																				// we
																				// copy
																				// from
																				// template
																				// to
																				// target
																				// file.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getTargetFileStr() {
		return this.targetFileStr;
	}

	public String getTemplateFileStr() {
		return this.templateFileStr;
	}

	public File getTemplateFile() {
		return this.templateFile;
	}

	public File getTargetFile() {
		return this.targetFile;
	}

	// Here we start to populate target file.
	public void populateTargetFile(Site site, FrontWindow fw) {
		try {
			InputStream fInputStream = new FileInputStream(getTargetFile().toString());
			XSSFWorkbook workbook = new XSSFWorkbook(fInputStream); // 1. create
																	// workbook.
			XSSFSheet sheet1 = workbook.getSheetAt(0); // 2. create sheet of
														// specific number
														// (0-based
														// enumeration).
			Row row = sheet1.getRow(24); // 3. create row of specific number
											// (0-based enumeration).
			Cell cell = row.getCell(4); // 4. create cell at specified row and
										// at specified column (0-based
										// enumeration).
			cell.setCellType(Cell.CELL_TYPE_STRING); // 5. set cell type.
			cell.setCellValue(site.getSiteCode()); // 6. populate cell that we
													// create previously.
			row = sheet1.getRow(26); // First populate all information from UP
										// file.
			cell = row.getCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(site.getSiteName());
			row = sheet1.getRow(28);
			cell = row.getCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(site.getDate());
			sheet1 = workbook.getSheetAt(1);
			row = sheet1.getRow(8);
			cell = row.getCell(3);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if (site.getGsm9Changed().getAntenaTypeS1() != 0) { // Antenna type
																// can be int or
																// string so
																// that is
																// reason why we
																// have 2
																// options how
																// to populate
																// cell.
				cell.setCellValue(site.getGsm9Changed().getAntenaTypeS1());
			} else if (site.getGsm9Changed().getAntenaTypeS1Str() != null
					&& site.getGsm9Changed().getAntenaTypeS1Str().length() != 0) { // If
																					// antenna
																					// type
																					// is
																					// string
																					// then
																					// we
																					// need
																					// to
																					// check
																					// is
																					// it
																					// different
																					// from
																					// null
																					// and
																					// also
																					// if
																					// its
																					// length
																					// is
																					// different
																					// from
																					// 0
																					// (not
																					// an
																					// empty
																					// string).
				cell.setCellValue(site.getGsm9Changed().getAntenaTypeS1Str());
			}
			cell = row.getCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if (site.getGsm9Changed().getAntenaTypeS2() != 0) {
				cell.setCellValue(site.getGsm9Changed().getAntenaTypeS2());
			} else if (site.getGsm9Changed().getAntenaTypeS2Str() != null
					&& site.getGsm9Changed().getAntenaTypeS2Str().length() != 0) {
				cell.setCellValue(site.getGsm9Changed().getAntenaTypeS2Str());
			}
			cell = row.getCell(5);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if (site.getGsm9Changed().getAntenaTypeS3() != 0) {
				cell.setCellValue(site.getGsm9Changed().getAntenaTypeS3());
			} else if (site.getGsm9Changed().getAntenaTypeS3Str() != null
					&& site.getGsm9Changed().getAntenaTypeS3Str().length() != 0) {
				cell.setCellValue(site.getGsm9Changed().getAntenaTypeS3Str());
			}
			cell = row.getCell(6);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if (site.getGsm9Changed().getAntenaTypeS4() != 0) {
				cell.setCellValue(site.getGsm9Changed().getAntenaTypeS4());
			} else if (site.getGsm9Changed().getAntenaTypeS4Str() != null
					&& site.getGsm9Changed().getAntenaTypeS4Str().length() != 0) {
				cell.setCellValue(site.getGsm9Changed().getAntenaTypeS4Str());
			}
			row = sheet1.getRow(9);
			if (site.getGsm9Changed().getAntenaTypeS1() != 0 | (site.getGsm9Changed().getAntenaTypeS1Str() != null
					&& site.getGsm9Changed().getAntenaTypeS1Str().length() != 0)) { // Only
																					// if
																					// we
																					// have
																					// antenna
																					// type
																					// in
																					// that
																					// sector
																					// then
																					// we
																					// populate
																					// other
																					// information
																					// for
																					// that
																					// sector.
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm9Changed().getAzimuthS1());
			}
			if (site.getGsm9Changed().getAntenaTypeS2() != 0 | (site.getGsm9Changed().getAntenaTypeS2Str() != null
					&& site.getGsm9Changed().getAntenaTypeS2Str().length() != 0)) {
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm9Changed().getAzimuthS2());
			}
			if (site.getGsm9Changed().getAntenaTypeS3() != 0 | (site.getGsm9Changed().getAntenaTypeS3Str() != null
					&& site.getGsm9Changed().getAntenaTypeS3Str().length() != 0)) {
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm9Changed().getAzimuthS3());
			}
			if (site.getGsm9Changed().getAntenaTypeS4() != 0 | (site.getGsm9Changed().getAntenaTypeS4Str() != null
					&& site.getGsm9Changed().getAntenaTypeS4Str().length() != 0)) {
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm9Changed().getAzimuthS4());
			}
			row = sheet1.getRow(11);
			if (site.getGsm9Changed().getAntenaTypeS1() != 0 | (site.getGsm9Changed().getAntenaTypeS1Str() != null
					&& site.getGsm9Changed().getAntenaTypeS1Str().length() != 0)) {
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm9Changed().getMechanicalTiltS1());
			}
			if (site.getGsm9Changed().getAntenaTypeS2() != 0 | (site.getGsm9Changed().getAntenaTypeS2Str() != null
					&& site.getGsm9Changed().getAntenaTypeS2Str().length() != 0)) {
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm9Changed().getMechanicalTiltS2());
			}
			if (site.getGsm9Changed().getAntenaTypeS3() != 0 | (site.getGsm9Changed().getAntenaTypeS3Str() != null
					&& site.getGsm9Changed().getAntenaTypeS3Str().length() != 0)) {
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm9Changed().getMechanicalTiltS3());
			}
			if (site.getGsm9Changed().getAntenaTypeS4() != 0 | (site.getGsm9Changed().getAntenaTypeS4Str() != null
					&& site.getGsm9Changed().getAntenaTypeS4Str().length() != 0)) {
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm9Changed().getMechanicalTiltS4());
			}
			row = sheet1.getRow(12);
			if (site.getGsm9Changed().getAntenaTypeS1() != 0 | (site.getGsm9Changed().getAntenaTypeS1Str() != null
					&& site.getGsm9Changed().getAntenaTypeS1Str().length() != 0)) {
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm9Changed().getElectricalTiltS1());
			}
			if (site.getGsm9Changed().getAntenaTypeS2() != 0 | (site.getGsm9Changed().getAntenaTypeS2Str() != null
					&& site.getGsm9Changed().getAntenaTypeS2Str().length() != 0)) {
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm9Changed().getElectricalTiltS2());
			}
			if (site.getGsm9Changed().getAntenaTypeS3() != 0 | (site.getGsm9Changed().getAntenaTypeS3Str() != null
					&& site.getGsm9Changed().getAntenaTypeS3Str().length() != 0)) {
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm9Changed().getElectricalTiltS3());
			}
			if (site.getGsm9Changed().getAntenaTypeS4() != 0 | (site.getGsm9Changed().getAntenaTypeS4Str() != null
					&& site.getGsm9Changed().getAntenaTypeS4Str().length() != 0)) {
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm9Changed().getElectricalTiltS4());
			}
			row = sheet1.getRow(17);
			cell = row.getCell(3);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if (site.getGsm18Changed().getAntenaTypeS1() != 0) {
				cell.setCellValue(site.getGsm18Changed().getAntenaTypeS1());
			} else {
				cell.setCellValue(site.getGsm18Changed().getAntenaTypeS1Str());
			}
			cell = row.getCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if (site.getGsm18Changed().getAntenaTypeS2() != 0) {
				cell.setCellValue(site.getGsm18Changed().getAntenaTypeS2());
			} else {
				cell.setCellValue(site.getGsm18Changed().getAntenaTypeS2Str());
			}
			cell = row.getCell(5);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if (site.getGsm18Changed().getAntenaTypeS3() != 0) {
				cell.setCellValue(site.getGsm18Changed().getAntenaTypeS3());
			} else {
				cell.setCellValue(site.getGsm18Changed().getAntenaTypeS3Str());
			}
			cell = row.getCell(6);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if (site.getGsm18Changed().getAntenaTypeS4() != 0) {
				cell.setCellValue(site.getGsm18Changed().getAntenaTypeS4());
			} else {
				cell.setCellValue(site.getGsm18Changed().getAntenaTypeS4Str());
			}
			row = sheet1.getRow(18);
			if (site.getGsm18Changed().getAntenaTypeS1() != 0 | (site.getGsm18Changed().getAntenaTypeS1Str() != null
					&& site.getGsm18Changed().getAntenaTypeS1Str().length() != 0)) {
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm18Changed().getAzimuthS1());
			}
			if (site.getGsm18Changed().getAntenaTypeS2() != 0 | (site.getGsm18Changed().getAntenaTypeS2Str() != null
					&& site.getGsm18Changed().getAntenaTypeS2Str().length() != 0)) {
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm18Changed().getAzimuthS2());
			}
			if (site.getGsm18Changed().getAntenaTypeS3() != 0 | (site.getGsm18Changed().getAntenaTypeS3Str() != null
					&& site.getGsm18Changed().getAntenaTypeS3Str().length() != 0)) {
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm9Changed().getAzimuthS3());
			}
			if (site.getGsm18Changed().getAntenaTypeS4() != 0 | (site.getGsm18Changed().getAntenaTypeS4Str() != null
					&& site.getGsm18Changed().getAntenaTypeS4Str().length() != 0)) {
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm18Changed().getAzimuthS4());
			}
			row = sheet1.getRow(20);
			if (site.getGsm18Changed().getAntenaTypeS1() != 0 | (site.getGsm18Changed().getAntenaTypeS1Str() != null
					&& site.getGsm18Changed().getAntenaTypeS1Str().length() != 0)) {
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm18Changed().getMechanicalTiltS1());
			}
			if (site.getGsm18Changed().getAntenaTypeS2() != 0 | (site.getGsm18Changed().getAntenaTypeS2Str() != null
					&& site.getGsm18Changed().getAntenaTypeS2Str().length() != 0)) {
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm18Changed().getMechanicalTiltS2());
			}
			if (site.getGsm18Changed().getAntenaTypeS3() != 0 | (site.getGsm18Changed().getAntenaTypeS3Str() != null
					&& site.getGsm18Changed().getAntenaTypeS3Str().length() != 0)) {
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm18Changed().getMechanicalTiltS3());
			}
			if (site.getGsm18Changed().getAntenaTypeS4() != 0 | (site.getGsm18Changed().getAntenaTypeS4Str() != null
					&& site.getGsm18Changed().getAntenaTypeS4Str().length() != 0)) {
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm18Changed().getMechanicalTiltS4());
			}
			row = sheet1.getRow(21);
			if (site.getGsm18Changed().getAntenaTypeS1() != 0 | (site.getGsm18Changed().getAntenaTypeS1Str() != null
					&& site.getGsm18Changed().getAntenaTypeS1Str().length() != 0)) {
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm18Changed().getElectricalTiltS1());
			}
			if (site.getGsm18Changed().getAntenaTypeS2() != 0 | (site.getGsm18Changed().getAntenaTypeS2Str() != null
					&& site.getGsm18Changed().getAntenaTypeS2Str().length() != 0)) {
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm18Changed().getElectricalTiltS2());
			}
			if (site.getGsm18Changed().getAntenaTypeS3() != 0 | (site.getGsm18Changed().getAntenaTypeS3Str() != null
					&& site.getGsm18Changed().getAntenaTypeS3Str().length() != 0)) {
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm18Changed().getElectricalTiltS3());
			}
			if (site.getGsm18Changed().getAntenaTypeS4() != 0 | (site.getGsm18Changed().getAntenaTypeS4Str() != null
					&& site.getGsm18Changed().getAntenaTypeS4Str().length() != 0)) {
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGsm18Changed().getElectricalTiltS4());
			}
			row = sheet1.getRow(26);
			cell = row.getCell(3);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if (site.getUmts21Changed().getAntenaTypeS1() != 0) {
				cell.setCellValue(site.getUmts21Changed().getAntenaTypeS1());
			} else {
				cell.setCellValue(site.getUmts21Changed().getAntenaTypeS1Str());
			}
			cell = row.getCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if (site.getUmts21Changed().getAntenaTypeS2() != 0) {
				cell.setCellValue(site.getUmts21Changed().getAntenaTypeS2());
			} else {
				cell.setCellValue(site.getUmts21Changed().getAntenaTypeS2Str());
			}
			cell = row.getCell(5);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if (site.getUmts21Changed().getAntenaTypeS3() != 0) {
				cell.setCellValue(site.getUmts21Changed().getAntenaTypeS3());
			} else {
				cell.setCellValue(site.getUmts21Changed().getAntenaTypeS3Str());
			}
			cell = row.getCell(6);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if (site.getUmts21Changed().getAntenaTypeS4() != 0) {
				cell.setCellValue(site.getUmts21Changed().getAntenaTypeS4());
			} else {
				cell.setCellValue(site.getUmts21Changed().getAntenaTypeS4Str());
			}
			row = sheet1.getRow(27);
			if (site.getUmts21Changed().getAntenaTypeS1() != 0 | (site.getUmts21Changed().getAntenaTypeS1Str() != null
					&& site.getUmts21Changed().getAntenaTypeS1Str().length() != 0)) {
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getUmts21Changed().getAzimuthS1());
			}
			if (site.getUmts21Changed().getAntenaTypeS2() != 0 | (site.getUmts21Changed().getAntenaTypeS2Str() != null
					&& site.getUmts21Changed().getAntenaTypeS2Str().length() != 0)) {
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getUmts21Changed().getAzimuthS2());
			}
			if (site.getUmts21Changed().getAntenaTypeS3() != 0 | (site.getUmts21Changed().getAntenaTypeS3Str() != null
					&& site.getUmts21Changed().getAntenaTypeS3Str().length() != 0)) {
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getUmts21Changed().getAzimuthS3());
			}
			if (site.getUmts21Changed().getAntenaTypeS4() != 0 | (site.getUmts21Changed().getAntenaTypeS4Str() != null
					&& site.getUmts21Changed().getAntenaTypeS4Str().length() != 0)) {
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getUmts21Changed().getAzimuthS4());
			}
			row = sheet1.getRow(29);
			if (site.getUmts21Changed().getAntenaTypeS1() != 0 | (site.getUmts21Changed().getAntenaTypeS1Str() != null
					&& site.getUmts21Changed().getAntenaTypeS1Str().length() != 0)) {
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getUmts21Changed().getMechanicalTiltS1());
			}
			if (site.getUmts21Changed().getAntenaTypeS2() != 0 | (site.getUmts21Changed().getAntenaTypeS2Str() != null
					&& site.getUmts21Changed().getAntenaTypeS2Str().length() != 0)) {
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getUmts21Changed().getMechanicalTiltS2());
			}
			if (site.getUmts21Changed().getAntenaTypeS3() != 0 | (site.getUmts21Changed().getAntenaTypeS3Str() != null
					&& site.getUmts21Changed().getAntenaTypeS3Str().length() != 0)) {
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getUmts21Changed().getMechanicalTiltS3());
			}
			if (site.getUmts21Changed().getAntenaTypeS4() != 0 | (site.getUmts21Changed().getAntenaTypeS4Str() != null
					&& site.getUmts21Changed().getAntenaTypeS4Str().length() != 0)) {
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getUmts21Changed().getMechanicalTiltS4());
			}
			row = sheet1.getRow(30);
			if (site.getUmts21Changed().getAntenaTypeS1() != 0 | (site.getUmts21Changed().getAntenaTypeS1Str() != null
					&& site.getUmts21Changed().getAntenaTypeS1Str().length() != 0)) {
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getUmts21Changed().getElectricalTiltS1());
			}
			if (site.getUmts21Changed().getAntenaTypeS2() != 0 | (site.getUmts21Changed().getAntenaTypeS2Str() != null
					&& site.getUmts21Changed().getAntenaTypeS2Str().length() != 0)) {
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getUmts21Changed().getElectricalTiltS2());
			}
			if (site.getUmts21Changed().getAntenaTypeS3() != 0 | (site.getUmts21Changed().getAntenaTypeS3Str() != null
					&& site.getUmts21Changed().getAntenaTypeS3Str().length() != 0)) {
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getUmts21Changed().getElectricalTiltS3());
			}
			if (site.getUmts21Changed().getAntenaTypeS4() != 0 | (site.getUmts21Changed().getAntenaTypeS4Str() != null
					&& site.getUmts21Changed().getAntenaTypeS4Str().length() != 0)) {
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getUmts21Changed().getElectricalTiltS4());
			}
			row = sheet1.getRow(36);
			cell = row.getCell(3);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if (site.getLte18Changed().getAntenaTypeS1() != 0) {
				cell.setCellValue(site.getLte18Changed().getAntenaTypeS1());
			} else {
				cell.setCellValue(site.getLte18Changed().getAntenaTypeS1Str());
			}
			cell = row.getCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if (site.getLte18Changed().getAntenaTypeS2() != 0) {
				cell.setCellValue(site.getLte18Changed().getAntenaTypeS2());
			} else {
				cell.setCellValue(site.getLte18Changed().getAntenaTypeS2Str());
			}
			cell = row.getCell(5);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if (site.getLte18Changed().getAntenaTypeS3() != 0) {
				cell.setCellValue(site.getLte18Changed().getAntenaTypeS3());
			} else {
				cell.setCellValue(site.getLte18Changed().getAntenaTypeS3Str());
			}
			cell = row.getCell(6);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			if (site.getLte18Changed().getAntenaTypeS4() != 0) {
				cell.setCellValue(site.getLte18Changed().getAntenaTypeS4());
			} else {
				cell.setCellValue(site.getLte18Changed().getAntenaTypeS4Str());
			}
			row = sheet1.getRow(37);
			if (site.getLte18Changed().getAntenaTypeS1() != 0 | (site.getLte18Changed().getAntenaTypeS1Str() != null
					&& site.getLte18Changed().getAntenaTypeS1Str().length() != 0)) {
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getLte18Changed().getAzimuthS1());
			}
			if (site.getLte18Changed().getAntenaTypeS2() != 0 | (site.getLte18Changed().getAntenaTypeS2Str() != null
					&& site.getLte18Changed().getAntenaTypeS2Str().length() != 0)) {
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getLte18Changed().getAzimuthS2());
			}
			if (site.getLte18Changed().getAntenaTypeS3() != 0 | (site.getLte18Changed().getAntenaTypeS3Str() != null
					&& site.getLte18Changed().getAntenaTypeS3Str().length() != 0)) {
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getLte18Changed().getAzimuthS3());
			}
			if (site.getLte18Changed().getAntenaTypeS4() != 0 | (site.getLte18Changed().getAntenaTypeS4Str() != null
					&& site.getLte18Changed().getAntenaTypeS4Str().length() != 0)) {
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getLte18Changed().getAzimuthS4());
			}
			row = sheet1.getRow(39);
			if (site.getLte18Changed().getAntenaTypeS1() != 0 | (site.getLte18Changed().getAntenaTypeS1Str() != null
					&& site.getLte18Changed().getAntenaTypeS1Str().length() != 0)) {
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getLte18Changed().getMechanicalTiltS1());
			}
			if (site.getLte18Changed().getAntenaTypeS2() != 0 | (site.getLte18Changed().getAntenaTypeS2Str() != null
					&& site.getLte18Changed().getAntenaTypeS2Str().length() != 0)) {
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getLte18Changed().getMechanicalTiltS2());
			}
			if (site.getLte18Changed().getAntenaTypeS3() != 0 | (site.getLte18Changed().getAntenaTypeS3Str() != null
					&& site.getLte18Changed().getAntenaTypeS3Str().length() != 0)) {
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getLte18Changed().getMechanicalTiltS3());
			}
			if (site.getLte18Changed().getAntenaTypeS4() != 0 | (site.getLte18Changed().getAntenaTypeS4Str() != null
					&& site.getLte18Changed().getAntenaTypeS4Str().length() != 0)) {
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getLte18Changed().getMechanicalTiltS4());
			}
			row = sheet1.getRow(40);
			if (site.getLte18Changed().getAntenaTypeS1() != 0 | (site.getLte18Changed().getAntenaTypeS1Str() != null
					&& site.getLte18Changed().getAntenaTypeS1Str().length() != 0)) {
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getLte18Changed().getElectricalTiltS1());
			}
			if (site.getLte18Changed().getAntenaTypeS2() != 0 | (site.getLte18Changed().getAntenaTypeS2Str() != null
					&& site.getLte18Changed().getAntenaTypeS2Str().length() != 0)) {
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getLte18Changed().getElectricalTiltS2());
			}
			if (site.getLte18Changed().getAntenaTypeS3() != 0 | (site.getLte18Changed().getAntenaTypeS3Str() != null
					&& site.getLte18Changed().getAntenaTypeS3Str().length() != 0)) {
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getLte18Changed().getElectricalTiltS3());
			}
			if (site.getLte18Changed().getAntenaTypeS4() != 0 | (site.getLte18Changed().getAntenaTypeS4Str() != null
					&& site.getLte18Changed().getAntenaTypeS4Str().length() != 0)) {
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getLte18Changed().getElectricalTiltS4());
			}
			if (fw.getUmts().isSelected()) { // Now, when we populate
												// report with data from
												// UP, depending which
												// report we do, we need
												// to populate others
												// information for that
												// technology that we do
												// not have in UP file.
				row = sheet1.getRow(32);
				if (site.getUmts21Changed().getAntenaTypeS1() != 0
						| (site.getUmts21Changed().getAntenaTypeS1Str() != null
								&& site.getUmts21Changed().getAntenaTypeS1Str().length() != 0)) {
					cell = row.getCell(3);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getRtaaTypeS1());
				}
				if (site.getUmts21Changed().getAntenaTypeS2() != 0
						| (site.getUmts21Changed().getAntenaTypeS2Str() != null
								&& site.getUmts21Changed().getAntenaTypeS2Str().length() != 0)) {
					cell = row.getCell(4);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getRtaaTypeS2());
				}
				if (site.getUmts21Changed().getAntenaTypeS3() != 0
						| (site.getUmts21Changed().getAntenaTypeS3Str() != null
								&& site.getUmts21Changed().getAntenaTypeS3Str().length() != 0)) {
					cell = row.getCell(5);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getRtaaTypeS3());
				}
				if (site.getUmts21Changed().getAntenaTypeS4() != 0
						| (site.getUmts21Changed().getAntenaTypeS4Str() != null
								&& site.getUmts21Changed().getAntenaTypeS4Str().length() != 0)) {
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getRtaaTypeS4());
				}
				row = sheet1.getRow(68);
				if (site.getUmts21Changed().getAntenaTypeS1() != 0
						| (site.getUmts21Changed().getAntenaTypeS1Str() != null
								&& site.getUmts21Changed().getAntenaTypeS1Str().length() != 0)) {
					cell = row.getCell(3);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getFeederSizeS1()); // FeederSizeSx
																// field
																// contains
																// information
																// is it real
																// feeder (and
																// it's size) or
																// fiber cable.
				}
				if (site.getUmts21Changed().getAntenaTypeS2() != 0
						| (site.getUmts21Changed().getAntenaTypeS2Str() != null
								&& site.getUmts21Changed().getAntenaTypeS2Str().length() != 0)) {
					cell = row.getCell(4);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getFeederSizeS2());
				}
				if (site.getUmts21Changed().getAntenaTypeS3() != 0
						| (site.getUmts21Changed().getAntenaTypeS3Str() != null
								&& site.getUmts21Changed().getAntenaTypeS3Str().length() != 0)) {
					cell = row.getCell(5);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getFeederSizeS3());
				}
				if (site.getUmts21Changed().getAntenaTypeS4() != 0
						| (site.getUmts21Changed().getAntenaTypeS4Str() != null
								&& site.getUmts21Changed().getAntenaTypeS4Str().length() != 0)) {
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getFeederSizeS4());
				}
				if (site.getArchitectureType().equals("Common")) { // Populate
																	// common
																	// specific
																	// information.
					row = sheet1.getRow(31);
					if (site.getUmts21Changed().getAntenaTypeS1() != 0
							| (site.getUmts21Changed().getAntenaTypeS1Str() != null
									&& site.getUmts21Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getMhaTypeS1());
					}
					if (site.getUmts21Changed().getAntenaTypeS2() != 0
							| (site.getUmts21Changed().getAntenaTypeS2Str() != null
									&& site.getUmts21Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getMhaTypeS2());
					}
					if (site.getUmts21Changed().getAntenaTypeS3() != 0
							| (site.getUmts21Changed().getAntenaTypeS3Str() != null
									&& site.getUmts21Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getMhaTypeS3());
					}
					if (site.getUmts21Changed().getAntenaTypeS4() != 0
							| (site.getUmts21Changed().getAntenaTypeS4Str() != null
									&& site.getUmts21Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getMhaTypeS4());
					}
					row = sheet1.getRow(69);
					if (site.getUmts21Changed().getAntenaTypeS1() != 0
							| (site.getUmts21Changed().getAntenaTypeS1Str() != null
									&& site.getUmts21Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getFeederLengthS1());
					}
					if (site.getUmts21Changed().getAntenaTypeS2() != 0
							| (site.getUmts21Changed().getAntenaTypeS2Str() != null
									&& site.getUmts21Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getFeederLengthS2());
					}
					if (site.getUmts21Changed().getAntenaTypeS3() != 0
							| (site.getUmts21Changed().getAntenaTypeS3Str() != null
									&& site.getUmts21Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getFeederLengthS3());
					}
					if (site.getUmts21Changed().getAntenaTypeS4() != 0
							| (site.getUmts21Changed().getAntenaTypeS4Str() != null
									&& site.getUmts21Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getFeederLengthS4());
					}
					row = sheet1.getRow(70);
					if (site.getUmts21Changed().getAntenaTypeS1() != 0
							| (site.getUmts21Changed().getAntenaTypeS1Str() != null
									&& site.getUmts21Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getEarthKitNoS1());
					}
					if (site.getUmts21Changed().getAntenaTypeS2() != 0
							| (site.getUmts21Changed().getAntenaTypeS2Str() != null
									&& site.getUmts21Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getEarthKitNoS2());
					}
					if (site.getUmts21Changed().getAntenaTypeS3() != 0
							| (site.getUmts21Changed().getAntenaTypeS3Str() != null
									&& site.getUmts21Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getEarthKitNoS3());
					}
					if (site.getUmts21Changed().getAntenaTypeS4() != 0
							| (site.getUmts21Changed().getAntenaTypeS4Str() != null
									&& site.getUmts21Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getEarthKitNoS4());
					}
					row = sheet1.getRow(71);
					if (site.getUmts21Changed().getAntenaTypeS1() != 0
							| (site.getUmts21Changed().getAntenaTypeS1Str() != null
									&& site.getUmts21Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperAtBtsLenS1());
					}
					if (site.getUmts21Changed().getAntenaTypeS2() != 0
							| (site.getUmts21Changed().getAntenaTypeS2Str() != null
									&& site.getUmts21Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperAtBtsLenS2());
					}
					if (site.getUmts21Changed().getAntenaTypeS3() != 0
							| (site.getUmts21Changed().getAntenaTypeS3Str() != null
									&& site.getUmts21Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperAtBtsLenS3());
					}
					if (site.getUmts21Changed().getAntenaTypeS4() != 0
							| (site.getUmts21Changed().getAntenaTypeS4Str() != null
									&& site.getUmts21Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperAtBtsLenS4());
					}
					row = sheet1.getRow(72);
					if (site.getUmts21Changed().getAntenaTypeS1() != 0
							| (site.getUmts21Changed().getAntenaTypeS1Str() != null
									&& site.getUmts21Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperBeforeMhaLenS1());
					}
					if (site.getUmts21Changed().getAntenaTypeS2() != 0
							| (site.getUmts21Changed().getAntenaTypeS2Str() != null
									&& site.getUmts21Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperBeforeMhaLenS2());
					}
					if (site.getUmts21Changed().getAntenaTypeS3() != 0
							| (site.getUmts21Changed().getAntenaTypeS3Str() != null
									&& site.getUmts21Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperBeforeMhaLenS3());
					}
					if (site.getUmts21Changed().getAntenaTypeS4() != 0
							| (site.getUmts21Changed().getAntenaTypeS4Str() != null
									&& site.getUmts21Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperBeforeMhaLenS4());
					}
					row = sheet1.getRow(73);
					if (site.getUmts21Changed().getAntenaTypeS1() != 0
							| (site.getUmts21Changed().getAntenaTypeS1Str() != null
									&& site.getUmts21Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperAfterMhaLenS1());
					}
					if (site.getUmts21Changed().getAntenaTypeS2() != 0
							| (site.getUmts21Changed().getAntenaTypeS2Str() != null
									&& site.getUmts21Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperAfterMhaLenS2());
					}
					if (site.getUmts21Changed().getAntenaTypeS3() != 0
							| (site.getUmts21Changed().getAntenaTypeS3Str() != null
									&& site.getUmts21Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperAfterMhaLenS3());
					}
					if (site.getUmts21Changed().getAntenaTypeS4() != 0
							| (site.getUmts21Changed().getAntenaTypeS4Str() != null
									&& site.getUmts21Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperAfterMhaLenS4());
					}
					row = sheet1.getRow(74);
					if (site.getUmts21Changed().getAntenaTypeS1() != 0
							| (site.getUmts21Changed().getAntenaTypeS1Str() != null
									&& site.getUmts21Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperPerSectorS1());
					}
					if (site.getUmts21Changed().getAntenaTypeS2() != 0
							| (site.getUmts21Changed().getAntenaTypeS2Str() != null
									&& site.getUmts21Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperPerSectorS2());
					}
					if (site.getUmts21Changed().getAntenaTypeS3() != 0
							| (site.getUmts21Changed().getAntenaTypeS3Str() != null
									&& site.getUmts21Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperPerSectorS3());
					}
					if (site.getUmts21Changed().getAntenaTypeS4() != 0
							| (site.getUmts21Changed().getAntenaTypeS4Str() != null
									&& site.getUmts21Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperPerSectorS4());
					}
				}
				if (site.getArchitectureType().equals("Distribute")) { // Or
																		// populate
																		// distribute
																		// specific
																		// information.
					row = sheet1.getRow(69);
					if (site.getUmts21Changed().getAntenaTypeS1() != 0
							| (site.getUmts21Changed().getAntenaTypeS1Str() != null
									&& site.getUmts21Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getFiberLengthS1());
					}
					if (site.getUmts21Changed().getAntenaTypeS2() != 0
							| (site.getUmts21Changed().getAntenaTypeS2Str() != null
									&& site.getUmts21Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getFiberLengthS2());
					}
					if (site.getUmts21Changed().getAntenaTypeS3() != 0
							| (site.getUmts21Changed().getAntenaTypeS3Str() != null
									&& site.getUmts21Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getFiberLengthS3());
					}
					if (site.getUmts21Changed().getAntenaTypeS4() != 0
							| (site.getUmts21Changed().getAntenaTypeS4Str() != null
									&& site.getUmts21Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getFiberLengthS4());
					}
					row = sheet1.getRow(73);
					if (site.getUmts21Changed().getAntenaTypeS1() != 0
							| (site.getUmts21Changed().getAntenaTypeS1Str() != null
									&& site.getUmts21Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getDistributeJumperLengthS1());
					}
					if (site.getUmts21Changed().getAntenaTypeS2() != 0
							| (site.getUmts21Changed().getAntenaTypeS2Str() != null
									&& site.getUmts21Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getDistributeJumperLengthS2());
					}
					if (site.getUmts21Changed().getAntenaTypeS3() != 0
							| (site.getUmts21Changed().getAntenaTypeS3Str() != null
									&& site.getUmts21Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getDistributeJumperLengthS3());
					}
					if (site.getUmts21Changed().getAntenaTypeS4() != 0
							| (site.getUmts21Changed().getAntenaTypeS4Str() != null
									&& site.getUmts21Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getDistributeJumperLengthS3());
					}
				}
			}
			if (fw.getLte().isSelected()) { // Same note as for line
											// 553.
				row = sheet1.getRow(42);
				if (site.getLte18Changed().getAntenaTypeS1() != 0 | (site.getLte18Changed().getAntenaTypeS1Str() != null
						&& site.getLte18Changed().getAntenaTypeS1Str().length() != 0)) {
					cell = row.getCell(3);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getRtaaTypeS1());
				}
				if (site.getLte18Changed().getAntenaTypeS2() != 0 | (site.getLte18Changed().getAntenaTypeS2Str() != null
						&& site.getLte18Changed().getAntenaTypeS2Str().length() != 0)) {
					cell = row.getCell(4);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getRtaaTypeS2());
				}
				if (site.getLte18Changed().getAntenaTypeS3() != 0 | (site.getLte18Changed().getAntenaTypeS3Str() != null
						&& site.getLte18Changed().getAntenaTypeS3Str().length() != 0)) {
					cell = row.getCell(5);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getRtaaTypeS3());
				}
				if (site.getLte18Changed().getAntenaTypeS4() != 0 | (site.getLte18Changed().getAntenaTypeS4Str() != null
						&& site.getLte18Changed().getAntenaTypeS4Str().length() != 0)) {
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getRtaaTypeS4());
				}
				row = sheet1.getRow(78);
				if (site.getLte18Changed().getAntenaTypeS1() != 0 | (site.getLte18Changed().getAntenaTypeS1Str() != null
						&& site.getLte18Changed().getAntenaTypeS1Str().length() != 0)) {
					cell = row.getCell(3);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getFeederSizeS1());
				}
				if (site.getLte18Changed().getAntenaTypeS2() != 0 | (site.getLte18Changed().getAntenaTypeS2Str() != null
						&& site.getLte18Changed().getAntenaTypeS2Str().length() != 0)) {
					cell = row.getCell(4);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getFeederSizeS2());
				}
				if (site.getLte18Changed().getAntenaTypeS3() != 0 | (site.getLte18Changed().getAntenaTypeS3Str() != null
						&& site.getLte18Changed().getAntenaTypeS3Str().length() != 0)) {
					cell = row.getCell(5);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getFeederSizeS3());
				}
				if (site.getLte18Changed().getAntenaTypeS4() != 0 | (site.getLte18Changed().getAntenaTypeS4Str() != null
						&& site.getLte18Changed().getAntenaTypeS4Str().length() != 0)) {
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getFeederSizeS4());
				}
				if (site.getArchitectureType().equals("Common")) { // Populate
																	// common
																	// specific
																	// information.
					row = sheet1.getRow(41);
					if (site.getLte18Changed().getAntenaTypeS1() != 0
							| (site.getLte18Changed().getAntenaTypeS1Str() != null
									&& site.getLte18Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getMhaTypeS1());
					}
					if (site.getLte18Changed().getAntenaTypeS2() != 0
							| (site.getLte18Changed().getAntenaTypeS2Str() != null
									&& site.getLte18Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getMhaTypeS2());
					}
					if (site.getLte18Changed().getAntenaTypeS3() != 0
							| (site.getLte18Changed().getAntenaTypeS3Str() != null
									&& site.getLte18Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getMhaTypeS3());
					}
					if (site.getLte18Changed().getAntenaTypeS4() != 0
							| (site.getLte18Changed().getAntenaTypeS4Str() != null
									&& site.getLte18Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getMhaTypeS4());
					}
					row = sheet1.getRow(79);
					if (site.getLte18Changed().getAntenaTypeS1() != 0
							| (site.getLte18Changed().getAntenaTypeS1Str() != null
									&& site.getLte18Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getFeederLengthS1());
					}
					if (site.getLte18Changed().getAntenaTypeS2() != 0
							| (site.getLte18Changed().getAntenaTypeS2Str() != null
									&& site.getLte18Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getFeederLengthS2());
					}
					if (site.getLte18Changed().getAntenaTypeS3() != 0
							| (site.getLte18Changed().getAntenaTypeS3Str() != null
									&& site.getLte18Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getFeederLengthS3());
					}
					if (site.getLte18Changed().getAntenaTypeS4() != 0
							| (site.getLte18Changed().getAntenaTypeS4Str() != null
									&& site.getLte18Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getFeederLengthS4());
					}
					row = sheet1.getRow(80);
					if (site.getLte18Changed().getAntenaTypeS1() != 0
							| (site.getLte18Changed().getAntenaTypeS1Str() != null
									&& site.getLte18Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getEarthKitNoS1());
					}
					if (site.getLte18Changed().getAntenaTypeS2() != 0
							| (site.getLte18Changed().getAntenaTypeS2Str() != null
									&& site.getLte18Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getEarthKitNoS2());
					}
					if (site.getLte18Changed().getAntenaTypeS3() != 0
							| (site.getLte18Changed().getAntenaTypeS3Str() != null
									&& site.getLte18Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getEarthKitNoS3());
					}
					if (site.getLte18Changed().getAntenaTypeS4() != 0
							| (site.getLte18Changed().getAntenaTypeS4Str() != null
									&& site.getLte18Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getEarthKitNoS4());
					}
					row = sheet1.getRow(81);
					if (site.getLte18Changed().getAntenaTypeS1() != 0
							| (site.getLte18Changed().getAntenaTypeS1Str() != null
									&& site.getLte18Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperAtBtsLenS1());
					}
					if (site.getLte18Changed().getAntenaTypeS2() != 0
							| (site.getLte18Changed().getAntenaTypeS2Str() != null
									&& site.getLte18Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperAtBtsLenS2());
					}
					if (site.getLte18Changed().getAntenaTypeS3() != 0
							| (site.getLte18Changed().getAntenaTypeS3Str() != null
									&& site.getLte18Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperAtBtsLenS3());
					}
					if (site.getLte18Changed().getAntenaTypeS4() != 0
							| (site.getLte18Changed().getAntenaTypeS4Str() != null
									&& site.getLte18Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperAtBtsLenS4());
					}
					row = sheet1.getRow(82);
					if (site.getLte18Changed().getAntenaTypeS1() != 0
							| (site.getLte18Changed().getAntenaTypeS1Str() != null
									&& site.getLte18Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperBeforeMhaLenS1());
					}
					if (site.getLte18Changed().getAntenaTypeS2() != 0
							| (site.getLte18Changed().getAntenaTypeS2Str() != null
									&& site.getLte18Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperBeforeMhaLenS2());
					}
					if (site.getLte18Changed().getAntenaTypeS3() != 0
							| (site.getLte18Changed().getAntenaTypeS3Str() != null
									&& site.getLte18Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperBeforeMhaLenS3());
					}
					if (site.getLte18Changed().getAntenaTypeS4() != 0
							| (site.getLte18Changed().getAntenaTypeS4Str() != null
									&& site.getLte18Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperBeforeMhaLenS4());
					}
					row = sheet1.getRow(83);
					if (site.getLte18Changed().getAntenaTypeS1() != 0
							| (site.getLte18Changed().getAntenaTypeS1Str() != null
									&& site.getLte18Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperAfterMhaLenS1());
					}
					if (site.getLte18Changed().getAntenaTypeS2() != 0
							| (site.getLte18Changed().getAntenaTypeS2Str() != null
									&& site.getLte18Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperAfterMhaLenS2());
					}
					if (site.getLte18Changed().getAntenaTypeS3() != 0
							| (site.getLte18Changed().getAntenaTypeS3Str() != null
									&& site.getLte18Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperAfterMhaLenS3());
					}
					if (site.getLte18Changed().getAntenaTypeS4() != 0
							| (site.getLte18Changed().getAntenaTypeS4Str() != null
									&& site.getLte18Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperAfterMhaLenS4());
					}
					row = sheet1.getRow(84);
					if (site.getLte18Changed().getAntenaTypeS1() != 0
							| (site.getLte18Changed().getAntenaTypeS1Str() != null
									&& site.getLte18Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperPerSectorS1());
					}
					if (site.getLte18Changed().getAntenaTypeS2() != 0
							| (site.getLte18Changed().getAntenaTypeS2Str() != null
									&& site.getLte18Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperPerSectorS2());
					}
					if (site.getLte18Changed().getAntenaTypeS3() != 0
							| (site.getLte18Changed().getAntenaTypeS3Str() != null
									&& site.getLte18Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperPerSectorS3());
					}
					if (site.getLte18Changed().getAntenaTypeS4() != 0
							| (site.getLte18Changed().getAntenaTypeS4Str() != null
									&& site.getLte18Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getJumperPerSectorS4());
					}
				}
				if (site.getArchitectureType().equals("Distribute")) { // Or
																		// populate
																		// distribute
																		// specific
																		// information.
					row = sheet1.getRow(79);
					if (site.getLte18Changed().getAntenaTypeS1() != 0
							| (site.getLte18Changed().getAntenaTypeS1Str() != null
									&& site.getLte18Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getFiberLengthS1());
					}
					if (site.getLte18Changed().getAntenaTypeS2() != 0
							| (site.getLte18Changed().getAntenaTypeS2Str() != null
									&& site.getLte18Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getFiberLengthS2());
					}
					if (site.getLte18Changed().getAntenaTypeS3() != 0
							| (site.getLte18Changed().getAntenaTypeS3Str() != null
									&& site.getLte18Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getFiberLengthS3());
					}
					if (site.getLte18Changed().getAntenaTypeS4() != 0
							| (site.getLte18Changed().getAntenaTypeS4Str() != null
									&& site.getLte18Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getFiberLengthS4());
					}
					row = sheet1.getRow(83);
					if (site.getLte18Changed().getAntenaTypeS1() != 0
							| (site.getLte18Changed().getAntenaTypeS1Str() != null
									&& site.getLte18Changed().getAntenaTypeS1Str().length() != 0)) {
						cell = row.getCell(3);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getDistributeJumperLengthS1());
					}
					if (site.getLte18Changed().getAntenaTypeS2() != 0
							| (site.getLte18Changed().getAntenaTypeS2Str() != null
									&& site.getLte18Changed().getAntenaTypeS2Str().length() != 0)) {
						cell = row.getCell(4);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getDistributeJumperLengthS2());
					}
					if (site.getLte18Changed().getAntenaTypeS3() != 0
							| (site.getLte18Changed().getAntenaTypeS3Str() != null
									&& site.getLte18Changed().getAntenaTypeS3Str().length() != 0)) {
						cell = row.getCell(5);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getDistributeJumperLengthS3());
					}
					if (site.getLte18Changed().getAntenaTypeS4() != 0
							| (site.getLte18Changed().getAntenaTypeS4Str() != null
									&& site.getLte18Changed().getAntenaTypeS4Str().length() != 0)) {
						cell = row.getCell(6);
						cell.setCellType(Cell.CELL_TYPE_STRING);
						cell.setCellValue(site.getDistributeJumperLengthS3());
					}
				}
			}
			sheet1 = workbook.getSheetAt(2);
			row = sheet1.getRow(7);
			cell = row.getCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(site.getInstalationType());
			if (site.getBtsCabinetType().contains("Flatpack")) { // Find out
																	// what is
																	// cabinet
																	// type.
				row = sheet1.getRow(10);
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(1);
			} else if (site.getBtsCabinetType().contains("24x")) {
				row = sheet1.getRow(11);
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(1);
			} else if (site.getBtsCabinetType().contains("BBU")) {
				row = sheet1.getRow(12);
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(1);
			} else if (site.getBtsCabinetType().contains("FCIA")) {
				row = sheet1.getRow(13);
				cell = row.getCell(4);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(1);
			}
			if (fw.getUmts().isSelected()) { // More technology specific
												// information.
				row = sheet1.getRow(17);
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getSysModule1Type());
				row = sheet1.getRow(18);
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getSysModule1Loc());
				row = sheet1.getRow(21);
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getTransModuleType());
				row = sheet1.getRow(22);
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getE1LinesNo());
				row = sheet1.getRow(23);
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGbEthElectLinesNo());
				row = sheet1.getRow(24);
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGbEthOptLinesNo());
				row = sheet1.getRow(25);
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getRfModule1Type());
				row = sheet1.getRow(27);
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getRfModule2Type());
				row = sheet1.getRow(29);
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getRfModule3Type());
				if (site.getRfModule1Type() != null) {
					row = sheet1.getRow(26);
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getRfModule1Loc()); // Populate RF
																// module
																// location only
																// if radio
																// module exist.
				}
				if (site.getRfModule2Type() != null) {
					row = sheet1.getRow(28);
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getRfModule2Loc());
				}
				if (site.getRfModule3Type() != null) {
					row = sheet1.getRow(30);
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getRfModule3Loc());
				}
			}
			if (fw.getLte().isSelected()) { // More technology specific
											// information.
				row = sheet1.getRow(17);
				cell = row.getCell(7);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getSysModule1Type());
				row = sheet1.getRow(18);
				cell = row.getCell(7);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getSysModule1Loc());
				row = sheet1.getRow(22);
				cell = row.getCell(7);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getE1LinesNo());
				row = sheet1.getRow(23);
				cell = row.getCell(7);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGbEthElectLinesNo());
				row = sheet1.getRow(24);
				cell = row.getCell(7);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getGbEthOptLinesNo());
				if (site.getOverTransSiteCode().charAt(2) == 'U') { // Depending
																	// what we
																	// use for
																	// transmission
																	// different
																	// cells are
																	// populate.
					row = sheet1.getRow(21);
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getOverTransModule());
					row = sheet1.getRow(22);
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getOverE1LinesNo());
					row = sheet1.getRow(23);
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getOverGbEthElectLinesNo());
					row = sheet1.getRow(24);
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getOverGbEthOptLinesNo());
				} else {
					row = sheet1.getRow(21);
					cell = row.getCell(4);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getOverTransModule());
					row = sheet1.getRow(22);
					cell = row.getCell(4);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getOverE1LinesNo());
					row = sheet1.getRow(23);
					cell = row.getCell(4);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getOverGbEthElectLinesNo());
					row = sheet1.getRow(24);
					cell = row.getCell(4);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getOverGbEthOptLinesNo());
				}
				row = sheet1.getRow(25);
				cell = row.getCell(7);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getRfModule1Type());
				row = sheet1.getRow(27);
				cell = row.getCell(7);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getRfModule2Type());
				row = sheet1.getRow(29);
				cell = row.getCell(7);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getRfModule3Type());
				if (site.getRfModule1Type() != null) {
					row = sheet1.getRow(26);
					cell = row.getCell(7);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getRfModule1Loc());
				}
				if (site.getRfModule2Type() != null) {
					row = sheet1.getRow(28);
					cell = row.getCell(7);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getRfModule2Loc());
				}
				if (site.getRfModule3Type() != null) {
					row = sheet1.getRow(30);
					cell = row.getCell(7);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getRfModule3Loc());
				}
			}
			row = sheet1.getRow(45);
			cell = row.getCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(site.getPowSupplyType());
			row = sheet1.getRow(46);
			cell = row.getCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(site.getRectifierType());
			row = sheet1.getRow(47);
			cell = row.getCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(site.getRectifierPower());
			row = sheet1.getRow(48);
			cell = row.getCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(site.getRectifierQty());
			row = sheet1.getRow(51);
			cell = row.getCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(site.getBatteryType());
			row = sheet1.getRow(52);
			cell = row.getCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(site.getBatteryCap());
			row = sheet1.getRow(53);
			cell = row.getCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(site.getBatQty());
			row = sheet1.getRow(54);
			cell = row.getCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(site.getCurrent());
			row = sheet1.getRow(55);
			cell = row.getCell(4);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(site.getVoltage());
			row = sheet1.getRow(114);
			cell = row.getCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			File engFile = new File("C:\\RG input\\Engineer.txt"); // Read
																	// engineer
																	// name from
																	// "Engineer.txt"
																	// and
																	// populate
																	// specific
																	// cell.
			FileReader engFileReader = new FileReader(engFile);
			BufferedReader engBufferReader = new BufferedReader(engFileReader);
			String engName = engBufferReader.readLine();
			engBufferReader.close();
			cell.setCellValue(engName);
			sheet1 = workbook.getSheetAt(3);
			row = sheet1.getRow(99);
			cell = row.getCell(5);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(site.getSiteInfo());
			row = sheet1.getRow(102);
			cell = row.getCell(5);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(site.getCommFile());
			row = sheet1.getRow(103);
			cell = row.getCell(5);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(site.getCrFile());
			if (fw.getUmts().isSelected()) {
				row = sheet1.getRow(19);
				cell = row.getCell(9);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getSwVersion());
				row = sheet1.getRow(39);
				cell = row.getCell(7);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getSiteConfig());
				row = sheet1.getRow(42);
				cell = row.getCell(7);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getTrsIp());
				row = sheet1.getRow(43);
				cell = row.getCell(7);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getNodebIp());
				row = sheet1.getRow(44);
				cell = row.getCell(7);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getRncIp());
				row = sheet1.getRow(45);
				cell = row.getCell(7);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getRcuaIp());
				row = sheet1.getRow(60);
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String rawStr = "", forCell = "";
				for (int i = 0; i < site.getCellIdS1().length; i++) { // Iterate
																		// through
																		// cell
																		// IDs
																		// for
																		// specific
																		// cell
																		// and
																		// Concatenate
																		// them
																		// with
																		// "/"
																		// between
																		// cell
																		// IDs.
					rawStr = site.getCellIdS1()[i];
					if (rawStr != null) {
						forCell += rawStr + " / ";
					}
				}
				if (!forCell.equals("")) {
					forCell = forCell.substring(0, forCell.length() - 3); // Subtract
																			// last
																			// added
																			// "
																			// /
																			// ".
				}
				cell.setCellValue(forCell);
				row = sheet1.getRow(61);
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				rawStr = "";
				forCell = "";
				for (int i = 0; i < site.getCellIdS2().length; i++) {
					rawStr = site.getCellIdS2()[i];
					if (rawStr != null) {
						forCell += rawStr + " / ";
					}
				}
				if (!forCell.equals("")) {
					forCell = forCell.substring(0, forCell.length() - 3);
				}
				cell.setCellValue(forCell);
				row = sheet1.getRow(62);
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				rawStr = "";
				forCell = "";
				for (int i = 0; i < site.getCellIdS3().length; i++) {
					rawStr = site.getCellIdS3()[i];
					if (rawStr != null) {
						forCell += rawStr + " / ";
					}
				}
				if (!forCell.equals("")) {
					forCell = forCell.substring(0, forCell.length() - 3);
				}
				cell.setCellValue(forCell);
				row = sheet1.getRow(63);
				cell = row.getCell(3);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				rawStr = "";
				forCell = "";
				for (int i = 0; i < site.getCellIdS4().length; i++) {
					rawStr = site.getCellIdS4()[i];
					if (rawStr != null) {
						forCell += rawStr + " / ";
					}
				}
				if (!forCell.equals("")) {
					forCell = forCell.substring(0, forCell.length() - 3);
				}
				cell.setCellValue(forCell);
				row = sheet1.getRow(60);
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				if (site.getUmts21Changed().getAntenaTypeS1() != 0
						| (site.getUmts21Changed().getAntenaTypeS1Str() != null
								&& site.getUmts21Changed().getAntenaTypeS1Str().length() != 0)) {
					cell.setCellValue(site.getUmts21Changed().getElectricalTiltS1());
				}
				row = sheet1.getRow(61);
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				if (site.getUmts21Changed().getAntenaTypeS2() != 0
						| (site.getUmts21Changed().getAntenaTypeS2Str() != null
								&& site.getUmts21Changed().getAntenaTypeS2Str().length() != 0)) {
					cell.setCellValue(site.getUmts21Changed().getElectricalTiltS2());
				}
				row = sheet1.getRow(62);
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				if (site.getUmts21Changed().getAntenaTypeS3() != 0
						| (site.getUmts21Changed().getAntenaTypeS3Str() != null
								&& site.getUmts21Changed().getAntenaTypeS3Str().length() != 0)) {
					cell.setCellValue(site.getUmts21Changed().getElectricalTiltS3());
				}
				row = sheet1.getRow(63);
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				if (site.getUmts21Changed().getAntenaTypeS4() != 0
						| (site.getUmts21Changed().getAntenaTypeS4Str() != null
								&& site.getUmts21Changed().getAntenaTypeS4Str().length() != 0)) {
					cell.setCellValue(site.getUmts21Changed().getElectricalTiltS4());
				}
				row = sheet1.getRow(60);
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				int rawInt = 0;
				forCell = "";
				for (int i = 0; i < site.getUarfcnS1().length; i++) { // Iterate
																		// through
																		// channel
																		// numbers
																		// for
																		// specific
																		// cell
																		// and
																		// Concatenate
																		// them
																		// with
																		// "/"
																		// between
																		// channels.
					rawInt = site.getUarfcnS1()[i];
					if (rawInt != 0) {
						forCell += rawInt + " / ";
					}
				}
				if (!forCell.equals("")) {
					forCell = forCell.substring(0, forCell.length() - 3); // Subtract
																			// last
																			// added
																			// "
																			// /
																			// ".
				}
				cell.setCellValue(forCell);
				row = sheet1.getRow(61);
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				rawInt = 0;
				forCell = "";
				for (int i = 0; i < site.getUarfcnS2().length; i++) {
					rawInt = site.getUarfcnS2()[i];
					if (rawInt != 0) {
						forCell += rawInt + " / ";
					}
				}
				if (!forCell.equals("")) {
					forCell = forCell.substring(0, forCell.length() - 3);
				}
				cell.setCellValue(forCell);
				row = sheet1.getRow(62);
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				rawInt = 0;
				forCell = "";
				for (int i = 0; i < site.getUarfcnS3().length; i++) {
					rawInt = site.getUarfcnS3()[i];
					if (rawInt != 0) {
						forCell += rawInt + " / ";
					}
				}
				if (!forCell.equals("")) {
					forCell = forCell.substring(0, forCell.length() - 3);
				}
				cell.setCellValue(forCell);
				row = sheet1.getRow(63);
				cell = row.getCell(6);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				rawInt = 0;
				forCell = "";
				for (int i = 0; i < site.getUarfcnS4().length; i++) {
					rawInt = site.getUarfcnS4()[i];
					if (rawInt != 0) {
						forCell += rawInt + " / ";
					}
				}
				if (!forCell.equals("")) {
					forCell = forCell.substring(0, forCell.length() - 3);
				}
				cell.setCellValue(forCell);

				if (site.getExtAl1Name() != null) { // Populate external alarms
													// table.
					row = sheet1.getRow(76);
					cell = row.getCell(2);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl1());
					cell = row.getCell(3);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl1Name());
					cell = row.getCell(5);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl1Pol());
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl1Sev());
				}
				if (site.getExtAl2Name() != null) {
					row = sheet1.getRow(77);
					cell = row.getCell(2);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl2());
					cell = row.getCell(3);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl2Name());
					cell = row.getCell(5);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl2Pol());
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl2Sev());
				}
				if (site.getExtAl3Name() != null) {
					row = sheet1.getRow(78);
					cell = row.getCell(2);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl3());
					cell = row.getCell(3);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl3Name());
					cell = row.getCell(5);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl3Pol());
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl3Sev());
				}
				if (site.getExtAl4Name() != null) {
					row = sheet1.getRow(79);
					cell = row.getCell(2);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl4());
					cell = row.getCell(3);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl4Name());
					cell = row.getCell(5);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl4Pol());
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl4Sev());
				}
				if (site.getExtAl5Name() != null) {
					row = sheet1.getRow(80);
					cell = row.getCell(2);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl5());
					cell = row.getCell(3);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl5Name());
					cell = row.getCell(5);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl5Pol());
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl5Sev());
				}
				if (site.getExtAl6Name() != null) {
					row = sheet1.getRow(81);
					cell = row.getCell(2);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl6());
					cell = row.getCell(3);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl6Name());
					cell = row.getCell(5);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl6Pol());
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl6Sev());
				}
				if (site.getExtAl7Name() != null) {
					row = sheet1.getRow(82);
					cell = row.getCell(2);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl7());
					cell = row.getCell(3);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl7Name());
					cell = row.getCell(5);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl7Pol());
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl7Sev());
				}
				if (site.getExtAl8Name() != null) {
					row = sheet1.getRow(83);
					cell = row.getCell(2);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl8());
					cell = row.getCell(3);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl8Name());
					cell = row.getCell(5);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl8Pol());
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl8Sev());
				}
				if (site.getExtAl9Name() != null) {
					row = sheet1.getRow(84);
					cell = row.getCell(2);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl9());
					cell = row.getCell(3);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl9Name());
					cell = row.getCell(5);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl9Pol());
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl9Sev());
				}
				if (site.getExtAl10Name() != null) {
					row = sheet1.getRow(85);
					cell = row.getCell(2);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl10());
					cell = row.getCell(3);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl10Name());
					cell = row.getCell(5);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl10Pol());
					cell = row.getCell(6);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue(site.getExtAl10Sev());
				}
			}
			if (fw.getLte().isSelected()) { // Populate information
											// specific for LTE.
				row = sheet1.getRow(19);
				cell = row.getCell(12);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(site.getSwVersion());
				row = sheet1.getRow(66);
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				if (site.getLte18Changed().getAntenaTypeS1() != 0 | (site.getLte18Changed().getAntenaTypeS1Str() != null
						&& site.getLte18Changed().getAntenaTypeS1Str().length() != 0)) {
					cell.setCellValue(site.getLte18Changed().getElectricalTiltS1());
				}
				row = sheet1.getRow(67);
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				if (site.getLte18Changed().getAntenaTypeS2() != 0 | (site.getLte18Changed().getAntenaTypeS2Str() != null
						&& site.getLte18Changed().getAntenaTypeS2Str().length() != 0)) {
					cell.setCellValue(site.getLte18Changed().getElectricalTiltS2());
				}
				row = sheet1.getRow(68);
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				if (site.getLte18Changed().getAntenaTypeS3() != 0 | (site.getLte18Changed().getAntenaTypeS3Str() != null
						&& site.getLte18Changed().getAntenaTypeS3Str().length() != 0)) {
					cell.setCellValue(site.getLte18Changed().getElectricalTiltS3());
				}
				row = sheet1.getRow(69);
				cell = row.getCell(5);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				if (site.getLte18Changed().getAntenaTypeS4() != 0 | (site.getLte18Changed().getAntenaTypeS4Str() != null
						&& site.getLte18Changed().getAntenaTypeS4Str().length() != 0)) {
					cell.setCellValue(site.getLte18Changed().getElectricalTiltS4());
				}
			}

			FileOutputStream fOutputStream = new FileOutputStream(getTargetFile());
			XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook); // This is
																	// used to
																	// tell
																	// excel to
																	// recalculate
																	// all
																	// formulas
																	// that have
																	// in
																	// itself.
			workbook.write(fOutputStream); // With this we write all data to
											// workbook.

			fOutputStream.close();
			fInputStream.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
