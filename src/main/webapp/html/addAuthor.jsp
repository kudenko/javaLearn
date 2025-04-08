<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="navBar.jsp" %>
        <h1>Add your author</h1>
        <p class="text-center">Enter author info to the form</p>
        <div class="container">
                    <h2>Add New Author</h2>
                    <c:if test="${not empty success}">
                        <p class="text-center">${success}</p>
                    </c:if>

                    <c:if test="${not empty error}">
                        <p class="text-center">${error}</p>
                    </c:if>
                    <form:form action="/javaLearnApp/authors/creation" modelAttribute="author" method="post">
                        <div class="form-group">
                            <form:label path="firstName">First Name:</form:label>
                            <form:input path="firstName" cssClass="form-control" />
                            <form:errors path="firstName" cssClass="text-danger" />
                        </div>
                        <div class="form-group">
                            <form:label path="lastName">Last Name:</form:label>
                            <form:input path="lastName" cssClass="form-control" />
                            <form:errors path="lastName" cssClass="text-danger" />
                        </div>
                        <div class="form-group">
                            <form:label path="email">Email:</form:label>
                            <form:input path="email" cssClass="form-control" />
                            <form:errors path="email" cssClass="text-danger" />
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form:form>
                </div>
    </body>
</html>