package IA.ontologia;

/**
 *
 * @author Beckhauser
 */
public class Tela extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5084652617691057489L;
	/**
	 * Creates new form Tela
	 */
	public Tela() {
		initComponents();
	}

	private void initComponents() {
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		conhecimento = new javax.swing.JComboBox<String>();
		nome = new javax.swing.JTextField();
		renda = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		investimento_inicial = new javax.swing.JTextField();
		jLabel6 = new javax.swing.JLabel();
		sonho = new javax.swing.JComboBox<String>();
		jLabel7 = new javax.swing.JLabel();
		riscos = new javax.swing.JComboBox<String>();
		jLabel8 = new javax.swing.JLabel();
		prazo = new javax.swing.JTextField();
		ok = new javax.swing.JButton();
		sair = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
		jLabel1.setText("Sistema de Apoio a Decisão de Investimento:");

		jLabel2.setText("Qual o seu nome?");

		jLabel3.setText("Qual a sua renda média familiar?");

		jLabel4.setText("Você tem conhecimento sobre algum tipo de investimento listado?");

		conhecimento.setModel(new javax.swing.DefaultComboBoxModel<String>(
				new String[] { "Ações", "Certificado de Depósito Bancário", "Letras de Crédito", "Títulos Públicos", "Sem Conhecimento" }));

		nome.setToolTipText("");
		nome.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nomeActionPerformed(evt);
			}
		});

		jLabel5.setText("Quanto você tem para investir inicialmente num possível investimento?");

		investimento_inicial.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				investimento_inicialActionPerformed(evt);
			}
		});

		jLabel6.setText("Qual o seu sonho? (no que você investiria com o dinheiro ganho?)");

		sonho.setModel(new javax.swing.DefaultComboBoxModel<String>(
				new String[] { "Reforma de Imóveis", "Viagem", "Automóvel", "Compra de Imóvel" }));

		jLabel7.setText("Você está disposto a assumir riscos no investimento ou prefere algo mais seguro?");

		riscos.setModel(
				new javax.swing.DefaultComboBoxModel<String>(new String[] { "Sim", "Sim mas de forma moderada", "Preferivelmente Não" }));

		jLabel8.setText("Para você, quanto tempo essa aplicação deveria gerar lucros? (responda em meses)");

		ok.setText("Ok");
		ok.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okActionPerformed(evt);
			}
		});

		sair.setText("Sair");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel5)
										.addComponent(jLabel6).addComponent(jLabel7).addComponent(jLabel8))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addGap(0, 1, Short.MAX_VALUE)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																.addComponent(investimento_inicial, javax.swing.GroupLayout.PREFERRED_SIZE,
																		226, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addContainerGap())
														.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
																layout.createSequentialGroup()
																		.addComponent(riscos, javax.swing.GroupLayout.PREFERRED_SIZE, 226,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addContainerGap())))
										.addGroup(layout.createSequentialGroup().addComponent(prazo).addContainerGap())))
						.addGroup(layout.createSequentialGroup().addComponent(ok)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(sair)
								.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup().addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel4).addComponent(jLabel2).addComponent(jLabel3))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
														.addComponent(renda).addComponent(nome)
														.addComponent(conhecimento, 0, 224, Short.MAX_VALUE))
												.addComponent(sonho, javax.swing.GroupLayout.PREFERRED_SIZE, 226,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGroup(layout.createSequentialGroup().addComponent(jLabel1).addGap(2, 2, 2))).addContainerGap()))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap().addComponent(jLabel1).addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(nome,
						javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(10, 10, 10)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3).addComponent(renda,
						javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel4).addComponent(
						conhecimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel5).addComponent(
						investimento_inicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel6).addComponent(sonho,
						javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(7, 7, 7)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel7).addComponent(riscos,
						javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(7, 7, 7)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel8).addComponent(prazo,
						javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(21, 21, 21)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(ok).addComponent(sair))
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void okActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_okActionPerformed
		Investidor investidor = new Investidor(nome.getText(), Integer.parseInt(renda.getText()),
				String.valueOf(conhecimento.getSelectedItem()), Integer.parseInt(investimento_inicial.getText()),
				String.valueOf(sonho.getSelectedItem()), String.valueOf(riscos.getSelectedItem()), Integer.parseInt(prazo.getText()));
		// App.rodarOntologia(investidor);
		System.out.println(investidor.retornaNome());
		System.out.println(investidor.retornaRentaFamiliar());
		System.out.println(investidor.retornaConhecimento());
		System.out.println(investidor.retornaInvestimentoInicial());
		System.out.println(investidor.retornaSonho());
		System.out.println(investidor.retornaRisco());
		System.out.println(investidor.retornaPrazo());
	}// GEN-LAST:event_okActionPerformed

	private void nomeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_nomeActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_nomeActionPerformed

	private void investimento_inicialActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_investimento_inicialActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_investimento_inicialActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Tela().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JComboBox<String> conhecimento;
	private javax.swing.JTextField investimento_inicial;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JTextField nome;
	private javax.swing.JButton ok;
	private javax.swing.JTextField prazo;
	private javax.swing.JTextField renda;
	private javax.swing.JComboBox<String> riscos;
	private javax.swing.JButton sair;
	private javax.swing.JComboBox<String> sonho;
	// End of variables declaration//GEN-END:variables
}
