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
	static int blockSize = 64;
	static int keySize = 64;//currently based on SHA256 return value
	static int numRounds = 16;
	static String hashedPassword;
	static byte userSalt[];
	
    public static void main(String[] args) 
    {
    	try {
			FileHandler fh = new FileHandler();
			
			fh.importFile("C:\\Users\\Garrett\\Documents\\GitHub\\4389_EncryptionScheme\\EncryptionSchemeMain\\testFile.txt");
			
			userSalt =HashHandler.returnSalt();
			hashedPassword = HashHandler.SHA256(userPassword);
			
			keyHandler kh = new keyHandler();
			keySize = hashedPassword.length()-1;
			
			EncryptionHandler eh = new EncryptionHandler(blockSize,keySize, numRounds,kh);//block size,key size
			
			eh.generateSubKeys(hashedPassword);
			eh.encrypt(fh.returnFile());
			eh.decrypt(userPassword);
			
				
		} catch (IOException e) {
		
			e.printStackTrace();
		}  catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}             

        // sample text to work with @@Jamie
	/*String sampleText = "Sample secret.";
	System.out.println(sampleText);
        
        byte[] key = generate();
        
        for(int i = 0; i< key.length; i++)
        {
            System.out.println(key[i]); 
        }*/
                    
    }
    
    
}
