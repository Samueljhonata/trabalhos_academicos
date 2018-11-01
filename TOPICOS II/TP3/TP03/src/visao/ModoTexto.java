package visao;

import controle.ControlaComandos;
import controle.ControlaProcesso;
import controle.ControlaUsuario;
import java.util.Scanner;
import modelo.TModo;
import modelo.Usuario;

public class ModoTexto {

    //http://www.bosontreinamentos.com.br/mysql/curso-de-mysql-gerenciamento-de-usuarios-do-sistema-criar-consultar-renomear-e-excluir/
    private void mostraHelp() {
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * Ajuda * * * * * * * * * * * * * * * * * *  * * * * *");
        System.out.println("- Comandos do sistema:");
        System.out.println(" help - ajuda");
        System.out.println(" exit - sair");
        System.out.println(" modo <ANALITICO,USUAL> - mostrar ou nao as classes de seguranca");
        System.out.println(" reconnect - trocar de usuario");
        System.out.println("- Para controle de usuario:");
        System.out.println(" CREATE USER '<nome_usuario>' IDENTIFIED BY '<senha>' CLASS <sigla_classe_seguranca> - insere usuario");
        System.out.println(" -Classes disponiveis: NAO CLASSIFICADO (U); CONFIDENCIAL (C); SECRETO (S); ALTAMENTE SECRETO (AS)");
        System.out.println(" DROP USER '<nome_usuario>' - exclui usuario");
        System.out.println(" SET PASSWORD FOR '<nome_usuario>' = '<senha>' - modifica senha do usuario");
        System.out.println(" SET CLASS FOR '<nome_usuario>' = <sigla_classe_seguranca> - modifica classe do usuario");
        System.out.println(" RENAME USER '<nome_usuario>' = '<novo_nome>' - renomear usuario");
        System.out.println("- Comandos SQL válidos:");
        System.out.println(" SELECT <*,numProcesso,nomeReu,nomeAutor,descricaoAuto,sentenca> FROM processo");
        System.out.println(" \t<WHERE  <atributo> <operador_logico> '<valor>' <, <atributo> <operador_logico> '<valor>'> >");
        System.out.println(" \t<ORDER BY <nomeAtributo> <ordem>>");
        System.out.println(" INSERT INTO processo VALUES('<numProcesso>','<nomeReu>','<nomeAutor>','<descricaoAuto>','<sentenca>')");
        System.out.println(" DELETE FROM processo");
        System.out.println(" \t<WHERE  <atributo> <operador_logico> '<valor>' <, <atributo> <operador_logico> '<valor>'> >");
        System.out.println(" UPDATE processo SET <atributo> <operador_logico> '<valor>' <, <atributo> <operador_logico> '<valor>'>");
        System.out.println(" \t<WHERE  <atributo> <operador_logico> '<valor>' <, <atributo> <operador_logico> '<valor>'> >");
        System.out.println("ou um comando sql valido");
    }

    public static void main(String[] args) {
        ModoTexto modoTexto = new ModoTexto();
        Scanner teclado = new Scanner(System.in);
        ControlaUsuario controlaUsuario = new ControlaUsuario();
        ControlaComandos controlaComandos = new ControlaComandos();
        Usuario usuario = null;
        String user, senha, comando;
        TModo modo = TModo.USUAL;

        System.out.println("* * * * Sistema Gerenciador de Banco de Dados Locked - SGBD-L * * * *");

        //executa
        while (true) {

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
                //try {
                    System.out.print("\n->");
                    comando = teclado.nextLine();
                    if (comando.equalsIgnoreCase("help")) {
                        modoTexto.mostraHelp();
                        continue;
                    } else if (comando.equalsIgnoreCase("exit")) {
                        System.out.println("Glória Adeuxx!");
                        return;
                    } else if (comando.equalsIgnoreCase("reconnect")) {
                        usuario = null;
                        break;
                    } else if (comando.contains("modo")){
                        String subComando = comando.split(" ")[1];
                        if (subComando.equalsIgnoreCase("ANALITICO")) {
                            modo = TModo.ANALITICO;
                        } else if (subComando.equalsIgnoreCase("USUAL")) {
                            modo = TModo.USUAL;
                        }
                        System.out.println("-- MODO " + modo.getNome() + " ATIVADO!");
                        continue;
                    }

                    controlaComandos.recebeComandos(comando, usuario, modo);
                /*} catch (Exception e) {

                }*/
            }

        }

    }
}