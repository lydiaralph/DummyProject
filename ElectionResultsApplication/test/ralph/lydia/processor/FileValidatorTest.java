package ralph.lydia.processor;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class FileValidatorTest {
	
	File xmlFile;
	
	@Test
	public void testValidXMLData() throws Exception {
		FileValidator fileValidator = new FileValidator();
		try{
			loadXmlFile("valid.xml");
		}
		catch(FileValidatorException e){
			fail(e.getMessage());
		}
		assertTrue(fileValidator.getFailureMessage(), fileValidator.validateXmlFile(xmlFile));
	}
	
	@Test
	public void testInvalidXMLData() throws Exception {
		FileValidator fileValidator = new FileValidator();
		try{
			loadXmlFile("invalid.xml");
		}
		catch(FileValidatorException e){
			fail(e.getMessage());
		}
		assertTrue(fileValidator.getFailureMessage(), fileValidator.validateXmlFile(xmlFile));
	}


	private void loadXmlFile(String fileName) throws FileValidatorException{
		try{
			URL xmlFileURL = Thread.currentThread().getContextClassLoader()
					.getResource(fileName);
			
			this.xmlFile = new File(xmlFileURL.toURI());
		}
		catch(URISyntaxException e){
			throw new FileValidatorException("Syntax error: " + e.getMessage());
		}
		catch(NullPointerException e){
			throw new FileValidatorException("Could not retrieve XML file: filepath was " + e.getMessage());
		}
	} 
}
