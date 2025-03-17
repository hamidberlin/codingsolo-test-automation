package de.codingsolo.selenium.configuration;

import java.io.InputStream;
import java.util.Properties;

//public class Config {
//
//	private static String configfile = "myconfig.properties";
//	private static Properties properties = loadProperties();
//	
//	public static String getBaseURL() {
//		String baseURL = (String) properties.get("baseurl");
//		throwExceptionWhenNull("baserurl", baseURL);
//		return baseURL;
//	}
//	
//	public static String getBrowserName(String browsername) {
//		String browser = (String) properties.get(browsername);
//		throwExceptionWhenNull(browsername, browser);
//		return browser;
//	}
//	
//	public static String getBrowserDriver(String browserdriver) {
//		String browserDriver = (String) properties.get(browserdriver);
//		throwExceptionWhenNull(browserdriver, browserDriver);
//		return browserDriver;
//	}
//	
//	private static void throwExceptionWhenNull(String property, String Parameter) {
//		if (Parameter == null) {
//			throw new RuntimeException("Parameter: "+property+" nicht in der Konfigurationsdatei gefunden.");
//		}
//	}
//
//	private static Properties loadProperties() {
//
//		try {
//
//			ClassLoader loader = Thread.currentThread().getContextClassLoader();
//			Properties props = new Properties();
//
//			InputStream is = loader.getSystemResourceAsStream(configfile);
//			props.load(is);
//
//			return props;
//
//		} catch (Exception e) {
//			throw new RuntimeException("Keine Konfigurationsdatei gefunden", e);
//		}
//	}
//
//}


public class Config {

    private static final String CONFIG_FILE = "myconfig.properties";
    private static final Properties properties = loadProperties();

    public static String getBaseURL() {
        String baseURL = properties.getProperty("baseurl");
        throwExceptionWhenNull("baseurl", baseURL);
        return baseURL;
    }

    public static String getBrowserName(String browserName) {
        String browser = properties.getProperty(browserName);
        throwExceptionWhenNull(browserName, browser);
        return browser;
    }

    public static String getBrowserDriver(String browserDriver) {
        String driver = properties.getProperty(browserDriver);
        throwExceptionWhenNull(browserDriver, driver);
        return driver;
    }

    private static void throwExceptionWhenNull(String property, String value) {
        if (value == null) {
            throw new RuntimeException("Parameter: " + property + " nicht in der Konfigurationsdatei gefunden.");
        }
    }

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

