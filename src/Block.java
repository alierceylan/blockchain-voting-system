import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Block {


	private Date Timestamp;
	private String nonce;


	private String sifreli_kimlik;
	

	private String muht;
	private String ilce;
	private String bel_mec;
	private String buyuk_bel;
	private String imza;
	private String hash;
	private String oy;
	private String previousHash;
	
	public Block( Date timestamp, String nonce, String sifreli_kimlik, 
			String muht, String ilce, String bel_mec, String buyuk_bel, String imza) {
		
		this.Timestamp = timestamp;
		this.nonce=nonce;
		this.sifreli_kimlik=sifreli_kimlik;
		this.muht=muht;
		this.ilce=ilce;
		this.bel_mec=bel_mec;
		this.buyuk_bel=buyuk_bel;
		this.imza=imza;	
		this.oy=this.sifreli_kimlik+this.muht+this.ilce+this.bel_mec+this.buyuk_bel+this.imza;
		
		this.hash = computeHash();}

	



	public String computeHash() {
		
		String dataToHash = "" +this.Timestamp + this.nonce+this.oy+this.previousHash;
		
		MessageDigest digest;
		String encoded = null;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
			encoded = Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		this.hash = encoded;
		return encoded;
		
	}



	public Date getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(Date timestamp) {
		Timestamp = timestamp;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public String getNonce() {
		return nonce;
	}


	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	
	public String getSifreli_kimlik() {
		return sifreli_kimlik;
	}


	public void setSifreli_kimlik(String sifreli_kimlik) {
		this.sifreli_kimlik = sifreli_kimlik;
	}


	public String getMuht() {
		return muht;
	}


	public void setMuht(String muht) {
		this.muht = muht;
	}


	public String getIlce() {
		return ilce;
	}


	public void setIlce(String ilce) {
		this.ilce = ilce;
	}


	public String getBel_mec() {
		return bel_mec;
	}


	public void setBel_mec(String bel_mec) {
		this.bel_mec = bel_mec;
	}


	public String getBuyuk_bel() {
		return buyuk_bel;
	}


	public void setBuyuk_bel(String buyuk_bel) {
		this.buyuk_bel = buyuk_bel;
	}


	public String getImza() {
		return imza;
	}


	public void setImza(String imza) {
		this.imza = imza;
	}


	public String getOy() {
		return oy;
	}


	public void setOy(String oy) {
		this.oy = oy;
	}

	
}
