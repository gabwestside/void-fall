package meujogo;

import java.awt.Rectangle;

//ESTA CLASSE FOI CRIADA AQUI APENAS COMO EXEMPLO. O ALUNO DEVE SUBSTITUI-LA POR OUTRA CLASSE CONFORME O PROJETO.
public class Ovni extends DesenhoAnimado {
	
	private Thread t = new Thread(this);
	
	private boolean isVisivel;

	public Ovni() {
	}

	public Ovni(int x, int y, String path) {
		super(x, y, path);
		t.start();

	}
	
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), 80, 50);
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
				t.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public char[] getOvni() {
		// TODO Auto-generated method stub
		return null;
	}
}