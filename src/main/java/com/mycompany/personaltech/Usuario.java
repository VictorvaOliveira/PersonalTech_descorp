/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltech;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author john
 */
@Entity
@Table(name = "TB_USUARIO")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TXT_TIPO_USUARIO",
        discriminatorType = DiscriminatorType.STRING, length = 1)
@NamedNativeQueries(
        {
            @NamedNativeQuery(
                    name = "Usuario.RetornaNome",
                    query = "SELECT TXT_NOME FROM TB_USUARIO WHERE ID = ?1"
            )
        }
)
public abstract class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotBlank
    @NotNull
    @Size(max = 50)
    @Column(name = "TXT_NOME", length = 50, nullable = false)
    private String nome;
    @NotBlank
    @NotNull
    @Size(max = 50)
    @Column(name = "TXT_SOBRENOME", length = 50, nullable = false)
    private String sobrenome;
    @CPF
    @Column(name = "TXT_CPF", length = 14, unique = true, nullable = false)
    private String cpf;
    @NotBlank
    @NotNull
    @Size(min = 6, max = 30)
    @Pattern(
            regexp = "((?=.*\\p{Lower}).{6,30})",
            message = "{com.mycompany.personaltech.Usuario.login}"
    )
    @Column(name = "TXT_LOGIN", length = 30, unique = true, nullable = false)
    private String login;
    
    // mensagem deve vir de um arquivo de configuração, olhar exemplo do github e aula
    @NotBlank
    @NotNull
    @Size(min = 6, max = 20)
    @Pattern(
            regexp = "((?=.*\\p{Digit})(?=.*\\p{Lower})(?=.*\\p{Upper})(?=.*\\p{Punct}).{6,20})",
            message = "{com.mycompany.personaltech.Usuario.senha}"
    )
    @Column(name = "TXT_SENHA", length = 20, nullable = false)
    private String senha;

    @Email
    @Column(name = "TXT_EMAIL", length = 50, nullable = false)
    private String email;

    @NotBlank
    @ValidaSexo
    @Size(min = 1, max = 1)
    @Column(name = "TXT_SEXO", length = 1, nullable = false)
    private String sexo;
    
    @Past
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_NASCIMENTO", nullable = true)
    private Date dataNascimento;

    @NotNull
    @Embedded
    private Endereco endereco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
