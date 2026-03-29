import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.java.com.vaicoin.client.Client;
import tr.gov.tubitak.uekae.esya.asn.util.AsnIO;

import java.awt.SystemColor;


public class Show_block extends JFrame {

	private JPanel contentPane;

	private JTextField textmuh;
	private JTextField textilce;
	private JTextField textbel_mec;
	private JTextField text_buyuksehir;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Show_block frame = new Show_block();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Show_block(Client client,String[] splitStr,ArrayList<String> blok_bilg) {

		
		Sifreleme sf=new Sifreleme();
		
		setTitle("Oyun Bulunduðu Blok");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 822);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setForeground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblKimlikBilgileri = new JLabel("\u015Eifreli Kimlik Bilgileri:");
		lblKimlikBilgileri.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKimlikBilgileri.setBounds(131, 129, 261, 22);
		getContentPane().add(lblKimlikBilgileri);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(131, 164, 272, 76);
		textArea.setText(splitStr[0]);
		getContentPane().add(textArea);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true); 
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 7));
		
		
		JLabel lblOyBilgileri = new JLabel("Oy Bilgileri");
		lblOyBilgileri.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOyBilgileri.setBounds(131, 236, 217, 30);
		getContentPane().add(lblOyBilgileri);
		
		JLabel lblMuhtarlk = new JLabel("Muhtarl\u0131k:");
		lblMuhtarlk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMuhtarlk.setBounds(131, 266, 95, 22);
		getContentPane().add(lblMuhtarlk);
		
		JLabel lblIleBelediyesi = new JLabel("\u0130l\u00E7e Belediyesi:");
		lblIleBelediyesi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIleBelediyesi.setBounds(131, 299, 95, 22);
		getContentPane().add(lblIleBelediyesi);
		
		JLabel lblBelediyeMeclisi = new JLabel("Belediye Meclisi:");
		lblBelediyeMeclisi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBelediyeMeclisi.setBounds(128, 334, 95, 22);
		getContentPane().add(lblBelediyeMeclisi);
		
		JLabel lblBykehirBelediyesi = new JLabel("B\u00FCy\u00FCk\u015Fehir Bel:");
		lblBykehirBelediyesi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBykehirBelediyesi.setBounds(131, 371, 95, 22);
		getContentPane().add(lblBykehirBelediyesi);
		
		textmuh = new JTextField();
		textmuh.setBounds(225, 266, 178, 22);
		textmuh.setText(splitStr[1]+" "+splitStr[2]);
		getContentPane().add(textmuh);
		textmuh.setColumns(10);
		
		textilce = new JTextField();
		textilce.setColumns(10);
		textilce.setBounds(225, 301, 178, 22);
		textilce.setText(splitStr[3]+" "+splitStr[4]);
		getContentPane().add(textilce);
		
		textbel_mec = new JTextField();
		textbel_mec.setColumns(10);
		textbel_mec.setBounds(225, 336, 178, 22);
		textbel_mec.setText(splitStr[5]+" "+splitStr[6]);
		getContentPane().add(textbel_mec);
		
		text_buyuksehir = new JTextField();
		text_buyuksehir.setColumns(10);
		text_buyuksehir.setBounds(225, 371, 178, 22);
		text_buyuksehir.setText(splitStr[7]+" "+splitStr[8]);
		getContentPane().add(text_buyuksehir);
		// TODO Auto-generated constructor stub
		
		
		JLabel imza= new JLabel("Ýmza:");
		imza.setFont(new Font("Tahoma", Font.PLAIN, 15));
		imza.setBounds(131, 406, 225, 22);
		getContentPane().add(imza);
		
		
		
		JTextArea textArea1 = new JTextArea();
		textArea1.setBounds(131, 427, 272, 84);
		textArea1.setText(sf.bytesToHex(client.getSignature()));
		getContentPane().add(textArea1);
		textArea1.setEditable(false);
		textArea1.setLineWrap(true);
		textArea1.setWrapStyleWord(true); 
		textArea1.setFont(new Font("Tahoma", Font.PLAIN, 7));
		
		JLabel lblBlokIndex = new JLabel("Blok index:");
		lblBlokIndex.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBlokIndex.setBounds(26, 13, 132, 45);
		contentPane.add(lblBlokIndex);
		
		textField = new JTextField();
		textField.setText(blok_bilg.get(0));
		textField.setBounds(183, 26, 205, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblZamanDamgas = new JLabel("Zaman Damgas\u0131:");
		lblZamanDamgas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblZamanDamgas.setBounds(26, 51, 132, 22);
		contentPane.add(lblZamanDamgas);
		
		textField_1 = new JTextField();
		textField_1.setText(blok_bilg.get(1));
		textField_1.setBounds(183, 53, 205, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNonce = new JLabel("Nonce:");
		lblNonce.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNonce.setBounds(26, 78, 132, 22);
		contentPane.add(lblNonce);
		
		JLabel lblPrev = new JLabel("Prev_Hash:");
		lblPrev.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrev.setBounds(26, 586, 103, 22);
		contentPane.add(lblPrev);
		
		JLabel lblHash = new JLabel("Hash:");
		lblHash.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHash.setBounds(26, 665, 78, 22);
		contentPane.add(lblHash);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(116, 565, 272, 67);
		textArea_1.setText(blok_bilg.get(3));
		contentPane.add(textArea_1);
		textArea_1.setEditable(false);
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true); 
		textArea_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(116, 645, 272, 67);
		textArea_2.setText(blok_bilg.get(4));
		contentPane.add(textArea_2);
		textArea_2.setEditable(false);
		textArea_2.setLineWrap(true);
		textArea_2.setWrapStyleWord(true); 
		textArea_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		textField_2 = new JTextField();
		textField_2.setText(blok_bilg.get(2));
		textField_2.setColumns(10);
		textField_2.setBounds(183, 80, 205, 22);
		contentPane.add(textField_2);
		
		JLabel lblVeri = new JLabel("Oy:");
		lblVeri.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVeri.setBounds(30, 332, 52, 22);
		contentPane.add(lblVeri);
		
		JPanel panel = new JPanel();
		panel.setBounds(109, 121, 311, 409);
		contentPane.add(panel);
		
		
		File folder = new File("C:\\Ali_Erceylan_E-Secim\\bin");
		File[] files = folder.listFiles();
		File file=new File("C://Users//eskiz//Desktop//Examples.jar");
		
	}

	public Show_block() {
		// TODO Auto-generated constructor stub
	}
}
