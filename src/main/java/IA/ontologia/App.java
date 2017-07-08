package IA.ontologia;

public class App {

	public static void main(String[] args) {

		Ontologia ontologia = new Ontologia();
		try {
			ontologia.criarOntologia("/home/vinicius/Desktop/ia/ontologia/src/main/java/IA/ontologia/Novooo.owl");
			ontologia.criarInferenciador();

			// ontologia.criarPropriedadeDeInformacao("valorRisco", "Rodrigo", "pequeno",
			// OWL2Datatype.RDF_PLAIN_LITERAL);
			// ontologia.criarPropriedadeDeInformacao("valorRentabilidade", "Rodrigo",
			// "pequeno", OWL2Datatype.RDF_PLAIN_LITERAL);
			// ontologia.criarPropriedadeDeInformacao("valorPrazo", "Andre", "medio",
			// OWL2Datatype.RDF_PLAIN_LITERAL);
			// ontologia.criarPropriedadeDeInformacao("valorInvestimentoInicial", "Andre",
			// "medio", OWL2Datatype.RDF_PLAIN_LITERAL);
			// System.out.println("qqqqqqqqqqqq");
			// ontologia.atualizarOntologia();
			ontologia.inferenciar();
			// ontologia.mostrarPropriedadesDasInstancias();
			// ontologia.mostrarClasseDepoisInferencia("Karol");
			ontologia.mostrarPropriedadesInstancias();

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
