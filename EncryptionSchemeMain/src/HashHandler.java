import java.security.*;
import java.lang.StringBuilder;

class HashHandler{
    public static void main(String args[]) throws NoSuchAlgorithmException{

        //String filecontent= userInput; // user entered password;
        //SHA256(filecontent);

    }//main
    private static byte salt[]=null;
    
    public static void setSalt(byte [] userSalt) {
    	salt = userSalt;
    }
 
    public static String SHA256(String pass) throws NoSuchAlgorithmException{

        //byte [] salt= returnSalt(); //returned salt
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
            //System.out.println(generatedHash);
            }
        catch(NoSuchAlgorithmException e){
            //
        }

        return generatedHash;
        }
    
////////////////////////////////////////////////////////////////////////////////////////////////
//  Function to return salt.
//
////////////////////////////////////////////////////////////////////////////////////////////////
      public static byte[] returnSalt(){
    	
        try{
        SecureRandom secure = SecureRandom.getInstance("SHA1PRNG");
        salt= new byte[16];
        secure.nextBytes(salt);
      }catch(NoSuchAlgorithmException e){
        //
      }

        return salt;

      }



}//class

