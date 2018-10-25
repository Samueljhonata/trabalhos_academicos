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
    public int compare(Processo o1, Processo o2) {
        if (o1.getNumProcesso().compareTo(o2.getNumProcesso()) < 0){
            return -1;
        }
        if (o1.getNumProcesso().compareTo(o2.getNumProcesso()) > 0){
            return 1;
        }
        
        if (o1.getTC().getNum() > o2.getTC().getNum()) {
            return -1;
        }
        return 1;
    }
    
    /*public static void main(String[] args) {
        Processo p1 = new Processo("0", ClasseSeguranca.CONFIDENCIAL, null, ClasseSeguranca.CONFIDENCIAL, null, ClasseSeguranca.CONFIDENCIAL, null, ClasseSeguranca.CONFIDENCIAL, null, ClasseSeguranca.CONFIDENCIAL);
        Processo p2 = new Processo("0", ClasseSeguranca.SECRETA, null, ClasseSeguranca.CONFIDENCIAL, null, ClasseSeguranca.CONFIDENCIAL, null, ClasseSeguranca.CONFIDENCIAL, null, ClasseSeguranca.CONFIDENCIAL);
        Processo p3 = new Processo("0", ClasseSeguranca.ALTAMENTE_SECRETA, null, ClasseSeguranca.CONFIDENCIAL, null, ClasseSeguranca.CONFIDENCIAL, null, ClasseSeguranca.CONFIDENCIAL, null, ClasseSeguranca.CONFIDENCIAL);
        
        ArrayList<Processo> l = new ArrayList<>();
        l.add(p1);
        l.add(p2);
        l.add(p3);
        
    }*/
    
    public static void main(String[] args) {
        String sql = "UPDATE processo SET numProcesso='52', nomeReu='35' WHERE a=5";
        String quebra = sql.split("SET")[1].split("WHERE")[0];
            if (quebra.contains("numProcesso")) {
                String numProcesso = quebra.split("numProcesso")[1].split("'")[1];
                System.out.println(numProcesso);
            }
            
            if (quebra.contains("nomeReu")) {
                String nomeReu = quebra.split("nomeReu")[1].split("'")[1];
                System.out.println(nomeReu);
            }
    }
    
}
