package simulation;

import util.AumentoMaiorDoQueJurosException;

public class Casa extends Financiamento {
    private double tamanhoAreaConstruida;
    private double tamanhoDoTerreno;

    // Adiciona ao contrutor os atributos da classe Financiamento junto aos atributos únicos da classe Casa. 
    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, double tamanhoAreaConstruida, double tamanhoDoTerreno) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tamanhoAreaConstruida = tamanhoAreaConstruida;
        this.tamanhoDoTerreno = tamanhoDoTerreno;
    }

    // permite que outras partes do código visualize a Area Construida(atributo que atualmente é privado/private).
    public double getAreaConstruida() {
        return tamanhoAreaConstruida;
    }

    // permite que outras partes do código visualize o Tamanho Terreno(atributo que atualmente é privado/private).
    public double getTamanhoTerreno() {
        return tamanhoDoTerreno;
    }

    private void conferirValorAcrescimo(double valorDeJuros, double valorAcrescimo) throws AumentoMaiorDoQueJurosException{
        valorDeJuros = (this.valorImovel / (this.prazoFinanciamentoEmAnos * 12)) * (1 +(this.taxaJurosAnual / 12)) - (this.valorImovel / (this.prazoFinanciamentoEmAnos * 12));
        valorAcrescimo = 80;
        if (valorDeJuros < valorAcrescimo) {
            throw new AumentoMaiorDoQueJurosException("O valor do acrescimo é maior que o juros, convertendo o valor de acrescimo para o mesmo do juros.");
        }

    }
    // Calcula o valor do pagamento mensal de acordo com a classe, nesse caso casa, metodo de calculo único da classe.
    public double calcularPagamentoMensal() {
        double valorDeJuros = (this.valorImovel / (this.prazoFinanciamentoEmAnos * 12))*(1 +(this.taxaJurosAnual / 12)) - (this.valorImovel / (this.prazoFinanciamentoEmAnos * 12));
        double valorAcrescimo = 80;
        if (valorAcrescimo > valorDeJuros) {
            valorAcrescimo = valorDeJuros;
        }
        valorAcrescimo = 80;
        try {
        conferirValorAcrescimo(valorDeJuros, valorAcrescimo);
        } catch (AumentoMaiorDoQueJurosException e) {
        }
        return (this.valorImovel / (this.prazoFinanciamentoEmAnos * 12)) * (1 +(this.taxaJurosAnual / 12)) + valorAcrescimo;
    }

    // Sobres creve o método abstrato imprimirDados, adicionando os atributos únicos da classe Casa.
    @Override
    public void imprimirDados() {
        System.out.println("----------Financiamento Casa----------");
        System.out.printf("Área do terreno: %.2f m²\nÁrea Construida: %.2f m²\n", getAreaConstruida(), getTamanhoTerreno());
        System.out.printf("Valor do Imovél: R$%.2f\nPrazo: %d anos\nJuros Anual: %.2f por cento\n", getValorImovel(), getPrazoFinanciamento(), getTaxaJurosAnual());
        System.out.printf("Valor Mensal: R$%.2f\nValor total do financiamento: R$%.2f\n", calcularPagamentoMensal(), calcularTotalPagamento());
    }

    // Converte os atributos de Casa para string
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Financiamento de Casa\n");
        sb.append("Valor da casa: R$ ").append(valorImovel).append("\n");
        sb.append("Prazo de financiamento: ").append(prazoFinanciamentoEmAnos).append(" anos.\n");
        sb.append("Taxa de Juros Anual: ").append(taxaJurosAnual).append("%.\n");
        sb.append("Tamanho da área construida: ").append(tamanhoAreaConstruida).append("m²\n");
        sb.append("Tamanho do terreno: ").append(tamanhoDoTerreno).append("m²\n");
        sb.append("Valor mensal: R$ ").append(calcularPagamentoMensal()).append("\n");
        sb.append("Valor total do financiamento: R$ ").append(calcularTotalPagamento()).append("\n\n");
        return sb.toString();
    }
    
}
