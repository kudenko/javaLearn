<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<title>All Books</title>
    <link rel="stylesheet" href="<c:url value='/javaLearnApp/css/table.css' />" />
<%@ include file="navBar.jsp" %>
<h1>All Books</h1>
       <c:choose>
           <c:when test="${not empty books}">
               <table>
                   <thead>
                       <tr>
                           <th>ID</th>
                           <th>Name</th>
                           <th>Count of Pages</th>
                           <th>Author</th>
                       </tr>
                   </thead>
                   <tbody>
                       <c:forEach var="book" items="${books}">
                           <tr>
                               <td>${book.id}</td>
                               <td>${book.name}</td>
                               <td>${book.countPages}</td>
                               <td>${book.author.firstName} ${book.author.lastName}</td>
                           </tr>
                       </c:forEach>
                   </tbody>
               </table>
           </c:when>
           <c:otherwise>
               <p>No books found.</p>
           </c:otherwise>
       </c:choose>
    </body>
</html>
