package com.mycompany.personaltech;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_ALUNO")
public class Aluno implements Serializable {

//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TXT_NOME", length = 100, nullable = false)
    private String nome;
    @Column(name = "TXT_SOBRENOME", length = 100, nullable = false)
    private String sobrenome;
    @Column(name = "TXT_CPF", length = 14, unique = true, nullable = false)
    private String cpf;
    @Column(name = "TXT_LOGIN", length = 50, unique = true, nullable = false)
    private String login;
    @Column(name = "TXT_SENHA", length = 20, nullable = false)
    private String senha;
    @Column(name = "TXT_EMAIL", length = 50, nullable = false)
    private String email;
    @Column(name = "TXT_SEXO", length = 1, nullable = false)
    private String sexo;

    @Temporal(TemporalType.DATE)
    @Column(name = "DT_NASCIMENTO", nullable = true)
    private Date dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "TXT_TIPO_USUARIO", length = 20, nullable = true)
    private TipoUsuario tipo;
    
    @ElementCollection
    @CollectionTable(name = "TB_TELEFONE_ALUNO",
            joinColumns = @JoinColumn(name = "ID_ALUNO", nullable = false))
    @Column(name = "TXT_NUM_TELEFONE", nullable = false, length = 20)
    private Collection<String> telefones;

    @Embedded // Mapeada na Classe Endereco
    private Endereco endereco;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_ALUNO", referencedColumnName = "ID")
    private List<Exercicio> exercicios;

    @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes;

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

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public List<Exercicio> getExercicios() {
        return exercicios;
    }

    public void addExercicio(Exercicio exercicio) {
        if (this.exercicios == null) {
            this.exercicios = new ArrayList<>();
        }
        this.exercicios.add(exercicio);
    }

    public Collection<String> getTelefones() {
        return telefones;
    }

    public void addTelefone(String telefone) {
        if (this.telefones == null) {
            this.telefones = new HashSet<>();
        }
        telefones.add(telefone);
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

    public void setTelefones(Collection<String> telefones) {
        this.telefones = telefones;
    }

    public void setExercicios(List<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
    
    public void addAvaliacao(Avaliacao avaliacao) {
        if (this.avaliacoes == null) {
            this.avaliacoes = new ArrayList<>();
        }
        this.avaliacoes.add(avaliacao);
        avaliacao.setAluno(this);
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
        if (!(object instanceof Aluno)) {
            return false;
        }
        Aluno other = (Aluno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.id + " -> " + this.nome;
    }

}
