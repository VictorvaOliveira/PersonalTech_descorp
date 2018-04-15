package com.mycompany.personaltech;

import com.mycompany.personaltech.Avaliacao;
import com.mycompany.personaltech.Exercicio;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-14T09:51:47")
@StaticMetamodel(Aluno.class)
public class Aluno_ extends Usuario_ {

    public static volatile ListAttribute<Aluno, Exercicio> exercicios;
    public static volatile ListAttribute<Aluno, Avaliacao> avaliacoes;
    public static volatile CollectionAttribute<Aluno, String> telefones;

}