package IA.ontologia;

public class Investidor {

	String nome, conhecimento, sonho, risco;
	int rendaFamiliar, investimentoInicial, prazo;

	Investidor(String nome, int rendaFamiliar, String conhecimento, int investimentoInicial, String sonho, String risco, int prazo) {
		this.nome = nome;
		this.conhecimento = conhecimento;
		this.sonho = sonho;
		this.risco = risco;
		this.rendaFamiliar = rendaFamiliar;
		this.investimentoInicial = investimentoInicial;
		this.prazo = prazo;
	}

	public String retornaNome() {
		return nome;
	}

	public String retornaConhecimento() {
		if (conhecimento == "Ações")
			return "acoes";
		if (conhecimento == "Certificado de Depósito Bancário")
			return "certificado_deposito_bancario";
		if (conhecimento == "Letras de Crédito")
			return "letra_de_credito";
		if (conhecimento == "Títulos Públicos")
			return "titulos_publicos";
		if (conhecimento == "Sem Conhecimento")
			return "sem_conhecimento";
		return "sem_conhecimento";

	}

	public String retornaSonho() {
		if (sonho.equals("Reforma de Imóveis")) {
			return "pequeno";
		} else if (sonho.equals("Compra de Imóvel")) {
			return "grande";
		}
		return "medio";
	}

	public String retornaRisco() {
		if (risco.equals("Sim")) {
			return "grande";
		} else if (risco.equals("Preferivelmente Não")) {
			return "pequeno";
		}
		return "medio";
	}

	public String retornaRentaFamiliar() {
		if (rendaFamiliar <= 2000) {
			return "pequeno";
		} else if (rendaFamiliar > 2000 && rendaFamiliar < 10000) {
			return "medio";
		}
		return "grande";
	}

	public String retornaInvestimentoInicial() {
		if (investimentoInicial <= 10000) {
			return "pequeno";
		} else if (investimentoInicial > 10000 && investimentoInicial < 30000) {
			return "medio";
		}
		return "grande";
	}

	public String retornaPrazo() {
		if (prazo <= 6) {
			return "pequeno";
		} else if (prazo > 6 && prazo <= 24) {
			return "medio";
		}
		return "grande";
	}
}
