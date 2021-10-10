package br.edu.ifpb.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "EPISODIO")
public class Episodio implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(min = 1, message = "Digite um número para o episódio")
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "TEMPORADA_ID")
    private Temporada temporada;

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
}
