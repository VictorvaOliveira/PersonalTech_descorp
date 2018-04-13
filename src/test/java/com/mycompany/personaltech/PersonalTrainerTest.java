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
import javax.persistence.TypedQuery;
import jdk.nashorn.internal.objects.NativeArray;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
    public void inserirPersonalTech_01() {
        PersonalTrainer pt = new PersonalTrainer();
        pt.setNome("Jubileu");
        pt.setSobrenome("Silva");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2018);
        c.set(Calendar.MONTH, Calendar.JULY);
        c.set(Calendar.DAY_OF_MONTH, 24);

        pt.setDataNascimento(c.getTime());
        pt.setCpf("00346246273");
        pt.setLogin("jubileu");
        pt.setSenha("12345");
        pt.setEmail("jubinha@gmail.com");
        pt.setSexo("M");

        Endereco end = new Endereco();
        end.setLogradouro("Miramar");
        end.setBairro("Miro");
        end.setNumero(765);
        end.setCep("123123");
        end.setCidade("Recife");
        end.setEstado("PE");

        pt.setEndereco(end);

        em.persist(pt);

    }

    @Test
    public void inserirPersonalTech_02() {
        PersonalTrainer pt = new PersonalTrainer();
        pt.setNome("Julian");
        pt.setSobrenome("Sousa");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2018);
        c.set(Calendar.MONTH, Calendar.JULY);
        c.set(Calendar.DAY_OF_MONTH, 24);

        pt.setDataNascimento(c.getTime());
        pt.setCpf("00346246212");
        pt.setLogin("julian");
        pt.setSenha("12345");
        pt.setEmail("jubinha@gmail.com");
        pt.setSexo("M");

        Endereco end = new Endereco();
        end.setLogradouro("Miramar");
        end.setBairro("Miro");
        end.setNumero(765);
        end.setCep("123123");
        end.setCidade("Recife");
        end.setEstado("PE");

        pt.setEndereco(end);

        em.persist(pt);

    }

    @Test
    public void removerPersonalTech_01() {
        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 25);
        em.remove(pt);
        assertEquals(null, em.find(PersonalTrainer.class, (long) 25));
    }

    @Test
    public void removerPersonalTech_02() {
        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 26);
        em.remove(pt);
        assertEquals(null, em.find(PersonalTrainer.class, (long) 26));
    }

    @Test
    public void selecionarPersonalTrainerPorId_01() {
        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 14);
        assertNotNull(pt);
        assertEquals("VICTOR", pt.getNome());
        logger.log(Level.INFO, "selecionarPersonalTrainerPorId: PT {0}", pt.toString());
    }
    @Test
    public void selecionarPersonalTrainerPorId_02() {
        Usuario pt = em.find(Usuario.class, (long)13 );
        assertNotNull(pt);
        assertEquals("THOR", pt.getNome());
        assertEquals("THORP", pt.getLogin());
        logger.log(Level.INFO, "selecionarPersonalTrainerPorId: PT {0}", pt.toString());
    }

    @Test
    public void alterarPersonalTrainerPorId() {
        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 14);
        assertNotNull(pt);
        pt.setEmail("victor123@gmail.com");
        em.flush();
        em.clear();
        logger.log(Level.INFO, "alterarPersonalPorId", pt.toString());

        pt = em.find(PersonalTrainer.class, (long) 14);
        assertNotNull(pt);
        assertEquals("victor123@gmail.com", pt.getEmail());
    } 
}
