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
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author john
 */
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
    public void inserirAvaliacaoNew() {
        Avaliacao av = new Avaliacao();
        av.setAluno(em.find(Aluno.class, (long)1));

        av.setNome_personal("MARINA SILVA");
        setAvaliacao(av);        
        av.addPerguntas(em.find(Pergunta.class, (long)1));
        em.persist(av);
        // Falta Assert
    }
    
    @Test
    public void remAvaliacaoNew() {
        Avaliacao av = em.find(Avaliacao.class, (long)7);
        em.remove(av);
    }
    
//    @Test
//    public void inserirAvaliacao_01() {
//    public void test01() {
//        Avaliacao av = new Avaliacao();
//        setAvaliacao(av);
//        av.setNome_personal("EVA");
//        
//        Aluno aluno = em.find(Aluno.class, (long) 2);
//        aluno.addAvaliacao(av);
//        em.flush();
//        
//        assertNotNull(aluno.getId());
//    }
//
//    @Test
//    public void selecionarAvaliacao_04() {
////    public void test04() {
//        Avaliacao av = em.find(Avaliacao.class, (long) 7);
//        assertNotNull(av);
//    }
//    
//    @Test
//    public void alterarAvaliacao_05() {
////    public void test05() {
//        Avaliacao av = em.find(Avaliacao.class, (long) 1);
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.YEAR, 2017);
//        c.set(Calendar.MONTH, Calendar.JULY);
//        c.set(Calendar.DAY_OF_MONTH, 9);
//        av.setDataAvaliacao(c.getTime());
//        assertNotNull(av);
//        assertEquals(c.getTime(), av.getDataAvaliacao());
//    }
//    
//    @Test
//    public void rem() {
////    public void test05() {
//        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 2);
//        em.remove(pt);
//        em.flush();
//        em.clear();
//        assertEquals(em.find(PersonalTrainer.class, (long) 2), null);
//    }
//    
//
////    public void testarPerguntasDaAvaliacao_07() {
////    public void test07() {
////        Pergunta p = em.find(Pergunta.class, (long) 2);
////        logger.log(Level.INFO, "selecionarAlunoPorId: Pergunta {0}", p.toString());
////        assertNotNull(p);
////    }
////    @Test
////    public void retornarListaDeAvaliacoesPorPersonal_08() {
////    public void test08() {
////        logger.log(Level.INFO, "Contagem dos alunos vinculados ao Personal 2");
////        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 2);
////        List<Avaliacao> avaliacoes = pt.getAvaliacoes();
////        assertEquals(2, avaliacoes.size());
////    }
////    @Test
////    public void retornarListaDeAvaliacoesPorAluno_09() {
////    public void test09() {
////        logger.log(Level.INFO, "Contagem dos alunos vinculados ao Personal 1");
////        Aluno aluno = em.find(Aluno.class, (long) 1);
////        List<Avaliacao> avaliacoes = aluno.getAvaliacoes();
////        assertNotNull(avaliacoes);
////    }
////    @Test
////    public void removerAvaliacao_10() {
////    public void test10() {
////        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 2);
////        Avaliacao av = em.find(Avaliacao.class, (long) 1);
////        pt.removeAvaliacao(av);
////    }
////    @Test
////    public void removerAvaliacaoPorDelecaoDeAluno_11() {
////    public void test11() {
////        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 2);
////        Aluno aluno = em.find(Aluno.class, (long) 1);
////        pt.removeAluno(aluno);
////    }
    private void setAvaliacao(Avaliacao av) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2018);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DAY_OF_MONTH, 25);
        av.setDataAvaliacao(c.getTime());
    }
}
