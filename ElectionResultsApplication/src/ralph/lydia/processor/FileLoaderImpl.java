package ralph.lydia.processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.List;


import ralph.lydia.utilities.LoadProperties;


public class FileLoaderImpl implements FileLoader{
	
	String filePath = LoadProperties.getKeyValue("XML_INBOUND");

	public File loadNextFile() throws NoFilesToProcessException{
		try{
		List<File> filesInFolder = Files.walk(Paths.get(this.filePath))
            .filter(Files::isRegularFile)
            .map(Path::toFile)
            .collect(Collectors.toList());
		
			return filesInFolder.get(0);
		}
		catch(IOException e){
			System.out.println("Error while attempting to find files in " + filePath);
			System.out.println(e.getMessage());
			return null;
		}
		catch(IndexOutOfBoundsException e) {
			throw new NoFilesToProcessException("There are not currently any files in " + filePath);
		}
	}
}
