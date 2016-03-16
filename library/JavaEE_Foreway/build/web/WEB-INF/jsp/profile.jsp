<%-- 
    Document   : profile
    Created on : 16.03.2016, 5:35:24
    Author     : Shyshkin Vladyslav
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" flush="true"/>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <h3>Добро пожаловать ${SesallUserDate.name}${SesallUserDate.lastName}</h3>
            <h4>Персональные данные:</h4>
            <label>Телефон</label>
            <input type="number" name="name" class="form-control" value="${SesallUserDate.phone}" readonly><br>
            <label>Адресс</label>
            <input type="text" name="name" class="form-control" value="${SesallUserDate.address}" readonly><br>
            <label>Дата рождения</label>
            <input type="text" name="name" class="form-control" value="${SesallUserDate.bdate}" readonly><br>       
        </div>
        <div class="col-md-5">
            <h4>Список избранных книг:</h4>
            <c:forEach items="${booksList}" var="item"> 
                <a href = "${pageContext.request.contextPath}/deteils/${item.books.id}.htm">${item.books.name}</a>
                <a class="btn btn-warning" href="${pageContext.request.contextPath}/deleteLikes/${item.id}.htm">-</a>
                <br>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="includes/footer.jsp" flush="true"/>
