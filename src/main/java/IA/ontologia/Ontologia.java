package IA.ontologia;

import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

public class Ontologia {

	public Ontologia() {
	}

	private OWLOntologyManager _gerenciador;
	private IRI _endereco;
	private OWLOntology _ontologia;
	private OWLReasoner _inferenciar;

	/**
	 * Método responsável por criar uma instancia da ontologia carregado de um arquivo.
	 * @param arquivo
	 * @throws OWLOntologyCreationException
	 */
	public void criarOntologia(String arquivo) throws OWLOntologyCreationException {
		_gerenciador = OWLManager.createOWLOntologyManager();
		File arq = new File(arquivo);
		_ontologia = _gerenciador.loadOntologyFromOntologyDocument(arq);
		_endereco = IRI.create("http://www.semanticweb.org/root/ontologies/2017/5/untitled-ontology-2");
	}
	
	public void adicionarPropriedade(String nomeInstancia, String nomeProp, String valorProp, OWL2Datatype tipo) {
		OWLDataFactory fac = 
	}

}
