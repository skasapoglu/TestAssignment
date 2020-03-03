package org.assignment.task.pages;

import java.util.concurrent.TimeUnit;

import org.assignment.task.common.Config;
import org.assignment.task.common.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageBase {

	protected final String loginUrl;
	protected final WebDriver driver;

	PageBase() {

		Config.initilize();
		loginUrl = Config.getHomePageUrl();
		driver = Driver.getInstance();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void waitUntilElementExists(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitUntilElementClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public boolean isElementEnabled(By locator) {

		WebElement element = driver.findElement(locator);
		JavascriptExecutor ex = (JavascriptExecutor) driver;

		return ex.executeScript("return arguments[0].click()", element) != null;

	}

	public boolean retryingFindClick(By locator) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 3) {
			try {
				WebElement element = driver.findElement(locator);
				Actions actions = new Actions(driver);
				actions.moveToElement(element).click().build().perform();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}

	public abstract boolean isAt();
}