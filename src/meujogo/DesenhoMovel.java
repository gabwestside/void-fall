package meujogo;

//ESTA CLASSE DEVE SE OBRIGATORIAMENTE MANTIDA NO PROJETO. QUALQUER ALTERAÇÃO REALIZADA DEVE OBEDECER A HIERARQUIA
//ELA DEVE SER UTILIZADA NA HERANÇA DE DESENHOS QUE POSSAM SER MOVIMENTADOS PELO USUÁRIO 
public class DesenhoMovel extends Desenho {
	
	public DesenhoMovel() {}
	
	public DesenhoMovel(int x, int y, String path) {
		super(x, y, path);
	}
	
	public void moverDireita() throws LimiteException {
		this.setX(this.getX() + 22);
		if(this.getX() >= 900) { throw new LimiteException(); }
	}
	
	public void moverEsquerda() throws LimiteException {
		this.setX(this.getX() - 22);
		if(this.getX() <= 0) { throw new LimiteException(); }
	}

	public void moverCima() throws LimiteException {
		this.setY(this.getY() - 22);
		if(this.getY() <= 0) { throw new LimiteException(); }
	}

	public void moverBaixo() throws LimiteException {
		this.setY(this.getY() + 22);
		if(this.getY() >= 600) { throw new LimiteException(); }
	}
	
}
