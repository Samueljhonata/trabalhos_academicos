
package modelo;

import java.util.Objects;

public class Processo {
    private String numProcesso;
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

    public Processo(String numProcesso, ClasseSeguranca C_numProcesso, String nomeAutor, ClasseSeguranca C_nomeAutor, String nomeReu, ClasseSeguranca C_nomeReu, String descricaoAuto, ClasseSeguranca C_descricaoAuto, String sentenca, ClasseSeguranca C_sentenca, ClasseSeguranca TC) {
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

    public Processo(String numProcesso, ClasseSeguranca C_numProcesso, String nomeAutor, ClasseSeguranca C_nomeAutor, String nomeReu, ClasseSeguranca C_nomeReu, String descricaoAuto, ClasseSeguranca C_descricaoAuto, String sentenca, ClasseSeguranca C_sentenca) {
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
        
        verificaTC();
        
        
    }

    public String getNumProcesso() {
        return numProcesso;
    }

    public void setNumProcesso(String numProcesso) {
        this.numProcesso = numProcesso;
    }

    public ClasseSeguranca getC_numProcesso() {
        return C_numProcesso;
    }

    public void setC_numProcesso(ClasseSeguranca C_numProcesso) {
        this.C_numProcesso = C_numProcesso;
        verificaTC();
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
        verificaTC();
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
        verificaTC();
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
        verificaTC();
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
        verificaTC();
    }

    public ClasseSeguranca getTC() {
        return TC;
    }

    private void verificaTC(){
        //verifica se a classe de segurança da tupla deve ser alterada
        int max = 0;
        if (this.C_numProcesso!=null && this.C_numProcesso.getNum() > max) {
            max = this.C_numProcesso.getNum();
        }
        if (this.C_nomeAutor!=null && this.C_nomeAutor.getNum() > max) {
            max = this.C_nomeAutor.getNum();
        }
        if (this.C_nomeReu!=null && this.C_nomeReu.getNum() > max) {
            max = this.C_nomeReu.getNum();
        }
        if (this.C_descricaoAuto!=null && this.C_descricaoAuto.getNum() > max) {
            max = this.C_descricaoAuto.getNum();
        }
        if (this.C_sentenca!=null && this.C_sentenca.getNum() > max) {
            max = this.C_sentenca.getNum();
        }
        
        this.TC = ClasseSeguranca.retorna(max);
    }

    public String mostraProcesso(TModo modo) {
        String retorno = "";
        
        
        retorno = "---------------------------------------------------------------------\n";
        if (C_numProcesso != null) {
            retorno += "No. Processo: " + numProcesso;
            if(modo == TModo.ANALITICO) {
                retorno += "(" + C_numProcesso.getCod() + ")\n";
            }else{
                retorno += "\n";
            }
        }
        if (C_nomeAutor != null) {
            retorno += "Autor: " + nomeAutor;
            if(modo == TModo.ANALITICO) {
                retorno +="(" + C_nomeAutor.getCod() + ")\n";
            }else{
                retorno += "\n";
            }
        }
        if (C_nomeReu != null) {
            retorno += "Reu: " + nomeReu;
            if(modo == TModo.ANALITICO) {
                retorno +="(" + C_nomeReu.getCod() + ")\n";
            }else{
                retorno += "\n";
            }
        }
        if (C_descricaoAuto != null) {
            retorno += "Auto: " + descricaoAuto;
            if(modo == TModo.ANALITICO) {
                retorno +="(" + C_descricaoAuto.getCod() + ")\n";
            }else{
                retorno += "\n";
            }
        }
        if (C_sentenca != null) {
            retorno += "Sentenca: " + sentenca;
            if(modo == TModo.ANALITICO) {
                retorno +="(" + C_sentenca.getCod() + ")\n";
            }else{
                retorno += "\n";
            }
        }
        if (TC != null && modo == TModo.ANALITICO) {
            retorno += "TC: " + TC;
        }
        return retorno;
    }
    
    public String mostraProcessoF() {
        return    "|  " + numProcesso + "(" + C_numProcesso.getCod() + ")\t|"
                + "  " + nomeAutor + "(" + C_nomeAutor.getCod() + ")\t|"
                + "  " + nomeReu + "(" + C_nomeReu.getCod() + ")\t|"
                + "  " + descricaoAuto + "(" + C_descricaoAuto.getCod() + ")\t|"
                + "  " + sentenca + "(" + C_sentenca.getCod() + ")\t|"
                + "TC: " + TC + "\t|"
                +"\n----------------------------------------------------------------------------------------";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.numProcesso);
        hash = 53 * hash + Objects.hashCode(this.TC);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Processo other = (Processo) obj;
        if (!Objects.equals(this.numProcesso, other.numProcesso)) {
            return false;
        }
        /*if (this.TC != other.TC) {
            return false;
        }*/
        return true;
    }

    @Override
    public Processo clone() throws CloneNotSupportedException {
        return new Processo(numProcesso, C_numProcesso, nomeAutor, C_nomeAutor, nomeReu, C_nomeReu, descricaoAuto, C_descricaoAuto, sentenca, C_sentenca);
    }
    
    
}
