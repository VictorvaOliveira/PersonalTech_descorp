/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltech.test;

////Classes que serão testadas
//import com.mycompany.personaltech.Aluno;
//import com.mycompany.personaltech.Endereco;
//import com.mycompany.personaltech.Exercicio;
//import com.mycompany.personaltech.NomeExercicio;
//import com.mycompany.personaltech.PersonalTrainer;
//import com.mycompany.personaltech.TipoExercicio;
//import com.mycompany.personaltech.TipoUsuario;
import com.mycompany.personaltech.DbUnitUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
 * @author victor
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SuppressWarnings("JPQLValidation")
public class JpqlTest {

    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    public JpqlTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        logger = Logger.getGlobal();
        //logger.setLevel(Level.INFO);
        logger.setLevel(Level.SEVERE);
        emf = Persistence.createEntityManagerFactory("PERSONALTECH");
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
     * Teste para retornar quantos tipos de exercício estão 
     * cadastrados na TB_EXERCICIO
     * 
     * OBS: Pegando informação de todos os alunos 
     * author: Victor Vinicius
     */
    @Test
    public void t01_tipoExercicio(){
        logger.info("Executando t01: SELECT DISTINCT (e.tipo) FROM Exercicio e ORDER BY e.tipo");
        TypedQuery<String> query
                = em.createQuery("SELECT DISTINCT (e.tipo) FROM Exercicio e ORDER BY e.tipo", String.class);
        List<String> tipos = query.getResultList();
        assertEquals(2, tipos.size());
        
    }
}
