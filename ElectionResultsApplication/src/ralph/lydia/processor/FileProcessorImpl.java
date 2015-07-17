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

public class FileProcessorImpl implements FileProcessor {

	private static File xmlFile;
	private static String outboundFolder;

	// public static void processFile(){
	public void processFile() {
		FileLoader loader = new FileLoaderImpl();
		FileValidator fileValidator = new FileValidator();
		ReadXMLFile reader = new ReadXMLFile();
		List<ResultModel> resultList = new ArrayList<ResultModel>();

		try {
			xmlFile = null; // Resets without having to create new object
			xmlFile = loader.loadNextFile();
			fileValidator.validateXmlFile(xmlFile);
			outboundFolder = "XML_PROCESSED";
		} catch (NoFilesToProcessException e){
			System.out.println(e.getMessage());
		
		} catch (FileValidatorException e) {
			System.out.println(e.getMessage());
			outboundFolder = "XML_INVALID";
		}

		try {
			resultList = ReadXMLFile.readXmlFileAndParseContents(xmlFile.getAbsolutePath());
	        for(ResultModel model : resultList) {
	            model.printAllValues();
	        }
		} catch(FileValidatorException e){
			System.out.println(e.getMessage());
			outboundFolder = "XML_INVALID";
		}
			
		try{
			moveToOutboundFolder(xmlFile, outboundFolder);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// Should probably be in different class - breaks Single Responsibility
	// principle?
	private static void moveToOutboundFolder(File inboundFile,
			String outboundFolder) throws IOException {
		String outboundDir = LoadProperties.getKeyValue(outboundFolder);
		


		try {
			Path processedFilePath = inboundFile.toPath();
			Path outboundFilePath = new File(outboundDir, inboundFile.getName())
					.toPath();
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

	public String getOutboundFolder() {
		return outboundFolder;
	}

}
