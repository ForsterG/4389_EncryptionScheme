import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class EncryptionHandler {
	
	private int keySize;
	private int blockSize;
	private int numRounds;
	
	char[] hashChar;
	private ArrayList<Character[]> subKeyList = new ArrayList<Character[]>();
	
	
	//private ArrayList<Integer> fileChars=new ArrayList<Integer>();
	private ArrayList<byte[]> halfBlockArray = new ArrayList<byte[]>();
	
	
	public EncryptionHandler(int blockSize, int keySize, int numRounds){
		this.blockSize=blockSize;
		this.keySize=keySize;
		this.numRounds=numRounds;
		
	}
	
	public void generateSubKeys(String userPassHash){
		
		hashChar =userPassHash.toCharArray();
		for(int x=0;x<numRounds;x++)
		{
			
			
			//subKeyList.add(arg0);
		}
		
		
		
	}
	private Character[] keySchedule(){
		return null;
		
	}
	
	public void encrypt(ArrayList<Integer> fileChars) throws IOException{
		
		splitIntoHalfBlocks(fileChars);
		//halfBlockArray.iterator();
		for(int x= 0;x<halfBlockArray.size();x++)
		{
			
		}
		//System.out.println("XOR Result =" +(num1 ^ num2));
		
		//System.out.print(blockArray.get(0));
		
	
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
