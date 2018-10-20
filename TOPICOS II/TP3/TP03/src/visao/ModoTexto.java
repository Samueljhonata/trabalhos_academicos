package visao;

import controle.ControlaComandos;
import controle.ControlaProcesso;
import controle.ControlaUsuario;
import java.util.Scanner;
import modelo.Usuario;

public class ModoTexto {

    
    private void mostraHelp(){
        System.out.println("******help********");
                    System.out.println("help - ajuda");
                    System.out.println("exit - sair");
                    System.out.println("reconnect - trocar de usuario");
                    System.out.println("ou um comando sql valido");
    }
    
    public static void main(String[] args) {
        ModoTexto modoTexto = new ModoTexto();
        Scanner teclado = new Scanner(System.in);
        ControlaUsuario controlaUsuario = new ControlaUsuario();
        ControlaComandos controlaComandos = new ControlaComandos();
        Usuario usuario = null;
        String user, senha, comando;

        System.out.println("* * * * Sistema Gerenciador de Banco de Dados Locked - SGBD-L * * * *");

        //executa
        while(true){
            
            //faz login
            while (true) {
                System.out.print("User: ");
                user = teclado.nextLine();
                System.out.print("Senha: ");
                senha = teclado.nextLine();
                usuario = controlaUsuario.fazerLogin(user, senha);
                if (usuario != null) {
                    System.out.println("Bem vindo, " + usuario.getUser());
                    break;
                } else {
                    System.out.println("Usuário e/ou senha incorreto(s)");
                }
            }

            //recebe comandos
            while (true) {
                System.out.print("\n->");
                comando = teclado.nextLine();
                if (comando.equalsIgnoreCase("help")){
                    modoTexto.mostraHelp();
                }else if (comando.equalsIgnoreCase("exit")) {
                    System.out.println("Glória Adeuxx!");
                    return;
                }else if(comando.equalsIgnoreCase("reconnect")){
                    usuario = null;
                    break;
                }
                
                controlaComandos.recebeComandos(comando, usuario);
            }

        }
    }

}
