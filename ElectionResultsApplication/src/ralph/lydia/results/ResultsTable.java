package ralph.lydia.results;

import java.util.List;

public interface ResultsTable {
	public void displayResults();
	public void refreshDisplay(List<ResultModel> resultList);
	public void sortAscendingOrder();
	public List<ResultModel> getConcatenatedResults();
}
