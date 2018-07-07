package com.mackaroo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MockarooDataValidation {

	WebDriver driver;
	List<String> cities = new ArrayList<>();
	List<String> countries = new ArrayList<>();

	@BeforeClass // runs once for all tests
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}

	@Test(priority = 1) // 2-3
	public void toNavigate() {
		driver.get("https://mockaroo.com/");
		String actual = driver.getTitle();
		String expected = "Mockaroo - Random Data Generator and API Mocking Tool | JSON / CSV / SQL / Excel";
		Assert.assertEquals(actual, expected);
	}

	@Test(priority = 2) // 4
	public void isMockarooDisplayed() {
		WebElement brand = driver.findElement(By.xpath("//div[@class = 'brand']"));
		WebElement tagline = driver.findElement(By.xpath("//div[@class = 'tagline']"));
		Assert.assertTrue(brand.isDisplayed());
		Assert.assertTrue(tagline.isDisplayed());

		String actualBrand = driver.findElement(By.xpath("//div[@class = 'brand']")).getText();
		String expectedBrand = "mockaroo";
		Assert.assertEquals(actualBrand, expectedBrand);

		String actualTagline = driver.findElement(By.xpath("//div[@class = 'tagline']")).getText();
		String expectedTagline = "realistic data generator";
		Assert.assertEquals(actualTagline, expectedTagline);

	}

	@Test(priority = 3) // 5

	public void removeAllFields() {

		List<WebElement> fields = driver
				.findElements(By.xpath("//a[@class = 'close remove-field remove_nested_fields']"));

		for (int i = 0; i < fields.size(); i++) {
			driver.findElement(By.xpath("(//a[@class = 'close remove-field remove_nested_fields'])[" + (i + 1) + "]"))
					.click();

		}
	}

	@Test(priority = 4) // 6
	public void verifyDefault() {
		WebElement fieldName = driver.findElement(By.xpath("//div[@class='column column-header column-name']"));
		WebElement type = driver.findElement(By.xpath("//div[@class = 'column column-header column-type']"));
		WebElement options = driver.findElement(By.xpath("//div[@class = 'column column-header column-options']"));

		Assert.assertTrue(fieldName.isDisplayed());
		Assert.assertTrue(type.isDisplayed());
		Assert.assertTrue(options.isDisplayed());

	}

	@Test(priority = 5) // 7
	public void isaddAnotherFieldEnabled() {
		WebElement addAnotherField = driver
				.findElement(By.xpath("//a[@class = 'btn btn-default add-column-btn add_nested_fields']"));
		Assert.assertTrue(addAnotherField.isEnabled());
	}

	@Test(priority = 6) // 8
	public void isRow1000() {
		String rows = driver.findElement(By.id("num_rows")).getAttribute("value");
		Assert.assertEquals(rows, "1000");
	}

	@Test(priority = 6) // 9
	public void isformatCsv() {
		String format = driver.findElement(By.id("schema_file_format")).getAttribute("value");
		Assert.assertEquals(format, "csv");
	}

	@Test(priority = 7) // 10
	public void islineEndingUnix() {
		String lineEnding = driver.findElement(By.id("schema_line_ending")).getAttribute("value");
		Assert.assertEquals(lineEnding, "unix");
	}

	@Test(priority = 8) // 11
	public void isChecked() {
		WebElement headerCheckBox = driver.findElement(By.id("schema_include_header"));
		Assert.assertTrue(headerCheckBox.isSelected());

		WebElement bomCheckBox = driver.findElement(By.id("schema_bom"));
		Assert.assertFalse(bomCheckBox.isSelected());
	}

	@Test(priority = 9) // 12
	public void addCityField() throws InterruptedException {
		// add city
		driver.findElement(By.xpath("//a[@class = 'btn btn-default add-column-btn add_nested_fields']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@placeholder='enter name...'])[7]")).sendKeys("City");
		driver.findElement(By.xpath("(//input[@class = 'btn btn-default'])[7]")).click();

	}

	@Test(priority = 10) // 13
	public void isDialogBoxDisplayed() throws InterruptedException {
		Thread.sleep(2000);
		WebElement chooseAType = driver.findElement(By.cssSelector(".modal-title"));
		Assert.assertTrue(chooseAType.isDisplayed());
	}

	@Test(priority = 11) // 14
	public void searchForCity() throws InterruptedException {
		driver.findElement(By.id("type_search_field")).click();
		driver.findElement(By.id("type_search_field")).sendKeys("city");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class = 'type']")).click();
	}

	@Test(priority = 12) // 15
	public void addCountryField() throws InterruptedException {
		// add country
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class = 'btn btn-default add-column-btn add_nested_fields']")).click();
		driver.findElement(By.xpath("(//input[@placeholder='enter name...'])[8]")).sendKeys("Country");
		driver.findElement(By.xpath("(//input[@class = 'btn btn-default'])[8]")).click();
		Thread.sleep(2000);
	}

	@Test(priority = 13)
	public void searchForCountry() throws InterruptedException {
		driver.findElement(By.id("type_search_field")).click();
		driver.findElement(By.id("type_search_field")).clear();
		driver.findElement(By.id("type_search_field")).sendKeys("country");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class = 'type-name']")).click();
	}

	@Test(priority = 14) // 16
	public void download() throws InterruptedException {
		// download
		Thread.sleep(2000);
		driver.findElement(By.id("download")).click();
		Thread.sleep(3000);

	}

	@Test(priority = 15) // 17
	public void readDownloadFile() throws IOException {

		List<String> cityNames = new ArrayList<>();
		cityNames = Utility.readFromFile(0);
		cities.addAll(cityNames);

		List<String> countryNames = new ArrayList<>();
		countryNames = Utility.readFromFile(1);
		countries.addAll(countryNames);

	}

	@Test(priority = 16) // 18
	public void firstRow() {
		String fieldName1 = cities.get(0);
		String fieldName2 = countries.get(0);

		Assert.assertEquals(fieldName1, "City");
		Assert.assertEquals(fieldName2, "Country");
	}

	@Test(priority = 17)
	public void records1000() {
		int numberOfRecords = countries.size() - 1;
		Assert.assertEquals(numberOfRecords, 1000);
	}

	@Test(priority = 18)
	public void longestCity() {
		String city = Utility.longestCityName(cities);
		System.out.println("The longest city name is: " + city);
	}

	@Test(priority = 19)
	public void shortestCity() {
		String city = Utility.shortestCityName(cities);
		System.out.println("The shortest city name is: " + city);
	}

	@Test(priority = 20)
	public void frequency() {
		SortedSet<String> unique = new TreeSet<String>(countries);
		for (String each : unique) {
			System.out.println(each + " - " + Collections.frequency(countries, each));
		}

	}

	@Test(priority = 21)
	public void uniqueCity() {
		Set<String> citySet = new HashSet<>(cities);
		int sizeOfCitySet = citySet.size();

		int uniqueCities = Utility.uniqueEntries(cities);

		Assert.assertEquals(sizeOfCitySet, uniqueCities);

	}

	@Test(priority = 22)
	public void uniqueCountry() {
		Set<String> countrySet = new HashSet<>(countries);
		int sizeOfCountrySet = countrySet.size();

		int uniqueCountries = Utility.uniqueEntries(countries);

		Assert.assertEquals(sizeOfCountrySet, uniqueCountries);

	}

}
