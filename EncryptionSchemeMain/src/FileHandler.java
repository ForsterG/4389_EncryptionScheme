//import java.io.File;
//import java.util.List;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.nio.file.Path;

public class FileHandler {

	 private String fileName;
	 private Path filePath;
	 private byte[] fileBytes;
	// private List<String> fileLines;
	public FileHandler()
	{
	
	}
	
	public void importFile(String passedFile) throws IOException
	{
	
		fileName = passedFile;
		filePath = Paths.get(fileName);
		//fileLines = Files.readAllLines(filePath);
		fileBytes = Files.readAllBytes(filePath);
		
		/*for(int x= 0;x<fileBytes.length-1;x++)
		{
			System.out.println(fileBytes[x]);
		}*/
		System.out.println("File Imported");
		
		
	}
	public byte[] returnFile()
	{
		return fileBytes;
		
	}
}
