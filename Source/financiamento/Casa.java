//Nome: Eduardo Cardoso Camargo
//Curso: Inteligência Artificial Aplicada


package financiamento;

import util.DescontoMaiorDoQueJurosException;

public class Casa extends FinanciamentoImovel {
    private static final long serialVersionUID = 1L;
    private static final double TAXA_SEGURO = 80;
    private final double areaConstruida;
    private final double tamanhoTerreno;

    public Casa(double valorImovel, int prazoAnos, double taxaJurosAnual, double areaConstruida, double tamanhoTerreno) {
        super(valorImovel, prazoAnos, taxaJurosAnual);
        this.areaConstruida = areaConstruida;
        this.tamanhoTerreno = tamanhoTerreno;
    }

    public double getAreaConstruida() {
        return areaConstruida;
    }

    public double getTamanhoTerreno() {
        return tamanhoTerreno;
    }

    @Override
    public double calcularPagamentoMensal() {
        double prazoMeses = getPrazoAnos() * 12;
        double financiamentoBase = (getValorImovel() / prazoMeses) * (1 + (getTaxaJurosAnual() / 100 / 12));
        return financiamentoBase + TAXA_SEGURO;
    }

    public void aplicarDesconto(double desconto) throws DescontoMaiorDoQueJurosException {
        double jurosMensais = (getValorImovel() * getTaxaJurosAnual() / 100) / 12;
        if (desconto > jurosMensais) {
            throw new DescontoMaiorDoQueJurosException("O valor do desconto não pode ser maior do que os juros mensais.");
        }
        // lógica para aplicar o desconto
        System.out.printf("Desconto de R$ %.2f aplicado com sucesso.%n", desconto);
    }

    @Override
    public void exibirDadosFinanciamento() {
        System.out.printf("Tipo de Imóvel: Casa%n");
        System.out.printf("Valor do Imóvel: R$ %.2f%n", getValorImovel());
        System.out.printf("Prazo do Financiamento: %d anos%n", getPrazoAnos());
        System.out.printf("Taxa de Juros Anual: %.2f%%%n", getTaxaJurosAnual());
        System.out.printf("Área Construída: %.2f m²%n", areaConstruida);
        System.out.printf("Tamanho do Terreno: %.2f m²%n", tamanhoTerreno);
        System.out.printf("Pagamento Mensal: R$ %.2f%n", calcularPagamentoMensal());
        System.out.printf("Total do Pagamento: R$ %.2f%n", calcularTotalPagamento());
        System.out.println("--------------------------------------------");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Área Construída: %.2f m², Tamanho do Terreno: %.2f m²",
                areaConstruida, tamanhoTerreno);
    }
}
