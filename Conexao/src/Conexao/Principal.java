package Conexao;

import java.io.IOException;
import java.net.Socket;

public class Principal {
	public static void main(String[] args) throws IOException {

		//________________________________________________________________________________________________________________________________________________

		int porta = 80;
		Socket socket = null;
		Servidor servidor = null; 

		//________________________________________________________________________________________________________________________________________________

		try {

			servidor = new Servidor(); 
			System.out.println("Aguardando conexao...");
			servidor.criarServidor(porta); //cria sevidor
			//________________________________________________________________________________________________________________________________________________	 

			while(true) {
				socket = servidor.esperarConexao(); //Aguarda uma conex�o com o cliente
				@SuppressWarnings("unused")
				Conexao conexao = new Conexao(socket); 
			}
		} catch (IOException e) {
			System.err.println("Sem conex�o com o cliente");
			servidor.fecharSocket(socket);

		}
	}
}
