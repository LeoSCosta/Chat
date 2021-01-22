package Conexao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	private ServerSocket servidor;
	
	
	public void criarServidor(int porta) throws IOException {
		servidor = new ServerSocket(porta); // Criação do servidor, é necessario a porta pela qual sera feita a comunicação 
	}
	
	public Socket esperarConexao() throws IOException {
		Socket socket = servidor.accept(); //Espera uma conexao que sera validada pelo metodo SeverSocket.accept()
		return socket;		
	}
	
	
	
	public void fecharSocket(Socket socket) throws IOException {
		socket.close();
	}
}
