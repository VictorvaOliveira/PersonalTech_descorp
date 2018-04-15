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
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ALUNO")
@DiscriminatorValue(value = "A")
@PrimaryKeyJoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
public class Aluno extends Usuario implements Serializable {

    @ElementCollection
    @CollectionTable(name = "TB_TELEFONE_ALUNO",
            joinColumns = @JoinColumn(name = "ID_ALUNO", nullable = false))
    @Column(name = "TXT_NUM_TELEFONE", nullable = false, length = 20)
    private Collection<String> telefones;

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
        this.exercicios = exercicios;//errado! corrigir!
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;//errado! corrigir
        //para cada avaliação da lista recebida, invocar add para uma única avaliação
        //for (Avaliacao avaliacao : avaliacoes) {
        //    addAvaliacao(avaliacao);
        //}
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
        return "TOSTRING ALUNO";
    }

}
