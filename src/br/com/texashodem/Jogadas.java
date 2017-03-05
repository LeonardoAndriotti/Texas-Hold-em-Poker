package br.com.texashodem;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * Classe responsavel por passar os valores das jogadas
 * 
 * @author Leonardo Andriotti
 *
 */
public class Jogadas extends Carta {

	private static final int CARTA_ALTA = 1;
	private static final int UM_PAR = 2;
	private static final int DOIS_PARES = 3;
	private static final int TRINCA = 4;
	private static final int STRAIGHT = 5;
	private static final int FLUSH = 6;
	private static final int FULL_HOUSE = 7;
	private static final int QUADRA = 8;
	private static final int STRAIGHT_FLUSH = 9;
	private static final int ROYAL_FLUSH = 10;

	// recebe o resultado, das combinações das cartas
	public int result(String cartas) {
		return combinacoes(cartas);
	}

	// retora o tipo d jogada.
	public int combinacoes(String cartas) {

		if (royal_flush(cartas))
			return ROYAL_FLUSH;
		else if (straightFlush(cartas))
			return STRAIGHT_FLUSH;
		else if (quadra(cartas))
			return QUADRA;
		else if (fullHouse(cartas))
			return FULL_HOUSE;
		else if (flush(cartas))
			return FLUSH;
		else if (straight(cartas))
			return STRAIGHT;
		else if (trinca(cartas))
			return TRINCA;
		else if (doisPares(cartas))
			return DOIS_PARES;
		else if (umPar(cartas))
			return UM_PAR;
		else if (cartaAlta(cartas))
			return CARTA_ALTA;

		return 0;
	}

	// A carta de maior valor
	private boolean cartaAlta(String cartas) {
		return true;
	}

	// Duas cartas do mesmo valor
	private boolean umPar(String cartas) {
		String cartasOrdenadas = ordenaCarta(cartas);
		String[] arraycartas = cartasOrdenadas.split("");
		ArrayList<String> cartaArray = new ArrayList<>();
		for (int i = 0; i < arraycartas.length; i += 2) {
			cartaArray.add(arraycartas[i]);
		}

		int count = 0;

		for (int i = 0; i < cartaArray.size(); i++) {
			count++;
			for (int j = i + 1; j < cartaArray.size(); j++) {
				if (cartaArray.get(j) != null && cartaArray.get(i) != null) {
					if (cartaArray.get(i).equals(cartaArray.get(j))) {
						count++;
						cartaArray.set(j, null);
					}
				}

			}

			if (count == 2) {
				return true;
			} else
				count = 0;

		}

		return false;
	}

	// Dois pares diferentes
	private boolean doisPares(String cartas) {

		String cartasOrdenadas = ordenaCarta(cartas);
		String[] arraycartas = cartasOrdenadas.split("");
		ArrayList<String> cartaArray = new ArrayList<>();
		for (int i = 0; i < arraycartas.length; i += 2) {
			cartaArray.add(arraycartas[i]);
		}

		boolean condicaoUm = false;
		boolean condicaoDois = false;
		int count = 0;
		int countDif = 0;

		for (int i = 0; i < cartaArray.size(); i++) {
			count++;
			for (int j = i + 1; j < cartaArray.size(); j++) {
				if (cartaArray.get(j) != null && cartaArray.get(i) != null) {
					if (cartaArray.get(i).equals(cartaArray.get(j))) {
						count++;
						cartaArray.set(j, null);
					}
				}

			}

			if (count == 2) {
				countDif++;
				count = 0;
				condicaoUm = true;
				if (countDif == 2)
					condicaoDois = true;

			} else
				count = 0;

			if (condicaoDois && condicaoUm) {
				return true;
			}
		}

		return false;
	}

	// Três cartas do mesmo valor e duas de valores diferentes
	private boolean trinca(String cartas) {
		String cartasOrdenadas = ordenaCarta(cartas);
		String[] arraycartas = cartasOrdenadas.split("");
		ArrayList<String> cartaArray = new ArrayList<>();
		for (int i = 0; i < arraycartas.length; i += 2) {
			cartaArray.add(arraycartas[i]);
		}

		boolean condicaoUm = false;
		boolean condicaoDois = false;
		int count = 0;
		int countDif = 0;

		for (int i = 0; i < cartaArray.size(); i++) {
			count++;
			for (int j = i + 1; j < cartaArray.size(); j++) {
				if (cartaArray.get(j) != null && cartaArray.get(i) != null) {
					if (cartaArray.get(i).equals(cartaArray.get(j))) {
						count++;
						cartaArray.set(j, null);
					}
				}

			}

			if (count == 1) {
				countDif++;
				count = 0;
				if (countDif == 2)
					condicaoUm = true;

			} else if (count >= 3) {
				condicaoDois = true;
				count = 0;
			} else
				count = 0;

			if (condicaoDois && condicaoUm) {
				return true;
			}
		}

		return false;
	}

	// todas as cartas com valores consecutivos
	private boolean straight(String cartas) {
		String cartasOrdenadas = ordenaCarta(cartas);
		if (sequenciaSemValidacaoNaipe(cartasOrdenadas))
			return true;
		return false;
	}

	// Todas as cartas do mesmo naipe
	private boolean flush(String cartas) {
		String[] arraycartas = cartas.split("");
		ArrayList<String> cartaArray = new ArrayList<>();
		for (int i = 0; i < arraycartas.length; i += 2) {
			cartaArray.add(arraycartas[i + 1]);
		}

		int count = 0;

		for (int i = 0; i < cartaArray.size(); i++) {
			count++;
			for (int j = 0 + 1; j < cartaArray.size(); j++) {
				if (cartaArray.get(i).equals(cartaArray.get(j))) {
					count++;

				}
			}

			if (count >= 5) {
				return true;
			} else
				count = 0;
		}
		return false;
	}

	// Uma trinca e um par
	private boolean fullHouse(String cartas) {
		String cartasOrdenadas = ordenaCarta(cartas);
		String[] arraycartas = cartasOrdenadas.split("");
		ArrayList<String> cartaArray = new ArrayList<>();
		for (int i = 0; i < arraycartas.length; i += 2) {
			cartaArray.add(arraycartas[i]);
		}

		boolean condicaoUm = false;
		boolean condicaoDois = false;
		int count = 0;

		for (int i = 0; i < cartaArray.size(); i++) {
			count++;
			for (int j = i + 1; j < cartaArray.size(); j++) {
				if (cartaArray.get(j) != null && cartaArray.get(i) != null) {
					if (cartaArray.get(i).equals(cartaArray.get(j))) {
						count++;
						cartaArray.set(j, null);
					}
				}

			}

			if (count == 2) {
				condicaoUm = true;
				count = 0;
			} else if (count >= 3) {
				condicaoDois = true;
				count = 0;
			} else
				count = 0;

			if (condicaoDois && condicaoUm) {
				return true;
			}
		}
		return false;
	}

	// Quatro cartas do mesmo valor
	private boolean quadra(String cartas) {
		String[] arraycartas = cartas.split("");
		ArrayList<String> cartaArray = new ArrayList<>();
		for (int i = 0; i < arraycartas.length; i += 2) {
			cartaArray.add(arraycartas[i]);
		}
		int count = 0;
		for (int i = 0; i < cartaArray.size(); i++) {
			for (int j = 1; j < cartaArray.size(); j++) {
				if (cartaArray.get(i).equals(cartaArray.get(j))) {
					count++;
				}
			}
			if (count >= 4) {
				return true;
			} else {
				count = 0;
			}
		}

		return false;
	}

	// Todas as cartas são consecutivas e do mesmo naipe
	private boolean straightFlush(String cartas) {
		String cartasOrdenadas = ordenaCarta(cartas);
		if (sequencia(cartasOrdenadas)) {
			return true;
		}
		return false;
	}

	// A sequência 10, Valete, Dama, Rei, Ás, do mesmo naipe
	private boolean royal_flush(String cartas) {
		if (cartas.contains("A") && cartas.contains("K") && cartas.contains("J") && cartas.contains("Q")
				&& cartas.contains("D")) {
			if (cartas.substring(cartas.indexOf("A") + 1, cartas.indexOf("A") + 2)
					.equals(cartas.substring(cartas.indexOf("K") + 1, cartas.indexOf("K") + 2))
					&& cartas.substring(cartas.indexOf("K") + 1, cartas.indexOf("K") + 2)
							.equals(cartas.substring(cartas.indexOf("Q") + 1, cartas.indexOf("Q") + 2))
					&& cartas.substring(cartas.indexOf("Q") + 1, cartas.indexOf("Q") + 2)
							.equals(cartas.substring(cartas.indexOf("J") + 1, cartas.indexOf("J") + 2))
					&& cartas.substring(cartas.indexOf("J") + 1, cartas.indexOf("J") + 2)
							.equals(cartas.substring(cartas.indexOf("D") + 1, cartas.indexOf("D") + 2)))
				return true;
		}
		return false;
	}

	private boolean sequenciaSemValidacaoNaipe(String cartasOrdenadas) {
		String[] arraycartas = cartasOrdenadas.split("");
		ArrayList<String> cartaArray = new ArrayList<>();
		for (int i = 0; i < arraycartas.length; i += 2) {
			cartaArray.add(arraycartas[i]);
		}

		int numeroSeq = 0;
		for (int i = cartaArray.size() - 1; i > 0; i--) {
			if (valorCartas(cartaArray.get(i)) - valorCartas(cartaArray.get(i - 1)) == 1) {
				numeroSeq++;
			} else if (valorCartas(cartaArray.get(i)) - valorCartas(cartaArray.get(i - 1)) > 1) {
				if (numeroSeq < 4) {
					numeroSeq = 0;
				}

			}
		}
		if (numeroSeq >= 4) {
			return true;
		}

		return false;
	}

	private boolean sequencia(String cartasOrdenadas) {
		String[] arraycartas = cartasOrdenadas.split("");
		ArrayList<String> cartaArray = new ArrayList<>();
		ArrayList<String> naipeArray = new ArrayList<>();
		for (int i = 0; i < arraycartas.length; i += 2) {
			cartaArray.add(arraycartas[i]);
			naipeArray.add(arraycartas[i + 1]);
		}

		int numeroSeq = 0;
		for (int i = cartaArray.size() - 1; i > 0; i--) {
			if (valorCartas(cartaArray.get(i)) - valorCartas(cartaArray.get(i - 1)) == 1
					&& naipeArray.get(i).equals(naipeArray.get(i - 1))) {
				numeroSeq++;
			} else {
				if (numeroSeq < 5)
					numeroSeq = 0;
			}
		}
		if (numeroSeq >= 4) {
			return true;
		}
		return false;
	}

	private String ordenaCarta(String cartas) {
		String[] arraycartas = cartas.split("");
		String aux;
		String auxNaipe;
		StringBuilder sbCartas = new StringBuilder();
		for (int i = arraycartas.length - 2; i >= 1; i -= 2) {
			for (int j = 0; j < i; j += 2) {
				if (valorCartas(arraycartas[j]) > valorCartas(arraycartas[j + 2])) {
					aux = arraycartas[j];
					arraycartas[j] = arraycartas[j + 2];
					arraycartas[j + 2] = aux;

					auxNaipe = arraycartas[j + 1];
					arraycartas[j + 1] = arraycartas[j + 3];
					arraycartas[j + 3] = auxNaipe;
				}
			}
		}

		for (String carta : arraycartas) {
			sbCartas.append(carta);
		}

		return sbCartas.toString();
	}

}
