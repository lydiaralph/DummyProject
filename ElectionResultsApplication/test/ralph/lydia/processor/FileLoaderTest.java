package ralph.lydia.processor;

import org.junit.Test;
import org.junit.BeforeClass;

import static org.junit.Assert.assertNotNull;

public class FileLoaderTest {
	
	@Test
	public void loadNextFileTest(){
		FileLoader loader = new FileLoaderImpl();
		assertNotNull("Could not load file", loader.loadNextFile());
	}

}
