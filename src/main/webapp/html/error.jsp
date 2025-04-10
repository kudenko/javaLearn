<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <body>
        <%@ include file="navBar.jsp" %>
        <h1>Welcome to Your Library</h1>
        <p class="text-center">Explore our collection of books, journals, and authors!</p>
        <c:if test="${not empty error}">
            <p class="text-center">${error}</p>
        </c:if>
    </body>
</html>
