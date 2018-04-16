<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.Conversation" %>
<%@ page import="codeu.model.data.Message" %>
<%@ page import="codeu.model.store.basic.UserStore" %>
<%
List<String> statistics = (List<String>) request.getAttribute("statistics");
%>

<!DOCTYPE html>
<html>
<head>
 <title>Admin</title>
 <link rel="stylesheet" href="/css/main.css" type="text/css">
 <style>
    #chat {
      background-color: white;
      height: 500px;
      overflow-y: scroll
    }
  </style>
</head>
<body>
<<<<<<< HEAD
        <nav>
            <a id="navTitle" href="/">CodeU Chat App</a>
            <a href="/conversations">Conversations</a>
                <% if (request.getSession().getAttribute("user") != null) { %>
            <a>Hello <%= request.getSession().getAttribute("user") %>!</a>
            <% } else { %>
                <a href="/login">Login</a>
                <a href="/register">Register</a>
            <% } %>
            <a href="/about.jsp">About</a>
            </nav>
<div id="container">
 <h2>Administration</h2>

 <h3>Site Statistics</h3>
 <p> Here are some site stats:</p>
 <div id="stats">
=======
 <h1>Admin</h1>
 <p>Temporary jsp. Bigger and better coming soon!</p>
 <div id-"stats">
>>>>>>> 284dec307db499b8cc20f910fdc71d2dc2cb3e89
     <ul>
  <%
        for (String stat : statistics) {
      %>
      <li><%= stat %></li>
      <%
     }
      %>
     </ul>
 </div>
<<<<<<< HEAD
 </div>
=======
>>>>>>> 284dec307db499b8cc20f910fdc71d2dc2cb3e89
</body>
</html>
