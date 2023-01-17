package modelDominio;

import java.io.Serializable;

public class Usuario implements Serializable {
    public static final long serialVersionUID = 321L;
    private String nome;
    private String senha;
    private String usuario;
    private String email;

    public Usuario(String senha, String usuario) {
        this.senha = senha;
        this.usuario = usuario;
    }

    public Usuario(String nome, String senha, String usuario) {
        this.nome = nome;
        this.senha = senha;
        this.usuario = usuario;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }
}
