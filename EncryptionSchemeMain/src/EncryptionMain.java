import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
			System.out.println("Hashed File1: "+hashedFile);
			
			
			//Demo Integrity Check
			FileOutputStream out = null;
			out = new FileOutputStream(eh.encryptedText);
			out.write((char)+165165453);
			out.close();
			
			fileSalt =HashHandler.returnSalt();
			hashedFile=	HashHandler.SHA256(eh.encryptedText);
			System.out.println("Hashed File2: "+hashedFile);
			//END DEMO
			
				
		} catch (IOException e) {
			e.printStackTrace();
		}  catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}             
    } 
}
