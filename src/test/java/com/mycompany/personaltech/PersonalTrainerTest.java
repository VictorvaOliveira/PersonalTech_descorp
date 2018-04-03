package com.mycompany.personaltech;

import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
public class PersonalTrainerTest {

    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    public PersonalTrainerTest() {
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
    public void inserirPersonalTrainer_01() {
        PersonalTrainer pt = new PersonalTrainer();

        pt.setNome("Iron");
        pt.setSobrenome("Man");
        pt.setSexo("M");
        pt.setCpf("222-222-746-22");
        pt.setEmail("ironman@personaltech.com");
        pt.setLogin("iron92man");
        pt.setSenha("ironman123");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1992);
        c.set(Calendar.MONTH, Calendar.AUGUST);
        c.set(Calendar.DAY_OF_MONTH, 12);
        pt.setDataNascimento(c.getTime());

        pt.setTipo(TipoUsuario.PERSONAL_TRAINER);

        Endereco end = new Endereco();
        end.setBairro("Cordeiro");
        end.setCep("50000-000");
        end.setCidade("Recife");
        end.setComplemento("Aptº 101");
        end.setEstado("Pernambuco");
        end.setLogradouro("Avenida Caxanga");
        end.setNumero(101);
        pt.setEndereco(end);
        
        em.persist(pt);

    }

    @Test
    public void selecionarPersonalTrainerPorId() {
        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 4);
        assertNotNull(pt);
        assertEquals("VICTOR", pt.getNome());
        assertEquals("VICTOR98", pt.getLogin());
        logger.log(Level.INFO, "selecionarPersonalTrainerPorId: PT {0}", pt.toString());
    }

    @Test
    public void alterarPersonalTrainerPorId() {
        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 4);
        assertNotNull(pt);
        pt.setEmail("victor123@gmail.com");
        em.flush();
        em.clear();
        logger.log(Level.INFO, "alterarPersonalPorId", pt.toString());

        pt = em.find(PersonalTrainer.class, (long) 4);
//        assertNotNull(aluno);
        assertEquals("victor123@gmail.com", pt.getEmail());
    }

    @Test
    public void removerPersonalTrainerPorCPF() {
        
        TypedQuery<PersonalTrainer> query = em.createNamedQuery("", PersonalTrainer.class);
        query.setParameter("cpf", "222-222-746-22");
        PersonalTrainer pt = query.getSingleResult();
        assertNotNull(pt);
        em.remove(pt);
        em.flush();
        assertEquals(0, query.getResultList().size());
        
        pt = em.find(PersonalTrainer.class, (String)"222-222-746-22");
        assertNull(pt);
    }
    @Test
    public void removerPersonalTrainerPorId() {
        PersonalTrainer pt = em.find(PersonalTrainer.class,(long)4);
        assertNotNull(pt);
        em.remove(pt);
        em.flush();
        em.clear();
        
        pt = em.find(PersonalTrainer.class, (long) 4);
        assertNull(pt);
    }


    @Test
    public void quantidadePersonalTrainer() {
        TypedQuery<Long> query = em.createQuery("SELECT count(pt.id) FROM PersonalTrainer pt", Long.class);
        
        Long quantidade = query.getSingleResult();
        assertEquals(new Long(4), quantidade);
    }

//    @Test
//    public void quantidadeALunoPorExercicio() {
//
//    }
}
