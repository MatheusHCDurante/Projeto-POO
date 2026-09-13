package util;

import java.util.InputMismatchException;
import static main.Main.getListaDeFinanciamento;
import simulation.Casa;

public final class InterfaceCasa extends InterfaceUsuario {

    /** Valor minimo de terreno. */
    private static final double VALOR_MIN_TERRENO = 200000.0;
    /** Valor máximo de juros. */
    private static final double VALOR_MAX_JUROS = 30.0;
    /**
     * Método para conferir se ha valores negativos ou zerado.
     * Para números reais.
     */
    private void conferirValoresNegativosOuZeroDouble(double valor)
        throws ValoresNegativosOuIgualZeroException {
            if (valor <= 0) {
                throw new ValoresNegativosOuIgualZeroException(
                    "O valor informado é zero ou negativo.");
            }
        }
    /**
     * Método para conferir se ha valores negativos ou zerado.
     * Para números inteiros.
     */
    private void conferirValoresNegativosOuZeroInt(int valor)
        throws ValoresNegativosOuIgualZeroException {
            if (valor <= 0) {
                throw new ValoresNegativosOuIgualZeroException(
                    "O valor informado é zero ou negativo.");
            }
        }
    /**
     * Método para conferir Valor minimo do terreno.
     * Para números inteiros.
     */
    private void conferirValoresMinimoImovel(double valor)
        throws ValoresMinimoImovelException {
            if (valor < VALOR_MIN_TERRENO) {
                throw new ValoresMinimoImovelException(
                    "O valor minimo de deve ser 200000,00.");
            }
        }
    /**
     * Método para conferir se ha valores acima do permitido.
     * Para números reais, O VALOR MÍNIMO E MÁXIMO PODE SER ALTERADO.
     */
    private void conferirValoresNaoPermitidosDouble(double valor)
        throws ValoresAcimaOuAbaixoDoPermitidoException {
            // Caso queira alterar o valor maximo ou mínimo.
            // NÃO ESQUECER DE MUDAR A MENSAGEM JUNTO.
            if (valor <= 0 || valor > VALOR_MAX_JUROS) {
                throw new ValoresAcimaOuAbaixoDoPermitidoException(
                    "O valor informado está fora do permitido"
                    + "O minimo de juros possível 0.1% e o maximo é 30.0%");
            }
    }
    /**
     * Método abstrato da InterfaceUsuario sobrescrito para obter o valor da casa.
     * 
     * @return Valor do imóvel.
     */
    @Override
    public double obterValorImovel() {
        while (true) { 
            // Valor base do valor do imóvel.
            double valorImovel = 0.0;
            try {
                // Solicita ao usuário o valor do imóvel
                // E substitui o valor atraves do scanner.
                System.out.println(
                    "Digite o valor da casa que deseja financiar: ");
                valorImovel += getScanner().nextDouble();
                getScanner().nextLine();
                // Informa ao usuário o prazo informado por ele.
                System.out.printf(
                    "O valor da casa é: R$%.2f\n", valorImovel);
                // Confere se há erro relacionado a valores negativos ou zerado.
                conferirValoresNegativosOuZeroDouble(valorImovel);
                conferirValoresMinimoImovel(valorImovel);
            // Captura entrada de dados errada.
            // Nesse caso qualquer caractere que não seja um número inteiro.
            } catch (InputMismatchException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("Porfavor use apenas númerais");
                getScanner().next();
                continue;
            // Captura o erro da entrada de valores negativos ou zerado.
            } catch (ValoresNegativosOuIgualZeroException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println(
                    "Número negativo, porfavor use apenas números positivos");
                continue;
            } catch (ValoresMinimoImovelException e) {
                System.out.println(e.getMessage());
                continue;
            }
            return valorImovel;
            }
        }

    /**
     * Método abstrato da InterfaceUsuario.
     * Sobrescrito para obter o prazo de financiamento.
     *
     * @return Prazo de financiamento em anos
     */
    @Override
    public int obterPrazoFinanciamento() {
        while (true) { 
            // Valor base do prazo de financiamento.
            int prazoFinanciamento = 0;
            try {
                // Solicita ao usuário o prazo de financiamento.
                // E substitui o valor atraves do scanner.
                System.out.println("Digite o prazo de pagamento em anos: ");
                prazoFinanciamento += getScanner().nextInt();
                // Informa ao usuário o prazo informado por ele.
                System.out.printf("O prazo de financiamento é: %d anos\n",
                 prazoFinanciamento);
                // Confere se há erro relacionado a valores negativos ou zerado.
                conferirValoresNegativosOuZeroInt(prazoFinanciamento);
            } catch (InputMismatchException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("Porfavor use apenas númerais");
                getScanner().next();
                continue;
            // Captura o erro da entrada de valores negativos ou zerado.
            }catch (ValoresNegativosOuIgualZeroException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println(
                    "Número negativo, porfavor use apenas números positivos");
                continue;
            }
            return prazoFinanciamento;
        }
    } 

    /**
     * Método abstrato da InterfaceUsuario sobrescrito para obter o juros.
     *
     * @return Porcentagem de juros anual.
     */
    @Override
    public double obterTaxaJuros() {
        while (true) { 
            // Define o valor base para a taxa de juros anual.
            double taxaJurosAnual = 0;
            try {
                // Solicita ao usuário a taxa de juros anual.
                // E substitui o valor atraves do scanner.
                System.out.println("Digite a taxa de juros anual: ");
                taxaJurosAnual += getScanner().nextDouble();
                // Informa ao usuário o juros anual informado por ele.
                System.out.println("A taxa de juros anual é: " 
                + taxaJurosAnual + "%\n");
                // Limita e confere o juros imposto no financiamento.
                conferirValoresNaoPermitidosDouble(taxaJurosAnual);
            // Captura o erro entrada de dado errado.
            // Nesse caso qualquer caractere que não seja um número inteiro. 
            }  catch (InputMismatchException e) {
                System.out.println("Porfavor use apenas númerais");
                getScanner().next();
                continue;
            // Captura o erro de valor acima ou abaixo do permitodo.
            } catch (ValoresAcimaOuAbaixoDoPermitidoException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println(
                    "O valor informado está fora do permitido."
                    + "O valor minimo de juros possível 0.1% e o maximo é 30.0%");
                continue;
            }
            return taxaJurosAnual; 
        }
    }

    /**
     * Método próprio para obter o atributo próprio da classe Casa.
     * No caso o tamanho da área construída.
     *
     * @return Tamanho da área construida.
     */
    public double obterTamanhoAreaConstruida() {
        while (true) {
            // Define o valor base para o tamanho da área construida.
            double tamanhoAreaConstruida = 0;
            try {
                // Solicita ao usuário o tamanho da área construida.
                // E substitui o valor atraves do scanner.
                System.out.println(
                    "Digite o tamanho da área construida: ");
                tamanhoAreaConstruida += getScanner().nextDouble();
                // Informa ao usuário o tamanho da área construida.
                System.out.printf("A área do terreno é %.2f m²\n",
                 tamanhoAreaConstruida);
                // Confere se há erro de valores negativos ou zerado.
                conferirValoresNegativosOuZeroDouble(tamanhoAreaConstruida);
            // Captura o erro entrada de dado errado.
            // Nesse caso qualquer caractere que não seja um número inteiro.
            } catch (InputMismatchException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("Porfavor use apenas númerais");
                getScanner().next();
                continue;
            // Captura o erro da entrada de valores negativos ou zerado.
            } catch (ValoresNegativosOuIgualZeroException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println(
                    "Número negativo, porfavor use apenas números positivos");
                continue;
            }
                return tamanhoAreaConstruida;
        }
    }

    /**
     * Método próprio para obter o atributo próprio da classe Casa.
     * No caso o tamanho do terreno.
     *
     * @return Tamanho do terreno
     */
    public double obterTamanhoDoTerreno() {
        while (true) { 
            // Define o valor base para o tamanho do terreno.
            double tamanhoDoTerreno = 0;
            try {
                // Solicita ao usuário o tamanho do terreno.
                // E substitui o valor atraves do scanner.
                System.out.println(
                    "Digite o tamanho do terreno, em que a casa está localizada: ");
                tamanhoDoTerreno += getScanner().nextDouble();
                conferirValoresNegativosOuZeroDouble(tamanhoDoTerreno);
                // Informa ao usuário o tamanho do terreno informado por ele.
                System.out.printf("A área do terreno é %.2f m²\n",
                    tamanhoDoTerreno);
            // Captura o erro entrada de dado errado.
            // Nesse caso qualquer caractere que não seja um número inteiro.
            } catch (InputMismatchException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("Porfavor use apenas númerais");
                getScanner().next();
                continue;
            // Captura o erro da entrada de valores negativos ou zerado.
            } catch (ValoresNegativosOuIgualZeroException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println(
                    "Número negativo, porfavor use apenas números positivos");
                continue;
            }
            getScanner().close();
            return  tamanhoDoTerreno;
        }
    }
    /**
     * Adiciona uma nova casa à listaDeFinancimanto.
     * @param valorImovel armazena o valor o imóvel.
     * @param prazoFincanciamento armazena prazo de financiamento.
     * @param taxaJurosAnual armazena juros anual em %.
     * @param tipoZoneamento armazena o tipo de zoneamento.
     * @param tamanhoAreaConstruida tamnho de área construida.
     * @param tamanhoDoTerreno tamanho do terreno.
     */
    public void adicionarALista(
        double valorImovel,
        int prazoFincanciamento,
        double taxaJurosAnual,
        double tamanhoAreaConstruida,
        double tamanhoDoTerreno) {
    getListaDeFinanciamento().add(
        new Casa(valorImovel,
            prazoFincanciamento,
            taxaJurosAnual,
            tamanhoAreaConstruida,
            tamanhoDoTerreno));
    }
}
