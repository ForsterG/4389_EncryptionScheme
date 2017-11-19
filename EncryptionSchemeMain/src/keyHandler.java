import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class keyHandler{
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

    public void EncryptionMain(Key key)
    {
        this.key = key;
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
