<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" flush="true"/>
<div class="container">
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>
    <div class="row">
        <form action="${pageContext.request.contextPath}/updateAction.htm" method = "POST">
            <input type ="hidden" value="${books.get(0).id}" name="indeficator" readonly>
            <label>Имя:</label>
            <input type="text" value="${books.get(0).name}" name="name" class="form-control" placeholder="Java Core" maxlength="25" required><br>
            <label>Жанр:</label>
            <select class="form-control" name = "genre">
                <c:forEach items="${genre}" var="item"> 
                    <c:if test="${item.id eq books.get(0).genre.id}">
                        <option selected style="background-color:yellowgreen" value="${item.id}">${item.name}</option> 
                    </c:if>
                    <c:if test="${item.id ne books.get(0).genre.id}">
                        <option value="${item.id}">${item.name}</option> 
                    </c:if>    
                </c:forEach>
            </select><br>
            <label>Автор</label>
            <input type="text" name="author" value="${books.get(0).author}" class="form-control" placeholder="Horstman" maxlength="25" required><br>
            <label>Малое описание:</label>
            <input type="text" name="smallText" value="${books.get(0).smallText}" class="form-control" placeholder="..." maxlength="390" required><br>
            <label>Большое описание:</label>
            <textarea name = "fullText" placeholder="Введите детали" class="form-control" required>${books.get(0).fullText}</textarea>
            <br>
            <label>Видимость:</label>
            <select class="form-control" name = "vision">  
                <c:if test="${books.get(0).vision eq '+'}">
                    <option selected style="background-color:yellowgreen" value = "+">Видимо</option>
                    <option value = "-">Скрыто</option>
                </c:if>
                <c:if test="${books.get(0).vision eq '-'}">
                    <option selected style="background-color:yellowgreen" value = "-">Скрыто</option>
                    <option value = "+">Видимо</option>
                </c:if>       
            </select>
            <br>
            <input type="submit" class="btn btn-default">
        </form>
    </div>
</div>
<jsp:include page="includes/footer.jsp" flush="true"/>
