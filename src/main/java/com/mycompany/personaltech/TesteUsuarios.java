///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.personaltech;
//
//import java.util.Calendar;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//
///**
// *
// * @author john
// */
//public class TesteUsuarios {
//
//    public static void main(String[] args) {
//        Aluno aluno = new Aluno();
//        preencherAluno(aluno);
//
//        PersonalTrainer personal = new PersonalTrainer();
//        preencherPersonalTrainer(personal);
//
//        EntityManagerFactory emf = null;
//        EntityManager em = null;
//        EntityTransaction et;
//
//        try {
//            emf = Persistence.createEntityManagerFactory("PersonalTech_PU");
//            em = emf.createEntityManager();
//            et = em.getTransaction();
//            et.begin();
//            em.persist(aluno);
//            em.persist(personal);
//            et.commit();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//            if (emf != null) {
//                emf.close();
//            }
//        }
//    }
//
//    private static void preencherAluno(Aluno aluno) {
//        aluno.setNome("John");
//        aluno.setSobrenome("Lima");
//        aluno.setCpf("000-000-000-00");
//        aluno.setEmail("johnmaykbrito@gmail");
//        aluno.setLogin("johnmaykbrito");
//        aluno.setSenha("123456");
//        aluno.addTelefone("(987)123-456-789");
//        aluno.addTelefone("(987)987-123-625");
//        aluno.setTipo(TipoUsuario.ALUNO);
//
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.YEAR, 1993);
//        c.set(Calendar.MONTH, Calendar.AUGUST);
//        c.set(Calendar.DAY_OF_MONTH, 16);
//        aluno.setDataNascimento(c.getTime());
//
//        preencherEndereco(aluno);
//        preencherExercicio(aluno);
//    }
//
//    private static void preencherEndereco(Aluno aluno) {
//        Endereco end = new Endereco();
//        end.setLogradouro("Rua Brigadeiro Antonio de Sampaio");
//        end.setNumero(385);
//        end.setComplemento("Bloco 10, apto. 302");
//        end.setBairro("Várzea");
//        end.setCep("50950-005");
//        end.setCidade("Recife");
//        end.setEstado("Pernambuco");
//        aluno.setEndereco(end);
//    }
//
//    private static void preencherExercicio(Aluno aluno) {
//        Exercicio e1 = new Exercicio();
//        e1.setTipo(TipoExercicio.ABDOMINAIS);
//        e1.setExercicio(NomeExercicio.ABD_BOSU);
//        aluno.addExercicio(e1);
//
//        Exercicio e2 = new Exercicio();
//        e2.setTipo(TipoExercicio.PEITORAL);
//        e2.setExercicio(NomeExercicio.PEIT_GRAVITON);
//        aluno.addExercicio(e2);
//
//        Exercicio e3 = new Exercicio();
//        e3.setTipo(TipoExercicio.COXAS);
//        e3.setExercicio(NomeExercicio.COXAS_STIFF_POLIA);
//        aluno.addExercicio(e3);
//    }
//
//    private static void preencherPersonalTrainer(PersonalTrainer pt) {
//        pt.setNome("Carlos");
//        pt.setSobrenome("Borges");
//        pt.setCpf("000-000-000-01");
//        pt.setEmail("c_borges@gmail");
//        pt.setLogin("c_borges");
//        pt.setSenha("123456");
//        pt.addTelefone("(111)123-456-789");
//        pt.addTelefone("(111)987-123-625");
//        pt.setTipo(TipoUsuario.PERSONAL_TRAINER);
//
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.YEAR, 1983);
//        c.set(Calendar.MONTH, Calendar.MARCH);
//        c.set(Calendar.DAY_OF_MONTH, 13);
//        pt.setDataNascimento(c.getTime());
//
//        preencherEnderecoPersonalTrainer(pt);
//    }
//
//    private static void preencherEnderecoPersonalTrainer(PersonalTrainer pt) {
//        Endereco end = new Endereco();
//        end.setLogradouro("Av. Boa Viagem");
//        end.setNumero(543);
//        end.setComplemento("Ed. Hulk, apto. 1603");
//        end.setBairro("Boa Viagem");
//        end.setCep("50950-005");
//        end.setCidade("Recife");
//        end.setEstado("Pernambuco");
//        pt.setEndereco(end);
//    }
//}
