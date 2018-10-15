package controle;

import DAO.UsuarioDAO;
import modelo.ClasseSeguranca;
import modelo.Usuario;

public class ControlaUsuario {
    private UsuarioDAO usuarioDAO;

    public ControlaUsuario() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    public Usuario insereUsuario(String nome, String senha, ClasseSeguranca classeSeguranca){
        return usuarioDAO.insereUsuario(nome, senha, classeSeguranca);
    }
    
    public Usuario atualizaUsuario(String nome, String senha, ClasseSeguranca classeSeguranca){
        return usuarioDAO.atualizaUsuario(nome, senha, classeSeguranca);
    }
}
