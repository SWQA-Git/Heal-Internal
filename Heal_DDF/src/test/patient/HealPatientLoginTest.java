package test.patient;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;
import jxl.read.biff.BiffException;
import lib.common.TestBase;
import lib.common.Utilities;
import lib.patient.HealPatientLoginPage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import verification.patient.HomePageVerifier;
import verification.patient.LoginPageVerifier;

public class HealPatientLoginTest extends TestBase {

	public HealPatientLoginTest() throws NullPointerException, BiffException,
			IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final Logger logger = LogManager
			.getLogger(HealPatientLoginTest.class.getName());
	HealPatientLoginPage healPatientLoginPage;
	LoginPageVerifier loginPageVerifier;
	HomePageVerifier homePageVerifier;

	@BeforeTest
	public void SetUp() throws BiffException, NullPointerException, IOException {
		initializeDriver();
	}

	@Test
	public void testSecurityCheckRedirectToLoginPage() throws Exception {
		Utilities.executionStartedMethod();
		healPatientLoginPage = new HealPatientLoginPage();
		healPatientLoginPage.validPatientLoginUrl();
		loginPageVerifier = new LoginPageVerifier();
		assertTrue(loginPageVerifier.verifyURLRedirectToLogin(),
				"Redirected to incorrect URL");
		healPatientLoginPage.invalidPatientLoginUrl();
		assertTrue(loginPageVerifier.verifyURLRedirectToLogin(),
				"Redirected to incorrect URL");
		Utilities.executionEndedMethod();
	}

	@Test
	public void testSecurityWithInvalidURL() throws Exception {
		Utilities.executionStartedMethod();
		healPatientLoginPage = new HealPatientLoginPage();
		homePageVerifier = new HomePageVerifier();
		healPatientLoginPage.validPatientLoginUrl();
		loginPageVerifier = new LoginPageVerifier();
		assertTrue(loginPageVerifier.verifyURLRedirectToLogin(),
				"Redirected to incorrect URL");
		healPatientLoginPage.invalidPatientLoginUrl01();
		assertTrue(loginPageVerifier.verifyURLRedirectToRegister(),
				"Redirected to incorrect URL");
		Utilities.executionEndedMethod();
	}

	@Test
	public void testElementsOfLoginPage() throws Exception {
		Utilities.executionStartedMethod();
		healPatientLoginPage = new HealPatientLoginPage();
		healPatientLoginPage.loadLoginURL();
		loginPageVerifier = new LoginPageVerifier();
		assertTrue(loginPageVerifier.verifyPageTitle(),
				"Incorrect title of the page");
		assertTrue(loginPageVerifier.verifyLabelEmail(),
				"Incorrect/Missing label Email");
		assertTrue(loginPageVerifier.verifyInputFieldEmail(),
				"Input field  email is missing");
		assertTrue(loginPageVerifier.verifyLabelPassword(),
				"Incorrect/Missing label Password");
		assertTrue(loginPageVerifier.verifyInputFieldPassword(),
				"Input field password is missing");
		assertTrue(loginPageVerifier.verifyLabelRememberMe(),
				"Incorrect/Missing label Remember Me");
		assertTrue(loginPageVerifier.verifyCheckboxRememberMe(),
				"Checkbox RememberMe is missing");
		assertTrue(loginPageVerifier.verifyLabelForgotPassword(),
				"Incorrect/Missing label Forgot Password");
		assertTrue(loginPageVerifier.verifyLinkForgotPassword(),
				"Link ForgotPassword is missing");
		assertTrue(loginPageVerifier.verifyLabelLogIn(),
				"Incorrect/Missing label LogIn");
		assertTrue(loginPageVerifier.verifyButtonLogIn(),
				"Button Login is missing");
		assertTrue(loginPageVerifier.verifyLabelRegister(),
				"Incorrect/Missing label Register");
		assertTrue(loginPageVerifier.verifyButtonRegister(),
				"Button Register is missing");
		assertTrue(loginPageVerifier.verifyLabelHelpLine(),
				"Incorrect/Missing label HelpLine");
		assertTrue(loginPageVerifier.verifyLabelPrivacyPolicy(),
				"Incorrect/Missing label Privacy Policy");
		assertTrue(loginPageVerifier.verifyLinkPrivacyPolicy(),
				"Link Privacy Policy is missing");
		assertTrue(loginPageVerifier.verifyLabelTermsOfService(),
				"Incorrect/Missing label TermsOfService");
		assertTrue(loginPageVerifier.verifyLinkTermsOfService(),
				"Link TermsOfService is missing");
		assertTrue(loginPageVerifier.verifyLabelNoticeOfPrivacyPractices(),
				"Incorrect/Missing label NoticeOfPrivacyPractices");
		assertTrue(loginPageVerifier.verifyLinkNoticeOfPrivacyPractices(),
				"Link NoticeOfPrivacyPractices is missing");
		assertTrue(loginPageVerifier.verifyLabelNondiscriminationNotice(),
				"Incorrect/Missing label NondiscriminationNotice");
		assertTrue(loginPageVerifier.verifyLinkNondiscriminationNotice(),
				"Link NondiscriminationNotice is missing");
		Utilities.executionEndedMethod();
	}

	@Test
	public void testBrowserbackButtonFlow() throws Exception {
		Utilities.executionStartedMethod();
		healPatientLoginPage = new HealPatientLoginPage();
		healPatientLoginPage.login();
		healPatientLoginPage.browserBackButton();
		homePageVerifier = new HomePageVerifier();
		assertTrue(homePageVerifier.verifyUsername(),
				"Clicking on back browser button once takes to another screen");
		healPatientLoginPage.logout();
		// healPatientLoginPage.browserBackButton();
		// healPatientLoginPage.browserBackButton();
		// healPatientLoginPage.browserBackButton();
		// loginPageVerifier = new LoginPageVerifier();
		// assertTrue(loginPageVerifier.verifyPageTitle());
		Utilities.executionEndedMethod();
	}

	@Test
	public void testRegisterLinkFlow() throws Exception {
		Utilities.executionStartedMethod();
		healPatientLoginPage = new HealPatientLoginPage();
		healPatientLoginPage.loadLoginURL();
		healPatientLoginPage.clickOnRegisterLink();
		homePageVerifier = new HomePageVerifier();
		assertTrue(homePageVerifier.verifyURLRedirectToRegister(),
				"Redirect to incorrect url, instead of Register page");
		Utilities.executionEndedMethod();
	}

	@Test
	public void testLogInFormWithBlankData() throws Exception {
		Utilities.executionStartedMethod();
		healPatientLoginPage = new HealPatientLoginPage();
		healPatientLoginPage.validPatientLoginUrl();
		healPatientLoginPage.allFieldBlank();
		loginPageVerifier = new LoginPageVerifier();
		assertTrue(loginPageVerifier.verifyValidationForUsername(),
				"Error message mismatch or not found");
		assertTrue(loginPageVerifier.verifyValidationForPassword(),
				"Error message mismatch or not found");
		healPatientLoginPage.enterOnlyEmail();
		assertTrue(loginPageVerifier.verifyValidationForPassword(),
				"Error message mismatch or not found");
		healPatientLoginPage.enterOnlyPassword();
		assertTrue(loginPageVerifier.verifyValidationForUsername(),
				"Error message mismatch or not found");
		Utilities.executionEndedMethod();
	}

	@Test
	public void testloginWithValidCredentails() throws Exception {
		Utilities.executionStartedMethod();
		healPatientLoginPage = new HealPatientLoginPage();
		healPatientLoginPage.login();
		homePageVerifier = new HomePageVerifier();
		assertTrue(homePageVerifier.verifyUsername(), "Username not correct");
		healPatientLoginPage.logout();
		Utilities.executionEndedMethod();
	}

	@Test
	public void testNormsAndConditionsLink() throws Exception {
		Utilities.executionStartedMethod();
		healPatientLoginPage = new HealPatientLoginPage();
		healPatientLoginPage.validPatientLoginUrl();
		healPatientLoginPage.clickOnPrivacyPolicyLink();
		healPatientLoginPage.focusOnNewTabs();
		loginPageVerifier = new LoginPageVerifier();
		assertTrue(loginPageVerifier.verifyURLRedirectToPolicyPrivate(),
				"Redirect to incorrect URL, instead of Policy Private");
		assertTrue(loginPageVerifier.verifyTitlePolicyPrivate(),
				"Incorrect title of the Policy Private");
		assertTrue(loginPageVerifier.verifyHeaderPolicyPrivate(),
				"Incorrect header of the Policy Private");
		healPatientLoginPage.focusOnMainWin();
		healPatientLoginPage.clickOnTermsOfServiceLink();
		healPatientLoginPage.focusOnNewTabs();
		assertTrue(loginPageVerifier.verifyURLRedirectToTermsOfService(),
				"Redirect to incorrect URL, instead of Terms Of Service");
		assertTrue(loginPageVerifier.verifyTitleTermsOfService(),
				"Incorrect title of the Terms Of Service");
		assertTrue(loginPageVerifier.verifyHeaderTermsOfService(),
				"Incorrect header of the Terms Of Service");
		healPatientLoginPage.focusOnMainWin();
		healPatientLoginPage.clickOnNoticeOfPrivacyPracticesLink();
		healPatientLoginPage.focusOnNewTabs();
		assertTrue(
				loginPageVerifier.verifyURLRedirectToNoticeOfPrivacyPractices(),
				"Redirect to incorrect URL, instead of Notice Of Privacy Practices");
		assertTrue(loginPageVerifier.verifyTitleNoticeOfPrivacyPractices(),
				"Incorrect title of the Notice Of Privacy Practices");
		assertTrue(loginPageVerifier.verifyHeaderNoticeOfPrivacyPractices(),
				"Incorrect header of the Notice Of Privacy Practices");
		healPatientLoginPage.focusOnMainWin();
		healPatientLoginPage.clickOnNondiscriminationNoticeLink();
		healPatientLoginPage.focusOnNewTabs();
		assertTrue(
				loginPageVerifier.verifyURLRedirectToNondiscriminationNotice(),
				"Redirect to incorrect URL, instead of Nondiscrimination Notice");
		assertTrue(loginPageVerifier.verifyTitleNondiscriminationNotice(),
				"Incorrect title of the Notice Of Nondiscrimination Notice");
		assertTrue(loginPageVerifier.verifyHeaderNondiscriminationNotice(),
				"Incorrect header of the Notice Of Nondiscrimination Notice");
		healPatientLoginPage.focusOnMainWin();
		Utilities.executionEndedMethod();
	}

	@Test
	public void testValidationOfLoginForm() throws Exception {
		Utilities.executionStartedMethod();
		healPatientLoginPage = new HealPatientLoginPage();
		healPatientLoginPage.loadLoginURL();
		healPatientLoginPage.validEmail();
		healPatientLoginPage.incorrectPassword();
		healPatientLoginPage.clickOnLoginButton();
		loginPageVerifier = new LoginPageVerifier();
		assertTrue(loginPageVerifier.verifyValidationMessage(),
				"Error message mismatch");
		healPatientLoginPage.invalidEmail();
		healPatientLoginPage.correctPassword();
		healPatientLoginPage.clickOnLoginButton();
		assertTrue(loginPageVerifier.verifyValidationMessage(),
				"Error message mismatch");
		healPatientLoginPage.invalidEmail();
		healPatientLoginPage.incorrectPassword();
		healPatientLoginPage.clickOnLoginButton();
		assertTrue(loginPageVerifier.verifyValidationMessage(),
				"Error message mismatch");
		Utilities.executionEndedMethod();
	}

	@AfterTest
	public void tearDown() {
		closeBrowser();
	}
}
