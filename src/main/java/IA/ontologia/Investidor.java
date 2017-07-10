package IA.ontologia;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

public class Investidor {

	String nome, conhecimento, sonho, risco;
	int rendaFamiliar, investimentoInicial, prazo;

	Investidor(String nome, int rendaFamiliar, String conhecimento, String sonho, String risco, int prazo) {
		this.nome = nome;
		this.conhecimento = conhecimento;
		this.sonho = sonho;
		this.risco = risco;
		this.rendaFamiliar = rendaFamiliar;
		this.prazo = prazo;
	}

	public void criarConhecimento(String arquivo) throws OWLOntologyStorageException, IOException, Exception {
		try {
			Ontologia ontologia = new Ontologia();
			ontologia.carregarOWL(arquivo);
			ontologia.criarInferenciador();

			ontologia.adicionarAxiomasPropriedade("temConhecimento", "carinha", retornaConhecimento());
			ontologia.criarPropriedadeDeInformacao("temRisco", "carinha", retornaRisco(), OWL2Datatype.RDF_PLAIN_LITERAL);
			ontologia.criarPropriedadeDeInformacao("temPrazo", "carinha", retornaPrazo(), OWL2Datatype.RDF_PLAIN_LITERAL);
			ontologia.criarPropriedadeDeInformacao("temRetorno", "carinha", retornaSonho(), OWL2Datatype.RDF_PLAIN_LITERAL);
			ontologia.criarPropriedadeDeInformacao("classeSocial", "carinha", retornaToleranciaRisco(), OWL2Datatype.RDF_PLAIN_LITERAL);

			ontologia.atualizarOntologia(arquivo.substring(0,arquivo.length()-5) + "mod.owl");
//			return ontologia.mostrarClasseDepoisInferencia("carinha");
			
		} catch (OWLOntologyCreationException ex) {
			Logger.getLogger(Investidor.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
	public void inferir(String arquivo) {
		Ontologia ontologia = new Ontologia();
		try {
			ontologia.carregarOWL(arquivo);
			ontologia.criarInferenciador();
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível descobrir seu perfil de investidor");
		}
		JOptionPane.showMessageDialog(null, ontologia.mostrarClasseDepoisInferencia("carinha"));
	}

	public String retornaNome() {
		return nome;
	}

	private String retornaConhecimento() {
		if (conhecimento == "Ações")
			return "bolsa";
		if (conhecimento == "Certificado de Depósito Bancário")
			return "certificado";
		if (conhecimento == "Letras de Crédito")
			return "letras_de_credito";
		if (conhecimento == "Títulos Públicos")
			return "titulos_publicos";
		if (conhecimento == "Sem Conhecimento")
			return "sem_conhecimento";
		return "sem_conhecimento";
	}

	private String retornaSonho() {
		if (sonho.equals("Reforma de Imóveis")) {
			return "pequeno";
		} else if (sonho.equals("Compra de Imóvel")) {
			return "grande";
		}
		return "medio";
	}

	private String retornaRisco() {
		if (risco.equals("Sim")) {
			return "grande";
		} else if (risco.equals("Preferivelmente Não")) {
			return "medio";
		}
		return "pequeno";
	}

	private String retornaToleranciaRisco() {
		if (rendaFamiliar <= 3000) {
			return "pequeno";
		}
		return "grande";
	}

	private String retornaPrazo() {
		if (prazo <= 6) {
			return "pequeno";
		} else if (prazo > 6 && prazo <= 24) {
			return "medio";
		}
		return "grande";
	}
}
