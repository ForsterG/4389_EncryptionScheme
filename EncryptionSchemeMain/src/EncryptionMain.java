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
			keySize = hashedPassword.length()-1;
			
			EncryptionHandler eh = new EncryptionHandler(blockSize,keySize, numRounds);//block size,key size
			
			eh.generateSubKeys(hashedPassword);
			eh.encrypt(fh.returnFile());
			eh.decrypt(userPassword);
			
				
		} catch (IOException e) {
		
			e.printStackTrace();
		}  catch (NoSuchAlgorithmException e) {
			
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
    
    public String encrypt(String origText) throws Exception 
    {
        return encrypt(generate(), origText);
    }

    public String encrypt(byte [] iv, String plaintext) throws Exception 
    {
//     byte [] decrypted = plaintext.getBytes();
//     byte [] encrypted = encrypt( iv, decrypted );

       StringBuilder ciphertext = new StringBuilder();

       return ciphertext.toString();
    }

    private Key key;

    public EncryptionMain(Key key)
    {
        this.key = key;
    }

    public EncryptionMain() throws Exception 
    {
        this(generateSymmetricKey());
    }

    public Key getKey() {
        return key;
    }

    public void setKey( Key key ) {
        this.key = key;
    }

    public static byte [] generate() {
        SecureRandom random = new SecureRandom();
        byte [] iv = new byte [16];
        random.nextBytes( iv );
        return iv;
    }

    public static Key generateSymmetricKey() throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        SecretKey key = generator.generateKey();
        return key;
    }

    public byte [] encrypt(byte [] iv, byte [] plaintext) throws Exception 
    {
        //For now, I used getAlgorithm to return the standard 
        // algorithm name for the AES key
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        return cipher.doFinal( plaintext );
    } 
}
