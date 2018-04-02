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
        pt.setCpf("111-111-111-11");
        pt.setEmail("ironman@personaltech.com");
        pt.setLogin("iron92man");
        pt.setSenha("ironman123");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1992);
        c.set(Calendar.MONTH, Calendar.AUGUST);
        c.set(Calendar.DAY_OF_MONTH, 12);
        pt.setDataNascimento(c.getTime());

        pt.setTipo(TipoUsuario.PERSONAL_TRAINER);

        List<String> telefone = null;
        telefone.add("1234-5678");
        pt.setTelefones(telefone);

        Endereco end = new Endereco();
        end.setBairro("Cordeiro");
        end.setCep("50000-000");
        end.setCidade("Recife");
        end.setComplemento("Aptº 101");
        end.setEstado("Pernambuco");
        end.setLogradouro("Avenida Caxanga");
        end.setNumero(101);
        pt.setEndereco(end);

        pt.setAvaliacoes();

        pt.setAlunos();

    }

    @Test
    public void selecionarPersonalTrainer_01() {
        // TODO
    }

    @Test
    public void alterarPersonalTrainer_01() {
        // TODO
    }

    @Test
    public void removerPersonalTrainer_01() {
        // TODO
    }
}
