package com.mycompany.personaltech;

import com.mycompany.personaltech.Aluno;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-06T23:31:49")
@StaticMetamodel(PersonalTrainer.class)
public class PersonalTrainer_ extends Usuario_ {

    public static volatile ListAttribute<PersonalTrainer, Aluno> alunos;
    public static volatile CollectionAttribute<PersonalTrainer, String> telefones;

}