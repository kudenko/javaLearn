<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="navBar.jsp" %>
        <h1>Add your book</h1>
        <p class="text-center">Enter book info to the form</p>
        <div class="container">
                    <h2>Add New Book</h2>
                    <c:if test="${not empty success}">
                        <p class="text-center">${success}</p>
                    </c:if>

                    <c:if test="${not empty error}">
                        <p class="text-center">${error}</p>
                    </c:if>
                    <form:form action="/javaLearnApp/books" modelAttribute="book" method="post">
                        <div class="form-group">
                            <form:label path="name">Book Name:</form:label>
                            <form:input path="name" cssClass="form-control" required="true"/>
                            <form:errors path="name" cssClass="text-danger" />
                        </div>
                        <div class="form-group">
                            <form:label path="countPages">Count of Pages:</form:label>
                            <form:input path="countPages" cssClass="form-control" type="number" min="1" required="true"/>
                            <form:errors path="countPages" cssClass="text-danger" />
                        </div>
                        <div class="form-group">
                            <label for="author">Select Author:</label>
                                <select class="form-control" id="author" name="authorId" required>
                                    <option value="">-- Select an Author --</option>
                                        <c:forEach var="author" items="${authors}">
                                            <option value="${author.id}">${author.firstName} ${author.lastName}</option>
                                        </c:forEach>
                                </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form:form>
                </div>
    </body>
</html>
