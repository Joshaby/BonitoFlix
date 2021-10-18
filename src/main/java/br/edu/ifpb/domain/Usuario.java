package br.edu.ifpb.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "USUARIO")
public class Usuario implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String email;

    private String senha;

    @OneToMany(mappedBy = "usuario")
    private Set<Serie> series = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Serie> temporada = new HashSet<>();

    public Usuario() {

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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return getId().equals(usuario.getId()) && getNome().equals(usuario.getNome()) && getEmail().equals(usuario.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getEmail());
    }
}