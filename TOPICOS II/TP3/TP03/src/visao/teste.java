/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import DAO.Conexao;
import controle.ControlaProcesso;
import controle.ControlaUsuario;
import java.util.ArrayList;
import modelo.ClasseSeguranca;
import modelo.Processo;

/**
 *
 * @author samuel
 */
public class teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControlaProcesso controlaProcesso=new ControlaProcesso();
        //controlaProcesso.insereProcesso(new Processo(100, ClasseSeguranca.CONFIDENCIAL, "nome", ClasseSeguranca.CONFIDENCIAL, "nR", ClasseSeguranca.SECRETA, "des", ClasseSeguranca.SECRETA, "sentenca", ClasseSeguranca.ALTAMENTE_SECRETA));
        /*ArrayList<Processo> a = controlaProcesso.pesquisaProcessos(null);
        for (Processo processo : a) {
            System.out.println(processo.getNumProcesso());
        }*/
    }
    
}
