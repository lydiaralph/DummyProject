package ralph.lydia.processor;

import org.junit.Test;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;

public class FileLoaderTest {
	
	@Test
	public void loadNextFileTest(){
		FileLoader loader = new FileLoaderImpl();
		try {
			assertNotNull("Could not load file", loader.loadNextFile());
		} catch(Exception e){
			fail(e.getMessage());
		}
	}

}
