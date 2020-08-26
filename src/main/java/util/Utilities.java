package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Utilities {

	public static Properties readProperties(String path) throws IOException {
		Properties prop = new Properties();
		File file = new File(path);
		if (file.exists()) {
			FileInputStream property = new FileInputStream(file);
			prop.load(property);
		}
		return prop;
	}

	public static String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

}
