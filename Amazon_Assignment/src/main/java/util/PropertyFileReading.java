package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReading {

	public static Properties prop = null;
	public static File file = null;
	public static FileInputStream fis = null;
	static Properties properties;

	public PropertyFileReading() {
		{
			file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\configurations.properties");
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			prop = new Properties();
			try {
				prop.load(fis);
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

	public String getDriverPath() {
		// TODO Auto-generated method stub
		String driverPath = getProperty("driverPath");
		if (driverPath != null)
			return driverPath;
		else
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
	}

	public static long getImplicitlyWait() {
		String implicitlyWait = getProperty("implicitlyWait");
		if (implicitlyWait != null)
			return Long.parseLong(implicitlyWait);
		else
			throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
	}
}
