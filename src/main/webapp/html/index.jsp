<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <body>
        <%@ include file="navBar.jsp" %>
        <h1>Welcome to Your Library</h1>
        <p class="text-center">Explore our collection of books, journals, and authors!</p>

        <ul>
            <c:forEach var="item" items="${items}">
                <li>${item}</li>
            </c:forEach>
        </ul>
    </body>
</html>
