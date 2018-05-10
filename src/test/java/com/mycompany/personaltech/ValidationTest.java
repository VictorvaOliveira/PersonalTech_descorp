/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltech;

import java.util.Calendar;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author john
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidationTest {

    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    public ValidationTest() {
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
//            fail(ex.getMessage());
        }
    }

    @Test
    public void Test_01() {
        Aluno aluno = null;
        try {
            aluno = new Aluno();
            aluno.setNome("Serj");
            aluno.setSobrenome(""); // não pode ser Blank
            aluno.setCpf("123.123.123-12"); // CPF Inválido
            aluno.setLogin("SERJTANKIAN"); // login inválido
            aluno.setSenha("aA1personal"); // senha inválida
            aluno.setEmail("serj"); // email inválido
            aluno.setSexo(""); // inválido (reprova em @NotNull e @NotBlank)

            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, 2019); // É uma data do futuro
            c.set(Calendar.MONTH, Calendar.OCTOBER);
            c.set(Calendar.DAY_OF_MONTH, 27);
            aluno.setDataNascimento(c.getTime());

            Endereco end = new Endereco();
            end.setLogradouro("21 St.");
            end.setBairro("Beverly Hills");
            end.setNumero(123);
            end.setCep("123456-88");
            end.setCidade("Los  Angeles");
            end.setEstado("PE");

            aluno.setEndereco(end);

            em.persist(aluno);
            em.flush();
            assertTrue(false);
        } catch (ConstraintViolationException ex) {
            Logger.getGlobal().info(ex.getMessage());

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            if (logger.isLoggable(Level.INFO)) {
                for (ConstraintViolation violation : constraintViolations) {
                    Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}\n\n", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                }
            }
            assertEquals(8, constraintViolations.size());
            assertNull(aluno.getId());
        }
    }
}
