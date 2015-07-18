package ralph.lydia.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ralph.lydia.results.ConstituencyResult;

import java.util.Stack;


public class ConstitResultHandler extends DefaultHandler{

	String elementName;
	
	//This is the list which shall be populated while parsing the XML.
	private ConstituencyResult constituencyResult = new ConstituencyResult();

	// Push any XML element into this stack
	private Stack<String> elementStack = new Stack<String> ();

	private Stack<ConstituencyResult> objectStack = new Stack<ConstituencyResult> ();

	@Override
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

		this.elementStack.push(qName);

		if ("constituencyResult".equals(qName)){
			ConstituencyResult result = new ConstituencyResult ();

			// Set all required attributes in any XML element here itself
			if(attributes != null && attributes.getLength() == 1){
			  result.setSeqNo(attributes.getValue(0));
			}
			this.objectStack.push(result);
		}
	}

	@Override
	public void endElement(String uri, String localName,
			String qName) throws SAXException {

		//Remove last added  element
		this.elementStack.pop();

		// ConstituencyResult instance has been constructed so pop it from object stack and push in resultsList
		if ("constituencyResults".equals(qName)){
			ConstituencyResult object = this.objectStack.pop();
			this.constituencyResult = object;
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

		// NOTE: deliberate typo 'consituency' not 'constituency' as XML element name is spelt this way
		if (elementName.equals("consituencyId")){
			ConstituencyResult constituencyResult = (ConstituencyResult) this.objectStack.peek();
			constituencyResult.setConstituencyId(value);
		} else if (elementName.equals("constituencyName")){
			ConstituencyResult constituencyResult = (ConstituencyResult) this.objectStack.peek();
			constituencyResult.setConstituencyName(value);
		}
	}

	private String currentElement(){
		return this.elementStack.peek();
	}

    public ConstituencyResult getConstituencyResult()
    {
        return this.constituencyResult;
    }


}
