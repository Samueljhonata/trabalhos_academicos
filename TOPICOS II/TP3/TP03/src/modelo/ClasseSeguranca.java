package modelo;

public enum ClasseSeguranca {
    NAO_CLASSIFICADA(0, "U"),
    CONFIDENCIAL(1, "C"),
    SECRETA(2, "S"),
    ALTAMENTE_SECRETA(3, "AS");

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

    public static ClasseSeguranca retorna(int num) {
        switch (num) {
            case 0:
                return ClasseSeguranca.NAO_CLASSIFICADA;
            case 1:
                return ClasseSeguranca.CONFIDENCIAL;
            case 2:
                return ClasseSeguranca.SECRETA;
            case 3:
                return ClasseSeguranca.ALTAMENTE_SECRETA;
        }
        return null;
    }
    
    public static ClasseSeguranca retorna(String sigla) {
        sigla = sigla.replaceAll("'", "").replaceAll(";", "").toUpperCase();
        System.out.println("SIGLA:" + sigla);
        switch (sigla) {
            case "U":
                return ClasseSeguranca.NAO_CLASSIFICADA;
            case "C":
                return ClasseSeguranca.CONFIDENCIAL;
            case "S":
                return ClasseSeguranca.SECRETA;
            case "AS":
                return ClasseSeguranca.ALTAMENTE_SECRETA;
        }
        return null;
    }

}
