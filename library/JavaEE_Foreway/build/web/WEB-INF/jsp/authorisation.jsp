<%-- 
    Document   : authorisation
    Created on : 15.03.2016, 21:29:34
    Author     : Shyshkin Vladyslav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" flush="true"/>
<div class="container">
    <c:if test="${not empty auth_error}">
    <h4 style="color:red">${auth_error}</h4>
    </c:if>
    <div class="row">
        <h2>Авторизируясь - Вы соглашаетесь с правилами сайта</h2>
        <hr>
        <div class="col-md-6">
                <form action="auth.htm" method="POST">
                    <label>Телефон</label>
                    <input type="number" name="phone" class="form-control" maxlength="10" placeholder="0915826956" required><br>
                    
                    <label>Пароль:</label>
                    <input id = "password" type="password" maxlength="14" name = "password" class="form-control">
                    <br>
                    <input type="submit" class="btn btn-default" onsubmit="authorisation();return false;">
                </form>
        </div>
        <div class="col-md-4">
            <h3>Преймущества при регистрации</h3>
            <ul>
                <li>Качать книги</li>
                <li>Добавлять в избранное</li>
            </ul>
        </div>
    </div>
</div>
<jsp:include page="includes/footer.jsp" flush="true"/>
