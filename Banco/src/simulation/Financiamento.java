package simulation;
import java.io.Serializable;
import static main.Main.listaDeFinanciamento;
public abstract class Financiamento implements Serializable {

    // Atributos (define os atributos da classe abstrata Financiamento).
    /** Valor do Imovel usado no financiamento. */
    protected  double valorImovel;
    /** Tempo de duração financiamento em anos. */
    protected int prazoFinanciamentoEmAnos;
    /** porcentagem de juros anual. */
    protected double taxaJurosAnual;
    /** valor inicial total de todos os imóveis simulados. */
    protected static double valorTotalImoveis;
    /** Valor total dos financiamentos simulados. */
    private static double valorTotalFinanciamentos;

    // Construtor
    public Financiamento(
        double valorImovel,
        int prazoFinanciamento,
        double taxaJurosAnual) {
        this.valorImovel = valorImovel;
        this.prazoFinanciamentoEmAnos = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    /**
     * Getters (permite que outras partes do código possam acesar.
     * Apenas ver os dados do atributo, não pode alterar os dados).
     * Permite que outras partes do código visualize o valor do imovél
     * (atributo que atualmente é privado/private).
     * 
     * @return valor do imovel.
     */
    public double getValorImovel() {
        return valorImovel;
    }
    /**
     * Permite que outras partes do código visualize o prazo.
     * De financiamento(atributo que atualmente é privado/private).
     * 
     * @return prazo de financimanto em anos.
     */
     public int getPrazoFinanciamento() {
        return prazoFinanciamentoEmAnos;
    }
    /**
     * Permite que outras partes do código visualize a taxa de.
     * juros anual(atributo que atualmente é privado/private).
     * 
     * @return porcentagem de juros anual.
     */
    public double getTaxaJurosAnual() {
        return taxaJurosAnual;
    }
    /**
     * métodos calculo.
     * Calcula o valor do pagamento mensal. Abstract, o cálculo muda de acordo.
     * com classe(financiamento da classe x tem formas próprias de calculo).
     */
    public abstract double calcularPagamentoMensal();

    /**
     * Calcula o valor de pagamento total.
     * 
     * @return Multiplicação do pagamento mensal por 12 meses.
     */
    public double calcularTotalPagamento() {
        return this.calcularPagamentoMensal() * 
        (this.prazoFinanciamentoEmAnos * 12);
    }

    /**
     * Calcula o valor total da soma de todos os imóveis
     * cadastrados na lista listaDeFinanciamento.
     * 
     * @return Soma dos valor inicial dos imovéis.
     */
    static public double calcularValorTotalImoveis() {
            valorTotalImoveis = 0;
    for (Financiamento financiamento: listaDeFinanciamento) {
        valorTotalImoveis += financiamento.valorImovel;
    }
    return valorTotalImoveis;
    }

    /**
     * Calcula o valor total da soma de todos os financiamentos
     * Cadastrados na lista listaDeFinanciamento.
     * 
     * @return soma do valor final dos financiamentos.
     */
    static public double calcularValorTotalFinanciamentos() {
        valorTotalFinanciamentos = 0;
        for (Financiamento financiamento : listaDeFinanciamento) {
        valorTotalFinanciamentos += financiamento.calcularTotalPagamento();
        }
        return valorTotalFinanciamentos;
    }

    // Método imprimir
    /**
     * Imprime todas as informações relacionadas ao financiamento.
     * primeiro informa as informações coletadas.
     * depois os resultados das operações necessárias .
     * para o calculo do financiamento.
     */
    public abstract void imprimirDados();

    /**
     * Imprime o valor total de todos os imovéis.
     * E o valor total de todos os financiamentos.
     */ 
    static public void imprimirValores() {
        System.out.println("\n----------Valor Total Imovéis----------");
        System.out.printf("Valor total dos imovéis é: R$%.2f\n",
        calcularValorTotalImoveis());
        System.out.println("\n----------Valor Total Financiamentos----------");
        System.out.printf("Valor total dos financiamentos é: R$%.2f\n", 
        calcularValorTotalFinanciamentos());
    }
}
