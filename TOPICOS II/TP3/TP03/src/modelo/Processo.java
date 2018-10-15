
package modelo;

public class Processo {
    private int numProcesso;
    private ClasseSeguranca C_numProcesso;
    private String nomeAutor;
    private ClasseSeguranca C_nomeAutor;
    private String nomeReu;
    private ClasseSeguranca C_nomeReu;
    private String descricaoAuto;
    private ClasseSeguranca C_descricaoAuto;
    private String sentenca;
    private ClasseSeguranca C_sentenca;
    private ClasseSeguranca TC;

    public Processo(int numProcesso, ClasseSeguranca C_numProcesso, String nomeAutor, ClasseSeguranca C_nomeAutor, String nomeReu, ClasseSeguranca C_nomeReu, String descricaoAuto, ClasseSeguranca C_descricaoAuto, String sentenca, ClasseSeguranca C_sentenca, ClasseSeguranca TC) {
        this.numProcesso = numProcesso;
        this.C_numProcesso = C_numProcesso;
        this.nomeAutor = nomeAutor;
        this.C_nomeAutor = C_nomeAutor;
        this.nomeReu = nomeReu;
        this.C_nomeReu = C_nomeReu;
        this.descricaoAuto = descricaoAuto;
        this.C_descricaoAuto = C_descricaoAuto;
        this.sentenca = sentenca;
        this.C_sentenca = C_sentenca;
        this.TC = TC;
    }

    public Processo(int numProcesso, ClasseSeguranca C_numProcesso, String nomeAutor, ClasseSeguranca C_nomeAutor, String nomeReu, ClasseSeguranca C_nomeReu, String descricaoAuto, ClasseSeguranca C_descricaoAuto, String sentenca, ClasseSeguranca C_sentenca) {
        int max = 0;
        this.numProcesso = numProcesso;
        this.C_numProcesso = C_numProcesso;
        this.nomeAutor = nomeAutor;
        this.C_nomeAutor = C_nomeAutor;
        this.nomeReu = nomeReu;
        this.C_nomeReu = C_nomeReu;
        this.descricaoAuto = descricaoAuto;
        this.C_descricaoAuto = C_descricaoAuto;
        this.sentenca = sentenca;
        this.C_sentenca = C_sentenca;
        
        if (this.C_numProcesso.getNum() > max) {
            max = this.C_numProcesso.getNum();
            this.TC = this.C_numProcesso;
        }
        if (this.C_nomeAutor.getNum() > max) {
            max = this.C_nomeAutor.getNum();
            this.TC = this.C_nomeAutor;
        }
        if (this.C_nomeReu.getNum() > max) {
            max = this.C_nomeReu.getNum();
            this.TC = this.C_nomeReu;
        }
        if (this.C_descricaoAuto.getNum() > max) {
            max = this.C_descricaoAuto.getNum();
            this.TC = this.C_descricaoAuto;
        }
        if (this.C_sentenca.getNum() > max) {
            max = this.C_sentenca.getNum();
            this.TC = this.C_sentenca;
        }
        
        
    }

    public int getNumProcesso() {
        return numProcesso;
    }

    public void setNumProcesso(int numProcesso) {
        this.numProcesso = numProcesso;
    }

    public ClasseSeguranca getC_numProcesso() {
        return C_numProcesso;
    }

    public void setC_numProcesso(ClasseSeguranca C_numProcesso) {
        this.C_numProcesso = C_numProcesso;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public ClasseSeguranca getC_nomeAutor() {
        return C_nomeAutor;
    }

    public void setC_nomeAutor(ClasseSeguranca C_nomeAutor) {
        this.C_nomeAutor = C_nomeAutor;
    }

    public String getNomeReu() {
        return nomeReu;
    }

    public void setNomeReu(String nomeReu) {
        this.nomeReu = nomeReu;
    }

    public ClasseSeguranca getC_nomeReu() {
        return C_nomeReu;
    }

    public void setC_nomeReu(ClasseSeguranca C_nomeReu) {
        this.C_nomeReu = C_nomeReu;
    }

    public String getDescricaoAuto() {
        return descricaoAuto;
    }

    public void setDescricaoAuto(String descricaoAuto) {
        this.descricaoAuto = descricaoAuto;
    }

    public ClasseSeguranca getC_descricaoAuto() {
        return C_descricaoAuto;
    }

    public void setC_descricaoAuto(ClasseSeguranca C_descricaoAuto) {
        this.C_descricaoAuto = C_descricaoAuto;
    }

    public String getSentenca() {
        return sentenca;
    }

    public void setSentenca(String sentenca) {
        this.sentenca = sentenca;
    }

    public ClasseSeguranca getC_sentenca() {
        return C_sentenca;
    }

    public void setC_sentenca(ClasseSeguranca C_sentenca) {
        this.C_sentenca = C_sentenca;
    }

    public ClasseSeguranca getTC() {
        return TC;
    }

    public void setTC(ClasseSeguranca TC) {
        this.TC = TC;
    }
    
    
    
    
}
