<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<title>User with id ${user.id}</title>
        <link rel="stylesheet" href="<c:url value='/javaLearnApp/css/table.css' />" />
<%@ include file="navBar.jsp" %>
<c:if test="${not empty error}">
    <p class="text-center">${error}</p>
</c:if>
        <h1>User with id ${user.id}</h1>
                       <c:choose>
                               <c:when test="${not empty user}">
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
                                               <tr>
                                                   <td>${user.userName}</td>
                                                   <td>${user.id}</td>
                                                   <td>${user.firstName}</td>
                                                   <td>${user.lastName}</td>
                                                   <td>${user.userRole}</td>
                                                   <td>${user.userStatus}</td>
                                                   <td><a href="<c:url value='/users/${user.id}/edit'/>">edit</a></td>
                                               </tr>
                                       </tbody>
                                   </table>
                               </c:when>
                               <c:otherwise>
                                   <p>No User found with id: ${user.id}.</p>
                               </c:otherwise>
                           </c:choose>
    </body>
</html>
