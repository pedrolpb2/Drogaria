package dao;
import beans.Medicamento;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicamentosDao {


    public void cadastrar(Medicamento medicamento){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO medicamento (codigo,descricao,precocompra,percentuallucro,datavencimento)VALUES(?,?,?,?,?)");
            stmt.setInt(1, medicamento.getCodigo());
            stmt.setString(2, medicamento.getDescricao());
            stmt.setDouble(3, medicamento.getPrecoCompra());
            stmt.setDouble(4, medicamento.getPercentualLucro());
            stmt.setString(5, medicamento.getDataVencimento());
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso");
        }catch (SQLIntegrityConstraintViolationException e){
            throw new RuntimeException("PRODUTO NÃO CADASTRADO POIS JÁ EXISTE OUTRO PRODUTO CADASTRADO COM O CÓDIGO INFORMADO!!!");
        } catch (SQLException ex){
            throw new RuntimeException("Erro ao salvar: ",ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Medicamento> listar(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Medicamento> medicamentos = new ArrayList<Medicamento>();
        try {
            stmt = con.prepareStatement("SELECT * FROM medicamento");
            rs = stmt.executeQuery();
            while (rs.next()){
                Medicamento medicamento = new Medicamento();
                medicamento.setCodigo(rs.getInt("codigo"));
                medicamento.setDescricao(rs.getString("descricao"));
                medicamento.setPrecoCompra(rs.getDouble("precocompra"));
                medicamento.setPercentualLucro(rs.getDouble("percentuallucro"));
                medicamento.setDataVencimento(rs.getString("datavencimento"));
                medicamentos.add(medicamento);
            }
        }catch (SQLException ex){
            throw new RuntimeException("Erro ao listar: ",ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    return medicamentos;
    }

    public void atualizar(Medicamento medicamento, int codigo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE medicamento SET codigo=?,descricao=?,precocompra=?,percentuallucro=?, datavencimento=? WHERE codigo = ?");
            stmt.setInt(1, medicamento.getCodigo());
            stmt.setString(2, medicamento.getDescricao());
            stmt.setDouble(3, medicamento.getPrecoCompra());
            stmt.setDouble(4, medicamento.getPercentualLucro());
            stmt.setString(5, medicamento.getDataVencimento());
            stmt.setInt(6,codigo);
            stmt.executeUpdate();
        }catch (SQLException ex){
            throw new RuntimeException("Erro ao atualizar: ",ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public Medicamento busca(int codigo){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.prepareStatement("SELECT * FROM dbdrogaria.medicamento WHERE codigo = ?");
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            if (rs.next()){
                Medicamento medicamento = new Medicamento();
                medicamento.setCodigo(rs.getInt("codigo"));
                medicamento.setDescricao(rs.getString("descricao"));
                medicamento.setPrecoCompra(rs.getDouble("precocompra"));
                medicamento.setPercentualLucro(rs.getDouble("percentuallucro"));
                medicamento.setDataVencimento(rs.getString("datavencimento"));
                return medicamento;
            }else {
                return null;
            }
        }catch (SQLException ex){
            throw new RuntimeException("Erro ao buscar: ",ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public void excluir(int codigo){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM medicamento WHERE codigo = ?");
            stmt.setInt(1, codigo);
            stmt.execute();
        }catch (SQLException ex){
            throw new RuntimeException("Erro ao exlcuir: ", ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public double calculaLucro(double precoCompra, double percelntualLucro){
        double percentual = percelntualLucro/100;
        double lucro = precoCompra * percentual;
        return lucro;
    }

}
