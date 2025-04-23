 <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
 <head>
        <title>Library</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="<c:url value='/javaLearnApp/css/styles.css' />" />
</head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/javaLearnApp/index">Library</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="/javaLearnApp/index">Home</a></li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Books <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/javaLearnApp/books">All Books</a></li>
                            <li><a href="/javaLearnApp/books/search">Find</a></li>
                            <li><a href="/javaLearnApp/books/creation">Create</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Journals <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/javaLearnApp/journals">All Journals</a></li>
                            <li><a href="/javaLearnApp/journals/search">Find</a></li>
                            <li><a href="/javaLearnApp/journals/creation">Create</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Authors <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/javaLearnApp/authors">All Authors</a></li>
                            <li><a href="/javaLearnApp/authors/search">Find</a></li>
                            <li><a href="/javaLearnApp/authors/creation">Create</a></li>
                        </ul>
                    </li>

                    <security:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Users <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/javaLearnApp/users">All Users</a></li>
                            <li><a href="/javaLearnApp/users/search">Find</a></li>
                            <li><a href="/javaLearnApp/users/creation">Create</a></li>
                        </ul>
                    </li>
                   </security:authorize>

                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/javaLearnApp/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
            </div>
        </nav>
    </body>
