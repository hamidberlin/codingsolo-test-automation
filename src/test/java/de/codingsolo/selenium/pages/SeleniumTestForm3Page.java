package de.codingsolo.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SeleniumTestForm3Page {
	
	private WebDriver driver;
	
	@FindBy(id = "form-widgets-bezeichnung")
	private WebElement inputBezeichnung;
	
	@FindBy(id = "form-widgets-kennung")
	private WebElement inputKennung;
	
	@FindBy(id = "form-widgets-anschrift")
	private WebElement inputAnschrift;
	
	@FindBy(id = "form-widgets-telefon")
	private WebElement inputTelefonnummerVersicherung;
	
	@FindBy(id = "form-widgets-str")
	private WebElement inputStrasseVersicherung;
	
	@FindBy(id = "form-widgets-plz")
	private WebElement inputPLZVersicherung;
	
	@FindBy(id = "form-widgets-ort")
	private WebElement inputOrtVersicherung;
	
	@FindBy(id = "form-widgets-auswahl1")
	private WebElement selectAuswahl1;
	
	@FindBy(id = "form-widgets-name")
	private WebElement inputNachname;
	
	@FindBy(id = "form-widgets-vorname")
	private WebElement inputVorname;
	
	@FindBy(id = "form-widgets-geburt")
	private WebElement inputGeburtsdatum;
	
	@FindBy(id = "form-widgets-telefonv")
	private WebElement inputTelefonummer;
	
	@FindBy(id = "form-widgets-strv")
	private WebElement inputStrasse;
	
	@FindBy(id = "form-widgets-plzv")
	private WebElement inputPLZ;
	
	@FindBy(id = "form-widgets-ortv")
	private WebElement inputOrt;
	
	@FindBy(name = "form.buttons.speichern")
	private WebElement btnSubmit;
	
	@FindBy(id="message")
	private WebElement textStatus1;
	
	@FindBy(xpath = "//*[@id='auswahl']//li[1]")
	private WebElement textStatus2;
		

	public SeleniumTestForm3Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void formularVersicherungBezeichnungEingaben(String bezeichnung) {
		this.inputBezeichnung.sendKeys(bezeichnung);
	}
	
	public void formularVersicherungKennungEingaben(String kennung) {
		this.inputKennung.sendKeys(kennung);
	}
	
	public void formularVersicherungAnschriftEingaben(String anschrift) {
		this.inputAnschrift.sendKeys(anschrift);
	}
	
	public void formularVersicherungTelefonEingaben(String telefonnummer) {
		this.inputTelefonnummerVersicherung.sendKeys(telefonnummer);
	}
	
	public void formularVersicherungStrasseEingaben(String strasse) {
		this.inputStrasseVersicherung.sendKeys(strasse);
	}
	
	public void formularVersicherungPLZEingaben(String plz) {
		this.inputPLZVersicherung.sendKeys(plz);
	}
	
	public void formularVersicherungOrtEingaben(String ort) {
		this.inputOrtVersicherung.sendKeys(ort);
	}
	

	public void selectAuswahl1Eingaben(String auswahl) {
	    Select select1 = new Select(selectAuswahl1);
	    select1.selectByValue(auswahl);
	}

	public void formularPersonenNachnameEingaben(String nachname) {
		this.inputNachname.sendKeys(nachname);
	}
	
	public void formularPersonenVornameEingaben(String vorname) {
		this.inputVorname.sendKeys(vorname);
	}
	
	public void formularPersonenGeburtsdatumEingaben(String geburtsdatum) {
		this.inputGeburtsdatum.sendKeys(geburtsdatum);
	}
	
	public void formularPersonenTelefonnummerEingaben(String telefonnummer) {
		this.inputTelefonummer.sendKeys(telefonnummer);
	}
	
	public void formularPersonenStrasseEingaben(String strasse) {
		this.inputStrasse.sendKeys(strasse);
	}
	
	public void formularPersonenPLZEingaben(String plz) {
		this.inputPLZ.sendKeys(plz);
	}
	
	public void formularPersonenOrtEingaben(String ort) {
		this.inputOrt.sendKeys(ort);
	}
	
	public void btnSubmitAnklicken() {
		btnSubmit.click();
	}
	
	public String statusText1Auslesen() {
		return textStatus1.getText();
	}
	
	public String statusText2Auslesen() {
		return textStatus2.getText();
	}

}
