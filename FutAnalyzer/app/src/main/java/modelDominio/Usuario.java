package modelDominio;

import java.io.Serializable;

public class Usuario implements Serializable {
    public static final long serialVersionUID = 321L;
    private String senha;
    private String usuario;
    private String email;
    private int cod;

    public Usuario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public Usuario(String senha, String usuario, String email) {
        this.senha = senha;
        this.usuario = usuario;
        this.email = email;
    }

    public Usuario(String usuario, String senha, String email, int cod) {
        this.senha = senha;
        this.usuario = usuario;
        this.email = email;
        this.cod = cod;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    @Override
    public String toString() {
        return "Usuario{" +  ", senha=" + senha + ", usuario=" + usuario + ", email=" + email + ", cod=" + cod + '}';
    }
}
