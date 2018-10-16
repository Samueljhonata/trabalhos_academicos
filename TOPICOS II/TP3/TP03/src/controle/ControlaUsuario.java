package controle;

import DAO.UsuarioDAO;
import java.util.ArrayList;
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
    
    public Usuario fazerLogin(String user, String senha){
        try{
        return usuarioDAO.pesquisaUsuarios(new Usuario(user, senha, null)).get(0);
        }catch (Exception e){
            return null;
        }
    }
}
