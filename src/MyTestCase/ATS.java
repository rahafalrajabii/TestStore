package MyTestCase;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ATS {
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();

	String TheUserName;
	String ThePassword = "T@321654";
	String TheURL = "https://automationteststore.com/";

	@BeforeTest
	public void TheURL() {
		driver.get("https://automationteststore.com/");
		driver.manage().window().maximize();

	}

	@Test(priority = 1, enabled = false)
	public void SignUpPage() throws InterruptedException {
		driver.navigate().to("https://automationteststore.com/index.php?rt=account/create");

//Elements
		WebElement firstnameInput = driver.findElement(By.xpath("//input[@id='AccountFrm_firstname']"));
		WebElement lastnameInput = driver.findElement(By.id("AccountFrm_lastname"));
		WebElement emailInput = driver.findElement(By.id("AccountFrm_email"));
		WebElement telephoneInput = driver.findElement(By.id("AccountFrm_telephone"));
		WebElement faxInput = driver.findElement(By.id("AccountFrm_fax"));
		WebElement companyInput = driver.findElement(By.id("AccountFrm_company"));
		WebElement address1Input = driver.findElement(By.id("AccountFrm_address_1"));
		WebElement address2Input = driver.findElement(By.id("AccountFrm_address_2"));
		WebElement cityInput = driver.findElement(By.id("AccountFrm_city"));
		WebElement postalcodeInput = driver.findElement(By.id("AccountFrm_postcode"));
		WebElement loginname = driver.findElement(By.id("AccountFrm_loginname"));
		WebElement passwordInput = driver.findElement(By.id("AccountFrm_password"));
		WebElement passwordconfirmInput = driver.findElement(By.id("AccountFrm_confirm"));
		WebElement agreeBox = driver.findElement(By.id("AccountFrm_agree"));
		WebElement ContinueButton = driver.findElement(By.cssSelector(".btn.btn-orange.pull-right.lock-on-click"));
		WebElement CountrySelect = driver.findElement(By.id("AccountFrm_country_id"));
		WebElement StateSelect = driver.findElement(By.id("AccountFrm_zone_id"));
//Data

		String[] firstnames = { "Rahaf", "Zaina", "Bayan", "Heba", "Sara" };
		int randomIndexForFirstName = rand.nextInt(firstnames.length);
		/* الافضل عشان لما اروح ع الاكشن ما يكون كتير طويل اني اعرف متغير تاني */
		String Randomfirstnames = firstnames[randomIndexForFirstName];
		System.out.println(firstnames[randomIndexForFirstName]);

		String[] lastnames = { "Alrajabi", "Abdullah", "Muhamad" };
		int randomIndexForLastName = rand.nextInt(lastnames.length);
		String Randomlastnames = lastnames[randomIndexForLastName];

		int RandonNumberforEmail = rand.nextInt(7000);
		String email = Randomfirstnames + Randomlastnames + RandonNumberforEmail + "@gmail.com";

		String telephone = "0123654789";
		String fax = "32145";
		String company = "PWC";
		String address1 = "amman khalda";
		String address2 = "amman shmisani";
		String city = "amman";
		String postalcode = "321654";
		// String password = "T@321654";

//Action
		TheUserName = Randomfirstnames + Randomlastnames + RandonNumberforEmail;
		// ThePassword= password;

		firstnameInput.sendKeys(Randomfirstnames);
		lastnameInput.sendKeys(Randomlastnames);
		emailInput.sendKeys(email);
		telephoneInput.sendKeys(telephone);
		faxInput.sendKeys(fax);
		companyInput.sendKeys(company);
		address1Input.sendKeys(address1);
		address2Input.sendKeys(address2);
		cityInput.sendKeys(city);

		Select MySelectforCountry = new Select(CountrySelect);

		int Totalcountries = CountrySelect.findElements(By.tagName("option")).size();

		int randomCountry = rand.nextInt(1, Totalcountries);
		MySelectforCountry.selectByIndex(randomCountry);

		System.out.println(randomCountry);

		Thread.sleep(2000);

		Select MySelectforState = new Select(StateSelect);
		int Numberforoptions = StateSelect.findElements(By.tagName("option")).size();
		StateSelect.findElements(By.tagName("option")).size();
		System.out.println(StateSelect.findElements(By.tagName("option")).size());

		int randomIndexforState = rand.nextInt(1, Numberforoptions);
		MySelectforState.selectByIndex(randomIndexforState);

		postalcodeInput.sendKeys(postalcode);
		loginname.sendKeys(Randomfirstnames + Randomlastnames + RandonNumberforEmail);
		passwordInput.sendKeys("T@321654");
		passwordconfirmInput.sendKeys("T@321654");
		agreeBox.click();
		ContinueButton.click();

	}

	@Test(priority = 2, enabled = true)
	public void LogOut() throws InterruptedException {

		Thread.sleep(2000);
		WebElement logoutButton = driver.findElement(By.linkText("Logoff"));
		logoutButton.click();

		WebElement ContinueButton = driver.findElement(By.linkText("Continue"));
		ContinueButton.click();

	}

	@Test(priority = 3, enabled = true)
	public void loginButton() throws InterruptedException {

		WebElement loginorregisterButton = driver.findElement(By.linkText("Login or register"));
		loginorregisterButton.click();

		WebElement loginName = driver.findElement(By.id("loginFrm_loginname"));
		WebElement passward = driver.findElement(By.id("loginFrm_password"));
		loginName.sendKeys(TheUserName);
		passward.sendKeys(ThePassword);

		Thread.sleep(2000);
		WebElement loginButton = driver.findElement(By.xpath("//button[normalize-space()='Login']"));
		loginButton.click();

	}

	@Test(priority = 4, invocationCount = 1)
	public void AddToCart() throws InterruptedException {
		driver.navigate().to("https://automationteststore.com/");

		Thread.sleep(3000);

		List<WebElement> TheListOfItems = driver.findElements(By.className("prdocutname"));

		int TotalItems = TheListOfItems.size();
		System.out.println(TotalItems);
		int RandomItemIndex = rand.nextInt(TotalItems);
		TheListOfItems.get(RandomItemIndex).click();

		/*
		 * arriba : de esto he tomado el numero de los items ahora tengo que hacerlo en
		 * LIST
		 * 
		 * bajo: ahora puede ser que hay un iten OutOF Stuck que vamos a hacer "" IF
		 * SENTANCE""
		 */

		if (driver.getPageSource().contains("Out of Stock")) {

			driver.navigate().back();

			System.out.println("Sorry The Item is Out of Stock");
		} else {
			System.out.println(" The Item is Avalible");
		}

	}
}
