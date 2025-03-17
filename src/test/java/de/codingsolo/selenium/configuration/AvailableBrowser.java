package de.codingsolo.selenium.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Enum zur Verwaltung der verfügbaren Browser für Selenium WebDriver.
 * Dieses Enum ermöglicht die einfache Instanziierung von WebDriver-Objekten für verschiedene Browser.
 */
public enum AvailableBrowser {

    FIREFOX {
        /**
         * Erstellt eine neue Instanz des Firefox WebDrivers.
         * Setzt den Systempfad für den GeckoDriver und gibt eine FirefoxDriver-Instanz zurück.
         *
         * @return WebDriver für Firefox
         */
        @Override
        public WebDriver createDriver() {
            System.setProperty("webdriver.gecko.driver", "/opt/homebrew/bin/geckodriver"); // Pfad zum GeckoDriver setzen
            WebDriver driver = new FirefoxDriver(); // FirefoxDriver-Instanz erstellen

            return driver;
        }
    },
    
    CHROME {
        /**
         * Erstellt eine neue Instanz des Chrome WebDrivers.
         * Setzt den Systempfad für den ChromeDriver und gibt eine ChromeDriver-Instanz zurück.
         *
         * @return WebDriver für Chrome
         */
        @Override
        public WebDriver createDriver() {
            System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver"); // Pfad zum ChromeDriver setzen
            WebDriver driver = new ChromeDriver(); // ChromeDriver-Instanz erstellen

            return driver;
        }
    };

    /**
     * Abstrakte Methode zur Erstellung eines WebDriver-Objekts.
     * Diese Methode wird von jeder Enum-Konstante (FIREFOX, CHROME) implementiert.
     *
     * @return WebDriver-Instanz für den jeweiligen Browser
     */
    public abstract WebDriver createDriver();
}
