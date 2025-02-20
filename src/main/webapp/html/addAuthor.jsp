<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
                    <form action="addAuthor" method="post">
                        <div class="form-group">
                            <label for="name">First Name:</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>
                        <div class="form-group">
                            <label for="lastname">Last Name:</label>
                            <input type="text" class="form-control" id="lastname" name="lastname" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
    </body>
</html>
