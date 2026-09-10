package util;

import java.util.InputMismatchException;
import java.util.Scanner;
import static main.Main.listaDeFinanciamento;
import simulation.Apartamento;

public class InterfaceApartamento extends InterfaceUsuario {

    // Método para conferir se ha valores negativos ou zerado, para números reais.
    private void conferirValoresNegativosOuZeroDouble(double valor) throws ValoresNegativosOuIgualZeroException {
        if (valor <= 0) {
            throw new ValoresNegativosOuIgualZeroException("O valor informado é zero ou negativo.");
        }
    }
    // Método para conferir se ha valores negativos ou zerado, para números inteiros.
    private void conferirValoresNegativosOuZeroInt(int valor) throws ValoresNegativosOuIgualZeroException {
        if (valor <= 0) {
            throw new ValoresNegativosOuIgualZeroException("O valor informado é zero ou negativo.");
        }
    }
    private void conferirValoresMinimoImovel(double valor) throws ValoresMinimoImovelException {
        if (valor < 200000) {
            throw new ValoresMinimoImovelException("O valor minimo de deve ser 200000,00.");
        }
    }
    // Método para conferir se ha valores acima do permitido, para números reais, O VALOR MÍNIMO E MÁXIMO PODE SER ALTERADO.
    private void conferirValoresNaoPermitidosDouble(double valor) throws ValoresAcimaOuAbaixoDoPermitidoException {
        // Caso queira alterar o valor maximo ou mínimo NÃO ESQUECER DE MUDAR A MENSAGEM JUNTO
        if (valor <= 0 || valor > 30.0) {
            throw new ValoresAcimaOuAbaixoDoPermitidoException("O valor informado está fora do permitido. O minimo de juros possível 0.1% e o maximo é 30.0%");
        }
    }

    // Método abstrato da InterfaceUsuario sobrescrito para obter o valor do apartamento.
    @Override
    public double obterValorImovel() {
        while (true) { 
            // Valor base do valor do imóvel.
            double valorImovel = 0.0;
            try {
                // Solicita ao usuário o valor do imóvel, e substitui o valor atraves do scanner.
                System.out.println("Digite o valor do apartamento que deseja financiar: ");
                valorImovel += scanner.nextDouble();
                // Informa ao usuário o prazo informado por ele.
                System.out.printf("O valor da casa é: R$%.2f\n", valorImovel);
                // Confere se há erro relacionado a valores negativos ou zerado.
                conferirValoresNegativosOuZeroDouble(valorImovel);
                conferirValoresMinimoImovel(valorImovel);
            // Captura entrada de dados errada, nesse caso qualquer caractere que não seja um número inteiro.
            } catch (InputMismatchException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("Porfavor use apenas númerais");
                scanner = new Scanner(System.in);
                continue;
            // Captura o erro da entrada de valores negativos ou zerado.
            } catch (ValoresNegativosOuIgualZeroException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("Número negativo, porfavor use apenas números positivos");
                continue;
            } catch (ValoresMinimoImovelException e) {
                System.out.println(e.getMessage());
                continue;
            }
            return valorImovel;  
        }
    }

    // Método abstrato da InterfaceUsuario sobrescrito para obter o prazo de financiamento.
    @Override
    public int obterPrazoFinanciamento() {
        while (true) {
            // Valor base do prazo de financiamento.
            int prazoFinanciamento = 0;
            try {
                // Solicita ao usuário o prazo de financiamento, e substitui o valor atraves do scanner.
                System.out.println("Digite o prazo de pagamento em anos: ");
                prazoFinanciamento += scanner.nextInt();
                // Informa ao usuário o prazo informado por ele.
                System.out.printf("O prazo de financiamento é: %d anos\n", prazoFinanciamento);
                // Confere se há erro relacionado a valores negativos ou zerado.
                conferirValoresNegativosOuZeroInt(prazoFinanciamento);
            // Captura o erro entrada de dado errada, nesse caso qualquer caractere que não seja um número inteiro.         
            } catch (InputMismatchException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("Porfavor use apenas númerais");
                scanner = new Scanner(System.in);
                continue;
            // Captura o erro da entrada de valores negativos ou zerado.
            } catch (ValoresNegativosOuIgualZeroException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("Número negativo, porfavor use apenas números positivos");
                continue;
            }
            return prazoFinanciamento;
        }
    }

    // Método abstrato da InterfaceUsuario sobrescrito para obter a taxa de juros.
    @Override
    public double obterTaxaJuros() {
        while (true) { 
            // Define o valor base para a taxa de juros anual.
            double taxaJurosAnual = 0;
            try {
                // Solicita ao usuário a taxa de juros anual, e substitui o valor atraves do scanner.
                System.out.println("Digite a taxa de juros anual: ");
                taxaJurosAnual += scanner.nextDouble();
                // Informa ao usuário o juros anual informado por ele.
                System.out.println("A taxa de juros anual é: " + taxaJurosAnual +"%\n");
                // Limita e confere o juros imposto no financiamento. (O limitador pode ser alterado seguindo as preferências da empresa.)
                conferirValoresNaoPermitidosDouble(taxaJurosAnual);
            // Captura o erro entrada de dado errado, nesse caso qualquer caractere que não seja um número inteiro. 
        }  catch (InputMismatchException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("Porfavor use apenas númerais");
                scanner = new Scanner(System.in);
                continue;
            // Captura o erro da entrada de valores acima ou abaixo do permitido.
            } catch (ValoresAcimaOuAbaixoDoPermitidoException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("O valor informado está fora do permitido. O valor minimo de juros possível 0.1% e o maximo é 30.0%");
                continue;
            }
            return taxaJurosAnual; 
        }
    }

    // Método próprio da InterfaceApartamento para obter o atributo próprio da classe Apartamento, no caso o número de vagas na garagem.
    public int obterNumeroDeVagasGaragem() {
        while (true) { 
            // Define o valor base para o número de vagas na garagem.
            int numeroDeVagasGaragem = 0;
            try {
                // Solicita ao usuário o número de vagas, e substitui o valor atraves do scanner.
                System.out.println("Digite o número de vagas na garagem; ");
                numeroDeVagasGaragem += scanner.nextInt();
                // Informa ao usuário o número de vagas na garagem informado por ele.
                System.out.println("O número de vagas é: " + numeroDeVagasGaragem);
                // Confere se há erro relacionado a valores negativos ou zerado.
                conferirValoresNegativosOuZeroDouble(numeroDeVagasGaragem);
            // Captura o erro entrada de dado errado, nesse caso qualquer caractere que não seja um número inteiro. 
            }  catch (InputMismatchException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("Porfavor use apenas númerais");
                scanner = new Scanner(System.in);
                continue;
            // Captura o erro da entrada de valores negativos ou zerado.
            } catch (ValoresNegativosOuIgualZeroException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("Número negativo, porfavor use apenas números positivos");
                continue;
            }
            return numeroDeVagasGaragem;
        }
    }

    // Método próprio da InterfaceApartamento para obter o atributo próprio da classe Apartamento, no caso o número do andar do apartamento.
    public int obterNumeroDoAndar() {
        while (true) { 
            // Define o valor base para o número de vagas na garagem.
            int numeroDoAndar = 0;
            try {
                // Solicita ao usuário o número de vagas, e substitui o valor atraves do scanner.
                System.out.println("Digite o andar do apartamento: ");
                numeroDoAndar += scanner.nextInt();
                // Informa ao usuário o número de vagas na garagem informado por ele.
                System.out.printf("O andar do apartamento é %d.\n", numeroDoAndar);       
                // Confere se há erro relacionado a valores negativos ou zerado.
                conferirValoresNegativosOuZeroDouble(numeroDoAndar);
            // Captura o erro entrada de dado errado, nesse caso qualquer caractere que não seja um número inteiro. 
            } catch (InputMismatchException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("Porfavor use apenas númerais");
                scanner = new Scanner(System.in);
                continue; 
            // Captura o erro da entrada de valores negativos ou zerado.
            } catch (ValoresNegativosOuIgualZeroException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("Número negativo, porfavor use apenas números positivos");
                continue;
            }
            scanner.close();
            return numeroDoAndar;
        }
    }

    // Adiciona um novo apartamento à listaDeFinancimanto.
    public void adicionarALista(double valorImovel, int prazoFincanciamento, double taxaJurosAnual, int numeroDeVagasGaragem, int numeroDoAndar) {
            listaDeFinanciamento.add(new Apartamento(valorImovel, prazoFincanciamento, taxaJurosAnual, numeroDeVagasGaragem, numeroDoAndar));
    }
}
