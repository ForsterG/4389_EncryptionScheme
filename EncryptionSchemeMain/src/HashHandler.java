import java.security.*;
import java.lang.StringBuilder;

class HashHandler{
    /*public static void main(String []args) throws NoSuchAlgorithmException{
        
        String filecontent= "file"; // user entered password;
        SHA256(filecontent);
        
    }//main*/
    
    // check
    public HashHandler() {
    	
    }
    
    public String generateHash(String userPassword) throws NoSuchAlgorithmException {
    	
		return SHA256(userPassword);
    }
    
    private String SHA256(String pass) throws NoSuchAlgorithmException{
        
        //generates a random sequence Byte to append to the hash.
        SecureRandom secure = SecureRandom.getInstance("SHA1PRNG");
        byte salt[]= new byte[16];
        secure.nextBytes(salt);
         String generatedHash=null;
       try{
            MessageDigest mess= MessageDigest.getInstance("SHA-256");
            mess.update(salt);
            byte[] mbyte=mess.digest(pass.getBytes());
            StringBuilder sb= new StringBuilder();
            for (int i=0; i<mbyte.length; i++){
                
                sb.append(Integer.toString((mbyte[i]&0xff)+0x100,16).substring(1));
            }
            
            generatedHash=sb.toString();
            //System.out.println(generatedHash);//Debug print hash
            }
        catch(NoSuchAlgorithmException e){
            
        }
        
        return generatedHash;
        }
        
}//class
