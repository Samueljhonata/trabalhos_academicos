
package controle;

import DAO.ProcessoDAO;
import java.util.ArrayList;
import java.util.Comparator;
import modelo.ClasseSeguranca;
import modelo.Processo;
import modelo.Usuario;

public class ControlaProcesso {
    private ProcessoDAO processoDAO;
    private ArrayList<Processo> listaProcessos;
    
    class ComparadorDeProcesso implements Comparator<Processo> {
    public int compare(Processo o1, Processo o2) {
        if (o1.getNumProcesso().compareTo(o2.getNumProcesso()) < 0){
            return -1;
        }
        if (o1.getNumProcesso().compareTo(o2.getNumProcesso()) > 0){
            return 1;
        }
        
        if (o1.getTC().getNum() > o2.getTC().getNum()) {
            return 1;
        }
        return -1;
    }
}

    public ControlaProcesso() {
        processoDAO = new ProcessoDAO();
    }
    
    public Processo insereProcesso(Processo processo){
        return processoDAO.insereProcesso(processo);
    }
    
    public boolean insereProcesso(String sql){
        return processoDAO.executaSQL(sql);
    }
    
    
    
    public boolean atualizaProcesso(Processo processo){
        return processoDAO.atualizaProcesso(processo);
    }
    
    public Processo pesquisaProcesso(String numProcesso, ClasseSeguranca TC){
        return processoDAO.pesquisaProcesso(numProcesso, TC);
    }
    
    public boolean excluiProcesso(Processo processo){
        return processoDAO.excluiProcesso(processo, processo.getTC());
    }
    
    /*public boolean excluiProcesso(String sql, Usuario usuario){
        return processoDAO.excluiProcesso(sql, usuario.getClasseSeguranca()));
    }*/
    

    public ArrayList<Processo> pesquisaProcessosSimples(String sql, Usuario usuario){
        return processoDAO.pesquisaProcessos(sql);
    }
    
    //pesquisa atraves de comando
    public ArrayList<Processo> pesquisaProcessos(String sql, Usuario usuario){
        ArrayList<Processo> retorno = new ArrayList<Processo>();
        ArrayList<Processo> processos = processoDAO.pesquisaProcessos(sql);
        
        //System.out.println("Pesquisa = " + sql);
        if (processos == null) {
            return null;
        }
        listaProcessos = new ArrayList<Processo>();
        for (Processo p : processos) {
            confereNivel(p, usuario.getClasseSeguranca());
        }
        for (Processo p : listaProcessos) {
//            System.out.println("Original: " + p.mostraProcesso());
            Processo temp = mascara(p, usuario.getClasseSeguranca());
            if (temp != null) {
                retorno.add(temp);
            }
            
        }
        return retorno;
    }
    
    /*public ArrayList<Processo> pesquisaProcessos(Processo processo, Usuario usuario){
        ArrayList<Processo> processos = processoDAO.pesquisaProcessos(processo);
        for (Processo p : processos) {
            //System.out.println("Original: " + p.mostraProcesso());
            p = mascara(p, usuario.getClasseSeguranca());
        }
        return processos;
    }*/
    
    private void confereNivel(Processo processo, ClasseSeguranca classeSeguranca){
        ArrayList<Processo> temp = new ArrayList<Processo>();
        int qtdPresente = 0;
        if (processo == null) {
            return;
        }
//        System.out.println("\nCONFERINDO: " + processo.getNumProcesso() + " - " + processo.getTC());
        //confere se nao tera processos repetidos com classe de segurança maior que a do usuário
        if (!listaProcessos.contains(processo)) { //se ainda não tinha um processo igual ao atual na lista
//            System.out.println("NOVO!");
            listaProcessos.add(processo);
        }else{ //se ja tinha um processo igual ao atual na lista
//            System.out.println("JA TINHA");
            if (processo.getTC().getNum() <= classeSeguranca.getNum()) { //se a classe do atual é menor que a classe do usuario
                listaProcessos.add(processo);
                qtdPresente++;
            }
            for (Processo p : listaProcessos) { //percorre procurando processos que sejam iguais ao atual
                if (p.getNumProcesso().equals(processo.getNumProcesso())){ //numProcesso igual
                    if(p.getTC().getNum() > classeSeguranca.getNum() && qtdPresente >0){ //classe de segurança maior
                        //listaProcessos.remove(p);
                        temp.add(p);
//                        System.out.println("REMOVE");
                    }else{ //classe de segurança menor
                        qtdPresente++;
//                        System.out.println("NAO REMOVE ");
                    }
                }
            }
//            System.out.println("qtdPresente=" + qtdPresente);
            
            for (Processo processo1 : temp) {
                qtdPresente--;
//                System.out.println("removendo ... " + processo1.getTC());
                listaProcessos.remove(processo1);
            }
            
            if (qtdPresente == 0 && listaProcessos.size() > 0) {
//                System.out.println("ENTROU");
                ComparadorDeProcesso comparadorDeTimes = new ComparadorDeProcesso();
                temp.sort(comparadorDeTimes);
//                System.out.println("COMPARANDO");
          /*      for (Processo processo1 : temp) {
                    System.out.println(processo1.getNumProcesso() + " - " + processo1.getTC());
                }*/
                listaProcessos.add(temp.get(0));
            }
        }

    }
    
    //encapsula os campos que o usuário não deve ter acesso
    private Processo mascara(Processo processo, ClasseSeguranca classeSeguranca){
        String testa = null;
        if (processo == null) {
            return null;
        }
        
        if (processo.getC_numProcesso() != null && processo.getC_numProcesso().getNum() > classeSeguranca.getNum()) {
            processo.setNumProcesso(null);
            processo.setC_numProcesso(classeSeguranca);
        }else{
            testa = "";
        }
        
        if (processo.getC_nomeAutor() != null && processo.getC_nomeAutor().getNum() > classeSeguranca.getNum()) {
            processo.setNomeAutor(null);
            processo.setC_nomeAutor(classeSeguranca);
        }else{
            testa = "";
        }
        
        if (processo.getC_nomeReu() != null && processo.getC_nomeReu().getNum() > classeSeguranca.getNum()) {
            processo.setNomeReu(null);
            processo.setC_nomeReu(classeSeguranca);
        }else{
            testa = "";
        }
        
        if (processo.getC_descricaoAuto() != null && processo.getC_descricaoAuto().getNum() > classeSeguranca.getNum()) {
            processo.setDescricaoAuto(null);
            processo.setC_descricaoAuto(classeSeguranca);
        }else{
            testa = "";
        }
        
        if (processo.getC_sentenca() != null && processo.getC_sentenca().getNum() > classeSeguranca.getNum()) {
            processo.setSentenca(null);
            processo.setC_sentenca(classeSeguranca);
        }else{
            testa = "";
        }
        
        if (testa == null) {
            return null;
        }
        return processo;
    }
    
    
    
    /*private String pesquisaNumProcesso(){
        return "`numProcesso` LIKE '1' " + " AND `C_numProcesso` = 1";
    }*/
    
    
}
