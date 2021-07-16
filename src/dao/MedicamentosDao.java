package dao;
import beans.Higiene;
import beans.Medicamento;
public class MedicamentosDao {

    private Medicamento[] db_medicamentos;
    private int qtdProdMedicamentoGuardados;
    int busca;

    public void inicializaVetor(){
        db_medicamentos = new Medicamento[15];
    }
    public void persisteMedicamentos(Medicamento medicamento){
        db_medicamentos[qtdProdMedicamentoGuardados] = medicamento;
        qtdProdMedicamentoGuardados++;
    }
    public boolean verificaCodigo(int codigo){
        for (int i=0; i<db_medicamentos.length; i++) {
            if (db_medicamentos[i] != null) {
                if (db_medicamentos[i].getCodigo() == codigo) {
                    return false;
                }
            }
        }
        return true;
    }
    public double calculaLucro(double precoCompra, double percelntualLucro){
        double percentual = percelntualLucro/100;
        double lucro = precoCompra * percentual;
        return lucro;
    }

    public Higiene buscaProdutoMaisCaro(){
        double maior = 0;
        int indice = 0;
        for (int i=0; i<db_medicamentos.length;){
            if (db_medicamentos[i]!= null) {
                if (db_medicamentos[i].getPrecoCompra() > maior) {
                    maior = db_medicamentos[i].getPrecoCompra();
                    indice = i;
                }
            }
            i++;
        }
        return db_medicamentos[indice];
    }
    public void relatorio(){
        for (int i=0; i<db_medicamentos.length;i++){
            if (db_medicamentos[i] != null) {
                System.out.println(db_medicamentos[i]);
            }
        }
    }
    public void alterar(int codigo, String descricao, double precoCompra, double percentualLucro, String dataVencimento) {
        Medicamento alterar = new Medicamento(codigo, descricao, precoCompra, percentualLucro, dataVencimento);
        db_medicamentos[busca] = alterar;
    }
    public Higiene busca(int codigo){
        for (busca=0; busca<db_medicamentos.length; busca++){
            if (db_medicamentos[busca] != null) {
                if (db_medicamentos[busca].getCodigo() == codigo && db_medicamentos[busca] != null) {
                    return db_medicamentos[busca];
                }
            }
        }
        return null;
    }
    public void exclui(int codigo){
        for (int i = 0; i< db_medicamentos.length; i++){
            if (db_medicamentos[i] != null) {
                if (db_medicamentos[i].getCodigo() == codigo) {
                    db_medicamentos[i] = null;
                }
            }
        }
    }
}
