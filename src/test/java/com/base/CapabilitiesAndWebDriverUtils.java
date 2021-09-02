package com.base;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.appium.java_client.*;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.math3.stat.descriptive.moment.SemiVariance.Direction;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.model.Device;
import com.browserstack.local.Local;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CapabilitiesAndWebDriverUtils {
	public static Properties props;
	public static StopWatch watch;
	public static int elementWaitInSeconds = 0;
	public static String platformName = null;

	public static Logger logger = Logger.getLogger(String.valueOf(CapabilitiesAndWebDriverUtils.class));
	public static AppiumDriverLocalService appiumService;
	public static String appiumServiceUrl;
	public static Configuration config = null;
	public static boolean AUTOEXECTIONSTATUS = true;
	public static RemoteWebDriver driver;
	public static String userDir = System.getProperty("user.dir");
	public static String browser = null;
	public static boolean initilizestatus = false;
	public static String ConfigurationFile = System.getProperty("user.dir") + "/Configs/Configuration.properties";
	public static WebElement element = null;
	public static String mainWindow = null;
	static long t1 = 0;
	static long t2 = 0;
	static WebDriverWait browserWithElementWait = null;
	static Date date = java.util.Calendar.getInstance().getTime();
	public static final String AUTOMATE_ACCESS_KEY = "GFNjp9AfYzBoFZkvucvS";
	public static final String AUTOMATE_USERNAME = "girishkr1";
	public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY
			+ "@hub-cloud.browserstack.com/wd/hub";
	public static WebDriverWait exwait = null;
	public static String ChallengeName;
	ExcelReader reader = new ExcelReader();

	static {
		try {
			platformName = getData("platformName");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public CapabilitiesAndWebDriverUtils() {
		try {
			exwait = new WebDriverWait(driver, 60);
			System.setProperty("currentDir", userDir);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void appiumStart() throws IOException, InterruptedException {
		AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
		serviceBuilder.usingAnyFreePort();
		HashMap<String, String> environment = new HashMap();
	}

	// This method Is responsible for stopping appium server.
	public static void appiumStop() throws IOException {
		appiumService.stop();
	}

	public static void initiateNativeAPP() throws IOException, InterruptedException {
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			if ("ANDROID".equalsIgnoreCase(platformName)) {
				System.out.println("Initialising the Android Application");

				File f = new File(getData("buildPath"));
				File fs = new File(f, getData("androidBuild"));
				capabilities.setCapability("platformName", getData("platformName"));
				capabilities.setCapability("deviceName", getData("deviceName"));
				capabilities.setCapability("android Version", getData("androidVersion"));
				capabilities.setCapability("app Package", getData("appPackage"));
				capabilities.setCapability("app Activity", ("appActivity"));
				capabilities.setCapability("noReset", "true");
				capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, getData("automationNameAndroid"));
				capabilities.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
				driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

			} else if ("IOS".equalsIgnoreCase(platformName)) {
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getData("deviceName"));
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, getData("platformName"));
				capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, getData("iOSVersion"));
				capabilities.setCapability("udid", getData("UDID"));
				capabilities.setCapability("Bundle identifier", getData("bundleIdentifier"));
				// capabilities.setCapability("noReset", "true");
				capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, getData("iOSAutomationName"));
				capabilities.setCapability(MobileCapabilityType.APP, getData("buildPath"));
				driver = new IOSDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
			} else if ("WEBAPP-ANDROID".equalsIgnoreCase(platformName)) {
				System.out.println("Initializing the Android device Browser app");
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("deviceName", config.getString("deviceId"));
				capabilities.setCapability("browserName", config.getString("browser"));
				driver = new AndroidDriver<>(new URL(appiumServiceUrl), capabilities);
				driver.get(config.getString("url"));
			} else if ("WEBAPP-IOS".equalsIgnoreCase(platformName)) {
				System.out.println("Initializing the IOS device browser app");
				capabilities.setCapability("platformName", "iOS");
				capabilities.setCapability("platformVersion", config.getString("Version"));
				capabilities.setCapability("deviceName", config.getString("deviceName"));
				capabilities.setCapability("browserName", config.getString("browser"));
				capabilities.setCapability("udid", config.getString("udid"));
				capabilities.setCapability("startIWDP", true);
				capabilities.setCapability("automationName", config.getString("automationName"));
				capabilities.setCapability("noReset", true);
				driver = new IOSDriver<WebElement>(new URL(appiumServiceUrl), capabilities);
				driver.get(config.getString("url"));
			} else if ("Web".equalsIgnoreCase(platformName)) {
				System.out.println("Initializing the desktop web browser");
				FileInputStream fis = new FileInputStream(ConfigurationFile);
				Properties prop = new Properties();
				prop.load(fis);
				String browserName = prop.getProperty("browser");
				Browser_Launch(browserName);
				driver.get(prop.getProperty("url"));

			} else if ("Web_Bstack".equalsIgnoreCase(platformName)) {
				System.out.println("Initializing the desktop web browserstack");
				FileInputStream fis = new FileInputStream(ConfigurationFile);
				Properties prop = new Properties();
				prop.load(fis);
				String browserName = prop.getProperty("browser");
				// Browser_Launch(browserName);
				local_instance_Browserstack();
				Browserstack_web(browserName);
				String uAgent = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
				System.out.println(uAgent);
				driver.get(prop.getProperty("BrowseStack_url"));
			} else if ("BrowserStackAndroid".equalsIgnoreCase(platformName)) {
				System.out.println("Initializing the App in browserstack");
				local_instance_Browserstack();
				capabilities.setCapability("browserstack.user", "girishkr1");
				capabilities.setCapability("browserstack.key", "GFNjp9AfYzBoFZkvucvS");
				capabilities.setCapability("os_version", getData("androidBrowserStackVersion"));
				capabilities.setCapability("device", getData("androidBrowserStackDevice"));
				capabilities.setCapability("real_mobile", "true");
				capabilities.setCapability("project", "DestinetDiscover");
				capabilities.setCapability("build", getData("buildName"));
				capabilities.setCapability("name", "Test : " + date);
				capabilities.setCapability("app", getData("apkkey"));
				capabilities.setCapability("browserstack.appium_version", "1.18.0");
				capabilities.setCapability("browserstack.local", "false");
				capabilities.setCapability("browserstack.local", "true");
				driver = new AndroidDriver<AndroidElement>(new URL("http://hub.browserstack.com/wd/hub"), capabilities);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			} else if ("BrowserStackIOS".equalsIgnoreCase(platformName)) {

				System.out.println("Initializing the IOS App in browserstack");
				capabilities.setCapability("browserstack.user", "girishkr1");
				capabilities.setCapability("browserstack.key", "GFNjp9AfYzBoFZkvucvS");
				capabilities.setCapability("os_version", getData("iosVersion"));
				capabilities.setCapability("device", getData("iOSDeviceName"));
				capabilities.setCapability("real_mobile", "true");
				capabilities.setCapability("project", "DestinetDiscover-BuildNi-1.3.0(23)");
				capabilities.setCapability("name", "Test : " + date);
				capabilities.setCapability("app", getData("iosAppKey"));
				capabilities.setCapability("browserstack.appium_version", "1.18.0");
				capabilities.setCapability("browserstack.local", "false");
				capabilities.setCapability("locale", "India");
				driver = new IOSDriver<MobileElement>(new URL("http://hub.browserstack.com/wd/hub"), capabilities);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Browser_Launch(String browser) throws IOException {

		if (browser.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
		} else if (browser.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			driver = new FirefoxDriver(options);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
		} else if (browser.contains("safari")) {
			SafariOptions options = new SafariOptions();
			driver = new SafariDriver(options);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
		} else if (browser.contains("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
		}
	}

	public static void Browserstack_web(String data) throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("os_version", "Big Sur");
		capabilities.setCapability("resolution", "1920x1080");
		capabilities.setCapability("browser", data);
		capabilities.setCapability("browser_version", "latest");

		capabilities.setCapability("os", "OS X");
		capabilities.setCapability("name", "BStack-[Java] Sample Test");
		capabilities.setCapability("build", "Dlx environment build ");

		capabilities.setCapability("browserstack.local", "true");
		driver = new RemoteWebDriver(new URL(URL), capabilities);
	}

	public static void local_instance_Browserstack() throws Exception {

		// creates an instance of Local
		Local bsLocal = new Local();

		// replace <browserstack-accesskey> with your key. You can also set an
		// environment variable - "BROWSERSTACK_ACCESS_KEY".
		HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
		bsLocalArgs.put("key", AUTOMATE_ACCESS_KEY);

		// starts the Local instance with the required arguments
		bsLocal.start(bsLocalArgs);
		bsLocalArgs.put("forcelocal", "true");
		// bsLocalArgs.put("proxyHost", "127.0.0.1");
		// bsLocalArgs.put("proxyPort", "9000");

		// check if BrowserStack local instance is running
		System.out.println(bsLocal.isRunning());

		// stop the Local instance
		// bsLocal.stop();
	}

	public void startTimer() {
		watch = StopWatch.createStarted();
	}

	public long stopTimer() {
		watch.stop();
		return watch.getTime(TimeUnit.MILLISECONDS);
	}

	public static boolean fluentWaitDisplayed(MobileElement element, long t, long ts) {
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(t))
					.pollingEvery(Duration.ofMillis(ts)).ignoring(Exception.class);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isToastMessageDisplayed(String message) {
		boolean isDisplayed = false;
		int count = 0;
		do {
			if (driver.getPageSource().contains(message)) {
				isDisplayed = true;
				break;
			}
			count++;
		} while (count < 10);
		return isDisplayed;
	}

	public static void keyBoardSearch() {
		driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));

	}

	public static void androidKeyBack() {
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.BACK));
	}

	public static void swipeDown() {

		Dimension dimension = driver.manage().window().getSize();
		int scrollStart = (int) (dimension.getHeight() * 0.5);
		int scrollEnd = (int) (dimension.getHeight() * 0.2);
		new TouchAction((PerformsTouchActions) driver).press(PointOption.point(0, scrollStart))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(0, scrollEnd))
				.release().perform();

	}

	public static void scrollAndClick(By listItems, String Text) throws InterruptedException {
		boolean flag = false;
		while (true) {
			for (WebElement el : driver.findElements(listItems)) {
				if (el.getText().equals(Text)) {
					el.click();
					flag = true;
					break;
				} else {
					swipeDown();
				}
			}
		}
	}

	public static void horizontalSwipe(List<MobileElement> e) {
		MobileElement firdelement = e.get(0);
		MobileElement secondElement = e.get(1);
		MobileElement thirdElement = e.get(2);

		int midOfY = thirdElement.getLocation().y + (thirdElement.getSize().height / 2);
		int fromXLocation = thirdElement.getLocation().x;
		int toXLocation = firdelement.getLocation().x;

		TouchAction action = new TouchAction((PerformsTouchActions) driver);
		action.press(PointOption.point(fromXLocation, midOfY))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
				.moveTo(PointOption.point(toXLocation, midOfY)).release().perform();

	}

	public static void horizontalSwipeAndriod(List<MobileElement> e) {

		MobileElement firdelement = e.get(0);
		MobileElement secondElement = e.get(1);
//		MobileElement thirdElement = e.get(2);
		int midOfY = secondElement.getLocation().y + (secondElement.getSize().height / 2);
		int fromXLocation = secondElement.getLocation().x;
		int toXLocation = firdelement.getLocation().x;
		TouchAction action = new TouchAction((PerformsTouchActions) driver);
		action.press(PointOption.point(fromXLocation, midOfY))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
				.moveTo(PointOption.point(toXLocation, midOfY)).release().perform();

	}

	public static boolean hidemobileKeyboard(AndroidDriver app) {
		boolean isKeyboardHideResult = false;
		try {
			app.hideKeyboard();
			isKeyboardHideResult = true;
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return isKeyboardHideResult;
	}

	public static void hideMobileKeyboard() {
		if (platformName.equalsIgnoreCase("android")) {
			hidemobileKeyboard((AndroidDriver) driver);
		} else {
			int isPresent = driver.findElements(By.xpath("//XCUIElementTypeButton[@name='Done']")).size();
			if (isPresent == 1) {
				driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Done']")).click();
			}
		}
	}

	public void ScrollTo(String Scroll, String direction, MobileElement element) {
		if ("ANDROID".equalsIgnoreCase(platformName)) {
			driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(" + Scroll
							+ ").instance(0))"));
			waitFor(1000);
			impWait(elementWaitInSeconds);
		} else if ("IOS".equalsIgnoreCase(platformName)) {
			for (int i = 0; i < 5; i++) {
				boolean isElementPresent = element.isDisplayed();
				if (!isElementPresent) {
					Map<String, Object> args = new HashMap<>();
					args.put("direction", direction);
					driver.executeScript("mobile: swipe", args);
				} else {
					break;
				}
			}
		}
	}

	public void ScrollToMobileElement(String Scroll) {
		MobileElement element = (MobileElement) driver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).instance(0)"
						+ ".scrollIntoView(new UiSelector().text(" + Scroll + "))"));
		waitFor(1000);
		element.click();
		impWait(elementWaitInSeconds);
	}

	public static void waitFor(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static boolean isSelected(MobileElement e) {
		boolean b = e.isSelected();
		return b;
	}

	public static boolean isEnabled(MobileElement e) {
		boolean result = e.isEnabled();
		return result;
	}

	public static boolean isDisplayed(MobileElement e) {
		boolean d = e.isDisplayed();
		return d;
	}

	public static void loadData() throws IOException {
		props = new Properties();
		File file = new File(System.getProperty("user.dir") + "/Configs/Configuration.properties");
		FileReader fileInput = new FileReader(file);
		props.load(fileInput);
	}

	public static String getData(String data) throws IOException {
		loadData();
		data = props.getProperty(data);
		return data;
	}

	public static void impWait(long t) {

		driver.manage().timeouts().implicitlyWait(t, TimeUnit.SECONDS);
	}

	public String RandomStringGenerate() {
		return RandomStringUtils.randomAlphanumeric(20);
	}

	public static boolean text_Validation(String objectProperty, String expectedText) {
		boolean Textvalidation = false;
		try {
			element = getElementByProperty(objectProperty, driver);
			String actualText = element.getText().replace('\n', ' ');
			if (actualText.equalsIgnoreCase(expectedText)) {
				Textvalidation = true;
				// System.out.println("Text expected and actual text are Same:" + actualText);
			} else {
				System.out.println(" Text expected and actual text are not Same:");
				System.out.println(" Text - Actual : " + actualText);
				System.out.println(" Text -Expected : " + expectedText);

			}
		} catch (Exception e) {

		} finally {
		}
		return Textvalidation;
	}

	public static WebElement getElementByProperty(String objectProperty, WebDriver webDriver) {
		element = null;

		String propertyType = null;
		WebDriverWait browserWithElementWait = null;
		try {

			browserWithElementWait = new WebDriverWait(webDriver, 30);
			propertyType = StringUtils.substringAfter(objectProperty, "~");
			objectProperty = StringUtils.substringBefore(objectProperty, "~");
			if (propertyType.equalsIgnoreCase("CSS")) {
				element = browserWithElementWait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(objectProperty)));
				highlightElement(element, webDriver);
			} else if (propertyType.equalsIgnoreCase("ID")) {
				element = browserWithElementWait
						.until(ExpectedConditions.visibilityOfElementLocated((By.id(objectProperty))));
				// highlightElement(webElement, browser);
			} else if (propertyType.equalsIgnoreCase("NAME")) {
				element = browserWithElementWait
						.until(ExpectedConditions.visibilityOf(driver.findElement(By.name(objectProperty))));
				highlightElement(element, webDriver);
			} else if (propertyType.equalsIgnoreCase("LINKTEXT")) {
				element = browserWithElementWait
						.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(objectProperty))));
				highlightElement(element, webDriver);
			} else if (propertyType.equalsIgnoreCase("CLASS")) {
				element = browserWithElementWait
						.until(ExpectedConditions.visibilityOf(driver.findElement(By.className(objectProperty))));
				highlightElement(element, webDriver);
			} else {
				element = browserWithElementWait
						.until(ExpectedConditions.visibilityOfElementLocated((By.ByXPath.xpath(objectProperty))));

			}
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {

		}

		return element;
	}

	public static void highlightElement(WebElement element, WebDriver webDriver) {
		for (int i = 0; i < 1; i++) {
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: black; border: 3px solid black;");
		}
	}

	public static String getMainWindow() throws Throwable {
		String mainWindow = null;
		try {
			mainWindow = driver.getWindowHandle();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mainWindow;
	}

	public static void switchToMainWindow() throws Throwable {
		// killChromeSession();
		try {
			driver.switchTo().window(mainWindow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void switchToNewWindow() throws Throwable {
		// killChromeSession();
		try {
			Set<String> windowNames = driver.getWindowHandles();
			for (String windowName : windowNames) {

				if (windowName != null) {
					driver.switchTo().window(windowName);
				} else {
					throw new Exception("New window could not be retrived");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean isElementEnabled(String objectProperty, String elementState) throws Exception {
		boolean isElementEnable = false;
		try {
			element = getElementByProperty(objectProperty, driver);
			String att = element.getAttribute("enabled");
			if (elementState.equals("enabled")) {
				if (att.equals("true")) {
					isElementEnable = true;
					System.out.println("Element is :" + elementState);
				} else {
					throw new Exception("Given Element is avilable");
				}
			}

			if (elementState.equals("disabled")) {
				if (att.equals("false")) {
					isElementEnable = true;
					System.out.println("Element is :" + elementState);
					// t2=System.currentTimeMillis();
				} else {
					throw new Exception("Given Element is avilable");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return isElementEnable;

	}

	public static String RandomStringGenerate(int count) {

		return RandomStringUtils.randomAlphanumeric(count);

	}

	public static boolean isElementSelected(String objectProperty) throws Exception {
		boolean isElementSelected = false;
		try {
			element = getElementByProperty(objectProperty, driver);
			if (element.isSelected()) {
				isElementSelected = true;
			} else {
				throw new Exception("Given Element not selected");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return isElementSelected;

	}

	public static void javascriptScroll(WebElement element) {
		waitFor(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: lightskyblue; border: 2px solid red;');",
				element);
		waitFor(2000);
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		js.executeScript("arguments[0].scrollIntoView();", element);

	}

	public static void swipeUp() {

		Dimension dimension = driver.manage().window().getSize();
		int scrollStart = (int) (dimension.getHeight() * 0.20);
		int scrollEnd = (int) (dimension.getHeight() * 0.80);
		new TouchAction((PerformsTouchActions) driver).press(PointOption.point(2, scrollStart))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(2, scrollEnd))
				.release().perform();
	}

	public static void swipeleft(MobileElement e) {
		Dimension dimension = driver.manage().window().getSize();
		int scrollStart = (int) (dimension.getWidth() * 0.8);
		int scrollEnd = (int) (dimension.getWidth() * 0.4);
		int y = (int) (dimension.getHeight() / 2);
		new TouchAction((PerformsTouchActions) driver).press(PointOption.point(scrollStart, y))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(scrollEnd, y))
				.release().perform();
	}
	
	public static void swipeDown1() {
		Dimension dimension = driver.manage().window().getSize();
		int scrollStart = (int) (dimension.getHeight() * 0.20);
		int scrollEnd = (int) (dimension.getHeight() * 0.35);
		new TouchAction((PerformsTouchActions) driver).press(PointOption.point(0, scrollStart))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(0, scrollEnd))
				.release().perform();
	}
	
	/****************************************** Mobile driver methods***********************************************************/

	public static void ClickOnMobileElement(MobileElement element) {
		MobileElement mobelement = (MobileElement) exwait.until(ExpectedConditions.visibilityOf(element));
		waitFor(2000);
		// js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
		// mobelement, "");
		mobelement.click();
	}

	public static void SendKeysOnMobileElement(MobileElement element, String Value) {
		MobileElement mobelement = (MobileElement) exwait.until(ExpectedConditions.visibilityOf(element));
		mobelement.clear();
		mobelement.sendKeys(Value);
	}

	public static void SendKeysOnMobileElementList(MobileElement mobileElement, String Value) {
		MobileElement mobelement = (MobileElement) exwait
				.until(ExpectedConditions.visibilityOf((WebElement) mobileElement));
		// mobelement.clear();
		mobelement.sendKeys(Value);
	}

	public static void SendKeysWithoutClear(MobileElement element, String Value) {
		MobileElement mobelement = (MobileElement) exwait.until(ExpectedConditions.visibilityOf(element));
		mobelement.sendKeys(Value);
	}

	public static void WaitForMobileElement(MobileElement element) {
		exwait.until(ExpectedConditions.visibilityOf(element));

	}

	public static void WaitForMobileElementList(List<MobileElement> title_MoreOptions) {
		exwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By) title_MoreOptions));

	}

	public static void TouchActionClick(MobileElement element) throws InterruptedException {
		MobileElement mobelement = (MobileElement) exwait.until(ExpectedConditions.visibilityOf(element));
		TouchActions act = new TouchActions(driver);
		act.click(mobelement).build().perform();

	}

	public static boolean IsDisplayedMobileElement(MobileElement element) {
		MobileElement mobelement = (MobileElement) exwait.until(ExpectedConditions.visibilityOf(element));
		return mobelement.isDisplayed();

	}

	public static void changeDriverContextToWeb(AppiumDriver driver) {
		Set<String> allContext = driver.getContextHandles();
		for (String context : allContext) {
			if (context.contains("WEBVIEW"))
				driver.context(context);
		}
	}

	public static void changeDriverContextToNative(AppiumDriver driver) {
		Set<String> allContext = driver.getContextHandles();
		for (String context : allContext) {
			if (context.contains("NATIVE"))
				driver.context(context);
		}

	}

	public void IosButtonClick(String string) {
		waitFor(6000);
		MobileElement element = (MobileElement) driver
				.findElementByXPath("//XCUIElementTypeButton[@name='" + string + "']");
		element.click();
	}

	public static void ClickUsingXandYCords(MobileElement element) {
		waitFor(3000);
		Point point = element.getLocation();
		int xcord = point.getX();
		int ycord = point.getY();
		// System.out.println("WebElement of :" + element + "X cord is :" +
		// point.getX());
		// System.out.println("WebElement of :" + element + "Y cord is :" +
		// point.getY());
		new TouchAction((PerformsTouchActions) driver).tap(PointOption.point(xcord, ycord)).perform();
	}

	public static String challengeName() {

		String asciiUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String asciiLowerCase = asciiUpperCase.toLowerCase();
		String digits = "1234567890";
		String asciiChars = asciiUpperCase + asciiLowerCase + digits;
		String randomString = "";
		StringBuilder sb = new StringBuilder();
		int i = 0;
		Random rand = new Random();
		while (i < 2) {
			randomString = randomString + sb.append(asciiChars.charAt(rand.nextInt(asciiChars.length())));
			i++;
		}
		ChallengeName = "Automation Challenge " + randomString;
		System.out.println("Challenge Name entered is : " + ChallengeName);
		return ChallengeName;

	}

	public static void MobDragAndDrop(MobileElement element1, MobileElement element2) {
		Actions act = new Actions(driver);
		act.clickAndHold(element1).moveToElement(element2).release().build().perform();

	}

	/*********************************************** Web driver methods *********************************************************/

	public static void ClickOnWebElement(WebElement element) {
		exwait = new WebDriverWait(driver, 60);
		WebElement webelement = exwait.until(ExpectedConditions.visibilityOf(element));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: lightskyblue; border: 2px solid red;');",
				webelement);
		waitFor(2000);
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webelement, "");
		webelement.click();
	}

	public static void SendKeysOnWebElement(WebElement element, String Value) {
		exwait = new WebDriverWait(driver, 60);
		WebElement webelement = exwait.until(ExpectedConditions.visibilityOf(element));
		webelement.clear();
		webelement.sendKeys(Value);
	}

	public static void WaitForWebElement(WebElement element) {
		exwait = new WebDriverWait(driver, 60);
		exwait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void WebActionClick(WebElement element) throws InterruptedException {
		exwait = new WebDriverWait(driver, 60);
		WebElement mobelement = exwait.until(ExpectedConditions.visibilityOf(element));
		Actions act = new Actions(driver);
		act.click(mobelement).build().perform();
	}

	public static boolean IsDisplayedWebElement(WebElement element) {
		exwait = new WebDriverWait(driver, 60);
		WebElement webelement = exwait.until(ExpectedConditions.visibilityOf(element));
		return webelement.isDisplayed();
	}

	public static void waitForPageLoaded(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForElementClick(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement webelement = wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public static void waitForElementDisplayed(WebElement element) {
		WebElement dynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOf(element));
	}

	public static void javaScriptScrollToEnd() {
		waitFor(2000);

		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void jsClick(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement webelement = wait.until(ExpectedConditions.visibilityOf(element));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: lightskyblue; border: 2px solid red;');",
				webelement);
		waitFor(2000);
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webelement, "");
		js.executeScript("arguments[0].click();", webelement);
	}

	public static void switchToIframe(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement webelement = wait.until(ExpectedConditions.visibilityOf(element));
		driver.switchTo().defaultContent();
		driver.switchTo().frame(webelement);
	}

	public static void mouseHoverWeb(WebElement element) throws InterruptedException {
		// waitFor(4000);
		exwait = new WebDriverWait(driver, 60);
		WebElement webelement = exwait.until(ExpectedConditions.visibilityOf(element));
		Actions act = new Actions(driver);
		act.moveToElement(webelement).contextClick(webelement).build().perform();
		waitFor(2000);

	}

	public static void SendKeysEnter(WebElement element) {
		exwait = new WebDriverWait(driver, 60);
		WebElement webelement = exwait.until(ExpectedConditions.visibilityOf(element));
		webelement.clear();
		webelement.sendKeys(Keys.ENTER);

	}

	public boolean ElementisPresent(WebElement e)

	{
		waitFor(5000);
		boolean flag = true;
		try {
			e.isDisplayed();
			flag = true;
		} catch (Exception a) {
			flag = false;
		}
		return flag;
	}
}