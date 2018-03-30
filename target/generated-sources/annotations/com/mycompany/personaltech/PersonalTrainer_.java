package com.mycompany.personaltech;

import com.mycompany.personaltech.Endereco;
import com.mycompany.personaltech.TipoUsuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-30T20:21:07")
@StaticMetamodel(PersonalTrainer.class)
public class PersonalTrainer_ { 

    public static volatile SingularAttribute<PersonalTrainer, String> senha;
    public static volatile SingularAttribute<PersonalTrainer, TipoUsuario> tipo;
    public static volatile SingularAttribute<PersonalTrainer, Endereco> endereco;
    public static volatile SingularAttribute<PersonalTrainer, String> cpf;
    public static volatile SingularAttribute<PersonalTrainer, String> nome;
    public static volatile SingularAttribute<PersonalTrainer, Long> id;
    public static volatile SingularAttribute<PersonalTrainer, String> sobrenome;
    public static volatile SingularAttribute<PersonalTrainer, String> login;
    public static volatile SingularAttribute<PersonalTrainer, Date> dataNascimento;
    public static volatile CollectionAttribute<PersonalTrainer, String> telefones;
    public static volatile SingularAttribute<PersonalTrainer, String> email;

}