package beans;
public class Higiene {

    private int codigo = 0;
    private String descricao = "";
    private double precoCompra = 0;
    private double percentualLucro = 0;

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getPrecoCompra() {
        return precoCompra;
    }
    public void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }
    public double getPercentualLucro() {
        return percentualLucro;
    }
    public void setPercentualLucro(double percentualLucro) {
        this.percentualLucro = percentualLucro;
    }
    public Higiene() {
    }
    public Higiene(int codigo, String descricao, double precoCompra, double percentualLucro) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.precoCompra = precoCompra;
        this.percentualLucro = percentualLucro;
    }
    public String toString() {
        return "Higiene{" +
                "codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                ", precoCompra=" + precoCompra +
                ", percentualLucro=" + percentualLucro +
                '}';
    }
}
