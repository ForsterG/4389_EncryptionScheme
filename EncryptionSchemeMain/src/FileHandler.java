//import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class FileHandler {

	 private String fileName;
	 private Path filePath;
	 private byte[] fileBytes;
	public FileHandler()
	{
	
	}
	
	public void importFile(String passedFile) throws IOException
	{
	
		fileName = passedFile;
		filePath = Paths.get(fileName);
		fileBytes = Files.readAllBytes(filePath);
		
	}
	public byte[] returnFile()
	{
		return fileBytes;
		
	}
}
