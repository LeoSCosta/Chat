package Cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class View extends javax.swing.JFrame {
	String[] strings = { };
	
	//Construtor da classe________________________________________________________________________________________________________________________________________________
	
	public View() {
		
		
		setTitle("Chat");
		initComponents();
		setVisible(true);
	}
	//________________________________________________________________________________________________________________________________________________

	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jScrollPane2 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList<>();
		jScrollPane3 = new javax.swing.JScrollPane();
		jTextArea2 = new javax.swing.JTextArea();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

		// definições dos paineis________________________________________________________________________________________________________________________________________________

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jTextArea1.setEditable(false);
		jTextArea1.setWrapStyleWord(true);

		jScrollPane1.setViewportView(jTextArea1);
		jList1.setModel(new javax.swing.AbstractListModel<String>() {

			private static final long serialVersionUID = 1L;	

			public int getSize() { return strings.length; }
			public String getElementAt(int i) { return strings[i]; }
		});
		
		
		jScrollPane2.setViewportView(jList1);
		jTextArea2.setColumns(20);
		jTextArea2.setRows(5);
		jScrollPane3.setViewportView(jTextArea2);
		
		if(jList1.isSelectionEmpty()) {
		
		}
		else 
			jList1.addListSelectionListener(new ListSelectionListener() {

				@Override
				public void valueChanged(ListSelectionEvent arg0) {
					System.out.println(arg0);
					
				}
				
			});
		
		
		// ouvinte do teclado para o jTextArea de escrita________________________________________________________________________________________________________________________________________________
		
		jTextArea2.addKeyListener(new KeyListener() {



			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					escrever();
				}
			}


			public void keyReleased(KeyEvent arg0) {


			}


			public void keyTyped(KeyEvent arg0) {


			}

		});
		//Ouvintes dos botões________________________________________________________________________________________________________________________________________________

		jButton1.setText("ENVIAR");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
				escrever();

			}
		});

		jButton2.setText("SAIR");
		jButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}

		});

		//Definição do layout________________________________________________________________________________________________________________________________________________


		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)))
								.addComponent(jScrollPane1))
						.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jScrollPane2)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
												.addGroup(layout.createSequentialGroup()
														.addComponent(jButton1)
														.addGap(18, 18, 18)
														.addComponent(jButton2)))))
						.addContainerGap())
				);

		pack();
	}               
	//Declaração das variaveis________________________________________________________________________________________________________________________________________________


	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) { 

	} 

	private javax.swing.JButton jButton2;
	public javax.swing.JList<String> jList1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextArea jTextArea2;
	private javax.swing.JButton jButton1;
	private String mensagem;

	//get&set________________________________________________________________________________________________________________________________________________


	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	//metodos da classe________________________________________________________________________________________________________________________________________________
	
	
	
	public void mandarMensagem (String texto) {
		
		
		if(texto.length()!= 0) {
			this.jTextArea1.append(texto);
			this.jTextArea1.append(System.getProperty("line.separator") );
		}
		

	}

	public void escrever() {   
		if(jTextArea2.getText().isEmpty()) {

		}else {
			mensagem =jTextArea2.getText();
			System.err.println(mensagem);
		}
	}

	public void limparTexto() {
		jTextArea2.setText(null);	
		mensagem = null;
	}
}

