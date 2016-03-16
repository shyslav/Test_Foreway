/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyslav.controller;

import com.shyslav.model.HibernateUtil;
import com.shyslav.model.User;
import com.shyslav.model.personalUserList.UsersBookList;
import com.shyslav.validation.valid;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Shyshkin Vladyslav
 */
@Controller
public class RegAuthController {

    @RequestMapping(value = "/registration")
    private String registration(ModelMap mv, HttpServletRequest request) {
        return "registration";
    }

    @RequestMapping(value = "/reg")
    private String reg(ModelMap mv, HttpServletRequest request, RedirectAttributes redirectAttributes) throws ParseException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String lastName = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String address = request.getParameter("adress");
        String bdate = request.getParameter("age");
        String password = request.getParameter("password");
        String re_password = request.getParameter("re_password");
        ArrayList<String> process = new ArrayList();
        ArrayList<String> error = new ArrayList();
        process.add(valid.phoneValid(phone));
        process.add(valid.NameValid(name));
        process.add(valid.SurnameValid(lastName));
        process.add(valid.passwordValid(password, re_password));
        for (String s : process) {
            if (!s.equals("ok")) {
                error.add(s);
            }
        }
        if (error.size() != 0) {
            redirectAttributes.addFlashAttribute("error", error);
            return "redirect:registration.htm";
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        User user = new User(name, lastName, phone, address, format.parse(bdate), 2, password);
        session.save(user);
        session.getTransaction().commit();
        return "redirect:index.htm";
    }

    @RequestMapping(value = "/authorisation")
    private String authorisation(ModelMap mv, HttpServletRequest request) {
        return "authorisation";
    }

    @RequestMapping(value = "/auth")
    private String auth(ModelMap mv, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession s) {
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<User> user = session.createQuery("from User us where us.phone='" + phone+"'").list();
        if (user.size() == 0) {
            redirectAttributes.addFlashAttribute("auth_error", "Пользователя с таким телефоном не существует");
            return "redirect:authorisation.htm";
        } else if (!user.get(0).getPassword().equals(password)) {
            redirectAttributes.addFlashAttribute("auth_error", "Пароль не совпадает");
            return "redirect:authorisation.htm";
        } else {
            s.setAttribute("Sesphone", phone);
            s.setAttribute("Sesrole", user.get(0).getRole());
            s.setAttribute("SesallUserDate", user.get(0));
            s.setAttribute("SesUserId", user.get(0).getId());
            redirectAttributes.addFlashAttribute("sucses", "Авторизация успешно выполнена");
            return "redirect:index.htm";
        }
    }
      @RequestMapping(value = "/exit")
      private String exit(HttpSession s)
      {
         s.invalidate();
         return "redirect:index.htm";
      }
      @RequestMapping (value = "/profile")
      private String profile(HttpSession s,ModelMap mv,RedirectAttributes redirectAttributes)
      {
          if(s.getAttribute("Sesrole")==null)
          {
              return "redirect:authorisation.htm";
          }
          int id = (int) s.getAttribute("SesUserId");
                  try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List<UsersBookList>books = session.createQuery("from UsersBookList ubl where ubl.user.id="+id).list();
            mv.addAttribute("booksList", books);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
          return "profile";
      }

}
