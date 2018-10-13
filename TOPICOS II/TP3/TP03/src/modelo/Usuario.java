package modelo;

public class Usuario {
    private String user;
    private String senha;
    private ClasseSeguranca classeSeguranca;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ClasseSeguranca getClasseSeguranca() {
        return classeSeguranca;
    }

    public void setClasseSeguranca(ClasseSeguranca classeSeguranca) {
        this.classeSeguranca = classeSeguranca;
    }
    
}
