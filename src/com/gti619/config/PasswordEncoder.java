package com.gti619.config;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.codec.Base64;




public class PasswordEncoder {

	/*  public static void main(String []args){
	         
	        String passwordEnc=null;
			try {
				//passwordEnc = AESencrypt("");
				passwordEnc = AESdecrypt("");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        System.out.println("Encrypted Text : " + passwordEnc);
	     }*/


	public static String MD5encrypt(String plainText) {

		byte byteData[] = plainText.getBytes();

		//convert the byte to hex format method 2
		StringBuffer hexString = new StringBuffer();
		for (int i=0;i<byteData.length;i++) {
			String hex=Integer.toHexString(0xff & byteData[i]);
			if(hex.length()==1) hexString.append('0');
			hexString.append(hex);
		}


		return hexString.toString();
	}

	public static String AESencrypt(String Data) throws Exception {
		 try
	        {
	            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	        
	            cipher.init(Cipher.ENCRYPT_MODE, AESgenerateKey());
	        
	         
	           return new String(Base64.encode(cipher.doFinal(Data.getBytes("UTF-8"))));
	        
	        }
	        catch (Exception e)
	        {
	           
	            System.out.println("Error while encrypting: "+e.toString());
	        }
	        return null;

    }

    public static String AESdecrypt(String encryptedData) throws Exception {
    	try
        {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
           
            cipher.init(Cipher.DECRYPT_MODE, AESgenerateKey());
            return new String(cipher.doFinal(Base64.decode(encryptedData.getBytes())));
            
        }
        catch (Exception e)
        {
         
            System.out.println("Error while decrypting: "+e.toString());
        }
        return null;
    }
    
    
	private static Key AESgenerateKey() throws Exception {
		String myKey = "!@#we$%@($dfsdf34237%#&($we#^@";
		byte[] key ;
		SecretKeySpec secretKey ;
		 MessageDigest sha = null;
	        try {
	            key = myKey.getBytes("UTF-8");
	            System.out.println(key.length);
	            sha = MessageDigest.getInstance("SHA-1");
	            key = sha.digest(key);
	            key = Arrays.copyOf(key, 16); // use only first 128 bit
	            System.out.println(key.length);
	            System.out.println(new String(key,"UTF-8"));
	            secretKey = new SecretKeySpec(key, "AES");
	            return secretKey;
	            
	        } catch (NoSuchAlgorithmException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
			return null;

	}



}