package dao;
import beans.Higiene;
public class HigieneDao {

    private Higiene[] db_higiene;
    private int qtdProdHigieneGuardados = 0;
    int busca;

    public void inicializaVetor() {
        db_higiene = new Higiene[15];
    }
    public void persisteHigiene(Higiene higiene) {
        db_higiene[qtdProdHigieneGuardados] = higiene;
        qtdProdHigieneGuardados++;
    }
    public boolean verificaCodigo(int codigo) {
        for (int i = 0; i < db_higiene.length; i++) {
            if (db_higiene[i] != null) {
                if (db_higiene[i].getCodigo() == codigo) {
                    return false;
                }
            }
        }
        return true;
    }
    public double calculaLucro(double precoCompra, double percentualLucro) {
        double percentual = percentualLucro / 100;
        double lucro = precoCompra * percentual;
        return lucro;
    }
    public Higiene buscaProdutoMaisCaro() {
        double maior = 0;
        int indice = 0;
        for (int i = 0; i < db_higiene.length; ) {
            if (db_higiene[i] != null) {
                if (db_higiene[i].getPrecoCompra() > maior) {
                    maior = db_higiene[i].getPrecoCompra();
                    indice = i;
                }
            }
            i++;
        }
        return db_higiene[indice];
    }
    public void relatorio() {
        for (int i = 0; i < db_higiene.length; i++) {
            if (db_higiene[i] != null) {
                System.out.println(db_higiene[i]);
            }
        }
    }
    public void alterar(int codigo, String descricao, double precoCompra, double percentualLucro) {
        Higiene alterar = new Higiene(codigo, descricao, precoCompra, percentualLucro);
        busca(codigo);
        db_higiene[busca] = alterar;
    }
    public Higiene busca(int codigo){
        for (busca=0; busca<db_higiene.length; busca++){
            if (db_higiene[busca] != null) {
                if (db_higiene[busca].getCodigo() == codigo && db_higiene[busca] != null) {
                    return db_higiene[busca];
                }
            }
        }
        return null;
    }
    public void exclui(int codigo){
        for (int i = 0; i< db_higiene.length; i++){
            if (db_higiene[i] != null) {
                if (db_higiene[i].getCodigo() == codigo) {
                    db_higiene[i] = null;
                }
            }
        }
    }
}
