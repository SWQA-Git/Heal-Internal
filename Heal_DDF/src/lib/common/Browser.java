package lib.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.read.biff.BiffException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.ClickElement;

public class Browser {
	protected static TestDriver driver;

	public Browser(String browserName) {
		driver = TestDriver.getInstance();
	}

	public Browser() {
		this("Browser");
	}
	
	

	public void clickOn(String pageName, String buttonName) {
		driver.getElement(pageName, buttonName).click();
	}

	public String getText(String pageName, String elementName) {
		return driver.getElement(pageName, elementName).getText();
	}

	public String replacePath(String pageName, String elementName, int i) {
		return driver.replacePath(pageName, elementName, i);
	}

	public void loadURL(String sheetName, String elementName) {
		String url = ConfigurationProperty.getInstance().getDataValue(
				sheetName, elementName);
		driver.loadURL(url);

	}

	public void navigateBack() {
		driver.navigateBack();
	}

	public String getCurrentURL() {
		return driver.getCurrentURL();
	}

	public int getGridRowCount(String pageName, String tableName) {
		String actualTableName = ConfigurationProperty.getInstance()
				.getLocationValue(pageName, tableName).concat("/tbody/tr");
		return driver.getElementsDirectly(actualTableName).size();
	}

	public int getCount(String pageName, String elementName) {
		return driver.getCount(pageName, elementName);
	}

	public void enterValue(String pageName, String fieldName, String fieldValue) {
		driver.getElement(pageName, fieldName).sendKeys(fieldValue);
		driver.getElement(pageName, fieldName).getAttribute("tagname");

	}

	public void enterValueWithAttribute(String pageName, String fieldName,
			String fieldValue) {
		driver.getElement(pageName, fieldName);
	}

	public void clearField(String pageName, String fieldName) {
		driver.getElement(pageName, fieldName).clear();
	}

	public WebElement getElementsDirectly(String xPathLocation) {
		return driver.getElementDirectly(xPathLocation);
	}

	public boolean isElementVisible(String pageName, String elementName) {
		return driver.getElement(pageName, elementName).isDisplayed();
	}

	public boolean isElementEnable(String pageName, String elementName) {
		return driver.getElement(pageName, elementName).isEnabled();
	}

	public void sendDataForceFully(String fieldValue) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("document.getElementById('pass').value = fieldValue;");
	}

	public void clickByActions(String pageName, String elementName) {
		driver.clickOnActions(pageName, elementName);
	}

	public void focusOnNewTab() {
		driver.focusOnNewTab();
		 
	}
	
	public void focusOnMainWindow()
	{
		driver.focusOnMainWindow();
	}

	public void closeTab() {
		driver.closeTab();
	}
    public void waitForElementPresent(String sheetName, String elementName) throws NoSuchElementException, BiffException, IOException{
    	driver.waitForElementPresent( sheetName, elementName);
    }
}
