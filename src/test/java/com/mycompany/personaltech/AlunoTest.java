/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltech;

import java.util.Collection;
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

/**
 *
 * @author john
 */
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
        //logger.setLevel(Level.INFO);
        logger.setLevel(Level.SEVERE);
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
            et.rollback();
            fail(ex.getMessage());
        }
    }

    /**
     * Test of getId method, of class Aluno.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Aluno instance = new Aluno();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Aluno.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        Aluno instance = new Aluno();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNome method, of class Aluno.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Aluno instance = new Aluno();
        String expResult = "";
        String result = instance.getNome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNome method, of class Aluno.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        String nome = "";
        Aluno instance = new Aluno();
        instance.setNome(nome);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSobrenome method, of class Aluno.
     */
    @Test
    public void testGetSobrenome() {
        System.out.println("getSobrenome");
        Aluno instance = new Aluno();
        String expResult = "";
        String result = instance.getSobrenome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSobrenome method, of class Aluno.
     */
    @Test
    public void testSetSobrenome() {
        System.out.println("setSobrenome");
        String sobrenome = "";
        Aluno instance = new Aluno();
        instance.setSobrenome(sobrenome);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCpf method, of class Aluno.
     */
    @Test
    public void testGetCpf() {
        System.out.println("getCpf");
        Aluno instance = new Aluno();
        String expResult = "";
        String result = instance.getCpf();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCpf method, of class Aluno.
     */
    @Test
    public void testSetCpf() {
        System.out.println("setCpf");
        String cpf = "";
        Aluno instance = new Aluno();
        instance.setCpf(cpf);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogin method, of class Aluno.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        Aluno instance = new Aluno();
        String expResult = "";
        String result = instance.getLogin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLogin method, of class Aluno.
     */
    @Test
    public void testSetLogin() {
        System.out.println("setLogin");
        String login = "";
        Aluno instance = new Aluno();
        instance.setLogin(login);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSenha method, of class Aluno.
     */
    @Test
    public void testGetSenha() {
        System.out.println("getSenha");
        Aluno instance = new Aluno();
        String expResult = "";
        String result = instance.getSenha();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSenha method, of class Aluno.
     */
    @Test
    public void testSetSenha() {
        System.out.println("setSenha");
        String senha = "";
        Aluno instance = new Aluno();
        instance.setSenha(senha);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class Aluno.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Aluno instance = new Aluno();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class Aluno.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        Aluno instance = new Aluno();
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTipo method, of class Aluno.
     */
    @Test
    public void testGetTipo() {
        System.out.println("getTipo");
        Aluno instance = new Aluno();
        TipoUsuario expResult = null;
        TipoUsuario result = instance.getTipo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTipo method, of class Aluno.
     */
    @Test
    public void testSetTipo() {
        System.out.println("setTipo");
        TipoUsuario tipo = null;
        Aluno instance = new Aluno();
        instance.setTipo(tipo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExercicios method, of class Aluno.
     */
    @Test
    public void testGetExercicios() {
        System.out.println("getExercicios");
        Aluno instance = new Aluno();
        List<Exercicio> expResult = null;
        List<Exercicio> result = instance.getExercicios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addExercicio method, of class Aluno.
     */
    @Test
    public void testAddExercicio() {
        System.out.println("addExercicio");
        Exercicio exercicio = null;
        Aluno instance = new Aluno();
        instance.addExercicio(exercicio);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTelefones method, of class Aluno.
     */
    @Test
    public void testGetTelefones() {
        System.out.println("getTelefones");
        Aluno instance = new Aluno();
        Collection<String> expResult = null;
        Collection<String> result = instance.getTelefones();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTelefone method, of class Aluno.
     */
    @Test
    public void testAddTelefone() {
        System.out.println("addTelefone");
        String telefone = "";
        Aluno instance = new Aluno();
        instance.addTelefone(telefone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataNascimento method, of class Aluno.
     */
    @Test
    public void testGetDataNascimento() {
        System.out.println("getDataNascimento");
        Aluno instance = new Aluno();
        Date expResult = null;
        Date result = instance.getDataNascimento();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataNascimento method, of class Aluno.
     */
    @Test
    public void testSetDataNascimento() {
        System.out.println("setDataNascimento");
        Date dataNascimento = null;
        Aluno instance = new Aluno();
        instance.setDataNascimento(dataNascimento);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndereco method, of class Aluno.
     */
    @Test
    public void testGetEndereco() {
        System.out.println("getEndereco");
        Aluno instance = new Aluno();
        Endereco expResult = null;
        Endereco result = instance.getEndereco();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEndereco method, of class Aluno.
     */
    @Test
    public void testSetEndereco() {
        System.out.println("setEndereco");
        Endereco endereco = null;
        Aluno instance = new Aluno();
        instance.setEndereco(endereco);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTelefones method, of class Aluno.
     */
    @Test
    public void testSetTelefones() {
        System.out.println("setTelefones");
        Collection<String> telefones = null;
        Aluno instance = new Aluno();
        instance.setTelefones(telefones);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setExercicios method, of class Aluno.
     */
    @Test
    public void testSetExercicios() {
        System.out.println("setExercicios");
        List<Exercicio> exercicios = null;
        Aluno instance = new Aluno();
        instance.setExercicios(exercicios);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Aluno.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Aluno instance = new Aluno();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Aluno.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Aluno instance = new Aluno();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Aluno.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Aluno instance = new Aluno();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
