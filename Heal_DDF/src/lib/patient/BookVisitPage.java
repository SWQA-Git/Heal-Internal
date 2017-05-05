package lib.patient;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import lib.common.Browser;
import lib.common.ConfigurationProperty;

public class BookVisitPage extends Browser {
	private static final Logger logger = LogManager
			.getLogger(BookVisitPage.class.getName());
	private Browser browser;
	private ConfigurationProperty config = ConfigurationProperty.getInstance();

	public BookVisitPage() {
		browser = new Browser();
	}

	public void clickOnBookVisits() {
		browser.clickOn("Home", "BookVisit");
	}

	public void clickOnBookVisitEmergencyYES() {
		browser.clickOn("BookVisit", "EmergencyYesButton");
	}

	public void clickOnBookVisitEmergencyNO() {
		browser.clickOn("BookVisit", "EmergencyNoButton");
	}

	public void clickOnOkButton() {
		browser.clickOn("BookVisit", "YesOKButton");
	}

	public void clickOnAddPatient(){
		
	}
	
	public void clickOnExistingPatient() {
		browser.clickOn("BookVisit", "ExistingPatient");
		String ExistingPatient = browser.getText("BookVisit", "ExistingPatient");
		System.out.println("Clicked on Existing patient - " + ExistingPatient);
		browser.clickOn("BookVisit", "ContinueButton");
		System.out.println("Clicked on Continue Button");
	}
	
	public void validBookVisitUrl() throws InterruptedException{
		loadURL("Properties","validBookVisitUrl");
		Thread.sleep(5000);
		System.out.println("Loaded URL: "
				+ config.getDataValue("Properties", "validBookVisitUrl"));
		logger.info("Loaded URL: "
				+ config.getDataValue("Properties", "validBookVisitUrl"));
	}
	
	public void invalidBookVisitUrl()throws InterruptedException{
		loadURL("Properties","invalidBookVisitUrl");
		Thread.sleep(5000);
		System.out.println("Loaded URL: "
				+ config.getDataValue("Properties", "invalidBookVisitUrl"));
		logger.info("Loaded URL: "
				+ config.getDataValue("Properties", "invalidBookVisitUrl"));
	}
	public void invalidBookVisitUrl01()throws InterruptedException{
		loadURL("Properties","invalidBookVisitUrl");
		Thread.sleep(5000);
		System.out.println("Loaded URL: "
				+ config.getDataValue("Properties", "invalidBookVisitUrl01"));
		logger.info("Loaded URL: "
				+ config.getDataValue("Properties", "invalidBookVisitUrl01"));
	}
}
