<%--
  Copyright codeu-sp18-team17.

  Where users manage their personal profile and conversations

  @author Huihan Li
  @since March 31, 2018
--%>
<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.User" %>
<%@ page import="codeu.model.data.Conversation" %>
<%@ page import="codeu.model.store.basic.UserStore" %>

<!DOCTYPE html>
<html>
<head>
  <title>Profile</title>
  <link rel="stylesheet" href="/css/main.css">
</head>

<body>

  <nav>
    <a id="navTitle" href="/">CodeU Chat App</a>
    <a href="/conversations">Conversations</a>
    <% if(request.getAttribute("user") != null){ %>
      <a>Hello <%= ((User)request.getAttribute("user")).getName() %>!</a>
    <% } %>

    <a href="/about.jsp">About</a>
  </nav>


  <div id="container">

    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>

    <% if(request.getAttribute("user") != null){ %>
      <h1><%= ((User)request.getAttribute("user")).getName() %>'s Profile Page</h1>

      <hr/>

      <h2>About <%= ((User)request.getAttribute("user")).getName() %></h2>
      <p>
        <%
          String profile = UserStore.getInstance().getUser(((User)request.getAttribute("user")).getName()).getProfile();
        %>
        <%= profile %>
      </p>

      <form action="<%= request.getRequestURI() %>" method="POST">
          <div class="form-group">
            <h3>Edit your About Me (only you can see this)</h3>
          <input type="text" name="profile">
        </div>

        <button type="submit">Submit</button>
      </form>

      <hr/>
    <% } %>


  </div>


</body>
</html>
