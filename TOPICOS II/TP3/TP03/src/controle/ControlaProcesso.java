
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
    
    public ArrayList<Processo> pesquisaProcessos(String sql, Usuario usuario){
        sql = montaWhere(sql, usuario.getClasseSeguranca());
        ArrayList<Processo> processos = processoDAO.pesquisaProcessos(sql);
        
        System.out.println("Pesquisa = " + sql);
        for (Processo p : processos) {
            //System.out.println("Original: " + p.mostraProcesso());
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
    
    
    private String montaWhere(String pesquisa, ClasseSeguranca classeSeguranca){
        //pesquisa = "SELECT * FROM `processo` WHERE `numProcesso` = 1";
        String retorno = pesquisa;
        String[] quebra = pesquisa.split("WHERE");
        String where;
        if (quebra.length > 1) {
            where = quebra[1];
            where = where.replaceAll("numProcesso", " C_numProcesso <= "+ classeSeguranca.getNum() +" AND numProcesso");
            where = where.replaceAll("nomeAutor", " C_nomeAutor <= "+ classeSeguranca.getNum() +" AND nomeAutor");
            where = where.replaceAll("nomeReu", " C_nomeReu <= "+ classeSeguranca.getNum() +" AND nomeReu");
            where = where.replaceAll("descricaoAuto", " C_descricaoAuto <= "+ classeSeguranca.getNum() +" AND descricaoAuto");
            where = where.replaceAll("sentenca", " C_sentenca <= "+ classeSeguranca.getNum() +" AND sentenca");
            System.out.println(where);
            
            retorno = quebra[0] + " WHERE " + where;
        }
        
        return retorno;
    }
    
    /*private String pesquisaNumProcesso(){
        return "`numProcesso` LIKE '1' " + " AND `C_numProcesso` = 1";
    }*/
    
    public void recebeComandos(String sql, Usuario usuario){
        if (sql.contains("SELECT")) {
            ArrayList<Processo> a = this.pesquisaProcessos(sql, usuario);
            for (Processo processo : a) {
                System.out.println(processo.mostraProcesso());
            }
        }
    }
}
