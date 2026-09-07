package simulation;
public class Terreno extends Financiamento {
    private String tipoDeZona;

    // Adiciona ao contrutor os atributos da classe Financiamento junto aos atributos únicos da classe Terreno. 
    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, String tipoDeZona) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tipoDeZona = tipoDeZona;
    }

    // permite que outras partes do código visualize o tipo de terreno(atributo que atualmente é privado/private).
    public String getTipoDeZona() {
        return tipoDeZona;
    }

    // Calcula o valor do pagamento mensal de acordo com a classe, nesse caso casa, metodo de calculo único da classe.
    public double calcularPagamentoMensal() {
        return (this.valorImovel / (this.prazoFinanciamentoEmAnos * 12))*(1 +(this.taxaJurosAnual / 12)) * 1.02;
    }

    // Sobrescreve o método abstrato imprimirDados, adicionando os atributos únicos da classe Terreno.
    @Override
    public void imprimirDados() {
        System.out.println("----------Financiamento de Terrenos----------");
        System.out.printf("Zona %s\n", getTipoDeZona());
        System.out.printf("Valor do Imovél: R$%.2f\nPrazo: %d anos\nJuros Anual: %.2f por cento\n", getValorImovel(), getPrazoFinanciamento(), getTaxaJurosAnual());
        System.out.printf("Valor Mensal: R$%.2f\nValor total do financiamento: R$%.2f\n", calcularPagamentoMensal(), calcularTotalPagamento());
    }

    // Converte os atributos de Terreno para string
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Financiamento de Terreno\n");
        sb.append("Valor da terreno: R$ ").append(valorImovel).append("\n");
        sb.append("Prazo de financiamento: ").append(prazoFinanciamentoEmAnos).append(" anos.\n");
        sb.append("Taxa de Juros Anual: ").append(taxaJurosAnual).append("%.\n");
        sb.append("Tipo de zoneamento: ").append(tipoDeZona).append("\n");
        sb.append("Valor mensal: R$ ").append(calcularPagamentoMensal()).append("\n");
        sb.append("Valor total do financiamento: R$ ").append(calcularTotalPagamento()).append("\n\n");
        return sb.toString();
    }
}