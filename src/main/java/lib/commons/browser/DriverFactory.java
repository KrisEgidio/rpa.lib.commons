package lib.commons.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/***
 * 
 * Creates an instance of a chromedriver configured with chromeOptions and implements autoCloseable class
 * 
 * @author Kris
 *
 */


public class DriverFactory implements AutoCloseable {

	private WebDriver driver;

	public DriverFactory() {
		driver = new ChromeDriver(BrowserConfiguration.getChromeOptions());
	}

	public DriverFactory(ChromeOptions options) {
		driver = new ChromeDriver(options);
	}

	/***
	 * 
	 * Return an chromedriver instance created by DriverFactory constructor
	 * 
	 * @return WebDriver
	 */
	public WebDriver getChromeDriver() {
		return driver;
	}

	
	/***
	 * 
	 * Closes all driver windows and quit chromedriver
	 * 
	 */
	public void close() throws Exception {
		driver.close();
		driver.quit();
	}
}
