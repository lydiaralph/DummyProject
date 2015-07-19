package ralph.lydia.parser;

import org.xml.sax.Attributes;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ralph.lydia.results.ResultModel;

import java.util.ArrayList;
import java.util.Stack;
import java.lang.IllegalArgumentException;

public class DefaultHandlerExt extends DefaultHandler{

	String elementName;

	//This is the list which shall be populated while parsing the XML.
	private ArrayList<ResultModel> resultsList = new ArrayList<ResultModel>();

	// Push any XML elements into the stack
	private Stack<String>  elementStack = new Stack<String> ();

	// As we complete one ResultModel block in XML, we will push the ResultModel instance into list
	private Stack<ResultModel> objectStack = new Stack<ResultModel> ();

	@Override
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

		this.elementStack.push(qName);

		//If this is start of 'result' element then prepare a new ResultModel instance and push it in object stack
		if ("result".equals(qName)){
			ResultModel result = new ResultModel();

			this.objectStack.push(result);
		}
	}

	@Override
	public void endElement(String uri, String localName,
			String qName) throws SAXException {

		//Remove last added  element
		this.elementStack.pop();

		// ResultModel instance has been constructed so pop it from object stack and push in resultsList
		if ("result".equals(qName)){
			ResultModel object = this.objectStack.pop();
			this.resultsList.add(object);
		}
	}

	@Override
	public void characters(char ch[], int start, int length) 
			throws SAXException{

		String elementName = currentElement();

		/** Need to trim whitespace */
		String value = new String(ch, start, length).trim();

		if(value.isEmpty()){
			// TODO: IMPROVE THIS
			// Nasty hack. I don't like throwing SAXException for what should be FileValidatorException
			throw new SAXException("Cannot accept empty value for " + elementName + " in XML file");
		}

		if (elementName.equals("partyCode")){
			ResultModel result = (ResultModel) this.objectStack.peek();
			try{
				result.setPartyCode(value);
			}
			catch(IllegalArgumentException e){
				System.out.println("Did not find a party code to match " + value + ". Setting PartyCode as 'OTH'...");
				result.setPartyCode("OTH");
			}
		} else if (elementName.equals("votes")){
			ResultModel result = (ResultModel) this.objectStack.peek();
			result.setVotes(Integer.parseInt(value)); 
		} else if (elementName.equals("share")){
			ResultModel result = (ResultModel) this.objectStack.peek();
			result.setShare(Float.parseFloat(value));
		}
	}

	private String currentElement(){
		return this.elementStack.peek();
	}

    public ArrayList<ResultModel> getResultsList()
    {
        return resultsList;
    }


}
