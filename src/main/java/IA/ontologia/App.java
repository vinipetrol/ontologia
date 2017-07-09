package IA.ontologia;

import org.semanticweb.owlapi.vocab.OWL2Datatype;

public class App {

	public static void main(String[] args) {
		// new Tela(new App()).setVisible(true);
		pensar();
	}

	public static void pensar() {
		Ontologia ontologia = new Ontologia();
		try {
			ontologia.criarOntologia("/home/vinicius/Desktop/ia/ontologia/src/main/java/IA/ontologia/outra.owl");
			ontologia.criarInferenciador();
			// ontologia.inferenciar();
			ontologia.criarPropriedadeDeInformacao("valorRisco", "investidor", "pequeno", OWL2Datatype.RDF_PLAIN_LITERAL);
			// ontologia.mostrarPropriedadesDasInstancias();
//			ontologia.atualizarOntologia();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void callback(Investidor investidor) {
		// App.rodarOntologia(investidor);
		System.out.println(investidor.retornaNome());
		System.out.println(investidor.retornaRentaFamiliar());
		System.out.println(investidor.retornaConhecimento());
		System.out.println(investidor.retornaInvestimentoInicial());
		System.out.println(investidor.retornaSonho());
		System.out.println(investidor.retornaRisco());
		System.out.println(investidor.retornaPrazo());
		Ontologia ontologia = new Ontologia();
		// OntologyManager om = new OntologyManager();

		try {
			// om.loadOntology("/home/vinicius/Desktop/ia/ontologia/src/main/java/IA/ontologia/Ontologia.owl");
			// om.createReasoner();
			// om.createPropertyAssertions("tem_conhecimento", "i1",
			// investidor.retornaConhecimento());
			//// om.createDataProperty("tolerancia_risco", "i1", _tolerancia,
			// OWL2Datatype.XSD_STRING);
			// om.showInstancesDataProperty();
			// om.saveOntology();
			ontologia.criarOntologia("/home/vinicius/Desktop/ia/ontologia/src/main/java/IA/ontologia/Ontologia.owl");
			ontologia.criarInferenciador();

			ontologia.criarPropriedadeDeInformacao("valorPrazo", "investidor", investidor.retornaPrazo(), OWL2Datatype.RDF_PLAIN_LITERAL);
			// ontologia.criarPropriedadeDeInformacao("Conhecimento",
			// investidor.retornaNome(), investidor.retornaConhecimento(),
			// OWL2Datatype.RDF_PLAIN_LITERAL);
			// ontologia.criarPropriedadeDeInformacao("Investimento_Inicial",
			// investidor.retornaNome(), investidor.retornaInvestimentoInicial(),
			// OWL2Datatype.RDF_PLAIN_LITERAL);
			// ontologia.criarPropriedadeDeInformacao("valorRentabilidade", "Rodrigo",
			// "pequeno", OWL2Datatype.RDF_PLAIN_LITERAL);
			// ontologia.criarPropriedadeDeInformacao("valorPrazo", "Andre", "medio",
			// OWL2Datatype.RDF_PLAIN_LITERAL);
			// ontologia.criarPropriedadeDeInformacao("valorInvestimentoInicial", "Andre",
			// "medio", OWL2Datatype.RDF_PLAIN_LITERAL);
			// System.out.println("qqqqqqqqqqqq");
			// ontologia.atualizarOntologia();
			// ontologia.inferenciar();
			// ontologia.mostrarPropriedadesDasInstancias();
			// ontologia.mostrarClasseDepoisInferencia("Karol");
			// ontologia.mostrarPropriedadesDasInstancias();

			// ontologia.createDataProperty("tem_muito_dinheiro", "i2", "10000",
			// OWL2Datatype.XSD_DOUBLE);
			// manager.showInstancesDataProperty();

			// manager.getClassInstances();
			// manager.showClassAfterReasoning("i1");
			// System.out.println(manager.getInstanceClass("agressivo", "i1"));

			// manager.showClasses();
			// manager.createPropertyAssertions();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
