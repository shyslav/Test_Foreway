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
import com.shyslav.model.User;
import com.shyslav.model.personalUserList.UsersBookList;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Shyshkin Vladyslav
 */
@Controller
public class MyController {

    /**
     * Начальная страница
     *
     * @param mv
     * @param request
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    private String index(ModelMap mv, HttpServletRequest request, HttpSession ses) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            standartInitialize(mv, ses);
            List books = session.createQuery("from Books books where books.vision = '+'").list();
            List menuha = session.createQuery("from MenuRole").list();
            mv.addAttribute("books", books);
            mv.addAttribute("test", menuha);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "index";
    }

    /**
     * Страница по определенным жанрам
     *
     * @param id индетификатор жанра
     * @param mv
     * @return index
     */
    @RequestMapping(value = "/genre/{id}")
    public String genre(@PathVariable("id") String id, ModelMap mv, HttpSession ses) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            standartInitialize(mv, ses);
            List books = session.createQuery("from Books books where books.vision = '+' and books.genre=" + id).list();
            mv.addAttribute("genreSelected", id);
            mv.addAttribute("books", books);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "index";
    }

    @RequestMapping(value = "/deleteLikes/{id}")
    public String deleteLikes(@PathVariable("id") String id, ModelMap mv, RedirectAttributes redirectAttributes) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("delete UsersBookList b where b.id = '" + id + "'");
            q.executeUpdate();
            session.getTransaction().commit();
            redirectAttributes.addFlashAttribute("sucses", "Успешно удалено");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "redirect:/profile.htm";
    }

    @RequestMapping(value = "/addToUserBookList/{id}")
    public String addToUserBookList(@PathVariable("id") String id, ModelMap mv, RedirectAttributes redirectAttributes, HttpSession ses) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Books book = (Books) session.createQuery("from Books b where b.id =" + id).list().get(0);
            User user = (User) session.createQuery("from User u where u.id =" + ses.getAttribute("SesUserId")).list().get(0);
            Query query = session.createSQLQuery("insert into UsersBookList(bookId,userId) values(:bId,:uId)")
                    .setInteger("bId", book.getId())
                    .setInteger("uId", user.getId());
            query.executeUpdate();
            session.getTransaction().commit();
            redirectAttributes.addFlashAttribute("sucses", "Добавлено в избранное");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "redirect:/index.htm";
    }

    @RequestMapping(value = "/search")
    private String search(ModelMap mv, HttpServletRequest request, HttpSession ses) throws UnsupportedEncodingException, SQLException {
        request.setCharacterEncoding("UTF-8");
        String searchText = request.getParameter("searchText");
        String query = "select b.id as id, server_Path as server_Path,g.name as genre,\n"
                + "b.name as name, \n"
                + "b.author as author, \n"
                + "b.small_text as smallText,\n"
                + "b.full_text as fullTexts, \n"
                + "b.date_create as dateCreate,\n"
                + "b.vision as vision from books b\n"
                + "inner join genre g on b.genre = g.id\n"
                + "where (b.name like \"%" + searchText + "%\"\n"
                + " or g.name like \"%" + searchText + "%\"\n"
                + " or b.author like \"%" + searchText + "%\" )\n"
                + " and b.vision = '+'";
        Statement st = null;
        Connection con = null;
        ResultSet rs = null;
        ArrayList<Books> result = new ArrayList();

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        try {
            Class.forName(cfg.getProperty("hibernate.connection.driver_class"));
            con = DriverManager.getConnection(cfg.getProperty("hibernate.connection.url"), cfg.getProperty("hibernate.connection.username"), cfg.getProperty("hibernate.connection.password"));
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                result.add(new Books(new Genre(rs.getString("genre")),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("smallText"),
                        rs.getString("fullTexts"),
                        rs.getDate("dateCreate"),
                        rs.getString("vision").charAt(0),
                        rs.getInt("id"),
                        rs.getString("server_Path")));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error" + ex);
        } finally {
            st.close();
            con.close();
        }
        standartInitialize(mv, ses);
        if (result.size() == 0) {
            mv.addAttribute("answer", "Поиск не дал результатов");
        } else {
            mv.addAttribute("books", result);
        }
        System.out.println(result.size());
        return "index";
    }

    @RequestMapping(value = "/deteils/{id}")
    public String details(@PathVariable("id") String id, ModelMap mv, HttpSession ses) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            standartInitialize(mv, ses);
            List books = session.createQuery("from Books books where books.vision = '+' and books.id=" + id).list();
            mv.addAttribute("books", books);
            mv.addAttribute("details", id);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return "index";
    }

    /**
     * Функция которая инициализирует начальные статические параметры на каждой
     * странице параметры и записивыет их в модель
     *
     * @param mv модель
     */
    public static void standartInitialize(ModelMap mv, HttpSession ses) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List result = session.createQuery("from Genre").list();
            mv.addAttribute("genre", result);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        usersLike(ses, mv);
    }

    private static void usersLike(HttpSession ses, ModelMap mv) {
        if (ses.getAttribute("SesUserId") == null) {
            return;
        }
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List<UsersBookList> userList = session.createQuery("from UsersBookList ubl where ubl.user.id=" + ses.getAttribute("SesUserId")).list();
            mv.addAttribute("userList", userList);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
