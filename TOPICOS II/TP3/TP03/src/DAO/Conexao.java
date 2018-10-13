package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import utilitarios.Arquivo;

public class Conexao {

    private static java.sql.Connection con = null;
    private static String nomeDB;
    private static String url;
    private static String driver = "com.mysql.jdbc.Driver";
    private static String user;
    private static String password;

    public Conexao() {
        conectar();
    }

    private boolean configura() {
        Arquivo arq = new Arquivo();
        try {
            ArrayList<String> texto = arq.lerArquivo("config.txt");

            for (int i = 0; i < texto.size(); i++) {
                System.out.println(texto.get(i));
            }

            url = texto.get(0);
            nomeDB = texto.get(1);
            url += nomeDB;
            driver = texto.get(2);
            user = texto.get(3);
            password = texto.get(4);
            return true;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo de configurações!", "Erro de configuração", 0);
            return false;
        }
    }

    public java.sql.Connection conectar() {
        
        try {

            if (con != null) {
                if (!con.isClosed()) {
                    //System.out.println("JÁ CONECTADO");
                    return con;
                }
            }
            System.out.println("Tentando conectar...");
            if (!configura()) {
                System.err.println("ERRO ao conectar ao banco");
                return null;
            }

            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado!");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Não foi possível encontrar o Driver!");
            JOptionPane.showMessageDialog(null, "Não foi possível encontrar o Driver!", "Erro ao conectar com o Banco de Dados", 0);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Não foi possível conectar ao banco!");
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco!", "Erro ao conectar com o Banco de Dados", 0);
        }
        return con;
    }

    public void desconectar() {
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getCon() {
        return con;
    }

    public static String getNomeDB() {
        return nomeDB;
    }
    
}
