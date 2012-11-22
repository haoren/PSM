package psm;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Log;

public class Mesej {
	  public static String getMd5Hash(byte[] input) {
	        try{
	        	MessageDigest md;
	           	md = MessageDigest.getInstance("MD5");
	            md.update(input);
	                return getMd5Hash(md);
	        } catch(NoSuchAlgorithmException e) {
	                Log.e("MD5", e.getMessage());
	                return null;
	        }
	  }

	  public static String getMd5Hash(String string) {
	    return getMd5Hash(string.getBytes());
	  }
	  
	  public static String getMd5Hash(MessageDigest digest) {
	      BigInteger number = new BigInteger(1, digest.digest());
	      String md5 = number.toString(16);
	           
	      // need to pad the hash
	      while (md5.length() < 32)
	        md5 = "0" + md5;
	           
	      return md5;
	  }
	  
	  public static String getMd5Hash(InputStream is) {
	    byte[] buffer = new byte[8192];
	    int read = 0;
	    try {
	      MessageDigest digest = MessageDigest.getInstance("MD5");
	      while( (read = is.read(buffer)) > 0) {
	        digest.update(buffer, 0, read);
	      }
	      return getMd5Hash(digest);
	    } catch(IOException e) {
	      throw new RuntimeException("Unable to process file for MD5", e);
	    } catch (NoSuchAlgorithmException e) {
	      throw new RuntimeException("Unable to process file for MD5", e);
	    }
	  }
	  
	}