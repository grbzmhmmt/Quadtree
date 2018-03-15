
//MUHAMMET GÜRBÜZ 140202010

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.*;

public class prolabII extends JFrame {
	/**
	 * 
	 */
	public static int kontrol = 0, clickkontrol = 0, click = 0, nulldegeri = 0, sayac = 0;
	public static int[][] dizi = new int[30][18];
	private final JLabel labelUsername = new JLabel("Hoþ Geldiniz"); // giriþ ekraný üstüne baslik yaziliyor
	private final JButton buttonreset = new JButton("RESET"); // ilk giriþ butonlarý oluþturuluyor ve isim giriliyor
	private final JButton buttonmanuel = new JButton("MANUEL"); // ilk giriþ butonlarý oluþturuluyor ve isim giriliyor
	private final JButton buttonRandom = new JButton("RANDOM"); // ilk giriþ butonlarý oluþturuluyor ve isim giriliyor
	private final JButton buttondaire = new JButton("CAP"); // ilk giriþ butonlarý oluþturuluyor ve isim giriliyor
	private final JButton buttonsorgu = new JButton("SORGU"); // ilk giriþ butonlarý oluþturuluyor ve isim giriliyor
	private static final long serialVersionUID = 1L;
	private JFrame pencere = new JFrame();
	Quad root, Anaroot;
	JSlider slid;
	JLabel label;


	public prolabII(Graphics g) { // programýn iþlevinin baþladýðý metod
		super("JPanel Demo Program");
		setLayout(new GridBagLayout());
		JPanel newPanel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		slid = new JSlider(JSlider.VERTICAL, 0, 10, 5);
		slid.setMajorTickSpacing(50);
		slid.setPaintTicks(true);
		slid.setForeground(Color.RED);
		add(slid, constraints);

		label = new JLabel("volume 20 %");	//slider ýn tanýmý yapýlýyor
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(slid, constraints);

		constraints.gridwidth = 2;
		constraints.gridx = 2; // random butonu olusturuluyor
		constraints.gridy = 2;
		newPanel.add(buttonRandom, constraints);

		constraints.gridx = 2; // reset butonu olusturuluyor
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		newPanel.add(buttonreset, constraints);

		constraints.gridx = 1; // daire butonu olusturuluyor
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		newPanel.add(buttondaire, constraints);

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 5;
		constraints.anchor = GridBagConstraints.CENTER;
		newPanel.add(labelUsername, constraints);

		constraints.gridx = 0; // manuel butonu olusturuluyor
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		newPanel.add(buttonmanuel, constraints);

		constraints.gridx = 1; // sorgu butonu olusturuluyor
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		newPanel.add(buttonsorgu, constraints);

		newPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"            QUADTREE ProlabII.II            "));// ilk konsol baslýk atiliyor
		add(newPanel);
		pack();
		setLocationRelativeTo(null);

		buttonreset.addActionListener(new ActionListener() { // reset butonuna basýldýðýný kontrol ediyor
			public void actionPerformed(ActionEvent e) {
				resetclick(); // eger basýlmýþsa resetclick metoduna gidiliyor
				clickkontrol = 2;
			}
		});
		buttonmanuel.addActionListener(new ActionListener() { // manuel butonuna basýldýðýný kontrol ediyor
			public void actionPerformed(ActionEvent e) {
				manuelclick(); // eger basýlmýþsa manuelclick metoduna gidiliyor
				clickkontrol = 0;
			}
		});
		buttondaire.addActionListener(new ActionListener() { // cap butonuna basýldýðýný kontrol ediyor
			public void actionPerformed(ActionEvent e) {
				daireclick(); // eger basýlmýþsa resetclick metoduna gidiliyor
				clickkontrol = 3;
			}
		});

		buttonRandom.addActionListener(new ActionListener() { // random butonuna basýldýðýný kontrol ediyor
			public void actionPerformed(ActionEvent e) {
				randomclick(); // eger basýlmýþsa randomclick metoduna gidiliyor
				clickkontrol = 1;
			}
		});

		buttonsorgu.addActionListener(new ActionListener() { // random butonuna basýldýðýný kontrol ediyor
			public void actionPerformed(ActionEvent e) {
				sorguclick(); // eger basýlmýþsa randomclick metoduna gidiliyor
				clickkontrol = 4;
			}
		});

		pencere.setSize(512, 512); // ekran boyutu giriliyor
		pencere.addMouseListener(new MouseListener() { // mouse click kontrolleri burada yapýlýyor
			int sorguX, sorguY, sorguR;
			
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				
				if (clickkontrol == 0) { // manuel giriþ kontrolu burada yapýlýyor
					
					int x = e.getX();	//manuel giriþ için koordinatlar alýnýyor
					int y = e.getY();
					Graphics g = pencere.getGraphics();	//pencere nesnemize referans olarak grafik tanýmlanýyor
					g.setColor(Color.RED);
					g.fillOval(x - 3, y - 3, 6, 6);	//nokta belirleniyor
					Quad quad = new Quad(x, y); // bir node objesi oluþturuluyor
					QuadTreeSearch search = new QuadTreeSearch(x, y); // agca ekleme ve arama yapýlabilmesi icin nesne olusturuluyor.
					search.insert(quad, x, y, g); // agaca eklemeye ve cigi cizdirmeye gonderiliyor

					click++;
				} else if (clickkontrol == 1) { // random atama kontrolu burada yapiliyor
					int j = 0;
					
					for (j = 0; j < 20; j++) {	//random seceneðinde noktalar için for döngüsü tutuluyor
						Random rd = new Random();
						int x = rd.nextInt(490) + 10;		//random olarak nokta alýnýyor
						int y = rd.nextInt(490) + 10;
						Graphics g = pencere.getGraphics();
						g.setColor(Color.RED);
						g.fillOval(x - 3, y - 3, 6, 6); //nokta belirleniyor
						Quad quad = new Quad(x, y); // bir node objesi oluþturuluyor
						QuadTreeSearch search = new QuadTreeSearch(x, y); // agca ekleme ve arama yapýlabilmesi icin nesne olusturuluyor.
						search.insert(quad, x, y, g); // agaca eklemeye ve cigi cizdirmeye gonderiliyor
						click++;
					}
				} else if (clickkontrol == 2) { // resetleme tusu kontrol iþlemleri yapýlýyor
					Graphics g = pencere.getGraphics();
					g.setColor(pencere.getBackground());
					g.fillRect(0, 0, 512, 512);
					Anaroot=Anaroot.ne = Anaroot.nw = Anaroot.se = Anaroot.sw = null;	//agacýn düüðümleri siliniyor
					
					for (int z = 0; z < 30; z++) {	//dizimiz sýfýrlanýyor
						dizi[z][0] = '\0';
					}
					click = kontrol = 0; // reset sonrasý yeniden aðaç olusturmak için kontrol sýfýrlanýyor
					clickkontrol = 8;
				} else if (clickkontrol == 3) { // cap a bastýysa burada iþlemleri yapýlýyor
					Graphics g = pencere.getGraphics();
					g.setColor(Color.RED);
					int x = e.getX();	//cap için merkez noktasý koordinatlarý alýnýyor
					int y = e.getY();
					sorguX = x;
					sorguY = y;
					g.drawOval(x-50, y-50, 100, 100);
					slid.addChangeListener(new ChangeListener() {
						int getval;
						public void stateChanged(ChangeEvent event) {
							
							System.out.println("" + slid.getValue());
							
							g.setColor(pencere.getBackground());
							
							g.drawOval(x - 10 * getval, y - 10 * getval, 20 * getval, 20 * getval);
							
							g.setColor(Color.RED);
							g.drawOval(x - 10 * slid.getValue(), y - 10 * slid.getValue(), 20 * slid.getValue(),20 * slid.getValue());
							
							getval = slid.getValue();
							sorguR = 10 * slid.getValue();
						}
					});
					
				} else if (clickkontrol == 4) { // daireye bastýysa burada iþlemleri yapýlýyor
					Graphics g = pencere.getGraphics();
					g.setColor(Color.RED);
					System.out.println("x : " + sorguX + "y : " + sorguY + "   r :" + sorguR);
					root = Anaroot;
					Sorgu sorgu = new Sorgu();
					// System.out.println("daire x : "+x+", y : "+y+", 25.0");
					sorgu.sorguici(sorguX, sorguY, sorguR, g);
					sorguX=sorguY=sorguR=0;
					 kontrol=0;
				}
			}
			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {}

			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {}

			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {}
		});
	}

	public void manuelclick() {
		pencere.setVisible(true);}

	public void sorguclick() {
		pencere.setVisible(true);}

	public void randomclick() {
		pencere.setVisible(true);}

	public void resetclick() {
		pencere.setVisible(true);}

	public void daireclick() {
		pencere.setVisible(true);}

	public void slideclick() {
		pencere.setVisible(true);}
	
	class Sorgu {
		private int x, y,s=0;
		double r;

		Sorgu() {
		}

		public void sorguici(int xx, int yy, double rr, Graphics g) {
			x = xx;
			y = yy;
			r = rr;
			try {
				if (nulldegeri == 6) {
					if(s==0){
						s++;
						sorguici(x, y, r, g);
					}
					siralama(g);
				}
				if (root.ne != null) {
					root = root.ne;
					sorguici(x, y, r, g);
				} else if (root.nw != null) {
					root = root.nw;
					sorguici(x, y, r, g);
				} else if (root.sw != null) {
					root = root.sw;
					sorguici(x, y, r, g);
				} else if (root.se != null) {
					root = root.se;
					sorguici(x, y, r, g);
				} else {
					int a = root.x - x;
					int b = root.y - y;

					if (a < 0) {
						a = a * (-1);
					}
					if (b < 0) {
						b = b * (-1);
					}
					double c = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));
					if (r >= c) {
						dizi[sayac][0] = root.x;
						dizi[sayac][1] = root.y;
						dizi[sayac][2] = root.harf;
						dizi[sayac + 1][0] = '\0';
						sayac++;
						if (root == root.F.ne) {
							root = root.F;
							root.ne = null;
						} else if (root == root.F.nw) {
							root = root.F;
							root.nw = null;
						} else if (root == root.F.sw) {
							root = root.F;
							root.sw = null;
						} else if (root == root.F.se) {
							root = root.F;
							root.se = null;
						}
						if (root.F == null) {
							if (root.se == null && root.nw == null && root.sw == null && root.ne == null) {
								nulldegeri = 6;
								
							}

						}
						if (nulldegeri == 6) {
							siralama(g);
						}
						sorguici(x, y, r, g);

					} else {
						if (root == root.F.ne) {
							root = root.F;
							root.ne = null;
						} else if (root == root.F.nw) {
							root = root.F;
							root.nw = null;
						} else if (root == root.F.sw) {
							root = root.F;
							root.sw = null;
						} else if (root == root.F.se) {
							root = root.F;
							root.se = null;
						}
						if (root.F == null) {
							if (root.se == null && root.nw == null && root.sw == null && root.ne == null) {
								nulldegeri = 6;
							}
						}
						sorguici(x, y, r, g);
					}

				}
			} catch (Exception e) {

			}

		}
		int o=0;
		
		public void siralama(Graphics g) {
			if(o==0){
				o++;
				
			}
			else{
				System.out.println("***********::::::siralama methodun dayýz::::::::************");

				int k, l;
				System.out.println("    x   " + ",     y :  ,     index :  ");
				for (k = 0; dizi[k][0] != '\0'; k++) {
					g.setColor(Color.BLACK);
					g.fillOval(dizi[k][0] - 3, dizi[k][1] - 3, 6, 6);
					String st=""+dizi[k][2];
					g.drawString(st, dizi[k][0]+2, dizi[k][1]-2);

					for (l = 0; dizi[l][0] != '\0'; l++) {
						if (dizi[k][0] < dizi[l][0]) {
							int tm = dizi[k][0];
							dizi[k][0] = dizi[l][0];
							dizi[l][0] = tm;

							tm = dizi[k][1];
							dizi[k][1] = dizi[l][1];
							dizi[l][1] = tm;

							tm = dizi[k][2];
							dizi[k][2] = dizi[l][2];
							dizi[l][2] = tm;
						} else if (dizi[k][0] == dizi[l][0]) {
							if (dizi[k][1] < dizi[l][1]) {
								int tm = dizi[k][2];
								dizi[k][2] = dizi[l][2];
								dizi[l][2] = tm;

								tm = dizi[k][1];
								dizi[k][1] = dizi[l][1];
								dizi[l][1] = tm;
							}

						}
					}
				}
				for (k = 0; dizi[k][0] != '\0'; k++) {	//dizi deki elemanlar ekrana basýlýyor

					System.out.println("x :  " + dizi[k][0] + ", y :  " + dizi[k][1] + ", index :  " + dizi[k][2]);

				}
				System.out.println("click  :  " + click);
			}
		}

	}

	class Quad { // dugum olusturma class ý
		Quad nw, ne, sw, se, F;
		private int x, y;
		int N, S, E, W, harf;

		Quad(int xx, int yy) {
			x = xx;
			y = yy;
		}

	}

	class QuadTreeSearch { // agacý oluþturma ve cizgileri cizme class ý
		public int x, y;

		QuadTreeSearch(int xx, int yy) { // class ýn constuctorý
			x = xx;
			y = yy;
		}

		public void insert(Quad node, int xx, int yy, Graphics g) { // agacý oluþturma ve cizgileri cizme metodu

			if (kontrol == 0) {
				root = Anaroot = node;
				root.x = Anaroot.x = x;
				root.y = Anaroot.y = y;
				root.F = Anaroot.F = Anaroot.ne = Anaroot.nw = Anaroot.se = Anaroot.sw = null;
				root.harf = click;
				root.E = 512;
				root.N = 0;
				root.S = 512;
				root.W = 0;
				g.setColor(Color.ORANGE);
				g.drawLine(x, y, x, root.E); // sag cizgi
				g.drawLine(x, root.N, x, y); // ust cizgi
				g.drawLine(x, y, root.S, y); // alt cizgi
				g.drawLine(x, y, root.W, y); // sol cizgi
				String st=click+""; 
				g.drawString(st, x+2, y-2);
				root.ne = root.nw = root.se = root.sw = null;
				kontrol++;
			} else {
				if (root.x <= x && root.y >= y) { // I. bolge
					if (root.ne == null) {
						node.x = x;
						node.y = y;
						node.ne = node.nw = node.se = node.sw = null;
						node.E = root.E;
						node.W = root.x;
						node.N = root.N;
						node.S = root.y;
						node.harf = click;
						g.setColor(Color.BLUE);
						g.drawLine(x, y, x, node.S); // assa cizgi
						g.drawLine(x, node.N, x, y); // ust cizgi
						g.drawLine(x, y, node.W, y); // sol cizgi
						g.drawLine(x, y, node.E, y); // sag cizgi
						String st=click+""; 
						g.drawString(st, x+2, y-2);
						node.F = root;
						root.ne = node;
						root = Anaroot;
					} else {
						root = root.ne;
						insert(node, node.x, node.y, g);

					}
				} else if (root.x > x && root.y > y) { // II. bolge
					if (root.nw == null) {
						node.x = x;
						node.y = y;
						node.ne = node.nw = node.se = node.sw = null;
						node.E = root.x;
						node.W = root.W;
						node.S = root.y;
						node.N = root.N;
						node.harf = click;
						g.setColor(Color.RED);
						g.drawLine(x, y, x, node.S); // assa cizgi
						g.drawLine(x, node.N, x, y); // ust cizgi
						g.drawLine(x, y, node.W, y); // sol cizgi
						g.drawLine(x, y, node.E, y); // sag cizgi
						String st=click+""; 
						g.drawString(st, x+2, y-2);
						System.out.println("node  x :  " + node.x + ",node y :  " + node.y);
						node.F = root;
						root.nw = node;
						root = Anaroot;
					} else {
						root = root.nw;
						insert(node, node.x, node.y, g);

					}
				} else if (root.x >= x && root.y <= y) { // III. bolge
					if (root.sw == null) {
						node.x = x;
						node.y = y;
						node.E = root.x;
						node.S = root.S;
						node.W = root.W;
						node.N = root.y;
						node.ne = node.nw = node.se = node.sw = null;
						g.setColor(Color.BLACK);
						g.drawLine(x, y, x, node.S); // assa cizgi
						g.drawLine(x, node.N, x, y); // ust cigi
						g.drawLine(x, y, node.W, y); // sol cizgi
						g.drawLine(x, y, node.E, y); // sag cizgi
						String st=click+""; 
						g.drawString(st, x+2, y-2);
						System.out.println("node  x :  " + node.x + ",node y :  " + node.y);
						node.harf = click;
						node.F = root;
						root.sw = node;
						root = Anaroot;
					} else {
						root = root.sw;
						insert(node, node.x, node.y, g);
					}
				} else if (root.x < x && root.y < y) { // IV. bolge
					if (root.se == null) {
						node.x = x;
						node.y = y;
						node.E = root.E;
						node.S = root.S;
						node.N = root.y;
						node.W = root.x;
						node.ne = node.nw = node.se = node.sw = null;
						g.setColor(Color.GREEN);
						g.drawLine(x, y, x, node.S); // assa cizgi
						g.drawLine(x, node.N, x, y); // ust cigi
						g.drawLine(x, y, node.W, y); // sol cizgi
						g.drawLine(x, y, node.E, y); // sag cizgi
						String st=click+""; 
						g.drawString(st, x+2, y-2);
						System.out.println("node  x :  " + node.x + ",node y :  " + node.y);
						node.harf = click;
						node.F = root;
						root.se = node;
						root = Anaroot;

					} else {
						root = root.se;
						insert(node, node.x, node.y, g);
					}
				}
			}
		}
	}

	

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new prolabII(null).setVisible(true);
			}
		});
	}
}
