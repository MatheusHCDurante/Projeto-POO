import util.InterfaceApartamento;
import simulation.Apartamento;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class testes {
    /** Guarda o System.in original para restaurar depois de cada teste. */
    private final InputStream systemInOriginal = System.in;
    /** Guarda Delta faviação de resultado depois da ,. */
    private static final double DELTA = 0.01;

    /**Testa calculo de pagamento mensal. */
    @Test
    @DisplayName("Calcula corretamente o pagamento mensal (Tabela Price)")
    void testCalcularPagamentoMensal() {
        // Dados de entrada
        final double valorDoImovel = 200000.0;
        final int prazoAnos = 10;
        final double taxaJurosAnual = 10.0;
        final int numeroDeVagas = 2;
        final int andar = 5;

        Apartamento apartamento = new Apartamento(
                valorDoImovel, prazoAnos, taxaJurosAnual, numeroDeVagas, andar);

        // Valor esperado calculado de forma independente, com a mesma
        // fórmula da Tabela Price usada em calcularPagamentoMensal().
        final double taxaMensal = (taxaJurosAnual / 100) / 12;
        final double prazoMeses = prazoAnos * 12;
        final double fator = Math.pow(1 + taxaMensal, prazoMeses);
        final double valorEsperado = valorDoImovel * taxaMensal * fator / (fator - 1);

        double resultadoAtual = apartamento.calcularPagamentoMensal();

        assertEquals(valorEsperado, resultadoAtual, DELTA,
                "O cálculo do pagamento mensal do Apartamento falhou.");
    }

    /**Testa mudança no valor mensal ao longo do tempo. */
    @Test
    @DisplayName("Pagamento mensal muda quando o prazo aumenta")
    void testPagamentoMensalDiminuiComPrazoMaior() {
        Apartamento prazoCurto = new Apartamento(200000.0, 5, 10.0, 0, 1);
        Apartamento prazoLongo = new Apartamento(200000.0, 20, 10.0, 0, 1);

        // Quanto maior o prazo, menor tende a ser a parcela mensal.
        assertTrue(prazoLongo.calcularPagamentoMensal()
                < prazoCurto.calcularPagamentoMensal(),
                "Prazo maior deveria gerar parcela mensal menor.");
    }

    @AfterEach
    void restaurarSystemIn() {
    System.setIn(systemInOriginal);
    }

    /**Testa exceção ao receber valor negartivo. */
    @Test
    @DisplayName("Rejeita valor negativo e aceita o próximo valor válido")
    void testarValorNegativo() {
        String entrada = "-100\n200000\n";
 
        System.setIn(
            new ByteArrayInputStream(entrada.getBytes())
        );
 
        InterfaceApartamento interfaceApartamento =
            new InterfaceApartamento();
 
        double resultado = interfaceApartamento.obterValorImovel();
 
        assertEquals(200000.0, resultado);
    }

    /**Testa o pagamento total. */
    @Test
    void testarCalcularTotalPagamento() {

        Apartamento apartamento = new Apartamento(200000.0, 10, 10.0, 2, 1);

        double resultado = apartamento.calcularTotalPagamento();

        double valorEsperado = 317161.20;

        assertEquals(valorEsperado, resultado, 0.01);
    }


    /**Testa o calculo de juros mensal. */
    @Test
    @DisplayName("Calcula corretamente a taxa de juros mensal a partir da anual")
    void testTaxaDeJurosMensal() {
        final double taxaJurosAnual = 12.0;
 
        Apartamento apartamento = new Apartamento(
                200000.0, 10, taxaJurosAnual, 1, 2);
 
        // (12% ao ano / 100) / 12 meses = 0.01 ao mês
        final double taxaMensalEsperada = (taxaJurosAnual / 100) / 12;
 
        assertEquals(taxaMensalEsperada, apartamento.taxaDeJurosMensal(), DELTA,
                "A taxa de juros mensal calculada está incorreta.");
    }

    
}
