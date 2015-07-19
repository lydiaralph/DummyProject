package ralph.lydia.results;

import java.util.List;
import java.util.ArrayList;

public class ResultsTableImpl implements ResultsTable {
	ConstituencyResult constituencyResult;
	List<ResultModel> concatenatedResults;

	// Constructor
	public ResultsTableImpl() {
		constituencyResult = new ConstituencyResult();
		concatenatedResults = new ArrayList<ResultModel>();

		for (PartyCode pc : PartyCode.values()) {
			ResultModel result = new ResultModel();
			result.setPartyCode(pc);
			result.setShare(0);
			result.setVotes(0);
			concatenatedResults.add(result);
		}
		this.constituencyResult.setResultList(concatenatedResults);
	}

	/**
	 * TODO:Improve
	 * Consider using JSPs?
	 */
	public void displayResults() {
		System.out.println("\n --- REFRESHING OVERALL RESULTS: NEW TOTALS --- \n");

		System.out.println("Running total:");
		System.out.println("Total votes cast: " + constituencyResult.getTotalVotes());
		
		// TODO: Investigate. This actually prints out descending order, but 
		// calling sortDescendingVotes prints out ascending order, and calling
		// nothing leaves results unsorted. Perhaps because
		// constituencyResult is already being sorted somewhere? Not robust.
		this.constituencyResult.sortAscendingVotes();
		
		for (ResultModel result : this.constituencyResult.getResultList()) {
			result.printAllValues();
		}
	}
	
	public void refreshDisplay(ConstituencyResult resultList){
		clearDisplay();
		updateResults(resultList);
		displayResults();
	}

	private void clearDisplay(){
		// TODO
	}
	
	/**
	 * For each result that has just come in, find the matching enum in
	 * concatenatedResults and increase votes and share
	 */
	public void updateResults(ConstituencyResult constitResult) {
		for (ResultModel concatResult : concatenatedResults) {

			for (ResultModel freshResult : constitResult.getResultList()) {

				if (freshResult.getPartyCode().equals(
						concatResult.getPartyCode())) {
					concatResult.addVotes(freshResult.getVotes());
				}
			}
		}
		this.constituencyResult.updateShareByTotalVotes();
	}
		
		public ConstituencyResult getConcatenatedResults(){
			return this.constituencyResult;
		}

}
