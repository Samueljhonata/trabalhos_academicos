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
    
    public Usuario atualizaSenhaUsuario(String nome, String senha){
        ArrayList<Usuario> usuarios = usuarioDAO.pesquisaUsuarios(new Usuario(nome, null, null));
        if (usuarios == null) {
            return null;
        }
        return usuarioDAO.atualizaUsuario(nome, senha, usuarios.get(0).getClasseSeguranca());
    }
    
    public Usuario fazerLogin(String user, String senha){
        try{
        return usuarioDAO.pesquisaUsuarios(new Usuario(user, senha, null)).get(0);
        }catch (Exception e){
            return null;
        }
    }
    
    public Usuario pesquisaUsuarioNome(String nome){
        ArrayList<Usuario> a = usuarioDAO.pesquisaUsuarios(new Usuario(nome, null, null));
        if (a == null) {
            return null;
        }
        return a.get(0);
    }
    
    public boolean removeUsuario(String nome){
        return  usuarioDAO.excluiUsuario(new Usuario(nome, null, null));
    }

    Object atualizaNomeUsuario(String nomeUsuario, String novoNome) {
        return usuarioDAO.atualizaNomeUsuario(nomeUsuario, novoNome);
    }
}
