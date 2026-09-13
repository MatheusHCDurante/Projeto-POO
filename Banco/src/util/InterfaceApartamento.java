package util;

import java.util.InputMismatchException;
import static main.Main.getListaDeFinanciamento;
import simulation.Apartamento;

public class InterfaceApartamento extends InterfaceUsuario {

    /** Valor minimo de terreno. */
    private static final double VALOR_MIN_TERRENO = 200000.0;
    /** Valor máximo de juros. */
    private static final double VALOR_MAX_JUROS = 30.0;

    /**
     * Método para conferir se ha valores inteiro negativos ou zerado.
     * Para números reais.
     *
     * @param valor Valor recebido.
     */
    private void conferirValoresNegativosOuZeroDouble(final double valor)
        throws ValoresNegativosOuIgualZeroException {
            if (valor <= 0) {
                throw new ValoresNegativosOuIgualZeroException(
                    "O valor informado é zero ou negativo.");
            }
        }

    /**
     * Método para conferir se ha valores double negativos ou zerado.
     * Para números inteiros.
     *
     * @param valor Valor recebido.
     */
    private void conferirValoresNegativosOuZeroInt(final int valor)
        throws ValoresNegativosOuIgualZeroException {
            if (valor <= 0) {
                throw new ValoresNegativosOuIgualZeroException(
                    "O valor informado é zero ou negativo.");
            }
        }

    /**
     * Método para conferir Valor minimo da casa.
     * Para números inteiros.
     *
     * @param valor Valor do terreno.
     */
    private void conferirValoresMinimoImovel(final double valor)
        throws ValoresMinimoImovelException {
            if (valor < VALOR_MIN_TERRENO) {
                throw new ValoresMinimoImovelException(
                    "O valor minimo de deve ser 200000,00.");
            }
        }
    /**
     * Método para conferir se ha valores acima do permitido.
     * Para números reais, O VALOR MÍNIMO E MÁXIMO PODE SER ALTERADO.
     *
     * @param valor Valor recebido.
     */
    private void conferirValoresNaoPermitidosDouble(final double valor)
        throws ValoresAcimaOuAbaixoDoPermitidoException {
        // Caso queira alterar o valor maximo ou mínimo .
            // NÃO ESQUECER DE MUDAR A MENSAGEM JUNTO.
            if (valor <= 0 || valor > VALOR_MAX_JUROS) {
                throw new ValoresAcimaOuAbaixoDoPermitidoException(
                    "O valor informado está fora do permitido."
                    + "O minimo de juros possível 0.1% e o maximo é 30.0%");
            }
        }

    /**
     * Método abstrato da InterfaceUsuario sobrescrito para obter.
     * Valor do apartamento.
     *
     * @return Valor do imóvel.
     */
    @Override
    public double obterValorImovel() {
        while (true) {
            // Valor base do valor do imóvel.
            double valorImovel = 0.0;
            try {
                // Solicita ao usuário o valor do imóvel.
                // E substitui o valor atraves do scanner.
                System.out.println(
                    "Digite o valor do apartamento que deseja financiar: ");
                valorImovel += getScanner().nextDouble();
                // Informa ao usuário o prazo informado por ele.
                System.out.printf("O valor da casa é: R$%.2f\n", valorImovel);
                // Confere se há erro relacionado a valores negativos ou zerado.
                conferirValoresNegativosOuZeroDouble(valorImovel);
                conferirValoresMinimoImovel(valorImovel);
            // Captura entrada de dados errada
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
     * Método abstrato da InterfaceUsuario sobrescrito para obter.
     * Prazo de financiamento.
     *
     * @return Prazo de financiamento em anos.
     */
    @Override
    public int obterPrazoFinanciamento() {
        while (true) {
            // Valor base do prazo de financiamento.
            int prazoFinanciamento = 0;
            try {
                // Solicita ao usuário o prazo de financiamento
                // E substitui o valor atraves do scanner.
                System.out.println("Digite o prazo de pagamento em anos: ");
                prazoFinanciamento += getScanner().nextInt();
                // Informa ao usuário o prazo informado por ele.
                System.out.printf("O prazo de financiamento é: %d anos\n",
                 prazoFinanciamento);
                // Confere se há erro relacionado a valores negativos ou zerado.
                conferirValoresNegativosOuZeroInt(prazoFinanciamento);
            // Captura o erro entrada de dado errada.
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
            return prazoFinanciamento;
        }
    }

    /**
     * Método abstrato da InterfaceUsuario sobrescrito para obter o juros.
     *
     * @return Porcentagem de juros.
     */
    @Override
    public double obterTaxaJuros() {
        while (true) {
            // Define o valor base para a taxa de juros anual.
            double taxaJurosAnual = 0;
            try {
                // Solicita ao usuário a taxa de juros anual
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
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("Porfavor use apenas númerais");
                getScanner().next();
                continue;
            // Captura o erro de valores acima ou abaixo do permitido.
            } catch (ValoresAcimaOuAbaixoDoPermitidoException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println(
                    "O valor informado está fora do permitido."
                    + "O minimo de juros possível 0.1% e o maximo é 30.0%");
                continue;
            }
            return taxaJurosAnual;
        }
    }

    /**
     * Método próprio para obter o atributo próprio da classe Apartamento.
     * No caso o número de vagas na garagem.
     *
     * @return Número de vagas de garagem.
     */
    public int obterNumeroDeVagasGaragem() {
        while (true) {
            // Define o valor base para o número de vagas na garagem.
            int numeroDeVagasGaragem = 0;
            try {
                // Solicita ao usuário o número de vagas.
                // E substitui o valor atraves do scanner.
                System.out.println("Digite o número de vagas na garagem; ");
                numeroDeVagasGaragem += getScanner().nextInt();
                // Informa ao usuário o número de vagas na garagem..
                System.out.println("O número de vagas é: " + numeroDeVagasGaragem);
                // Confere se há erro relacionado a valores negativos ou zerado.
                conferirValoresNegativosOuZeroDouble(numeroDeVagasGaragem);
            // Captura o erro entrada de dado errado.
            // Nesse caso qualquer caractere que não seja um número inteiro.
            }  catch (InputMismatchException e) {
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
            return numeroDeVagasGaragem;
        }
    }

    /**
     * Método próprio para obter o atributo próprio da classe Apartamento.
     * No caso o número do andar do apartamento.
     *
     * @return Andar do apartamento
     */
    public int obterNumeroDoAndar() {
        while (true) {
            // Define o valor base para o número de vagas na garagem.
            int numeroDoAndar = 0;
            try {
                // Solicita ao usuário o número de vagas,
                // E substitui o valor atraves do scanner.
                System.out.println("Digite o andar do apartamento: ");
                numeroDoAndar += getScanner().nextInt();
                // Informa ao usuário o número de vagas na garagem.
                System.out.printf(
                    "O andar do apartamento é %d.\n", numeroDoAndar);
                // Confere se há erro de valores negativos ou zerado.
                conferirValoresNegativosOuZeroDouble(numeroDoAndar);
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
            return numeroDoAndar;
        }
    }

    /**
     * Adiciona um novo apartamento à listaDeFinancimanto.
     *
     * @param valorImovel armazena o valor o imóvel.
     * @param prazoFincanciamento armazena prazo de financiamento.
     * @param taxaJurosAnual armazena juros anual em %.
     * @param numeroDeVagasGaragem quantidade de vagas na garagem.
     * @param numeroDoAndar andar do apartamento.
     */
    public void adicionarALista(
        final double valorImovel,
        final int prazoFincanciamento,
        final double taxaJurosAnual,
        final int numeroDeVagasGaragem,
        final int numeroDoAndar) {
        getListaDeFinanciamento().add(
            new Apartamento(
                valorImovel,
                prazoFincanciamento,
                taxaJurosAnual,
                numeroDeVagasGaragem,
                numeroDoAndar));
    }
}
