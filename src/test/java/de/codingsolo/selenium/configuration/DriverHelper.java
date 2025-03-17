package de.codingsolo.selenium.configuration;

import org.openqa.selenium.WebDriver;

/**
 * Die Klasse DriverHelper dient als Hilfsklasse zur Erstellung einer WebDriver-Instanz 
 * basierend auf dem angegebenen Browsernamen.
 */
public class DriverHelper {

    /**
     * Überprüft, ob der übergebene Browser-String null oder leer ist, 
     * und wirft eine RuntimeException, falls dies der Fall ist.
     *
     * @param browser Der Name des Browsers als String.
     * @throws RuntimeException falls der Browser-Name null oder leer ist.
     */
    private static void throwExceptionWhenNull(String browser) {
        if (browser == null || browser.trim().isEmpty()) {
            throw new RuntimeException("Es wurde kein gültiger WebDriver angegeben.");
        }
    }

    /**
     * Erstellt und gibt eine WebDriver-Instanz für den angegebenen Browser zurück.
     *
     * @param browser Der Name des Browsers als String (z. B. "chrome" oder "firefox").
     * @return Eine WebDriver-Instanz für den angegebenen Browser.
     * @throws RuntimeException falls der übergebene Browser-Name ungültig ist.
     */
    public static WebDriver getDriver(String browser) {
        throwExceptionWhenNull(browser); // Überprüfung, ob der Browsername gültig ist

        // Konvertiert den übergebenen Browsernamen in Großbuchstaben und sucht den passenden Eintrag im Enum
        AvailableBrowser currentBrowser;
        try {
            currentBrowser = AvailableBrowser.valueOf(browser.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Ungültiger Browsername: " + browser + 
                ". Verfügbare Optionen: CHROME, FIREFOX.");
        }

        // Erstellt die WebDriver-Instanz für den angegebenen Browser
        return currentBrowser.createDriver();
    }
}
