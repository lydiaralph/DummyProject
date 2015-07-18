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

	// public static void processFile(){
	public void processFile() {
		FileLoader loader = new FileLoaderImpl();
		List<ResultModel> resultList = new ArrayList<ResultModel>();

		try{
			xmlFile = loader.loadNextFile();
			xmlFile = validateFile(xmlFile);
			resultList = getFileData(xmlFile);
			moveToOutboundFolder(xmlFile, outboundFolder);
		} catch (NullPointerException e){
			System.out.println(e.getMessage());
			return;
		} catch(NoFilesToProcessException e){
			e.getMessage();
			return;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// Should probably be in different class - breaks Single Responsibility
	// principle?
	private void moveToOutboundFolder(File inboundFile,
			String outboundFolder) throws IOException {
		String outboundDir = LoadProperties.getKeyValue(outboundFolder);

		try {
			Path processedFilePath = inboundFile.toPath();
			Path outboundFilePath = new File(outboundDir, inboundFile.getName()).toPath();
			System.out.println("Moving file " + inboundFile + " at "
					+ processedFilePath);
			System.out.println(" to outbound folder " + outboundFolder + " at "
					+ outboundDir);

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

	private String getOutboundFolder() {
		return outboundFolder;
	}

	private void setOutboundFolder(String s) {
		this.outboundFolder = s;
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

	private List<ResultModel> getFileData(File xmlFile){
		List<ResultModel> resultList = new ArrayList<ResultModel>();  
		try {
			resultList = ReadXMLFile.readXmlFileAndParseContents(xmlFile.getAbsolutePath());
			for(ResultModel model : resultList) {
				model.printAllValues();
			}
		} catch(NullPointerException | FileValidatorException e){
			System.out.println(e.getMessage());
			this.setOutboundFolder("XML_INVALID");
		}
		return resultList;

	}
}
