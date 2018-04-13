package com.mycompany.personaltech;

import java.util.Calendar;
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

public class AlunoTest {

    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    public AlunoTest() {
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
    public void inserirAluno_01() {
        Aluno aluno = new Aluno();
        aluno.setNome("KELLY");
        aluno.setSobrenome("GÜIÇA");
        aluno.setCpf("123-321-416-13");
        aluno.setLogin("KELLY");
        aluno.setSenha("123");
        aluno.setEmail("KELLY@gmail");
        aluno.setSexo("F");

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2000);
        c.set(Calendar.MONTH, Calendar.AUGUST);
        c.set(Calendar.DAY_OF_MONTH, 27);
        aluno.setDataNascimento(c.getTime());

        Endereco end = new Endereco();
        end.setLogradouro("RUA DO CORDEIRO");
        end.setBairro("CORDEIRO");
        end.setNumero(666);
        end.setCep("123456-88");
        end.setCidade("RECIFE");
        end.setEstado("PERNAMBUCO");

        aluno.setEndereco(end);

        em.persist(aluno);

        em.flush();
        assertNotNull(em.find(Aluno.class, (long) 37));

    }

    @Test
    public void inserirAluno_02() {
        Aluno aluno = new Aluno();
        aluno.setNome("KEN");
        aluno.setSobrenome("GALINDA");
        aluno.setCpf("123-321-436-12");
        aluno.setLogin("KENNYG");
        aluno.setSenha("123");
        aluno.setEmail("KENNYH@gmail");
        aluno.setSexo("F");
        aluno.addTelefone("111 222 333");

        Exercicio ex = new Exercicio();
        ex.setExercicio(NomeExercicio.COXAS_45_STIFF_BARRA);
        ex.setTipo(TipoExercicio.COXAS);
        aluno.addExercicio(ex);

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2000);
        c.set(Calendar.MONTH, Calendar.AUGUST);
        c.set(Calendar.DAY_OF_MONTH, 27);
        aluno.setDataNascimento(c.getTime());

        Endereco end = new Endereco();
        end.setLogradouro("RUA DO CORDEIRO");
        end.setBairro("CORDEIRO");
        end.setNumero(666);
        end.setCep("123456-88");
        end.setCidade("RECIFE");
        end.setEstado("PERNAMBUCO");

        aluno.setEndereco(end);

        em.persist(aluno);

        em.flush();
        assertNotNull(em.find(Aluno.class, (long) 38));
    }

    @Test
    public void atualizarAluno_01() {
        Aluno aluno = em.find(Aluno.class, (long) 24);
        assertNotNull(aluno);
        aluno.setEmail("email@hotmail.com");
        em.flush();
        em.clear();
        logger.log(Level.INFO, "selecionarAlunoPorId: Aluno {0}", aluno.getNome());
        aluno = em.find(Aluno.class, (long) 24);
        assertEquals("email@hotmail.com", aluno.getEmail());
    }

    @Test
    public void atualizarAluno_02() {
        Aluno aluno = em.find(Aluno.class, (long) 27);
        assertNotNull(aluno);
        aluno.setEmail("email@hotmail.com");
        em.flush();
        em.clear();
        logger.log(Level.INFO, "selecionarAlunoPorId: Aluno {0}", aluno.getNome());
        aluno = em.find(Aluno.class, (long) 24);
        assertEquals("email@hotmail.com", aluno.getEmail());
    }

    @Test
    public void selecionarAlunoPorCPF_01() {
        String jpql = "SELECT a FROM Aluno a where a.cpf = ?1";
        Query query = em.createQuery(jpql);
        query.setParameter(1, "05842569855");

        Aluno aluno = (Aluno) query.getSingleResult();

        assertEquals("05842569855", aluno.getCpf());
        logger.log(Level.INFO, "selecionarAlunoPorId: Aluno {0}", aluno.toString());
    }

    @Test
    public void selecionarAlunoPorId() {
        Aluno aluno = em.find(Aluno.class, (long) 29);
        assertNotNull(aluno);
        assertEquals("MICHEL", aluno.getNome());
        String nome = aluno.getNome();
        assertEquals("05842569855", aluno.getCpf());
        logger.log(Level.INFO, "selecionarAlunoPorId: Aluno {0}", aluno.toString());
    }

    @Test
    public void deletarAluno_01() {
        Aluno aluno = em.find(Aluno.class, (long) 12);
        assertNotNull(aluno);
        em.remove(aluno);
        em.flush();
        em.clear();
        aluno = em.find(Aluno.class, (long) 12);
        assertNull(aluno);
    }

    @Test
    public void deletarAluno_02() {
        Aluno aluno = em.find(Aluno.class, (long) 36);
        em.remove(aluno);
        assertEquals(null, em.find(Aluno.class, (long) 36));
    }

    @Test
    public void selecionarAvaliacoes() {
        String jpql = "SELECT COUNT(a) FROM Aluno a";
        Query query = em.createQuery(jpql);

        Long alunos = (Long) query.getSingleResult();

        assertTrue(alunos == 30);
    }
}
