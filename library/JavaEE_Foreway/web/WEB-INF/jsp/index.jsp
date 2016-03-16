<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="includes/header.jsp" flush="true"/>

<div class="container">
    <c:if test="${not empty sucses}">
        <h4 style="color:green">${sucses}</h4>
    </c:if>
        ${Sesrole}
    <div class="row">
        <div class="col-md-3">
            <p class="lead">Поиск по книгам</p>
            <form method="GET" action="${pageContext.request.contextPath}/search.htm">
                <div class="input-group">            
                    <input type="text" class="form-control" placeholder="Что искать?" name="searchText">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="submit" alt="Введите жанр, название или автора книги">Go!</button> 
                    </span>     
                </div><!-- /input-group -->
            </form>
            <br>
            <div class="list-group">
                <p class="lead">Жанры книг</p>
                <c:forEach var="num" items="${genre}">
                    <c:if test="${num.id eq genreSelected}">
                        <a href="${pageContext.request.contextPath}/genre/${num.id}.htm" class="list-group-item active">${num.name}</a>
                    </c:if>
                    <c:if test="${num.id ne genreSelected}">
                        <a href="${pageContext.request.contextPath}/genre/${num.id}.htm" class="list-group-item">${num.name}</a>
                    </c:if>
                </c:forEach>
                <!--            <a href="#" class="list-group-item active">Category 1</a>
                                <a href="#" class="list-group-item">Category 2</a>
                                <a href="#" class="list-group-item">Category 3</a>>-->
            </div>
        </div>     
        <c:if test="${not empty answer}">
            <div class="col-md-9">
                <h1>${answer}</h1>
            </div>
        </c:if>
        <c:forEach items="${books}" var="item">
            <div class="col-md-9">
                <div class="thumbnail">
                    <div class="caption-full">
                        <h4 class="pull-right">${item.genre.name}</h4>
                        <h4><a href="#">${item.name}</a>
                        </h4>
                        <c:choose>
                            <c:when test="${not empty details}">
                                ${item.fullText}
                            </c:when>
                            <c:otherwise>
                                ${item.smallText}
                            </c:otherwise>  
                        </c:choose>
                        <c:set var="contains" value="false" />
                        <c:set var="element" value="0" />
                        <div class="text-right">
                            <c:choose>
                                <c:when test="${not empty Sesrole and Sesrole eq '3'}">          
                                    <c:forEach items="${userList}" var="userList">
                                        <c:if test="${userList.books.id eq item.id}">
                                            Данная книга в избранном:
                                            <c:set var="contains" value="true" />
                                            <c:set var="element" value="${userList.id}" />
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${contains eq 'false'}">
                                        <a class="btn btn-success" href="${pageContext.request.contextPath}/addToUserBookList/${item.id}.htm">+</a>
                                    </c:if>
                                    <c:if test="${contains eq 'true'}">
                                        <a class="btn btn-warning" href="${pageContext.request.contextPath}/deleteLikes/${element}.htm">-</a>
                                    </c:if>
                                    <c:if test="${not empty item.server_Path}">
                                        <a class="btn btn-success" href ="${pageContext.request.contextPath}/uploads/${item.server_Path}">Скачать</a>
                                    </c:if>
                                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/delete/${item.id}.htm">Удалить</a>
                                    <a class="btn btn-danger" href="${pageContext.request.contextPath}/edit/${item.id}.htm">Изменить</a>
                                </c:when>
                                <c:when test="${not empty Sesrole}">
                                    <c:forEach items="${userList}" var="userList">
                                        <c:if test="${userList.books.id eq item.id}">
                                            Данная книга в избранном:
                                            <c:set var="contains" value="true" />
                                            <c:set var="element" value="${userList.id}" />
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${contains eq 'false'}">
                                        <a class="btn btn-success" href="${pageContext.request.contextPath}/addToUserBookList/${item.id}.htm">+</a>
                                    </c:if>
                                    <c:if test="${contains eq 'true'}">
                                        <a class="btn btn-warning" href="${pageContext.request.contextPath}/deleteLikes/${element}.htm">-</a>
                                    </c:if>
                                    <c:if test="${not empty item.server_Path}">
                                        <a class="btn btn-success" href ="${pageContext.request.contextPath}/uploads/${item.server_Path}">Скачать</a>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${empty details}">
                                <a class="btn btn-success" href="${pageContext.request.contextPath}/deteils/${item.id}.htm">Подробнее</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>   
        </c:forEach>    
    </div>
</div>
<!-- /.container -->
<jsp:include page="includes/footer.jsp" flush="true"/>
