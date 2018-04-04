package com.mycompany.personaltech;

import com.mycompany.personaltech.Aluno;
import com.mycompany.personaltech.Avaliacao;
import com.mycompany.personaltech.Endereco;
import com.mycompany.personaltech.TipoUsuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-03T20:28:12")
=======
<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-03T18:46:08")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-03T20:04:36")
>>>>>>> master
>>>>>>> dev_victor
@StaticMetamodel(PersonalTrainer.class)
public class PersonalTrainer_ { 

    public static volatile SingularAttribute<PersonalTrainer, TipoUsuario> tipo;
    public static volatile SingularAttribute<PersonalTrainer, Endereco> endereco;
    public static volatile ListAttribute<PersonalTrainer, Aluno> alunos;
    public static volatile SingularAttribute<PersonalTrainer, String> nome;
    public static volatile SingularAttribute<PersonalTrainer, String> login;
    public static volatile CollectionAttribute<PersonalTrainer, String> telefones;
    public static volatile SingularAttribute<PersonalTrainer, String> senha;
    public static volatile ListAttribute<PersonalTrainer, Avaliacao> avaliacoes;
    public static volatile SingularAttribute<PersonalTrainer, String> cpf;
    public static volatile SingularAttribute<PersonalTrainer, Long> id;
    public static volatile SingularAttribute<PersonalTrainer, String> sobrenome;
    public static volatile SingularAttribute<PersonalTrainer, String> sexo;
    public static volatile SingularAttribute<PersonalTrainer, Date> dataNascimento;
    public static volatile SingularAttribute<PersonalTrainer, String> email;

}