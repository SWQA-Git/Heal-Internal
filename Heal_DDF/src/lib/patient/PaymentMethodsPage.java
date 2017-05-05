package lib.patient;

import java.io.IOException;

import jxl.read.biff.BiffException;
import lib.common.Browser;
import lib.common.ConfigurationProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;

public class PaymentMethodsPage extends Browser {
	private static final Logger logger = LogManager
			.getLogger(PaymentMethodsPage.class.getName());
	private ConfigurationProperty config = ConfigurationProperty.getInstance();

	public PaymentMethodsPage(String browserName) {
		super(browserName);
	}

	public PaymentMethodsPage() {
	}

	public void loadURL(String elementName) {
		loadURL("Properties", elementName);
	}

	public void enterValue(String fieldname, String fieldvalue) {
		clearField("PaymentMethods", fieldname);
		enterValue("PaymentMethods", fieldname, fieldvalue);
	}

	public void clickOn(String fieldname) {
		clickOn("PaymentMethods", fieldname);
	}

	public void loadPaymentURL() {
		loadURL("HealPaymentMethodsURL");
	}

	public void clickOnPaymentMethods() {
		clickOn("Home", "PaymentMethods");
	}

	public void loadInvalidPaymentURL() throws NoSuchElementException,
			BiffException, IOException {
		loadURL("invalidPaymentUrl");
		waitForElementPresent("HealRegistration", "FirstName");
	}

	public void loadInvalidPaymentURL01() throws NoSuchElementException,
			BiffException, IOException {
		loadURL("invalidPaymentUrl01");
		waitForElementPresent("HealRegistration", "FirstName");
	}

	public void loadInvalidPaymentURL02() throws NoSuchElementException,
			BiffException, IOException {
		loadURL("invalidPaymentUrl02");
		waitForElementPresent("HealRegistration", "FirstName");
	}

	public void enterPayamentDetails() {
		enterValue("CardNumber",
				config.getDataValue("PaymentMethods", "CardNumber"));
		enterValue("Expiration",
				config.getDataValue("PaymentMethods", "Expiration"));
		enterValue("SecurityCode",
				config.getDataValue("PaymentMethods", "SecurityCode"));
	}

	public void enterPayamentDetailsFor15Digits() {
		enterValue("CardNumber",
				config.getDataValue("PaymentMethods", "CardNumberWith15digits"));
		enterValue("Expiration",
				config.getDataValue("PaymentMethods", "ExpirationDate15"));
		enterValue("SecurityCode",
				config.getDataValue("PaymentMethods", "SecurityCode"));
	}

	public void enterPayamentDetailsFor14Digits() {
		enterValue("CardNumber",
				config.getDataValue("PaymentMethods", "CardNumberWith14digits"));
		enterValue("Expiration",
				config.getDataValue("PaymentMethods", "ExpirationDate14"));
		enterValue("SecurityCode",
				config.getDataValue("PaymentMethods", "SecurityCode"));
	}

	public void enterInvalidCardNumber() {
		enterValue("CardNumber",
				config.getDataValue("PaymentMethods", "InvalidCardNum"));
	}

	public void clearCardNumber() {
		clearField("PaymentMethods", "CardNumber");
	}

	public void clickOnApplyCard() {
		clickOn("ApplyCard");
	}

	public void enterExpiryDateWithInvalidMonth() {
		enterValue("Expiration",
				config.getDataValue("PaymentMethods", "InvalidMonth"));
	}

	public void enterExpiryDateWithSpecialChar() {
		enterValue("Expiration", config.getDataValue("PaymentMethods",
				"AlphabetsAndSpecialCharacters"));
	}

	public void enterExpiryDateWithDoubleSlash() {
		enterValue("Expiration",
				config.getDataValue("PaymentMethods", "DoubleSlash"));

	}
	
	public void enterExpiryDateInFormatMMYY(){
		enterValue("Expiration",
				config.getDataValue("PaymentMethods", "ExpirationDateInDiffFormat"));
	}

	public void enterExpiryDateWithPastDate() {
		enterValue("Expiration",
				config.getDataValue("PaymentMethods", "PastDate"));

	}

	public void clearExpiryDate() {
		clearField("PaymentMethods", "Expiration");

	}
	
	public void enterSecurityCodeWith4Digits() {
		enterValue("SecurityCode",
				config.getDataValue("PaymentMethods", "4Digits"));
	}
	public void enterSecurityCodeMoreThan4Digits() {
		enterValue("SecurityCode",
				config.getDataValue("PaymentMethods", "MoreThan4Digits"));
	}
	
	public void enterSecurityCodeWithSplChr(){
		enterValue("SecurityCode",
				config.getDataValue("PaymentMethods", "SecuritySplChr"));
	}
	public void clearSecurityCode() {
		clearField("PaymentMethods", "SecurityCode");
	}
	
}
