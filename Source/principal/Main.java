package principal;

import financiamento.FinanciamentoImovel;
import financiamento.Casa;
import financiamento.Apartamento;
import financiamento.Terreno;
import util.DescontoMaiorDoQueJurosException;
import util.InterfaceUsuario;
import java.io.*;
import java.text.NumberFormat; // Formatação dos valores
import java.util.ArrayList; // Array
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        InterfaceUsuario entradaDeDados = new InterfaceUsuario();
        ArrayList<FinanciamentoImovel> financiamentos = new ArrayList<>(); // Array list
        boolean continuar = true;
        Locale localeBR = Locale.of("pt", "BR");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeBR); // Formatei os valores aqui pra facilitar pois fica muito ruim trabalhar com 500.000 por exemplo

        while (continuar && financiamentos.size() < 4) {
            boolean confirmar = false;
            while (!confirmar) {
                System.out.println("Informações sobre o Financiamento (limite de 4 financiamentos):");
                String tipoImovel = entradaDeDados.solicitarTipoImovel(); //Solicito o tipo do imóvel

                double valorImovel = entradaDeDados.solicitarValorImovel(); //Solicito o valor do imóvel
                String valorImovelFormatado = currencyFormatter.format(valorImovel); // Em seguida já faço a formatação necessária 

                int prazoAnos = entradaDeDados.solicitarPrazoAnos();
                double taxaJurosAnual = entradaDeDados.solicitarTaxaJurosAnual();

                double desconto = 0;
                if (tipoImovel.equalsIgnoreCase("casa")) {
                    boolean descontoValido = false; // Desconto válido?
                    System.out.println("Digite o valor do desconto (ou 0 se não houver desconto) e pressione Enter:"); // Solicita o valor do desconto
                    while (!descontoValido) {
                        String descontoInput = entradaDeDados.getScanner().nextLine();
                        if (!descontoInput.isEmpty()) {
                            try {
                                desconto = Double.parseDouble(descontoInput);
                                if (desconto > taxaJurosAnual) {
                                    System.out.println("Erro: O desconto não pode ser maior que a taxa de juros anual, digite novamente abaixo:");
                                } else {
                                    descontoValido = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Valor de desconto inválido. Por favor, digite um número válido."); // Caso não seja válido ele pede novamente
                            }
                        }
                    }
                }

                System.out.println("Confirme as informações:");
                System.out.println("Tipo de Imóvel: " + tipoImovel);
                System.out.println("Valor do Imóvel: " + valorImovelFormatado);
                System.out.println("Prazo do Financiamento: " + prazoAnos + " anos");
                System.out.println("Taxa de Juros Anual: " + taxaJurosAnual + "%");
                if (tipoImovel.equalsIgnoreCase("casa")) {
                    System.out.println("Desconto: " + desconto + "%");
                }

                System.out.println("As informações de valores estão corretas? (Digite 'voltar' para refazer ou pressione Enter duas vezes para continuar)");
                entradaDeDados.getScanner().nextLine();  // Consome o \n deixado pelo next()
                String resposta = entradaDeDados.getScanner().nextLine();
                if (!resposta.equalsIgnoreCase("voltar")) {
                    confirmar = true;
                    FinanciamentoImovel financiamento;

                    switch (tipoImovel.toLowerCase()) {
                        case "casa":
                            double areaConstruida = entradaDeDados.solicitarAreaConstruida(); // Solicitei os dados da área
                            double tamanhoTerreno = entradaDeDados.solicitarTamanhoTerreno(); //Solicitei os dados do terreno (no caso em ambos os dados não pedi alteração pois eles não estão influenciando na fórmula pra cálculo)
                            financiamento = new Casa(valorImovel, prazoAnos, taxaJurosAnual, areaConstruida, tamanhoTerreno);
                            try {
                                ((Casa) financiamento).aplicarDesconto(desconto);
                            } catch (DescontoMaiorDoQueJurosException e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                            break;
                        case "apartamento":
                            int numVagasGaragem = entradaDeDados.solicitarNumeroVagasGaragem(); // Solicito o número de vagas que a garagem vai ter
                            int numAndar = entradaDeDados.solicitarNumerarAndar(); // Solicito em que andar vai ficar (não limitei pois vai que o indivíduo quer fazer um financiamento para o burj khalifa)
                            financiamento = new Apartamento(valorImovel, prazoAnos, taxaJurosAnual, numVagasGaragem, numAndar);
                            break;
                        case "terreno":
                            String tipoZona = entradaDeDados.solicitarTipoZona(); // Zona (solicitei)
                            financiamento = new Terreno(valorImovel, prazoAnos, taxaJurosAnual, tipoZona);
                            break;
                        default:
                            System.out.println("Tipo de imóvel não reconhecido."); // Caso o usuário não coloque certo o imóvel
                            return;
                    }

                    financiamentos.add(financiamento);
                }
            }

            // Limpa o console pra deixar mais clean
            try {
                if (System.getProperty("os.name").startsWith("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
            } catch (Exception e) {
                // Tratamento de exceção
            }

            // Exibe os resultados finais
            double totalValorImoveis = 0;
            double totalValorFinanciamentos = 0;

            for (FinanciamentoImovel financiamento : financiamentos) {
                financiamento.exibirDadosFinanciamento();
                totalValorImoveis += financiamento.getValorImovel();
                totalValorFinanciamentos += financiamento.calcularTotalPagamento();
            }

            System.out.printf("Total de todos os imóveis: %s%n", currencyFormatter.format(totalValorImoveis));
            System.out.printf("Total de todos os financiamentos: %s%n", currencyFormatter.format(totalValorFinanciamentos));

            // Pergunta ao usuário se deseja simular outro financiamento
            System.out.println("Deseja simular outro financiamento? Se sim, digite 'c'para continuar, ou qualquer outra tecla para finalizar."); // No caso aqui achei o v bem intuitivo aí preferi
            entradaDeDados.getScanner().nextLine();  // Consome o \n deixado pelo next()
            String resposta = entradaDeDados.getScanner().nextLine();

            if (!resposta.equalsIgnoreCase("c")) {
                continuar = false;
            }
        }

        // Salva os dados no arquivo financiamentos (achei o nome fácil de achar)
        salvarDadosEmArquivoTexto(financiamentos, "financiamentos.txt");

        // Lê os dados do arquivo de texto
        System.out.println("\nDados lidos do arquivo de texto:");
        lerDadosDeArquivoTexto("financiamentos.txt");

        // Aqui eu salvo os dados serializados
        salvarDadosSerializados(financiamentos, "financiamentos.ser");

        // Leio os dados serializazdos
        System.out.println("\nDados lidos do arquivo serializado:");
        ArrayList<FinanciamentoImovel> financiamentosLidos = lerDadosSerializados("financiamentos.ser");
        for (FinanciamentoImovel financiamentoLido : financiamentosLidos) {
            financiamentoLido.exibirDadosFinanciamento();
        }
    }

    public static void salvarDadosEmArquivoTexto(ArrayList<FinanciamentoImovel> financiamentos, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (FinanciamentoImovel financiamento : financiamentos) {
                writer.write(financiamento.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados em arquivo de texto: " + e.getMessage());
        }
    }
         //Caso dê algum erro, já fiz um point pra explicar
    public static void lerDadosDeArquivoTexto(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler os dados do arquivo de texto: " + e.getMessage());
        }
    }
         //Caso dê algum erro, já fiz um point pra explicar
    public static void salvarDadosSerializados(ArrayList<FinanciamentoImovel> financiamentos, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(financiamentos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados serializados: " + e.getMessage());
        }
    }
        //Caso dê algum erro, já fiz um point pra explicar
    @SuppressWarnings("unchecked")
    public static ArrayList<FinanciamentoImovel> lerDadosSerializados(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (ArrayList<FinanciamentoImovel>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao ler os dados serializados: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
