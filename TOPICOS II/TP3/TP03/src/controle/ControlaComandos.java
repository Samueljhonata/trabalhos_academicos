package controle;

import java.util.ArrayList;
import modelo.ClasseSeguranca;
import modelo.Processo;
import modelo.Usuario;

public class ControlaComandos {
    ControlaProcesso controlaProcesso;

    public ControlaComandos() {
        controlaProcesso = new ControlaProcesso();
    }
    
    
    
    public void recebeComandos(String sql, Usuario usuario) {
        if (sql.contains("SELECT")) {
            sql = montaWhere(sql, usuario.getClasseSeguranca());
            sql = montaSelect(sql);
            
            ArrayList<Processo> a = controlaProcesso.pesquisaProcessos(sql, usuario);
            if (a != null) {
                for (Processo processo : a) {
                    System.out.println(processo.mostraProcesso());
                }
            }
        }
    }
    
    
    //monta a cláusula select da pesquisa inserindo os atributos de segurança
    private String montaSelect(String pesquisa){
        String retorno = pesquisa;
        String select;
        String quebra[];
        if (pesquisa.contains("*")) {
            return pesquisa;
        }
        quebra = pesquisa.split("FROM");
        select = quebra[0];
        select = select.replaceAll("numProcesso", " C_numProcesso, " + "numProcesso");
        select = select.replaceAll("nomeAutor", " C_nomeAutor, " + "nomeAutor");
        select = select.replaceAll("nomeReu", " C_nomeReu, " + "nomeReu");
        select = select.replaceAll("descricaoAuto", " C_descricaoAuto, " + "descricaoAuto");
        select = select.replaceAll("sentenca", " C_sentenca, " +"sentenca");
            
        retorno = select + " FROM " + quebra[1];
        return retorno;
        
    }
    
    //monta a cláusula where da pesquisa inserindo os atributos de segurança, de acordo com a classe do usuario
    private String montaWhere(String pesquisa, ClasseSeguranca classeSeguranca){
        //pesquisa = "SELECT * FROM `processo` WHERE `numProcesso` = 1";
        String retorno = pesquisa;
        String[] quebra;
        String where;
        quebra = pesquisa.split("WHERE");
        
        if (quebra.length > 1) {
            where = quebra[1];
            where = where.replaceAll("numProcesso", " C_numProcesso <= "+ classeSeguranca.getNum() +" AND numProcesso");
            where = where.replaceAll("nomeAutor", " C_nomeAutor <= "+ classeSeguranca.getNum() +" AND nomeAutor");
            where = where.replaceAll("nomeReu", " C_nomeReu <= "+ classeSeguranca.getNum() +" AND nomeReu");
            where = where.replaceAll("descricaoAuto", " C_descricaoAuto <= "+ classeSeguranca.getNum() +" AND descricaoAuto");
            where = where.replaceAll("sentenca", " C_sentenca <= "+ classeSeguranca.getNum() +" AND sentenca");
            //System.out.println(where);
            
            retorno = quebra[0] + " WHERE " + where;
        }
        
        return retorno;
    }
    
}
