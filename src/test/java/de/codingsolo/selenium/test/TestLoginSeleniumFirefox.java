package de.codingsolo.selenium.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import de.codingsolo.selenium.configuration.Config;
import de.codingsolo.selenium.configuration.DriverHelper;
import de.codingsolo.selenium.pages.SeleniumHomePage;
import de.codingsolo.selenium.pages.SeleniumLoginPage;

@RunWith(Parameterized.class)
public class TestLoginSeleniumFirefox {
    private WebDriver driver;
    private final String browserName;
    private final String username;
    private final String password;

    public TestLoginSeleniumFirefox(String browserName, String username, String password) {
        this.browserName = browserName;
        this.username = username;
        this.password = password;
    }

    @Before
    public void setUp() {
        System.out.println("Initialisiere WebDriver für: " + browserName);
        driver = DriverHelper.getDriver(browserName);
        driver.manage().window().maximize();
        
        try {
            String baseUrl = Config.getBaseURL();
            if (baseUrl == null || baseUrl.isEmpty()) {
                throw new RuntimeException("Konfigurationsdatei ist leer oder ungültig");
            }
            driver.get(baseUrl);
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Laden der Konfiguration: " + e.getMessage(), e);
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            System.out.println("Test abgeschlossen - Browser schließen");
            driver.quit();
        }
    }

    @Test
    public void testLogin() {
        System.out.println("Starte Test: Login erfolgreich");

        SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
        loginPage.zugangsdatenEingeben(username, password);
        loginPage.loginButtonAnklicken();

        SeleniumHomePage homepage = new SeleniumHomePage(driver);
        String erfolgsMeldung = homepage.statusMeldungAuslesen();
        assertTrue("Login Erfolgreich!", erfolgsMeldung.contains("Willkommen!"));
    }

    @Parameterized.Parameters(name = "0")
    public static Collection<Object[]> provideTestData() {
        return Arrays.asList(new Object[][]{
            {"firefox", "selenium42", "R5vxI0j60"},
            {"chrome", "selenium42", "R5vxI0j60"}
        });
    }
}

