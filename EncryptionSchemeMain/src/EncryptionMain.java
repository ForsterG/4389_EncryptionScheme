import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EncryptionMain 
{
	static String userPassword= "UTDALLAS"; // user entered password;
	static String falsePassword ="FALSE PASSWORD";
	static int numRounds = 16;
	
	
	static String hashedPassword;
	static String hashedFile;
	static byte userSalt[];
	static byte fileSalt[];
	
    public static void main(String[] args) 
    {
    	try {
			FileHandler fh = new FileHandler();
			fh.importFile("C:\\Users\\Garrett\\Documents\\GitHub\\4389_EncryptionScheme\\EncryptionSchemeMain\\testFile.txt");
			
			userSalt =HashHandler.returnSalt();
			hashedPassword = HashHandler.SHA256(userPassword);
			hashedPassword = HashHandler.SHA256(falsePassword);
			
			System.out.println("Hashed Password: "+hashedPassword);
			EncryptionHandler eh = new EncryptionHandler(numRounds,hashedPassword,falsePassword,fh.returnFile());
			
			
			fileSalt =HashHandler.returnSalt();
			hashedFile=	HashHandler.SHA256(eh.encryptedText);
			System.out.println("Hashed File: "+hashedFile);
			
				
		} catch (IOException e) {
			e.printStackTrace();
		}  catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}             
    } 
}
