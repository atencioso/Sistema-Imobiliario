//Nome: Eduardo Cardoso Camargo
//Curso: Inteligência Artificial Aplicada



package util;

import java.util.Scanner;

public class InterfaceUsuario {
    private Scanner scanner;

    public InterfaceUsuario() {
        this.scanner = new Scanner(System.in);
    }

    public double solicitarValorImovel() { // Valor do imóvel
        double valor;
        do {
            System.out.print("Digite o valor do imóvel (positivo): ");
            valor = scanner.nextDouble();
            if (valor <= 0) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        } while (valor <= 0);
        return valor;
    }

    public int solicitarPrazoAnos() {
        int prazo;
        boolean confirmar = false;
        do {
            System.out.print("Digite o prazo do financiamento (em anos, positivo): "); // Pede o prazo de financiamento
            prazo = scanner.nextInt();
            if (prazo <= 0) {
                System.out.println("Prazo inválido. Tente novamente.");
            } else if (prazo > 75) {
                scanner.nextLine();  // Consome o \n deixado pelo nextInt()
                System.out.println("O prazo de vida média do ser humano é de 75 anos, tem certeza que deseja fazer um financiamento de " + prazo + " anos? Clique Enter para continuar ou digite 'voltar' para digitar novamente."); //Defini o prazo para caso o usuário digite um valor acima de 75 ele questionar
                String resposta = scanner.nextLine();
                if (!resposta.equalsIgnoreCase("voltar")) {
                    confirmar = true;
                }
            } else {
                confirmar = true;
            }
        } while (!confirmar);
        return prazo;
    }

    public double solicitarTaxaJurosAnual() { //Taxa de juros anual
        double taxa;
        do {
            System.out.print("Digite a taxa de juros anual (em %, positivo): ");
            taxa = scanner.nextDouble();
            if (taxa <= 0) { // Limitação da taxa de juros
                System.out.println("Taxa inválida. Tente novamente.");
            }
        } while (taxa <= 0);
        return taxa;
    }

    public String solicitarTipoImovel() {
        String tipoImovel;
        do {
            System.out.print("Escolha o tipo de imóvel que deseja financiar (casa, apartamento, terreno): "); // Tipo de imóvel
            tipoImovel = scanner.next().toLowerCase();
            if (!tipoImovel.equals("casa") && !tipoImovel.equals("apartamento") && !tipoImovel.equals("terreno")) {
                System.out.println("Tipo de imóvel inválido. Tente novamente.");
            }
        } while (!tipoImovel.equals("casa") && !tipoImovel.equals("apartamento") && !tipoImovel.equals("terreno"));
        return tipoImovel;
    }

    public double solicitarAreaConstruida() {
        double area;
        do {
            System.out.print("Digite a área construída do imóvel (em m²): "); // Área Construída
            area = scanner.nextDouble();
            if (area <= 0) { // Limitação da área
                System.out.println("Área inválida. Tente novamente.");
            }
        } while (area <= 0);
        return area;
    }

    public double solicitarTamanhoTerreno() {
        double terreno;
        do {
            System.out.print("Digite o tamanho do terreno do imóvel (em m²): "); // Tamanho do terreno
            terreno = scanner.nextDouble();
            if (terreno <= 0) { // Limitação do tamanho
                System.out.println("Tamanho do terreno inválido. Tente novamente.");
            }
        } while (terreno <= 0);
        return terreno;
    }

    public int solicitarNumeroVagasGaragem() {
        int vagas;
        do {
            System.out.print("Digite o número de vagas na garagem do apartamento: ");
            vagas = scanner.nextInt();
            if (vagas <= 0) { // Limitação do número de garagem
                System.out.println("Número de vagas inválido. Tente novamente.");
            }
        } while (vagas <= 0); 
        return vagas;
    }

    public int solicitarNumerarAndar() {
        int andar;
        do {
            System.out.print("Digite o número do andar do apartamento: "); //Andar
            andar = scanner.nextInt();
            if (andar <= 0) {
                System.out.println("Número do andar inválido. Tente novamente."); //Caso seja inválido
            }
        } while (andar <= 0);
        return andar;
    }

    public String solicitarTipoZona() {
        System.out.print("Digite o tipo da zona do terreno (residencial, comercial): "); //Zona
        return scanner.next();
    }

    public Scanner getScanner() {
        return scanner;
    }
}
