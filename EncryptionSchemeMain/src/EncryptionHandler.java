import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class EncryptionHandler {
	
	private int keySize;
	private int blockSize;
	private int numRounds;
	
	private char[] hashChar;
	private ArrayList<byte[]> subKeyList = new ArrayList<byte[]>();
	private ArrayList<byte[]> halfBlockArray = new ArrayList<byte[]>();
	
	private ArrayList<Integer> hashChars=new ArrayList<Integer>();
	private ArrayList<byte[]> hashBytes = new ArrayList<byte[]>();
	
	
	
	public EncryptionHandler(int blockSize, int keySize, int numRounds){
		this.blockSize=blockSize;
		this.keySize=keySize;
		this.numRounds=numRounds;
		
	}
	
	public void generateSubKeys(String userPassHash){
		
		hashChar =userPassHash.toCharArray();
		for(int x=0;x<hashChar.length;x++)
		{
			hashChars.add((int)hashChar[x]);
			//System.out.println(hashChar.get(x));
			byte[] bytes = ByteBuffer.allocate(Integer.SIZE/8).putInt(hashChars.get(x)).array();
			/*for(int y=0;y<bytes.length;y++)
			{
				System.out.println(bytes[y]);
			}*/
			hashBytes.add(bytes);
		}
	}
	
	
	public void encrypt(ArrayList<Integer> fileChars) throws IOException{
		
		splitIntoHalfBlocks(fileChars);
		splitHash(hashChars);
		
		for(int x= 0;x<halfBlockArray.size();x++)
		{
			
		}
		for(int x =0;x<halfBlockArray.get(0).length;x++)
		{
			byte[] blockBytes = halfBlockArray.get(0);
			byte[] keyBytes = hashBytes.get(0);
			char xorBlockKey =(char) (blockBytes[3]^keyBytes[3]);
			System.out.println(xorBlockKey);
		}
		
		
		
		
		//Integer y = halfBlockArray.get(0) ^ hashBytes.get(0);
		//hashChar[x]
		//System.out.println("XOR Result =" +(num1 ^ num2));
		
		//System.out.print(blockArray.get(0));
		
	
	}
	
	private void splitHash(ArrayList<Integer> fileChars){
		
		for(int x = 0; x<fileChars.size(); x++ )
		{
			
			byte[] bytes = ByteBuffer.allocate(Integer.SIZE/8).putInt(fileChars.get(x)).array();
			hashBytes.add(bytes);
			
			//Debug statement to list half-blocks
			/*for(int y= 0;y<bytes.length;y++)
			{
				System.out.print(y+"\t");
				System.out.println(bytes[y]);
			}
			for (byte b : bytes) {
				   System.out.format("0x%x ", b);
				   
				}
			System.out.println();//*/
		}
		
	}
	
	private void splitIntoHalfBlocks(ArrayList<Integer> fileChars){
		
		for(int x = 0; x<fileChars.size(); x++ )
		{
			byte[] bytes = ByteBuffer.allocate(Integer.SIZE/8).putInt(fileChars.get(x)).array();
			halfBlockArray.add(bytes);
			
			//Debug statement to list half-blocks
			/*for(int y= 0;y<bytes.length;y++)
			{
				System.out.print(y+"\t");
				System.out.println(bytes[y]);
			}
			for (byte b : bytes) {
				   System.out.format("0x%x ", b);
				   
				}
			System.out.println();//*/
		}
		
	}
	
	private void executeRound(){
		
		
	}
	
	public void decrypt(){
	
	}
}
