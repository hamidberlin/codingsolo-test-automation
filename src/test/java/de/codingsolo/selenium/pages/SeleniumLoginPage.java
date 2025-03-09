package de.codingsolo.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumLoginPage {

	private WebDriver driver;
	
	/**
	 * Wir nutzen die Vorteile von @FindBy um leichter auf die WebElemente zu zugreifen.
	 * 
	 * 	Es ist möglich dass bei Asynchrone Application die @FinBy-Annotation nicht 
		funktioniert und man doch auf die alte Methode zugreifen muss(this.driver.findElement(by....)
		Aber in meisten Fälle sollte die @FindBy-Annotation triggern.
		Es spart uns Zeit auf diese Weise, wenn wir die Elmente schon vorher implementieren.
	 */

	
	
	// Eingabefeld Benutzername
	@FindBy(css = "#__ac_name")
	private WebElement inputBenutzername;
	
	// Eingabefeld Password
	@FindBy(xpath = "//input[@id='__ac_password']")
	private WebElement inputPassword;
	
	// Button für die Anmeldung
	@FindBy(xpath = "//input[@value=\'Anmelden\']")
	private WebElement btnAnmeldung;
	
	// Statusmeldung
	@FindBy(css = ".portalMessage")
	private WebElement statusMeldung;
	
	public SeleniumLoginPage(WebDriver driver) {
		this.driver = driver;
		// Hiermit initialisieren wir die Elemente unsere PageObjektKlasse
		PageFactory.initElements(driver, this);
	}
	
	public void zugangsdatenEingeben(String benutzername, String password) {
		inputBenutzername.sendKeys(benutzername);
		inputPassword.sendKeys(password);
	}
	
	public void loginButtonAnklicken() {
		btnAnmeldung.click();
	}
	
	public String statusMeldungAuslesen() {
		return statusMeldung.getText();
	}
}
