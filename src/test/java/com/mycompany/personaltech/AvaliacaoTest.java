package com.mycompany.personaltech;

import java.util.Calendar;
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

/**
 *
 * @author john
 */
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
    public void inserirAvaliacao_01() {
        Aluno aluno = em.find(Aluno.class, (long)2);
        em.flush();
        Avaliacao av = new Avaliacao();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2018);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DAY_OF_MONTH, 25);
        av.setdataAvaliacao(c.getTime());
        aluno.addAvaliacao(av);
        PersonalTrainer p = new PersonalTrainer();
        p = em.find(PersonalTrainer.class, (long) 1);
        p.addAvaliacao(av);
        em.flush();
        em.clear();
        assertNotNull(aluno.getId());
    }
    
    @Test
    public void inserirAvaliacao_02() {
        Aluno aluno = em.find(Aluno.class, (long)2);
        em.flush();
        Avaliacao av = new Avaliacao();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2018);
        c.set(Calendar.MONTH, Calendar.FEBRUARY);
        c.set(Calendar.DAY_OF_MONTH, 25);
        av.setdataAvaliacao(c.getTime());
        aluno.addAvaliacao(av);   
        PersonalTrainer p = new PersonalTrainer();
        p = em.find(PersonalTrainer.class, (long) 1);
        p.addAvaliacao(av);
        em.flush();
        em.clear();
        assertNotNull(aluno.getId());
    }

    @Test
    public void selecionarAvaliacao_01() {
        // TODO
    }

    @Test
    public void alterarAvaliacao_01() {
        // TODO
    }

    @Test
    public void removerAvaliacao_01() {
        // TODO
    }

}
