package Model;


public class Jogador{
    int eliminado = 0, pos_peao = 0, preso = 0, tempo_preso = 0, carta_prisao = 0;
    float  dinheiro = 0, dinheiro_prop = 0;
    char cor;
    
    public Jogador(char cor, float dinheiro, float dinheiro_prop){
        this.cor = cor;
		this.dinheiro = dinheiro;
		this.dinheiro_prop = dinheiro_prop;	
    }
    public int getposicao(){
        return pos_peao;
    }
	public void eliminar_jog(){
		this.eliminado = 1;
		this.dinheiro = 0;
		this.dinheiro_prop = 0;
	}
	public boolean jog_elim(){
        return eliminado == 1;
    }
	public float dinheiro_vivo(){
		return dinheiro;
    }
	public void novo_dinheiro(float num){
		this.dinheiro = num;
	}
	public void aumenta_dinheiro(float num){
		this.dinheiro += num;
	}
	public float dinheiro_totalcomdesconto(){
		float dindin = (float) (dinheiro + (0.9*dinheiro_prop));
        return dindin;
    }
    public float dinheiro_total(){
        return dinheiro + dinheiro_prop;
    }
    public char getcor() {
    	return cor;
    }
	public void movimenta(int num){
		this.pos_peao = num;
	}
	public void prender_soltar(int num){
		this.preso = num;
		if(num == 0){
			this.tempo_preso = 0;
			this.carta_prisao = 0;
		}
	}
	
	public int preso_ousolto(){
		return preso;
	}
	public boolean prisao_carta(){
		return carta_prisao == 1;
	}
	public int prisao_tempo(){
		return tempo_preso;
	}
}
