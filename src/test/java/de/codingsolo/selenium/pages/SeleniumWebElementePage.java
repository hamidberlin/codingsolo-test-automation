package de.codingsolo.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumWebElementePage {
	
	private WebDriver driver;
	
	@FindBy(id = "checkBoxOption1")
	private WebElement checkBox1;
	
	@FindBy(id = "checkBoxOption2")
	private WebElement checkBox2;
	
	@FindBy(id = "checkBoxOption3")
	private WebElement checkBox3;
	
	@FindBy(css = "input[value='radio1")
	private WebElement redio1Btn;
	
	@FindBy(css = "input[value='radio2")
	private WebElement redio2Btn;
	
	@FindBy(css = "input[value='radio3")
	private WebElement redio3Btn;

	public SeleniumWebElementePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void checkBox1Anklicken() {
		checkBox1.click();
	}
	
	public void checkBox2Anklicken() {
		checkBox2.click();
	}
	
	public void checkBox3Anklicken() {
		checkBox3.click();
	}
	
	public boolean checkBox1StatusAuslesen() {
		return checkBox1.isSelected();
	}
	
	public boolean checkBox2StatusAuslesen() {
		return checkBox2.isSelected();
	}
	
	public boolean checkBox3StatusAuslesen() {
		return checkBox3.isSelected();
	}

	public void radioButton1Anklicken() {
		redio1Btn.click();
	}
	
	public void radioButton2Anklicken() {
		redio2Btn.click();
	}
	
	public void radioButton3Anklicken() {
		redio3Btn.click();
	}
	
	public String radioButton1getAttribute(String attributname) {
		return redio1Btn.getAttribute(attributname);
	}
	
	public String radioButton2getAttribute(String attributname) {
		return redio2Btn.getAttribute(attributname);
	}
	
	public String radioButton3getAttribute(String attributname) {
		return redio3Btn.getAttribute(attributname);
	}
}
