package meujogo;

import java.awt.Rectangle;

//ESTA CLASSE FOI CRIADA AQUI APENAS COMO EXEMPLO. O ALUNO DEVE SUBSTITUI-LA POR OUTRA CLASSE CONFORME O PROJETO.
public class BlackHole extends DesenhoAnimado {

	private Thread t = new Thread(this);
	private boolean isVisivel;

	public BlackHole() {
	}

	public BlackHole(int x, int y, String path) {
		super(x, y, path);
		t.start();
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while (true) {
			if (this.getY() > (-50)) {
				this.setY(this.getY() - 15);
			} else if (this.getY() <= (-50)) {
				this.setY(680);
			}
			try {
				t.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), 90, 50);
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public char[] getMeteoro() {
		// TODO Auto-generated method stub
		return null;
	}
}