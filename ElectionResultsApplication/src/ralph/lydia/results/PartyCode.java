package ralph.lydia.results;


/**
 * This enum is used to analyse the party code supplied in the XML file
 * @author lydia.ralph
 *
 */
public enum PartyCode {
	LAB  ("LABOUR"),
	LD   ("LIBERAL DEMOCRATS"),
	CON  ("CONSERVATIVE"),
	UKIP ("UKIP"),
	OTH  ("OTHER PARTIES");

	/** Holds the enum value*/
	private int partyCodeType;

	/** Holds the string representation*/
	private String partyCodeName;

//	private PartyCode(int type, String name) {
//		partyCodeType = type;
//		partyCodeName = name;
//	}

	private PartyCode(String name) {
		partyCodeName = name;
	}
	
	public int getPartyCodeType() {
		return partyCodeType;
	}

	public String getPartyCodeName() {
		return partyCodeName;
	}
}

