package ralph.lydia.processor;

import java.io.File;
//import java.nio.file.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import ralph.lydia.utilities.LoadProperties;
import ralph.lydia.parser.ReadXMLFile;
import ralph.lydia.results.ResultModel;

/**
 * FileProcessorImpl is a supervisor class:
 * - loads the file
 * - validates the file against the schema
 * - reads the data in from the xml file
 * - concatenates this data
 * - moves the file to relevant folder 
 * 
 * @author lydia
 *
 */
public class FileProcessorImpl implements FileProcessor {

	private File xmlFile;
	private String outboundFolder;

	public List<ResultModel> processFile() {
		
		List<ResultModel> resultList = new ArrayList<ResultModel>();

		try{
			xmlFile = loadFile();
			xmlFile = validateFile(xmlFile);
			resultList = readFile(xmlFile);
			moveFileToOutboundFolder(xmlFile, outboundFolder);
			
		} catch (NullPointerException e){
			System.out.println(e.getMessage());
		} catch(NoFilesToProcessException e){
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return resultList;
	}
	
	// Should probably be in different class - breaks Single Responsibility
	// principle?
	private void moveFileToOutboundFolder(File inboundFile,
			String outboundFolder) throws IOException {
		String outboundDir = LoadProperties.getKeyValue(outboundFolder);

		try {
			Path processedFilePath = inboundFile.toPath();
			Path outboundFilePath = new File(outboundDir, inboundFile.getName()).toPath();
//			System.out.println("Moving file " + inboundFile + " at "
//					+ processedFilePath);
//			System.out.println(" to outbound folder " + outboundFolder + " at "
//					+ outboundDir);

			if (outboundDir == null || outboundDir.isEmpty()) {
				throw new IOException("Could not find outbound folder "
						+ outboundFolder + " at path '" + outboundDir + "'. Config error?");
			}

			Files.move(processedFilePath, outboundFilePath);
		} catch (IOException e) {
			throw new IOException("Unexpected error while moving file: "
					+ e.getMessage());
		}
	}

	private File loadFile() throws NoFilesToProcessException{
		FileLoader loader = new FileLoaderImpl();
		try{
			File xmlFile = loader.loadNextFile();
			return xmlFile;	
		} catch(NoFilesToProcessException e){
			throw new NoFilesToProcessException(e.getMessage());
		}
		
	}
	
	private File validateFile(File xmlFile){
		try {
			FileValidator fileValidator = new FileValidator();
			fileValidator.validateXmlFile(xmlFile);
			this.setOutboundFolder("XML_PROCESSED");
		} catch (FileValidatorException e) {
			System.out.println(e.getMessage());
			this.setOutboundFolder("XML_INVALID");
		}
		return xmlFile;
	}

	private List<ResultModel> readFile(File xmlFile){
		List<ResultModel> resultList = new ArrayList<ResultModel>();  
		try {
			resultList = ReadXMLFile.readXmlFileAndParseContents(xmlFile.getAbsolutePath());
			for(ResultModel model : resultList) {
//				model.printAllValues();
			}
		} catch(NullPointerException | FileValidatorException e){
			System.out.println(e.getMessage());
			this.setOutboundFolder("XML_INVALID");
		}
		return resultList;

	}
	

	private String getOutboundFolder() {
		return outboundFolder;
	}

	private void setOutboundFolder(String s) {
		this.outboundFolder = s;
	}
}
