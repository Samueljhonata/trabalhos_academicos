package controle;

import modelo.ClasseSeguranca;
import modelo.TModo;
import modelo.Usuario;

public class ControlaComandosSistema {

    private ControlaUsuario controlaUsuario;

    public ControlaComandosSistema() {
        controlaUsuario = new ControlaUsuario();
    }

    public boolean recebeComando(String sql, Usuario usuario, TModo modo) {
        String s = sql.toUpperCase();
        String nomeUsuario;
        String senha;
        String classe;
        ClasseSeguranca classeSeguranca;
        Usuario novo;

        if (s.contains("CREATE USER")) { //criar usuario
            sql = sql.replaceAll("'", "").replace(";", "");
            String[] a = sql.split(" ");
            nomeUsuario = a[2];
            senha = a[5];
            classe = a[7];
            classeSeguranca = ClasseSeguranca.retorna(classe);
            //usuario pode criar outros somente do mesmo nivel de segurança ou menor
            if (classeSeguranca.getNum() <= usuario.getClasseSeguranca().getNum()) {
                novo = controlaUsuario.insereUsuario(nomeUsuario, senha, classeSeguranca, modo);
                if (novo != null) {
                    return true;
                }
            } else {
                System.err.println("ERRO AO INSERIR USUARIO - USUARIO ATUAL NAO TEM PRIVILEGIOS SUFICIENTES");
                return false;
            }
        }

        if (s.contains("DROP USER")) { //excluir usuario
            sql = sql.replaceAll("'", "").replace(";", "");
            String[] a = sql.split(" ");
            nomeUsuario = a[2];
            Usuario usuarioRemover = controlaUsuario.pesquisaUsuarioNome(nomeUsuario, modo);
            //usuario pode remover outros somente do mesmo nivel de segurança ou menor
            if (usuarioRemover.getClasseSeguranca().getNum() <= usuario.getClasseSeguranca().getNum()) {

                return (controlaUsuario.removeUsuario(nomeUsuario, modo));
            } else {
                System.err.println("ERRO AO EXCLUIR USUARIO - USUARIO ATUAL NAO TEM PRIVILEGIOS SUFICIENTES");
                return false;
            }
        }

        if (s.contains("SET PASSWORD FOR")) { //alterar senha usuario
            sql = sql.replaceAll("'", "").replace(";", "");
            String[] a = sql.split(" ");
            nomeUsuario = a[3];
            senha = sql.split("=")[1].replaceAll(" ", "");
            Usuario usuarioEditar = controlaUsuario.pesquisaUsuarioNome(nomeUsuario, modo);
            //usuario pode alterar senha de outros somente do mesmo nivel de segurança ou menor
            if (usuarioEditar.getClasseSeguranca().getNum() <= usuario.getClasseSeguranca().getNum()) {
                return controlaUsuario.atualizaSenhaUsuario(nomeUsuario, senha, modo);
            } else {
                System.err.println("ERRO AO ALTERAR SENHA USUARIO - USUARIO ATUAL NAO TEM PRIVILEGIOS SUFICIENTES");
                return false;
            }
        }

        if (s.contains("SET CLASS FOR")) { //alterar classe de segurança do usuario
            sql = sql.replaceAll("'", "").replace(";", "");
            String[] a = sql.split(" ");
            nomeUsuario = a[3];
            classe = sql.split("=")[1].replaceAll(" ", "");
            Usuario usuarioEditar = controlaUsuario.pesquisaUsuarioNome(nomeUsuario, modo);
            //usuario pode alterar senha de outros somente do mesmo nivel de segurança ou menor
            if (usuarioEditar.getClasseSeguranca().getNum() <= usuario.getClasseSeguranca().getNum()) {

                if (controlaUsuario.atualizaUsuario(nomeUsuario, usuarioEditar.getSenha(), ClasseSeguranca.retorna(classe), modo) != null) {
                    return true;
                }
                return false;
            } else {
                System.err.println("ERRO AO ALTERAR CLASSE DE SEGURANCA DO USUARIO - USUARIO ATUAL NAO TEM PRIVILEGIOS SUFICIENTES");
                return false;
            }
        }

        if (s.contains("RENAME USER")) { //alterar nome usuario
            sql = sql.replaceAll("'", "").replace(";", "");
            String[] a = sql.split(" ");
            nomeUsuario = a[2];
            String novoNome = a[4];
            Usuario usuarioEditar = controlaUsuario.pesquisaUsuarioNome(nomeUsuario, modo);
            //usuario pode alterar nome de outros somente do mesmo nivel de segurança ou menor
            if (usuarioEditar.getClasseSeguranca().getNum() <= usuario.getClasseSeguranca().getNum()) {

                if (controlaUsuario.atualizaNomeUsuario(nomeUsuario, novoNome, modo) != null) {
                    return true;
                }
                return false;
            } else {
                System.err.println("ERRO AO ALTERAR NOME USUARIO - USUARIO ATUAL NAO TEM PRIVILEGIOS SUFICIENTES");
                return false;
            }
        }

        return false;
    }

}
