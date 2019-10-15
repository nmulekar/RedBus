package redbus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedbusEx {

	public static void main(String[] args) throws Exception {

		ChromeOptions options = new ChromeOptions();

		options.addArguments("--disable-notifications");

		WebDriver driver = new ChromeDriver(options);

		System.getProperty("webdriver.chrome.driver", "E:\\Java By Kiran\\RedBus\\chromedriver.exe");

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		driver.get("https://www.redbus.com/");

		// ===================================================================================

		WebDriverWait wait = new WebDriverWait(driver, 30);

		Actions action = new Actions(driver);

		// ===================================================================================

		WebElement india = driver.findElement(By.xpath("//div[text()='India']"));

		india.click();

		String parent = driver.getWindowHandle();

		String parentTitle = driver.getTitle();

		System.out.println("Title >> " + parentTitle);

		Set<String> windowSet = driver.getWindowHandles();

		for (String handle : windowSet) {

			if (!handle.equals(parent)) {

				driver.switchTo().window(handle);

				String childTitle = driver.getTitle();

				System.out.println("Next Title >> " + childTitle);

			}
		}

		// ===================================================================================

		WebElement from = driver.findElement(By.id("src"));

		from.sendKeys("Pune");

		WebElement shivajiNagar = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Shivaji Nagar, Pune']")));

		action.moveToElement(shivajiNagar).click().build().perform();

		WebElement to = driver.findElement(By.id("dest"));

		to.sendKeys("Aurangabad");

		WebElement aurangabad = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Aurangabad (All Locations)']")));

		action.moveToElement(aurangabad).click().build().perform();

		// ===================================================================================

		DateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy");

		Date date = new Date();

		String currentDate = dateformat.format(date);

		System.out.println("Current Date is >> " + currentDate);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DAY_OF_MONTH, 6);

		String departureDate = sdf.format(cal.getTime());

		System.out.println("Departure Date >> " + departureDate);

		Calendar cal1 = Calendar.getInstance();

		cal1.add(Calendar.DAY_OF_MONTH, 7);

		String returnDate = sdf.format(cal1.getTime());

		System.out.println("Return Date >> " + returnDate);

		System.out.println("===================================================================================");

		String departureDateArr[] = departureDate.split("-");

		String day = departureDateArr[0];
		String month = departureDateArr[1];
		String year = departureDateArr[2];

		WebElement onwardCalender = driver.findElement(By.id("rb-calendar_onward_cal"));

		action.moveToElement(onwardCalender).build().perform();

		String beforeXpath = "//div[@id='rb-calendar_onward_cal']//table//tbody//tr[";

		String afterXpath = "]//td[";

		boolean flag = false;

		for (int row = 3; row <= 7; row++) {

			for (int col = 1; col <= 7; col++) {

				String dayValue = driver.findElement(By.xpath(beforeXpath + row + afterXpath + col + "]")).getText();

				System.out.println(dayValue);

				if (dayValue.equals(day)) {

					WebElement ClickOnValue = wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(beforeXpath + row + afterXpath + col + "]")));

					ClickOnValue.click();

					flag = true;

					break;
				}
			}

			if (flag) {

				break;
			}
		}

		// -----------------------------------------------------------------

		String returnDateArr[] = returnDate.split("-");

		String returnDay = returnDateArr[0];
		String returnMonth = returnDateArr[1];
		String returnYear = returnDateArr[2];

		WebElement returnCalender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("return_cal")));

		action.moveToElement(returnCalender).click().build().perform();

		String beforeXpath1 = "//div[@id=\"rb-calendar_return_cal\"]//table//tbody//tr[";

		String afterXpath1 = "]//td[";

		boolean flag1 = false;

		for (int row = 3; row <= 7; row++) {

			for (int col = 1; col <= 7; col++) {

				String dayValue = driver.findElement(By.xpath(beforeXpath1 + row + afterXpath1 + col + "]")).getText();

				System.out.println(dayValue);

				if (dayValue.equals(returnDay)) {

					WebElement ClickOnValue = wait.until(ExpectedConditions
							.visibilityOfElementLocated(By.xpath(beforeXpath1 + row + afterXpath1 + col + "]")));

					ClickOnValue.click();

					flag1 = true;

					break;
				}
			}

			if (flag1) {

				break;
			}
		}

		// -----------------------------------------------------------------

		WebElement searchBusBtn = driver.findElement(By.id("search_btn"));

		searchBusBtn.click();

		// -----------------------------------------------------------------

		WebElement viewBuses = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class=\"clearfix\"]//div[@class=\"w-14 fl\"]//div[@class=\"button\"]")));

		action.moveToElement(viewBuses).click().build().perform();

		// -----------------------------------------------------------------

	}

}
