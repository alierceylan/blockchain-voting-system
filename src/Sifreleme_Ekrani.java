import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.bouncycastle.crypto.CryptoException;

import tr.gov.tubitak.uekae.esya.api.common.ESYAException;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartCardException;


public class Sifreleme_Ekrani extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sifreleme_Ekrani frame = new Sifreleme_Ekrani();
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
	 * @throws NoSuchPaddingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws SmartCardException 
	 * @throws InvalidKeyException 
	 */
	public Sifreleme_Ekrani(ArrayList secimler,String sifreli_kimlik_hex) throws InvalidKeyException, SmartCardException, NoSuchAlgorithmException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, ESYAException, IOException {
		BlockGui bg=new BlockGui();
		setTitle("e-Oy Pusulas\u0131");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblifrelenmiKimlikBilgileri = new JLabel("\u015Eifrelenmi\u015F Kimlik Bilgileri:");
		lblifrelenmiKimlikBilgileri.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblifrelenmiKimlikBilgileri.setBounds(40, 31, 348, 48);
		contentPane.add(lblifrelenmiKimlikBilgileri);
		
		JTextArea textPane = new JTextArea();
		textPane.setBounds(36, 79, 366, 103);
		textPane.setText(sifreli_kimlik_hex);
		contentPane.add(textPane);
		textPane.setEditable(false);
		textPane.setLineWrap(true);
		textPane.setWrapStyleWord(true); 
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JLabel lblOyBilgileri = new JLabel("Yerel Se\u00E7im Oy Bilgileri:");
		lblOyBilgileri.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOyBilgileri.setBounds(53, 212, 349, 28);
		
		getContentPane().add(lblOyBilgileri);
		
		
		JLabel lblNewLabel = new JLabel("Muhtarl\u0131k");
		lblNewLabel.setBounds(53, 254, 104, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u0130l\u00E7e Belediyesi");
		lblNewLabel_1.setBounds(53, 283, 98, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Belediye Meclisi");
		lblNewLabel_2.setBounds(53, 312, 104, 16);
		getContentPane().add(lblNewLabel_2);
		
		JTextField txtDenizToprak = new JTextField();
		txtDenizToprak.setText(secimler.get(0).toString());
		txtDenizToprak.setBounds(187, 254, 215, 22);
		getContentPane().add(txtDenizToprak);
		txtDenizToprak.setColumns(10);
		
		JTextField txtAPartisi = new JTextField();
		txtAPartisi.setText(secimler.get(1).toString());
		txtAPartisi.setBounds(187, 283, 215, 22);
		getContentPane().add(txtAPartisi);
		txtAPartisi.setColumns(10);
		
		JTextField txtYPartisi = new JTextField();
		txtYPartisi.setText(secimler.get(2).toString());
		txtYPartisi.setBounds(187, 312, 215, 22);
		getContentPane().add(txtYPartisi);
		txtYPartisi.setColumns(10);
		
		JTextField txtSerapSerin = new JTextField();
		txtSerapSerin.setText(secimler.get(3).toString());
		txtSerapSerin.setBounds(187, 344, 215, 22);
		getContentPane().add(txtSerapSerin);
		txtSerapSerin.setColumns(10);
		
		JLabel lblBykehirBelediyesi = new JLabel("B\u00FCy\u00FCk\u015Fehir Belediyesi");
		lblBykehirBelediyesi.setBounds(53, 344, 127, 16);
		getContentPane().add(lblBykehirBelediyesi);
		
		JButton btnNewButton = new JButton("K\u00F6reltme \u0130\u015Flemi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Koreltilmis KoVeri = null;
				try {
					KoVeri = new Koreltilmis();
				} catch (CryptoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SmartCardException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ESYAException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				KoVeri.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(60, 388, 328, 25);
		contentPane.add(btnNewButton);
		
		
}

	public Sifreleme_Ekrani() {
		// TODO Auto-generated constructor stub
	}}
