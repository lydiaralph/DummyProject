package ralph.lydia.results;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ResultsTableImplTest {

	@Test
	public void newResultTableTest(){
		ResultsTable resultTable = new ResultsTableImpl();
		
		assertEquals(resultTable.getConcatenatedResults().getResultList().size(), PartyCode.values().length );
		resultTable.displayResults();
		
	}
}
