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
        String comando = sql.split(" ")[0].toUpperCase();
        if(comando.equals("INSERT")){
            sql = montaInsercao(sql, usuario.getClasseSeguranca());
            if(!controlaProcesso.insereProcesso(sql)){
                System.out.println("-- ERRO AO EXECUTAR COMANDO!");
            }
        }
        
        if (comando.contains("SELECT")) {
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
    
    private String montaExclusao(String sql, ClasseSeguranca classeSeguranca){
        sql = sql /*+ " AND `C_numProcesso` <= "+classeSeguranca.getNum()
                +" AND `C_nomeAutor` <= "+classeSeguranca.getNum()
                +" AND `C_nomeReu` <= "+classeSeguranca.getNum()
                +" AND `C_descricaoAuto` <= "+classeSeguranca.getNum()
                +" AND `C_sentenca` <= "+classeSeguranca.getNum()*/
                +" AND `TC` <= "+classeSeguranca.getNum()+"";
        
        return sql;
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
    
    private String montaInsercao(String sql, ClasseSeguranca classeSeguranca){
        sql = sql.replaceAll("\\);","");
        String[] a = sql.split("\\(");
        String[] quebra = a[1].split(",");
        String retorno = "INSERT INTO processo VALUES(";
        if (classeSeguranca == ClasseSeguranca.NAO_CLASSIFICADA) {
            retorno = retorno + quebra[0] + ", " + 0 + ", "; //numProcesso
            retorno = retorno + quebra[1] + ", " + 0 + ", "; //nomeAutor
            retorno = retorno + quebra[2] + ", " + 0 + ", "; //nomeReu
            retorno = retorno + quebra[3] + ", " + 0 + ", "; //descricaoAuto
            retorno = retorno + quebra[4] + ", " + 0 + ", "; //sentenca
            retorno = retorno + " 0);";
        } else if (classeSeguranca == ClasseSeguranca.CONFIDENCIAL) {
            retorno = retorno + quebra[0] + ", " + 0 + ", "; //numProcesso
            retorno = retorno + quebra[1] + ", " + 1 + ", "; //nomeAutor
            retorno = retorno + quebra[2] + ", " + 1 + ", "; //nomeReu
            retorno = retorno + quebra[3] + ", " + 1 + ", "; //descricaoAuto
            retorno = retorno + quebra[4] + ", " + 1 + ", "; //sentenca
            retorno = retorno + " 1);";
        } else if (classeSeguranca == ClasseSeguranca.SECRETA) {
            retorno = retorno + quebra[0] + ", " + 0 + ", "; //numProcesso
            retorno = retorno + quebra[1] + ", " + 1 + ", "; //nomeAutor
            retorno = retorno + quebra[2] + ", " + 1 + ", "; //nomeReu
            retorno = retorno + quebra[3] + ", " + 2 + ", "; //descricaoAuto
            retorno = retorno + quebra[4] + ", " + 2 + ", "; //sentenca
            retorno = retorno + " 2);";
        }else if (classeSeguranca == ClasseSeguranca.ALTAMENTE_SECRETA) {
            retorno = retorno + quebra[0] + ", " + 0 + ", "; //numProcesso
            retorno = retorno + quebra[1] + ", " + 1 + ", "; //nomeAutor
            retorno = retorno + quebra[2] + ", " + 1 + ", "; //nomeReu
            retorno = retorno + quebra[3] + ", " + 2 + ", "; //descricaoAuto
            retorno = retorno + quebra[4] + ", " + 3 + ", "; //sentenca
            retorno = retorno + " 3);";
        }
        return retorno;
    }
}
