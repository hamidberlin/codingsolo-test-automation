package de.codingsolo.selenium.configuration;

import java.io.InputStream;
import java.util.Properties;

/**
 * Diese Klasse lädt und verwaltet Konfigurationswerte aus einer properties-Datei.
 * Sie stellt Methoden bereit, um die Basis-URL, den Browsernamen und den WebDriver-Pfad zu erhalten.
 */
public class Config {

    // Name der Konfigurationsdatei
    private static final String CONFIG_FILE = "myconfig.properties";

    // Statische Initialisierung der Properties, damit sie einmalig geladen werden
    private static final Properties properties = loadProperties();

    /**
     * Gibt die Basis-URL aus der Konfigurationsdatei zurück.
     * 
     * @return Die Basis-URL als String
     * @throws RuntimeException wenn die Eigenschaft nicht gefunden wird
     */
    public static String getBaseURL() {
        String baseURL = properties.getProperty("baseurl");
        throwExceptionWhenNull("baseurl", baseURL);
        return baseURL;
    }

    /**
     * Gibt den Namen des Browsers zurück, der in der Konfigurationsdatei definiert ist.
     * 
     * @param browserName Der Schlüssel für den Browsernamen in der Properties-Datei
     * @return Der Browsername als String
     * @throws RuntimeException wenn der Wert nicht gefunden wird
     */
    public static String getBrowserName(String browserName) {
        String browser = properties.getProperty(browserName);
        throwExceptionWhenNull(browserName, browser);
        return browser;
    }

    /**
     * Gibt den Pfad zum WebDriver zurück, der in der Konfigurationsdatei definiert ist.
     * 
     * @param browserDriver Der Schlüssel für den WebDriver-Pfad in der Properties-Datei
     * @return Der WebDriver-Pfad als String
     * @throws RuntimeException wenn der Wert nicht gefunden wird
     */
    public static String getBrowserDriver(String browserDriver) {
        String driver = properties.getProperty(browserDriver);
        throwExceptionWhenNull(browserDriver, driver);
        return driver;
    }

    /**
     * Prüft, ob ein Konfigurationswert null ist, und wirft in diesem Fall eine Exception.
     * 
     * @param property Der Name der Konfigurationseigenschaft
     * @param value Der gelesene Wert aus der Konfigurationsdatei
     * @throws RuntimeException wenn der Wert null ist
     */
    private static void throwExceptionWhenNull(String property, String value) {
        if (value == null) {
            throw new RuntimeException("Parameter: " + property + " nicht in der Konfigurationsdatei gefunden.");
        }
    }

    /**
     * Lädt die Konfigurationswerte aus der Properties-Datei.
     * 
     * @return Ein Properties-Objekt mit den geladenen Werten
     * @throws RuntimeException wenn die Datei nicht gefunden oder nicht geladen werden kann
     */
    private static Properties loadProperties() {
        Properties props = new Properties();

        try (InputStream is = Config.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (is == null) {
                throw new RuntimeException("Konfigurationsdatei '" + CONFIG_FILE + "' nicht gefunden.");
            }
            props.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Laden der Konfigurationsdatei: " + CONFIG_FILE, e);
        }

        return props;
    }
}
