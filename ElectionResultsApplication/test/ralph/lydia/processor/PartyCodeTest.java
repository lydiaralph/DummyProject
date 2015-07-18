package ralph.lydia.processor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import ralph.lydia.results.PartyCode;

public class PartyCodeTest {
	
	@Test
	public void partyCodeTest(){
		assertEquals(PartyCode.CON, PartyCode.valueOf("CON"));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void codeDoesNotExistTest(){
		assertEquals(null, PartyCode.valueOf("Does not exist"));
	}
}
