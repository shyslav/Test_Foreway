/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shyslav.controller;

import com.shyslav.model.HibernateUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
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
public class UploadController {

    @RequestMapping(value = "/uploadAction")
    private String uploadAction(ModelMap mv, RedirectAttributes redirectAttributes, HttpSession ses, HttpServletRequest request) throws URISyntaxException {
        String name = null;
        String genre = null;
        String author = null;
        String smallText = null;
        String fullText = null;
        String vision = null;
        String serverPath = null;
        Properties props = new Properties();
        try (InputStream in = UploadController.class.getResourceAsStream("application.properties")) {
            props.load(in);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        DiskFileItemFactory d = new DiskFileItemFactory();
        ServletFileUpload uploadre = new ServletFileUpload(d);
        System.out.println(props.getProperty("downloadDirectory"));
        try {
            List<FileItem> list = uploadre.parseRequest(request);
            for (FileItem f : list) {
                if (f.isFormField() == false) {
                    //write file to upload folder;

                    if (!FilenameUtils.getExtension(f.getName()).equals("pdf")) {
                        String ext = FilenameUtils.getExtension(f.getName());
                        System.out.println(ext);
                        System.out.println("comed");
                        redirectAttributes.addFlashAttribute("error", "Не верный формат для загрузки, доступен только pdf");
                        return "redirect:add.htm";
                    }
                    serverPath = "/"+author+"_"+name+"_"+genre+"_"+Math.random()*100+"."+FilenameUtils.getExtension(f.getName());
                    f.write(new File(props.getProperty("downloadDirectory") + serverPath));
                    System.out.println(f.getName());
                } else if (f.isFormField()) {
                    String fieldname = f.getFieldName();
                    if (fieldname.equals("name")) {
                        name = f.getString("UTF-8");
                    } else if (fieldname.equals("genre")) {
                        genre = f.getString("UTF-8");
                    } else if (fieldname.equals("author")) {
                        author = f.getString("UTF-8");
                    } else if (fieldname.equals("smallText")) {
                        smallText = f.getString("UTF-8");
                    } else if (fieldname.equals("fullText")) {
                        fullText = f.getString("UTF-8");
                    } else if (fieldname.equals("vision")) {
                        vision = f.getString("UTF-8");
                    }
                    System.out.println(f.getFieldName().toString());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        if (name == null || genre == null || author == null
                || smallText == null || fullText == null || vision == null
                || smallText.length() < 30 || fullText.length() < 50 || name.length() < 3) {
            redirectAttributes.addFlashAttribute("error", "Все поля обязательны к заполнению");
            return "redirect:add.htm";
        }
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery("INSERT INTO books\n"
                    + "(name, author, genre, small_text, full_text, date_create, vision, server_Path)\n"
                    + "VALUES('" + name + "', '" + author + "', " + genre + ", '" + smallText + "', '" + fullText + "', NOW(), '" + vision + "','"+serverPath+"')");
            System.out.println(query.toString());
            int result = query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        redirectAttributes.addFlashAttribute("sucses", "Книга успешно добавлена");
        return "redirect:index.htm";
    }

}
