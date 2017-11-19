import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class EncryptionHandler {
	

	private int numRounds;
	
	private char[] hashChar;
	private ArrayList<byte[]> halfBlockArray = new ArrayList<byte[]>();
	
	private ArrayList<Integer> hashChars=new ArrayList<Integer>();
	private ArrayList<byte[]> hashBytes = new ArrayList<byte[]>();
	
	private ArrayList<byte[]> encryptedOutput = new ArrayList<byte[]>();
	private ArrayList<byte[]> decryptedOutput = new ArrayList<byte[]>();
	
	public EncryptionHandler(int blockSize, int keySize, int numRounds,keyHandler kh){
		this.numRounds=numRounds;
	}
	
	public ArrayList<byte[]> encrypt(ArrayList<Integer> fileChars) throws IOException{
		
		splitIntoHalfBlocks(fileChars);
		splitHash(hashChars);
		
		System.out.println("Unecrypted:\t");
		for(int x=0;x<halfBlockArray.size();x++)
		{
			byte holder[] =new byte[4];
			 holder = halfBlockArray.get(x);
			 
			 System.out.print((char)(holder[0]+holder[1]+holder[2]+holder[3]));
		}//*/
		
		encryptedOutput = xorBlockAndKey(halfBlockArray,hashBytes);
		FileOutputStream out = null;
		out = new FileOutputStream("EncryptRound1.txt");
		
		for(int x=0;x<encryptedOutput.size();x++)
		{
			out.write(encryptedOutput.get(x));
		}
		
		System.out.println();
		System.out.println("First Round:\t");
		for(int x=0;x<encryptedOutput.size();x++)
		{
			byte holder[] =new byte[4];
			 holder = encryptedOutput.get(x);
			
			 System.out.print((char)(holder[0]+holder[1]+holder[2]+holder[3]));
		}
		encryptedOutput = executeRounds(encryptedOutput,hashBytes);
		
		System.out.println();
		System.out.println("Second Round :\t");
		for(int x=0;x<encryptedOutput.size();x++)
		{
			byte holder[] =new byte[4];
			 holder = encryptedOutput.get(x);
			
			 System.out.print((char)(holder[0]+holder[1]+holder[2]+holder[3]));
		}//*/
		
		
		
		
		for(int x=0;x<numRounds;x++)
		{
			
			encryptedOutput = xorBlockAndKey(encryptedOutput,hashBytes);
			encryptedOutput = executeRounds(encryptedOutput,hashBytes);
			
		}
		
		
		out = new FileOutputStream("Encrypted.txt");
		for(int x=0;x<encryptedOutput.size();x++)
		{
			out.write(encryptedOutput.get(x));
		}
		
		out.close();
		
		return encryptedOutput;
		
	}
	
	public ArrayList<byte[]> decrypt(String userPassHash) throws IOException
	{
		generateSubKeys(userPassHash);
		decryptedOutput = unexecuteRounds(encryptedOutput,hashBytes);
		decryptedOutput=unXorBlockAndKey(decryptedOutput,hashBytes);
		
		for(int x=0;x<numRounds;x++)
		{
			decryptedOutput = unexecuteRounds(encryptedOutput,hashBytes);
			decryptedOutput=unXorBlockAndKey(decryptedOutput,hashBytes);
			
		}
		
		
		System.out.println();
		 System.out.println("Unencrypted:\t");
		for(int x=0;x<decryptedOutput.size();x++)
		{
			byte holder[] =new byte[4];
			 holder = decryptedOutput.get(x);
			
			 System.out.print((char)(holder[0]+holder[1]+holder[2]+holder[3]));
		}//*/
		
		FileOutputStream out = null;
		out = new FileOutputStream("Decrypted.txt");
		
		for(int x=0;x<encryptedOutput.size();x++)
		{
			out.write(decryptedOutput.get(x));
		}
		out.close();
		return decryptedOutput;
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
			}//*/
			hashBytes.add(bytes);
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
	
	private ArrayList<byte[]> xorBlockAndKey(ArrayList<byte[]> blockArray, ArrayList<byte[]> keyArray){
		
		ArrayList<byte[]> xorBlockKey = new ArrayList<byte[]>();
		
		for(int x= 0;x<blockArray.size();x++)
		{
			byte xorByte[]=new byte[4];
			byte blockBytes[] = blockArray.get(x);
			byte keyBytes[] = keyArray.get(x%keyArray.size());
			
			
			for(int y =0;y<blockArray.get(x).length;y++)
			{
				//Debug prints
				//System.out.print("Block "+blockBytes[y]);
				//System.out.print("\tKey "+keyBytes[y]);
				xorByte[y] = (byte)(blockBytes[y]^keyBytes[y]);
				//System.out.println("\tXOR "+xorByte[y]);
			}
			
		 xorBlockKey.add(xorByte);
		// System.out.println("OUTPUT"+(xorByte[0]+xorByte[1]+xorByte[2]+xorByte[3]));
		 
		}
		/*for(int x= 0;x<xorBlockKey.size();x++)
		{
			byte[] holder =new byte[4];
			 holder = xorBlockKey.get(x);
			 System.out.println(holder[0]);
				System.out.println(holder[1]);
				System.out.println(holder[2]);
				System.out.println(holder[3]);
		}*/
		return xorBlockKey;
	}
	
	private ArrayList<byte[]> unXorBlockAndKey(ArrayList<byte[]> blockArray, ArrayList<byte[]> keyArray) {
		ArrayList<byte[]> unXorBlockKey = new ArrayList<byte[]>();
		
		for(int x= 0;x<blockArray.size();x++)
		{
			byte xorByte[]=new byte[4];
			byte blockBytes[] = blockArray.get(x);
			byte keyBytes[] = keyArray.get(x%keyArray.size());
			
			
			for(int y =0;y<blockArray.get(x).length;y++)
			{
				//Debug prints
				//System.out.print("Block "+blockBytes[y]);
				//System.out.print("\tKey "+keyBytes[y]);
				xorByte[y] = (byte)(blockBytes[y]^keyBytes[y]);
				//System.out.println("\tXOR "+xorByte[y]);
			}
			
		 unXorBlockKey.add(xorByte);
		 //System.out.println("OUTPUT"+(xorByte[0]+xorByte[1]+xorByte[2]+xorByte[3]));
		 
		}
		/*for(int x= 0;x<unXorBlockKey.size();x++)
		{
			byte[] holder =new byte[4];
			 holder = unXorBlockKey.get(x);
			 System.out.println(holder[0]);
				System.out.println(holder[1]);
				System.out.println(holder[2]);
				System.out.println(holder[3]);
		}//*/
		return unXorBlockKey;
	}
	
	private ArrayList<byte[]> executeRounds(ArrayList<byte[]> blockArray, ArrayList<byte[]> keyArray){
		
		ArrayList<byte[]> outputList = new ArrayList<byte[]>();
		
		//for(int x= 0;x<numRounds;x++)
		//{
			
			for(int y= 0;y<blockArray.size();y++)
			{
				byte holder[] =new byte[4];
				byte blockBytes[] = null;
				blockBytes = blockArray.get(y);
				/*System.out.println(blockBytes[0]);
				System.out.println(blockBytes[1]);
				System.out.println(blockBytes[2]);
				System.out.println(blockBytes[3]);*/
				
				for(int z =0;z<blockArray.get(y).length;z++)
				{
					holder[z]=(byte) ~blockBytes[z];
					//System.out.print("OUTPUT"+blockBytes[z]);
					//System.out.println("\t"+holder[z]);
				}
				
					outputList.add(holder);
						
			}
			/*for(int e= 0;e<encryptedOutput.size();e++)
			{
				byte xorByte[]=new byte[4];
				byte formBytes[] = blockArray.get(e);
				byte keyBytes[] = keyArray.get(e%keyArray.size());
				
				for(int y =0;y<blockArray.get(x).length;y++)
				{
					//Debug prints
					//System.out.print("Block "+blockBytes[y]);
					//System.out.print("\tKey "+keyBytes[y]);
					//System.out.println("\tXOR "+(char)xorByte[y]);
					xorByte[y] = (byte)(formBytes[y]^keyBytes[y]);
				}
				
			 xorBlockKey.add(xorByte);
			// System.out.println("OUTPUT"+(xorByte[0]+xorByte[1]+xorByte[2]+xorByte[3]));
			 
			}*/
			
		//}//*///numrounds
		return outputList;
	}
	
	
	public ArrayList<byte[]> unexecuteRounds(ArrayList<byte[]> blockArray, ArrayList<byte[]> keyArray){
		ArrayList<byte[]> outputList = new ArrayList<byte[]>();
		//for(int x= 0;x<numRounds;x++)
		//{
			
			
			for(int y= 0;y<blockArray.size();y++)
			{
				byte holder[] =new byte[4];
				byte blockBytes[] = null;
				blockBytes = blockArray.get(y);
				/*System.out.println(holder[0]);
				System.out.println(holder[1]);
				System.out.println(holder[2]);
				System.out.println(holder[3]);*/
				
				for(int z =0;z<blockArray.get(y).length;z++)
				{
					holder[z]=(byte) ~blockBytes[z];
					//System.out.print("UNREVERSED"+blockBytes[z]);
					//System.out.println("\t"+holder[z]);
				}
				
				outputList.add(holder);
				
			}
	
	//}
		return outputList;
}
}//CLASS END
