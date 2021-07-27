package util;

import java.sql.*;

public class ConnectionFactory {

    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/dbdrogaria";
    private final static String USER = "root";
    private final static String PASS = "@#Madruguinha!$2021";

    public static Connection getConnection(){
       try {
           Class.forName(DRIVER);
           return DriverManager.getConnection(URL, USER, PASS);
       }catch (ClassNotFoundException | SQLException ex){
           throw new RuntimeException("erro ao obter conexão: ", ex);
       }
    }
    public static void closeConnection(Connection con){
        try {
            if (con != null){
                con.close();
            }
        }catch (SQLException ex){
            throw new RuntimeException("Erro ao fechar conexão: ",ex);
        }
    }
    public static void closeConnection(Connection con, PreparedStatement stmt){
       closeConnection(con);

        try {
            if (stmt != null){
                stmt.close();
            }
        }catch (SQLException ex){
            throw new RuntimeException("Erro ao fechar conexão: ",ex);
        }
    }
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        closeConnection(con, stmt);

        try {
            if (rs != null){
                rs.close();
            }
        }catch (SQLException ex){
            throw new RuntimeException("Erro ao fechar conexão: ",ex);
        }
    }

}
