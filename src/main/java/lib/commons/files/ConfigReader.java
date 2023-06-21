package lib.commons.files;

import static java.util.Objects.isNull;

import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigReader {

	private static final Logger log = LoggerFactory.getLogger(ConfigReader.class);
	private static String configPath;
	private static Properties properties;
	
	ConfigReader(String configPath){
		ConfigReader.configPath = configPath;
	}
	
	public static String getProperty(String key) {
		if(isNull(properties)) {
			log.info("Properties is null, loading file");
			loadFile();
		}
		
		String value = properties.getProperty(key);
		return isNull(value) ? "Key not found!" : value;
	}
	
	public static void loadFile() {
		try (FileInputStream fileInput = new FileInputStream(configPath)) {
			properties = new Properties();
			properties.load(fileInput);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
}