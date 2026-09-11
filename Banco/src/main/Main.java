package main;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import simulation.Apartamento;
import simulation.Casa;
import simulation.Financiamento;
import simulation.Terreno;

import util.InterfaceCasa;
import util.InterfaceApartamento;
import util.InterfaceTerreno;



public class Main {
    public static List<Financiamento> listaDeFinanciamento = 
        new ArrayList<Financiamento>();
    // Método para fazer a leitura dos dados salvo no arquivo 
    // "financiamentos.txt"
    public static void LerFinanciamentosSalvos() {
        FileReader leitor = null;
        try {
            leitor = new FileReader("financiamentos.txt");
            int c;
            while ((c = leitor.read()) != -1)
                System.out.print((char)c);
            leitor.close(); 
        // Captura de erro de arquivos não encontrado.
        } catch (FileNotFoundException e) {
            // Informa o erro ao usuário.
            System.out.println("Arquivo não encontrado! Reiniciando o código");
        // Captura de erro IO.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para serializar a listaDeFinanciamento
    // no arquivo Financiamento2.txt
    public static void EscreverDadosSerializados() {
        ObjectOutputStream escritor2 = null;
        try {
            // Cria uma nova entrada de objeto em um novo arquivo 
            // "Financiamento2.txt", chamado escritor2
            escritor2 = new ObjectOutputStream(
                new FileOutputStream("Financiamento2.txt"));
            for (Financiamento obj : listaDeFinanciamento) {
                escritor2.writeObject(obj);
            }
            escritor2.flush();
            escritor2.close();
        // Captura de erro de arquivos não encontrado.
        } catch (FileNotFoundException e) {
            // Informa o erro ao usuário.
            System.out.println(
                "Arquivo não encontrado! Reinicando o programa.");
        // Captura de erro IO.
        } catch (IOException e) {
            e.printStackTrace();
        } 
    } 
    // Método para realizar a leitura dos dados 
    // serializados no arquivo Financiamento2.txt
    public static void LerDadosSerializados() {
        ObjectInputStream leitor2 = null;
        try {
            // Cria uma nova saida de objeto no arquivo 
            // "Financiamento2.txt", chamado leitor2
            leitor2 = new ObjectInputStream(new FileInputStream("Financiamento2.txt"));
            while (true) {
                try {
                    Financiamento obj = (Financiamento)leitor2.readObject();
                    System.out.println(obj);
                // Captura o erro de fim de arquivo.
                } catch (EOFException e) {
                    // Caso ocorra, informa o usuário.
                    System.out.println("Fim do arquivo.");
                    break;
                } 
            } 
        // Captura o erro de Classe não encontrada.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        // Captura de erro de arquivos não encontrado.
        } catch (FileNotFoundException e) {
            // Informa o erro ao usuário.
            System.out.println(
                "Arquivo não encontrado! Reinicando o programa.");
        // Captura de erro IO.
        } catch (IOException e) {
            e.printStackTrace(); 
        } 
    } 

    public static void carregarFinanciamentosIniciais() {
        listaDeFinanciamento.add(new Casa(2000, 1, 1.0, 81, 360));
        listaDeFinanciamento.add(new Casa(2500000, 10, 1.8, 200, 800));
        listaDeFinanciamento.add(new Apartamento(300000, 20, 3.8, 1, 20));
        listaDeFinanciamento.add(new Apartamento(154000, 2, 0.8, 3, 75));
        listaDeFinanciamento.add(new Terreno(1000000, 2, 10, "Residêncial"));
    }

    // Início do main
    public static void main(String[] args) {
        InterfaceCasa interfaceCasa = new InterfaceCasa();
        InterfaceApartamento interfaceApartamento = new InterfaceApartamento();
        InterfaceTerreno interfaceTerreno = new InterfaceTerreno();
        int opcaoFinanciamento = interfaceCasa.obterTipoDeFinanciamento();
        
        Financiamento casa = (new Casa(2000, 1, 1.0, 81, 360));
        Financiamento casa2 = (new Casa(2500000, 10, 1.8, 200, 800));
        Financiamento apartamento = (new Apartamento(300000, 20, 3.8, 1, 20));
        Financiamento apartamento2 = (new Apartamento(154000,2, 0.8, 3, 75));
        Financiamento terreno = (new Terreno(1000000, 2, 10, "Residêncial"));
        FileWriter escritor = null;
        try {
            escritor = new FileWriter("financiamentos.txt");
            escritor.write(casa.toString());
            escritor.write(casa2.toString());
            escritor.write(apartamento.toString());
            escritor.write(apartamento2.toString());
            escritor.write(terreno.toString());
            // Finaliza o escritor
            escritor.flush();
            escritor.close();
        // Captura de erro de arquivos não encontrado.
        } catch (FileNotFoundException e) {
            // Informa o erro ao usuário.
            System.out.println("Arquivo não encontrado! Reinicando o programa.");
        // Captura de erro IO.
        } catch (IOException e) {
            e.printStackTrace();
        }

        carregarFinanciamentosIniciais();

        if (opcaoFinanciamento == 1) {
            // Atributos bases de Financiamento + atributos únicos de Casa.
            double valorImovel = interfaceCasa.obterValorImovel();
            int prazoFinanciamento = interfaceCasa.obterPrazoFinanciamento();
            double taxaJurosAnual = interfaceCasa.obterTaxaJuros();
            double tamanhoAreaConstruida = interfaceCasa.obterTamanhoAreaConstruida();
            double tamanhoDoTerreno = interfaceCasa.obterTamanhoDoTerreno();
            // Adiciona uma nova casa na listaDeFinanciamento.
            interfaceCasa.adicionarALista(valorImovel, prazoFinanciamento, taxaJurosAnual, tamanhoAreaConstruida, tamanhoDoTerreno);

            // Salvamento  e leitura de dados
            // Instância uma nova casa, atraves das informações dadas pelo úsuario.
            Financiamento casa3 = new Casa(valorImovel, prazoFinanciamento, taxaJurosAnual, prazoFinanciamento, taxaJurosAnual);
            //Instância o escrito e define como nulo.
            try {
                // Define o escritor com arquivo "financiamentos.txt", e não permite que ele sobrescreva as informações já existente.
                escritor = new FileWriter("financiamentos.txt", true);
                escritor.write(casa3.toString());
                // Finaliza o escritor
                escritor.flush();
                escritor.close();
            // Captura de erro de arquivos não encontrado.
            } catch (FileNotFoundException e) {
                // Informa o erro ao usuário.
                System.out.println("Arquivo não encontrado! Reinicando o programa.");
            // Captura de erro IO.
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("\n=========Financiamentos:=========\n");
            // Salvamento e leitura da listaDeFinanciamento, Serializado.
            // Serialização das informações da lista de Financiamento
            EscreverDadosSerializados();
            // Leitura da listaDeFinanciamento serializado.
            LerDadosSerializados();

            // Imprime o valor total dos imovéis e financiamento.
            Financiamento.imprimirValores();

        // Se o úsuario escolhe a opção 3 inicia o código do Apartamento.
        } else if (opcaoFinanciamento == 2) {
            // Atributos bases de Financiamento + atributos únicos de Apartamento.
            double valorImovel = interfaceApartamento.obterValorImovel();
            int prazoFinanciamento = interfaceApartamento.obterPrazoFinanciamento();
            double taxaJurosAnual = interfaceApartamento.obterTaxaJuros();
            int numeroDeVagasGaragem = interfaceApartamento.obterNumeroDeVagasGaragem();
            int numeroDoAndar = interfaceApartamento.obterNumeroDoAndar();
            // Adiciona um novo apartamento na listaDeFinanciamento.
            interfaceApartamento.adicionarALista(valorImovel, prazoFinanciamento, taxaJurosAnual, numeroDeVagasGaragem, numeroDoAndar);

            
            // Instância um novo apartamento, atraves das informações dadas pelo úsuario.
            Financiamento apartamento3 = new Apartamento(valorImovel, prazoFinanciamento, taxaJurosAnual, numeroDeVagasGaragem, numeroDoAndar);
            //Instância o escrito e define como nulo.
            try {
                // Define o escritor com arquivo "financiamentos.txt", e não permite que ele sobrescreva as informações já existente.
                escritor = new FileWriter("financiamentos.txt", true);
                escritor.write(apartamento3.toString());
                // Finaliza o escritor
                escritor.flush();
                escritor.close();
            // Captura de erro de arquivos não encontrado.
            } catch (FileNotFoundException e) {
                // Informa o erro ao usuário.
                System.out.println("Arquivo não encontrado! Reinicando o programa.");
            // Captura de erro IO.
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("\n=========Financiamentos:=========\n");
            // Salvamento e leitura da listaDeFinanciamento, Serializado.
            // Serialização das informações da lista de Financiamento
            EscreverDadosSerializados();
            // Leitura da listaDeFinanciamento serializado.
            LerDadosSerializados();
            // Imprime o valor total dos imovéis e financiamento.
            Financiamento.imprimirValores();

            // Se o úsuario escolhe a opção 3 inicia o código do Terreno.
        } else if (opcaoFinanciamento == 3) {
            // Atributos bases de Financiamento + atributos únicos de Terreno
            double valorImovel = interfaceTerreno.obterValorImovel();
            int prazoFinanciamento = interfaceTerreno.obterPrazoFinanciamento();
            double taxaJurosAnual = interfaceTerreno.obterTaxaJuros();
            String tipoZoneamento = interfaceTerreno.obterTipoDeZoneamento();
            // Adiciona um novo terreno na listaDeFinanciamento.
            interfaceTerreno.adicionarALista(valorImovel, prazoFinanciamento, taxaJurosAnual, tipoZoneamento);

            // Instância um novo terreno, atraves das informações dadas pelo úsuario.
            Financiamento terreno2 = new Terreno(valorImovel, prazoFinanciamento, taxaJurosAnual, tipoZoneamento);
            // Instância o escritor.
            try {
                // Define o escritor com arquivo "financiamentos.txt", e não permite que ele sobrescreva as informações já existente.
                escritor = new FileWriter("financiamentos.txt", true);
                escritor.write(terreno2.toString());
                // Finaliza o escritor
                escritor.flush();
                escritor.close();
            // Captura de erro de arquivos não encontrado.
            } catch (FileNotFoundException e) {
                // Informa o erro ao usuário.
                System.out.println("Arquivo não encontrado! Reinicando o programa.");
            // Captura de erro IO.
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("\n=========Financiamentos:=========\n");
            // Salvamento e leitura da listaDeFinanciamento, Serializado.
            // Serialização das informações da lista de Financiamento
            EscreverDadosSerializados();
            // Leitura da listaDeFinanciamento serializado.
            LerDadosSerializados();
            // Imprime o valor total dos imovéis e financiamento.
            Financiamento.imprimirValores();
        }
    }
}
