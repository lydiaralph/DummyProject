package ralph.lydia.results;

import java.util.List;
import java.util.ArrayList;

public class ResultsTableImpl implements ResultsTable{
	List<ResultModel> concatenatedResults;
	
	// Constructor
	public ResultsTableImpl(){
		concatenatedResults = new ArrayList<ResultModel>();

		for(PartyCode pc : PartyCode.values()){
			ResultModel result = new ResultModel();
			result.setPartyCode(pc);
			result.setShare(0);
			result.setVotes(0);
			concatenatedResults.add(result);
		}
	}

	public void displayResults(){
		for(ResultModel model : concatenatedResults) {
			model.printAllValues();
		}
	}
	
	public void refreshDisplay(List<ResultModel> resultList){
		
	}
	
	public void sortAscendingOrder(){
		
	}
	
	public List<ResultModel> getConcatenatedResults(){
		return this.concatenatedResults;
	}

}
