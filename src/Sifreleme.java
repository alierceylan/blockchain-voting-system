import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class Sifreleme {
	static String dosya="C:\\BlockchainSeries1-master";
	public static void main(String args[]) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException{
		
		 Sifreleme sf=new Sifreleme();
		 PublicKey publicKey = sf.readPublicKey("public.der");
	     PrivateKey privateKey = sf.readPrivateKey("private.der");
	     byte[] message = "A partisi".getBytes("UTF8");
	     byte[] secret = sf.encrypt(publicKey, message);
	     byte[] recovered_message = sf.decrypt(privateKey, secret);
	     System.out.println(new String(recovered_message, "UTF8"));
	}
	public static byte[] sifrele(String isim) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException{
		
		 Sifreleme sf=new Sifreleme();
		 PublicKey publicKey = sf.readPublicKey("public.der");
	     
	     byte[] message =isim.getBytes("UTF8");
	     byte[] secret = sf.encrypt(publicKey, message);
	   
	     return secret;
	}
	
	public static byte[] Coz(byte[] secret) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException{
		
		 Sifreleme sf=new Sifreleme();
		 PrivateKey privateKey = sf.readPrivateKey("private.der");
		 byte[] recovered_message = sf.decrypt(privateKey, secret);

	   
	     return recovered_message;
	}
	
	public static byte[] readFileBytes(String filename) throws IOException 
	{
	    Path path = Paths.get(filename);
	    return Files.readAllBytes(path);        
	}

	public PublicKey readPublicKey(String filename) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException
	{
	    X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(readFileBytes(dosya +"\\Ali_sifreleme\\public.der"));
	    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	    return keyFactory.generatePublic(publicSpec);       
	}

	public PrivateKey readPrivateKey(String filename) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException{
	    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(readFileBytes(dosya+"\\Ali_sifreleme\\private.der"));
	    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	    return keyFactory.generatePrivate(keySpec);     
	}
	
	public byte[] encrypt(PublicKey key, byte[] plaintext) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException 
	{
	    Cipher cipher = Cipher.getInstance("RSA");   
	    cipher.init(Cipher.ENCRYPT_MODE, key);  
	    return cipher.doFinal(plaintext);
	}

	public byte[] decrypt(PrivateKey key, byte[] ciphertext) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException 
	{
	    Cipher cipher = Cipher.getInstance("RSA");   
	    cipher.init(Cipher.DECRYPT_MODE, key);  
	    return cipher.doFinal(ciphertext);
	}
////Byte to Hex String
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
    public static PublicKey getPublic(String filename)
		    throws Exception {

		    byte[] keyBytes = Files.readAllBytes(Paths.get(filename));

		    X509EncodedKeySpec spec =new X509EncodedKeySpec(keyBytes);
		    KeyFactory kf = KeyFactory.getInstance("RSA");
		    return kf.generatePublic(spec);
		  }
		  
	public static PrivateKey getPrivate(String filename)throws Exception {

		    byte[] keyBytes = Files.readAllBytes(Paths.get(filename));

			PKCS8EncodedKeySpec spec =new PKCS8EncodedKeySpec(keyBytes);
		    KeyFactory kf = KeyFactory.getInstance("RSA");
			return kf.generatePrivate(spec);
					  }
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
		
}
