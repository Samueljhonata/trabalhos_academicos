package modelo;

public enum TModo {
    ANALITICO("ANALITICO"), USUAL("USUAL");

    private String nome;

    private TModo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
