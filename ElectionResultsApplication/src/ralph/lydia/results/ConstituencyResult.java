package ralph.lydia.results;

import java.util.ArrayList;
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

	public void printConstituencyResult(){
		System.out.println("Finished processing result file with seqNo " + this.getSeqNo());
		System.out.println("Results for constituency " + this.getConstituencyName() 
				+ " (ID: " + this.getConstituencyId() + "):");
		for (ResultModel result : this.getResultList()) {
			result.printAllValues();
		}
	}
}


