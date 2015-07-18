package ralph.lydia.parser;

import ralph.lydia.processor.FileValidatorException;
import ralph.lydia.results.ConstituencyResult;
import ralph.lydia.parser.ConstitResultHandler;

import java.io.IOException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * Modelled on {@link http://www.mkyong.com/java/how-to-read-xml-file-in-java-sax-parser mkyong.com}
 *
 * Also modelled on {@link http://howtodoinjava.com/2012/12/18/how-to-parse-an-xml-using-sax-parser-and-defaulthandler/ howtodoinjava.com}
 * @author lydia.ralph
 *
 */

public class ReadXMLFile {
	
	public static ConstituencyResult readXmlFileAndParseContents(String filePath) 
			throws FileValidatorException {
		
		try {
			ConstituencyResult constitResult = new ConstituencyResult();
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			ConstitResultHandler constitResultHandler = new ConstitResultHandler();
			DefaultHandlerExt handler = new DefaultHandlerExt();
			
			try{
				saxParser.parse(filePath, constitResultHandler);
				saxParser.parse(filePath, handler);
			} catch(SAXException|IOException e){
				throw new FileValidatorException(e.getMessage());
			}
			
			constitResult = constitResultHandler.getConstituencyResult();
			constitResult.setResultList(handler.getResultsList());
			return constitResult;
		} catch(ParserConfigurationException|SAXException|FileValidatorException e) {
			throw new FileValidatorException(e.getMessage());
		}
	}
}
