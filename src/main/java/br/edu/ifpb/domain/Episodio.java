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

    private String nome;

    @ManyToOne
    @JoinColumn(name = "TEMPORADA_ID")
    private Temporada temporada;

    private Boolean assistidoCheck = false;

    public Episodio() {

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Temporada getTemporada() {
        return temporada;
    }
    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public Boolean getAssistidoCheck() {
        return assistidoCheck;
    }
    public void setAssistidoCheck(Boolean assistidoCheck) {
        this.assistidoCheck = assistidoCheck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Episodio)) return false;
        Episodio episodio = (Episodio) o;
        return getId().equals(episodio.getId()) && getNome().equals(episodio.getNome()) && getTemporada().equals(episodio.getTemporada());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getTemporada());
    }
}