/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.vaicoin.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;

/**
 *
 * @author vaibhav
 */
public class Utils {

    public static byte[] getRandomBytes(int count) {
        byte[] bytes = new byte[count];
        new SecureRandom().nextBytes(bytes);
        return bytes;
    }
    
    // generate 2048-bit sized RSA key pair
    public static AsymmetricCipherKeyPair generateKeyPair() {
        RSAKeyPairGenerator gen = new RSAKeyPairGenerator();    
        gen.init(new RSAKeyGenerationParameters(new BigInteger("101011", 16),new SecureRandom(), 2048, 80));

     
//        KeyGenerationParameters a=new  KeyGenerationParameters(new SecureRandom(), 2048);
//        gen.init(a);
//      

        //gen.init(new RSAKeyGenerationParameters(BigInteger.valueOf(3), new SecureRandom(), 1024, 80));
        
        return gen.generateKeyPair();
    }
}
