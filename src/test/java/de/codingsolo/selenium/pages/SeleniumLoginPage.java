package de.codingsolo.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumLoginPage {

	private WebDriver driver;

	
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
