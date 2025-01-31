<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Library</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            body {
                background-image: url('https://example.com/your-image.jpg'); /* Replace with your image URL */
                background-size: cover;
                background-position: center;
                background-attachment: fixed;
                color: white;
                text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
            }
            h1 {
                text-align: center;
                margin-top: 100px;
                font-size: 3em;
            }
            .navbar {
                background-color: rgba(0, 0, 0, 0.7) !important;
            }
            .navbar-brand, .navbar-nav > li > a {
                color: white !important;
            }
            .navbar-brand:hover, .navbar-nav > li > a:hover {
                color: #f8f8f8 !important;
            }
            ul {
                text-align: center;
                list-style-type: none;
                padding: 0;
            }
            li {
                font-size: 1.2em;
                margin: 10px 0;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="/">Library</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="/javaLearnApp/index">Home</a></li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Books <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/javaLearnApp/findBookForm">Find</a></li>
                            <li><a href="/javaLearnApp/createBookForm">Create</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Journals <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Find</a></li>
                            <li><a href="#">Create</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Authors <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Find</a></li>
                            <li><a href="/javaLearnApp/addAuthor">Create</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>

        <h1>Search your book</h1>
        <p class="text-center">Enter book info to the form</p>
        <input class="form-control" type="text" placeholder="Book's name" aria-label="book's name">

        <ul>
            <c:forEach var="item" items="${items}">
                <li>${item}</li>
            </c:forEach>
        </ul>
    </body>
</html>
