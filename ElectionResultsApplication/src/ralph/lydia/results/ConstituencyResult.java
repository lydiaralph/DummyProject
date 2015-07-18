package ralph.lydia.results;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ConstituencyResult {

	int seqNo;
	int constituencyId;
	String constituencyName;
	List<ResultModel> resultList = new ArrayList<ResultModel>();

	public void setSeqNo(int id){
		this.seqNo = id;
	}

	public void setSeqNo(String id){
		this.seqNo = Integer.parseInt(id);
	}

	public void setConstituencyId(String id){
		this.constituencyId = Integer.parseInt(id);
	}

	public void setConstituencyId(int id){
		this.constituencyId = id;
	}

	public void setConstituencyName(String cName){
		this.constituencyName = cName;
	}

	public void setResultList(List<ResultModel> resultList){
		this.resultList = resultList;
	}

	public int getSeqNo(){
		return this.seqNo;
	}
	
	public int getConstituencyId(){
		return this.constituencyId;
	}

	public String getConstituencyName(){
		return this.constituencyName;
	}

	public List<ResultModel> getResultList(){
		return this.resultList;
	}
	
	public int getTotalVotes(){
		int totalVotes = 0;
		for (ResultModel result : this.getResultList()) {
			totalVotes += result.getVotes();
		}
		return totalVotes;
	}
	
	public void updateShareByTotalVotes(){
		int totalVotes = this.getTotalVotes();
		
		for (ResultModel result : resultList) {
			result.setShare((float) result.getVotes()*100 / totalVotes);
		}
	}

	public void printConstituencyResult(){
		System.out.println("\n --- FINISHED PROCESSING CONSTITUENCY RESULT --- \n");
		System.out.println("Finished processing result file with seqNo " + this.getSeqNo());
		System.out.println("Results for constituency " + this.getConstituencyName() 
				+ " (ID: " + this.getConstituencyId() + "):");
		for (ResultModel result : this.getResultList()) {
			result.printAllValues();
		}
	}
	
	/**
	 * Sort ConstituencyResult by party with most votes
	 * @see http://stackoverflow.com/a/12450149/2294676
	 * @param resultslist
	 */
	
	public void sortDescendingVotes(){
		Collections.sort(this.resultList, descVotes);
	}
	
    // These variables are static because you don't need multiple copies
    // for sorting, as they have no intrinsic state.
    static private Comparator<ResultModel> descVotes;

    // Initialize static variables inside a static block.
    static {
        descVotes = new Comparator<ResultModel>(){
            @Override
            public int compare(ResultModel party1, ResultModel party2){
                return party1.getVotes() - party2.getVotes();
            }
        };
    }
    
	public void sortAscendingVotes(){
		Collections.sort(this.resultList, ascVotes);
	}
	
    // These variables are static because you don't need multiple copies
    // for sorting, as they have no intrinsic state.
    static private Comparator<ResultModel> ascVotes;

    // Initialize static variables inside a static block.
    static {
        ascVotes = new Comparator<ResultModel>(){
            @Override
            public int compare(ResultModel party1, ResultModel party2){
                return party2.getVotes() - party1.getVotes();
            }
        };
    }
	
}


