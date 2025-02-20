<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
                    <form action="addBook" method="post">
                        <div class="form-group">
                            <label for="name">Book Name:</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>
                        <div class="form-group">
                            <label for="lastname">Count of Pages:</label>
                            <input type="number" class="form-control" id="countPages" name="countPages" required>
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
                    </form>
                </div>
    </body>
</html>