package br.edu.ifpb.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "USUARIO")
public class Usuario implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(min = 6, max = 16, message = "Digite um nome que tenha entre 6 ou 16 caracteres")
    private String nome;

    @NotBlank(message = "Preenchimento obrigatório")
    @Email(message = "Digite um email válido")
    private String email;

    @NotBlank(message = "Preenchimento obrigatório")
    @Size(min = 6, max = 16, message = "Digite uma senha que tenha entre 6 ou 16 caracteres")
    private String senha;

    public Usuario() {

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
}
