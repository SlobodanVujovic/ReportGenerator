/*
 * Class that keep data from UP table.
 */

package reportGenerator;

public class AffectedSite {
	private String siteCode, antenaTypeS1Str, antenaTypeS2Str, antenaTypeS3Str, antenaTypeS4Str;
	private String[] antennaTypeStr = new String[4];
	private int[] azimuth = new int[4], mechanicalTilt = new int[4], electricalTilt = new int[4],
			antenaTypeInt = new int[4];
	private int azimuthS1, azimuthS2, azimuthS3, azimuthS4, mechanicalTiltS1, mechanicalTiltS2, mechanicalTiltS3,
			mechanicalTiltS4, electricalTiltS1, electricalTiltS2, electricalTiltS3, electricalTiltS4, antenaTypeS1,
			antenaTypeS2, antenaTypeS3, antenaTypeS4, numOfSectors;
	private float antHighS1, antHighS2, antHighS3, antHighS4;
	private float[] antHigh = new float[4];

	public String getSiteCode() {
		return this.siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public int getAntenaTypeS1() {
		return this.antenaTypeS1;
	}

	public void setAntenaTypeS1(int antenaTypeS1) {
		this.antenaTypeS1 = antenaTypeS1;
	}

	public int getAntenaTypeS2() {
		return this.antenaTypeS2;
	}

	public void setAntenaTypeS2(int antenaTypeS2) {
		this.antenaTypeS2 = antenaTypeS2;
	}

	public int getAntenaTypeS3() {
		return this.antenaTypeS3;
	}

	public void setAntenaTypeS3(int antenaTypeS3) {
		this.antenaTypeS3 = antenaTypeS3;
	}

	public int getAntenaTypeS4() {
		return this.antenaTypeS4;
	}

	public void setAntenaTypeS4(int antenaTypeS4) {
		this.antenaTypeS4 = antenaTypeS4;
	}

	public String getAntenaTypeS1Str() {
		return this.antenaTypeS1Str;
	}

	public void setAntenaTypeS1Str(String antenaTypeS1Str) {
		this.antenaTypeS1Str = antenaTypeS1Str;
	}

	public String getAntenaTypeS2Str() {
		return this.antenaTypeS2Str;
	}

	public void setAntenaTypeS2Str(String antenaTypeS2Str) {
		this.antenaTypeS2Str = antenaTypeS2Str;
	}

	public String getAntenaTypeS3Str() {
		return this.antenaTypeS3Str;
	}

	public void setAntenaTypeS3Str(String antenaTypeS3Str) {
		this.antenaTypeS3Str = antenaTypeS3Str;
	}

	public String getAntenaTypeS4Str() {
		return this.antenaTypeS4Str;
	}

	public void setAntenaTypeS4Str(String antenaTypeS4Str) {
		this.antenaTypeS4Str = antenaTypeS4Str;
	}

	public int getAzimuthS1() {
		return this.azimuthS1;
	}

	public void setAzimuthS1(int azimuthS1) {
		this.azimuthS1 = azimuthS1;
	}

	public int getAzimuthS2() {
		return this.azimuthS2;
	}

	public void setAzimuthS2(int azimuthS2) {
		this.azimuthS2 = azimuthS2;
	}

	public int getAzimuthS3() {
		return this.azimuthS3;
	}

	public void setAzimuthS3(int azimuthS3) {
		this.azimuthS3 = azimuthS3;
	}

	public int getAzimuthS4() {
		return this.azimuthS4;
	}

	public void setAzimuthS4(int azimuthS4) {
		this.azimuthS4 = azimuthS4;
	}

	public int getMechanicalTiltS1() {
		return this.mechanicalTiltS1;
	}

	public void setMechanicalTiltS1(int mechanicalTiltS1) {
		this.mechanicalTiltS1 = mechanicalTiltS1;
	}

	public int getMechanicalTiltS2() {
		return this.mechanicalTiltS2;
	}

	public void setMechanicalTiltS2(int mechanicalTiltS2) {
		this.mechanicalTiltS2 = mechanicalTiltS2;
	}

	public int getMechanicalTiltS3() {
		return this.mechanicalTiltS3;
	}

	public void setMechanicalTiltS3(int mechanicalTiltS3) {
		this.mechanicalTiltS3 = mechanicalTiltS3;
	}

	public int getMechanicalTiltS4() {
		return this.mechanicalTiltS4;
	}

	public void setMechanicalTiltS4(int mechanicalTiltS4) {
		this.mechanicalTiltS4 = mechanicalTiltS4;
	}

	public int getElectricalTiltS1() {
		return this.electricalTiltS1;
	}

	public void setElectricalTiltS1(int electricalTiltS1) {
		this.electricalTiltS1 = electricalTiltS1;
	}

	public int getElectricalTiltS2() {
		return this.electricalTiltS2;
	}

	public void setElectricalTiltS2(int electricalTiltS2) {
		this.electricalTiltS2 = electricalTiltS2;
	}

	public int getElectricalTiltS3() {
		return this.electricalTiltS3;
	}

	public void setElectricalTiltS3(int electricalTiltS3) {
		this.electricalTiltS3 = electricalTiltS3;
	}

	public int getElectricalTiltS4() {
		return this.electricalTiltS4;
	}

	public void setElectricalTiltS4(int electricalTiltS4) {
		this.electricalTiltS4 = electricalTiltS4;
	}

	public float getAntHighS1() {
		return this.antHighS1;
	}

	public void setAntHighS1(float antHighS1) {
		this.antHighS1 = antHighS1;
	}

	public float getAntHighS2() {
		return this.antHighS2;
	}

	public void setAntHighS2(float antHighS2) {
		this.antHighS2 = antHighS2;
	}

	public float getAntHighS3() {
		return this.antHighS3;
	}

	public void setAntHighS3(float antHighS3) {
		this.antHighS3 = antHighS3;
	}

	public float getAntHighS4() {
		return this.antHighS4;
	}

	public void setAntHighS4(float antHighS4) {
		this.antHighS4 = antHighS4;
	}

	public int getNumOfSectors() {
		return this.numOfSectors;
	}

	/*
	 * According to antenna determine number of sectors of BTS.
	 */
	public void setNumOfSectors() {
		if (getAntenaTypeS1() != 0) {
			this.numOfSectors = 1;
		}
		if (getAntenaTypeS2() != 0) {
			this.numOfSectors = 2;
		}
		if (getAntenaTypeS3() != 0) {
			this.numOfSectors = 3;
		}
		if (getAntenaTypeS4() != 0) {
			this.numOfSectors = 4;
		}
	}
}
