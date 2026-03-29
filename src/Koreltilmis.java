import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;

import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;

import main.java.com.vaicoin.client.ClientRequest;

import tr.gov.tubitak.uekae.esya.api.common.ESYAException;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartCardException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import main.java.com.vaicoin.server.*;
import main.java.com.vaicoin.server.impl.ServerImpl;
import main.java.com.vaicoin.utils.Utils;

public class Koreltilmis extends JFrame {

	private JPanel contentPane;
	public byte[] Kor_imza_message,Kor_imza_signature;
	public byte[] koreltilmis;
	Server server;
	
	ProtoClientRequest clientRequest;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Koreltilmis frame = new Koreltilmis();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws CryptoException 
	 * @throws IOException 
	 * @throws ESYAException 
	 * @throws NoSuchPaddingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws SmartCardException 
	 * @throws InvalidKeyException 
	 */
	public Koreltilmis() throws CryptoException, InvalidKeyException, SmartCardException, NoSuchAlgorithmException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, ESYAException, IOException {
		BlockGui bg=new BlockGui();
		Sifreleme sf=new Sifreleme();
		
		server =  new ServerImpl(Utils.generateKeyPair());		
		RSAKeyParameters a = server.getPublic();		
		String koreltilecek = bg.sifreli_kimlik_hex+" "+bg.oy_bilgileri;
	    clientRequest = new ProtoClientRequest( a,koreltilecek );
	   	   	    
		koreltilmis = clientRequest.generateClientRequest1();
		
		Kor_imza_message = clientRequest.generateClientRequest().getMessage();
		Kor_imza_signature = server.sign(clientRequest.generateClientRequest());
		
		//Köreltme
		
		setTitle("e-Oy Pusulas\u0131");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblifrelenmiKimlikBilgileri = new JLabel("K\u00F6reltilmi\u015F Veri:");
		lblifrelenmiKimlikBilgileri.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblifrelenmiKimlikBilgileri.setBounds(40, 31, 348, 48);
		contentPane.add(lblifrelenmiKimlikBilgileri);
		
		
		JTextArea textPane = new JTextArea();
		textPane.setBounds(40, 73, 354, 255);
		textPane.setText(sf.bytesToHex(koreltilmis));
		contentPane.add(textPane);
		textPane.setEditable(false);
		textPane.setLineWrap(true);
		textPane.setWrapStyleWord(true); 
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		
		
		JButton btnNewButton = new JButton("Ýmzalat");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KorImzali KorImza = null;
				try {
					KorImza = new KorImzali(Kor_imza_message, Kor_imza_signature,clientRequest,server);
				} catch (InvalidKeyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SmartCardException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidKeySpecException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalBlockSizeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BadPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CryptoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ESYAException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				KorImza.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(82, 366, 271, 25);
		contentPane.add(btnNewButton);
		
	}

}
