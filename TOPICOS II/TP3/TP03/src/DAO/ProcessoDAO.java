
package DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.ClasseSeguranca;
import modelo.Processo;
import modelo.Usuario;

public class ProcessoDAO {
    private Conexao conexao;
    private Statement s;
    private String nomeTabela = "processo";
    

    public ProcessoDAO() {
        this.conexao = new Conexao();
    }
    
    //usado para executar INSERT, UPDATE e DELETE
    public boolean executaSQL(String sql){
        try {
            System.out.println(sql);
            s = conexao.conectar().createStatement();
            if(s.executeUpdate(sql) != 0){
                return true;
            }
            
        } catch (Exception ex) {
            System.err.println("ERRO AO EXECUTAR COMANDO SQL");
            return false;
        }
        return false;
    }

    
    //usado para executar SELECT
    public ArrayList<Processo> pesquisaProcessos(String sql){
        ArrayList<Processo> retorno = new ArrayList<Processo>();
        try {
            
            s = conexao.conectar().createStatement();
            System.out.println(sql);
            ResultSet result = s.executeQuery(sql);
            System.out.println(sql);

            while (result.next()) {
                retorno.add(monta(result));
            }
            
        } catch (Exception ex) {
            System.err.println("ERRO PESQUISAR PESSOA");
            return null;
        }
        return retorno;
    }
    
    private Processo monta(ResultSet result) throws Exception {
        String numProcesso = null;
        ClasseSeguranca C_numProcesso = null;
        String nomeAutor = null;
        ClasseSeguranca C_nomeAutor = null;
        String nomeReu = null;
        ClasseSeguranca C_nomeReu = null;
        String descricaoAuto = null;
        ClasseSeguranca C_descricaoAuto = null;
        String sentenca = null;
        ClasseSeguranca C_sentenca = null;
        ClasseSeguranca TC = null;
        
        try {
            numProcesso = result.getString("numProcesso");
        } catch (Exception e) {
        }
        try {
            C_numProcesso = ClasseSeguranca.retorna(result.getInt("C_numProcesso"));
        } catch (Exception e) {
        }
        try {
            nomeAutor = result.getString("nomeAutor");
        } catch (Exception e) {
        }
        try {
            C_nomeAutor = ClasseSeguranca.retorna(result.getInt("C_nomeAutor"));
        } catch (Exception e) {
        }
        try {
            nomeReu = result.getString("nomeReu");
        } catch (Exception e) {
        }
        try {
            C_nomeReu = ClasseSeguranca.retorna(result.getInt("C_nomeReu"));
        } catch (Exception e) {
        }
        try {
            descricaoAuto = result.getString("descricaoAuto");
        } catch (Exception e) {
        }
        try {
            C_descricaoAuto = ClasseSeguranca.retorna(result.getInt("C_descricaoAuto"));
        } catch (Exception e) {
        }
        try {
            sentenca = result.getString("sentenca");
        } catch (Exception e) {
        }
        try {
            C_sentenca = ClasseSeguranca.retorna(result.getInt("C_sentenca"));
        } catch (Exception e) {
        }
        try {
            TC = ClasseSeguranca.retorna(result.getInt("TC"));
        } catch (Exception e) {
        }
        /*Processo b = new Processo(numProcesso, C_numProcesso, 
                nomeAutor, C_nomeAutor,
                nomeReu, C_nomeReu,
                descricaoAuto, C_descricaoAuto,
                sentenca, C_sentenca,
                TC);*/
        System.out.println(numProcesso+ C_numProcesso+ nomeAutor+ C_nomeAutor+  C_nomeReu+ C_descricaoAuto+C_sentenca+ TC);
        return new Processo(numProcesso, C_numProcesso, nomeAutor, C_nomeAutor, nomeReu, C_nomeReu, descricaoAuto, C_descricaoAuto, sentenca, C_sentenca, TC);
    }


    
/*    public Processo insereProcesso(String sql, String numProcesso, ClasseSeguranca classeSeguranca){
        try {
            

            System.err.println(sql);
            s = conexao.conectar().createStatement();
            s.executeUpdate(sql);
            
        } catch (Exception ex) {
            System.err.println("ERRO CADASTRAR PROCESSO");
            return null;
        }
        return this.pesquisaProcesso(numProcesso, classeSeguranca);
    }
  */      
    /*public boolean excluiProcesso(String sql) {
        try {
            System.out.println(sql);
            s = conexao.conectar().createStatement();
            if(s.executeUpdate(sql) != 0){
                return true;
            }
            
        } catch (Exception ex) {
            System.err.println("ERRO EXCLUIR PROCESSO");
            return false;
        }
        return false;
    }*/
    
    
    
    
    
    
    
    ////////////////////////////////////////////////////////
    public boolean atualizaProcesso(Processo processo){
        String sql;
        try {
            sql = "UPDATE `"+conexao.getNomeDB()+"`.`"+nomeTabela+"` SET "
                    + "C_numProcesso = '"+processo.getC_numProcesso().getNum()+"', "
                    + "nomeAutor = '"+processo.getNomeAutor()+"', "
                    + "C_nomeAutor = '"+processo.getC_nomeAutor().getNum()+"', "
                    + "nomeReu = '"+processo.getNomeReu()+"', "
                    + "C_nomeReu = '"+processo.getC_nomeReu().getNum()+"', "
                    + "descricaoAuto = '"+processo.getDescricaoAuto()+"', "
                    + "C_descricaoAuto = '"+processo.getC_descricaoAuto().getNum()+"', "
                    + "sentenca = '"+processo.getSentenca()+"', "
                    + "C_sentenca = '"+processo.getC_sentenca().getNum()+"', "
                    + "TC = '"+processo.getTC().getNum()+"' "
                    + "WHERE `"+nomeTabela+"`.`numProcesso` = '"+processo.getNumProcesso()+"' AND TC = "+processo.getTC().getNum()+";";

            System.out.println(sql);
            s = conexao.conectar().createStatement();
            s.executeUpdate(sql);
            
        } catch (Exception ex) {
            System.err.println("ERRO CADASTRAR USUARIO");
            return false;
        }
        return true;
    }
    
    public ArrayList<Processo> pesquisaProcessos(Processo processo){
        String sql = "";
        ArrayList<Processo> retorno = new ArrayList<Processo>();
        try {
            if(processo == null){
                sql = "SELECT * FROM " + nomeTabela;
            }
            s = conexao.conectar().createStatement();
            ResultSet result = s.executeQuery(sql);
            System.out.println(sql);

            while (result.next()) {
                retorno.add(monta(result));
            }
            
        } catch (Exception ex) {
            System.err.println("ERRO PESQUISAR PESSOA");
            return null;
        }
        return retorno;
    }

    public boolean excluiProcesso(Processo processo, ClasseSeguranca TC) {
        String sql;
        try {
            sql = "DELETE FROM `"+nomeTabela+"` WHERE `";
            
            if (processo.getNumProcesso() != null) {
                    sql = sql +nomeTabela+"`.`numProcesso` = '"+processo.getNumProcesso()+"' AND `"+nomeTabela+"`.`TC` = "+TC.getNum()+"";
            }
            
            System.out.println(sql);
            s = conexao.conectar().createStatement();
            if(s.executeUpdate(sql) != 0){
                return true;
            }
            
        } catch (Exception ex) {
            System.err.println("ERRO EXCLUIR PROCESSO");
            return false;
        }
        return false;
    }

    public Processo pesquisaProcesso(String numProcesso, ClasseSeguranca classeSeguranca){
        String sql = "";
        try {
            sql = "SELECT * FROM " + nomeTabela + " WHERE numProcesso = " + numProcesso + " AND TC = " + classeSeguranca.getNum();
            s = conexao.conectar().createStatement();
            ResultSet result = s.executeQuery(sql);
            System.err.println(sql);

            while (result.next()) {
                return (monta(result));
            }
            
        } catch (Exception ex) {
            System.err.println("ERRO PESQUISAR PESSOA");
            return null;
        }
        return null;
    }
    
    public Processo insereProcesso(Processo processo){
        String sql;
        try {
            sql = "INSERT INTO `"+conexao.getNomeDB()+"`.`"+nomeTabela+"` VALUES ("
                    + "'"+processo.getNumProcesso()+"', "
                    + "'"+processo.getC_numProcesso().getNum()+"', "
                    + "'"+processo.getNomeAutor()+"', "
                    + "'"+processo.getC_nomeAutor().getNum()+"', "
                    + "'"+processo.getNomeReu()+"', "
                    + "'"+processo.getC_nomeReu().getNum()+"', "
                    + "'"+processo.getDescricaoAuto()+"', "
                    + "'"+processo.getC_descricaoAuto().getNum()+"', "
                    + "'"+processo.getSentenca()+"', "
                    + "'"+processo.getC_sentenca().getNum()+"', "
                    + "'"+processo.getTC().getNum()+"');";

            System.out.println(sql);
            s = conexao.conectar().createStatement();
            s.executeUpdate(sql);
            
        } catch (Exception ex) {
            System.err.println("ERRO CADASTRAR PROCESSO");
            return null;
        }
        return processo;
    }
    

}
