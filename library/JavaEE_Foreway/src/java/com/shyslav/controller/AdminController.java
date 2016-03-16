/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyslav.controller;

import com.shyslav.fileDate.dataDelete;
import com.shyslav.model.Books;
import com.shyslav.model.Genre;
import com.shyslav.model.HibernateUtil;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Shyshkin Vladyslav
 */
@Controller
public class AdminController {

    @RequestMapping(value = "/add")
    public String add(ModelMap mv, HttpServletRequest request, HttpSession ses, RedirectAttributes redirectAttributes) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List genre = session.createQuery("from Genre").list();
            mv.addAttribute("genre_list", genre);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "add";
    }

    @RequestMapping(value = "/updateAction")
    public String updateAction(ModelMap mv, HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession ses) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String genre = request.getParameter("genre");
        String author = request.getParameter("author");
        String smallText = request.getParameter("smallText");
        String fullText = request.getParameter("fullText");
        String vision = request.getParameter("vision");
        String indeficator = request.getParameter("indeficator");

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE books\n"
                    + " SET name='" + name + "', "
                    + " author='" + author + "', "
                    + " genre=" + genre + ", "
                    + " small_text='" + smallText + "', "
                    + " full_text='" + fullText + "', "
                    + " vision='" + vision + "'"
                    + " WHERE id=" + indeficator);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        redirectAttributes.addFlashAttribute("sucses", "Изменино");
        return "redirect:index.htm";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") String id, ModelMap mv, RedirectAttributes redirectAttributes, HttpSession ses) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("delete Books b where b.id = '" + id + "'");
            List<Books> list = session.createQuery("from Books b where b.id = '" + id + "'").list();
            if (list.get(0).getServer_Path() != null) {
                dataDelete.Delete(list.get(0).getServer_Path());
            }
            q.executeUpdate();
            session.getTransaction().commit();
            redirectAttributes.addFlashAttribute("sucses", "Успешно удалено");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "redirect:/index.htm";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") String id, HttpServletRequest request, ModelMap mv, RedirectAttributes redirectAttributes, HttpSession ses) {
        MyController.standartInitialize(mv, ses);
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            //Query q = session.createQuery("delete Books b where b.id = '" + id + "'");
            List<Books> list = session.createQuery("from Books b where b.id = '" + id + "'").list();
            mv.addAttribute("books", list);
            //q.executeUpdate();
            session.getTransaction().commit();

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "edit";
    }

}
