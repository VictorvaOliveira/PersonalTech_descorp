/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltech;

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

    /**
     * Test of getId method, of class Aluno.
     */
    @Test
    public void inserirExercicio_01() {
        Exercicio ex = new Exercicio();
        ex.setExercicio(NomeExercicio.ABD_BOSU);
        ex.setTipo(TipoExercicio.ABDOMINAIS);

        Aluno al = new Aluno();
        al = em.find(Aluno.class, (long) 22);
        al.addExercicio(ex);

        em.persist(ex);

    }

    @Test
    public void selecionarExercicio_01() {
        Exercicio ex = em.find(Exercicio.class, (long) 5);
        assertNotNull(ex);
        logger.log(Level.INFO, "selecionarExercicio: Exercicio {0}", ex.toString());
    }

    @Test
    public void alterarExercicio_01() {
        Exercicio ex = em.find(Exercicio.class, (long) 14);
        assertNotNull(ex);
        ex.setExercicio(NomeExercicio.ABD_INFRA_PRANCHA_ABD);
        ex.setTipo(TipoExercicio.ABDOMINAIS);
        em.flush();
        em.clear();
    }

    @Test
    public void removerExercicio_01() {
        Exercicio ex = em.find(Exercicio.class, (long) 10);
        assertNotNull(ex);
        em.remove(ex);
        assertNull(em.find(Exercicio.class, (long) 10));
    }
}
