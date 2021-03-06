package DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.ClasseSeguranca;
import modelo.TModo;
import modelo.Usuario;

public class UsuarioDAO {

    private Conexao conexao;
    private Statement s;
    private String nomeTabela = "usuariobd";

    public UsuarioDAO() {
        this.conexao = new Conexao();
    }

    public Usuario insereUsuario(String nome, String senha, ClasseSeguranca classeSeguranca, TModo modo) {
        String sql;
        try {
            sql = "INSERT INTO `" + conexao.getNomeDB() + "`.`" + nomeTabela + "` (`user`, `senha`, `nivel`) VALUES ("
                    + "'" + nome + "', "
                    + "'" + senha + "', "
                    + "'" + classeSeguranca.getNum() + "');";

            if (modo == TModo.ANALITICO) {
                System.out.println(" COMANDO REAL: " + sql);
            }
            s = conexao.conectar().createStatement();
            s.executeUpdate(sql);

        } catch (Exception ex) {
            System.err.println("ERRO CADASTRAR USUARIO");
            return null;
        }
        return new Usuario(nome, senha, classeSeguranca);
    }

    public Usuario atualizaUsuario(String nome, String senha, ClasseSeguranca classeSeguranca, TModo modo) {
        String sql;
        try {
            sql = "UPDATE `" + conexao.getNomeDB() + "`.`" + nomeTabela + "` SET "
                    + "`senha` = '" + senha + "', "
                    + "`nivel` = '" + classeSeguranca.getNum() + "' "
                    + "WHERE `usuariobd`.`user` = '" + nome + "';";

            if (modo == TModo.ANALITICO) {
                System.out.println(" COMANDO REAL: " + sql);
            }
            s = conexao.conectar().createStatement();
            s.executeUpdate(sql);

        } catch (Exception ex) {
            System.err.println("ERRO CADASTRAR USUARIO");
            return null;
        }
        return new Usuario(nome, senha, classeSeguranca);
    }

    public boolean atualizaSenhaUsuario(String nome, String senha, TModo modo) {
        String sql;
        try {
            sql = "UPDATE " + nomeTabela + " SET "
                    + "`senha` = '" + senha + "' "
                    + "WHERE `usuariobd`.`user` = '" + nome + "';";

            if (modo == TModo.ANALITICO) {
                System.out.println(" COMANDO REAL: " + sql);
            }
            s = conexao.conectar().createStatement();
            s.executeUpdate(sql);

        } catch (Exception ex) {
            System.err.println("ERRO CADASTRAR USUARIO");
            return false;
        }
        return true;
    }

    public boolean atualizaNomeUsuario(String nomeAtual, String novoNome, TModo modo) {
        String sql;
        try {
            sql = "UPDATE `" + conexao.getNomeDB() + "`.`" + nomeTabela + "` SET "
                    + "`user` = '" + novoNome + "' "
                    + "WHERE `usuariobd`.`user` = '" + nomeAtual + "';";

            if (modo == TModo.ANALITICO) {
                System.out.println(" COMANDO REAL: " + sql);
            }
            s = conexao.conectar().createStatement();
            s.executeUpdate(sql);

        } catch (Exception ex) {
            System.err.println("ERRO CADASTRAR USUARIO");
            return false;
        }
        return true;
    }

    public ArrayList<Usuario> pesquisaUsuarios(Usuario usuario, TModo modo) {
        String sql = "";
        ArrayList<Usuario> retorno = new ArrayList<Usuario>();
        try {
            if (usuario == null) {
                sql = "SELECT * FROM " + nomeTabela;
            } else if (usuario.getUser() != null && usuario.getSenha() != null) {
                sql = "SELECT * FROM " + nomeTabela + " WHERE user = '" + usuario.getUser() + "' AND senha = '" + usuario.getSenha() + "'";
            } else if (usuario.getUser() != null) {
                sql = "SELECT * FROM " + nomeTabela + " WHERE user = '" + usuario.getUser() + "'";
            }
            if (modo == TModo.ANALITICO) {
                System.out.println(" COMANDO REAL: " + sql);
            }
            s = conexao.conectar().createStatement();
            ResultSet result = s.executeQuery(sql);

            while (result.next()) {
                retorno.add(monta(result));
            }

        } catch (Exception ex) {
            System.err.println("ERRO PESQUISAR USUARIO");
            return null;
        }
        return retorno;
    }

    public boolean excluiUsuario(Usuario usuario, TModo modo) {
        String sql;
        try {
            sql = "DELETE FROM " + nomeTabela + " WHERE `user` = '" + usuario.getUser() + "'";
            if (modo == TModo.ANALITICO) {
                System.out.println(" COMANDO REAL: " + sql);
            }
            s = conexao.conectar().createStatement();
            s.executeUpdate(sql);
        } catch (Exception ex) {
            System.err.println("ERRO EXCLUIR USUARIO");
            return false;
        }
        return true;
    }

    private Usuario monta(ResultSet result) throws Exception {
        return new Usuario(result.getString("user"), result.getString("senha"), ClasseSeguranca.retorna(result.getInt("nivel")));
    }
}
