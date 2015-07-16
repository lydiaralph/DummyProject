package ralph.lydia.processor;

public class NoFilesToProcessException extends Exception{

	public NoFilesToProcessException(){
		super();
	}

    public NoFilesToProcessException(String message){
       super(message);
    }
	
}
