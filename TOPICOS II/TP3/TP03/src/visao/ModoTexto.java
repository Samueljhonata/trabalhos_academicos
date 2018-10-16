package visao;

import controle.ControlaProcesso;
import controle.ControlaUsuario;
import java.util.Scanner;
import modelo.Usuario;

public class ModoTexto {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ControlaUsuario controlaUsuario = new ControlaUsuario();
        ControlaProcesso controlaProcesso = new ControlaProcesso();
        Usuario usuario;
        String user, senha, comando;

        System.out.println("* * * * Sistema Gerenciador de Banco de Dados Locked - SGBD-L * * * *");

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
        while(true){
            System.out.print("\n->");
            comando = teclado.nextLine();
            controlaProcesso.recebeComandos(comando, usuario);
        }
    }

}
