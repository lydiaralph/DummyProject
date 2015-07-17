package ralph.lydia.processor;

import application.PartyCode;
import ralph.lydia.results.ResultModel;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Modelled on {@link http://www.mkyong.com/java/how-to-read-xml-file-in-java-sax-parser mkyong.com} 
 * @author lydia
 *
 */

public class ReadXMLFile {
	
	public static List<ResultModel> readXmlFileAndParseContents(String filePath) 
			throws FileValidatorException {
		
		List<ResultModel> resultsList = new ArrayList<ResultModel>();
		
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {
				
				private String elementName;
				ResultModel result = new ResultModel();

				private void setElementName(String s){
					this.elementName = s;
				}
				
				private String getElementName(){
					return this.elementName;
				}
				
				@Override
				public void startElement(String uri, String localName,String qName, 
						Attributes attributes) throws SAXException {

					System.out.println("Start Element :" + qName);

					this.setElementName(qName);
					
//					if (qName.equalsIgnoreCase("partyCode")) {
//						partyCode = true;
//					}
//
//					if (qName.equalsIgnoreCase("votes")) {
//						votes = true;
//					}
//
//					if (qName.equalsIgnoreCase("share")) {
//						share = true;
//					}
				}

				@Override
				public void endElement(String uri, String localName,
						String qName) throws SAXException {

					System.out.println("End Element :" + qName);
				}

				@Override
				public void characters(char ch[], int start, int length) 
						throws SAXException{

					/** Need to trim whitespace */
					String value = new String(ch, start, length).trim();
					
					if(value.isEmpty()){
						// TODO: IMPROVE THIS
						// Nasty hack. I don't like throwing SAXException for what should be FileValidatorException
						throw new SAXException("Cannot accept empty value for " + this.getElementName() + " in XML file");
					}
					
					System.out.println("Setting value " + value + " as " + elementName);
					
					switch(elementName){
						case "partyCode":
							result.setPartyCode(value); break;
						
						case "votes":
							result.setVotes(Integer.parseInt(value)); break;
						
						case "share":
							result.setShare(Float.parseFloat(value)); break;
					}
					
					resultsList.add(this.result);
					
				}

			};

			try{
				saxParser.parse(filePath, handler);
			}
			catch(SAXException|IOException e){
				throw new FileValidatorException(e.getMessage());
				
			}

		} catch(ParserConfigurationException|SAXException|FileValidatorException e) {
			throw new FileValidatorException(e.getMessage());
		}
		return resultsList;
	}
	
}
