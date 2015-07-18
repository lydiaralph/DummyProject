package ralph.lydia.processor;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.File;

import ralph.lydia.utilities.LoadProperties;

public class FileValidatorTest {

	@Test
	public void testValidXMLData() throws Exception {
		String testFileName = "validResult.xml";
		
		FileValidator fileValidator = new FileValidator();
		
		File f = new File(loadXmlFile(testFileName));
		
		assertTrue(fileValidator.getFailureMessage(), fileValidator.validateXmlFile(f));
	}

	@Test(expected=FileValidatorException.class)
	public void testInvalidXMLData() throws Exception {
		String testFileName = "invalidResult.xml";
		
		FileValidator fileValidator = new FileValidator();
		
		File f = new File(loadXmlFile(testFileName));
		
		assertFalse("Expected file validator to reject invalid file", fileValidator.validateXmlFile(f));
	}
	
	
	// Should not be in this file?
	@Test
	public void testValidateXmlFromFileLoader(){
			FileLoader loader = new FileLoaderImpl();
			FileValidator fileValidator = new FileValidator();
			try{
				assertTrue(fileValidator.validateXmlFile(loader.loadNextFile()));
				assertTrue(fileValidator.getFailureMessage().isEmpty());
			} catch (NoFilesToProcessException|FileValidatorException e){
				fail(e.getMessage());
			}
	}
	
	/**
	 * Helper method for testing, not used in program 
	 * @param fileName
	 * @return absolute path for file from config.properties
	 */
	private String loadXmlFile(String fileName){
		return LoadProperties.getKeyValue("XML_TEST_INBOUND") + fileName;
	}
	
}