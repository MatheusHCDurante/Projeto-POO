package simulation;

public class Apartamento extends Financiamento{
    private int numeroDeVagasGaragem;
    private int numeroDoAndar;

    // Adiciona ao contrutor os atributos da classe Financiamento junto aos atributos únicos da classe Apartamento. 
    public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, int numeroDeVagasGaragem, int numeroDoAndar) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.numeroDeVagasGaragem = numeroDeVagasGaragem;
        this.numeroDoAndar = numeroDoAndar;
    }

    // permite que outras partes do código visualize o númeor de vagas na garagem(atributo que atualmente é privado/private).
    public int getNumeroDeVagasGaragem() {
        return numeroDeVagasGaragem;
    }

    // permite que outras partes do código visualize o númeor do andar do apartamento(atributo que atualmente é privado/private).
    public int getNumeroDoAndar() {
        return numeroDoAndar;
    }

    // Método da classe Apartamentos, para calcular a taxa de juros mensal.
    public double taxaDeJurosMensal() {
        return taxaJurosAnual / 12;
    }

    // Método da classe Apartamentos, para calcular o prazo anual em meses.
    public double prazoMensal() {
        return prazoFinanciamentoEmAnos * 12;
    }

    // Calcula o valor do pagamento mensal de acordo com a classe, nesse caso casa, metodo de calculo único da classe.
    public double calcularPagamentoMensal() {
        return valorImovel * taxaDeJurosMensal() * Math.pow((1 + taxaDeJurosMensal()), prazoMensal()) / Math.pow((1 + taxaDeJurosMensal()), prazoMensal()) - 1;
    }
    // Sobrescreve o método abstrato imprimirDados, adicionando os atributos únicos da classe Apartamento.
    @Override
    public void imprimirDados() {
        System.out.println("----------Financiamento Apartamento----------");
        System.out.printf("Andar Nº %d \nVagas de garagem: %d \n", getNumeroDoAndar(), getNumeroDeVagasGaragem());
        System.out.printf("Valor do Imovél: R$%.2f\nPrazo: %d anos\nJuros Anual: %.2f por cento\n", getValorImovel(), getPrazoFinanciamento(), getTaxaJurosAnual());
        System.out.printf("Valor Mensal: R$%.2f\nValor total do financiamento: R$%.2f\n", calcularPagamentoMensal(), calcularTotalPagamento());
    }

    // Converte os atributos de Apartamento para string
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Financiamento de Apartamento\n");
        sb.append("Valor do apartamento: R$ ").append(valorImovel).append("\n");
        sb.append("Prazo de financiamento: ").append(prazoFinanciamentoEmAnos).append(" anos.\n");
        sb.append("Taxa de Juros Anual: ").append(taxaJurosAnual).append("%.\n");
        sb.append("Quantidade de vagas na garagem: ").append(numeroDeVagasGaragem).append("\n");
        sb.append("Número do andar: ").append(numeroDoAndar).append("\n");
        sb.append("Valor mensal: R$ ").append(calcularPagamentoMensal()).append("\n");
        sb.append("Valor total do financiamento: R$ ").append(calcularTotalPagamento()).append("\n\n");
        return sb.toString();
    }
}
