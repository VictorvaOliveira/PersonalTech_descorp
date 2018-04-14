package com.mycompany.personaltech;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExercicioTest {

    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    public ExercicioTest() {
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

    @Test
    public void inserirExercicio_01() {
        Exercicio ex = new Exercicio();
        ex.setExercicio(NomeExercicio.ABD_BOSU);
        ex.setTipo(TipoExercicio.ABDOMINAIS);

        Aluno al = em.find(Aluno.class, (long) 22);
        al.addExercicio(ex);

        em.persist(al);
    }

    @Test
    public void inserirExercicio_02() {
        Aluno aluno = new Aluno();
        aluno.setNome("CUCA");
        aluno.setSobrenome("RECA");
        aluno.setCpf("123-321-466-66");
        aluno.setLogin("CUCAA");
        aluno.setSenha("123");
        aluno.setEmail("KELLY@gmail");
        aluno.setSexo("M");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2000);
        c.set(Calendar.MONTH, Calendar.AUGUST);
        c.set(Calendar.DAY_OF_MONTH, 27);
        aluno.setDataNascimento(c.getTime());

        Endereco end = new Endereco();
        end.setLogradouro("RUA DO CORDEIRO");
        end.setBairro("CORDEIRO");
        end.setNumero(666);
        end.setCep("123456-88");
        end.setCidade("RECIFE");
        end.setEstado("PERNAMBUCO");
        aluno.setEndereco(end);

        Exercicio ex = new Exercicio();
        ex.setExercicio(NomeExercicio.BICEPS_ROSCA_LIFE);
        ex.setTipo(TipoExercicio.BICEPS);
        aluno.addExercicio(ex);

        em.persist(aluno);
        em.flush();
        assertNotNull(em.find(Exercicio.class, (long) ex.getId()));
    }

    @Test
    public void selecionarExercicio_01() {
        Exercicio ex = em.find(Exercicio.class, (long) 5);
        assertNotNull(ex);
        logger.log(Level.INFO, "selecionarExercicio: Exercicio {0}", ex.toString());
    }

    @Test
    public void selecionarExercicio_02() {
        Aluno al = em.find(Aluno.class, (long) 21);
        List<Exercicio> ex = al.getExercicios();
        assertNotNull(ex);
        assertEquals(ex.size(), 4);
        logger.log(Level.INFO, "selecionarExercicio: Exercicio {0}", ex.toString());
    }

    @Test
    public void alterarExercicio_01() {
        Exercicio ex = em.find(Exercicio.class, (long) 14);
        assertNotNull(ex);
        ex.setExercicio(NomeExercicio.ABD_INFRA_PRANCHA_ABD);
        ex.setTipo(TipoExercicio.ABDOMINAIS);
        em.persist(ex);
        em.flush();
        assertEquals(NomeExercicio.ABD_INFRA_PRANCHA_ABD, ex.getExercicio());
        assertEquals(TipoExercicio.ABDOMINAIS, ex.getTipo());
    }

    @Test
    public void alterarExercicio_02() {
        Exercicio ex = em.find(Exercicio.class, (long) 12);
        assertNotNull(ex);
        ex.setExercicio(NomeExercicio.BICEPS_ROSCA_SCOTT_HALT_90_ROSCA_SCOTT_UNI);
        ex.setTipo(TipoExercicio.BICEPS);
        em.persist(ex);
        em.flush();
        assertEquals(NomeExercicio.BICEPS_ROSCA_SCOTT_HALT_90_ROSCA_SCOTT_UNI, ex.getExercicio());
        assertEquals(TipoExercicio.BICEPS, ex.getTipo());
    }

    @Test
    public void removerExercicio_01() {
        Exercicio ex = em.find(Exercicio.class, (long) 10);
        assertNotNull(ex);
        em.remove(ex);
        em.flush();
        assertNull(em.find(Exercicio.class, (long) 10));
    }

    @Test
    public void removerExercicio_02() {
        Aluno al = em.find(Aluno.class, (long) 15);
        Exercicio ex = al.getExercicios().get(0);
        assertNotNull(ex);
        if (ex != null) {
            al.removeExercicio(ex);
            em.persist(al);
            em.flush();
        }
        al = em.find(Aluno.class, (long) 15);
        int size = al.getExercicios().size();
        assertEquals(1, size);
    }
}
