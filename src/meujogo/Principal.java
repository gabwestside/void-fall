package meujogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Principal extends JFrame implements ActionListener {

	private Desenho espaco;
	private Desenho gameover;
	private static Astronaut astronaut;
	private static ArrayList<Coin> coin = new ArrayList<Coin>();
	private static ArrayList<BlackHole> blackhole = new ArrayList<BlackHole>();
	private static ArrayList<Ovni> ovni = new ArrayList<Ovni>();
	int cordenadas[] = new int[2];
	int cordenadas2[] = new int[7];
	int cordenadas3[] = new int[6];
	private int contador = 0;
	private Stars stars;
	private boolean enJogo;
	private static final long serialVersionUID = 1L;

	public Principal() {
		MusicaDoJogo m = new MusicaDoJogo();
		m.main(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Desenhando a tela e seus elementos
		espaco = new Desenho(0, 0, "background.jpg");
		astronaut = new Astronaut(300, 0, "astronauta.png");
		for (int j = 0; j < cordenadas3.length; j++) {
			int x = (int) (Math.random() * 3000 + 1000);
			int y = (int) (Math.random() * 500 + 30);
			coin.add(new Coin(x, y, "coin.png"));
		}
		for (int i = 0; i < cordenadas.length; i++) {
			int x = (int) (Math.random() * 700 + 20);
			int y = (int) (Math.random() * 1000 + 600);
			blackhole.add(new BlackHole(x, y, "meteoro.png"));
		}
		for (int i = 0; i < cordenadas2.length; i++) {
			int x = (int) (Math.random() * 850 + 10);
			int y = (int) (Math.random() * 1000 + 600);
			ovni.add(new Ovni(x, y, "ovni.png"));
		}
		stars = new Stars(0, 100, "Stars.png");
		gameover = new Desenho(0, 0, "fimdejogo.png");
		// Adicionando o evento de teclado
		addKeyListener(new TecladoAdapter());
		this.setTitle("Void Fall");
		this.setResizable(false); //Para sempre ficar o tamanho da tela escolhido

		enJogo = true;

		JOptionPane.showMessageDialog(null, "Colete 5 estrelas e desvie de tudo que puder!");

	}

	// EVITAR ALTERAR ESSE M�TODO
	public static void main(String[] args) {
		// Criando uma inst�ncia da classe principal
		Principal t = new Principal();
		t.setSize(900, 600);
		t.createBufferStrategy(1);
		t.setVisible(true);
		t.createBufferStrategy(2);

		try {
			File file = new File("Void-Fall.txt");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(astronaut);
			oos.writeObject(ovni);
			oos.writeObject(blackhole);
			oos.writeObject(coin);
			oos.close();
			fos.close();

			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Astronaut a = (Astronaut) ois.readObject();
			System.out.println(a.getAstronaut());
			Ovni o = (Ovni) ois.readObject();
			System.out.println(o.getOvni());
			BlackHole b = (BlackHole) ois.readObject();
			System.out.println(b.getMeteoro());
			Coin c = (Coin) ois.readObject();
			System.out.println(c.getCoin());
			ois.close();
			fis.close();

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo n�o encontrado");
		} catch (IOException e) {
			System.out.println("Erro de I/O");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// EVITAR ALTERAR ESSE M�TODO
	public void renderizarGraphics() throws WinException, LoseException, Lose2Exception {
		if (!getBufferStrategy().contentsLost())
			getBufferStrategy().show();
		Graphics g = getBufferStrategy().getDrawGraphics();

		// Criando um contexto gr�fico
		Graphics g2 = g.create();
		// Limpando a tela
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());

		renderizarImagens(g2);

		// Liberando os contextos criados.
		g.dispose();
		g2.dispose();
	}

	// ESSE � O M�TODO QUE DEVE SER ADAPTADO AO PROJETO
	public void renderizarImagens(Graphics g2) throws WinException, LoseException, Lose2Exception {
		// Desenhando as imagens
		if (enJogo == true) {

			espaco.desenhar(g2);
			stars.desenhar(g2);

			for (int j = 0; j < coin.size(); j++) {
				coin.get(j).desenhar(g2);
			}

			for (int o = 0; o < ovni.size(); o++) {
				ovni.get(o).desenhar(g2);
			}
			for (int i = 0; i < blackhole.size(); i++) {
				blackhole.get(i).desenhar(g2);
			}
			astronaut.desenhar(g2);
		} else {
			gameover.desenhar(g2);
		}
		checarColisoes();
	}

	// EVITAR ALTERAR ESSE M�TODO
	public void paint(Graphics g) {
		try {
			this.renderizarGraphics();
		} catch (WinException e) {
			JOptionPane.showMessageDialog(null, "Voce pegou as 5 estrelas!");

			System.exit(0);
		} catch (LoseException e) {
			JOptionPane.showMessageDialog(null, "Voce foi atingido por um Cometa!");

			// System.exit(0);
		} catch (Lose2Exception e) {
			JOptionPane.showMessageDialog(null, "Voce foi atingido por uma Nave Alienigena!");

			// System.exit(0);
		}

		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		astronaut.update();

		for (int p = 0; p < coin.size(); p++) {
			Coin on = coin.get(p);
			if (on.isVisivel()) {
				on.update();
			} else {
				coin.remove(p);
			}
		}
		repaint();
	}

	public void checarColisoes() throws WinException, LoseException, Lose2Exception {
		Rectangle formaAstronaut = astronaut.getBounds();
		Rectangle formaBlackHole;
		Rectangle formaOVNI;
		Rectangle formaCoin;

		for (int i = 0; i < blackhole.size(); i++) {
			BlackHole tempBlackHole = blackhole.get(i);
			formaBlackHole = tempBlackHole.getBounds();
			if (formaAstronaut.intersects(formaBlackHole)) {
				astronaut.setVisivel(false);
				tempBlackHole.setVisivel(false);
				enJogo = false;
				{
					throw new LoseException();
				}
			}
		}

		for (int j = 0; j < coin.size(); j++) {
			Coin tempCoin = coin.get(j);
			formaCoin = tempCoin.getBounds();
			if (formaAstronaut.intersects(formaCoin)) {
				astronaut.setVisivel(false);
				tempCoin.setVisivel(false);
				coin.remove(j);
				contador++;
			}
			if (contador == 5) {
				throw new WinException();
			}
		}

		for (int o = 0; o < ovni.size(); o++) {
			Ovni tempOVNI = ovni.get(o);
			formaOVNI = tempOVNI.getBounds();
			if (formaAstronaut.intersects(formaOVNI)) {
				astronaut.setVisivel(false);
				tempOVNI.setVisivel(false);
				enJogo = false;
				{
					throw new Lose2Exception();
				}
			}
		}
	}

	private class TecladoAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent evt) {
			// Especificando o comportamento das teclas
			if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
				try {
					astronaut.moverDireita();
				} catch (LimiteException e) {
					JOptionPane.showMessageDialog(null, "Voc� foi longe demais");

					e.printStackTrace();
				}
				repaint();
			} else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
				try {
					astronaut.moverEsquerda();
				} catch (LimiteException e) {
					JOptionPane.showMessageDialog(null, "Voc� foi longe demais");

					e.printStackTrace();
				}
				repaint();
			} else if (evt.getKeyCode() == KeyEvent.VK_UP) {
				try {
					astronaut.moverCima();
				} catch (LimiteException e) {
					JOptionPane.showMessageDialog(null, "Voc� foi longe demais");

					e.printStackTrace();
				}
				repaint();
			} else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
				try {
					astronaut.moverBaixo();
				} catch (LimiteException e) {
					JOptionPane.showMessageDialog(null, "Voc� foi longe demais");

					e.printStackTrace();
				}
				repaint();
			}

		}

		public void keyReleased(KeyEvent arg0) {
		}

		public void keyTyped(KeyEvent arg0) {
		}
	}
}
