<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<body>
    <%@ include file="navBar.jsp" %>
        <h1>Find your journal</h1>
        <p class="text-center">Find your journal</p>
        <div class="container">
                    <h2>Search Journal</h2>
                    <form action="/javaLearnApp/journals" method="get">
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" class="form-control" id="name" name="name" required="true"/>
                        </div>
                        <div class="form-group">
                            <label for="year">Year:</label>
                            <input type="number" class="form-control" id="year" name="year" required="true"/>
                        </div>
                        <div class="form-group">
                            <label for="number">Number:</label>
                            <input type="number" class="form-control" id="number" name="number" required="true"/>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
    </body>
</html>
