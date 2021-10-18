package br.edu.ifpb.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "SERIE")
public class Serie implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    @OneToMany(mappedBy = "serie")
    private Set<Temporada> temporadas = new HashSet<>();

    public Serie() {

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

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Temporada> getTemporadas() {
        return temporadas;
    }
    public void setTemporadas(Set<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Serie)) return false;
        Serie serie = (Serie) o;
        return getId().equals(serie.getId()) && getNome().equals(serie.getNome()) && getUsuario().equals(serie.getUsuario());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getUsuario());
    }
}