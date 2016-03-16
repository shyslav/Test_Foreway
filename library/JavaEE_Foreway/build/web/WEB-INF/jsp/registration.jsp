<%-- 
    Document   : registration
    Created on : 15.03.2016, 19:18:03
    Author     : Shyshkin Vladyslav
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" flush="true"/>
<div class="container">
    <div class="row">
        <div class="col-md-7">
            <form action="reg.htm" method="POST">
                <label>Имя:</label>
                <input type="text" name="name" id="name_json" class="form-control" placeholder="Петр" required><br>

                <label>Фамилия:</label>
                <input type="text" name="surname" id="surname_json" class="form-control" placeholder="Петров" required><br>

                <label>Телефон:</label>
                <input type="number" name="phone" id="email_json" class="form-control" maxlength="10" placeholder="0915826956" required><br>

                <label>Возраст:</label>
                <input type="date" name="age" class="form-control" placeholder="16-99" required><br>

                <label>Адресс:</label>
                <input type="text" name="adress" class="form-control" placeholder="Киев"><br>

                <label>Пароль:</label>
                <input type="password" maxlength="14" name = "password" id="password_json" class="form-control" required><br>

                <label>Повторите пароль:</label>
                <input type="password" maxlength="14" name = "re_password" id="re_password_json" class="form-control" required><br><br>
                <input type="submit" class="btn btn-default">
            </form>
        </div>
        <div class="col-md-5">
            <h3>Все поля обязательны для заполнения</h3>
            <ul>
                <li>Поля Имя, Фамилия не должны иметь цифр и должны иметь большую букву</li>
                <li>Вораст от 16 лет</li>
                <li>Пароли должны быть идентичны и не более 14 символов. Должен состоять из цифр и букв</li>
            </ul>
            <c:if test="${not empty error}">
                <h3>Вы совершили такие ошибки при вводе</h3>
                <ul>
                    <c:forEach items="${error}" var="item">
                        <li>${item}</li>
                        </c:forEach>
                </ul>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="includes/footer.jsp" flush="true"/>
