package lib.common;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import jxl.read.biff.BiffException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestDriver {

	private WebDriver webDriver;
	private static TestDriver testDriver = null;
	private static final int TIMEOUT_SECONDS = 60;
	private final static Logger logger = LogManager.getLogger(TestDriver.class
			.getName());
	private ConfigurationProperty config;

	public TestDriver(String browserName) {
		config = ConfigurationProperty.getInstance();
		initialize(browserName);
	}

	public static TestDriver getInstance() {
		// return testDriver==null?new TestDriver("Browser"):testDriver;
		if (testDriver == null)
			testDriver = new TestDriver("Browser");
		return testDriver;
	}

	private void initialize(String browserName) {
		String browser = config.getDataValue("Properties", browserName);
		logger.info("Browser=" + browser);
		switch (browser) {
		case "Firefox":
			// System.setProperty("webdriver.gecko.driver",
			// config.getPath("FirefoxExecutable"));
			webDriver = new FirefoxDriver();
			break;
		case "InternetExplorer":
			// System.setProperty("webdriver.ie.driver",
			// config.getPath("IEExecutable"));
			System.setProperty("webdriver.ie.driver",
					config.getDriver("IEDriverServer.exe"));
			DesiredCapabilities iEDesiredCapabilities = DesiredCapabilities
					.internetExplorer();
			iEDesiredCapabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			iEDesiredCapabilities.setCapability("ignoreZoomSetting", true);
			webDriver = new InternetExplorerDriver(iEDesiredCapabilities);
			break;
		case "Chrome":
			// logger.info("Path to Driver: "+config.getPath("ChromeDriver"));
			System.setProperty("webdriver.chrome.driver",
					config.getDriver("chromedriver.exe"));
			DesiredCapabilities chromeDesiredCapabilities = DesiredCapabilities
					.chrome();
			webDriver = new ChromeDriver(chromeDesiredCapabilities);
			break;
		case "FirefoxHeadless":
			DesiredCapabilities phantomjsDesiredCapabilities = new DesiredCapabilities();
			phantomjsDesiredCapabilities.setJavascriptEnabled(true);
			phantomjsDesiredCapabilities.setCapability(
					PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
					config.getPath("PhantomJS"));
			phantomjsDesiredCapabilities.setCapability(
					PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {
							"-web-security=no", "-ignore-ssl-errors=yes" });
			phantomjsDesiredCapabilities.setCapability(
					PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
					"--webdriver-loglevel=NONE");
			phantomjsDesiredCapabilities.setCapability("takeScreenshot", true);
			webDriver = new PhantomJSDriver(phantomjsDesiredCapabilities);
			break;
		case "ChromeHeadless":
			System.setProperty("webdriver.chrome.driver",
					config.getPath("ChromeDriver"));
			DesiredCapabilities htmUnitDesiredCapabilities = DesiredCapabilities
					.chrome();
			webDriver = new HtmlUnitDriver();
			htmUnitDesiredCapabilities.setJavascriptEnabled(true);
			java.util.logging.Logger.getLogger("com.gargoylesoftware")
					.setLevel(Level.OFF);
			break;
		case "Electron":
			System.setProperty("webdriver.chrome.driver",
					config.getPath("ChromeElectron"));
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setBinary(config.getPath("ElectronBinary"));
			DesiredCapabilities caDesiredCapabilities = DesiredCapabilities
					.chrome();
			caDesiredCapabilities.setCapability(ChromeOptions.CAPABILITY,
					chromeOptions);
			webDriver = new ChromeDriver(caDesiredCapabilities);
			break;
		}
		webDriver.manage().window().maximize();
		webDriver
				.manage()
				.timeouts()
				.implicitlyWait(
						Long.parseLong(config.getDataValue("Properties",
								"timeout")), TimeUnit.SECONDS);
	}

	public void closeBrowser() {
		/*
		 * if (webDriver.toString().contains("null")) {
		 * logger.info("Trying to close already closed browser.."); }
		 *//*
			 * else { logger.info("Browser: QUIT"); webDriver.quit(); }
			 */
		webDriver.quit();
	}

	public void refreshPage() {
		webDriver.navigate().refresh();
	}

	void doubleClickOn(WebElement element) {
		Actions action = new Actions(webDriver).doubleClick(element);
		action.build().perform();
	}

	public void mouse_Hover(int times) {
		try {
			for (int i = 0; i < times; i++) {
				Robot robot = new Robot();
				robot.mouseMove((int) (Math.random() * 1024),
						(int) (Math.random() * 786));
				robot.delay(1000);
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	public Dimension getOriginalSize() {
		return webDriver.manage().window().getSize();
	}

	public void resizeWindow(int dimensionX, int dimensionY) {
		Dimension dimension = new Dimension(dimensionX, dimensionY);
		webDriver.manage().window().setSize(dimension);
	}

	public Point getLocation(WebElement webElement) {
		return webElement.getLocation();
	}

	/*
	 * public void mouse_Hover(String sheetName, String menuElement, String
	 * elementName) { Actions actions = new Actions(webDriver);
	 * actions.moveToElement
	 * (webDriver.findElement(By.xpath(config.getLocationValue(sheetName,
	 * menuElement)))).build().perform();
	 * actions.moveToElement(webDriver.findElement
	 * (By.xpath(config.getLocationValue(sheetName, elementName)))).click(); }
	 */

	public void mouseHoverAndGetTooltip(WebElement webElement) {
		Actions actions = new Actions(webDriver);
		actions.moveToElement(webElement).click().build().perform();
		WebElement tooltip = webDriver.findElement(By
				.className("highcharts-tooltip"));
		if (tooltip.isDisplayed())
			logger.info(tooltip.getText() + " displayed");

	}

	public void dragAndDrop(String sheetName, String source, String target) {
		Actions actions = new Actions(webDriver);
		WebElement targetWebElement = webDriver.findElement(By.xpath(config
				.getLocationValue(sheetName, target)));
		WebElement sourceWebElement = webDriver.findElement(By.xpath(config
				.getLocationValue(sheetName, source)));
		actions.dragAndDrop(sourceWebElement, targetWebElement).perform();
	}

	public void drag_Drop(String pageName, String source, String target) {
		Utilities.sleep(2000);
		Actions builder = new Actions(webDriver);
		WebElement sourceWebElement = webDriver.findElement(By.xpath(source));
		WebElement targetWebElement = webDriver.findElement(By.xpath(config
				.getLocationValue(pageName, target)));
		builder.dragAndDrop(sourceWebElement, targetWebElement).perform();
	}

	public void selectFrame(String sheetName, String elementName) {
		webDriver.switchTo().frame(
				webDriver.findElement(By.xpath((config.getLocationValue(
						sheetName, elementName)))));
	}

	public String getWindowHandle() {
		return webDriver.getWindowHandle();
	}

	public Set<String> getWindowHandles() {
		return webDriver.getWindowHandles();
	}

	public void switchWindow(String windowID) {
		webDriver.switchTo().window(windowID);
		logger.info("Switched To=" + webDriver.getTitle());
	}

	public void switchTabLtoR() {
		Actions actions = new Actions(webDriver);
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();
	}

	public void switchToTab(int i) {
		Actions actions = new Actions(webDriver);
		actions.keyDown(Keys.CONTROL).sendKeys(Integer.toString(i)).build()
				.perform();
	}

	public void focusOnNewTab(){
		ArrayList<String> tabs2 = new ArrayList<String> (webDriver.getWindowHandles());
		webDriver.switchTo().window(tabs2.get(1));
//		webDriver.close();
//		webDriver.switchTo().window(tabs2.get(0));
	}
	
	public void focusOnMainWindow(){
		ArrayList<String> tabs2 = new ArrayList<String> (webDriver.getWindowHandles());
		webDriver.switchTo().window(tabs2.get(1)).close();
		webDriver.switchTo().window(tabs2.get(0));
	}

	public void verifyShortcut(String menu) {
		Actions actions = new Actions(webDriver);
		Utilities.sleep(1000);
		switch (menu) {
		case "Show / hide this help menu":
			actions.keyDown(Keys.SHIFT).sendKeys("?").build().perform();
			break;
		case "play/pause":
			actions.sendKeys(Keys.SPACE).build().perform();
			break;
		case "normal playback speed":
			actions.sendKeys("1").build().perform();
			break;
		case "2X playback speed":
			actions.sendKeys("2").build().perform();
			break;
		case "0.5X playback speed":
			actions.sendKeys("5").build().perform();
			break;
		case "Show/Hide Heat Map":
			break;
		case "next keyframe":
			actions.sendKeys(Keys.ARROW_RIGHT).build().perform();
			break;
		case "previous keyframe":
			actions.sendKeys(Keys.ARROW_LEFT).build().perform();
			break;
		case "zoom in":
			actions.sendKeys(Keys.ADD).build().perform();
			break;
		case "zoom out":
			actions.sendKeys(Keys.SUBTRACT).build().perform();
			break;
		case "Enable/Disable verify frame scaling":
			actions.sendKeys("s").build().perform();
			break;
		case "Enable/Disable auto tab-switching":
			break;
		case "Show/Hide HTML Source":
			actions.sendKeys("c").build().perform();
			break;
		case "Update HTML Source Viewer":
			break;
		}
		Utilities.sleep(1000);
	}

	public String getPageSource() {
		return webDriver.getPageSource();
	}

	public void clickOn(String elementName) {
		webDriver.findElement(By.xpath(elementName)).click();
	}

	public WebElement getElement(String sheetName, String elementName) {
		return webDriver.findElement(By.xpath(config.getLocationValue(
				sheetName, elementName)));
	}

	public String replacePath(String sheetName, String elementName, int i) {
		String path = config.getLocationValue(sheetName, elementName);
		String pathChange = path + "[" + i + "]";
		return path.replace(path, pathChange);
	}

	public WebElement getElementByClassname(String className) {
		return webDriver.findElement(By.className(className));
	}

	public WebElement getElement(String selector) {
		return webDriver.findElement(By.cssSelector(selector));
	}

	public void scrollToElement(WebElement webElement) {
		JavascriptExecutor jse = (JavascriptExecutor) webDriver;
		jse.executeScript("arguments[0].scrollIntoView();", webElement);
	}

	public void scrollUpDown() {
		JavascriptExecutor jse = (JavascriptExecutor) webDriver;
		jse.executeScript("window.scrollTo(0, 250)", "");
		Utilities.sleep(1000);
		jse.executeScript("window.scrollBy(0,-250)", "");

	}

	public double getSize(String sheetName, String elementName) {
		return webDriver
				.findElement(
						By.xpath(config
								.getLocationValue(sheetName, elementName)))
				.getSize().getWidth();
	}

	public double getWidthOfElement(String sheetName, String elementName) {
		return webDriver
				.findElement(
						By.cssSelector(config.getLocationValue(sheetName,
								elementName))).getSize().getWidth();
	}

	public List<WebElement> getElements(String sheetName, String elementName) {
		return webDriver.findElements(By.xpath(config.getLocationValue(
				sheetName, elementName)));
	}

	public String getFieldValue(String pageName, String elementName) {
		WebElement element = webDriver.findElement(By.xpath(config
				.getLocationValue(pageName, elementName)));
		return element.getText();
	}

	public WebElement getElementDirectly(String xPathLocation) {
		return webDriver.findElement(By.xpath(xPathLocation));
	}

	public List<WebElement> getElementsDirectly(String xPathLocation) {
		return webDriver.findElements(By.xpath(xPathLocation));
	}

	public void loadURL(String url) {
		webDriver.get(url);
	}

	public void navigateBack() {
		webDriver.navigate().back();
	}

	public String getCurrentURL() {
		return webDriver.getCurrentUrl();
	}

	public String getTitle() {
		return webDriver.getTitle();
	}

	public boolean isElementPresent(String sheetName, String elementName) {
		try {
			return webDriver.findElement(
					By.xpath(config.getLocationValue(sheetName, elementName)))
					.isDisplayed();
		} catch (NoSuchElementException exception) {
			return false;
		}
	}

	public boolean isElementEnabled(String sheetName, String elementName) {
		try {
			return webDriver.findElement(
					By.xpath(config.getLocationValue(sheetName, elementName)))
					.isEnabled();
		} catch (NoSuchElementException exception) {
			return false;
		}
	}

	public void openInNewTab(String sheetName, String linkText) {
		logger.info("Open New Tab : " + linkText);
		WebElement webElement = webDriver.findElement(By.xpath(config
				.getLocationValue(sheetName, linkText)));
		Actions actions = new Actions(webDriver);
		actions.keyDown(Keys.CONTROL).click(webElement).build().perform();
	}

	public void switchToNewTab() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.delay(1000);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void openInNewTab(String sheetName, String linkText,
			String subElement) {
		logger.info("Open New Tab : on " + getCurrentURL() + " : " + linkText);
		Actions actions = new Actions(webDriver);
		WebElement webElement = webDriver.findElement(By.xpath(config
				.getLocationValue(sheetName, linkText)));
		actions.moveToElement(webElement).build().perform();
		WebElement subWebElement = webDriver.findElement(By.xpath((config
				.getLocationValue(sheetName, subElement))));
		actions.keyDown(Keys.CONTROL).click(subWebElement).build().perform();
	}

	public void closeTab() {
		logger.info("Closed Tab=" + webDriver.getTitle());
		System.out.println("Closed Tab=" + webDriver.getTitle());
		webDriver.close();
	}

	public void open_closedTab() {
		webDriver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL,
				Keys.SHIFT + "t");
		List<String> tabs = new ArrayList<>(getWindowHandles());
		webDriver.switchTo().window(tabs.get(1));
		logger.info("Reopened Tab=" + webDriver.getTitle());
	}

	public List<WebElement> selectFromDropDown(String pageName,
			String elementName) {
		WebElement select = webDriver.findElement(By.xpath(config
				.getLocationValue(pageName, elementName)));
		Select dropDown = new Select(select);
		return dropDown.getOptions();
	}

	public void clickLink(String link) {
		webDriver.findElement(By.linkText(link)).click();
	}

	public int getCount(String sheetName, String elementName) {
		/*
		 * WebElement address =
		 * webDriver.findElement(By.xpath(config.getLocationValue(sheetName,
		 * elementName))); Select select = new Select(address); List<WebElement>
		 * elem = select.getOptions(); return elem.size();
		 */

		List<WebElement> services = webDriver.findElements(By.xpath(elementName));
		System.out.println(services);
		return services.size();
	}

	public void clickOnActions(String sheetName, String elementName) {
		Actions act = new Actions(webDriver);
		act.moveToElement(
				webDriver.findElement(By.xpath(config.getLocationValue(
						sheetName, elementName)))).click().build().perform();
	}
	public void waitForElementPresent(String sheetName, String elementName) throws IOException, BiffException, NoSuchElementException {
		WebDriverWait wait = new WebDriverWait(webDriver, TIMEOUT_SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath((ConfigurationProperty.xlLocation.getValueFor(sheetName, elementName)))));
	}

}
