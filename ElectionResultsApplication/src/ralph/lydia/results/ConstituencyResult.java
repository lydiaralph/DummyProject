package ralph.lydia.results;

import java.util.ArrayList;
import java.util.List;

public class ConstituencyResult {

	int constituencyId;
	String constituencyName;
	List<ResultModel> resultList = new ArrayList<ResultModel>();
	
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
	
	public int getConstituencyId(){
		return this.constituencyId;
	}
	
	public int getConstituencyName(){
		return this.getConstituencyName();
	}
	
}
