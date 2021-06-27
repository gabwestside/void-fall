package meujogo;

//ESTA CLASSE DEVE SE OBRIGATORIAMENTE MANTIDA NO PROJETO. QUALQUER ALTERAÇÃO REALIZADA DEVE OBEDECER A HIERARQUIA
//ELA DEVE SER UTILIZADA NA HERANÇA DE DESENHOS QUE POSSAM SER MOVIMENTADOS (ANIMADOS) DE FORMA AUTOMÁTICA
public abstract class DesenhoAnimado extends Desenho implements Runnable {

	public DesenhoAnimado() {}
	
	public DesenhoAnimado(int x, int y, String path) {
		super(x, y, path);
	}
	
}