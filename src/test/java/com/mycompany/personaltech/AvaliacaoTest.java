package com.mycompany.personaltech;

import java.util.Calendar;
import java.util.List;
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
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AvaliacaoTest {

    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    public AvaliacaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        logger = Logger.getGlobal();
        logger.setLevel(Level.INFO);

        emf = Persistence.createEntityManagerFactory("PersonalTech_PU");

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
    public void inserirAvaliacao_01() {
        Avaliacao av = new Avaliacao();
        setAvaliacao(av);
        av.setNome_personal("EVA");

        Avaliacao av2 = new Avaliacao();
        setAvaliacao(av2);
        av2.setNome_personal("EVA");

        Aluno aluno = em.find(Aluno.class, (long) 3);
        aluno.addAvaliacao(av);
        aluno.addAvaliacao(av2);
        em.flush();
        Aluno aluno2 = em.find(Aluno.class, (long) 3);
        assertEquals(aluno.getAvaliacoes().size(), 2);
    }

    @Test
    public void inserirAvaliacao_02() {
        Avaliacao av = new Avaliacao();
        setAvaliacao(av);
        av.setNome_personal("EVA");

        Aluno aluno = em.find(Aluno.class, (long) 4);
        aluno.addAvaliacao(av);
        em.flush();

        assertNotNull(aluno.getId());
    }

    @Test
    public void removeAvaliacao_01() {

        Avaliacao av = em.find(Avaliacao.class, (long) 3);
        em.remove(av);
        em.flush();
        av = em.find(Avaliacao.class, (long) 3);
        assertNull(av);
    }

    @Test
    public void removerAvaliacaoPorAluno_01() {
        Aluno aluno = em.find(Aluno.class, (long) 5);
        aluno.removeAvaliacao(aluno.getAvaliacoes().get(0));
        aluno.removeAvaliacao(aluno.getAvaliacoes().get(0));
        em.persist(aluno);
        aluno = em.find(Aluno.class, (long) 5);
        int size = aluno.getAvaliacoes().size();
        assertEquals(0, size);
    }

    @Test
    public void removerAvaliacaoPorAluno_02() {
        Aluno aluno = em.find(Aluno.class, (long) 15);
        em.remove(aluno);
        Avaliacao avaliacao = em.find(Avaliacao.class, (long) 1);
        assertNull(avaliacao);
    }

    @Test
    public void selecionarAvaliacoes() {
        String jpql = "SELECT a FROM Avaliacao a";
        Query query = em.createQuery(jpql);

        List<Avaliacao> avaliacao = query.getResultList();

        assertNotNull(avaliacao);

    }

    private void setAvaliacao(Avaliacao av) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2018);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DAY_OF_MONTH, 25);
        av.setDataAvaliacao(c.getTime());
    }
}
