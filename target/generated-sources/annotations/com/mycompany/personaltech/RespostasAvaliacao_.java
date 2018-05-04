package com.mycompany.personaltech;

import com.mycompany.personaltech.Avaliacao;
import com.mycompany.personaltech.Pergunta;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-03T21:35:50")
@StaticMetamodel(RespostasAvaliacao.class)
public class RespostasAvaliacao_ { 

    public static volatile SingularAttribute<RespostasAvaliacao, Pergunta> pergunta;
    public static volatile SingularAttribute<RespostasAvaliacao, String> txt_resposta;
    public static volatile SingularAttribute<RespostasAvaliacao, Long> id;
    public static volatile SingularAttribute<RespostasAvaliacao, Avaliacao> avaliacao;

}