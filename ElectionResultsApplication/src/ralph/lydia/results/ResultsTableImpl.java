package ralph.lydia.results;

import java.util.List;
import java.util.ArrayList;

public class ResultsTableImpl implements ResultsTable {
	List<ResultModel> concatenatedResults;

	// Constructor
	public ResultsTableImpl() {
		concatenatedResults = new ArrayList<ResultModel>();

		for (PartyCode pc : PartyCode.values()) {
			ResultModel result = new ResultModel();
			result.setPartyCode(pc);
			result.setShare(0);
			result.setVotes(0);
			concatenatedResults.add(result);
		}
	}

	/**
	 * TODO:Improve
	 * Consider using JSPs?
	 */
	public void displayResults() {
		for (ResultModel result : concatenatedResults) {
			result.printAllValues();
		}
	}
	
	public void refreshDisplay(List<ResultModel> resultList){
		clearDisplay();
		updateResults(resultList);
		displayResults();
	}

	private void clearDisplay(){
		
	}
	
	/**
	 * For each result that has just come in, find the matching enum in
	 * concatenatedResults and increase votes and share
	 */
	public void updateResults(List<ResultModel> resultList) {
		
		System.out.println("\n --- REFRESHING DISPLAY --- \n");

		for (ResultModel concatResult : concatenatedResults) {

			for (ResultModel freshResult : resultList) {

				if (freshResult.getPartyCode().equals(
						concatResult.getPartyCode())) {
					concatResult.addVotes(freshResult.getVotes());
					concatResult.addShare(freshResult.getShare());
				}
			}
		}
	}
		
		public void sortAscendingOrder(){
			
		}
		
		public List<ResultModel> getConcatenatedResults(){
			return this.concatenatedResults;
		}

}
