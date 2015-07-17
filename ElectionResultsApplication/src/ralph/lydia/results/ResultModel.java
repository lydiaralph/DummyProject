package ralph.lydia.results;

import ralph.lydia.processor.FileValidatorException;

public class ResultModel {

//	private PartyCode partyCode;
	
	private String partyCode;
	private int votes;
	private float share;
	
	public ResultModel(){
		this.setPartyCode(null);
		this.setVotes(0);
		this.setShare(0);
	}
	
//	public PartyCode getPartyCode(){
	public String getPartyCode(){
		return this.partyCode;
	}
	
	public int getVotes(){
		return this.votes;
	}
	
	public float getShare(){
		return this.share;
	}
	
	public void setPartyCode(String partyCode){
//		this.partyCode = PartyCode.valueOf(partyCode);
		
		this.partyCode = "dummy string";
	}
	
	public void setVotes(int votes){
		this.votes = votes;
	}
	
	public void setShare(float share){
		this.share = share;
	}
	
	public void printAllValues(){
		System.out.println("PartyCode: " + this.getPartyCode() + "\n");
		System.out.println("Votes: " + this.getVotes() + "\n");
		System.out.println("Share: " + this.getShare() + "\n");
	}
	
}
