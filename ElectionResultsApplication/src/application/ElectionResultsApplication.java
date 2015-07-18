package application;

import ralph.lydia.processor.FileProcessor;
import ralph.lydia.processor.FileProcessorImpl;
import ralph.lydia.results.ResultsTable;
import ralph.lydia.results.ResultsTableImpl;
import ralph.lydia.utilities.LoadProperties;

public class ElectionResultsApplication {

	public static void main(String[] args){
		
		ResultsTable results = new ResultsTableImpl();
		int sleepInterval = Integer.parseInt(LoadProperties.getKeyValue("SLEEP_INTERVAL"));
		
		FileProcessor fp = new FileProcessorImpl();
		do {
			results.refreshDisplay(fp.processFile());
			try{
				Thread.sleep(sleepInterval);
			} 
			catch(InterruptedException e){
				System.out.println("Unexpected interruption: " + e.getMessage());
			}
		} while (true);
	}
}
