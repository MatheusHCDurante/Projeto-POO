package simulation;
public class Terreno extends Financiamento {
    /** Tipo de zoneamento. */
    private String tipoDeZona;
    /** Quantidade meses por ano. */
    private static final int MESES_POR_ANO = 12;
    /** Valor de acrecimo no valor do financiamento. */
    private static final double ACRESCIMO_TERRENO = 1.02;

    /**
     * Adiciona ao contrutor os atributos da classe Financiamento.
     * Junto aos atributos únicos da classe Terreno.
     *
     * @param valorImovel Retorna valor do imóvel.
     * @param prazoFinanciamento Retorna prazo de financiamento.
     * @param taxaJurosAnual Retorna porcentagem de juros anual.
     * @param tipoDeZona Retorna tipo de zoneamento.
     */
    public Terreno(
        final double valorImovel,
        final int prazoFinanciamento,
        final double taxaJurosAnual,
        final String tipoDeZona) {
            super(valorImovel, prazoFinanciamento, taxaJurosAnual);
            this.tipoDeZona = tipoDeZona;
        }

    /**
     * Permite que outras partes do código visualize o tipo de terreno.
     *
     * @return Retorna tipo de zoneamento do terreno.
     */
    public String getTipoDeZona() {
        return tipoDeZona;
    }

    /**
     * Calcula o valor do pagamento mensal de acordo com a classe.
     * Nesse caso casa, metodo de calculo único da classe.
     *
     * @return Retorna valor de pagamento mensal.
     */
    public double calcularPagamentoMensal() {
        return (getValorImovel() / (getPrazoFinanciamento() * MESES_POR_ANO))
        * (1 + (this.getTaxaJurosAnual() / MESES_POR_ANO)) * ACRESCIMO_TERRENO;
    }

    /**
     * Sobrescreve o método abstrato imprimirDados.
     * Adicionando os atributos únicos da classe Terreno.
     */
    @Override
    public void imprimirDados() {
        System.out.println("----------Financiamento de Terrenos----------");
        System.out.printf("Zona %s\n", getTipoDeZona());
        System.out.printf("Valor do Imovél: R$%.2f"
        + "\nPrazo: %d anos\nJuros Anual: %.2f por cento\n",
        getValorImovel(), getPrazoFinanciamento(), getTaxaJurosAnual());
        System.out.printf("Valor Mensal: R$%.2f"
        + "\nValor total do financiamento: R$%.2f\n",
         calcularPagamentoMensal(), calcularTotalPagamento());
    }

    /**
     * Converte os atributos de Terreno para string.
     *
     * @return Retorna mensagem com informações do financiamento.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Financiamento de Terreno\n");
        sb.append(String.format("Valor da terreno: R$ %.2f",
        getValorImovel())).append("\n");
        sb.append("Prazo de financiamento: ")
        .append(getPrazoFinanciamento()).append(" anos.\n");
        sb.append("Taxa de Juros Anual: ")
        .append(getTaxaJurosAnual()).append("%.\n");
        sb.append("Tipo de zoneamento: ")
        .append(tipoDeZona).append("\n");
        sb.append(String.format("Valor mensal: R$ %.2f",
            calcularPagamentoMensal())).append("\n");
        sb.append(String.format("Valor total do financiamento: R$ %.2f",
            calcularTotalPagamento())).append("\n\n");
        return sb.toString();
    }
}
