package com.mycompany.personaltech;

import com.mycompany.personaltech.RespostasAvaliacao;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-05T09:40:52")
@StaticMetamodel(Avaliacao.class)
public class Avaliacao_ { 

    public static volatile SingularAttribute<Avaliacao, Date> dataAvaliacao;
    public static volatile SingularAttribute<Avaliacao, Long> id;
    public static volatile ListAttribute<Avaliacao, RespostasAvaliacao> respostas;

}