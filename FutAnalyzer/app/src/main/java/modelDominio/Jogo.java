package modelDominio;

import java.io.Serializable;

public class Jogo implements Serializable {
    private static final long serialVersionUID = 123L;

    private int id;
    private int meuPlacar;
    private int advPlacar;
    private int idUsuario;

    public Jogo(int id, int meuPlacar, int advPlacar, int idUsuario) {
        this.id = id;
        this.meuPlacar = meuPlacar;
        this.advPlacar = advPlacar;
        this.idUsuario = idUsuario;
    }

    public Jogo(int meuPlacar, int advPlacar, int idUsuario) {
        this.meuPlacar = meuPlacar;
        this.advPlacar = advPlacar;
        this.idUsuario = idUsuario;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMeuPlacar() {
        return meuPlacar;
    }

    public void setMeuPlacar(int meuPlacar) {
        this.meuPlacar = meuPlacar;
    }

    public int getAdvPlacar() {
        return advPlacar;
    }

    public void setAdvPlacar(int advPlacar) {
        this.advPlacar = advPlacar;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Jogo{" +
                "id=" + id +
                ", meuPlacar=" + meuPlacar +
                ", advPlacar=" + advPlacar +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
