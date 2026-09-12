package util;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class InterfaceUsuario {
    /*
     * Obter dados apartir do usuário
     */
    Scanner scanner = new Scanner(System.in);
    // Método para conferir se ha valores negativos ou zerado, para números inteiros.
    static public void conferirValoresNegativosOuZero(int tipoFinanciamento)
    throws ValoresNegativosOuIgualZeroException {
        if (tipoFinanciamento <= 0) {
            throw new ValoresNegativosOuIgualZeroException(
                "O valor informado é zero ou negativo.");
        }
    }

    // Solicita para o úsiario qual o tipo de financiamento ele quer fazer
    public int obterTipoDeFinanciamento() {
        while (true) { 
            // Define o valor base para o tipo de Financiamento.
            int tipoFinanciamento = 0;
            Scanner scanner = new Scanner(System.in);
            try {
                // Oferece ao usário opções e solicita o tamanho do
                // terreno e substitui o valor atraves do scanner.
                System.out.println("Escolha qual o tipo de ímovel que você deseja financiar:\n(1) Casa.\n(2) Apartamento.\n(3) Terreno.\n");
                tipoFinanciamento += scanner.nextInt();
                // Informa ao usuário o tipo de financiamento selecionado.
                conferirValoresNegativosOuZero(tipoFinanciamento);
            // Captura o erro entrada de dado errado,
            // nesse caso qualquer caractere que não seja um número inteiro.
            } catch (InputMismatchException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("Porfavor use apenas númerais");
                continue;
            // Captura o erro da entrada de valores negativos ou zerado.
            } catch (ValoresNegativosOuIgualZeroException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println(
                    "Número negativo, porfavor use apenas números positivos");
                continue;
            }
                return tipoFinanciamento;
            }
        }

    /** Método abstrato para obter o valor dos imóveis de cada sub-classe. */
    public abstract double obterValorImovel();
    /**
     * Método abstrato para obter o prazo de financiamento de cada sub-classe.
     */
    public abstract int obterPrazoFinanciamento();
    /** Método abstrato para obter a taxa de juros de cada sub-classe. */
    public abstract double obterTaxaJuros();
}
