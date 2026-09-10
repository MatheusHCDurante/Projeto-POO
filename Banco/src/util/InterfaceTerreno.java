package util;

import java.util.InputMismatchException;
import java.util.Scanner;
import static main.Main.listaDeFinanciamento;
import simulation.Terreno;

public class InterfaceTerreno extends InterfaceUsuario {

    // Método para conferir se ha valores negativos ou zerado, para números reais.
    private void conferirValoresNegativosOuZeroDouble(double valor) throws ValoresNegativosOuIgualZeroException {
        if (valor <= 0) {
            throw new ValoresNegativosOuIgualZeroException("O valor informado é zero ou negativo.");
        }
    }
    private void conferirValoresMinimoImovel(double valor) throws ValoresMinimoImovelException {
        if (valor < 200000) {
            throw new ValoresMinimoImovelException("O valor minimo de deve ser 200000,00.");
        }
    }
    // Método para conferir se ha valores negativos ou zerado, para números inteiros.
    private void conferirValoresNegativosOuZeroInt(int valor) throws ValoresNegativosOuIgualZeroException {
        if (valor <= 0) {
            throw new ValoresNegativosOuIgualZeroException("O valor informado é zero ou negativo.");
        }
    }
    // Método para conferir se ha valores acima do permitido, para números reais, O VALOR MÍNIMO E MÁXIMO PODE SER ALTERADO.
    private void conferirValoresNaoPermitidosDouble(double valor) throws ValoresAcimaOuAbaixoDoPermitidoException {
        // Caso queira alterar o valor maximo ou mínimo NÃO ESQUECER DE MUDAR A MENSAGEM JUNTO
        if (valor <= 0 || valor > 30.0) {
            throw new ValoresAcimaOuAbaixoDoPermitidoException("O valor informado está fora do permitido. O minimo de juros possível 0.1% e o maximo é 30.0%");
        }
    }

    // Método abstrato da InterfaceUsuario sobrescrito para obter o valor do terreno.
    @Override
    public double obterValorImovel() {
        while (true) { 
            // Valor base do valor do imóvel.
            double valorImovel = 0.0;
            try {
                // Solicita ao usuário o valor do imóvel, eu substitui o valor atraves do scanner.
                System.out.println("Digite o valor do apartamento que deseja financiar: ");
                valorImovel += scanner.nextDouble();
                // Informa ao usuário o prazo informado por ele.
                System.out.printf("O valor da casa é: R$%.2f\n", valorImovel);
                // Confere se há erro relacionado a valores negativos ou zerado.
                conferirValoresNegativosOuZeroDouble(valorImovel);
                conferirValoresMinimoImovel(valorImovel);
            // Captura entrada de dados errada, nesse caso qualquer caractere que não seja um número inteiro.  
            } catch (InputMismatchException e) {
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
                // Solicita ao usuário o prazo de inanciamento, eu substitui o valor atraves do scanner.
                System.out.println("Digite o prazo de pagamento em anos: ");
                prazoFinanciamento += scanner.nextInt();
                // Informa ao usuário o prazo informado por ele.
                System.out.printf("O prazo de financiamento é: %d anos\n", prazoFinanciamento);
                // Confere se há erro relacionado a valores negativos ou zerado.
                conferirValoresNegativosOuZeroInt(prazoFinanciamento);
            // Captura o erro entrada de dado errada, nesse caso qualquer caractere que não seja um número inteiro.         
            } catch (InputMismatchException e) {
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
            // Valor base da taxa de juros.
            double taxaJurosAnual = 0;
            try {
                // Solicita ao usuário a taxa de juros anual, e substitui o valor atraves do scanner.
                System.out.println("Digite a taxa de juros anual: ");
                taxaJurosAnual += scanner.nextDouble();
                // Informa ao usuário o juros anual informado por ele.
                System.out.println("A taxa de juros anual é: " + taxaJurosAnual +"%\n");
                // Limita e confere o juros imposto no financiamento. (O limitador pode ser alterado seguindo as preferências da empresa.)
                conferirValoresNaoPermitidosDouble(taxaJurosAnual);
            // Captura o erro entrada de dado errada, nesse caso qualquer caractere que não seja um número inteiro.      
            } catch (InputMismatchException e) {
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

    // Método próprio da InterfaceTerreno para obter o atributo próprio da classe Terreno, no caso o tipo de zoneamento do terreno.
    public String obterTipoDeZoneamento() {
        while (true) {
            int tipoZoneamento = 0;
            try {
                // Mostra ao usuário as opções de zoneamento, mostrando as principais, pode ser adicionado mais, caso seja nescesario
                // NESSE CASO SE ATENTAR EM MUDAR O CÓDIGO.
                System.out.println("Escolha o tipo de zoneamento:\n(1) Residêncial.\n(2) Comercial.\n(3) Industrial.");
                tipoZoneamento += scanner.nextInt();

                // Na opção 1, informa a escolha para o úsario e retorna a escolha para o sistema, no caso "Residêncial".
                if (tipoZoneamento == 1) {
                    System.out.println("Zoneamento Residêncial.");
                    return "Residêncial.";
                // Na opção 2, informa a escolha para o úsario e retorna a escolha para o sistema, no caso "Comercial.".
                } else if (tipoZoneamento == 2) {
                    System.out.println("Zoneamento comercial.");
                    return "Comercial.";
                // Na opção 2, informa a escolha para o úsario e retorna a escolha para o sistema, no caso "Industrial.".
                } else if (tipoZoneamento == 3) {
                    System.out.println("Zoneamento Industrial.");
                    return "Industrial.";
                // Caso o úsiario use um número além do permitido, informa o erro ao usuário e reinicia o loop.
                } else {
                    System.out.println("Tipo de zoneamento não encontrado, escolha entre os existente.");
                    continue;
                }  
            // Captura o erro entrada de dado errada, nesse caso qualquer caractere que não seja um número inteiro.         
            } catch (InputMismatchException e) {
                // Caso ocorra o erro, informa ao usuário.
                System.out.println("Porfavor use apenas númerais");
                scanner = new Scanner(System.in);
                continue;
            }
        }
    }

    // Adiciona um novo terreno à listaDeFinancimanto.
    public void adicionarALista(double valorImovel, int prazoFincanciamento, double taxaJurosAnual, String tipoZoneamento) {
        listaDeFinanciamento.add(new Terreno(valorImovel, prazoFincanciamento, taxaJurosAnual, tipoZoneamento));
    }
}
