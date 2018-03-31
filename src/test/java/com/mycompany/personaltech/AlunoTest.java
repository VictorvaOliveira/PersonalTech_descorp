/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltech;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author john
 */
public class AlunoTest {

    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    public AlunoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        logger = Logger.getGlobal();
        logger.setLevel(Level.INFO);
        //logger.setLevel(Level.SEVERE);
        emf = Persistence.createEntityManagerFactory("PersonalTech_PU");
//        emf.createEntityManager();
        DbUnitUtil.inserirDados();
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        em = emf.createEntityManager();
        beginTransaction();
    }

    @After
    public void tearDown() {
        commitTransaction();
        em.close();
    }

    private void beginTransaction() {
        et = em.getTransaction();
        et.begin();
    }

    private void commitTransaction() {
        try {
            et.commit();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            if (et.isActive()) {
                et.rollback();
            }
            fail(ex.getMessage());
        }
    }

    /**
     * Test of getId method, of class Aluno.
     */
    @Test
    public void selecionarAlunoPorId() {
        Aluno aluno = em.find(Aluno.class, (long) 2);
        assertNotNull(aluno);
        assertEquals("JOAO", aluno.getNome());
        assertEquals("111.222.333-43", aluno.getCpf());
        logger.log(Level.INFO, "selecionarAlunoPorId: Aluno {0}", aluno.toString());
    }

    @Test
    public void atualizarAluno() {
        Aluno aluno = em.find(Aluno.class, (long) 1);
        assertNotNull(aluno);
//        logger.log(Level.INFO, "selecionarAlunoPorId: Aluno {0}", aluno.toString());
        aluno.setNome("ZEBRA");
        em.flush();
        em.clear();

        aluno = em.find(Aluno.class, (long) 1);
//        assertNotNull(aluno);
        assertEquals("ZEBRA", aluno.getNome());
    }

    @Test
    public void pegarAluno() {
        String jpql = "SELECT a FROM Aluno a where a.cpf = ?1";
        Query query = em.createQuery(jpql);
        query.setParameter(1, "111.222.333-42");

        Aluno usuario = (Aluno) query.getSingleResult();

        assertEquals("111.222.333-42", usuario.getCpf());
    }

    @Test
    public void deletarAluno() {
        Aluno aluno = em.find(Aluno.class, (long) 2);
        assertNotNull(aluno);
        em.remove(aluno);
        em.flush();
        em.clear();
        aluno = em.find(Aluno.class, (long) 2);
        assertNull(aluno);
    }

    @Test
    public void inserirAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome("Jurubeba");
        aluno.setSobrenome("Alcoolica");
        aluno.setCpf("123-321-436-12");
        aluno.setLogin("juba");
        aluno.setSenha("123");
        aluno.setEmail("juba@gmail");

        Endereco end = new Endereco();
        end.setLogradouro("Rua do Cordeiro");
        end.setBairro("Cordeiro");
        end.setNumero(666);
        end.setCep("123456-88");
        end.setCidade("Recife");
        end.setEstado("Pernambuco");

        aluno.setEndereco(end);

        em.persist(aluno);
        em.flush();
        assertNotNull(aluno.getId());
    }
}
