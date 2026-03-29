/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vaicoin.server.impl;

import main.java.com.vaicoin.client.Client;
import main.java.com.vaicoin.client.ClientRequest;
import main.java.com.vaicoin.server.Server;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.signers.PSSSigner;

/**
 *
 * @author vaibhav
 */
public class ServerImpl implements Server {

    private final AsymmetricCipherKeyPair keys;

    public ServerImpl(AsymmetricCipherKeyPair keys) {
        this.keys = keys;
    }

    @Override
    public RSAKeyParameters getPublic() {
        return (RSAKeyParameters) keys.getPublic();
    }

    @Override
    public byte[] sign(ClientRequest clientRequest) {
        // Sign the client request using server's private key.
        byte[] message = clientRequest.getMessage();
//        System.out.println("Servera gelen mesaj: "+ bytesToHex(message));
        RSAEngine engine = new RSAEngine();
        engine.init(true, keys.getPrivate());
//        System.out.println("Merhaba'nýn imzalanmasý: "+bytesToHex(engine.processBlock("merhaba".getBytes(), 0, "merhaba".getBytes().length)));
        return engine.processBlock(message, 0, message.length);
    }

    @Override
    public boolean verify(Client client) {
        // Verify that the client has a valid signature using server's public key.
        byte[] id = client.getID();
        byte[] signature = client.getSignature();

        PSSSigner signer = new PSSSigner(new RSAEngine(), new SHA1Digest(), 20);
        signer.init(false, keys.getPublic());

        signer.update(id, 0, id.length);

        return signer.verifySignature(signature);
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

}
