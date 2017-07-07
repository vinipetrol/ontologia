package IA.ontologia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.semanticweb.HermiT.Configuration;
import org.semanticweb.HermiT.Configuration.TableauMonitorType;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.HermiT.Reasoner.ReasonerFactory;
import org.semanticweb.HermiT.monitor.CountingMonitor;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.ConsoleProgressMonitor;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.util.InferredAxiomGenerator;
import org.semanticweb.owlapi.util.InferredClassAssertionAxiomGenerator;
import org.semanticweb.owlapi.util.InferredDisjointClassesAxiomGenerator;
import org.semanticweb.owlapi.util.InferredOntologyGenerator;
import org.semanticweb.owlapi.util.InferredSubClassAxiomGenerator;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String... args) throws Exception{
		inferir();
	}
	
	public static void teste() throws Exception {
		// First, we create an OWLOntologyManager object. The manager will load and
		// save ontologies.
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		// Now, we create the file from which the ontology will be loaded.
		// Here the ontology is stored in a file locally in the ontologies subfolder
		// of the examples folder.
		File inputOntologyFile = new File(
				"/home/vinicius/eclipse-workspace/ontologia/src/main/java/IA/ontologia/Novooo.owl");
		// We use the OWL API to load the ontology.
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(inputOntologyFile);
		// Now we create a configuration object that we use to overwrite HermiT's
		// default
		// settings.
		Configuration config = new Configuration();
		// Lets make HermiT show information about the tableau for each reasoning task
		// at the
		// start and end of a task and in certain time intervals.
		config.tableauMonitorType = TableauMonitorType.TIMING;
		// Now we can start and create the reasoner with the above created
		// configuration.
		Reasoner hermit = new Reasoner(config, ontology);
		// Lets see whether HermiT finds that the icecream class in the pizza ontology
		// is unsatisfiable
		// (I know it is).
		// First, create an instance of the OWLClass object for the icecream class.
		IRI icecreamIRI = IRI
				.create("/home/vinicius/eclipse-workspace/ontologia/src/main/java/IA/ontologia/Novooo.owl#Investidor");
		OWLClass owlClass = manager.getOWLDataFactory().getOWLClass(icecreamIRI);
		// Since we have used the timing monitor HermiT will print some information
		Set<OWLIndividual> individuos = owlClass.getIndividuals(ontology);

		for (OWLIndividual i : individuos) {
			System.out.println(i.toString());
		}

		config = new Configuration();
		CountingMonitor countingMonitor = new CountingMonitor();
		config.monitor = countingMonitor;
		// Now we can start and create the reasoner with the above created
		// configuration.
		hermit = new Reasoner(config, ontology);
		// Let's classify the ontology and see which were the 2 hardest satisfiability
		// and subsumption tests that HermiT performed during the classification.
		hermit.precomputeInferences(InferenceType.CLASS_HIERARCHY);

		// for (TestRecord record : countingMonitor.getTimeSortedTestRecords(2,
		// StandardTestType.CONCEPT_SATISFIABILITY))
		// System.out.println(record.toString());
		// System.out.println("The 2 hardest subsumption tests were:");
		// for (TestRecord record : countingMonitor.getTimeSortedTestRecords(2,
		// StandardTestType.CONCEPT_SUBSUMPTION))
		// System.out.println(record.toString());
	}

	public static void inferir() throws Exception {
		// First, we create an OWLOntologyManager object. The manager will load and
		// save ontologies.
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		// Now, we create the file from which the ontology will be loaded.
		// Here the ontology is stored in a file locally in the ontologies subfolder
		// of the examples folder.
		File inputOntologyFile = new File("/home/vinicius/eclipse-workspace/ontologia/src/main/java/IA/ontologia/Novooo.owl");
		// We use the OWL API to load the ontology.
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(inputOntologyFile);
		// Now we can start and create the reasoner. Since materialisation of axioms is
		// controlled
		// by OWL API classes and is not natively supported by HermiT, we need to
		// instantiate HermiT
		// as an OWLReasoner. This is done via a ReasonerFactory object.
		ReasonerFactory factory = new ReasonerFactory();
		// The factory can now be used to obtain an instance of HermiT as an
		// OWLReasoner.
		Configuration c = new Configuration();
		c.reasonerProgressMonitor = new ConsoleProgressMonitor();
		OWLReasoner reasoner = factory.createReasoner(ontology, c);
		// The following call causes HermiT to compute the class, object,
		// and data property hierarchies as well as the class instances.
		// Hermit does not yet support precomputation of property instances.
		// reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY,
		// InferenceType.CLASS_ASSERTIONS, InferenceType.OBJECT_PROPERTY_HIERARCHY,
		// InferenceType.DATA_PROPERTY_HIERARCHY,
		// InferenceType.OBJECT_PROPERTY_ASSERTIONS);
		// We now have to decide which kinds of inferences we want to compute. For
		// different types
		// there are different InferredAxiomGenerator implementations available in the
		// OWL API and
		// we use the InferredSubClassAxiomGenerator and the
		// InferredClassAssertionAxiomGenerator
		// here. The different generators are added to a list that is then passed to an
		// InferredOntologyGenerator.
		List<InferredAxiomGenerator<? extends OWLAxiom>> generators = new ArrayList<InferredAxiomGenerator<? extends OWLAxiom>>();
		generators.add(new InferredSubClassAxiomGenerator());
		generators.add(new InferredClassAssertionAxiomGenerator());
		// We dynamically overwrite the default disjoint classes generator since it
		// tries to
		// encode the reasoning problem itself instead of using the appropriate methods
		// in the
		// reasoner. That bypasses all our optimisations and means there is not progress
		// report :-(
		// We don't want that!
		generators.add(new InferredDisjointClassesAxiomGenerator() {
			boolean precomputed = false;

			protected void addAxioms(OWLClass entity, OWLReasoner reasoner, OWLDataFactory dataFactory,
					Set<OWLDisjointClassesAxiom> result) {
				if (!precomputed) {
					reasoner.precomputeInferences(InferenceType.DISJOINT_CLASSES);
					precomputed = true;
				}
				for (OWLClass cls : reasoner.getDisjointClasses(entity).getFlattened()) {
					result.add(dataFactory.getOWLDisjointClassesAxiom(entity, cls));
				}
			}
		});
		// We can now create an instance of InferredOntologyGenerator.
		InferredOntologyGenerator iog = new InferredOntologyGenerator(reasoner, generators);
		// Before we actually generate the axioms into an ontology, we first have to
		// create that ontology.
		// The manager creates the for now empty ontology for the inferred axioms for
		// us.
		OWLOntology inferredAxiomsOntology = manager.createOntology();
		// Now we use the inferred ontology generator to fill the ontology. That might
		// take some
		// time since it involves possibly a lot of calls to the reasoner.
		iog.fillOntology(manager, inferredAxiomsOntology);
		// Now the axioms are computed and added to the ontology, but we still have to
		// save
		// the ontology into a file. Since we cannot write to relative files, we have to
		// resolve the
		// relative path to an absolute one in an OS independent form. We do this by
		// (virtually) creating a
		// file with a relative path from which we get the absolute file.
		File inferredOntologyFile = new File("/home/vinicius/eclipse-workspace/ontologia/src/main/java/IA/ontologia/inferido.owl");
		if (!inferredOntologyFile.exists())
			inferredOntologyFile.createNewFile();
		inferredOntologyFile = inferredOntologyFile.getAbsoluteFile();
		// Now we create a stream since the ontology manager can then write to that
		// stream.
		OutputStream outputStream = new FileOutputStream(inferredOntologyFile);
		// We use the same format as for the input ontology.
		manager.saveOntology(inferredAxiomsOntology, manager.getOntologyFormat(ontology), outputStream);
		// Now that ontology that contains the inferred axioms should be in the
		// ontologies subfolder
		// (you Java IDE, e.g., Eclipse, might have to refresh its view of files in the
		// file system)
		// before the file is visible.
		System.out.println(
				"The ontology in examples/ontologies/pizza-inferred.owl should now contain all inferred axioms (you might need to refresh the IDE file view). ");
	}
}
