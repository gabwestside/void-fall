package meujogo;

import java.awt.Rectangle;

//ESTA CLASSE FOI CRIADA AQUI APENAS COMO EXEMPLO. O ALUNO DEVE SUBSTITUI-LA POR OUTRA CLASSE CONFORME O PROJETO.
public class Coin extends DesenhoAnimado {

	private Thread t = new Thread(this);

	private boolean isVisivel;

	public Coin() {
	}

	public Coin(int x, int y, String path) {
		super(x, y, path);
		t.start();

	}

	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), 50, 50);
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while (true) {
			while (true) {
				if (this.getX() > (-50)) {
					this.setX(this.getX() - 15);
				} else if (this.getX() <= (-50)) {
					this.setX(950);
				}
				try {
					t.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public char[] getCoin() {
		// TODO Auto-generated method stub
		return null;
	}

}
