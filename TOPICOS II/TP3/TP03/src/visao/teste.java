package visao;

import DAO.Conexao;
import controle.ControlaProcesso;
import controle.ControlaUsuario;
import java.util.ArrayList;
import modelo.ClasseSeguranca;
import modelo.Processo;
import modelo.Usuario;

public class teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControlaProcesso controlaProcesso=new ControlaProcesso();
        //Processo p = controlaProcesso.pesquisaProcessos(null, new Usuario(null, nul, ClasseSeguranca.SECRETA)).get(0);
//        if (p != null) {
//            System.out.println(p.getTC());
//            //p.setNomeReu("Jose");
//            p.setC_nomeReu(ClasseSeguranca.CONFIDENCIAL);
//            controlaProcesso.atualizaProcesso(p);
//            System.out.println(p.getTC());
//        }
        //controlaProcesso.insereProcesso(new Processo(100, ClasseSeguranca.CONFIDENCIAL, "nome", ClasseSeguranca.CONFIDENCIAL, "nR", ClasseSeguranca.SECRETA, "des", ClasseSeguranca.SECRETA, "sentenca", ClasseSeguranca.ALTAMENTE_SECRETA));
        /*ArrayList<Processo> a = controlaProcesso.pesquisaProcessos(null);
        for (Processo processo : a) {
        System.out.println(processo.getNumProcesso());
        }*/
        
        
        /*Processo p1 = null;
        ArrayList<Processo> p = controlaProcesso.pesquisaProcessos(p1, new Usuario(null, null, ClasseSeguranca.SECRETA));
        for (Processo processo : p) {
            System.out.println(processo.mostraProcesso());
        }*/
        
        String sql = "SELECT * FROM processo";
        ArrayList<Processo> p = controlaProcesso.pesquisaProcessos(sql, new Usuario(sql, null, ClasseSeguranca.NAO_CLASSIFICADA));
        for (Processo processo : p) {
            System.out.println(processo.mostraProcessoF());
        }
    }
    
}
