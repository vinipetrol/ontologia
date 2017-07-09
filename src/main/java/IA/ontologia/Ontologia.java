package IA.ontologia;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.StreamDocumentTarget;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
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
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

public class Ontologia {

	public Ontologia() {
	}

	private OWLOntologyManager _gerenciador;
	private IRI _endereco;
	private OWLOntology _ontologia;
	private OWLReasoner _inferenciar;
	private OWLDataFactory _fabrica;

	/**
	 * Método responsável por criar uma instancia da ontologia carregado de um
	 * arquivo.
	 * 
	 * @param arquivo
	 * @throws OWLOntologyCreationException
	 */
	public void criarOntologia(String arquivo) throws OWLOntologyCreationException {
		_gerenciador = OWLManager.createOWLOntologyManager();
		File arq = new File(arquivo);
		_ontologia = _gerenciador.loadOntologyFromOntologyDocument(arq);
		_endereco = IRI.create("http://www.semanticweb.org/root/ontologies/2017/5/untitled-ontology-2");
		_fabrica = _gerenciador.getOWLDataFactory();
	}

	/**
	 * Método que cria o inferenciador(reasoner) da aplicação.
	 */
	public void criarInferenciador() {
		if (_inferenciar == null) {
			ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor();
			OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
			OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
			_inferenciar = reasonerFactory.createNonBufferingReasoner(_ontologia, config);
		}
	}

	/**
	 * Método para inferênciar sobre a ontologia.
	 */
	public void inferenciar() {
		System.out.println(_inferenciar.isConsistent());
		_inferenciar.precomputeInferences();
	}

	/**
	 * Método que retorna as instancias de uma classe dado o nome da mesma.
	 * 
	 * @param nomeClasse
	 *            Nome da classe
	 * @return Lista de nome dos indivíduos da classe
	 */
	public List<String> getInstanciaPeloNomeClasse(String nomeClasse) {
		criarInferenciador();
		inferenciar();
		OWLClass owlClasse = _fabrica.getOWLClass(IRI.create(_endereco + "#" + nomeClasse));
		NodeSet<OWLNamedIndividual> conjuntoIndividuos = _inferenciar.getInstances(owlClasse, false);
		Set<OWLNamedIndividual> individuos = conjuntoIndividuos.getFlattened();
		List<String> instancias = new ArrayList<String>();
		for (OWLNamedIndividual ind : individuos) {
			instancias.add(ind.toString());
		}
		return instancias;

	}

	/**
	 * Método que retorna o individuo dado nome da classe e da instância.
	 * 
	 * @param nomeClasse
	 *            Nome da classe
	 * @param nomeInstancia
	 *            Nome da instância
	 * @return Indivíduo encontrado
	 */
	public OWLNamedIndividual getIndividuoPorNomeClasseEInstancia(String nomeClasse, String nomeInstancia) {
		OWLClass owlClasse = _fabrica.getOWLClass(IRI.create(_endereco + "#" + nomeClasse));
		NodeSet<OWLNamedIndividual> conjuntoIndividuos = _inferenciar.getInstances(owlClasse, false);
		Set<OWLNamedIndividual> individuos = conjuntoIndividuos.getFlattened();
		for (OWLNamedIndividual ind : individuos) {
			if (ind.toString().indexOf(_endereco + "#" + nomeInstancia) != -1) {
				return ind;
			}
		}
		return null;
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
		OWLIndividual ind1 = _fabrica.getOWLNamedIndividual(IRI.create(_endereco + "#" + instancia1));
		OWLIndividual ind2 = _fabrica.getOWLNamedIndividual(IRI.create(_endereco + "#" + instancia2));
		OWLObjectProperty objPropriedade = _fabrica.getOWLObjectProperty(IRI.create(_endereco + "#" + propriedade));
		OWLObjectPropertyAssertionAxiom propAxioma = _fabrica.getOWLObjectPropertyAssertionAxiom(objPropriedade, ind1, ind2);
		AddAxiom novoAxioma = new AddAxiom(_ontologia, propAxioma);
		_gerenciador.applyChange(novoAxioma);
		_gerenciador.saveOntology(_ontologia, new StreamDocumentTarget(new ByteArrayOutputStream()));
		atualizarOntologia();
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
			throws OWLOntologyStorageException, IOException, OWLOntologyCreationException {
		OWLIndividual ind1 = _fabrica.getOWLNamedIndividual(IRI.create("#" + instancia));
		OWLDataProperty propDado = _fabrica.getOWLDataProperty(IRI.create("#" + propriedade));
		OWLDatatype valorIRI = _fabrica.getOWLDatatype(tipo.getIRI());
		OWLLiteral literal = _fabrica.getOWLLiteral(valor, valorIRI);
		OWLDataPropertyAssertionAxiom assersaoPropNovo = _fabrica.getOWLDataPropertyAssertionAxiom(propDado, ind1, literal);
		_gerenciador.addAxiom(_ontologia, assersaoPropNovo);
		System.out.println(
				"Do margherita pizzas have a topping that is morzarella or goats cheese? " + _inferenciar.isEntailed(assersaoPropNovo));
		atualizarOntologia();
	}

	/**
	 * Método que salva a ontologia num arquivo.
	 * 
	 * @throws IOException
	 * @throws OWLOntologyStorageException
	 * @throws OWLOntologyCreationException
	 */
	public void atualizarOntologia() throws IOException, OWLOntologyStorageException, OWLOntologyCreationException {
		File output = new File("/home/vinicius/Desktop/ia/ontologia/src/main/java/IA/ontologia/outra" + 1 + ".owl");
		if (!output.exists())
			output.createNewFile();
		output = output.getAbsoluteFile();
		OutputStream stream = new FileOutputStream(output);
		_gerenciador.saveOntology(_ontologia, _gerenciador.getOntologyFormat(_ontologia), stream);
	}

	/**
	 * Método que mostra as propriedades das instâncias.
	 * 
	 * @throws Exception
	 */
	public void mostrarPropriedadesDasInstancias() throws Exception {
		_inferenciar.precomputeInferences(InferenceType.CLASS_HIERARCHY);
		for (OWLClass c : _ontologia.getClassesInSignature()) {
			NodeSet<OWLNamedIndividual> instancias = _inferenciar.getInstances(c, false);
			for (OWLNamedIndividual inst : instancias.getFlattened()) {
				System.out.println(inst);
				for (OWLDataProperty prop : _ontologia.getDataPropertiesInSignature()) {
					Set<OWLLiteral> literais = _inferenciar.getDataPropertyValues(inst, prop);
					for (OWLLiteral lit : literais) {
						System.out.println(lit);
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
	public void mostrarClasseDepoisInferencia(String instancia) {
		_inferenciar.precomputeInferences();
		for (OWLClass classe : _ontologia.getClassesInSignature()) {
			NodeSet<OWLNamedIndividual> instancias = _inferenciar.getInstances(classe, true);
			for (OWLNamedIndividual individuo : instancias.getFlattened()) {
				if (individuo.toString().indexOf(_endereco + "#" + instancia) != -1) {
					System.out.println(individuo + "\t" + classe);
				}
			}
		}
	}

	/**
	 * Método que mostra as propriedades das instâncias.
	 * 
	 * @throws Exception
	 */
	public void mostrarPropriedadesInstancias() throws Exception {
		Set<OWLLogicalAxiom> axiomas = _ontologia.getLogicalAxioms();
		java.util.Iterator<OWLLogicalAxiom> iterator = axiomas.iterator();
		while (iterator.hasNext()) {
			OWLAxiom axioma = iterator.next();
			if (!axioma.getIndividualsInSignature().isEmpty()) {
				System.out.println("instancia: " + axioma.getIndividualsInSignature());
				System.out.println(axioma.getDataPropertiesInSignature());
				System.out.println(axioma.getObjectPropertiesInSignature());
				System.out.println("classe: " + axioma.getClassesInSignature());
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
		String classes = "";
		Set<OWLLogicalAxiom> axioma = _ontologia.getLogicalAxioms();
		java.util.Iterator<OWLLogicalAxiom> iterator = axioma.iterator();
		while (iterator.hasNext()) {
			OWLAxiom oxioma = iterator.next();
			if (!oxioma.getIndividualsInSignature().isEmpty() && !oxioma.getClassesInSignature().isEmpty()) {
				if (oxioma.getIndividualsInSignature().toString().indexOf(instancia) != -1) { // achou a classe certa
					for (OWLClass owlClasse : oxioma.getClassesInSignature()) {
						classes += owlClasse.toString() + "\n";
					}
				}
			}
		}
		return classes;
	}

	/**
	 * Método que mostra todas as classes da ontologia.
	 */
	public void mostrarClasses() {
		for (OWLClass owlClasse : _ontologia.getClassesInSignature()) {
			System.out.println(owlClasse);
		}
	}

}
