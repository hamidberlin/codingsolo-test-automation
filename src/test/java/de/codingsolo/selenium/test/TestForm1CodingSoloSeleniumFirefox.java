package de.codingsolo.selenium.test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestForm1CodingSoloSeleniumFirefox {
	
	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.out.println("Initialisiere Webdriver");
		System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://seleniumkurs.codingsolo.de");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test abgeschlossen. - Aufräumen");
		driver.quit(); // Nutze quit statt close!
	}

	@Test
	public void testForm1() {
		System.out.println("Starte Test Navigation");

		// Arrange: 
		
		// Login
		driver.findElement(By.cssSelector("input.form-control[type='text']")).sendKeys("selenium42");
		driver.findElement(By.cssSelector("input.form-control[type='password']")).sendKeys("R5vxI0j60");
		driver.findElement(By.cssSelector("input.btn-primary")).click();
		
		// Navigation zum Formular
		driver.findElement(By.id("portaltab-burger-menu")).click();
		driver.findElement(By.linkText("Selenium Testapplikationen")).click();
		driver.findElement(By.linkText("Selenium Test Form1")).click();
		
		// Starte Formular
		driver.findElement(By.id("form-widgets-betreff")).sendKeys("Automatisierte Test");
		driver.findElement(By.id("form-widgets-name")).sendKeys("Dieter");

		Select dropDown1 = new Select(driver.findElement(By.id("form-widgets-auswahl1")));
		dropDown1.selectByVisibleText("Selenium Automatisierung mit Dieter");

		Select dropDown2 = new Select(driver.findElement(By.id("form-widgets-auswahl2-from")));
		dropDown2.selectByIndex(2);
		dropDown2.selectByIndex(4);
		dropDown2.selectByIndex(6);

		driver.findElement(By.name("from2toButton")).click();

		Select dropDown3 = new Select(driver.findElement(By.id("form-widgets-auswahl2-to")));
		dropDown3.selectByIndex(2);
		driver.findElement(By.name("upButton")).click();

		// Act: Formular speichern
		
		driver.findElement(By.name("form.buttons.speichern")).click();

		// Assert: Erfolgsmeldung prüfen
		
		String erfolgsMeldung = driver.findElement(By.id("message")).getText();
		assertTrue(erfolgsMeldung.contains("Kurs Selenium Automatisierung"));
		
		String erstesElement = driver.findElement(By.xpath("//ul[@id='companies']/li")).getText();
		assertEquals(erstesElement, "Magazzini Alimentari Riuniti");
	}
}
