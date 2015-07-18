package ralph.lydia.utilities;

public class LoadFilesFromTestInbound {
	/**
	 * Helper method for testing, not used in program 
	 * @param fileName
	 * @return absolute path for file from config.properties
	 */
	public static String loadXmlFile(String fileName){
		return LoadProperties.getKeyValue("XML_TEST_INBOUND") + fileName;
	}
}
