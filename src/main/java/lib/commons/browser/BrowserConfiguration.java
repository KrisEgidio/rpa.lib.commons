package lib.commons.browser;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;

/***
 * 
 * Creates and return a new chromeOptions object with some standard configurations for robot performance
 * 
 * @author Kris
 *
 */
public class BrowserConfiguration {
	public static ChromeOptions getChromeOptions() {

		ChromeOptions options = new ChromeOptions();

		options.addArguments("--enable-automation");
		options.addArguments("--start-maximized");
		options.addArguments("--no-sandbox");
		options.addArguments("--dns-prefetch-desable");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--incognito");
		options.addArguments("--enable-precise-memory-info");
		options.addArguments("--disable-default-apps");
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-gpu");
		options.addArguments("--disable-infobars");
		
		Map<String, Object> chromePreferences = new HashMap<String, Object>() {
			private static final long serialVersionUID = 1L;
			{
				put("profile.managed_default_content_settings.popups", 2);
				put("profile.managed_default_content_settings.notifications", 2);
				put("profile.managed_default_content_settings.cookies", 2);
				put("profile.managed_default_content_settings.images", 2);
				put("profile.managed_default_content_settings.stylesheets", 2);
				put("profile.managed_default_content_settings.javascript", 0);
				put("profile.managed_default_content_settings.plugins", 2);
				put("profile.managed_default_content_settings.geolocation", 0);
				put("profile.managed_default_content_settings.media_stream", 0);
				put("download.default_directory", 0);
			}
		};

		options.setExperimentalOption("prefs", chromePreferences);

		return options;
	}	
}
