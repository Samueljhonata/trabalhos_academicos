
package controle;

import DAO.ProcessoDAO;
import java.util.ArrayList;
import modelo.ClasseSeguranca;
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
    
    public boolean atualizaProcesso(Processo processo){
        return processoDAO.atualizaProcesso(processo);
    }
    
    public boolean excluiProcesso(Processo processo){
        return processoDAO.excluiProcesso(processo.getNumProcesso(), processo.getTC());
    }
    
    public boolean excluiProcesso(String sql, Usuario usuario){
        return processoDAO.excluiProcesso(montaExclusao(sql, usuario.getClasseSeguranca()));
    }
    
    private String montaExclusao(String sql, ClasseSeguranca classeSeguranca){
        sql = sql + " AND `C_numProcesso` <= "+classeSeguranca.getNum()
                +" AND `C_nomeAutor` <= "+classeSeguranca.getNum()
                +" AND `C_nomeReu` <= "+classeSeguranca.getNum()
                +" AND `C_descricaoAuto` <= "+classeSeguranca.getNum()
                +" AND `C_sentenca` <= "+classeSeguranca.getNum()
                +" AND `TC` <= "+classeSeguranca.getNum()+"";
        
        return sql;
    }

    //pesquisa atraves de comando
    public ArrayList<Processo> pesquisaProcessos(String sql, Usuario usuario){
        
        ArrayList<Processo> processos = processoDAO.pesquisaProcessos(sql);
        
        System.out.println("Pesquisa = " + sql);
        if (processos == null) {
            return null;
        }
        for (Processo p : processos) {
//            System.out.println("Original: " + p.mostraProcesso());
            p = mascara(p, usuario.getClasseSeguranca());
        }
        return processos;
    }
    
    public ArrayList<Processo> pesquisaProcessos(Processo processo, Usuario usuario){
        ArrayList<Processo> processos = processoDAO.pesquisaProcessos(processo);
        for (Processo p : processos) {
            //System.out.println("Original: " + p.mostraProcesso());
            p = mascara(p, usuario.getClasseSeguranca());
        }
        return processos;
    }
    
    //encapsula os campos que o usuário não deve ter acesso
    private Processo mascara(Processo processo, ClasseSeguranca classeSeguranca){
        if (processo == null) {
            return null;
        }
        
        if (processo.getC_numProcesso() != null && processo.getC_numProcesso().getNum() > classeSeguranca.getNum()) {
            processo.setNumProcesso(null);
            processo.setC_numProcesso(classeSeguranca);
        }
        
        if (processo.getC_nomeAutor() != null && processo.getC_nomeAutor().getNum() > classeSeguranca.getNum()) {
            processo.setNomeAutor(null);
            processo.setC_nomeAutor(classeSeguranca);
        }
        
        if (processo.getC_nomeReu() != null && processo.getC_nomeReu().getNum() > classeSeguranca.getNum()) {
            processo.setNomeReu(null);
            processo.setC_nomeReu(classeSeguranca);
        }
        
        if (processo.getC_descricaoAuto() != null && processo.getC_descricaoAuto().getNum() > classeSeguranca.getNum()) {
            processo.setDescricaoAuto(null);
            processo.setC_descricaoAuto(classeSeguranca);
        }
        
        if (processo.getC_sentenca() != null && processo.getC_sentenca().getNum() > classeSeguranca.getNum()) {
            processo.setSentenca(null);
            processo.setC_sentenca(classeSeguranca);
        }
        
        return processo;
    }
    
    
    
    /*private String pesquisaNumProcesso(){
        return "`numProcesso` LIKE '1' " + " AND `C_numProcesso` = 1";
    }*/
    
    
}
