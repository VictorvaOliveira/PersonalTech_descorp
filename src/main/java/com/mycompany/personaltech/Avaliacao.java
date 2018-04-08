package com.mycompany.personaltech;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_AVALIACAO")
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "DT_AVALIACAO", nullable = false, unique = false)
    private Date dataAvaliacao;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_AVALIACAO", referencedColumnName = "ID")
    private List<RespostasAvaliacao> respostas;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_ALUNO", referencedColumnName = "ID")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_PT", referencedColumnName = "ID")
    private PersonalTrainer personalTrainer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public PersonalTrainer getPersonalTrainer() {
        return personalTrainer;
    }

    public void setPersonalTrainer(PersonalTrainer personalTrainer) {
        this.personalTrainer = personalTrainer;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public List<RespostasAvaliacao> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<RespostasAvaliacao> respostas) {
        this.respostas = respostas;
    }

    public Date getdataAvaliacao() {
        return dataAvaliacao;
    }

    public void setdataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public void addRespostaAvaliacao(RespostasAvaliacao resposta) {
        if (this.respostas == null) {
            this.respostas = new ArrayList<>();
        }
        this.respostas.add(resposta);
    }

    public void removeRespostaAvaliacao(RespostasAvaliacao resposta) {
        if (this.respostas == null) {
            return;
        }
        this.respostas.remove(resposta);
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
        if (!(object instanceof Avaliacao)) {
            return false;
        }
        Avaliacao other = (Avaliacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.personaltech.Avaliacao[ id=" + id + " ]";
    }

}
