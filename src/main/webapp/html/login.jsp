<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<body>
    <%@ include file="navBar.jsp" %>
        <h1>Login with Your User</h1>
        <p class="text-center">Login with your user</p>
        <div class="container">
                    <h2>Login</h2>
                    <form action="/javaLearnApp/login" method="post">
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text" class="form-control" id="username" name="username" required />
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" id="password" name="password" required />
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                        <c:if test="${param.error != null}">
                            <div style="color: red;">Invalid username or password.</div>
                        </c:if>
                    </form>
                </div>
    </body>
</html>
