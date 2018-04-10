package com.mycompany.personaltech;

import com.mycompany.personaltech.Aluno;
import com.mycompany.personaltech.PersonalTrainer;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-09T21:58:21")
@StaticMetamodel(Avaliacao.class)
public class Avaliacao_ { 

    public static volatile SingularAttribute<Avaliacao, Aluno> aluno;
    public static volatile SingularAttribute<Avaliacao, PersonalTrainer> personalTrainer;
    public static volatile SingularAttribute<Avaliacao, Date> dataAvaliacao;
    public static volatile SingularAttribute<Avaliacao, Long> id;

}