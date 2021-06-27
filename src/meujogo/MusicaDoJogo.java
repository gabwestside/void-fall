package meujogo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class MusicaDoJogo {
	
	public static void main(String[] args) {

		String path = "Void-Fall.mp3";
		File mp3File = new File(path);
		MP3Musica musica = new MP3Musica();
		musica.tocar(mp3File);
		musica.start();
		
	}

	public static class MP3Musica extends Thread {
		private File mp3;
		private Player player;

		public void tocar(File mp3) {
			this.mp3 = mp3;
		}

		public void run() {
			try {
				FileInputStream fis = new FileInputStream(mp3);
				BufferedInputStream bis = new BufferedInputStream(fis);

				this.player = new Player(bis);
				System.out.println("Sobreviva a esta queda no vazio!");
				this.player.play();

			} catch (Exception e) {
				System.out.println("Erro: não conseguimos tocar a musica " + mp3);
				e.printStackTrace();
			}
		}
	}

}
