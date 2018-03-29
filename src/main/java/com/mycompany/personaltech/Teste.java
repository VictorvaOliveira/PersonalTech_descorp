package com.mycompany.personaltech;

import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Teste {
    // estes teste passaram
    public static void main(String[] args) {
        Usuario user = new Usuario();
        preencherUsuario(user);
        preencherExercicio(user);

        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction et;

        try {
            emf = Persistence.createEntityManagerFactory("PersonalTech_PU");
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            em.persist(user);
            et.commit();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }

    private static void preencherUsuario(Usuario user) {
        user.setNome("Fulano");
        user.setSobrenome("da Silva");
        user.setEmail("fulano@gmail.com");
        user.setLogin("fulano3");
        user.setSenha("teste");
        user.setCpf("534.585.764-47");
        user.addTelefone("(81) 3456-2525");
        user.addTelefone("(81) 9122-4528");
        user.setTipo(TipoUsuario.ALUNO);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1993);
        c.set(Calendar.MONTH, Calendar.FEBRUARY);
        c.set(Calendar.DAY_OF_MONTH, 25);
        user.setDataNascimento(c.getTime());

        preencherEndereco(user);
    }

    private static void preencherEndereco(Usuario user) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua Iolanda Rodrigues Sobral");
        endereco.setBairro("Iputinga");
        endereco.setCidade("Recife");
        endereco.setEstado("Pernambuco");
        endereco.setCep("50690-220");
        endereco.setNumero(550);
        endereco.setComplemento("Perto do Satanás");
        user.setEndereco(endereco);
    }

    private static void preencherExercicio(Usuario user) {
        Exercicio exercicio1 = new Exercicio();
        exercicio1.setTipo(TipoExercicio.ABDOMINAIS);
        exercicio1.setExercicio(NomeExercicio.ABD_BOSU);
        user.addExercicio(exercicio1);
        
        Exercicio exercicio2 = new Exercicio();
        exercicio2.setTipo(TipoExercicio.BICEPS);
        exercicio2.setExercicio(NomeExercicio.BICEPS_90_ROSCA_SIMULTANEA);
        user.addExercicio(exercicio2);
    }
}
