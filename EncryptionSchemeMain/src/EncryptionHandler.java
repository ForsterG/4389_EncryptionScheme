import java.io.*;
public class EncryptionHandler {
	
	byte[] fileBytes;
	public EncryptionHandler(){
		
	}
	
	public void encrypt(byte[] passedFile) throws IOException
	{
	fileBytes = Serializer.toBytes(passedFile);
	//rework process
	}
	public void decrypt(byte[] passedFile)
	{
	
	}
}
