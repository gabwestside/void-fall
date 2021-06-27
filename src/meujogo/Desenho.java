package meujogo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Desenho {

	private int x;
	private int y;
	private int dx, dy;
	private BufferedImage img;
	private boolean isVisivel;

	public Desenho() {
	}

	public Desenho(int x, int y, String path) {
		this.setX(x);
		this.setY(y);
		this.setImg(path);
		isVisivel = true;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(String path) {
		File fileImg = new File(path);
		try {
			img = ImageIO.read(fileImg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		x += dx;
		y += dy;
	}

	public void desenhar(Graphics g) {
		// Desenhando a imagem na tela
		g.drawImage(this.getImg(), this.getX(), this.getY(), null);
	}


	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}
	
}
