
package controle;

import DAO.ProcessoDAO;
import java.util.ArrayList;
import modelo.Processo;
import modelo.Usuario;

public class ControlaProcesso {
    private ProcessoDAO processoDAO;

    public ControlaProcesso() {
        processoDAO = new ProcessoDAO();
    }
    
    
    
    public Processo insereProcesso(Processo processo){
        return processoDAO.insereProcesso(processo);
    }
    
    public ArrayList<Processo> pesquisaProcessos(Processo processo, Usuario usuario){
        return processoDAO.pesquisaProcessos(processo);
    }
    
    /*public String montaPesquisa(String pesquisa){
        pesquisa = "SELECT * FROM `processo` WHERE `numProcesso` = 1";
        String[] a = pesquisa.split(" ");
        if (a[0].equalsIgnoreCase("SELECT")) {
            
        }
    }
    
    private String pesquisaNumProcesso(){
        return "`numProcesso` LIKE '1' " + " AND `C_numProcesso` = 1";
    }*/
}
