///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.personaltech;
//
//import java.io.Serializable;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
///**
// *
// * @author john
// */
//@Entity
//@Table(name = "TB_RESP_ALUNO")
//public class RespostasAvaliacao implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "TXT_RESP_ALUNO", nullable = true, length = 1000)
//    private String txt_resposta;
//
////    @Column(name = "TXT_PERGUNTA", nullable = false)
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false, orphanRemoval = false)
//    @JoinColumn(name = "ID_PERGUNTA_AVAL", referencedColumnName = "ID")
//    private Pergunta pergunta;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTxt_resposta() {
//        return txt_resposta;
//    }
//
//    public void setTxt_resposta(String txt_resposta) {
//        this.txt_resposta = txt_resposta;
//    }
//
//    public Pergunta getPergunta() {
//        return pergunta;
//    }
//
//    public void setPergunta(Pergunta pergunta) {
//        this.pergunta = pergunta;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof RespostasAvaliacao)) {
//            return false;
//        }
//        RespostasAvaliacao other = (RespostasAvaliacao) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.mycompany.personaltech.RespostasAvaliacao[ id=" + id + " ]";
//    }
//
//}
