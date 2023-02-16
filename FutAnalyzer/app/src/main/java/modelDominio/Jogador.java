package modelDominio;

import java.io.Serializable;

public class Jogador implements Serializable {
    private static final long serialVersionUID = 123L;

    private String nome;
    private int overall;
    private int gol;
    private int cod; // id

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    private int idUsuario;

    public int getGol() {
        return gol;
    }

    public void setGol(int gol) {
        this.gol = gol;
    }

    public Jogador(String nome, int overall, int gol, int idUsuario){
        this.nome = nome;
        this.overall = overall;
        this.gol = gol;
        this.idUsuario = idUsuario;
    }

    public Jogador(int cod, String nome, int overall, int gol, int idUsuario){
        this.cod = cod;
        this.nome = nome;
        this.overall = overall;
        this.gol = gol;
        this.idUsuario = idUsuario;
    }

    public Jogador(int cod, String nome, int overall) {
        this.cod = cod;
        this.nome = nome;
        this.overall = overall;
    }

    public Jogador(String nome, int overall) {
        this.nome = nome;
        this.overall = overall;

    }



    public Jogador(int cod) {
        this.cod = cod;
    }



    public Jogador(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}

