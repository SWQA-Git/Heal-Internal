package test.patient;

import java.io.IOException;

import jxl.read.biff.BiffException;
import lib.common.TestBase;
import lib.common.Utilities;
import lib.patient.HealPatientLoginPage;
import lib.patient.PaymentMethodsPage;
import lib.patient.RegisterPage;
import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import verification.patient.HomePageVerifier;
import verification.patient.PaymentMethodsPageVerifier;
import verification.patient.RegisterPageVerifier;

public class PaymentMethodsTest extends TestBase {
	public PaymentMethodsTest() throws NullPointerException, BiffException,
			IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final Logger logger = LogManager
			.getLogger(PaymentMethodsTest.class.getName());

	HealPatientLoginPage healPatientLoginPage;
	PaymentMethodsPage paymentMethodsPage;
	PaymentMethodsPageVerifier paymentMethodsPageVerifier;
	RegisterPage registerPage;
	RegisterPageVerifier registerPageVerifier;
	HomePageVerifier homePageVerifier;

	@BeforeTest
	public void SetUp() throws BiffException, NullPointerException, IOException {
		initializeDriver();
	}

	@Test(priority =1)
	public void testCheckRedirectToPaymentMethods() throws Exception {
		Utilities.executionStartedMethod();
		healPatientLoginPage = new HealPatientLoginPage();
		healPatientLoginPage.login();
		paymentMethodsPage = new PaymentMethodsPage();
		paymentMethodsPage.loadPaymentURL();
		paymentMethodsPageVerifier = new PaymentMethodsPageVerifier();
		assertTrue(paymentMethodsPageVerifier.verifyHeader(),
				"Incorrect/Missing Header");
		healPatientLoginPage.logout();
		Utilities.executionEndedMethod();
	}

	@Test(priority =2)
	public void testCheckSecurityRedirectToPayments() throws Exception {
		Utilities.executionStartedMethod();
		paymentMethodsPage = new PaymentMethodsPage();
		paymentMethodsPage.loadInvalidPaymentURL();
		paymentMethodsPageVerifier = new PaymentMethodsPageVerifier();
		assertTrue(paymentMethodsPageVerifier.verifyURLRedriectToRegister(),
				"Redriected to incorrect URL");
		paymentMethodsPage.loadInvalidPaymentURL01();
		assertTrue(paymentMethodsPageVerifier.verifyURLRedriectToRegister(),
				"Redriected to incorrect URL");
		paymentMethodsPage.loadInvalidPaymentURL02();
		assertTrue(paymentMethodsPageVerifier.verifyURLRedriectToRegister(),
				"Redriected to incorrect URL");
		Utilities.executionEndedMethod();
	}

	@Test(priority =3)
	public void testElementsOfPayment() throws Exception {
		Utilities.executionStartedMethod();
		registerPage = new RegisterPage("Browser");
		registerPage.newRegistration();
		paymentMethodsPage = new PaymentMethodsPage();
		paymentMethodsPage.clickOnPaymentMethods();
		homePageVerifier = new HomePageVerifier();
		assertTrue(homePageVerifier.verifyTitle(),
				"Incorrect/Mismatch title of the page");
		assertTrue(homePageVerifier.verifyLinkHome(), "Link home is missing");
		assertTrue(homePageVerifier.verifyLinkBookVisit(),
				"Link BookVisit is missing");
		assertTrue(homePageVerifier.verifyLinkVisits(),
				"Link Visits is missing");
		assertTrue(homePageVerifier.verifyLinkProfiles(),
				"Link Profiles is missing");
		assertTrue(homePageVerifier.verifyLinkPaymentMethods(),
				"Link PaymentMethods is missing");
		assertTrue(homePageVerifier.verifyLinkSignOut(),
				"Link SignOut is missing");
		assertTrue(homePageVerifier.verifyLinkPrivacyPolicy(),
				"Link PrivacyPolicy is missing");
		assertTrue(homePageVerifier.verifyLinkTermsofService(),
				"Link Terms Of Service is missing");
		assertTrue(homePageVerifier.verifyLinkNoticeofPrivacyPractices(),
				"Link Notice of Privacy Practices is missing");
		assertTrue(homePageVerifier.verifyLinkNondiscriminationNotice(),
				"Link Nondiscrimination Notice is missing");
		assertTrue(homePageVerifier.verifyLabelHelpLine(),
				"Label of Helpline is missing/mismatch");
		paymentMethodsPageVerifier = new PaymentMethodsPageVerifier();
		assertTrue(paymentMethodsPageVerifier.verifyHeader(),
				"Incorrect/Missing Header");
		assertTrue(paymentMethodsPageVerifier.verifySubHeader(),
				"Incorrect/Missing SubHeader");
		assertTrue(paymentMethodsPageVerifier.verifyCardNumberLabel(), "");
		// assertTrue(paymentMethodsPageVerifier.verifyInputFieldCardNumber(),"");
		assertTrue(paymentMethodsPageVerifier.verifyExpirationLabel(), "");
		// assertTrue(paymentMethodsPageVerifier.verifyInputFieldExpiration(),"");
		assertTrue(paymentMethodsPageVerifier.verifySecurityCodeLabel(), "");
		// assertTrue(paymentMethodsPageVerifier.verifyInputFieldSecurityCode(),"");
		assertTrue(paymentMethodsPageVerifier.verifyApplyCardLabel(), "");
		assertTrue(paymentMethodsPageVerifier.verifyButtonApplyCard(), "");
		healPatientLoginPage = new HealPatientLoginPage();
		healPatientLoginPage.logout();
		Utilities.executionEndedMethod();
	}

	@Test (priority =12)
	public void testAddPayments() throws Exception {
		Utilities.executionStartedMethod();
		registerPage = new RegisterPage("Browser");
		registerPage.newRegistration();
		paymentMethodsPage = new PaymentMethodsPage();
		paymentMethodsPage.clickOnPaymentMethods();
		paymentMethodsPage.enterPayamentDetails();
		paymentMethodsPage.clickOnApplyCard();
		paymentMethodsPageVerifier = new PaymentMethodsPageVerifier();
		assertTrue(paymentMethodsPageVerifier.verifyHeader(),
				"Incorrect/Missing Header");
		assertTrue(paymentMethodsPageVerifier.verifySubPaymentsDetails(),
				"Incorrect/Missing subheader payment details");
		assertTrue(paymentMethodsPageVerifier.verifyCardBrandVisa(),
				"Incorrect/missing brand name");
		assertTrue(paymentMethodsPageVerifier.verifyCardNumber(),
				"Incorrec/missing card number");
		assertTrue(paymentMethodsPageVerifier.verifyExpirationDate(),
				"Incorrect/missing expiration date");
		assertTrue(paymentMethodsPageVerifier.verifyEditPaymentButtonLabel(),
				"Incorrect/missing button edit payment");
		healPatientLoginPage = new HealPatientLoginPage();
		healPatientLoginPage.logout();
		Utilities.executionEndedMethod();
	}

	@Test(priority =4)
	public void testAddPaymentsWith14DigitsCardnumber() throws Exception {
		Utilities.executionStartedMethod();
		registerPage = new RegisterPage("Browser");
		registerPage.newRegistration();
		paymentMethodsPage = new PaymentMethodsPage();
		paymentMethodsPage.clickOnPaymentMethods();
		paymentMethodsPage.enterPayamentDetailsFor14Digits();
		paymentMethodsPage.clickOnApplyCard();
		paymentMethodsPageVerifier = new PaymentMethodsPageVerifier();
		assertTrue(paymentMethodsPageVerifier.verifyHeader(),
				"Incorrect/Missing Header");
		assertTrue(paymentMethodsPageVerifier.verifySubPaymentsDetails(),
				"Incorrect/Missing subheader payment details");
		assertTrue(paymentMethodsPageVerifier.verifyCardBrandDinnersClub(),
				"Incorrect/missing brand name");
		assertTrue(paymentMethodsPageVerifier.verifyCardNumber14Digits(),
				"Incorrec/missing card number");
		assertTrue(paymentMethodsPageVerifier.verifyExpirationDate14(),
				"Incorrect/missing expiration date");
		assertTrue(paymentMethodsPageVerifier.verifyEditPaymentButtonLabel(),
				"Incorrect/missing button edit payment");
		healPatientLoginPage = new HealPatientLoginPage();
		healPatientLoginPage.logout();
		Utilities.executionEndedMethod();
	}

	@Test(priority =5)
	public void testAddPaymentsWith15DigitsCardnumber() throws Exception {
		Utilities.executionStartedMethod();
		registerPage = new RegisterPage("Browser");
		registerPage.newRegistration();
		paymentMethodsPage = new PaymentMethodsPage();
		paymentMethodsPage.clickOnPaymentMethods();
		paymentMethodsPage.enterPayamentDetailsFor15Digits();
		paymentMethodsPage.clickOnApplyCard();
		paymentMethodsPageVerifier = new PaymentMethodsPageVerifier();
		assertTrue(paymentMethodsPageVerifier.verifyHeader(),
				"Incorrect/Missing Header");
		assertTrue(paymentMethodsPageVerifier.verifySubPaymentsDetails(),
				"Incorrect/Missing subheader payment details");
		assertTrue(paymentMethodsPageVerifier.verifyCardBrandAmericanExpress(),
				"Incorrect/missing brand name");
		assertTrue(paymentMethodsPageVerifier.verifyCardNumber15Digits(),
				"Incorrec/missing card number");
		assertTrue(paymentMethodsPageVerifier.verifyExpirationDate15(),
				"Incorrect/missing expiration date");
		assertTrue(paymentMethodsPageVerifier.verifyEditPaymentButtonLabel(),
				"Incorrect/missing button edit payment");
		healPatientLoginPage = new HealPatientLoginPage();
		healPatientLoginPage.logout();
		Utilities.executionEndedMethod();
	}

	@Test (priority =6)
	public void testInvalidCardNumber() throws Exception {
		Utilities.executionStartedMethod();
		registerPage = new RegisterPage("Browser");
		registerPage.newRegistration();
		paymentMethodsPage = new PaymentMethodsPage();
		paymentMethodsPage.clickOnPaymentMethods();
		paymentMethodsPage.enterPayamentDetails();
		paymentMethodsPage.enterInvalidCardNumber();
		paymentMethodsPage.clickOnApplyCard();
		paymentMethodsPageVerifier = new PaymentMethodsPageVerifier();
		assertTrue(
				paymentMethodsPageVerifier
						.verifyValidationForInvalidCardNumber(),
				"Incorrect/missing error message for invalid Card number");
		paymentMethodsPage.clearCardNumber();
		paymentMethodsPage.clickOnApplyCard();
		assertTrue(
				paymentMethodsPageVerifier.verifyValidationForBlankCardNumber(),
				"Incorrect/missing error message for blank Card number");
		healPatientLoginPage = new HealPatientLoginPage();
		healPatientLoginPage.logout();
		Utilities.executionEndedMethod();
	}

	@Test (priority =7)
	public void testInvalidExpirationDate() throws Exception {
		Utilities.executionStartedMethod();
		registerPage = new RegisterPage("Browser");
		registerPage.newRegistration();
		paymentMethodsPage = new PaymentMethodsPage();
		paymentMethodsPage.clickOnPaymentMethods();
		paymentMethodsPage.enterPayamentDetails();
		paymentMethodsPage.enterExpiryDateWithInvalidMonth();
		paymentMethodsPage.clickOnApplyCard();
		paymentMethodsPageVerifier = new PaymentMethodsPageVerifier();
		assertTrue(
				paymentMethodsPageVerifier
						.verifyValidationForInvalidExpirationDate(),
				"Incorrect/missing error message for invalid ExpirationDate");
		paymentMethodsPage.enterExpiryDateWithSpecialChar();
		paymentMethodsPage.clickOnApplyCard();
		assertTrue(
				paymentMethodsPageVerifier
						.verifyValidationForInvalidExpirationDate(),
				"Incorrect/missing error message for invalid ExpirationDate");
		// paymentMethodsPage.enterExpiryDateWithDoubleSlash();
		// paymentMethodsPage.clickOnApplyCard();
		// assertTrue(
		// paymentMethodsPageVerifier
		// .verifyValidationForInvalidExpirationDate(),
		// "Incorrect/missing error message for invalid ExpirationDate");
		paymentMethodsPage.enterExpiryDateWithPastDate();
		paymentMethodsPage.clickOnApplyCard();
		assertTrue(
				paymentMethodsPageVerifier
						.verifyValidationForInvalidExpirationDate(),
				"Incorrect/missing error message for invalid ExpirationDate");
		paymentMethodsPage.clearExpiryDate();
		paymentMethodsPage.clickOnApplyCard();
		assertTrue(
				paymentMethodsPageVerifier
						.verifyValidationForBlankExpirationDate(),
				"Incorrect/missing error message for blank ExpirationDate");
		healPatientLoginPage = new HealPatientLoginPage();
		healPatientLoginPage.logout();
		Utilities.executionEndedMethod();
	}

	@Test(priority =8)
	public void testInvalidSecurityCode() throws Exception {
		Utilities.executionStartedMethod();
		registerPage = new RegisterPage("Browser");
		registerPage.newRegistration();
		paymentMethodsPage = new PaymentMethodsPage();
		paymentMethodsPage.clickOnPaymentMethods();
		paymentMethodsPage.enterPayamentDetails();
		paymentMethodsPage.enterSecurityCodeMoreThan4Digits();
		paymentMethodsPage.clickOnApplyCard();
		paymentMethodsPageVerifier = new PaymentMethodsPageVerifier();
		assertTrue(
				paymentMethodsPageVerifier
						.verifyValidationForInvalidSecurityCode(),
				"Incorrect/missing error message for invalid security code");
		// paymentMethodsPage.clearSecurityCode();
		paymentMethodsPage.enterSecurityCodeWithSplChr();
		paymentMethodsPage.clickOnApplyCard();
		assertTrue(
				paymentMethodsPageVerifier
						.verifyValidationForInvalidSecurityCode(),
				"Incorrect/missing error message for invalid security code");
		paymentMethodsPage.clearSecurityCode();
		paymentMethodsPage.clickOnApplyCard();
		assertTrue(
				paymentMethodsPageVerifier
						.verifyValidationForBlankSecurityCode(),
				"Incorrect/missing error message for blank security code");
		healPatientLoginPage = new HealPatientLoginPage();
		healPatientLoginPage.logout();
		Utilities.executionEndedMethod();
	}

	@Test(priority =10)
	public void testExpiryDateWithMMYYFormat() throws Exception {
		Utilities.executionStartedMethod();
		registerPage = new RegisterPage("Browser");
		registerPage.newRegistration();
		paymentMethodsPage = new PaymentMethodsPage();
		paymentMethodsPage.clickOnPaymentMethods();
		paymentMethodsPage.enterPayamentDetails();
		paymentMethodsPage.enterExpiryDateInFormatMMYY();
		paymentMethodsPage.clickOnApplyCard();
		paymentMethodsPageVerifier = new PaymentMethodsPageVerifier();
		assertTrue(paymentMethodsPageVerifier.verifyHeader(),
				"Incorrect/Missing Header");
		assertTrue(paymentMethodsPageVerifier.verifySubPaymentsDetails(),
				"Incorrect/Missing subheader payment details");
		assertTrue(
				paymentMethodsPageVerifier.verifyExpirationDateInDiffFormat(),
				"Incorrect/missing expiration date");
		healPatientLoginPage = new HealPatientLoginPage();
		healPatientLoginPage.logout();
		Utilities.executionEndedMethod();
	}
	
	@Test(priority =11)
	public void testSecurityCodeWith4Digits() throws Exception{
		Utilities.executionStartedMethod();
		registerPage = new RegisterPage("Browser");
		registerPage.newRegistration();
		paymentMethodsPage = new PaymentMethodsPage();
		paymentMethodsPage.clickOnPaymentMethods();
		paymentMethodsPage.enterPayamentDetails();
		paymentMethodsPage.enterSecurityCodeWith4Digits();
		paymentMethodsPage.clickOnApplyCard();
		paymentMethodsPageVerifier = new PaymentMethodsPageVerifier();
		assertTrue(paymentMethodsPageVerifier.verifyHeader(),
				"Incorrect/Missing Header");
		assertTrue(paymentMethodsPageVerifier.verifySubPaymentsDetails(),
				"Incorrect/Missing subheader payment details");
		Utilities.executionEndedMethod();
	}

	@AfterTest
	public void tearDown() {
		closeBrowser();
	}
}
