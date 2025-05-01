<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<title>All Users</title>
        <link rel="stylesheet" href="<c:url value='/css/table.css' />" />
<%@ include file="navBar.jsp" %>
        <h1>All Users</h1>
                       <c:choose>
                               <c:when test="${not empty users}">
                                   <table>
                                       <thead>
                                           <tr>
                                               <th>Username</th>
                                               <th>ID</th>
                                               <th>First Name</th>
                                               <th>Last Name</th>
                                               <th>User Role</th>
                                               <th>User Status</th>
                                               <th>Action</th>
                                           </tr>
                                       </thead>
                                       <tbody>
                                           <c:forEach var="user" items="${users}">
                                               <tr>
                                                   <td><a href="<c:url value='/users/${user.id}'/>">${user.userName}</a></td>
                                                   <td>${user.id}</td>
                                                   <td>${user.firstName}</td>
                                                   <td>${user.lastName}</td>
                                                   <td>${user.userRole}</td>
                                                   <td>${user.userStatus}</td>
                                                   <td><a href="<c:url value='/users/${user.id}'/>">view</a>
                                                   |
                                                   <a href="<c:url value='/users/${user.id}/edit'/>">edit</a></td>
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
