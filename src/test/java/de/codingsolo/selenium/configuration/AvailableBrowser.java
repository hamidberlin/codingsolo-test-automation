package de.codingsolo.selenium.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum AvailableBrowser {

	FIREFOX {

		@Override
		public WebDriver createDriver() {
			System.setProperty("webdriver.gecko.driver", "/opt/homebrew/bin/geckodriver");
			WebDriver driver = new FirefoxDriver();

			return driver;
		}
	},
	CHROME {

		@Override
		public WebDriver createDriver() {
			System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
			WebDriver driver = new ChromeDriver();

			return driver;
		}
	};

	public abstract WebDriver createDriver();
}
