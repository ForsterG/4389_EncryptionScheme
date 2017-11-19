import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;


public class EncryptionHandler {
	
	private int numRounds;
	public String encryptedText="EncryptRound1";
	
	public EncryptionHandler(int numRounds, String userHashPassword,String falseHashPassword,ArrayList<Integer> userFile) throws IOException{
		this.numRounds=numRounds;
		
		ArrayList<byte[]> halfBlockArray = new ArrayList<byte[]>();
		halfBlockArray= splitIntoHalfBlocks(userFile);
		
		ArrayList<byte[]> hashBlockArray = new ArrayList<byte[]>();
		hashBlockArray = generateSubKeys(userHashPassword);
		
		ArrayList<byte[]> encryptedMain = new ArrayList<byte[]>();
		encryptedMain = encrypt(halfBlockArray,hashBlockArray,encryptedText,this.numRounds);
		
		ArrayList<byte[]> decryptedMain = new ArrayList<byte[]>();
		decryptedMain =decrypt(encryptedMain,hashBlockArray,"DecryptRound1.txt");
		
		hashBlockArray = generateSubKeys(falseHashPassword);
		decryptedMain =decrypt(encryptedMain,hashBlockArray,"DecryptFalseRound1.txt");
		
		/*System.out.println("ENCRYPTED:\t");
		for(int x=0;x<encryptedMain.size();x++)
		{
			byte holder[] =new byte[4];
			 holder = encryptedMain.get(x);
			 
			 System.out.print((holder[0]+holder[1]+holder[2]+holder[3]));
		}//*/
		/*System.out.println("DENCRYPTED:\t");
		for(int x=0;x<decryptedMain.size();x++)
		{
			byte holder[] =new byte[4];
			 holder = decryptedMain.get(x);
			 
			 System.out.print((char)(holder[0]+holder[1]+holder[2]+holder[3]));
		}//*/
		
		
	}
	
	public ArrayList<byte[]> encrypt(ArrayList<byte[]> fileBytes, ArrayList<byte[]> hashBytes,String fileName,int number) throws IOException{
		
		ArrayList<byte[]> encryptedOutput = new ArrayList<byte[]>();
		FileOutputStream out = null;
		out = new FileOutputStream(fileName+".txt");
		
		encryptedOutput = xorBlockAndKey(fileBytes, hashBytes);
		
		for(int x=0;x<encryptedOutput.size();x++)
		{
			out.write(encryptedOutput.get(x));
		}
		
		encryptedOutput = executeRounds(encryptedOutput, hashBytes);
		
		out = new FileOutputStream(fileName+"_ 2.txt");
		
		for(int x=0;x<encryptedOutput.size();x++)
		{
			out.write(encryptedOutput.get(x));
		}
			
		out.close();
		
		return encryptedOutput;
	}
	
	public ArrayList<byte[]> decrypt(ArrayList<byte[]> fileBytes, ArrayList<byte[]> hashBytes,String fileName) throws IOException
	{
		
		ArrayList<byte[]> decryptedOutput = new ArrayList<byte[]>();
		
		decryptedOutput = executeRounds(fileBytes,hashBytes);
		decryptedOutput = xorBlockAndKey(decryptedOutput, hashBytes);
		
	
		
		FileOutputStream out = null;
		out = new FileOutputStream(fileName);
		
		for(int x=0;x<decryptedOutput.size();x++)
		{
			out.write(decryptedOutput.get(x));
		}
		/*decryptedOutput=unXorBlockAndKey(decryptedOutput,hashBytes);
		
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
		}//
		
		FileOutputStream out = null;
		out = new FileOutputStream("Decrypted.txt");
		
		for(int x=0;x<encryptedOutput.size();x++)
		{
			out.write(decryptedOutput.get(x));
		}*/
		out.close();
		return decryptedOutput;
	}
	
	
	public ArrayList<byte[]> generateSubKeys(String userPassHash){
		
		ArrayList<byte[]> fileBytes = new ArrayList<byte[]>();
		ArrayList<Integer> fileChars=new ArrayList<Integer>();
		char[] hashChar;
		hashChar =userPassHash.toCharArray();
		
		for(int x=0;x<hashChar.length;x++)
		{
			fileChars.add((int)hashChar[x]);
			//System.out.println(fileChars.get(x));
			byte[] bytes = ByteBuffer.allocate(Integer.SIZE/8).putInt(fileChars.get(x)).array();
			/*for(int y=0;y<bytes.length;y++)
			{
				System.out.println((char)bytes[y]);
			}//*/
			fileBytes.add(bytes);
		}
		return fileBytes;
	}

	
	
	private ArrayList<byte[]> splitIntoHalfBlocks(ArrayList<Integer> fileChars){
	
		ArrayList<byte[]> fileBytes = new ArrayList<byte[]>();
		for(int x = 0; x<fileChars.size(); x++ )
		{
			byte[] bytes = ByteBuffer.allocate(Integer.SIZE/8).putInt(fileChars.get(x)).array();
			fileBytes.add(bytes);
			
			//Debug statement to list half-blocks
			/*for(int y= 0;y<bytes.length;y++)
			{
				System.out.print(y+"\t");
				System.out.println((bytes[y]);
			}
			/*for (byte b : bytes) {
				   System.out.format("0x%x ", b);
				   
				}
			System.out.println();//*/
		}
		return fileBytes;
		
	}
	
	private ArrayList<byte[]> xorBlockAndKey(ArrayList<byte[]> blockArray, ArrayList<byte[]> keyArray){
		
		ArrayList<byte[]> xorBlockKey = new ArrayList<byte[]>();
		
		for(int x= 0;x<blockArray.size();x++)
		{
			byte xorByte[]=new byte[4];
			byte blockBytes[] = blockArray.get(x);
			//System.out.println("blockBytes "+(char)+(blockBytes[0]+blockBytes[1]+blockBytes[2]+blockBytes[3]));
			byte keyBytes[] = keyArray.get(x%keyArray.size());
			//System.out.print((char)+(keyBytes[0]+keyBytes[1]+keyBytes[2]+keyBytes[3]));
			
			for(int y =0;y<blockArray.get(x).length;y++)
			{
				//Debug prints
				//System.out.print("Block "+blockBytes[y]);
				//System.out.print("\tKey "+keyBytes[y]);
				xorByte[y] = (byte)(blockBytes[y]^keyBytes[y]);
				//System.out.println("\tXOR "+xorByte[y]);
			}
			
		 xorBlockKey.add(xorByte);
		 //System.out.println("OUTPUT"+(xorByte[0]+xorByte[1]+xorByte[2]+xorByte[3]));
		 
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
	
	
	private ArrayList<byte[]> unexecuteRounds(ArrayList<byte[]> blockArray, ArrayList<byte[]> keyArray){
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
