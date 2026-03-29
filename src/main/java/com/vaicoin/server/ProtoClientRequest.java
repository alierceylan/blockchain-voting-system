/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vaicoin.server;

import main.java.com.vaicoin.client.Client;
import main.java.com.vaicoin.client.ClientRequest;
import main.java.com.vaicoin.client.impl.ClientImpl;
import main.java.com.vaicoin.client.impl.ClientRequestImpl;
import main.java.com.vaicoin.utils.Utils;
import java.math.BigInteger;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.engines.RSABlindingEngine;
import org.bouncycastle.crypto.generators.RSABlindingFactorGenerator;
import org.bouncycastle.crypto.params.RSABlindingParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.PSSSigner;

/**
 *
 * @author vaibhav
 */
public class ProtoClientRequest {
	private final byte[] clientID;
	private final RSABlindingParameters blindingParams;

	public ProtoClientRequest(RSAKeyParameters pub,String clientID1) {
		// Create a 128-bit globally unique ID for the client.
//		clientID = Utils.getRandomBytes(16);
		
		clientID = clientID1.getBytes();
        
		// Generate a blinding factor using the server's public key.
		RSABlindingFactorGenerator blindingFactorGenerator
			= new RSABlindingFactorGenerator();
		blindingFactorGenerator.init(pub);

		BigInteger blindingFactor
			= blindingFactorGenerator.generateBlindingFactor();

		blindingParams = new RSABlindingParameters(pub, blindingFactor);
	}
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

	public ClientRequest generateClientRequest() throws CryptoException {
		// "Blind" the client and generate a client request to be signed by the
		// server.
		PSSSigner signer = new PSSSigner(new RSABlindingEngine(),
				new SHA1Digest(), 20);
		signer.init(true, blindingParams);

		signer.update(clientID, 0, clientID.length);

		byte[] sig = signer.generateSignature();

		return new ClientRequestImpl(sig);
	}
	public byte[] generateClientRequest1() throws CryptoException {
		// "Blind" the client and generate a client request to be signed by the
		// server.
		PSSSigner signer = new PSSSigner(new RSABlindingEngine(),
				new SHA1Digest(), 20);
		signer.init(true, blindingParams);

		signer.update(clientID, 0, clientID.length);

		byte[] sig = signer.generateSignature();

		return sig;
	}

	public Client createClient(byte[] signature) {
		// "Unblind" the server's signature (so to speak) and create a new client
		// using the ID and the unblinded signature.
		RSABlindingEngine blindingEngine = new RSABlindingEngine();
		blindingEngine.init(false, blindingParams);

		byte[] s = blindingEngine.processBlock(signature, 0, signature.length);
		
		ClientImpl unblind = new ClientImpl(clientID, s);
		
		
		return unblind;
	}
}
