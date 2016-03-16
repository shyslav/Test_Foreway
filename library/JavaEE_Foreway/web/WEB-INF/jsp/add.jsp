<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" flush="true"/>
<div class="container">
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>
       <div class="row">
<!--        <form action="addControl.htm" method="POST">-->
<form action="uploadAction.htm" method ="POST" enctype="multipart/form-data">
            <label>Имя:</label>
            <input type="text" name="name" id="name_json" class="form-control" placeholder="Java Core" maxlength="25" required><br>
            <label>Жанр:</label>
            <select class="form-control" name = "genre">
                <c:forEach items="${genre_list}" var="item"> 
                    <option selected value="${item.id}">${item.name}</option>
                </c:forEach>
            </select><br>
            <label>Автор</label>
            <input type="text" name="author" id="name_json" class="form-control" placeholder="Horstman" maxlength="25" required><br>
            <label>Малое описание:</label>
            <input type="text" name="smallText" id="name_json" class="form-control" placeholder="..." maxlength="390" required><br>
            <label>Большое описание:</label>
            <textarea name = "fullText" placeholder="Введите детали" class="form-control" required></textarea>
            <br>
            <label>Видимость:</label>
            <select class="form-control" name = "vision">    
                <option value = "+">Видимо</option>
                <option value = "-">Скрытор</option>
            </select>
            <br>
            <input type="file" name = "file" accept=".pdf">
            <br>
             <input type="submit" class="btn btn-default">
        </form>
       </div>
</div>
<jsp:include page="includes/footer.jsp" flush="true"/>
