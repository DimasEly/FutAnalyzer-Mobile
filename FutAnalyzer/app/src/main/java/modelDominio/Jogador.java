package modelDominio;

import java.io.Serializable;

public class Jogador implements Serializable {
    private static final long serialVersionUID = 123L;
    private String nome;
    private int overall;
    private int gol;

    public int getGol() {
        return gol;
    }

    public void setGol(int gol) {
        this.gol = gol;
    }

    public Jogador(String nome, int overall, int gol){
        this.nome = nome;
        this.overall = overall;
        this.gol = gol;
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
}
