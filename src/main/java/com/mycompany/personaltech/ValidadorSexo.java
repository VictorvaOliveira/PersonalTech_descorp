/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltech;

/**
 *
 * @author john
 */
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorSexo implements ConstraintValidator<ValidaSexo, String> {
    private List<String> sexo;
    
    @Override
    public void initialize(ValidaSexo validaSexo) {
        this.sexo = new ArrayList<>();
        this.sexo.add("F");
        this.sexo.add("M");
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        return valor == null ? false : sexo.contains(valor);
    }
    
}
