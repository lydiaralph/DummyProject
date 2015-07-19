package ralph.lydia.processor;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileValidator {

	//	ErrorReporter errorReporter;

	private String errorString = " was not valid against the schema.";
	private String validString = " was valid.";

	private String failureMessage;

	/**
	 * Takes file and checks it against schema. 
	 *  
	 * @param xmlFile
	 * @return boolean
	 * @throws FileValidatorException
	 */
	public boolean validateXmlFile(File xmlFile) throws FileValidatorException{

		Source xmlFileSource = new StreamSource(xmlFile);
		try{
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(loadXSD());

			Validator validator = schema.newValidator();
			validator.validate(xmlFileSource);
			return true;
		} catch (SAXException e) {
			this.setFailureMessage("ERROR: " + xmlFile.getName() + errorString);
			this.appendFailureMessage("\nReason: " + e.getMessage());
			throw new FileValidatorException(this.getFailureMessage());
		}
		catch (IOException e){
			this.setFailureMessage("Could not analyse xmlFileSource " + xmlFileSource);
			this.appendFailureMessage("\nReason: " + e.getMessage());
			throw new FileValidatorException(this.getFailureMessage());
		}
		catch(FileValidatorException e){
			throw new FileValidatorException(e.getMessage());
		}
	}

	private void setFailureMessage(String message){
		this.failureMessage = message;
	}

	public String getFailureMessage(){
		return this.failureMessage;
	}

	private void appendFailureMessage(String message){
		this.setFailureMessage(this.failureMessage + message);
	}

	
	/** 
	 * @see http://stackoverflow.com/a/15203740/2294676
	 * @return
	 * @throws FileValidatorException
	 */
	private static Source loadXSD() throws FileValidatorException {
		try{
			FileInputStream in = new FileInputStream("ElectionResultXmlSchema.xsd");
			Source xsd = new StreamSource(in);
			return xsd;
		} catch(FileNotFoundException e){
			throw new FileValidatorException("Didn't find a schema file: " + e.getMessage());
		}
	} 
}
