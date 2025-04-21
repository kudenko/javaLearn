<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="navBar.jsp" %>
        <h1>Add your Journal</h1>
        <p class="text-center">Enter Journal info to the form</p>
        <c:if test="${not empty success}">
            <p class="text-center">${success}</p>
        </c:if>

        <c:if test="${not empty error}">
            <p class="text-center">${error}</p>
        </c:if>
        <div class="container">
                    <h2>Add New Journal</h2>
                    <form:form action="/javaLearnApp/journals" modelAttribute="journal" method="post">
                        <div class="form-group">
                            <form:label path="name">Journal Name:</form:label>
                            <form:input path="name" cssClass="form-control" required="true"/>
                            <form:errors path="name" cssClass="text-danger" />
                        </div>
                        <div class="form-group">
                            <form:label path="countPages">Count of Pages:</form:label>
                            <form:input path="countPages" cssClass="form-control" required="true" type="number" min="1"/>
                            <form:errors path="countPages" cssClass="text-danger" />
                        </div>
                        <div class="form-group">
                            <form:label path="number">Number:</form:label>
                            <form:input path="number" cssClass="form-control" required="true" type="number" min="1"/>
                            <form:errors path="number" cssClass="text-danger" />
                        </div>
                        <div class="form-group">
                            <form:label path="publicationYear">Publication Year:</form:label>
                            <form:input path="publicationYear" cssClass="form-control" required="true" type="number" min="1" max="3000"/>
                            <form:errors path="publicationYear" cssClass="text-danger" />
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form:form>
                </div>
    </body>
</html>
