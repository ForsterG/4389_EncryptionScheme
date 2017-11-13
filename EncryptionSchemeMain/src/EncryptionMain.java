import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class EncryptionMain {

	public static void main(String[] args) {

		String userPassword= "UTDALLAS"; // user entered password;
		int blockSize = 64;
		int keySize = 64;//currently based on SHA256 return value
		int numRounds = 16;
		
		FileHandler fh = new FileHandler();
		EncryptionHandler eh = new EncryptionHandler(blockSize,keySize, numRounds);//block size,key size
		HashHandler hh = new HashHandler();
		
		
		try {
			fh.importFile("C:\\Users\\Garrett\\Documents\\GitHub\\4389_EncryptionScheme\\EncryptionSchemeMain\\testFile.txt");
			//System.out.print(hh.generateHash(userPassword));
			eh.generateSubKeys(hh.generateHash(userPassword));
			//eh.encrypt(fh.returnFile());
			
			//TODO hashed password needs to be stored in order to decrypt
			
			
			
		} catch (IOException e) {
		
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		
		

	}

}
