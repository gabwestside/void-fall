package meujogo;

//ESTA CLASSE FOI CRIADA AQUI APENAS COMO EXEMPLO. O ALUNO DEVE SUBSTITUI-LA POR OUTRA CLASSE CONFORME O PROJETO.
public class Stars extends DesenhoAnimado {

	private Thread t = new Thread(this);

	public Stars() {
	}

	public Stars(int x, int y, String path) {
		super(x, y, path);
		t.start();
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while(true){
			if(this.getY() > (-450)){
				this.setY(this.getY() - 30);
			}
			else if(this.getY() <= (-450)){
				this.setY(0);
			}
			try{
				t.sleep(200);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

}