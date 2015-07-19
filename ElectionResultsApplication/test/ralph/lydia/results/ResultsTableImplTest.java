package ralph.lydia.results;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import ralph.lydia.parser.ReadXMLFile;
import ralph.lydia.processor.FileProcessor;
import ralph.lydia.processor.FileProcessorImpl;
import ralph.lydia.processor.FileValidator;
import ralph.lydia.results.ResultsTable;
import ralph.lydia.utilities.LoadFilesFromTestInbound;

public class ResultsTableImplTest {

		@Test
		public void newResultTableTest(){
			ResultsTable resultTable = new ResultsTableImpl();
			
			assertEquals(resultTable.getConcatenatedResults().getResultList().size(), PartyCode.values().length );
			resultTable.displayResults();
		}


	/**
	 * Set up new ResultsTable. Read in new XML file. Check results have updated accordingly.
	 */
	@Test
	public void endToEndTest(){
		ResultsTable overallResultsTable = new ResultsTableImpl();
		List<ResultModel> listOfPartyResults = new ArrayList<ResultModel>();
		FileProcessor fp = new FileProcessorImpl();
		int i = -1;
		int j = -1;

		listOfPartyResults = overallResultsTable.getConcatenatedResults().getResultList();
		assertEquals(listOfPartyResults.get(0).getVotes(),0);

		// LOAD NEW FILE(1)
		File f = new File(LoadFilesFromTestInbound.loadXmlFile("endToEndTest.xml"));

		overallResultsTable.updateResults(fp.validateAndReadFileOnly(f));

		listOfPartyResults = overallResultsTable.getConcatenatedResults().getResultList();

		// TODO: Improve: is there an easier way to get indices?
		for (ResultModel result : listOfPartyResults) {
			if(result.getPartyCode().equals(PartyCode.CON)){
				i = listOfPartyResults.indexOf(result);
			}
			else if(result.getPartyCode().equals(PartyCode.LAB)){
				j = listOfPartyResults.indexOf(result);
			}
		}

		assertEquals(listOfPartyResults.get(i).getVotes(),18686);
		assertEquals(listOfPartyResults.get(j).getVotes(),12749);
		assertEquals(overallResultsTable.getConcatenatedResults().getTotalVotes(),47353);

		// LOAD NEW FILE(2)
		File f2 = new File(LoadFilesFromTestInbound.loadXmlFile("endToEndTest2.xml"));

		overallResultsTable.updateResults(fp.validateAndReadFileOnly(f2));

		listOfPartyResults = overallResultsTable.getConcatenatedResults().getResultList();

		// TODO: Improve: is there an easier way to get indices?
		for (ResultModel result : listOfPartyResults) {
			if(result.getPartyCode().equals(PartyCode.CON)){
				i = listOfPartyResults.indexOf(result);
			}
			else if(result.getPartyCode().equals(PartyCode.LAB)){
				j = listOfPartyResults.indexOf(result);
			}
		}

		assertEquals(listOfPartyResults.get(i).getVotes(),(18686+19003));
		assertEquals(listOfPartyResults.get(j).getVotes(),(12749+8750));
		assertEquals(overallResultsTable.getConcatenatedResults().getTotalVotes(),81768);
		
		float expectedShare = (float)((18686+19003)*100)/81768;
		assertEquals(listOfPartyResults.get(i).getShare(),expectedShare, 0.01);


	}

}
