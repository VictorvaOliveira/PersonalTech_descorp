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

//    /**
//     * Test of getId method, of class Aluno.
//     */
//    @Test
//    public void inserirPersonalTrainer_01() {
//        logger.log(Level.INFO, "Inserção do Personal");
//
//        PersonalTrainer pt = new PersonalTrainer();
//        setPt(pt);
//        em.persist(pt);
//        em.flush();
//        PersonalTrainer p = em.find(PersonalTrainer.class, (long) 7);
//
//        assertEquals(pt, p);
//    }
    @Test
    public void a() {
        Aluno a = new Aluno();
        a.setNome("Jubileu");
        a.setSobrenome("Silva");
        
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2018);
        c.set(Calendar.MONTH, Calendar.JULY);
        c.set(Calendar.DAY_OF_MONTH, 24);
        
        a.setDataNascimento(c.getTime());
        a.setCpf("00346246273");
        a.setLogin("jubileu");
        a.setSenha("12345");
        a.setEmail("jubinha@gmail.com");
        a.setSexo("M");
        
        Endereco end = new Endereco();
        end.setLogradouro("Miramar");
        end.setBairro("Miro");
        end.setNumero(765);
        end.setCep("123123");
        end.setCidade("Recife");
        end.setEstado("PE");
        
        
        a.setEndereco(end);
        
        em.persist(a);
        
    }
    
    @Test
    public void b() {
        PersonalTrainer a = new PersonalTrainer();
        a.setNome("Julian");
        a.setSobrenome("Sousa");
        
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2018);
        c.set(Calendar.MONTH, Calendar.JULY);
        c.set(Calendar.DAY_OF_MONTH, 24);
        
        a.setDataNascimento(c.getTime());
        a.setCpf("00346246212");
        a.setLogin("julian");
        a.setSenha("12345");
        a.setEmail("jubinha@gmail.com");
        a.setSexo("M");
        
        Endereco end = new Endereco();
        end.setLogradouro("Miramar");
        end.setBairro("Miro");
        end.setNumero(765);
        end.setCep("123123");
        end.setCidade("Recife");
        end.setEstado("PE");
        
        
        a.setEndereco(end);
        
        em.persist(a);
        
    }
    
    @Test
    public void c() {
        Aluno a = em.find(Aluno.class, (long)37);
        em.remove(a);
        assertEquals(null, em.find(Aluno.class, (long)37));
    }

    @Test
    public void alterarPersonalTrainerPorId() {
//        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 4);
//        assertNotNull(pt);
//        pt.setEmail("victor123@gmail.com");
//        em.flush();
//        em.clear();
//        logger.log(Level.INFO, "alterarPersonalPorId", pt.toString());
//        
//        pt = em.find(PersonalTrainer.class, (long) 4);
//        assertNotNull(pt);
//        assertEquals("victor123@gmail.com", pt.getEmail());
    }

    @Test
    public void selecionarPersonalTrainerPorId() {
//        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 4);
//        assertNotNull(pt);
//        assertEquals("VICTOR", pt.getNome());
//        assertEquals("VICTOR98", pt.getLogin());
//        logger.log(Level.INFO, "selecionarPersonalTrainerPorId: PT {0}", pt.toString());
    }

    @Test
    public void removerPersonalTrainerPorId() {
//        logger.log(Level.INFO, "Remoção do Personal id = 4");
//        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 4);
//        
//        assertNotNull(pt);
//        em.remove(pt);
//        em.flush();
//        em.clear();
//        
//        pt = em.find(PersonalTrainer.class, (long) 4);
//        assertNull(pt);
    }

//    @Test
//    public void coletarAlunos_01() {
//        logger.log(Level.INFO, "Contagem dos alunos vinculados ao Personal 3");
//        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 3);
//        List<Aluno> a = pt.getAlunos();
//        assertEquals(5, a.size());
//    }
//
//    @Test
//    public void coletarAluno_02() {
//        logger.log(Level.INFO, "Contagem dos alunos vinculados ao Personal 4");
//        PersonalTrainer pt = em.find(PersonalTrainer.class, (long) 4);
//        assertNotNull(pt);
//        List<Aluno> a = pt.getAlunos();
//        assertEquals(5, a.size());
//    }
//    
//    private void setPt(PersonalTrainer pt) {
//        pt.setNome("IRON");
//        pt.setSobrenome("MAN");
//        pt.setSexo("M");
//        pt.setCpf("222-222-746-22");
//        pt.setEmail("ironman@personaltech.com");
//        pt.setLogin("IRON92MAN");
//        pt.setSenha("IRONMAN123");
//        
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.YEAR, 1992);
//        c.set(Calendar.MONTH, Calendar.AUGUST);
//        c.set(Calendar.DAY_OF_MONTH, 12);
//        pt.setDataNascimento(c.getTime());
//        pt.setTipo(TipoUsuario.PERSONAL_TRAINER);
//        
//        Endereco end = new Endereco();
//        end.setBairro("CORDEIRO");
//        end.setCep("50000-000");
//        end.setCidade("RECFIFE");
//        end.setComplemento("Aptº 101");
//        end.setEstado("PERNAMBUCO");
//        end.setLogradouro("AV CAXANGA");
//        end.setNumero(101);
//        pt.setEndereco(end);
//    }
}
