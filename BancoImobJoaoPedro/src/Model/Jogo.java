package Model;

import java.util.Random;


public class Jogo{
    Random random = new Random();
    int dado[] = new int[2], banco = 200000;
    int qtd;
    Jogador jog[];
	Tabuleiro tabuleiro;
    char cor[] = {'V','A','L','Y','R','P'}; /*Vermelho, Azul, Laranja, Yellow, Roxo, Preto*/
	
    
    public Jogo(int qtd){
        this.qtd = qtd;
        jog = new Jogador[qtd];
        tabuleiro = new Tabuleiro();
        for(int i = 0; i<qtd; i++) {
        	jog[i] = new Jogador(cor[i], 4000, 0);
        }
    }
	
    public int[] jogardado(){
        this.dado[0] = random.nextInt(6)+1;
        this.dado[1] = random.nextInt(6)+1;
        return dado;
    }
	
	public int getpeao(int ind_jog){
		return jog[ind_jog].getposicao();
	}
	
	public void vender_imov(int pos){
		float valor = (float) tabuleiro.preco_compra(pos) + ((tabuleiro.num_casas(pos) +  tabuleiro.num_hotel(pos)) * tabuleiro.preco_construir(pos));
		valor = (float) (valor * 0.9);
		int vendedor = tabuleiro.temdono(pos);
		jog[vendedor].aumenta_dinheiro(valor);
		tabuleiro.novo_dono(pos, -1);
		tabuleiro.alteranum_hotel(pos, 0);
		tabuleiro.alteranum_casas(pos, 0);
		
	}
	
	void eliminar_jogador(int ind_jog){
		for(int i=0;i<40;i++){
			if(tabuleiro.temdono(i) == ind_jog){
				vender_imov(i);
			}
		}
		jog[ind_jog].eliminar_jog();
	}
	
	void pagamento(int ind_jog, int somadados, int pos_nova, int tipo){
		int hotel, casas;
		float valor = 0;
		int dono = tabuleiro.temdono(pos_nova);
		if (tipo == 1){
			hotel = tabuleiro.num_hotel(pos_nova);
			casas = tabuleiro.num_casas(pos_nova);
			valor = tabuleiro.aluguel(pos_nova);
			if (casas != 0){
				valor += tabuleiro.alug_casa(pos_nova, casas);
			}
			if (hotel != 0){
				valor += tabuleiro.alug_hotel(pos_nova);
			}
		}
		if (tipo == 3){
			valor = somadados * tabuleiro.aluguel(pos_nova);
		}
		float dindin_jog = jog[ind_jog].dinheiro_vivo();
		if(valor <= dindin_jog){
			jog[ind_jog].novo_dinheiro(dindin_jog - valor);
			jog[dono].aumenta_dinheiro(valor);
		}
		else{
			dindin_jog = jog[ind_jog].dinheiro_totalcomdesconto();
			if(valor <= dindin_jog){
				System.out.println("Jogador tem que vender alguma propriedade para continuar no jogo!!!");
				/* Fazer codigo para venda de propriedades para continuar no jogo*/
				jog[dono].aumenta_dinheiro(valor);
			}
			else{
				System.out.println("Jogador não tem dinheiro suficiente, ELIMINADO!!!");
				eliminar_jogador(ind_jog);
				jog[dono].aumenta_dinheiro(dindin_jog);
			}
		}
	}
	
	public void mandar_prisao(int ind_jog){
		jog[ind_jog].prender_soltar(1);
		jog[ind_jog].movimenta(10);
	}
	
	public void liberdade_prisao(int ind_jog){
		if(jog[ind_jog].prisao_carta() || jog[ind_jog].prisao_tempo() >= 3 || dado[0] == dado[1]){
			jog[ind_jog].prender_soltar(0);
		}
	}
	public void movimentar(int ind_jog, int somadados){
		int pos_atual = jog[ind_jog].getposicao();
		int pos_nova;
		if( pos_atual + somadados < 40){
			pos_nova = pos_atual + somadados;
		}
		else{
			pos_nova = pos_atual + somadados - 40;
			jog[ind_jog].aumenta_dinheiro(200); /* Passou do ponto de partida ganhou dindin*/
			this.banco -= 200;
		}
		int tipo = tabuleiro.qualtipo(pos_nova);
		if(tipo == 1 || tipo == 3) {
			if(tabuleiro.temdono(pos_nova) != -1 && tabuleiro.temdono(pos_nova) != ind_jog){
				pagamento(ind_jog, somadados, pos_nova, tipo);
			}
			if(tabuleiro.temdono(pos_nova) == ind_jog){
				System.out.println("Jogador pode comprar casa ou hotel para o local!!!");
				/* Fazer codigo para jogador decidir se quer comprar uma casa ou hotel*/
			}
			else{
				System.out.println("Não tem que pagar aluguel, mas jogador pode comprar o local!!!");
				/* Fazer codigo para jogador decidir se quer comprar ou não o campo atual*/
			}
		}
		if(tipo == 2){
			System.out.println("Vai receber uma carta de Sorte ou Reves!!!");
			/* Fazer codigo para tirar carta*/
		}
		if(tipo == 5){
			if(pos_nova == 18){ /* Lucros ou dividendos*/
				jog[ind_jog].aumenta_dinheiro(200);
				this.banco -= 200;
			}
			else{
				jog[ind_jog].aumenta_dinheiro(-200); /* Imposto de renda*/
				this.banco += 200;
			}
		}
		if(tipo == 6){
			mandar_prisao(ind_jog);
			pos_nova = 10;
		}
		jog[ind_jog].movimenta(pos_nova);
	}
	
    
    public boolean ganhou(){ /*vai verificar se os outros jogadores estao eliminados*/
    	int comp = 0;
        for(int i = 0; i<qtd; i++) {
        	if(jog[i].jog_elim()) {
        		comp++;
        	}
        }
        return comp == (qtd - 1);
    }
	
    public char[] ver_ganhador() { /* caso jogo acabe antecipadamente, quem tem mais dinheiro ganha*/
		float[] valores = new float[qtd];
		float aux1;
		char[] ordem = new char[qtd];
		int min, j;
		char aux2;
		for(int i=0;i<qtd;i++){
			ordem[i] = jog[i].getcor();
			valores[i] = jog[i].dinheiro_total();
		}
    	for(int i=0;i<(i+qtd);i++){
			min = i;
			for(j=(i+1);j<qtd;j++){
				if(valores[j]<valores[min]){
					min = j;
				}
			}
			if(i!= min){
				aux1 = valores[i];
				aux2 = ordem[i];
				valores[i] = valores[min];
				ordem[i] = ordem[min];
				valores[min] = aux1;
				ordem[min] = aux2;
			}
		}
    	return ordem;
    }
}
