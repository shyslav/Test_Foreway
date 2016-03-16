<%-- 
    Document   : header
    Created on : 14.03.2016, 19:31:39
    Author     : Shyshkin Vladyslav
--%>
<%@page import="com.shyslav.menu.MenuRole"%>
<%@page import="org.springframework.ui.ModelMap"%>
<%@page import="com.shyslav.menu.WMenu"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.hibernate.Session"%>
<%@page import="javax.servlet.http.HttpServletRequest" %>
<%@page import="java.util.List"%>
<%@page import="com.shyslav.model.HibernateUtil"%>

<%
    HttpSession s = request.getSession();
    int id = 1;
    if(s.getAttribute("Sesrole")!=null)
    {
        id = Integer.parseInt(s.getAttribute("Sesrole").toString());
    }
    List<MenuRole> menu = null;
    try {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        ses.beginTransaction();
        menu = ses.createQuery("from MenuRole mr where mr.role.id='"+id+"'").list();
        ses.getTransaction().commit();
    } catch (Exception ex) {
        System.out.println(ex);
    }%>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Library</title>

        <!-- Bootstrap Core CSS -->
        <link href="${pageContext.request.contextPath}/files/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/files/css/shop-item.css" rel="stylesheet">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>
    <body>

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}">Library</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <%for (MenuRole m : menu) {%>
                        <li>
                            <a href="${pageContext.request.contextPath}/<%=m.getWMenu().getLink()%>.htm"><%=m.getWMenu().getName()%></a>  
                        </li> 
                        <%}%>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>

