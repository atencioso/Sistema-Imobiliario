//Nome: Eduardo Cardoso Camargo
//Curso: Inteligência Artificial Aplicada



package financiamento;

import java.io.Serializable;

public abstract class FinanciamentoImovel implements Serializable {
    private static final long serialVersionUID = 1L;
    private final double valorImovel;
    private final int prazoAnos;
    private final double taxaJurosAnual;

    public FinanciamentoImovel(double valorImovel, int prazoAnos, double taxaJurosAnual) {
        this.valorImovel = valorImovel;
        this.prazoAnos = prazoAnos;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    public double getValorImovel() {
        return valorImovel;
    }

    public int getPrazoAnos() {
        return prazoAnos;
    }

    public double getTaxaJurosAnual() {
        return taxaJurosAnual;
    }

    public double calcularTotalPagamento() {
        return calcularPagamentoMensal() * prazoAnos * 12;
    }

    public abstract double calcularPagamentoMensal();

    public abstract void exibirDadosFinanciamento();

    @Override
    public String toString() {
        return String.format("Valor do Imovel: R$ %.2f, Prazo do Financiamento: %d anos, Taxa de Juros Anual: %.2f%%",
                valorImovel, prazoAnos, taxaJurosAnual);
    }
}
