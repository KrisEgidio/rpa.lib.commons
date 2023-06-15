package lib.commons.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 
 * Class used to manipulate files and directories
 * 
 * @author Kris
 *
 */

public class CommonFiles {

	private static final Logger log = LoggerFactory.getLogger(CommonFiles.class);

	/***
	 * Move any file using source Path (inputPath) and Destiny Path (outputPath),
	 * and REPLACE_EXISTING as standardCopyOption
	 * 
	 * @param inputPath
	 * @param outputPath
	 */

	public static void moveFile(String inputPath, String outputPath) {
		try {
			Files.move(Paths.get(inputPath), Paths.get(outputPath), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			log.error("Falha ao mover o arquivo: " + e.getMessage());
		}
	}

}
