<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<body>
    <%@ include file="navBar.jsp" %>
        <h1>Search User</h1>
        <p class="text-center">Search User by username</p>
        <div class="container">
                    <h2>Search User</h2>
                    <form action="/javaLearnApp/users" method="get">
                        <div class="form-group">
                            <label for="Username">Email:</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
    </body>
</html>
