package ralph.lydia.results;

public interface ResultsTable {
	public void displayResults();
	public void updateResults(ConstituencyResult resultList);
	public void refreshDisplay(ConstituencyResult resultList);
	public void sortAscendingOrder();
	public ConstituencyResult getConcatenatedResults();
}
