<%@ page import="com.example.mevenproject.document.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mevenproject.document.Teacher" %>
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
<%Teacher teacher= (Teacher) request.getAttribute("teacherData"); %>
<% List<Student> students= (List<Student>) request.getAttribute("students");%>
<% System.out.println(teacher);%>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">

  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="#">StudentDetails <span class="sr-only"></span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="http://localhost:9199/student/login">StudentLogin <span class="sr-only"></span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="http://localhost:9199/student/register">AddStudent</a>
      </li>
      <%--<li class="nav-item active" style="padding-left: 640px;">
        <a class="nav-link" href="#">Welcome<br> <%=teacher.getName()%> <span class="sr-only"></span></a>
      </li>--%>

    </ul>
  </div>
</nav>
<div>
  <table class="table table-info">
    <thead>
    <tr>
      <th> Name </th>
      <th> Roll No. </th>
      <th> Section </th>
      <th> Email</th>
      <th> delete</th>
      <th>Update</th>
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
      <td><a href="/teacher/deleteStudent?rollno=<%=students.get(i).getRollno()%>&section=<%=students.get(i).getSection()%>">delete</a></td>
      <%--<td><a href="http://localhost:9192/student/updateStudent/<%=students.get(i).getRollno()%>/<%=students.get(i).getSection()%>">update</a></td>--%>
      <%--?productID=<%=products.get(i).getProductID()--%>
      <td> <a href="http://localhost:9199/student/updateStudent/<%=students.get(i).getRollno()%>/<%=students.get(i).getSection()%>">update</a></td>
    </tr>
    <%}%>

    </tbody>
  </table>

</div>
</body>
</html>

