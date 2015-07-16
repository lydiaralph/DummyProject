package ralph.lydia.processor;

import java.io.File;

/**
 * Pings Inbound folder and loads first XML file into application for processing.
 * @author lydia
 *
 */
public interface FileLoader  {
	public File loadNextFile() throws NoFilesToProcessException;
}
