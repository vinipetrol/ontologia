package IA.ontologia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLLogicalAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.reasoner.ConsoleProgressMonitor;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

public class Ontologia {
	private OWLOntologyManager gerenciador;
	private IRI endereco;
	private OWLOntology ontologia;
	private OWLReasoner reasoner;

	/**
	 * Método que inicializa uma ontologia carregada de um arquivo, e inicializa
	 * variávis globais.
	 * 
	 * @param path
	 * @throws OWLOntologyCreationException
	 */
	public void carregarOWL(String arquivo) throws OWLOntologyCreationException {
		gerenciador = OWLManager.createOWLOntologyManager();
		File file = new File(arquivo);
		ontologia = gerenciador.loadOntologyFromOntologyDocument(file);
		endereco = IRI.create("http://www.semanticweb.org/root/ontologies/2017/6/ontologia");
	}

	/**
	 * Método que cria uma axioma de propriedade entre 2 individuos.
	 * 
	 * @param propriedade
	 *            Nome da propriedade
	 * @param instancia1
	 *            Nome do indivíduo 1
	 * @param instancia2
	 *            Nome do indivíduo 2
	 * @throws Exception
	 */
	public void adicionarAxiomasPropriedade(String propriedade, String instancia1, String instancia2) throws Exception {
		OWLDataFactory fac = gerenciador.getOWLDataFactory();
		OWLIndividual i1 = fac.getOWLNamedIndividual(IRI.create("#" + instancia1));
		OWLIndividual i2 = fac.getOWLNamedIndividual(IRI.create("#" + instancia2));
		OWLObjectProperty prop = fac.getOWLObjectProperty(IRI.create("#" + propriedade));
		OWLObjectPropertyAssertionAxiom propertyAssertion = fac.getOWLObjectPropertyAssertionAxiom(prop, i1, i2);
		gerenciador.addAxiom(ontologia, propertyAssertion);
	}

	/**
	 * Método que adiciona uma propriedade a um indivíduo
	 * 
	 * @param propriedade
	 *            Nome da propriedade
	 * @param instancia
	 *            Nome do indivíduo
	 * @param valor
	 *            Valor da propriedade
	 * @param tipo
	 *            Tipo do valor
	 * @throws OWLOntologyStorageException
	 * @throws IOException
	 * @throws OWLOntologyCreationException
	 */
	public void criarPropriedadeDeInformacao(String propriedade, String instancia, String valor, OWL2Datatype tipo)
			throws OWLOntologyStorageException, IOException {
		OWLDataFactory fac = gerenciador.getOWLDataFactory();
		OWLIndividual i = fac.getOWLNamedIndividual(IRI.create("#" + instancia));
		OWLDataProperty dado = fac.getOWLDataProperty(IRI.create("#" + propriedade));
		OWLDatatype owlTipo = fac.getOWLDatatype(tipo.getIRI());
		OWLLiteral literal = fac.getOWLLiteral(valor, owlTipo);
		gerenciador.addAxiom(ontologia, fac.getOWLDataPropertyAssertionAxiom(dado, i, literal));
	}

	public void criarInferenciador() {
		OWLReasonerFactory rf = new Reasoner.ReasonerFactory();
		reasoner = rf.createNonBufferingReasoner(ontologia, new SimpleConfiguration(new ConsoleProgressMonitor()));
	}

	public void inferir() {
		reasoner.precomputeInferences();
	}

	/**
	 * Método que retorna as instancias de uma classe dado o nome da mesma.
	 * 
	 * @param nomeClasse
	 *            Nome da classe
	 * @return Lista de nome dos indivíduos da classe
	 */
	public List<String> getInstanciaPeloNomeClasse(String nomeClasse) {
		OWLReasonerFactory rf = new Reasoner.ReasonerFactory();
		OWLReasoner reasoner = rf.createNonBufferingReasoner(ontologia, new SimpleConfiguration(new ConsoleProgressMonitor()));
		reasoner.precomputeInferences();
		OWLClass owlClassName = gerenciador.getOWLDataFactory().getOWLClass(IRI.create(endereco + "#" + nomeClasse));
		NodeSet<OWLNamedIndividual> individuos = reasoner.getInstances(owlClassName, false);
		List<String> instancias = new ArrayList<String>();
		for (OWLNamedIndividual ind : individuos.getFlattened()) {
			System.out.println("Individuo ----------------- " + ind);
			instancias.add(ind.toString());
		}
		return instancias;
	}

	/**
	 * Método que exibe o individuo dado nome da classe e da instância.
	 * 
	 * @param nomeClasse
	 *            Nome da classe
	 * @param nomeInstancia
	 *            Nome da instância
	 */
	public void mostrarIndividuoPorNomeClasseEInstancia(String nomeClasse, String nomeInstancia) {
		OWLClass owlClasse = gerenciador.getOWLDataFactory().getOWLClass(IRI.create(endereco + "#" + nomeClasse));
		NodeSet<OWLNamedIndividual> conjunto = reasoner.getInstances(owlClasse, false);
		Set<OWLNamedIndividual> individuos = conjunto.getFlattened();
		System.out.println(individuos.isEmpty());
		for (OWLNamedIndividual ind : individuos) {
			if (ind.toString().indexOf(endereco + "#" + nomeInstancia) != -1) {
				System.out.println("Instancia: " + nomeInstancia + " ------------ Classe: " + nomeClasse);
			}
			System.out.println(" ----------- Indivíduo" + ind + "\n");
		}
	}

	/**
	 * Método que mostra as propriedades das instâncias.
	 * 
	 * @throws Exception
	 */
	public void mostrarPropriedadesDasInstancias() throws Exception {
		OWLReasonerFactory rf = new Reasoner.ReasonerFactory();
		OWLReasoner rea = rf.createReasoner(ontologia, new SimpleConfiguration(new ConsoleProgressMonitor()));
		rea.precomputeInferences(InferenceType.CLASS_HIERARCHY);
		for (OWLClass classe : ontologia.getClassesInSignature()) {
			for (OWLNamedIndividual ind : rea.getInstances(classe, false).getFlattened()) {
				System.out.println("Lista: \n" + ind);
				for (OWLDataProperty prop : ontologia.getDataPropertiesInSignature()) {
					System.out.println(prop);
					for (OWLLiteral value : rea.getDataPropertyValues(ind, prop)) {
						System.out.println(value);
					}
				}
			}
		}
	}

	/**
	 * Método que mostra a classe da instância depois de realizada uma inferência.
	 * 
	 * @param instancia
	 *            Nome da instância
	 */
	public String mostrarClasseDepoisInferencia(String instancia) {
		String retorno = "";
		reasoner.precomputeInferences();
		for (OWLClass classe : ontologia.getClassesInSignature()) {
			for (OWLNamedIndividual ind : reasoner.getInstances(classe, true).getFlattened()) {
//				System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
				if (ind.toString().indexOf("#" + instancia) != -1) {
					retorno += ind + " ----------- " + classe + "\n";
					System.out.println(ind + " ----------- " + classe);
				}
			}
		}
		return retorno;
	}

	/**
	 * Método que mostra as propriedades das instâncias.
	 * 
	 * @throws Exception
	 */
	public void mostrarPropriedadesInstancias() throws Exception {
		java.util.Iterator<OWLLogicalAxiom> iterator = ontologia.getLogicalAxioms().iterator();
		while (iterator.hasNext()) {
			OWLAxiom axioma = iterator.next();
			if (!axioma.getIndividualsInSignature().isEmpty()) {
				System.out.println("Instancia: " + axioma.getIndividualsInSignature());
				System.out.println(axioma.getDataPropertiesInSignature());
				System.out.println(axioma.getObjectPropertiesInSignature());
				System.out.println("Classe da instancia: " + axioma.getClassesInSignature());
			}
		}

	}

	/**
	 * Método que itera pela instância
	 * 
	 * @param instancia
	 *            Nome da instância.
	 * @return Resultado da iteração
	 */
	public String getResultadoPorInstancia(String instancia) {
		java.util.Iterator<OWLLogicalAxiom> iterator = ontologia.getLogicalAxioms().iterator();
		String classes = "";
		while (iterator.hasNext()) {
			OWLAxiom axioma = iterator.next();
			if (!axioma.getIndividualsInSignature().isEmpty() && !axioma.getClassesInSignature().isEmpty()) {
				System.out.println(axioma.getIndividualsInSignature().toString());
				if (axioma.getIndividualsInSignature().toString().indexOf(instancia) != -1) {
					for (OWLClass owlClass : axioma.getClassesInSignature()) {
						System.out.println("class " + axioma.getClassesInSignature());
						classes += owlClass.toString() + "\n";
					}
				}
			}
		}
		return classes;
	}

	/**
	 * Método que salva a ontologia num arquivo.
	 * 
	 * @throws IOException
	 * @throws OWLOntologyStorageException
	 * @throws OWLOntologyCreationException
	 */
	public void atualizarOntologia(String arquivo) throws IOException, OWLOntologyStorageException {
		File output = new File(arquivo);
		if (!output.exists())
			output.createNewFile();
		output = output.getAbsoluteFile();
		OutputStream stream = new FileOutputStream(output);
		gerenciador.saveOntology(ontologia, gerenciador.getOntologyFormat(ontologia), stream);
	}

	/**
	 * Método que mostra todas as classes da ontologia.
	 */
	public void mostrarClasses() {
		for (OWLClass classe : ontologia.getClassesInSignature()) {
			System.out.println(classe);
		}
	}

}
