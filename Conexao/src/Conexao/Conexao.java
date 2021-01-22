package Conexao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Conexao extends Thread {

	//________________________________________________________________________________________________________________________________________________

	private Socket clientes; // Gera as conexões entre cliente e servidor
	private String nomeCliente; // Usada para registro do cliente
	public String mensagem; 
	public static final Map<String, Conexao> cliente = new HashMap<String, Conexao>(); //Registro de conexao dos clientes
	private BufferedReader entrada; //Variavel que controla a entrada de mensagens para os clientes
	private PrintWriter saida; //Variavel que controla a saida de mensagens para os clientes
	public static String lista = ""  ;

	//Metodo construtor da classe________________________________________________________________________________________________________________________________________________

	public Conexao (Socket clientes) {
		this.clientes = clientes;
		start(); //inicia a thread
	}


	//get&set________________________________________________________________________________________________________________________________________________

	public String getNomeCliente() {
		return nomeCliente;
	}


	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}


	public PrintWriter getSaida() {
		return saida;
	}


	public void setSaida(PrintWriter saida) {
		this.saida = saida;
	}

	//________________________________________________________________________________________________________________________________________________

	public void run() { //Execução da thread



		try {
			comunicacao(); // Inicia a Troca de mensagens
			saida.println("Conectado"); 
			registroCliente(); //Solicita o nome do cliente e registra o clente por meio do map Cliente
			

			while(true) { //mantem a troca de mensagens
				mensagem = entrada.readLine(); 

				if(mensagem.equals("@listaClientes")) {
					lista();
					continue;
				} else {
					if(mensagem.toLowerCase().startsWith("@usuario")){
						String nomeDestinatario = mensagem.substring(8 , mensagem.length());
						Conexao destinatario =  cliente.get(nomeDestinatario);
						if (destinatario == null) {

							
						}else {

							destinatario.getSaida().println(this.nomeCliente + ": " + entrada.readLine());
						}
					}else {
						
					}
				}
			}


		} catch (IOException e) {

			System.err.println("Sem Conexao com o cliente...");
			
		}


	}


	//________________________________________________________________________________________________________________________________________________



	private void comunicacao () throws IOException {
		entrada = new BufferedReader(new InputStreamReader(clientes.getInputStream()));
		saida = new PrintWriter(clientes.getOutputStream(),true);
	}


	private void registroCliente () throws IOException {
		mensagem = entrada.readLine();
		this.nomeCliente = mensagem;
		cliente.put(nomeCliente, this);
		saida.println("Ola " + nomeCliente);
		lista = lista + this.nomeCliente + ",";

	}

	public void lista() {
		saida.println(lista);
	}
}
	




