
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
    
    public Usuario atualizaUsuario(String nome, String senha, ClasseSeguranca classeSeguranca){
        String sql;
        try {
            sql = "UPDATE `"+conexao.getNomeDB()+"`.`"+nomeTabela+"` SET "
                    + "`senha` = '"+senha+"', "
                    + "`nivel` = '"+classeSeguranca.getNum()+"' "
                    + "WHERE `usuariobd`.`user` = '"+nome+"';";

            System.out.println(sql);
            s = conexao.conectar().createStatement();
            s.executeUpdate(sql);
            
        } catch (Exception ex) {
            System.err.println("ERRO CADASTRAR USUARIO");
            return null;
        }
        return new Usuario(nome, senha, classeSeguranca);
    }
    
    public ArrayList<Processo> pesquisaProcessos(Processo processo){
        String sql = "";
        ArrayList<Processo> retorno = new ArrayList<Processo>();
        try {
            if(processo == null){
                sql = "SELECT * FROM " + nomeTabela;
            }
            System.out.println(sql);
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
    
    private Processo monta(ResultSet result) throws Exception {
        return new Processo(result.getInt("numProcesso"), ClasseSeguranca.retorna(result.getInt("C_numProcesso")), 
                result.getString("nomeAutor"), ClasseSeguranca.retorna(result.getInt("C_nomeAutor")), 
                        result.getString("nomeReu"), ClasseSeguranca.retorna(result.getInt("C_nomeReu")), 
                        result.getString("descricaoAuto"), ClasseSeguranca.retorna(result.getInt("C_descricaoAuto")),
                        result.getString("sentenca"), ClasseSeguranca.retorna(result.getInt("C_sentenca")),
                        ClasseSeguranca.retorna(result.getInt("TC")));
        
    }
}
