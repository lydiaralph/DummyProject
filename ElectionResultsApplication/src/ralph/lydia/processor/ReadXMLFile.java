package ralph.lydia.processor;

import application.PartyCode;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Map;
import java.util.HashMap;

/**
 * Modelled on {@link http://www.mkyong.com/java/how-to-read-xml-file-in-java-sax-parser mkyong.com} 
 * @author lydia
 *
 */

public class ReadXMLFile {
	//	private PartyCode partyCode;
	//	private int votes;
	//	private float share;

	public static void main(String argv[]) {

		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				boolean partyCode = false;
				boolean votes = false;
				boolean share = false;

				@Override
				public void startElement(String uri, String localName,String qName, 
						Attributes attributes) throws SAXException {

					System.out.println("Start Element :" + qName);

					if (qName.equalsIgnoreCase("partyCode")) {
						partyCode = true;
					}

					if (qName.equalsIgnoreCase("votes")) {
						votes = true;
					}

					if (qName.equalsIgnoreCase("share")) {
						share = true;
					}
				}

				@Override
				public void endElement(String uri, String localName,
						String qName) throws SAXException {

					System.out.println("End Element :" + qName);
				}

				@Override
				public void characters(char ch[], int start, int length) throws SAXException {

					if (partyCode) {
						System.out.println("Party code : " + new String(ch, start, length));
						partyCode = false;
					}

					if (votes) {
						System.out.println("Votes : " + new String(ch, start, length));
						votes = false;
					}

					if (share) {
						System.out.println("Share : " + new String(ch, start, length));
						share = false;
					}
				}

			};

			saxParser.parse("/home/lydia/Documents/TECH/ElectionResultsApplication/Inbound/result001.xml", handler);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
