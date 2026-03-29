import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tr.gov.tubitak.uekae.esya.api.common.ESYAException;


public class OyVer extends JFrame {
	JFrame frame;
	JTextField textField;
	JTextField txtErceylan;
	JTextField txtAli;
	byte[] sifreli_kimlik_bil;
	String sifreli_kimlik_hex;
	String oy_bilgileri; 
	static ArrayList <String> secimler=new ArrayList <String>();
	JPasswordField passwordField;
	JComboBox comboBox,comboBox_1,comboBox_2,comboBox_3;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OyVer frame = new OyVer();
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
	 * @throws NoSuchPaddingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public OyVer() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, IOException {
		
		
		setTitle("e-Oy Pusulas\u0131");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		secimler.add("Seçim Yapýnýz!");
		secimler.add("Seçim Yapýnýz!");
		secimler.add("Seçim Yapýnýz!");
		secimler.add("Seçim Yapýnýz!");
		//KARTLI GÝRÝÞ ÝÇÝN
		
				//Ýsim ve TC Kimlik No sertifikadan çekilecek
				//ECertificate cert = SmartCardManager.getInstance().getSignatureCertificate(true);
			//	String TCno = cert.getSubject().getSerialNumberAttribute();
			//	String name = cert.getSubject().getCommonNameAttribute();
				
				//KARTSIZ DENEME YAPILACAKSA AÞAÐIDAKÝ SATIRLARI KULLANABÝLÝRSÝNÝZ
				String TCno = "12345678901";
				String name = "Cenk Duran";
				
				
				
				
				Sifreleme sf=new Sifreleme();
				frame = new JFrame("e-Oy Pusulasý");
				frame.setBounds(100, 100, 450, 480);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null);
				
				JLabel lblKimlikBilgileri = new JLabel("Kimlik Bilgileri:");
				lblKimlikBilgileri.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblKimlikBilgileri.setBounds(53, 40, 127, 28);
				frame.getContentPane().add(lblKimlikBilgileri);
				
				JLabel lblOyBilgileri = new JLabel("Yerel Se\u00E7im Oy Bilgileri:");
				lblOyBilgileri.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblOyBilgileri.setBounds(53, 212, 349, 28);
				frame.getContentPane().add(lblOyBilgileri);
				
				JLabel lblTc = new JLabel("TC:");
				lblTc.setBounds(53, 81, 56, 16);
				frame.getContentPane().add(lblTc);
				
				JLabel lblSoyad = new JLabel("Soyad\u0131:");
				lblSoyad.setBounds(53, 110, 56, 16);
				frame.getContentPane().add(lblSoyad);
				JLabel lblAd = new JLabel("Ad\u0131:");
				lblAd.setBounds(53, 139, 56, 16);
				frame.getContentPane().add(lblAd);
				

				
				
				String[] splitStr = name.split("\\s+");
				String kimlik_bil = splitStr[0]+" "+splitStr[1]+" "+TCno;
				oy_bilgileri=secimler.get(0)+" "+secimler.get(1)+" "+secimler.get(2)+" "+secimler.get(3);
				//Kimlik Bilgilerini Þifrele
				sifreli_kimlik_bil = sf.sifrele(kimlik_bil);
				sifreli_kimlik_hex=sf.bytesToHex(sifreli_kimlik_bil);
				
				txtErceylan = new JTextField();
				txtErceylan.setText(splitStr[1]);
				txtErceylan.setBounds(187, 107, 215, 22);
				frame.getContentPane().add(txtErceylan);
				txtErceylan.setColumns(10);
				
				txtAli = new JTextField();
				txtAli.setText(splitStr[0]);
				txtAli.setBounds(187, 136, 215, 22);
				frame.getContentPane().add(txtAli);
				txtAli.setColumns(10);
				
				textField = new JTextField();
				textField.setText(TCno);
				textField.setBounds(187, 78, 215, 22);
				frame.getContentPane().add(textField);
				textField.setColumns(10);
				
				JLabel lblNewLabel = new JLabel("Muhtarl\u0131k");
				lblNewLabel.setBounds(53, 254, 104, 16);
				frame.getContentPane().add(lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("\u0130l\u00E7e Belediyesi");
				lblNewLabel_1.setBounds(53, 283, 98, 16);
				frame.getContentPane().add(lblNewLabel_1);
				
				JLabel lblNewLabel_2 = new JLabel("Belediye Meclisi");
				lblNewLabel_2.setBounds(53, 312, 104, 16);
				frame.getContentPane().add(lblNewLabel_2);
				
				JLabel lblBykehirBelediyesi = new JLabel("B\u00FCy\u00FCk\u015Fehir Belediyesi");
				lblBykehirBelediyesi.setBounds(53, 344, 127, 16);
				frame.getContentPane().add(lblBykehirBelediyesi);
				
				JButton btnNewButton = new JButton("Kimlik Bilgilerini \u015Eifrele");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						char[] input = passwordField.getPassword();
					    String passString = new String(input);  
					    
					    if(!(passString.equals("123456")))
					    {			     
							JOptionPane.showMessageDialog(null, "Lütfen Parolanýzý Giriniz!", "InfoBox: " + "Uyarý!", JOptionPane.INFORMATION_MESSAGE);
						
						}
						else{
								
						try {
							Sifreleme_Ekrani se=new Sifreleme_Ekrani(secimler,sifreli_kimlik_hex);
							
							se.setVisible(true);
						} catch (InvalidKeyException | NoSuchAlgorithmException
								| InvalidKeySpecException | IllegalBlockSizeException
								| BadPaddingException | NoSuchPaddingException
								| ESYAException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}}
				});
				btnNewButton.setBounds(233, 171, 169, 39);
				frame.getContentPane().add(btnNewButton);
				
				
				String[] muhtarlýk_list = { "Seçim Yapýnýz!", "Deniz Toprak", "Canan Tanir", "Ahmet Selim"};
				
				comboBox = new JComboBox(muhtarlýk_list);
				comboBox.setBackground(Color.WHITE);
				comboBox.setBounds(187, 253, 215, 22);
				frame.getContentPane().add(comboBox);
			    comboBox.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent evt) {
			            	
			            	secimler.set(0, comboBox.getSelectedItem().toString()) ;
			            }
			        });
			    	  
				
				String[] ilce_bel_list = { "Seçim Yapýnýz!", "Fatma Kel","Ahmet Can", "Timur Yorum"};
				
				comboBox_1 = new JComboBox(ilce_bel_list);
				comboBox_1.setBackground(Color.WHITE);
				comboBox_1.setBounds(187, 280, 215, 22);
				frame.getContentPane().add(comboBox_1);
				comboBox_1.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		            	
		            	secimler.set(1, comboBox_1.getSelectedItem().toString()) ;
		            }
		        });
				
				String[] bel_mec = { "Seçim Yapýnýz!", "X Partisi", "Y Partisi", "Z Partisi"};
				
				comboBox_2 = new JComboBox(bel_mec);
				comboBox_2.setBackground(Color.WHITE);
				comboBox_2.setBounds(187, 309, 215, 22);
				frame.getContentPane().add(comboBox_2);
				comboBox_2.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		            	
		            	secimler.set(2, comboBox_2.getSelectedItem().toString()) ;
		            }
		        });
				
				
				
				
				String[] buyuk_bel = { "Seçim Yapýnýz!", "Gulse Isler","Sevda Duru","Vedat Aral"};
				
				comboBox_3 = new JComboBox(buyuk_bel);
				comboBox_3.setBackground(Color.WHITE);
				comboBox_3.setBounds(187, 341, 215, 22);
				frame.getContentPane().add(comboBox_3);
				comboBox_3.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent evt) {
		            	
		            	secimler.set(3, comboBox_3.getSelectedItem().toString()) ;
		            }
		        });
				
				JLabel lblifre = new JLabel("Parola");
				lblifre.setBounds(53, 176, 56, 28);
				frame.getContentPane().add(lblifre);
				
				passwordField = new JPasswordField();
				passwordField.setBounds(117, 179, 104, 22);
				frame.getContentPane().add(passwordField);

				
			}
	}


