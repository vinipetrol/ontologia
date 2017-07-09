package IA.ontologia;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.StreamDocumentTarget;
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

public class OntologyManager {
	private OWLOntologyManager manager;
	private IRI documentIRI;
	private OWLOntology localOntology;
	private OWLReasoner reasoner;

	public OntologyManager() {

	}

	public OntologyManager(String path) {

	}

	public void loadOntology(String path) throws OWLOntologyCreationException {
		/* Since we wont load our ontology from web, we have to load from a file */
		this.manager = OWLManager.createOWLOntologyManager();
		// TODO: find the current directory and make this path relative
		File file = new File(path);
		// loading the local ontology
		this.localOntology = manager.loadOntologyFromOntologyDocument(file);

		System.out.println("Loaded ontology: " + localOntology);
		// We can always obtain the location where an ontology was loaded from
		// this.documentIRI = manager.getOntologyDocumentIRI(localOntology);
		this.documentIRI = IRI.create("http://www.semanticweb.org/root/ontologies/2017/5/untitled-ontology-2");

		System.out.println("    from: " + documentIRI);
	}

	public void createPropertyAssertions(String property, String instance1, String instance2) throws Exception {
		// We can specify the properties of an individual using property
		OWLDataFactory factory = manager.getOWLDataFactory();

		OWLIndividual inst1 = factory.getOWLNamedIndividual(IRI.create(documentIRI + "#" + instance1));
		OWLIndividual inst2 = factory.getOWLNamedIndividual(IRI.create(documentIRI + "#" + instance2));
		OWLObjectProperty temConhecimento = factory.getOWLObjectProperty(IRI.create(documentIRI + "#" + property));

		OWLObjectPropertyAssertionAxiom propertyAssertion = factory.getOWLObjectPropertyAssertionAxiom(temConhecimento, inst1, inst2);
		// AddAxiom addAxiomChange = new AddAxiom(localOntology, propertyAssertion);
		manager.addAxiom(localOntology, propertyAssertion);

		/*
		 * isnt required to define a class that a instance belongs, the reasoner should
		 * do this
		 * 
		 * manager.saveOntology(localOntology, new StreamDocumentTarget( new
		 * ByteArrayOutputStream())); saveOntology();
		 */
	}

	public void createDataProperty(String property, String instance1, String value, OWL2Datatype dataType)
			throws OWLOntologyStorageException, IOException {
		OWLDataFactory factory = manager.getOWLDataFactory();
		OWLIndividual inst1 = factory.getOWLNamedIndividual(IRI.create(documentIRI + "#" + instance1));
		OWLDataProperty dataProperty = factory.getOWLDataProperty(IRI.create(documentIRI + "#" + property));

		// OWLDatatype stringValue =
		// factory.getOWLDatatype(OWL2Datatype.XSD_STRING.getIRI()); //trocar o
		// XSD_STRING POR ENUM
		OWLDatatype stringValue = factory.getOWLDatatype(dataType.getIRI()); // trocar o XSD_STRING POR ENUM
		OWLLiteral literalValue = factory.getOWLLiteral(value, stringValue);
		OWLDataPropertyAssertionAxiom dataPropertyAssertion = factory.getOWLDataPropertyAssertionAxiom(dataProperty, inst1, literalValue);
		manager.addAxiom(localOntology, dataPropertyAssertion);
		// saveOntology();

	}

	public void createReasoner() {
		ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor();
		OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
		OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
		this.reasoner = reasonerFactory.createNonBufferingReasoner(localOntology, config);
	}

	public void reason() {
		this.reasoner.precomputeInferences();
	}

	public List<String> getClassInstances(String className) {
		List<String> instances = new ArrayList<String>();
		ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor();
		OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
		OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
		OWLReasoner reasoner = reasonerFactory.createNonBufferingReasoner(localOntology, config);
		// Ask the reasoner to precompute some inferences
		reasoner.precomputeInferences();
		OWLDataFactory factory = manager.getOWLDataFactory();
		OWLClass owlClassName = factory.getOWLClass(IRI.create(documentIRI + "#" + className));

		NodeSet<OWLNamedIndividual> individualsNodeSet = reasoner.getInstances(owlClassName, false);
		Set<OWLNamedIndividual> individuals = individualsNodeSet.getFlattened();
		for (OWLNamedIndividual ind : individuals) {
			System.out.println("    " + ind);
			instances.add(ind.toString());
		}

		return instances;

	}

	public String getInstanceClass(String classN, String instanceName) {
		String className = "";

		OWLDataFactory factory = manager.getOWLDataFactory();
		OWLClass owlClassName = factory.getOWLClass(IRI.create(documentIRI + "#" + classN));

		NodeSet<OWLNamedIndividual> individualsNodeSet = reasoner.getInstances(owlClassName, false);
		Set<OWLNamedIndividual> individuals = individualsNodeSet.getFlattened();
		System.out.println(individuals.isEmpty());
		System.out.println("Instances of agressivo: ");
		for (OWLNamedIndividual ind : individuals) {
			if (ind.toString().indexOf(documentIRI + "#" + instanceName) != -1) {
				System.out.println("Class for instanceName " + instanceName + " has been found " + classN);
			}
			System.out.println("    " + ind);
		}
		System.out.println("\n");

		return className;
	}

	public void getClassInstances() {
		ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor();
		OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
		// Create a reasoner that will reason over our ontology and its imports
		// closure. Pass in the configuration.
		// not using it in tests, we don't need the output
		// OWLReasoner reasoner = reasonerFactory.createReasoner(o, config);
		// OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
		OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();

		OWLReasoner reasoner = reasonerFactory.createNonBufferingReasoner(localOntology, config);
		// Ask the reasoner to precompute some inferences
		reasoner.precomputeInferences();
		OWLDataFactory factory = manager.getOWLDataFactory();
		System.out.println("is consistent " + reasoner.isConsistent());

		OWLClass agressivo = factory
				.getOWLClass(IRI.create("http://www.semanticweb.org/root/ontologies/2017/5/untitled-ontology-2#agressivo"));

		NodeSet<OWLNamedIndividual> individualsNodeSet = reasoner.getInstances(agressivo, false);
		// The reasoner returns a NodeSet again. This time the NodeSet contains
		// individuals. Again, we just want the individuals, so get a flattened
		// set.

		Set<OWLNamedIndividual> individuals = individualsNodeSet.getFlattened();
		System.out.println(individuals.isEmpty());
		System.out.println("Instances of agressivo: ");
		for (OWLNamedIndividual ind : individuals) {
			System.out.println("    " + ind);
		}
		System.out.println("\n");
	}

	public void showInstancesDataProperty() throws Exception {

		// Create a console progress monitor. This will print the reasoner
		// progress out to the console.
		ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor();
		OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
		// Create a reasoner that will reason over our ontology and its imports
		// closure. Pass in the configuration.
		// not using it in tests, we don't need the output
		// OWLReasoner reasoner = reasonerFactory.createReasoner(o, config);
		OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
		OWLReasoner localreasoner = reasonerFactory.createReasoner(localOntology);
		// Ask the reasoner to precompute some inferences
		localreasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
		// for each class, look up the instances
		for (OWLClass c : localOntology.getClassesInSignature()) {

			// the boolean argument specifies direct subclasses; false would
			// specify all subclasses
			// a NodeSet represents a set of Nodes.
			// a Node represents a set of equivalent classes/or sameAs
			// individuals
			NodeSet<OWLNamedIndividual> instances = localreasoner.getInstances(c, false);
			for (OWLNamedIndividual i : instances.getFlattened()) {
				// look up all property assertions
				System.out.println(i);

				for (OWLDataProperty op : localOntology.getDataPropertiesInSignature()) {

					Set<OWLLiteral> valuesNodeSet = localreasoner.getDataPropertyValues(i, op);
					System.out.println(op);
					for (OWLLiteral value : valuesNodeSet) {
						System.out.println(value);
					}
				}
			}
		}
	}

	public void showClassAfterReasoning(String instance1) {
		reason();
		reasoner.precomputeInferences();
		for (OWLClass c : localOntology.getClassesInSignature()) {
			NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(c, true);
			for (OWLNamedIndividual node : instances.getFlattened()) {
				if (node.toString().indexOf(documentIRI + "#" + instance1) != -1) {
					System.out.println(node + "\t" + c);
				}
			}
		}
	}

	public void showInstancesProperties() throws Exception {
		Set<OWLLogicalAxiom> axiomSet = localOntology.getLogicalAxioms();
		java.util.Iterator<OWLLogicalAxiom> iteratorAxiom = axiomSet.iterator();

		while (iteratorAxiom.hasNext()) {
			OWLAxiom tempAx = iteratorAxiom.next();
			if (!tempAx.getIndividualsInSignature().isEmpty()) {
				System.out.println("Instance " + tempAx.getIndividualsInSignature());
				System.out.println(tempAx.getDataPropertiesInSignature());
				System.out.println(tempAx.getObjectPropertiesInSignature());
				System.out.println("CLASS " + tempAx.getClassesInSignature());
			}
		}

	}

	/*
	 * this method will return the results of a instance such as: Properties and
	 * class
	 */
	public String getInstanceResult(String instance) {
		String classes = "";

		Set<OWLLogicalAxiom> axiomSet = localOntology.getLogicalAxioms();
		java.util.Iterator<OWLLogicalAxiom> iteratorAxiom = axiomSet.iterator();
		System.out.println(documentIRI.toString() + "#" + instance);
		while (iteratorAxiom.hasNext()) {
			OWLAxiom tempAx = iteratorAxiom.next();
			if (!tempAx.getIndividualsInSignature().isEmpty() && !tempAx.getClassesInSignature().isEmpty()) {
				System.out.println(tempAx.getIndividualsInSignature().toString());
				System.out.println(tempAx.getIndividualsInSignature().toString().indexOf(instance));
				if (tempAx.getIndividualsInSignature().toString().indexOf(instance) != -1) {
					for (OWLClass owlClass : tempAx.getClassesInSignature()) {
						System.out.println("class " + tempAx.getClassesInSignature());
						classes += owlClass.toString() + "\n";
					}

				}

			}
		}
		return classes;

	}

	public void saveOntology() throws IOException, OWLOntologyStorageException {
		// File output = File.createTempFile("new_ontology", "owl");
		File output = new File("new_ontology.owl");
		IRI documentIRI2 = IRI.create(output);
		// save in OWL/XML format
		manager.saveOntology(localOntology, new OWLXMLOntologyFormat(), documentIRI2);
		// save in RDF/XML
		manager.saveOntology(localOntology, documentIRI2);
		// print out the ontology
		StreamDocumentTarget target = new StreamDocumentTarget(new ByteArrayOutputStream());
		manager.saveOntology(localOntology, target);
		// Remove the ontology from the manager
		manager.removeOntology(localOntology);
	}

	public void showClasses() {
		for (OWLClass owlClass : localOntology.getClassesInSignature()) {
			System.out.println(owlClass);
		}
	}
}