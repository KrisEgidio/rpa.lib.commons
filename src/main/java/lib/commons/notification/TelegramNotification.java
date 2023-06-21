package lib.commons.notification;

import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 
 * Send telegram notification for a unique CHAT_ID
 * 
 * @author Kris
 *
 */

public class TelegramNotification {

	private static final Logger log = LoggerFactory.getLogger(TelegramNotification.class);
	private static final String BREAK_LINE = "%0A";
	private static final String BOT_ID = "6016949934:AAHWvE877ijnDYWRLhMCq5KXJx8nB2jxir4";
	private static final String CHAT_ID = "-1001931266901";

	/***
	 * 
	 * Send the telegram notification with message passed by param
	 * 
	 * @param message
	 */
		
	public static void sendMessage(String message) {
		StringBuilder urlRequest = new StringBuilder()
				.append("https://api.telegram.org/bot")
				.append(BOT_ID)
				.append("/sendMessage?")
				.append("chat_id=")
				.append(CHAT_ID)
				.append("&text=")
				.append(message.replace("BREAK_LINE", BREAK_LINE))
				.append("&parse_mode=HTML");
		
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(urlRequest.toString()).openConnection();
			
			if(connection.getResponseCode() == 200) {
				log.info("Message sent with success");
			} else {
				log.warn("Failed trying to send request: " + connection.getResponseMessage());
				log.warn("Status Code: " + connection.getResponseCode());
				log.warn("URL: " + urlRequest.toString());
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}	
}
