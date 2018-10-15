
package DAO;

import java.sql.Statement;
import modelo.ClasseSeguranca;
import modelo.Usuario;

public class UsuarioDAO {
    private Conexao conexao;
    private Statement s;
    private String nomeTabela = "usuariobd";
    

    public UsuarioDAO() {
        this.conexao = new Conexao();
    }
    
    public Usuario insereUsuario(String nome, String senha, ClasseSeguranca classeSeguranca){
        String sql;
        try {
            sql = "INSERT INTO `"+conexao.getNomeDB()+"`.`"+nomeTabela+"` (`user`, `senha`, `nivel`) VALUES ("
                    + "'"+nome+"', "
                    + "'"+senha+"', "
                    + "'"+classeSeguranca.getNum()+"');";

            System.out.println(sql);
            s = conexao.conectar().createStatement();
            s.executeUpdate(sql);
            
        } catch (Exception ex) {
            System.err.println("ERRO CADASTRAR USUARIO");
            return null;
        }
        return new Usuario(nome, senha, classeSeguranca);
    }
    
    public Usuario atualizaUsuario(String nome, String senha, ClasseSeguranca classeSeguranca){
        String sql;
        try {
            sql = "UPDATE `"+conexao.getNomeDB()+"`.`"+nomeTabela+"` SET "
                    + "`senha` = '"+senha+"', "
                    + "`nivel` = '"+classeSeguranca.getNum()+"' "
                    + "WHERE `usuariobd`.`user` = '"+nome+"';";

            System.out.println(sql);
            s = conexao.conectar().createStatement();
            s.executeUpdate(sql);
            
        } catch (Exception ex) {
            System.err.println("ERRO CADASTRAR USUARIO");
            return null;
        }
        return new Usuario(nome, senha, classeSeguranca);
    }
}
