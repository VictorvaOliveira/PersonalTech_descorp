package com.mycompany.personaltech;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB_ALUNO")
@DiscriminatorValue(value = "A")
@PrimaryKeyJoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Aluno.PorNome",
                    query = "SELECT a FROM Aluno a WHERE a.nome LIKE :nome ORDER BY a.id"
            ),
                @NamedQuery(
                    name = "Aluno.PorTipoDeExercicio",
                    query = "SELECT DISTINCT a FROM Aluno a JOIN a.exercicios xs WHERE xs.tipo = :ex"
            )
        }
)
public class Aluno extends Usuario implements Serializable {

    @Size(max = 5)
    @ElementCollection
    @CollectionTable(name = "TB_TELEFONE_ALUNO",
            joinColumns = @JoinColumn(name = "ID_ALUNO", nullable = false))
    @Column(name = "TXT_NUM_TELEFONE", nullable = false, length = 20)
    private Collection<String> telefones;
    
    @Size(max = 10)
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_ALUNO", referencedColumnName = "ID_USUARIO")
    private List<Exercicio> exercicios;
    
    @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes;

    public List<Exercicio> getExercicios() {
        return exercicios;
    }

    public void addExercicio(Exercicio exercicio) {
        if (this.exercicios == null) {
            this.exercicios = new ArrayList<>();
        }
        this.exercicios.add(exercicio);
    }

    public void removeExercicio(Exercicio exercicio) {
        if (this.exercicios == null) {
            return;
        }
        this.exercicios.remove(exercicio);
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

    public void setTelefones(Collection<String> telefones) {
        this.telefones = telefones;
    }

    public void setExercicios(List<Exercicio> exercicios) {
        // corrigido
        for (Exercicio exercicio : exercicios) {
            exercicios.add(exercicio);
        }
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        // corrigido
        for (Avaliacao avaliacao : avaliacoes) {
            addAvaliacao(avaliacao);
        }
    }

    public void addAvaliacao(Avaliacao avaliacao) {
        if (this.avaliacoes == null) {
            this.avaliacoes = new ArrayList<>();
        }
        this.avaliacoes.add(avaliacao);
        avaliacao.setAluno(this);
    }

    public void removeAvaliacao(Avaliacao avaliacao) {
        if (this.avaliacoes == null) {
            return;
        }
        this.avaliacoes.remove(avaliacao);
    }

    @Override
    public String toString() {
        return "com.mycompany.personaltech.Aluno[ id=" + id + " ]";
    }

}
