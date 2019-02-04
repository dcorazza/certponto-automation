package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebBrowser {
	
	private static WebBrowser webBrowser;
	private WebDriver webDriver;
	
	private WebBrowser() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver.exe");
		
		webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	private static WebBrowser getInstance() {
		if(webBrowser == null) {
			webBrowser = new WebBrowser();
		}
		
		return webBrowser;
	}
	
	public static void waitUntil(ExpectedCondition<?> expectedCondition) {
		new WebDriverWait(getInstance().webDriver, 30L).until(expectedCondition);
	}
	
	public static void waitPageLoad() {
		new WebDriverWait(getInstance().webDriver, 30L).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
		
		try {
			Thread.sleep(7000);
		} catch(InterruptedException e) {
			throw new InternalErrorException("Erro ao aguardar o load da p�gina");
		}
	}
	
	public static void clickElement(By by) {
		WebElement webElement = findElement(by);
		new WebDriverWait(getInstance().webDriver, 30L).until(ExpectedConditions.elementToBeClickable(webElement));
		webElement.click();
	}
	
	public static void waitForElement(By by) {
		new WebDriverWait(getInstance().webDriver, 30L).until(ExpectedConditions.presenceOfElementLocated(by));
		new WebDriverWait(getInstance().webDriver, 30L).until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public static void loadURL(String url) {
		getInstance().webDriver.get(url);
	}
	
	public static WebElement findElement(By by) {
		waitForElement(by);
		return getInstance().webDriver.findElement(by);
	}

	public static WebElement findElementNoWait(By by) {
		return getInstance().webDriver.findElement(by);
	}
	
	public static WebElement findElement(WebElement parentElement, By childBy) {
		new WebDriverWait(getInstance().webDriver, 30L).until(ExpectedConditions.presenceOfNestedElementLocatedBy(parentElement, childBy));
		new WebDriverWait(getInstance().webDriver, 30L).until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parentElement, childBy));
		return parentElement.findElement(childBy);
	}
	
	public static List<WebElement> findElements(By by) {
		waitForElement(by);
		return getInstance().webDriver.findElements(by);
	}
	
	public static boolean isElementDisplayed(By by) {
		try {
			return getInstance().webDriver.findElement(by).isDisplayed();
		} catch(NoSuchElementException e) {
			return false;
		}
	}
	
	public static void switchToFrame(String frame) {
		waitForElement(By.id(frame));
		getInstance().webDriver.switchTo().frame(frame);
	}
	
	public static void switchToTab(int tabIndex) {
		ArrayList<String> tabs = new ArrayList<String> (getInstance().webDriver.getWindowHandles());
		getInstance().webDriver.switchTo().window(tabs.get(tabIndex));
	}
	
	public static void scrollDown() {
		JavascriptExecutor jsx = (JavascriptExecutor) WebBrowser.getInstance().webDriver;
        jsx.executeScript("window.scrollBy(0, 500)", "");
	}
	
	public static void scrollUp() {
		JavascriptExecutor jsx = (JavascriptExecutor) WebBrowser.getInstance().webDriver;
		jsx.executeScript("window.scrollBy(0, -500)", "");
	}
	
	public static String takeSnapShot() {
		try {
			return "data:image/png;base64," + ((TakesScreenshot) getInstance().webDriver).getScreenshotAs(OutputType.BASE64);
		} catch (Exception e) {
			return "Erro ao tirar snapshot";
		}
	}
	
	public static void close() {
		if(webBrowser != null && webBrowser.webDriver != null) {
			webBrowser.webDriver.quit();
		}
	}
	
}
