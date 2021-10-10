package br.edu.ifpb.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "SERIE")
public class Serie implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(min = 6, max = 16, message = "Digite um nome que tenha entre 6 ou 16 caracteres")
    private String nome;

    @OneToMany(mappedBy = "serie")
    private Set<Temporada> temporadas = new HashSet<>();

    public Serie() {

    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Temporada> getTemporadas() {
        return temporadas;
    }
    public void setTemporadas(Set<Temporada> temporadas) {
        this.temporadas = temporadas;
    }
}
