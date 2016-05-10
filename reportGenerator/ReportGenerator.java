/**
 * This is program for generating excel report after Telekom SRB NSN (e)NodeB installation (UMTS and LTE).
 * Program use different input files to collect data from and then use template report which is copied and
 * appropriate populate depending on type and configuration of BTS. (This is main class - program start).
 * 
 * @author Slobodan Vujovic {@literal <slobodan.vujovic@roamingnetworks.rs>}
 * 
 * @version 1.0
 * @since 03.03.2016.
 * 
 */

package reportGenerator;

public class ReportGenerator {

	public static void main(String[] args) {
		Engineer engineer = new Engineer(); // All start with engineer name. If
											// "Engineer.txt" doesn't exist,
											// window to provide name pop up, if
											// file exist, FrontWindow is
											// created.
		engineer.createSubmitWindow();
	}
}