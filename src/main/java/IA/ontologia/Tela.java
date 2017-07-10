/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA.ontologia;

import java.io.File;

import javax.swing.JFileChooser;

/**
 *
 * @author Beckhauser
 */
public class Tela extends javax.swing.JFrame {

	private javax.swing.JComboBox<String> conhecimento;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JTextField nome;
	private javax.swing.JButton ok;
	private javax.swing.JTextField prazo;
	private javax.swing.JTextField renda;
	private javax.swing.JComboBox<String> riscos;
	private javax.swing.JComboBox<String> sonho;
	private final App _app;

	public Tela(App app) {
		_app = app;
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
		jLabel6 = new javax.swing.JLabel();
		sonho = new javax.swing.JComboBox<String>();
		jLabel7 = new javax.swing.JLabel();
		riscos = new javax.swing.JComboBox<String>();
		jLabel8 = new javax.swing.JLabel();
		prazo = new javax.swing.JTextField();
		ok = new javax.swing.JButton();

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

		jLabel6.setText("Qual o seu sonho? (no que você investiria com o dinheiro ganho?)");
		sonho.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Reforma de Imóveis", "Compra de Imóveis", "Outro" }));
		sonho.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sonhoActionPerformed(evt);
			}
		});
		jLabel7.setText("Você está disposto a assumir riscos no investimento ou prefere algo mais seguro?");
		riscos.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { "Sim", "Preferivelmente Não", "Não" }));
		jLabel8.setText("Para você, quanto tempo essa aplicação deveria gerar lucros? (responda em meses)");
		ok.setText("Ok");
		ok.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okActionPerformed(evt);
			}
		});
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel4).addComponent(jLabel2).addComponent(jLabel3))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
														.addComponent(renda).addComponent(nome)
														.addComponent(conhecimento, 0, 224, Short.MAX_VALUE)))
										.addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
								.addGap(2, 2, 2))
						.addGroup(layout.createSequentialGroup().addComponent(jLabel6)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(sonho, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addComponent(jLabel7)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(riscos, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addComponent(jLabel8)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(prazo))
						.addGroup(layout.createSequentialGroup().addComponent(ok).addGap(0, 0, Short.MAX_VALUE)))
				.addContainerGap()));
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
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel6).addComponent(sonho,
						javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel7).addComponent(riscos,
						javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel8).addComponent(prazo,
						javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(ok)
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void okActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_okActionPerformed
		Investidor investidor = new Investidor(nome.getText(), Integer.parseInt(renda.getText()),
				String.valueOf(conhecimento.getSelectedItem()), String.valueOf(sonho.getSelectedItem()),
				String.valueOf(riscos.getSelectedItem()), Integer.parseInt(prazo.getText()));
		Investidor investidor2 = new Investidor(nome.getText(), Integer.parseInt(renda.getText()),
				String.valueOf(conhecimento.getSelectedItem()), String.valueOf(sonho.getSelectedItem()),
				String.valueOf(riscos.getSelectedItem()), Integer.parseInt(prazo.getText()));
		// _app.callback(investidor);
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(Tela.this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				investidor.criarConhecimento(file.getAbsolutePath());
				fc = new JFileChooser();
				returnVal = fc.showOpenDialog(Tela.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File f2 = fc.getSelectedFile();
					investidor2.inferir(f2.getAbsolutePath());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(file.getAbsolutePath());
		}
		
	}
	

	private void nomeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_nomeActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_nomeActionPerformed

	private void sonhoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_sonhoActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_sonhoActionPerformed

}