package br.edu.ifpb.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "EPISODIO")
public class Episodio implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "TEMPORADA_ID")
    private Temporada temporada;

    private Boolean assistidoCheck;

    public Episodio() {

    }

    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Temporada getTemporada() {
        return temporada;
    }
    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Episodio)) return false;
        Episodio episodio = (Episodio) o;
        return id.equals(episodio.id) && getNumero().equals(episodio.getNumero()) && getTemporada().equals(episodio.getTemporada());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getNumero(), getTemporada());
    }
}