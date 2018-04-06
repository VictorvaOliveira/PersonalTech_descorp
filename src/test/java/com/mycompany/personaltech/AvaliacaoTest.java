package com.mycompany.personaltech;

import java.util.Calendar;
import java.util.Date;
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
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

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
        Aluno aluno = em.find(Aluno.class, (long) 2);
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
        Aluno aluno = em.find(Aluno.class, (long) 2);
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
    public void inserirAvaliacao_03() {
        Aluno aluno = em.find(Aluno.class, (long) 5);
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
    public void selecionarAvaliacao_04() {
        Avaliacao av = em.find(Avaliacao.class, (long) 1);
        assertNotNull(av);
    }

    @Test
    public void alterarAvaliacao_05() {
        Avaliacao av = em.find(Avaliacao.class, (long) 1);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2017);
        c.set(Calendar.MONTH, Calendar.JULY);
        c.set(Calendar.DAY_OF_MONTH, 9);
        av.setDataAvaliacao(c.getTime());
        assertNotNull(av);
        assertEquals(c.getTime(), av.getDataAvaliacao());
    }

    @Test
    public void alterarAvaliacao_06() {
        Avaliacao av = em.find(Avaliacao.class, (long) 2);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2017);
        c.set(Calendar.MONTH, Calendar.AUGUST);
        c.set(Calendar.DAY_OF_MONTH, 9);
        av.setDataAvaliacao(c.getTime());
        assertNotNull(av);
        assertEquals(c.getTime(), av.getDataAvaliacao());
    }

    @Test
    public void testarPerguntasDaAvaliacao_07() {
        Pergunta p = em.find(Pergunta.class, (long) 2);
        logger.log(Level.INFO, "selecionarAlunoPorId: Pergunta {0}", p.toString());
        assertNotNull(p);
    }

    @Test
    public void retornarListaDeAvaliacoesPorPersonal_08() {
        logger.log(Level.INFO, "Contagem dos alunos vinculados ao Personal 2");
        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 2);
        List<Avaliacao> avaliacoes = pt.getAvaliacoes();
        assertEquals(setDate().toString(),
                avaliacoes.get(0).getDataAvaliacao().toString());
        assertEquals(2, avaliacoes.size());
    }

    @Test
    public void retornarListaDeAvaliacoesPorAluno_09() {
        logger.log(Level.INFO, "Contagem dos alunos vinculados ao Personal 1");
        Aluno aluno = em.find(Aluno.class, (long) 1);
        List<Avaliacao> avaliacoes = aluno.getAvaliacoes();
        assertNotNull(avaliacoes);
    }

//    @Test
//    public void removerAvaliacao_10() {
//        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 2);
//        Avaliacao av = em.find(Avaliacao.class, (long) 1);
//        pt.removeAvaliacao(av);
//    }
//
//    @Test
//    public void removerAvaliacaoPorDelecaoDeAluno_11() {
//        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 2);
//        Aluno aluno = em.find(Aluno.class, (long) 1);
//        pt.removeAluno(aluno);
//    }

    private Date setDate() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1930);
        c.set(Calendar.MONTH, Calendar.NOVEMBER);
        c.set(Calendar.DAY_OF_MONTH, 02);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

}
