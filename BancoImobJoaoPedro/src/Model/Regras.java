package Model;

public class Regras {
	
	public int qtd, dados[] = {1,1};
	Jogo partida;
	
	public Regras(int qtd) {
		this.qtd = qtd;
		partida = new Jogo(qtd);
	}
	
	public int[] jogardado() {/*joga os dados e retorna {dado0,dado1}*/
		dados = partida.jogardado();
		return dados;
	}
	public void set_dados(int a, int b) {
		this.dados[0] = a;
		this.dados[1] = b;
	}
	
	public void vender_imov(int pos){
		partida.vender_imov(pos);
	}
	public void movimentar(int ind_jog, int somadados){
		partida.movimentar(ind_jog, somadados);
	}
	public boolean ganhou(){ /*vai verificar se os outros jogadores estao eliminados, restando somente o campeao*/
		 return partida.ganhou();
	}
	
	public int getpeao(int ind_jog) { /*retorna a posicao do peao do jogador*/
		return partida.getpeao(ind_jog);
	}
	public char[] ver_ganhador() { /* caso jogo acabe antecipadamente, quem tem mais dinheiro ganha e o vetor esta na ordem de dinheiro*/
		return partida.ver_ganhador();
	}
	public void liberdade_prisao(int ind_jog){
		liberdade_prisao(ind_jog);
	}
}
