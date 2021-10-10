package br.edu.ifpb.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "TEMPORADA")
public class Temporada implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 6, message = "Digite um nome que contenho no mínimo 6 caracteres")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "SERIE_ID")
    private Serie serie;

    @OneToMany(mappedBy = "temporada")
    private Set<Episodio> episodios = new HashSet<>();

    public Temporada() {

    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Serie getSerie() {
        return serie;
    }
    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Set<Episodio> getEpisodios() {
        return episodios;
    }
    public void setEpisodios(Set<Episodio> episodios) {
        this.episodios = episodios;
    }
}