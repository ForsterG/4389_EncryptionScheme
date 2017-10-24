import java.io.IOException;

public class EncryptionMain {

	public static void main(String[] args) {

		FileHandler fh = new FileHandler();
		EncryptionHandler eh = new EncryptionHandler();
		
		try {
			fh.importFile("C:\\Users\\Garrett\\Documents\\GitHub\\4389_EncryptionScheme\\EncryptionSchemeMain\\testFile.txt");
			//eh.encrypt(fh.returnFile());
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		
		
		
		
		
		

	}

}
