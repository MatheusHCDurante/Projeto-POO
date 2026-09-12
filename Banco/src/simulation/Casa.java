package simulation;

import util.AumentoMaiorDoQueJurosException;

public class Casa extends Financiamento {
    /** Tamanho da área contruida no terreno. */
    private double tamanhoAreaConstruida;
    /** Tamanho do terreno. */
    private double tamanhoDoTerreno;
    /** Quantidade meses por ano. */
    private static final int MESES_POR_ANO = 12;
    /** Valor de acrecimo no valor do financiamento. */
    private static final double ACRESCIMO_CASA = 80;

    /**
     * Adiciona ao contrutor os atributos da classe Financiamento.
     * Junto aos atributos únicos da classe Casa.
     *
     * @param valorImovel Valor do imóvel.
     * @param prazoFinanciamento Prazo do financiamento.
     * @param taxaJurosAnual Porcentagem de juros anual.
     * @param tamanhoDaAreaConstruida Tamanho da área construida no terreno.
     * @param tamanhoTerreno Tamanho do terreno.
     */
    public Casa(
        final double valorImovel,
        final int prazoFinanciamento,
        final double taxaJurosAnual,
        final double tamanhoDaAreaConstruida,
        final double tamanhoTerreno) {
            super(valorImovel, prazoFinanciamento, taxaJurosAnual);
            this.tamanhoAreaConstruida = tamanhoDaAreaConstruida;
            this.tamanhoDoTerreno = tamanhoTerreno;
        }

    /**
     * Permite que outras partes do código visualize.
     *
     * @return Retorna o tamanho do terreno indicado pelo usuário.
     */
    public double getAreaConstruida() {
        return tamanhoAreaConstruida;
    }

    /**
     * Permite que outras partes do código visualize o TamanhoTerreno.
     *
     * @return Retorna o tamanho do terreno indicado pelo usuário.
     */
    public double getTamanhoTerreno() {
        return tamanhoDoTerreno;
    }

    private void conferirValorAcrescimo(
        final double valorDeJuros,
        final double valorAcrescimo)
        throws AumentoMaiorDoQueJurosException {

        if (valorDeJuros < valorAcrescimo) {
            throw new AumentoMaiorDoQueJurosException(
                "O valor do acrescimo é maior que o juros,"
                + "convertendo o valor de acrescimo para o mesmo do juros.");
            }
        }
    /**
     * Calcula o valor do pagamento mensal de acordo com a classe.
     * Messe caso casa, metodo de calculo único da classe.
     *
     * @return Calculo de pagamento mensal.
     */
    public double calcularPagamentoMensal() {
        double valorDeJuros = (this.getValorImovel()
            / (this.getPrazoFinanciamento() * MESES_POR_ANO))
            * (1 + (this.getTaxaJurosAnual() / MESES_POR_ANO)) - (this.getValorImovel()
            / (this.getPrazoFinanciamento() * MESES_POR_ANO));

        double valorAcrescimo = ACRESCIMO_CASA;
        if (valorAcrescimo > valorDeJuros) {
            valorAcrescimo = valorDeJuros;
        }
        try {
        conferirValorAcrescimo(valorDeJuros, valorAcrescimo);
        } catch (AumentoMaiorDoQueJurosException e) {
        }
        return (this.getValorImovel() / (this.getPrazoFinanciamento() * MESES_POR_ANO))
        * (1 + (this.getTaxaJurosAnual() / MESES_POR_ANO)) + valorAcrescimo;
    }

    /**
     * Sobres creve o método abstrato imprimirDados.
     * Adicionando os atributos únicos da classe Casa.
     */
    @Override
    public void imprimirDados() {
        System.out.println("----------Financiamento Casa----------");
        System.out.printf("Área do terreno: %.2fm²\nÁrea Construida: %.2fm²\n",
        getAreaConstruida(), getTamanhoTerreno());
        System.out.printf(
            "Valor do Imovél: R$%.2f"
            + "\nPrazo: %d anos\nJuros Anual: %.2f por cento\n",
        getValorImovel(), getPrazoFinanciamento(), getTaxaJurosAnual());
        System.out.printf(
            "Valor Mensal: R$%.2f\nValor total do financiamento: R$%.2f\n",
        calcularPagamentoMensal(), calcularTotalPagamento());
    }

    /**
     * Converte os atributos de Casa para string.
     *
     * @return Retorna mensagem com informações do financiamento.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Financiamento de Casa\n");
        sb.append("Valor da casa: R$ ")
            .append(getValorImovel()).append("\n");
        sb.append("Prazo de financiamento: ")
            .append(getPrazoFinanciamento()).append(" anos.\n");
        sb.append("Taxa de Juros Anual: ")
            .append(getTaxaJurosAnual()).append("%.\n");
        sb.append("Tamanho da área construida: ")
            .append(tamanhoAreaConstruida).append("m²\n");
        sb.append("Tamanho do terreno: ")
            .append(tamanhoDoTerreno).append("m²\n");
        sb.append(String.format("Valor mensal: R$ %.2f",
            calcularPagamentoMensal())).append("\n");
        sb.append(String.format("Valor total do financiamento: R$ %.2f",
            calcularTotalPagamento())).append("\n\n");
        return sb.toString();
    }
}
