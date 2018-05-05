package com.mycompany.personaltech;

import com.mycompany.personaltech.RespostasAvaliacao;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-04T22:23:33")
@StaticMetamodel(Pergunta.class)
public class Pergunta_ { 

    public static volatile SingularAttribute<Pergunta, String> pergunta;
    public static volatile SingularAttribute<Pergunta, Long> id;
    public static volatile ListAttribute<Pergunta, RespostasAvaliacao> respostas;

}