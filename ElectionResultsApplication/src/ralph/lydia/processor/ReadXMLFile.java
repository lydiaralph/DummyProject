package ralph.lydia.processor;

import ralph.lydia.results.ResultModel;
import ralph.lydia.results.DefaultHandlerExt;

import java.util.List;
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
	
	public static List<ResultModel> readXmlFileAndParseContents(String filePath) 
			throws FileValidatorException {
		
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandlerExt handler = new DefaultHandlerExt();

			try{
				saxParser.parse(filePath, handler);
			} catch(SAXException|IOException e){
				throw new FileValidatorException(e.getMessage());
			}

			return handler.getResultsList();
		} catch(ParserConfigurationException|SAXException|FileValidatorException e) {
			throw new FileValidatorException(e.getMessage());
		}
	}
}
