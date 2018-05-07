package com.mycompany.personaltech;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
@Table(name = "TB_EXERCICIO")
@NamedNativeQueries(
        {
            @NamedNativeQuery(
                    name = "Exercicio.RetornaTipoExercicio",
                    query = "SELECT ex.ID, ex.TXT_NOME_EXERCICIO, ex.TXT_TIPO_EXERCICIO, a.TXT_NOME mapa FROM TB_EXERCICIO ex JOIN TB_USUARIO a ON ex.ID_ALUNO = a.ID WHERE ex.ID = ?1",
                    resultSetMapping = "mapping"
            )
        }
)
@SqlResultSetMapping(
name = "mapping",
        entities = {
            @EntityResult(entityClass = Exercicio.class)
        },
        columns = {
            @ColumnResult(name = "mapa", type = String.class)
        }
)
public class Exercicio implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TXT_TIPO_EXERCICIO", length = 20, nullable = true)
    private TipoExercicio tipo;
    @Enumerated(EnumType.STRING)
    @Column(name = "TXT_NOME_EXERCICIO", length = 50, nullable = true)
    private NomeExercicio exercicio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoExercicio getTipo() {
        return tipo;
    }

    public void setTipo(TipoExercicio tipo) {
        this.tipo = tipo;
    }

    public NomeExercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(NomeExercicio exercicio) {
        this.exercicio = exercicio;
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
        if (!(object instanceof Exercicio)) {
            return false;
        }
        Exercicio other = (Exercicio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.personaltech.Exercicio[ id=" + id + " ]";
    }    
}
