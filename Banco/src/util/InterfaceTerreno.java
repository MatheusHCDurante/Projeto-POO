package util;

import java.util.InputMismatchException;
import static main.Main.listaDeFinanciamento;
import simulation.Terreno;

public class InterfaceTerreno extends InterfaceUsuario {

    /** Valor minimo de terreno */
    private static final  double valorMinTerreno = 200000.0;
    /** Valor máximo de juros */
    private static final  double valorMaxJuros = 30.0;

    // Método para conferir se ha valores negativos ou zerado.
    private void conferirValoresNegativosOuZeroDouble(final double valor)
    throws ValoresNegativosOuIgualZeroException {
        if (valor <= 0) {
            throw new ValoresNegativosOuIgualZeroException(
                "O valor informado é zero ou negativo.");
        }
    }
    private void conferirValoresMinimoImovel(final double valor)
    throws ValoresMinimoImovelException {
        if (valor < valorMinTerreno) {
            throw new ValoresMinimoImovelException(
                "O valor minimo de deve ser 200000,00.");
        }
    }
    // Método para conferir se ha valores negativos ou zerado.
    private void conferirValoresNegativosOuZeroInt(final int valor)
    throws ValoresNegativosOuIgualZeroException {
        if (valor <= 0) {
            throw new ValoresNegativosOuIgualZeroException(
                "O valor informado é zero ou negativo.");
        }
    }
    // Método para conferir se ha valores acima do permitido.
    // O VALOR MÍNIMO E MÁXIMO PODE SER ALTERADO.
    private void conferirValoresNaoPermitidosDouble(final double valor)
    throws ValoresAcimaOuAbaixoDoPermitidoException {
        // Caso queira alterar o valor maximo ou mínimo
        // NÃO ESQUECER DE MUDAR A MENSAGEM JUNTO
        if (valor <= 0 || valor > valorMaxJuros) {
            throw new ValoresAcimaOuAbaixoDoPermitidoException(
                "O valor informado está fora do permitido."
                + " O mínimo de juros possível 0.1% e o maximo é 30.0%");
        }
    }

    // Método abstrato da InterfaceUsuario sobrescrito
    // Para obter o valor do terreno.
    @Override
    public double obterValorImovel() {
        while (true) {
            // Valor base do valor do imóvel.
            double valorImovel = 0.0;
            try {
                // Solicita ao usuário o valor do imóvel.
                // Eu substitui o valor atraves do scanner.
                System.out.println(
                    "Digite o valor do terreno que deseja financiar: ");
                valorImovel += getScanner().nextDouble();
                // Informa ao usuário o prazo informado por ele.
                System.out.printf("O valor da casa é: R$%.2f\n", valorImovel);
                // Confere se há erro relacionado a valores negativos ou zerado.
                conferirValoresNegativosOuZeroDouble(valorImovel);
                conferirValoresMinimoImovel(valorImovel);
            // Captura entrada de dados errada.
            // Nesse caso qualquer caractere que não seja um número inteiro.
            } catch (InputMismatchException e) {
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

    // Método abstrato da InterfaceUsuario.
    // Sobrescrito para obter o prazo de financiamento.
    @Override
    public int obterPrazoFinanciamento() {
        while (true) {
            // Valor base do prazo de financiamento.
            int prazoFinanciamento = 0;
            try {
                // Solicita ao usuário o prazo de inanciamento.
                // Eu substitui o valor atraves do scanner.
                System.out.println("Digite o prazo de pagamento em anos: ");
                prazoFinanciamento += getScanner().nextInt();
                // Informa ao usuário o prazo informado por ele.
                System.out.printf(
                    "O prazo de financiamento é: %d anos\n",
                    prazoFinanciamento);
                // Confere se há erro relacionado a valores negativos ou zerado.
                conferirValoresNegativosOuZeroInt(prazoFinanciamento);
            // Captura o erro entrada de dado errada.
            // Nesse caso qualquer caractere que não seja um número inteiro.
            } catch (InputMismatchException e) {
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

    // Método abstrato da InterfaceUsuario.
    // Sobrescrito para obter a taxa de juros.
    @Override
    public double obterTaxaJuros() {
        while (true) {
            // Valor base da taxa de juros.
            double taxaJurosAnual = 0;
            try {
                // Solicita ao usuário a taxa de juros anual.
                // Substitui o valor atraves do scanner.
                System.out.println("Digite a taxa de juros anual: ");
                taxaJurosAnual += getScanner().nextDouble();
                // Informa ao usuário o juros anual informado por ele.
                System.out.println(
                    "A taxa de juros anual é: " + taxaJurosAnual + "%\n");
                // Limita e confere o juros imposto no financiamento.
                // (O limitador pode ser alterado.)
                conferirValoresNaoPermitidosDouble(taxaJurosAnual);
            // Captura o erro entrada de dado errada.
            // Nesse caso qualquer caractere que não seja um número inteiro.
            } catch (InputMismatchException e) {
                System.out.println("Porfavor use apenas númerais");
                getScanner().next();
                continue;
            // Captura o erro de valores acima ou abaixo do permitido.
            } catch (ValoresAcimaOuAbaixoDoPermitidoException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println(
                    "O valor informado está fora do permitido."
                    + "O mínimo de juros possível 0.1% e o maximo é 30.0%");
                continue;
            }
            return taxaJurosAnual;
        }
    }

    /**
     * Método próprio da InterfaceTerreno para obter o atributo.
     * Próprio da classe Terreno, no caso o tipo de zoneamento do terreno.
     *
     * @return Retorna o tipo de zoneamento do terreno.
     */
    public String obterTipoDeZoneamento() {
        while (true) {
            int tipoZoneamento = 0;
            try {
                // Mostra ao usuário as opções de zoneamento.
                //  mostrando as principais.
                // Pode ser adicionado mais, caso seja nescesario.
                // NESSE CASO SE ATENTAR EM MUDAR O CÓDIGO.
                System.out.println(
                    "Escolha o tipo de zoneamento:"
                    + "\n(1) Residêncial.\n(2) Comercial.\n(3) Industrial.");
                tipoZoneamento += getScanner().nextInt();

                // Na opção 1, informa a escolha para o úsario.
                // Retorna a escolha para o sistema, no caso "Residêncial".
                if (tipoZoneamento == 1) {
                    System.out.println("Zoneamento Residêncial.");
                    return "Residêncial.";
                // Na opção 2, informa a escolha para o úsario.
                // Retorna a escolha para o sistema, no caso "Comercial.".
                } else if (tipoZoneamento == 2) {
                    System.out.println("Zoneamento comercial.");
                    return "Comercial.";
                // Na opção 2, informa a escolha para o úsario.
                // Retorna a escolha para o sistema, no caso "Industrial.".
                } else if (tipoZoneamento == 3) {
                    System.out.println("Zoneamento Industrial.");
                    return "Industrial.";
                // Caso o úsiario use um número além do permitido.
                // Informa o erro ao usuário e reinicia o loop.
                } else {
                    System.out.println(
                        "Tipo de zoneamento não encontrado"
                    );

                    continue;
                }
            // Captura o erro entrada de dado errada.
            // Nesse caso qualquer caractere que não seja um número inteiro.
            } catch (InputMismatchException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("Por favor use apenas númerais");
                getScanner().next();
                continue;
            }
        }
    }

    /** Adiciona um novo terreno à listaDeFinancimanto.
     * @param valorImovel armazena o valor o imóvel.
     * @param prazoFincanciamento armazena prazo de financiamento.
     * @param taxaJurosAnual armazena juros anual em %.
     * @param tipoZoneamento armazena o tipo de zoneamento.
     * Zoneamento residencial, comercial ou industrial.
     */
    public void adicionarALista(
        final double valorImovel,
        final int prazoFincanciamento,
        final double taxaJurosAnual,
        final String tipoZoneamento) {
        listaDeFinanciamento.add(new Terreno(valorImovel, prazoFincanciamento,
        taxaJurosAnual, tipoZoneamento));
    }
}
