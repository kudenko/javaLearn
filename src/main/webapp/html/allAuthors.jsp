<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<title>All Authors</title>
    <link rel="stylesheet" type="text/css" href="/javaLearnApp/css/table.css">
<%@ include file="navBar.jsp" %>
        <h1>All Authors</h1>
                       <c:choose>
                               <c:when test="${not empty authors}">
                                   <table>
                                       <thead>
                                           <tr>
                                               <th>ID</th>
                                               <th>First Name</th>
                                               <th>Last Name</th>
                                               <th>Email</th>
                                           </tr>
                                       </thead>
                                       <tbody>
                                           <c:forEach var="author" items="${authors}">
                                               <tr>
                                                   <td>${author.id}</td>
                                                   <td>${author.firstName}</td>
                                                   <td>${author.lastName}</td>
                                                   <td>${author.email}</td>
                                               </tr>
                                           </c:forEach>
                                       </tbody>
                                   </table>
                               </c:when>
                               <c:otherwise>
                                   <p>No authors found.</p>
                               </c:otherwise>
                           </c:choose>
    </body>
</html>
