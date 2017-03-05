package br.com.texashodem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * Leitura do arquivo txt
 */
public class LeituraCartas {

	public static void leitor(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";
		while (true) {
			if (linha != null) {
				if (linha.length() <= 0 || linha.length() < 18)
					System.out.println("Entrada inválida");
				else
					new Mesa().separador(linha);

			} else
				break;
			linha = buffRead.readLine();
		}
		buffRead.close();
	}

	public static void main(String[] args) {
		//insira o caminho 
		String path = "C:\\Users\\Leonardo Andriotti\\workspace\\TexasHoldem\\arquivo.txt";

		try {
			LeituraCartas.leitor(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
