import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;
import tr.gov.tubitak.uekae.esya.asn.util.AsnIO;
import main.java.com.vaicoin.client.Client;
import main.java.com.vaicoin.server.ProtoClientRequest;
import main.java.com.vaicoin.server.Server;

import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;

import javax.swing.JPasswordField;


public class Unblind extends JFrame {

	private JPanel contentPane;
	private JTextField textmuh;
	private JTextField textilce;
	private JTextField textbel_mec;
	private JTextField text_buyuksehir;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Unblind frame = new Unblind();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Unblind() {
		

			
	}
	/**
	 * Create the frame.
	 */

	public Unblind(Client client,byte[] Kor_imza_message,byte[] Kor_imza_signature,ProtoClientRequest clientRequest,Server server) {
		
		Sifreleme sf=new Sifreleme();
		
	
		String s = new String(client.getID(), StandardCharsets.UTF_8);
		System.out.println(s);
		String[] splitStr = s.split("\\s+");
		
		
		setTitle("e-Oy Pusulas\u0131");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblKimlikBilgileri = new JLabel("\u015Eifreli Kimlik Bilgileri:");
		lblKimlikBilgileri.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblKimlikBilgileri.setBounds(26, 26, 261, 22);
		getContentPane().add(lblKimlikBilgileri);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(26, 61, 379, 110);
		textArea.setText(splitStr[0]);
		getContentPane().add(textArea);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true); 
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		
		JLabel lblOyBilgileri = new JLabel("Oy Bilgileri");
		lblOyBilgileri.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOyBilgileri.setBounds(26, 200, 217, 30);
		getContentPane().add(lblOyBilgileri);
		
		JButton btnNewButton = new JButton("Þifre Çöz");
		btnNewButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				
				if( textArea.getText().toString().contentEquals(splitStr[0]))
				{char[] input = passwordField.getPassword();
			    String passString = new String(input);  
			    
			    if(!(passString.equals("123456")))
			    {			     
					JOptionPane.showMessageDialog(null, "Lütfen Parolanýzý Giriniz!", "InfoBox: " + "Uyarý!", JOptionPane.INFORMATION_MESSAGE);
				
				}
				else{
								
				try {
					byte[] veri = sf.Coz(sf.hexStringToByteArray(splitStr[0]));
					String s_veri = new String( veri , StandardCharsets.UTF_8);
					textArea.setText(s_veri);
					
				} catch (InvalidKeyException | NoSuchAlgorithmException
						| InvalidKeySpecException | IllegalBlockSizeException
						| BadPaddingException | NoSuchPaddingException
						| IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}}
				else{textArea.setText(splitStr[0]);}
			}
			
		});
		btnNewButton.setBounds(228, 184, 130, 22);
		getContentPane().add(btnNewButton);
		
		JLabel lblMuhtarlk = new JLabel("Muhtarl\u0131k:");
		lblMuhtarlk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMuhtarlk.setBounds(26, 230, 95, 22);
		getContentPane().add(lblMuhtarlk);
		
		JLabel lblIleBelediyesi = new JLabel("\u0130l\u00E7e Belediyesi:");
		lblIleBelediyesi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIleBelediyesi.setBounds(26, 260, 95, 22);
		getContentPane().add(lblIleBelediyesi);
		
		JLabel lblBelediyeMeclisi = new JLabel("Belediye Meclisi");
		lblBelediyeMeclisi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBelediyeMeclisi.setBounds(26, 290, 95, 22);
		getContentPane().add(lblBelediyeMeclisi);
		
		JLabel lblBykehirBelediyesi = new JLabel("B\u00FCy\u00FCk\u015Fehir Belediyesi");
		lblBykehirBelediyesi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBykehirBelediyesi.setBounds(26, 320, 160, 22);
		getContentPane().add(lblBykehirBelediyesi);
		
		textmuh = new JTextField();
		textmuh.setBounds(183, 230, 205, 22);
		textmuh.setText(splitStr[1]+" "+splitStr[2]);
		getContentPane().add(textmuh);
		textmuh.setColumns(10);
		
		textilce = new JTextField();
		textilce.setColumns(10);
		textilce.setBounds(183, 260, 205, 22);
		textilce.setText(splitStr[3]+" "+splitStr[4]);
		getContentPane().add(textilce);
		
		textbel_mec = new JTextField();
		textbel_mec.setColumns(10);
		textbel_mec.setBounds(183, 290, 205, 22);
		textbel_mec.setText(splitStr[5]+" "+splitStr[6]);
		getContentPane().add(textbel_mec);
		
		text_buyuksehir = new JTextField();
		text_buyuksehir.setColumns(10);
		text_buyuksehir.setBounds(183, 320, 205, 22);
		text_buyuksehir.setText(splitStr[7]+" "+splitStr[8]);
		getContentPane().add(text_buyuksehir);
		// TODO Auto-generated constructor stub
		
		JButton btnNewButton_1 = new JButton("Ýmza Doðrula");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Client client = clientRequest.createClient(Kor_imza_signature);
				boolean valid = server.verify(client);
				System.out.println("doðrulanabiliyor mu?    "+ valid);
				String sonuc;
				if(valid){
					sonuc="Ýmza Doðrulanmýþtýr.";	
				}
				else{
					sonuc="Ýmza Doðrulama Baþarýsýz.";
				}
				
					JOptionPane.showMessageDialog(null, sonuc, "InfoBox: " + "Uyarý!", JOptionPane.INFORMATION_MESSAGE);
				
				
			}
		});
		btnNewButton_1.setBounds(26, 475, 160, 20);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Kaydet");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame parentFrame = new JFrame();
				 
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a file to save");   
				 
				int userSelection = fileChooser.showSaveDialog(parentFrame);
				 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fileChooser.getSelectedFile();
				    
				   
				    try {
						AsnIO.dosyayaz(client.getSignature(), fileToSave.getAbsolutePath()+".der");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    
				}						
				
			}
		});
		
		btnNewButton_2.setBounds(200, 475, 160, 20);
		getContentPane().add(btnNewButton_2);
		
		
		JButton btnNewButton_3 = new JButton("Oy Kullan");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestBlockchain tb=new TestBlockchain();
				ArrayList<String> blok_bilg = tb.Blok_Olustur(client,splitStr);
				Show_block sb=new Show_block( client,splitStr,blok_bilg);
				sb.setVisible(true);
				
			}
		});
		
		btnNewButton_3.setBounds(26, 500, 340, 20);
		getContentPane().add(btnNewButton_3);
		
		
		
		
		JLabel imza= new JLabel("Ýmza:");
		imza.setFont(new Font("Tahoma", Font.PLAIN, 18));
		imza.setBounds(26, 340, 261, 22);
		getContentPane().add(imza);
		
		
		
		JTextArea textArea1 = new JTextArea();
		textArea1.setBounds(26, 360, 379, 105);
		textArea1.setText(sf.bytesToHex(client.getSignature()));
		getContentPane().add(textArea1);
		textArea1.setEditable(false);
		textArea1.setLineWrap(true);
		textArea1.setWrapStyleWord(true); 
		textArea1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(94, 184, 104, 22);
		contentPane.add(passwordField);
		
		JLabel lblParola = new JLabel("Parola:");
		lblParola.setBounds(36, 184, 56, 16);
		contentPane.add(lblParola);
		
		
		
	}
}
