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
	GRN  ("GREEN"),
	IND  ("INDEPENDENT"),
	SNP  ("SCOTTISH NATIONAL PARTY"),
	BNP  ("BRITISH NATIONAL PARTY"),
//	SGP  ("SGP"),
//	SSP  ("SSP"),
//	SLP  ("SLP"),
//	OCV  ("OCV"),
//	PC   ("PC"),
//	DUP  ("DUP"),
//	SDLP ("SDLP"),
//	UUP  ("UUP"),
//	AP   ("AP"),
//	SF   ("SF"),
//	VFY  ("VFY"),
//	ED   ("ED"),
//	RES  ("RES"),
//	MRLP ("MRLP"),
//	CHP  ("CHP"),
//	WP   ("WP"),
//	CPB  ("CPB"),
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

