package ralph.lydia.processor;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.File;

import ralph.lydia.utilities.LoadFilesFromTestInbound;

public class FileValidatorTest {

	@Test
	public void testValidXMLData() {
		String testFileName = "validResult.xml";
		
		FileValidator fileValidator = new FileValidator();
		
		File f = new File(LoadFilesFromTestInbound.loadXmlFile(testFileName));
		
		try{
			assertTrue(fileValidator.getFailureMessage(), fileValidator.validateXmlFile(f));
		} catch(FileValidatorException e){
			fail(e.getMessage());
		}
	}

	@Test(expected=FileValidatorException.class)
	public void testInvalidXMLData() throws FileValidatorException{
		String testFileName = "invalidResult.xml";
		
		FileValidator fileValidator = new FileValidator();
		
		File f = new File(LoadFilesFromTestInbound.loadXmlFile(testFileName));
	
		assertFalse("Expected file validator to reject invalid file", 
				fileValidator.validateXmlFile(f));
	
	}
	
	@Test(expected=FileValidatorException.class)
	public void testBlankXML() throws FileValidatorException{
		String testFileName = "blank.xml";
		
		FileValidator fileValidator = new FileValidator();
		
		File f = new File(LoadFilesFromTestInbound.loadXmlFile(testFileName));
		
		assertTrue(fileValidator.getFailureMessage(), fileValidator.validateXmlFile(f));
	}
}