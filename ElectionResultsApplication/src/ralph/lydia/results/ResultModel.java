package ralph.lydia.results;

public class ResultModel {

	private PartyCode partyCode;
	
	private int votes;
	private float share;
	
	public PartyCode getPartyCode(){
		return this.partyCode;
	}
	
	public String getPartyCodeName(){
		return this.partyCode.getPartyCodeName();
	}
	
	public int getVotes(){
		return this.votes;
	}
	
	public float getShare(){
		return this.share;
	}
	
	public void setPartyCode(String partyCode) throws IllegalArgumentException{
		this.partyCode = PartyCode.valueOf(partyCode);
	}
	
	public void setPartyCode(PartyCode partyCode) {
		this.partyCode = partyCode;
	}
	
	public void setVotes(int votes){
		this.votes = votes;
	}
	
	public void setShare(float share){
		this.share = share;
	}
	
	public void addVotes(int votes){
		this.votes += votes;
	}
	
	public void printAllValues(){
		System.out.println("\n");
		System.out.println("PartyCode: " + this.getPartyCodeName());
		System.out.println("Votes: " + this.getVotes());
		System.out.printf("Share: " + String.format("%.2f", this.getShare()) + "%%\n" );
	}
	
}
