<%@ page import="com.example.mevenproject.document.Student" %>
<%@ page import="java.util.List" %>
<!doctype html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <title>Document</title>


    <style>
        body {
            margin:0;
            padding:0;
            font-family: sans-serif;
            background: linear-gradient(#141e30, #243b55);
        }

        .h1, h1 {
            margin-top: 0.5rem;
            margin-bottom: 0.5rem;
            font-weight: 500;
            line-height: 1.2;
            color: #ced4da;
        }
    </style>
</head>
<% Student student= (Student) request.getAttribute("studentData");%>
<% List<Student> students= (List<Student>) request.getAttribute("students");%>
<body>
<div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Section List</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav" style="float: right; padding-right: 30px;margin-left: 900px;">
            <ul class="navbar-nav">
                <li class="nav-item"  style="float: right; margin-right: 0px; " >
                    <a class="nav-link" href="#"  style="float: right; padding-right: 30px;">Welcome <br>
                        <%=student.getName()%> <span class="sr-only"></span></a>
                </li>
            </ul>
        </div>
    </nav>
    <table class="table table-info">
        <thead>
        <tr>
            <th> Name </th>
            <th> Roll No. </th>
            <th> Section </th>
            <th> Email</th>
            <th>JavaMarks</th>
            <th>SqlMarks</th>
        </tr>
        </thead>
        <%
            for(int i = 0; i < students.size(); i++){
        %>
        <tbody>
        <tr >
            <td><span > <%=students.get(i).getName()%> </span></td>
            <td><span > <%=students.get(i).getRollno()%></span></td>
            <td><span > <%=students.get(i).getSection()%></span></td>
            <td><span > <%=students.get(i).getEmail()%></span></td>

            <td><span > <%=students.get(i).getJavaMarks()%></span></td>
            <td><span > <%=students.get(i).getSqlMarks()%></span></td>
        </tr>
        <%}%>

        </tbody>
    </table>

</div>
</body>
</html>
