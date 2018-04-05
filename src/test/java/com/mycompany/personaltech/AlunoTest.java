/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltech;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
        // Coment teste branch
        Aluno aluno = em.find(Aluno.class, (long) 23);
        assertNotNull(aluno);
        assertEquals("MICHEL", aluno.getNome());
        assertEquals("05842569855", aluno.getCpf());
        logger.log(Level.INFO, "selecionarAlunoPorId: Aluno {0}", aluno.toString());
    }

    @Test
    public void atualizarAluno() {
        // Coment teste branch
        Aluno aluno = em.find(Aluno.class, (long) 1);
        assertNotNull(aluno);
        aluno.setEmail("zoiao.com");
        em.flush();
        em.clear();
        logger.log(Level.INFO, "selecionarAlunoPorId: Aluno {0}", aluno.toString());

        aluno = em.find(Aluno.class, (long) 1);
//        assertNotNull(aluno);
        assertEquals("zoiao.com", aluno.getEmail());
    }
    @Test
    public void selecionarAlunoPorCPF() {
        // Coment teste branch
        String jpql = "SELECT a FROM Aluno a where a.cpf = ?1";
        Query query = em.createQuery(jpql);
        query.setParameter(1, "111.222.333-42");

        Aluno aluno = (Aluno) query.getSingleResult();

        assertEquals("111.222.333-42", aluno.getCpf());
        logger.log(Level.INFO, "selecionarAlunoPorId: Aluno {0}", aluno.toString());
    }


    @Test
    public void inserirAluno() {
        // Coment teste branch
        Aluno aluno = new Aluno();
        aluno.setNome("Jurubeba");
        aluno.setSobrenome("Alcoolica");
        aluno.setCpf("123-321-436-12");
        aluno.setLogin("juba");
        aluno.setSenha("123");
        aluno.setEmail("juba@gmail");
        aluno.setSexo("M");
        aluno.addTelefone("111 222 333");
        
        Exercicio ex = new Exercicio();
        ex.setExercicio(NomeExercicio.COXAS_45_STIFF_BARRA);
        ex.setTipo(TipoExercicio.COXAS);
        aluno.addExercicio(ex);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2000);
        c.set(Calendar.MONTH, Calendar.AUGUST);
        c.set(Calendar.DAY_OF_MONTH, 27);
        aluno.setDataNascimento(c.getTime());

        aluno.setTipo(TipoUsuario.ALUNO);

        Endereco end = new Endereco();
        end.setLogradouro("Rua do Cordeiro");
        end.setBairro("Cordeiro");
        end.setNumero(666);
        end.setCep("123456-88");
        end.setCidade("Recife");
        end.setEstado("Pernambuco");

        aluno.setEndereco(end);

        PersonalTrainer pt = new PersonalTrainer();
        pt = em.find(PersonalTrainer.class, (long) 1);
        pt.addAluno(aluno);
        em.flush();
        em.clear();

        assertNotNull(aluno.getId());
    }

    @Test
    public void inserirAluno_02() {
        // Coment teste branch
        Aluno aluno = new Aluno();
        aluno.setNome("Jurubeba2");
        aluno.setSobrenome("Alcoolica");
        aluno.setCpf("123-321-416-13");
        aluno.setLogin("juba2asd");
        aluno.setSenha("123");
        aluno.setEmail("juba@gmail");
        aluno.setSexo("M");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2000);
        c.set(Calendar.MONTH, Calendar.AUGUST);
        c.set(Calendar.DAY_OF_MONTH, 27);
        aluno.setDataNascimento(c.getTime());

        aluno.setTipo(TipoUsuario.ALUNO);

        Endereco end = new Endereco();
        end.setLogradouro("Rua do Cordeiro");
        end.setBairro("Cordeiro");
        end.setNumero(666);
        end.setCep("123456-88");
        end.setCidade("Recife");
        end.setEstado("Pernambuco");

        aluno.setEndereco(end);

        PersonalTrainer pt = new PersonalTrainer();
        pt = em.find(PersonalTrainer.class, (long) 1);
        pt.addAluno(aluno);
        em.flush();
        em.clear();

        assertNotNull(aluno.getId());
    }
    
    @Test
    public void deletarAluno_01() {
        Aluno aluno = em.find(Aluno.class, (long) 3);
        assertNotNull(aluno);
        em.remove(aluno);
        em.flush();
        em.clear();
        aluno = em.find(Aluno.class, (long) 3);
        assertNull(aluno);
    }

    @Test
    public void deletarAluno_02() {
        Aluno aluno = em.find(Aluno.class, (long) 2);
        assertNotNull(aluno);
        PersonalTrainer pt = new PersonalTrainer();
        pt = em.find(PersonalTrainer.class, (long) 1);
        pt.removeAluno(aluno);
    }
}
