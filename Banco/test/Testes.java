import util.InterfaceApartamento;
import simulation.Apartamento;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Testes {
    /** Guarda o System.in original para restaurar depois de cada teste. */
    private final InputStream systemInOriginal = System.in;
    /** Guarda Delta faviação de resultado depois da ,. */
    private static final double DELTA = 0.01;
    /** Guarda valor do imovel. */
    private static final double VALOR_IMOVEL = 200000.0;
    /** Guarda prazo de 10 anos. */
    private static final int PRAZO_ANOS_10 = 10;
    /** Guarda prazo de 20 anos. */
    private static final int PRAZO_ANOS_20 = 20;
    /** Guarda taxa de juros. */
    private static final double TAXA_JUROS = 10.0;
    /** Guarda numero de vagas. */
    private static final int NUMERO_VAGAS = 0;
    /** Guarda andar do apartamento. */
    private static final int NUMERO_ANDAR = 1;
    /** Guarda andar do apartamento. */
    private static final Double VALOR_TESTE = 317161.20;



    /**Testa calculo de pagamento mensal. */
    @Test
    @DisplayName("Calcula corretamente o pagamento mensal")
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
        final double valorEsperado = valorDoImovel * taxaMensal * fator
        / (fator - 1);

        double resultadoAtual = apartamento.calcularPagamentoMensal();

        assertEquals(valorEsperado, resultadoAtual, DELTA,
                "O cálculo do pagamento mensal do Apartamento falhou.");
    }

    /**Testa mudança no valor mensal ao longo do tempo. */
    @Test
    @DisplayName("Pagamento mensal muda quando o prazo aumenta")
    void testPagamentoMensalDiminuiComPrazoMaior() {
        Apartamento prazoCurto = new Apartamento(
            VALOR_IMOVEL,
            PRAZO_ANOS_10,
            TAXA_JUROS,
            NUMERO_VAGAS,
            NUMERO_ANDAR);
        Apartamento prazoLongo = new Apartamento(
            VALOR_IMOVEL,
            PRAZO_ANOS_20,
            TAXA_JUROS,
            NUMERO_VAGAS,
            NUMERO_ANDAR);

        // Quanto maior o prazo, menor tende a ser a parcela mensal.
        assertTrue(prazoLongo.calcularPagamentoMensal()
                < prazoCurto.calcularPagamentoMensal(),
                "Prazo maior deveria gerar parcela mensal menor.");
    }

    @AfterEach
    final void restaurarSystemIn() {
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

        assertEquals(VALOR_IMOVEL, resultado);
    }

    /**Testa o pagamento total. */
    @Test
    void testarCalcularTotalPagamento() {

        Apartamento apartamento = new Apartamento(
            VALOR_IMOVEL,
            PRAZO_ANOS_10,
            TAXA_JUROS,
            NUMERO_VAGAS,
            NUMERO_ANDAR);

        double resultado = apartamento.calcularTotalPagamento();
        final double taxaMensal = (TAXA_JUROS / 100) / 12;
        final double prazoMeses = PRAZO_ANOS_10 * 12;
        final double fator = Math.pow(1 + taxaMensal, prazoMeses);
        final double pagamentoMensalEsperado = VALOR_IMOVEL * taxaMensal
        * fator / (fator - 1);
        final double valorEsperado = pagamentoMensalEsperado * prazoMeses;

        assertEquals(valorEsperado, resultado, DELTA);
    }


    /**Testa o calculo de juros mensal. */
    @Test
    @DisplayName("Calcula corretamente a taxa de juros mensal.")
    void testTaxaDeJurosMensal() {
        final double taxaJurosAnual = 12.0;

        Apartamento apartamento = new Apartamento(
            VALOR_IMOVEL,
            PRAZO_ANOS_10,
            taxaJurosAnual,
            NUMERO_VAGAS,
            NUMERO_ANDAR);

        final double taxaMensalEsperada = (taxaJurosAnual / 100) / 12;

        assertEquals(
            taxaMensalEsperada,
            apartamento.taxaDeJurosMensal(),
            DELTA,
            "A taxa de juros mensal calculada está incorreta.");
    }
}
