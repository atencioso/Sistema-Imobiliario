//Nome: Eduardo Cardoso Camargo
//Curso: Inteligência Artificial Aplicada




package financiamento;

public class Terreno extends FinanciamentoImovel {
    private static final long serialVersionUID = 1L;
    private static final double ACRESCIMO_INADIMPLENCIA = 1.02;
    private final String tipoZona;

    public Terreno(double valorImovel, int prazoAnos, double taxaJurosAnual, String tipoZona) {
        super(valorImovel, prazoAnos, taxaJurosAnual);
        this.tipoZona = tipoZona;
    }

    public String getTipoZona() {
        return tipoZona;
    }

    @Override
    public double calcularPagamentoMensal() {
        double prazoMeses = getPrazoAnos() * 12;
        double financiamentoBase = (getValorImovel() / prazoMeses) * (1 + (getTaxaJurosAnual() / 100 / 12));
        return financiamentoBase * ACRESCIMO_INADIMPLENCIA;
    }

    @Override
    public void exibirDadosFinanciamento() {
        System.out.printf("Tipo de Imóvel: Terreno%n");
        System.out.printf("Valor do Imóvel: R$ %.2f%n", getValorImovel());
        System.out.printf("Prazo do Financiamento: %d anos%n", getPrazoAnos());
        System.out.printf("Taxa de Juros Anual: %.2f%%%n", getTaxaJurosAnual());
        System.out.printf("Tipo de Zona: %s%n", tipoZona);
        System.out.printf("Pagamento Mensal: R$ %.2f%n", calcularPagamentoMensal());
        System.out.printf("Total do Pagamento: R$ %.2f%n", calcularTotalPagamento());
        System.out.println("--------------------------------------------");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Tipo de Zona: %s", tipoZona);
    }
}

