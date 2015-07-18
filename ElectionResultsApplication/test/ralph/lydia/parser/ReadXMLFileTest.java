package ralph.lydia.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import ralph.lydia.results.ResultModel;
import ralph.lydia.results.ConstituencyResult;
import ralph.lydia.utilities.LoadFilesFromTestInbound;

import java.io.File;

import org.junit.Test;

/**
 *
 */

public class ReadXMLFileTest {
	
	@Test
	public void readXMLData() throws Exception {
		String testFileName = "readXmlFileTest.xml";
		
		File f = new File(LoadFilesFromTestInbound.loadXmlFile(testFileName));
		
		ConstituencyResult constituencyResult = new ConstituencyResult();
		
		constituencyResult = ReadXMLFile.readXmlFileAndParseContents(f.getAbsolutePath());

		constituencyResult.printConstituencyResult();
		
		for(ResultModel result : constituencyResult.getResultList()) {
	       switch(result.getPartyCode()){
	       case CON:
	    	   assertEquals(7924,result.getVotes());
	    	   break;
	    	   
	       case LD:
	    	   assertEquals(19.10,result.getShare(),0.09);
	    	   break;
	    	   
	       case UKIP:
	    	   assertEquals(296,result.getVotes());
	    	   break;
	    	   
	       default:break;
   
	       }
		}
	}
	
	@Test
	public void readIncorrectXMLData() throws Exception {
		String testFileName = "readXmlFileTest.xml";
		
		File f = new File(LoadFilesFromTestInbound.loadXmlFile(testFileName));
		
		ConstituencyResult constituencyResult = new ConstituencyResult();
		
		constituencyResult = ReadXMLFile.readXmlFileAndParseContents(f.getAbsolutePath());

		constituencyResult.printConstituencyResult();
		
		for(ResultModel result : constituencyResult.getResultList()) {
	       switch(result.getPartyCode()){
	       case CON:
	    	   assertNotEquals(924,result.getVotes());
	    	   break;
	    	   
	       case LD:
	    	   assertNotEquals(3597,result.getVotes());
	    	   break;
	    	   
	       case UKIP:
	    	   assertNotEquals(236,result.getShare());
	    	   break;
	    	   
	       default:break;
   
	       }
		}
	}


}
