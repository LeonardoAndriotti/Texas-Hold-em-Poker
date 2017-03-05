package br.com.texashodem;

import java.util.ArrayList;
import java.util.HashMap;

public class Carta {
	int maiorJgadorUm = 0;
	int maiorJgadorDois = 0;

	// faz a comparação, quem tem melhor mão
	public String vencedorTexasHoldem(HashMap carta) {

		StringBuilder sbCarta = new StringBuilder();
		sbCarta.append(carta.get("jogadorUm"));
		sbCarta.append(carta.get("mesa"));
		int resultadoJogadorUm = new Jogadas().result(sbCarta.toString());

		if (resultadoJogadorUm == 1) {
			String[] arraycartas = sbCarta.toString().split("");
			ArrayList<String> cartaArray = new ArrayList<>();
			for (int i = 0; i < arraycartas.length; i += 2) {
				cartaArray.add(arraycartas[i]);
			}

			for (int i = 0; i < cartaArray.size() - 1; i++) {
				if (maiorJgadorUm < valorCartas(cartaArray.get(i))) {
					maiorJgadorUm = valorCartas(cartaArray.get(i));
				}
			}
		}

		sbCarta = new StringBuilder();
		sbCarta.append(carta.get("jogadorDois"));
		sbCarta.append(carta.get("mesa"));
		int resultadoJogadorDois = new Jogadas().result(sbCarta.toString());

		if (resultadoJogadorDois == 1) {
			String[] arraycartas = sbCarta.toString().split("");
			ArrayList<String> cartaArray = new ArrayList<>();
			for (int i = 0; i < arraycartas.length; i += 2) {
				cartaArray.add(arraycartas[i]);
			}

			for (int i = 0; i < cartaArray.size() - 1; i++) {
				if (maiorJgadorDois < valorCartas(cartaArray.get(i))) {
					maiorJgadorDois = valorCartas(cartaArray.get(i));
				}
			}
		}

		if (resultadoJogadorUm > resultadoJogadorDois)
			return "Jogador 1";
		else if (resultadoJogadorDois > resultadoJogadorUm)
			return "Jogador 2";
		else if (resultadoJogadorDois == resultadoJogadorUm && (resultadoJogadorUm != 1 && resultadoJogadorDois != 1))
			return "Empate";
		else if (resultadoJogadorUm == 1 && resultadoJogadorDois == 1) {
			if (maiorJgadorUm > maiorJgadorDois)
				return "Jogador 1";
			else if (maiorJgadorUm < maiorJgadorDois)
				return "Jogador 2";
			else if (maiorJgadorUm == maiorJgadorDois)
				return "Empate";
		} else
			return "Entrada Inválida";

		return "Entrada Inválida";
	}
 // valor das cartas
	public int valorCartas(String carta) {
		switch (carta) {
		case "2":
			return 2;
		case "3":
			return 3;
		case "4":
			return 4;
		case "5":
			return 6;
		case "7":
			return 7;
		case "8":
			return 8;
		case "9":
			return 9;
		case "D":
			return 10;
		case "J":
			return 11;
		case "Q":
			return 12;
		case "K":
			return 13;
		case "A":
			return 14;

		default:
			break;
		}
		return 0;
	}

}
