<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<title>All Journals</title>
    <link rel="stylesheet" href="<c:url value='/javaLearnApp/css/table.css' />" />
<%@ include file="navBar.jsp" %>
<h1>All Journals</h1>
       <c:choose>
           <c:when test="${not empty journals}">
               <table>
                   <thead>
                       <tr>
                           <th>ID</th>
                           <th>Name</th>
                           <th>Count of Pages</th>
                           <th>Number</th>
                           <th>Publication Year</th>
                       </tr>
                   </thead>
                   <tbody>
                       <c:forEach var="journal" items="${journals}">
                           <tr>
                               <td>${journal.id}</td>
                               <td>${journal.name}</td>
                               <td>${journal.countPages}</td>
                               <td>${journal.number}</td>
                               <td>${journal.publicationYear}</td>
                           </tr>
                       </c:forEach>
                   </tbody>
               </table>
           </c:when>
           <c:otherwise>
               <p>No Journals found.</p>
           </c:otherwise>
       </c:choose>
    </body>
</html>
