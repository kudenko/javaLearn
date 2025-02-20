<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
                    <form action="addJournal" method="post">
                        <div class="form-group">
                            <label for="name">Journal Name:</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>
                        <div class="form-group">
                            <label for="countPages">Count of Pages:</label>
                            <input type="number" class="form-control" id="countPages" name="countPages" required>
                        </div>
                        <div class="form-group">
                            <label for="number">Number:</label>
                            <input type="number" class="form-control" id="number" name="number" required>
                        </div>
                        <div class="form-group">
                            <label for="number">Publication Year:</label>
                            <input type="number" class="form-control" id="publicationYear" name="publicationYear" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
    </body>
</html>
