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
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TB_PERSONALTRAINER")
@DiscriminatorValue(value = "P")
@PrimaryKeyJoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
public class PersonalTrainer extends Usuario implements Serializable {
    
    @Size(max = 5)
    @ElementCollection
    @CollectionTable(name = "TB_TELEFONE_PT",
            joinColumns = @JoinColumn(name = "ID_PERSONALTRAINER", nullable = false))
    @Column(name = "TXT_NUM_TELEFONE", nullable = false, length = 20)
    private Collection<String> telefones;
    
    @Size(max = 100)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_PT",referencedColumnName = "ID_USUARIO")
    private List<Aluno> alunos;

    public Collection<String> getTelefones() {
        return telefones;
    }

    public void addTelefone(String telefone) {
        if (this.telefones == null) {
            this.telefones = new HashSet<>();
        }
        telefones.add(telefone);
    }

    public void addAluno(Aluno aluno) {
        if (this.alunos == null) {
            this.alunos = new ArrayList<>();
        }
        alunos.add(aluno);
    }

    public void removeAluno(Aluno aluno) {
        if (aluno == null) {
            return;
        }
        this.alunos.remove(aluno);
    }

    public void setTelefones(Collection<String> telefones) {
        for (String telefone : telefones) {
            this.telefones.add(telefone);
        }
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
    
    

    @Override
    public String toString() {
        return "com.mycompany.personaltech.Exercicio[ id=" + id + " ]";
    }

}
