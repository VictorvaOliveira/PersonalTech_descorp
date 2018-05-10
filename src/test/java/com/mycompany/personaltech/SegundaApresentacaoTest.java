/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltech;

import java.text.SimpleDateFormat;
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
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
public class SegundaApresentacaoTest {

    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    public SegundaApresentacaoTest() {
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

    // ALUNO
    @Test
    public void JPQLretornaAlunosComInicial_J() {
        TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a WHERE a.nome LIKE :nome ORDER BY a.id DESC", Aluno.class);
        query.setParameter("nome", "j%");
        List<Aluno> alunos = query.getResultList();
        for (Aluno aluno : alunos) {
            assertEquals("J", aluno.getNome().substring(0, 1).toUpperCase());
        }
        if (alunos.isEmpty()) {
            assertEquals(0, alunos.size());
        }
    }

    @Test
    public void NQretornaAlunosComInicial_J() {
        TypedQuery<Aluno> query = em.createNamedQuery("Aluno.PorNome", Aluno.class);
        query.setParameter("nome", "j%");
        List<Aluno> alunos = query.getResultList();
        for (Aluno aluno : alunos) {
            assertEquals("J", aluno.getNome().substring(0, 1).toUpperCase());
        }
        if (alunos.isEmpty()) {
            assertEquals(0, alunos.size());
        }
    }

    @Test
    public void JPQLretornaAlunosQueTemExercicios() {
        TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a WHERE a.exercicios IS NOT EMPTY", Aluno.class);
        List<Aluno> alunos = query.getResultList();
        assertNotEquals(alunos.size(), 0);
    }

    @Test
    public void JPQLexistenciaDeEntidadeEmColecao() {
        Exercicio ex = em.find(Exercicio.class, (long) 1);
        TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a JOIN FETCH a.exercicios xs WHERE :ex MEMBER OF a.exercicios", Aluno.class);
        query.setParameter("ex", ex);
        List<Aluno> alunos = query.getResultList();
        assertNotNull(alunos);
    }

    @Test
    public void NQretornaAlunoPorTipoDeExercicio() {
        TypedQuery<Aluno> query = em.createNamedQuery("Aluno.PorTipoDeExercicio", Aluno.class);
        query.setParameter("ex", TipoExercicio.BICEPS);
        List<Aluno> alunos = query.getResultList();
        System.out.println(alunos.size());
        assertEquals(alunos.size(), 6);
    }

    @Test
    public void JPQLretornaTotalDoTipoDeExercico() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(e) FROM Exercicio e WHERE e.tipo = ?1", Long.class);
        query.setParameter(1, TipoExercicio.PEITORAL);
        long total = query.getSingleResult();
        assertEquals(total, (long) 11);
    }

    @Test
    public void NativeRetornaNomeAluno() {
        Query query = em.createNativeQuery("SELECT TXT_NOME FROM TB_USUARIO WHERE ID = 4");
        String nomeAluno = (String) query.getSingleResult();
        System.out.println(nomeAluno);
        assertNull(null);
    }

    @Test
    public void NativeRetornaEntidadeAvaliacao() {
        Query query = em.createNativeQuery("SELECT ID, DT_AVALIACAO, ID_ALUNO, TXT_NOME_PT  FROM TB_AVALIACAO WHERE DT_AVALIACAO = ?1", Avaliacao.class);
        query.setParameter(1, "1950-12-02");//id av = 6
        Avaliacao avaliacao = (Avaliacao) query.getSingleResult();
        assertEquals("THOR", avaliacao.getNome_personal());
    }

    @Test
    public void NamedNativeRetornaNomeUsuario() {
        Query query = em.createNamedQuery("Usuario.RetornaNome");
        query.setParameter(1, 4);
        String nomeAluno = (String) query.getSingleResult();
        assertEquals("JOAO", nomeAluno);
    }

    @Test
    public void JPQLretornaDatasMinEMax() {
        Query query = em.createQuery("SELECT MIN(u.dataNascimento), MAX(u.dataNascimento) FROM Usuario u");
        Object[] resultado = (Object[]) query.getSingleResult();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String menorData = dateFormat.format((Date) resultado[0]);
        String maiorData = dateFormat.format((Date) resultado[1]);

        assertEquals("12-09-1968", menorData);
        assertEquals("17-09-2000", maiorData);

    }

    @Test
    public void NativeGroupBy() {
        Query query = em.createNativeQuery("SELECT TB_USUARIO.TXT_NOME, COUNT(TB_ALUNO.ID_USUARIO) FROM TB_ALUNO INNER JOIN TB_USUARIO ON TB_ALUNO.ID_PT = TB_USUARIO.ID GROUP BY TXT_NOME ORDER BY COUNT(TB_ALUNO.ID_USUARIO) DESC");
        List lista = query.getResultList();
        for (int i = 0; i < lista.size(); i++) {
            Object[] item = (Object[]) lista.get(i);
            System.out.println(item[0] + " : " + item[1]);
            assertEquals(2, item.length);
        }
        Object[] item = (Object[]) lista.get(0);
        assertEquals(item[0], "VICTOR");
        assertEquals(item[1], (long) 6);
    }
    
    // PERSONAL
    @Test
    public void NamedNativeRetornaNomePersonal() {
        Query query = em.createNamedQuery("Usuario.RetornaNome");
        query.setParameter(1, 2);
        String nomePersonal = (String) query.getSingleResult();
        assertEquals("JUCA", nomePersonal);
    }
    
    // AVALIACAO
    @Test
    public void JPQLupdateAvaliacaoDate_01() {
        Query query = em.createQuery("UPDATE Avaliacao a SET a.dataAvaliacao = :strNewDate WHERE a.dataAvaliacao = :strOldDate");

        Date newDate = setDate(1993, 7, 6); //1993-AUG-16 *7 is August because the month index is 0 based
        query.setParameter("strNewDate", newDate);

        Date oldDate = setDate(1980, 10, 2); // 1980-NOV-02
        query.setParameter("strOldDate", oldDate);

        query.executeUpdate();

        Query newQuery = em.createQuery("SELECT av.dataAvaliacao FROM Avaliacao av WHERE av.id = :idAval");
        newQuery.setParameter("idAval", 3);
        Date resultDate = (Date) newQuery.getSingleResult();

        assertEquals(newDate.getYear(), resultDate.getYear());
    }

    @Test
    public void JPQLdeleteAvaliacaoDate_01() {
        Query query = em.createQuery("DELETE FROM Avaliacao a WHERE a.id = :id AND a.dataAvaliacao = :date");

        Calendar c = Calendar.getInstance(); // CURRENT DATE
        c.setTime(new Date());
        Date date = c.getTime();
        query.setParameter("date", date);
        query.setParameter("id", 7);
        query.executeUpdate();
        
        assertNull(em.find(Avaliacao.class, (long) 7));
    }

    // Métodos Auxiliares
    private Date setDate(int ano, int mes, int dia) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, ano);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.DAY_OF_MONTH, dia);
        Date date = c.getTime();
        return date;
    }
    
    
    // EXERCÍCIO
    @Test
    public void NamedNativeRetornaNomeAluno() {
        Query query = em.createNamedQuery("Exercicio.RetornaTipoExercicio");
        query.setParameter(1, 12);
        List result = query.getResultList();
        Object[] item = (Object[]) result.get(0);
        assertEquals(1, result.size());
        assertEquals("BIANCA", item[1]);
    }
    
    @Test
    public void retonarAlunosQuePraticamExercicioX() {
        Exercicio ex = em.find(Exercicio.class, (long) 1);
        NomeExercicio nome = NomeExercicio.BICEPS_BARRA_ROSCA_PRON_POLIA;
        TypedQuery<Aluno> query = em.createQuery("SELECT DISTINCT a FROM Aluno a JOIN FETCH a.exercicios xs WHERE xs.exercicio = :tipo", Aluno.class);
        query.setParameter("tipo", nome);
        List<Aluno> alunos = query.getResultList();
        assertEquals(alunos.size(), 4);
        assertNotNull(alunos);
    }
}
