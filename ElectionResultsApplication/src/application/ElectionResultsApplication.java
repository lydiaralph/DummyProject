package application;

import ralph.lydia.processor.FileProcessor;
import ralph.lydia.processor.FileProcessorImpl;
import ralph.lydia.results.ResultsTable;
import ralph.lydia.results.ResultsTableImpl;

public class ElectionResultsApplication {

	public static void main(String[] args){
		
		ResultsTable results = new ResultsTableImpl();
		
		System.out.println("Hello world");
		
		FileProcessor fp = new FileProcessorImpl();
		int count = 0;
		do {
			// implement Scheduler here?
			results.refreshDisplay(fp.processFile());
		} while (count < 700);
		System.out.println("Processed " + count + " files");

		fp.processFile();
	}
}
