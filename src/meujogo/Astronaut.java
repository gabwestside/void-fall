package meujogo;

import java.awt.Rectangle;

public class Astronaut extends DesenhoMovel {

	private boolean isVisivel;
	
	public Astronaut() {}
	
	public Astronaut(int x, int y, String path) {
		super(x, y, path);
		isVisivel = true;
	}
	
	public void update() {
		setX(getX() + this.getDx());
		setY(getY() + this.getDy());
	}
	
	
	
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), 50, 50);
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public char[] getAstronaut() {
		return null;
	}


}
