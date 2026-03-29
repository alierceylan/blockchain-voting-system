import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.JButton;

import tr.gov.tubitak.uekae.esya.api.common.ESYAException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class Giris extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Giris frame = new Giris();
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
	public Giris() {
		//KARTLI GÝRÝÞ ÝÇÝN
		
		//Ýsim ve TC Kimlik No sertifikadan çekilecek
		//ECertificate cert = SmartCardManager.getInstance().getSignatureCertificate(true);
	//	String TCno = cert.getSubject().getSerialNumberAttribute();
	//	String name = cert.getSubject().getCommonNameAttribute();
		
		//KARTSIZ DENEME YAPILACAKSA AÞAÐIDAKÝ SATIRLARI KULLANABÝLÝRSÝNÝZ
		String TCno = "12345678901";
		String name = "Cenk Duran";
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 588);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTckn = new JLabel("TCKN:");
		lblTckn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTckn.setBounds(45, 266, 80, 36);
		contentPane.add(lblTckn);
	
		
		JLabel lblParola = new JLabel("Parola:");
		lblParola.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblParola.setBounds(45, 387, 80, 36);
		contentPane.add(lblParola);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(45, 421, 359, 22);
		contentPane.add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(45, 305, 359, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(TCno);
		
		JLabel lblEseim = new JLabel("e-Se\u00E7im");
		lblEseim.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblEseim.setBounds(168, 124, 151, 36);
		contentPane.add(lblEseim);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(45, 365, 359, 22);
		contentPane.add(textField_1);
		textField_1.setText(name);
		
		JLabel lblAdSoyad = new JLabel("Ad Soyad:");
		lblAdSoyad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAdSoyad.setBounds(45, 326, 80, 36);
		contentPane.add(lblAdSoyad);
		
		JTextArea txtrUyarKartOkuyucunuzun = new JTextArea();
		txtrUyarKartOkuyucunuzun.setForeground(new Color(178, 34, 34));
		txtrUyarKartOkuyucunuzun.setBackground(SystemColor.menu);
		txtrUyarKartOkuyucunuzun.setFont(new Font("Monospaced", Font.PLAIN, 13));
		txtrUyarKartOkuyucunuzun.setText("Uyar\u0131: Kart okuyucunuzun bilgisayarýnýza ba\u011Fl\u0131 oldu\u011Fundan emin olunuz!");
		txtrUyarKartOkuyucunuzun.setBounds(45, 216, 359, 37);
		contentPane.add(txtrUyarKartOkuyucunuzun);
		txtrUyarKartOkuyucunuzun.setEditable(false);
		txtrUyarKartOkuyucunuzun.setLineWrap(true);
		txtrUyarKartOkuyucunuzun.setWrapStyleWord(true); 
		
		JButton btnNewButton = new JButton("Giri\u015F Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] input = passwordField.getPassword();
			    String passString = new String(input);
			    
			    if(!(passString.equals("123456")))
			    {			     
					JOptionPane.showMessageDialog(null, "Parolanýz yanlýþ!", "InfoBox: " + "Uyarý!", JOptionPane.INFORMATION_MESSAGE);
				
				}
			    else if((passString.equals(null))){
			    	
			    	JOptionPane.showMessageDialog(null, "Lütfen Parolanýzý giriniz!", "InfoBox: " + "Uyarý!", JOptionPane.INFORMATION_MESSAGE);
			    }
				else{
					
						OyVer ov = null;
						try {
							ov = new OyVer();
						} catch (InvalidKeyException e1) {
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
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ov.setVisible(true);
						
						
						
				
				}
		
			}});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(130, 475, 173, 25);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("");
		Image img=new ImageIcon(this.getClass().getResource("/secim.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(168, 13, 151, 113);
		contentPane.add(lblNewLabel);
	}
}
