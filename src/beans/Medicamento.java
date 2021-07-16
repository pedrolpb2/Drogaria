package beans;
public class Medicamento extends Higiene {

    private String dataVencimento = "";

    public String getDataVencimento() {
        return dataVencimento;
    }
    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    public Medicamento() {
    }
    public Medicamento(int codigo, String descricao, double precoCompra, double percentualLucro, String dataVencimento) {
        super(codigo, descricao, precoCompra, percentualLucro);
        this.dataVencimento = dataVencimento;
    }
    public String toString() {
        return "Medicamento{" +
                "codigo=" + getCodigo() +
                ", descricao='" + getDescricao() + '\'' +
                ", precoCompra=" + getPrecoCompra() +
                ", percentualLucro=" + getPercentualLucro() +
                ", dataVencimento=" + getDataVencimento() +
                '}';
    }
}
