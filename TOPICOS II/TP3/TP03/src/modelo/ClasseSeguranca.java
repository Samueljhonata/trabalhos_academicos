
package modelo;

public enum ClasseSeguranca {
    NAO_CLASSIFICADA(0,"U"),
    CONFIDENCIAL(1,"C"),
    SECRETA(2,"S"),
    ALTAMENTE_SECRETA(3,"AS");
    
    private int num;
    private String cod;

    private ClasseSeguranca(int num, String cod) {
        this.num = num;
        this.cod = cod;
    }

    public int getNum() {
        return num;
    }

    public String getCod() {
        return cod;
    }
    
    
}
