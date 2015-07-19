package ralph.lydia.processor;

import java.io.File;
import java.util.List;

import ralph.lydia.results.ConstituencyResult;

public interface FileProcessor {
	public ConstituencyResult processFile();
	public ConstituencyResult validateAndReadFileOnly(File xmlFile);
}
