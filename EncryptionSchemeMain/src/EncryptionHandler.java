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
	
	private byte xorByte[];
	private ArrayList<byte[]> xorBlockKey = new ArrayList<byte[]>();
	
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
		
		xorByte =new byte[4];//XOR together key and block
		for(int x= 0;x<halfBlockArray.size();x++)
		{
			
			byte[] blockBytes = halfBlockArray.get(x);
			
			byte[] keyBytes = hashBytes.get(x);
			System.out.println(x%hashBytes.size());
			
			/*for(int y =0;y<halfBlockArray.get(x).length;y++)
			{
				//System.out.print("Block "+blockBytes[y]);
				//System.out.print("\tKey "+keyBytes[y]);
				xorByte[y] = (byte)(blockBytes[y]^keyBytes[y]);
				//System.out.println("\tXOR "+(char)xorByte[y]);
			}
		 xorBlockKey.add(xorByte);
		 System.out.print((char)(xorByte[0]+xorByte[1]+xorByte[2]+xorByte[3]));*/
		}
		
		
		
		
		
		
		
		
		//XOR each byte together 
		/*byte test[] = null;
		for(int y=0;y<xorBlockKey.size();y++)
		{
			test =xorBlockKey.get(y);
		for(int x =0;x<xorBlockKey.get(0).length;x++)
		{
			
			System.out.println("BEFORE"+test[x]);
			test[x]=(byte) ~test[x];
			System.out.println("AFTER"+test[x]);
		}
		System.out.println((char)(test[0]+test[1]+test[2]+test[3]));
		}*/
		
		/*for(int x= 0;x<xorBlockKey.size();x++)
		{
			byte asd[]=xorBlockKey.get(x);
			System.out.print(asd);
			System.out.print(asd[1]);
			System.out.print(asd[2]);
			System.out.print((char)asd[3]);
		}*/
		
		
		
	
		//Integer y = halfBlockArray.get(0) ^ hashBytes.get(0);
		//hashChar[x]
		//System.out.println("XOR Result =" +(num1 ^ num2));
		
		//System.out.print(blockArray.get(0));
		
	
	}
	public void splitBlock(ArrayList<byte[]> halfBlock)
    {
        for(int i = 0; i<halfBlock.size();i++)
        {
            byte [] splitBytes = ByteBuffer.allocate(Byte.SIZE/8).put(halfBlock.get(i)).array();
            
        }
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
