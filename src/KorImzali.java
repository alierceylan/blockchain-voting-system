import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.swing.AbstractAction;
import javax.swing.Action;

import main.java.com.vaicoin.server.ProtoClientRequest;
import main.java.com.vaicoin.server.Server;

import org.bouncycastle.crypto.CryptoException;

import main.java.com.vaicoin.client.Client;
import sun.applet.Main;
import tr.gov.tubitak.uekae.esya.api.common.ESYAException;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartCardException;
import tr.gov.tubitak.uekae.esya.asn.util.AsnIO;


public class KorImzali extends JFrame {

	private JPanel contentPane;
	Server server;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KorImzali frame = new KorImzali();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ESYAException 
	 * @throws CryptoException 
	 * @throws NoSuchPaddingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws SmartCardException 
	 * @throws InvalidKeyException 
	 */
	public KorImzali(byte[] Kor_imza_message,byte[] Kor_imza_signature,ProtoClientRequest clientRequest,Server server) throws InvalidKeyException, SmartCardException, NoSuchAlgorithmException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, CryptoException, ESYAException, IOException {
	 
		

		Sifreleme sf=new Sifreleme();
		setTitle("e-Oy Pusulas\u0131");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblifrelenmiKimlikBilgileri = new JLabel("Kör Ýmzalý Oy Pusulasý:");
		lblifrelenmiKimlikBilgileri.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblifrelenmiKimlikBilgileri.setBounds(40, 31, 348, 48);
		contentPane.add(lblifrelenmiKimlikBilgileri);
		
		JTextArea textPane = new JTextArea();
		textPane.setBounds(40, 79, 327, 122);
		textPane.setText(sf.bytesToHex(Kor_imza_message));
		contentPane.add(textPane);
		textPane.setEditable(false);
		textPane.setLineWrap(true);
		textPane.setWrapStyleWord(true); 
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JTextArea textPane_1 = new JTextArea();
		textPane_1.setBounds(40, 243, 332, 79);
		textPane_1.setText(sf.bytesToHex(Kor_imza_signature));
		contentPane.add(textPane_1);
		textPane_1.setEditable(false);
		textPane_1.setLineWrap(true);
		textPane_1.setWrapStyleWord(true); 
		textPane_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel lblImza = new JLabel("\u0130mza:");
		lblImza.setBounds(40, 214, 56, 16);
		lblImza.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblImza);
		
		
		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFrame parentFrame = new JFrame();
				 
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a file to save");   
				 
				int userSelection = fileChooser.showSaveDialog(parentFrame);
				 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fileChooser.getSelectedFile();
				    
				   
				    try {
						AsnIO.dosyayaz(Kor_imza_message, fileToSave.getAbsolutePath()+".der");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    
				}
				
			}});
		
		btnKaydet.setBounds(67, 370, 264, 25);
		contentPane.add(btnKaydet);
		
		
		
		JButton btnNewUnblind = new JButton("Köreltmayi kaldýr");
		btnNewUnblind.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				Client client = clientRequest.createClient(Kor_imza_signature);
				boolean valid = server.verify(client);
				Unblind ub=new Unblind(client, Kor_imza_message, Kor_imza_signature,clientRequest,server);
				ub.setVisible(true);
				
			
			
		}});
		btnNewUnblind.setBounds(67, 400, 264, 25);
		contentPane.add(btnNewUnblind);
	}


	public KorImzali() {
		// TODO Auto-generated constructor stub
	}


	
}
