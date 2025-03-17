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
import de.codingsolo.selenium.pages.SeleniumLoginPage;
import de.codingsolo.selenium.pages.SeleniumTestApplikationenPage;
import de.codingsolo.selenium.pages.SeleniumTestForm1Page;

// JUnit-Annotation, um einen parametrisierten Test auszuführen
@RunWith(Parameterized.class)
public class TestForm1CodingSoloSeleniumFirefox {
	
	private WebDriver driver; // WebDriver-Instanz für den Browser
    private final String browserName; // Name des Browsers für den Test
    
    // Konstruktor, um den Browsernamen als Parameter zu übergeben
	public TestForm1CodingSoloSeleniumFirefox(String browserName) {
		super();
		this.browserName = browserName;
	}

    /**
     * Vor jedem Test wird der WebDriver initialisiert und die Testseite geladen.
     */
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
            driver.get(baseUrl); // Lädt die Basis-URL aus der Konfigurationsdatei
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Laden der Konfiguration: " + e.getMessage(), e);
        }
    }

	/**
     * Nach jedem Test wird der WebDriver beendet.
     */
	@After
	public void tearDown() throws Exception {
		System.out.println("Test abgeschlossen. - Aufräumen");
		driver.quit(); 
	}

	/**
     * Testfall für die Navigation und das Ausfüllen des Formulars.
     */
	@Test
	public void testForm1() {
		System.out.println("Starte Test Navigation");

		// Arrange (Vorbereitung des Tests)
		
		// Login
		SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
		loginPage.zugangsdatenEingeben("selenium42", "R5vxI0j60");
		loginPage.loginButtonAnklicken();
		
		// Navigation zum Formular
		SeleniumHomePage homePage = new SeleniumHomePage(driver);
		homePage.btnMenuAusklappen();
		homePage.seleniumTestLinkAnklicken();
		
		SeleniumTestApplikationenPage testAppPage = new SeleniumTestApplikationenPage(driver);
		testAppPage.testForm1Anklicken();
		
		// Starte Formular
		SeleniumTestForm1Page testForm1Page = new SeleniumTestForm1Page(driver);
		testForm1Page.betreffEingeben("Automatisierte Test");
		testForm1Page.nameEingeben("Dieter");
		testForm1Page.kursAuswaehlen("Selenium Automatisierung mit Dieter");
	
		// Auswahl von Firmen in der Liste
		testForm1Page.firmaInBox1Auswaehlen(new int[] {2, 4, 6});
		testForm1Page.firmenUerbernehmen();
		testForm1Page.firmaInBox2Auswaehlen(new int[] {2});
		testForm1Page.ausgewählteFirmenNachObenVerschieben();
		
		// Act (Aktion)
		testForm1Page.formularSpeichern();
	
		// Assert (Überprüfung der erwarteten Ergebnisse)
		
		// Überprüfung der Erfolgsmeldung
		String erfolgsMeldung = testForm1Page.statusMeldungAuslesen();
		assertTrue(erfolgsMeldung.contains("Kurs Selenium Automatisierung"));
		
		// Überprüfung des ersten Elements in der Liste
		String erstesElement = testForm1Page.erstesListenElementAuslesen();
		assertEquals(erstesElement, "Magazzini Alimentari Riuniti");
	}
	
	/**
     * Parameter für den Testlauf (hier nur Firefox als Beispiel).
     */
	 @Parameters(name = "{0}")
	public static Collection<Object[]> provideTestData() {
	    return Arrays.asList(new Object[][]{
	        {"firefox"}            
	    });
	}
}
