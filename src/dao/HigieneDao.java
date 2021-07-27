package dao;
import beans.Higiene;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HigieneDao {

    public void cadastrar(Higiene higiene) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO higiene (codigo,descricao,precocompra,percentuallucro)VALUES(?,?,?,?)");
            stmt.setInt(1, higiene.getCodigo());
            stmt.setString(2, higiene.getDescricao());
            stmt.setDouble(3, higiene.getPrecoCompra());
            stmt.setDouble(4, higiene.getPercentualLucro());
            stmt.executeUpdate();
            System.out.println("Salvo com sucesso");
        } catch (SQLIntegrityConstraintViolationException e){
            throw new RuntimeException("PRODUTO NÃO CADASTRADO POIS JÁ EXISTE OUTRO PRODUTO CADASTRADO COM O CÓDIGO INFORMADO!!!");
        } catch (SQLException ex){
            throw new RuntimeException("Erro ao salvar: ",ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Higiene> listar(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Higiene> higienes = new ArrayList<Higiene>();
        try {
            stmt = con.prepareStatement("SELECT * FROM higiene");
            rs = stmt.executeQuery();
            while (rs.next()){
                Higiene higiene = new Higiene();
                higiene.setCodigo(rs.getInt("codigo"));
                higiene.setDescricao(rs.getString("descricao"));
                higiene.setPrecoCompra(rs.getDouble("precocompra"));
                higiene.setPercentualLucro(rs.getDouble("percentuallucro"));
                higienes.add(higiene);
            }
        }catch (SQLException ex){
            throw new RuntimeException("Erro ao listar: ",ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    return higienes;
    }

    public void atualizar(Higiene higiene, int codigo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE higiene SET codigo=?,descricao=?,precocompra=?,percentuallucro=? WHERE codigo = ?");
            stmt.setInt(1, higiene.getCodigo());
            stmt.setString(2, higiene.getDescricao());
            stmt.setDouble(3, higiene.getPrecoCompra());
            stmt.setDouble(4, higiene.getPercentualLucro());
            stmt.setInt(5,codigo );
            stmt.executeUpdate();
        }catch (SQLException ex){
            throw new RuntimeException("Erro ao atualizar: ",ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public Higiene busca(int codigo){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = con.prepareStatement("SELECT * FROM dbdrogaria.higiene WHERE codigo = ?");
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            if (rs.next()){
                Higiene higiene = new Higiene();
                higiene.setCodigo(rs.getInt("codigo"));
                higiene.setDescricao(rs.getString("descricao"));
                higiene.setPrecoCompra(rs.getDouble("precocompra"));
                higiene.setPercentualLucro(rs.getDouble("percentuallucro"));
                return higiene;
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
            stmt = con.prepareStatement("DELETE FROM higiene WHERE codigo = ?");
            stmt.setInt(1, codigo);
            stmt.execute();
        }catch (SQLException ex){
            throw new RuntimeException("Erro ao exlcuir: ", ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public double calculaLucro(double precoCompra, double percentualLucro) {
        double percentual = percentualLucro / 100;
        double lucro = precoCompra * percentual;
        return lucro;
    }

}
