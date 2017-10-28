import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.LinkOption;


public class FileHandler {

	 private String fileName;
	 private Path filePath;
	 private ArrayList<Integer> fileChars=new ArrayList<Integer>();
	 
	public FileHandler() {
	
	}
	
	public void importFile(String passedFile) throws IOException {
		
		fileName = passedFile;
		filePath = Paths.get(fileName);
		
		if(Files.exists(filePath, LinkOption.NOFOLLOW_LINKS)) {
			if(Files.isReadable(filePath)) {
				
				BufferedReader br = new BufferedReader(new FileReader(passedFile));
				
				while(br.ready()) {
					fileChars.add(br.read());
				}
				br.close();
				System.out.println("File Imported");
			}
			else {
				System.out.println(passedFile+" is not readable");
			}
		}
		else {
			System.out.println(passedFile+" does not exist");
		}
		
		/*for(int x=0;x<fileChars.size();x++)//Debug statement walk the ArrayList
		{
			System.out.print(fileChars.get(x)+"\t");
			char y = (char) fileChars.get(x).intValue();
			System.out.println(y);
		}//*/

	}
	
	public ArrayList<Integer> returnFile() {
		return fileChars;
		
	}
}
