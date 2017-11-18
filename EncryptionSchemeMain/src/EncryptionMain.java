import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class EncryptionMain {

	public static void main(String[] args) {

		String userPassword= "UTDALLAS"; // user entered password;
		int blockSize = 64;
		int keySize = 64;//currently based on SHA256 return value
		int numRounds = 16;
		
		String hashedPassword;
		byte userSalt[];
		
		
		try {
			FileHandler fh = new FileHandler();
			fh.importFile("C:\\Users\\Garrett\\Documents\\GitHub\\4389_EncryptionScheme\\EncryptionSchemeMain\\testFile.txt");
			
			userSalt =HashHandler.returnSalt();
			hashedPassword = HashHandler.SHA256(userPassword);
			keySize = hashedPassword.length()-1;
			//System.out.println("Hashed Password: "+hashedPassword);
			
			EncryptionHandler eh = new EncryptionHandler(blockSize,keySize, numRounds);//block size,key size
			
			eh.generateSubKeys(hashedPassword);
			eh.encrypt(fh.returnFile());
				
		} catch (IOException e) {
		
			e.printStackTrace();
		}  catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		
		

	}

}
