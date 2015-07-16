package ralph.lydia.processor;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.File;

import ralph.lydia.utilities.LoadProperties;

public class FileValidatorTest {

	@Test
	public void testValidXMLData() throws Exception {
		String testFileName = "result001.xml";
		
		FileValidator fileValidator = new FileValidator();
		
		File f = new File(loadXmlFile(testFileName));
		
		assertTrue(fileValidator.getFailureMessage(), fileValidator.validateXmlFile(f));
	}

	@Test
	public void testInvalidXMLData() throws Exception {
		String testFileName = "result001invalid.xml";
		
		FileValidator fileValidator = new FileValidator();
		
		File f = new File(loadXmlFile(testFileName));
		
		assertFalse("Expected file validator to reject invalid file", fileValidator.validateXmlFile(f));
	}
	
	private String loadXmlFile(String fileName){
		return LoadProperties.getKeyValue("XML_INBOUND") + fileName;
	}
	
}