package Cliente;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class Cliente {

	int porta = 80;
	Socket cliente;
	View janela;
	PrintWriter saida;
	BufferedReader entrada;
	String nomeCliente;
	boolean status;

	//________________________________________________________________________________________________________________________________________________

	public void conexao() {

		String ip = JOptionPane.showInputDialog("Digite o IP do Servidor");

		try {
			cliente = new Socket ( ip , porta);
			janela = new View ();
			new Thread() {

				@Override
				public void run() {

					try {

						entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

						while(true) {
							String m = entrada.readLine();
							janela.mandarMensagem(m);
							System.out.println(m);
							if(nomeCliente != null) {
								atualizarLista();
							}
						}


					} catch (IOException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(janela, "Sem conexão com o servidor");
						System.exit(0);

					}

				}
			}.start();

			//________________________________________________________________________________________________________________________________________________

			saida = new PrintWriter(cliente.getOutputStream(),true);
			nomeCliente = JOptionPane.showInputDialog("Digite o seu nome");
			saida.println(nomeCliente);
			saida.flush();


			while(true) {  
				System.out.println(janela.getMensagem());
				String mt = janela.getMensagem();
				if (mt != null) {
					janela.limparTexto();
					System.err.println(mt); 
					mandarMsgCliente(mt);
					if(status) {
						saida.println(mt);
					}
					mt = null;

				}

			}

		} catch (UnknownHostException e) {
			System.err.println("Endereço invalido");
			e.printStackTrace();
			JOptionPane.showMessageDialog(janela, "Endereço de IP incorreto ou servidor offline");
			conexao();
		} catch (IOException e) {
			System.err.println("Sem conexao com o servidor...");
			e.printStackTrace();
		}
	}


	//________________________________________________________________________________________________________________________________________________

	public void mandarMsgCliente(String mensagem) {

		Object usuario = janela.jList1.getSelectedValue();
		if(usuario != null){
			saida.println("@usuario" + usuario);
			janela.mandarMensagem(mensagem);
			status = true;
		}else{
			JOptionPane.showMessageDialog(janela, "Selecione um usuario");	
			status = false;
		}
	}

	public void atualizarLista() throws IOException {
		saida.println("@listaClientes");
		String lista = entrada.readLine();
		String[]array = lista.split(",");
		janela.jList1.setListData(array);

	}

	//________________________________________________________________________________________________________________________________________________

	public static void main(String []args) {

		Cliente c = new Cliente();
		c.conexao();
	}
}
