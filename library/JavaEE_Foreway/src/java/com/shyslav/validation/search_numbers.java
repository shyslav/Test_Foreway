/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyslav.validation;

/**
 *
 * @author Shyshkin Vladyslav
 */
public class search_numbers {
     public boolean isNumber(String str)
    {
        if(!str.matches("^\\D*$"))
        {
            return false;
        }
        return true;
    }
    public boolean isTextOnly(String str)
    {
        char first = str.charAt(0);
        if(Character.isLowerCase(first))
        {
            return false;
        }
        return true;
    }
}
