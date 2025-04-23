<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<title>All Users</title>
        <link rel="stylesheet" href="<c:url value='/javaLearnApp/css/table.css' />" />
<%@ include file="navBar.jsp" %>
        <h1>All Users</h1>
                       <c:choose>
                               <c:when test="${not empty users}">
                                   <table>
                                       <thead>
                                           <tr>
                                               <th>ID</th>
                                               <th>First Name</th>
                                               <th>Last Name</th>
                                               <th>Username</th>
                                               <th>User Role</th>
                                               <th>User Status</th>
                                           </tr>
                                       </thead>
                                       <tbody>
                                           <c:forEach var="user" items="${users}">
                                               <tr>
                                                   <td>${user.id}</td>
                                                   <td>${user.firstName}</td>
                                                   <td>${user.lastName}</td>
                                                   <td>${user.userName}</td>
                                                   <td>${user.userRole}</td>
                                                   <td>${user.userStatus}</td>
                                               </tr>
                                           </c:forEach>
                                       </tbody>
                                   </table>
                               </c:when>
                               <c:otherwise>
                                   <p>No Users found.</p>
                               </c:otherwise>
                           </c:choose>
    </body>
</html>
