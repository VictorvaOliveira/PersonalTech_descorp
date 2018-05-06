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
        aluno.removeAvaliacao(aluno.getAvaliacoes().get(0));
        em.persist(aluno);
        aluno = em.find(Aluno.class, (long) 5);
        int size = aluno.getAvaliacoes().size();
        assertEquals(0, size);
    }

    @Test
    public void removerAvaliacaoPorAluno_02() {
        // Ao remover aluno, avaliações devem ser deletadas
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
    
    @Test
    public void UpdateAvaliacaoDate_01() {
        Query query = em.createQuery("UPDATE Avaliacao a SET a.dataAvaliacao = :strNewDate WHERE a.dataAvaliacao = :strOldDate");
        
        Date newDate = setNewDate(1993, 7, 6); //1993-AUG-16 *7 is August because the month index is 0 based
        query.setParameter("strNewDate", newDate);
        
        Date oldDate = setOldNewDate(1980, 10, 2); // 1980-NOV-02
        query.setParameter("strOldDate", oldDate);
        
        query.executeUpdate();
        
//        Precisa ou não?
//        commitTransaction();
//        beginTransaction();
        
        Query newQuery = em.createQuery("SELECT av.dataAvaliacao FROM Avaliacao av WHERE av.id = :idAval");
        newQuery.setParameter("idAval", 3);
        Date resultDate = (Date) newQuery.getSingleResult();
        
        assertEquals(newDate.getYear(), resultDate.getYear());
    }
    
    
    
    
    // Métodos Auxiliares
    private Date setNewDate(int ano, int mes, int dia) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.DAY_OF_MONTH, dia);
        Date newDate = c.getTime();
        return newDate;
    }

    private Date setOldNewDate(int ano, int mes, int dia) {
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.YEAR, ano);
        c1.set(Calendar.MONTH, mes);
        c1.set(Calendar.DAY_OF_MONTH, dia);
        Date oldDate = c1.getTime();
        return oldDate;
    }
}
