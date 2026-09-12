package simulation;

public class Apartamento extends Financiamento {
    /** Número de vagas na garagem. */
    private int numeroDeVagasGaragem;
    /** Andar do apartamento. */
    private int numeroDoAndar;

    /**
     * Adiciona ao contrutor os atributos da classe Financiamento.
     * Junto aos atributos únicos da classe Apartamento.
     *
     * @param valorDoImovel Valor do imóvel.
     * @param prazoDoFinanciamento Prazo de financiamento.
     * @param taxaDeJurosAnual Porcentagem de juros anual.
     * @param numeroDeVagas Número de vagas na garagem.
     * @param andar Andar do apartamento.
     */
    public Apartamento(
        final double valorDoImovel,
        final int prazoDoFinanciamento,
        final double taxaDeJurosAnual,
        final int numeroDeVagas,
        final int andar) {

        super(
            valorDoImovel,
            prazoDoFinanciamento,
            taxaDeJurosAnual);

        this.numeroDeVagasGaragem = numeroDeVagas;
        this.numeroDoAndar = andar;
        }

    /**
     * Permite que outras partes do código visualize.
     * Quantidade de vagas na garagem.
     *
     * @return Retorna número de vagas na garagem.
     */
    public int getNumeroDeVagasGaragem() {
        return numeroDeVagasGaragem;
    }

    /**
     * Permite que outras partes do código visualize andar do apartamento.
     *
     * @return Retorna número do andar.
     */
    public int getNumeroDoAndar() {
        return numeroDoAndar;
    }

    /**
     * Método da classe Apartamentos, para calcular a taxa de juros mensal.
     *
     * @return Porcentagem de juros mensal.
     */
    public double taxaDeJurosMensal() {
        return getTaxaJurosAnual() / 12;
    }

    /**
     * Método da classe Apartamentos, para calcular o prazo anual em meses.
     *
     * @return Retorna quantidade de meses de financiamento.
     */
    public double prazoMensal() {
        return getPrazoFinanciamento() * 12;
    }

    /**
     * Calcula o valor do pagamento mensal de acordo com a classe.
     * Nesse caso casa, metodo de calculo único da classe.
     *
     * @return Retorna valor pagamento mensal.
     */
    public double calcularPagamentoMensal() {
        return getValorImovel() * taxaDeJurosMensal()
        * Math.pow((1 + taxaDeJurosMensal()), prazoMensal())
        / Math.pow((1 + taxaDeJurosMensal()), prazoMensal()) - 1;
    }
    /**
     * Sobrescreve o método abstrato imprimirDados.
     * Adicionando os atributos únicos da classe Apartamento.
     */
    @Override
    public void imprimirDados() {
        System.out.println("----------Financiamento Apartamento----------");
        System.out.printf("Andar Nº %d \nVagas de garagem: %d \n",
        getNumeroDoAndar(), getNumeroDeVagasGaragem());
        System.out.printf("Valor do Imovél: R$%.2f\nPrazo: %d anos"
        + "\nJuros Anual: %.2f por cento\n",
        getValorImovel(), getPrazoFinanciamento(), getTaxaJurosAnual());
        System.out.printf("Valor Mensal: R$%.2f"
        + "\nValor total do financiamento: R$%.2f\n",
         calcularPagamentoMensal(), calcularTotalPagamento());
    }

    /**
     * Converte os atributos de Apartamento para string.
     *
     * @return Retorna mensagem com informações do financiamento.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Financiamento de Apartamento\n");
        sb.append("Valor do apartamento: R$ ")
        .append(getValorImovel()).append("\n");
        sb.append("Prazo de financiamento: ")
        .append(getPrazoFinanciamento()).append(" anos.\n");
        sb.append("Taxa de Juros Anual: ")
        .append(getTaxaJurosAnual()).append("%.\n");
        sb.append("Quantidade de vagas na garagem: ")
        .append(numeroDeVagasGaragem).append("\n");
        sb.append("Número do andar: ")
        .append(numeroDoAndar).append("\n");
        sb.append("Valor mensal: R$ ")
        .append(calcularPagamentoMensal()).append("\n");
        sb.append("Valor total do financiamento: R$ ")
        .append(calcularTotalPagamento()).append("\n\n");
        return sb.toString();
    }
}
