package com.mycompany.personaltech;

import com.mycompany.personaltech.Endereco;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-14T09:51:47")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> senha;
    public static volatile SingularAttribute<Usuario, Endereco> endereco;
    public static volatile SingularAttribute<Usuario, String> cpf;
    public static volatile SingularAttribute<Usuario, String> nome;
    public static volatile SingularAttribute<Usuario, Long> id;
    public static volatile SingularAttribute<Usuario, String> sobrenome;
    public static volatile SingularAttribute<Usuario, String> login;
    public static volatile SingularAttribute<Usuario, String> sexo;
    public static volatile SingularAttribute<Usuario, Date> dataNascimento;
    public static volatile SingularAttribute<Usuario, String> email;

}