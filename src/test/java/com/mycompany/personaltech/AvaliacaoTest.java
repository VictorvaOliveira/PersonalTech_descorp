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

    @Test
    public void inserirAvaliacao_01() {
        // avaliação 1
        Avaliacao av = new Avaliacao();
        av.setNome_personal("VICTOR");

        // avaliação 2
        Avaliacao av2 = new Avaliacao();
        av2.setNome_personal("VICTOR");

        Aluno aluno = em.find(Aluno.class, (long) 3);// CARL
        aluno.addAvaliacao(av);
        aluno.addAvaliacao(av2);
        em.flush();

        Aluno aluno2 = em.find(Aluno.class, (long) 3);

        assertEquals(aluno.getAvaliacoes().size(), 2);
    }

    @Test
    public void inserirAvaliacao_02() {
        // avaliação 1
        Avaliacao av = new Avaliacao();
        av.setNome_personal("VICTOR");

        Aluno aluno = em.find(Aluno.class, (long) 4);
        aluno.addAvaliacao(av);
        em.flush();

        assertNotNull(av.getId());
        assertNotNull(av.getDataAvaliacao());
    }

    @Test
    public void removeAvaliacao_01() {
        //Remove uma avaliação sem deletar o aluno
        Avaliacao av = em.find(Avaliacao.class, (long) 5);
        Aluno aluno = av.getAluno();
        em.remove(av);
        em.flush();
        em.clear();
        av = em.find(Avaliacao.class, (long) 5);
        assertNull(av);
        assertNotNull(em.find(Aluno.class, aluno.getId()));
    }

    @Test
    public void removerAvaliacaoPorAluno_01() {
        // Remover uma avaliação via aluno
        Aluno aluno = em.find(Aluno.class, (long) 5); // RICARDO
        aluno.removeAvaliacao(aluno.getAvaliacoes().get(0)); // remove o primeiro exercicio da lista
        em.persist(aluno);
        aluno = em.find(Aluno.class, (long) 5);
        int size = aluno.getAvaliacoes().size();
        assertEquals(0, size);
    }

    @Test
    public void removerAlunoCascadeAvaliacoes_02() {
        // Ao remover aluno, avaliações deste devem ser removidas
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
}
