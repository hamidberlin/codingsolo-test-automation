package de.codingsolo.selenium.test;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import de.codingsolo.selenium.configuration.Config;
import de.codingsolo.selenium.configuration.DriverHelper;
import de.codingsolo.selenium.pages.SeleniumHomePage;
import de.codingsolo.selenium.pages.SeleniumIframePage;
import de.codingsolo.selenium.pages.SeleniumLoginPage;
import de.codingsolo.selenium.pages.SeleniumTestApplikationenPage;

/**
 * Testklasse zur Interaktion mit einem iFrame in Selenium mit JUnit und Parameterisierung.
 */
@RunWith(Parameterized.class)
public class TestIFrameSeleniumFirefox {
    
    private WebDriver driver;
    private final String browsername;
    private String username;
    private String userpassword;
    
    /**
     * Konstruktor zur Initialisierung der Testparameter.
     * 
     * @param testName Name des Testfalls
     * @param browsername Name des Browsers
     * @param username Benutzername für den Login
     * @param userpassword Passwort für den Login
     */
    public TestIFrameSeleniumFirefox(String testName, String browsername, String username, String userpassword) {
        this.browsername = browsername;
        this.username = username;
        this.userpassword = userpassword;
    }

    /**
     * Setzt die Testumgebung auf, indem der WebDriver initialisiert und die Testseite geladen wird.
     */
    @Before
    public void setUp() {
        System.out.println("Initialisiere WebDriver");
        driver = DriverHelper.getDriver(browsername);
        driver.manage().window().maximize();
        driver.get(Config.getBaseURL()); 
    }

    /**
     * Beendet den Test und schließt den WebDriver, um Ressourcen freizugeben.
     */
    @After
    public void tearDown() {
        System.out.println("Test abgeschlossen. - Aufräumen");
        if (driver != null) {
            driver.quit();
        }
    }
    
    /**
     * Testfall: Interaktion mit einem iFrame.
     * 
     * Schritte:
     * 1. Login mit gültigen Zugangsdaten.
     * 2. Navigation zur iFrame-Testseite.
     * 3. Wechsel in den iFrame.
     * 4. Eingabe eines Namens und Auslösen eines Alerts.
     * 5. Überprüfung der Alarmnachricht.
     * 6. Schließen des Alerts.
     * 
     * Erwartetes Ergebnis:
     * - Die Alarmnachricht enthält den eingegebenen Namen.
     */
    @Test
    public void testIFrame() {
        System.out.println("Starte Test iFrame");
        
        // Arrange: Login auf der Seite
        SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
        loginPage.zugangsdatenEingeben(username, userpassword);
        loginPage.loginButtonAnklicken();
        
        // Navigation zur iFrame-Testseite
        SeleniumHomePage homePage = new SeleniumHomePage(driver);
        homePage.btnMenuAusklappen();
        homePage.seleniumTestLinkAnklicken();
        
        SeleniumTestApplikationenPage testAppPage = new SeleniumTestApplikationenPage(driver);
        testAppPage.iframeTestAnklicken();
        
        SeleniumIframePage iFramePage = new SeleniumIframePage(driver);
        iFramePage.wechselZuIframe();

        // Act: Name eingeben und Alert auslösen
        iFramePage.nameEintragen("Max");
        iFramePage.alarmBtnAnklickken();
        
        // Assert: Überprüfung der Alarmnachricht
        assertTrue(iFramePage.alarmNachrichtAuslesen().contains("Max"));
        
        // Alert schließen
        iFramePage.alarmNachrichtSchliessen();
    }
    
    /**
     * Stellt Testdaten für den parametrisierten Test bereit.
     * 
     * @return Eine Liste von Testparametern
     */
    @Parameters(name = "{0}")
    public static Collection<Object[]> provideTestData() {
        return Arrays.asList(new Object[][]{
                {"TestIFrame TestFall1 Firefox", "firefox", "selenium42", "R5vxI0j60"}
        });
    }
}