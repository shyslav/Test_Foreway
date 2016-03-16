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
public class valid {

    public static String phoneValid(String phone) {
        if (phone.length() != 10) {
            return "Телефон должен состоять из 10 цифр";
        }
        return "ok";
    }

     public static String passwordValid(String password, String re_password) {
        if (!password.equals(re_password)) {
            return "Пароли не совпадают";
        } else if (password.length() < 5) {
            return "Пароль должен быть более 5 символов";
        } else {
            search_numbers sn = new search_numbers();
            if (sn.isNumber(password)) {
                return "Пароль должен содержать цифру";
            }
        }
        return "ok";
    }

     public static String NameValid(String name) {
        if (name.length() < 3) {
            return "Имя должно быть более 3 символов";
        } else if (name.length() > 20) {
            return "Имя должно быть менее 20 символов";
        } else {
            search_numbers sn = new search_numbers();
            if (!sn.isNumber(name)) {
                return "Имя не должно состоять из цифр";
            } else if (!sn.isTextOnly(name)) {
                return "Имя должно быть с большой буквы";
            }
        }
        return "ok";
    }

    public static String SurnameValid(String surname) {
        if (surname.length() < 3) {
            return "Фамилия должно быть более 3 символов";
        } else if (surname.length() > 40) {
            return "Фамилия должно быть менее 40 символов";
        } else {
            search_numbers sn = new search_numbers();
            if (!sn.isNumber(surname)) {
                return "Фамилия не должно состоять из цифр";
            } else if (!sn.isTextOnly(surname)) {
                return "Фамилия должно быть с большой буквы";
            }
        }
        return "ok";
    }
}
