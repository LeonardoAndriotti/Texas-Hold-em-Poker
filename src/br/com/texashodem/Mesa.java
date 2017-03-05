package br.com.texashodem;

import java.util.HashMap;
/*
 *  separa a entrada para jogador um, jogador dois e mesa
 */
public class Mesa extends Carta {
	HashMap<String, String> cartaMap = new HashMap<>();

	public void separador(String linha) {
		jogadorUm(linha.substring(0, 4));
		jogadorDois(linha.substring(4, 8));
		mesa(linha.substring(8));
		resultado();
	}

	public void jogadorUm(String mao) {
		cartaMap.put("jogadorUm", mao);
	}

	public void jogadorDois(String mao) {
		cartaMap.put("jogadorDois", mao);
	}

	public void mesa(String mesa) {
		cartaMap.put("mesa", mesa);
	}

	//saida no console do resultado das cartas dos jogadores
	public void resultado() {
		System.out.println(vencedorTexasHoldem(cartaMap));
	}
}
