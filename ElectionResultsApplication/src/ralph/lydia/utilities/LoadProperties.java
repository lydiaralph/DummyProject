package ralph.lydia.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadProperties {

	public static String getKeyValue(String tagName){
		return loadProperties().getProperty(tagName);
	}

	private static Properties loadProperties() {
		try{
			Properties properties = new Properties();

			FileInputStream in = new FileInputStream("config.properties");
			properties.load(in);
			in.close();
			return properties;
		}
		catch(IOException e){
			System.out.println("Failed to read from properties file: " + e.getMessage());
			return null;
		}

	}
}
