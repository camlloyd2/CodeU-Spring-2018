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
<%
  User user = (User)request.getAttribute("user");
%>

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
      <a>Hello <%= user.getName() %>!</a>
    <% } %>

    <a href="/about.jsp">About</a>
  </nav>


  <div id="container">

    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>

    <% if(request.getAttribute("user") != null){ %>
      <h1><%= user.getName() %>'s Profile Page</h1>

      <hr/>

      <h2>About <%= user.getName() %></h2>
      <p style="width: 800px; word-wrap: break-word;">
        <%
          String profile = UserStore.getInstance().getUser(user.getName()).getProfile();

          if (profile==null)
            profile = user.getName() + " has not set their \"About me\" yet.";

          System.out.println("Profile:"+ profile);
        %>
        <%= profile %>
      </p>

      <form action="/profile/<%= user.getName()%>" method="POST">
        <div class="form-group">
          <h3>Edit your <b>About Me</b> (only you can see this)</h3>

          <textarea style="height:100px;width:100%;font-family:Arial;border:1px solid #a6a6a6; background-color:white; resize:none" wrap="hard" size="1000" placeholder="1000 character limit..." maxlength="1000" type="text" name="profile"></textarea>

        </div>
        <br>
        <button type="submit">Submit</button>
      </form>

      <hr/>
    <% } %>


  </div>


</body>
</html>
