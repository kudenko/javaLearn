<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@ include file="navBar.jsp" %>
        <h1>Register new user</h1>
        <p class="text-center">User Registration</p>
        <div class="container">
                    <h2>Register New User</h2>
                    <c:if test="${not empty success}">
                        <p class="text-center">${success}</p>
                    </c:if>

                    <c:if test="${not empty error}">
                        <p class="text-center">${error}</p>
                    </c:if>
                    <form:form action="/javaLearnApp/users" modelAttribute="user" method="post">
                        <form:hidden path="userRole" value="ROLE_USER" />
                        <div class="form-group">
                            <form:label path="firstName">First Name:</form:label>
                            <form:input path="firstName" cssClass="form-control" required="true"/>
                            <form:errors path="firstName" cssClass="text-danger" />
                        </div>
                        <div class="form-group">
                            <form:label path="lastName">Last Name:</form:label>
                            <form:input path="lastName" cssClass="form-control" type="text" required="true"/>
                            <form:errors path="lastName" cssClass="text-danger" />
                        </div>
                        <div class="form-group">
                            <form:label path="userName">Username:</form:label>
                            <form:input path="userName" cssClass="form-control" type="text" required="true"/>
                            <form:errors path="userName" cssClass="text-danger" />
                        </div>
                        <div class="form-group">
                            <form:label path="userStatus">User Status:</form:label>
                                <form:select path="userStatus" cssClass="form-control" required="true">
                                    <form:option value="ACTIVE">Active</form:option>
                                    <form:option value="INACTIVE">Inactive</form:option>
                                </form:select>
                            <form:errors path="userStatus" cssClass="text-danger" />
                        </div>
                        <div class="form-group">
                            <form:label path="password">Password:</form:label>
                            <form:input path="password" cssClass="form-control" type="password" required="true"/>
                            <form:errors path="password" cssClass="text-danger" />
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form:form>
                </div>
    </body>
</html>
