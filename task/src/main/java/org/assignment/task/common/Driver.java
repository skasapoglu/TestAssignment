package org.assignment.task.common;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {

	private static WebDriver instance = null;

	public synchronized static WebDriver getInstance() {
		if (instance == null) {
			Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
			System.setProperty("webdriver.chrome.silentOutput", "true");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox");
			options.addArguments("enable-automation");
			options.addArguments("--disable-extensions");
			options.addArguments("--dns-prefetch-disable");
			options.addArguments("--disable-gpu");
			instance = new ChromeDriver(options);
			instance.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}
		return instance;
	}

	public static void quit() {
		instance.quit();
		instance = null;
	}

}