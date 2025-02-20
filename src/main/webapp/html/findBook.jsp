<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<body>
    <%@ include file="navBar.jsp" %>
        <h1>Search your book</h1>
        <p class="text-center">Search Book</p>
        <div class="container">
                    <h2>Search Book</h2>
                    <form action="findBook" method="post">
                        <div class="form-group">
                            <label for="name">Book name:</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
    </body>
</html>