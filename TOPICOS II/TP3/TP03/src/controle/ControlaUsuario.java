package controle;

import DAO.UsuarioDAO;
import java.util.ArrayList;
import modelo.ClasseSeguranca;
import modelo.TModo;
import modelo.Usuario;

public class ControlaUsuario {

    private UsuarioDAO usuarioDAO;

    public ControlaUsuario() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public Usuario insereUsuario(String nome, String senha, ClasseSeguranca classeSeguranca, TModo modo) {
        return usuarioDAO.insereUsuario(nome, senha, classeSeguranca, modo);
    }

    public Usuario atualizaUsuario(String nome, String senha, ClasseSeguranca classeSeguranca, TModo modo) {
        return usuarioDAO.atualizaUsuario(nome, senha, classeSeguranca, modo);
    }

    public boolean atualizaSenhaUsuario(String nome, String senha, TModo modo) {
        return usuarioDAO.atualizaSenhaUsuario(nome, senha, modo);
    }

    public Usuario fazerLogin(String user, String senha, TModo modo) {
        try {
            return usuarioDAO.pesquisaUsuarios(new Usuario(user, senha, null), modo).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public Usuario pesquisaUsuarioNome(String nome, TModo modo) {
        ArrayList<Usuario> a = usuarioDAO.pesquisaUsuarios(new Usuario(nome, null, null), modo);
        if (a == null || a.size() == 0) {
            return null;
        }
        return a.get(0);
    }

    public boolean removeUsuario(String nome, TModo modo) {
        return usuarioDAO.excluiUsuario(new Usuario(nome, null, null), modo);
    }

    Object atualizaNomeUsuario(String nomeUsuario, String novoNome, TModo modo) {
        return usuarioDAO.atualizaNomeUsuario(nomeUsuario, novoNome, modo);
    }
}
